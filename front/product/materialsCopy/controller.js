app.controller("materialsCopyMain", function($scope, $timeout, $state, appData, $rootScope) {
	$scope.isPrint = false;
	$scope.printShow = null;
	$scope.materialData = null;
	$scope.getImg = function(img, url) {
		if(img) {
			$scope.materialData = img;
			appData.materialImg = img;
			appData.material = url;
			$scope.isPrint = true;
			$state.go("choose");
		}
	};
});

app.controller("materialsChoose", function($scope, $timeout, $state, appData, $rootScope) {
	$scope.materialImg = appData.materialImg;
	$scope.printQuantity = 1;
	$scope.minus = function() {
		if($scope.printQuantity > 1) {
			--$scope.printQuantity;
		}
	};
	$scope.plus = function() {
		if($scope.printQuantity < 5) {
			++$scope.printQuantity;
		}

	};
	$scope.print = function() {
		$scope.printShow = 'show';
		$timeout(function() {
			LODOP_PRINT.materialPrint(appData.materialImg, $scope.printQuantity);
		})
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	}
	$scope.prevStep = function() {
		//		window.location.href = "../copy/index.html";
		$state.go("main");
	}
})