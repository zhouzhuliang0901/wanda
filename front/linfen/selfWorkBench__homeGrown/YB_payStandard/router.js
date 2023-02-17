app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "药品及床位费支付标准查询服务"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "药品及床位费支付标准查询服务"
			}
		})
		.state("searchMedicines", {
			url: "/searchMedicines",
			templateUrl: "views/searchMedicines.html",
			controller: "searchMedicines",
			data: {
				title: "药品及床位费支付标准查询服务"
			}
		})
		.state("medicinesInfo", {
			url: "/medicinesInfo",
			templateUrl: "views/medicinesInfo.html",
			controller: "medicinesInfo",
			data: {
				title: "药品及床位费支付标准查询服务"
			}
		})
})