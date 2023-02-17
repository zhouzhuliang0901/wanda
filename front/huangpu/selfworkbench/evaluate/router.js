app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "基层立法点"
			}
		})
		.state("advise", {
			url: "/advise",
			templateUrl: "views/advise.html",
			controller: "advise",
			data: {
				title: "基层立法点"
			}
		})
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "list",
			data: {
				title: "基层立法点"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "iframe",
			data: {
				title: "基层立法点"
			}
		})
})