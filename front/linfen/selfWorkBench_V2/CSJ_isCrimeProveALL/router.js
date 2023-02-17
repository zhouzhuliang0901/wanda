app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/choiceMode");
	$stateProvider
		.state("choiceMode", {
			url: "/choiceMode",
			templateUrl: "views/choiceMode.html",
			controller: "choiceMode",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("choiceProvince", { //选择省市
			url: "/choiceProvince",
			templateUrl: "views/choiceProvince.html",
			controller: "choiceProvince",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "户籍证明开具"
			}
		})
})