app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.isLoading = true;
	$scope.funName = appData.funName = "公积金缴存提取经办银行查询";
	appData.bankname = "";
	$scope.queryManagementDept = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/queryHandlingPlace.do",
			dataType: "json",
			data: {
				bankname: encodeURI(appData.bankname)
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
	//获取银行列表
	$scope.queryBankName = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/queryHandlingBank.do",
			dataType: "json",
			data: {},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.bankNameList = dataJson;
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
		$scope.queryManagementDept();

	}
	$scope.queryBankName();
	$scope.isLoading = false;
	$scope.change = function(name, index) {
		$scope.isLoading = true;
		$scope.current = index;
		appData.bankname = name;
		console.log(appData.bankname);
		$scope.queryManagementDept();
	};
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