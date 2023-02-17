app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: ""
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: ""
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: ""
			}
		})
		.state("iStd", {
			url: "/iStd",
			templateUrl: "views/iStd.html",
			controller: "iStd",
			data: {
				title: ""
			}
		})
		.state("iTeacher", {
			url: "/iTeacher",
			templateUrl: "views/iTeacher.html",
			controller: "iTeacher",
			data: {
				title: ""
			}
		})
		.state("choice", {
			url: "/choice",
			templateUrl: "views/choice.html",
			controller: "choice",
			data: {
				title: ""
			}
		})
		.state("tchTravelExplain", {
			url: "/tchTravelExplain",
			templateUrl: "views/tchTravelExplain.html",
			controller: "tchTravelExplain",
			data: {
				title: ""
			}
		})
		.state("iCard", {
			url: "/iCard",
			templateUrl: "views/iCard.html",
			controller: "iCard",
			data: {
				title: ""
			}
		})
		.state("stdCertificateInfo", {
			url: "/stdCertificateInfo",
			templateUrl: "views/stdCertificateInfo.html",
			controller: "stdCertificateInfo",
			data: {
				title: ""
			}
		})
		.state("tchBasicInfo", {
			url: "/tchBasicInfo",
			templateUrl: "views/tchBasicInfo.html",
			controller: "tchBasicInfo",
			data: {
				title: ""
			}
		})
		.state("previewResult", {
			url: "/previewResult",
			templateUrl: "views/previewResult.html",
			controller: "previewResult",
			data: {
				title: ""
			}
		})
		.state("schoolReportPrint", {
			url: "/schoolReportPrint",
			templateUrl: "views/schoolReportPrint.html",
			controller: "schoolReportPrint",
			data: {
				title: ""
			}
		})
		.state("stdBasicInfo", {
			url: "/stdBasicInfo",
			templateUrl: "views/stdBasicInfo.html",
			controller: "stdBasicInfo",
			data: {
				title: ""
			}
		})
		.state("provePrinting", {
			url: "/provePrinting",
			templateUrl: "views/provePrinting.html",
			controller: "provePrinting",
			data: {
				title: ""
			}
		})
		.state("std-Diushi-Step2", {
			url: "/std-Diushi-Step2",
			templateUrl: "views/std-Diushi-Step2.html",
			controller: "std-Diushi-Step2",
			data: {
				title: ""
			}
		})
		.state("std-Diushi-Step3", {
			url: "/std-Diushi-Step3",
			templateUrl: "views/std-Diushi-Step3.html",
			controller: "std-Diushi-Step3",
			data: {
				title: ""
			}
		})
})