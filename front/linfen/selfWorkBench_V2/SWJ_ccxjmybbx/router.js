app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "城乡居民医疗保险打印"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "城乡居民医疗保险打印"
			}
		})
		.state("chooseDate", {
			url: "/chooseDate",
			templateUrl: "views/chooseDate.html",
			controller: "chooseDate",
			data: {
				title: "我的小区信息查询"
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "preview",
			data: {
				title: "城乡居民医疗保险打印"
			}
		})
})