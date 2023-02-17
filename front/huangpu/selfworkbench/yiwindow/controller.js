app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办理业务";
	$scope.stuffName = perjsonStr;
	$scope.choiceType = function(url, name) {
		trackEvent($.config.get('uniqueId'), name);
		trackEventSelf(name, name);
		appData.type = name;
		appData.url = url;
		$state.go("iframe");
	}
});
app.controller("main2", function($scope, $state, appData, $sce) {
	$scope.operation = "请选择办理业务";
	$scope.stuffName = perjsonStr2;
	$scope.choiceType = function(url, name) {
		trackEvent($.config.get('uniqueId'), name);
		trackEventSelf(name, name);
		appData.type = name;
		appData.url = url;
		appData.size = true;
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
	if(appData.type == "wyc") {
		$scope.funName = "网约车";
		appData.address = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jump.do?redirect_uri=http://180.169.19.159:8080/zwdt/hpaizwdt/redirectmanage.jsp?infoguid=1";
	} else if(appData.type == "ycdzsdy") {
		$scope.funName = "原产地证书打印";
		appData.address = "https://swapp.singlewindow.cn/deskserver/sw/deskIndex?menu_id=aps";
	} else if(appData.type == "jlbg") {
		$scope.funName = "酒类许可证（变更）";
		appData.address = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jump.do?redirect_uri=http://180.169.19.159:8080/zwdt/hpaizwdt/redirectmanage.jsp?infoguid=3";
	} else if(appData.type == "jlzx") {
		$scope.funName = "酒类许可证（注销）";
		appData.address = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jump.do?redirect_uri=http://180.169.19.159:8080/zwdt/hpaizwdt/redirectmanage.jsp?infoguid=2";
	} else if(appData.type == "mfjd") {
		$scope.funName = "免费寄递";
		appData.address = "https://mp.weixin.qq.com/s?__biz=Mzg4OTI4ODQ2Ng==&mid=100001589&idx=1&sn=9540632b68b06f97f89156687613e1df&chksm=4fef65487898ec5e4d04da8d9bbbe25ec648c67b367c2b5ced9e9548f21ddfa2a40f03bfb78d#rd";
	} else if(appData.type == "kkfd") {
		$scope.funName = "开咖啡店";
		appData.address = "https://zwdt.sh.gov.cn/qykj/guide_hp/yslb";
	}else if(appData.type=="zxjj"){
		$scope.funName = "中心简介";
		appData.address = "https://mp.weixin.qq.com/s?__biz=Mzg4NzI5MTM3Mg==&mid=100000559&idx=1&sn=410e2fe4711b14b5ab2aa44764eb0f2b&chksm=4f8defac78fa66baa2ce65f3f03414abd2b3c2d5f33ea8adb0e94a28e863a921e99de299cc22#rd";
	}else if(appData.type == "wlfw"){
		$scope.funName = "为老服务";
		appData.address = "https://mp.weixin.qq.com/s/ehGB6td-xmvvuu9b0C-4KQ";
	}else {
		$scope.funName = appData.type;
		appData.address = appData.url;
	}
	$scope.prevStep = function() {
		window.history.go(-1);
	}
	console.log(appData.type);
	console.log($scope.funName + "-------" + appData.address);
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(200,180,1500,700,appData.address);
});