app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider.state("main", {
		url: "/main",
		templateUrl: "views/main.html",
		controller: "mainController",
		data: {
			title: "金融服务"
		}
	})
	.state("enterpriseOpenAccount", {
		url: "/enterpriseOpenAccount",
		templateUrl: "views/enterpriseOpenAccount.html",
		controller: "enterpriseOpenAccountController",
		data: {
			title: "企业开户服务"
		}
	})
	.state("financialLoan", {
		url: "/financialLoan",
		templateUrl: "views/financialLoan.html",
		controller: "financialLoanController",
		data: {
			title: "普惠金融贷款"
		}
	})
	.state("edcxQRCode", {
		url: "/edcxQRCode",
		templateUrl: "views/edcxQRCode.html",
		controller: "edcxQRCodeController",
		data: {
			title: "普惠金融贷款"
		}
	})
	.state("internationalForeignExchange", {
		url: "/internationalForeignExchange",
		templateUrl: "views/internationalForeignExchange.html",
		controller: "internationalForeignExchangeController",
		data: {
			title: "国际外汇业务"
		}
	})
	.state("personalFinance", {
		url: "/personalFinance",
		templateUrl: "views/personalFinance.html",
		controller: "personalFinanceController",
		data: {
			title: "个人金融服务"
		}
	})
	.state("debitCardQRCode", {
		url: "/debitCardQRCode",
		templateUrl: "views/debitCardQRCode.html",
		controller: "debitCardQRCodeController",
		data: {
			title: "个人金融服务"
		}
	})
	.state("creditCard", {
		url: "/creditCard",
		templateUrl: "views/creditCard.html",
		controller: "creditCardController",
		data: {
			title: "电子证照"
		}
	})
	.state("foreignCurrencyExchange", {
		url: "/foreignCurrencyExchange",
		templateUrl: "views/foreignCurrencyExchange.html",
		controller: "foreignCurrencyExchangeController",
		data: {
			title: "外币兑换预约"
		}
	})
	.state("crossBorderRemittance", {
		url: "/crossBorderRemittance",
		templateUrl: "views/crossBorderRemittance.html",
		controller: "crossBorderRemittanceController",
		data: {
			title: "外币兑换预约"
		}
	})
	.state("websiteService", {
		url: "/websiteService",
		templateUrl: "views/websiteService.html",
		controller: "websiteServiceController",
		data: {
			title: "银行网点服务"
		}
	})
	.state("rentService", {
		url: "/rentService",
		templateUrl: "views/rentService.html",
		controller: "rentServiceController",
		data: {
			title: "住房租赁服务"
		}
	})
})
