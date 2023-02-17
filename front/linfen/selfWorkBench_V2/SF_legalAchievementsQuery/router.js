app.config(function($urlRouterProvider, $stateProvider) {
    /*elicense router start*/
    $urlRouterProvider.otherwise("/loginType");
    $stateProvider
        .state("loginType", {
            url: "/loginType",
            templateUrl: "views/loginType.html",
            controller: "loginType",
            data: {
                title: "法职成绩查询"
            }
        })
        .state("login", {
            url: "/login",
            templateUrl: "views/login.html",
            controller: "login",
            data: {
                title: "法职成绩查询"
            }
        })
       .state("info", {
            url: "/info",
            templateUrl: "views/info.html",
            controller: "info",
            data: {
                title: "法职成绩查询"
            }
        }).state("result", {
            url: "/result",
            templateUrl: "views/result.html",
            controller: "result",
            data: {
                title: "法职成绩查询"
            }
        })
})