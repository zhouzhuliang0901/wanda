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
	$scope.stuffName1 = perjsonStr1;
	$scope.stuffName2 = perjsonStr2;
	$scope.stuffName3 = perjsonStr3;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.getMatterCon = function(itemName, code, type, url) {
		if(url=="flexibleEmployment"){
			$state.go("flexibleEmployment");
		}else{
			window.location.href = url;
		}
		appData.itemName = itemName;
		appData.code = code;
		appData.type = type;
	};
});
app.controller("flexibleEmployment", function($scope, $state, appData) {
	$scope.operation = "请选择办事事项";
	$(".headName").html("灵活就业登记");
	$scope.stuffName = flexibleEmployment;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function(){
		$.device.GoHome();
	}
	$scope.getMatterCon = function(type) {
		window.location.href = type;
	};
});