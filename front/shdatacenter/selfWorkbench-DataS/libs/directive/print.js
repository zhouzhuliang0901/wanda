app.directive("print", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/print.html",
		terminal: true,
		scope: {
			show:"="
		},
		controller: function($scope, $location, $state, $rootScope, $interval, $timeout) {

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
		}
	}
});