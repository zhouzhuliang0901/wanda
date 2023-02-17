app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
        .when('/itemlist',{templateUrl:'views/itemlist.html', controller:'itemlistController'})
		.when('/select',{templateUrl:'views/select.html', controller:'selectController'})
		.when('/idCard',{templateUrl:'views/idCard.html', controller:'idCardController'})
		.when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
		.when('/iframe',{templateUrl:'views/iframe.html', controller:'iframeController'})
		.otherwise({
			redirectTo: '/list'
		})
}]);