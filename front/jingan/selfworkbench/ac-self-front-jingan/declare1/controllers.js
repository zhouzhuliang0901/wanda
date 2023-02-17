// urlHost = urlHost = 'http://180.169.7.194:8081/ac-self-sq';
urlHost = "http://xzfwzx.jingan.gov.cn:8080/ac-self-sq"
// urlHost = urlHost = 'http://10.2.14.143:8080/ac-self-sq';
app.controller("listController", function($scope, $location, $http, $rootScope, appData, $state) {
	appData.isUpload = [];
	appData.listImg = [];
	appData.fileName = [];
	$scope.current = 0;
	$scope.searchType = ["按事项", "按部门"];
	$scope.matterVal = '';
	$scope.itemName = "";
	$scope.isLoding = false;
	$scope.deptToItem = false;
	var flag = true;
	//
	//阅签选择
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch (type) {
			case "按部门":
				$scope.deptToItem = false;
				$scope.isLoding = false;
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.getDepartment();
				$scope.dept = true;
				break;
			case "按事项":
				$scope.deptToItem = false;
				$scope.isLoding = false;
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.personalItem();
				$scope.dept = false;
		}
	};
	//获取所有事项
	$scope.personalItem = function() {
		$scope.current = 0;
		$http.jsonp(urlHost + '/selfapi/selfDeclare/getAllItem.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemType = dataJson;
			$scope.isLoding = true;
		}).error(function(err) {
			console.log("getAllItem error");
		});
	}
	//获取所有部门
	$scope.getDepartment = function() {
		$scope.matterVal = '';
		$scope.current = 1;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/selfDeclare/getAllOrgan.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson;
			$scope.isLoding = true;
		}).error(function() {
			console.log('getAllOrgan error')
		})
	};

	//查询事项
	$scope.getSearchMatter = function() { // 查询事项
		$scope.current = 0;
		$scope.dept = false;
		$scope.itemType = "";
		console.log($scope.matterVal)
		var vConfig = {
			itemName: encodeURI($scope.matterVal),
			jsonpCallback: "JSON_CALLBACK",
		};
		if ($scope.matterVal) {
			$http.jsonp(urlHost + '/selfapi/selfDeclare/getItemByItemName.do', {
				params: vConfig
			}).success(function(dataJson) {
				$scope.itemType = dataJson;
			}).error(function() {
				console.log('getItemByItemName error')
			})
		} else {
			layer.msg('请输入事项名称');
		}
	};
	//通过部门id获取部门事项
	$scope.getItemByOrganCode = function(code) {
		$scope.current = 1;
		$scope.dept = true;
		$scope.deptToItem = true;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/selfDeclare/getItemByOrgan.do', {
			params: oConfig
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemName = dataJson;
		}).error(function() {
			console.log('getItemByOrgan error')
		})
	};

	$scope.$on('$viewContentLoaded', function() {
		$scope.isLoding = false;
		$scope.personalItem();
	});

	$scope.getMatterCond = function() {
		var oConfig = {
			stItemNo: appData.itemNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/selfDeclare/getItemTransactName.do', {
			params: oConfig
		}).success(function(dataJson) {
			appData.itemList = dataJson;
			console.log(dataJson);
			if (dataJson[0].stTransactName != null && dataJson[0].stTransactName != undefined &&
				dataJson[0].stTransactName != '') {
				appData.statusName = dataJson.stTransactName;
				$state.go('matter');
			} else {
				appData.statusName = '';
				appData.itemId = appData.itemList[0].stId;
				appData.nmBelong = appData.itemList[0].nmBelong;
				appData.description = appData.itemList[0].stOrgName;
				appData.itemTenNo = appData.itemList[0].stItemTenCode;
				appData.organCode = appData.itemList[0].stOrgCode;
				appData.itemCode = appData.itemList[0].stItemCode
				$state.go('guideline')
			}
		}).error(function() {
			console.log('getItemTransactName error')
		})
	};

	//事项列表
	$scope.toItemTypeMaterials = function(item) {
		appData.itemName = item.stSubItemName;
		appData.itemNo = item.stItemNo;
		$scope.getMatterCond();
	}
	//部门所选事项
	$scope.toItemTypeMaterials2 = function(item) {
		appData.itemName = item.stSubItemName;
		appData.itemNo = item.stItemNo;
		$scope.getMatterCond();
	}

	$scope.toItemByDept = function(item) {
		$scope.getItemByOrganCode(item.organCode);
	}
});
app.controller("matterController", function($scope, $http, $location, appData, $timeout, $rootScope, $state) {
	$scope.allName = appData.itemName;
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.itemList = appData.itemList;

	$scope.getSubItem = function(item) {
		appData.itemId = item.stId;
		appData.nmBelong = item.nmBelong;
		appData.statusName = item.stTransactName;
		appData.description = item.stOrgName;
		appData.itemNo = item.stItemNo;
		appData.itemTenNo = item.stItemTenCode;
		appData.itemCode = item.stItemCode;
		appData.organCode = item.stOrgCode;
		$state.go("guideline");
	};
	$scope.prev = function() {
		$state.go("list");
	}

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("guidelineController", function($scope, $http, $location, $sce, appData, $timeout, $state) {
	$scope.itemName = appData.itemName;
	$scope.statusName = (appData.statusName == "") ? "" : ("---" + appData.statusName);
	$scope.ItemStuffList = "";
	$scope.getGuieInfo = function() {
		var oConfig = {
			stId: appData.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/selfDeclare/getItemDetailByID.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideInfo = dataJson.info;
			//			$scope.guideInfo.stPromiseTime = $sce.trustAsHtml("<p>法定办结时限:" + $scope.guideInfo.stPromiseTime + "</p><p>承诺办结时限:" + $scope.guideInfo.stLegalTime + "</p>");
			//			$scope.clRange = dataJson.guide.clRange;
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	//办事指南二维码地址
	var itemStr = appData.itemNo;
	itemStr = itemStr.substring(0, itemStr.length - 1);
	var SHCODE = appData.areaCode + itemStr + 1;
	if (appData.areaCode == "SH00SH") {
		$scope.codeUrl =
			"http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=&_organCode_=&_organType_=&_itemId=" +
			SHCODE + "&_itemType=审批&_stSubitemId=" + appData.itemId;
	} else {
		$scope.codeUrl =
			"http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=黄浦区&_organCode_=SH00HP&_organType_=other&_itemId=" +
			SHCODE + "&_itemType=审批&_stSubitemId=" + appData.itemId;
	}
	console.log($scope.codeUrl);
	var qrcode = new QRCode("code", {
		text: $scope.codeUrl,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});

	$scope.prev = function() {
		if (appData.statusName == "") {
			$state.go("list");
		} else {
			$state.go("matter");
		}
	}
	//继续
	$scope.next = function() {
		$location.path("/select");
	}
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

	$scope.getGuieInfo();
});
app.controller("selectController", function($scope, $http, $location, appData, $timeout) {
	try {
		window.external.URL_CLOSE();
	} catch (e) {}
	$.device.Camera_Hide();
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.statusName = (appData.statusName == '')?'':('---'+appData.statusName);
	$scope.concel = false;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	
	console.log(appData.nmBelong);
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		if(appData.nmBelong.indexOf("个人") != -1){
			$location.path('/idCard');
		}else{
			$scope.isAlert = true;
			$scope.msg = "该事项的服务对象为"+appData.nmBelong+"，不支持当前登录方式，请重新选择。"
		}
	}
	// 市民云亮证
	$scope.citizen = function() {
		if(appData.nmBelong.indexOf("个人") != -1){
			$location.path('/citizen');
		}else{
			$scope.isAlert = true;
			$scope.msg = "该事项的服务对象为"+appData.nmBelong+"，不支持当前登录方式，请重新选择。"
		}
	}
	// 电子营业执照扫码
	$scope.ca = function() {
		if((appData.nmBelong.indexOf("法人") != -1) || (appData.nmBelong.indexOf('其他组织') != -1)){
			$location.path('/ca');
		}else{
			$scope.isAlert = true;
			$scope.msg = "该事项的服务对象为"+appData.nmBelong+"，不支持当前登录方式，请重新选择。"
		}
	}
	$scope.prev = function() {
		$location.path("/guideline");
	}
});
app.controller("idCardController", function($scope, $http, $location, appData, $timeout) {
	var name = appData.itemName;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = (appData.statusName == '')?'':('---'+appData.statusName);
	$scope.isRead = true;
	
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson != undefined && dataJson != null && dataJson != "") {
					appData.zwdtsw_user_id = dataJson.zwdtsw_user_id
					appData.encrypt_identity = dataJson.encrypt_identity;
					appData.targetTypeName = '个人'
					appData.applyNo = '030112522100AGG';
					$location.path("/materialList");
					// $location.path("/info");
				}
			},
			error: function(err) {}
		});
	}
	
	// appData.licenseName = '陈云翔';
	// appData.licenseNumber = '310105197805313613';
	// $scope.tokenType = "token";
	// $scope.token = function() {
	// 		$scope.getUserInfoByAccessToken();
	
	// }
	
	$scope.getIdcard = function(info, images) {

		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		appData.licenseName = info.Name;
		appData.licenseNumber = info.Number;
		appData.VALIDENDDAY = info.ValidtermOfEnd;
		appData.VALIDSTARTDAY = info.ValidtermOfStart;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		alert('info')
		$location.path("/info");
	}

	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("caController", function($scope, $http, $location, appData, $timeout) {
	var name = appData.itemName;
	if (name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = (appData.statusName == "") ? "" : ("---" + appData.statusName);
	
	//生成授权二维码
	$scope.createFrQrCode = function() {
		$.customAjax.get(urlHost + '/selfapi/workPlatform/createFrQrCode.do', {},
		function(res) {
			$scope.codeImg = 'data:image/png;base64,' + res.data.qrimage;
			$scope.qrid = res.data.qrid;
		},
		function(err) {});
	}
	$scope.createFrQrCode();
	var num = 0;
	function queryFrQrCode() {
		if (num < 20) {
		      console.log("输出");    
		      setTimeout(function(){queryFrQrCode()},3000);
		      num ++;
		}
		$.customAjax.get(urlHost + '/selfapi/workPlatform/queryFrQrCode.do', {
			qrid: $scope.qrid || ''
		},
		function(res) {
			console.log(res);
			if(res.code == '0'){
				appData.creditCode = res.data.uniscid;
				appData.companyName = res.data.entname;
				appData.caCode = res.data.operInfo.commonCode;
				$scope.getTokenSNOForCorporation(appData.creditCode,appData.companyName,appData.caCode);
			}
		},
		function(err) {});
	}
	queryFrQrCode();
	
	//法人获取tokenSNO
	$scope.getTokenSNOForCorporation = function(creditCode,companyName,caCode){
		$.customAjax.get(urlHost + '/selfapi/workPlatform/getTokenSNOForCorporation.do', {
			creditCode:creditCode,
			companyName:encodeURI(companyName),
			use_type:'2',
			caCode:caCode,
		},
		function(res) {
			appData.tokenSNO = res.tokenSNO;
			$state.go('info');
		},
		function(err) {});
	}
	// $scope.getTokenSNOForCorporation("91310109761627350W","上海神兵信息安全有限公司","9210202531@91310109761627350W");
	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("citizenController", function($scope, $http, $location, appData, $timeout) {
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.statusName = (appData.statusName == "") ? "" : ("---" + appData.statusName);
	$scope.isLoding = true;
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}

	$scope.citizenLogin = function(info) {
		function ClearBr(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
			appData.licenseName = info.zwdtsw_name;
			appData.licenseNumber = info.zwdtsw_cert_id;
			appData.zwdtsw_user_id = info.zwdtsw_user_id;
			appData.encrypt_identity = ClearBr(info.encrypt_identity);
			$location.path("/info");
	}
	$scope.prevStep = function() {
		$location.path("/select");
	}
});
app.controller("infoController", function($scope, $http, $location, appData, $timeout) {
	$.device.Camera_Hide();
	$scope.fullItemName = appData.itemName + "--" + appData.statusName;
	var name = appData.itemName;
	$scope.imgSrc1 = 'images/tips/unchecked.png';
	$scope.imgSrc2 = 'images/tips/unchecked.png';
	$scope.check_obj1 = true;
	$scope.check_obj2 = true;
	$scope.itemName = name;
	$scope.isLoding = true;
	$scope.concel = false;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.cardTypeList = [{
		'name':'身份证'
	},{
		'name':'护照'
	},{
		'name':'军官证'
	}]
	
	$scope.change = function(index,item){
		$scope.current = index;
		$scope.licenseType = item.name;
	}
	$scope.getUserInfoByAccessToken = function() {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				accessToken: appData.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if (dataJson != undefined && dataJson != null && dataJson != "") {
					appData.zwdtsw_user_id = dataJson.zwdtsw_user_id
					appData.encrypt_identity = dataJson.encrypt_identity;
				}
			},
			error: function(err) {}
		});
	}
	$scope.getUserInfoByAccessToken();
	//单选
	$scope.check_obj = function(type) {
		if (type == 1) {
			$scope.imgSrc1 = 'images/tips/checked.png';
			$scope.imgSrc2 = 'images/tips/unchecked.png';
			$scope.targetTypeName = '个人'
			$scope.check_obj1 = true;
			$scope.check_obj2 = false;
		} else if (type == 2) {
			$scope.imgSrc1 = 'images/tips/unchecked.png';
			$scope.imgSrc2 = 'images/tips/checked.png';
			$scope.targetTypeName = '法人'
			$scope.check_obj2 = true;
			$scope.check_obj1 = false;
		}
	}


	$('#username').val(appData.licenseName);
	$('#licenseNo').val(appData.licenseNumber);
	if (appData.mobile) {
		$('#mobile').val(appData.mobile);
	}
	$scope.prevStep = function() {
		$location.path("/select");
	}
	// 保存数据
	$scope.flag = true;
	$scope.goNext = function() {
		/*$location.path("/materialUpload");*/
		if (!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if ($scope.targetTypeName==null || $scope.targetTypeName==undefined ||$scope.targetTypeName=='') {
				$scope.isAlert = true;
				$scope.msg = "请选择办理对象！";
				return;
			}
			if ($('#username').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的申请人姓名！";
				return;
			}
			if (!isPhoneAvailable($('#mobile').val())) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if($scope.targetTypeName == "个人"){
				if ($scope.licenseType==null || $scope.licenseType==undefined ||$scope.licenseType=='') {
					$scope.isAlert = true;
					$scope.msg = "请选择申请人证件类型！";
					return;
				}
				if  ($('#licenseNo').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入证件号码！";
					return;
				}
			}else if($scope.targetTypeName == '法人'){
				if ($('#qymc').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的企业名称！";
					return;
				}
				if ($('#code').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的社会统一信用代码！";
					return;
				}
			}
		} while (condFlag);
		$scope.isLoding = false;
		$.customAjax.get(urlHost + '/selfapi/publicService/saveApply.do', {
			'tenCode': appData.itemTenNo,
			'departCode': appData.organCode,
			'targetTypeName': encodeURI($scope.targetTypeName),
			'targetName': encodeURI($('#username').val() || $('#qymc').val()),
			'targetNo': $('#licenseNo').val() || $('#code').val(),
			'itemName': encodeURI(appData.itemName),
			'mobile': $('#mobile').val(),
			'userId': appData.zwdtsw_user_id,
			'username': encodeURI(appData.licenseName),
			'licenseNo': appData.licenseNo,
			'content': '',
		},
		function(dataJson) {
			$scope.flag = true;
			appData.applyNo = dataJson.applyNo;
			if (($('#username').val()) !== null) {
				appData.username = $('#username').val();
			} else if (($('#targetName').val()) !== null) {
				appData.username = $('#targetName').val();
			}
			$scope.isLoding = true;
			appData.targetTypeName = $scope.targetTypeName;
			appData.mobile = $('#mobile').val();
			$location.path("/materialList");
		},
		function(err) {
			console.log(e)
		});
		$scope.flag = false;
	};
});
app.controller("uploadMethodController", function($scope, $rootScope, $http, $location, appData, $timeout) {
	removeAnimate($('.scrollBox2'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = appData.itemName;
	$scope.statusName = (appData.statusName == "") ? "" : ("---" + appData.statusName);
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = appData.stStuffName;
	$scope.stuffNameShow = appData.isShowStuffName;
	addAnimate($('.scrollBox2'))
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/materialPic');
	};
	$scope.prevStep = function() {
		$location.path("/materialList");
	}
});
app.controller("materialPicController", function($scope, $http, $location, appData, $timeout) {
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.imgUrls = "";
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$scope.licenseNumber = appData.licenseNumber;
	$scope.noDzzzData = false;
	$scope.currentPage = 1;
	$scope.totalPages = 1; //总页数
	$scope.previewImgList = []; //预览图片
	$scope.emptyPreviewImgList = []; //存在空值的数组
	$scope.totalList = [];
	$scope.url = urlHost;
	
	$scope.concel = false;
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	
	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.prevPage = function() {
		if ($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
		}
	};
	$scope.currentList = function(current) {
		// if (current === undefined) {
		// 	current = $scope.currentPage;
		// }
		// $("#jq22 img").remove();
		// $scope.currentImgIndex = null;
		// $scope.startPos = (current - 1) * 3; //0 -3  3 - 6
		// $scope.endPos = $scope.startPos + 3;
		// console.log($scope.totalList);
		// $scope.emptyPreviewImgList = $scope.totalList.slice($scope.startPos, $scope.endPos);
		// $scope.totalPages = Math.ceil($scope.totalList.length / 3);
		// $scope.emptyPreviewImgList.length = 3;
		// for (var i in $scope.emptyPreviewImgList) {
		// 	;
		// 	if ($scope.emptyPreviewImgList[i] != undefined) {
		// 		$scope.previewImgList.push($scope.emptyPreviewImgList[i]);
		// 	}
		// 	$("#jq22").append('<img data-original="' + $scope.url + $scope.emptyPreviewImgList[i]
		// 		.pictureUrlForBytes + '" src="' + $scope.url + $scope.emptyPreviewImgList[i]
		// 		.pictureUrlForBytes + '" alt="">');
		// 	$("#jq22").on('click', 'img', function() {
		// 		$(this).addClass("imgStyle").siblings().removeClass("imgStyle");
		// 		appData.selectImg = $(this).attr('src');
		// 	})
		// }
		// //图片显示
		// var viewer = new Viewer(document.getElementById('jq22'), {
		// 	url: 'data-original',
		// });
	}
	
	$scope.test = function(certUuid){
		$.customAjax.getText(urlHost + '/selfapi/electronicCertificate/preview.do', {
			orgName:'SH00JA',
			machineMAC: $.config.get("uniqueId") || "",
			itemName: encodeURI(appData.itemName),
			itemCode: appData.itemCode,
			businessCode: appData.applyNo,
			certUuid:encodeURIComponent(certUuid),
		},
		function(res) {
			$scope.previewImg = 'data:image/png;base64,' +res;
			appData.selectImg = res;
			console.log($scope.previewImg)
		},
		function(err) {});
	}
	
	$scope.profileShow = function() {
		$.ajax({
			url: urlHost + "/selfapi/electronicCertificate/getCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				jsonpCallback: "JSON_CALLBACK",
				orgName: 'SH00JA',//appData.description,
				machineMAC: $.config.get("uniqueId") || "",
				itemName: encodeURI(appData.itemName),
				itemCode: appData.itemCode,
				businessCode: appData.applyNo,
				holderCode: appData.encrypt_identity || appData
					.licenseNumber, // "340881199303145313", //
				type: (appData.targetTypeName == '个人') ? 0 : 1,
				catMainCode: appData.catMainCode,
			},
			success: function(json) {
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if (!dataJson) { // !dataJson[0].address
					$scope.isAlert = true;
					$scope.msg = '没有可使用的电子证照，请重新选择上传方式';
					$scope.alertConfirm = function(){
						$scope.isAlert = false;
						$location.path('/uploadMethod');
					}
				} else {
					$scope.imgUrls = dataJson;
					$scope.test(dataJson[0].certUuid);
					// $scope.totalList = $scope.imgUrls.slice(0, $scope.imgUrls.length);
					// $scope.currentList();
				}
			},
			error: function(err) {
				console.log(err)
			}
		});
	};
	$scope.profileShow();

	$scope.goNext = function() {
		//		appData.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		// $scope.waitUploadImgUrl = appData.selectImg;
		// $scope.jsonData = {
		// 	'applyNo': appData.applyNo, //  '751122018600008'
		// 	'stuffId': appData.stStuffId,
		// 	'stuffCode': appData.stuffCode,
		// 	'stuffName': appData.stuffName,
		// 	'stuffType': 0,
		// 	'stuffStatus': 0,
		// };
		// $scope.jsonData = JSON.stringify($scope.jsonData);
		// console.log($scope.jsonData);
		
		$.customAjax.postJson(urlHost + '/selfapi/publicService/uploadItemStuffs.do',{
			'applyNo': appData.applyNo, //  '751122018600008'
			'stuffId': appData.stStuffId,
			'stuffCode': appData.stuffCode,
			'stuffName': appData.stuffName,
			'stuffType': 0,
			'stuffStatus': 0,
			'file':appData.selectImg
		},function(res){
			layer.msg("上传成功");
			appData.isUpload.push({
				index: appData.currentIndex,
				stuffName: appData.stStuffName,
				img: $scope.waitUploadImgUrl,
				method: "个人档案"
			});
			$timeout(function() {
				$location.path('/materialList');
			}, 1000);
		},function(err){
			layer.msg("上传失败");
		})
		// $.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost +
		// 	$scope.waitUploadImgUrl,
		// 	"C:\\waitUploadImg.jpg",
		// 	//将选中图片下载
		// 	function(bytesCopied, totalBytes) {
		// 		console.log(bytesCopied + "," + totalBytes);
		// 	},
		// 	function(result) {
		// 		//将选中图片上传到服务器
		// 		$.device.httpUpload(urlHost + '/selfapi/publicService/uploadItemStuffs.do', "file",
		// 			"C:/waitUploadImg.jpg",
		// 			$scope.jsonappData,
		// 			function(result) {
		// 				layer.msg("上传成功");
		// 				appData.isUpload.push({
		// 					index: appData.currentIndex,
		// 					stuffName: appData.stStuffName,
		// 					img: $scope.waitUploadImgUrl,
		// 					method: "个人档案"
		// 				});
		// 				$timeout(function() {
		// 					$location.path('/materialList');
		// 				}, 1000);
		// 			},
		// 			function(webexception) {
		// 				layer.msg("上传失败");
		// 			});
		// 	},
		// 	function(webexception) {
		// 		alert("下载文档失败");
		// 	}
		// );

	};
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
});
app.controller("finishController", function($scope, $http, $location, appData, $timeout) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();

	var name = appData.itemName;
	$scope.itemName = name;
	$scope.finishUp = [];
	$scope.isLoading = true;
	$scope.prevText = "返回";
	$scope.nextText = "确认上传";
	$.device.cmCaptureShow(680, 530, 210, 340);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	$scope.next = function() {
		var scanImg = "";
		layer.msg("正在上传中，请稍候");
		$scope.isLoading = false;
		$.device.cmCaptureCaptureUrl(function(info) {
			scanImg = info;
		})
		$scope.jsonData1 = {
			'applyNo': appData.applyNo,
			'stuffId': appData.stStuffId,
			'stuffCode': appData.stStuffCode,
			'stuffName': appData.stStuffName,
			'stuffType': 0,
			'stuffStatus': 0,

		};
		$scope.jsonData1 = JSON.stringify($scope.jsonData1);
		$.device.httpUpload(urlHost + "/selfapi/publicService/uploadItemStuffs.do", "file", scanImg,
			$scope.jsonData1,
			function(result) {
				appData.uploadStuffId = appData.stStuffId;
				if (appData.listImg.length < 1) {
					appData.currentIndex++; // 没有材料列表时   文件下标+1
				}
				$scope.finishUp.push({
					index: appData.currentIndex,
					stuffName: appData.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				appData.fileName.push('扫描文件');
				imgHTML += '<div class="img" id="' + appData.uploadStuffId + '"><img src="' + scanImg +
					'" width="150" height="200" /></div>';
				$('.imgBox').html(imgHTML);
				$scope.isLoading = true;
				$scope.isFinish = true;
				$scope.$apply();

			},
			function(webexception) {
				layer.msg("上传材料失败")
			});
	};
	// 完成拍照
	$scope.finishUpload = function() {
		for (var i = 0; i < appData.isUpload.length; i++) {
			if (appData.currentIndex == appData.isUpload[i].index) {
				appData.isUpload[i] = "";
			}
		}
		for (var i = 0; i < $scope.finishUp.length; i++) {
			appData.isUpload.push($scope.finishUp[i]);
		}
		$timeout(function() {
			$.device.cmCaptureHide(); // 关闭高拍仪
			$location.path('/materialList');
		}, 20);
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialList');
	}
});
app.controller("materialListController", function($scope, $http, $location, appData, $timeout) {
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.statusName = (appData.statusName == "") ? "" : ("---" + appData.statusName);
	$scope.nextText = "提交办件";
	$scope.isAlert = false;
	$scope.concel = false;
	//必传材料列表
	$scope.mustUpload = [];
	$scope.current = 0;
	// 获取材料列表
	var fConfig = {
		itemCode: appData.itemTenNo, //"0101361000",//
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp(urlHost + '/selfapi/publicService/getItemStuffs.do', {
		params: fConfig
	}).success(function(dataJson) {
		//材料列表
		if (dataJson.data.flag == false) {
			if (appData.targetTypeName == "个人") {
				console.log(dataJson.data.subItems[0].itemCode.indexOf('-0'))
				if (dataJson.data.subItems[0].itemCode.indexOf('-1') != -1) {
					$scope.stuffList = dataJson.data.subItems[0].stuffs;
				} else {
					$scope.stuffList = dataJson.data.subItems[1].stuffs;
				}
			} else if (appData.targetTypeName == "法人") {
				if (dataJson.data.subItems[0].itemCode.indexOf('-0') != -1) {
					$scope.stuffList = dataJson.data.subItems[0].stuffs;
				} else {
					$scope.stuffList = dataJson.data.subItems[1].stuffs;
				}
			}
		} else {
			$scope.stuffList = dataJson.data.stuffs;
		}
		$scope.getListImg();
	}).error(function() {
		console.log('queryStuffList error')
	})

	$scope.getListImg = function() {
		console.log($scope.stuffList);
		if ($scope.stuffList != "" && $scope.stuffList != null && $scope.stuffList != undefined) {
			for (var i = 0; i < $scope.stuffList.length; i++) {
				if ($scope.stuffList[i].isMust == 1) {
					$scope.mustUpload.push({
						index: i,
						stuffName: $scope.stuffList[i].stuffName
					});
				}
			}

			if (appData.listImg == 0) {
				for (var i = 0; i < $scope.stuffList.length; i++) {
					appData.listImg[i] = {
						'activeImg': null,
						'index': i,
						'stuffName': $scope.stuffList[i].stuffName,
						'upload': true,
						'upload2': false,
					}
				}
			}
			console.log(appData.listImg);
			//设置上传文件 按钮变化
			if (appData.isUpload != "") {
				for (var i = 0; i < appData.isUpload.length; i++) {
					for (var j = 0; j < appData.listImg.length; j++) {
						if (appData.listImg[j].upload != false) {
							if (appData.isUpload[i].stuffName == appData.listImg[j].stuffName) {
								appData.listImg[appData.isUpload[i].index].upload = false;
								appData.listImg[appData.isUpload[i].index].upload2 = true;
							}
						}
					}
				}
			}
			$scope.listImg = appData.listImg;
		} else {
			$scope.isAlert = true;
			$scope.msg = "此事项暂无材料";
			$scope.alertConfirm = function() {
				$scope.isAlert = false;
			}
		}
	}

	// 材料上传
	appData.currentIndex++;
	$scope.toUploadMaterial = function(index, id, code, name, certs) {
		appData.stStuffId = id;
		appData.stStuffCode = code;
		appData.stStuffName = name;
		appData.currentIndex = index;
		appData.stuffImg = appData.listImg[appData.currentIndex];
		appData.catMainCode = certs[0].certCode
		$location.path("/uploadMethod");
	}
	//重新上传
	$scope.toNewUploadMaterial = function(index, id, code, name, certs) {
		appData.stStuffId = id;
		appData.stStuffCode = code;
		appData.stStuffName = name;
		appData.currentIndex = index;
		appData.stuffImg = appData.listImg[appData.currentIndex];
		appData.catMainCode = certs[0].certCode
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function(index, code, name) {
		appData.currentIndex = index;
		appData.view = appData.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function() {
		var a = 0;
		console.log($scope.mustUpload);
		console.log($scope.mustUpload.length);
		// if ($scope.mustUpload.length == 0) {
		$location.path('/infoFinish');
		// } else {
		// 	if ($scope.mustUpload.length <= appData.isUpload.length) {
		// 		for (var i = 0; i < appData.isUpload.length; i++) {
		// 			for (var j = 0; j < $scope.mustUpload.length; j++) {
		// 				if (appData.isUpload[i].stuffName == $scope.mustUpload[j].stuffName) {
		// 					a++;
		// 				}
		// 			}
		// 		}
		// 		if (a >= $scope.mustUpload.length) {
		// 			$location.path('/infoFinish');
		// 		} else {
		// 			layer.msg("请提交必上传材料");
		// 		}
		// 	} else {
		// 		layer.msg("请提交必上传材料");
		// 	}
		// }
	};

	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.prevStep = function() {
			$location.path("/info");
	}
});
app.controller("materialViewController", function($scope, $http, $location, data) {
	console.log(appData.isUpload);
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	var name = appData.itemName;
	$scope.itemName = name;
	$scope.statusName = (appData.statusName == "") ? "" : ("---" + appData.statusName);
	//当页显示图片
	$scope.currentList = function(current) {
		if (current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6
		$scope.endPos = $scope.startPos + 3;
		console.log($scope.stuffList);
		$scope.showImgList = $scope.stuffList.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.stuffList.length / 3);
	}
	for (var i = 0; i < appData.view.length; i++) {
		if (appData.currentIndex == appData.view[i].index) {
			$scope.stuffList.push(appData.view[i]);
			$scope.currentList();
		}
	}

	//下一页
	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	//上一页
	$scope.prevPage = function() {
		if ($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
			console.log($scope.showImgList);
		}
	};
	if ($scope.stuffList[0].method === "高拍仪") {
		$scope.scanShow = true;
		$scope.upanShow = false;
	} else if ($scope.stuffList[0].method === "U盘上传") {
		$scope.scanShow = false;
		$scope.upanShow = true;
	} else if ($scope.stuffList[0].method === "个人档案") {
		$scope.scanShow = true;
		$scope.upanShow = false;
	}
	//图片显示
	$scope.closeFlag = true;
	$scope.imgShow = function(imgUrl) {
		$scope.largeImg = imgUrl;
		$scope.closeFlag = !$scope.closeFlag;
	}
	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch (e) {
			layer.msg("未找到此文件");
		}
	}
	$scope.prevStep = function() {
		$location.path("/materialList");
	}
});
app.controller("infoFinishController", function($scope, $http, $location, appData, $timeout) {
	$scope.statusName = (appData.statusName == "") ? "" : ("---" + appData.statusName);
	$scope.allName = appData.itemName + $scope.statusName;
	var name = appData.itemName;
	$scope.itemName = name;
	var htmlBody = "";
	$scope.nextText = "返回首页";
	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = {
			applyNo: appData.applyNo, // '751122018600008'
			subItemCodes: '', // appData.itemId
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/selfapi/publicService/submitItem.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.print();
		}).error(function(e) {
			console.log(e)
		});
	}
	//保存办件信息 埋点
	$scope.saveApplyInfo = function(){
		$scope.params = {
			stMachineId:$.config.get('uniqueId') || '12-12-12-12-12',
			stModuleName: '区级公共服务',
			stModuleOp: '办理',
			stItemName: $scope.allName,
			stName:appData.licenseName,
			stIdentityNo: appData.licenseNumber,
			stMobile: '',
			stBusinessNo:appData.applyNo,
			stDesc: "",
			stOpResult: 'SUCCESS',
			stExt1: '',
			stExt2: "",
			stExt3: "",
			stExt4: "",
			stExt5: "",
			stItemNo:appData.itemCode,
		}
		$.customAjax.post(urlHost + '/selfapi/applyInfo/saveApplyInfo.do',{
			param:JSON.stringify($scope.params),
		},
			function(res){},
			function(err){}
		)
	}
	$scope.saveApplyInfo();
	$scope.stuffList = function() {
		console.log(JSON.stringify(appData.isUpload));
		var stuffList = [];
		for (let i in appData.isUpload) {
			console.log(stuffList.indexOf(appData.isUpload[i].stuffName));
			if (stuffList.indexOf(appData.isUpload[i].stuffName) == -1 && appData.isUpload[i].stuffName !=
				undefined && appData.isUpload[i].stuffName != null) {
				stuffList.push(appData.isUpload[i].stuffName);
			}
		}
		var str =
			"<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr>";
		htmlBody = str;
		var str2 = "</table>";
		for (let i in stuffList) {
			var str1 = "<tr><td>" + (parseInt(i) + 1) + "</td><td>" + stuffList[i] + "</td><td></td></tr>";
			htmlBody = htmlBody + str1;
		}
		htmlBody = htmlBody + str2;
	}
	$scope.stuffList();
	$scope.submitApply();
	$scope.applyNo = appData.applyNo;
	$scope.statusText = '打印凭证';
	var code = "http://zwdt.huangpuqu.sh.cn:8080/ac/aci/HP/declare/index.html#/qrCode?applyNo=" + appData
		.applyNo;
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	$scope.print = function() {
		if (appData.itemName == "对公园举办活动的许可(对公园举办全园性活动的许可)") {
			$scope.applyNo = "0102253211000CT";
		} else if ((appData.itemName == "对临时使用绿地的许可" && appData.statusName == "新办")) {
			$scope.applyNo = "0100839211000Y3";
		}
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", $scope.applyNo);
		//		lodop.ADD_PRINT_TEXT(170, 574, 200, 30, "扫一扫,查进度");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 10);
		//		lodop.ADD_PRINT_BARCODE(38, 550, 168, 146, "QRCode", $scope.applyNo); // code
		lodop.ADD_PRINT_TEXT(190, 165, 600, 50, "上海市政务服务申请材料 收件凭证");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请事项：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 125, 600, 30, appData.itemName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "申请人：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(250, 125, 600, 30, appData.username);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(250, 480, 600, 30, "联系电话：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(250, 580, 600, 30, appData.mobile);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(330, 65, 700, 30, "经核查，您（单位）提交的申请材料齐全，符合法定形式，现予收件。");
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(370, 25, 700, 120,
			"    根据规定，承办部门将在出具本凭证之日起5个工作日内，作出受理或不予受理的决定，申请材料不齐全或者不符合法定形式的，将在5个工作日内一次告知需要补正的材料。如在5个工作日内未被告知需要补正材料的，则视为正式受理，本凭证即为受理凭证。"
		);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(500, 65, 670, 150, "收件材料清单附后");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(560, 480, 300, 30, "收件日期：" + date.getFullYear() + "年" + month + "月" + date
			.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, htmlBody);
		lodop.PRINT();
	};
});
app.controller("qrCodeController", function($scope, $http, $location, appData, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoding = false;
	$scope.process = function() {
		var pConfig = {
			stApplyNo: $scope.applyNo,
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost + '/aci/autoterminal/eventquery/getApplyInfoByStApplyNo.do', {
			params: pConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.stItemName;
			$scope.name = dataJson.stName || dataJson.stUnit;
			$scope.date = dataJson.stApplyStr;
			$scope.isLoding = true;
		}).error(function(err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});
