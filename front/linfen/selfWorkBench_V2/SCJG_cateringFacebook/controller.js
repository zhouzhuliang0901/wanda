app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "餐饮脸谱";
	$scope.isLoading = false;
	$scope.result = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.queryBalance = function(cardNo) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/foodAndBeverage/foodAndBeverage.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				name: encodeURI(cardNo),
			},
			success: function(dataJson) {
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
				if(dataJson.length>0){
					appData.queryList = dataJson;
					$state.go("queryList");
				}else{
					$scope.isAlert = true;
					$scope.msg = "未查询到数据";
					$scope.alertConfirm = function(){
						$scope.isAlert = false;
						$scope.isLoading = false;
						$state.go("choose");
					}
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.prevStep = function() {
		$.device.GoHome();
		//window.location.href = "../SCJG_allItem/index.html";
	}
});

app.controller("queryList", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "餐饮脸谱查询";
	$scope.queryList = appData.queryList;
	$scope.isLoading = false;
	$scope.result = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.prevStep = function() {
		$state.go("choose");
	}
	$scope.detail = function(list) {
		console.log(list);
		appData.list = list;
		$state.go("detail");
	}
});
app.controller("detail", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "餐饮脸谱查询";
	$scope.list = appData.list;
	$scope.isLoading = false;
	$scope.result = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.prevStep = function() {
		$state.go("queryList");
	}
});