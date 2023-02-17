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
app.controller('loginType', function($state, $scope, appData, $rootScope, appFactory) {
	removeAnimate($('.scrollBox2'))
	addAnimate($('.scrollBox2'))
	appData.xsbb = false;
	let test = 1;
	console.log(Array.isArray(test));

	appData.funName = "劳动者基本信息查询及维护";
	appData.itemCode = "312090047000";
	$scope.operation = "请选择登录方式";
	//获取统一审批编码
	$scope.getApplyNoByItemNo = function(code) {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + '/aci/workPlatform/elderlyCard/getApplyNoByItemNo.do',
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				itemCode: code
			},
			success: function(dataJson) {
				if(dataJson.success === true) {
					appData.applyNo = dataJson.aplyNo;
				} else {
					appData.applyNo = "";
				}
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.getApplyNoByItemNo(appData.itemCode);
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('login', function($scope, $http, $state, appData, $rootScope, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
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
			$state.go("info");
		}
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
		$state.go("info");
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
app.controller('info', function($state, $scope, appData, appFactory, $rootScope, $timeout, $http) {
	$scope.operation = "请填写基本信息";
	appData.xsbb = true;
	$scope.nextText = "提交";
	$scope.otherCity = true;
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$scope.isLoading = false;
	}
	$scope.stIdCard = appData.licenseNumber;
	$scope.stName = appData.licenseName;
	$scope.realtionProvinceList = [{
		proId: "310000",
		province: "上海市"
	}];
	$scope.regProvinceList = $rootScope.provinceList;
	//文化程度
	$scope.edudegreeList = edudegree;
	//健康状态
	$scope.healthInfoList = healthInfo;
	//残疾状况
	$scope.deformTypeList = deformType;

	//获取用户id
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "json",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson.zwdtsw_user_id != undefined && dataJson.zwdtsw_user_id != null && dataJson.zwdtsw_user_id != "") {
					appData.zwdtsw_user_id = dataJson.zwdtsw_user_id;
				} else {
					$scope.isAlert = true;
					$scope.msg = dataJson.ERROR || '未获得对应的用户标识！';
					$scope.alertConfirm = function() {
						$state.go("loginType");
					}
				}
			},
			error: function(err) {
				console.log("getUserInfoByAccessToken err");
				$scope.isAlert = true;
				$scope.msg = '未获得对应的用户标识！';
				$scope.alertConfirm = function() {
					$state.go("loginType");
				}
			}
		});
	}
	if(appData.zwdtsw_user_id) {} else {
		$scope.getUserInfoByAccessToken();
	}
	//监听省市 区域变化
	let change = function() {
		$timeout(function() {
			selectBlur();
			$scope.realtionProvince = $scope.realtionProvinceList[0];
			$scope.$watch("realtionProvince", function(val) {
				if(val) {
					appFactory.getDictionary(val.proId, "", "", function(dataJson) {
						$scope.realtionCountyList = dataJson;
						if(!isBlank($scope.per_reladdr_provincecode)) {
							$scope.realtionCounty = filterByInfo($scope.realtionCountyList, $scope.per_reladdr_provincecode, "cityId");
							$scope.per_reladdr_provincecode = "";
						}
					});
					if(val.province == "上海市") {
						$scope.otherCity = false;
					} else {
						$scope.otherCity = true;
					}
				}
			});
			$scope.$watch("realtionCounty", function(val) {
				if(val) {
					appFactory.getDictionary("", val.cityId, "", function(dataJson) {
						$scope.realtionStreetList = dataJson;
						console.log($scope.per_reladdr_streetcode + "-----");
						if(!isBlank($scope.per_reladdr_streetcode)) {
							$scope.realtionStreet = filterByInfo($scope.realtionStreetList, $scope.per_reladdr_streetcode, "streetId");
							$scope.per_reladdr_streetcode = "";
						}
					});
				}
			});
			$scope.$watch("realtionStreet", function(val) {
				if(val) {
					appFactory.getDictionary("", "", val.streetId, function(dataJson) {
						$scope.realtionLaneList = dataJson;
						console.log($scope.per_reladdr_lanecode + "-----");
						if(!isBlank($scope.per_reladdr_lanecode)) {
							$scope.realtionLane = filterByInfo($scope.realtionLaneList, $scope.per_reladdr_lanecode, "jwhId");
							$scope.per_reladdr_lanecode = ""
						}
					});
				}
			});
			// $scope.$watch("regProvince", function(val) {
			// 	if(val) {
			// 		appFactory.getDictionary(val.proId, "", "", function(dataJson) {
			// 			$scope.regCountyList = dataJson;
			// 			if(!isBlank($scope.per_regaddr_provincecode)) {
			// 				$scope.regCounty = filterByInfo($scope.regCountyList, $scope.per_regaddr_provincecode, "cityId");
			// 				$scope.per_regaddr_provincecode = ""
			// 			}
			// 		});
			// 		if(val.province == "上海市") {
			// 			$scope.otherCity = false;
			// 		} else {
			// 			$scope.otherCity = true;
			// 		}
			// 	}
			// });
			// $scope.$watch("regCounty", function(val) {
			// 	if(val) {
			// 		appFactory.getDictionary("", val.cityId, "", function(dataJson) {
			// 			$scope.regStreetList = dataJson;
			// 			if(!isBlank($scope.per_regaddr_streetcode)) {
			// 				$scope.regStreet = filterByInfo($scope.regStreetList, $scope.per_regaddr_streetcode, "streetId");
			// 				$scope.per_regaddr_streetcode = ""
			// 			}
			// 		});
			// 	}
			// });
			// $scope.$watch("regStreet", function(val) {
			// 	if(val) {
			// 		appFactory.getDictionary("", "", val.streetId, function(dataJson) {
			// 			$scope.regLaneList = dataJson;
			// 			if(!isBlank($scope.per_regaddr_lanecode)) {
			// 				$scope.regLane = filterByInfo($scope.regLaneList, $scope.per_regaddr_lanecode, "jwhId");
			// 				$scope.per_regaddr_lanecode = ""
			// 			}
			// 		});
			// 	}
			// });

			$scope.$watch("per_healthInfo", function(val) {
				if(val) {
					if(val.id == "5") {
						$scope.isDeformity = true;
					} else {
						$scope.isDeformity = false;
					}
				}
			});
		}, 100);
	}

	//劳动者个人联系信息查询接口
	$scope.getRelationInfo = function() {
		$scope.isLoading = true;
		$scope.getInfoParamStr = {
			cert_type: "01",
			cert_id: appData.licenseNumber,
			per_name: encodeURI(appData.licenseName),
			qry_type: "1"
		}
		appFactory.pro_fetch("LD0017Q2", "ld", appData.applyNo, JSON.stringify($scope.getInfoParamStr), function(dataJson) {
			console.log(dataJson);
			if(!isBlank(dataJson)) {
				$scope.head = dataJson.data.msg.head;
				$scope.body = dataJson.data.msg.body;
				if(!isBlank($scope.head)) {
					if($scope.head.rst.buscode == "000000") {
						$scope.infoResult = $scope.body;
						/*联系信息*/
						$scope.per_reltel = $scope.infoResult.per_reltel;
						$scope.per_nation = $scope.infoResult.per_nation;
						$scope.nation = filterByInfo(nation, $scope.per_nation, "name");
						$scope.per_edudegreeName = $scope.infoResult.per_edudegree;
						$scope.per_edudegree = filterByInfo($scope.edudegreeList, $scope.per_edudegreeName, "name");
						$scope.per_postcode = $scope.infoResult.per_postcode;
						$scope.party = filterByInfo(party, $scope.infoResult.per_party, "name");
						//联系地址 区划字典
						$scope.per_reladdr_provincecode = $scope.infoResult.per_reladdr_provincecode;
						$scope.per_reladdr_province = $scope.infoResult.per_reladdr_province;
						$scope.per_reladdr_streetcode = $scope.infoResult.per_reladdr_streetcode;
						$scope.per_reladdr_street = $scope.infoResult.per_reladdr_street;
						$scope.per_reladdr_lanecode = $scope.infoResult.per_reladdr_lanecode;
						$scope.per_reladdr_lane = $scope.infoResult.per_reladdr_lane;
						//户籍地址  区划字典
						$scope.per_regaddr_provincecode = $scope.infoResult.per_regaddr_provincecode;
						$scope.per_regaddr_province = $scope.infoResult.per_regaddr_province;
						$scope.per_regaddr_streetcode = $scope.infoResult.per_regaddr_streetcode;
						$scope.per_regaddr_street = $scope.infoResult.per_regaddr_street;
						$scope.per_regaddr_lanecode = $scope.infoResult.per_regaddr_lanecode;
						$scope.per_regaddr_lane = $scope.infoResult.per_regaddr_lane;
						$scope.isLoading = false;
						change();
						$scope.regProvince = getKeyByChild($rootScope.provinceList, $scope.per_regaddr_provincecode.toString());
						$scope.regCounty = $scope.per_regaddr_province;
						if($scope.per_regaddr_provincecode == "310000") {
							$scope.otherCity1 = false;
							$scope.regStreet = $scope.per_regaddr_streetcode;
							$scope.regLane = $scope.per_regaddr_lanecode;
						} else {
							$scope.otherCity1 = true;
						}
						//详细地址
						$scope.per_reladdr = $scope.infoResult.per_reladdr;
						$scope.per_regaddr = $scope.infoResult.per_regaddr;
						/*学籍信息*/
						if($scope.infoResult.per_studyexpinfos.rows != 0) {
							$scope.studentInfo = $scope.infoResult.per_studyexpinfos.per_studyexpinfo;
							$scope.begindate = formatDateCustom($scope.studentInfo.begindate.toString());
							$scope.enddate = formatDateCustom($scope.studentInfo.enddate.toString());
							$scope.schoolname = $scope.studentInfo.schoolname;
							$scope.speciality = $scope.studentInfo.speciality;
							$scope.qrzstatus = ($scope.studentInfo.qrzstatus == "全日制") ? "是" : "否";
						}
						console.log($scope.infoResult);
					} else {
						$scope.isAlert = true;
						$scope.msg = $scope.head.rst.errmsg;
					}
				} else {
					$scope.isAlert = true;
					$scope.msg = "查询接口异常,请稍后再试";
				}
			} else {
				$scope.isAlert = true;
				$scope.msg = "查询接口异常,请稍后再试";
			}
		}, function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "查询接口异常,请稍后再试";
		});
	}
	$scope.getRelationInfo();

	//获取学信网信息
	$scope.getXuexinInfo = function() {
		$http.get($.getConfigMsg.preUrlSelf + "/selfapi/pensionAdjustment/queryEducationalInfoForPer.do", {
			params: {
				name: encodeURI(appData.licenseName),
				idCard: appData.licenseNumber,
			}
		}).success(function(dataJson) {
			if(dataJson.code == "200") {
				let result = null;
				if(Array.isArray(dataJson.data.xjxl.xl)){
					result = dataJson.data.xjxl.xl[0];
				}else{
					result = dataJson.data.xjxl.xl;
				}
				if(!isBlank(result)) {
					$scope.begindate = formatDateCustom(result.byrq.toString());
					$scope.enddate = formatDateCustom(result.rxrq.toString());
					$scope.schoolname = result.yxmc;
					$scope.speciality = result.zymc;
					$scope.qrzstatus = (result.xxxs == "普通全日制") ? "是" : "否";
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "获取学信网信息失败,请稍后再试";
				}
			} else {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "获取学信网信息失败,请稍后再试";
			}
		}).error(function(err) {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = "获取学信网信息失败,请稍后再试";
		});
	}

	//提交
	$scope.nextStep = function() {
		var condFlag = false;
		do {
			if(isBlank($scope.per_reltel)) {
				$scope.isAlert = true;
				$scope.msg = "请填写联系电话！";
				return;
			}
			if(isBlank($scope.per_edudegree)) {
				$scope.isAlert = true;
				$scope.msg = "请选择文化程度";
				return;
			}
			if(isBlank($scope.per_postcode)) {
				$scope.isAlert = true;
				$scope.msg = "请输入邮编";
				return;
			}
			if(isBlank($scope.realtionProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择联系地址（省/市）";
				return;
			}
			if(isBlank($scope.realtionCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择联系地址（区/县）";
				return;
			}
			if(isBlank($scope.realtionStreet)) {
				$scope.isAlert = true;
				$scope.msg = "请选择联系地址（街道）";
				return;
			}
			if(isBlank($scope.realtionLane)) {
				$scope.isAlert = true;
				$scope.msg = "请选择联系地址（居委）";
				return;
			}
			if(isBlank($scope.per_reladdr)) {
				$scope.isAlert = true;
				$scope.msg = "请输入联系详细地址";
				return;
			}
			if(isBlank($scope.per_healthInfo)) {
				$scope.isAlert = true;
				$scope.msg = "请选择健康状况！";
				return;
			}
			if(isBlank($scope.per_deformType) && $scope.isDeformity == true) {
				$scope.isAlert = true;
				$scope.msg = "请选择残疾类型";
				return;
			}
			if(isBlank($scope.regProvince)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（省/市）";
				return;
			}
			if(isBlank($scope.regCounty)) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（区/县）";
				return;
			}
			if(isBlank($scope.regStreet) && $scope.otherCity == false) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（街道）";
				return;
			}
			if(isBlank($scope.regLane) && $scope.otherCity == false) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址（居委）";
				return;
			}
			if(isBlank($scope.per_regaddr)) {
				$scope.isAlert = true;
				$scope.msg = "请输入户籍详细地址";
				return;
			}

		} while (condFlag);
		//提交参数集合
		condFlag = true;
		//办件信息同步接口
		$scope.saveItemInfo = function(result) {
			$http.get($.getConfigMsg.preUrlSelf + "/selfapi/pensionAdjustment/saveItemInfo.do", {
				params: {
					userId: appData.zwdtsw_user_id,
					itemCode: encodeURI(appData.itemCode),
					itemName: encodeURI(appData.funName),
					username: encodeURI(appData.licenseName),
					mobile: "",
					idCardNo: appData.licenseNumber,
					result: result
				}
			}).success(function(dataJson) {
				if(dataJson.code == "200") {
					appData.applyNo = dataJson.data.applyNo;
					if(result == "1") {
						$state.go("submit");
					} else {
						$state.go("loginType");
					}
				} else {
					$scope.isLoading = false;
					appData.applyNo = "";
					$scope.isAlert = true;
					$scope.msg = "办件信息同步失败,请稍后再试";
				}
			}).error(function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "办件信息同步异常,请稍后再试";
			});
		}
		//保存户籍信息
		$scope.saveRegInfo = function() {
			$scope.isLoading = true;
			$scope.paramStr = {
				cert_type: "01",
				cert_id: appData.licenseNumber,
				person_name: appData.licenseName,
				type_id: "2",
				cper_regaddr_provincecode: $scope.regCounty.cityId,
				cper_regaddr_streetcode: (isBlank($scope.regStreet)) ? "" : $scope.regStreet.streetId,
				cper_regaddr_lanecode: (isBlank($scope.regLane)) ? "" : $scope.regLane.jwhId,
				cper_regaddr: $scope.per_regaddr
			}
			console.log($scope.saveParamStr);
			appFactory.pro_fetch("LD0017S5", "ld", appData.applyNo, encodeURI(JSON.stringify($scope.paramStr)), function(dataJson) {
				console.log(dataJson);
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.data.msg.head;
					$scope.body = dataJson.data.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.saveResult = $scope.body;
							$scope.saveItemInfo("1");
						} else {
							$scope.isLoading = false;
							$scope.isAlert = true;
							$scope.msg = "保存信息异常，请确定后在重试";
							$scope.alertConfirm = function() {
								$scope.isAlert = false;
								$scope.saveItemInfo("0");
							}
						}
					} else {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "保存接口异常,请稍后再试";
					}
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "保存接口异常,请稍后再试";
				}
			}, function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "保存接口异常,请稍后再试";
			});
		}
		//人社不见面统一 --个人信息 发放形式保存接口
		$scope.saveApplyInfo = function() {
			$scope.isLoading = true;
			if($scope.per_reladdr == $scope.infoResult.per_reladdr) {
				$scope.chkreladdr = "0";
			} else {
				$scope.chkreladdr = "1";
			}
			$scope.saveParamStr = {
				per_certtype: "01",
				per_certno: appData.licenseNumber,
				per_xm: appData.licenseName,
				chkpersoninfo: "0",
				per_mz: $scope.nation.id,
				per_edudegree: $scope.per_edudegree.id,
				per_party: $scope.party.id,
				chkreladdr: $scope.chkreladdr,
				per_postcode: $scope.per_postcode,
				per_relregaddr_qx: $scope.realtionCounty.cityId,
				per_relregaddr_jd: $scope.realtionStreet.streetId,
				per_relregaddr_jw: $scope.realtionLane.jwhId,
				per_relregaddr: $scope.per_reladdr,
				per_reltel: $scope.per_reltel,
				chknzf: ($scope.infoResult.per_nzf == "") ? "0" : "1",
				per_nzfdate: $scope.infoResult.per_nzf_date,
				chkfamilyinfo: "0",
				chkarmyinfo: "0",
				chkhealthinfo: "0",
				occuptnRecno: "",
				foreignlangno: "",
				techtitlerecno: "",
				honorrecno: "",
				studyexprecno: "",
				workexprecno: "",
				chkworkbook: "0",
				workbookrecno: "0",
				workbookscdjzh: "0"
			}
			console.log($scope.saveParamStr);
			appFactory.pro_fetch("LD0017S2", "ld", appData.applyNo, encodeURI(JSON.stringify($scope.saveParamStr)), function(dataJson) {
				console.log(dataJson);
				if(!isBlank(dataJson)) {
					$scope.head = dataJson.data.msg.head;
					$scope.body = dataJson.data.msg.body;
					if(!isBlank($scope.head)) {
						if($scope.head.rst.buscode == "000000") {
							$scope.saveResult = $scope.body;
							// $scope.saveRegInfo();
							$scope.saveItemInfo("1");
						} else {
							$scope.saveItemInfo("0");
							$scope.isAlert = true;
							$scope.msg = "";
							//							$scope.saveItemInfo("0");
						}
					} else {
						$scope.isLoading = false;
						$scope.isAlert = true;
						$scope.msg = "保存接口异常,请稍后再试";
					}
				} else {
					$scope.isLoading = false;
					$scope.isAlert = true;
					$scope.msg = "保存接口异常,请稍后再试";
				}
			}, function(err) {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = "保存接口异常,请稍后再试";
			});
		}
		$scope.saveApplyInfo();
	}
});
app.controller('submit', function($state, $scope, appData) {
	$scope.funName = appData.funName;
	$scope.applyNo = appData.applyNo;
	$scope.nextText = "返回首页";
//模块使用记录
	$scope.jsonStr = {
		SUCCESS: "true",
		data: {
			name: $scope.funName,
			Number: $scope.applyNo,
		}
	}
	recordUsingHistory('人社服务', '办理', $scope.funName, appData.licenseName, appData.licenseNumber, '', $scope.applyNo, JSON.stringify($scope.jsonStr));
	//行为分析(办理)
	trackEventForAffairs($scope.applyNo, $scope.funName, "上海市人力资源社会保障局", appData.licenseName, appData.licenseNumber, '');
	$scope.goHome = function() {
		$.device.GoHome();
	}
});