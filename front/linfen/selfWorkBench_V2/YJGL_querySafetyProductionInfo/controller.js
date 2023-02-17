app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "安全生产协会会员培训机构条件核实情况查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.areaList = areaList;
	$scope.change = function(code, index) {
		appData.areaCode = code;
		$scope.current = index;
	};

	$scope.queryInfo = function(projectName) {
		if(appData.areaCode == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请选择区";
			return;
		}
		if(projectName == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请填写单位名称";
			return;
		}
		appData.projectName = projectName;
		$state.go("detail");
	}
	//	$scope.queryAssessor('','');
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../YJGL_allItem/index.html"
	}
});
app.controller("detail", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "安全生产协会会员培训机构条件核实情况查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.isShow = false;

	$scope.queryInfo = function() {
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/emergencyBureau/queryInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				method: "querpxjg",
				companyName: encodeURI(appData.projectName),
				region: appData.areaCode || "",
			},
			success: function(dataJson) {
				console.log(dataJson)
				$scope.isLoading = false;
				$scope.isShow = true;
				if(dataJson != undefined) {
					$scope.Item = dataJson;
					$scope.isResult = true;
				} else {
					$scope.isResult = false;
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				console.log(err);
			}
		});

		//模块使用记录 埋点
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		};
		recordUsingHistory('应急局服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName, "", "查询", "上海市应急管理局", "", "", "");
	}
	$scope.queryInfo();
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("choose");
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