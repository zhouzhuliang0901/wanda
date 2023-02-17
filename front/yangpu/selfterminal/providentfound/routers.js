app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/main',{templateUrl:'views/main.html', controller:'mainController'})
        .when("/verify",{templateUrl:'views/verify.html'})
        .when('/infoList',{templateUrl:'views/infoList.html', controller:'infoListController'})
        .when('/pwait',{templateUrl:'../libs/common/views/pwait.html', controller:'../libs/common/js/pwaitController'})
        .otherwise({redirectTo:'/main'})
}]);