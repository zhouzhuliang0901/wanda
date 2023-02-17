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
app.run(function($rootScope, $log, $location) {
	$rootScope.$on("$viewContentLoaded", function(event, toState) {
//		$(function() {
//			$(".tabBotbox1inner1").perfectScrollbar();
//			$(".main2").perfectScrollbar();
//		});
		$rootScope.customMain = function(){
			try{
				window.external.URL_CLOSE();
			}catch(e){
				//TODO handle the exception
			}
			$location.path("/main");
		}
		$rootScope.goAppHistoryBack = function() {
			$.device.Camera_Hide();
			$.device.idCardClose();
			$.device.qrCodeClose();
			try{
				window.external.URL_CLOSE();
			}catch(e){}
//			console.log($location.path());
//			if($location.path()=="/main") {
//				$.device.GoHome();
//			} else {
//				$location.path("/main");
//			}
		};
	});
});

app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		try{
			text = text.replace(/nbsp&/ig, "");
		return $sce.trustAsHtml(text);
		}catch(e){}
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