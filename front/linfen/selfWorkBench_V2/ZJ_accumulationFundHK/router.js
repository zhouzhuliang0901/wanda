app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state('main', {
			url: '/main',
			templateUrl: 'views/main.html',
			controller: 'main',
			data: {
				title: '住建'
			}
		})
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "住建"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "住建"
			}
		})
	/*idcard router end*/
})