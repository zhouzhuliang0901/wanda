app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider

		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "licenseMain",
			data: {
				title: "我的证照"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "licenseLoginType",
			data: {
				title: "我的证照"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "licenseLogin",
			data: {
				title: "我的证照"
			}
		})
		.state("queryFace", {
			url: "/queryFace",
			templateUrl: "views/queryFace.html",
			controller: "queryFace",
			data: {
				title: "我的证照"
			}
		})
		.state("license", {
			url: "/license",
			templateUrl: "views/license.html",
			controller: "licenseLicense",
			data: {
				title: "我的证照"
			}
		})
		.state("licenseList", {
			url: "/licenseList",
			templateUrl: "views/licenseList.html",
			controller: "licenseList",
			data: {
				title: "我的证照"
			}
		})
		.state("upload", {
			url: "/upload",
			templateUrl: "views/upload.html",
			controller: "historyUpload",
			data: {
				title: "我的证照"
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "elicensePreview",
			data: {
				title: "我的证照"
			}
		})
})