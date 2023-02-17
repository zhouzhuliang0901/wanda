app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "驾照考点查询";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	$scope.current = 0;
	$scope.queryList = getListByName(resultList, "科目一");
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
		}
	}
	recordUsingHistory('交通委服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
	//行为分析(查询)
	trackEventForQuery(appData.funName, "", "查询", "上海市交通委员会", "","", "");
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.queryList = [];
		$scope.queryList = getListByName(resultList, name);
	};
	$scope.prevStep = function() {
		$.device.GoHome();
		//window.location.href = "../JTW_allItem/index.html"
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