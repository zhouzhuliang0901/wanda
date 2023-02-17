app.config(function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise("/main");
	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "main"
			}
		})
		.state("RS_List", { //人设事项列表
			url: "/RS_List",
			templateUrl: "views/RS_List.html",
			controller: "RS_List",
			data: {
				title: "RS_List"
			}
		})
		.state("RSListFlexibleEmployment", {
			url: "/RSListFlexibleEmployment",
			templateUrl: "views/RS_List_flexibleEmployment.html",
			controller: "RSListFlexibleEmployment",
			data: {
				title: "RSListFlexibleEmployment"
			}
		})
		.state("JTW_List", {
			url: "/JTW_List",
			templateUrl: "views/JTW_List.html",
			controller: "JTW_List",
			data: {
				title: "JTW_List"
			}
		})
		.state("YB_List", {
			url: "/YB_List",
			templateUrl: "views/JTW_List.html",
			controller: "YB_List",
			data: {
				title: "YB_List"
			}
		})
		.state("MZ_List", {
			url: "/MZ_List",
			templateUrl: "views/MZ_List.html",
			controller: "MZ_List",
			data: {
				title: "MZ_List"
			}
		})
		.state("GA_List", {
			url: "/GA_List",
			templateUrl: "views/GA_List.html",
			controller: "GA_List",
			data: {
				title: "GA_List"
			}
		})
		.state("CL_List", {
			url: "/CL_List",
			templateUrl: "views/CL_List.html",
			controller: "CL_List",
			data: {
				title: "CL_List"
			}
		})
		.state("JW_List", {
			url: "/JW_List",
			templateUrl: "views/JW_List.html",
			controller: "JW_List",
			data: {
				title: "JW_List"
			}
		})
		.state("SCJG_List", {
			url: "/SCJG_List",
			templateUrl: "views/SCJG_List.html",
			controller: "SCJG_List",
			data: {
				title: "SCJG_List"
			}
		})
		.state("ZJ_List", {
			url: "/ZJ_List",
			templateUrl: "views/ZJ_List.html",
			controller: "ZJ_List",
			data: {
				title: "ZJ_List"
			}
		})
		.state("DA_List", {
			url: "/DA_List",
			templateUrl: "views/DA_List.html",
			controller: "DA_List",
			data: {
				title: "DA_List"
			}
		})
		.state("FGW_List", {
			url: "/FGW_List",
			templateUrl: "views/FGW_List.html",
			controller: "FGW_List",
			data: {
				title: "FGW_List"
			}
		})
		.state("GH_List", {
			url: "/GH_List",
			templateUrl: "views/GH_List.html",
			controller: "GH_List",
			data: {
				title: "GH_List"
			}
		})
		.state("NW_List", {
			url: "/NW_List",
			templateUrl: "views/NW_List.html",
			controller: "NW_List",
			data: {
				title: "NW_List"
			}
		})
		.state("SWJ_List", {
			url: "/SWJ_List",
			templateUrl: "views/SWJ_List.html",
			controller: "SWJ_List",
			data: {
				title: "SWJ_List"
			}
		})
		.state("WJW_List", {
			url: "/WJW_List",
			templateUrl: "views/WJW_List.html",
			controller: "WJW_List",
			data: {
				title: "WJW_List"
			}
		})
		.state("YJGL_List", {
			url: "/YJGL_List",
			templateUrl: "views/YJGL_List.html",
			controller: "YJGL_List",
			data: {
				title: "YJGL_List"
			}
		}).state("FGJ_List", {
			url: "/FGJ_List",
			templateUrl: "views/FGJ_List.html",
			controller: "FGJ_List",
			data: {
				title: "FGJ_List"
			}
		})
		.state("QLR_List", {
			url: "/QLR_List",
			templateUrl: "views/QLR_List.html",
			controller: "QLR_List",
			data: {
				title: "QLR_List"
			}
		})
		.state("SKYL_List", {
			url: "/SKYL_List",
			templateUrl: "views/SKYL_List.html",
			controller: "SKYL_List",
			data: {
				title: "SKYL_List"
			}
		})
		.state("SWW_List", {
			url: "/SWW_List",
			templateUrl: "views/SWW_List.html",
			controller: "SWW_List",
			data: {
				title: "SWW_List"
			}
		})
		.state("SF_List", {
			url: "/SF_List",
			templateUrl: "views/SF_List.html",
			controller: "SF_List",
			data: {
				title: "SF_List"
			}
		})
		.state("LS_List", {
			url: "/LS_List",
			templateUrl: "views/LS_List.html",
			controller: "LS_List",
			data: {
				title: "LS_List"
			}
		})
		.state("JXW_List", {
			url: "/JXW_List",
			templateUrl: "views/JXW_List.html",
			controller: "JXW_List",
			data: {
				title: "JXW_List"
			}
		})
		.state("test_List", {
			url: "/test_List",
			templateUrl: "views/test_List.html",
			controller: "test_List",
			data: {
				title: "test_List"
			}
		})
})