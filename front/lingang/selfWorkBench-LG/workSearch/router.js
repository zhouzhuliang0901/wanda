app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "searchMain",
			data: {
				title: "进度查询"
			}
		})
		.state("input", {
			url: "/input",
			templateUrl: "views/input.html",
			controller: "searchInput",
			data: {
				title: "进度查询"
			}
		})
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "searchList",
			data: {
				title: "办件列表"
			}
		})
		.state("detail", {
			url: "/detail",
			templateUrl: "views/detail.html",
			controller: "searchDetail",
			data: {
				title: "办件信息"
			},
			params:{
				prevRoute:null
			}
		})
		.state("detailForOther", {
			url: "/detailForOther",
			templateUrl: "views/detailForOther.html",
			controller: "detailForOther",
			data: {
				title: "办件信息"
			},
		})

	/*idcard router end*/
})