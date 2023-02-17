app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/guideline");
	$stateProvider
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "婚姻登记档案查询"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "婚姻登记档案查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "婚姻登记档案查询"
			}
		})
		.state("info", {  //信息填写页
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "婚姻登记档案查询"
			}
		})
		.state("submit", {  //江苏省 信息填写页
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "婚姻登记档案查询"
			}
		})
})