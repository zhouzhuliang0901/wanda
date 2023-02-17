var app = angular.module("terminalApp", ["ngAnimate", "ui.router"]);
app.value("appData", {});
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);

//自定义指令repeatFinish --------- 防止滑动时触发点击事件
app.directive('repeatFinish', function() {
	return {
		link: function(scope, element, attr) {
			if(scope.$last == true) {
				var scroll = new BScroll('.wrapper', {
					scrollX: false,
					scrollY: true,
					scrollbar: true,
					//     preventDefault: false,
					//     checkDOMChanges: true,
					click: true,
					tap: true
				})
				$('.wrapper').find("a").on('tap', function() {});
			}
		}
	}
});

app.run(function($rootScope, $log, $location, $state) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			if($state.$current.name == "main") {
				$.device.GoHome();
			} else {
				$location.path("/main");
			}
		};
	});
});
app.factory("appFactory", function($http, $rootScope) {
//	$.getConfigMsg.preUrlSelf = "http://10.2.104.95:8081/ac-self";
//	$.getConfigMsg.preUrlSelf = "http://180.169.7.194:8081/ac-self";
	let jsonp = function(url, data, success, failture) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + url,
			dataType: "json",
			jsonp: "jsonpCallback",
			data: data,
			success: function(res) {
				success(res);
			},
			err: function(err) {
				failture(err);
			}
		});
	}
	return {
		jsonp: jsonp,
	}
})