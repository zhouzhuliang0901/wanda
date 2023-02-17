app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "民政"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "民政"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "民政"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "民政"
			}
		})
		.state("infoChoose", {
			url: "/infoChoose",
			templateUrl: "views/infoChoose.html",
			controller: "infoChoose",
			data: {
				title: "民政"
			}
		})
		.state("pickUpMethod", {
			url: "/pickUpMethod",
			templateUrl: "views/pickUpMethod.html",
			controller: "pickUpMethod",
			data: {
				title: "民政"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "民政"
			}
		})
	/*idcard router end*/
})