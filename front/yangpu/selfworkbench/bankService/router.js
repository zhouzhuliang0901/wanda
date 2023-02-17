app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "bankMain",
			data: {
				title: "银行服务"
			},
			params:{
				type:""
			}
		})
		.state("details", {
			url: "/detail",
			templateUrl: "views/detail.html",
			controller: "bankDetail",
			data: {
				title: "银行服务"
			},
			params:{
				type:""
			}
		})
	
	
	/*idcard router end*/
})