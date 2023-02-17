app.directive("zizhuHeader", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/zizhuheader.html",
		scope: {
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {

			$scope.maxCountDown = 60;
			$scope.minTime = 10;
			$scope.timer = null;
			$scope.timeCount = function() {
				$interval.cancel($scope.timer);
				$scope.timer = $interval(function() {
					$scope.maxCountDown--;
					if($scope.maxCountDown < 1) {
						//$rootScope.SisAlert = false;
						//广播事件
//						$rootScope.$broadcast('changeModel','false');
						$interval.cancel($scope.timer);
						$scope.isAlert = true;
						$scope.msg = "是否返回首页？";
						$scope.alertConfirm = function(){
							window.external.URL_CLOSE();
							$.device.cmCaptureHide();
							$.device.Camera_Hide();
							$.device.qrCodeClose();
							$.device.GoHome();
						}
						$scope.alertCancel = function(){
							$scope.isAlert = false;
							$scope.resetCountDown();
						}
						$scope.minCount = function() {
							$interval.cancel($scope.timer);
							$scope.timer = $interval(function() {
								$scope.minTime--;
								if($scope.minTime < 1) {
									try{
										window.external.URL_CLOSE();
									}catch(e){}
									$.device.cmCaptureHide();
									$.device.Camera_Hide();
									$.device.qrCodeClose();
									$.device.GoHome();
								}
							}, 1000);
						}
						$scope.minCount();
					}
				}, 1000);
			}
			$scope.timeCount();
			$scope.resetCountDown = function() {
				$interval.cancel($scope.timer);
				$timeout(function() {
					$scope.maxCountDown =60;
				});
				$timeout(function() {
					$scope.timeCount();
				}, 5000);
			};
			window.addEventListener("click", $scope.resetCountDown);
			window.addEventListener("touchstart", $scope.resetCountDown);
			window.addEventListener("input", $scope.resetCountDown);
		}
	}
});