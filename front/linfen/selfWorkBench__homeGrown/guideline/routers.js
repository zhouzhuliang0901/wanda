app.config(['$routeProvider',function($routeProvider){
    $routeProvider
    	// 原来的start路由
        .when('/main',{templateUrl:'views/main.html', controller:'mainController'})
    	.when('/main_old',{templateUrl:'views/main_old.html', controller:'main_oldController'})
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
        .when('/citylist',{templateUrl:'views/Citylist.html', controller:'citylistController'})
		.when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
		.when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
		.otherwise({
			redirectTo: '/citylist'
		})
}]);