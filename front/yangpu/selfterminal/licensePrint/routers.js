app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/main', {
            templateUrl: 'views/main.html',
            controller: 'mainController'
        })
		.when('/choose', {
            templateUrl: '../libs/common/views/choose.html',
            controller: 'chooseController'
        })
        .when('/pwait', {
            templateUrl: '../libs/common/views/pwait.html',
            controller: 'printController'
        })
        .when('/pay', {
            templateUrl: '../libs/common/views/pay.html',
            controller: 'payController'
        })
        .otherwise({
            redirectTo: '/main'
        })
}]);