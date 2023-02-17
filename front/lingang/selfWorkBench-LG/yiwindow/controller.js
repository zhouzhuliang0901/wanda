app.controller("main", function($scope, $state, $timeout, appData, $sce, $location) {
	if(appData.type == null || appData.type == undefined || appData.type == "") {
		appData.type = $location.search().type;
	}
	if(appData.type == "zzlb") {
		$scope.funName = "证照联办";
		appData.address = "https://zzlb.lgxc.gov.cn/singleLogin";
	} else if(appData.type == "ycdzsdy") {
		$scope.funName = "原产地证书打印";
		appData.address = "https://swapp.singlewindow.cn/deskserver/sw/deskIndex?menu_id=aps";
	} else if(appData.type == "yspjy") {
		$scope.funName = "艺术品经营备案（辅助）";
		appData.address = "https://p.lgxc.gov.cn/fastconv/unmanned";
	} else if(appData.type == "ptdxs") {
		$scope.funName = '普通地下室备案（辅助）';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/unmanned';
	} else if(appData.type == "scjs") {
		$scope.funName = '生产建设项目水土保持方案审批';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/unmanned';
	} else if(appData.type == "jzgdyjsg") {
		$scope.funName = "建筑工地夜间施工作业审批";
		appData.address = "https://p.lgxc.gov.cn/fastconv/easytodo/nightBuild";
	} else if(appData.type == "czwsps") {
		$scope.funName = '城镇污水排入排水管网许可';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/easytodo/ctSewage';
	} else if(appData.type == "hdjs") {
		$scope.funName = '河道内建设项目施工方案的审核';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/easytodo/riverContractor';
	} else if(appData.type == "hdjsyx") {
		$scope.funName = "河道内建设项目施工方案审核(延续)";
		appData.address = "https://p.lgxc.gov.cn/fastconv/fasttodo/riverBuildCon";
	} else if(appData.type == "hdsmqyyx") {
		$scope.funName = '河道内树木迁移的审批(延续)';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/fasttodo/riverTreeCon';
	} else if(appData.type == "czwspsyx") {
		$scope.funName = '城镇污水排入排水管网许可(延续)';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/fasttodo/ctSewageCon';
	} else if(appData.type == "hb") {
		$scope.funName = '好办';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/easytodo';
	} else if(appData.type == "kb") {
		$scope.funName = '快办';
		appData.address = 'https://p.lgxc.gov.cn/fastconv/fasttodo';
	}
	$scope.prevStep = function() {
		window.history.go(-1);
	}
	console.log(appData.type);
	console.log($scope.funName + "-------" + appData.address);
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_EDGE_OPEN(200,180,1500,700,appData.address);
	//	window.external.URL_OPEN(200,180,1500,700,appData.address);
});