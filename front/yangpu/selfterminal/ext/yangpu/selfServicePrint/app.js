var app = angular.module("selfServicePrintApp",['ng','ngRoute']);
app.value("data",{});
app.run(["$rootScope", '$log', "$location", function ($rootScope, $log, $location) {
    $rootScope.$on('$routeChangeSuccess', function (evt, next, current) {
        $.device.officeClose();
    });
}]);
