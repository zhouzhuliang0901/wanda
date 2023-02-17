app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
        .when('/itemlist',{templateUrl:'views/itemlist.html', controller:'itemlistController'})
        
        // 原来的guideline模块,修改为main模块
//		.when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
		.when('/main',{templateUrl:'views/main.html', controller:'mainController'})
		
		.when('/guidelineJybt',{templateUrl:'views/guidelineJybt.html', controller:'guidelineJybtController'})
		.when('/guidelineJygx',{templateUrl:'views/guidelineJygx.html', controller:'guidelineJygxController'})
		.when('/guidelineSybx',{templateUrl:'views/guidelineSybx.html', controller:'guidelineSybxController'})
		.when('/guidelineHjdjsl',{templateUrl:'views/guidelineHjdjsl.html', controller:'guidelineHjdjslController'})
		.when('/select',{templateUrl:'views/select.html', controller:'selectController'})
		.when('/idCard',{templateUrl:'views/idCard.html', controller:'idCardController'})
		.when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
		.when('/queryFace',{templateUrl:'views/queryFace.html', controller:'queryFaceController'})
		.when('/iframe',{templateUrl:'views/iframe.html', controller:'iframeController'})
		.when('/info',{templateUrl:'views/info.html', controller:'infoController'})
		.when('/infoJybt',{templateUrl:'views/infoJybt.html', controller:'infoJybtController'})
		.when('/infoJygx',{templateUrl:'views/infoJygx.html', controller:'infoJygxController'})
		.when('/infoSybx',{templateUrl:'views/infoSybx.html', controller:'infoSybxController'})
		.when('/infoHjdjsl',{templateUrl:'views/infoHjdjsl.html', controller:'infoHjdjslController'})
		.when('/materialList',{templateUrl:'views/materialList.html', controller:'materialListController'})
		.when('/materialJygxList',{templateUrl:'views/materialJygxList.html', controller:'materialJygxListController'})
		.when('/materialJybtList',{templateUrl:'views/materialJybtList.html', controller:'materialJybtListController'})
		.when('/materialSybxList',{templateUrl:'views/materialSybxList.html', controller:'materialSybxListController'})
		.when('/materialHjdjslList',{templateUrl:'views/materialHjdjslList.html', controller:'materialHjdjslListController'})
		.when('/autoForm',{templateUrl:'views/autoForm.html', controller:'autoFormController'})
		.when('/sound',{templateUrl:'views/sound.html', controller:'soundController'})
		.when('/signatureSybx',{templateUrl:'views/signatureSybx.html', controller:'signatureSybxController'})
		.when('/signature',{templateUrl:'views/signature.html', controller:'signatureController'})
		.when('/signatureJybt',{templateUrl:'views/signatureJybt.html', controller:'signatureJybtController'})
		.when('/materialView',{templateUrl:'views/materialView.html', controller:'materialViewController'})
		.when('/uploadMethod',{templateUrl:'views/uploadMethod.html', controller:'uploadMethodController'})
		.when('/materialPic',{templateUrl:'views/materialPic.html', controller:'materialPicController'})
		.when('/takePhoto/:flag',{templateUrl:'views/takePhoto.html', controller:'takePhotoController'})
		.when('/finish',{templateUrl:'views/finish.html', controller:'finishController'})
		.when('/infoFinish',{templateUrl:'views/infoFinish.html', controller:'infoFinishController'})
		.when('/infoFinishJybt',{templateUrl:'views/infoFinishJybt.html', controller:'infoFinishJybtController'})
		.when('/qrCode',{templateUrl:'views/qrCode.html', controller:'qrCodeController'})
//		.otherwise({
//			redirectTo: '/list'
//		})
		.otherwise({
			redirectTo: '/main'
		})
}]);