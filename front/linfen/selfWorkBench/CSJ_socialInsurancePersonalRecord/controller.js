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
	appData.funName = "社会保险个人权益记录单查询";
	$scope.funName = appData.funName;
	$scope.prevStep = function() {
		window.location.href = "../CSJ_allItem/index.html";
	}
	$scope.nextStep = function() {
		$state.go('choiceProvince');
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

	//test 跳过核验
	$scope.idcardLogin = function() {
		appData.licenseNumber = "310111196303070036";
		appData.licenseName = "吴兴宝";
		$state.go('choiceProvince');
	}
	$scope.idcardLogin();

	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go('choiceProvince');
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
		$state.go('choiceProvince');
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
app.controller('choiceProvince', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.funName = appData.funName;
	appData.licenseName = "邹天奇";
	appData.licenseNumber = "430426199804106174";
	$scope.choiceType = function(type) {
		appData.type = type;
		$state.go("info");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
})
app.controller('info', function($state, $scope, appData, $timeout, appFactory, $http) {
	$scope.operation = "请选择查询条件";
	$scope.nextText = "查询";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.show = appData.type == "sh" ? false : true; //判断省市是否为上海
	switch(appData.type) {
		case "sh":
			$scope.provinceList = [{
				"id": '310000',
				"shortname": '上海市',
				"parentid": 'sh'
			}];
			break;
		case "js":
			$scope.provinceList = [{
				"id": '320000',
				"shortname": '江苏省',
				"parentid": 'js'
			}];
			break;
		case "zj":
			$scope.provinceList = [{
				"id": '330000',
				"shortname": '浙江省',
				"parentid": 'zj'
			}];
			break;
		case "ah":
			$scope.provinceList = [{
				"id": '340000',
				"shortname": '安徽省',
				"parentid": 'ah'
			}];
	}
	$scope.province = $scope.provinceList[0];
	//接口获取信息预填
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.countyList = filterByInfo(city,$scope.province.id,'parentid');
	$timeout(function() {
		$scope.$watch('county',function(val){
			console.log(val);
			if(val){
				console.log(val);
				$scope.streetList = filterByInfo(county,val.id,'parentid');
			}
		})
	}, 100)
	//初始化日期控件
	$(".form_datetime").datetimepicker({
		format: "yyyy", //显示日期格式
		autoclose: true,
		todayBtn: true,
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
		startView: 4,
		minView: 4,
	});
	//监听日期控件 变化
	$scope.nextStep = function() {
		var condFlag = false;
		do {} while (condFlag);
		//提交参数集合
		condFlag = true;
		//办件信息同步接口
	}
});
app.controller('submit', function($state, $scope, appData) {
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