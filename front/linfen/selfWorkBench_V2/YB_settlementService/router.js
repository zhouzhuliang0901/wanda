app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");

	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "医保"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "医保"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "医保"
			}
		})
		.state("applyInfo", {
			url: "/applyInfo",
			templateUrl: "views/applyInfo.html",
			controller: "applyInfo",
			data: {
				title: "医保"
			}
		})
		.state("billInfoMZ", {
			url: "/billInfoMZ",
			templateUrl: "views/billInfoMZ.html",
			controller: "billInfoMZ",
			data: {
				title: "医保"
			}
		})
		.state("billInfoZY", {
			url: "/billInfoZY",
			templateUrl: "views/billInfoZY.html",
			controller: "billInfoZY",
			data: {
				title: "医保"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "医保"
			}
		})
	/*idcard router end*/
})