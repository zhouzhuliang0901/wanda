app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData, $timeout) {
				appData = {};
				$rootScope.isChild = false;
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
					try {
						//签注机吞卡
						window.external.DataCard_Open('XPS Card Printer');
					} catch(e) {
						console.log(e);
						$.log.debug(e);
					}
				}
				$scope.read = function() {
					try {
//						//签注机读卡
//						var info = window.external.DataCard_Read();
//						$.log.debug(info);
//						$.log.debug(JSON.stringify(info));
						//签注机打印
						//window.external.DataCard_Residence('XPS Card Printer',135,85,300,400,"<style>td{padding:0px;text-align: justify; -ms-text-justify: inter-ideograph; -ms-text-align-last: justify;}</style><table cellspacing='0' style='font-family:黑体;font-size: 10px;font-weight: bold;'><tr><td>居住地</td><td>:</td><td rowspan='2'>徐汇区长寿路999路9号999室<td></tr><tr><td>地址</td><td>:</td></tr><tr><td>有效期限</td><td>:</td><td>2018年06月09日--2019年06月09日<td></tr></table>");
						window.external.DataCard_Print(135,85,300, 400,"<style>td{padding:0px;text-align: justify; -ms-text-justify: inter-ideograph; -ms-text-align-last: justify;}</style><table cellspacing='0' style='font-family:黑体;font-size: 10px;font-weight: bold;'><tr><td>居住地</td><td>:</td><td rowspan='2'>徐汇区长寿路999路9号999室<td></tr><tr><td>地址</td><td>:</td></tr><tr><td>有效期限</td><td>:</td><td>2018年06月09日--2019年06月09日<td></tr></table>");
					} catch(e) {
						console.log(e);
						$.log.debug(e);
					}
				}
				$scope.exit = function() {
					try {
						//签注机退卡
						window.external.DataCard_Close();
					} catch(e) {
						console.log(e);
						$.log.debug(e);
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