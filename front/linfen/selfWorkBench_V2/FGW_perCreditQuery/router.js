app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "个人信用报告"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "个人信用报告"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "个人信用报告"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "个人信用报告"
			}
		})
		.state("creditReport", {
			url: "/creditReport",
			templateUrl: "views/creditReport.html",
			controller: "creditReport",
			data: {
				title: "个人信用报告"
			}
		})
		.state("input", {
			url: "/input",
			templateUrl: "views/input.html",
			controller: "input",
			data: {
				title: "个人信用报告"
			}
		})
})