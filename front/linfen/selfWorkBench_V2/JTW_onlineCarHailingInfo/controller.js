app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "网约车平台企业信息查询";
	$scope.isLoading = true;
	$scope.queryCompanyInfoForOnlineCar = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/onlineCarCompanyQuery/queryCompanyInfoForOnlineCar.do",
			dataType: "json",
			data: {
				companyName: encodeURI("")
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.queryList = dataJson.data;
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
		recordUsingHistory('交通委服务', '查询',$scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海市交通委员会", '', '', "");
	}
	$scope.queryCompanyInfoForOnlineCar();
	$scope.prevStep = function() {
		window.location.href = "../JTW_allItem/index.html"
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