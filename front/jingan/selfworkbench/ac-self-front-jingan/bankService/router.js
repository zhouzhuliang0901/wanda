app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider

		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "mainController",
			data: {
				title: "银行服务"
			}
		})
		.state("huiDongNi", {
			url: "/huiDongNi",
			templateUrl: "views/huiDongNi.html",
			controller: "huiDongNiController",
			data: {
				title: "银行服务"
			}
		})
		.state("productDescription", {
			url: "/productDescription",
			templateUrl: "views/productDescription.html",
			controller: "productDescriptionController",
			data: {
				title: "银行服务"
			}
		})
		.state("funcHighlights", {
			url: "/funcHighlights",
			templateUrl: "views/funcHighlights.html",
			controller: "funcHighlightsController",
			data: {
				title: "银行服务"
			}
		})
		.state("loanService", {
			url: "/loanService",
			templateUrl: "views/loanService.html",
			controller: "loanServiceController",
			data: {
				title: "银行服务"
			}
		})
		.state("laborHarbor", {
			url: "/laborHarbor",
			templateUrl: "views/laborHarbor.html",
			controller: "laborHarborController",
			data: {
				title: "银行服务"
			}
		})
		.state("JABranchBankOperateNetwork", {
			url: "/JABranchBankOperateNetwork",
			templateUrl: "views/JABranchBankOperateNetwork.html",
			controller: "JABranchBankOperateNetworkController",
			data: {
				title: "银行服务"
			}
		})
})