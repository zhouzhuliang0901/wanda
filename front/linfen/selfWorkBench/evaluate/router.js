app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/List");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "evaluateMain",
			data: {
				title: "我要找茬"
			}
		})
		.state("List", {
			url: "/List",
			templateUrl: "views/list.html",
			controller: "evaluateList",
			data: {
				title: "我要找茬"
			}
		})
})