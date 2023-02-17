app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
        .when('/item',{templateUrl:'views/item.html', controller:'itemController'})
        .when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
        .when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
        .when('/precautions',{templateUrl:'views/precautions.html', controller:'preController'})
        .when('/apply',{templateUrl:'views/apply.html', controller:'applyController'})
//      .when('/select',{templateUrl:'views/select.html', controller:'selectController'})        
//      .when('/main',{templateUrl:'views/main.html', controller:'mainController'})
//      .when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
//	    .when('/capture',{templateUrl:'views/capture.html', controller:'captureController'})
        .when('/sign',{templateUrl:'views/sign.html', controller:'signController'})
        .when('/handleLocation',{templateUrl:'views/handleLocation.html', controller:'handLocationController'})
        .when('/info',{templateUrl:'views/info.html', controller:'infoController'})
        .when('/riverInfo1',{templateUrl:'views/riverInfo/info1.html', controller:'riverInfo1Controller'})
        .when('/riverInfo2',{templateUrl:'views/riverInfo/info2.html', controller:'riverInfo2Controller'})
        .when('/materialUpload',{templateUrl:'views/materialUpload.html', controller:'materialUploadController'})
        .when('/uploadMethod',{templateUrl:'views/uploadMethod.html', controller:'uploadMethodController'})
        .when('/materialPic',{templateUrl:'views/materialPic.html', controller:'materialPicController'})
        .when('/takePhoto/:flag',{templateUrl:'views/takePhoto.html', controller:'takePhotoController'})
        .when('/finish',{templateUrl:'views/finish.html', controller:'finishController'})
        .when('/infoFinish',{templateUrl:'views/infoFinish.html', controller:'infoFinishController'})
        .when('/qrCode',{templateUrl:'../eventquery/views/detail.html', controller:'qrCodeController'})
        
        // 登录流程
        // 选择方式
//      .when('/select',{templateUrl:'../login/views/select.html', controller:'selectController'})    
//      // 身份证
//      .when('/main',{templateUrl:'../login/views/main.html', controller:'mainController'})
//      // 二维码
//      .when('/citizen',{templateUrl:'../login/views/citizen.html', controller:'citizenController'})
//      // 拍照验证
//	    .when('/capture',{templateUrl:'../login/views/capture.html', controller:'captureController'})
//	    // 法人
//      .when('/key', {
//          templateUrl: '../login/views/key.html',
//          controller: 'keyController'
//      })
        
//      // 法人
//      .when('/key', {
//          templateUrl: 'views/key.html',
//          controller: 'keyController'
//      })
//		// 亮证
//      .when('/idcard', {
//          templateUrl: '../libs/common/views/idCard.html',
//          controller: 'idcardController'
//      })
        
        .otherwise({redirectTo:'/matter'})
}]);