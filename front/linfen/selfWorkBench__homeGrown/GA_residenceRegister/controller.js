                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 // ?????????main?????????
app.controller("main", function($scope, $state, appData, $sce) {
	$scope.operation = "???????????????";
	$scope.stuffName = perjsonStr;

	appData.funName = newPerJsonStr.residence_register.stuffName;
	appData.type = newPerJsonStr.residence_register.type;
	appData.ywlx = newPerJsonStr.residence_register.ywlx;

	$scope.choiceType = function(type, name, ywlx) {
		appData.funName = name;
		appData.type = type;
		appData.ywlx = ywlx;
		if($.device.vendor() == "wonders") {
			$state.go("loginType");
		} else {
			$state.go("loginResidence");
		}
	}
	$scope.choiceType('residenceVisa', '???????????????', '00');
});
app.controller('loginType', function($state, $scope, appData, $location) {
	$scope.funName = appData.funName;
	$scope.operation = "?????????????????????";
	if(appData.type == "" || appData.type == undefined) {
		appData.type = $location.search().type;
	}
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		appData.SwipeType = "sbCard";
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.msg = "";
	$scope.loginType = appData.loginType;
//	$scope.idcardLogin = function() {
//		appData.licenseNumber = "350722199011260084";
//		appData.licenseName = "?????????";
//		$state.go("updateResidence");
//	}
//	$scope.idcardLogin();
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			$scope.loginType = "recognition";
		} else {
			layer.msg("???????????????????????????????????????????????????");
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("updateResidence");
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.licenseName = idcardInfo.realname;
		appData.licenseNumber = idcardInfo.idcard;
		$state.go("updateResidence");
	}
});
//??????????????? ??????
//?????????loginResidence?????????
app.controller('loginResidence', function($state, $scope, appData, $http, $interval, $timeout) {
	$scope.ResidenceImg = "../libs/common/images/dicon/" + acBridgeMac.imagePath() + "/residence-into.png";
	//$scope.ResidenceImg="../libs/common/images/dicon/zhuofansoft/residence-into.png";
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.operation = "???????????????";
	$scope.time = null;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		//???????????????
		//		window.external.DataCard_Close();
		$.device.dataCardCardOut();
		$.device.GoHome();
	}
	$scope.readResidence = function() {
		//???????????????
		try {
			$.device.dataCardCardIn();
			setTimeout(function() {
				//???????????????
				$.device.dataCardRead(function(data) {
					appData.cardInfo = data;
					$.log.debug(appData.cardInfo);
					if(appData.cardInfo == undefined) {
						$scope.isAlert = true;
						$scope.msg = "?????????????????????";
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
							setTimeout(function() {
								$scope.readResidence();
							}, 3000)
						}
					}
					appData.cardInfo = JSON.parse(appData.cardInfo);
					if(appData.cardInfo != "" || appData.cardInfo.Ssn != "") {
						//clearInterval($scope.time);
						$state.go("infoResidence");
					} else {
						$scope.isAlert = true;
						$scope.msg = "???????????????????????????????????????";
					}
				});
			}, 3000);
		} catch(e) {
			$scope.readResidence();
		}
	}
	$scope.open = function() {
		try {
			window.external.Hd_Audio_PPlay("Realtek High Definition Audio", window.external.GetCurrentPath() + "\\resources\\audio\\residenceVisa.wav");
		} catch(e) {}
		setTimeout(function() {
			$scope.readResidence();
		}, 5000)
	}
	$scope.open();
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller("infoResidence", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "??????";
	$scope.isLoding = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.stName = appData.cardInfo.PeopleName;
	$scope.stIdCard = appData.cardInfo.Ssn;
	$scope.sex = ((parseInt($scope.stIdCard.substring(16, 17)) % 2) == 0) ? "???" : "???";
	$scope.updateResidenceLicenseInfo = function() {
		$scope.cardId = appData.cardInfo.IccId;
		$scope.regCode = appData.cardInfo.RegCode;
		$scope.EndDate = appData.cardInfo.EndDate;
		$scope.StartDate = appData.cardInfo.StartDate;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/residenceLicense/updateResidenceLicenseInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				cardId: $scope.cardId,
				regcode: $scope.regCode,
				deviceId: "10.81.16.56",
				userId: "zz_b4d9523bddf43934"
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.SUCCESS == true) {
					$scope.liveAddr = dataJson.respData.liveAddr;
					appData.liveAddr = $scope.liveAddr;
					$scope.validdate = dataJson.respData.validdate;
					appData.validdate = $scope.validdate;
					appData.StartDate = dataJson.respData.validdateStart;
					appData.EndDate = dataJson.respData.validdateEnd;
					if($scope.EndDate != "" && $scope.EndDate != undefined && appData.EndDate != "" && appData.EndDate != undefined) {
						if($scope.EndDate === appData.EndDate) {
							$scope.isLoding = true;
							$scope.isAlert = true;
							$scope.msg = "???????????????????????? ??????????????????<p> ?????????</p>";
							$scope.alertConfirm = function() {
								$scope.isAlert = false;
								//???????????????
								$.device.dataCardCardOut();
								$state.go("main");
							}
						} else {
							$scope.isLoding = true;
						}
					} else {
						$scope.isLoding = true;
						$scope.isAlert = true;
						$scope.msg = dataJson.resultMessage;
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
							//???????????????
							$.device.dataCardCardOut();
							$state.go("main");
						}
					}
				} else {
					$scope.isLoding = true;
					$scope.isAlert = true;
					if(dataJson.resultMessage.indexOf("????????????") != -1) {
						$scope.msg = "??????????????????,?????????????????????!!!";
					} else {
						$scope.msg = "??????????????????,??????????????????????????????";
					}
					$scope.alertConfirm = function() {
						$scope.isAlert = false;
						//???????????????
						$.device.dataCardCardOut();
						$state.go("main");
					}
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.updateResidenceLicenseInfo();

	// ????????????
	$scope.prevStep = function() {
		$state.go("loginResidence");
	}
	$scope.nextStep = function() {
		$state.go("updateResidence");
	};
	//??????
	$scope.dataClose = function() {
		//???????????????
		//		window.external.DataCard_Close();
		$.device.dataCardCardOut();
	}
});
app.controller("updateResidence", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.confirmshow = "false";
	$scope.nextText = "????????????";
	$scope.isLoading = true;
	if($.device.vendor() == "wonders") {
		$scope.stName = appData.licenseName;
		$scope.stIdCard = appData.licenseNumber;
		$scope.sex = ((parseInt($scope.stIdCard.substring(16, 17)) % 2) == 0) ? "???" : "???";
		$.device.dataCardQuery($scope.stIdCard, $scope.stName, function(res) {
			$scope.isLoading = false;
			$scope.liveAddr = res.liveAddr;
			$scope.validdate = res.facedate + '-' + res.validdate;
		});
	} else {
		$scope.stName = appData.cardInfo.PeopleName;
		$scope.stIdCard = appData.cardInfo.Ssn;
		$scope.sex = ((parseInt($scope.stIdCard.substring(16, 17)) % 2) == 0) ? "???" : "???";
		$scope.liveAddr = appData.liveAddr;
		$scope.validdate = appData.validdate;
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("infoResidence");
	}
	//??????
	$scope.dataClose = function() {
		//???????????????
		//		window.external.DataCard_Close();
		$.device.dataCardCardOut();
	}
	$scope.nextStep = function() {
		if($.device.vendor() == "wonders") {
			//?????????
			$scope.isAlert = true;
			$scope.msg = "????????????????????????????????????????????????10???...";
			$timeout(function() {
				try {
					$.device.dataCardErase($scope.stIdCard, $scope.stName, function(err) {
						$scope.isAlert = false;
						$timeout(function() {
							$scope.confirmshow = "true";
							$scope.isAlert = true;
							$scope.msg = err.Msg;
							$scope.alertConfirm = function(){
								$scope.isAlert = false;
							}
						})
						$.log.debug("????????????????????????" + err.Msg);
					});
				} catch(e) {}
				$scope.isAlert = false;
				$timeout(function() {
					$scope.isAlert = true;
					$scope.msg = "<p>????????????????????????20???,?????????...</p>";
				})
			}, 1000);
		} else {
			//??????
			var printTextArray = new Array();
			printTextArray.push({
				printText: '???????????????' + $scope.validdate,
				x: 87,
				y: 165,
				fontFamily: '??????',
				fontSize: 8
			});
			//??????
			var wanda = "<style>td{padding:0px;text-align: justify; -ms-text-justify: inter-ideograph; -ms-text-align-last: justify;}</style><table cellspacing='0' style='font-family:??????;font-size: 10px;font-weight: bold;'><tr><td>?????????</td><td>:</td><td rowspan='2'>" + $scope.liveAddr + "<td></tr><tr><td>??????</td><td>:</td></tr><tr><td>????????????</td><td>:</td><td>" + $scope.validdate + "<td></tr></table>";
			trackEvent("???????????????");
			$scope.isAlert = true;
			$scope.msg = "<p>?????????????????????????????????</p><p>???????????????20??????</p>";
			$timeout(function() {
				//window.external.DataCard_Print(135, 85, 300, 400, "<style>td{padding:0px;text-align: justify; -ms-text-justify: inter-ideograph; -ms-text-align-last: justify;}</style><table cellspacing='0' style='font-family:??????;font-size: 10px;font-weight: bold;'><tr><td>?????????</td><td>:</td><td rowspan='2'>" + $scope.liveAddr + "<td></tr><tr><td>??????</td><td>:</td></tr><tr><td>????????????</td><td>:</td><td>" + $scope.validdate + "<td></tr></table>");
				$.device.dataCardPrint(135, 85, 300, 400, wanda, printTextArray, $scope.validdate);

			}, 100);
		}
		$timeout(function() {
			$.device.GoHome();
		}, 15000);
	}
});