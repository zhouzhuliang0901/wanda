app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/index");
	$stateProvider
	.state("index", {
			url: "/index",
			templateUrl: "views/index.html",
			controller: "index",
			data: {
				title: "登录方式"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/loginType.html",
			controller: "archivesLoginType",
			data: {
				title: "登录方式"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "archivesLogin",
			data: {
				title: "登录验证"
			}
		})
		.state("loginUkey", {
			url: "/loginUkey",
			templateUrl: "views/loginType.html",
			controller: "loginUkey",
			data: {
				title: "登录方式"
			}
		})
		.state("infoMarry", {
			url: "/infoMarry",
			templateUrl: "views/infoMarry.html",
			controller: "infoMarry",
			data: {
				title: "基础信息"
			}
		})
		.state("MaterialList", {
			url: "/MaterialList",
			templateUrl: "views/MaterialList.html",
			controller: "MaterialList",
			data: {
				title: "上传材料"
			}
		})
		.state("fileUpload", {
			url: "/fileUpload",
			templateUrl: "views/FileUpload.html",
			controller: "fileUpload",
			data: {
				title: "上传材料"
			}
		})
		.state("finish", {
			url: "/finish",
			templateUrl: "views/finish.html",
			controller: "finish",
			data: {
				title: "拍照上传"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "提交"
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "preview",
			data: {
				title: "档案查询"
			}
		})
		.state("underway", {
			url: "/underway",
			templateUrl: "views/underway.html",
			controller: "underway",
			data: {
				title: "扫描信息"
			}
		})
})