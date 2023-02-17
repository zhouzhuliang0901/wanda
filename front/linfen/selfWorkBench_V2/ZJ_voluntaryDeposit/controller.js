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
app.controller('loginType', function($state, $scope, appData) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.funName = appData.funName = "自愿缴存（信息变更）";
	$scope.prevStep = function() {
		window.location.href = '../ZJ_allItem/index.html';
	}
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
});
app.controller('login', function($scope, $http, $state, appData) {
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
app.controller('info', function($state, $scope, appData, $rootScope, $timeout, $http) {
	$scope.operation = "请选择查询条件";
	$scope.funName = appData.funName;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stIdCardType = "居民身份证";
	$scope.stSex = IdCard(appData.licenseNumber,2);
	$scope.dtBirthDate = IdCard(appData.licenseNumber,1);
	$scope.licenseNumber =appData.licenseNumber;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
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
	$scope.accumulationFundInfo = function() {
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrl + "/aci/accumulationFund/accumulationFundInfo.do",
			dataType: "json",
			data: {
				name: encodeURI(appData.licenseName),
				identNo: $scope.licenseNumber
			},
			success: function(dataJsonp) {
				if(dataJsonp.head.rst.buscode == "000000") {
					$scope.account = dataJsonp.body.pri_account;
				} else {
					$scope.msg = "未查询到信息";
				}
			},
			error: function(err) {}
		});
	}
	$scope.accumulationFundInfo()
	//提交
	$scope.nextStep = function() {
		$scope.paramsInfo = form2arr($('#infoParams').serialize(), $(".in"));
		var condFlag = false;
		do {
			if(isBlank($scope.stAddress)) {
				$scope.isAlert = true;
				$scope.msg = "请输入联系地址";
				return
			}
		} while (condFlag);
		condFlag = true;
		$scope.isLoading = true;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/voluntaryDeposit/infoChange.do",
			dataType: "json",
			data: {
				account:$scope.account,
				address:encodeURI($scope.stAddress)
			},
			success: function(res) {
				console.log(res);
				$scope.isLoading = false;
				if(res.ret_code == "200") {
					$state.go('submit');
				} else {
					$scope.isAlert = true;
					$scope.msg = res.ret_msg;
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
	$scope.licenseName = appData.licenseName;
	$scope.date = getCurrentDate(2);
	$scope.nextText = "返回首页";
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
		}
	}
	recordUsingHistory('住建服务', '办理', appData.funName, appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs('', appData.funName, "上海市住房和城乡建设委员会", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});