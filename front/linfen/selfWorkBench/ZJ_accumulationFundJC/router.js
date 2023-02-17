app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/info");

	$stateProvider
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "公安"
			}
		})
	/*idcard router end*/
})