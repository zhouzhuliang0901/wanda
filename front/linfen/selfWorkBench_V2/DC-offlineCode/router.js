app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "离线码打印"
			}
		})
		.state("qinshuCode", {
			url: "/qinshuCode",
			templateUrl: "views/qinshuCode.html",
			controller: "qinshuCode",
			data: {
				title: "离线码打印"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "离线码打印"
			}
		})
		.state("print", {
			url: "/print",
			templateUrl: "views/print.html",
			controller: "print",
			data: {
				title: "离线码打印"
			}
		})
})