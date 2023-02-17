app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/TBGPItems");
	$stateProvider
		.state("entryPage", {
			url: "/entryPage",
			templateUrl: "views/entryPage.html",
			controller: 'entryPageController',
			data: {
				title: ""
			}
		})
		.state("SSYSPage", {
			url: "/SSYSPage",
			templateUrl: "views/SProvinceYCityPage.html",
			controller: "SProvinceYCityPageController",
			data: {
				title: ""
			}
		})
		.state("TBGPItems", {
			url: "/TBGPItems",
			templateUrl: "views/TBHighFrequencyItems.html",
			controller: "TBHighFrequencyItemsController",
			data: {
				title: ""
			}
		})

})