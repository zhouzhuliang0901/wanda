app.directive("apptbFooter", function() {
	return {
		restrict: "E",
		templateUrl: "../libs/views/footer.html",
		scope: {
			goBack: "&",
			home: "&",
			customHome: "@",
			prev: "&",
			next: "&",
			defaultIsShowPrevBtn: "@",
			defaultIsShowNextBtn: "@",
			prevBtnInfo: "@",
			nextBtnInfo: "@"
		},
		controller: function($scope, $location, appData, $rootScope, $interval, $timeout) {
			var time = $.config.get('idleTime');
			//$scope.customHome为true，在功能子页面里。 并传入$scope.home 则执行$scope.home
			$scope.isFunction = $location.$$path;
			$scope.isCustom = $scope.customHome || undefined;
			$scope.prevBtnInfo = $scope.prevBtnInfo || "上一步";
			$scope.nextBtnInfo = $scope.nextBtnInfo || "下一步";
			// 是否显示上一步下一步按钮
			$scope.isShowPrevBtn = $scope.defaultIsShowPrevBtn || "1";
			$scope.isShowNextBtn = $scope.defaultIsShowNextBtn || "1";
			$scope.guanbi = false;
			$scope.xsbb = appData.xsbb;
			//线上帮办
			$scope.getHelp = function() {
				$scope.guanbi = true;
				switch (appData.funName) {
					case "劳动者基本信息查询及维护":
						$scope.itemCode = "312000384000";
						$scope.subitemCode = "";
						break;
					case "有无违法犯罪记录证明开具":
						$scope.itemCode = '312000218000';
						$scope.subitemCode = "11310000002420447T231200021800001";
						break;
					case "居住证签注":
						$scope.itemCode = "312000194000";
						$scope.subitemCode = "";
						break;
					case "失业登记(本市户籍)":
						$scope.itemCode = "312000617000";
						$scope.subitemCode = "";
						break;
					case "失业登记(非本市户籍)":
						$scope.itemCode = "312000617000";
						$scope.subitemCode = "";
						break;
					case "养老金卡（折）调整":
						$scope.itemCode = "312000497000";
						$scope.subitemCode = "";
						break;
					case "新版社保卡开通":
						$scope.itemCode = "312000176000";
						$scope.subitemCode = "11310000002420447T231200017600001";
						break;
					case "失业保险金申领":
						$scope.itemCode = "310500212001";
						$scope.subitemCode = "11310115002421239Q331050021200102";
						break;
					case "办理门急诊就医记录册":
						$scope.itemCode = "312000377000";
						$scope.subitemCode = "310000999000YB0000531200037700001";
						break;
					case "办理居民医保受理":
						$scope.itemCode = "312000361000";
						$scope.subitemCode = "310000999000YB0000531200036100001";
						break;
					case "本市户籍人员退出灵活就业登记":
						$scope.itemCode = "312000202000";
						$scope.subitemCode = "310000999000RS0000531200020200001";
						break;
					case "来沪人员灵活就业登记录入":
						$scope.itemCode = "312000202000";
						$scope.subitemCode = "310000999000RS0000531200020200001";
						break;
					case "本市户籍人员灵活就业登记":
						$scope.itemCode = "312000202000";
						$scope.subitemCode = "310000999000RS0000531200020200001";
						break;
					case "来沪人员灵活就业登记中止":
						$scope.itemCode = "312000202000";
						$scope.subitemCode = "310000999000RS0000531200020200001";
						break;
					case "户籍证明开具":
						$scope.itemCode = "312000088000";
						$scope.subitemCode = "11310000002420447T231200008800001";
						break;
					case "户籍人户分离人员居住登记受理":
						$scope.itemCode = "312000083000";
						$scope.subitemCode = "310000999000GA0000531200008300001";
						break;
					case "户籍人户分离人员居住登记注销":
						$scope.itemCode = "312000083000";
						$scope.subitemCode = "310000999000GA0000531200008300001";
						break;
				}
				if ($rootScope.tokenSNO) {
					//根据tokenSNO获取access_token
					$.ajax({
						url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
						type: "post",
						dataType: "json",
						//					jsonp: "jsonpCallback",
						timeout: 5000,
						data: {
							tokenSNO: $rootScope.tokenSNO,
						},
						success: function(res) {
							console.log(res);
							console.log(appData.funName);
							if (res.SUCCESS === true) {
								$scope.token = res.accessToken;
								$scope.callback =
									"http://10.81.16.73:3080/smzy/bangban_qa/tourist/guide/qa?matterCode=" +
									$scope.itemCode + "&subitemCode=" + $scope.subitemCode +
									"&from=xsbb&clientType=3"
								$scope.url =
									"http://10.81.16.73:3080/smzy/shspace/loginsso/in?access_token=" +
									$scope.token + "&callback=" + encodeURIComponent($scope
										.callback);
								$.log.debug($scope.url);
								console.log($scope.url);
								$.device.openUrlWindow($scope.url);

							}
						},
						error: function(err) {
							console.log(err);
						}
					})
				} else {
					$scope.xsbb = appData.xsbb;
					$scope.callback =
						"https://zwdt.sh.gov.cn/smzy/bangban_qa/tourist/guide/qa?matterCode=" + $scope
						.itemCode + "&subitemCode=" + $scope.subitemCode + "&from=xsbb&clientType=3"
					$scope.url = "https://zwdt.sh.gov.cn/smzy/shspace/loginsso/in?access_token=" +
						$scope.token + "&callback=" + encodeURIComponent($scope.callback);
					$.log.debug($scope.url);
					console.log($scope.url);
					$.device.openUrlWindow($scope.url);
				}
			}

			$scope.closeUrlWindow = function() {
				$scope.guanbi = false;
				$.device.closeUrlWindow();
			}

			$scope.nextStep = function(i, obj) {
				$scope.next();
			}

			$scope.prevStep = function() {
				if ($scope.prev) {
					$scope.prev();
					$rootScope.goAppHistoryBack()
				} else {
					$rootScope.goAppHistoryBack()
				}
			}

			$scope.$on("isCustom", function(val) {
				if (val) {
					$scope.isCustom = val;
				}
			});

			$scope.goHome = function() {
				$.device.Face_Close();
				$.device.Camera_Hide();
				$.device.Camera_UnLink();
				$.device.cmCaptureHide();
				$.device.idCardClose();
				$.device.qrCodeClose();
				$.device.officeClose();
				$.device.dataCardCardOut();
				try {
					$.device.closeUrlWindow();
					window.external.URL_CLOSE();
					window.external.Hd_Audio_Stop();
				} catch (e) {
					//TODO handle the exception
				}
				$.device.GoHome();
			};
			$scope.maxCountDown = time || 60;
			$scope.minTime = 10;
			$scope.timer = null;
			$scope.timeCount = function() {
				$interval.cancel($scope.timer);
				$scope.timer = $interval(function() {
					$scope.maxCountDown--;
					if ($scope.maxCountDown < 1) {
						$rootScope.SisAlert = false;
						$.device.Camera_Hide();
						$.device.cmCaptureHide();
						$.device.Face_Close();
						// 广播事件
						$rootScope.$broadcast('changeModel', 'false');
						$interval.cancel($scope.timer);
						$scope.isAlert = true;
						$scope.msg = "是否返回首页？";
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
							// window.external.URL_CLOSE();
							try {
								$.device.closeUrlWindow();
								window.external.URL_CLOSE();
								window.external.Hd_Audio_Stop();
							} catch (e) {}
							$.device.Face_Close();
							$.device.cmCaptureHide();
							$.device.Camera_Hide();
							$.device.Camera_UnLink();
							$.device.qrCodeClose();
							$.device.idCardClose();
							$.device.officeClose();
							$.device.dataCardCardOut();
							$.device.GoHome();
						}
						$scope.alertCancel = function() {
							$scope.isAlert = false;
							$scope.resetCountDown();
						}
						$scope.minCount = function() {
							$interval.cancel($scope.timer);
							$scope.timer = $interval(function() {
								$scope.minTime--;
								if ($scope.minTime < 1) {
									try {
										$.device.closeUrlWindow();
										window.external.URL_CLOSE();
										window.external.Hd_Audio_Stop();
									} catch (e) {}
									$.device.Face_Close();
									$.device.cmCaptureHide();
									$.device.Camera_Hide();
									$.device.Camera_UnLink();
									$.device.qrCodeClose();
									$.device.idCardClose();
									$.device.officeClose();
									$.device.dataCardCardOut();
									$.device.GoHome();
								}
							}, 1000);
						}
						$scope.minCount();
					}
				}, 1000);
			}

			// 如果是main页面,就取消倒计时功能
			//			if($location.path() == '/main') {
			//				return
			//			} else {
			//
			//			}
			$scope.timeCount();
			$scope.resetCountDown = function() {
				$interval.cancel($scope.timer);
				$timeout(function() {
					$scope.maxCountDown = time || 60;
				});
				$timeout(function() {
					$scope.timeCount();
				}, 5000);
			};
			window.addEventListener("click", $scope.resetCountDown);
			window.addEventListener("touchstart", $scope.resetCountDown);
			window.addEventListener("input", $scope.resetCountDown);
			$rootScope.$on("$viewContentLoaded", function() {
				if (!$scope.moduleName) {
					try {
						$scope.moduleName = $state.$current.data.title;
					} catch (e) {

					}
				}
			})
		}
	}
});
