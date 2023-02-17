var app = angular.module("myApp", []);

app.controller("devicesCtrl", function($scope) {
	$scope.show = false; // 设备内容区域是否展示
	$scope.cardType = false; // 信息内容区域是否是读卡
	$scope.Info = []; // 记录卡片类型读取信息

	//切换标签关闭信息框和加载框
	$scope.tab = function() {
		$scope.show = false;
		$scope.qrCodeType = false;
		$scope.loading = false;
	}

	// 身份证阅读器
	$scope.idCardOpen = function() {
		$scope.show = false;
		$scope.loading = true;
		$scope.CardOpen = function(callback) {
			try {
				window.external.Hd_IdCard_Open();
				window.idCardCallBack = function(value) {
					callback(value);
				};
				window.HtmlUserInfo = function(value) {
					callback(value);
				};
				window.SMYHtmlUserInfo = function(value) {
					callback(value);
				};
			} catch(e) {
				alert("身份证读取失败")
			}
		}
		$scope.CardOpen(function(val) {
			$scope.loading = false;
			$scope.show = true;
			$scope.cardType = true;
			var val = JSON.parse(val);
			$scope.Info = [{
					"explain": "姓名",
					"key": "Name",
					"value": val.Name
				},
				{
					"explain": "性别",
					"key": "Sex",
					"value": val.Sex
				},
				{
					"explain": "民族",
					"key": "People",
					"value": val.People
				},
				{
					"explain": "出生日期",
					"key": "Birthday",
					"value": val.Birthday
				},
				{
					"explain": "地址",
					"key": "Address",
					"value": val.Address
				},
				{
					"explain": "身份证号",
					"key": "Number",
					"value": val.Number
				},
				{
					"explain": "证照地址",
					"key": "CardImagePath",
					"value": val.CardImagePath
				},
			];
			$scope.$apply();
		});
	}
	$scope.idCardClose = function() {
		$scope.show = false;
		$scope.cardType = false;
		$scope.loading = false;
		window.external.Hd_IdCard_Close();
	}

	// 社保卡阅读器
	$scope.ssCardOpen = function() {
		$scope.show = false;
		$scope.loading = true;
		$scope.CardOpen = function(callback) {
			try {
				window.external.Hd_SsCard_Open();
				window.SsCardCallBack = function(value) {
					callback(value);
				};
			} catch(e) {
				alert("居住证读取失败")
			}
		}
		$scope.CardOpen(function(val) {
			$scope.show = true;
			$scope.cardType = true;
			$scope.loading = false;
			var val = JSON.parse(val);
			$scope.Info = [{
					"explain": "发卡地行政区划代码",
					"key": "Area",
					"value": val.Area
				},
				{
					"explain": "身份证号",
					"key": "Ssn",
					"value": val.Ssn
				},
				{
					"explain": "卡号",
					"key": "CardNo",
					"value": val.CardNo
				},
				{
					"explain": "社保卡编号",
					"key": "IccId",
					"value": val.IccId
				},
				{
					"explain": "持卡人姓名",
					"key": "PeoPleName",
					"value": val.PeoPleName
				},
				{
					"explain": "第几代社保卡",
					"key": "Gversion",
					"value": val.Gversion
				},
				{
					"explain": "有效开始日期",
					"key": "StartDate",
					"value": val.StartDate
				},
				{
					"explain": "有效截止日期",
					"key": "EndDate",
					"value": val.EndDate
				},
				{
					"explain": "注册号",
					"key": "RegCode",
					"value": val.RegCode
				}
			];
			$scope.$apply();
		});
	}
	$scope.ssCardClose = function() {
		$scope.show = false;
		$scope.cardType = false;
		$scope.loading = false;
		window.external.Hd_SsCard_Close();
	}

	//高拍仪开关
	$scope.ScanOpen = function() {
		$scope.cardType = false;
		try {
			window.external.CmCapture_Show(900, 550, 730, 270);
		} catch(e) {
			alert("高拍仪打开失败");
		}
	}
	$scope.ScanClose = function() {
		$scope.show = false;
		try {
			window.external.CmCapture_Close();
			window.external.CmCapture_Hide();
		} catch(e) {
			alert("高拍仪关闭失败")
		}

	}
	$scope.scanPhoto = function() {
		var basePath = window.external.CmCapture_Capture_Base64();
		var path = window.external.CmCapture_Capture_Url();
		$scope.show = true;
		$scope.info = "拍照成功，图片保存在" + path; //+"  图片的base64编码是："+basePath;
		$scope.$apply();
	}

	// 医保记录册打印
	$scope.bookPrint = function() {
		$scope.SerialPort_Open = function(portName, baudRate, dataBits, callback) {
			try {
				window.external.SerialPort_Open(portName, baudRate, dataBits);
				window.serialPortCallback = function(value) {
					callback(value);
				};
			} catch(e) {
				alert("记录册打印机打开失败");
			}
		}
		var bookMedicalPort = "COM" + (window.external.GetConfig('bookMedicalPort') || "4");
		$scope.SerialPort_Open(bookMedicalPort, 9600, 8, function() {}) //开启串口
		var lodop = window.external.Printer_Lodop();
		lodop.SET_PRINTER_INDEX('DASCOM DS-7860');
		lodop.ADD_PRINT_TEXT(230, 140, 670, 50, "123456789");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(270, 140, 670, 30, "测试1号");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(305, 140, 670, 30, "男");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(340, 140, 670, 30, "987654321");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(375, 140, 670, 30, "年月");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.PRINT(); //打印
		setTimeout(function() {
			window.external.SerialPort_WriteString("S0001#") //发送指令
		}, 0)
	}
	// word打印
	$scope.wordPrint = function() {
		try {
			window.external.Office_Print();
		} catch(e) {}
	}
	// pdf打印
	$scope.pdfPrint = function(url) {
		try {
			window.external.Office_Pdf_Print(url);
		} catch(e) {
			alert("pdf打印失败")
		}
	}
	// html打印
	$scope.htmlPrint = function() {
		try {
			var lodop = window.external.Printer_Lodop();
			var style = "<style>div{width:100%;height:80%;border:1px solid #000;}</style>";
			var html = style + "<body><div>这里可设置您自己的html内容</div></body>";
			lodop.ADD_PRINT_TEXT(50, 0, "100%", 100, "设置标题");
			lodop.SET_PRINT_STYLEA(0, "Alignment", 2);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.ADD_PRINT_HTM(150, 0, "100%", "100%", html);
			lodop.PRINT();
		} catch(e) {
			alert("打印html失败")
		}
	}
	$scope.printClose = function() {
		$scope.show = false;
		$scope.loading = false;
		window.external.SerialPort_Close();
	}

	// 二维码扫描
	$scope.qrCodeOpen = function() {
		$scope.loading = true;
		$scope.qCodeOpen = function(callback) {
			try {
				window.external.Hd_QrScanner_Open();
				window.qrScannerCallBack = function(value) {
					callback(value);
				};
				window.GetScannerCode = function(value) {
					callback(value);
				};
			} catch(e) {
				alert("二维码扫描口打开失败")
			}
		}
		$scope.qCodeOpen(function(params) {
			$scope.show = true;
			$scope.loading = false;
			$scope.qrCodeType = true;
			$scope.info = "二维码扫描结果：" + params;
			$scope.$apply();
		})
	}
	$scope.qrCodeClose = function() {
		$scope.show = false;
		$scope.loading = false;
		$scope.qrCodeType = false;
		window.external.Hd_QrScanner_Close();
	}

	// 前置摄像头
	$scope.CameraOpen = function() {
//		$.device.Camera_Init(640, 480, 640, 240);
//		let camera = window.external.GetConfig('camera');
//		let index = window.external.GetConfig('resolution') || 1;
//		$.device.Camera_Link(camera, index); //初始化摄像头
//		$.device.Camera_Show();
//		oSCameraInfo.innerHTML = "摄像头打开成功";
				try {
					window.external.VideoCapture_Init(640, 480, 850, 270);
				} catch(error) {
					alert("摄像头初始化失败")
				}
				var cameraName = window.external.GetConfig('camera');
				var index = window.external.GetConfig('resolution') || 1;
				try {
					/*选择摄像头CameraName、分辨率ResolvingPower*/
					window.external.VideoCapture_Link(cameraName, index);
				} catch(error) {
					alert("选择摄像头名字或者像素失败")
				}
				try {
					window.external.VideoCapture_Show();
				} catch(error) {
					alert("摄像头没准备好");
				}
	}
	$scope.CameraClose = function() {
		$scope.show = false;
		try {
			window.external.VideoCapture_Close();
			window.external.VideoCapture_Hide();
		} catch(error) {
			alert("关闭失败")
		}
	}
	$scope.takePhoto = function() {
		var path = window.external.VideoCapture_Capture_Base64();
		$scope.show = true;
		$scope.info = "拍照成功，已获取其Base64编码";
	}

	// 活体检测
	$scope.faceOpen = function() {

		$scope.Face_Show = function(width, height, x, y, callback) {
			try {
				window.external.Face_Show(width, height, x, y);
				window.faceCallBack = function(value) {
					callback(value);
				};
			} catch(error) {
				alert("活体检测设备打开失败");
			}
		}
		$scope.Face_Show(640, 480, 850, 270, function(info) {
			$scope.show = true;
			$scope.info = "检测成功";
			$scope.$apply();
		});
	}
	$scope.faceClose = function() {
		$scope.show = false;
		try {
			window.external.Face_Close();
		} catch(error) {
			alert("关闭失败");
		}
	}

	// 居住证签注机
	$scope.dataCardOpen = function() {
		$scope.show = false;
		try {
			window.external.DataCard_Open('XPS Card Printer');
		} catch(error) {
			alert("居住证设备打开失败")
		}
	}
	$scope.dataCardClose = function() {
		$scope.show = false;
		$scope.loading = false;
		$scope.cardType = false;
		window.external.DataCard_Close();
	}
	$scope.dataCardRead = function() {
		$scope.loading = true;
		$scope.cardType = true;
		var info = window.external.DataCard_Read();
		var val = JSON.parse(info);
		$scope.Info = [{
				"explain": "发卡地行政区划代码",
				"key": "Area",
				"value": val.Area
			},
			{
				"explain": "身份证号",
				"key": "Ssn",
				"value": val.Ssn
			},
			{
				"explain": "卡号",
				"key": "CardNo",
				"value": val.CardNo
			},
			{
				"explain": "社保卡编号",
				"key": "IccId",
				"value": val.IccId
			},
			{
				"explain": "持卡人姓名",
				"key": "PeoPleName",
				"value": val.PeoPleName
			},
			{
				"explain": "第几代社保卡",
				"key": "Gversion",
				"value": val.Gversion
			},
			{
				"explain": "有效开始日期",
				"key": "StartDate",
				"value": val.StartDate
			},
			{
				"explain": "有效截止日期",
				"key": "EndDate",
				"value": val.EndDate
			},
			{
				"explain": "注册号",
				"key": "RegCode",
				"value": val.RegCode
			}
		];
		$scope.loading = false;
		$scope.show = true;
		$scope.$apply();
		window.external.Hd_SsCard_Close();
	}
	$scope.dataCardWrite = function(){
			window.external.DataCard_Print(135, 85, 300, 400, "<style>td{padding:0px;text-align: justify; -ms-text-justify: inter-ideograph; -ms-text-align-last: justify;}</style><table cellspacing='0' style='font-family:黑体;font-size: 10px;font-weight: bold;'><tr><td>居住地</td><td>:</td><td rowspan='2'>测试居住地<td></tr><tr><td>地址</td><td>:</td></tr><tr><td>有效期限</td><td>:</td><td>测试有效期<td></tr></table>");
//			oDataCardInfo.innerHTML = "写卡中。。。";
		}

});