function removeAnimate(ele) {
    $(ele).css({
        "transform": "translateY(0px)",
        "top": 0
    }).removeClass('transformto')
}

function addAnimate(ele) {
    $(ele).addClass('transformto').on("animationend", function() {
        $(ele).removeClass('transformto')
    })
}
app.controller('loginType', function($state, $scope, appData) {
    removeAnimate($('.scrollBox2'))
    addAnimate($('.scrollBox2'))
	appData.funName = $(".headName").text();
    $scope.operation = "请选择登录方式";
    $scope.choiceLogin = function(type) {
        appData.loginType = type;
        $state.go("login");
    }
    $scope.prevStep = function() {
        $state.go("guideline");
    }
});
app.controller('login', function($scope, $http, $state, appData) {
    $scope.operation = "身份证登录";
    $scope.loginType = appData.loginType;
    $scope.loginBtn = false;
    $scope.isAlert = false;
    $scope.concel = "false";
    $scope.alertConfirm = function() {
        $scope.isAlert = false;
        $state.go("loginType");
    }
    switch($scope.loginType) {
        case "idcard":
            $scope.operation = "身份证登录";
            break;
        case "ukey":
            $scope.loginBtn = true;
            $scope.operation = "ukey登录";
            break;
        case "cloud":
            $scope.operation = "随申办登录";
            break;
    }

    $scope.idcardLogin = function(info, images) {
        if(info) {
            $scope.faceImage = images;
            $scope.loginType = 'recognition';
            appData.licenseNumber = info.Number;
            appData.licenseName = info.Name;
            appData.VALIDENDDAY = info.ValidtermOfEnd;
            appData.VALIDSTARTDAY = info.ValidtermOfStart;
        } else {
            layer.msg("没有获取到")
        }
    }
    $scope.getResult = function(img) {
        $scope.img = img;
        $state.go('info');
    }
    $scope.prevStep = function() {
        $state.go("loginType");
    }

    $scope.citizenLogin = function(info) {
        function ClearBr(key) {
            key = key.replace(/\+/g, "-");
            key = key.replace(/\#/g, ",");
            return key;
        }
        if(appData.qrCodeType == "suishenma") {
            appData.licenseName = info.zwdtsw_name;
            appData.licenseNumber = info.zwdtsw_cert_id;
            appData.encrypt_identity = ClearBr(info.encrypt_identity);
            appData.stMobile = info.zwdtsw_link_phone;
            $state.go('info');
        } else {
            var idcardInfo = info.result.data;
            appData.licenseName = idcardInfo.realname;
            appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
            appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
            $state.go('info');
        }
    }
})
app.controller('info', function($state, $scope, appData, $rootScope, $timeout, $http) {
    $scope.funName = appData.funName;
    $scope.stName = appData.licenseName;
    $scope.stIdCard = appData.licenseNumber;
    $scope.isAlert = false;
    $scope.concel = "false";
    $scope.isLoading = false;
    $scope.nextText = "查询";
    $scope.alertConfirm = function() {
        $scope.isAlert = false;
        $scope.isLoading = false;
    }

$scope.yearList = [];
	var thisYear = (new Date()).getFullYear();
	for(var i = 2002; i <= thisYear; i++) {
		$scope.yearList.push(i);
	}
	$scope.change = function(index,item){
		$scope.current = index;
		$scope.examYear = item;
	}
    //提交
    $scope.nextStep = function() {
        var condFlag = false;
        do {
            if(isBlank($scope.examYear)) {
                $scope.isAlert = true;
                $scope.msg = "请选择查询年份";
                return;
            }
        } while (condFlag);
        condFlag = true;
        $scope.isLoading = true;
        $.ajax({
            type: "post",
            url: $.getConfigMsg.preUrlSelf + "/selfapi/legalProfession/qualificationCertificateInquiry.do",
            dataType: "json",
            jsonp: "jsonpCallback",
            data: {
            	idCard:appData.licenseNumber,
            	examYear:$scope.examYear,
            },
            success: function(res) {
               $scope.isLoading = false;
                console.log(res);
                if(res.code == '200'){
					appData.result = res.data;
					$state.go('result')
                }else{
                	$scope.isAlert = true;
                	$scope.msg = res.msg;
                }
            },
            error: function(err) {
                $scope.isLoading = false;
                $scope.isAlert = true;
                $scope.msg = "查询接口异常,请稍候再试";
            },
        });
    }
});
app.controller('result', function($state, $scope, appData, $rootScope, $timeout, $http) {
    $scope.funName = appData.funName;
    $scope.isAlert = false;
    $scope.concel = "false";
    $scope.isLoading = false;
    $scope.info = appData.result;

    //模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: '',
		}
	}
	recordUsingHistory('司法服务', '查询', $scope.funName, appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
	trackEventForQuery($scope.funName, "", "查询", "上海市司法局", appData.licenseName, appData.licenseNumber, "");

    $scope.alertConfirm = function() {
        $scope.isAlert = false;
        $scope.isLoading = false;
    }

});