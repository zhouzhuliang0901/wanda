app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, $interval, appFactory) {
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
				$scope.test = function() {
					saveDeviceStatus("?????????", 0, "??????", 0, 200, 200, 0);
				}
				$scope.read = function() {
					saveDeviceStatus("?????????", 0, "??????", 0, 0, 0, 246);
				}
				$scope.exit = function() {
					saveDeviceStatus("?????????", 0, "??????", 0, 0, 0, 0);
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