app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = $('headName').text();
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../SWW_allItem/index.html"
	}
	$scope.query = function(cardNo,corpName,brandGroup,mobile) {
		let condFlag = false;
		do {
			if(isBlank(cardNo)) {
				$scope.isAlert = true;
				$scope.msg = "请输入个人卡号！";
				return;
			}
			if(isBlank(corpName)) {
				$scope.isAlert = true;
				$scope.msg = "请输入经营者名称！";
				return;
			}
			if(isBlank(mobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入手机号！";
				return;
			}
		} while (condFlag);
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/prepaidCard/balanceOfCard.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				corpName: encodeURI(corpName||""),
				brandGroup: encodeURI(brandGroup||""),
				cardNo:cardNo,
				mobile:mobile,
			},success:function(dataJsonp){
				console.log(dataJsonp);
				$scope.isLoading = false;
				if(dataJsonp.code == 200){
					$scope.queryDetailList = dataJsonp.body.dataMap;
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
