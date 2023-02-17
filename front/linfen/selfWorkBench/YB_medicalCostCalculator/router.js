app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "公安"
			}
		})
		.state("infoEmployees", {
			url: "/infoEmployees",
			templateUrl: "views/infoEmployees.html",
			controller: "infoEmployees",
			data: {
				title: "公安"
			}
		})
		.state("infoResidents", {
			url: "/infoResidents",
			templateUrl: "views/infoResidents.html",
			controller: "infoResidents",
			data: {
				title: "公安"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "公安"
			}
		})
	/*idcard router end*/
})