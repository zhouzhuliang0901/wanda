app.config(function($routeProvider) {
	$routeProvider
		.when('/main', {
			templateUrl: 'views/main.html',
			controller: 'mainController'
		})
		.when('/list', {
			templateUrl: 'views/list.html',
			controller: 'listController'
		})
		.when('/detail', {
			templateUrl: 'views/detail.html',
			controller: 'detailController'
		})
		.when('/hint', {
			templateUrl: 'views/hint.html',
			controller: 'hintController'
		})
		.when('/input', {
			templateUrl: 'views/input.html',
			controller: 'inputController'
		})
		.otherwise({
			redirectTo: '/main'
		})
});