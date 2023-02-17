
app.controller("mainController", function($scope, $http, $location, appData, $timeout, $rootScope, $sce) {
	console.log(localStorage.routerKeyword)
	if(localStorage.routerKeyword == 'info-open-query') {
		$scope.applyUrl = "http://www.jingan.gov.cn/xxgk/xxgk.html";
		$scope.operation = '信息公开查询';
	} else if(localStorage.routerKeyword == 'opinoin-collect') {
		$scope.applyUrl = "http://www.jingan.gov.cn/Template/zmhdlxwm.html";
		$scope.operation = '意见收集反馈';
	} else if(localStorage.routerKeyword == 'public-participate') {
		$scope.applyUrl = "http://www.jingan.gov.cn/zmhd/003001/zmhdmoreinfo.html";
		$scope.operation = '公众参与';
	} else {
		return;
	}
	
	window.external.URL_OPEN(0, 250, 1080, 1500, $scope.applyUrl);

	// 设置url被angular信任 正常跳转
//	$scope.prevStep = function() {
//		try {
//			window.external.URL_CLOSE();
//		} catch(e) {
//
//		}
//		$location.path("/main");
//	}
	$scope.goHome = function() {
		$.device.Camera_Hide();
		$.device.Camera_UnLink();
		$.device.cmCaptureHide();
		$.device.idCardClose();
		$.device.qrCodeClose();
		$.device.officeClose();
		try {
			window.external.URL_CLOSE();
		} catch(e) {
			//TODO handle the exception
		}
		$.device.GoHome();
	};
});