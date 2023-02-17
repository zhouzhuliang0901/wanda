app.controller("listController", function($scope, $route, $http, $location, data, $timeout) {
	window.location.href = "../declare/index.html";
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.itemName = "因教学、科学研究确需在无居民海岛采集生物和非生物标本的批准";
	$scope.allName = data.itemName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...';
	}
	$scope.itemName = name;
	$scope.pageSize = 6;
	$scope.currentPage = 1;
	$scope.totalPages = 1;
	$scope.itemList = [{
			"stStatusName": "新办",
			"itemId": "38a4e7b4-5ecc-4893-8e5c-207c2a773bb2"
		},
		{
			"stStatusName": "延续",
			"itemId": "ce23ad19-3689-4e75-b364-1722b97c11f9"
		},
		{
			"stStatusName": "依申请变更",
			"itemId": "27138075-1244-4892-8f65-f092e537ebf2"
		}
	];
	$scope.getSearchMatter = function() { // 查询事项
		var vConfig = {
			pageSize: 1,
			currentPage: 1,
			itemName: "在无居民海岛采集生物和非生物样本的审批",
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp($.getConfigMsg.declareUrl + '/aci/declare/getCNItemListByItemNameForPage.do', {
			params: vConfig
		}).success(function(dataJson) {
			$scope.item = dataJson.itemSetList[0];
			if(dataJson.itemSetList.length != 0) {
				data.organCode = $scope.item.organCode;
				data.organName = $scope.item.organName;
				data.itemNo = $scope.item.stItemNo;
				data.itemTenNo = $scope.item.stItemTenNo;
			} else {
				layer.alert("当前事项缺少必要的信息，无法办理！");
				$location.path("/list");
			}
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})

	};
//	$scope.getSearchMatter();

	$scope.getSubItem = function(statusName, itemId, index) {
		data.statusIndex = 1;
		data.statusName = statusName;
		data.itemId = itemId;
		$location.path("/guideline");
	};
});
app.controller("guidelineController", function($scope, $route, $http, $location, data, $timeout) {
	// 判断每一个值是否是undefined，如果不是保存到localStroage.xxx中，方便以后点击上一步，找到对应的数据。
	if(undefined != data.itemName && undefined != data.statusName && undefined != data.itemId){
		localStorage.setItem("itemName",data.itemName);
		localStorage.setItem("statusName",data.statusName);
		localStorage.setItem("itemId",data.itemId);
	}
	console.log(localStorage.getItem("itemName")+localStorage.getItem("statusName")+localStorage.getItem("itemId"));
	
	var name = data.itemName;
	if(undefined == name){
		name = localStorage.getItem("itemName");
	}
	var statusN = data.statusName
	if(undefined == statusN){
		statusN = localStorage.getItem("statusName");
	}
	$scope.pageSize = 8;
	$scope.currentPage = 1;
	$scope.totalPages = null;
	$scope.itemNameBefore = name;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = statusN;
 
	//设置滚动条速度
	$scope.rollingSpeed = 500;
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId == undefined ? localStorage.getItem("itemId") : data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp($.getConfigMsg.declareUrl + '/aci/declare/getCNGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideTitle = $scope.itemNameBefore + "--" + $scope.statusName;
			$scope.guideInfo = dataJson.guide;
			$scope.clDealAccording = dataJson.guide.clDealAccording.replace(/\n/g, "<br>");
			$scope.clDealType = dataJson.guide.clDealType.replace(/\n/g, "<br>");
			$scope.clRange = dataJson.guide.clRange;
			data.deal = dataJson.guide.clDealAccording;
			data.nmBelong = dataJson.guide.nmBelong;
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	$scope.getGuieInfo();
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("preController", function($scope, $route, $http, $location, data, $timeout) {
	// 判断每一个值是否是undefined，如果不是保存到localStroage.xxx中，方便以后点击上一步，找到对应的数据。
	if(undefined != data.itemName && undefined != data.statusName && undefined != data.statusIndex&& undefined != data.type&& undefined != data.deal){
		localStorage.setItem("itemName",data.itemName);
		localStorage.setItem("statusName",data.statusName);
		localStorage.setItem("statusIndex",data.statusIndex);
		localStorage.setItem("type",data.type);
		localStorage.setItem("deal",data.deal);
	}
	var name = data.itemName;
	if(undefined == name){
		name = localStorage.getItem("itemName");
	}
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	var statusName = data.statusName;
	if(undefined == statusName){
		statusName = localStorage.getItem("statusName");
	}
	var statusIndex = data.statusIndex;
	if(undefined == statusIndex){
		statusIndex = localStorage.getItem("statusIndex");
	}
	var type = data.type;
	if(undefined == type){
		type = localStorage.getItem("type");
	}
	var deal = data.deal;
	if(undefined == deal){
		deal = localStorage.getItem("deal");
	}
	$scope.itemName = name;
	$scope.statusName = statusName;
	$scope.statusIndex = statusIndex;
	$scope.type = type;
	$scope.deal = deal;
	$scope.nmBelong = data.nmBelong;
	
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	// 继续 
	$scope.next = function() {
//		$location.path("/select");
		window.location.replace("../login/index.html#/select?itemName="+$scope.itemName
		+"&statusIndex="+$scope.statusIndex+"&statusName="+$scope.statusName
		+"&nmBelong="+$scope.nmBelong
		+"&href=SW_islandSampling"+"&type="+$scope.type+"&deal="+$scope.deal);
	}
});
//app.controller("selectController", function($scope, $route, $http, $location, data, $timeout) {
//	$scope.identitySelect = true;
//	$scope.modeSelect = false;
//	$scope.legalPersonSelect = false;
//	
//	$scope.Individual = function(){
//		$scope.identitySelect = false;
//		$scope.modeSelect = true;
//		$scope.legalPersonSelect = false;
//	}
//	
//	$scope.legalPerson = function(){
//		$scope.identitySelect = false;
//		$scope.modeSelect = false;
//		$scope.legalPersonSelect = true;
//	}
//	
//	var name = data.itemName;
//	if(name.length > 10) {
//		name = name.slice(0, 10) + '...';
//	}
//	$scope.itemName = name;
//	// 刷身份证获取信息
//	$scope.scanIdcard = function() {
//		$location.path('/main')
//	}
//	// 随申办亮证
//	$scope.citizen = function() {
//		$location.path('/citizen')
//	}
//	// Ukey
//	$scope.key = function(){
//		$location.path('/key')
//	}
//	// 亮证
//	$scope.goCitizen = function(){
//		$location.path("/idcard").search({
//          from: "legalPerson"
//      })
//	}
//});
//app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout) {
//	var name = data.itemName;
//	if(name.length > 10) {
//		name = name.slice(0, 10) + '...'
//	}
//	$scope.itemName = name;
//	console.log(name);
//	// 扫描二维码
//	OcxControl.Barcode(function(res) {
//		var httpConfig = {
//			codeParam: res,
//			jsonpCallback: "JSON_CALLBACK"
//		};
//		OcxControl.Light.qrcodeLightClose();
//		OCX_Barcode.CloseConnection(); // 关闭二维码扫描
//		$http.jsonp($.getConfigMsg.declareUrl + "/aci/window/getInfoByCodeTest.do", {
//				params: httpConfig
//			})
//			.success(function(dataJsonp) {
//				if(dataJsonp.result.success) {
//					data.idCardName = dataJsonp.result.data.realname;
//					data.idCardNum = dataJsonp.result.data.idcard;
//					data.mobile = dataJsonp.result.data.mobile;
//					$timeout(function() {
//						$location.path('/capture');
//					}, 100)
//				} else {
//					layer.alert("error:" + dataJsonp.result.msg);
//					//					layer.msg(dataJsonp.result.msg);
//					$timeout(function() {
//						$location.path('/select');
//					}, 2000)
//				}
//			})
//			.error(function(err) {
//				console.log("二维码已过期！")
//			});
//	}, function(err) {
//		console.log("二维码扫描出错：" + err)
//	});
//});
//app.controller("mainController", function($scope, $route, $location, $http, data, $timeout) {
//	var name = data.itemName;
//	if(name.length > 10) {
//		name = name.slice(0, 10) + '...'
//	}
//	$scope.itemName = name;
//	// 读取身份证信息
//	$scope.readIdCard = function(idCardInfo) {
//		data.idCardInfo = idCardInfo;
//		data.idCardName = idCardInfo.idCardName;
//		data.idCardNum = idCardInfo.idCardNum;
//		data.startDate = idCardInfo.startDate;
//		data.endDate = idCardInfo.endDate;
//		data.HeadImg = idCardInfo.HeadImg;
//		$timeout(function() {
//			$location.path("/capture");
//		}, 100);
//	}
////	$scope.readIdcard();
////	data.idCardName = "测试";
////	data.idCardNum = "430426199804106174";
////	$location.path(getRoute(data.statusIndex));
//
//});
//app.controller("captureController", function($scope, $route, $http, $location, data, $timeout) {
//	var name = data.itemName;
//	try {
//		if(name.length > 10) {
//			name = name.slice(0, 10) + '...'
//		}
//		$scope.itemName = name;
//	} catch(e) {}
//	$scope.idCardInfo = data.idCardInfo;
//	$scope.getResult = function() {
//		$location.path(getRoute(data.statusIndex));
//	}
//});
app.controller("riverInfo1Controller", function($scope, $route, $http, $location, data, $timeout) {
	// 登录流程相关
	var url = decodeURI(window.location.href);
	var temp1 = url.split('?');
    var pram = temp1[1];
    var keyValue = pram.split('&');
    var obj = {};
    for (var i = 0; i<keyValue.length; i++){
        var item = keyValue[i].split('=');
        var key = item[0];
        var value = item[1];
        obj[key] = value;
    }
    console.log(obj);
	data.idCardName = obj.idCardName;//姓名
	data.idCardNum = obj.idCardNum;//身份证号
	data.itemName = obj.itemName;//事项名
	data.statusName = obj.statusName;//情形名
	data.type = obj.type;//事项类型
	// 页面相关
	// 新改扩
	$scope.fullItemName = data.itemName + "—— " + data.statusName;
	$scope.itemName = data.itemName;
	if(data.statusName == "新办"){
		data.itemCode = "310101243000-03";
	}else if(data.statusName == "延续"){
		data.itemCode = "310101243000-02";
	}else{
		data.itemCode = "310101243000-01";
	}
	if(data.itemCode) {
		$.ajax({
				type: "get",
				url: $.getConfigMsg.declareUrl + '/aci/getItemApplyPlace.do?',
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: {
					itemCodes: data.itemCode,
					regionCode:"SH00SH",
				},
				success: function(dataJson) {
					if(dataJson) {
						data.departCode = dataJson[0].bldCode; 
					} else {
						layer.msg("查询办理点信息失败，请稍后重试");
						$location.path("/matter");
					}
				},
				error:function(err){
					
				}
			});
	};
	// 单位类型
	$scope.ST_ORG_TYPE = ST_ORG_TYPE;
	$scope.selected_st_org_type = $scope.ST_ORG_TYPE[0];
	// 行政区
	$scope.DISTRICTS = DISTRICTS;
	$scope.selected_districts = $scope.DISTRICTS[0];
	// 证件类型
	$scope.ZZJGLX = ZZJGLX;
	$scope.selected_zzjglx = $scope.ZZJGLX[0];
	
	//执行一个laydate实例
	laydate.render({
	  elem: '#STARTDATE' ,//指定元素
	  trigger: 'click'
	});
	laydate.render({
	  elem: '#ENDDATE' ,//指定元素
	  trigger: 'click'
	});
	
	// 保存数据
	$scope.goNext = function() {
		// 判空操作，正式环境开放
		var blackArr = $(".true").siblings("input");
		if(!isInputBlack(blackArr)) {
			layer.msg('请输入必要的信息');
			return;
		}
		var info = {
			"ZZJGLX": {
				"name": $scope.selected_zzjglx.name,
				"value": $scope.selected_zzjglx.value
			},
			"mdata": "0QOEpDXGO4lM+Ft3RWAUJIcxnIgbp7p6H/ysL8mgE7NS5UqAhEWmYsnjO9JdhFj5ejkNekxZl9IQJJp3rDKHpjdafNHqmAcfpNaYw9DwtyD1EhYUurREQWM0A2TN5tP3icTgF9wA4dSID6kkDmvIjEe3+8R0MKuhjbQWSO8RjQJ8ICGe8K2hRMMQTgt2ucFruSukybrIdHS9JtrFJM3bRiZB4vqyRZj8oCJzwQVKCRbzyawTmFoeZh2dW8gTkrqiIi2Tl+VF3HWMNU1FhOrOTW/jSJplLuPA1Zn2pqI3bIYeA21NV7lGNJp8wumceUPbpM3iq+cYFokqBk4NLYYXbJL+cyxECXYrT1y1p2/U3tE=",
			"userType": "法人",
			"CJSYSBHYQ": $scope.CJSYSBHYQ,
			"username": data.idCardName,
			"eformCode": data.itemCode,
			"STARTDATE": {
				"value": $('#STARTDATE').val(),
				"unix": Date.parse(new Date($('#STARTDATE').val()))
			},
			"COMPANYCODE": $scope.COMPANYCODE,
			"userId": "",
			"ARTMPHONE": $scope.ARTMPHONE,
			"licenseType": {
				"name": "身份证",
				"value": "a"
			},
			"APPADDRESS": $scope.APPADDRESS,
			"LINKPOSTCODE": $scope.LINKPOSTCODE,
			"ARTIFICIALPERSON": $scope.ARTIFICIALPERSON,
			"PROJECTNAME": $scope.PROJECTNAME,
			"CJJHYJQKSM": $scope.CJJHYJQKSM,
			"DWTEL": $scope.DWTEL,
			"CJPOSTCARD06": $scope.CJPOSTCARD06,
			"CJPOSTCARD05": $scope.CJPOSTCARD05,
			"ST_LINK_ADDRESS": $scope.ST_LINK_ADDRESS,
			"CJPOSTCARD07": $scope.CJPOSTCARD07,
			"CJPOSTCARD02": $scope.CJPOSTCARD02,
			"CJPOSTCARD01": $scope.CJPOSTCARD01,
			"CJPOSTCARD04": $scope.CJPOSTCARD04,
			"CJPOSTCARD03": $scope.CJPOSTCARD03,
			"mobile": $scope.mobile,
			"CJJHHNR": $scope.CJJHHNR,
			"expId": "",
			"CJSEX06": $scope.CJSEX06,
			"CJSEX05": $scope.CJSEX05,
			"ZCZJ": $scope.ZCZJ,
			"DISTRICTS": {
				"name": $scope.selected_districts.name,
				"value": $scope.selected_districts.value
			},
			"CJSEX07": $scope.CJSEX07,
			"targetName": $scope.targetName,
			"CJSEX02": $scope.CJSEX02,
			"CJSEX01": $scope.CJSEX01,
			"CJSEX04": $scope.CJSEX04,
			"CJSEX03": $scope.CJSEX03,
			"ST_ORG_TYPE": {
				"name": $scope.selected_st_org_type.name,
				"value": $scope.selected_st_org_type.value
			},
			"licenseNo": data.idCardNum,
			"LINKFAX": $scope.LINKFAX,
			"LINKPHONE": $scope.LINKPHONE,
			"ENDDATE": {
				"value": $('#ENDDATE').val(),
				"unix": Date.parse(new Date($('#ENDDATE').val()))
			},
			"ST_LINK_NAME": $scope.ST_LINK_NAME,
			"LINKHANDSET": $scope.LINKHANDSET,
			"CJNAME01": $scope.CJNAME01,
			"ARTFAX": $scope.ARTFAX,
			"CJNAME05": $scope.CJNAME05,
			"CJNAME04": $scope.CJNAME04,
			"CJCOMPANY01": $scope.CJCOMPANY01,
			"CJNAME03": $scope.CJNAME03,
			"LINKMAN": $scope.LINKMAN,
			"CJNAME02": $scope.CJNAME02,
			"CJCOUNTRY07": $scope.CJCOUNTRY07,
			"CJCOMPANY04": $scope.CJCOMPANY04,
			"ARTPHONE": $scope.ARTPHONE,
			"CJCOMPANY05": $scope.CJCOMPANY05,
			"CJNAME07": $scope.CJNAME07,
			"CJCOUNTRY05": $scope.CJCOUNTRY05,
			"BEIZHU": $scope.BEIZHU,
			"CJCOMPANY02": $scope.CJCOMPANY02,
			"CJCOUNTRY06": $scope.CJCOUNTRY06,
			"CJNAME06": $scope.CJNAME06,
			"CJCOMPANY03": $scope.CJCOMPANY03,
			"CJCOUNTRY03": $scope.CJCOUNTRY03,
			"APPPOST": $scope.APPPOST,
			"CJCOUNTRY04": $scope.CJCOUNTRY04,
			"CJCOMPANY06": $scope.CJCOMPANY06,
			"CJCOUNTRY01": $scope.CJCOUNTRY01,
			"CJCOMPANY07": $scope.CJCOMPANY07,
			"CJCOUNTRY02": $scope.CJCOUNTRY02,
			"stuff19n10792": [],
			"stuff19n10791": [],
			"stuff19n00245": [],
			"stuff19n10793": [],
			"stuff19n10783": [],
			"stuffPreFill": {
				"cert": {},
				"img": {}
			}
		};
		var fConfig = {
			departCode: data.departCode,
			accessToken: "",
			info: info,
			itemCode: data.itemCode,
		};
		$http.get($.getConfigMsg.declareUrl + '/aci/saveInfo.do?', {
			params: {
				info: JSON.stringify(fConfig),
			}
		}).success(function(dataJson) {
			if(dataJson) {
				data.stApplyNo = dataJson.applyNo; // stApplyNo下边需要用到，统一社会审批编码
				$location.path("/materialUpload");
			} else {
				layer.msg("信息提交失败，请稍后重试");
			}
		}).error(function(e) {
			console.log(e)
		});
	};

	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});

app.controller("materialUploadController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;

	// 是否有材料列表
	$scope.haveList = true;
	$scope.noList = false;
	data.stuffListLength = 0;
	// 获取材料列表
	var fConfig = {
		itemCode: data.itemCode, // 事项编码
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp($.getConfigMsg.declareUrl + '/aci/getItemStuffs.do', {
			params: fConfig
		})
		.success(function(dataJson) {
			if(dataJson.isSuccess || dataJson.data) {
				if(dataJson.data.stuffs.length > 0) {
					$scope.haveList = true;
//					data.stuffListLength = dataJson.data.stuffs.length;
					$scope.stuffList = dataJson.data.stuffs;
					if(data.listImg == 0) {
						for(var s = 0; s < $scope.stuffList.length; s++) {
							data.listImg[s] = {
								'activeImg': null,
								'stuffName': $scope.stuffList[s].stuffName,
								'index': s
							}
							if($scope.stuffList[s].isMust == 1){
								data.stuffListLength++;
							}
						}
					}
					// 设置已上传文件图标的变化
					if(data.isUpload.length > 0) {
						for(var i = 0; i < data.isUpload.length; i++) {
							for(var j = 0; j < data.listImg.length; j++) {
								if(data.listImg[j].activeImg != 'images/state_1.png') {
									if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
										data.listImg[data.isUpload[i].index].activeImg = 'images/state_1.png';
									}
								}
							}
						}
					}
					$scope.listImg = data.listImg;
					// 设置 选择上传方式 时 材料名称标题 是否显示
					data.isShowStuffName = true;
				} else {
					$scope.haveList = false;
					$scope.stuffList = [];
					data.isShowStuffName = false;					
				}
			} else {
				layer.alert(dataJson.msg);
				$location.path("/matter");
			}
		}).error(function(e) {
			console.log(e)
		});

	// 判断材料是否上传 
	data.currentIndex++;

	// 查询电子证照库
	$scope.getCertsDataBase64 = function(certName, certNo) {
		var base64 = '';
		$http.jsonp($.getConfigMsg.declareUrl + "/aci/autoterminal/dzzz/queryCertBaseDatas.do", {
				params: {
					jsonpCallback: "JSON_CALLBACK",
					certNo: data.idCardNum,
					name: encodeURI(data.idCardName),
					type: "0",
					catMainCode: certNo,
					machineId: $.config.get("uniqueId"),
					itemName: encodeURI(certName),
					itemCode: data.itemCode,
					businessCode: "",
					startDay: data.startDate || '',
					endDay: data.endDate || '',
				}
			})
			.success(function(result) {
				$scope.isLoading = false;
				if(result.length == 0) {
					return false;
				} else {
					base64 = result[0].str;
				}
			})
			.error(function(err) {
				console.log(err);
				$scope.isLoading = false;
			})
		return base64;
	};

	// 点击材料列表上传  code是材料编号
	$scope.uploadMaterial = function(index, name, code, certs) {
		data.currentIndex = index;
		data.stuffName = name;
		data.stuffCode = code;
		if(certs.length > 0) {
			// 自动上传材料
			$scope.isLoading = true;
			var stuffId = '';
			for(var i = 0; i < certs.length; i++) {
				var base64 = $scope.getCertsDataBase64(certs[i].certName, certs[i].certCode);
				if(base64) {
					$.ajax({
						url: $.getConfigMsg.declareUrl + '/aci/uploadItemStuffs.do',
						type: "post",
						dataType: "json",
						data: {
							FileData: base64,
							applyNo: data.stApplyNo,
							stuffId: stuffId,
							stuffCode: data.stuffCode,
						},
						success: function(res) {
							if(res.isSuccess) {
								stuffId = res.stuffId;
								data.isUpload.push({
									index: data.currentIndex,
									stuffName: data.stuffName
								});
								data.listImg[data.currentIndex].activeImg = 'images/state_1.png';
							}
						},
						error: function(err) {
							console.log(err)
						}
					});
				} else {
					layer.alert("未查询到"+certs[i].certName+"证照，请手动上传");
					$location.path("/uploadMethod");
				}
			}
			$scope.isLoading = false;
//			data.listImg[data.currentIndex].activeImg = 'images/state_1.png';
		} else {
			$location.path("/uploadMethod");
		}
	};

	// 上传完文件 提交办件
	$scope.submit = function() {
//		$location.path('/infoFinish');
		if(data.isUpload.length >= data.stuffListLength) {
			$location.path('/infoFinish');
		} else {
			layer.msg('请提交必上传的材料!');
		}
	};
	$scope.returnList = function() {
		$scope.isLoading = false;
	}
	// 滚动条
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			preventDefault: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

});
app.controller("uploadMethodController", function($scope, $route, $http, $location, data, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stuffName = data.stuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	// 关闭高拍仪
	//   OcxControl.scanClose();
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U盘上传
	$scope.takePhoto = function() {
		layer.confirm('请确认是否插入U盘！', {
			btn: ['已插入U盘', '未插入U盘'] //按钮
		}, function() {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function() {
				$location.path('/takePhoto/U');
			}, 20);
		}, function() {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function() {
				layer.msg('请选择其他上传方式！');
			}, 20);
		});
	};
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/materialPic');
	};
});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	var $temp = new ImageShow(".imageShow");
	$scope.profileShow = function() {
		$.ajax({
			type: "get",
			async: true,
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				type: 0,
				username: "yun",
				password: "04b34557c2110962",
				stName: data.idCardName, // data.idCardName
				stIdNo: data.idCardNum // data.idCardNum	张：362330199307205799	于：412702199406084145
			},
			url: "http://31.0.1.212:8080/ac/aci/app/getLicenseStuffList.do",
			success: function(json) {
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson) { // !dataJson[0].address
					layer.alert('没有数据，请重新选择上传方式！', {
						skin: 'layui-layer-molv', //样式类名
						closeBtn: 0
					}, function() {
						$timeout(function() {
							layer.close();
							$location.path('/uploadMethod');
						}, 100);
					});
				} else {
					data.profile = dataJson;
					$temp.SetUrls(data.profile);
				}
			},
			error: function(err) {
				console.log(e)
			}
		});
	};
	$scope.goNext = function() {
		var page = $('.currentPage').html();
		page = page.split('/')[0] - 1;
		data.page = page;
		$location.path('/takePhoto/pic');
	}
	$scope.profileShow();
});
app.controller("takePhotoController", function($scope, $route, $http, $location, data, $timeout, $routeParams) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	if($routeParams.flag == 'U') { // U盘上传
		$.device.fileOpen(function(value) {
			$timeout(function() {
				$scope.UData = value;
				$('#showImg').hide();
				var list = '<div class="document"><div>待上传文件：</div><div>' +
					$scope.UData + '</div></div>';
				$('.camera').html(list);
			}, 100)
		});
	} else { // 个人档案上传
		$scope.waitUploadImgUrl = data.profile[data.page].address;
		$scope.imgName = data.profile[data.page].stLicenseName;
		//	'http://hengshui.5uban.com/ac' + 
		$('#showImg').attr('src', $scope.waitUploadImgUrl);
	}
	$scope.photoFlag = true;
	$scope.nextFlag = false;
	// 上一步
	//$scope.back = function() {
	//history.back();
	//}
	// 拍照
	$scope.highCapture = function() { // 高拍仪拍照
		if($routeParams.flag == 'U') { // U盘上传
			$scope.jsonData1 = {
				applyNo: data.stApplyNo, //   '751122018600008'
				stuffId: "",
				stuffCode: ""
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload($.getConfigMsg.declareUrl + "/aci/uploadItemStuffs.do", "FileData", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stuffName
					});
					$timeout(function() {
						$location.path('/materialUpload');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
			data.fileName.push($scope.UData);
		} else if($routeParams.flag == 'pic') { // 个人档案上传
			var loading = layer.load(1, {
				shade: [0.4, '#000'] //0.1透明度的白色背景
			});
			$scope.jsonData = {
				'applyNo': data.stApplyNo, //   '751122018600008'
				'projectid2': data.projectId2,
				'fileName': ""
			};
			$scope.jsonData = JSON.stringify($scope.jsonData);
			$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		$.getConfigMsg.declareUrl + 
				$scope.waitUploadImgUrl,
				"C:\\waitUploadImg.jpg",
				//将选中图片下载
				function(bytesCopied, totalBytes) {
					console.log(bytesCopied + "," + totalBytes);
				},
				function(result) {
					//将选中图片上传到服务器
					$.device.httpUpload($.getConfigMsg.declareUrl + '/aci/declare/uploadCNStuff.do', "file", "C:/waitUploadImg.jpg",
						$scope.jsonData,
						function(result) {
							layer.close(loading);
							layer.msg("上传成功");
							data.isUpload.push({
								index: data.currentIndex,
								stuffName: data.stuffName
							});
							$timeout(function() {
								$location.path('/materialUpload');
							}, 1000);
						},
						function(webexception) {
							layer.msg("上传失败");
						});
				},
				function(webexception) {
					layer.alert("下载文档失败");
				}
			);
			data.fileName.push($scope.imgName);
		}
		$scope.photoFlag = false;
		$scope.nextFlag = true;
//		data.isUpload.push({
//			index: data.currentIndex,
//			stuffName: data.stuffName
//		});
	};
});
app.controller("finishController", function($scope, $route, $http, $location, data, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.$on("$locationChangeStart", function() {
		OcxControl.scanClose(); // 关闭高拍仪
	});
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.capture = true; // 拍照按钮显示   重拍、上传按钮不显示
	data.uploadStuffId = "";
	
	OcxControl.scanOpen({ // 打开高拍仪
		height: "530",
		width: "745",
		left: "865",
		top: "300"
	}, function(data) {
		$('#showImg').attr('src', '');
	});

	

	// 重拍
	$scope.refresh = function() {
		OcxControl.scanClose(); // 关闭高拍仪
		OcxControl.scanOpen({ // 打开高拍仪
			height: "530",
			width: "745",
			left: "865",
			top: "300"
		}, function(data) {
			$scope.capture = true;
			$('#showImg').attr('src', '');
		});
	};

	// 拍照
	$scope.takePhoto = function() {
		var imgHTML = '';
		$scope.capture = false;
		OcxControl.scanSave2(function(scanImg) { // 高拍仪拍照	
			data.scanImg = scanImg;
			var params = {
				FileData: scanImg,
				applyNo: data.stApplyNo,
				stuffId: data.uploadStuffId,
				stuffCode: data.stuffCode,
			}
			// 上传附件
			$.ajax({
				url: $.getConfigMsg.declareUrl + '/aci/uploadItemStuffs.do',
				type: "post",
				dataType: "json",
				data: params,
				success: function(res) {
					if(res.isSuccess){
						data.uploadStuffId = res.stuffId;
					}else{
						data.uploadStuffId = 0;
					}
					if(data.listImg.length < 1) {
						data.currentIndex++; // 没有材料列表时   文件下标+1 
					}
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stuffName
					});
					data.fileName.push('扫描文件');

					imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="data:image/png;base64,' + scanImg + '" width="150" height="200" /><p onclick="del(\'' + data.uploadStuffId + '\')" class="del">X</p></div>';
					$('.imgBox').html(imgHTML);
				},
				error: function(err) {
					if(res.isSuccess){
						data.uploadStuffId = res.stuffId;
					} else{
						data.uploadStuffId = 0;
					}
					if(data.listImg.length < 1) {
						data.currentIndex++; // 没有材料列表时   文件下标+1 
					}
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stuffName
					});
					data.fileName.push('扫描文件');

					imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="data:image/png;base64,' + scanImg + '" width="150" height="200" /><p onclick="del(\'' + data.uploadStuffId + '\')" class="del">X</p></div>';
					$('.imgBox').html(imgHTML);
				}
			});
		});
	};

	// 上传
	$scope.upload = function() {
		OcxControl.scanClose(); // 关闭高拍仪
		$timeout(function() {
			layer.confirm('上传成功！', {
				btn: ['继续上传', '完成上传'] //按钮
			}, function() {
				$('.layui-layer-shade').hide();
				$('.layui-layer').hide();
				$scope.capture = true;
				$timeout(function() {
					OcxControl.scanOpen({ // 打开高拍仪
						height: "530",
						width: "745",
						left: "865",
						top: "300"
					}, function(data) {
						$('#showImg').attr('src', '');
					});
				}, 20);
			}, function() {
				$('.layui-layer-shade').hide();
				$('.layui-layer').hide();
				$scope.capture = false;
				$timeout(function() {
					$location.path('/materialUpload');
				}, 20);
			});
		}, 100);
	}

	// 完成上传，返回材料清单
	$scope.finishUpload = function() {
		$timeout(function() {
			OcxControl.scanClose(); // 关闭高拍仪
			$location.path('/materialUpload');
		}, 20);
	};
});
app.controller("infoFinishController", function($scope, $route, $http, $location, data, $timeout, $sce) {
	$scope.allName = data.itemName + '--' + data.statusName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 生成办件编码
	$scope.finishData = {
		applyNo: data.stApplyNo,
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp($.getConfigMsg.declareUrl + '/aci/submitItem.do', {
		params: $scope.finishData
	}).success(function(dataJson) {
		console.log(dataJson);
	}).error(function(e) {
		console.log(e)
	});

	$scope.qrcode = new QRCode("code", {
		text: 'http://zwdt.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg2.jsp?ST_WF_ID=' + $scope.applyNo + '&IdOrCode=' + data.idCardName,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});

	$scope.applyNo = data.stApplyNo;
	$scope.statusText = '打印凭证';
	// 打印凭条
	var f = 0;
	$scope.print = function() {
		f++;
		$scope.statusText = '返回首页';
		if($scope.statusText == '返回首页' && f == 2) {
			$.device.GoHome();
		} else {
			OcxControl.receiptPrint(
				'自助申报申请回执\n' +
				'@qrcode@' + 'http://zwdt.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg2.jsp?ST_WF_ID=' + $scope.applyNo + '&IdOrCode=' + data.idCardName + '@qrcode@\n' +
				'\n事项名称：' + $scope.allName +
				'\n事件编码：' + $scope.applyNo +
				'\n您可至 “一网通办” 总门户 “用户中心” 查询办理进度，或通过扫描上方二维码查询\n请您携带办理材料至 上海市长宁区水务局 进行办理\n',
				function(success) {
					OcxControl.receiptPrintClose();
				},
				function(error) {
					layer.msg('打印失败');
				});
		}
	};
});
//法人
//app.controller("keyController", function ($rootScope, $rootScope, $scope, $route, $http, $location, $timeout, data) {
//	var name = data.itemName;
//	if(name.length > 10) {
//		name = name.slice(0, 10) + '...'
//	}
//	$scope.itemName = name;
//  $scope.password = "";
//  $scope.goCitizen = function () {
//      $location.path("/idcard").search({
//          from: "legalPerson"
//      })
//  };
//  $scope.validate = function () {
//      if (!$scope.password) {
//          layer.msg("密码不能为空！");
//          return;
//      }
//      /* Change the path and password below */
//      $scope.strpripath = "com1";
//      $scope.strcertpath = "com1";
//      $scope.strcertchainpath = "com1";
//      // 请输入证书验证参数
//      $scope.ConfigurationNum = parseInt("1");
//      // 请输入USB设备参数
//      $scope.DevNumber = parseInt("10");
//      //初始化函数
//      SafeEngineCtl.SEH_InitialSession($scope.DevNumber, $scope.strpripath, $scope.password, 0, $scope.DevNumber, $scope.strpripath, "");
//      if (SafeEngineCtl.ErrorCode != 0) {
//          layer.msg("验证不通过，请重新输入验证密码或者插入key");
//          return;
//      }
//      //配置参数
//      SafeEngineCtl.SEH_SetConfiguration($scope.ConfigurationNum);
//      if (SafeEngineCtl.ErrorCode != 0) {
//          layer.msg("验证不通过，请重新输入验证密码或者插入key");
//          SafeEngineCtl.SEH_ClearSession();
//          return;
//      }
//      //获取证书内容
//      $scope.strCert = SafeEngineCtl.SEH_GetSelfCertificate($scope.DevNumber, $scope.strcertpath, "");
//      if (SafeEngineCtl.ErrorCode != 0) {
//          layer.msg("验证不通过，请重新输入验证密码或者插入key");
//          return;
//      }
//
//      //获取证书细目	14
//      //获取证书中的企业名称
//      $scope.companyName = SafeEngineCtl.SEH_GetCertDetail($scope.strCert, 14);
//      //获取证书中企业编码
//      $scope.stIdNo = SafeEngineCtl.SEH_GetCertInfoByOID($scope.strCert, "1.2.156.112570.11.210");
//      SafeEngineCtl.SEH_ClearSession();
//      if ($scope.companyName != undefined && $scope.companyName != null && $scope.companyName != "" && $scope.companyName != " ") {
//          data.companyName = $scope.companyName;
//          data.companyStIdNo = $scope.stIdNo;
//          data.dataType = '1';
//          data.idCardName = $scope.companyName;
//          data.idCardNum = $scope.stIdNo;
//          $rootScope.personType = '法人';
//          $location.path(getRoute(data.statusIndex));
//      }
//  };
//});
//app.controller("idcardController", function ($scope, $rootScope, $route, $http, $location, data, $timeout) {
//  //获取身份证信息
//  data.licenseGather = undefined;
//  $scope.readIdCard = function () {
//      PROMISE_METHOD.getIdCardInfo()
//          .then(function (dataObj) {
//              var list = JSON.parse(dataObj.identityInfo);
//				// 格式化日期方法的参数 pattern
//				var pattern = /(\d{4})(\d{2})(\d{2})/;
//				data.dataSource = 'idcard';
//				data.idCardNum = list.Code;
//				data.idCardName = list.Name;
//				// 格式化日期
//				data.ValidPeriod = list.ValidPeriod.split("-");
//				data.startDay = data.ValidPeriod[0].replace(pattern, '$1-$2-$3');
//				data.endDay = data.ValidPeriod[1].replace(pattern, '$1-$2-$3').trim();
//              data.frontImg = "data:image/png;base64," + dataObj.frontImg;
//              data.backImg = "data:image/png;base64," + dataObj.backImg;
//              data.dataNumber = list.Code;
//              data.dataName = list.Name;
//              data.dataCode = list.Code;
//              if (data.dataType == '0') {
//                  $rootScope.personType = "个人";
//              } else {
//                  $rootScope.personType = "法人";
//              }
//
//              $location.path(getRoute(data.statusIndex));
//              $scope.$apply();
//          })
//          .catch(function (err) {
//              console.log(err);
//          });
//  };
//  $scope.readCitizenCloud = function () {
//      PROMISE_METHOD.getQrCodeInfo()
//          .then(function (code) {
//              return new Promise(function (resolve, reject) {
//                  data.dataSource = 'code';
//                  var maxLoginCount = 3;
//                  var httpConfig = {
//                      codeParam: code,
//                      jsonpCallback: "JSON_CALLBACK",
//                      using: "",
//						lzAddress: "",
//						machineId: $.config.get('uniqueId'),
//						itemName: "",
//						itemCode: "",
//						bussinessCode: ""
//                  };
//
//                  function _login() {
//                      $http.jsonp("http://116.239.7.225:8080/ac/aci/window/getInfoByCodeTest.do", {
//                              params: httpConfig
//                          })
//                          .success(function (dataJsonp) {
//
//                              resolve(dataJsonp);
//                          })
//                          .error(function (err) {
//                              if (maxLoginCount > 0) {
//                                  //最多maxLoginCount次重新登录
//                                  --maxLoginCount;
//                                  $timeout(function () {
//                                      _login();
//                                  }, 500);
//                              } else {
//                                  reject("接口出错：" + err);
//                              }
//                          });
//                  }
//                  _login();
//              })
//          })
//          .then(function (dataObj) {
//              var idcardInfo = dataObj.result.data;
//              console.log(JSON.stringify(dataObj));
//              if (data.dataType == '0') {
//                  if (dataObj.url == "") {
//                      layer.msg("未识别到证照信息！", {
//                          time: 5000
//                      });
//                      $route.reload();
//                      throw Error("未识别到证照信息！");
//                  }
//                  if (dataObj.code !== idcardInfo.idcard) {
//                      layer.msg("二维码类型错误，请扫描市民亮证个人二维码", {
//                          time: 2000
//                      });
//                      $route.reload();
//                      throw Error("二维码类型错误，请扫描市民亮证个人二维码");
//                  }
//              } else {
//                  if (dataObj.code == idcardInfo.idcard) {
//                      layer.msg("未识别到您的企业信息，请确认二维码正确后重新扫描！", {
//                          time: 5000
//                      });
//                      $route.reload();
//                      throw Error("未识别到您的企业信息，请确认二维码正确后重新扫描！");
//                  }
//              }
//              data.idCardName = idcardInfo.realname;
//              data.idCardNum = idcardInfo.idcard;
//
//              data.dataName = idcardInfo.realname;
//              data.dataCode = dataObj.code;
//
//              localStorage.setItem("portrait", $.getConfigMsg.preUrl + dataObj.url);
//              return new Promise(function (resolve, reject) {
//                  resolve("licenseLibrary");
//              })
//          })
//          .then(function (fileName) {
//              data.backImg = data.frontImg = localStorage.getItem("portrait");
//              if (data.dataType == '0') {
//                  $rootScope.personType = "个人";
//              } else {
//                  $rootScope.personType = "法人";
//              }
//              $location.path(getRoute(data.statusIndex));
//              $scope.$apply();
//
//              return new Promise(function (resolve, reject) {
//                  resolve("licenseLibrary");
//              });
//          })
//          .catch(function (err) {
//              console.log(err);
//          })
//  };
//  $scope.readCitizenCloud.isOpen = true;
//  // $scope.cloudActive = true;
//  if ($location.search().from == 'legalPerson') {
//      $scope.readIdCard.isClose = true;
//      data.dataType = "1";
//      $scope.readCitizenCloud();
//  } else {
//      data.dataType = '0';
//      $scope.readIdCard();
//      // $scope.currentLogin = 'cloud'
//      // $scope.readCitizenCloud();
//  }
//});