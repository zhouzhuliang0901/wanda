app.directive("appFooter", function () {
	return {
		restrict: "E",
		templateUrl: "../libs/views/footer.html",
		scope: {
			next: "&",
			prev: "&",
			prevText: "@",
			nextText: "@",
			defaultNext:"@",
			defaultPrev:"@"
		},
		transclude: true,
		controller: function ($scope, $location, $state, $rootScope, $http, appFactory, $interval, $timeout) {
			$scope.nextTips = $scope.nextText || "下一步";
			$scope.prevTips = $scope.prevText || "上一步";
			$scope.hasDefaultPrev = $scope.defaultPrev||"1";
			$scope.hasDefaultNext = $scope.defaultNext||"1";

			
			$scope.$watch("hasDefaultPrev",function(val){
				if (val) {
					$scope.hasDefaultPrev = val;
				}
			})
			$scope.$watch("hasDefaultNext",function(val){
				if (val) {
					$scope.hasDefaultNext = val;
				}
			})
			$scope.$watch("nextText", function (newVal, oldVal) {
				if (newVal) {
					$scope.nextTips = newVal;
				}
				console.log($scope.nextTips)
			})
			$scope.$watch("prevText", function (newVal) {
				if (newVal) {
					$scope.prevTips = newVal;
				}
			})

			$scope.nextStep = function (i, obj) {
				$scope.next();
			}
			$scope.prevStep = function () {

				if ($scope.prev) {
					$scope.prev();
					$rootScope.goAppHistoryBack()
				} else {
					$rootScope.goAppHistoryBack()
				}
			}
		}
	}
});