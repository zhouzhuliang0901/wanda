app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "二级注册计量师查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.queryStandard = function(licenseNumber, idNumber) {
		$scope.isLoading = true;
		if(licenseNumber == undefined || licenseNumber == '') {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "证件号码不能为空";
			return;
		}
		if(idNumber == undefined || idNumber == '') {
			$scope.isLoading = false
			$scope.isAlert = true;
			$scope.msg = "证书编号不能为空";
			return;
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/measuring/queryMeasurementStandard.do",
			dataType: "json",
			data: {
				holderCode: licenseNumber,
				certCode: idNumber,
				catMainCode: '310191352736400'
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				$scope.isShow = true;
				$scope.item = dataJson;
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
		recordUsingHistory('市场监管服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市市场监督管理局", "", "", "");
	}

	$scope.reset = function() {
		$scope.idNumber = '';
		$scope.licenseNumber = '';
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		window.location.href = "../SCJG_allItem/index.html"
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