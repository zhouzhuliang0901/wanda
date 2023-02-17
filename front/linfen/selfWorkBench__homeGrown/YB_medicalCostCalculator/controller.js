app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "residents") {
			$state.go("infoResidents");
		} else {
			$state.go("infoEmployees");
		}
	}
	$scope.prevStep = function(){
		window.location.href = "../medical/index.html";
	}
});
app.controller('infoEmployees', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//表单信息
	$scope.visitTypeList = visitType;
	$scope.ztqkList = ztqk;
	$scope.objectTypeList = objectType;
	$scope.ageTypeList = ageType;
	$timeout(function() {
		selectBlur();
	}, 100);
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.stZtqkName = name;
		$scope.stZtqkId = id;
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stVisitType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择就诊类别！";
				return;
			}
			if(isBlank($scope.stZtqkId)) {
				$scope.isAlert = true;
				$scope.msg = "请选择职退情况！";
				return;
			}
			if(isBlank($scope.stObjectType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择对象类别！";
				return;
			}
			if(isBlank($scope.stAgeType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择年龄分类！";
				return;
			}
			if(isBlank($scope.CURACCOUNTAMT)) {
				$scope.isAlert = true;
				$scope.msg = "请输入当年账户余额！";
				return;
			}
			if(isBlank($scope.HISACCOUNTAMT)) {
				$scope.isAlert = true;
				$scope.msg = "请输入历年账户余额！";
				return;
			}
			if(isBlank($scope.TOTALEXPENSE)) {
				$scope.isAlert = true;
				$scope.msg = "请输入本次医疗费用总额！";
				return;
			}
			if(isBlank($scope.ZY_QFLJTOTAL)) {
				$scope.isAlert = true;
				$scope.msg = "请输入住院起付端医疗费用累计！";
				return;
			}
			if(isBlank($scope.MJZZFCASHTOTAL)) {
				$scope.isAlert = true;
				$scope.msg = "请输入年度累计门诊自负段已付医疗费用数额！";
				return;
			}
			if(isBlank($scope.TCTOTALLJ)) {
				$scope.isAlert = true;
				$scope.msg = "请输入年度起付线上封顶线下（住院/家床/大病）医疗费用数值累计！";
				return;
			}
			if(!isBlank($scope.stZtqkId) && $scope.stZtqkId == "1") {
				if($scope.stObjectType.id == "1" || $scope.stObjectType.id == "2" || $scope.stObjectType.id == "3" || $scope.stObjectType.id == "4") {
					$scope.isAlert = true;
					$scope.msg = "职退状态与年龄段选择上逻辑矛盾，请重新选择！";
					return;
				}
			}
			if(!isBlank($scope.stZtqkId) && $scope.stZtqkId == "2") {
				if($scope.stObjectType.id == "5" || $scope.stObjectType.id == "6" || $scope.stObjectType.id == "7" || $scope.stObjectType.id == "8") {
					$scope.isAlert = true;
					$scope.msg = "职退状态与年龄段选择上逻辑矛盾，请重新选择！";
					return;
				}
			}
			if(!isBlank($scope.stZtqkId) && $scope.stZtqkId == "1") {
				if($scope.stAgeType.id == "3" || $scope.stAgeType.id == "4") {
					$scope.isAlert = true;
					$scope.msg = "职退状态与年龄分类逻辑矛盾，请重新选择！";
					return;
				}
			}
			if(!isBlank($scope.stZtqkId) && $scope.stZtqkId == "2") {
				if($scope.stAgeType.id == "1" || $scope.stAgeType.id == "2") {
					$scope.isAlert = true;
					$scope.msg = "职退状态与年龄分类逻辑矛盾，请重新选择！";
					return;
				}
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/MedicalInstitution/calculationSocialInsuranceForEmployees.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				JYLB: $scope.stVisitType.id,
				ZTZT: $scope.stZtqkId,
				NLD_DIC: $scope.stObjectType.id,
				NLFL: $scope.stAgeType.id,
				CURACCOUNTAMT: $scope.CURACCOUNTAMT || 0.00,
				HISACCOUNTAMT: $scope.HISACCOUNTAMT || 0.00,
				MJZZFCASHTOTAL: $scope.MJZZFCASHTOTAL || 0.00,
				ZY_QFLJTOTAL: $scope.ZY_QFLJTOTAL || 0.00,
				TCTOTALLJ: $scope.TCTOTALLJ || 0.00,
				TOTALEXPENSE: $scope.TOTALEXPENSE || 0.00,
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(!isBlank(dataJson[0])) {
					appData.resultList = dataJson[0];
					$state.go("submit");
				} else {
					$scope.isAlert = true;
					$scope.msg = "暂未计算出您的医疗费用分担详情";
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "暂未计算出您的医疗费用分担详情";
				console.log(err);
			}
		});
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
		});
	};
	$scope.isScroll();
});

app.controller('infoResidents', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	appData.isUpload = [];
	appData.listImg = [];
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//表单信息
	$scope.visitTypeList = visitType2;
	$scope.objectTypeList = objectType2;
	$timeout(function() {
		selectBlur();
	}, 100);
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.stVisitTypeName = name;
		$scope.stVisitTypeId = id;
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.stVisitTypeId)) {
				$scope.isAlert = true;
				$scope.msg = "请选择就诊类别！";
				return;
			}
			if(isBlank($scope.stObjectType)) {
				$scope.isAlert = true;
				$scope.msg = "请选择对象类别！";
				return;
			}
			if(isBlank($scope.TOTALEXPENSE)) {
				$scope.isAlert = true;
				$scope.msg = "请输入本次医疗费用总额！";
				return;
			}
			if($scope.stObjectType.id == "7" && isBlank($scope.MJZZFCASHTOTAL)) {
				$scope.isAlert = true;
				$scope.msg = "请输入年度累计门诊自负段已付医疗费用数额！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/MedicalInstitution/calculationSocialInsuranceForResidents.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				JYLB: $scope.stVisitTypeId,
				RYLB: $scope.stObjectType.id,
				MJZZFDYFLJ: $scope.MJZZFCASHTOTAL || 0.00,
				TOTALEXPENSE: $scope.TOTALEXPENSE || 0.00,
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(!isBlank(dataJson[0])) {
					appData.resultList = dataJson[0];
					$state.go("submit");
				} else {
					$scope.isAlert = true;
					$scope.msg = "暂未计算出您的医疗费用分担详情";
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "暂未计算出您的医疗费用分担详情";
				console.log(err);
			}
		});
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			bounce: true,
			click: true,
			taps: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true,
		});
	};
	$scope.isScroll();
});
app.controller('submit', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName = "医疗费用分担模拟计算器";
	$scope.resultList = appData.resultList;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.nextText = "返回首页";
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: '账户清算信息',
			}
		}
		recordUsingHistory('医保服务', '查询', $scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery($scope.funName, "", "查询", "上海市医疗保障局", '', '', "");
	$scope.prevStep = function() {
		if(appData.type == "residents") {
			$state.go("infoResidents");
		} else {
			$state.go("infoEmployees");
		}
	}
	$scope.nextStep = function () {
		$state.go('main');
	}
});
