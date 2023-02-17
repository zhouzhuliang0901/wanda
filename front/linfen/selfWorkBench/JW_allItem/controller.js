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
		appData.funName = name;
		trackEvent(name);
		appData.type = type;
		appData.ywlx = ywlx;
		if(type == "JW_studentAffairsCenter") {
			window.location.href = "../JW_studentAffairsCenter/index.html"
		} else if(type == "JW_privateEducational") {
			window.location.href = "../JW_privateEducational/index.html"
		}else if(type == "JW_modelHighschool") {
			window.location.href = "../JW_modelHighschool/index.html"
		}else{
			window.location.href = type;
		}
	}
});