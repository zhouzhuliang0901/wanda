app.controller("main", function($scope, $state, appData) {
	$scope.funName = "帮办助手";
	$scope.choice = function(address) {
		window.location.href = address;
	};
	$scope.aa = function() {
		window.location.href = "http://zwdt.huangpuqu.sh.cn:8080/ac/ext/huangpu/selfterminal/businessstart_1.jsp?itemId=be330267-da98-4d8e-b79b-33ca3722c922";
	}
	$scope.type = function(){
		$state.go("choice");
	};
});
app.controller("choice",function($scope, $state, appData){
	$scope.funName = "中心微信号";
	$scope.service = function(address){
		appData.address = address;
		appData.funName = "服务指南";
		$state.go("iframe");
	}
	$scope.downLoad = function(){
		$state.go("code");
	}
});
