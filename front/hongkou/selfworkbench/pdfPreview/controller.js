app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办理业务";
	try{
		window.external.URL_CLOSE();
	}catch(e){}
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(url, name) {
		appData.name = name;
		appData.url = url;
		$state.go("iframe");
	}
});
app.controller("synthesizeIframe", function($scope, $state, $timeout, appData, $sce, $location) {
//	$scope.name = appData.name + ".pdf";
	$scope.funName = '惠企政策';
////	$scope.src ="js/"+;
//	window.external.URL_OPEN(200,180,1500,700, "file:///D:/Debug/temp/file/"+$scope.name);
//	$scope.prevStep = function() {
//		$state.go("main");
//	}
	window.external.URL_OPEN(200,180,1500,700, "https://www.ssme.sh.gov.cn");
});