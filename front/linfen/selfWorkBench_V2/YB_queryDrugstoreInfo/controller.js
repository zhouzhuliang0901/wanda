//医疗定点药店查询条件
app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "医保定点药店查询";
	$scope.concel = "false";
	$scope.isAlert = false;
	appData.area = "";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.areaList = areaList;
	$scope.change2 = function(name, index, id) {
		$scope.current2 = null;
		$scope.current2 = index;
		appData.area = id;
	};
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
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(appData.area == "" || appData.area == null || appData.area == undefined) {
				$scope.isAlert = true;
				$scope.msg = "请选择所属区！";
				return;
			}
		} while (condFlag);
		appData.drugstoreName = $scope.drugstoreName;
		$state.go("queryList");
	}
});
////医疗定点药店查询查询结果
app.controller("queryList", function($scope, $state, appData, $sce, $timeout) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("choose");
	}
	$scope.queryDrugstoreInfo = function() {
		console.log(appData.hospitalClass);
		console.log(appData.area);
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/MedicalInstitution/queryDrugstoreInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				area: appData.area,//所属区
				hospitalName: encodeURI(appData.drugstoreName||""), //医院名称
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.isLoading = false;
				if(dataJson.code == 409 || dataJson.message == "HD:参数中存在不合法信息") {
					$scope.isAlert = true;
					$scope.msg = "未查询到信息,请重试";
					$timeout(function () {
						$scope.isAlert = false;
						$state.go('choose');
					}, 5000);
				}
				if(dataJson[0].ddyds) {
					$scope.queryList = dataJson[0].ddyds[0].ddyd;
					$scope.listLength = $scope.queryList.length;
				} else {
					$scope.isAlert = true;
					$scope.msg = "无查询结果";
				}
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: appData.funName,
					}
				}
				recordUsingHistory('医保服务', '查询', appData.funName,"", "", '', '', JSON.stringify($scope.jsonStr));
//行为分析(查询)
				trackEventForQuery(appData.funName, "", "查询", "上海市医疗保障局", "", "", "");
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "未查询到信息,请重试";
			}
		});
	}
	$scope.queryDrugstoreInfo();
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
	$scope.prevStep = function() {
		$state.go("choose");
	}
});