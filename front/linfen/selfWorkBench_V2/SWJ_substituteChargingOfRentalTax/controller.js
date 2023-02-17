app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.nextText = "查询";
	$scope.funName = appData.funName = "个人房屋出租税收代征点查询";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	$scope.concel = "false";
	$scope.queryManagementDept = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/rentalTax/SubstituteChargingOfRentalTax.do",
			dataType: "json",
			data: {
				txtUnitName: encodeURI(appData.areaName || ""),
				txtZSD: encodeURI($scope.txtZSD || ""),
				txtZSDaddress: encodeURI($scope.txtZSDaddress || "")
			},
			success: function(dataJson) {
				console.log(dataJson.pageInfo.pageData.length);
				$scope.isLoading = false;
				if(dataJson != "" && dataJson != undefined && dataJson != null) {
					if(dataJson.pageInfo.pageData.length == 0) {
						$scope.isAlert = true;
						$scope.msg = "暂未查询到数据";
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
						}
					}else{
						appData.queryList = dataJson.pageInfo.pageData;
						$state.go("info");
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "暂未查询到数据";
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
	}
	$scope.change = function(name, index, id) {
		$scope.current = index;
		appData.areaName = name;
	};
	$scope.nextStep = function() {
		$scope.queryManagementDept();
	}
});
app.controller("info", function($scope, $state, appData, $sce) {
	$scope.queryList = appData.queryList;
	$scope.prevStep = function() {
		$state.go('choose');
	}
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
			Number: "",
		}
	}
	recordUsingHistory('税务服务', '查询', appData.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
	//行为分析(查询)
	trackEventForQuery(appData.funName, '', "查询", "上海市税务局", '', '', "");
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