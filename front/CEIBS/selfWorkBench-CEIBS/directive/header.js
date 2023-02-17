app.directive("appHeader", function () {
    return {
        restrict: "E",
        templateUrl: "directive/header.html",
        scope: {
            funName: "@",
        },
        controller: function ($scope, $location, $state, $rootScope, $interval, $timeout) {}
    }
});
