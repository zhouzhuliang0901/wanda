app.controller("evaluateMain", function($scope, $state, appData) {
	$scope.funName = '评价及建议';
	$scope.next = function(type) {
		appData.type = type;
		$state.go("List");
	}
});
app.controller("evaluateList", function($scope, $state, appData) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.nextTxt = "提交";
	$scope.save = function() {
		$scope.isAlert = true;
				$scope.msg = "评价成功";
		$.ajax({
			url: "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/autoterminal/forward.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				fmd: "aci-SelfmOpinion",
				fdo: "saveSelfmOpinion",
				nmSatisfation: appData.type,
				stUname: encodeURI($('#name').val()),
				stPhone: $('#mobile').val(),
				stUnit: encodeURI($('#targetname').val()),
				stContent: encodeURI($('#text').val()),
				stMachineId: $.config.get('uniqueId') || '12-12-12-12',
			},
			success: function(json) {
				$scope.alertConfirm = function(){
					$.device.GoHome();
				};
			},
			error: function(json) {
				$scope.alertConfirm = function(){
					$.device.GoHome();
				};
			}
		})
	}
});