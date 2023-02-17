app.config(['$routeProvider',function($routeProvider){
    $routeProvider
		.when('/list',{templateUrl:'views/list.html', controller:'listController'})
		.when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
		.when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
		.otherwise({
			redirectTo: '/list'
		})
}]);