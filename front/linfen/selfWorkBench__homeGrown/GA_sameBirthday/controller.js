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
app.controller('loginType', function($state, $scope, appData, $http) {
	$scope.operation = "请选择登录方式";
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.choiceLogin = function(type) {
		//		appData.licenseNumber = "370285199611114728";
		//		$state.go('info');
		appData.loginType = type;
		$state.go('login');
	}
});
app.controller('login', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$.state.go("main");
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
		} else {
			layer.msg("很抱歉,没有获取到您的信息,请重试")
		}
	}

	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$state.go("info");
	}

	$scope.prevStep = function() {
		$.device.Face_Close();
		$state.go("loginType");
	}
	$scope.citizenLogin = function(info) {
		if(info) {
			if(appData.qrCodeType == "suishenma") {
				appData.licenseName = info.zwdtsw_name;
				appData.licenseNumber = info.zwdtsw_cert_id;
				$state.go("info");
			} else {
				var idcardInfo = info.result.data;
				appData.licenseName = idcardInfo.realname;
				appData.licenseNumber = idcardInfo.idcard;
				$state.go("info");
			}
		}
	}
});
app.controller("info", function($scope, $state, appData, $sce, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	$scope.funName = appData.funName = "同日出生人数";
	$scope.isAlert = false;
	$scope.searchInfo = false;
	$scope.man = false;
	$scope.woman = false;
	$scope.concel = "false";
	$scope.SexList = [{
		"sex": '男'
	}, {
		"sex": '女'
	}];
    $scope.SexList=changeListname($scope.SexList,"sex");
	// 初始化日期插件
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});

	// 展示个人信息
	$scope.licenseNumber = appData.licenseNumber;
	$scope.Sex = ((parseInt($scope.licenseNumber.substring(16, 17)) % 2) == 0) ? "女" : "男";
	if($scope.Sex == '女') {
		setTimeout(function() {
			$("#nations").find("option[value='object:16']").attr('selected', 'selected');
		}, 300)
	} else {
		setTimeout(function() {
			$("#nations").find("option[value='object:15']").attr('selected', 'selected');
		}, 300)
	}
	$scope.Birthday = ($scope.licenseNumber).substring(6, 10) + "-" + ($scope.licenseNumber).substring(10, 12) + "-" + ($scope.licenseNumber).substring(12, 14);
	// 确认信息查询人数
	$scope.search = function() {
		var birthday = ($("#Birthday").val().replace(/-/g, ''));
		$scope.year = birthday.substring(0, 4);
		$scope.month = birthday.substring(4, 6);
		$scope.day = birthday.substring(6, 8);
		$scope.isLoading = true;
		$scope.searchInfo = false;
		if($scope.Sex.sex == '女' || $scope.Sex == '女') {
			appData.sex = 2;
			$scope.woman = true;
			$scope.man = false;
		} else {
			appData.sex = 1;
			$scope.man = true;
			$scope.woman = false;
		}
		// 查询全部的人数
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/sameBirth/queryTheSameBirth.do",
			dataType: "jsonp",
			timeout: 5000,
			jsonp: "jsonpCallback",
			data: {
				csrq: birthday
			},
			success: function(dataJsonp) {
				console.log(dataJsonp)
				$scope.isLoading = false;
				$scope.searchInfo = true;
				if(dataJsonp.Success) {
					$scope.searchNum = dataJsonp.Data[0].zs;
					$scope.$apply();
				} else {
					$scope.isAlert = true;
					$scope.msg = '总数据获取失败';
				}
			},
			error: function(err) {
				$scope.isLoading = false;
				console.info("queryInfo error");
			}
		});
		// 查询相同性别的人数
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/sameBirth/queryTheSameBirth.do",
			dataType: "jsonp",
			timeout: 5000,
			jsonp: "jsonpCallback",
			data: {
				csrq: birthday,
				xbdm: appData.sex
			},
			success: function(dataJsonp) {
				console.log(dataJsonp);
				$scope.isLoading = false;
				$scope.searchInfo = true;
				try {
					if(dataJsonp.Success) {
						$scope.sexNum = dataJsonp.Data[0].zs;
						$scope.$apply();
					} else {
						$scope.isAlert = true;
						$scope.msg = '同性别数据获取失败';
					}
				} catch(e) {

				}
			},
			error: function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = '接口异常';
				console.info("queryInfo error");
			}
		});
		//模块使用记录
		$scope.jsonStr = {
			SUCCESS: "true",
			data: {
				name: appData.funName,
			}
		}
		recordUsingHistory('公安服务', '查询', appData.funName, appData.licenseName, appData.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
		trackEventForQuery(appData.funName, '', '查询', '上海市公安局', appData.licenseName, appData.licenseNumber, '');
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
});