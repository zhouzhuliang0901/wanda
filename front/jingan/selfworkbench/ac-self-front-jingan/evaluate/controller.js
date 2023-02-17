app.controller("evaluateMain", function($scope, $state, appData, $timeout) {
	$scope.funName = '评价及建议';
	$scope.imgSrc1 = 'images/评价-好评.png';
	$scope.imgSrc2 = 'images/评价-中评.png';
	$scope.imgSrc3 = 'images/评价-差评.png';
	$scope.next = function(type){
		appData.type = type;
		if(type == '1') {
			$scope.imgSrc1 = 'images/评价-好评1.png';
			$scope.imgSrc2 = 'images/评价-中评.png';
			$scope.imgSrc3 = 'images/评价-差评.png';
		} else if(type == '2') {
			$scope.imgSrc2 = 'images/评价-中评1.png';
			$scope.imgSrc1 = 'images/评价-好评.png';
			$scope.imgSrc3 = 'images/评价-差评.png';
		} else if(type == '3') {
			$scope.imgSrc3 = 'images/评价-差评1.png';
			$scope.imgSrc1 = 'images/评价-好评.png';
			$scope.imgSrc2 = 'images/评价-中评.png';
		}
		$timeout(function () {
			$state.go("List");
		}, 300);
	}
});
app.controller("evaluateList", function($scope, $state, appData) {
	$scope.isAlert = false;
	$scope.alertConfirm = function(){
		$scope.isAlert = false;
//		window.location.href = '../guideline/index.html#/start';
		$.device.GoHome();
	}
	$scope.prevStep = function () {
		window.location.href = '../guideline/index.html#/start';
	}
	$scope.save = function(){
		$scope.concel = 'false';
		$.ajax({
			url: "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/autoterminal/forward.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				fmd: "aci-SelfmOpinion", 		
				fdo: "saveSelfmOpinion",
//				nmSatisfation:appData.type,
				stUname :encodeURI($('#name').val()),
				stPhone :$('#mobile').val(),
				stUnit :encodeURI($('#targetname').val()),
				stContent : encodeURI($('#text').val()),
				stMachineId : $.config.get('uniqueId') || '12-12-12-12',
			},
			success:function(json){
				$scope.isAlert=true;
				$scope.msg = "评价成功";
//				$scope.alertConfirm();
			},
			error:function(json){
				$scope.isAlert=true;
				$scope.msg = "评价成功";
//				$scope.alertConfirm();
			}
		})
	}
});
