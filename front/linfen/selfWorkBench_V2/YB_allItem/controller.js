app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择服务内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, ywlx,url) {
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		window.location.href = url
	}
});