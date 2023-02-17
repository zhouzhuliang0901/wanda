app.controller("inputPhone", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	removeAnimate($('.stable'))
	addAnimate($('.stable'))
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.nextText = "查询";
	$scope.isLoding = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.count = (appData.sumbitInfo == undefined) ? "" : appData.sumbitInfo.stMobile;
	$scope.keybordArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'X', 0, '删除'];
	$scope.keyboardInput = function(e) { //键盘输入
		var keycode = window.event ? e.keyCode : e.which;
		if(keycode >= 48 && keycode <= 57) {} else if(keycode == 8) {} else {
			console.log(e);
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		}
	}
	$scope.inputNumber = function(item) { //软键盘输入
		if($scope.count == "ss") {
			$scope.count = "";
		} else if(item === '删除') {
			$scope.count = $scope.count.substring(0, $scope.count.length - 1);
		} else {
			$scope.count += item;
			console.log($scope.count);
		}
	}
	$scope.nextStep = function() {
		//var code = $.config.get("password");
		var code = '8888';
		if(code === $scope.count) {
			$state.go("loginType");
		} else {
			$scope.isAlert = true;
			$scope.msg = "密码不正确，请重试";
		}

	}
})
app.controller("loginType", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.choiceLogin = function(type) {
		if(type == "makeBook") {
			$state.go("medicalBookDetails");
		} else if(type == "update") {
			$state.go("update");
		}else if(type == "cazsbd") {
			$state.go("cazsbd");
		}
	}
});
app.controller("update", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("loginType");
	}
	$scope.nextStep = function() {
		$scope.sum = Number($scope.stPaperNum) + Number($scope.stBookNum);
		console.log($scope.stPaperNum);
		console.log($scope.stBookNum);
		if($('#stPaperNum').val() >= 1) {
			saveDeviceStatus("A4Printer", 0, "正常", 0, Number($scope.stPaperNum), Number($scope.stPaperNum), 0);
		}
		if($('#stBookNum').val() >= 1) {
			saveDeviceStatus("MedicalInsuranc", 0, "正常", 0, Number($scope.stBookNum), Number($scope.stBookNum), 0);
		}
		$scope.isAlert = true;
		$scope.msg = "增加成功";
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});

app.controller("medicalBookDetails", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	removeAnimate($('.stable'))
	addAnimate($('.stable'))
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.show = false;
	$scope.isLoding = true;
	$scope.isShowPrint = false;
	$scope.nextText = "测试打卡"
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.prevStep = function() {
		$state.go("loginType");
	}
	//获取医保记录册打印机
	try {
		var DSPrinterLibOCX = document.getElementById('DSPrinterLibOCX');

		function GetUSB() //枚举USB设置并打开设备
		{
			var str = DSPrinterLibOCX.DSEnumPrinter(); //枚举
			var pos = str.indexOf("@USB");
			str = str.substr(pos + 1, 6);
			var value = str;
			DSPrinterLibOCX.DSOpenPrinter(value); //打开
		}
	} catch(e) {}
	$(".form_datetime").datetimepicker({
		format: "yyyy-mm-dd", //显示日期格式
		autoclose: true,
		todayBtn: true,
		minView: "month", //只选择到天自动关闭
		language: 'zh-CN',
		pickerPosition: 'bottom-left',
	});
	$timeout(function() {
		function getNowFormatDate() {
			var date = new Date();
			var seperator1 = "-";
			var seperator2 = ":";
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if(month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if(strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
			return currentdate;
		}
		var date = getNowFormatDate();
		$("#stStartDate").attr("value", date);
		$("#stEndDate").attr("value", date);
	}, 300);
	$scope.query = function() {
		$scope.startDate = $('#stStartDate').val();
		$scope.endDate = $('#stEndDate').val();
		$scope.isLoding = false;
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/medicalInsurance/getBookingInfo.do",
			/*url写异域的请求地址*/
			dataType: "json",
			/*加上datatype*/
			jsonp: "jsonpCallback",
			/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
			data: {
				startTime: $scope.startDate,
				endTime: $scope.endDate,
				machineId: jQuery.getConfigMsg.uniqueId || "",
			},
			success: function(dataJson) {
				if(dataJson) {
					$scope.show = true;
					$scope.isLoding = true;
					$scope.medicalDetail = dataJson;
					$scope.count = dataJson.length;
				}
				console.log(dataJson);
			},
			error: function(err) {
				console.log(err);
			}
		});
	}
	$scope.query();
	$scope.print = function(jlch, kh, xm, xb, sfzh) {
		xb = (xb == '1'?'男':'女');
		//清除医保记录册缓存
		try {
			if(DSPrinterLibOCX) {
				GetUSB();
				DSPrinterLibOCX.DSclearcacheA();
			}
		} catch(e) {}
		console.log("医保记录测补打信息" + jlch + "-----" + kh + "-------" + xm + "-------" + xb + "-------------" + sfzh)
		$.log.debug("医保记录测补打信息" + jlch + "-----" + kh + "-------" + xm + "-------" + xb + "-------------" + sfzh);
		$scope.confirm = function() {
			var sfzhTime=sfzh.substring(6, 10) + "年" + sfzh.substring(10, 12) + "月";
		$.device.medicalPrint(jlch, kh, xb, xm, sfzhTime);
		};
		$scope.confirm();
	}
	$scope.testPrint = function() {
		//清除医保记录册缓存
		try {
			if(DSPrinterLibOCX) {
				GetUSB();
				DSPrinterLibOCX.DSclearcacheA();
			}
		} catch(e) {}
//		$.device.serialPortClose();
//		var bookMedicalPort = "COM" + (window.external.GetConfig('bookMedicalPort') || "4");
//		$.device.serialPortOpen(bookMedicalPort, 9600, 8, function() {}) //开启串口
		$scope.confirm = function() {
			$.device.medicalPrint("", "", "", "", "");
//			var lodop = $.device.printGetLodop('medical')
//			lodop.SET_PRINTER_INDEX('DASCOM DS-7860');
//			lodop.ADD_PRINT_TEXT(230, 140, 670, 50, "");
//			lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
//			lodop.PRINT(); //打印
//			saveDeviceStatus("MedicalInsuranc", 0, "正常", 0, 0, 0, 1);
//			setTimeout(function() {
//				$.device.serialPortWriteString("S0001#") //发送指令
//			}, 0)
			//  alert('打印完毕，关闭串口')
			//  $.device.serialPortClose();

			$scope.isShowPrint = "show";
			setTimeout(function() {
				$scope.isShowPrint = false;
				$state.go("medicalBookDetials");
			}, 5000);
			try {
				if(DSPrinterLibOCX) {
					DSPrinterLibOCX.DSClosePrinter();
				}
			} catch(e) {}
		};
		$scope.confirm();
	}
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
})

app.controller("cazsbd", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("loginType");
	}
	//获取标识
	$scope.GetDataMac = function() {
		$.post("http://127.0.0.1:18495/cert/getMac",{},function(data, textStatus, jqXHR){
			console.log(JSON.parse(jqXHR.responseText));
			var result=JSON.parse(jqXHR.responseText)
			if(result){
				if(result.data){
					console.log(result);
					$scope.stzsMac = result.data;
				}else{
					alert("获取证书getMac失败");
				}
			}else{
				alert("获取证书getMac失败");
			}
		});    
	}
	$scope.macUniqueId=function(){
		$.config.load('uniqueId', function(f) {
			$scope.stsbMac = f;
			console.log(f);
		});
	}
	$scope.GetDataMac();
	$scope.macUniqueId();
	$scope.nextStep=function(){
		var dataS = {
			'mac': $scope.stsbMac,
			'seqId':$scope.stzsMac
		};
		$.ajax({
			type: "post",
			url: $.getConfigMsg.preUrlSelf+"/selfapi/bindCertificate.do",
			dataType: "json",
			//jsonp: "jsonpCallback",
			timeout: 5000,
			data: dataS,
			success: function(result) {
				console.log(result);
				if(result){
					$scope.isAlert = true;
					$scope.msg = result.msg
				}
			},
			error: function(err) {
				console.log(err);
				$scope.isAlert = true;
				$scope.msg = "接口异常";
			},
		});
	};
	
	$scope.prevStep = function() {
		$state.go("loginType");
	}
});