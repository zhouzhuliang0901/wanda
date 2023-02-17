app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");

	$stateProvider
	.state("loginType", {
		url: "/loginType",
		templateUrl: "views/loginType.html",
		controller: "loginType",
		data: {
			title: "发改委"
		}
	})
	.state("login", {
		url: "/login",
		templateUrl: "views/login.html",
		controller: "login",
		data: {
			title: "发改委"
		}
	})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "公安"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "公安"
			}
		})
})