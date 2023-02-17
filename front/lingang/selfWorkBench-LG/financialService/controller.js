app.controller("mainController", function($scope, $state, appData) {

	$scope.operation = "金融服务";
	$scope.choiceType = function(type) {
		$state.go(type);
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
});

app.controller("enterpriseOpenAccountController", function($scope, $state, appData) {

	$scope.operation = "企业开户服务";
	$scope.isShowCancelBtn = "false";
	$scope.choiceType = function(type) {
		$scope.isAlert = true;
		$scope.msg = "对不起,该功能暂未开放!";
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
});
app.controller('financialLoanController', function($state, $scope, appData) {
	$scope.operation = "普惠金融贷款";
	$scope.isShowCancelBtn = "false";
	$scope.choiceType = function(type) {
		//		$scope.isAlert = true;
		//		$scope.msg = "对不起,该功能暂未开放!";
		$state.go("edcxQRCode")
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
});
app.controller('edcxQRCodeController', function($state, $scope, appData) {
	$scope.operation = "信贷额度查询";
	$scope.isShowCancelBtn = "false";
	$scope.choiceType = function(type) {
		//		$scope.isAlert = true;
		//		$scope.msg = "对不起,该功能暂未开放!";
		$state.go("edcxQRCode")
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prev = function() {
		$state.go('financialLoan');
	}
});
app.controller('internationalForeignExchangeController', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "国际外汇业务";
	$scope.is_show_cancel_btn = "false";
	$scope.choiceType = function(type) {
		if(type == "foreign_currency_exchange") {
			$state.go("foreignCurrencyExchange");
		} else if(type == "cross_border_remittance") {
			$scope.isAlert = true;
			$scope.msg = "对不起,该功能暂未开放";
			//			$state.go("crossBorderRemittance");
		}
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
})
app.controller("personalFinanceController", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope, $interval) {
	$scope.operation = "请选择卡类型";
	$scope.choiceType = function(type) {
		if(type == "debit_card") {
			$state.go('debitCardQRCode');
		} else if(type == "credit_card") {
			$state.go("creditCard")
		}
	}
});
app.controller("debitCardQRCodeController", function($scope, $state, appData, $http, appFactory) {
	$scope.prev = function() {
		$state.go('personalFinance');
	}
});

app.controller("creditCardController", function($scope, $state, appData, $sce, $interval) {
	appData.address = "http://creditcard.ccb.com/cn/creditcard/card_list.html"
	$scope.address = $sce.trustAsResourceUrl(appData.address);
	window.external.URL_OPEN(50, 160, 1800, 780, $scope.address);
	$scope.prev = function() {
		$state.go('personalFinance');
	}
});

app.controller("foreignCurrencyExchangeController", function($scope, $state, appData, $sce) {
	$scope.prev = function() {
		$state.go('internationalForeignExchange');
	}
});

app.controller("crossBorderRemittanceController", function($scope, $state, appData, $sce) {
	$scope.is_show_next_btn = "0";
	window.external.URL_OPEN(50, 160, 1800, 780, "https://b2b.ccb.com");
	$scope.address = $sce.trustAsResourceUrl("https://b2b.ccb.com");
});

app.controller("websiteServiceController", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	
});

app.controller("rentServiceController", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = "请扫描二维码";
});