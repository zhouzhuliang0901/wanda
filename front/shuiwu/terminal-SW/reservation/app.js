var app = angular.module("reservationApp", ["ng", "ngRoute"]);
app.value('data', {});
app.run(["$rootScope", '$log', "$location", function ($rootScope, $log, $location) {
    $rootScope.$on('$routeChangeStart', function (evt, next, current) {
        try {
            if (current.$$route.originalPath !== $location.path()) {
                OcxControl.idCardClose();
            }
        } catch (error) {
            console.log("未知路由!")
        }
    });
}]);
app.factory("$jsonp", function ($http) {
	var factory = {};
	factory.get = function (method, parameter, callback) {
		var httpConfig = angular.merge({
			username: "yun",
			password: "04b34557c2110962",
		}, parameter);
		$.ajax({
			url: $.getConfigMsg.preUrl + method,
			dataType: "jsonp",
			jsonp:"jsonpprifx",
			data: httpConfig,
			success: function (dataJsonp) {
				callback && callback(dataJsonp);
			},
			error: function (err) {
				layer.alert('对不起没有找到数据，请返回首页', {
					skin: 'layui-layer-lan',
					closeBtn: 0,
					anim: 4 //动画类型
				});
			}
		})
	}
	return factory;
});