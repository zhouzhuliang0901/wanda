app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "计量标准考评员查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.queryStandard = function(name, unit) {
		if(name != undefined && name!="" && name!=null){
			if(unit ==undefined || unit == "" || unit == null){
				$scope.isAlert = true;
				$scope.msg = "单位不能为空";
				return;
			}
		}
		if(unit != undefined && unit!="" && unit!=null){
			if(name ==undefined || name == "" || name == null){
				$scope.isAlert = true;
				$scope.msg = "姓名不能为空";
				return;
			}
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/measuring/queryAssessor.do",
			dataType: "json",
			data: {
				name: encodeURI(name || ""),
				unit: encodeURI(unit || ""),
				type: 1
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.isShow = true;
				$scope.itemList = dataJson;
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
		recordUsingHistory('市场监管服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市市场监督管理局", "", "", "");
	}
	$scope.queryStandard("", "");
	$scope.reset = function() {
		$scope.unit = '';
		$scope.name = '';
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