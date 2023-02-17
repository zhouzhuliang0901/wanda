app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/iframe");

	$stateProvider
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