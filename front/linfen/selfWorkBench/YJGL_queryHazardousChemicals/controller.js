app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "上海市禁止、限制和控制危险化学品目录查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.Range = ['全市禁止', '工业区禁止部分', '中心城限制和控制部分'];
	$scope.queryInfo = function(medicalName, range) {
		if(medicalName == undefined) {
			medicalName = '';
		}
		if(range == undefined) {
			$scope.isAlert = true;
			$scope.msg = "请选择禁止范围";
			return;
		} else if(range == ("全市禁止")) {
			range = 7;
		} else if(range == ("工业区禁止部分")) {
			range = 8;
		} else {
			range = 0;
		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/emergencyBureau/queryInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				method: "queryWhpml",
				chemicalsName: encodeURI(medicalName),
				measures: range
			},
			success: function(dataJson) {
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