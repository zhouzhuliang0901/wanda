//参保人员待遇查询
app.controller('main', function($scope, $state, appData, $sce, appFactory) {
	//	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.show = false;
	$scope.nextText = "返回首页";
	$scope.medicalType = medicalType;
	$scope.search = function(list, id) {
		var result = list.filter(function(p) {
			return p.pid == id;
		});
		return result;
	}
	$scope.change = function(name, index, id) {
		$scope.current = null;
		$scope.current2 = null;
		$scope.show = true;
		$scope.show2 = false;
		$scope.current = index;
		$scope.treatmentDetials = $scope.search(treatmentDetials, id);
	};
	$scope.change2 = function(name, index, guideline) {
		$scope.current2 = null;
		$scope.show2 = true;
		$scope.current2 = index;
		$scope.guideline = $sce.trustAsHtml(guideline);
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('reduceChoose');
	}
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: '参保人员待遇查询',
		}
	}
	recordUsingHistory('医保服务', '查询', '参保人员待遇查询', '', '', '', '', JSON.stringify($scope.jsonStr));
	//行为分析(查询)
	trackEventForQuery("参保人员待遇查询", "", "查询", "上海市医疗保障局", "", "", "");
	$scope.prevStep = function() {
		$state.go("reduceChoose");
	}
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
});