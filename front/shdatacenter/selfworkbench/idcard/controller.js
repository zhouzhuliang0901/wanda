app.controller("idcardMain", function ($scope, $state, appData) {
	appData.idcardImg = {};
	$scope.over = false;
	$scope.getImg = function (img, url) {
		if (appData.idcardImg.frontImg == undefined) {
			appData.idcardImg.frontImg = img;
			appData.idcardImg.frontUrl = url;

		} else {
			$scope.over = true;

			appData.idcardImg.backImg = img;
			appData.idcardImg.backUrl = url;
			$state.go("choose");
		}
	}
	$scope.prevStep = function(){
		window.location.href = "../copy/index.html";
	}
});
app.controller("idcardChoose", function ($scope, appData, $timeout, $state) {
	$scope.frontImg = appData.idcardImg.frontImg;
	$scope.backImg = appData.idcardImg.backImg;
	$scope.printQuantity = 1;
	$scope.isPrint = false;
	$scope.minus = function () {
		if ($scope.printQuantity > 1) {
			--$scope.printQuantity;
		}
	};
	$scope.plus = function () {
		++$scope.printQuantity;

	};
	$scope.print = function () {
		$timeout(function () {
			$scope.isPrint = 'show';
			$timeout(function(){
				LODOP_PRINT.idcardPrint(appData.idcardImg.frontImg, appData.idcardImg.backImg);
			});
			$timeout(function () {
				$.device.GoHome();

			}, 3000);
		})
	}
	$scope.prevStep = function(){
		$state.go("main");
	}
});