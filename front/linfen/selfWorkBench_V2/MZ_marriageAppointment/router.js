app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");

	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "民政服务"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "民政服务"
			}
		})
		.state("marriageInfo", {
			url: "/marriageInfo",
			templateUrl: "views/marriageInfo.html",
			controller: "marriageInfo",
			data: {
				title: "民政服务"
			}
		})
		.state("divorceInfo", {
			url: "/divorceInfo",
			templateUrl: "views/divorceInfo.html",
			controller: "divorceInfo",
			data: {
				title: "民政服务"
			}
		})
		.state("divAppointmentTime", {
			url: "/divAppointmentTime",
			templateUrl: "views/divAppointmentTime.html",
			controller: "divAppointmentTime",
			data: {
				title: "民政服务"
			}
		})
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "list",
			data: {
				title: "民政服务"
			}
		})
		.state("selectList", {
			url: "/selectList",
			templateUrl: "views/selectList.html",
			controller: "selectList",
			data: {
				title: "民政服务"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "民政服务"
			}
		})
		.state("cancel", {
			url: "/cancel",
			templateUrl: "views/cancel.html",
			controller: "cancel",
			data: {
				title: "民政服务"
			}
		})
		.state("queryAppointment", {
			url: "/queryAppointment",
			templateUrl: "views/queryAppointment.html",
			controller: "queryAppointment",
			data: {
				title: "民政服务"
			}
		})
		.state("certification", {
			url: "/certification",
			templateUrl: "views/certification.html",
			controller: "certification",
			data: {
				title: "民政服务"
			}
		})
		.state("appointmentTime", {
			url: "/appointmentTime",
			templateUrl: "views/appointmentTime.html",
			controller: "appointmentTime",
			data: {
				title: "民政服务"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "民政服务"
			}
		})
		.state("submitInfo", {
			url: "/submitInfo",
			templateUrl: "views/submitInfo.html",
			controller: "submitInfo",
			data: {
				title: "民政服务"
			}
		})
		.state("divSubmitInfo", {
			url: "/divSubmitInfo",
			templateUrl: "views/divSubmitInfo.html",
			controller: "divSubmitInfo",
			data: {
				title: "民政服务"
			}
		})
		.state("applyInfo", {
			url: "/applyInfo",
			templateUrl: "views/applyInfo.html",
			controller: "applyInfo",
			data: {
				title: "民政服务"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "民政服务"
			}
		})
	/*idcard router end*/
})