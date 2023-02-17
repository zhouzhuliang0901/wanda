app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/rdLoginType");

	$stateProvider
		.state("rdLoginType", {
			url: "/rdLoginType",
			templateUrl: "views/loginType.html",
			controller: "rdLoginType",
			data: {
				title: "为您推荐"
			}
		})
		.state("rdLogin", {
			url: "/rdLogin",
			templateUrl: "views/login.html",
			controller: "rdLogin",
			data: {
				title: "为您推荐"
			}
		})
	
	/*idcard router end*/
})