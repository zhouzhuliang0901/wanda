app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData) {
				appData = {};
				$rootScope.isChild = false;
				$scope.answer = function() {
					if(($('.input1').val()) == undefined) {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=";
					} else {
						window.location.href = "https://zwdt.sh.gov.cn/smzy/shell_terminal/tourist/guide/qa?ask=" + encodeURI($('.input1').val());
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
		})
})