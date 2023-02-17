app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/personChoice");

	$stateProvider
		.state("personChoice", {
			url: "/personChoice",
			templateUrl: "views/personChoice.html",
			controller: "personChoice",
			data: {
				title: "办理社保卡开通服务"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "司法局"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "司法局"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "司法局"
			}
		})
		.state("agentInfo", {
			url: "/agentInfo",
			templateUrl: "views/agentInfo.html",
			controller: "agentInfo",
			data: {
				title: "司法局"
			}
		})
		.state("pickUpMethod", {
			url: "/pickUpMethod",
			templateUrl: "views/pickUpMethod.html",
			controller: "pickUpMethod",
			data: {
				title: "司法局"
			}
		})
		.state("uploadMethod", {
			url: "/uploadMethod",
			templateUrl: "views/uploadMethod.html",
			controller: "uploadMethod",
			data: {
				title: "司法局"
			}
		})
		.state("uFileUpload", {
			url: "/uFileUpload",
			templateUrl: "views/uFileUpload.html",
			controller: "uFileUpload",
			data: {
				title: "司法局"
			}
		})
		.state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialList",
			data: {
				title: "司法局"
			}
		})
		.state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialView",
			data: {
				title: "司法局"
			}
		}).state("takePhoto", {
			url: "/takePhoto",
			templateUrl: "views/takePhoto.html",
			controller: "takePhoto",
			data: {
				title: "司法局"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "司法局"
			}
		})
	/*idcard router end*/
})