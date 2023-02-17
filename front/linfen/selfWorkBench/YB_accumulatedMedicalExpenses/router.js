app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start  */
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "医保"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "医保"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "医保"
			}
		})
})