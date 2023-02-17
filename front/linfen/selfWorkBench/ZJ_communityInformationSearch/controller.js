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
					$state.go("info");
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
			$scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
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
	$scope.funName = appData.funName = "小区信息查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
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
				"name":spaceName,
				"pageSize":"100"
			}
			console.log("$scope.applicationInfo1="+JSON.stringify($scope.applicationInfo1))
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/PropertyInquiry/getCommunityList.do",
				//  url: "http://localhost:8080/ac-self/selfapi/PropertyInquiry/getCommunityList.do",
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
					$timeout(function() {
						$scope.communityList = dataJson.data.content;//我就想延时一秒 没理由
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
	
		console.log("小区序号"+codeV)
		appData.spaceInfoList = filterBySpaceName($scope.communityList,codeV)
		console.log("当前小区信息"+JSON.stringify(appData.spaceInfoList))
		$timeout(function() {
			$state.go("info")
		}, 1000)
		
		
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
		});
	
	};
	$scope.isScroll();
	
});

app.controller("info", function($scope, $state, appData, $sce,$timeout) {
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
	//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('住建服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName,'','查询','上海市住房和城乡建设委员会','','','');
	// $scope.spaceInfoList = communityList.data.content[0];
	$scope.spaceInfoList = appData.spaceInfoList[0];
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}		
	$scope.prevStep = function() {
		$state.go('choose');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: false,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});