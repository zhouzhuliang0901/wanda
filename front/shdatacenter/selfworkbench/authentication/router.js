app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider

		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "市民网上实名认证服务"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "市民网上实名认证服务"
			}
		})
		.state("inputPhone", {
			url: "/inputPhone",
			templateUrl: "views/inputPhone.html",
			controller: "inputPhone",
			data: {
				title: "市民网上实名认证服务"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "市民网上实名认证服务"
			}
		})
		.state("recognition", {
			url: "/recognition",
			templateUrl: "views/recognition.html",
			controller: "recognition",
			data: {
				title: "市民网上实名认证服务"
			}
		})
})