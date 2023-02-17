app.controller("info", function($scope, $state, appData, $sce) {
	$scope.operation = "上海市历年(补充)住房公积金缴存基数、比例、上下限一览表";
	$scope.funName = appData.funName = "公积金缴存表查询";
	$scope.isLoading = false;
	var Info = []; // 按年分类之后的公积金数据
	appData.queryA = []; // 公积金
	appData.querySA = []; // 补充公积金
	$scope.Item = []; // 存放界面信息
	$scope.queryAccumulation = function(){
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/queryAccumulationFund.do",
			dataType: "json",
			data: {},
			success: function(dataJson) {
				$scope.isLoading = false;
				appData.queryA = dataJson;
//				console.log(dataJson);
				$scope.querySupplementAccumulation();
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});
	}
	$scope.queryAccumulation();
	$scope.querySupplementAccumulation = function() {
		var Info = [];
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/querySupplementAccumulationFund.do",
			dataType: "json",
			data: {},
			success: function(dataJson) {
				appData.querySA = dataJson;
				for(var i = 0; i < appData.querySA.length; i++){
					appData.queryA.push(appData.querySA[i]);
				}
//				console.log(appData.queryA.length);
				for(var i = 0; i < appData.queryA.length; i++){
					var sameYear = [];					
					var currentYear = appData.queryA[i].year;
					var count = 0;
					for(var j = 0; j < appData.queryA.length; j++){
						if(appData.queryA[j].year == currentYear){
							count++;
							if(appData.queryA[j].zfjc){
								appData.queryA[j].addzfjc = "null";
							}
							sameYear.push(appData.queryA[j]);
							appData.queryA[j].year = 0;
						}
					}
					if(currentYear != 0){
						Info.push($.extend({currentYear:currentYear},{count:count},{sameYear:sameYear})); //Object.assign({},item,{state: '0'})
					}
				}
				$scope.Item = Info;
				$scope.isLoading = false;
				$scope.$apply();
				console.log($scope.Item);
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
		recordUsingHistory('住建服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName,'','查询','上海市住房和城乡建设委员会','','','');
	}
	
	
	$scope.prevStep = function() {
		window.location.href = "../housingConstruction/index.html"
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