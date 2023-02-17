app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "mainController",
			data: {
				title: "企业服务"
			}
		})
		
	/*idcard router end*/
})