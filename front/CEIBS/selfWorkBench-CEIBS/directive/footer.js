app.directive("appFooter", function() {
	return {
		restrict: "E",
		templateUrl: "directive/footer.html",
		scope: {
			home: "&",
			prev: "&",
			next: "&",
			defaultIsShowPrevBtn: "@",
			defaultIsShowNextBtn: "@",
			prevBtnInfo: "@",
			nextBtnInfo: "@"
		},
		controller: function($scope, $location, appData, $rootScope, $interval, $timeout) {
			$scope.isFunction = $location.$$path;
			if(appData.version == "Chinese"){
				$scope.prevBtnInfo = $scope.prevBtnInfo || "上一步";
				$scope.nextBtnInfo = $scope.nextBtnInfo || "下一步";
			}else if(appData.version == "English"){
				$scope.prevBtnInfo = $scope.prevBtnInfo || "Previous";
				$scope.nextBtnInfo = $scope.nextBtnInfo || "Next";
			}
			// 是否显示上一步下一步按钮
			$scope.isShowPrevBtn = $scope.defaultIsShowPrevBtn || "1";
			$scope.isShowNextBtn = $scope.defaultIsShowNextBtn || "1";

			$scope.nextStep = function(i, obj) {
				$scope.next();
			}

			$scope.prevStep = function() {
				$scope.prev();
			}

			$scope.$on("isCustom", function(val) {
				if(val) {
					$scope.isCustom = val;
				}
			});

			$scope.goHome = function() {
				$.device.qrCodeClose();
				try {
					window.external.URL_CLOSE();
					window.external.Hd_Audio_Stop();
				} catch(e) {
					//TODO handle the exception
				}
				$.device.GoHome();
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