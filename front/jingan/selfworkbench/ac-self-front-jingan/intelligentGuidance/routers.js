app.config(['$routeProvider',function($routeProvider){
    $routeProvider
    	.when('/main',{templateUrl:'views/main.html', controller:'mainController'})
    	.when('/companyStart',{templateUrl:'views/companyStart.html', controller:'companyStartController'})
    	.when('/projectConstruction',{templateUrl:'views/projectConstruction.html', controller:'projectConstructionController'})
		.otherwise({
			redirectTo: '/main'
		})
}]);