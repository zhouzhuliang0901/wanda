app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "我的小区信息查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "我的小区信息查询"
			}
		})
		.state("houseLIist", {
			url: "/houseLIist",
			templateUrl: "views/houseLIist.html",
			controller: "houseLIist",
			data: {
				title: "我的小区信息查询"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "我的小区信息查询"
			}
		})
})