app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/list");
	$stateProvider
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "listController",
			data: {
				title: "自助办理"
			}
		})
		.state("matter", {
			url: "/matter",
			templateUrl: "views/matter.html",
			controller: "matterController",
			data: {
				title: "自助办理"
			}
		})
		.state("guideline", {
			url: "/guideline",
			templateUrl: "views/guideline.html",
			controller: "guidelineController",
			data: {
				title: "自助办理"
			}
		})
		.state("select", {
			url: "/select",
			templateUrl: "views/select.html",
			controller: "selectController",
			data: {
				title: "自助办理"
			}
		})
		.state("idCard", {
			url: "/idCard",
			templateUrl: "views/idCard.html",
			controller: "idCardController",
			data: {
				title: "自助办理"
			}
		})
		.state("ca", {
			url: "/ca",
			templateUrl: "views/ca.html",
			controller: "caController",
			data: {
				title: "自助办理"
			}
		})
		.state("citizen", {
			url: "/citizen",
			templateUrl: "views/citizen.html",
			controller: "citizenController",
			data: {
				title: "自助办理"
			}
		}).state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "infoController",
			data: {
				title: "自助办理"
			}
		}).state("materialList", {
			url: "/materialList",
			templateUrl: "views/materialList.html",
			controller: "materialListController",
			data: {
				title: "自助办理"
			}
		}).state("materialView", {
			url: "/materialView",
			templateUrl: "views/materialView.html",
			controller: "materialViewController",
			data: {
				title: "自助办理"
			}
		}).state("uploadMethod", {
			url: "/uploadMethod",
			templateUrl: "views/uploadMethod.html",
			controller: "uploadMethodController",
			data: {
				title: "自助办理"
			}
		}).state("materialPic", {
			url: "/materialPic",
			templateUrl: "views/materialPic.html",
			controller: "materialPicController",
			data: {
				title: "自助办理"
			}
		}).state("finish", {
			url: "/finish",
			templateUrl: "views/finish.html",
			controller: "finishController",
			data: {
				title: "自助办理"
			}
		}).state("infoFinish", {
			url: "/infoFinish",
			templateUrl: "views/infoFinish.html",
			controller: "infoFinishController",
			data: {
				title: "自助办理"
			}
		})
})
