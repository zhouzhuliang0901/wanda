app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/list");
	
	$stateProvider
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "faceList",
			data: {
				title: "人证核验"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "faceMain",
			data: {
				title: "人证核验"
			}
		})
		.state("citizen", {
			url: "/citizen",
			templateUrl: "views/citizen.html",
			controller: "faceCitizen",
			data: {
				title: "人证核验"
			}
		})
	/*idcard router end*/
})