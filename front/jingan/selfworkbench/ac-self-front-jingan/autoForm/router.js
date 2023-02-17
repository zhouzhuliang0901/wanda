app.config(function($urlRouterProvider, $stateProvider) {
    /*elicense router start*/
    $urlRouterProvider.otherwise("/main");

    $stateProvider
        .state("main", {
            url: "/main",
            templateUrl: "views/main.html",
            controller: "formMain",
            data: {
                title: "自助取表"
            }
        })
        .state("explain", {
            url: "/explain",
            templateUrl: "views/explain.html",
            controller: "formExplain",
            data: {
                title: "自助取表"
            }
        })
        .state("paste", {
            url: "/paste",
            templateUrl: "views/pasteIdcard.html",
            controller: "formPaste",
            data: {
                title: "自助取表"
            }
        })
        .state("loginType", {
            url: "/loginType",
            templateUrl: "views/loginType.html",
            controller: "formLoginType",
            data: {
                title: "自助取表"
            }
        })
        .state("login", {
            url: "/login",
            templateUrl: "views/login.html",
            controller: "formLogin",
            data: {
                title: "自助取表"
            }
        })
        .state("print", {
            url: "/print",
            templateUrl: "views/print.html",
            controller: "formPrint",
            data: {
                title: "自助取表"
            }
        });
});
