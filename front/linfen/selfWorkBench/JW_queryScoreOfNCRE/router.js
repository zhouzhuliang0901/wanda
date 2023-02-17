app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider

		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "上海市计算机等级考试成绩查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "上海市计算机等级考试成绩查询"
			}
		})
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "上海市计算机等级考试成绩查询"
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "preview",
			data: {
				title: "上海市计算机等级考试成绩查询"
			}
		})
})