app.controller("formMain", function($scope, $state, appData, appFactory) {
	$scope.operation = "自助填表";
	$scope.takeFormList = [
		{
			formName: "居住登记信息表",
			formImg: "../libs/common/images/autoForm/jzdj.png"
		},
		{
			formName: "上海市居住证申请表",
			formImg: "../libs/common/images/autoForm/jzsq.png"
		},
		{
			formName: "上海市居住证签注申请表",
			formImg: "../libs/common/images/autoForm/jzqzsq.png"
		}
	];
	$scope.currentPage = 1;
	$scope.totalPages = Math.ceil($scope.takeFormList.length / 3);
	$scope.currentList = $scope.takeFormList.slice(($scope.currentPage - 1) * 3, $scope.currentPage * 3);
	$scope.prev = function() {
		if($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.currentList = $scope.takeFormList.slice(($scope.currentPage - 1) * 3, $scope.currentPage * 3);
		}
	}
	$scope.next = function() {
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.currentList = $scope.takeFormList.slice(($scope.currentPage - 1) * 3, $scope.currentPage * 3);
		}
	}
	$scope.isPrint = "show";
	$scope.choiceForm = function(item) {
		appData.formName = item.formName;
		appData.imgUrl = item.formImg;
		$state.go("explain");
	};
});
app.controller("formExplain", function($scope, $state, appData, appFactory) {
	$scope.operation = "";
	$scope.largeImg = appData.imgUrl;
	$scope.formName = appData.formName;
	if($scope.formName == "居住登记信息表" || $scope.formName == "上海市居住证签注申请表") {
		$scope.text = "";
	} else {
		$scope.text = "2. 16岁以下，请前往窗口办理。";
	}
	$scope.nextStep = function() {
		$state.go("loginType");
	};
});
app.controller('formLoginType', function($state, $scope, appData) {
	$scope.operation = "请选择登录方式";
	$scope.formName = appData.formName;
	$scope.choiceLogin = function(type) {
		appData.idcardName = "zoutanqi";
		appData.idCardNum = "430426199508138892";//"310105197805313613";	
		appData.mobile = "18692067056";
		appData.nation = "汉族";
		if($scope.formName == "居住登记信息表") {
			$state.go("jzdj");
		} else if($scope.formName == "上海市居住证申请表") {
			$state.go("jzsq");
		} else if($scope.formName == "上海市居住证签注申请表") {
			$state.go("jzqzsq");
		}
	//	appData.loginType = type;
	//	$state.go("login");
	}
});
app.controller('formLogin', function($scope, $http, $state, appData, appFactory) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.formName = appData.formName;
	$scope.loginBtn = false;
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "cloud":
			$scope.operation = "市民云";
			break;
	}
	$scope.formChoice = function() {
		if($scope.formName == "居住登记信息表") {
			$state.go("jzdj");
			$scope.$apply();
		} else if($scope.formName == "上海市居住证申请表") {
			$state.go("jzsq");
			$scope.$apply();
		} else if($scope.formName == "上海市居住证签注申请表") {
			$state.go("jzqzsq");
			$scope.$apply();
		}
	}
	$scope.idcardLogin = function(info,images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.idCardNum = info.Number;
			appData.idcardName = info.Name;
			appData.nation = info.People;
			appData.address = info.Address;
			if(appData.nation.lastIndexOf('族') < 0) {
				appData.nation = appData.nation + '族';
			}
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img){
		$scope.img = img;
		$scope.formChoice();
	} 
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.idcardName = idcardInfo.realname;
		appData.idCardNum = idcardInfo.idcard;
		appData.mobile = idcardInfo.mobile;
		$scope.formChoice();
	}

});
app.controller("formJzdj", function($scope, $state, appData,$http,appFactory,$timeout) {
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		$scope.stName = appData.idcardName;
		$scope.stIdCard = appData.idCardNum;
		$scope.mobile = appData.mobile;
		$scope.stSex = ((parseInt(appData.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		City();
		
		PublicchoiceById('liveType');
		PublicchoiceById('liveReason');
		PublicchoiceById('education');
		if(appData.nation){
			$timeout(function() {
				setSelectCheckedByText("nations", appData.nation);
			}, 1000);
		}else{
			//身份证信息
			appFactory.idCardInfo(appData.idCardNum,function(dataJsonp){
				if(dataJsonp.SETHNIC.lastIndexOf('族') < 0) {
					dataJsonp.SETHNIC = dataJsonp.SETHNIC + '族';
				}
				setSelectCheckedByText("nations", dataJsonp.SETHNIC);
			});
		}
		if(appData.address){
			$scope.stAddress = appData.address;
		}else{
			//户口本信息
			appFactory.idCardInfo(appData.idCardNum,function(dataJsonp){
				$scope.stAddress = dataJsonp.SADDR;
			});
		}
	});
	

	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#nations').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择民族！";
				return;
			}
			if(!isPhoneAvailable($scope.mobile)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if($('#stAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择户籍地址！";
				return;
			}
			if($('#shCounty').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住区/县！";
				return;
			}
			if($('#shStreet').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住街/道！";
				return;
			}
			if($('#stShRoad').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住路名！";
				return;
			}
			if($("#liveType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住证明类别！";
				return;
			}
			if($("#liveReason .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住事由！";
				return;
			}
			if($("#education .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择文化程度！";
				return;
			}
		} while (condFlag);
		//居住登记表信息字段
		appData.jzdj = {
			stName: $scope.stName,
			stIdCard: $scope.stIdCard,
			mobile: $scope.mobile,
			stSex: $scope.stSex,
			nations: $('#nations').attr('vName'),
			address: $scope.stAddress,
			shCounty: $('#stShCounty').val(),
			shStreet: $('#stShStreet').val(),
			stShRoad: $('#stShRoad').val(),
			stShLane: $scope.stShLane,
			stShNo: $scope.stShNo,
			stShRoom: $scope.stShRoom,
			liveType: $("#liveType .in").text(),
			liveReason: $("#liveReason .in").text(),
			education: $("#education .in").text(),
			other: $scope.other
		}
		console.info(appData.jzdj);
		$scope.flag = false;
		$state.go("print");
	}
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:true,
			bounce:true,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();
});
app.controller("formJzsq", function($scope, $state, appData,appFactory,$timeout) {
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		$scope.stName = appData.idcardName;
		$scope.stIdCard = appData.idCardNum;
		$scope.stPhone = appData.mobile;
		$scope.stSex = ((parseInt(appData.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.stTel = $scope.stPhone;
		City();
		PublicchoiceById('liveType');
		PublicchoiceById('liveReason');
		PublicchoiceById('takePhoto');
		PublicchoiceById('education');
		PublicchoiceById('ifStudy');
		PublicchoiceById('ifWorking');
		PublicchoiceById('industyType');
		PublicchoiceById('unoccupied');
		PublicchoiceById('liveSource');
		if(appData.nation){
			$timeout(function() {
				setSelectCheckedByText("nations", appData.nation);
			}, 1000);
		}else{
			//身份证信息
			appFactory.idCardInfo(appData.idCardNum,function(dataJsonp){
				if(dataJsonp.SETHNIC.lastIndexOf('族') < 0) {
					dataJsonp.SETHNIC = dataJsonp.SETHNIC + '族';
				}
				setSelectCheckedByText("nations", dataJsonp.SETHNIC);
			});
		}
		if(appData.address){
			$scope.stAddress = appData.address;
		}else{
			//户口本信息
			appFactory.idCardInfo(appData.idCardNum,function(dataJsonp){
				$scope.stAddress = dataJsonp.SADDR;
			});
		}
		
	});
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#nations').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择民族！";
				return;
			}
			if(!isPhoneAvailable($scope.stPhone)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if(!isPhoneAvailable($scope.stPhone)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的固定电话！";
				return;
			}
			if($('#stAddress').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入户籍地址！";
				return;
			}
			if($("#takePhoto .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择照片是否采集！";
				return;
			}
			if($('#shCounty').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住区/县！";
				return;
			}
			if($('#shStreet').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住街/道！";
				return;
			}
			if($('#stShRoad').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住路名！";
				return;
			}
			if($("#liveType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住证明类别！";
				return;
			}
			if($("#liveReason .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住事由！";
				return;
			}
			if($("#education .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择文化程度！";
				return;
			}
			if($("#ifStudy .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择是否在读！";
				return;
			}
		} while (condFlag);
		//居住证申请信息字段
		appData.jzsq = {
			stName: $scope.stName,
			stIdCard: $scope.stIdCard,
			stPhone: $scope.stPhone,
			stTel: $scope.stTel,
			stSex: $scope.stSex,
			stMail: $scope.stMail,
			nations: $('#nations').attr('vName'),
			address: $scope.stAddress,
			shCounty: $('#stShCounty').val(),
			shStreet: $('#stShStreet').val(),
			stShRoad: $('#stShRoad').val(),
			stShLane: $scope.stShLane,
			stShNo: $scope.stShNo,
			stShRoom: $scope.stShRoom,
			takePhoto: $("#takePhoto .in").text(),
			liveType: $("#liveType .in").text(),
			liveReason: $("#liveReason .in").text(),
			education: $("#education .in").text(),
			ifStudy: $("#ifStudy .in").text(),
			other: $scope.other,
			stRelationship1: $scope.stRelationship1,
			stName1: $scope.stName1,
			stIdCard1: $scope.stIdCard1,
			stRelationship2: $scope.stRelationship2,
			stName2: $scope.stName2,
			stIdCard2: $scope.stIdCard2,
			stRelationship3: $scope.stRelationship3,
			stName3: $scope.stName3,
			stIdCard3: $scope.stIdCard3,
			stRelationship4: $scope.stRelationship4,
			stName4: $scope.stName4,
			stIdCard4: $scope.stIdCard4,
			stSchool: $scope.stSchool,
			stSchoolAddr: $scope.stSchoolAddr,
			dtStart: $scope.dtStart,
			dtEnd: $scope.dtEnd,
			ifWorking: $("#ifWorking .in").text(),
			stJob: $scope.stJob,
			stCompany: $scope.stCompany,
			stCompanyAddr: $scope.stCompanyAddr,
			industyType: $("#industyType .in").text(),
			unoccupied: $("#unoccupied .in").text(),
			liveSource: $("#liveSource .in").text()
		}
		console.info(appData.jzsq);
		$state.go("print");
	}
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:true,
			bounce:true,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();
});
app.controller("formJzqzsq", function($scope, $state, appData,appFactory,$timeout) {
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		$scope.stName = appData.idcardName;
		$scope.stIdCard = appData.idCardNum;
		$scope.stPhone = appData.mobile;
		$scope.stSex = ((parseInt(appData.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.stTel = $scope.stPhone;
		City();
		PublicchoiceById('liveType');
		PublicchoiceById('liveReason');
		if(appData.nation){
			$timeout(function() {
				setSelectCheckedByText("nations", appData.nation);
			}, 1000);
		}else{
			//身份证信息
			appFactory.idCardInfo(appData.idCardNum,function(dataJsonp){
				if(dataJsonp.SETHNIC.lastIndexOf('族') < 0) {
					dataJsonp.SETHNIC = dataJsonp.SETHNIC + '族';
				}
				setSelectCheckedByText("nations", dataJsonp.SETHNIC);
			});
		}
	});
	$scope.isAlert = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}

	$scope.flag = true;
	$scope.nextStep = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#nations').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择民族！";
				return;
			}
			if(!isPhoneAvailable($scope.stPhone)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的手机号！";
				return;
			}
			if(!isPhoneAvailable($scope.stPhone)) {
				$scope.isAlert = true;
				$scope.msg = "请输入正确的固定电话！";
				return;
			}
			if($('#shCounty').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住区/县！";
				return;
			}
			if($('#shStreet').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住街/道！";
				return;
			}
			if($('#stShRoad').val() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请输入居住路名！";
				return;
			}
			if($("#liveType .in").text() < 1) {
				$scope.isAlert = true;
				$scope.msg = "请选择居住证明类别！";
				return;
			}
		} while (condFlag);
		//居住登记表信息字段
		appData.jzqzsq = {
			stName: $scope.stName,
			stIdCard: $scope.stIdCard,
			stTel: $scope.stTel,
			stPhone: $scope.stPhone,
			stSex: $scope.stSex,
			nations: $('#nations').attr('vName'),
			shCounty: $('#stShCounty').val(),
			shStreet: $('#stShStreet').val(),
			stShRoad: $('#stShRoad').val(),
			stShLane: $scope.stShLane,
			stShNo: $scope.stShNo,
			stShRoom: $scope.stShRoom,
			liveType: $("#liveType .in").text(),
		}
		console.info(appData.jzqzsq);
		$state.go("print");
	}
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:true,
			bounce:true,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();
});
app.controller("formPrint", function($scope, $state, appData, appFactory, $timeout) {
	$scope.largeImg = "../libs/common/images/autoForm/png";
	$scope.printQuantity = 1;
	$scope.isPrint = false;

	$scope.getForm = function() {
		$.log.debug(appData.jzdj);
	$.log.debug(appData.jzsq);
	$.log.debug(appData.jzqzsq);
	$.log.debug(appData.formName);
		var index2 = layer.load(0, {
			shade: [0.5, 'white'] //0.7透明度的黑色背景
		});
		//word表格类型
		$scope.name = appData.formName + ".doc";
		$.device.officeOpenRelative($scope.name);
		$timeout(function() {
			layer.close(index2);
			$.device.officeShow(600, 800, 360, 170);
		}, 4000);
		if(appData.formName == "居住登记信息表") {
			$timeout(function() {
				formJzdj(appData.jzdj);
			}, 2000);
		} else if(appData.formName == "上海市居住证申请表") {
			$timeout(function() {
				formJzsq(appData.jzsq);
			}, 2000);
		} else if(appData.formName == "上海市居住证签注申请表") {
			$timeout(function() {
				formJzqzsq(appData.jzqzsq);
			}, 2000);
		}
	};
	$scope.getForm();

	$scope.minus = function() {
		if($scope.printQuantity > 1) {
			--$scope.printQuantity;
		}
	}
	$scope.plus = function() {
		if($scope.printQuantity < 5) {
			++$scope.printQuantity;
		}
	}
	$scope.print = function() {
		$.device.officePrint();
		$.device.officeClose();
		$scope.isPrint = "show";
		$timeout(function() {
			$scope.isPrint = "hidden";
			$state.go("main");
		}, 3000);
	}
});