app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "synthesizeMain",
			data: {
				title: "综合通办服务"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "synthesizeIframe",
			data: {
				title: "综合通办服务"
			}
		})
	
	/*idcard router end*/
})