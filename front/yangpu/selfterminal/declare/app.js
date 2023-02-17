var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
	return {};
});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
app.run(["$rootScope", '$log', "$location", function($rootScope, $log, $location) {
	$rootScope.$on('$routeChangeStart', function(evt, next, current) {
		try {
			if(current.$$route.originalPath !== $location.path()) {
				OcxControl.scanClose();
				OcxControl.idCardClose();
				OcxControl.Light.qrcodeLightClose();
				OCX_Barcode.CloseConnection();		// 关闭二维码扫描
				OcxControl.cameraClose();
				OcxControl.receiptPrintClose();
			}
		} catch(error) {
			console.log("未知路由!")
		}
	});
}]);