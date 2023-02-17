app.controller('loginType', function($state, $scope, appData, $location) {
	$scope.operation = "请选择登录方式";
	$scope.funName = "不动产登记信息自助查询、打印";
	appData.funName = $scope.funName;
	appData.itemCode = "";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		window.location.href = "../FGJ_allItem/index.html";
	}
});
app.controller('login', function($scope, $http, $state, appData) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.nextLink = ""; // 下一步标识符
	$scope.loginType = appData.loginType;
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("input");
		}
	}
//	//test
//	$scope.idcardLogin = function() {
//		appData.licenseNumber = "310109197112014058";
//		appData.licenseName = "张三";
//		$state.go("input");
//	}
//	$scope.idcardLogin();
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			$scope.loginType = 'recognition';
		} else {
			layer.msg("很抱歉，未获取到相关信息，请重试。");
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("input");
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}
});

app.controller('input', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.query = function(licenseCode) {
//		licenseCode = "200425507282";
		let condFlag = false;
		do {
			if(isBlank(licenseCode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入产证编码！";
				return;
			}
		} while (condFlag);
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/immovables/queryImmovables.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				certCode: licenseCode,
				machineId: $.config.get('uniqueId')||"",
			},success:function(dataJsonp){
				$scope.isLoading = false;
				console.log(dataJsonp.data);
				if(dataJsonp.errorCode == "0" && dataJsonp.data.length>0){
					appData.queryList = dataJsonp.data[0];
					$state.go("info");
				}else{
					$scope.isAlert = true;
					$scope.msg = "未查询到信息"
				}
			},error:function(err){
				console.log(err);
			}
		});
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});

app.controller('info', function($state, $scope, appData, $location, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.queryList = appData.queryList;
	$scope.detailList = {
		'cqzh':$scope.queryList.sourceData.产权证号,
		'qmc':$scope.queryList.sourceData.区名称,
		'qbh':$scope.queryList.sourceData.区编号,
		'zl':$scope.queryList.sourceData.坐落,
		'shbw':$scope.queryList.sourceData.室号部位,
		'fwlx':$scope.queryList.sourceData.房屋类型,
		'fwbh':$scope.queryList.sourceData.房屋编号,
		'sjbh':$scope.queryList.sourceData.收件编号,
		'fwyt':$scope.queryList.sourceData.房屋用途||"",
	};
	console.log($scope.detailList);
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.isLoading = false;
	$scope.nextText = "打印";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.checkImmovables = function(){
		$scope.isLoading = true;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/immovables/checkImmovables.do",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				districtId: $scope.detailList.qbh,
				houseId: $scope.detailList.fwbh,
				transactionId:$scope.detailList.sjbh,
				ownerCardNo:appData.licenseNumber,
			},success:function(dataJsonp){
				$scope.isLoading = false;
				if(dataJsonp.errorCode == "0" && dataJsonp.result == true){
					appData.pdfLicense = $.getConfigMsg.preUrlSelf + dataJsonp.derivePictureUrlForBytes
				}else{
					$scope.isAlert = true; 
					$scope.msg = "人房核验失败，请重试"
				}
			},error:function(err){
				console.log(err);
			}
		});
	}
	$scope.checkImmovables();
	$scope.nextStep = function(){
		$scope.isShowPrint = "show";
		console.log(appData.pdfLicense);
		$scope.path = "D:\\pdfPrint.pdf";
		$scope.filePath = "D:/pdfPrint.pdf";
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			appData.pdfLicense,
			$scope.path,
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				$.device.pdfPrint($scope.filePath);
				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 1);
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: appData.funName,
						Number: "",
					}
				}
				recordUsingHistory('房管局服务', '查询+打印', appData.funName, appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
				//行为分析(查询)
				trackEventForQuery(appData.funName, '', "打印", "上海市房屋管理局", appData.licenseName, appData.licenseNumber, "");
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);
	}
	$scope.prevStep = function() {
		$state.go("input");
	}
});