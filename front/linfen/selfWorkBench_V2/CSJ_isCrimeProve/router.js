app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/choiceMode");
	$stateProvider
	.state("choiceMode", {
			url: "/choiceMode",
			templateUrl: "views/choiceMode.html",
			controller: "choiceMode",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("choiceProvince", {  //选择省市
			url: "/choiceProvince",
			templateUrl: "views/choiceProvince.html",
			controller: "choiceProvince",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("infoJS", {  //江苏省 信息填写页
			url: "/infoJS",
			templateUrl: "views/infoJS.html",
			controller: "infoJS",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("infoZJ", {  //江苏省 信息填写页
			url: "/infoZJ",
			templateUrl: "views/infoZJ.html",
			controller: "infoZJ",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("infoAH", {  //江苏省 信息填写页
			url: "/infoAH",
			templateUrl: "views/infoAH.html",
			controller: "infoAH",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialList",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("uploadMethod", {
			url: "/uploadMethod",
			templateUrl: "views/uploadMethod.html",
			controller: "uploadMethod",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("materialPic", {
			url: "/materialPic",
			templateUrl: "views/materialPic.html",
			controller: "materialPic",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialView",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("takePhoto", {
			url: "/takePhoto",
			templateUrl: "views/takePhoto.html",
			controller: "takePhoto",
			data: {
				title: "户籍证明开具"
			}
		})
		.state("submit", {  //江苏省 信息填写页
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "户籍证明开具"
			}
		})
})