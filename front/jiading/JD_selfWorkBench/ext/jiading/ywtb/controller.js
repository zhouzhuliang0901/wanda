function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller("mainController", function($scope, $location, $http, appData, $rootScope, $timeout, $interval, appFactory) {
	$scope.applyUrl = "http://zwdt.sh.gov.cn/govPortals/region/SH00JD";
	window.external.URL_OPEN(50, 100, 1800, 850, $scope.applyUrl);

	// 设置url被angular信任 正常跳转
	$scope.prevStep = function() {
		$.device.Camera_Hide();
//		$.device.Camera_UnLink();
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
	$scope.goHome = function() {
		$.device.Camera_Hide();
//		$.device.Camera_UnLink();
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