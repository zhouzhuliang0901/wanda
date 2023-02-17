function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}
function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller("guideline", function($scope, $state, appData) {
	$scope.operation = "申请大龄失业就业岗位补贴办事指南";
	trackEvent($(".headName").text());
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		// window.location.href="../aSocial/index.html#";
		$.device.GoHome();
	}
	$scope.nextStep = function() {
		$state.go("loginType");
		$scope.isLoading = true;
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true,
		});
	};
	$scope.isScroll();
});
app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	appData.funName = "申请大龄失业就业岗位补贴";
	appData.itemCode = "312090054000";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("guideline");
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
			dataType: "json",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					appData.token = res.accessToken;
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
				name: encodeURI(appData.licenseName),
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
			// $scope.faceImage = images;
			// $scope.loginType = 'recognition';
			// appData.licenseNumber = info.Number;
			// appData.licenseName = info.Name;
					$scope.faceImage = images;
					appData.licenseNumber = info.Number;
					appData.licenseName = info.Name;
					appData.VALIDENDDAY = info.ValidtermOfEnd;
					appData.VALIDSTARTDAY = info.ValidtermOfStart;
					$scope.loginType = 'recognition';
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
		$state.go("info");
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
app.controller('info', function($state, $scope, appData, $location, appFactory, $timeout, $rootScope) {
	$scope.isLoading = true;
	$scope.getUserInfo = function() {
		$scope.applicationInfo = {
			"Cetf_id":appData.licenseNumber,
			"Person_name":encodeURI(appData.licenseName)
		}
		// $scope.applicationInfo = {
		// 	"Cetf_id":"310104196403132018",
		// 	"Person_name":"胡泽敏"
		// }
		console.log(JSON.stringify($scope.applicationInfo))
		$.ajax({//办理大龄失业就业岗位补贴申请判断接口（LD0096Q1）
			type: "POST",
			url: $.getConfigMsg.preUrlSelf + '/selfapi/UnemploymentRegistration/LD0096Q1',
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0096Q1",
			dataType: 'json',
			data: {
				json: JSON.stringify($scope.applicationInfo)
			},
			success: function(dataJson) {
				console.log("返回参数"+ JSON.stringify(dataJson))
				// console.log(dataJson.msg.body)
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.msg.head;
					$scope.body = dataJson.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							appData.khyh = dataJson.msg.body.khyh,//开户银行
							appData.yhzh = dataJson.msg.body.yhzh,//银行账号
							appData.sqksrq = dataJson.msg.body.sqksrq,//申请开始日期
							appData.sqqx = dataJson.msg.body.sqqx,//申请期限
							appData.sqjsrq = dataJson.msg.body.sqjsrq,//申请结束日期
							appData.sqrq = dataJson.msg.body.sqrq,//申请日期
							$scope.stName = appData.licenseName,
							$scope.stIdCard = appData.licenseNumber,
							$scope.khyh = appData.khyh;
							$scope.yhzh = appData.yhzh;
							$scope.sqksrq = getNowFormatDate4(appData.sqksrq);
							$scope.sqqx = appData.sqqx;
							$scope.sqjsrq = getNowFormatDate4(appData.sqjsrq);
							$scope.isLoading = false
						} else {
							$scope.isAlert = true;
							$scope.msg =$scope.head.rst.errmsg.replace(/\[.*?\]/g,'');
							$scope.alertConfirm = function() {
								$state.go("loginType");
							}
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "查询接口异常,请稍后再试";
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "查询接口异常,请稍后再试";
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "查询接口异常,请稍后再试";
			}
		});
	}
	$scope.getUserInfo();
	$scope.funName = appData.funName;
	$scope.nextText = "提交"
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		console.log("token:"+appData.token);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log("ssssuid"+dataJson.zwdtsw_user_id);
				if(dataJson.zwdtsw_user_id != undefined && dataJson.zwdtsw_user_id != null && dataJson.zwdtsw_user_id != "") {
					appData.zwdtsw_user_id = dataJson.zwdtsw_user_id;
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.ERROR || '未获得对应的用户标识！';
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log("getUserInfoByAccessToken err");
				$scope.isAlert = true;
				$scope.msg = '未获得对应的用户标识！';
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
		if(appData.zwdtsw_user_id) {} else {
			$scope.getUserInfoByAccessToken();
		}
	//办件入库
	$scope.submitInfo = function(result){
		$http.get($.getConfigMsg.preUrlSelf + "/selfapi/pensionAdjustment/saveItemInfo.do", {
			params: {
				userId: appData.zwdtsw_user_id,
				itemCode: encodeURI(appData.itemCode),
				itemName: encodeURI(appData.funName),
				username: encodeURI(appData.licenseName),
				mobile: $scope.stMobile,
				idCardNo: appData.licenseNumber,
				result: result
			}
		}).success(function(dataJson) {
			if(dataJson.code == "200") {
				appData.applyNo = dataJson.data.applyNo;
				if(result == "1") {
					$state.go("submit");
				} else {
					$state.go("loginType");
				}
			} else {
				$scope.isLoading = false;
				appData.applyNo = "";
				$scope.isAlert = true;
				$scope.msg = "办件信息同步失败,请稍后再试";
			}
		}).error(function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "办件信息同步异常,请稍后再试";
		});
	}
	$scope.nextStep = function() {
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$scope.applicationInfo1 = {
			// "Person_name":"汤银根",//姓名
			// "Cetf_id":"310228196301102615",//证件号码
			"Person_name":$scope.stName,//姓名
			"Cetf_id":$scope.stIdCard,//证件号码
			"sqqx":appData.sqqx,//申请期限
			"sqksrq":appData.sqksrq,//申请开始日期
			"sqjsrq":appData.sqjsrq,//申请结束日期
			"sftqygy":"0",//是否提前一个月 直接填0
			"sfbf":"0",//是否补发 直接填0
			"bfys":"0",//补发月数 直接填0
			"bfyf":"",//补发月份 直接填空
	}
	console.log("提交参数："+JSON.stringify($scope.applicationInfo1))
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/UnemploymentRegistration/LD0096S1",
			// url: "http://localhost:8080/ac-self/selfapi/UnemploymentRegistration/LD0096S1",
			dataType: "json",
			data: {
				json: JSON.stringify($scope.applicationInfo1)
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.msg.head.rst.buscode == "000000") {
					$scope.isAlert = true;
					$scope.msg = "提交成功";
					$scope.submitInfo("1");
				} else {
					$scope.isAlert = true;
					// $scope.msg = "提交失败请重试！";
					$scope.msg =dataJson.msg.head.rst.errmsg;
					$scope.submitInfo("0");
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "提交失败，请重试";
				return;
				console.log("sendYwtbApplyInfo err");
			}
		});
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
			checkDOMChanges: true,
		});
	};
	$scope.isScroll();
});
app.controller("submit", function($scope, $state, appData, $sce, appFactory) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
		//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('人社服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});