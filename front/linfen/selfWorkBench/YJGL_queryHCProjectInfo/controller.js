app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "危险化学品建设项目信息查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.queryInfo = function(projectName) {
		if(projectName == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请填写建设项目名称";
			return;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/emergencyBureau/queryInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				method: "queryWhjsxm",
				documentName: encodeURI(projectName)
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
		recordUsingHistory('市场监管服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName, "", "查询", "上海市市场监督管理局", "", "", "");
	}
	//	$scope.queryAssessor('','');
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../YJGL_allItem/index.html"
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