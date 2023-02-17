app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/login");
	$stateProvider

		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "居住签证"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "居住签证"
			}
		})
		.state("update", {
			url: "/update",
			templateUrl: "views/update.html",
			controller: "update",
			data: {
				title: "居住签证"
			}
		})
})