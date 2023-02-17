var app = angular.module("eventqueryApp", ["ng", "ngRoute"]);
app.run(["$rootScope", '$log', "$location", function ($rootScope, $log, $location) {
    $rootScope.$on('$routeChangeStart', function (evt, next, current) {

        try {
            if (current.$$route.originalPath !== $location.path()) {
               $rootScope.prevRouteName = current.$$route.originalPath;

                OcxControl.idCardClose();
                OcxControl.BarcodeClose();
            }
        } catch (error) {
            console.log("未知路由!")
        }
    });
}]);
app.value('data',{});
function getCodeApplyNo(str, name) {
    if (str.indexOf("http") !== 0) {
        return str;
    };
    var params = str.split('?')[1].split("&");
    var parObj = {};
    for (var i = 0; i < params.length; i++) {
		var  arr = params[i].split("=");
        parObj[arr[0]] = arr[1];
    }
    if (!parObj[name]) {
        return null;
    } else {
        return parObj[name];
    }
}