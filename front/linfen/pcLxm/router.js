app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: ""
			}
		})
		.state("password", {
			url: "/password",
			templateUrl: "views/password.html",
			controller: "password",
			data: {
				title: ""
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: ""
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: ""
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "preview",
			data: {
				title: ""
			}
		})
})