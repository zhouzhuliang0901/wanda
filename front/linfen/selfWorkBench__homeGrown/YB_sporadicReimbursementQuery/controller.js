app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "零星报销进度查询";
	trackEvent("零星报销进度查询");
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.show = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.query = function(sqh){
		console.log(sqh);
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: "http://10.2.105.10:8080/ac-self/selfapi/reimbursement/queryReimbursementStatus.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				sqh: sqh,
			},success:function(dataJsonp){
				$scope.isLoading = false;
				$scope.show = true;
				if(dataJsonp.head.rst.buscode == "000000"){
					console.log(dataJsonp.body);
					$scope.queryList = dataJsonp.body;
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
				Number:"" ,
			}
		}
		recordUsingHistory('医保服务', '查询',$scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海市医疗保障局", '', '', "");
	}
	
	$scope.prevStep = function() {
		$.device.GoHome();
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
