app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "copyMain",
			data: {
				title: "材料复印"
			}
		})
		.state("idcardCopy", {
			url: "/idcardCopy",
			templateUrl: "views/idcardCopy.html",
			controller: "idcardCopy",
			data: {
				title: "材料复印"
			}
		})
		.state("materialsCopy", {
			url: "/materialsCopy",
			templateUrl: "views/materialsCopy.html",
			controller: "materialsCopy",
			data: {
				title: "材料复印"
			}
		})
		.state("idcardPrint", {
			url: "/idcardPrint",
			templateUrl: "views/idcardPrint.html",
			controller: "idcardPrint",
			data: {
				title: "材料复印"
			}
		})
		.state("materialsPrint", {
			url: "/materialsPrint",
			templateUrl: "views/materialsPrint.html",
			controller: "materialsPrint",
			data: {
				title: "材料复印"
			}
		})
	
	/*idcard router end*/
})