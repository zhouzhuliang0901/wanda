app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "main.html",
			controller: "materialsPrintMain"
		})
	/*idcard router end*/
})