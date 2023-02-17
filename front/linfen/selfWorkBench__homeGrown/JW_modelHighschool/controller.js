app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "全市示范高中信息";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.queryHighSchools = function(schoolName) {
		$scope.isLoading = true;
		if(schoolName == undefined){
			schoolName = '';
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/educationCommission/queryHighSchoolInfo.do",
			dataType: "json",
			data: {
				schoolName: encodeURI(schoolName)
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(schoolName == ''){
					$scope.queryList = dataJson;
				}else{
					var matchList = [];
					dataJson.map(function(item,index){
						if(item.mz.match(schoolName)){
							matchList.push(item);
						}
					})
					$scope.queryList = matchList;
				}
				console.log($scope.queryList);
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
		recordUsingHistory('教委服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市教育委员会", "","", "");
	}
	$scope.queryHighSchools();
	$scope.prevStep = function() {
		$.device.GoHome();
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