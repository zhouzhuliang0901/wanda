app.controller("guidelineSybxController", function($scope, $route, $http, $location, $rootScope, $sce, data, $timeout,appFactory) {
	data.itemName = "生育保险";
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function(){
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
		$timeout(function(){
			$scope.isAlert = false;
		},3000);
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
	}
	$rootScope.goHome = function() {
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("infoSybxController", function($scope, $route, $http, $location, data, $rootScope,$timeout, appFactory) {
	$.device.Camera_Hide();
	$.device.idCardClose();
	var name = data.itemName;
	$scope.itemName = name;
	City();
	PublicchoiceById('stEducation');
	PublicchoiceById('stMaleEducation');
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}
	$scope.info = function() {
		$scope.stName = data.idCardName;
		$scope.stIdCard = data.idCardNum;
		$scope.stSex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.dtBirth = (data.idCardNum).substring(6, 10) + "-" + (data.idCardNum).substring(10, 12) + "-" + (data.idCardNum).substring(12, 14);
		$scope.stMomile = data.mobile || "";
		$timeout(function(){
			setSelectCheckedByText("nations", data.nations);
		},300);
		$scope.next1 = function(){
			//男方户口本
			var nConfig = {
				identNo: $scope.nIdCard, //data.idCardNum,
				catMainCode: "310105105000100"
			}
			//男方户口本信息
			var manLicense = $.ajax({
				type: "get",
				url:urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				timeout:5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: nConfig,
				success:function(dataJsonp){
					console.info(dataJsonp);
					$scope.hjc = (dataJsonp.SADDR.split('市')[1]).split('区')[0];
					$scope.hjs = (dataJsonp.SADDR.split('市')[1]).split('区')[1];
					setHjAdressText('maleProvince', 'maleCity', 'maleCounty', 'maleStreet', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0]+'镇');
					setHjAdressText('mLiveProvince', 'mLiveCity', 'mLiveCounty', 'mLiveStreet', "上海市", "上海市", $scope.hjc + '区',$scope.hjs.split('镇')[0]+'镇');
					$scope.ads = $scope.hjs.split('镇')[1];
					$scope.mStAddress = $scope.ads;
					$scope.mLiveStAddress = $scope.ads;
					$('#stMaleEducation a').eq(5).addClass('in');
					$scope.isLoding = true;
				},
				error:function(err){
					$scope.isLoding = true;
					console.info("queryMarriageCertificateInfo error");
				},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
			　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
			 　　　　　manLicense .abort();
			　　　　　$scope.next1();
			　　　　}
			　　}
			});
		}
		$scope.next2 = function(){
			//出生证明
			var iConfig = {
				identNo: data.idCardNum,
				catMainCode: "314910001000500"
			}
			//结婚证信息
			var marryLicense = $.ajax({
				type: "get",
				url:urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				timeout:5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: iConfig,
				success:function(dataJsonp){
					$scope.stChildName = dataJsonp.SNEONATALNAME || dataJsonp.SNAME;
					$scope.stMaleName = dataJsonp.SDADNAME;
					setSelectCheckedByText("maleNations", dataJsonp.SDADETHNIC);
					$scope.stMaleIdCard = dataJsonp.HOLDERCODE.split(",")[1];
					$scope.nIdCard = dataJsonp.HOLDERCODE.split(",")[1];
					console.log($scope.stMaleIdCard);
					$scope.next1();
				},
				error:function(err){
					console.info("queryMarriageCertificateInfo error");
					$scope.next1();
				},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
			　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
			 　　　　　marryLicense .abort();
			　　　　　 $scope.next2();
			　　　　}
			　　}
			})
		}
		$scope.next3 = function(){
			//结婚证明
			var bConfig = {
				identNo: data.idCardNum,
				catMainCode: "310105127000101"
			}
			//出生证信息
			var birthLicense = $.ajax({
				type: "get",
				url:urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				timeout:5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: bConfig,
				success:function(dataJsonp){
					$scope.stMarryId = dataJsonp.CERTCODE;
					$scope.dtMarry = dataJsonp.SDATE.split(" ")[0];
					$timeout(function(){
						$scope.next2();
					},300);
				},
				error:function(err){
					console.info("queryMarriageCertificateInfo error");
					$timeout(function(){
						$scope.next2();
					},300);
				},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
			　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
			 　　　　　birthLicense .abort();
			　　　　　$scope.next3();
			　　　　}
			　　}
			});
		}
		$scope.next4 = function(){
			//女方户口本
			var hConfig = {
				identNo: data.idCardNum,
				catMainCode: "310105105000100"
			}
			//女方户口本信息
			var womenLicense = $.ajax({
				type: "get",
				url:urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				/*加上datatype*/
				timeout:5000,
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: hConfig,
				success:function(dataJsonp){
					$scope.hjc = (dataJsonp.SADDR.split('市')[1]).split('区')[0];
					$scope.hjs = (dataJsonp.SADDR.split('市')[1]).split('区')[1];
					console.log($scope.hjs.split('镇')[0]+'镇');
					setHjAdressText('province', 'city', 'county', 'street', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0]+'镇');
					setHjAdressText('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0]+'镇');
					$scope.ads = $scope.hjs.split('镇')[1];
					$scope.stAddress = $scope.ads;
					$scope.liveStAddress = $scope.ads;
					$('#stEducation a').eq(5).addClass('in');
					$timeout(function(){
						$scope.next3();
					},300);
					
				},
				error:function(err){
					console.info("queryMarriageCertificateInfo error");
					$timeout(function(){
						$scope.next3();
					},300);
				},
				complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
			　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
			 　　　　　womenLicense .abort();
			　　　　　  $scope.next3();
			　　　　}
			　　}
			});
		}
		$scope.next5 = function(){
			//身份证信息
			var manIdcard = $.ajax({
				type: "get",
				url:urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				timeout:5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					identNo: data.idCardNum,
					catMainCode:"310105109000100"
				},
				success:function(dataJsonp){
					console.info(dataJsonp);
					setSelectCheckedByText("nations", dataJsonp.SETHNIC);
					$timeout(function(){
						$scope.next4();
					},300);
				},error:function(err){
					console.info("queryMarriageCertificateInfo error");
					$timeout(function(){
						$scope.next4();
					},300);
				},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
			　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
			 　　　　　manIdcard .abort();
			　　　　　  $scope.next5();
			　　　　}
			　　}
			})
		}
		$scope.next5();
	}
	$scope.info();
	$scope.synchronize = function() {
		if($('#liveProvince').attr('vName') == "上海市") {
			$('#stPostCode').val("200000");
		}
		$scope.lStreet =($('#liveStreet').attr('vName') == "选择街/镇")? "":$('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
			$scope.lStreet + (($scope.liveStAddress == undefined) ? "" : $scope.liveStAddress);
		$('#stPostAddress').val($scope.address);
	}
	$scope.synchronize1 = function() {
		setHjAdress('maleProvince', 'maleCity', 'maleCounty', 'maleStreet', $('#province').attr('vId'), $('#city').attr('vId'), $('#county').attr('vId'), $('#street').attr('vId'));
		$('#mStAddress').val($scope.stAddress);
	}
	$scope.synchronize2 = function() {
		setHjAdress('mLiveProvince', 'mLiveCity', 'mLiveCounty', 'mLiveStreet', $('#liveProvince').attr('vId'), $('#liveCity').attr('vId'), $('#liveCounty').attr('vId'), $('#liveStreet').attr('vId'));
		$('#mLiveStAddress').val($scope.liveStAddress);
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
		data.maleNations =$('#maleNations').attr('vId');
		data.maleIdCard = $scope.stMaleIdCard;
		data.maleMobile = $scope.stMaleMomile;
		data.childName = $scope.stChildName;
		data.childIdCard= $scope.stChildIdCard;
		data.jzAddress = {
			liveProvince:$('#liveProvince').attr('vId'),
			liveCity:$('#liveCity').attr('vId'),
			liveCounty:$('#liveCounty').attr('vId'),
			liveStreet:$('#liveStreet').attr('vId')
		}
		data.hjAddress = {
			province:$('#province').attr('vId'),
			city:$('#city').attr('vId'),
			county:$('#county').attr('vId'),
			street:$('#street').attr('vId')
		}
		data.jzAddressName = {
			liveProvince:$('#liveProvince').attr('vName'),
			liveCity:$('#liveCity').attr('vName'),
			liveCounty:$('#liveCounty').attr('vName'),
			liveStreet:$('#liveStreet').attr('vName')
		}
		data.hjAddressName = {
			province:$('#province').attr('vName'),
			city:$('#city').attr('vName'),
			county:$('#county').attr('vName'),
			street:$('#street').attr('vName')
		}
		//配偶
		data.mjzAddress = {
			liveProvince:$('#mLiveProvince').attr('vId'),
			liveCity:$('#mLiveCity').attr('vId'),
			liveCounty:$('#mLiveCounty').attr('vId'),
			liveStreet:$('#mLiveStreet').attr('vId')
		}
		data.mhjAddress = {
			province:$('#maleProvince').attr('vId'),
			city:$('#maleCity').attr('vId'),
			county:$('#maleCounty').attr('vId'),
			street:$('#maleStreet').attr('vId')
		}
		data.mjzAddressName = {
			liveProvince:$('#mLiveProvince').attr('vName'),
			liveCity:$('#mLiveCity').attr('vName'),
			liveCounty:$('#mLiveCounty').attr('vName'),
			liveStreet:$('#mLiveStreet').attr('vName')
		}
		data.mhjAddressName = {
			province:$('#maleProvince').attr('vName'),
			city:$('#maleCity').attr('vName'),
			county:$('#maleCounty').attr('vName'),
			street:$('#maleStreet').attr('vName')
		}
		//判断是否有女方身份证照
		$scope.nextload4 = function() {
			//判断是否有男方户籍证明
			appFactory.pro_fetch($('#stMaleIdCard').val(), '310105105000100', 'YJS00020004','YJS0002','男方户籍证明','1',function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr4 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length<=3){
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
				$location.path("/materialSybxList");
				$scope.$apply();
			},function(status){
				if(status=='timeout'){
		 　　　　　$scope.nextload4();
		　　　　}
			});
		}
		$scope.nextload3 = function() {
			//判断是否有女方户籍证明
			appFactory.pro_fetch(data.idCardNum, '310105105000100','YJS00020002','YJS0002','女方户籍证明','1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr3 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length<=2){
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
			},function(status){
				if(status=='timeout'){
		 　　　　　$scope.nextload3();
		　　　　}
			})
		}
		$scope.nextload2 = function() {
			//判断是否有男方身份证照
			appFactory.pro_fetch($('#stMaleIdCard').val(), '310105109000100', 'YJS00020003','YJS0002','男方居民身份证','1',function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr2 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length<=1){
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
			},function(status){
				if(status=='timeout'){
		 　　　　　$scope.nextload2();
		　　　　}
			})
		}
		$scope.nextload1 = function(){
			appFactory.pro_fetch(data.idCardNum, '310105109000100', 'YJS00020001','YJS0002','女方居民身份证','1',function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr1 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length<=0){
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
			},function(status){
				if(status=='timeout'){
		 　　　　　$scope.nextload1();
		　　　　}
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
app.controller("materialSybxListController", function($scope, $route, $http, $location, $rootScope,data, $timeout,appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	//必传材料列表
	data.currentIndex = 0;
	$scope.current = 0;
	console.log(data.isUpload);
	
	//设置上传文件 按钮变化
	$scope.btn = function() {
		data.listImg = [{
			'index': 0,
			'stuffName': "1、女方居民身份证",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020001",
			"needflag": "1"
		}, {
			'index': 1,
			'stuffName': "2、男方居民身份证",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020003",
			"needflag": "1"
		}, {
			'index': 2,
			'stuffName': "3、女方户籍证明",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020002",
			"needflag": "1"
		}, {
			'index': 3,
			'stuffName': "4、男方户籍证明",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020004",
			"needflag": "1"
		}, {
			'index': 4,
			'stuffName': "5、银行卡",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020005",
			"needflag": "1"
		}, {
			'index': 5,
			'stuffName': "6、生育医学证明",
			'upload': false,
			'upload2': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020006",
			"needflag": "1"
		}];
		console.log(data.isUpload);
		console.log(data.listImg);
		if(data.isUpload != "") {
				for(var i = 0; i < data.isUpload.length; i++) {
					for(var j = 0; j < data.listImg.length; j++) {
						console.log(data.isUpload[i].status);
						if(data.isUpload[i].status ==1){
							if(data.listImg[j].upload != false) {
								if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
									console.log(i);
									data.listImg[j].upload = false;
									data.listImg[j].upload2 = true;
								}
							}
						}else if(data.isUpload[i].status ==0){
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
	$scope.toUploadMaterial = function(index, name,affairscode,archivescode,needflag) {
		//		data.stStuffId = id;
		//		data.stStuffCode = code;
		data.stStuffName = name;
		data.affairscode = affairscode;
		data.archivescode = archivescode;
		data.needflag = needflag;
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
app.controller("signatureSybxController", function($scope, $http, $location, $rootScope,data) {
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
});