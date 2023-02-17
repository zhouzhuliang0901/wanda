app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择服务内容";
	$scope.stuffName = perjsonStr;
	$scope.concel = "false";
	$scope.choiceType = function(name,url) {
		appData.funName = name;
		window.location.href = url
	}
});