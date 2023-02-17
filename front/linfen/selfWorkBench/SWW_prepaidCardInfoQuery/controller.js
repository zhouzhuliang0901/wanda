app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "预付卡信息查询";
	$.log.debug($.device.fileBase64("F://test.bmp"));
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.basic = true;
	$scope.detail = false;
	$scope.prevText = "返回列表";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.prevStep = function() {
		window.location.href = "../SWW_allItem/index.html"
	}
	$scope.query = function(name, sign) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/prepaidCard/cardInfoQuery.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				corpName: encodeURI(name||""),
				uniqueNo: sign||"",
			},success:function(dataJsonp){
				console.log(dataJsonp);
				$scope.isLoading = false;
				$scope.basic = true;
				if(dataJsonp.code == 200){
					$scope.queryList = dataJsonp.body.dataMap.portalCorpList;
				}else{
					$scope.isAlert = true;
					$scope.msg = "未查询到你的信息"
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
	
	//详情信息
	$scope.detailInfoQuery = function(uniqueNo) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/prepaidCard/cardDetailInfoQuery.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				uniqueNo: uniqueNo
			},
			success: function(dataJsonp) {
				$scope.isLoading = false;
				$scope.basic = false;
				$scope.detail = true;
				console.log(dataJsonp);
				if(dataJsonp.code == 200){
					$scope.queryDetailList = dataJsonp.body.dataMap;
				}else{
					$scope.isAlert = true;
					$scope.msg = "未查询到你的信息"
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.return = function(){
		$scope.basic = true;
		$scope.detail = false;
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