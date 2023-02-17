app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/guideline");
	$stateProvider
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("authorizationCommitments", {
			url: "/authorizationCommitments",
			templateUrl: "views/authorizationCommitments.html",
			controller: "authorizationCommitments",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("info", {  //信息填写页
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("houseInfo", {  //信息填写页
			url: "/houseInfo",
			templateUrl: "views/houseInfo.html",
			controller: "houseInfo",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("bankInfo", {  //信息填写页
			url: "/bankInfo",
			templateUrl: "views/bankInfo.html",
			controller: "bankInfo",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("amountInfo", {  //信息填写页
			url: "/amountInfo",
			templateUrl: "views/amountInfo.html",
			controller: "amountInfo",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialList",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("uploadMethod", {
			url: "/uploadMethod",
			templateUrl: "views/uploadMethod.html",
			controller: "uploadMethod",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("materialPic", {
			url: "/materialPic",
			templateUrl: "views/materialPic.html",
			controller: "materialPic",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialView",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("takePhoto", {
			url: "/takePhoto",
			templateUrl: "views/takePhoto.html",
			controller: "takePhoto",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
		.state("submit", {  //江苏省 信息填写页
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "长三角购房提取住房公积金"
			}
		})
})