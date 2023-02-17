app.config(['$routeProvider',function($routeProvider){
    $routeProvider 
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
		.when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
		.when('/selectTwo',{templateUrl:'views/selectTwo.html', controller:'selectTwoController'})
		.when('/idCard',{templateUrl:'views/idCard.html', controller:'idCardController'})
		.when('/ssCard',{templateUrl:'views/ssCard.html', controller:'ssCardController'})
		.when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
		.when('/socialShow',{templateUrl:'views/socialShow.html', controller:'socialShowController'})
		.when('/socialComfirm',{templateUrl:'views/socialComfirm.html', controller:'socialComfirmController'})
		.otherwise({
			redirectTo: '/list'
		})
}]);