app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "组合贷款各受理银行查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.queryBanks = function(){
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanAndRepayment/queryBankName.do",
			dataType: "json",
			data: {},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.bankList = dataJson;
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
	}
	$scope.queryBanks();
	$scope.queryBanksInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/loanAndRepayment/queryAccepBank.do",
			dataType: "json",
			data: {
				officeName:encodeURI(appData.bankName),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.queryList = dataJson;
				console.log(dataJson)
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
	$scope.change = function(name,index) {
		$scope.isLoading = true;
		appData.bankName = name;
		$scope.current = index;
		$scope.queryBanksInfo();
	};
	$scope.queryBanksInfo();
	$scope.prevStep = function() {
		window.location.href = "../housingConstruction/index.html"
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
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