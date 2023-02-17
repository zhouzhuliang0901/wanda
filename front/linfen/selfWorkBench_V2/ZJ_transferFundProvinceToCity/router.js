app.config(function($urlRouterProvider, $stateProvider) {
    /*elicense router start*/
    $urlRouterProvider.otherwise("/handleChoice");
    $stateProvider
        .state("handleChoice", {
            url: "/handleChoice",
            templateUrl: "views/handleChoice.html",
            controller: "handleChoice",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("guideline", {
            url: "/guideline",
            templateUrl: "views/guideline.html",
            controller: "guideline",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("loginType", {
            url: "/loginType",
            templateUrl: "views/loginType.html",
            controller: "loginType",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("login", {
            url: "/login",
            templateUrl: "views/login.html",
            controller: "login",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("handleInfo", { 
            url: "/handleInfo",
            templateUrl: "views/handleInfo.html",
            controller: "handleInfo",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        }).state("info", {
            url: "/info",
            templateUrl: "views/info.html",
            controller: "info",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        }).state("materialList", {
            url: "/materialList",
            templateUrl: "views/materialList.html",
            controller: "materialList",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("uploadMethod", {
            url: "/uploadMethod",
            templateUrl: "views/uploadMethod.html",
            controller: "uploadMethod",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("materialPic", {
            url: "/materialPic",
            templateUrl: "views/materialPic.html",
            controller: "materialPic",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("materialView", {
            url: "/materialView",
            templateUrl: "views/materialView.html",
            controller: "materialView",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("takePhoto", {
            url: "/takePhoto",
            templateUrl: "views/takePhoto.html",
            controller: "takePhoto",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
        .state("submit", { //江苏省 信息填写页
            url: "/submit",
            templateUrl: "views/submit.html",
            controller: "submit",
            data: {
                title: "个人住房公积金从外省市转移到本市"
            }
        })
})