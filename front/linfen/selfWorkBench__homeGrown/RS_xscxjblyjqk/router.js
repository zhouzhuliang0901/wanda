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
					saveDeviceStatus("打印机", 0, "正常", 0, 200, 200, 0);
				}
				$scope.read = function() {
					saveDeviceStatus("打印机", 0, "正常", 0, 0, 0, 246);
				}
				$scope.exit = function() {
					saveDeviceStatus("身份证", 0, "正常", 0, 0, 0, 0);
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
					$.device.exeOpenAbsolute("智能工作台视频咨询", window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
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
				var vList = ['index/1.mp4', 'index/1.mp4']; // 初始化播放列表
				var vLen = vList.length; // 播放列表的长度
				var curr = 0; // 当前播放的视频
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
				//监听播放时间
				//使用事件监听方式捕捉事件
				vid.addEventListener("timeupdate", function() {
					var timeDisplay;
					//用秒数来显示当前播放进度
					timeDisplay = Math.floor(vid.currentTime);
					console.log(Math.floor(vid.currentTime))
					//当视频播放到 4s的时候做处理
					if(timeDisplay == 4) {
						vid.currentTime = 0;
						vid.play();
					}
				}, false);
			}
		})
})