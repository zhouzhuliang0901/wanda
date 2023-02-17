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
		templateUrl: "directive/loading.html",
		scope: {},
		controller: function($scope, $location, $rootScope, $interval, $timeout, $sce,appData) {
			if(appData.version == "Chinese"){
				$scope.content = "正在加载数据，请稍后...";
			}else if(appData.version == "English"){
				$scope.content = "Loading, please wait";
			}
		}
	}
});