app.config(function($urlRouterProvider, $stateProvider) {
    /*elicense router start*/
    $urlRouterProvider.otherwise("/guideline");
    $stateProvider
        .state("guideline", {
            url: "/guideline",
            templateUrl: "views/guideline.html",
            controller: "guideline",
            data: {
                title: "个人申请出具异地贷款缴存使用证明"
            }
        })
        .state("loginType", {
            url: "/loginType",
            templateUrl: "views/loginType.html",
            controller: "loginType",
            data: {
                title: "个人申请出具异地贷款缴存使用证明"
            }
        })
        .state("login", {
            url: "/login",
            templateUrl: "views/login.html",
            controller: "login",
            data: {
                title: "个人申请出具异地贷款缴存使用证明"
            }
        })
        .state("info", { //信息填写页
            url: "/info",
            templateUrl: "views/info.html",
            controller: "info",
            data: {
                title: "个人申请出具异地贷款缴存使用证明"
            }
        }).state("resultList", {
            url: "/resultList",
            templateUrl: "views/resultList.html",
            controller: "resultList",
            data: {
                title: "个人申请出具异地贷款缴存使用证明"
            }
        }).state("resultDetail", {
            url: "/resultDetail",
            templateUrl: "views/resultDetail.html",
            controller: "resultDetail",
            data: {
                title: "个人申请出具异地贷款缴存使用证明"
            }
        }).state("submit", {
            url: "/submit",
            templateUrl: "views/submit.html",
            controller: "submit",
            data: {
                title: "个人申请出具异地贷款缴存使用证明"
            }
        })
})