app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider

		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "licenseMain",
			data: {
				title: "证明开具"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "licenseLoginType",
			data: {
				title: "证明开具"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "licenseLogin",
			data: {
				title: "证明开具"
			}
		})
		.state("license", {
			url: "/license",
			templateUrl: "views/license.html",
			controller: "licenseLicense",
			data: {
				title: "证明开具"
			}
		})
		.state("upload", {
			url: "/upload",
			templateUrl: "views/upload.html",
			controller: "historyUpload",
			data: {
				title: "证明开具"
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "elicensePreview",
			data: {
				title: "证明开具"
			}
		})
		.state("apply", {
			url: "/apply",
			templateUrl: "views/apply.html",
			controller: "apply",
			data: {
				title: "证明开具"
			}
		})
})