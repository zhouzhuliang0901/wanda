var app = angular.module("autoformApp", ["ng", "ngRoute"]);
app.value('data', {});
app.run(["$rootScope", '$log', "$location", function ($rootScope, $log, $location) {
    $rootScope.$on('$routeChangeStart', function (evt, next, current) {
        try {
            if (current.$$route.originalPath !== $location.path()) {
                OcxControl.idCardClose();
                $.device.officeClose();
            }
        } catch (error) {
            console.log("未知路由!");
        }
    });
}]);