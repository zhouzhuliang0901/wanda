//定义地址 上海09，江苏10，浙江19，安徽12
var nhSProvinceYCity={
	'js':'http://nisp.tpaas.abc/stm/nh/#/',
	'zj':'http://127.0.0.1:8085/OpenPage1',
	'ah':'http://nisp.tpaas.abc/slxt-web/index.html#/home',
	'sh':'../index.html#/index-yh-yb',
}
var zhSProvinceYCity={
	'js':'http://214.177.41.152:8890/stm/zh/',
	'zj':'https://portal.zjzwfw.gov.cn/bizAio/aio/v2#/aio?envAioArea=561',
	'ah':'http://214.177.41.152:8092/slxt-web/index.html#/home',
	'sh':'',
}
app.controller('entryPageController', function($scope, $state, appData, $sce) {
	$scope.goToBD = function() {
		var yhNameProv;
		try{
			if(nhdeviceSum.Phaseiidevice()){
				yhNameProv=nhdeviceSum.getConfigDataInfo.prov;
				nhSProvinceYCity['zj']='https://portal.zjzwfw.gov.cn/bizAio/aio/v2#/aio?envAioArea=494';
			}else{
				yhNameProv=window.external.GetConfig('prov');
			}
			//上海09，江苏10，浙江19，安徽12
		}catch(e){}
		if(yhNameProv=='10'){
			//alert('江苏');
			window.location.href=nhSProvinceYCity['js'];
		}else if(yhNameProv=='19'){
			//alert('浙江');
			window.location.href=nhSProvinceYCity['zj'];
		}else if(yhNameProv=='12'){
			//alert('安徽');
			window.location.href=nhSProvinceYCity['ah'];
		}else if(yhNameProv=='09'){
			//alert('上海');
			window.location.href = '../index.html#/index-nh-cm';
		}else{
			//alert('上海');
			window.location.href = '../index.html#/index-nh-cm';
		}
	};
	$scope.goToCSJTB = function() {
		var yhNameProv;
		try{
			if(nhdeviceSum.Phaseiidevice()){
				yhNameProv=nhdeviceSum.getConfigDataInfo.prov;
			}else{
				yhNameProv=window.external.GetConfig('prov');
			}
			//上海09，江苏10，浙江19，安徽12
		}catch(e){}
		if(yhNameProv=='09'){
			//alert('上海');
			$state.go('TBGPItems');
		}else{
			window.location.href = '../index.html#/csjindex';
		}
	};
	$scope.isShowYHhome = true;
	//农行返回首页
	$scope.proMinimizes = function() {
		//WebSocketDebugHide();
		if(nhdeviceSum.Phaseiidevice()){
			nhdeviceSum.sleftclose();
		}else{
			WebSocketDebugHide();
		}
	}
});
app.controller('SProvinceYCityPageController', function($scope, $state, appData, $sce) {
	$scope.isShowYHhome = false;
	if(acBridgeMac.vendor() == "nhdevice") $scope.isShowYHhome = true;
	//农行返回首页
	$scope.proMinimizes = function() {
		try{
			//window.external.GoHome();
			$.device.GoHome();
		}catch(e){}
	}
	$scope.goToAppSSYS = function(data) {
		var urldata='';
		try{
			if(nhdeviceSum.Phaseiidevice()){
				nhSProvinceYCity['zj']='https://portal.zjzwfw.gov.cn/bizAio/aio/v2#/aio?envAioArea=494';
			}
		}catch(e){}
		if(acBridgeMac.vendor() == "zhdevice"){
			urldata=zhSProvinceYCity;
		}else{
			urldata=nhSProvinceYCity;
		}
		if(urldata[data]==''){
			alert("地址空白");
		}else{
			console.log(urldata[data]);
			window.location.href = urldata[data];
		}
		
	}
});
app.controller('TBHighFrequencyItemsController', function($scope, $state, appData, $sce) {
	try {
		localStorage.clear();
	} catch(e) {}
	try {
		localStorage.setItem("yhPublicItemdevice", 'indexMainPageYH/index.html');
	} catch(e) {}
	$scope.isShowYhWSRK = false;
	$scope.isShowYHhome = false;
	if((acBridgeMac.vendor() == "nhdevice")||(acBridgeMac.vendor() == "zhdevice")) $scope.isShowYhWSRK = true;
	if(acBridgeMac.vendor() == "nhdevice") $scope.isShowYHhome = true;

	$scope.toMorenhWSRK = function() {
		$state.go('SSYSPage');
	}
	$scope.toMore = function(data) {
		if(data == 'csjindex') {
			window.location.href = '../index.html#/csjindex';
		} else if(data == 'indexYH') {
			if(acBridgeMac.vendor() == "nhdevice") {
				window.location.href = '../index.html#/index-nh';
			} else if(acBridgeMac.vendor() == "zhdevice") {
				window.location.href = '../index.html#/index-zh';
			} else if(acBridgeMac.vendor() == "jhdevice") {
				window.location.href = '../index.html#/index-yh-yb';
			} else {
				window.location.href = '../index.html#/index-yh-yb';
			}
		}
	}
	$scope.goToApp = function(address) {
		if(address == '') {
			alert('未提供地址！！！')
		}else{
			window.location.href = address;
		}
	};
	$scope.goHomeYh = function() {
		if(acBridgeMac.vendor() == "nhdevice") {
			//window.external.GoHome();
			$.device.GoHome();
		} else if(acBridgeMac.vendor() == "zhdevice") {
			//alert('zhdevice未提供返回首页方法')
		} else if(acBridgeMac.vendor() == "jhdevice") {
			//alert('jhdevice未提供返回首页方法')
		} else {
			//alert('未提供返回首页方法')
		}
	}

});