app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/icbc", {
            templateUrl: "views/icbc.html",
            controller: "icbcController"
        })
        .when("/ccb", {
            templateUrl: "views/ccb.html",
            controller: "ccbController"
        })
        .when("/xmbc", {
            templateUrl: "views/xmbc.html",
            controller: "xmbcController"
        })
        .when("/detail", {
            templateUrl: "views/detail.html",
            controller: "detailController"
        })
        .otherwise({
            redirectTo: '/icbc'
        })

}])