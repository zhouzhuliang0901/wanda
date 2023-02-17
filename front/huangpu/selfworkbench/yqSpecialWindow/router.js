app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "疫情专窗"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "iframe",
			data: {
				title: "疫情专窗"
			}
		})
		.state("details", {
			url: "/details",
			templateUrl: "views/details.html",
			controller: "details",
			data: {
				title: "疫情专窗"
			}
		})
	/*idcard router end*/
})