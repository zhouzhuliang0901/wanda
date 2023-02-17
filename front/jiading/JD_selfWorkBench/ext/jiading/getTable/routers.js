app.config(['$routeProvider',function($routeProvider){
    $routeProvider
    	.when('/start',{templateUrl:'views/start.html', controller:'startController'})
    	.when('/innerH',{templateUrl:'views/innerH.html', controller:'innerHController'})
		.otherwise({
			redirectTo: '/start'
		})
}]);