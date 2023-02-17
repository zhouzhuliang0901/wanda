app.controller("synthesizeMain", function($scope, $state, appData) {
	$scope.funName = "政策推送服务";
	$scope.choice = function(address,funName) {
		appData.address = address;
		appData.funName = funName;
		$state.go("iframe");
	};
	$scope.type = function(){
		$state.go("choice");
	};
	$scope.goToApp = function(address) {
		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
});
app.controller("choiceType",function($scope, $state, appData){
	$scope.funName = "工伤认定、申请";
	$scope.service = function(address){
		appData.address = address;
		appData.funName = "服务指南";
		$state.go("iframe");
	}
	$scope.downLoad = function(){
		$state.go("code");
	}
	$scope.prevStep = function(){
		$state.go("main");
	}
});
app.controller("synthesizeIframe", function($scope, $state,$timeout, appData,$sce) {
	$scope.funName = appData.funName;
	console.log($scope.funName);
	$scope.address = $sce.trustAsResourceUrl(appData.address);	 
	window.external.URL_OPEN(200,180,1500,700,appData.address);
	$scope.prevStep = function(){
		$state.go("main");
	}
});
app.controller("code", function($scope, $state,$timeout, appData,$sce) {
	$scope.funName = "表格下载";
	$scope.prevStep = function(){
		$state.go("choice");
	}
});