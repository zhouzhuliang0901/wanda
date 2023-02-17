app.directive("appSearch", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/searchBox.html",
		scope: {
			search: "&",
			tips: "@",
			icon:"@"
		},
		transclude: true,
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.data = $scope.tips || "请输入";
			$scope.autoSearch = null;
			$scope.iconImg = $scope.icon|| "../libs/common/images/search/icon.png";
			$scope.clickSearch = function() {
				if($scope.data == $scope.tips || $scope.data == "请输入") {
					return;
				} else {
					$scope.search({
						val: $scope.data
					})
				}
			}
			$scope.$watch("data", function(newVal, oldVal) {
				$timeout.cancel($scope.autoSearch);
				if($scope.data == $scope.tips || $scope.data == "请输入" || $scope.data == ""||newVal===oldVal) {
					return;
				} else {
					$scope.autoSearch = $timeout(function() {//停止输入600ms搜索
						$scope.search({
							val: $scope.data
						});
					}, 600);
				}
			});

		}
	}
});