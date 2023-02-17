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
app.controller('handleChoice', function($state, $scope, appData) {
    removeAnimate($('.scrollBox2'))
    addAnimate($('.scrollBox2'))
    $scope.operation = appData.funName = "个人住房公积金从外省市转移到本市";
    appData.isHandle = false;
    $scope.prevStep = function() {
        window.location.href = '../ZJ_allItem/index.html';
    }
    $scope.handleSearch = function() {
        appData.isHandle = true;
        $state.go("loginType");
    }
    $scope.handle = function() {
        $state.go("guideline");
    }
});
app.controller("guideline", function($scope, $state, appData, $http, $timeout, $rootScope) {
    $scope.funName = appData.funName;
    appData.isHandle = false;
    $scope.prevStep = function() {
        $state.go("handleChoice");
    }
    $scope.nextStep = function() {
        $state.go('loginType');
    }
    $scope.isScroll = function() {
        new iScroll("wrapper", {
            vScrollbar: false,
            hScrollbar: false,
            hideScrollbar: true,
            bounce: true,
            click: true,
            taps: true,
            preventDefault: false,
            hScroll: false,
            checkDOMChanges: true
        });
    };
    $scope.isScroll();
    addAnimate($('.main2'))
});
app.controller('loginType', function($state, $scope, appData) {
    removeAnimate($('.scrollBox2'))
    addAnimate($('.scrollBox2'))
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

    //跳转页面
    $scope.nextStep = function() {
        $scope.tokenType = "token";
        $scope.token = function() {
            if(appData.isHandle) {
                $state.go('handleInfo');
            } else {
                $state.go('info');
            }
        }
    }

//    	$scope.idcardLogin = function() {
//		appData.licenseNumber = "430426199804106174";
//		appData.licenseName = "邹天奇";
//		$state.go('handleInfo');
//	}
//	$scope.idcardLogin();

    $scope.idcardLogin = function(info, images) {
        if(info) {
            $scope.faceImage = images;
            $scope.loginType = 'recognition';
            appData.licenseNumber = info.Number;
            appData.licenseName = info.Name;
            appData.VALIDENDDAY = info.ValidtermOfEnd;
            appData.VALIDSTARTDAY = info.ValidtermOfStart;
            appData.Address = info.Address;
            appData.nation = info.People;
            if(appData.nation.lastIndexOf('族') < 0) {
                appData.nation = appData.nation + '族';
            }
        } else {
            layer.msg("没有获取到")
        }
    }
    $scope.getResult = function(img) {
        $scope.img = img;
        if(appData.isHandle) {
            $state.go('handleInfo');
        } else {
            $state.go('info');
        }
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
            $scope.nextStep();
        } else {
            var idcardInfo = info.result.data;
            appData.licenseName = idcardInfo.realname;
            appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
            appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
            $scope.nextStep();
        }
    }
})
app.controller('handleInfo', function($state, $scope, appData, $rootScope, $timeout, $http) {
    $scope.funName = appData.funName;
    $scope.isAlert = false;
    $scope.concel = "false";
    $scope.isLoading = false;
    $scope.nextText = "撤销办理";
    $scope.alertConfirm = function() {
        $scope.isAlert = false;
        $scope.isLoading = false;
    }

    $scope.zjlxList = zjlx;
    $scope.statusList = ztm;
	$scope.ywlxList = ywlx;
    // 查询办理进度
    $scope.searchProcess = function(account) {
        $scope.isLoading = true;
        $.ajax({
            type: "post",
            url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFundChange/queryProcessingProgress.do",
            dataType: "json",
            jsonp: "jsonpCallback",
            data: {
                account: account,
            },
            success: function(res) {
                $scope.isLoading = false;
                if(res.ret_code == '200') {
                    $scope.info = res.dataList[0];
                } else {
                    $scope.isAlert = true;
                    $scope.msg = res.ret_msg;
                }
            },
            error: function(err) {
                $scope.isLoading = false;
                $scope.isAlert = true;
                $scope.msg = "查询到办理进度失败，请重试";
            },
        });
    }

	$scope.nextStep = function(){
		$.ajax({
			type: "post",
            url: $.getConfigMsg.preUrlSelf +"/selfapi/accumulationFundChange/revokeApply.do",
            dataType: "json",
            jsonp: "jsonpCallback",
            data: {
            	account: $scope.personalAccount,
            },
            success:function(res){
            	if(res.ret_code == "200"){
            		$scope.isAlert = true;
               		$scope.msg = "撤销成功";
            	}else{
            		$scope.isAlert = true;
               		$scope.msg = res.ret_msg;
               		$scope.isAlert = true;
               		$scope.msg = "撤销成功";
            	}
            },
            error:function(err){
				$scope.isLoading = false;
                $scope.isAlert = true;
                $scope.msg = "撤销办理失败，请重试";
            }
		})
	}

    // 查询个人公积金账号基本信息
    $scope.pAccountInfo = function() {
        $.ajax({
            type: "post",
            url: $.getConfigMsg.preUrl + "/aci/accumulationFund/accumulationFundInfo.do",
            dataType: "json",
            jsonp: "jsonpCallback",
            data: {
                name: encodeURI(appData.licenseName),
                identNo: appData.licenseNumber,
            },
            success: function(res) {
                if(res.head.rst.buscode == "000000") {
                    $scope.personalAccount = res.body.pri_account;
                    $scope.companyAccount = res.body.unit_code;
                    $scope.searchProcess(res.body.pri_account);
                } else {
                    $scope.isAlert = true;
                    $scope.msg = "未查询到基本账号信息，请重试";
                }
            },
            error: function(err) {
                $scope.isAlert = true;
                $scope.msg = "未查询到基本账号信息，请重试";
            }

        });
    }
    $scope.pAccountInfo();
});
app.controller('info', function($state, $scope, appData, $rootScope, $timeout, $http) {
    $scope.funName = appData.funName;
    $scope.stName = appData.licenseName;
    $scope.stIdCard = appData.licenseNumber;
    $scope.stIdCardType = "居民身份证";
    $scope.stMobile = appData.stMobile || '';
    appData.zczxdm = '';
    $scope.isAlert = false;
    $scope.concel = "false";
    $scope.isLoading = false;
    $scope.nextText = "提交";
    $scope.alertConfirm = function() {
        $scope.isAlert = false;
        $scope.isLoading = false;
    }
    $scope.loading = function() {
        $scope.isLoading = true;
    }
    $scope.stopLoading = function() {
        $scope.isLoading = false;
    }
    $scope.centerDatas = [{
        "ZXMC": '请输入省市关键字实时加载数据'
    }];
    $scope.$on('test', function(event, data) {
        appData.zczxdm = data;
    })

    // 查询个人公积金账号基本信息
    $scope.pAccountInfo = function() {
        $.ajax({
            type: "post",
            url: $.getConfigMsg.preUrl + "/aci/accumulationFund/accumulationFundInfo.do",
            dataType: "json",
            jsonp: "jsonpCallback",
            data: {
                name: encodeURI(appData.licenseName),
                identNo: appData.licenseNumber,
            },
            success: function(res) {
                if(res.head.rst.buscode == "000000") {
                    $scope.personalAccount = res.body.pri_account;
                    $scope.companyAccount = res.body.unit_code;
                } else {
                    $scope.isAlert = true;
                    $scope.msg = "未查询到基本账号信息，请重试";
                }
            },
            error: function(err) {
                $scope.isAlert = true;
                $scope.msg = "未查询到基本账号信息，请重试";
            }

        });
    }
    $scope.pAccountInfo();
    //提交
    $scope.nextStep = function() {
        let paramsInfo = form2arr($('#infoParams').serialize());
        var condFlag = false;
        do {
            if(isBlank($scope.stName)) {
                $scope.isAlert = true;
                $scope.msg = "请输入申请人姓名";
                return
            }
            if(isBlank($scope.stIdCard)) {
                $scope.isAlert = true;
                $scope.msg = "请输入申请人身份证号";
                return
            }
            if(!isPhoneAvailable($scope.stMobile)) {
                $scope.isAlert = true;
                $scope.msg = "请输入正确的手机号码";
                return
            }
            if(isBlank($scope.companyName)) {
                $scope.isAlert = true;
                $scope.msg = "请输入原工作单位名称";
                return
            }
            if(isBlank($scope.fundAccount)) {
                $scope.isAlert = true;
                $scope.msg = "请输入原住房公积金账号";
                return
            }
            if(isBlank(paramsInfo.fundCenter)) {
                $scope.isAlert = true;
                $scope.msg = "请选择公积金管理中心";
                return
            }
        } while (condFlag);
        condFlag = true;
        //提交参数
        let params = {
            custAcct: paramsInfo.companyAccount,
            grzh: paramsInfo.personalAccount,
            telephone: paramsInfo.mobile,
            zrzxmc: encodeURI(paramsInfo.fundCenter),
            zczxdm: appData.zczxdm,
            sourcedwmc: encodeURI(paramsInfo.companyName),
            sourcegrzh: paramsInfo.fundAccount,
        };
        $scope.isLoading = true;
        $.ajax({
            type: "post",
            url: $.getConfigMsg.preUrlSelf + "/selfapi/accumulationFundChange/applyAccumulationFundChange.do",
            dataType: "json",
            jsonp: "jsonpCallback",
            data: params,
            success: function(res) {
                $scope.isLoading = false;
                if(res.ret_code == '200') {
                    $state.go('submit');
                } else {
                    $scope.isAlert = true;
                    $scope.msg = res.ret_msg;
                }
            },
            error: function(err) {
                $scope.isLoading = false;
                $scope.isAlert = true;
                $scope.msg = "提交接口异常,请稍候再试";
            },
        });
    }
});
app.controller('submit', function($state, $scope, appData) {
    $scope.itemName = appData.funName;
    $scope.applyNo = appData.applyNo || '';
    $scope.licenseName = appData.licenseName;
    $scope.stMobile = appData.stMobile || '';
    $scope.date = getCurrentDate(2);
    $scope.nextText = "返回首页";
    //模块使用记录
    $scope.jsonStr = {
        SUCCESS: "true",
        data: {
            name: appData.funName,
            Number: $scope.applyNo,
        }
    }
    recordUsingHistory('住建服务', '办理', appData.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
    //行为分析(办理)
    trackEventForAffairs("", appData.funName, "上海市住房和城乡建设委员会", appData.licenseName, appData.licenseNumber, $scope.stMobile);
    $scope.goHome = function() {
        $.device.GoHome();
    }
});