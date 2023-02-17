app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "危险化学品经营许可证核发查询";
	$scope.isLoading = false;
	appData.bankName = '';
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.queryInfo = function(medicalName, qualiNumber) {
		if(medicalName == undefined || medicalName == '') {
			$scope.isAlert = true;
			$scope.msg = "请填写单位名称";
			return;
		} 
//		if(qualiNumber == undefined || qualiNumber == '') {
//			$scope.isAlert = true;
//			$scope.msg = "请填写证书编号";
//			return;
//		}
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/emergencyBureau/queryInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				method: "queryWhjyxk",
				companyName:encodeURI(medicalName),
				registrationNumber:qualiNumber||""
			},
			success: function(dataJson) {
				console.log(dataJson)
				$scope.isLoading = false;
				$scope.isShow = true;
				if(dataJson != undefined){
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
		$scope.jsonStr = {SUCCESS: "true",data: {name: appData.funName,}};
		recordUsingHistory('应急局服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName, "", "查询", "上海市应急管理局", "", "", "");
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