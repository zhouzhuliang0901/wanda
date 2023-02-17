app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = $('.headName').text();
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.show = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../WJW_allItem/index.html"
	}
	$scope.query = function(name, licenseNo) {
		$scope.queryList = [];
		if(isBlank(name)) {
			$scope.isAlert = true;
			$scope.msg = "医师姓名不能为空";
			return;
		}
		if(isBlank(licenseNo)) {
			$scope.isAlert = true;
			$scope.msg = "执业证书编号不能为空";
			return;
		}else if(licenseNo.length<15){
			$scope.isAlert = true;
			$scope.msg = "请输入正确执业证书编号";
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/medicalEnquiry/queryDocAndNurse.do",
			async: true,
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				code: "doc",
				name: encodeURI(name || ""),
				certCode: licenseNo
			},
			success: function(dataJsonp) {
				if(dataJsonp.success == true) {
					$scope.result = dataJsonp.data.return.resultDocs;
					$scope.isLoading = false;
					if(!isBlank($scope.result)) {
						if(isArrayFn($scope.result)) {
							$scope.queryList = $scope.result;
						} else {
							$scope.queryList.push($scope.result);
						}
					} else {
						$scope.isAlert = true;
						$scope.msg = "未查询到您的信息";
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到您的信息";
				}
				console.log(dataJsonp);
			},
			error: function(err) {
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
		recordUsingHistory('卫健委服务', '查询',$scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海市卫生健康委员会", '', '', "");
	}
	$scope.reset = function() {
		$scope.name = '';
		$scope.licenseNo = '';
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