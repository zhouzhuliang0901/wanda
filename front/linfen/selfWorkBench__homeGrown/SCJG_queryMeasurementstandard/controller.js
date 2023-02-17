app.controller("choose", function($scope, $state, appData, $sce) {
	$scope.funName = appData.funName = "社会公用计量标准查询";
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isShow = false;
	$scope.queryStandard = function(licenseNumber,idNumber) {
		$scope.isLoading = true;
		if(licenseNumber == undefined || licenseNumber == ''){
			$scope.isLoading =false;
			$scope.isAlert = true;
			$scope.msg = "证件号码不能为空";
			return;
		}
		if(idNumber == undefined || idNumber == ''){
			$scope.isLoading = false
			$scope.isAlert = true;
			$scope.msg = "证书编号不能为空";
			return;
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "selfapi/measuring/queryMeasurementStandard.do",
//			url: "http://10.2.104.131:8080/ac-self/selfapi/measuring/queryMeasurementStandard.do",
			dataType: "json",
			data: {			
				holderCode: licenseNumber,
				certCode: idNumber,
				catMainCode: '310100748000100'
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
		trackEventForQuery(appData.funName, "", "查询", "上海市市场监督管理局", "","", "");	
	}

	$scope.reset = function(){
		$scope.idNumber = '';
		$scope.licenseNumber = '';
	}
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		if($scope.isDetial){
			$.device.GoHome();
		} else {	
			$scope.isDetial = true;	
			$scope.funName = "社会公用计量标准查询";
		}
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