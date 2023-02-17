var app = angular.module("serviceFileApp", ["ng", "ngRoute"]);
app.value('data', {});
app.run(["$rootScope", '$log', "$location", function ($rootScope, $log, $location) {
    $rootScope.$on('$routeChangeStart', function (evt, next, current) {
        try {
            if (current.$$route.originalPath !== $location.path()) {
                OcxControl.idCardClose();
                OcxControl.BarcodeClose();
                
            }
        } catch (error) {
            console.log("未知路由!");
        }
    });
}]);
app.factory("customFetch", function($http) {
	var factory = {};
	var httpConfig = {
		username: "yun",
		password: "04b34557c2110962",
		jsonpprifx: "JSON_CALLBACK"
	}
	factory.jsonp = function(api, parameter, res, err) {
		httpConfig = angular.merge(httpConfig, parameter);
		$http.jsonp($.getConfigMsg.preUrl + api, {
				params: httpConfig
			})
			.success(function(data) {
				res && res(data);
			})
			.error(function(error) {
				err && err(error);
			});
	};
	factory.post = function(api, parameter, res, err) {
		httpConfig = angular.merge(httpConfig, parameter);
		$http({
				method: "post",
				url: $.getConfigMsg.preUrl + api,
				data: parameter
			})
			.success(function(data) {
				res && res(data);
			})
			.error(function(error) {
				console.log(error)
				err && err(error);
			});
	};
	return factory;
});