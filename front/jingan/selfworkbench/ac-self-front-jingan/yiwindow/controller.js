app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办理业务";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(url, name) {
		appData.type = name;
		appData.url = url;
		$state.go("iframe");
	}
});
app.controller("synthesizeIframe", function($scope, $state, $timeout, appData, $sce, $location) {
	if(appData.type == null || appData.type == undefined || appData.type == "") {
		appData.type = $location.search().type;
	}
	if(appData.size == true) {
		$scope.size = appData.size;
	}
	$scope.funName = appData.type;
	appData.address = appData.url;
	$scope.prevStep = function() {
		window.history.go(-1);
	}
	console.log(appData.type);
	console.log($scope.funName + "-------" + appData.address);
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.location.href = appData.address;
});