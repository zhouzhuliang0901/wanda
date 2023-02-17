function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto');
}
app.controller('guideline', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "非上海生源高校毕业生落户查询";
	appData.funName = $scope.funName;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.guideline = "<p>1、本查询仅针对申请落户本市的非本市生源应届普通高校毕业生;</p>" +
		"<p>2、申请人在拿到《申报户口证明信》10个工作日之后可进行查询;</p>";
	addAnimate($('.scrollBox2'))
	$scope.prevStep = function() {
		window.location.href = "../publicSecurity/index.html";
	}
	$scope.nextStep = function() {
		$state.go("loginType");
	}
});
app.controller('loginType', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "信用报告";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go('login');
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$.state.go("main");
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
		$state.go("info");
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			$state.go("info");
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			$state.go("info");
		}
	}
})

app.controller('info', function($state, $scope, appData, $http) {
	removeAnimate($('.scrollBox2'))
	$scope.operation = "请选择登录方式";
	$scope.funName = "信用报告";
	$scope.nextText = "返回首页";
	$scope.isLoading = true;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.stName = appData.licenseName;
	$scope.sex = ((parseInt(appData.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	$scope.stIdCard = appData.licenseNumber;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("loginType");
	}
	addAnimate($('.scrollBox2'))
	$scope.querySettleInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/settleDown/querySettleInfo.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				idCrd: appData.licenseNumber,
				userName: encodeURI(appData.licenseName)
			},
			success: function(dataJson) {
				$scope.isLoading = false;
				if(dataJson.type == "yes") {
					$scope.result = dataJson.result01;
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.result01;
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "暂查不到您的信息"
				console.log(err);
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
				Number: "",
			}
		}
		recordUsingHistory('公安服务', '查询', appData.funName, appData.licenseName, appData.licenseNumber, "", "", JSON.stringify($scope.jsonStr));
		//行为分析(查询)
		trackEventForQuery(appData.funName, "", "查询", "上海市公安局", appData.licenseName, appData.licenseNumber, "");
	}
	$scope.querySettleInfo();
	$scope.prevStep = function() {
		$state.go("main");
	}
});