app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/firstMenu");
	$stateProvider
		.state("firstMenu", {
			url: "/firstMenu",
			templateUrl: "views/firstMenu.html",
			controller: "firstMenu",
			data: {
				title: "政府公开信息"
			}
		})
		.state("secondMenu", {
            url: "/secondMenu",
            templateUrl: "views/secondMenu.html",
            controller: "secondMenu",
            data: {
                title: "政府公开信息"
            }
        })
		.state("info", {
			url: "/info",
			templateUrl: "views/info.html",
			controller: "info",
			data: {
				title: "政府公开信息"
			}
		})
		.state("choice", {
			url: "/choice",
			templateUrl: "views/choice.html",
			controller: "choice",
			data: {
				title: "政府公开信息"
			}
		})
		.state("leaderPhotoes", {
            url: "/leaderPhotoes",
            templateUrl: "views/neirong/leader/photoes.html",
            controller: "leaderPhotoes",
            data: {
                title: "政府公开信息"
            }
        })
		.state("leaderInfo", {
            url: "/leaderInfo",
            templateUrl: "views/neirong/leader/info.html",
            controller: "leaderInfo",
            data: {
                title: "政府公开信息"
            }
        })
		.state("workOrganization", {
            url: "/workOrganization",
            templateUrl: "views/neirong/workOrganization/info.html",
            controller: "workOrganization",
            data: {
                title: "政府公开信息"
            }
        })
		.state("planInfo", {
            url: "/planInfo",
            templateUrl: "views/neirong/planInfo/info.html",
            controller: "planInfo",
            data: {
                title: "政府公开信息"
            }
        })
		.state("budgetIframe", {
            url: "/budgetIframe",
            templateUrl: "views/neirong/budget/iframe.html",
            controller: "budgetIframe",
            data: {
                title: "政府公开信息"
            }
        })
		.state("budgetChoice", {
            url: "/budgetChoice",
            templateUrl: "views/neirong/budget/choice.html",
            controller: "budgetChoice",
            data: {
                title: "政府公开信息"
            }
        })
		.state("chargeList", {
            url: "/chargeList",
            templateUrl: "views/neirong/chargeList/table.html",
            controller: "chargeList",
            data: {
                title: "政府公开信息"
            }
        })
		.state("workReport", {
            url: "/workReport",
            templateUrl: "views/neirong/workReport/choice.html",
            controller: "workReport",
            data: {
                title: "政府公开信息"
            }
        })
		.state("workReportInfo", {
            url: "/workReportInfo",
            templateUrl: "views/neirong/workReport/info.html",
            controller: "workReportInfo",
            data: {
                title: "政府公开信息"
            }
        })
		.state("publicService", {
            url: "/publicService",
            templateUrl: "views/neirong/publicService/table.html",
            controller: "publicService",
            data: {
                title: "政府公开信息"
            }
        })
		.state("noticeChoice", {
            url: "/noticeChoice",
            templateUrl: "views/governmentNotice/choice.html",
            controller: "noticeChoice",
            data: {
                title: "政府公开信息"
            }
        })
})