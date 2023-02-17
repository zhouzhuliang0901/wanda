app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider

		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "list",
			data: {
				title: "上年度养老保险个人权益记录单"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "上年度养老保险个人权益记录单"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "上年度养老保险个人权益记录单"
			}
		})
		.state("input", {
			url: "/input",
			templateUrl: "views/input.html",
			controller: "input",
			data: {
				title: "上年度养老保险个人权益记录单"
			}
		})
		.state("social", {
			url: "/social",
			templateUrl: "views/social.html",
			controller: "social",
			data: {
				title: "上年度养老保险个人权益记录单"
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
				title: "上年度养老保险个人权益记录单"
			}
		})
})