app.controller('guideline', function($state, $scope, appData, $http) {
	$scope.funName = "房产税查询";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../SWJ_allItem/index.html"
	}
	$scope.nextStep = function() {
		$state.go("loginType");
	}
});
app.controller('loginType', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "信用报告";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go('login');
	}
	$scope.prevStep = function() {
		$state.go("guideline");
	}
});
app.controller('login', function($scope, $http, $state, appData, $timeout) {
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
			$scope.operation = "随申办登录";
			break;
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.archivesNumber = info.Number;
			appData.archivesName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("choose");
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.archivesName = info.zwdtsw_name;
			appData.archivesNumber = info.zwdtsw_cert_id;
			$state.go("choose");
		} else {
			var idcardInfo = info.result.data;
			appData.archivesName = idcardInfo.realname;
			appData.archivesNumber = idcardInfo.idcard;
			$state.go("choose");
		}

	}
})
app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "房产税查询";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//表单信息
	$scope.yearList = year;
	$scope.areaList = area;
	$scope.licenseTypeList = licenseType;
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.licenseTypeName = name;
		appData.licenseTypeId = id;
	}
	$scope.change1 = function(name, index, id) {
		$scope.current1 = index;
		appData.areaName = $scope.areaName = name;
	}
	$scope.change2 = function(name, index, id) {
		$scope.current2 = index;
		appData.yearName = $scope.yearName = name;
	}
	$scope.prevStep = function() {
		$state.go("guideline");
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.areaName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择区划！";
				return;
			}
			if(isBlank($scope.yearName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择年份！";
				return;
			}
			if(isBlank($scope.keyWord)) {
				$scope.isAlert = true;
				$scope.msg = "请输入数字编号！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		appData.keyWord = $scope.keyWord;
		$state.go("info");
	}
});
app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "房产税查询结果";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.nextText = "返回列表";
	$scope.basic = false;
	$scope.detail = false;
	$scope.date = getNowFormatDate();
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.query = function() {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/propertyTax/queryPropertyTax.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				fczjlx: appData.licenseTypeId,
				zjlx: "01",
				zjhm: appData.licenseNumber,
				qxjc: encodeURI(appData.areaName || ""),
				nf: appData.yearName,
				szbm: appData.keyWord,
			},
			success: function(dataJsonp) {
				$scope.isLoading = false;
				console.log(dataJsonp);
				if(!isBlank(dataJsonp)) {
					$scope.fwdz = dataJsonp.fwdz;
					$scope.queryList = dataJsonp.pageInfo.pageData;
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到信息";
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: $scope.funName,
				Number: "",
			}
		}
		recordUsingHistory('税务服务', '查询', $scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海市税务局", '', '', "");
	}
	$scope.query();

	$scope.prevStep = function() {
		$state.go("choose");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			taps: true,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});