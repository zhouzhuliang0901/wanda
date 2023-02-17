app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
        .when('/item',{templateUrl:'views/item.html', controller:'itemController'})
        .when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
        .when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
        .when('/precautions',{templateUrl:'views/precautions.html', controller:'preController'})
        .otherwise({redirectTo:'/list'})
}]);