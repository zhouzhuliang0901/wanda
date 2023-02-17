app.controller('loginType', function($state, $scope, appData, $location, appFactory) {
	$scope.operation = "请选择登录方式";
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	appData.sign = "token";
	$scope.alertConfirm = function() {
		$state.go("loginType");
	}
	switch ($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "cloud":
			$scope.operation = "随申办登录";
			break;
	}
	//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "json",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if (res.SUCCESS === true) {
					appData.token = res.accessToken;
					console.log("token1:" + appData.token)
					$state.go("choose");
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if (status === 'timeout' || status ===
					'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}
	//获取token ------1、两照对比获取tokenSNO
	$scope.getTokenSNO = function(face, photograph) {
		var idCardPhoto = face;
		var capturePhoto = photograph;
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getTokenSNO.do",
			type: "post",
			dataType: "json",
			//			jsonp: "jsonpCallback",
			data: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
				facePhoto: capturePhoto,
				copyIDPhoto: idCardPhoto
			},
			success: function(res) {
				console.log(res);
				if (res.SUCCESS === true && res.verify === 1) {
					$scope.getAccessToken(res.tokenSNO);
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log(err);
			},
			complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
				if (status === 'timeout' || status ===
					'error') { //超时,status还有success,error等值的情况
					rec.abort();
				}
			}
		})
	}
	// $scope.idcardLogin = function(info, images) {
	// 	appData.licenseNumber = '430426199804106174';
	// 	appData.licenseName = '邹天奇';
	// 	$state.go("choose");
	// }
	// $scope.idcardLogin();
	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			// $scope.faceImage = images;
			// appData.licenseNumber = info.Number;
			// appData.licenseName = info.Name;
			// appData.VALIDENDDAY = info.ValidtermOfEnd;
			// appData.VALIDSTARTDAY = info.ValidtermOfStart;
			// $scope.loginType = 'recognition';
			// appData.licenseNumber = '310228198808070818';
			// appData.licenseName = '陈雷';
			// $scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
		} else {
			layer.msg("没有获取到")
		}
	}
	// $scope.idcardLogin();
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("choose");
	}

	$scope.citizenLogin = function(info) {
		let idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$scope.getTokenSNO(recognition_base64_photo, recognition_base64_photo);
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
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			$scope.getAccessToken(appData.tokenSNO);
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			$scope.getTokenSNO(photo, photo);
			try {
				$scope.$apply();
			} catch (e) {}
		}
	}
});
app.controller("choose", function($scope, $state, appData, $timeout, $sce) {
	$scope.funName = appData.funName = "白蚁防治单位自主查询";
	appData.area = "";
	$scope.areaList = areaList;
	$scope.isLoading = false;
	// $scope.queryList = spaceInfoList.data;
	$scope.change = function(name, index, areaCode) {
		$scope.isLoading = true;
		console.log("areaCode" + name)
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/housingManagement/termiteControlQuery.do",
			// url:"http://180.169.7.194:8081/ac-self/selfapi/housingManagement/termiteControlQuery.do",
			dataType: "json",
			data: {
				areaName: encodeURI(name)
			},
			success: function(dataJson) {
				if (dataJson.code == "200") {
					console.log("内容" + JSON.stringify(dataJson))
					$timeout(function() {
						$scope.queryList = dataJson.data.content;
					}, 100);
					console.log(dataJson);
					$scope.isLoading = false;
				} else {
					$scope.isAlert = true;
					$scope.msg = "数据加载异常,请重试";
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}

			},
			error: function(err) {
				console.log("getItemApplyPlace err");
			}
		});
	};
	$scope.spaceInfo = function(RN) {

		console.log("当前序号" + RN)
		appData.spaceInfoList = filterBySpaceName($scope.queryList, RN)
		// appData.spaceInfoList = spaceInfoList.data.content
		console.log("当前公司" + JSON.stringify(appData.spaceInfoList))
		$timeout(function() {
			$state.go("info")
		}, 1000)


	};
	$scope.prevStep = function() {

	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});

app.controller("info", function($scope, $state, appData, $sce, $timeout) {
	$scope.isLoading = false;
	$scope.concel = 'false';
	$scope.isDetial = true;
	$scope.spaceInfoList = appData.spaceInfoList[0];
	// $scope.change = function(FILE_PATH){
	// 	$scope.isAlert = true;
	// 	$scope.msg = "<img ng-src=""></img>"
	// };
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go('choose');
	}
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
		}
	}
	recordUsingHistory('房管局服务', '查询', appData.funName, "", "", '', '', JSON.stringify($scope.jsonStr));
	trackEventForQuery(appData.funName, '', '查询', '上海市住房和城乡建设委员会', '', '', '');
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: false,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
