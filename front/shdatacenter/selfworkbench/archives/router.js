app.config(function($urlRouterProvider, $stateProvider) {
	/*earchives router start*/
	$urlRouterProvider.otherwise("/main");
	$stateProvider

		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "archivesMain",
			data: {
				title: "档案查询"
			}
		})
		.state("loginType", {
			url: "/loginType",
			templateUrl: "views/loginType.html",
			controller: "archivesLoginType",
			data: {
				title: "档案查询"
			}
		})
		.state("IdCardType", {
			url: "/IdCardType",
			templateUrl: "views/IdCardType.html",
			controller: "IdCardType",
			data: {
				title: "档案查询"
			}
		})
		.state("login", {
			url: "/login",
			templateUrl: "views/login.html",
			controller: "archivesLogin",
			data: {
				title: "档案查询"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guideline",
			data: {
				title: "档案查询"
			}
		})
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "档案查询"
			}
		})
		.state("infoMarry", {
			url: "/infoMarry",
			templateUrl: "views/infoMarry.html",
			controller: "infoMarry",
			data: {
				title: "档案查询"
			}
		})
		.state("infoOnlyChild", {
			url: "/infoOnlyChild",
			templateUrl: "views/infoOnlyChild.html",
			controller: "infoOnlyChild",
			data: {
				title: "档案查询"
			}
		})
		.state("infoOnlyChildForSon", {
			url: "/infoOnlyChildForSon",
			templateUrl: "views/infoOnlyChildForSon.html",
			controller: "infoOnlyChildForSon",
			data: {
				title: "档案查询"
			}
		})
		.state("infoChildrenOfEducatedYouthToHousehold", {
			url: "/infoChildrenOfEducatedYouthToHousehold",
			templateUrl: "views/infoChildrenOfEducatedYouthToHousehold.html",
			controller: "infoChildrenOfEducatedYouthToHousehold",
			data: {
				title: "档案查询"
			}
		})
		.state("infoEducatedYouthToMountainAndCountryside", {
			url: "/infoEducatedYouthToMountainAndCountryside",
			templateUrl: "views/infoEducatedYouthToMountainAndCountryside.html",
			controller: "infoEducatedYouthToMountainAndCountryside",
			data: {
				title: "档案查询"
			}
		})
		.state("infoEducatedYouthToCity", {
			url: "/infoEducatedYouthToCity",
			templateUrl: "views/infoEducatedYouthToCity.html",
			controller: "infoEducatedYouthToCity",
			data: {
				title: "档案查询"
			}
		})
		.state("infoReproductionOfChildren", {
			url: "/infoReproductionOfChildren",
			templateUrl: "views/infoReproductionOfChildren.html",
			controller: "infoReproductionOfChildren",
			data: {
				title: "档案查询"
			}
		})
		.state("infoWorkRelatedInjury", {
			url: "/infoWorkRelatedInjury",
			templateUrl: "views/infoWorkRelatedInjury.html",
			controller: "infoWorkRelatedInjury",
			data: {
				title: "档案查询"
			}
		})
		.state("infoStudentStatus", {
			url: "/infoStudentStatus",
			templateUrl: "views/infoStudentStatus.html",
			controller: "infoStudentStatus",
			data: {
				title: "档案查询"
			}
		})
		.state("infoVeteran", {
			url: "/infoVeteran",
			templateUrl: "views/infoVeteran.html",
			controller: "infoVeteran",
			data: {
				title: "档案查询"
			}
		})
		.state("infoMilitaryService", {
			url: "/infoMilitaryService",
			templateUrl: "views/infoMilitaryService.html",
			controller: "infoMilitaryService",
			data: {
				title: "档案查询"
			}
		})
		.state("infoTalentIntroduction", {
			url: "/infoTalentIntroduction",
			templateUrl: "views/infoTalentIntroduction.html",
			controller: "infoTalentIntroduction",
			data: {
				title: "档案查询"
			}
		})
		.state("infoThreeGorgesMigrants", {
			url: "/infoThreeGorgesMigrants",
			templateUrl: "views/infoThreeGorgesMigrants.html",
			controller: "infoThreeGorgesMigrants",
			data: {
				title: "档案查询"
			}
		})
		.state("infoForeignMarriage", {
			url: "/infoForeignMarriage",
			templateUrl: "views/infoForeignMarriage.html",
			controller: "infoForeignMarriage",
			data: {
				title: "档案查询"
			}
		})
		.state("infoTalentIntroductionToCity", {
			url: "/infoTalentIntroductionToCity",
			templateUrl: "views/infoTalentIntroductionToCity.html",
			controller: "infoTalentIntroductionToCity",
			data: {
				title: "档案查询"
			}
		})
		.state("infoCountrysideMoveToCity", {
			url: "/infoCountrysideMoveToCity",
			templateUrl: "views/infoCountrysideMoveToCity.html",
			controller: "infoCountrysideMoveToCity",
			data: {
				title: "档案查询"
			}
		})
		.state("submit", {
			url: "/submit",
			templateUrl: "views/submit.html",
			controller: "submit",
			data: {
				title: "档案查询"
			}
		})
		.state("preview", {
			url: "/preview",
			templateUrl: "views/preview.html",
			controller: "preview",
			data: {
				title: "档案查询"
			}
		})
})