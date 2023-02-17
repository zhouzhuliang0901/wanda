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
app.controller("list", function($scope, $state, appData) {
	$scope.operation = "请选择办事事项";
	$scope.stuffName1 = DA_allItem;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName,url) {
		window.location.href = url;
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
		//		if(code == "") {
		//			$scope.isAlert = true;
		//			$scope.msg = "暂未开放";
		//		} else if(code == "RS_ssCardInfo") {
		//			window.location.href = "../RS_ssCardInfo/index.html"
		//		} else {
		//			$state.go("loginType");
		//		}
	};
});