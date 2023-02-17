app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/inputPhone");
	$stateProvider

		.state("inputPhone", {
			url: "/inputPhone",
			templateUrl: "views/inputPhone.html",
			controller: "inputPhone",
			data: {
				title: "制册补证"
			}
		}).state("medicalBookDetails", {
			url: "/medicalBookDetails",
			templateUrl: "views/medicalBookDetails.html",
			controller: "medicalBookDetails",
			data: {
				title: "制册补证"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "制册补证"
			}
		})
		.state("update", {
			url: "/update",
			templateUrl: "views/update.html",
			controller: "update",
			data: {
				title: "制册补证"
			}
		})
		.state("cazsbd", {
			url: "/cazsbd",
			templateUrl: "views/cazsbd.html",
			controller: "cazsbd",
			data: {
				title: "cazsbd"
			}
		})
})