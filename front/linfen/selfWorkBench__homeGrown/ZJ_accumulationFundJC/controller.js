app.controller('main', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.operation = '公积金缴存计算';
	$scope.choiceType = function () {
		$state.go('info');
	}
});
app.controller('info', function($state, $scope, appData, $timeout, $rootScope) {
	$scope.funName = "公积金缴存计算";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.nextText = "查询";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getAllYear = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/getAllYear.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.allYear = dataJson;
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getAllYear();

	$scope.queryDepositRatioByYear = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/queryDepositRatioByYear.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				year: encodeURI($scope.stYear.year)
			},
			success: function(dataJson) {
				var zfjc = [];
				var addzfjc = [];
				for(var i = 0; i < dataJson.length; i++) {
					if(dataJson[i].hasOwnProperty('zfjc')) {
						zfjc.push(dataJson[i]);
					} else if(dataJson[i].hasOwnProperty('addzfjc')) {
						addzfjc.push(dataJson[i]);
					}
				}
				$scope.zfjcList = zfjc;
				$scope.addzfjcList = addzfjc;
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	//表单信息
	$timeout(function() {
		selectBlur();
		$scope.$watch("stYear", function(val) {
			if(val) {
				$scope.queryDepositRatioByYear();
			}
		})
	}, 100);
	$scope.change = function(zfjc, index) {
		$scope.current = index;
		$scope.zfjc = zfjc;
	}
	$scope.change1 = function(addzfjc, index) {
		$scope.current1 = index;
		$scope.addzfjc = addzfjc;
	}
	$scope.prevStep = function() {
		$state.go('main');
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.count)) {
				$scope.isAlert = true;
				$scope.msg = "请输入工资基数！";
				return;
			}
			if(isBlank($scope.stYear)) {
				$scope.isAlert = true;
				$scope.msg = "请选择缴存年度！";
				return;
			}
			if(isBlank($scope.zfjc)) {
				$scope.isAlert = true;
				$scope.msg = "请选择住房公积金缴存比例！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFund/calculationDepositLimit.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				salary:$scope.count,
				year:encodeURI($scope.stYear.year),
				zfjc:encodeURI($scope.zfjc),
				addzfjc:encodeURI($scope.addzfjc)
			},
			success: function(dataJson) {
				console.log(dataJson);
				$scope.sum = dataJson.basic;
				if(isBlank($scope.addzfjc)){
					$scope.bcsum = 0;
				}else{
					$scope.bcsum = dataJson.supplement;
				}
			},
			error: function(err) {
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
});