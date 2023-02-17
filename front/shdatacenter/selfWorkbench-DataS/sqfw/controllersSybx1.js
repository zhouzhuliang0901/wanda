app.controller("guidelineSybxController", function($scope, $route, $http, $location, $rootScope, $sce, data, $timeout, appFactory) {
	data.itemName = "生育保险";
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.guideInfo = {
		clName: "<p>生育保险 </p >", //事项名称
		clDept: "市卫健委、市人社局", //主管部门
		clApplyConds: "<p>申请对象需符合以下条件：</p ><p>1、	申请人为本市户籍的在职妇女，与用人单位建立过劳动关系的且所在单位已参加本市城镇职工生育保险并按规定建立了个人账户且生产当月必须正常缴费；</p ><p>2、	申请人在本市民政机构登记结婚，且为首次婚姻；</p ><p>3、	申请人在计划内生产第一个、第二个子女的，且在本市医疗机构生育的；</p >", //申报条件
		clApprovalMater: "<p>申请对象的（需原件）：</p ><p>1、申请人有效身份证；</p ><p>2、申请人户籍证明；</p ><p>3、申请人婚姻状况证明；</p ><p>4、申请人配偶有效身份证；</p ><p>5、申请人配偶户籍证明；</p ><p>6、《生育医学证明》原件；</p ><p>7、子女的户籍证明原件；</p ><p>8、在本市指定金融机构范围内（需现场校验）开立的实名制结算账户卡。</p >",
		clApprovalProcess: "<p>【本事项可“ 全市通办 ”】符合申请条件的人员，可通过自助服务设备等渠道提交申请，申请人户籍所在街道、镇社区事务受理服务中心会对申请进行处理。</p >", // 办理流程
		clChargeStd: "<p>免费</p >",
		clContext: "在外省市或国外生育的请视频链接工作人员进行现场指导" //备注
	};
	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_TEXT(40, 354, 250, 50, "办事指南");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.ADD_PRINT_HTM(70, 45, "100%", "100%", "<style> dd{width:620px;} dt{font-weight:bold}</style><body>" + document.getElementById("scrollBox2").innerHTML + "</body>");
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.next = function() {
		data.clDept = $scope.guideInfo.clDept;
		$location.path("/select");
		//		$location.path("/materialSybxList");
	}
	$scope.prev = function() {
		window.location.href = "../declare/index.html";
	}
	$rootScope.goHome = function() {
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("infoSybxController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$.device.idCardClose();
	var name = data.itemName;
	var date = new Date();
	$scope.itemName = name;
	City();
	shouliCenter();
	PublicchoiceById('stEducation');
	PublicchoiceById('stMaleEducation');
	WdatePicker({ //日期插件
		eCont: 'datepicker',
		doubleCalendar: true,
		dateFmt: 'yyyy-MM-dd',
		minDate: '%y-%M-{%d+1}',
		opposite: true,
		disabledDates: date
	});
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}
	$scope.isLoding = false;
	$scope.info = function() {
		$scope.stName = data.idCardName;
		$scope.stIdCard = data.idCardNum;
		$scope.stSex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.dtBirth = (data.idCardNum).substring(6, 10) + "-" + (data.idCardNum).substring(10, 12) + "-" + (data.idCardNum).substring(12, 14);
		$scope.stMomile = data.mobile || "";
		$scope.stChildName = "陈严佑";
		$scope.stMaleName = "陈擂";
		$scope.stMaleMomile = "13524622962";
		setSelectCheckedByText("maleNations", "汉族");
		$scope.stMaleIdCard = "310115198606194014";
		$scope.stChildIdCard = "310115201404254014";
		$timeout(function() {
			setHjAdressText('maleProvince', 'maleCity', 'maleCounty', 'maleStreet', 'maleNeighborhood', "上海市", "上海市", '浦东新区', '北蔡镇', '卫行村村委会');
		}, 300);
		$timeout(function() {
			setHjAdressText('mLiveProvince', 'mLiveCity', 'mLiveCounty', 'mLiveStreet', 'mLiveNeighborhood', "上海市", "上海市", '浦东新区', '北蔡镇', '卫行村村委会');
		}, 300);
		$timeout(function() {
			setHjAdressText('province', 'city', 'county', 'street', 'neighborhood', "上海市", "上海市", '浦东新区', '大团镇', '团新村村委会');
		}, 300);
		$timeout(function() {
			setHjAdressText('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', 'liveNeighborhood', "上海市", "上海市", '浦东新区', '大团镇', '团新村村委会');
		}, 300);
		$scope.mStAddress = "卫行村张浜队曹家宅5号";
		$scope.mLiveStAddress = "卫行村张浜队曹家宅5号";
		$scope.stMarryId = "J310115-2011-305187女";
		$scope.dtMarry = "2011-10-18";
		$scope.stAddress = "团新村严楼404号";
		$scope.liveStAddress = "团新村严楼404号";
		setSelectCheckedByText("nations", "汉族"); -
		$('#stMaleEducation a').eq(5).addClass('in');
		$('#stEducation a').eq(5).addClass('in');
		$timeout(function() {
			$scope.isLoding = true;
		}, 3000);
	}
	$scope.info();
	$scope.synchronize = function() {
		if($('#liveProvince').attr('vName') == "上海市") {
			$('#stPostCode').val("200000");
		}
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
			$scope.lStreet + (($scope.liveStAddress == undefined) ? "" : $scope.liveStAddress);
		$('#stPostAddress').val($scope.address);
	}
	$scope.synchronize1 = function() {
		setHjAdress('maleProvince', 'maleCity', 'maleCounty', 'maleStreet', 'maleNeighborhood', $('#province').attr('vId'), $('#city').attr('vId'), $('#county').attr('vId'), $('#street').attr('vId'), $('#neighborhood').attr('vId'));
		$('#mStAddress').val($scope.stAddress);
	}
	$scope.synchronize2 = function() {
		setHjAdress('mLiveProvince', 'mLiveCity', 'mLiveCounty', 'mLiveStreet', 'mLiveNeighborhood', $('#liveProvince').attr('vId'), $('#liveCity').attr('vId'), $('#liveCounty').attr('vId'), $('#liveStreet').attr('vId'), $('#liveNeighborhood').attr('vId'));
		$('#mLiveStAddress').val($scope.liveStAddress);
	}
	$scope.ifmulti = function() {
		console.log($scope.app_ifmulti);
		if($scope.app_ifmulti == "是") {
			$scope.show = true;
		} else {
			$scope.show = false;
		}
	}
	$scope.prevStep = function() {
		$location.path("/select");
	}
	// 保存数据
	$scope.flag = true;
	$scope.next = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			//			if($('#nations').attr('vName') < 1) {
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请选择民族！";
			//				return;
			//			}
			//			if($('#province').attr('vName')<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请选择户籍省份！";
			//				return;
			//			}
			//			if($('#city').attr('vName')<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请选择户籍城市！";
			//				return;
			//			}
			//			if($('#county').attr('vName')<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请选择户籍区/县！";
			//				return;
			//			}
			//			if($('#liveProvince').attr('vName')<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请选择居住省份！";
			//				return;
			//			}
			//			if($('#liveCity').attr('vName')<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请选择居住城市！";
			//				return;
			//			}
			//			if($('#liveCounty').attr('vName')<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请选择居住区/县！";
			//				return;
			//			}
			//			if($scope.stRecipientPostCode.length<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请输入收件地址邮编！";
			//				return;
			//			}
			//			if($scope.stRecipientPostAddress.length<1){
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请输入收件地址！";
			//				return;
			//			}
		} while (condFlag);
		$scope.flag = false;
		data.nations = $('#nations').attr('vId');
		data.nationName = $('#nations').attr('vName');
		//提交所需字段
		data.centerCode = $('#stCenter').attr('vId');
		data.centerAddress = $('#stCenter').attr('vName');
		data.address = $scope.stAddress;
		data.liveAddress = $scope.liveStAddress;
		data.maddress = $scope.mStAddress;
		data.mliveAddress = $scope.mLiveStAddress;
		data.mobile = $('#stMomile').val();
		data.sex = $('#stSex').val();
		data.date = $scope.date;
		data.maleName = $scope.stMaleName;
		data.maleNations = $('#maleNations').attr('vId');
		data.maleIdCard = $scope.stMaleIdCard;
		data.maleMobile = $scope.stMaleMomile;
		data.childName = $scope.stChildName;
		data.childIdCard = $scope.stChildIdCard;
		data.iswy = ($scope.iswy == "是") ? "1" : "0";
		data.isdszv = ($scope.isdszv == "是") ? "1" : "0";
		data.mIsdszv = ($scope.mIsdszv == "是") ? "1" : "0";
		data.bony = $scope.bony;
		data.girln = $scope.girln;

		data.app_sydate = $scope.app_sydate;
		data.app_boys = $scope.app_boys;
		data.app_girls = $scope.app_girls;
		data.app_ifmulti = $scope.app_ifmulti;
		data.app_abortion = $scope.app_abortion;
		data.app_type = $scope.app_type || "";
		data.app_borg = $('#app_borg').val() || "";
		if(data.app_borg == "男") {
			data.app_borgid = "1";
		} else if(data.app_borg == "女") {
			data.app_borgid = "2";
		} else {
			data.app_borgid = "";
			data.app_borg = "";
		}
		data.jzAddress = {
			liveProvince: $('#liveProvince').attr('vId'),
			liveCity: $('#liveCity').attr('vId'),
			liveCounty: $('#liveCounty').attr('vId'),
			liveStreet: $('#liveStreet').attr('vId'),
			liveNbd: $('#liveNeighborhood').attr('vId')
		}
		data.hjAddress = {
			province: $('#province').attr('vId'),
			city: $('#city').attr('vId'),
			county: $('#county').attr('vId'),
			street: $('#street').attr('vId'),
			nbd: $('#neighborhood').attr('vId')
		}
		data.jzAddressName = {
			liveProvince: $('#liveProvince').attr('vName'),
			liveCity: $('#liveCity').attr('vName'),
			liveCounty: $('#liveCounty').attr('vName'),
			liveStreet: $('#liveStreet').attr('vName'),
			liveNbd: $('#liveNeighborhood').attr('vName'),
		}
		data.hjAddressName = {
			province: $('#province').attr('vName'),
			city: $('#city').attr('vName'),
			county: $('#county').attr('vName'),
			street: $('#street').attr('vName'),
			nbd: $('#neighborhood').attr('vName')
		}
		//配偶
		data.mjzAddress = {
			liveProvince: $('#mLiveProvince').attr('vId'),
			liveCity: $('#mLiveCity').attr('vId'),
			liveCounty: $('#mLiveCounty').attr('vId'),
			liveStreet: $('#mLiveStreet').attr('vId'),
			liveNbd: $('#mLiveNeighborhood').attr('vId')
		}
		data.mhjAddress = {
			province: $('#maleProvince').attr('vId'),
			city: $('#maleCity').attr('vId'),
			county: $('#maleCounty').attr('vId'),
			street: $('#maleStreet').attr('vId'),
			nbd: $('#maleNeighborhood').attr('vId')
		}
		data.mjzAddressName = {
			liveProvince: $('#mLiveProvince').attr('vName'),
			liveCity: $('#mLiveCity').attr('vName'),
			liveCounty: $('#mLiveCounty').attr('vName'),
			liveStreet: $('#mLiveStreet').attr('vName'),
			liveNbd: $('#mLiveNeighborhood').attr('vName')
		}
		data.mhjAddressName = {
			province: $('#maleProvince').attr('vName'),
			city: $('#maleCity').attr('vName'),
			county: $('#maleCounty').attr('vName'),
			street: $('#maleStreet').attr('vName'),
			nbd: $('#maleNeighborhood').attr('vName')
		}
		//判断是否有女方身份证照
		$scope.nextload4 = function() {
			//判断是否有男方户籍证明
			appFactory.pro_fetch($('#stMaleIdCard').val(), '310105105000100', 'YJS00020004', 'YJS0002', '男方户籍证明', '1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr4 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 3) {
						console.log(data.isUpload);
						data.isUpload.push({
							index: 3,
							stuffName: "4、男方户籍证明",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$scope.isLoding = true;
				$location.path("/materialSybxList");
				$scope.$apply();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload4();　　　　
				}
			}, function(dataJson) {
				data.imgId4 = dataJson.rtnData.imgid;
			});
		}
		$scope.nextload3 = function() {
			//判断是否有女方户籍证明
			appFactory.pro_fetch(data.idCardNum, '310105105000100', 'YJS00020002', 'YJS0002', '女方户籍证明', '1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr3 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 2) {
						data.isUpload.push({
							index: 2,
							stuffName: "3、女方户籍证明",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$scope.nextload4();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload3();　　　　
				}
			}, function(dataJson) {
				data.imgI3 = dataJson.rtnData.imgid;
			})
		}
		$scope.nextload2 = function() {
			//判断是否有男方身份证照
			appFactory.pro_fetch($('#stMaleIdCard').val(), '310105109000100', 'YJS00020003', 'YJS0002', '男方居民身份证', '1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr2 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 1) {
						data.isUpload.push({
							index: 1,
							stuffName: "2、男方居民身份证",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$scope.nextload3();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload2();　　　　
				}
			}, function(dataJson) {
				data.imgId2 = dataJson.rtnData.imgid;
			})
		}
		$scope.nextload1 = function() {
			appFactory.pro_fetch(data.idCardNum, '310105109000100', 'YJS00020001', 'YJS0002', '女方居民身份证', '1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr1 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 0) {
						data.isUpload.push({
							index: 0,
							stuffName: "1、女方居民身份证",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$scope.nextload2();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload1();　　　　
				}
			}, function(dataJson) {
				data.imgId1 = dataJson.rtnData.imgid;
			})
		}
		$scope.nextload1();

	};

	//滚动条
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("materialSybxListController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	//必传材料列表
	data.currentIndex = 0;
	$scope.current = 0;
	console.log(data.isUpload);
	$scope.nextText = "提交";
	//设置上传文件 按钮变化
	$scope.btn = function() {
		data.listImg = [{
			'index': 0,
			'stuffName': "1、女方居民身份证",
			'upload': true,
			'upload2': false,
			'upload3': false,
		}, {
			'index': 1,
			'stuffName': "2、男方居民身份证",
			'upload': true,
			'upload2': false,
			'upload3': false,
		}, {
			'index': 2,
			'stuffName': "3、女方户籍证明",
			'upload': true,
			'upload2': false,
			'upload3': false,
		}, {
			'index': 3,
			'stuffName': "4、男方户籍证明",
			'upload': true,
			'upload2': false,
			'upload3': false,
		}, {
			'index': 4,
			'stuffName': "5、银行卡",
			'upload': true,
			'upload2': false,
			'upload3': false,
		}, {
			'index': 5,
			'stuffName': "6、生育医学证明",
			'upload': false,
			'upload2': false,

		}];
		console.log(data.isUpload);
		console.log(data.listImg);
		if(data.isUpload != "") {
			for(var i = 0; i < data.isUpload.length; i++) {
				for(var j = 0; j < data.listImg.length; j++) {
					console.log(data.isUpload[i].status);
					if(data.isUpload[i].status == 1) {
						if(data.listImg[j].upload != false) {
							if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
								console.log(i);
								data.listImg[j].upload = false;
								data.listImg[j].upload2 = true;
							}
						}
					} else if(data.isUpload[i].status == 0) {
						if(data.listImg[j].upload != false) {
							if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
								data.listImg[j].upload = false;
								data.listImg[j].upload3 = true;
							}
						}
					}
				}
			}
		}
		console.info(data.listImg);
		$scope.listImg = data.listImg;
	}
	$scope.btn();
	// 材料上传 
	data.currentIndex++;
	$scope.toUploadMaterial = function(index, name) {
		//		data.stStuffId = id;
		//		data.stStuffCode = code;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function(index) {
		data.currentIndex = index;
		data.view = data.isUpload;
		$location.path("/materialView");
	}
	$scope.prevStep = function() {
		$location.path("/infoSybx");
	}
	//提交办件
	$scope.submit = function() {
		$location.path('/signatureSybx');
	};

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("signatureSybxController", function($scope, $http, $location, $rootScope, data) {
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		var name = data.itemName;
		$scope.itemName = name;
		$scope.signature = null;
		$scope.signatureFlag = false;
		$scope.SignatureBoardPlug = new SignatureBoardPlug({
			canvas: "#signature",
			clearBtn: ".clearRect",
			getSigntrue: ".saveImg",
			color: "black"
		});
	});

	$scope.isSignature = function() {
		$scope.signatureFlag = true;
	};
	$scope.notSignature = function() {
		$scope.signatureFlag = false;
	}
	$scope.saveSignature = function() {
		$scope.signature = $scope.SignatureBoardPlug.Signatrue;
		if($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		data.picStr = $scope.signature.split(",")[1];
		$location.path("/infoFinish");
	};
	$scope.prev = function(){
		$location.path("/materialSybxList");
	}
});