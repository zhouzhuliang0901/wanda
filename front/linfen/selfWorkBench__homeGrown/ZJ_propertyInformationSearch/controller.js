app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	appData.sign = "token";
	$scope.alertConfirm = function() {
		$state.go("loginType");
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}
		//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
					console.log("token1:"+appData.token)
					$state.go("choose");
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}
	//获取token ------1、两照对比获取tokenSNO
	$scope.getTokenSNO = function(face, photograph) {
		var idCardPhoto = face;
		var capturePhoto = photograph;
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
			type: "post",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				name: appData.licenseName,
				idCard: appData.licenseNumber,
				facePhoto: capturePhoto,
				copyIDPhoto: idCardPhoto
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true && res.verify === 1) {
					$scope.getAccessToken(res.tokenSNO);
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if(status === 'timeout' || status === 'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
					// $scope.faceImage = images;
					// appData.licenseNumber = info.Number;
					// appData.licenseName = info.Name;
					// appData.VALIDENDDAY = info.ValidtermOfEnd;
					// appData.VALIDSTARTDAY = info.ValidtermOfStart;
					// $scope.loginType = 'recognition';
			// appData.licenseNumber = '310228198808070818';
			// appData.licenseName = '陈雷';
			// $scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
		} else {
			layer.msg("没有获取到")
		}
	}
	// $scope.idcardLogin();
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("choose");
	}

	$scope.citizenLogin = function(info) {
		let idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			$scope.getAccessToken(appData.tokenSNO);
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			$scope.getTokenSNO(photo, photo);
			try {
				$scope.$apply();
			} catch(e) {}
		}
	}
});
app.controller("choose", function($scope, $state, appData,$timeout,$sce) {
	$scope.funName = appData.funName = "物业企业信息查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.spaceList = [];
	$scope.isDetial = true;
	$scope.isTip = false;
	// $scope.spaceList = spaceList.data.content
	$scope.getAuthorizationToken = function() {
		$scope.applicationInfo= {
			"access_token_zwdtsw": appData.token
		}
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/PropertyInquiry/doLogin.do",
			//  url: "http://localhost:8080/ac-self/selfapi/PropertyInquiry/doLogin.do",
			dataType: "json",
			// jsonp: "jsonpCallback",
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				console.log(dataJson.data.content)
				appData.AuthorizationToken  = dataJson.data.content;
					$scope.isLoading = false
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			}
		});
	}
	$scope.getAuthorizationToken();
	$scope.selectSpaceName = function(spaceName){
		$scope.isLoading = true
			$scope.applicationInfo1= {
				"propertyName":spaceName,
				"pageSize":"100"
			}
			console.log("$scope.applicationInfo1="+JSON.stringify($scope.applicationInfo1))
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/PropertyInquiry/getEnterpriseList.do",
				//  url: "http://localhost:8080/ac-self/selfapi/PropertyInquiry/getEnterpriseList.do",
				dataType: "json",
				// jsonp: "jsonpCallback",
				data: {
					Authorization:appData.AuthorizationToken,
					json: JSON.stringify($scope.applicationInfo1)
				},
				success: function(dataJson) {
					console.log("dataJson.data="+dataJson.data)
					$scope.isLoading = false
					$scope.isDetial = true
					$scope.isTip = true;
					$timeout(function() {
						$scope.spaceList = dataJson.data.content;//我就想延时一秒 没理由
						$scope.isLoading = false;
					}, 1000)
				},
				error: function(err) {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "接口异常";
				}
			});
	};

	$scope.spaceInfo = function(codeV){
		appData.spaceCode = codeV;
		console.log("企业编号"+codeV)
		$scope.applicationInfo2= {
			"propertyId":codeV
		}
		// console.log("$scope.applicationInfo2="+JSON.stringify($scope.applicationInfo2))
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/PropertyInquiry/getEnterpriseDetail.do",
			//  url: "http://localhost:8080/ac-self/selfapi/PropertyInquiry/getEnterpriseDetail.do",
			dataType: "json",
			// jsonp: "jsonpCallback",
			data: {
				Authorization:appData.AuthorizationToken,
				json: JSON.stringify($scope.applicationInfo2)
			},
			success: function(dataJson) {
				console.log("dataJson.data="+dataJson.data)
				appData.spaceInfoList = dataJson.data.content[0];
				$timeout(function() {
					$state.go('info');
				}, 1000)

			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			}
		});

	};
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		if($scope.isDetial){
			$.device.GoHome();
		} else {
			$scope.isDetial = true;
		}
	}

	$scope.isScroll = function() {
		 myiScroll = new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true,
			onScrollMove: function() {
			if (this.y <= this.maxScrollY ) {

			}
			}
		});

	};
	$scope.isScroll();

});
app.controller("info", function($scope, $state, appData, $sce,$timeout) {
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
	$scope.areaList = areaList
	$scope.isInfo1 = true;
	$scope.isInfo2 = false;
	$scope.isInfo3 = false;
	$scope.isInfo4 = false;
	$scope.spaceInfoList = appData.spaceInfoList;
	$scope.spaceInfoList2 =[]
	$scope.spaceInfoList3 =[]
	$scope.spaceInfoList4 =[]


	//企业各区在管小区数
	$scope.spaceInfo2 = function(){
		$scope.applicationInfo2= {
			"propertyId":appData.spaceCode
		}
		// console.log("$scope.applicationInfo2="+JSON.stringify($scope.applicationInfo2))
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/PropertyInquiry/getTotalCommunity.do",
			//  url: "http://localhost:8080/ac-self/selfapi/PropertyInquiry/getTotalCommunity.do",
			dataType: "json",
			// jsonp: "jsonpCallback",
			data: {
				Authorization:appData.AuthorizationToken,
				json: JSON.stringify($scope.applicationInfo2)
			},
			success: function(dataJson) {
				// console.log("dataJson.data="+dataJson.data)
				$scope.spaceInfoList2 = dataJson.data.content;
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			}
		});

	};
	//失信信息
	$scope.spaceInfo3 = function(){
		$scope.applicationInfo3= {
			"propertyId":appData.spaceCode,
			"deptType":"83"
		}
		console.log("$scope.applicationInfo3="+JSON.stringify($scope.applicationInfo3))
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/PropertyInquiry/getDishonestScore.do",
			//  url: "http://localhost:8080/ac-self/selfapi/PropertyInquiry/getDishonestScore.do",
			dataType: "json",
			// jsonp: "jsonpCallback",
			data: {
				Authorization:appData.AuthorizationToken,
				json: JSON.stringify($scope.applicationInfo3)
			},
			success: function(dataJson) {
				// console.log("dataJson.data="+dataJson.data)
				$scope.spaceInfoList3 = dataJson.data.content;
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			}
		});

	};
	//业绩信息
	$scope.spaceInfo4 = function(){
		$scope.applicationInfo4= {
			"propertyId":appData.spaceCode,
			"deptType":"83"
		}
		// console.log("$scope.applicationInfo4="+JSON.stringify($scope.applicationInfo4))
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/PropertyInquiry/getPerformanceInformation.do",
			//  url: "http://localhost:8080/ac-self/selfapi/PropertyInquiry/getPerformanceInformation.do",
			dataType: "json",
			// jsonp: "jsonpCallback",
			data: {
				Authorization:appData.AuthorizationToken,
				json: JSON.stringify($scope.applicationInfo4)
			},
			success: function(dataJson) {
				// console.log("dataJson.data="+dataJson.data)
				$scope.spaceInfoList4 = dataJson.data.content;
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			}
		});

	};
	$scope.spaceInfo2();
	$scope.spaceInfo3();
	$scope.spaceInfo4();
	$scope.change = function(name, index, id) {
		console.log(index)
		if(index == 0){
			$scope.isInfo1 = true;
			$scope.isInfo2 = false;
			$scope.isInfo3 = false;
			$scope.isInfo4 = false;
		}else if(index == 1){
			$scope.isInfo1 = false;
			$scope.isInfo2 = true;
			$scope.isInfo3 = false;
			$scope.isInfo4 = false;
		}else if(index == 2){
			$scope.isInfo1 = false;
			$scope.isInfo2 = false;
			$scope.isInfo3 = true;
			$scope.isInfo4 = false;
		}else if(index == 3){
			$scope.isInfo1 = false;
			$scope.isInfo2 = false;
			$scope.isInfo3 = false;
			$scope.isInfo4 = true;
		}
		$scope.current = index;
		$scope.queryList = [];
	};
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go('choose');
	}
	//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('住建服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName,'','查询','上海市住房和城乡建设委员会','','','');
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});