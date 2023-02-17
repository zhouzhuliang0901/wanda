app.config(function($routeProvider) {
    $routeProvider
        .when('/main', {
            templateUrl: 'views/main.html',
            controller: 'mainController'
        })
        .when('/note', {
            templateUrl: 'views/note.html',
            controller: 'noteController'
        })
        .when('/idCard', {
            templateUrl: '../libs/common/views/idCard.html',
            controller: 'idCardController'
        })
        .when('/fillform', {
            templateUrl: 'views/fillform.html',
            controller: 'fillformController'
        })
        .when('/choose', {
            templateUrl: '../libs/common/views/choose.html',
            controller: 'chooseController'
        })
        .when('/pwait', {
            templateUrl: '../libs/common/views/pwait.html',
            controller: 'printController'
        })
        .otherwise({
            redirectTo: '/main'
        })
});
