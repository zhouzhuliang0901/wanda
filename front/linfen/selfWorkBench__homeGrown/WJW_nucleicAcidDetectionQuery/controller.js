app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "本市核酸检测采样机构查询";
//	appData.areaName = "徐汇";
//	$scope.current = 1;
	appData.area = "";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	$scope.queryManagementDept = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/samplingMechanism/querySamplingMechanism.do",
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
				name: $scope.funName,
				Number: "",
			}
		}
		recordUsingHistory('卫健委服务', '查询',$scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海市卫生健康委员会", '', '', "");
	}
	$scope.change = function(name, index, id) {
		$scope.isLoading = true;
		$scope.current = index;
		appData.areaName = name;
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