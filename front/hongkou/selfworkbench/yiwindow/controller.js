app.controller("waiter", function ($scope, $state, appData,$sce,$location) {
	$scope.funName = "无人干预";
	if(appData.type == null || appData.type == undefined || appData.type == "") {
		appData.type = $location.search().type;
	}
	if(appData.type == "wrgy") {
		$scope.funName = "无人干预";
		appData.address = "http://zwdthk.sh.gov.cn:8080/hkzwdt/hkzwdt/nobody.html";
	} else if(appData.type == "zzbl") {
		$scope.funName = "自助办理";
		appData.address = "http://zwdt.sh.gov.cn/govPortals/region/SH00HK";
	} else if(appData.type == "yjs") {
		$scope.funName = "一件事";
		appData.address = "http://yjsdy.shhk.gov.cn:8080/smart/#/";
	}else if(appData.type == "mytj") {
		$scope.funName = "免于提交";
		appData.address = "http://zwdthk.sh.gov.cn:8080/hkzwdt/hkzwdt/twoavoidsubmit.html";
	} else if(appData.type == "zwgk") {
		$scope.funName = "政务公开";
		appData.address = "http://xxgk.shhk.gov.cn/hkxxgk/";
	}
	console.log(appData.address);
 	window.external.URL_OPEN(200,180,1500,700, appData.address);
});
