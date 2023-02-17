app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "waiter",
			data: {
				title: "自助送收件"
			}
		})
	/*idcard router end*/
})