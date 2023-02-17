var urlHost = ' http://218.202.254.222';
//var urlHost = 'http://xzfwzx.jingan.gov.cn:8080/ac'; // 静安外网地址
var t;


function removeAnimate(ele){
	$(ele).css({"transform":"translateY(0px)","top":0}).removeClass('transformto')
}
function addAnimate(ele){
	$(ele).addClass('transformto').on("animationend",function(){
		$(ele).removeClass('transformto')
	})
}

function time() {
	var time = 60;
	t = setInterval(function() {
		if(time == 0) {
			clearInterval(t);
			$.device.GoHome();
		}
		$(".minute").text(time);
		time--;
	}, 1000)
}
app.controller("departmentListController", function($scope, $route, $location, $http, $rootScope, data) {
	removeAnimate($('.linkbox1'))
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.resetData = "";
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.idRead = false;
	$scope.isLoading = false;
	addAnimate($('.linkbox1'))
	$scope.getDepartmentList = function () {
		$.ajax({
			type:"get",
			url:JA_Extranet_urlHost + "/aci/materialUp/organList.do",
//			async:true
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data:{},
			success: function (returnData) {
				console.log(returnData);
				$scope.isLoading = true;
				$scope.itemName = returnData.data;
			},
			error: function (err) {
				console.log(err);
			}
		});
	}
	$scope.getDepartmentList();
	$scope.toMaterials = function(departmentCode, departmentId, departmentOrganName, departmentShortName) {
		$scope.isLoading = false;
		data.departmentCode = departmentCode;
		data.departmentId = departmentId;
		data.departmentOrganName = departmentOrganName;
		data.departmentShortName = departmentShortName;
		$location.path("/list");
	};
	$rootScope.goHome = function() {
		$scope.isLoading = false;
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
	
	$scope.prevStep = function () {
		window.location.href = '../declare/index.html';
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
//	$scope.isScroll();
});
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data, $timeout) {
	$scope.loadingTips = '正在加载数据，请稍后...';
	removeAnimate($('.linkbox1'))
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.resetData = "";
//	$scope.itemName = mainMatterList;// 线下办理主事项列表
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.idRead = false;
	$scope.isLoading = false;
	clearInterval(t);
//	time();
	addAnimate($('.linkbox1'))
	$scope.queryItemListByOrganId = function () {
		$.ajax({
			type:"get",
//			url:JA_Extranet_urlHost + "/aci/materialUp/queryItemList.do",
			url:JA_Extranet_urlHost + "/aci/materialUp/itemByOrganId.do",
//			async:true
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data:{
				organId: data.departmentId
			},
			success: function (returnData) {
				if(!returnData.data) {
					$scope.loadingTips = '该部门暂无事项!';
					$timeout(function () {
						$location.path('/departmentList');
					}, 3000);
				} else {
					$scope.isLoading = true;
					console.log(returnData);
					$scope.itemName = returnData.data;
				}
				
			},
			error: function (err) {
				console.log(err);
			}
		});
	}
	$scope.queryItemListByOrganId();

//	itemName, organCode, itemNo, clNameCode, clDealAccording, clDealOrgan, clApprovalConds,
//		clApprovalCount, clApprovalMater, clApprovalLimit, clApprovalCert, clChargeStd, clApplyRightsDuties,
//		clApplyReceive, clConsultWay, clComplaintChannel, clDealType, clDecidedOpen, clRange, itemId, organName, itemTenNo
	$scope.toMaterials = function(itemName, itemNo, itemId) {
		$scope.isLoading = false;
		data.itemName = itemName;
		data.itemNo = itemNo;
		data.itemId = itemId;
//		data.itemTenNo = itemTenNo;
//		data.organName = organName;
//		data.clRange = clRange;
//		data.organCode = organCode;
//		data.organCode = organCode;
//		data.clNameCode = clNameCode;
//		data.clDealAccording = clDealAccording;
//		data.clDealOrgan = clDealOrgan;
//		data.clApprovalConds = clApprovalConds;
//		data.clApprovalCount = clApprovalCount;
//		data.clApprovalMater = clApprovalMater;
//		data.clApprovalLimit = clApprovalLimit;
//		data.clApprovalCert = clApprovalCert;
//		data.clChargeStd = clChargeStd;
//		data.clApplyRightsDuties = clApplyRightsDuties;
//		data.clApplyReceive = clApplyReceive;
//		data.clConsultWay = clConsultWay;
//		data.clComplaintChannel = clComplaintChannel;
//		data.clDealType = clDealType;
//		data.clDecidedOpen = clDecidedOpen;
//		$location.path("/guideline");
		$location.path("/matter");
	};
	$rootScope.goHome = function() {
		$scope.isLoading = false;
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
	
	$scope.prevStep = function () {
		$location.path('/departmentList');
	}
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
//	$scope.isScroll();
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
//	clearInterval(t);
//	time();
	addAnimate($('.main2'))
	$scope.isLoading = true;
	if(data.itemTenNo == "") {
		$location.path("/matter");
	} else {
		$scope.guideInfo = {
			clRange: data.clRange,
			clNameCode: data.clNameCode,
			clDealAccording: data.clDealAccording,
			clDealOrgan: data.clDealOrgan,
			clApprovalConds: data.clApprovalConds,
			clApprovalCount: data.clApprovalCount,
			clApprovalMater: data.clApprovalMater,
			clApprovalLimit: data.clApprovalLimit,
			clApprovalCert: data.clApprovalCert,
			clChargeStd: data.clChargeStd,
			clApplyRightsDuties: data.clApplyRightsDuties,
			clApplyReceive: data.clApplyReceive,
			clConsultWay: data.clConsultWay,
			clComplaintChannel: data.clComplaintChannel,
			clDealType: data.clDealType,
			clDecidedOpen: data.clDecidedOpen
		}
		var lodop = $.device.printGetLodop();
		//办事指南二维码地址
		if(name == "特种设备安装改造维修施工告知") {
			var itemUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_itemId=SH00JD310150473001&_itemType=%E5%AE%A1%E6%89%B9&_stSubitemId=504e035e-a1f3-46ce-b6d5-71143f8bf0d7";
		} else if(name == "特种设备使用登记") {
			var itemUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_itemId=SH00JD310150679001&_itemType=%E5%AE%A1%E6%89%B9&_stSubitemId=589c323d-8ea2-45a7-a423-3349ca3cf7c5";
		} else if(name == "网络预约出租汽车驾驶员人员背景审查") {
			var itemUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_itemId=SH00SH310100891002&_itemType=%E5%AE%A1%E6%89%B9&_stSubitemId=4df7e909-5471-49cf-adf6-3cfc7f4604bd#work-apply-data";
		}
		$scope.codeUrl = itemUrl;
		console.log($scope.codeUrl);
		var qrcode = new QRCode("code", {
			text: $scope.codeUrl,
			width: 200,
			height: 200,
			correctLevel: 0,
			render: "table"
		});

		$scope.print = function() {
			 	 	/*var style = "<style>table tr>td{text-align:center}table tr>td:nth-child(1){text-align:left}"
			 			+"table tr>td:nth-child(1){width:920px;}table tr>td:nth-child(2){display: none;}"
			 			+"table tr>th:nth-child(2){display: none;}table tr>td:nth-child(3){width:200px;margin-left: 20px;}"
			 			+"table tr>td:nth-child(4){width:130px;margin-left: 20px;}table tr>td:nth-child(5){width:150px;margin-left: 20px;}</style>";
			 		var html = style+"<body>"+document.getElementById("lodop").innerHTML+"</body>";
			 		lodop.ADD_PRINT_TEXT(50,0,"100%",100,$scope.itemName+"--材料清单");
			 		lodop.SET_PRINT_STYLEA(0,"Alignment",2);
			 		lodop.SET_PRINT_STYLEA(0,"FontSize",20);
				  	lodop.ADD_PRINT_HTM(150,0,"100%","100%",html);scrollBox2
			*/
			lodop.ADD_PRINT_HTM(0, 0, 1500, "100%", document.getElementById("scrollBox2").innerHTML);
			lodop.PRINT();
		};

		$scope.isScroll = function() {

			new iScroll("wrapper", {
				vScrollbar: false,
				hScrollbar: false,
				hideScrollbar: false,
				bounce: true,
				checkDOMChanges: true
			});
		};
		$scope.isScroll();
		$scope.prevStep = function(){
			$scope.isLoading = false;
			$location.path("/list");
		}
		$scope.nextStep = function() {
			$scope.isLoading = false;
			$location.path("/matter");
		}
	};
});

app.controller("matterController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.scrollBox1'))
	$.device.Camera_Hide();
	var name = data.itemName;
	var itemId = data.itemId;
	$scope.itemName = name;
	$scope.statusName = [];
//	clearInterval(t);
//	time();
	addAnimate($('.scrollBox1'))
	$scope.isLoading = true;
	//根据事项id获取情形
	$scope.getMatterCond = function() {
		var oConfig = {
			itemId: itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
//		urlHost + '/aci/materialUp/queryStatusList.do'
		$http.jsonp(JA_Extranet_urlHost + '/aci/materialUp/queryStatusList.do', {
			params: oConfig
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemList = dataJson.data;
		}).error(function() {
			console.log('queryStatusList error')
		})
	};
	$scope.getMatterCond();
	$scope.getSubItem = function(statusName, stStatusId) {
		$scope.isLoading = false;
		data.stStatusId = stStatusId;
		data.statusName = statusName;
		$location.path("/select");
	}
	$scope.prevStep = function() {
		$scope.isLoading = false;
		$location.path('/list');
//		console.log(data.itemTenNo)
//		if(data.itemTenNo == "") {
//			$location.path("/list");
//		} else {
//			$location.path("/guideline");
//		}
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.scrollBox2'))
	try {
		window.external.URL_CLOSE();
	} catch(e) {}
	$.device.Camera_Hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
//	time();
	addAnimate($('.scrollBox2'))
	$scope.isLoading = true;
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$scope.isLoading = false;
		$location.path('/idCard');
		
		//	测试数据
// 	data.idCardName = "岳敏";
// 	data.idCardNum = "31010919810727205X";
// 	$location.path("/info");
	}
	// 市民云亮证
	$scope.citizen = function() {
		$scope.isLoading = false;
		$location.path('/citizen');
	}
	$scope.prevStep = function() {
		$scope.isLoading = false;
		$location.path('/matter');
	}
});

app.controller("idCardController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;
	$scope.isLoading = true;
//	clearInterval(t);
	addAnimate($('.main2'))
	$scope.getIdcard = function(info, images) {

		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$location.path("/info");
	}
	$scope.prevStep = function(){
	$scope.isLoading = false;
		$location.path("/select");
	}
	
//	测试数据
// 	data.idCardName = "岳敏";
// 	data.idCardNum = "31010919810727205X";
// 	$location.path("/info");
});

app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
//	time();
	addAnimate($('.main2'))
	$scope.prevStep = function(){
		$location.path("/select");
	}
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({
//			url: " http://218.202.254.222/aci/window/getInfoByCodeTest.do",
			url: JA_Extranet_urlHost + "/aci/window/getQrCodeInfoByElectronicCert.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success === false) {
					layer.msg(dataJsonp.result.msg);
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}
				data.idCardName = dataJsonp.result.data.realname;
				data.idCardNum = dataJsonp.result.data.idcard;
				data.mobile = dataJsonp.result.data.mobile;
				data.idcard_valid_start_day = dataJsonp.result.data.VALIDSTARTDAY;
				data.idcard_valid_end_day = dataJsonp.result.data.VALIDENDDAY;
				$timeout(function() {
					$location.path('/info');
				}, 100);
			},
			error: function(err) {
				console.log("二维码已过期！")
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
});

app.controller("infoController", function($scope, $route, $http, $location, data, $timeout) {
//	removeAnimate($('.scrollBox2'))
	$.device.Camera_Hide();
	$scope.fullItemName = data.itemName + "--" + data.statusName;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
//	addAnimate($('.scrollBox2'))
	$scope.targetTypeName = '个人';
	$scope.targetTips = '申请人身份证号';
	$scope.targetName = '申请人姓名';
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.isLoading = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	// $wathc 监听 targetTypeName 
	$scope.$watch('targetTypeName', function(newValue, oldValue) {
		$scope.targetTypeName == '个人' ? $scope.targetTips = '申请人身份证号' : $scope.targetTips = '统一社会信用代码';
		$scope.targetTypeName == '个人' ? $scope.targetName = '申请人姓名' : $scope.targetName = '企业名称';
		$scope.targetTypeName == '个人' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
		$scope.targetTypeName == '个人' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
	});
	$('#targetName').val(data.idCardName);
	$('#username').val(data.idCardName);
	$('#licenseNo').val("");
	if(data.mobile) {
		$('#mobile').val(data.mobile);
	}
	$scope.prevStep = function(){
	$scope.isLoading = false;
		$location.path("/select");
	}
	
	$scope.handleGetCertWay = function ($event) {
		console.log($event.target.innerText);
		$scope.getCertWay = $event.target.innerText;
		data.getCertWay = $scope.getCertWay;
		$($event.target).addClass('clicked').siblings().removeClass('clicked');
	}
	
	// 保存数据
	$scope.flag = true;
	$scope.goNext = function() {
		/*$location.path("/materialUpload");*/
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($scope.targetTypeName == '个人') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的申请人姓名！";
					return;
				}
				//			if(!checkIdCard($('#targetNo').val())){
				//				$scope.isAlert = true;
				//				$scope.msg = "请输入正确的身份证信息！";
				//				return;
				//			}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的经办人姓名！";
					return;
				}
				//			if(!checkIdCard($('#licenseNo').val())){
				//				$scope.isAlert = true;
				//				$scope.msg = "请输入正确的身份证信息！";
				//				return;
				//			}

				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的手机号！";
					return;
				}
				if(!$scope.getCertWay){
					$scope.isAlert = true;
					$scope.msg = '请选择取证方式!';
					return;
				}
			} else if($scope.targetTypeName == '法人') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的企业名称！";
					return;
				}
				//			if($('#targetNo').val().length < 17) {
				//				$scope.isAlert = true;
				//				$scope.msg = "请输入正确的统一社会信用代码！";
				//				return;
				//			}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的经办人姓名！";
					return;
				}
				//			if(!checkIdCard($('#licenseNo').val())){
				//				$scope.isAlert = true;
				//				$scope.msg = "请输入正确的身份证信息！";
				//				return;
				//			}
				//				
				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的手机号！";
					return;
				}
				if(!$scope.getCertWay){
					$scope.isAlert = true;
					$scope.msg = '请选择取证方式!';
					return;
				}

			}
		} while (condFlag);
		$scope.isLoading = false;
		
		data.mobile = document.getElementById("mobile").value;
		data.targetName = document.getElementById("targetName").value;
		data.targetNo = document.getElementById("targetNo").value;
		data.licenseNo = document.getElementById("licenseNo").value;
		var from = $('#infoForm').serialize();
		var fConfig = {
			jsonpCallback: "JSON_CALLBACK",
			stStatusId: data.stStatusId,
			stItemId: data.itemId,
			stWindowNo: "C26",
			loginName: "GZT001",
			getCertWay: $scope.getCertWay,
			stUserName: data.idCardName,
			stUnit: data.targetName || "",
			stMobile: data.mobile,
			stIdentityNo: data.targetNo,
			societyNo: $('#licenseNo').val() || ""
		};
//		urlHost
		$http.jsonp('http://101.230.224.65:8080/ac/aci/materialUp/saveNew.do?', {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			console.log(dataJson);
			data.applyId = dataJson.applyId;
			console.log("在ajax中测试appyId传入数据为：" + data.applyId);
			if(($('#username').val()) !== null) {
				data.username = $('#username').val();
			} else if(($('#targetName').val()) !== null) {
				data.username = $('#targetName').val();
			}
			$location.path("/materialList");
		}).error(function(e) {
			console.log(e)
		});
		$scope.flag = false;
	};
});
app.controller("materialListController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$.device.fileClose();
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	addAnimate($('.main2'))
	$scope.current = 0;
	$scope.commit = false;
	$scope.isLoading = true;
	data.materialNameList = [];
	console.log(data.isUpload);
	// 获取材料列表  	
	var oConfig = {
		statusId: data.stStatusId,
		jsonpCallback: "JSON_CALLBACK",
	};
//	urlHost
	$http.jsonp(JA_Extranet_urlHost + '/aci/materialUp/queryStuffList.do', {
		params: oConfig
	}).success(function(dataJson) {
		$scope.commit = true;
		$scope.stuffList = dataJson.data;
		if(data.listImg == 0) {
			for(var i = 0; i < $scope.stuffList.length; i++) {
				data.listImg[i] = {
					'activeImg': null,
					'index': i,
					'stuffName': $scope.stuffList[i].stStuffName,
					'upload': true,
					'upload2': false,
				}
			}
		}
		//设置上传文件 按钮变化
		if(data.isUpload != "") {
			for(var i = 0; i < data.isUpload.length; i++) {
				for(var j = 0; j < data.listImg.length; j++) {
					if(data.listImg[j].upload != false) {
						if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
							data.listImg[data.isUpload[i].index].upload = false;
							data.listImg[data.isUpload[i].index].upload2 = true;
						}
					}
				}
			}
		}

		$scope.listImg = data.listImg;
		console.log(data.listImg)
		data.listImg.forEach(function (value, index) {
			if(value.upload2 == true) {
				value.materialSource = '电子文件';
				data.materialNameList.push(value);
			}
		})
		console.log(data.materialNameList);
	}).error(function() {
		console.log('queryStuffList error')
	})

	// 材料上传 
	data.currentIndex++;
	$scope.toUploadMaterial = function(index, id, name) {
		$scope.isLoading = false;
		data.stStuffId = id;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//重新上传
	$scope.toNewUploadMaterial = function(index, id, name) {
		$scope.isLoading = false;
		for(var i = 0; i < data.isUpload.length; i++) {
			if(index == data.isUpload[i].index) {
				//	data.isUpload[i] = "";
			}
		}
		data.resetData = "reset";
		data.stStuffId = id;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function(index, name) {
		$scope.isLoading = false;
		data.currentIndex = index;
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function() {
		$scope.isLoading = false;
		$location.path('/infoFinish');
	};
	$scope.prevStep = function(){
		$scope.isLoading = false;
		$location.path('/info');
	}

	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
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
	$scope.statusName = data.statusName;
	clearInterval(t);
//	time();
	//上传材料信息
	$scope.stStuffName = data.stuffImg.stStuffName;
	$scope.stuffImg = data.sample;
	$scope.test = function() {
		$('#test').viewer({
			url: 'data-original',
		});
	}
	$scope.upload = function() {
		$location.path("/uploadMethod");
	}
	$scope.prevStep = function(){
		$location.path('/materialList');
	};
	$scope.nextStep = function(){
		$location.path('/uploadMethod');
	}
});
app.controller("uploadMethodController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.linkBox1'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$.device.fileClose();
	$scope.statusName = data.statusName;
//	if(name.length > 10) {
//		name = name.slice(0, 10) + '...'
//	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	clearInterval(t);
//	time();
	addAnimate($('.linkBox1'))
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U盘上传
	$scope.isAlert = false;
	$scope.msg = '请确认是否插入U盘';
	$scope.confirmText = '是';
	$scope.cancelText = '否';
	$scope.takePhoto = function() {
		$scope.isAlert = true;
//		layer.confirm("<em style='color:black'>" + '请确认是否插入U盘！' + "</em>", {
//			btn: ['已插入U盘', '未插入U盘'] //按钮
//		}, function() {
//			$('.layui-layer-shade').hide();
//			$('.layui-layer').hide();
//			$timeout(function() {
//				$location.path('/takePhoto/U');
//			}, 20);
//		}, function() {
//			$('.layui-layer-shade').hide();
//			$('.layui-layer').hide();
//			$timeout(function() {
//				layer.msg('请选择其他上传方式！');
//			}, 20);
//		});
	};
	$scope.alertConfirm = function(){
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			$location.path('/takePhoto/U');
		}, 20);
	}
	$scope.alertCancel = function(){
		$scope.isAlert = false;		
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			layer.msg('请选择其他上传方式！');
		}, 20);
	}
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/history');
	};
	$scope.prevStep = function() {
		$location.path('/materialList');
	};
	
});
app.controller("historyController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.linkBox1'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$.device.fileClose();
	$scope.statusName = data.statusName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	clearInterval(t);
//	time();
	addAnimate($('.linkBox1'))
	// 个人电子证照上传
	$scope.scanPhoto = function() {
		$location.path('/materialPic');
	};
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/materialPic1');
	};
	$scope.prevStep = function(){
		$location.path('/uploadMethod');
	}
});

app.controller("finishController", function($scope, $route, $http, $location, data, $timeout,$compile) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();

	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	$scope.itemName = name;
//	clearInterval(t);
//	time();
	$.device.cmCaptureShow(680, 530, 210, 340);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	// 
	$scope.isFinish = false;
	$scope.isLoading = true;
	// 拍照
	var imgHTML = '';
	var imgIndex = 0;
	var isShow = true;
	$scope.next = function() {
		if(isShow == false){
			$.device.cmCaptureShow(680, 530, 210, 340); // 开启高拍仪
			isShow = true;
			return;
		}
		$.device.cmCaptureHide(); // 关闭高拍仪
		$scope.isLoading = false;
		var scanImg = $.device.cmCaptureCaptureUrl();
		$scope.jsonData1 = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
			fileName: "photo1.png"
		};
		$scope.jsonData1 = JSON.stringify($scope.jsonData1);
//		urlHost 
//		http://12.113.230.10:8080/ac
//		JA_Extranet_urlHost
		$.device.httpUploadSafe("http://101.230.224.65:8080/ac/aci/materialUp/uploadFile.do", "fileInput", scanImg,
			$scope.jsonData1,
			function(result) {
//				alert(scanImg);
				data.resetData = "";
				data.uploadStuffId = data.stStuffId;
				if(data.listImg.length < 1) {
					data.currentIndex++; // 没有材料列表时   文件下标+1 
				}
				//					data.isUpload.push({
				//						index: data.currentIndex,
				//						stuffName: data.stStuffName,
				//						img:scanImg,
				//						method:"高拍仪"
				//					});
				$scope.isfinishUp.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				console.log(data.isUpload);
				data.fileName.push('扫描文件');
				$.log.debug("scanImg:" + scanImg);
//				'+scanImg+'
				imgHTML+='<div ng-click="show()" class="img" id="'+data.uploadStuffId+'"><img src="'+scanImg+'" width="150" height="200" /></div>';
				$('.imgBox').html($compile(imgHTML)($scope));
				$scope.$apply();
				$scope.isFinish = true;
				$scope.isLoading = true;
				$.device.cmCaptureShow(680, 530, 210, 340); // 开启高拍仪
				$scope.$apply();
			},
			function(webexception) {
				$scope.isLoading = true;
				layer.msg("上传材料失败")
				$timeout(function() {
					$location.path('/finish');
				}, 10000);

			});
			$scope.show = function(){
				$.device.cmCaptureHide();
				$(".img").viewer({
					url:"src",
				});				
				isShow = false;
			};
			
	};
	// 完成拍照
	$scope.finishUpload = function() {
		for(var i = 0; i < data.isUpload.length; i++) {
			if(data.currentIndex == data.isUpload[i].index) {
				data.isUpload[i] = "";
			}
		}
		for(var i = 0; i < $scope.isfinishUp.length; i++) {
			data.isUpload.push($scope.isfinishUp[i]);
		}
		$timeout(function() {
			$.device.cmCaptureHide(); // 关闭高拍仪
			$location.path('/materialList');
		}, 20);
	};

	$scope.prevStep = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialList');
	}
});

app.controller("takePhotoController", function($scope, $route, $http, $location, data, $timeout, $routeParams) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	$scope.itemName = name;
//	clearInterval(t);
//	time();
	try {
		$.device.fileOpen(function(value) {
			$timeout(function() {
				$scope.UData = value; //"E://123.txt";// 
			}, 100)

		});
	} catch(e) {
		$timeout(function() {
			layer.msg("请插入U盘后操作");
		}, 10000)
		$location.path("/uploadMethod");
	}

	// 继续U盘上传
	$scope.takePhoto = function() {
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				applyId: data.applyId,
				/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
				stuffId: data.stStuffId,
				fileName: $scope.UData,
				reset: data.resetData,
				type: "2",
				itemId: data.itemId,
				stuffName: data.stStuffName,
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
//			urlHost
//			http://101.230.224.65:8080/ac
//			http://12.113.230.10:8080/ac
			$.device.httpUploadSafe("http://101.230.224.65:8080/ac/aci/materialUp/uploadFile.do", "fileInput", $scope.UData,
				$scope.jsonData1,
				function(result) {
//					alert(result);
					data.resetData = "";
					layer.msg("上传成功");
					data.fileName.push($scope.UData);
					//					data.isUpload.push({
					//						index: data.currentIndex,
					//						stuffName: data.stStuffName,
					//						fileName:$scope.UData,
					//						method:"U盘上传"
					//					});
					$scope.isfinishUp.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					//					$timeout(function() {
					//						$location.path('/materialList');
					//					}, 10000);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
		try {
			$.device.fileOpen(function(value) {
				$timeout(function() {
					$scope.UData = value; //"E://123.txt";// 
				}, 100)
			});
		} catch(e) {
			$timeout(function() {
				layer.msg("请插入U盘后操作");
			}, 100)
			$location.path("/uploadMethod");
		}
	};
	// 上传
	$scope.highCapture = function() {
//		alert(data.applyId);
//		alert(data.stStuffId);
//		alert($scope.UData);
//		alert(data.resetData);
//		alert(data.itemId);
//		alert(data.stStuffName);
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				applyId: data.applyId,
				/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
				stuffId: data.stStuffId,
				fileName: $scope.UData,
				reset: data.resetData,
				type: "2",
				itemId: data.itemId,
				stuffName: data.stStuffName,
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
//			urlHost
//			preUrl_JA_intranet
			$.device.httpUploadSafe("http://101.230.224.65:8080/ac/aci/materialUp/uploadFile.do", "fileInput", $scope.UData,
				$scope.jsonData1,
				function(result) {
//					alert(result);
					layer.msg("上传成功");
					data.resetData = "";
					data.fileName.push($scope.UData);
					//					data.isUpload.push({
					//						index: data.currentIndex,
					//						stuffName: data.stStuffName,
					//						fileName:$scope.UData,
					//						method:"U盘上传"
					//					});
					$scope.isfinishUp.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					for(var i = 0; i < data.isUpload.length; i++) {
						if(data.currentIndex == data.isUpload[i].index) {
							data.isUpload[i] = "";
						}
					}
					for(var i = 0; i < $scope.isfinishUp.length; i++) {
						data.isUpload.push($scope.isfinishUp[i]);
					}
					$timeout(function() {
						$location.path('/materialList');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
	};
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
});

app.controller("materialViewController", function($scope, $http, $location, data) {
	$scope.stuffList = [];
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	for(var i = 0; i < data.view.length; i++) {
		if(data.currentIndex == data.view[i].index) {
			$scope.stuffList.push(data.view[i]);
		}
	}
	
/*	测试代码
 * 	alert($scope.stuffList.length);
	$scope.stuffList = [
		{img: '../libs/common/images/bank1.jpg'},
		{img: '../libs/common/images/bank2.jpg'},
		{img: '../libs/common/images/bank3.jpg'}
	]
	$scope.stuffList[0].method = "个人档案";
	console.log($scope.stuffList[0].method)
	
 	图片预览时, 动态改变父盒子宽度
	$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
  	var uploadImgList = $('.scan .swiper img');
  	$('.scan .swiper').width(uploadImgList.length * 350);
	});
	
 	图片预览时, 动态改变父盒子宽度
	var uploadImgList = $('.scan .swiper img');
	$('.scan .swiper').width(uploadImgList.length * 350);
 * 
 *	测试代码结束
 * */

	if($scope.stuffList[0].method === "高拍仪") {
		$scope.scanShow = true;
		$scope.upanShow = false;
		$scope.picShow = false;
	} else if($scope.stuffList[0].method === "U盘上传") {
		$scope.scanShow = false;
		$scope.upanShow = true;
		$scope.picShow = false;
	} else if($scope.stuffList[0].method === "个人档案") {
		$scope.scanShow = false;
		$scope.upanShow = false;
		$scope.picShow = true;
	}
	
	//放大样张
	$scope.showWord = function() {
		$(".word").viewer({
			url: "data-original",
		});
	}
	//放大已上传材料
	$scope.showScan = function() {
		$(".scan").viewer({
			url: "data-original",
		});
	}
	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch(e) {
			layer.msg("未找到此文件");
		}
	}
	$scope.prevStep = function(){
		$location.path('/materialList');
	};
});

app.controller("materialPic1Controller", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	var Btn = document.getElementById("consureOnclick");
	Btn.style.display = "block";
	$scope.itemName = name;
	$scope.imgUrls = "";
	clearInterval(t);
//	time();
	$scope.profileShow = function() {
//		$.ajax({
//			//			url: "http://218.202.254.222/aci/autoterminal/dzzz/queryCertBaseData.do",
////			url: "http://218.202.254.222/aci/autoterminal/forward.do",
//			url: JA_Extranet_urlHost + "/aci/autoterminal/forward.do",
//			type: "get",
//			dataType: "jsonp",
//			jsonp: "jsonpCallback",
//			data: {
//				//				jsonpCallback: "JSON_CALLBACK",
//				//				certNo: data.idCardNum, //"340881199303145313" || idCardNum  idCardName
//				//				type: 0
//				fmd: "aci-archives",
//				fdo: "getLicenseStuffList",
//				jsonpCallback: "JSON_CALLBACK",
//				stName: data.idCardName, // "夏雷" ||"340881199303145313" || 
//				stIdNo: data.idCardNum,
//				type: 0
//			},
//			success: function(json) {
//				var dataJson = eval("(" + JSON.stringify(json) + ")");
//				if(!dataJson) { // !dataJson[0].address
//					layer.msg("没有数据，请重新选择上传方式!");
//					$timeout(function() {
//						$location.path('/uploadMethod');
//					}, 1000);
//				} else {
//					$scope.imgUrls = dataJson;
//					$scope.$apply();
//					console.log($scope.imgUrls)
//				}
//			},
//			error: function(err) {
//				console.log(err)
//				layer.msg("请求失败!");
//			}
//		});
		
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			type: '0', //"0" ||
			fmd: "aci-archives",
			fdo: "getLicenseStuffList",
			stName: data.idCardName, // "夏雷" ||"340881199303145313" || 
			stIdNo: data.idCardNum
		};
		$timeout(function() {
			$http.jsonp(JA_Extranet_urlHost + "/aci/autoterminal/forward.do", {
					params: httpConfig
				})
				.success(function(json) {
					var dataJson = eval("(" + JSON.stringify(json) + ")");
					if(dataJson == 0) { // !dataJson[0].address //!dataJson
						layer.msg("没有数据，请重新选择上传方式!");
						$timeout(function() {
							$location.path('/uploadMethod');
						}, 1000);
					} else {
						$scope.imgUrls = dataJson;
						$scope.$apply();
						console.log($scope.imgUrls)
					}
				})
				.error(function(err) {
					console.log(err);
				})
		})
	};

	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		$scope.current = index;
	}
	$scope.isLoading = true;
	$scope.goNext = function() {
		Btn.style.display = "none";
//		" http://218.202.254.222"
		data.selectImg = JA_Extranet_urlHost + $scope.imgUrls[$scope.current].imageUrl;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			fileName: "waitUploadImg.jpg",
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//将选中图片上传到服务器
//				urlHost
				$.device.httpUpload(JA_Extranet_urlHost + '/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						data.resetData = "";
						layer.msg("上传成功");
						$scope.isLoading = true;
						//						data.isUpload.push({
						//							index: data.currentIndex,
						//							stuffName: data.stStuffName,
						//							img:$scope.waitUploadImgUrl,
						//							method:"个人档案"
						//						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};

});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	var Btn = document.getElementById("consureOnclick");
	Btn.style.display = "block";
	$scope.itemName = name;
	$scope.imgUrls = "";
//	clearInterval(t);
//	time();
	$scope.profileShow = function() {
//		$.ajax({
//			url: "http://180.169.7.194:8080/ac-product/aci/autoterminal/dzzz/queryCertBaseData.do",
//			type: "get",
//			dataType: "jsonp",
//			jsonp: "jsonpCallback",
//			data: {
//				certNo: data.idCardNum, //"340881199303145313" || 
//				type: 0,
//				jsonpCallback: "JSON_CALLBACK"
//			},
//			success: function(json) {
//				var dataJson = eval("(" + JSON.stringify(json) + ")");
//				
//				if(!dataJson) { // !dataJson[0].address
//					layer.msg("没有数据，请重新选择上传方式!");
//					$timeout(function() {
//						$location.path('/uploadMethod');
//					}, 1000);
//				} else {
//					$scope.imgUrls = dataJson;
//					$scope.$apply();
//					console.log($scope.imgUrls)
//				}
//			},
//			error: function(err) {
//				console.log(err)
//			}
//		});

		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: data.idCardNum, //'310105198006031219', //data.idCardNum, //"340881199303145313" || 
			type: '0', //"0" ||
			machineId: $.config.get('uniqueId') || "",
			itemName: "",
			itemCode: "",
			businessCode: "",
			name: data.idCardName,
			startDay: data.idcard_valid_start_day || '',
			endDay: data.idcard_valid_end_day || ''
		};
		$timeout(function() {
			$http.jsonp(JA_Extranet_urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(json) {
					var dataJson = eval("(" + JSON.stringify(json) + ")");
					console.log(dataJson.length);
					if(dataJson.length == 0) { // !dataJson[0].address //!dataJson
//						layer.msg("没有数据，请重新选择上传方式!");
						$scope.isAlert = true;
						$scope.noDzzzData = true;
						$scope.msg = '没有数据,请重新选择上传方式!';
						$timeout(function() {
							$location.path('/uploadMethod');
						}, 1000);
					} else {
						$scope.imgUrls = dataJson;
//						$scope.$apply();
						console.log($scope.imgUrls)
					}
				})
				.error(function(err) {
					console.log(err);
				})
		})
	};
	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		$scope.current = index;
	}
	$scope.isLoading = true;
	$scope.goNext = function() {
		if($scope.current == 0) {
			layer.msg("请选择需要上传的材料");
			return;
		}
		Btn.style.display = "none";
//" http://180.169.7.194:8080/ac-product"
		data.selectImg = "http://180.169.7.194:8080/ac-product" + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			fileName: "waitUploadImg.jpg",
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				console.log($scope.jsonData);
				//将选中图片上传到服务器
//				urlHost  http://10.237.16.72
				$.device.httpUpload(JA_Extranet_urlHost + '/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						console.log($scope.jsonData);
						data.resetData = "";
						layer.msg("上传成功");
						$scope.isLoading = true;
						//						data.isUpload.push({
						//							index: data.currentIndex,
						//							stuffName: data.stStuffName,
						//							img:$scope.waitUploadImgUrl,
						//							method:"个人档案"
						//						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
						console.log($scope.jsonData);
					});
			},
			function(webexception) {
				alert("下载文档失败");
				console.log($scope.jsonData);
			}
		);

	};

});
app.controller("infoFinishController", function($scope, $route, $http, $location, data, $timeout) {
	var lodop = $.device.printGetLodop();
	var name = data.itemName;
	data.applyNo = "";
	console.log(name + "提交办事名字测试");
	var statusName = data.statusName;
	console.log(statusName + "提交办事情形测试");
	$scope.allName = name + '--' + statusName;
	$scope.username = data.username;
	$scope.materialNameList = data.materialNameList;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	clearInterval(t);
//	time();
	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = {
			//		applyNo: data.applyNo, // '751122018600008'
			//		subItemCodes: '', // data.itemId
			applyId: data.applyId,
			userName: data.idCardName,
			subitemNos: "",
			itemNo: data.itemNo,
			jsonpCallback: "JSON_CALLBACK",
		};
//		urlHost
		$http.jsonp(JA_Extranet_urlHost + '/aci/materialUp/toSubmit.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			console.log(dataJson);
			console.log('办件编码: ' + dataJson.applyNo);
			data.applyNo = dataJson.applyNo;
			$scope.applyNo = data.applyNo;
			$scope.statusText = '打印凭证';
//			http://218.202.254.222
			var express_Delivery_QRCode = JA_Extranet_urlHost + "/aci/ems/getNotice.do?stApplyId=" + data.applyId;
			// 进度查询需要传data.applyNo
//			var progress_Query_QRCode = JA_Extranet_urlHost + "/aci/workapply/getNewQueueInfo.do?t=b&bid=" + data.applyNo;
			var progress_Query_QRCode = "http://xzfwzx.jingan.gov.cn:8080/ac/aci/ac-self-front/OfflineHandle/index.html#/qrCode?applyNo=" + data.applyNo;
			var date = new Date();
			var month = date.getMonth() + 1;
			// 打印凭条
			var f = 0;
			$scope.print = function() {
				f++;
				$scope.statusText = '返回首页';
				if($scope.statusText == '返回首页' && f == 2) {
					$.device.GoHome();
				} else {
					lodop = $.device.printGetLodop();
					lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", $scope.applyNo);
					lodop.ADD_PRINT_BARCODE(15, 550, 100, 100, "QRCode", express_Delivery_QRCode);
					lodop.ADD_PRINT_TEXT(112, 566, 100, 50, "快递寄送");
					lodop.ADD_PRINT_BARCODE(15, 660, 100, 100, "QRCode", progress_Query_QRCode);
					lodop.ADD_PRINT_TEXT(112, 681, 100, 50, "进度查询");
					lodop.ADD_PRINT_TEXT(130, 260, 300, 50, data.departmentOrganName); // 部门名称
					lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
					lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
					lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
					lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "办件编码：" + $scope.applyNo);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
					lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + $scope.username);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
					lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "申请事项：" + $scope.allName);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
					lodop.ADD_PRINT_TEXT(340, 28, 600, 30, "取证方式：" + data.getCertWay);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
					lodop.ADD_PRINT_TEXT(370, 28, 700, 30, "请您携带办理材料至静安区行政服务中心窗口或将办理材料放入证照柜中进行办理。");
					lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
					lodop.ADD_PRINT_TEXT(420, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
					lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
					//					lodop.ADD_PRINT_TEXT(480,28,670,30,"亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
					//					lodop.SET_PRINT_STYLEA(0,"FontSize",14);
					lodop.ADD_PRINT_HTML(480, 30, 700, $scope.materialNameList.length*150, document.getElementById('materialForm').innerHTML);
//					lodop.PRINT_DESIGN();
					lodop.PRINT();
				}
			};
			$scope.print();

		}).error(function(e) {
			console.log(e)
		});
	}

	$scope.submitApply();

});

app.controller("qrCodeController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoading = false;
	$scope.process = function() {
		var pConfig = {
			stApplyNo: $scope.applyNo,
//			stApplyNo: '14931015071300020036570',
			jsonpCallback: "JSON_CALLBACK"
		}
//		urlHost
		$http.jsonp(JA_Extranet_urlHost + '/aci/autoterminal/eventquery/getApplyInfoByStApplyNo.do', {
			params: pConfig
		}).success(function(dataJson) {
			console.log(dataJson)
			$scope.isLoading = true;
			$scope.applyNo = dataJson.stApplyNo;
			$scope.itemName = dataJson.stItemName;
			$scope.name = dataJson.stName || dataJson.stUnit;
			$scope.date = dataJson.stApplyStr;
			$scope.status = dataJson.stFinalState;
		}).error(function(err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});