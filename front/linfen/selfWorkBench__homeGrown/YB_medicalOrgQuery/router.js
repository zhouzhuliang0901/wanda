app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/main");

	$stateProvider
		.state("main", {
			url: "/main",
			templateUrl: "views/main.html",
			controller: "main",
			data: {
				title: "医保"
			}
		})
		.state("handleLoginType", {
			url: "/handleLoginType",
			templateUrl: "views/handleLoginType.html",
			controller: "handleLoginType",
			data: {
				title: "医保个人信息查询"
			}
		})
		.state("treatmentDetails", {
			url: "/treatmentDetails",
			templateUrl: "views/treatmentDetails.html",
			controller: "treatmentDetails",
			data: {
				title: "医保个人信息查询"
			}
		})
		.state("handleLoginTypeManual", {
			url: "/handleLoginTypeManual",
			templateUrl: "views/handleLoginTypeManual.html",
			controller: "handleLoginTypeManual",
			data: {
				title: "医保个人信息查询"
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
		.state("loginTypeBookMaking", {
			url: "/loginTypeBookMaking",
			templateUrl: "views/loginType.html",
			controller: "loginTypeBookMaking",
			data: {
				title: "办理医保就医记录册"
			}
		})
		.state("loginType1", {
			url: "/loginType1",
			templateUrl: "views/loginType.html",
			controller: "loginType1",
			data: {
				title: "医保综合减负试算"
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
		.state("swipeAgentIdcard", {
			url: "/swipeAgentIdcard",
			templateUrl: "views/swipeAgentIdcard.html",
			controller: "swipeAgentIdcard",
			data: {
				title: "医保"
			}
		})
		.state("queryFace", {
			url: "/queryFace",
			templateUrl: "views/queryFace.html",
			controller: "queryFace",
			data: {
				title: "医保"
			}
		})
		.state("iframe", {
			url: "/iframe",
			templateUrl: "views/iframe.html",
			controller: "iframe",
			data: {
				title: "医保"
			}
		})
		.state("infoChoose", {
			url: "/infoChoose",
			templateUrl: "views/infoChoose.html",
			controller: "infoChoose",
			data: {
				title: "医保"
			}
		})
		.state("bookMaking", {
			url: "/bookMaking",
			templateUrl: "views/bookMaking.html",
			controller: "bookMaking",
			data: {
				title: "办理医保就医记录册"
			}
		})
		.state("idcardOrCitizen", {
			url: "/idcardOrCitizen",
			templateUrl: "views/idcardOrCitizen.html",
			controller: "idcardOrCitizen",
			data: {
				title: "办理医保就医记录册"
			}
		})
		.state("bookMakingChoose", {
			url: "/bookMakingChoose",
			templateUrl: "views/bookMakingChoose.html",
			controller: "bookMakingChoose",
			data: {
				title: "办理医保就医记录册"
			}
		})
		.state("bookMakingReason", {
			url: "/bookMakingReason",
			templateUrl: "views/bookMakingReason.html",
			controller: "bookMakingReason",
			data: {
				title: "办理医保就医记录册"
			}
		})
		.state("bookMakingTakePhoto", {
			url: "/bookMakingTakePhoto",
			templateUrl: "views/bookMakingTakePhoto.html",
			controller: "bookMakingTakePhoto",
			data: {
				title: "办理医保就医记录册"
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
		.state("reduceChoose", {
			url: "/reduceChoose",
			templateUrl: "views/reduceChoose.html",
			controller: "reduceChoose",
			data: {
				title: "医保"
			}
		})
		.state("reduce", {
			url: "/reduce",
			templateUrl: "views/reduce.html",
			controller: "reduce",
			data: {
				title: "医保"
			}
		})
		.state("medicalDetails", {
			url: "/medicalDetails",
			templateUrl: "views/medicalDetails.html",
			controller: "medicalDetails",
			data: {
				title: "医保"
			}
		})
		.state("accountSettlement", {
			url: "/accountSettlement",
			templateUrl: "views/accountSettlement.html",
			controller: "accountSettlement",
			data: {
				title: "医保"
			}
		})
		.state("handleProgressQuery", {
			url: "/handleProgressQuery",
			templateUrl: "views/handleProgressQuery.html",
			controller: "handleProgressQuery",
			data: {
				title: "医保"
			}
		})
		.state("servicesList", {
			url: "/servicesList",
			templateUrl: "views/servicesList.html",
			controller: "servicesList",
			data: {
				title: "医保"
			}
		})
		.state("ybzlydfwChoose", {
			url: "/ybzlydfwChoose",
			templateUrl: "views/ybzlydfwChoose.html",
			controller: "ybzlydfwChoose",
			data: {
				title: "医保"
			}
		})
		.state("ybzlydfwQueryList", {
			url: "/ybzlydfwQueryList",
			templateUrl: "views/ybzlydfwQueryList.html",
			controller: "ybzlydfwQueryList",
			data: {
				title: "医保"
			}
		})
		.state("chxhlChoose", {
			url: "/chxhlChoose",
			templateUrl: "views/chxhlChoose.html",
			controller: "chxhlChoose",
			data: {
				title: "医保"
			}
		})
		.state("chxpgChoose", {
			url: "/chxpgChoose",
			templateUrl: "views/chxpgChoose.html",
			controller: "chxpgChoose",
			data: {
				title: "医保"
			}
		})
		.state("chxQueryList", {
			url: "/chxQueryList",
			templateUrl: "views/chxQueryList.html",
			controller: "chxQueryList",
			data: {
				title: "医保"
			}
		})
		.state("sybxjgwdChoose", {
			url: "/sybxjgwdChoose",
			templateUrl: "views/sybxjgwdChoose.html",
			controller: "sybxjgwdChoose",
			data: {
				title: "医保"
			}
		})
		.state("sybxjgwdQueryList", {
			url: "/sybxjgwdQueryList",
			templateUrl: "views/sybxjgwdQueryList.html",
			controller: "sybxjgwdQueryList",
			data: {
				title: "医保"
			}
		})
		.state("bskzydjyChoose", {
			url: "/bskzydjyChoose",
			templateUrl: "views/bskzydjyChoose.html",
			controller: "bskzydjyChoose",
			data: {
				title: "医保"
			}
		})
		.state("bskzydjyQueryList", {
			url: "/bskzydjyQueryList",
			templateUrl: "views/bskzydjyQueryList.html",
			controller: "bskzydjyQueryList",
			data: {
				title: "医保"
			}
		})
		.state("ybxgfwChoose", {
			url: "/ybxgfwChoose",
			templateUrl: "views/ybxgfwChoose.html",
			controller: "ybxgfwChoose",
			data: {
				title: "医保"
			}
		})
		.state("ybxgfwQueryList", {
			url: "/ybxgfwQueryList",
			templateUrl: "views/ybxgfwQueryList.html",
			controller: "ybxgfwQueryList",
			data: {
				title: "医保"
			}
		})
		.state("yljgChoose", {
			url: "/yljgChoose",
			templateUrl: "views/yljgChoose.html",
			controller: "yljgChoose",
			data: {
				title: "医保"
			}
		})
		.state("yljgQueryList", {
			url: "/yljgQueryList",
			templateUrl: "views/yljgQueryList.html",
			controller: "yljgQueryList",
			data: {
				title: "医保"
			}
		})
	/*idcard router end*/
})