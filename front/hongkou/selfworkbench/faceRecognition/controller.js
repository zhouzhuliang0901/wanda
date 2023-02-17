app.controller("faceList", function($scope, $state, appData, $timeout) {
	$scope.choiceType = function(type){
		appData.loginType = type;
		if(type=="idCard"){
			$state.go("main");
		}else if(type="citizen"){
			$state.go("citizen");
		}
	}
});

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

app.controller("faceCitizen", function($scope, $state, appData, $timeout) {
	$scope.isRead = true;
});