app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	trackEvent($(".headName").text());
	$scope.funName = appData.funName = "属地派出所查询";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.change = function(name, index, id) {
		if(name == "浦东新区") {
			name = "浦东";
		}else if(name == "上海"){
			name = "";
		}
		$scope.isLoading = true;
		$scope.current = index;
		$scope.queryList = [];
		$scope.queryList = listRemoveRepeat(_queryName(name, appData.queryList, "policeDepartment"));
		$scope.isLoading = false;
	};
	$scope.queryTerritoryOfStation = function() {
		$scope.isLoading = true;
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/policeStation/queryTerritoryOfStation.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				policeDepartment: "",
				policeStation: encodeURI("派出所"),
				work: ""
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				console.log(dataJson);
				appData.queryList = dataJson;
				$scope.queryList = listRemoveRepeat(appData.queryList);
				console.log($scope.queryList);
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: appData.funName,
					}
				}
				recordUsingHistory('公安服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
				//行为分析(查询)
				trackEventForQuery(appData.funName, "", "查询", "上海市公安局", "", "", "");
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "接口异常请稍候再试";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
				}
			}
		});
	}
	$scope.queryTerritoryOfStation();
	$scope.queryInfo = function(content) {
		console.log(content);
		$scope.queryList = [];
		$scope.queryList = listRemoveRepeat(_queryName(content, appData.queryList, "policeStation"));
	}
	$scope.detail = function(list) {
		appData.detailList = list;
		$state.go("detail");
	}
	$scope.prevStep = function() {
		window.location.href = "../publicSecurity/index.html";
	}
});
app.controller("detail", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName;
	$scope.detailList = appData.detailList;
	console.log($scope.detailList);
	$scope.list = _queryName($scope.detailList.policeStation, appData.queryList, "policeStation");
	console.log($scope.list);
	$scope.isLoading = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	var scroll = new BScroll('.wrapper', {
		scrollX: false,
		scrollY: true,
		scrollbar: true,
		click: true,
		tap: true,
		preventDefault: false,
		checkDOMChanges: true,
	})
	$scope.prevStep = function() {
		$state.go("choose");
	}
});