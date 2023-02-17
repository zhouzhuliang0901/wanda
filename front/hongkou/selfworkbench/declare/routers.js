app.config(['$routeProvider',function($routeProvider){
    $routeProvider
    	.when('/start',{templateUrl:'views/start.html', controller:'startController'})
    	.when('/main',{templateUrl:'views/main.html', controller:'mainController'})
        .when('/list',{templateUrl:'views/list.html', controller:'listController'})
        .when('/citylist',{templateUrl:'views/Citylist.html', controller:'citylistController'})
		.when('/matter',{templateUrl:'views/matter.html', controller:'matterController'})
		.when('/guideline',{templateUrl:'views/guideline.html', controller:'guidelineController'})
		.when('/apply',{templateUrl:'views/apply.html', controller:'applyController'})
		.when('/select',{templateUrl:'views/select.html', controller:'selectController'})
		.when('/idCard',{templateUrl:'views/idCard.html', controller:'idCardController'})
		.when('/citizen',{templateUrl:'views/citizen.html', controller:'citizenController'})
		.when('/queryFace',{templateUrl:'views/queryFace.html', controller:'queryFaceController'})
		.when('/photo',{templateUrl:'views/photo.html', controller:'photoController'})
		.when('/info',{templateUrl:'views/info.html', controller:'infoController'})
		.when('/materialUpload',{templateUrl:'views/materialUpload.html', controller:'materialUploadController'})
		.when('/materialList',{templateUrl:'views/materialList.html', controller:'materialListController'})
		.when('/materialView',{templateUrl:'views/materialView.html', controller:'materialViewController'})
		.when('/uploadMethod',{templateUrl:'views/uploadMethod.html', controller:'uploadMethodController'})
		.when('/materialPic',{templateUrl:'views/materialPic.html', controller:'materialPicController'})
		.when('/takePhoto/:flag',{templateUrl:'views/takePhoto.html', controller:'takePhotoController'})
		.when('/finish',{templateUrl:'views/finish.html', controller:'finishController'})
		.when('/infoFinish',{templateUrl:'views/infoFinish.html', controller:'infoFinishController'})
		.otherwise({
			redirectTo: '/start'
		})
}]);