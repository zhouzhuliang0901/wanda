app.controller("materialsPrintMain", function($scope, $state, $timeout) {
	$.device.qrCodeOpen(function(url) {
		LODOP_PRINT.materialPrint(url);
	});
});