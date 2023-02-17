
app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/start");
	
	$stateProvider
	.state("start", {
			url: "/start",
			templateUrl: "views/start.html",
			controller: "startController",
			data: {
				title: "自助办理"
			}
		})
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "mainController",
			data: {
				title: "自助办理"
			}
		})
		.state("list", {
			url: "/list",
			templateUrl: "views/list.html",
			controller: "listController",
			data: {
				title: "自助办理"
			}
		})
		.state("guide", {
			url: "/guide",
			templateUrl: "views/guide.html",
			controller: "guideController",
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
		.state("citizen", {
			url: "/citizen",
			templateUrl: "views/citizen.html",
			controller: "citizenController",
			data: {
				title: "自助办理"
			}
		})
		
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "infoController",
			data: {
				title: "自助办理"
			}
		})
		.state("information", {
			url: "/information",
			templateUrl: "views/information.html",
			controller: "informationController",
			data: {
				title: "自助办理"
			}
		})
		
		
		.state("table", {
			url: "/table",
			templateUrl: "views/table.html",
			controller: "tableController",
			data: {
				title: "自助办理"
			}
		})
		.state("confirm", {
			url: "/confirm",
			templateUrl: "views/confirm.html",
			controller: "confirmController",
			data: {
				title: "自助办理"
			}
		})
		.state("waitting", {
			url: "/waitting",
			templateUrl: "views/waitting.html",
			controller: "waittingController",
			data: {
				title: "自助办理"
			}
		})
		.state("verify", {
			url: "/verify",
			templateUrl: "views/verify.html",
			controller: "verifyController",
			data: {
				title: "自助办理"
			}
		})
		.state("signatureJybt", {
			url: "/signatureJybt",
			templateUrl: "views/signatureJybt.html",
			controller: "signatureJybtController",
			data: {
				title: "自助办理"
			}
		})
		.state("confirmapply", {
			url: "/confirmapply",
			templateUrl: "views/confirmapply.html",
			controller: "confirmapplyController",
			data: {
				title: "自助办理"
			}
		})
		.state("sign", {
			url: "/sign",
			templateUrl: "views/sign.html",
			controller: "signController",
			data: {
				title: "自助办理"
			}
		})
		.state("infoFinish", {
			url: "/infoFinish",
			templateUrl: "views/infoFinish.html",
			controller: "infoFinishController",
			data: {
				title: "自助办理"
			}
		})
		
})