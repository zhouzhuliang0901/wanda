//app.config(['$routeProvider',function($routeProvider){
//  $routeProvider 
//      .when('/list',{templateUrl:'views/list.html', controller:'listController'})
//		.when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
//		.when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
//		.when('/precautions',{templateUrl:'views/precautions.html', controller:'precautionsController'})
//		.when('/apply',{templateUrl:'views/apply.html', controller:'applyController'})
//		.when('/select',{templateUrl:'views/select.html', controller:'selectController'})
//		.when('/idCard',{templateUrl:'views/idCard.html', controller:'idCardController'})
//		.when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
//		.when('/photo',{templateUrl:'views/photo.html', controller:'photoController'})
//		.when('/info',{templateUrl:'views/info.html', controller:'infoController'})
//		.when('/materialUpload',{templateUrl:'views/materialUpload.html', controller:'materialUploadController'})
//		.when('/materialList',{templateUrl:'views/materialList.html', controller:'materialListController'})
//		.when('/materialView',{templateUrl:'views/materialView.html', controller:'materialViewController'})
//		.when('/uploadMethod',{templateUrl:'views/uploadMethod.html', controller:'uploadMethodController'})
//		.when('/materialPic',{templateUrl:'views/materialPic.html', controller:'materialPicController'})
//		.when('/takePhoto/:flag',{templateUrl:'views/takePhoto.html', controller:'takePhotoController'})
//		.when('/finish',{templateUrl:'views/finish.html', controller:'finishController'})
//		.when('/infoFinish',{templateUrl:'views/infoFinish.html', controller:'infoFinishController'})
//		 .when('/qrCode',{templateUrl:'views/qrCode.html', controller:'qrCodeController'})
//		  .when('/history',{templateUrl:'views/history.html', controller:'historyController'})
//		  .when('/materialPic1',{templateUrl:'views/materialPic1.html', controller:'materialPic1Controller'})
//		.otherwise({
//			redirectTo: '/list'
//		})
//}]);
app.config(function($urlRouterProvider, $stateProvider) {
	/*elicense router start*/
	$urlRouterProvider.otherwise("/list");
	$stateProvider.state("list", {
		url: "/list",
		templateUrl: "views/list.html",
		controller: "listController",
		data: {
			title: "???????????????"
		}
	})
	.state("select", {
		url: "/select",
		templateUrl: "views/select.html",
		controller: "selectController",
		data: {
			title: "???????????????"
		}
	})
	.state("idCard", {
		url: "/idCard",
		templateUrl: "views/idCard.html",
		controller: "idCardController",
		data: {
			title: "???????????????"
		}
	})
	.state("info", {
		url: "/info",
		templateUrl: "views/info.html",
		controller: "infoController",
		data: {
			title: "???????????????"
		}
	})
	.state("materialList", {
		url: "/materialList",
		templateUrl: "views/materialList.html",
		controller: "materialListController",
		data: {
			title: "???????????????"
		}
	})
	.state("uploadMethod", {
		url: "/uploadMethod",
		templateUrl: "views/uploadMethod.html",
		controller: "uploadMethodController",
		data: {
			title: "???????????????"
		}
	})
	.state("finish", {
		url: "/finish",
		templateUrl: "views/finish.html",
		controller: "finishController",
		data: {
			title: "???????????????"
		}
	})
	.state("takePhoto", {
		url: "/takePhoto/:flag",
		templateUrl: "views/takePhoto.html",
		controller: "takePhotoController",
		data: {
			title: "???????????????"
		}
	})
	.state("materialPic", {
		url: "/materialPic",
		templateUrl: "views/materialPic.html",
		controller: "materialPicController",
		data: {
			title: "???????????????"
		}
	})
	.state("infoFinish", {
		url: "/infoFinish",
		templateUrl: "views/infoFinish.html",
		controller: "infoFinishController",
		data: {
			title: "???????????????"
		}
	})
	.state("materialView", {
		url: "/materialView",
		templateUrl: "views/materialView.html",
		controller: "materialViewController",
		data: {
			title: "???????????????"
		}
	})
	.state("citizen", {
		url: "/citizen",
		templateUrl: "views/citizen.html",
		controller: "citizenController",
		data: {
			title: "???????????????"
		}
	})
})
