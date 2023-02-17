app.controller('rdLoginType', function($state, $scope, appData,$http) {
	$scope.operation = "请选择登录方式";
	$scope.person = true;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("rdLogin"); 
		
	}
});
app.controller('rdLogin', function($scope, $http, $state, appData, appFactory,$timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.alertConfirm = function(){
	
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办";
			break;
	}
	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.idcardLogin = function(info) {
		if(info) {
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			$scope.goToApp();
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$scope.goToApp();
	}
	
	$scope.goToApp = function() {
		var address = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/scene-recommend-list?identity="+appData.licenseNumber;
		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
	$scope.prevStep = function(){
		$state.go("rdLoginType");
	}
});
app.controller("synthesizeIframe", function($scope, $state,$timeout, appData,$sce) {
	$scope.address = $sce.trustAsResourceUrl(appData.address);	 
	window.external.URL_OPEN(50,160,1800,800,appData.address);
});