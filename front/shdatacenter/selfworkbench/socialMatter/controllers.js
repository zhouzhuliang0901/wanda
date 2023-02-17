var t;
document.addEventListener('touchstart', T);
document.addEventListener('click', T);

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

function T() {
	clearInterval(t);
	time();
}
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data) {
	$scope.currentMatters = [];
	$scope.matterVal = '';
	clearInterval(t);
	time();

	$scope.itemName = [{
			itemName: "城镇职工基本养老保险-参加个人城镇基本养老保险缴费情况"
		},
		{
			itemName: "城镇职工基本养老保险-转往外省市缴费凭证"
		},
		
	]

	$scope.toMaterials = function(itemName) {
		data.itemName = itemName;
			$location.path("/selectTwo");
	};
	$rootScope.goHome = function() {
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, data, $timeout) {
	var name = data.itemName;
	$scope.itemName = name;
	clearInterval(t);
	time();
	$scope.guideInfo = {
		clRange: "市档案局",
		clNameCode: "申请人应为在本市民政部门办理婚姻登记（结婚、离婚）手续的婚姻当事人本人。受婚姻当事人委托代为办理的，或其他相关利用人，应按有关规定前往保管该档案的区县档案馆办理。",
		clDealAccording: "1.中共上海市委办公厅、上海市人民政府办公厅印发《关于加强和改进新形势下本市档案工作的实施意见》的通知（沪委办发〔2014〕26号）“档案部门与相关部门要加强协作配合，将民生档案服务纳入全市社区事务受理中心服务范围，拓展档案服务内容和功能。”</br>2.《上海市档案条例》第三十三条 法人、其他组织以及个人利用档案馆未开放的档案或者其他组织保存的档案，应当经有关档案馆或者有关组织同意，并应当遵守国家保密规定，不得擅自抄录、复制档案或者泄露档案内容。</br>2.《上海市国家综合档案馆档案利用和公布办法》（沪档〔2008〕27号）第八条第二款 ……公民利用记载本人有关知识青年上山下乡、支援内地建设、婚姻登记、计划生育（独生子女）、学历、学籍、职称、获奖荣誉、离退休的证明性未开放档案，可以凭本人身份证到档案馆办理申请手续。</br>3.《关于在全市社区事务受理服务中心全面开展民生档案利用便民服务的通知》（沪档〔2012〕94号）",
		clDealOrgan: "【本事项可“全市通办”】符合申请条件的人员，可通过自助服务设备等渠道提交申请。 <br/>1.提出申请：申请人填写申请表，提交申请材料。<br/>2.审核出证：相关档案馆审核，对符合利用条件的制作电子档案文件。<br/>3.自助打印：约30分钟后，申请人可在自助终端上打印带有档案局电子印章的档案证明。",
		clApprovalMater: "<table><tbody><tr><th>材料名称</th><th>材料类型</th><th>纸质/电子报件</th><th>材料份数</th><th>材料必要性</th></tr><tr><td>本人有效居民身份证原件</td><td>原件</td><td>纸质或电子</td><td>1</td><td>必要</td></tr></table></tbody>"
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
	$scope.next = function() {
		$location.path("/select");
	}
});
app.controller("selectTwoController", function($scope, $route, $http, $location, data, $timeout) {
	$.device.Camera_Hide();
	$scope.itemName = data.itemName;
	clearInterval(t);
	time();
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	// 社保卡
	$scope.socialCard = function() {
		$location.path('/ssCard');
	}
});



app.controller("ssCardController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.isRead = true;
	clearInterval(t);
	time();
//		$scope.getIdcard = function(info,images){
//			
//			$scope.faceImage = images;
//			$scope.isRead = false;//faceImg
//			$scope.$apply();
//	 		data.idCardName = info.Name;
//			data.idCardNum = info.Number;
//		}
//		$scope.getResult = function(img){
//			$scope.img = img;
//		if(data.itemName =="城镇职工基本养老保险-参加个人城镇基本养老保险缴费情况"){
//			$location.path("/socialShow");
//		}else if(data.itemName =="城镇职工基本养老保险-转往外省市缴费凭证"){
//			$location.path("/socialComfirm");
//		}
//		} 
	data.idCardName = "陈云翔";
	data.idCardNum = "310105197805313613";
	if(data.itemName == "城镇职工基本养老保险-参加个人城镇基本养老保险缴费情况") {
		$location.path("/socialShow");
	} else if(data.itemName == "城镇职工基本养老保险-转往外省市缴费凭证") {
		$location.path("/socialComfirm");
	} 
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.isRead = true;
	clearInterval(t);
	time();
	//	$scope.getIdcard = function(info,images){
	//		
	//		$scope.faceImage = images;
	//		$scope.isRead = false;//faceImg
	//		$scope.$apply();
	// 		data.idCardName = info.Name;
	//		data.idCardNum = info.Number;
	//	}
	//	$scope.getResult = function(img){
	//		$scope.img = img;
	//	if(data.itemName =="城镇职工基本养老保险-参加个人城镇基本养老保险缴费情况"){
	//		$location.path("/socialShow");
	//	}else if(data.itemName =="城镇职工基本养老保险-转往外省市缴费凭证"){
	//		$location.path("/socialComfirm");
	//	}
	//	} 
	data.idCardName = "陈云翔";
	data.idCardNum = "310105197805313613";
	if(data.itemName == "城镇职工基本养老保险-参加个人城镇基本养老保险缴费情况") {
		$location.path("/socialShow");
	} else if(data.itemName == "城镇职工基本养老保险-转往外省市缴费凭证") {
		$location.path("/socialComfirm");
	}
});

app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	clearInterval(t);
	time();
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({
			url: " http://218.202.254.222/aci/window/getInfoByCodeTest.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success === false) {
					layer.msg(dataJsonp.result.msg);
					$timeout(function() {
						$location.path("/selectTwo");
					}, 100);
				}
				data.idCardName = dataJsonp.result.data.realname;
				data.idCardNum = dataJsonp.result.data.idcard;
				data.mobile = dataJsonp.result.data.mobile;
				$timeout(function() {
					if(data.itemName == "城镇职工基本养老保险-参加个人城镇基本养老保险缴费情况") {
						$location.path("/socialShow");
					} else if(data.itemName == "城镇职工基本养老保险-转往外省市缴费凭证") {
						$location.path("/socialComfirm");
					} 
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
app.controller("socialShowController", function($scope, $route, $http, $location, data, $timeout) {
	clearInterval(t);
	time();

	$scope.stName = data.idCardName;
	$scope.stCard = data.idCardNum;
	$scope.justOk = false;
	$scope.printOk = false;
	$scope.nextOk = false;
	$scope.isLoding = false;
	$timeout(function() {
		$scope.isLoding = true;
		$scope.justOk = true;
		$scope.printOk = true;
		$scope.nextOk = true;
	}, 2000);

	var bbb = "<table>";
	var ddd = "</table>";
	var aaa = "<style>table{ font-size:13px;border-collapse:collapse;height: auto;max-width: 80%;width: 80%;table-layout: fixed;word-break: break-all;}table, th, td{border: 1px solid black;text-align: center; overflow: hidden;}td,th{line-height:20px;}</style>";
	var eee = "<tr><td>顺序号</td><td>年月</td> <td>缴费情况</td><td>补缴起止年月</td></tr>";
	var ccc = "<tr><td>1</td><td>2017.08</td><td>未缴费</td><td>2017.09.15</td></tr>";
	//这个ccc 是lodop打印table表格循环样式
	var fff = "<tr><td>姓名</td><td>" + data.idCardName + "</td><td>公民身份证号码</td><td>" + data.idCardNum + "</td></tr>";
	var ggg = "<tr><td>近24个月所在缴费单位信息</td></tr>";
	var hhh = "<tr><td>缴费单位名称</td><td>缴费起止时间</td><td>缴费单位名称</td><td>缴费起止时间</td></tr>";
	var iii = "<tr><td>上海万达信息股份有限公司</td><td>2018，6-2019.1</td><td>上海思科科技有限公司</td><td>2018，6-2019.1</td></tr>";
	var jjj = "<tr><td>截止2019年8月累计缴费月数</td><td>" + $scope.times + "</td></tr>";
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//$scope.isAlert = true;
	//$scope.msg = "无数据，如有疑问请和社保中心联系，谢谢！";
	var b = '';
	var a = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0];
	var c = '';
	for(var i = 1; i < a.length + 1; i++) {
		//	<tr><td>1</td><td>2017.08</td><td>未缴费</td><td>2017.09.15</td></tr>
		b += '<tr><td>' + i + '</td><td>2017.08</td><td>未缴费</td><td>2017.09.15</td></tr>';
	}
	$("#tableOne").html(b);
	for(var i = 1; i < a.length + 1; i++) {
		c += '<tr><td>中化资产管理（上海）有限公司</td><td>2017.08-至今</td><td>中化资产管理</td><td>2017.08-至今</td></tr>';
	}
	$("#tableTwo").html(c);
	$scope.times = 150;
	$scope.print = function() {
		var date = new Date();
		var month = date.getMonth() + 1;
		//aaa+bbb为css+table头         ddd为</table>
		//需要循环 ccc ：为缴费信息4个排列       iii:公司 缴费起止时间   jjj需要计算次数 截止日期也要改
		//var lodop = $.device.printGetLodop();
		//		lodop.ADD_PRINT_TEXT(100, 165, 550, 40, "参保人员城镇基本养老保险缴费情况");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		//		lodop.SET_PRINT_STYLEA(0, "Bold", 1);
		//		lodop.ADD_PRINT_TABLE(160, 75, "100%", "100%", aaa + bbb + fff + ddd);
		//		lodop.ADD_PRINT_TABLE(180, 75, "100%", "100%", aaa + bbb + eee + ccc + ddd);
		//		lodop.ADD_PRINT_TABLE(758, 75, "100%", "100%", aaa + bbb + ggg + ddd);
		//		lodop.ADD_PRINT_TABLE(778, 75, "100%", "100%", aaa + bbb + hhh + iii + ddd);
		//		lodop.ADD_PRINT_TABLE(847, 75, "100%", "100%", aaa + bbb + jjj + ddd);
		//		lodop.ADD_PRINT_TEXT(880, 70, 700, 30, "备注：1、本缴费情况的信息，以申请打印时点上的参保缴费情况为依据，仅供参考。");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
		//		lodop.ADD_PRINT_TEXT(900, 80, 700, 30, "2、根据当前本市社会保险费缴费记账规则，最近1个月的缴费或处于缴费扣款途中，尚未记账。");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
		//		lodop.ADD_PRINT_TEXT(920, 80, 800, 30, "3、本缴费情况加盖电子印章，与社保经办机构印章具有同等效力，不再另行盖章。");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
		//		lodop.ADD_PRINT_TEXT(955, 480, 250, 30, "上海市社会保险事业管理中心");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
		//		lodop.ADD_PRINT_TEXT(980, 498, 208, 30, "打印日期：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 11);
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
	$scope.isScroll = function() {
		new iScroll("wrapper2", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

});

app.controller("socialComfirmController", function($scope, $route, $http, $location, data, $timeout) {
	clearInterval(t);
	time();
	$scope.stName = data.idCardName;
	$scope.stCard = data.idCardNum;
	$scope.xm = "康敏";
	$scope.xb = "男性";
	$scope.grbh = "200210111348980";
	$scope.sfz = "310109198202260030";
	$scope.hjddz = "上海市虹口区新市南路918弄5号301室";
	//		$scope.cbqssj = ;//开始时间  页面为起始-终止
	//		$scope.cbzzsj = ;//终止时间
	$scope.startToEnd = "2019-06 2019-08";
	$scope.jfys = "101";
	$scope.grzhcce = "39201.90";
	$scope.xzqhdm = "310101";
	$scope.jbjgmc = "上海市社会保险事业管理中心黄埔分中心";
	$scope.dh = "021-63156586";
	$scope.yzbm = "200011";
	$scope.dz = "陆家浜路265号";

	$scope.justOk = false;
	$scope.printOk = false;
	$scope.nextOk = false;
	$scope.isLoding = false;

	$timeout(function() {
		$scope.isLoding = true;
		$scope.justOk = true;
		$scope.printOk = true;
		$scope.nextOk = true;
	}, 1000);

	$scope.concel = "false";

	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	//	$scope.isAlert = true;
	//	$scope.msg = "无数据，如有疑问请和社保中心联系，谢谢！";
	$scope.print = function() {
		//调用接口
	}

});