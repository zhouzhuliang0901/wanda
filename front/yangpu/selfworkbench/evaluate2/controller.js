app.controller("evaluateMain", function($scope, $state, appData) {
	$scope.funName = '评价及建议';
	$scope.next = function(){
		$state.go("List");
	}
});
app.controller("evaluateList", function($scope, $state, appData) {
	$scope.save = function(){
		$.ajax({
			url: "http://zwdtyp.sh.gov.cn:8088/ac/aci/autoterminal/forward.do",
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
				layer.msg("评价成功");
			},
			error:function(json){
				layer.msg("评价成功");
			}
	})
}
});
