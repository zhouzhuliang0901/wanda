app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData,appFactory) {
				appData = {};
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				appFactory.queryDevice("A4Printer", function(dataJson) {
					if(dataJson.isSuccess == true) {
						$rootScope.paperSum = dataJson.data.data.nmRemain;
					} else {
						$rootScope.paperSum = 0;
					}
				})
				$rootScope.isChild = false;
				$scope.licenseShow = false;
				$('.navigation ul li').css({
					"width": "258px",
				});
				$('.navigation ul > li:first-child,.navigation ul > li:last-child').css({
					"width": "289px",
				});
				var uniqueId = $.config.get('uniqueId');
				var isLicense;
				try{
					isLicense = $.config.get('hasLicense');
				}catch(e){
					isLicense = "N";
				}
				if(uniqueId == "HP011" || uniqueId == "HPZX001" || isLicense == "Y") {
					$scope.licenseShow = true;

					$('.navigation ul li').css({
						"width": "240px",
					});
					$('.navigation ul > li:nth-child(3)').css({
						"width": "176px",
					});
					$('.navigation ul > li:nth-child(5)').css({
						"width": "224px",
					});
					$('.navigation ul > li:first-child,.navigation ul > li:last-child').css({
						"width": "287px",
					});
				}
				$scope.answer = function() {
					if(($('#input1').val()) == undefined) {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=";
					} else {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=" + encodeURI($('#input1').val());
					}
					$.log.debug('https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=' + encodeURI($('#input1').val()));
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
					$.log.debug(window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
					$.device.exeOpenAbsolute("智能工作台视频咨询", window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
				}
//				$(function() {
//					var swiper = new Swiper('.swiper-container1', {
//						pagination: '.swiper-pagination',
//						slidesPerView: 'auto',
//						paginationClickable: true,
//						spaceBetween: 270
//					});
//				});
			}
		})
		.state("main2", {
			url: "/main2",
			templateUrl: "index/main_second.html",
			controller: function($scope, $location, $rootScope, appData,appFactory) {
				appData = {};
				try {
					$scope.id = window.external.GetConfig("uniqueId");
				} catch(e) {}
				appFactory.queryDevice("A4Printer", function(dataJson) {
					if(dataJson.isSuccess == true) {
						$rootScope.paperSum = dataJson.data.data.nmRemain;
					} else {
						$rootScope.paperSum = 0;
					}
				})
				$rootScope.isChild = false;
				$scope.licenseShow = false;
				$('.navigation ul li').css({
					"width": "258px",
				});
				$('.navigation ul > li:first-child,.navigation ul > li:last-child').css({
					"width": "289px",
				});
				var uniqueId = $.config.get('uniqueId');
				var isLicense;
				try{
					isLicense = $.config.get('hasLicense');
				}catch(e){
					isLicense = "N";
				}
				if(uniqueId == "HP011" || uniqueId == "HPZX001" || isLicense == "Y") {
					$scope.licenseShow = true;

					$('.navigation ul li').css({
						"width": "240px",
					});
					$('.navigation ul > li:nth-child(3)').css({
						"width": "176px",
					});
					$('.navigation ul > li:nth-child(5)').css({
						"width": "224px",
					});
					$('.navigation ul > li:first-child,.navigation ul > li:last-child').css({
						"width": "287px",
					});
				}
				$scope.answer = function() {
					if(($('#input1').val()) == undefined) {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=";
					} else {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=" + encodeURI($('#input1').val());
					}
					$.log.debug('https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=' + encodeURI($('#input1').val()));
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
					$.log.debug(window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
					$.device.exeOpenAbsolute("智能工作台视频咨询", window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
				}
			}
		})
})