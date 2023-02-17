app.controller("materialsCopyMain", function($scope, $timeout, $state, appData,$rootScope) {
	$scope.isPrint = false;
	$scope.printShow = null;
	$scope.materialData = null;
	$scope.getImg = function(img, url) {
		if(img) {
			$scope.materialData = img;
			appData.material = url;
			$scope.isPrint = true;
		}
	};
	$scope.printQuantity = 1;
	$scope.minus = function() {
		if($scope.printQuantity > 1) {
			--$scope.printQuantity;
		}
	};
	$scope.plus = function() {
		++$scope.printQuantity;

	};
	$scope.print = function() {
		$scope.printShow = 'show';
		$timeout(function() {
			LODOP_PRINT.materialPrint(appData.material);
		})
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	}
	$scope.prevStep = function(){
		window.location.href = "../copy/index.html";
	}
});