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
app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择服务";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(url, name) {
		trackEvent($.config.get('uniqueId'),name);
		trackEventSelf(name, name);
		window.location.href = url;
	}
});