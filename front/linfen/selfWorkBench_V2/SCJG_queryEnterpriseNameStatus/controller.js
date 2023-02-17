app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "企业名称状态查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.queryStandard = function(enterpriseName) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/SAFMR/getCompanyStateInfo.do",
			dataType: "json",
			data: {
				companyName: encodeURI(enterpriseName || ""),
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.isShow = true;
				if(dataJson.success == true) {
					$scope.queryList = dataJson.data;
				} else {
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

	$scope.reset = function() {
		$scope.idNumber = '';
		$scope.licenseNumber = '';
	}
	$scope.queryStandard("");
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