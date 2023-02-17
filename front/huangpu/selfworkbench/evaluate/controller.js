app.controller("main", function($scope, $state, appData, $sce) {
	$scope.choice = function(address) {
		appData.address = address;
		$state.go("iframe");
	};
	$scope.advise = function() {
		$state.go("list");
	}
});
app.controller("list", function($scope, $state, appData) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function(){
		$state.go("main");
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
app.controller("iframe", function($scope, $state, $timeout, appData, $sce, $location) {
	$scope.funName = appData.funName;
	$scope.prevStep = function() {
		window.external.URL_CLOSE();
		$state.go("main");
	}
	console.log($scope.funName + "-------" + appData.address);
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(200,180,1500,700,appData.address);
});