app.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
    	.state('choiceArea', {
            url: '/',
            templateUrl: 'views/choiceArea.html',
            controller: 'choiceArea'
        })
    	.state('choiceCenter', {
            url: '/choiceCenter',
            templateUrl: 'views/choiceCenter.html',
            controller: 'choiceCenter'
        })
    	.state('one', {
            url: '/one',
            templateUrl: 'views/one.html',
            controller: 'onecontroller'
        }).state('tow', {
            url: '/tow',
            templateUrl: 'views/tow.html',
            controller: 'towcontroller'
        }).state('three', {
            url: '/three',
            templateUrl: 'views/three.html',
            controller: 'threecontroller',
        }).state('four', {
            url: '/four',
            templateUrl: 'views/four.html',
            controller: 'fourcontroller'
        }).state('five', {
            url: '/five',
            templateUrl: 'views/five.html',
            controller: 'fivecontroller'
        })
        .state('six', {
            url: '/six',
            templateUrl: 'views/six.html',
            controller: 'sixcontroller'
        })
    $urlRouterProvider.otherwise('/');

});