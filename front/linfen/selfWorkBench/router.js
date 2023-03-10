app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, $interval, appFactory, $http) {
				appFactory.queryDevice("A4Printer", function(dataJson) {
					if(dataJson.isSuccess == true) {
						$rootScope.paperSum = dataJson.data.data.nmRemain;
					} else {
						$rootScope.paperSum = 0;
					}
				})
				appFactory.queryDevice("MedicalInsuranc", function(dataJson) {
					if(dataJson.isSuccess == true) {
						$rootScope.bookSum = dataJson.data.data.nmRemain;
					} else {
						$rootScope.bookSum = 0;
					}
				})
				appData = {};
				$timeout(function() {
					$.device.Face_Close();
				}, 300);
				$rootScope.isChild = false;
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				var url = $location.absUrl().split('selfWorkBench')[1];
				$scope.time = null;
				$scope.time2 = null;
				//				$scope.sense = function() {
				//				window.SenseCallBack = function(value) {
				//					alert(value);
				//					clearTimeout($scope.time2);
				//					if(value == "I(00,01,0)E" && url == '/index.html#/main') {
				//						$scope.time2 = setTimeout(function() {
				//							clearInterval($scope.time);
				//							$location.path("/video");
				//							$scope.$apply();
				//						}, 10000);
				//					}
				//						else if(value == "I(00,01,1)E" && url == '/index.html#/main') {
				//							clearInterval($scope.time);
				//							$scope.sense();
				//						}
				//				}
				//				}
				//				$scope.time = setInterval(function(){
				//					$scope.sense();
				//				},3000);
				$scope.answer = function() {
					if(($('#input1').val()) == undefined) {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=";
					} else {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=" + encodeURI($('#input1').val());
					}
					$.log.debug('https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=' + encodeURI($('.input1').val()));
				}
				$scope.sound = function() {
					window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=";
				}
				$scope.enterEvent = function(e) {
					var keycode = window.event ? e.keyCode : e.which;
					if(keycode == 13) {
						$scope.answer();
					}
				}
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				$scope.openExe = function() {
					$.log.debug(window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
					$.device.exeOpenAbsolute("???????????????????????????", window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
				}
				$(function() {
					var swiper = new Swiper('.swiper-container1', {
						pagination: '.swiper-pagination',
						slidesPerView: 'auto',
						paginationClickable: true,
						spaceBetween: 270
					});
				});
				// ????????????
				// ????????????
				let serverip_str = "10.207.184.92";
				let serverport_str = "5060";
				let agentid_str = "8110";
				let agentpasswd_str = "81108110";
				let agentdial_no = "8000";
				//				let addnum_no = "962266218";
				let addnum_no = "";
				try {
					addnum_no = window.external.GetConfig("telephoneNumber");
					$.log.debug('telephoneNumber: ' + addnum_no);
				} catch(e) {
					$.log.debug('????????????????????????????????????: telephoneNumber');
				}
				try {
					$scope.hasCall = window.external.GetConfig("hasCall");
					$.log.debug('hasCall: ' + $scope.hasCall);
				} catch(e) {
					$.log.debug('?????????????????????????????????: hasCall');
				}
				if($scope.hasCall == 'Y') {
					$scope.hasCall = true;
				} else {
					$scope.hasCall = false;
				}
				$scope.callConsultation = function() {
					console.log('????????????');
					$scope.isAlert = true;
					$scope.msg = '??????????????????????';
					$scope.confirm_show = true;
					$scope.cancel_show = true;
				}
				$scope.alertConfirm = function() {
					$scope.msg = '?????????, ???????????????...';
					$scope.confirm_show = false;
					// ?????????????????????
					var rtnv1 = document.getElementById("ssipc").SetServerParam(serverip_str, serverport_str, 0);
					$.log.debug('?????????????????????: ' + rtnv1)
					// ??????
					var rtnv2 = document.getElementById("ssipc").StartLink(agentid_str, agentpasswd_str, agentid_str);
					$.log.debug('?????????????????????: ' + rtnv2)
					// ??????
					var rtnv3 = document.getElementById("ssipc").StartCall(agentdial_no);
					$.log.debug('????????????: ' + rtnv3)
					// ????????????
					setTimeout(function() {
						var rtnv4 = document.getElementById("ssipc").SendDTMF(addnum_no);
						$.log.debug('??????????????????: ' + rtnv4)
					}, 3000)
				}
				$scope.alertCancel = function() {
					$scope.isAlert = false;
					// ????????????
					var rtnv5 = document.getElementById("ssipc").StopCall();
					$.log.debug('????????????: ' + rtnv5)
				}
				//????????????????????????
				let curWwwPath = window.document.location.hostname;
				if(curWwwPath == "183.194.250.112") {
					$scope.isShow = false;
				} else if(curWwwPath == "10.81.16.56") {
					$scope.isShow = true;
				} else {
					$scope.isShow = false;
				}
				//??????????????????
				if($.getConfigMsg.isCommunity == 'Y' || $.getConfigMsg.isCommunity == null || $.getConfigMsg.isCommunity == undefined) {
					$scope.isCommunity = false;
				}else{
					$scope.isCommunity = true;
				}
				//??????????????????
				$scope.weatherForecast = function() {
					//?????????????????????
					let date = new Date();
					let month = date.getMonth() + 1;
					let day = date.getDate();
					if(month >= 1 && month <= 9) {
						month = "0" + month;
					}
					if(day >= 0 && day <= 9) {
						day = "0" + day;
					}
					let today = date.getFullYear() + "-" + month + "-" + day;
					appFactory.jsonp($.getConfigMsg.preUrlSelf + "/selfapi/meteorological/weatherOnThe10th.do", "", function(dataJson) {
						if(dataJson.data.Code == 0 && dataJson.success == true && dataJson.data.Data) {
							$scope.dataWeatherList = dataJson.data.Data;
							$scope.todayWeather = fuzzyQuery($scope.dataWeatherList, today, "forecastdate")[0];
							$scope.weatherImg = screen($scope.todayWeather.dayweather);
						}
					}, function(err) {
						console.log(err);
					})
					appFactory.jsonp($.getConfigMsg.preUrlSelf + "/selfapi/meteorological/severeWeatherWarning.do", "", function(dataJson) {
						if(dataJson.Code == 0 && dataJson.Success == true) {
							$scope.dataWarningList = dataJson.Data;
							if($scope.dataWarningList[0].state == "??????" && dataJson.Data){
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade+$scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				$scope.weatherForecast();
			}
		}).state("video", {
			url: "/video",
			templateUrl: "index/video.html",
			controller: function($scope, $location, $rootScope, appData, $timeout) {
				var vList = ['index/1.mp4', 'index/1.mp4']; // ?????????????????????
				var vLen = vList.length; // ?????????????????????
				var curr = 0; // ?????????????????????
				var vid = document.getElementById("myVideo");
				var p = function() {
					vid.play();
					if(vid.currentTime == 5) {
						vid.currentTime = 0;
						vid.play();
					}
					var url = $location.absUrl().split('selfWorkBench')[1];
					window.SenseCallBack = function(value) {
						$.log.debug("*********" + value);
						if(value == "I(00,01,1)E" && url == '/index.html#/video') {
							window.external.Hd_Audio_PPlay("Realtek High Definition Audio", window.external.GetCurrentPath() + "\\resources\\audio\\main.wav");
							//vid.src = 'index/2.mp4';
							vid.currentTime = 5;
							vid.play();
							vid.onended = function() {
								$.device.audioStop();
								$location.path("/main");
								$scope.$apply();
							}
						}
					};
				}
				p();
				//??????????????????
				//????????????????????????????????????
				vid.addEventListener("timeupdate", function() {
					var timeDisplay;
					//????????????????????????????????????
					timeDisplay = Math.floor(vid.currentTime);
					console.log(Math.floor(vid.currentTime))
					//?????????????????? 4s??????????????????
					if(timeDisplay == 4) {
						vid.currentTime = 0;
						vid.play();
					}
				}, false);
			}
		})
})