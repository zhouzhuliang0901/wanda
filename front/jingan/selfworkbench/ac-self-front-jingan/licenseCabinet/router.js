app.config(function ($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.otherwise("/main");
    $stateProvider
        .state("main", {
            url: "/main",
            templateUrl: "views/main.html",
            controller: "mainController"
        })
        .state("licenseTake", {//办事人员取证
            url: "/licenseTake",
            templateUrl: "views/licenseTake.html",
            controller: "licenseTakeController"
        })
        .state("faceVerification", {//人证核验拍照
            url: "/faceVerification",
            templateUrl: "views/faceVerification.html",
            controller: "faceVerificationController"
        })
        .state("personInfo", {//个人信息
            url: "/personInfo",
            templateUrl: "views/personInfo.html",
            controller: "personInfoController"
        })
        .state("verificationAwait", {//人证核验等待
            url: "/verificationAwait",
            templateUrl: "views/verificationAwait.html",
            controller: "verificationAwaitController"
        })
        .state("verificationComplete", {//人证核验完成
            url: "/verificationComplete",
            templateUrl: "views/verificationComplete.html",
            controller: "verificationCompleteController"
        })
        .state("licenseInputCode", {//输入验证码
            url: "/licenseInputCode",
            templateUrl: "views/licenseInputCode.html",
            controller: "licenseInputCodeController"
        })
        .state("signature", {//签名版
            url: "/signature",
            templateUrl: "views/signature.html",
            controller: "signatureController"
        })
        .state("getLicenseConfirm", {//获取证照信息
            url: "/getLicenseConfirm",
            templateUrl: "views/getLicenseConfirm.html",
            controller: "getLicenseConfirmController"
        })
        .state("openBox", {//开柜
            url: "/openBox",
            templateUrl: "views/openBox.html",
            controller: "openBoxController"
        })
        .state("staffOperation", {//工作人员操作
            url: "/staffOperation",
            templateUrl: "views/staffOperation.html",
            controller: "staffOperationController"
        })
        .state("staffOperationChoice",{//工作人员选择放证或者取证
            url:"/staffOperationChoice",
            templateUrl:"views/staffOperationChoice.html",
            controller:"staffOperationChoiceController"
        })
        .state("licenseInfoList", {//工作人员取证照列表
            url: "/licenseInfoList",
            templateUrl: "views/licenseInfoList.html",
            controller: "licenseInfoListController"
        })
        .state("materialInfoList", {//工作人员取材料列表
            url: "/materialInfoList",
            templateUrl: "views/materialInfoList.html",
            controller: "materialInfoListController"
        })
        .state("scanCode", {//工作人员扫描二维码放入材料
            url: "/scanCode",
            templateUrl: "views/scanCode.html",
            controller: "scanCodeController"
        })
        
        .state("putMaterialConfirm", {//工作人员确认放入证照信息
            url: "/putMaterialConfirm",
            templateUrl: "views/putMaterialConfirm.html",
            controller: "putMaterialConfirmController"
        })
        .state("putMaterialConfirmzf", {//工作人员确认放入卓凡证照信息
            url: "/putMaterialConfirmzf",
            templateUrl: "views/putMaterialConfirmzf.html",
            controller: "putMaterialConfirmzfController"
        })
        .state("cabinetController", {//控制所有柜子
            url: "/cabinetController",
            templateUrl: "views/cabinetController.html",
            controller: "cabinetControllerController"
        })
})