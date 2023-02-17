app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "政策推"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "iframe",
			data: {
				title: "政"
			}
		})
		.state("choice", {
			url: "/choice",
			templateUrl: "views/choice.html",
			controller: "choice",
			data: {
				title: "政"
			}
		})
		.state("code", {
			url: "/code",
			templateUrl: "views/code.html",
			controller: "code",
			data: {
				title: "政"
			}
		})
	
	/*idcard router end*/
})