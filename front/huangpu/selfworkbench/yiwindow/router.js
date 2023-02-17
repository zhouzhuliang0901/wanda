app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
	.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "政"
			}
		})
	.state("main2", {
			url: "/main2",
			templateUrl: "views/main2.html",
			controller: "main2",
			data: {
				title: "政"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "synthesizeIframe",
			data: {
				title: "政"
			}
		})
		
	
	/*idcard router end*/
})