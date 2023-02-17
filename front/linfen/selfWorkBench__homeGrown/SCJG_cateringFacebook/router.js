app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/choose");

	$stateProvider
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "市场监管局"
			}
		})
		.state("queryList", {
			url: "/queryList",
			templateUrl: "views/queryList.html",
			controller: "queryList",
			data: {
				title: "市场监管局"
			}
		})
		.state("detail", {
			url: "/detail",
			templateUrl: "views/detail.html",
			controller: "detail",
			data: {
				title: "市场监管局"
			}
		})
	/*idcard router end*/
})