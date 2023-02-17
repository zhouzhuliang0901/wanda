app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "evaluateMain",
			data: {
				title: "评价及建议"
			}
		})
		.state("List", {
			url: "/List",
			templateUrl: "views/list.html",
			controller: "evaluateList",
			data: {
				title: "评价及建议"
			}
		})
})