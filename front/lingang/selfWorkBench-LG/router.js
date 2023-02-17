app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "index/main.html",
			controller: function($scope, $location, $rootScope, appData) {
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
			}
		})
})