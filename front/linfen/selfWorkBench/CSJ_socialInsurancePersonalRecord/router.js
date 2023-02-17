app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/guideline");
	$stateProvider
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "社会保险个人权益记录单查询"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "社会保险个人权益记录单查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "社会保险个人权益记录单查询"
			}
		})
		.state("choiceProvince", {
			url: "/choiceProvince",
			templateUrl: "views/choiceProvince.html",
			controller: "choiceProvince",
			data: {
				title: "社会保险个人权益记录单查询"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "社会保险个人权益记录单查询"
			}
		})
})