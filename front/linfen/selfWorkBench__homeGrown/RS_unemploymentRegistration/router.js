app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/selectList");
	$stateProvider
		.state("selectList", {
			url: "/selectList",
			templateUrl: "views/selectList.html",
			controller: "selectList",
			data: {
				title: "人社"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "人社"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "人社"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "人社"
			}
		})
		.state("info1", {
			url: "/info1",
			templateUrl: "views/info1.html",
			controller: "info1",
			data: {
				title: "人社"
			}
		})
		.state("info2", {
			url: "/info2",
			templateUrl: "views/info2.html",
			controller: "info2",
			data: {
				title: "人社"
			}
		})
		.state("info3", {
			url: "/info3",
			templateUrl: "views/info3.html",
			controller: "info3",
			data: {
				title: "人社"
			}
		})
		.state("info4", {
			url: "/info4",
			templateUrl: "views/info4.html",
			controller: "info4",
			data: {
				title: "人社"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "人社"
			}
		})
	/*idcard router end*/
})