app.controller("faceMain", function($scope, $state, appData, $timeout) {
	$scope.faceImg = null;
	$scope.isRead = true;
	$scope.captureImg = null;
	$scope.printWait = null;
	$scope.idcardInfo = {};
	$scope.getIdacrd = function(info, images) {
		$scope.faceImg = images;
		$scope.idcardInfo = info;
		$scope.isRead = false;
	};
	$scope.recognition = function(img) {
		$scope.captureImg = img;
		$scope.printWait = "show";
		$timeout(function() {
			LODOP_PRINT.faceRecognition("data:image/png;base64," + $scope.captureImg, "data:image/bmp;base64," + $scope.faceImg, $scope.idcardInfo);
		},100);
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	};
});