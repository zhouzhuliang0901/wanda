app.directive("sscard", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/sscard.html",
		terminal: true,
		scope: {
			read: "&",
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout, appData) {
			$.device.idCardClose();
			$.device.qrCodeClose();
			try {
				window.external.Hd_Audio_Stop();

			} catch(e) {}
			//$scope.showImage = "../libs/common/images/tips/sscard.png";
			if(appData.SwipeType == 'sbCard') {
				$scope.swipeSscardOrYbcard = '请插入社保卡';
				$scope.showImage = "../libs/common/images/newTips/ssCard-chip.png";
			} else if(appData.SwipeType == 'ybCard') {
				$scope.swipeSscardOrYbcard = '请刷医保卡';
				$scope.showImage = "../libs/common/images/newTips/ssCard-magnetic.png";
			}
			$scope.idcardInfo = null;
			$scope.needImg = $scope.hasImg || 'yes';
			//						$timeout(function(){				
			//							$scope.read({
			//								info: 1,
			//								images: 2
			//							}); //回调controller方法
			//						},2000)
			$scope.$watch("hasImg", function(newVal) {
				if(newVal) {
					$scope.needImg = newVal;
				}
			})
			$scope.readssCard = function() {
				$.device.ssCardOpen(function(list) {
					$.log.debug(list);
					var data = JSON.parse(list);
					$scope.idcardInfo = data;
					$scope.read({
						info: $scope.idcardInfo,
					}); //回调controller方法
				})
			};
			$scope.readssCard();

		}
	}
});