app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/index");

	$stateProvider
		.state("index", {
			url: "/index",
			templateUrl: "views/index.html",
			controller: "index",
			data: {
				title: "index"
			}
		})
		.state("indexhandle", {
			url: "/indexhandle",
			templateUrl: "views/indexhandle.html",
			controller: "indexhandle",
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
		.state("agentloginType", {
			url: "/agentloginType",
			templateUrl: "views/agentloginType.html",
			controller: "agentloginType",
			data: {
				title: ""
			}
		})
		.state("agentlogin", {
			url: "/agentlogin",
			templateUrl: "views/login.html",
			controller: "agentlogin",
			data: {
				title: ""
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: ""
			}
		})
		.state("takePhoto", {
			url: "/takePhoto",
			templateUrl: "views/takePhoto.html",
			controller: "takePhoto",
			data: {
				title: ""
			}
		}).state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialList",
			data: {
				title: ""
			}
		}).state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialView",
			data: {
				title: "材料显示"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: ""
			}
		})
		.state("appointmentQuery", {
			url: "/appointmentQuery",
			templateUrl: "views/appointmentQuery.html",
			controller: "appointmentQuery",
			data: {
				title: "预约查询"
			}
		})
		
	/*idcard router end*/
})
