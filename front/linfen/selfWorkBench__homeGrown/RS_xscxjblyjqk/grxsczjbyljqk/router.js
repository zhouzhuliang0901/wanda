app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "城镇职工基本养老保险"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "城镇职工基本养老保险"
			}
		})
		.state("input", {
			url: "/input",
			templateUrl: "views/input.html",
			controller: "input",
			data: {
				title: "城镇职工基本养老保险"
			}
		})
		.state("social", {
			url: "/social",
			templateUrl: "views/social.html",
			controller: "social",
			data: {
				title: "城镇职工基本养老保险"
			}
		})
		.state("residenceInfo", {
			url: "/residenceInfo",
			templateUrl: "views/residenceInfo.html",
			controller: "residenceInfo",
			data: {
				title: "医保"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "iframe",
			data: {
				title: "城镇职工基本养老保险"
			}
		})
})