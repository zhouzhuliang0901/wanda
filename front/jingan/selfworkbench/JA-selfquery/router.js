app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData, $timeout) {
				$scope.goToApp = function(address, keyword) {
					localStorage.routerKeyword = keyword
					window.location.href = address;
				};
			}
		})
})