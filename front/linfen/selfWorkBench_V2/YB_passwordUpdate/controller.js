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
app.controller('main', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'));
	$scope.operation = "请选择登录方式";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go('login');
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$.state.go("main");
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办";
			break;
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
		} else {
			layer.msg("很抱歉,没有获取到您的信息,请重试")
		}
	}

	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("info");
	}

	$scope.prevStep = function() {
		$.device.Face_Close();
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
			//					jsonp: "jsonpCallback",
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
				facePhoto: capturePhoto,
				copyIDPhoto: idCardPhoto
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true && res.verify === 1) {
					$rootScope.tokenSNO = res.tokenSNO;
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
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			$scope.getAccessToken(appData.tokenSNO);
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			$scope.getTokenSNO(photo,photo);
		}

	}
})
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.funName = "填写个人信息";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.concel = "false";
	$scope.nextText = "提交";
	$scope.consignerName = appData.licenseName;
	$scope.consignerID = appData.licenseNumber;
	// 判断是否有多个账户号
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token,
		function(data) {
			if(data[0].cbrxxs.length == 1) {
				appData.zhh = data[0].cbrxxs[0].cbrxx[0].zhh;
				$scope.ZHH = appData.zhh;
			} else {
				$scope.isAlert = true;
				$scope.msg = "账户有问题，无法操作";
			}
		},
		function(err) {
			$scope.isAlert = true;
			$scope.msg = "未查询到信息 ，请重试";
		});

	$scope.prevStep = function() {
		$state.go('main');
	}

	// 判断是否变更成功
	$scope.nextStep = function() {
		// 是否为空判断
		if(!$scope.ZHH) {
			$scope.isAlert = true;
			$scope.msg = "账户号未查询到";
			return;
		} else if($scope.ZHH.length != 8) {
			$scope.isAlert = true;
			$scope.msg = "账户号长度为8位";
			return;
		}
		if(!$scope.oldPassword) {
			$scope.isAlert = true;
			$scope.msg = "请填写原密码";
			return;
		} else if($scope.oldPassword.length != 6) {
			$scope.isAlert = true;
			$scope.msg = "原密码长度为6位";
			return;
		}
		if(!$scope.password) {
			$scope.isAlert = true;
			$scope.msg = "请填写新密码";
			return;
		} else if($scope.password.length != 6) {
			$scope.isAlert = true;
			$scope.msg = "修改后的密码长度应为6位";
			return;
		}
		if(!$scope.consignerMobile) {
			$scope.isAlert = true;
			$scope.msg = "请填写手机号";
			return;
		} else {
			if(!validateTel($scope.consignerMobile)) {
				$scope.isAlert = true;
				$scope.msg = "手机号格式不正确!"
				return;
			}
		}
		// 新旧密码的对比
		if($scope.password == $scope.oldPassword) {
			$scope.isAlert = true;
			$scope.msg = "更改后的密码不能与原密码相同";
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/MedicalInstitution/updatePassword.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				ZHH: appData.zhh,
				oldPassword: $scope.oldPassword,
				password: $scope.password,
				consignerName: appData.licenseName,
				consignerID: appData.licenseNumber,
				consignerMobile: $scope.consignerMobile,
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.success) {
					$scope.isAlert = true;
					$scope.msg = "变更成功";
				} else {
					if(dataJson.data) {
						$scope.isAlert = true;
						$scope.msg = dataJson.data[0].errmsg;
					} else {
						$scope.isAlert = true;
						$scope.msg = "变更失败";
					}
				}
				console.log(dataJson);
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "访问失败";
				$.log.debug("err:" + JSON.stringify(err))
			}
		});


		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: "办理医保网站密码申请及变更",
				Number: ""
			}
		}
		recordUsingHistory('医保服务', '办理', "办理医保网站密码申请及变更", appData.licenseName, appData.licenseNumber, $scope.consignerMobile, "", JSON.stringify($scope.jsonStr));
		trackEventForAffairs($scope.applyNo, '办理', '上海市医疗保障局', appData.licenseName, appData.licenseNumber, $scope.consignerMobile)
	}

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});