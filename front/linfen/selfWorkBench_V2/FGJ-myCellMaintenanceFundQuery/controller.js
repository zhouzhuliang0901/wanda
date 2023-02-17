function removeAnimate(ele) {
	//	$(ele).css({
	//		"transform": "translateY(0px)",
	//		"top": 0
	//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
app.controller('loginType', function($state, $scope, appData) {
	//显示社保卡登录选项
	$scope.ShowSscard = jQuery.getConfigMsg.isShowSscard;
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.funName = $(".headName").text();
	$scope.operation = "请选择登录方式";
	appData.SwipeType = 'sbCard';
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, $timeout) {
	$scope.loginType = appData.loginType;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$.device.GoHome();
	}
	switch ($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "ukey":
			$scope.loginBtn = true;
			$scope.operation = "ukey登录";
			break;
		case "cloud":
			$scope.operation = "随申办";
			break;
		case "sscard":
			$scope.operation = "社保卡登录";
			break;
	}

	$scope.nextStep = function() {
		$scope.tokenType = "token";
		$scope.token = function() {
			$state.go("houseLIist");
		}
	};

	// $scope.idcardLogin = function(info, images) {
	// 	appData.licenseNumber = '430426199804106174';
	// 	appData.licenseName = '邹天奇';
	// 	$scope.nextStep();
	// }
	// $scope.idcardLogin();

	$scope.idcardLogin = function(info, images) {
		if (info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.licenseNumber = info.Number;
			appData.licenseName = info.Name;
			appData.VALIDENDDAY = info.ValidtermOfEnd;
			appData.VALIDSTARTDAY = info.ValidtermOfStart;
			$scope.nextStep();
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$state.go("houseLIist");
	}
	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		if (appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.zwdtsw_user_id = info.zwdtsw_user_id;
			appData.zwdtsw_link_phone = info.zwdtsw_link_phone;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$scope.nextStep();
		} else {
			var idcardInfo = info.result.data;
			appData.licenseName = idcardInfo.realname;
			appData.licenseNumber = idcardInfo.idcard;
			appData.VALIDENDDAY = idcardInfo.VALIDENDDAY;
			appData.VALIDSTARTDAY = idcardInfo.VALIDSTARTDAY;
			$scope.nextStep();
		}
	}

	$scope.prevStep = function() {
		$state.go("loginType");
	}
});
app.controller('houseLIist', function($state, $scope, appData, $rootScope) {
	$scope.operation = "选择房屋信息";
	$scope.isAlert = false;
	$scope.concel = 'false';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$(".menpai").attr("disabled", "disabled");
	$(".fenhu").attr("disabled", "disabled");

	if (appData.zwdtsw_link_phone) {} else {
		//获取手机号
		$.customAjax.get($.getConfigMsg.preUrlSelf + '/aci/workPlatform/getUserInfoByAccessToken.do', {
			accessToken: appData.token
		}, function(res) {
			appData.zwdtsw_link_phone = res.zwdtsw_link_phone
		}, function(err) {});
	}


	//获取小区与房屋列表
	$scope.getHouseInfo = function() {
		$scope.isLoading = true;
		$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/housingManagement/getHouseInfo.do', {
			tokenSNO: $rootScope.tokenSNO,
			communityName: '',
		}, function(res) {
			$scope.isLoading = false;
			if (res.code == '200') {
				$scope.myHouseList = res.data.content;
			} else {

			}
		}, function(err) {})
	}

	$scope.getHouseInfo();
	layui.use(['form', 'layedit', 'laydate'], function() {
		var form = layui.form,
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate;
		//小区列表 (目前固定读取options.js)
		var dat;
		layui.each(content, function(index, item) {
			dat = dat + "<option value='" + item.communityId + "'>" + item
				.communityName + "</option>"
			if (content.length == (index + 1)) {
				$('.xiaoqu').append(dat)
				return form.render();
			}
		})
		form.on('select(xiaoqu)', function(data) {
			if (data.value) {
				appData.communityId = data.value;
				$.customAjax.post($.getConfigMsg.preUrlSelf +
					'/selfapi/housingManagement/getUnitId.do', {
						tokenSNO: $rootScope.tokenSNO,
						communityId: appData.communityId,
						name: '',
					},
					function(res) {
						$scope.unitList = res.data.content;
						$scope.isLoading = false;
						$(".menpai").removeAttr("disabled");
						layui.each($scope.unitList, function(index, item) {
							$('.menpai').append("<option value='" + item.unitId +
								"'>" + item
								.unitAddress + "</option>")
						})
						form.render();
					},
					function(err) {})
			}
		});
		form.on('select(menpai)', function(data) {
			if (data.value) {
				appData.unitId = data.value;
				$.customAjax.post($.getConfigMsg.preUrlSelf +
					'/selfapi/housingManagement/getHouseId.do', {
						tokenSNO: $rootScope.tokenSNO,
						communityId: appData.communityId,
						unitId: appData.unitId,
						name: '',
					},
					function(res) {
						$scope.houseList = res.data.content;
						$scope.isLoading = false;
						$(".fenhu").removeAttr("disabled");
						layui.each($scope.houseList, function(index, item) {
							$('.fenhu').append("<option value='" + item.houId +
								"'>" + item
								.houNo + "</option>")
						})
						form.render();
					},
					function(err) {})
			}
		});
		form.on('select(fenhu)', function(data) {
			if (data.value) {
				appData.houId = data.value;
			}
		})
	})

	//添加房屋信息
	$scope.addHouse = function() {
		$scope.isLoading = true;
		$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/housingManagement/addHouse.do', {
			tokenSNO: $rootScope.tokenSNO,
			communityId: appData.communityId,
			unitId: appData.unitId,
			houId: appData.houId,
		}, function(res) {
			console.log(res);
			$scope.isLoading = false;
			if (res.code == '200') {
				$scope.isAlert = true;
				$scope.msg = "添加成功";
				$scope.alertConfirm = function() {
					$scope.getHouseInfo();
					$scope.isAlert = false;
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = res.msg;
			}
		}, function(err) {
			$scope.isAlert = true;
			$scope.msg = '添加信息接口异常，请重试';
		})
	}

	//删除房屋信息
	$scope.delete = function(index, item) {
		$scope.isLoading = true;
		$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/housingManagement/deleteHouse.do', {
			tokenSNO: $rootScope.tokenSNO,
			houId: item.houId,
			phone: appData.zwdtsw_link_phone,
		}, function(res) {
			console.log(res);
			$scope.isLoading = false;
			if (res.code == '200') {
				$scope.isAlert = true;
				$scope.msg = "删除成功";
				$scope.alertConfirm = function() {
					$scope.getHouseInfo();
					$scope.isAlert = false;
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = res.msg;
			}
		}, function(err) {
			$scope.isAlert = true;
			$scope.msg = '删除接口异常，请重试';
		})
	}
	//查询房屋信息
	$scope.query = function(index, item) {
		var flag = false;
		var input = '.input-area' + index;
		do {
			if ($(input).val() < 1 && item.isBinding == "0") {
				$scope.isAlert = true;
				$scope.msg = "请输入核验面积！";
				return;
			}
		} while (flag);
		console.log($(input).val());
		$scope.isLoading = true;
		//验证房屋面积
		$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/housingManagement/checkHouse.do', {
			tokenSNO: $rootScope.tokenSNO,
			houId: item.houId,
			area: (item.isBinding == "1") ? item.houArea : $(input).val(),
		}, function(res) {
			console.log(res);
			$scope.isLoading = false;
			if (res.code == '200') {
				appData.infoCommunityId = item.communityId;
				$state.go('info');
			} else {
				$scope.isAlert = true;
				$scope.msg = res.msg;
			}
		}, function(err) {
			$scope.isAlert = true;
			$scope.msg = '校验面积接口异常，请重试';
		})
	}
});
app.controller('info', function($state, $scope, appData, $rootScope) {
	removeAnimate($('.scrollBox2'));
	addAnimate($('.scrollBox2'));
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//我的商品住宅小区维修资金帐目公布
	$scope.queryInfo = function() {
		$scope.isLoading = true;
		$.customAjax.post($.getConfigMsg.preUrlSelf + '/selfapi/housingManagement/maintenanceFund.do', {
			tokenSNO: $rootScope.tokenSNO,
			communityId: appData.infoCommunityId,
		}, function(res) {
			console.log(res);
			$scope.isLoading = false;
			if (res.code == "200") {
				$scope.info = res.data.content;
				$scope.detailList = res.data.content.list;
				//模块使用记录
				$scope.jsonStr = {
					SUCCESS: "true",
					data: {
						name: appData.funName,
						Number: "",
					}
				}
				recordUsingHistory('房管局服务', '查询', appData.funName, appData.licenseName, appData
					.licenseNumber, '', '', JSON.stringify($scope.jsonStr));
			} else {
				$scope.isAlert = true;
				$scope.msg = res.msg;
			}
		}, function(err) {
			$scope.isAlert = true;
			$scope.msg = '查询接口异常，请重试';
		})
	}
	$scope.queryInfo();
});
