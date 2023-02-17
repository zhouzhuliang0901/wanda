app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "无障碍电影放映点信息查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.areLocationList = areLocationList;
	$scope.isDetial = true;
	//模块使用记录
    $scope.jsonStr = {
        SUCCESS: "true",
        data: {
            name: appData.funName,
            Number: "",
        }
    }
    recordUsingHistory('残联服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
    //行为分析(查询)
    trackEventForQuery(appData.funName, '', "查询", "上海市残疾人联合会", "", "", "");
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		if($scope.isDetial){
			$.device.GoHome();
		} else {	
			$scope.isDetial = true;	
			$scope.funName = "民办教育机构查询";
		}
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