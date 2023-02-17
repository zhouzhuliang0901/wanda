app.directive("idcard", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/idcard.html",
		terminal: true,
		scope: {
			read: "&",
		},
		controller: function($scope, $location, $rootScope, $interval, $timeout, data) {
			$scope.readIdcard = function() {
				OcxControl.idCardRead(function(dataJson) {
					$scope.identityInfo = JSON.parse(dataJson.identityInfo);

					// 格式化日期方法的参数 pattern
					$scope.pattern = /(\d{4})(\d{2})(\d{2})/;

					// 格式化日期
					$scope.ValidPeriod = $scope.identityInfo.ValidPeriod.split("-");
					$scope.idCardInfo = {
						identityInfo: $scope.identityInfo,
						idCardName: $scope.identityInfo.Name,
						idCardNum: $scope.identityInfo.Code,
						startDate: $scope.ValidPeriod[0].replace($scope.pattern, '$1-$2-$3'),
						endDate: $scope.ValidPeriod[1].replace($scope.pattern, '$1-$2-$3').trim(),
						HeadImg: dataJson.portrait
					}
					$scope.read({
						info: $scope.idCardInfo
					})
				});
			}
			$scope.readIdcard();

		}
	}
});