app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "synthesizeMain",
			data: {
				title: "政策推"
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
		.state("choice", {
			url: "/choice",
			templateUrl: "views/choiceType.html",
			controller: "choiceType",
			data: {
				title: "政"
			}
		})
	
	/*idcard router end*/
})