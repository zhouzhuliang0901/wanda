app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/main',{templateUrl:'../libs/common/views/idCard.html', controller:'mainController'})
        .when('/choose',{templateUrl:'../libs/common/views/choose.html', controller:'chooseController'})
        .when('/pay',{templateUrl:'../libs/common/views/pay.html', controller:'payController'})
        .when('/pwait',{templateUrl:'../libs/common/views/pwait.html', controller:'printController'})
        .when('/hint',{templateUrl:'views/hint.html', controller:'hintController'})
        .otherwise({redirectTo:'/main'})
}]);