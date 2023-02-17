app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData) {
				appData = {};
				$rootScope.isChild = false;
				$scope.licenseShow = false;
				$('.navigation ul li').css({
					"width": "258px",
					"background-size": "258px 58px"
				});
				$('.navigation ul > li:first-child,.navigation ul > li:last-child').css({
					"width": "289px",
					"background-size": "289px 58px"
				});
				var uniqueId = $.config.get('uniqueId');
				if(uniqueId == "HP011") {
					$scope.licenseShow = true;
					$('.navigation ul li').css({
						"width": "205px",
						"background-size": "205px 58px"
					});
					$('.navigation ul > li:first-child,.navigation ul > li:last-child').css({
						"width": "289px",
						"background-size": "289px 58px"
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
				//				$scope.openExe = function() {
				//					$.log.debug(window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
				//					$.device.exeOpenAbsolute("智能工作台视频咨询", window.external.GetCurrentPath() + "\\resources\\wandazhineng\\msconf.exe");
				//				}
				$(function() {
					var swiper = new Swiper('.swiper-container1', {
						pagination: '.swiper-pagination',
						slidesPerView: 'auto',
						paginationClickable: true,
						spaceBetween: 270
					});
				});
				// 根据设备标识 切换显示自主取表模块和银行服务模块JAWDself-terminal0026
				//				$scope.isShowBankService = $.config.get('uniqueId').substring(0, 4) == 'bank' ? true : false;
				//				if($.config.get('uniqueId') && $.config.get('uniqueId').substring(0, 4) == 'bank') {
				//					$scope.isShowBankService = true;
				//				} else if($.config.get('uniqueId') && $.config.get('uniqueId').substring(0, 4) != 'bank') {
				//					$scope.isShowBankService = false;
				//				} else {
				//					$scope.isShowBankService = false;
				//				}

				if($.config.get('uniqueId') && $.config.get('uniqueId') == 'JAWDself-terminal0026') {
					$scope.isShowBankService = true;
				} else {
					$scope.isShowBankService = false;
				}

				//最小化窗口
				$scope.proMinimizes = function() {
					console.log(1);
					$.device.proMinimizes();
				}
			}
		})
})