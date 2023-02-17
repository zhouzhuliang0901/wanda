app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider

		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "家庭医保共济"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "家庭医保共济"
			}
		}).state("sscard", {
			url: "/sscard",
			templateUrl: "views/sscard.html",
			controller: "sscard",
			data: {
				title: "家庭医保共济"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "家庭医保共济"
			}
		})
		.state("signature", {
			url: "/signature",
			templateUrl: "views/signature.html",
			controller: "signature",
			data: {
				title: "家庭医保共济"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "家庭医保共济"
			}
		})
})