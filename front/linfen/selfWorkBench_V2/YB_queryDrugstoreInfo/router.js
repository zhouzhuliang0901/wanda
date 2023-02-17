app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/choose");

	$stateProvider
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "医保"
			}
		})
		.state("queryList", {
			url: "/queryList",
			templateUrl: "views/queryList.html",
			controller: "queryList",
			data: {
				title: "医保"
			}
		})
	/*idcard router end*/
})