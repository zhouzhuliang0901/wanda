app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/start',{templateUrl:'views/start.html', controller:'startController'})
    	.when('/main',{templateUrl:'views/main.html', controller:'mainController'})
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
//      .when('/citylist',{templateUrl:'views/Citylist.html', controller:'citylistController'})
		.when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
		.when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
		.otherwise({
			redirectTo: '/start'
		})
}]);