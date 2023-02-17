app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/guideline");
	$stateProvider

		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "archivesMain",
			data: {
				title: "档案查询"
			}
		})
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "档案查询"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "archivesLoginType",
			data: {
				title: "档案查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "archivesLogin",
			data: {
				title: "档案查询"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "档案查询"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "档案查询"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "档案查询"
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "preview",
			data: {
				title: "档案查询"
			}
		})
})