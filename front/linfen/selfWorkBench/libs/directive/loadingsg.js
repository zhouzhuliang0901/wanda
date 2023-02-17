//进入模块加载
setTimeout(function() {
	$(".loadingBox").css("display", "none");
}, 500);
//动画效果
function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
//
app.directive("loading", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/loading.html",
		scope: {},
		controller: function($scope, $location, $rootScope, $interval, $timeout, $sce) {}
	}
});