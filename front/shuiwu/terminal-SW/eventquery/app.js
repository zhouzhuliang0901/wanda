var app = angular.module("eventqueryApp", ["ng", "ngRoute"]);
app.value('data', {});
app.run(["$rootScope", '$log', "$location", function ($rootScope, $log, $location) {
    $rootScope.$on('$routeChangeStart', function (evt, next, current) {
        try {
            if (current.$$route.originalPath !== $location.path()) {
                OcxControl.idCardClose();
                OcxControl.BarcodeClose();
            }
        } catch (error) {
            console.log("未知路由!")
        }
    });
}]);

function getCodeApplyNo(str, name) {
    if (str.indexOf("http") !== 0) {
        return str;
    };
    var params = str.split('?')[1].split("=");
    var parObj = {};
    for (var i = 0; i < params.length; i++) {
        if ((i + 2) % 2 === 0) {
            parObj[params[i]] = params[i + 1];
        }
    }
    if (!parObj[name]) {
        return null;
    } else {
        return parObj[name];
    }
}