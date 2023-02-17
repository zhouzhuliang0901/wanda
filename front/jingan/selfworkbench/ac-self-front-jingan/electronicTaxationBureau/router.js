app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "searchMain",
			data: {
				title: "电子税务局"
			}
		})
		.state("handle_person", {
			url: "/handle_person",
			templateUrl: "views/handle_person.html",
			controller: "handle_person",
			data: {
				title: "电子税务局"
			}
		})
		.state("func_introduct", {
			url: "/func_introduct",
			templateUrl: "views/func_introduct.html",
			controller: "func_introduct",
			data: {
				title: "电子税务局"
			}
		})
		.state("operate_rules", {
			url: "/operate_rules",
			templateUrl: "views/operate_rules.html",
			controller: "operate_rules",
			data: {
				title: "电子税务局"
			}
		})
		.state("handle_legal", {
			url: "/handle_legal",
			templateUrl: "views/handle_legal.html",
			controller: "handle_legal",
			data: {
				title: "电子税务局"
			}
		})

	/*idcard router end*/
})