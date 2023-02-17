app.controller("materialsCopyMain", function($scope, $timeout, $state, appData) {
	$scope.isPrint = false;
	$scope.printShow = null;
	$scope.materialData = null;
	var lodop = $.device.printGetLodop();
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
			lodop.ADD_PRINT_IMAGE(0, 0, 880, 1000, "<img border='0'  src='"+$scope.materialData+"'>");
			lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
			lodop.SET_PRINT_COPIES($scope.printQuantity);
			lodop.PRINT();
		//	LODOP_PRINT.materialPrint(appData.material);
		})
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	}
});