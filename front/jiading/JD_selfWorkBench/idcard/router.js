app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "idcardMain",
			data: {
				title: "身份证复印"
			}
		})
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "idcardChoose",
			data: {
				title: "身份证复印"
			}
		})
	/*idcard router end*/
})