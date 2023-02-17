var app = angular.module("declareApp", ["ng", "ngRoute"]);
app.factory('data', function() {
	return {};
});
//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					scrollX: false,
					scrollY: true,
					scrollbar: true,
					preventDefault: false,
					checkDOMChanges: true,
					click: true,
					tap: true
				})
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		text = text.replace(/nbsp&/ig, "");
		return $sce.trustAsHtml(text);
	};
}]);
// 页面渲染完成监听事件
app.directive('onFinish', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    }});