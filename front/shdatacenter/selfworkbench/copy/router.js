app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/copyType");

	$stateProvider
		.state("copyType", {
			url: "/copyType",
			templateUrl: "views/copyType.html",
			controller: "copyType",
			data: {
				title: "材料复印"
			}
		})
	
	/*idcard router end*/
})