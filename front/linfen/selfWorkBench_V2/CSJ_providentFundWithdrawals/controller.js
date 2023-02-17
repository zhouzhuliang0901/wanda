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
app.controller("guideline", function($scope, $state, appData, $http, $timeout, $rootScope) {
//	$.getConfigMsg.preUrlSelf = "http://180.169.7.194:8081/ac-self"
	$scope.funName = appData.funName = "长三角购房提取住房公积金";
	$scope.prevStep = function() {
		window.location.href = '../CSJ_allItem/index.html';
	}
	$scope.nextStep = function() {
		$state.go('authorizationCommitments');
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
app.controller("authorizationCommitments", function($scope, $state, appData, $http, $timeout, $rootScope) {
	$scope.funName = appData.funName;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go('guideline');
	}
	$scope.ISchecked=true;
	$scope.readClick = function() {
		if($scope.ISchecked){
			$scope.ISchecked=false;
		}else{
			$scope.ISchecked=true;
		}
	}

//	$('.swearYes').click(function() {
//		if($(this).hasClass("checked")) {
//			$(this).removeClass("checked");
//		} else {
//			$(this).addClass("checked");
//		}
//	})

	//sign = 1 代表从长三角进入
	$scope.nextStep = function() {
		if($scope.ISchecked) {
			$scope.isAlert = true;
			$scope.msg = '请选择已读并承诺';
		} else {
			$state.go('loginType');
		}
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
			$scope.operation = "扫码登录";
			break;
	}

	//跳转页面
	$scope.nextStep = function() {
		$state.go('info');
	}
//	$scope.idcardLogin = function() {
//		appData.licenseNumber = "320721199408210016";
//		appData.licenseName = "张子昱";
//		$state.go('info');
//
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
		$state.go('info');
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			try{
				key = key.replace(/\+/g, "-");
			    key = key.replace(/\#/g, ",");
			    return key;
			}catch(e){}
		}
		if(appData.qrCodeType == "suishenma") {
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
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
app.controller('info', function($state, $scope, appData, $rootScope, $timeout, $http, appFactory) {
	console.log(appData.licenseName);
	$scope.operation = "请选择查询条件";
	$scope.funName = appData.funName;
	$scope.stName = appData.licenseName;
	$scope.stIdCard = appData.licenseNumber;
	$scope.stIdCardType = "中华人民共和国居民身份证";
	$scope.idCardType_mate = '中华人民共和国居民身份证'
	$scope.licenseNumber = appData.licenseNumber;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoading = false;
	$scope.mateInfoShow = false;
	$scope.nextText = "提交";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.jcdprovinceList = countyOrgProvince;
	$scope.hjprovinceList = hjList;
	$scope.provinceList = hjList;
	$scope.marryTypeList = marryType;
	$scope.isPropertyOwnerList = ['是', '否'];
	$scope.togetherList = [{
		id: "0",
		name: '否'
	}, {
		id: "1",
		name: '是'
	}]

	$scope.houseTypeList = houseType;

	$scope.$watch('maritalStatus', function(val) {
		if(val) {
			if(val.name == '未婚') {
				$scope.mateInfoShow = false;
			} else if(val.name == '已婚') {
				$scope.mateInfoShow = true;

			}
		}
	})

	$scope.$watch('jcdProvince', function(val) {
		if(val) {
			$scope.jcdCountyList = filterByInfo(countyOrg, val.provinceName, 'provinceName');
		}
	})
	$scope.$watch('province_house', function(val) {
		if(val) {
			appFactory.get_dinctionary(val.code, function(res) {
				$scope.cityList = res.data;
			})
		}
	})

	$scope.$watch('city_house', function(val) {
		if(val) {
			appFactory.get_dinctionary(val.code, function(res) {
				$scope.countyList = res.data;
			})
		}
	})

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
	
	//提交
	$scope.nextStep = function() {
		var condFlag = false;
		do {} while (condFlag);
		condFlag = true;
		appData.idMarried = $scope.maritalStatus.id;
		appData.hj = $scope.hjprovince.name;
		if(isBlank($scope.county_house)){
			$scope.county_house = {
				name:"",
				code:"",
			}
		}
		//提交参数
		if($scope.maritalStatus.id == "10") {
			$scope.personList = [{
				"name": $scope.stName,
				"identityType": "身份证",
				"identityNo": $scope.stIdCard,
				"depositName": $scope.jcdProvince.provinceName,
				"depositCode": $scope.jcdProvince.provinceCode,
				"depositProvinceName": $scope.jcdCounty.depositName,
				"depositProvinceCode": $scope.jcdCounty.depositCode,
				"isPropertyOwner": ($scope.propertyOwner=='是')?'1':'0',
				"relationship": "1",
				"isExtract": "1",
				"phone": $scope.mobile,
				"marriage": $scope.maritalStatus.id,
				"spouseIdentityType": "",
				"spouseIdentityNo": "",
				"spouseName": "",
				"householdRegisterName": "",
				"householdRegisterCode": "",
				"householdRegisterPName": $scope.hjprovince.name,
				"householdRegisterPCode": $scope.hjprovince.code,
				"realNameAuth": "1"
			}];
			$scope.houseInfo = {
				"provinceName": $scope.province_house.name,
				"cityName": $scope.city_house.name,
				"districtName": $scope.county_house.name,
				"provinceCode": $scope.province_house.code,
				"cityCode": $scope.city_house.code,
				"districtCode": $scope.county_house.code,
				"houseType": $scope.houseType.id
			}
		} else if($scope.maritalStatus.id == "20") {
			$scope.personList = [{
				"name": $scope.stName,
				"identityType": "身份证",
				"identityNo": $scope.stIdCard,
				"depositName": $scope.jcdProvince.provinceName,
				"depositCode": $scope.jcdProvince.provinceCode,
				"depositProvinceName": $scope.jcdCounty.depositName,
				"depositProvinceCode": $scope.jcdCounty.depositCode,
				"isPropertyOwner": ($scope.propertyOwner=='是')?'1':'0',
				"relationship": "1",
				"isExtract": "1",
				"phone": $scope.mobile,
				"marriage": $scope.maritalStatus.id,
				"spouseIdentityType": "身份证",
				"spouseIdentityNo": $scope.idCard_mate,
				"spouseName": $scope.name_mate,
				"householdRegisterName": '',
				"householdRegisterCode": '',
				"householdRegisterPName": $scope.hjprovince_mate.name,
				"householdRegisterPCode": $scope.hjprovince_mate.code,
				"realNameAuth": "1"
			}, {
				"name": $scope.name_mate,
				"identityType": "身份证",
				"identityNo": $scope.idCard_mate,
				"depositName": $scope.jcdProvince_mate.provinceName,
				"depositCode": $scope.jcdProvince.provinceCode,
				"depositProvinceName": $scope.jcdCounty_mate.depositName,
				"depositProvinceCode": $scope.jcdCounty_mate.depositCode,
				"isPropertyOwner":($scope.propertyOwner_mate=='是')?'1':'0',
				"relationship": "2",
				"isExtract": "0",
				"phone": $scope.mobile_mate,
				"marriage": $scope.maritalStatus.id,
				"spouseIdentityType": "身份证",
				"spouseIdentityNo": $scope.stIdCard,
				"spouseName": $scope.stName,
				"householdRegisterName": "",
				"householdRegisterCode": "",
				"householdRegisterPName": $scope.hjprovince.name,
				"householdRegisterPCode": $scope.hjprovince.code,
				"realNameAuth": "0"
			}];
			$scope.houseInfo = {
				"provinceName": $scope.province_house.name,
				"cityName": $scope.city_house.name,
				"districtName": $scope.county_house.name,
				"provinceCode": $scope.province_house.code,
				"cityCode": $scope.city_house.code,
				"districtCode": $scope.county_house.code,
				"houseType": $scope.houseType.id
			}
		}

		$scope.submitApplicantsAndHousingInfo = function() {
			$scope.isLoading = true;
			$.ajax({
				type: "post",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/withdrawalOfProvidentFund/submitApplicantsAndHousingInfo.do",
				dataType: "json",
				//				jsonp: "jsonpCallback",
				data: {
					"applyId": appData.applyId,
					'personList': encodeURI(JSON.stringify($scope.personList)),
					'houseInfo': encodeURI(JSON.stringify($scope.houseInfo))
				},
				success: function(res) {
					console.log(res);
					$scope.isLoading = false;
					if(res.code == "200"){
						$state.go('houseInfo');
					}else{
						$scope.isAlert = true;
						$scope.msg = "提交接口异常,请稍候再试";
					}
				},
				error: function(err) {
					$scope.isAlert = true;
					$scope.msg = "提交接口异常,请稍候再试";
				},
			});
		}

		//校验参数
		$scope.verifyParams = {
			name: encodeURI($scope.stName),
			idCard: $scope.stIdCard,
			marriageStatue: $scope.maritalStatus.id,
			hj: encodeURI($scope.hjprovince.name),
			spouseName: encodeURI($scope.name_mate || ""),
			spouseIdCard: $scope.idCard_mate || "",
			spouseHj: (isBlank($scope.hjprovince_mate)) ? "" : encodeURI($scope.hjprovince_mate.name),
		}

		//校验接口
		$scope.checkInfo = function() {
			$scope.isLoading = true;
			$.ajax({
				type: "get",
				url: $.getConfigMsg.preUrlSelf + "/selfapi/withdrawalOfProvidentFund/checkInfo.do",
				dataType: "json",
				// jsonp: "jsonpCallback",
				data: $scope.verifyParams,
				success: function(res) {
					console.log(res);
					$scope.isLoading = false;
					appData.applyId = res.applyId;
					if(!isBlank(res.hyResult) && !isBlank(res.hyResult)) {
						if(res.hyResult.code == "200") {
							$scope.submitApplicantsAndHousingInfo();
						} else if(res.hyResult.code == "1002") {
							$scope.isAlert = true;
							$scope.msg = "您的婚姻信息核验未通过，不属于本平台受理范围，如您符合国家和住房公积金缴存地管理部门规定，请您携带业务材料前往公积金缴存地管理中心业务网点线下审核办理"
						} else if(res.hyResult.code == "500") {
							$scope.isAlert = true;
							$scope.msg = "网络或系统异常，请稍等再试！";
						} else {
							$scope.isAlert = true;
							$scope.msg = "校验异常,请检查确认信息！";
						}
					}
				},
				error: function(err) {
					$scope.isAlert = true;
					$scope.msg = "校验接口异常,请稍候再试";
				},
			});
		}

		$scope.checkInfo();
	}
});
app.controller("houseInfo", function($scope, $state, appData, $http, $timeout, $rootScope) {
//	$.getConfigMsg.preUrlSelf = "http://180.169.7.194:8081/ac-self";
	//检验并获取房屋信息
	$scope.checkHouseInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/withdrawalOfProvidentFund/checkHouseInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				applyId: appData.applyId ||'1d122798-62e6-42be-9f1f-b4b7119772ad',
				purchaseVerificationItems: $scope.purchaseVerificationItems || '01',
				propertyCertificateNum: '苏(2018)苏州市吴江区不动产权第9045662号',
				contractNum: '',
			},
			success: function(res) {
				console.log(res);
			},
			err: function(err) {
				console.log(err);
			}
		});
	}
	$scope.checkHouseInfo();
	//获取购房核查项
	$scope.getPurchaseVerificationItems = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/withdrawalOfProvidentFund/getPurchaseVerificationItems.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				applyId: appData.applyId,
				idMarried:appData.idMarried,
				hj: encodeURI(appData.hj),
			},
			success: function(res) {
				console.log(res);
//				if(res.code == "200") {
//					$scope.purchaseVerificationItems = res.purchaseVerificationItems;
//				} else if(res.code == "0") {
//					$scope.isAlert = true;
//					$scope.msg = "缴存地人员信息校验不通过！"
//				} else if(res.code == "101") {
//					$scope.isAlert = true;
//					$scope.msg = "缴存地人员信息校验接口异常！"
//				} else if(res.code == "201") {
//					$scope.isAlert = true;
//					$scope.msg = "获取购房核查项接口异常！"
//				}
			},
			err: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getPurchaseVerificationItems();
	$scope.nextStep = function(){
		$state.go('bankInfo');
	}
});
app.controller("bankInfo", function($scope, $state, appData, $http, $timeout, $rootScope) {
	//获取银行卡信息
	$scope.getBankInfo = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/withdrawalOfProvidentFund/getBankInfo.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				applyId: appData.applyId,
			},
			success: function(res) {
				console.log(res);
			},
			err: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getBankInfo();
});
app.controller("amountInfo", function($scope, $state, appData, $http, $timeout, $rootScope) {
	$scope.max = 123456.43;
});
app.controller('submit', function($state, $scope, appData) {
	$scope.itemName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.licenseName = appData.licenseName;
	$scope.date = getCurrentDate(2);
	$scope.nextText = "返回首页";
	$scope.sumbit = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrlSelf + "/selfapi/changjiangDelta/submit.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				applyNo: appData.applyNo,
			},
			success: function(res) {
				console.log(res);
			},
			error: function(err) {
				$scope.isAlert = true;
				$scope.msg = "提交接口异常,请稍候再试";
			},
		});
	}
	$scope.sumbit();
	//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: appData.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('长三角服务', '办理', appData.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, appData.funName, "长三角服务", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});