app.controller('login', function($state, $scope, appData, $http) {
	$scope.operation = "请选择登录方式";
	$scope.choice = function() {
		appData.Number = "430426199804106174";
		appData.Name = "邹天奇";
		$state.go("info");
	}
});
app.controller("info", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "继续";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stName = appData.Name;
	$scope.stIdCard = appData.Number;

	// 保存数据
	$scope.prevStep = function(){
		$state.go("login");
	}
	$scope.nextStep = function() {
		$state.go("update");
	};
});
app.controller("update", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "更新卡片";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("info");
	}
	$scope.nextStep = function() {
		$state.go("login");
	}
});
