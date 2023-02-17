app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/guideline");
	$stateProvider
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialList",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("uploadMethod", {
			url: "/uploadMethod",
			templateUrl: "views/uploadMethod.html",
			controller: "uploadMethod",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialView",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("takePhoto", {
			url: "/takePhoto",
			templateUrl: "views/takePhoto.html",
			controller: "takePhoto",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		}).state("uFileUpload", {
			url: "/uFileUpload",
			templateUrl: "views/uFileUpload.html",
			controller: "uFileUpload",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "对公园举办全园性活动的许可"
			}
		})
})