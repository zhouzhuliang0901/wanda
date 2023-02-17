app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "离线码"
			}
		})
		.state("demo", {
			url: "/demo",
			templateUrl: "views/demo.html",
			controller: "demo",
			data: {
				title: "离线码"
			}
		})
})