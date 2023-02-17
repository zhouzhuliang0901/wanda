app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "餐饮脸谱查询";
	$scope.isLoading = false;
	$scope.result = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.queryBalance = function(cardNo) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/foodAndBeverage/foodAndBeverage.do",
			dataType: "jsonp",
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
				recordUsingHistory('市场监管局', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
				console.log(dataJson.length);
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
		window.location.href = "../SCJG_allItem/index.html";
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