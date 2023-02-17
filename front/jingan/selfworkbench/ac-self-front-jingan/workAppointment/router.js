app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "appointmentMain",
			data: {
				title: "自助预约"
			}
		})
		.state("matter", {
			url: "/matter",
			templateUrl: "views/matter.html",
			controller: "appointmentMatter",
			data: {
				title: "自助预约"
			}
		})
		.state("date", {
			url: "/date",
			templateUrl: "views/date.html",
			controller: "appointmentDate",
			data: {
				title: "预约时间"
			}
		})
		.state("authentication", {
			url: "/authentication",
			templateUrl: "views/authentication.html",
			controller: "appointmentAuthentication",
			data: {
				title: "办事预约"
			}
		})
		.state("input", {
			url: "/input",
			templateUrl: "views/input.html",
			controller: "appointmentInput",
			data: {
				title: "办事预约"
			}
		})
		.state("complete", {
			url: "/complete",
			templateUrl: "views/complete.html",
			controller: "appointmentComplete",
			data: {
				title: "预约完成"
			}
		})
	/*idcard router end*/
})