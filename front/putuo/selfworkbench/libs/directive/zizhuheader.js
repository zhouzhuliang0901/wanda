app.directive("zizhuHeader", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/header.html",
		scope: {
			goBack: "&",
			home: "&",
			customHome: "@",
			funName: "@",
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {
			//$scope.customHome为true，在功能子页面里。 并传入$scope.home 则执行$scope.home
			$scope.moduleName = $scope.funName;
			$scope.isFunction = $location.$$path;
			$scope.isCustom = $scope.customHome || undefined;

			$scope.$on("isCustom", function(val) {
				if(val) {
					$scope.isCustom = val;
				}
			});

			$scope.goHome = function() {
				$.device.Camera_Hide();
				$.device.cmCaptureHide();
				$.device.idCardClose();
				$.device.qrCodeClose();
				$.device.officeClose();
				try {
					window.external.URL_CLOSE();
				} catch(e) {
					//TODO handle the exception
				}
				if($scope.isFunction == "/main") {
					$.device.GoHome();
				} else {
					if($scope.isCustom) {
						$scope.home();
					} else {
						$.device.GoHome();
					}
				}
			};
			$scope.maxCountDown = 60;
			$scope.timer = null;
			$scope.timeCount = function() {
				$interval.cancel($scope.timer);
				$scope.timer = $interval(function() {
					$scope.maxCountDown--;
					if($scope.maxCountDown < 1) {

						$interval.cancel($scope.timer);
						$scope.goHome();
					}
				}, 1000);
			}
			$scope.timeCount();
			$scope.resetCountDown = function() {
				$interval.cancel($scope.timer);
				$timeout(function() {
					$scope.maxCountDown = 60;
				});
				$timeout(function() {
					$scope.timeCount();
				}, 5000);
			};
			window.addEventListener("click", $scope.resetCountDown);
			window.addEventListener("touchstart", $scope.resetCountDown);
			window.addEventListener("input", $scope.resetCountDown);
			$rootScope.$on("$viewContentLoaded", function() {
				if(!$scope.moduleName) {
					try {
						$scope.moduleName = $state.$current.data.title;
					} catch(e) {

					}
				}
			})
		}
	}
});