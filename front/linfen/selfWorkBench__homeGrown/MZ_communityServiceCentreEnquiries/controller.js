app.controller("choose", function($scope, $state, appData, $sce,$timeout) {
	$scope.operation = "请选择查询内容";
	$scope.funName = appData.funName = "社区事务受理地址查询";
	appData.area = "";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	// $scope.queryList = spaceInfoList.data;
	$scope.change = function(name, index, areaCode) {
		$scope.isLoading = true;
		console.log("areaCode"+areaCode)
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/CivilServiceAppointment/getCentreList.do",
			// url:"http://localhost:8080/ac-self/selfapi/CivilServiceAppointment/getCentreList.do",
			dataType: "json",
			data: {
				regionCode: areaCode 
			},
			success: function(dataJson) {
				console.log("内容"+JSON.stringify(dataJson))
				$timeout(function() {
					$scope.queryList = dataJson.data;
				}, 100);
				console.log(dataJson);
				$scope.isLoading = false;
			},
			error: function(err) {
				console.log("getItemApplyPlace err");
			}
		});
	};
	$scope.spaceInfo = function(codeV){
	
		console.log("社区序号"+codeV)
		appData.spaceInfoList = filterBySpaceName($scope.queryList,codeV)
		console.log("当前社区编号"+JSON.stringify(appData.spaceInfoList))
		$timeout(function() {
			$state.go("info")
		}, 1000)
		
		
	};
	$scope.prevStep = function() {
		
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
app.controller("info", function($scope, $state, appData, $sce,$timeout) {
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
	// $scope.spaceInfoList = spaceInfoList.data[0];
	$scope.spaceInfoList = appData.spaceInfoList[0];


	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}		
	$scope.prevStep = function() {
		$state.go('choose');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: false,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});