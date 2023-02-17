app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "合同示范文本查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.queryStandard = function(contractName) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/SAFMR/getContractText.do",
			dataType: "json",
			data: {
				contractName: encodeURI(contractName || ""),
			},
			success: function(dataJson) {
				if(dataJson.success == true) {
					$scope.isLoading = false;
					$scope.isShow = true;
					$scope.queryList = dataJson.data;
				} else {
					$scope.isLoading = false;
					$scope.isShow = true;
					$scope.isAlert = true;
					$scope.msg = dataJson.msg;
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
		//		模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('市场监管服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市市场监督管理局", "", "", "");
	}
	$scope.queryStandard("");
	$scope.reset = function() {
		$scope.idNumber = '';
		$scope.licenseNumber = '';
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../SCJG_allItem/index.html"
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