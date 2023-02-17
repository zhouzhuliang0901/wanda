app.controller("waiter", function($scope, $state, appData, $sce) {
	//店小二
	// 	window.external.URL_OPEN(50,160,1800,800, "http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");
	// 	$scope.address = $sce.trustAsResourceUrl("http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");	
	//$scope.chooseFive = function(){
	//window.external.URL_OPEN(50,160,1800,800, "http://gcls.sh.gov.cn/user/login");
	//$scope.address = $sce.trustAsResourceUrl("http://gcls.sh.gov.cn/user/login");	
	// }
	$scope.chooseFour = function() {
		$state.go("loginType");
		appData.url = "http://yct.sh.gov.cn/portal_yct/";
		//	 	window.external.URL_OPEN(50,160,1800,800, "http://yct.sh.gov.cn/portal_yct/");
		// 		$scope.address = $sce.trustAsResourceUrl("http://yct.sh.gov.cn/portal_yct/");	
	}
	$scope.chooseThree = function() {
		window.external.URL_OPEN(50, 150, 1800, 800, "http://xzsp.scjgj.sh.gov.cn/shzjxzsp/login/outNet");
		$scope.address = $sce.trustAsResourceUrl("http://xzsp.scjgj.sh.gov.cn/shzjxzsp/login/outNet");
	}
	$scope.chooseTwo = function() {
		window.external.URL_OPEN(50, 150, 1800, 800, "http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");
		$scope.address = $sce.trustAsResourceUrl("http://tzjc.scjgj.sh.gov.cn/secms/loginout.jsp");
	}
	$scope.chooseOne = function() {
		window.external.URL_OPEN(50, 150, 1800, 800, "http://xuke.smda.sh.cn/AppRoveManage/SQ0106CatalogController/itemDepartment");
		$scope.address = $sce.trustAsResourceUrl("http://xuke.smda.sh.cn/AppRoveManage/SQ0106CatalogController/itemDepartment");
	}
	$scope.chooseFive = function() {
		window.external.URL_OPEN(50, 150, 1800, 800, "http://www.jiading.gov.cn/");
		$scope.address = $sce.trustAsResourceUrl("http://www.jiading.gov.cn/");
	}


	//	window.external.URL_OPEN(50,160,1800,800, "http://zwdthp.sh.gov.cn/zwdtSW/smart/zwdtSW/workGuide/typeGuide.jsp");
	//	$scope.address = $sce.trustAsResourceUrl("http://zwdthp.sh.gov.cn/zwdtSW/smart/zwdtSW/workGuide/typeGuide.jsp");
});
app.controller('licenseLoginType', function($state, $scope, appData, $http) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {
		//TODO handle the exception
	}
	$scope.operation = "请选择登录方式";
	$scope.person = true;
	$scope.choiceLogin = function(type) {
//				appData.licenseNumber = "310105197805313613";
//				appData.licenseName = "陈云翔";
//				$state.go("apply");
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('licenseLogin', function($scope, $http, $state, appData, appFactory, $timeout) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {
		//TODO handle the exception
	}
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {

	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}

	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}

	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("apply");
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$state.go("apply");
	}
})
app.controller('apply', function($state, $scope, appData, $http) {
	$.device.idCardClose();
	$.device.qrCodeClose();
	$scope.info = {
		type:"1",
		idCard:appData.licenseNumber,
		name:appData.licenseName
	}
	console.log($scope.info);
	var httpConfig = {
		jsonpCallback: "JSON_CALLBACK",
		data:encodeURIComponent(JSON.stringify($scope.info))
	}
	console.log(httpConfig);
	$scope.encryptDataByRSA = function(){
		$http.jsonp('http://hengshui.5uban.com/xhac/aci/workPlatform/util/encryptDataByRSA.do',{
			params:httpConfig
		}).success(function(dataJson){
			console.log(dataJson.result);
			$scope.url = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpUcRedirect.do?app_id=535984a5&data=" + encodeURIComponent(dataJson.result) + "&redirect_uri=";
			console.log("666"+$scope.url + appData.url);
			window.external.URL_OPEN(50, 160, 1800, 800, $scope.url + encodeURIComponent(appData.url));
		}).error(function(err){
			console.log('encryptDataByRSA err');
		});
		
	}
	$scope.encryptDataByRSA();
	
});