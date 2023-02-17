app.directive("viewSelf", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/viewSelf.html",
		terminal: true,
		scope: {
			show: "=",
			selectImg: "@"
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {
			$scope.selectImg = $scope.selectImg;
			$scope.__show = $scope.show;
			$scope.$on("$destroy",function(){
				$scope.show = "hidden";
			});
			$scope.$watch("show",function(val){
				$scope.__show = val;
			});
			$scope.close = function(){
				$scope.__show = "hidden";
				$scope.show = 'hidden';
			};
			$scope.viewClose = function () {
				$scope.__show = "hidden";
				$scope.show = 'hidden';
				console.log($scope.selectImg);
			}
		}
	}
});