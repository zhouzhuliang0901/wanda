app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "办理医保网站密码变更服务"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "办理医保网站密码变更服务"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "办理医保网站密码变更服务"
			}
		})
})