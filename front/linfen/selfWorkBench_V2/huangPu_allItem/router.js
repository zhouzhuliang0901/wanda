app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "医保"
			}
		})
	/*idcard router end*/
})