app.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
        .when('/item',{templateUrl:'views/item.html', controller:'itemController'})
        .when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
        .when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
        .when('/precautions',{templateUrl:'views/precautions.html', controller:'preController'})
        .when('/apply',{templateUrl:'views/apply.html', controller:'applyController'})
//      .when('/select',{templateUrl:'../login/views/select.html', controller:'selectController'})        
//      .when('/main',{templateUrl:'views/main.html', controller:'mainController'})
//      .when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
//	    .when('/capture',{templateUrl:'views/capture.html', controller:'captureController'})
        .when('/sign',{templateUrl:'views/sign.html', controller:'signController'})
        .when('/handleLocation',{templateUrl:'views/handleLocation.html', controller:'handLocationController'})
        .when('/info',{templateUrl:'views/info.html', controller:'infoController'})
        .when('/riverInfo1',{templateUrl:'views/riverInfo/info1.html', controller:'riverInfo1Controller'})
        .when('/riverInfo2',{templateUrl:'views/riverInfo/info2.html', controller:'riverInfo2Controller'})
        .when('/riverInfo3',{templateUrl:'views/riverInfo/info3.html', controller:'riverInfo3Controller'})
        .when('/riverInfo4',{templateUrl:'views/riverInfo/info4.html', controller:'riverInfo4Controller'})
        .when('/riverInfo5',{templateUrl:'views/riverInfo/info5.html', controller:'riverInfo5Controller'})
        .when('/riverInfo6',{templateUrl:'views/riverInfo/info6.html', controller:'riverInfo6Controller'})
        .when('/materialUpload',{templateUrl:'views/materialUpload.html', controller:'materialUploadController'})
        .when('/uploadMethod',{templateUrl:'views/uploadMethod.html', controller:'uploadMethodController'})
        .when('/materialPic',{templateUrl:'views/materialPic.html', controller:'materialPicController'})
        .when('/takePhoto/:flag',{templateUrl:'views/takePhoto.html', controller:'takePhotoController'})
        .when('/finish',{templateUrl:'views/finish.html', controller:'finishController'})
        .when('/infoFinish',{templateUrl:'views/infoFinish.html', controller:'infoFinishController'})
        .when('/qrCode',{templateUrl:'../eventquery/views/detail.html', controller:'qrCodeController'})
        // 法人
//      .when('/key', {
//          templateUrl: 'views/key.html',
//          controller: 'keyController'
//      })
        // 亮证
//      .when('/idcard', {
//          templateUrl: '../libs/common/views/idCard.html',
//          controller: 'idcardController'
//      })
        .otherwise({redirectTo:'/matter'})
}]);