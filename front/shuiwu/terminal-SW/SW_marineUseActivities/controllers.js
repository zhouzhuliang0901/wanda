app.controller("listController", function($scope, $route, $http, $location, data, $timeout) {
	window.location.href = "../declare/index.html";
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.itemName = "临时海域使用活动审批";
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
			"itemId": "eb256c6e-33e8-44cf-b536-344ddec138a6"
		},
		{
			"stStatusName": "依申请变更",
			"itemId": "17fdf3f0-1aac-49bc-bb64-82f13abdffd8"
		}
	];
	$scope.getSearchMatter = function() { // 查询事项
		var vConfig = {
			pageSize: 1,
			currentPage: 1,
			itemName: "特殊性临时用海申请的审批",
			jsonpCallback: "JSON_CALLBACK",
		};
//		$http.jsonp($.getConfigMsg.declareUrl + '/aci/declare/getCNItemListByItemNameForPage.do', {
//			params: vConfig
//		}).success(function(dataJson) {
//			$scope.item = dataJson.itemSetList[0];
//			if(dataJson.itemSetList.length != 0) {
//				data.organCode = $scope.item.organCode;
//				data.organName = $scope.item.organName;
//				data.itemNo = $scope.item.stItemNo;
//				data.itemTenNo = $scope.item.stItemTenNo;
//			} else {
//				layer.alert("当前事项缺少必要的信息，无法办理！");
//				$location.path("/list");
//			}
//		}).error(function() {
//			console.log('getOrganListForDeclarePage error')
//		})

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
		+"&href=SW_marineUseActivities"+"&type="+$scope.type+"&deal="+$scope.deal);
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
//	$scope.getResult = function() {
//		$location.path(getRoute(data.statusIndex));
//	}
//});app.controller("mainController", function($scope, $route, $location, $http, data, $timeout) {
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
////	data.idCardName = "邹天奇";
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
	data.itemCode = data.statusName === "新办" ? "310101230000-02" : "310101230000-01";
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
	// 项目性质
	$scope.PROJECTPROPERTY = PROJECTPROPERTY;
	$scope.selected_projectproperty = $scope.PROJECTPROPERTY[0];
	
	//执行一个laydate实例
	laydate.render({
	  elem: '#SQYHSJ_1' ,//指定元素
	  trigger: 'click'
	});
	laydate.render({
	  elem: '#SQYHSJ_2' ,//指定元素
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
			"APPLIER": $scope.APPLIER,
			"PROJECTPROPERTY": {
				"name": $scope.selected_projectproperty.name,
				"value": $scope.selected_projectproperty.value
			},
			"USEFRINGE": $scope.USEFRINGE,
			"USETYPE": $scope.USETYPE,
			"expId": "",
			"POSTCODE": $scope.POSTCODE,
			"DISTRICTS": {
				"name": $scope.selected_districts.name,
				"value": $scope.selected_districts.value
			},
			"ZZJGLX": {
				"name": $scope.selected_zzjglx.name,
				"value": $scope.selected_zzjglx.value
			},
			"targetName": $scope.targetName,
			"mdata": "0QOEpDXGO4lM+Ft3RWAUJKIVSqV0nfF1zDd+b5qbnCEp2Jrmy6UV3+4h7BNYfIL/wfIgBPsIhcCiSVfQOgdrA/6Ck+wL/P5uE2KtAQWyjdy8IpUCyeAV0QfhPZRwEL6slrVdT6Dh13p3kRoAfs5S5bzRZ9iKywuhYpZXsL0YShvYQqDoBkhbkTFmyBuI1TpEIRDbYdbOJHBW2Qwhq0Oc5XPk6GGckZIYeofT95GVHuGAwpN7CrdsFBOBSpx1PxepCTbF+BbP8mxapVPNB5iHlz0+OtgRucIhr6F0Eje2ufA97Qujz22NJWK/gOCg8FE3Pe/f6zqOtxwfGtXii/sJlA==",
			"userType": "法人",
			"PHONE": $scope.PHONE,
			"ST_ORG_TYPE": {
				"name": $scope.selected_st_org_type.name,
				"value": $scope.selected_st_org_type.value
			},
			"username": data.idCardName,
			"licenseNo": data.idCardNum,
			"eformCode": data.itemCode,
			"COMPANYCODE": $scope.COMPANYCODE,
			"ARTMPHONE": $scope.ARTMPHONE,
			"userId": "",
			"INVEST": $scope.INVEST,
			"LINKFAX": $scope.LINKFAX,
			"APPLIERS": $scope.APPLIERS,
			"POSITION": $scope.POSITION,
			"licenseType": {
				"name": "身份证",
				"value": "a"
			},
			"APPADDRESS": $scope.APPADDRESS,
			"DATUM": $scope.DATUM,
			"ST_LINK_NAME": $scope.ST_LINK_NAME,
			"ADDRESS": $scope.ADDRESS,
			"ARTIFICIALPERSON": $scope.ARTIFICIALPERSON,
			"USETOTALAREA": $scope.USETOTALAREA,
			"DWTEL": $scope.DWTEL,
			"USELOCATION": $scope.USELOCATION,
			"IDCARD": $scope.IDCARD,
			"ARTFAX": $scope.ARTFAX,
			"LINKMAN": $scope.LINKMAN,
			"WSLBH": $scope.WSLBH,
			"ARTPHONE": $scope.ARTPHONE,
			"BEIZHU": $scope.BEIZHU,
			"ST_LINK_ADDRESS": $scope.ST_LINK_ADDRESS,
			"SQYHSJ_2": {
				"value": $('#SQYHSJ_2').val(),
				"unix": Date.parse(new Date($('#SQYHSJ_2').val()))
			},
			"APPPOST": $scope.APPPOST,
			"SQYHSJ_1": {
				"value": $('#SQYHSJ_1').val(),
				"unix": Date.parse(new Date($('#SQYHSJ_1').val()))
			},
			"mobile": $scope.mobile,
			"stuff2345": [],
			"stuff19n00245": [],
			"stuff19n10774": [],
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
	//设置滚动条速度
	$scope.rollingSpeed = 500;
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
							data.isUpload.push({
								index: data.currentIndex,
								stuffName: data.stuffName
							});
							console.log(err)
						}
					});
				} else {
					layer.alert("未查询到" + certs[i].certName + "证照，请手动上传")
					$location.path("/uploadMethod");
				}
			}
			$scope.isLoading = false;
			
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
	data.uploadStuffId = "";// 上传多个材料时所需的id

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
	var imgHTML = '',
		imgIndex = 0;
	$scope.takePhoto = function() {
		$scope.capture = false;
		//var scanImg = "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";

		OcxControl.scanSave2(function(scanImg) { // 高拍仪拍照	
			data.scanImg = scanImg;
			/*
			 * 	file：上传的文件，base64、文件流都可以
				applyNo：统一审批编码
				projectid2：办件材料主键
				fileName：办件材料名称
				stuffType：材料类型，取值范围：0为普通文件材料、1为证照库证照
				stuffStatus：材料状态，取值范围：0为首次提交、2为补充材料
				
				applyNo, projectid2,fileName,file是必须的
			*/
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