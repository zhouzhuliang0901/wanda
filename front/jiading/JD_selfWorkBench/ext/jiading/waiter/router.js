app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "waiter",
			data: {
				title: "企业服务"
			}
		})
		
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "licenseLoginType",
			data: {
				title: "企业服务"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "licenseLogin",
			data: {
				title: "企业服务"
			}
		}).state("apply", {
			url: "/apply",
			templateUrl: "views/apply.html",
			controller: "apply",
			data: {
				title: "企业服务"
			}
		})
		
	/*idcard router end*/
})