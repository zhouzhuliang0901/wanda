app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/main', {
            templateUrl: 'views/main.html',
            controller: 'mainController'
        })
        .when('/idcard', {
            templateUrl: '../libs/common/views/idCard.html',
            controller: 'idcardController'
        })
        .when('/base', {
            templateUrl: 'views/base.html',
            controller: 'baseController'
        })
        .when('/ext', {
            templateUrl: 'views/ext.html',
            controller: 'extController'
        })
        .when('/tphoto', {
            templateUrl: 'views/tphoto.html',
            controller: 'tphotoController'
        })
        .when('/key', {
            templateUrl: 'views/key.html',
            controller: 'keyController'
        })
        .when('/space', {
            templateUrl: 'views/space.html',
            controller: 'spaceController'
        })
        .when('/choose', {
            templateUrl: '../libs/common/views/choose.html',
            controller: 'chooseController'
        })
        .when('/pay', {
            templateUrl: '../libs/common/views/pay.html',
            controller: 'payController'
        })
        .when('/pwait', {
            templateUrl: '../libs/common/views/pwait.html',
            controller: 'printController'
        })
        .when('/licenseLibrary', {
            templateUrl: 'views/licenseLibrary.html',
            controller: 'licenseLibraryController'
        })
        .otherwise({
            redirectTo: '/main'
        })
}]);