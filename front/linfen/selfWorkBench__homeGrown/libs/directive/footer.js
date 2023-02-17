app.directive("apptbFooter", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/footer.html",
		scope: {
			goBack: "&",
			home: "&",
			customHome: "@",
			prev: "&",
			next: "&",
			defaultIsShowPrevBtn: "@",
			defaultIsShowNextBtn: "@",
			prevBtnInfo: "@",
			nextBtnInfo: "@"
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout) {
			var time = $.config.get('idleTime');
			//$scope.customHome为true，在功能子页面里。 并传入$scope.home 则执行$scope.home
			$scope.isFunction = $location.$$path;
			$scope.isCustom = $scope.customHome || undefined;
			$scope.prevBtnInfo = $scope.prevBtnInfo || "上一步";
			$scope.nextBtnInfo = $scope.nextBtnInfo || "下一步";
			// 是否显示上一步下一步按钮
			$scope.isShowPrevBtn = $scope.defaultIsShowPrevBtn || "1";
			$scope.isShowNextBtn = $scope.defaultIsShowNextBtn || "1";

			$scope.nextStep = function(i, obj) {
				$scope.next();
			}
			
			$scope.prevStep = function() {
				if($scope.prev) {
					$scope.prev();
					$rootScope.goAppHistoryBack()
				} else {
					$rootScope.goAppHistoryBack()
				}
			}

			$scope.$on("isCustom", function(val) {
				if(val) {
					$scope.isCustom = val;
				}
			});

			$scope.goHome = function() {
				$.device.Face_Close();
				$.device.Camera_Hide();
				$.device.Camera_UnLink();
				$.device.cmCaptureHide();
				$.device.idCardClose();
				$.device.qrCodeClose();
				$.device.officeClose();
				$.device.dataCardCardOut();
				try {
					window.external.URL_CLOSE();
					window.external.Hd_Audio_Stop();
				} catch(e) {
					//TODO handle the exception
				}
				$.device.GoHome();
			};
			$scope.maxCountDown = time || 60;
			$scope.minTime = 10;
			$scope.timer = null;
			$scope.timeCount = function() {
				$interval.cancel($scope.timer);
				$scope.timer = $interval(function() {
					$scope.maxCountDown--;
					if($scope.maxCountDown < 1) {
						$rootScope.SisAlert = false;
						$.device.Camera_Hide();
						$.device.cmCaptureHide();
						$.device.Face_Close();
						// 广播事件
						$rootScope.$broadcast('changeModel', 'false');
						$interval.cancel($scope.timer);
						$scope.isAlert = true;
						$scope.msg = "是否返回首页？";
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
							// window.external.URL_CLOSE();
							try {
								window.external.URL_CLOSE();
								window.external.Hd_Audio_Stop();
							} catch(e) {}
							$.device.Face_Close();
							$.device.cmCaptureHide();
							$.device.Camera_Hide();
							$.device.Camera_UnLink();
							$.device.qrCodeClose();
							$.device.idCardClose();
							$.device.officeClose();
							$.device.dataCardCardOut();
							$.device.GoHome();
						}
						$scope.alertCancel = function() {
							$scope.isAlert = false;
							$scope.resetCountDown();
						}
						$scope.minCount = function() {
							$interval.cancel($scope.timer);
							$scope.timer = $interval(function() {
								$scope.minTime--;
								if($scope.minTime < 1) {
									try {
										window.external.URL_CLOSE();
										window.external.Hd_Audio_Stop();
									} catch(e) {}
									$.device.Face_Close();
									$.device.cmCaptureHide();
									$.device.Camera_Hide();
									$.device.Camera_UnLink();
									$.device.qrCodeClose();
									$.device.idCardClose();
									$.device.officeClose();
									$.device.dataCardCardOut();
									$.device.GoHome();
								}
							}, 1000);
						}
						$scope.minCount();
					}
				}, 1000);
			}
			
			// 如果是main页面,就取消倒计时功能
//			if($location.path() == '/main') {
//				return 
//			} else {
//				
//			}
			$scope.timeCount();
			$scope.resetCountDown = function() {
				$interval.cancel($scope.timer);
				$timeout(function() {
					$scope.maxCountDown = time || 60;
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