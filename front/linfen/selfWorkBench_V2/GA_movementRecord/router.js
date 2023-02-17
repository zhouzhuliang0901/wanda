app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "居住证签注"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "医保"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "loginType",
			data: {
				title: "医保个人信息查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "login",
			data: {
				title: "医保"
			}
		})
		.state("choose", {
			url: "/choose",
			templateUrl: "views/choose.html",
			controller: "choose",
			data: {
				title: "医保"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "医保"
			}
		})
		.state("applyInfoHKM", {
			url: "/applyInfoHKM",
			templateUrl: "views/applyInfoHKM.html",
			controller: "applyInfoHKM",
			data: {
				title: "医保"
			}
		})
		.state("applyInfoTaiWan", {
			url: "/applyInfoTaiWan",
			templateUrl: "views/applyInfoTaiWan.html",
			controller: "applyInfoTaiWan",
			data: {
				title: "医保"
			}
		})
		.state("loginResidence", {
			url: "/loginResidence",
			templateUrl: "views/loginResidence.html",
			controller: "loginResidence",
			data: {
				title: "居住签证"
			}
		})
		.state("infoResidence", {
			url: "/infoResidence",
			templateUrl: "views/infoResidence.html",
			controller: "infoResidence",
			data: {
				title: "居住签证"
			}
		})
		.state("updateResidence", {
			url: "/updateResidence",
			templateUrl: "views/updateResidence.html",
			controller: "updateResidence",
			data: {
				title: "居住签证"
			}
		})
		.state("isCrimeChoose", {
			url: "/isCrimeChoose",
			templateUrl: "views/isCrimeChoose.html",
			controller: "isCrimeChoose",
			data: {
				title: "是否 违法犯罪"
			}
		})
		.state("exitAndEntryChoose", {
			url: "/exitAndEntryChoose",
			templateUrl: "views/exitAndEntryChoose.html",
			controller: "exitAndEntryChoose",
			data: {
				title: "处境入籍记录查询"
			}
		})
		.state("exitAndEntryList", {
			url: "/exitAndEntryList",
			templateUrl: "views/exitAndEntryList.html",
			controller: "exitAndEntryList",
			data: {
				title: "处境入籍记录查询"
			}
		})
		.state("exitAndEntryDetail", {
			url: "/exitAndEntryDetail",
			templateUrl: "views/exitAndEntryDetail.html",
			controller: "exitAndEntryDetail",
			data: {
				title: "处境入籍记录查询"
			}
		})
	/*idcard router end*/
})