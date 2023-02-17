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
app.controller('loginType', function($state, $scope, appData, $http) {
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
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout, $rootScope) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = false;
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
			$scope.operation = "随申办登录";
			break;
	}
	
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("info");
		}
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
	$scope.citizenLogin = function(info) {
		if(info) {
			if(appData.qrCodeType == "suishenma") {
				appData.licenseName = info.zwdtsw_name;
				appData.licenseNumber = info.zwdtsw_cert_id;
				$scope.nextStep();
			} else {
				var idcardInfo = info.result.data;
				appData.licenseName = idcardInfo.realname;
				appData.licenseNumber = idcardInfo.idcard;
				$scope.nextStep();
			}
		}
	}
})
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.caption = "办理医疗卡挂失及撤销";
	$scope.isAlert = false;
	$scope.isLoading = true;
	$scope.concel = "false";
	$scope.licenseName = appData.licenseName;
	$scope.licenseNumber = appData.licenseNumber;
	$scope.$watch("$viewContentLoaded", function() {
		$("#cardData").focus();
		$("#cardData").select();
	})
	//获取医保卡号 
	$scope.getYBcard = function(zhh) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/queryCardNo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				zhh: appData.zhh,
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.YBKH = dataJson.body.ybkh;
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "访问失败";
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
	}

	// 判断是否有多个账户号
	appFactory.pro_fetch(appData.licenseNumber, appData.licenseName, appData.token,
		function(data) {
			console.log(data);
			$scope.isLoading = false;
			if(data[0].cbrxxs.length == 1) {
				appData.zhh = data[0].cbrxxs[0].cbrxx[0].zhh;
				console.log(appData.zhh);
				$scope.ZHH = appData.zhh;
				$scope.getYBcard(appData.zhh);
			} else {
				$scope.isAlert = true;
				$scope.msg = "账户有问题，无法操作";
			}
		},
		function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "未查询到信息 ，请重试";
		}
	);

	// 挂失
	$scope.lose = function() {
		if(!appData.zhh) {
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/medicalInsuranceApply/reportTheLossOfCard.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				zhh: appData.zhh,
				kh: $scope.YBKH,
				wtrxm: appData.licenseName,
				wtrsfzh: appData.licenseNumber,
				mkbz: "000",
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.body.lsh) {
					$scope.isAlert = true;
					$scope.msg = "挂失成功";
				} else {
					if(dataJson.head.rst.errmsg) {
						$scope.isAlert = true;
						$scope.msg = dataJson.head.rst.errmsg;
					} else {
						$scope.isAlert = true;
						$scope.msg = "挂失失败";
					}
				}
				console.log(dataJson);
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "访问失败";
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: "办理医疗卡挂失",
				Number: ""
			}
		}
		recordUsingHistory('医保服务', '办理', "办理医疗卡挂失", appData.licenseName, appData.licenseNumber, $scope.consignerMobile, "", JSON.stringify($scope.jsonStr));
		trackEventForAffairs('','办理','上海市医疗保障局',appData.licenseName,appData.licenseNumber,$scope.consignerMobile)
	}
	// 撤销
	$scope.revoke = function() {
		if(!$scope.cardData){
			layer.msg("请刷医保卡");
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/medicalInsuranceApply/cancellationOfLossReport.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				kh: $scope.YBKH,
				knsj: $scope.cardData,
				wtrxm: appData.licenseName,
				wtrsfzh: appData.licenseNumber,
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.body.lsh) {
					$scope.isAlert = true;
					$scope.msg = "撤销成功";
				} else {
					if(dataJson.head.rst.errmsg) {
						$scope.isAlert = true;
						$scope.msg = dataJson.head.rst.errmsg;
					} else {
						$scope.isAlert = true;
						$scope.msg = "撤销失败";
					}
				}
				console.log(dataJson);
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "访问失败";
				$.log.debug("err:" + JSON.stringify(err))
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: "办理医疗卡撤销",
				Number: ""
			}
		}
		recordUsingHistory('医保服务', '办理', "办理医疗卡撤销", appData.licenseName, appData.licenseNumber, $scope.consignerMobile, "", JSON.stringify($scope.jsonStr));
		trackEventForAffairs('','办理','上海市医疗保障局',appData.licenseName,appData.licenseNumber,$scope.consignerMobile)
	}

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
});