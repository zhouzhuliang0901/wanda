//公用单选
function PublicchoiceById(PcId) {
	$("#" + PcId + " a").click(function() {
		if($(this).attr('class') == 'in') {
			$(this).removeClass("in");
		} else {
			$("#" + PcId + " a").removeClass("in");
			$(this).addClass("in");
		}
	})
}
app.controller("evaluateMain", function($scope, $state, appData) {
	$scope.funName = '评价及建议';
	$scope.next = function(){
		$state.go("List");
	}
});
app.controller("evaluateList", function($scope, $state, appData) {
	PublicchoiceById("info");
	$scope.nextTips = "确认";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function(){
		$.device.GoHome();
	}
	$scope.prevStep = function(){
		$state.go("main");
	}
	$scope.nextStep = function(){
		$scope.isAlert=true;
		$scope.msg = "提交成功";
		$.ajax({
			url: "http://172.16.125.53:8080/ac-product/aci/autoterminal/forward.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				fmd: "aci-SelfmOpinion", 		
				fdo: "saveSelfmOpinion",
				stUname :encodeURI($('#name').val()),
				stPhone :$('#mobile').val(),
				stUnit :encodeURI($('#targetname').val()),
				stContent : encodeURI($('#text').val()),
				stMachineId : $.config.get('uniqueId') || '12-12-12-12',
			},
			success:function(json){
				$scope.alertConfirm();
			},
			error:function(json){
				$scope.alertConfirm();
			}
	})
}
});
