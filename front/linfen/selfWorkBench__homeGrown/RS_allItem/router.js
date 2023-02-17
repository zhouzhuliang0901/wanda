app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/list");
	$stateProvider
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "list",
			data: {
				title: "城镇职工基本养老保险"
			}
		}).state("flexibleEmployment", {
			url: "/flexibleEmployment",
			templateUrl: "views/flexibleEmployment.html",
			controller: "flexibleEmployment",
			data: {
				title: "城镇职工基本养老保险"
			}
		})
})