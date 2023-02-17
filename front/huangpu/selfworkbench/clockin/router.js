app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/loginType");
	$stateProvider
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "工作人员打卡"
			}
		})
		.state("idcardLogin", {
			url: "/idcardLogin",
			templateUrl: "views/idcardLogin.html",
			controller: "idcardLogin",
			data: {
				title: "工作人员打卡"
			}
		})
		.state("accountLogin",{
			url:"/accountLogin",
			templateUrl:"views/accountLogin.html",
			controller:"accountLogin",
			data:{
				title:"工作人员打卡"
			}
		})
		.state("signIn",{
			url:"/sighIn",
			templateUrl:"views/sighIn.html",
			controller:"signIn",
			data:{
				title:"工作人员打卡"
			}
		})
		.state("signOut",{
			url:"/signout",
			templateUrl:"views/signOut.html",
			controller:"signOut",
			data:{
				title:"工作人员打卡"
			}
		})
})