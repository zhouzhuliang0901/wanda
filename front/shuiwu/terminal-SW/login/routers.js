app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/select',{templateUrl:'views/select.html', controller:'selectController'})        
        .when('/main',{templateUrl:'views/main.html', controller:'mainController'})
        .when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
	    .when('/capture',{templateUrl:'views/capture.html', controller:'captureController'})
        // 法人
        .when('/key', {
            templateUrl: 'views/key.html',
            controller: 'keyController'
        })
        // 亮证
        .when('/idcard', {
            templateUrl: '../libs/common/views/idCard.html',
            controller: 'idcardController'
        })
        .otherwise({redirectTo:'/select'})
}]);