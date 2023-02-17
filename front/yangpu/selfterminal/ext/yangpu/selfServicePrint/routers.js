app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/main", {
            templateUrl: "views/main.html",
            controller: "mainController"
        })
        .when("/matter", {
            templateUrl: "views/matter.html",
            controller: "matterController"
        })
        .when("/preview", {
            templateUrl: "views/preview.html",
            controller: "previewController"
        })
        .when("/pwait", {
            templateUrl: "../../../libs/common/views/pwait.html",
            controller: "printController"
        })
        .when("/forms", {
            templateUrl: "views/forms.html",
            controller: "formsController"
        })
        .otherwise({
            redirectTo: '/main'
        })
}]);
