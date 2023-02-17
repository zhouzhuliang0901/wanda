/*idcard router start*/
app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	
	 $stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "materialsCopyMain",
			data: {
				title: "材料复印"
			}
		})
	/*idcard router end*/
})