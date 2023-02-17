app.controller("idcardMain", function($scope, $state, appData,$timeout) {
	appData.idcardImg = {};
	$scope.over = false;
	$scope.isPrint = false;
	var lodop = $.device.printGetLodop();
	$scope.getImg = function(img, url) {
		if(appData.idcardImg.frontImg == undefined) {
			appData.idcardImg.frontImg = img;
			appData.idcardImg.frontUrl = url;

		} else {
			$scope.over = true;
			appData.idcardImg.backImg = img;
			appData.idcardImg.backUrl = url;
			$scope.frontImg = appData.idcardImg.frontImg;
			$scope.backImg = appData.idcardImg.backImg;
			$scope.isPrint = true;
		}
	}
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
		lodop.ADD_PRINT_IMAGE(140, 220, 880, 1000, "<img border='0' style='width:320px;height:205px;' src='" + appData.idcardImg.frontImg + "'>");
		lodop.ADD_PRINT_IMAGE(460, 220, 380, 1000, "<img border='0' style='width:320px;height:205px;' src='" + appData.idcardImg.backImg + "'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		lodop.SET_PRINT_COPIES($scope.printQuantity);
		lodop.PRINT();
		$timeout(function() {
			$.device.GoHome();
		}, 3000);
	}
});
app.controller("idcardChoose", function($scope, appData, $timeout, $state) {

});