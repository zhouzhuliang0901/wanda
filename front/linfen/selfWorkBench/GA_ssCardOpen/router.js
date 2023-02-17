app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start  */
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("personChoice", {
			url: "/personChoice",
			templateUrl: "views/personChoice.html",
			controller: "personChoice",
			data: {
				title: "办理社保卡开通服务"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "办理社保卡开通服务"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "办理社保卡开通服务"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "办理社保卡开通服务"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "办理社保卡开通服务"
			}
		})
		.state("inputNumber", {
			url: "/inputNumber",
			templateUrl: "views/inputNumber.html",
			controller: "inputNumber",
			data: {
				title: "办理社保卡开通服务"
			}
		})
})