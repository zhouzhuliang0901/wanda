app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "iframe",
			data: {
				title: "一网通办事项"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "一网通办事项"
			}
		})
	/*idcard router end*/
})