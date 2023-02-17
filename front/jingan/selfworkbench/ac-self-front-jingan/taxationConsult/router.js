app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "searchMain",
			data: {
				title: "税务咨询"
			}
		})
		.state("intelligentConsult", {
			url: "/intelligentConsult",
			templateUrl: "views/intelligentConsult.html",
			controller: "intelligentConsult",
			data: {
				title: "智能咨询"
			}
		})

	/*idcard router end*/
})