function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}
app.controller('loginType', function($state, $scope, appData, $http, $location) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	trackEvent($(".headName").text());
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.operation = "请选择登录方式";
	$scope.person = true;
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$.device.GoHome();
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout, $rootScope) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('main');
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
			$scope.operation = "随申办登录";
			break;
	}
	//跳转页面
	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("info");
		}
	}
	$scope.caLoginStatus = "";
	$scope.prevStep = function() {
		$state.go("loginType");
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
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("info");
		$scope.$apply();
	}
	$scope.citizenLogin = function(info) {
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.encrypt_identity = info.encrypt_identity;
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
app.controller("info", function($scope, $state, appData, $sce, appFactory, $timeout) {
	$scope.caption = "查询结果"
	$scope.funName = appData.funName;
	//icon base64
	$scope.car = car;
	$scope.license = license;
	$scope.isLoading = true;
	$scope.concel = "false";
	$scope.isAlert = false;
	$scope.showDetail = "";
	$scope.vehicleIllegalInfoList = [];
	$scope.vehicleInfoShow = false;
	$scope.vehicleIllegalInfoShow = false;
	$scope.nextText = "打印";
	let info = []; // 记录医保金数据
	$scope.searchType = ["机动车", "驾驶证"];
	$scope.licenseName = appData.licenseName;
	$scope.licenseNumber = appData.licenseNumber;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	let date = new Date();
	let year = date.getFullYear();
	$scope.current = appData.type;
	console.log($scope.current);
	$scope.change = function(index) {
		$scope.current = index;
	}

	//判断是否为空 为空用"-"展示
	function changeTrim(str) {
		str = str.trim();
		if(str == undefined || str == "") {
			return '—';
		} else {
			return str;
		}
	}
	//2、机动车违法信息查询
	$scope.myVehVio = function(hphm, hpzl, fdjh6) {
		appFactory.pro_fetch(appData.tokenSNO, function(res) {
			if(res.SUCCESS === true) {
				let token = res.accessToken;
				$.ajax({
					type: "get",
					url: $.getConfigMsg.preUrlSelf + "/selfapi/vehicleManagement/myVehVio.do",
					dataType: "json",
					jsonp: "jsonpCallback",
					data: {
						accessToken: token,
						hphm: encodeURI(hphm),
						hpzl: hpzl,
						fdjh6: fdjh6,
						sjlx: 2
					},
					success: function(dataJson) {
						if(dataJson.success == true && dataJson.data.code == "0") {
							$scope.vehicleIllegalInfo = dataJson.data.vios;
							$scope.vehicleIllegalInfoList.push($scope.vehicleIllegalInfo);
							console.log($scope.vehicleIllegalInfoList);
							if($scope.vehicleIllegalInfo.length > 0) {
								$scope.vehicleIllegalInfoShow = true;
							}
						} else {
							$scope.isAlert = true;
							$scope.msg = dataJson.msg;
							$scope.alertConfirm = function() {
								$state.go("loginType");
							}
						}
					},
					error: function(err) {
						console.log(err);
					}
				});
			} else {
				$scope.isAlert = true;
				$scope.msg = "数据加载异常,请重试";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		})
	}

	//3、驾驶证违法信息查询
	$scope.myDrvVio = function(jszh, hpzl) {
		appFactory.pro_fetch(appData.tokenSNO, function(res) {
			if(res.SUCCESS === true) {
				let token = res.accessToken;
				$.ajax({
					type: "get",
					url: $.getConfigMsg.preUrlSelf + "/selfapi/vehicleManagement/myDrvVio.do",
					dataType: "json",
					jsonp: "jsonpCallback",
					data: {
						accessToken: token,
						jszh: encodeURI(jszh),
						hpzl: hpzl,
						sjlx: 1
					},
					success: function(dataJson) {
						console.log(dataJson);
						if(dataJson.success == true && dataJson.data.code == "0") {
							$scope.driverIllegalInfoShow = true;
							$scope.driverIllegalInfo = dataJson.data.vios;
							$scope.score = dataJson.data.zjf;
						} else {
							$scope.isAlert = true;
							$scope.msg = dataJson.msg;
							$scope.alertConfirm = function() {
								$state.go("loginType");
							}
						}
					},
					error: function(err) {
						console.log(err);
					}
				});
			} else {
				$scope.isAlert = true;
				$scope.msg = "数据加载异常,请重试";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		})
		 //模块使用记录
        $scope.jsonStr = {
            SUCCESS: "true",
            data: {
                name: '驾驶证违法信息查询',
            }
        }
        recordUsingHistory('公安服务', '查询', '驾驶证违法信息查询', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
        trackEventForQuery('驾驶证违法信息查询','','查询','上海市公安局',appData.licenseName,appData.licenseNumber,'');
	}

	//1、本人机动车查询接口
	$scope.getVehicle = function() {
		appFactory.pro_fetch(appData.tokenSNO, function(res) {
			if(res.SUCCESS === true) {
				let token = res.accessToken;
				$.ajax({
					type: "get",
					url: $.getConfigMsg.preUrlSelf + "/selfapi/vehicleManagement/getVehicle.do",
					dataType: "json",
					jsonp: "jsonpCallback",
					data: {
						accessToken: token
					},
					success: function(dataJson) {
						console.log(dataJson);
						if(dataJson.success == true && dataJson.data.code == "0") {
							$scope.isLoading = false;
							$scope.vehicleInfo = dataJson.data.vehlist;
							$scope.stMobile = dataJson.data.mobile;
							if($scope.vehicleInfo.length > 0) {
								$scope.myDrvVio($scope.vehicleInfo[0].hphm, $scope.vehicleInfo[0].hpzl);
								for(let i = 0; i < $scope.vehicleInfo.length; i++) {
									$scope.myVehVio($scope.vehicleInfo[i].hphm, $scope.vehicleInfo[i].hpzl, $scope.vehicleInfo[i].fdjh6);
								}
								$scope.vehicleInfoShow = true;
							}
						} else {
							$scope.isAlert = true;
							$scope.msg = dataJson.msg;
							$scope.alertConfirm = function() {
								$state.go("loginType");
							}
						}
					},
					error: function(err) {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "未查询到您的信息,请重试";
						$scope.alertConfirm = function() {
							$state.go("loginType");
						}
					}
				});
			} else {
				$scope.isAlert = true;
				$scope.msg = "数据接口异常,请重试";
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		})
		 //模块使用记录
        $scope.jsonStr = {
            SUCCESS: "true",
            data: {
                name: '机动车违法信息查询',
            }
        }
        recordUsingHistory('公安服务', '查询', '机动车违法信息查询', appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
        trackEventForQuery('机动车违法信息查询','','查询','上海市公安局',appData.licenseName,appData.licenseNumber,'');
	}
	$scope.getVehicle();

	//驾驶证信息
	$scope.driverLicense = function() {
		let nConfig = {
			holderCode: appData.encrypt_identity || $scope.licenseNumber, //data.idCardNum,
			catMainCode: "310100208000100",
			machineId: $.config.get('uniqueId') || "HPZX001",
			itemName: encodeURI("机动车违法信息查询"),
			itemCode: "",
			businessCode: "",
			name: encodeURI($scope.licenseName),
			startDay: appData.VALIDSTARTDAY,
			endDay: appData.VALIDENDDAY,
			type:"0"
		}
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/DZCert/getCertOriginalData.do",
			/*url写异域的请求地址*/
			dataType: "json",
			/*加上datatype*/
			contentType: 'application/x-www-form-urlencoded',
			//jsonp:'jsonpCallback',
			beforeSend: function(xhr) {
	            xhr.setRequestHeader("account",'admin');
	            xhr.setRequestHeader("password",(hex_md5(window.btoa('Wonders300168'))));
	        },
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: nConfig,
			success: function(dataJsonp) {
//				if(dataJsonp.success == true) {
					$scope.driverLicenseInfo = dataJsonp.data;
					console.log($scope.driverLicenseInfo);
					//清分周期
					$scope.qfrq = year + "" + dataJsonp.data.MAKEDAY.split(" ")[0].substring(4, 10);
					console.log($scope.qfrq);
//				}
			},
			error: function(err) {
				console.info("getCertOriginalData error");
			},
		});

		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/vehicleManagement/driverLicenseForImg.do",
			/*url写异域的请求地址*/
			dataType: "json",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				certNo: appData.encrypt_identity || $scope.licenseNumber, //"340881199303145313"
				catMainCode: "310100208000100",
				machineId: $.config.get('uniqueId') || "",
				use: encodeURI('证照查看'),
				name: encodeURI($scope.licenseName),
				startDay: appData.VALIDSTARTDAY,
				endDay: appData.VALIDENDDAY,
			},
			success: function(dataJsonp) {
				if(dataJsonp.success == true) {
					$scope.licenseList = dataJsonp.data;
					let oneImgBase64 = $scope.licenseList[0].png;
					let twoImgBase64 = $scope.licenseList[2].png;
					$scope.oneImg = "data:image/png;base64," + oneImgBase64.replace(/[\r\n]/g, "");
					$scope.twoImg = "data:image/png;base64," + twoImgBase64.replace(/[\r\n]/g, "")
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJsonp.msg;
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
					}
				}
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "未查询到您的信息,请重试";
				$scope.alertConfirm = function() {
					$scope.isAlert = false;
				}
			},
		});
	}
	$scope.driverLicense();

	let i = 0;
	$scope.getDetail = function(index, info) {
		$scope.vehicleIllegalInfo = $scope.vehicleIllegalInfoList[index];
		if(i % 2 == 0) {
			$scope.showDetail = index;
		} else {
			$scope.showDetail = "";
		}
		i++;
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('loginType');
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.print = function() {
		$state.go("print");
	}
});