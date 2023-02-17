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
		clApplyConds: "<p>申请对象需符合以下条件：</p ><p>1、	申请人为本市户籍的在职妇女，与用人单位建立过劳动关系的且所在单位已参加本市城镇职工生育保险并按规定建立了个人账户且生产当月必须正常缴费；</p ><p>2、	申请人在本市民政机构登记结婚，且夫妻双方均为首次婚姻；</p ><p>3、	申请人为初育，且 2014年10月1日之后在本市医疗机构生育的，且出生证明中的父亲与配偶信息一致；</p ><p style='color: #fab93f;'>提示：鉴于自助设备条件有限，目前自助机仅供在申请人夫妻为初婚初育，且在本市民政机构结婚登记，及在本市医疗机构分娩。其它符合领取生育保险金条件的申请人请到窗口办理。</p >", //申报条件
		clApprovalMater: "<p>申请对象的（需原件）：</p ><p>1、申请人有效身份证；</p ><p>2、申请人户籍证明；</p ><p>3、申请人婚姻状况证明；</p ><p>4、申请人配偶有效身份证；</p ><p>5、申请人配偶户籍证明；</p ><p>6、申请人配偶的婚姻状况证明；</p ><p>7、《生育医学证明》原件（红、黄两联）；</p ><p>8、申请人有效银行卡；</p >",
		clApprovalProcess: "<p>【本事项可“ 全市通办 ”】符合申请条件的人员，可通过自助服务设备等渠道提交申请，申请人户籍所在街道、镇社区事务受理服务中心会对申请进行处理。</p >", // 办理流程
		clChargeStd: "<p>免费</p >",
		clContext: "" //备注
	};

	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		var lodop = $.device.printGetLodop('');
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
		//$location.path("/materialJybtList");
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
	$scope.itemName = name;
	$scope.isLoading = true;
	$scope.childsHtmlList = []; //生育孩子数html的div数  动态生成
	$scope.childsList = []; //子女信息数组
	data.childsInfo = ""; //子女信息 用于提交display字段
	$scope.nextText = "提交";
	City();
	shouliCenter();
	PublicchoiceById('stEducation');
	PublicchoiceById('stMaleEducation');
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.isLoading = true;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}
	$scope.info = function() {
		$scope.stName = data.idCardName;
		$scope.stIdCard = data.idCardNum;
		$scope.stSex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.dtBirth = (data.idCardNum).substring(6, 10) + "-" + (data.idCardNum).substring(10, 12) + "-" + (data.idCardNum).substring(12, 14);
		$scope.stMomile = data.mobile || "";
		$scope.stChildIdCard = "310115201404254014";
		$scope.stMaleMomile = "13524622962";
		$timeout(function() {
			setSelectCheckedByText("nations", data.nations);
		}, 300);
		$scope.next1 = function() {
			//男方户口本
			var nConfig = {
				identNo: $scope.nIdCard, //data.idCardNum,
				catMainCode: "310105105000100",
				machineId: $.config.get('uniqueId'),
				itemName: "生育保险",
				itemCode: "",
				businessCode: "",
				name: data.idCardName,
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			}
			//男方户口本信息
			var manLicense = $.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "json",
				timeout: 5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: nConfig,
				success: function(dataJsonp) {
					console.info(dataJsonp);
					setCenter();
					$scope.hjc = (dataJsonp.SADDR.split('市')[1]).split('区')[0];
					$scope.hjs = (dataJsonp.SADDR.split('市')[1]).split('区')[1];
					setHjAdressText('maleProvince', 'maleCity', 'maleCounty', 'maleStreet', 'maleNeighborhood', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + '镇', '');
					setHjAdressText('mLiveProvince', 'mLiveCity', 'mLiveCounty', 'mLiveStreet', 'mLiveNeighborhood', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + '镇', '');
					$scope.ads = $scope.hjs.split('镇')[1];
					$scope.mStAddress = $scope.ads;
					$scope.mLiveStAddress = $scope.ads;
					$('#stMaleEducation a').eq(5).addClass('in');
					$scope.isLoading = true;
				},
				error: function(err) {
					$scope.isLoading = true;
					console.info("queryMarriageCertificateInfo error");
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						manLicense.abort();　　　　　
						$scope.next1();　　　　
					}　　
				}
			});
		}
		$scope.next2 = function() {
			//出生证明
			var iConfig = {
				identNo: data.idCardNum,
				catMainCode: "314910001000500",
				name: data.idCardName,
				machineId: $.config.get('uniqueId'),
				itemName: "就业补贴",
				itemCode: "",
				businessCode: "",
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			}
			//结婚证信息
			var marryLicense = $.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "json",
				timeout: 5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: iConfig,
				success: function(dataJsonp) {
					$scope.stChildName = dataJsonp.SNEONATALNAME || dataJsonp.SNAME;
					$scope.stMaleName = dataJsonp.SDADNAME;
					setSelectCheckedByText("maleNations", dataJsonp.SDADETHNIC);
					$scope.stMaleIdCard = dataJsonp.HOLDERCODE.split(",")[1];
					$scope.nIdCard = dataJsonp.HOLDERCODE.split(",")[1];
					console.log($scope.stMaleIdCard);
					$scope.next1();
				},
				error: function(err) {
					console.info("queryMarriageCertificateInfo error");
					$scope.next1();
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						marryLicense.abort();　　　　　
						$scope.next2();　　　　
					}　　
				}
			})
		}
		$scope.next3 = function() {
			//结婚证明
			var bConfig = {
				identNo: data.idCardNum,
				catMainCode: "310105127000101",
				name: data.idCardName,
				machineId: $.config.get('uniqueId'),
				itemName: "生育保险",
				itemCode: "",
				businessCode: "",
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			}
			//出生证信息
			var birthLicense = $.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "json",
				timeout: 5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: bConfig,
				success: function(dataJsonp) {
					$scope.stMarryId = dataJsonp.CERTCODE;
					$scope.dtMarry = dataJsonp.MAKEDAY.split(" ")[0];
					$timeout(function() {
						$scope.next2();
					}, 300);
				},
				error: function(err) {
					console.info("queryMarriageCertificateInfo error");
					$timeout(function() {
						$scope.next2();
					}, 300);
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						birthLicense.abort();　　　　　
						$scope.next3();　　　　
					}　　
				}
			});
		}
		$scope.next4 = function() {
			//女方户口本
			var hConfig = {
				identNo: data.idCardNum,
				catMainCode: "310105105000100",
				name: data.idCardName,
				machineId: $.config.get('uniqueId'),
				itemName: "生育保险",
				itemCode: "",
				businessCode: "",
				startDay: data.VALIDSTARTDAY,
				endDay: data.VALIDENDDAY,
			}
			//女方户口本信息
			var womenLicense = $.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "json",
				/*加上datatype*/
				timeout: 5000,
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: hConfig,
				success: function(dataJsonp) {
					$scope.hjc = (dataJsonp.SADDR.split('市')[1]).split('区')[0];
					$scope.hjs = (dataJsonp.SADDR.split('市')[1]).split('区')[1];
					console.log($scope.hjs.split('镇')[0] + '镇');
					setHjAdressText('province', 'city', 'county', 'street', 'neighborhood', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + '镇', '');
					setHjAdressText('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', 'liveNeighborhood', "上海市", "上海市", $scope.hjc + '区', $scope.hjs.split('镇')[0] + '镇', '');
					$scope.ads = $scope.hjs.split('镇')[1];
					$scope.stAddress = $scope.ads;
					$scope.liveStAddress = $scope.ads;
					$('#stEducation a').eq(5).addClass('in');
					$timeout(function() {
						$scope.next3();
					}, 300);

				},
				error: function(err) {
					console.info("queryMarriageCertificateInfo error");
					$timeout(function() {
						$scope.next3();
					}, 300);
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						womenLicense.abort();　　　　　
						$scope.next3();　　　　
					}　　
				}
			});
		}
		$scope.next5 = function() {
			//身份证信息
			var manIdcard = $.ajax({
				type: "get",
				url: urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "json",
				timeout: 5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					identNo: data.idCardNum,
					catMainCode: "310105109000100",
					name: data.idCardName,
					machineId: $.config.get('uniqueId'),
					itemName: "生育保险",
					itemCode: "",
					businessCode: "",
					startDay: data.VALIDSTARTDAY,
					endDay: data.VALIDENDDAY,
				},
				success: function(dataJsonp) {
					console.info(dataJsonp);
					setSelectCheckedByText("nations", dataJsonp.SETHNIC);
					$timeout(function() {
						$scope.next4();
					}, 300);
				},
				error: function(err) {
					console.info("queryMarriageCertificateInfo error");
					$timeout(function() {
						$scope.next4();
					}, 300);
				},
				complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数
					　　　　
					if(status == 'timeout') { //超时,status还有success,error等值的情况
						　　　　　
						manIdcard.abort();　　　　　
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
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
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
	//是否多胞胎
	$scope.ifmulti = function() {
		if($scope.app_ifmulti == "是") {
			$scope.show = true;
		} else {
			$scope.show = false;
		}
	}

	$scope.changeChilds = function() {
		$scope.childsHtmlList = [];
		$scope.boyn = ($scope.boyn == undefined) ? 0 : $scope.boyn;
		$scope.girln = ($scope.girln == undefined) ? 0 : $scope.girln;
		$scope.childsSum = parseInt($scope.boyn) + parseInt($scope.girln);
		for(var i = 0; i < $scope.childsSum; i++) {
			$scope.childsHtmlList.push({
				name: "stChildName" + i,
				idCard: "stChildIdCard" + i,
				birth: "stChildBirth" + i,
				sex: "stChildSex" + i
			})
		}
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
			//子女信息
			$scope.childsInfo = "";
			for(var i = 0; i < $scope.childsHtmlList.length; i++) {
				if(!checkIdCard($("#stChildIdCard" + i).val())) {
					$scope.SisAlert = true;
					$scope.Smsg = "请输入正确子女身份证号！";
					return;
				} else {
					$scope.childsList.push({
						znname: $("#stChildName" + i).val(), //子女姓名
						zncardid: $("#stChildIdCard" + i).val(), //子女身份证
						relationCodeN: $("#mgx option:selected").text(), //与子女关系 
						relationCode: $('#mgx').val(), //与子女关系  ID
						zbirth: getBirthForIdCard($("#stChildIdCard" + i).val()) || "", //子女出生日期
						mgx: $("#mgx option:selected").text(), //与母亲关系
						fgx: $("#fgx option:selected").text(), //与父亲关系
						isfhq: $("#isfhq option:selected").text(), //是否父亲婚前生育
						ismhq: $("#ismhq option:selected").text(), //是否母亲婚前生育
						isbcs: $("#isbcs option:selected").text() //是否本次生育 
					})
					$scope.childsInfo = "姓名:" + $("#stChildName" + i).val() + ",身份证号:" + $("#stChildIdCard" + i).val() + ",与子女关系:" + $("#mgx option:selected").text();
				}
			}
			$scope.childsInfo = "(" + $scope.childsInfo + ")";
			data.childsInfo = $scope.childsInfo;
			data.childsList = $scope.childsList;
			console.log($scope.childsList);
			console.log(data.childsInfo);
		} while (condFlag);
		$scope.flag = false;
		data.nations = $('#nations').attr('vId'); //民族ID
		data.nationName = $('#nations').attr('vName'); //民族
		//提交所需字段
		data.centerCode = $('#stCenter').attr('vId'); //受理中心ID
		data.centerAddress = $('#stCenter').attr('vName'); //受理中心名
		data.address = $scope.stAddress; //女方户籍详细地址
		data.liveAddress = $scope.liveStAddress; //女方居住详细地址
		data.maddress = $scope.mStAddress; //配偶户籍详细地址
		data.mliveAddress = $scope.mLiveStAddress; //配偶居住详细地址
		data.mobile = $('#stMomile').val(); //女方手机号
		data.sex = "女"; //$('#stSex').val(); //女方性别
		data.date = $scope.dtBirth;
		data.maleName = $scope.stMaleName; //配偶姓名
		data.maleNations = $('#maleNations').attr('vId'); //配偶民族ID
		data.maleIdCard = $scope.stMaleIdCard; //配偶身份证号
		data.maleMobile = $scope.stMaleMomile; //配偶联系电话
		data.iswy = ($scope.iswy == "是") ? "1" : "0"; //是否晚育
		data.isdszv = ($scope.isdszv == "是") ? "1" : "0"; //女方是否独生子女
		data.mIsdszv = ($scope.mIsdszv == "是") ? "1" : "0"; //配偶是否独生子女
		data.boyn = $scope.boyn; //生育男孩数
		data.girln = $scope.girln; //生育女孩数
		//新增提交字段(2019-12-06)
		var now = new Date();
		data.app_applydate = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();
		data.app_sydate = $scope.app_sydate; //生育日期
		data.app_boys = $scope.app_boys; //生育多胞胎男孩数
		data.app_girls = $scope.app_girls; //生育多胞胎女孩数
		data.app_ifmulti = $scope.app_ifmulti; //是否多胞胎
		data.app_abortion = $scope.app_abortion; //是否流产
		data.app_type = $scope.app_type || ""; //分娩方式
		data.app_borg = $('#app_borg').val() || ""; //分娩男女
		data.nativepropc = $scope.nativepropc; //申请人户口性质
		data.culturec = $("#stEducation a .in").text(); //申请人文化程度
		data.marristatusc = $('#marristatusc option:selected').text(); //申请人婚姻状况
		data.marristatuscId = $scope.marristatusc; //申请人婚姻状况ID
		data.spousemarristatusc = $('#spousemarristatusc option:selected').text(); //配偶婚姻状况
		data.spousemarristatuscId = $scope.spousemarristatusc; //配偶婚姻状况ID
		data.spousecompany = $scope.stMaleCompany; //配偶工作单位
		data.spouseculturec = $("#stMaleEducation a .in").text(); //配偶文化程度
		data.marritestifysn = $scope.stMarryId; //婚姻证明号
		data.marriregdate = $scope.dtMarry; //婚姻变动日期
		if(data.app_borg == "男") {
			data.app_borgid = "1";
		} else if(data.app_borg == "女") {
			data.app_borgid = "2";
		} else {
			data.app_borgid = "";
			data.app_borg = "";
		}
		//女方居住地址 省  市   区县   街道  ID
		data.jzAddress = {
			liveProvince: $('#liveProvince').attr('vId'),
			liveCity: $('#liveCity').attr('vId'),
			liveCounty: $('#liveCounty').attr('vId'),
			liveStreet: $('#liveStreet').attr('vId'),
			liveNeighborhood: $('#liveNeighborhood').attr('vId')
		}
		//女方户籍地址 省  市   区县   街道  ID
		data.hjAddress = {
			province: $('#province').attr('vId'),
			city: $('#city').attr('vId'),
			county: $('#county').attr('vId'),
			street: $('#street').attr('vId'),
			neighborhood: $('#neighborhood').attr('vId')
		}
		//女方居住地址 省  市   区县   街道  名
		data.jzAddressName = {
			liveProvince: $('#liveProvince').attr('vName'),
			liveCity: $('#liveCity').attr('vName'),
			liveCounty: $('#liveCounty').attr('vName'),
			liveStreet: $('#liveStreet').attr('vName'),
			liveNeighborhood: $('#liveNeighborhood').attr('vName')
		}
		//女方户籍地址 省  市   区县   街道  名
		data.hjAddressName = {
			province: $('#province').attr('vName'),
			city: $('#city').attr('vName'),
			county: $('#county').attr('vName'),
			street: $('#street').attr('vName'),
			neighborhood: $('#neighborhood').attr('vName')
		}
		//配偶居住地址 省  市   区县   街道  ID
		data.mjzAddress = {
			liveProvince: $('#mLiveProvince').attr('vId'),
			liveCity: $('#mLiveCity').attr('vId'),
			liveCounty: $('#mLiveCounty').attr('vId'),
			liveStreet: $('#mLiveStreet').attr('vId'),
			liveNeighborhood: $('#mLiveNeighborhood').attr('vId')
		}
		//配偶户籍地址 省  市   区县   街道  ID
		data.mhjAddress = {
			province: $('#maleProvince').attr('vId'),
			city: $('#maleCity').attr('vId'),
			county: $('#maleCounty').attr('vId'),
			street: $('#maleStreet').attr('vId'),
			neighborhood: $('#maleNeighborhood').attr('vId')
		}
		//配偶居住地址 省  市   区县   街道  名
		data.mjzAddressName = {
			liveProvince: $('#mLiveProvince').attr('vName'),
			liveCity: $('#mLiveCity').attr('vName'),
			liveCounty: $('#mLiveCounty').attr('vName'),
			liveStreet: $('#mLiveStreet').attr('vName'),
			liveNeighborhood: $('#mLiveNeighborhood').attr('vName')
		}
		//配偶户籍地址 省  市   区县   街道  名
		data.mhjAddressName = {
			province: $('#maleProvince').attr('vName'),
			city: $('#maleCity').attr('vName'),
			county: $('#maleCounty').attr('vName'),
			street: $('#maleStreet').attr('vName'),
			neighborhood: $('#maleNeighborhood').attr('vName')
		}
		//判断女方是否有结婚证明
		$scope.nextload6 = function() {
			appFactory.pro_fetch(data.idCardNum, data.idCardName, '生育保险', data.VALIDSTARTDAYTRAT, data.VALIDENDDAY, '310105127000101', 'YJS00020008', 'YJS0002', '结婚证', '1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr6 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 5) {
						console.log(data.isUpload);
						data.isUpload.push({
							index: 7,
							stuffName: "8、女方结婚证明",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$location.path("/materialSybxList");
				$scope.$apply();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload6();　　　　
				}
			}, function(dataJson) {
				data.imgId6 = dataJson.rtnData.imgid;
			});
		}
		//判断男方是否有结婚证明
		$scope.nextload5 = function() {
			appFactory.pro_fetch($('#stMaleIdCard').val(), data.maleName, '生育保险', data.VALIDSTARTDAYTRAT, data.VALIDENDDAY, '310105127000101', 'YJS00020007', 'YJS0002', '结婚证', '1', function(dataJson) {
				try {
					if(!dataJson) {
						$scope.SisAlert = true;
						$scope.Smsg = "没有该证照!";
					}
					data.imgStr5 = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					if(data.isUpload.length <= 4) {
						console.log(data.isUpload);
						data.isUpload.push({
							index: 6,
							stuffName: "7、男方结婚证明",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$scope.nextload6();
				$scope.$apply();
			}, function(status) {
				if(status == 'timeout') {　　　　　
					$scope.nextload5();　　　　
				}
			}, function(dataJson) {
				data.imgId5 = dataJson.rtnData.imgid;
			});
		}
		$scope.nextload4 = function() {
			//判断是否有男方户籍证明
			appFactory.pro_fetch($('#stMaleIdCard').val(), data.maleName, '生育保险', data.VALIDSTARTDAYTRAT, data.VALIDENDDAY, '310105105000100', 'YJS00020004', 'YJS0002', '男方户籍证明', '1', function(dataJson) {
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
				$scope.nextload5();
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
			appFactory.pro_fetch(data.idCardNum, data.idCardName, '生育保险', data.VALIDSTARTDAYTRAT, data.VALIDENDDAY, '310105105000100', 'YJS00020002', 'YJS0002', '女方户籍证明', '1', function(dataJson) {
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
			appFactory.pro_fetch($('#stMaleIdCard').val(), data.maleName, '生育保险', data.VALIDSTARTDAYTRAT, data.VALIDENDDAY, '310105109000100', 'YJS00020003', 'YJS0002', '男方居民身份证', '1', function(dataJson) {
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
			$scope.isLoading = false;
			appFactory.pro_fetch(data.idCardNum, data.idCardName, '生育保险', data.VALIDSTARTDAYTRAT, data.VALIDENDDAY, '310105109000100', 'YJS00020001', 'YJS0002', '女方居民身份证', '1', function(dataJson) {
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
	$scope.nextText = "提交";
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
		}, {
			'index': 6,
			'stuffName': "7、男方结婚证明",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020007",
			"needflag": "1"
		}, {
			'index': 7,
			'stuffName': "8、女方结婚证明",
			'upload': true,
			'upload2': false,
			'upload3': false,
			"affairscode": "YJS0002",
			"archivescode": "YJS00020008",
			"needflag": "1"
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
	$scope.toUploadMaterial = function(index, name, affairscode, archivescode, needflag) {
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
});