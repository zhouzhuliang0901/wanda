var app = angular.module("autoformApp", ["ng", "ngRoute"]);
app.run(["$rootScope", '$log', "$location", function ($rootScope, $log, $location) {
    $rootScope.$on('$routeChangeStart', function (evt, next, current) {
        try {
            if (current.$$route.originalPath !== $location.path()) {
                $.device.officeClose();
                OcxControl.idCardClose();
            }
        } catch (error) {
            console.log("未知路由!")
        }
    });
}]);
app.value('data',{});