app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/choose");

	$stateProvider
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "教委"
			}
		})
		.state("detail", {
			url: "/detail",
			templateUrl: "views/detail.html",
			controller: "detail",
			data: {
				title: "教委"
			}
		})
	/*idcard router end*/
})