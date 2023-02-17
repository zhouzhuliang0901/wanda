app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/guideline");

	$stateProvider
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "公安"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "公安"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "公安"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "公安"
			}
		})
		.state("infoChoose", {
			url: "/infoChoose",
			templateUrl: "views/infoChoose.html",
			controller: "infoChoose",
			data: {
				title: "公安"
			}
		})
		.state("pickUpMethod", {
			url: "/pickUpMethod",
			templateUrl: "views/pickUpMethod.html",
			controller: "pickUpMethod",
			data: {
				title: "公安"
			}
		})
		.state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialList",
			data: {
				title: "公安"
			}
		})
		.state("uploadMethod", {
			url: "/uploadMethod",
			templateUrl: "views/uploadMethod.html",
			controller: "uploadMethod",
			data: {
				title: "公安"
			}
		}).state("materialPic", {
			url: "/materialPic",
			templateUrl: "views/materialPic.html",
			controller: "materialPic",
			data: {
				title: "公安"
			}
		}).state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialView",
			data: {
				title: "公安"
			}
		}).state("takePhoto", {
			url: "/takePhoto",
			templateUrl: "views/takePhoto.html",
			controller: "takePhoto",
			data: {
				title: "公安"
			}
		}).state("uFileUpload", {
			url: "/uFileUpload",
			templateUrl: "views/uFileUpload.html",
			controller: "uFileUpload",
			data: {
				title: "公安"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "公安"
			}
		})
	/*idcard router end*/
})