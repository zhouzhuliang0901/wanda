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
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx) {
		trackEvent('住建委', name);
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "CL_informationCard") {
			window.location.href = "../CL_informationCard/index.html"
		} else {
			window.location.href = "../"+type+"/index.html"
		}
	}
});