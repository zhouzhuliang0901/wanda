app.controller('choose', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "耗材查询";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//表单信息
	$scope.departmentList = department;
	$scope.hcTypeList = hcType;
	$scope.change = function(name, index, id) {
		$scope.current = index;
		$scope.departmentName = name;
		appData.departmentId = id;
	}
	$scope.change1 = function(name, index, id) {
		$scope.current1 = index;
		$scope.hcTypeName = name;
		appData.hcTypeId = id;
	}
	$scope.prevStep = function() {
		window.location.href = "../SKYL_allItem/index.html"
	}
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.departmentName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择作用科室！";
				return;
			}
			if(isBlank($scope.hcTypeName)) {
				$scope.isAlert = true;
				$scope.msg = "请选择耗材类别！";
				return;
			}
		} while (condFlag);
		//提交参数集合
		condFlag = true;
		$scope.isLoading = true;
		appData.keyWord = $scope.keyWord;
		$state.go("info");
	}
});
app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = "耗材查询结果";
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.nextText = "返回列表";
	$scope.basic = false;
	$scope.detail = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.basicInfoQuery = function() {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/consumablesInquiry/basicInfoQuery.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				department: appData.departmentId,
				category: appData.hcTypeId,
				keyWord: encodeURI(appData.keyWord || "")
			},
			success: function(dataJsonp) {
				$scope.isLoading = false;
				$scope.basic = true;
				console.log(dataJsonp);
				if(dataJsonp.MessageHeader.code == "1") {
					$scope.queryList = dataJsonp.ResponseData.data;
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到信息";
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
				name: $scope.funName,
				Number: "",
			}
		}
		recordUsingHistory('申康医联服务', '查询',$scope.funName, '', '', '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery($scope.funName, '', "查询", "上海申康医联发展中心", '', '', "");
	}
	$scope.basicInfoQuery();

	//详情信息
	$scope.detailInfoQuery = function(code) {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/consumablesInquiry/detailInfoQuery.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				code: code
			},
			success: function(dataJsonp) {
				$scope.isLoading = false;
				$scope.basic = false;
				$scope.detail = true;
				console.log(dataJsonp);
				if(dataJsonp.MessageHeader.code == "1") {
					$scope.queryDetailList = dataJsonp.ResponseData.data[0];
				} else {
					$scope.isAlert = true;
					$scope.msg = "未查询到信息";
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}

	$scope.prevStep = function() {
		$state.go("choose");
	}
	$scope.nextStep = function() {
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