app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择查询内容";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(type, name, url) {
		appData.funName = name;
		appData.url = url;
		$state.go('iframe');
	}
});
app.controller('iframe', function($state, $scope, appData, $location,$sce) {
	$scope.address = $sce.trustAsResourceUrl(appData.url);
});