app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main2", {
			url: "/main2",
			templateUrl: "views/main2.html",
			controller: "main2",
			data: {
				title: "市民网上实名认证服务"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
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
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "市民网上实名认证服务"
			}
		})
		.state("creditReport", {
			url: "/creditReport",
			templateUrl: "views/creditReport.html",
			controller: "creditReport",
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
})