app.controller("mainController", function($scope, $route, $location, $http, data, $timeout) {
	$scope.currentPage = 1;
	$scope.totalPages = 1;
	$scope.formList = [{
			itemUrl: "http://61.172.176.214:28089/ac-product-ext/fillform/opq/info.do?source=msmp1&itemCode=310100536000&conditionCode=310100536000-01",
			itemName: "取水许可证(延续)",
		},
	];
	// 跳转
	$scope.goto = function(url) {
		window.external.URL_OPEN(120,240,1700,800,url);
	}
});