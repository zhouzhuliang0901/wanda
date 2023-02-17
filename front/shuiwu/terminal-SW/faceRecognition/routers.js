app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/main',{templateUrl:'views/main.html', controller:'mainController'})
        .when('/capture',{templateUrl:'views/capture.html', controller:'captureController'})
        .when('/pwait',{templateUrl:'../libs/common/views/pwait.html', controller:'printController'})
        .otherwise({redirectTo:'/main'})
}]);