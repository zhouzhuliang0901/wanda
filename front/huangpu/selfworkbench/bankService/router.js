app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/type");

	$stateProvider
		.state("type", {
			url: "/type",
			templateUrl: "views/type.html",
			controller: "type",
			data: {
				title: "银行服务"
			},
			params: {
				type: ""
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "bankMain",
			data: {
				title: "银行服务"
			},
			params: {
				type: ""
			}
		})
		.state("details", {
			url: "/detail",
			templateUrl: "views/detail.html",
			controller: "bankDetail",
			data: {
				title: "银行服务"
			},
			params: {
				type: ""
			}
		})
		.state("choice", {
			url: "/choice",
			templateUrl: "views/choice.html",
			controller: "bankChoice",
			data: {
				title: "银行服务"
			},
			params: {
				type: ""
			}
		})

	/*idcard router end*/
})