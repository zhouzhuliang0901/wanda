app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/guideline");

	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "发改委"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "发改委"
			}
		})
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "list",
			data: {
				title: "发改委"
			}
		})
		.state("selectList", {
			url: "/selectList",
			templateUrl: "views/selectList.html",
			controller: "selectList",
			data: {
				title: "发改委"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "发改委"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "发改委"
			}
		})
		.state("applyInfo", {
			url: "/applyInfo",
			templateUrl: "views/applyInfo.html",
			controller: "applyInfo",
			data: {
				title: "发改委"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "发改委"
			}
		})
	/*idcard router end*/
})