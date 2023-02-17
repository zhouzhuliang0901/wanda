app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main2.html",
			controller: function($scope, $location, $rootScope, appData, $sce, $timeout, $interval, appFactory, $http) {
				try {
					localStorage.clear();
				} catch(e) {}
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
					$.device.cmCaptureClose();
					var date = new Date();
					var week = date.getDay();
					var mint = date.getMinutes();
					if(mint < 10) {
						mint = 0 + "" + mint;
					}
					var time = parseInt(date.getHours() + "" + mint);
					var thisDay = false;
					var videoWeek = [1, 2, 3, 4, 5]; //'周一至周五'
					for(i = 0; i < videoWeek.length; i++) {
						if(Number(videoWeek[i]) == week) {
							thisDay = true;
						}
					}
					console.log(thisDay)
					console.log(time)
					//周一至周五，上午9点至11点30，下午13点30至16点30
					if(thisDay && ((900 <= time && time <= 1130) || (1330 <= time && time <= 1630))) {
						$.log.debug(window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
						$.device.exeOpenAbsolute("智能工作台视频咨询", window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
					} else {
						$scope.isAlert = true;
						$scope.msg = $sce.trustAsHtml('抱歉，当前时间不在服务时段<p>（周一至周五，上午8点30至11点30，下午13点30至16点30）</p>');
						$scope.confirm_show = true;
						$scope.cancel_show = false;
						$scope.alertConfirm = function() {
							$scope.isAlert = false;
						}
					}
				}
				$(function() {
					var swiper = new Swiper('.swiper-container1', {
						pagination: '.swiper-pagination',
						slidesPerView: 'auto',
						paginationClickable: true,
						spaceBetween: 270
					});
				});
				// 服务咨询
				// 拨号参数
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
					$.log.debug('没有获取到电话号码配置项: telephoneNumber');
				}
				try {
					$scope.hasCall = window.external.GetConfig("hasCall");
					$.log.debug('hasCall: ' + $scope.hasCall);
				} catch(e) {
					$.log.debug('没有获取到电话机配置项: hasCall');
				}
				if($scope.hasCall == 'Y') {
					$scope.hasCall = true;
				} else {
					$scope.hasCall = false;
				}
				$scope.callConsultation = function() {
					console.log('电话咨询');
					$scope.isAlert = true;
					$scope.msg = '请确认是否拨号?';
					$scope.confirm_show = true;
					$scope.cancel_show = true;
				}
				$scope.alertConfirm = function() {
					$scope.msg = '拨号中, 请耐心等待...';
					$scope.confirm_show = false;
					// 服务器参数设定
					var rtnv1 = document.getElementById("ssipc").SetServerParam(serverip_str, serverport_str, 0);
					$.log.debug('服务器连接返回: ' + rtnv1)
					// 登录
					var rtnv2 = document.getElementById("ssipc").StartLink(agentid_str, agentpasswd_str, agentid_str);
					$.log.debug('客户端登录返回: ' + rtnv2)
					// 外呼
					var rtnv3 = document.getElementById("ssipc").StartCall(agentdial_no);
					$.log.debug('外呼返回: ' + rtnv3)
					// 追加号码
					setTimeout(function() {
						var rtnv4 = document.getElementById("ssipc").SendDTMF(addnum_no);
						$.log.debug('追加号码返回: ' + rtnv4)
					}, 3000)
				}
				$scope.alertCancel = function() {
					$scope.isAlert = false;
					// 挂断电话
					var rtnv5 = document.getElementById("ssipc").StopCall();
					$.log.debug('挂断返回: ' + rtnv5)
				}
				//是否展示档案服务
				let curWwwPath = window.document.location.hostname;
				if(curWwwPath == "183.194.250.112") {
					$scope.isShow = false;
				} else if(curWwwPath == "10.81.16.56") {
					$scope.isShow = true;
				} else {
					$scope.isShow = false;
				}
				//是否社区事项
				console.log($.getConfigMsg.isCommunity != 'N');
				if($.getConfigMsg.isCommunity != 'N') {
					$scope.isCommunity = false;
					if($scope.hasCall == false) {
						$('.navigation ul li a').css({
							'padding': '0px 58px'
						});
						$('.navigation ul>li:last-child a').css({
							'padding': '0px 20px'
						});
					} else {
						$('.navigation ul li a').css({
							'padding': '0px 38px'
						});
					}
				} else {
					$scope.isCommunity = true;
					if($scope.hasCall == false) {
						$('.navigation ul li a').css({
							'padding': '0px 38px'
						});
					} else {
						$('.navigation ul li a').css({
							'padding': '0px 20px'
						});
					}
				}
				//是否岸锋机器
				try {
					$.getConfigMsg.isAnfeng = window.external.GetConfig("isAnfeng");
				} catch(e) {}
				if($.getConfigMsg.isAnfeng == 'Y') {
					$scope.isAnfeng = true;
				} else {
					$scope.isAnfeng = false;
				}
				//是否有视频咨询
				try {
					$scope.hasVideoConsultation = window.external.GetConfig("hasVideoConsultation");
				} catch(e) {}

				//是否为宜川路街道  有证照柜小人图标
				try {
					$scope.isLicenseCabinet = window.external.GetConfig("isLicenseCabinet");
				} catch(e) {}

				//是否存在离线码
				//				if($.config.get('isDataCenter') == 'Y'){
				//					$('.lxm').show();
				//				}else{
				//					$('.lxm').hide();
				//				}

				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				//$scope.weatherForecast();
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
		}).state("index-zh", {
			url: "/index-zh",
			templateUrl: "index/index-zh.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = JSON.parse(window.AppHost.getManage().getMachineInfo())['networkinterface'][0].mac.match(new RegExp("\\w{1," + 2 + "}", "g")).join("-");
				} catch(e) {}
				try {
					localStorage.setItem("zhdeviceUrlSuffixName", 'index-zh');
				} catch(e) {}
				//$scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
			}
		}).state("index-zh-ah", {
			url: "/index-zh-ah",
			templateUrl: "index/index-zh-ah.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = JSON.parse(window.AppHost.getManage().getMachineInfo())['networkinterface'][0].mac.match(new RegExp("\\w{1," + 2 + "}", "g")).join("-");
				} catch(e) {}
				try {
					localStorage.setItem("zhdeviceUrlSuffixName", 'index-zh-ah');
				} catch(e) {}
				//$scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(/allOrganYH/.test(address.split("/")[0])) address = address + '?zhid=zhdeviceAH';
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
			}
		}).state("index-zh-print", {
			url: "/index-zh-print",
			templateUrl: "index/index-zh-print.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = JSON.parse(window.AppHost.getManage().getMachineInfo())['networkinterface'][0].mac.match(new RegExp("\\w{1," + 2 + "}", "g")).join("-");
				} catch(e) {}
				//$scope.weatherForecast();
				try {
					localStorage.setItem("zhdeviceUrlSuffixName", 'index-zh-print');
				} catch(e) {}
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
			}
		})
		.state("index-nh", {
			url: "/index-nh",
			templateUrl: "index/index-nh.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				//$scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//最小化  切换
				$scope.proMinimizes = function() {
					//WebSocketDebugHide();
					try{
						//window.external.GoHome();
						$.device.GoHome();
					}catch(e){}
				}
			}
		}).state("index-nh-cm", {
			url: "/index-nh-cm",
			templateUrl: "index/index-nh-cm.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				try {
					if(nhdeviceSum.Phaseiidevice()){
						$scope.showLXM=nhdeviceSum.getConfigDataInfo.isShowLxmPrint;
					}else{
						$scope.showLXM = window.external.GetConfig("isShowLxmPrint");
					}
					$scope.showLXM = $scope.showLXM == 'N' ? false : true;
				} catch(e) {}
				//$scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//最小化  切换
				$scope.proMinimizes = function() {
					//WebSocketDebugHide();
					try{
						// window.external.GoHome();
						$.device.GoHome();
					}catch(e){}
				}
			}
		}).state("index-pf", {
			url: "/index-pf",
			templateUrl: "index/index-pf.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = spdb.getConfig();
				} catch(e) {}
				//$scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//返回浦发首页
				$scope.proMinimizes = function() {
					spdb.goHome();
				}
			}
		}).state("index-jtyh", {
			url: "/index-jtyh",
			templateUrl: "index/index-jtyh.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = "";
				} catch(e) {}
				$scope.showLXM = false;
				try {
					$scope.showLXM = "print" == window.isBocomDev? true:false;
				} catch(e) {}
				//$scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//首页
				$scope.proMinimizes = function() {
					try {
						$.deviceBocom.destroyWindow();
					} catch(e) {}
				}
			}
		})
		.state("index-ns", {
			url: "/index-ns",
			templateUrl: "index/index-ns.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				try {
					window.external.Hd_IdCard_Close();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				// $scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//最小化  切换
				$scope.proMinimizes = function() {
					try{
						var nsIndexName='0';
						if(0 < window.navigator.userAgent.indexOf("SHRCB")){
							nsIndexName='1';
						}
					}catch(e){
						nsIndexName='0';
					}
					try{
						if(nsIndexName=='0'){
							WebSocketDebugHide();
						}else{
							$.nsdeviceSum.hideWindow(function(result) {})
						}
					}catch(e){}
					// WebSocketDebugHide();
					// $.nsdeviceSum.hideWindow(function(result) {})
				}
			}
		})
		.state("index-ns-print", {
			url: "/index-ns-print",
			templateUrl: "index/index-ns-print.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				try {
					localStorage.setItem("nsdeviceUrlSuffixName", 'index-ns-print');
				} catch(e) {}
				try {
					window.external.Hd_IdCard_Close();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				// $scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//最小化  切换
				$scope.proMinimizes = function() {
					$.nsdeviceSum.hideWindow(function(result) {})
				}
			}
		})
		.state("index-gh", {
			url: "/index-gh",
			templateUrl: "index/index-gh.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				//$scope.weatherForecast();
				try {
					localStorage.setItem("ghdeviceUrlSuffixName", 'index-gh');
				} catch(e) {}
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//最小化  切换
				$scope.proMinimizes = function() {}
			}
		}).state("index-gh-print", {
			url: "/index-gh-print",
			templateUrl: "index/index-gh-print.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				//$scope.weatherForecast();
				try {
					localStorage.setItem("ghdeviceUrlSuffixName", 'index-gh-print');
				} catch(e) {}
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//最小化  切换
				$scope.proMinimizes = function() {}
			}
		})
		.state("index-pt", {
			url: "/index-pt",
			templateUrl: "index/index-pt.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}

				//是否展示档案服务
				let curWwwPath = window.document.location.hostname;
				if(curWwwPath == "183.194.250.112") {
					$scope.isShow = false;
				} else if(curWwwPath == "10.81.16.56") {
					$scope.isShow = true;
				} else {
					$scope.isShow = false;
				}
				//是否社区事项
				console.log($.getConfigMsg.isCommunity != 'N');
				if($.getConfigMsg.isCommunity != 'N') {
					$scope.isCommunity = false;
					$('.navigation ul li a').css({
						'padding': '0px 58px'
					});
					$('.navigation ul>li:last-child a').css({
						'padding': '0px 20px'
					});
				} else {
					$scope.isCommunity = true;
					$('.navigation ul li a').css({
						'padding': '0px 38px'
					});
				}
				//是否有视频咨询
				try {
					$scope.hasVideoConsultation = window.external.GetConfig("hasVideoConsultation");
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {
					//获取当天年月日
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
							if($scope.dataWarningList[0].state == "发布" && dataJson.Data) {
								$scope.warningImg = filterByInfo(warningList, $scope.dataWarningList[0].grade + $scope.dataWarningList[0].category, "name");
							}
						}
					}, function(err) {
						console.log(err);
					})
				}
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				//$scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
			}
		}).state("csjindex", {
			url: "/csjindex",
			templateUrl: "index/csjindex.html",
			controller: function($scope, $location, $rootScope, appData, $sce, $timeout, $interval, appFactory, $http) {
				try {
					localStorage.clear();
				} catch(e) {}
				try {
					localStorage.setItem("yhPublicItemdevice", 'csjindex');
				} catch(e) {}
				$scope.isShowYHhome = false;
				$scope.nhisShowWsrk = false;
				$scope.isShowYhBottomHome = false;
				$scope.isShowYHhomedev = '';
				try {
					if("nhdevice" == acBridgeMac.vendor()) {
						$scope.isShowYHhome = true;
						$scope.nhisShowWsrk = true;
						$scope.isShowYhBottomHome = true;
						$scope.isShowYHhomedev = 'nhdevice';
					};
				} catch(e) {}
				try {
					if(acBridgeMac.vendor() == 'jtdevice') {
						$scope.isShowYHhome = true;
						$scope.isShowYHhomedev = 'jtdevice';
					};
				} catch(e) {}
				try {
					if(acBridgeMac.vendor() == 'ghdevice') {
						$scope.isShowYhBottomHome = false;
					};
				} catch(e) {}
				//最小化  切换
				$scope.proMinimizes = function() {
					if(acBridgeMac.vendor() == 'nhdevice') {
						//WebSocketDebugHide();
						try{
							// window.external.GoHome();
							$.device.GoHome();
						}catch(e){}
					} else if(acBridgeMac.vendor() == 'jtdevice') {
						try {
							$.deviceBocom.destroyWindow();
						} catch(e) {}
					}
				}
				$scope.goToApphomeyh = function() {
					if(acBridgeMac.vendor() == 'nhdevice') {
						// window.external.GoHome();
						$.device.GoHome();
					} else  {
						alert('未提供返回首页方法');
					}
				}
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
			}
		})
		.state("index-yh-yb", {
			url: "/index-yh-yb",
			templateUrl: "index/index-yh-yb.html",
			controller: function($scope, $location, $rootScope, appData, $sce, $timeout, $interval, appFactory, $http) {
				try {
					localStorage.clear();
				} catch(e) {}
				try {
					localStorage.setItem("yhPublicItemdevice", 'index-yh-yb');
				} catch(e) {}
				$scope.isShowYHhome = false;
				$scope.isShowYHhomedev = '';
				try {
					if("nhdevice" == acBridgeMac.vendor()) {
						$scope.isShowYHhome = true;
						$scope.isShowYHhomedev = 'nhdevice';
					};
				} catch(e) {}
				try {
					if(acBridgeMac.vendor() == 'jtdevice') {
						$scope.isShowYHhome = true;
						$scope.isShowYHhomedev = 'jtdevice';
					};
				} catch(e) {}
				//最小化  切换
				$scope.proMinimizes = function() {
					if(acBridgeMac.vendor() == 'nhdevice') {
						//WebSocketDebugHide();
						try{
							// window.external.GoHome();
							$.device.GoHome();
						}catch(e){}
					} else if(acBridgeMac.vendor() == 'jtdevice') {
						try {
							$.deviceBocom.destroyWindow();
						} catch(e) {}
					}
				}
				//模糊查询
				$scope.searchShow = false;
				$scope.searchShowData = {}
				$scope.sound = function() {
					console.log($scope.askText);
					if($scope.askText == undefined || $scope.askText == '' || $scope.askText == null) {
						$scope.searchShow = false;
					} else {
						$scope.searchShow = true;
						$scope.searchShowData=searchList($scope.askText);
						console.log(searchList($scope.askText));
					}

				}
				$scope.searchgoToApp = function(data) {
					if(data.url!=''){
						window.location.href = data.url.substring(3,data.url.length);
					}
				}
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
			}
		}).state("index-sh", {
			url: "/index-sh",
			templateUrl: "index/index-sh.html",
			controller: function($scope, $location, $rootScope, appData, $timeout, appFactory) {
				try {
					localStorage.clear();
				} catch(e) {}
				try {
					window.external.Hd_IdCard_Close();
				} catch(e) {}
				//天气预报查询
				$scope.weatherForecast = function() {}
				try {
					
				} catch(e) {}
				// $scope.weatherForecast();
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				//最小化  切换
				$scope.proMinimizes = function() {}
			}
		}).state("index-wj", {
			url: "/index-wj",
			templateUrl: "index/index-wj.html",
			controller: function($scope, $location, $rootScope, appData, $sce, $timeout, $interval, appFactory, $http) {
				$scope.goToApp = function(address) {
					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
			}
		})
		
})