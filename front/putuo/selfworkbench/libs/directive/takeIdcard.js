app.directive("takeIdcard", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/takeIdcard.html",
		scope: {
			result: "&"
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.camera = true;
			$scope.imageData = null;
			/*
			 打开高拍仪
			 * */
			$scope.capture = function() {
				$scope.imageData = 'img';
				$scope.camera = false;
			};
			$scope.reCapture = function() {
				$scope.camera = true;
				$scope.imageData = null;
			};
			$scope.confirm = function() {
				$scope.result({//返回图片数据
					img: $scope.imageData
				});
				$scope.camera = true;
				$scope.imageData = null;
			};
		}
	}
});