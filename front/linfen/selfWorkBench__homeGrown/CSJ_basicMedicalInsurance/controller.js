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
app.controller("guideline", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.funName = appData.funName = "基本医疗保险关系转移接续";
	$scope.prevStep = function() {
		window.location.href = '../CSJ_allItem/index.html';
	}
	//sign = 1 代表从长三角进入
	$scope.nextStep = function() {
		$state.go('loginType');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			click: true,
			taps: true,
			preventDefault: false,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	addAnimate($('.main2'))
});
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
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
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("loginType");
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
			$state.go('info');
		}
	}
//	$scope.idcardLogin = function() {
//		appData.licenseNumber = "430426199804106174";
//		appData.licenseName = "邹天奇";
//		$state.go('info');
//	}
//	$scope.idcardLogin();
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			appData.Address = info.Address;
			appData.nation = info.People;
			if(appData.nation.lastIndexOf('族') < 0) {
				appData.nation = appData.nation + '族';
			}
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go('info');
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
})
app.controller('info', function($state, $scope, appData, $rootScope, $timeout, appFactory, $http) {
	$scope.operation = "请选择查询条件";
	$scope.funName = appData.funName;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stIdCardType = "中华人民共和国居民身份证";
	$scope.licenseNumber = appData.encrypt_identity || appData.licenseNumber;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.isOutShangHai = false;
	$scope.isInShangHai = false;
	$scope.nextText = "提交";
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.provinceList = [{
		"code": "330000",
		"name": "浙江省",
		"parentCode": "000000"
	}, {
		"parentCode": "000000",
		"code": "320000",
		"name": "江苏省"
	}, {
		"parentCode": "000000",
		"code": "319900",
		"name": "上海市"
	}];

	$scope.$watch('outProvince', function(val) {
		if(val) {
			if(val.code == "319900") {
				$scope.isOutShangHai = true;
			} else {
				$scope.isOutShangHai = false;
				$scope.outCityList = filterByInfo($rootScope.allList, val.code, 'parentCode')
			}
		}
	})

	$scope.$watch('outCity', function(val) {
		if(val) {
			$scope.outCountyList = filterByInfo($rootScope.allList, val.code, 'parentCode')
		}
	})

	$scope.$watch('inProvince', function(val) {
		if(val) {
			if(val.code == "319900") {
				$scope.isInShangHai = true;
			} else {
				$scope.isInShangHai = false;
				$scope.inCityList = filterByInfo($rootScope.allList, val.code, 'parentCode')
			}
		}
	})

	$scope.$watch('inCity', function(val) {
		if(val) {
			$scope.inCountyList = filterByInfo($rootScope.allList, val.code, 'parentCode')
		}
	})
	//提交
	$scope.nextStep = function() {
		$scope.paramsInfo = form2arr($('#infoParams').serialize(), $(".in"));
		console.log($scope.paramsInfo);
		var condFlag = false;
		do {
			if(!isPhoneAvailable($scope.stMobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号";
				return
			}
			if(isBlank($scope.outProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择转出地信息（省）";
				return
			}
			if(isBlank($scope.outCity) && $scope.isOutShangHai == false) {
				$scope.isAlert = true;
				$scope.msg = "请选择转出地信息（市）";
				return
			}
			if(isBlank($scope.outCounty) && $scope.isOutShangHai == false) {
				$scope.isAlert = true;
				$scope.msg = "请选择转出地信息（区）";
				return
			}
			if(isBlank($scope.inProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择转入地信息（省）";
				return
			}
			if(isBlank($scope.inCity) && $scope.isInShangHai == false) {
				$scope.isAlert = true;
				$scope.msg = "请选择转入地信息（市）";
				return
			}
			if(isBlank($scope.inCounty) && $scope.isInShangHai == false) {
				$scope.isAlert = true;
				$scope.msg = "请选择转入地信息（区）";
				return
			}
		} while (condFlag);
		condFlag = true;
		//取区划代码
		if(!isBlank($scope.inCounty)) {
			$scope.toArea = $scope.inCounty.code;
		} else if(!isBlank($scope.inCity)) {
			$scope.toArea = $scope.inCity.code;
		} else {
			$scope.toArea = $scope.inProvince.code;
		}
		if(!isBlank($scope.outCounty)) {
			$scope.fromArea = $scope.outCounty.code;
		} else if(!isBlank($scope.outCity)) {
			$scope.fromArea = $scope.outCity.code;
		} else {
			$scope.fromArea = $scope.outProvince.code;
		}
		//提交参数
		let params = {
			licenseNo: $scope.paramsInfo.licenseNo,
			userName: $scope.paramsInfo.userName,
			toArea: $scope.toArea,
			fromArea: $scope.fromArea,
			mobile: $scope.paramsInfo.mobile,
			applyNo: "1122211",
		};
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/medicalInsuranceTransfer/accessApply.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: encodeURI(params),
			success: function(res) {
				console.log(res);
				$scope.isLoading = false;
				if(res.success == true) {
					$state.go('submit');
				} else {
					$scope.isAlert = true;
					$scope.msg = "提交失败,请确认信息是否有误";
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "提交接口异常,请稍候再试";
			},
		});
	}
});
app.controller('submit', function($state, $scope, appData) {
	$scope.itemName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.licenseName = appData.licenseName;
	$scope.date = getCurrentDate(2);
	$scope.nextText = "返回首页";
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('长三角服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "长三角服务", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});