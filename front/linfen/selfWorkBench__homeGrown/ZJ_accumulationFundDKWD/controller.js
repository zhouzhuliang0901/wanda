app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "纯公积金贷款受理网点";
	appData.areaName = "徐汇";
	appData.area = "";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	$scope.queryManagementDept = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanAndRepayment/queryLoanPlace.do",
			dataType: "json",
			data: {
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.queryList = dataJson;
				appData.queryList = $scope.queryList;
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
	$scope.queryManagementDept();
	$scope.change = function(name, index, id) {
		$scope.current = index;
		if(name == "全部"){
			$scope.isLoading = true;
			$scope.queryManagementDept("");
		}else{
			if(name == "浦东新区"){
				name = "浦东区";
			}
			$scope.queryList = [];
			$scope.queryList.push(filterGetValueByInfo(appData.queryList,name));
		}
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