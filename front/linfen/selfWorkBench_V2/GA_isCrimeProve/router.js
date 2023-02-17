app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "医保"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "医保"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "医保个人信息查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "医保"
			}
		})
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "医保"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "医保"
			}
		})
		.state("isCrimeChoose", {
			url: "/isCrimeChoose",
			templateUrl: "views/isCrimeChoose.html",
			controller: "isCrimeChoose",
			data: {
				title: "是否 违法犯罪"
			}
		})
		.state("isCrimeDetail", {
			url: "/isCrimeDetail",
			templateUrl: "views/isCrimeDetail.html",
			controller: "isCrimeDetail",
			data: {
				title: "是否 违法犯罪"
			}
		})
		.state("isCrimeInfo", {
			url: "/isCrimeInfo",
			templateUrl: "views/isCrimeInfo.html",
			controller: "isCrimeInfo",
			data: {
				title: "是否 违法犯罪"
			}
		})
		.state("isCrimeSelectMethod", {
			url: "/isCrimeSelectMethod",
			templateUrl: "views/isCrimeSelectMethod.html",
			controller: "isCrimeSelectMethod",
			data: {
				title: "是否 违法犯罪"
			}
		})
		.state("isCrimeInput", {
			url: "/isCrimeInput",
			templateUrl: "views/isCrimeInput.html",
			controller: "isCrimeInput",
			data: {
				title: "是否 违法犯罪"
			}
		})
		.state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialList",
			data: {
				title: "是否 违法犯罪"
			}
		})
		.state("uploadMethod", {
			url: "/uploadMethod",
			templateUrl: "views/uploadMethod.html",
			controller: "uploadMethod",
			data: {
				title: "是否 违法犯罪"
			}
		}).state("materialPic", {
			url: "/materialPic",
			templateUrl: "views/materialPic.html",
			controller: "materialPic",
			data: {
				title: "是否 违法犯罪"
			}
		}).state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialView",
			data: {
				title: "是否 违法犯罪"
			}
		}).state("takePhoto", {
			url: "/takePhoto",
			templateUrl: "views/takePhoto.html",
			controller: "takePhoto",
			data: {
				title: "是否 违法犯罪"
			}
		}).state("uFileUpload", {
			url: "/uFileUpload",
			templateUrl: "views/uFileUpload.html",
			controller: "uFileUpload",
			data: {
				title: "是否 违法犯罪"
			}
		}).state("infoFinish", {
			url: "/infoFinish",
			templateUrl: "views/infoFinish.html",
			controller: "infoFinish",
			data: {
				title: "是否 违法犯罪"
			}
		})
	/*idcard router end*/
})