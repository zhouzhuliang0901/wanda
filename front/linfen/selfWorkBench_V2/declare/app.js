var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
    return {};
});
app.filter('myFilter', function() {
	return function(value,collection) {
		var result = "";
		angular.forEach(collection, function(item) {
			//过滤数组中值与指定值相同的元素
			if(item['value'] == value) {
				result = item.name
			}
		});
		return result;
	}
})
app.factory('appData', function() {
    return {};
});
app.filter('to_trusted', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);
//自定义指令repeatFinish
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					scrollX: false,
					scrollY: true,
					scrollbar: true,
					click: true,
					tap: true
				})
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});
