app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "synthesizeMain",
			data: {
				title: "我的信息"
			}
		})
		.state("infoLoginType", {
			url: "/infoLoginType",
			templateUrl: "views/loginType.html",
			controller: "infoLoginType",
			data: {
				title: "我的信息"
			}
		})
		.state("infoLogin", {
			url: "/infoLogin",
			templateUrl: "views/login.html",
			controller: "infoLogin",
			data: {
				title: "我的信息"
			}
		})
		.state("apply", {
			url: "/apply",
			templateUrl: "views/apply.html",
			controller: "apply",
			data: {
				title: "我的信息"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "synthesizeIframe",
			data: {
				title: "我的信息"
			}
		})
		.state("print", {
			url: "/print",
			templateUrl: "views/print.html",
			controller: "print",
			data: {
				title: "我的信息"
			}
		})
	
	/*idcard router end*/
})