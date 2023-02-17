app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "长三角服务"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "iframe",
			data: {
				title: "长三角服务"
			}
		})
		.state("choice", {
			url: "/choice",
			templateUrl: "views/choice.html",
			controller: "choice",
			data: {
				title: "长三角服务"
			}
		})
})