app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData,appFactory) {
				appData = {};
				$rootScope.isChild = false;
				$scope.goToApp = function(address) {

					if(address.indexOf("http") != -1) {
						window.location.href = address;
					} else {
						window.location.href = address;
					}
				};
				$(function() {
					var swiper = new Swiper('.swiper-container1', {
						pagination: '.swiper-pagination',
						slidesPerView: 'auto',
						paginationClickable: true,
						spaceBetween: 270
					});
				});
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
					appFactory.jsonp("http://183.194.250.112/ac-self/selfapi/meteorological/weatherOnThe10th.do", "", function(dataJson) {
						if(dataJson.data.Code == 0 && dataJson.success == true && dataJson.data.Data) {
							$scope.dataWeatherList = dataJson.data.Data;
							$scope.todayWeather = fuzzyQuery($scope.dataWeatherList, today, "forecastdate")[0];
							$scope.weatherImg = screen($scope.todayWeather.dayweather);
						}
					}, function(err) {
						console.log(err);
					})
					appFactory.jsonp("http://183.194.250.112/ac-self/selfapi/meteorological/severeWeatherWarning.do", "", function(dataJson) {
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
				$scope.weatherForecast();
			}
		})
})