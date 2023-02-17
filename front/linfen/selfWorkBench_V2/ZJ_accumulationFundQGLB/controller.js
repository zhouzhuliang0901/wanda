app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "公积金区管理部查询";
	appData.areaName = "徐汇";
	appData.area = "";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	$scope.queryManagementDept = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/queryManagementDept.do",
			dataType: "json",
			data: {
				area: encodeURI(appData.areaName)
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.queryList = dataJson;
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('住建服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName,'','查询','上海市住房和城乡建设委员会','','','');
	}
	$scope.change = function(name, index, id) {
		$scope.isLoading = true;
		$scope.current = index;
		appData.areaName = name;
		if(name == "全部"){
			appData.areaName = "";
		}
		$scope.queryManagementDept();
	};
	$scope.queryManagementDept();
	$scope.prevStep = function() {
		window.location.href = "../housingConstruction/index.html"
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});