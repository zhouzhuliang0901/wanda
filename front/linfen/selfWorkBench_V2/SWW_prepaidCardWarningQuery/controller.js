app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "预付卡警示名单查询";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.waringTypeList = waringType;
	$scope.prevStep = function() {
		window.location.href = "../SWW_allItem/index.html"
	}
	$scope.query = function(name,waringType) {
		console.log(waringType);
		$scope.isLoading = true;
		$scope.waringTypeId = waringType.id;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/prepaidCard/warningOfCard.do",
			async: true,
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				corpName: encodeURI(name||""),
				warningType: waringType.id,
			},success:function(dataJsonp){
				console.log(dataJsonp);
				$scope.isLoading = false;
				if(dataJsonp.code == 200){
					$scope.queryList = dataJsonp.body.dataMap.portalWarningList;
				}else{
					$scope.isAlert = true;
					$scope.msg = "未查询到信息"
				}
			},error:function(err){
				console.log(err);
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: $scope.funName,
				Number: "",
			}
		}
		recordUsingHistory('商务委服务', '查询',$scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海市商务委员会", '', '', "");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: true,
			taps: true,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
