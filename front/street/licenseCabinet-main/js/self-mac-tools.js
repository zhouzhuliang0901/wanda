/**
 * 打印工具类
 */
jQuery.jatools = {
	init: function() {
		var heads = document.getElementsByTagName("head");
		var obj = document.createElement("OBJECT");
		obj.setAttribute("ID", "jatoolsPrinter");
		obj.setAttribute("CLASSID",
			"CLSID:B43D3361-D075-4BE2-87FE-057188254255");
		obj.setAttribute("CODEBASE", "jatoolsPrinter.cab#version=8,6,0,0");
		if (heads.length)
			heads[0].appendChild(obj);
		else
			document.documentElement.appendChild(obj);
	},
	print: function(options) {
		var defaults = {
			settings: {
				topMargin: 1,
				leftMargin: 1,
				bottomMargin: 1,
				rightMargin: 1
			}, // 设置上下左距页边距为10毫米，注意，单位是 1/10毫米
			documents: document,
			classesReplacedWhenPrint: new Array(
				'.only_for_print{display:block}'),
			copyrights: '杰创软件拥有版权  www.jatools.com'
		};
		var myDoc = $.extend(defaults, options);
		document.getElementById("jatoolsPrinter").print(myDoc, false);
	},
	printPreview: function(options) {
		var defaults = {
			settings: {
				topMargin: 1,
				leftMargin: 1,
				bottomMargin: 1,
				rightMargin: 1
			}, // 设置上下左距页边距为10毫米，注意，单位是 1/10毫米
			documents: document,
			classesReplacedWhenPrint: new Array(
				'.only_for_print{display:block}'),
			copyrights: '杰创软件拥有版权  www.jatools.com'
		};

		var myDoc = $.extend(defaults, options);
		document.getElementById("jatoolsPrinter").printPreview(myDoc, false);
	}
};
/**
 * 打印工具类Lodop
 */
jQuery.lodop = {
	load: function() {
		var CreatedOKLodop7766 = null;
		var strHtmInstall =
			"<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
		var strHtmUpdate =
			"<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
		var strHtm64_Install =
			"<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
		var strHtm64_Update =
			"<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
		var strHtmFireFox =
			"<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
		var strHtmChrome = "<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
		var LODOP;
		try {
			// =====判断浏览器类型:===============
			var isIE = (navigator.userAgent.indexOf('MSIE') >= 0) ||
				(navigator.userAgent.indexOf('Trident') >= 0);
			var is64IE = isIE && (navigator.userAgent.indexOf('x64') >= 0);
			// =====如果页面有Lodop就直接使用，没有则新建:==========
			if (oOBJECT != undefined || oEMBED != undefined) {
				if (isIE)
					LODOP = oOBJECT;
				else
					LODOP = oEMBED;
			} else {
				if (CreatedOKLodop7766 == null) {
					LODOP = document.createElement("object");
					LODOP.setAttribute("width", 0);
					LODOP.setAttribute("height", 0);
					LODOP
						.setAttribute("style",
							"position:absolute;left:0px;top:-100px;width:0px;height:0px;");
					if (isIE)
						LODOP.setAttribute("classid",
							"clsid:2105C259-1E0C-4534-8141-A753534CB4CA");
					else
						LODOP.setAttribute("type", "application/x-print-lodop");
					document.documentElement.appendChild(LODOP);
					CreatedOKLodop7766 = LODOP;
				} else
					LODOP = CreatedOKLodop7766;
			}
			// =====判断Lodop插件是否安装过，没有安装或版本过低就提示下载安装:==========
			if ((LODOP == null) || (typeof(LODOP.VERSION) == "undefined")) {
				if (navigator.userAgent.indexOf('Chrome') >= 0)
					document.documentElement.innerHTML = strHtmChrome +
					document.documentElement.innerHTML;
				if (navigator.userAgent.indexOf('Firefox') >= 0)
					document.documentElement.innerHTML = strHtmFireFox +
					document.documentElement.innerHTML;
				if (is64IE)
					document.write(strHtm64_Install);
				else if (isIE)
					document.write(strHtmInstall);
				else
					document.documentElement.innerHTML = strHtmInstall +
					document.documentElement.innerHTML;
				return LODOP;
			} else if (LODOP.VERSION < "6.1.8.7") {
				if (is64IE)
					document.write(strHtm64_Update);
				else if (isIE)
					document.write(strHtmUpdate);
				else
					document.documentElement.innerHTML = strHtmUpdate +
					document.documentElement.innerHTML;
				return LODOP;
			}
			// =====如下空白位置适合调用统一功能(如注册码、语言选择等):====

			// ============================================================
			return LODOP;
		} catch (err) {
			if (is64IE)
				document.documentElement.innerHTML = "Error:" +
				strHtm64_Install + document.documentElement.innerHTML;
			else
				document.documentElement.innerHTML = "Error:" + strHtmInstall +
				document.documentElement.innerHTML;
		}
		return LODOP;
	}
};
/**
 * 设备帮助类
 */
jQuery.device = {
	SarkInit: function() {
		try {
			let cabinetPort = $.config.get('cabinetPort') || 'COM2'
			window.external.SerialPort_Open(cabinetPort, 9600, 8);

		} catch (e) {

		}

	},
	SarkOpen: function(CabinetNum) {
		var hexStr = null;
		var hasCabinetNum = false;
		if ($.config.get('licenseCabinetVersion') == "old") {
			var Custom = [
				//自定义柜号
				"01",
				"02",
				"03",
				"04",
				"05",
				"06",
				"07",
				"08",
				"09",
				"10",
				"11",
				"12"
			];
		}else if ($.config.get('licenseCabinetVersion') == 'hangxin36' || $.config.get('licenseCabinetVersion') == 'hangxin36(2)') {
			var Custom = [
				//自定义柜号
				"01",
				"02",
				"03",
				"04",
				"05",
				"06",
				"07",
				"08",
				"09",
				"10",
				"11",
				"12",
				"13",
				"14",
				"15",
				"16",
				"17",
				"18",
				"19",
				"20",
				"21",
				"22",
				"23",
				"24",
				"25",
				"26",
				"27",
				"28",
				"29",
				"30",
				"31",
				"32",
				"33",
				"34",
				"35",
				"36",
			];
		} else if ($.config.get('licenseCabinetVersion') == 'hangxin60') {
			var Custom = [
				//自定义柜号
				"01",
				"02",
				"03",
				"04",
				"05",
				"06",
				"07",
				"08",
				"09",
				"10",
				"11",
				"12",
				"13",
				"14",
				"15",
				"16",
				"17",
				"18",
				"19",
				"20",
				"21",
				"22",
				"23",
				"24",
				"25",
				"26",
				"27",
				"28",
				"29",
				"30",
				"31",
				"32",
				"33",
				"34",
				"35",
				"36",
				"37",
				"38",
				"39",
				"40",
				"41",
				"42",
				"43",
				"44",
				"45",
				"46",
				"47",
				"48",
				"49",
				"50",
				"51",
				"52",
				"53",
				"54",
				"55",
				"56",
				"57",
				"58",
				"59",
				"60",
			];
		}else if ($.config.get('licenseCabinetVersion') == 'xige') {
			var Custom = [//玺格
				//自定义柜号
				"01",
				"02",
				"03",
				"04",
				"05",
				"06",
				"07",
				"08",
				"09",
				"10",
				"11",
				"12",
				"13",
				"14",
				"15",
				"16",
				"17",
				"18",
				"19",
				"20",
				"21",
				"22",
				"23",
				"24",
				"25",
				"26",
				"27",
				"28",
				"29",
				"30",
			];
		} else {
			var Custom = [
				//自定义柜号
				"01",
				"02",
				"03",
				"04",
				"05",
				"06",
				"07",
				"08",
				"09",
				"10",
				"11",
				"12"
			];
		}
		if ($.config.get('licenseCabinetVersion') == "old") {
			var SarkNum = [
				"8A0101119A", //第一列01
				"8A01021198", //02
				"8A01031199", //03
				"8A0104119E", //04
				"8A0105119F", //05
				"8A0106119C", //06
				"8A0107119D", //07
				"8A01081192", //08
				"8A01091193", //09
				"8A010A1190", //10
				"8A010B1191", //11
				"8A010C1196" //12
			];
		} else if ($.config.get('licenseCabinetVersion') == 'hangxin36') {
			var SarkNum = [
				"4E4253450901820191", //1
				"4E4253450901820292", //2
				"4E4253450901820393", //3
				"4E4253450901820494", //4
				"4E4253450901820595", //5    
				"4E4253450901820696", //6    
				"4E4253450901820797", //7
				"4E4253450901820898", //8
				"4E4253450901820999", //9
				"4E4253450901820A9A", //10    
				"4E4253450901820B9B", //11
				"4E4253450901820C9C", //12
				"4E4253450902820192", //13
				"4E4253450902820291", //14
				"4E4253450902820390", //15
				"4E4253450902820497", //16
				"4E4253450902820596", //17    
				"4E4253450902820695", //18    
				"4E4253450902820794", //19
				"4E425345090282089B", //20
				"4E425345090282099A", //21
				"4E4253450902820A99", //22
				"4E4253450902820B98", //23
				"4E4253450902820C9F", //24    
				"4E4253450902820D9E", //25
				"4E4253450902820E9D", //26    
				"4E4253450902820F9C", //27
				"4E4253450902821083", //28
				"4E4253450902821182", //29    
				"4E4253450902821281", //30
				"4E4253450902821380", //31
				"4E4253450902821487", //32
				"4E4253450902821586", //33
				"4E4253450902821685", //34
				"4E4253450902821784", //35
				"4E425345090282188B", //36
			];
		}else if ($.config.get('licenseCabinetVersion') == 'hangxin36(2)') {
			var SarkNum = [
				"8A0101119B", //1
				"8A01021198", //2
				"8A01031199", //3
				"8A0104119E", //4
				"8A0105119F", //5    
				"8A0106119C", //6    
				"8A0107119D", //7
				"8A01081192", //8
				"8A01091193", //9
				"8A010A1190", //10    
				"8A010B1191", //11
				"8A010C1196", //12
				"8A02011198", //13
				"8A0202119B", //14
				"8A0203119A", //15
				"8A0204119D", //16
				"8A0205119C", //17    
				"8A0206119F", //18    
				"8A0207119E", //19
				"8A02081191", //20
				"8A02091190", //21
				"8A020A1193", //22
				"8A020B1192", //23
				"8A020C1195", //24    
				"8A020D1194", //25
				"8A020E1197", //26    
				"8A020F1196", //27
				"8A02101189", //28
				"8A02011188", //29    
				"8A0201218B", //30
				"8A0201318A", //31
				"8A0201418D", //32
				"8A0201518C", //33
				"8A0201618F", //34
				"8A0201718E", //35
				"8A02018181", //36
			];
		}else if ($.config.get('licenseCabinetVersion') == 'hangxin60') {
			var SarkNum = [
				"4E4253450900820190", //01
				"4E4253450900820293", //02
				"4E4253450900820392", //03
				"4E4253450900820495", //04
				"4E4253450900820594", //05
				"4E4253450900820697", //06
				"4E4253450900820796", //07
				"4E4253450900820899", //08
				"4E4253450900820998", //09
				"4E4253450900820A9B", //10
				"4E4253450900820B9A", //11
				"4E4253450900820C9D", //12
				"4E4253450901820191", //13
				"4E4253450901820292", //14
				"4E4253450901820393", //15
				"4E4253450901820494", //16
				"4E4253450901820595", //17
				"4E4253450901820696", //18
				"4E4253450901820797", //19
				"4E4253450901820898", //20
				"4E4253450901820999", //21
				"4E4253450901820A9A", //22
				"4E4253450901820B9B", //23
				"4E4253450901820C9C", //24
				"4E4253450901820D9D", //25
				"4E4253450901820E9E", //26
				"4E4253450901820F9F", //27
				"4E4253450901821080", //28
				"4E4253450901821181", //29
				"4E4253450901821282", //30
				"4E4253450901821383", //31
				"4E4253450901821484", //32
				"4E4253450901821585", //33
				"4E4253450901821686", //34
				"4E4253450901821787", //35
				"4E4253450901821888", //36
				"4E4253450902820192", //37
				"4E4253450902820291", //38
				"4E4253450902820390", //39
				"4E4253450902820497", //40
				"4E4253450902820596", //41
				"4E4253450902820695", //42
				"4E4253450902820794", //43
				"4E425345090282089B", //44
				"4E425345090282099A", //45
				"4E4253450902820A99", //46
				"4E4253450902820B98", //47
				"4E4253450902820C9F", //48
				"4E4253450902820D9E", //49
				"4E4253450902820E9D", //50
				"4E4253450902820F9C", //51
				"4E4253450902821083", //52
				"4E4253450902821182", //53
				"4E4253450902821281", //54
				"4E4253450902821380", //55
				"4E4253450902821487", //56
				"4E4253450902821586", //57
				"4E4253450902821685", //58
				"4E4253450902821784", //59
				"4E425345090282188B" //60
			];
		}else if ($.config.get('licenseCabinetVersion') == 'xige') {
			var SarkNum = [ //玺格
				"fef25501", //1
				"fef25502", //2
				"fef25503", //3
				"fef25504", //4
				"fef25505", //5    
				"fef25506", //6    
				"fef25507", //7
				"fef25508", //8
				"fef25509", //9
				"fef2550A", //10    
				"fef2550B", //11
				"fef2550C", //12
				"fef2550D", //13
				"fef2550E", //14
				"fef2550F", //15
				"fef25510", //16
				"fef25511", //17
				"fef25512", //18    
				"fef25513", //19    
				"fef25514", //20
				"fef25515", //21
				"fef25516", //22
				"fef25517", //23
				"fef25518", //24
				"fef25519", //25    
				"fef2551A", //26
				"fef2551B", //27    
				"fef2551C", //28
				"fef2551D", //29
				"fef2551E", //30    
			];
		} else {
			var SarkNum = [
				"4E4253450901820191", //第一列01
				"4E4253450901820292", //02
				"4E4253450901820393", //03
				"4E4253450901820494", //04
				"4E4253450901820595", //05
				"4E4253450901820696", //06
				"4E4253450901820797", //07
				"4E4253450901820898", //08
				"4E4253450901820999", //09
				"4E4253450901820A9A", //10
				"4E4253450901820B9B", //11
				"4E4253450901820C9C" //12
			];
		}
		for (var i = 0; i < Custom.length; i++) {
			if (Custom[i] === CabinetNum) {
				hexStr = SarkNum[i];
				window.external.SerialPort_WriteHexStr(hexStr);
			}
		}
	},
	GoHome: function() {
		try {
			window.external.GoHome(); //返回config 主页地址
		} catch (err) {
			window.external.Log_Debug("不支持 window.external.GoHome()");
		}
	},
	Camera_Init: function(width, height, x, y) {
		try {
			/*初始化摄像头 如果位置尺寸不变只需要调用一次*/
			window.external.VideoCapture_Init(width, height, x, y);
		} catch (error) {
			console.log("不支持 VideoCapture_Init");
		}
	},
	Camera_Link: function(CameraName, ResolvingPowerIndex) {
		try {
			/*选择摄像头CameraName、分辨率ResolvingPower*/
			window.external.VideoCapture_Link(CameraName, ResolvingPowerIndex);
		} catch (error) {
			console.log("不支持 VideoCapture_Link");
		}
	},
	Camera_Show: function() {
		try {
			/**打开准备好的摄像头  前置两个方法
			 * window.external.VideoCapture_Init(width,height,x,y);
			 * window.external.VideoCapture_Link(CameraName, ResolvingPowerIndex);
			 */
			window.external.VideoCapture_Show();
		} catch (error) {
			console.log("摄像头没有准备好！");
		}
	},
	Camera_Hide: function(width, height, x, y) {
		try {
			/**关闭准备好的摄像头*/
			window.external.VideoCapture_Hide();
		} catch (error) {}
	},
	Camera_Base64: function() {
		try {
			/**关闭准备好的摄像头*/
			return window.external.VideoCapture_Capture_Base64();
		} catch (error) {

		}
	},
	//获取当前应用程序路径
	currentPath: function() {
		try {
			window.external.GetCurrentPath();
		} catch (e) {
			return "C:";
		}
	},

	// 二维码
	qrCodeOpen: function(callback) {
		try {
			//			window.external.Hd_QrScanner_Open();
			//			window.qrScannerCallBack = function(value) {
			//				callback(value);
			//			};
			//			window.GetScannerCode = function(value) {
			//				callback(value);
			//			};
			acBridgeMac.qrCodeOpen(callback);
		} catch (e) {}
	},
	qrCodeClose: function() {
		try {
			//			window.external.Hd_QrScanner_Close();
			acBridgeMac.qrCodeClose();
		} catch (e) {}
	},

	// 身份证阅读器
	idCardOpen: function(callback) {
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
		} catch (e) {}
	},
	idCardClose: function() {
		try {
			window.external.Hd_IdCard_Close();
		} catch (e) {}
	},

	// 高拍仪
	cmCaptureShow: function(width, height, x, y) {
		try {
			window.external.CmCapture_Show(width, height, x, y);
		} catch (e) {}
	},
	cmCaptureSelectRect: function(x, y, xwidth, height) {
		try {
			window.external.CmCapture_SelectRect(x, y, xwidth, height);
		} catch (e) {}
	},
	cmCaptureCaptureImageModel: function(callback) {
		try {
			window.external.CmCapture_Capture_ImageModel();
			window.cmCaptureImageCallBack = function(value) {
				callback(value);
			};
		} catch (e) {}
	},
	cmCaptureCaptureUrl: function() {
		try {
			return window.external.CmCapture_Capture_Url();
		} catch (e) {}
	},
	cmCaptureCaptureBase64: function() {
		try {
			return window.external.CmCapture_Capture_Base64();
		} catch (e) {}
	},
	cmCaptureHide: function() {
		try {
			window.external.CmCapture_Hide();
		} catch (e) {}
	},
	cmCaptureClose: function() {
		try {
			window.external.CmCapture_Close();
		} catch (e) {}
	},

	// 智能语音
	audioStart: function(callback) {
		try {
			window.external.Hd_Audio_Start();
			window.audioCallBack = function(value) {
				callback(value);
			};
			window.RecordSoundInfo = function(value) {
				callback(value);
			};
		} catch (e) {}
	},
	audioStop: function() {
		try {
			window.external.Hd_Audio_Stop();
		} catch (e) {}
	},
	//文件操作

	fileOpen: function() {
		try {
			window.external.File_Open();
		} catch (e) {
			window.external.debug("打开U盘失败");
		}
	},
	fileClose: function() {
		try {
			window.external.File_Close();
		} catch (e) {
			window.external.debug("关闭U盘失败");
		}
	},
	fileBase64: function(filePath) {
		try {
			return window.external.File_Base64(filePath);
		} catch (e) {}
		return "";
	},
	// Office操作
	officeOpen: function(path) {
		try {
			window.external.Office_Open(path);
		} catch (e) {}
	},
	officeReadOnlyOpen: function(path) {
		try {
			window.external.Office_ReadOnly_Open(path);
		} catch (e) {}
	},
	officeOpenRelative: function(fileName) {
		try {
			window.external.Office_Open_Relative(fileName);
		} catch (e) {}
	},
	officeReadOnly: function(isRead) {
		try {
			window.external.Office_ReadOnly(isRead);
		} catch (e) {}
	},
	officeShow: function(width, height, x, y) {
		try {
			window.external.Office_Show(width, height, x, y);
		} catch (e) {}
	},
	officeSetStringValue: function(field, value) {
		try {
			window.external.Office_SetStringValue(field, value);
		} catch (e) {}
	},
	officeFrame: function() {
		try {
			window.external.Office_Frame();
		} catch (e) {}
	},
	officeSetJpgValue: function(field, value) {
		try {
			window.external.Office_SetJpgValue(field, value);
		} catch (e) {}
	},
	officePrint: function() {
		try {
			window.external.Office_Print();
		} catch (e) {}
	},
	officeDocSet: function(name, json1, json2) {
		try {
			window.external.Office_Doc_Set(name, json1, json2);
		} catch (e) {}
	},
	officeDocPrint: function(url) {
		try {
			window.external.office_Doc_Print(url);
		} catch (e) {}
	},
	officeClose: function() {
		try {
			window.external.Office_Close();
		} catch (e) {}
	},
	pdfPrint: function(url) {
		try {
			window.external.Office_Pdf_Print(url);
		} catch (e) {}
	},
	// HTTP相关
	httpPost: function(url, keyValueJsonStr, successCallback, errorCallback) {
		try {
			window.external.Http_Post(url, keyValueJsonStr, "httpSuccessCallback", "httpErrorCallback");
			window.httpSuccessCallback = function(result) {
				successCallback(result);
			};
			window.httpErrorCallback = function(webexception) {
				errorCallback(webexception);
			};
		} catch (e) {
			;
		}
	},
	httpUpload: function(url, fileKey, filePath, keyValueJsonStr, successCallback, errorCallback) {
		try {
			window.external.Http_Upload(url, fileKey, filePath, keyValueJsonStr, "httpUploadSuccessCallback",
				"httpUploadErrorCallback");
			window.httpUploadSuccessCallback = function(result) {
				successCallback(result);
			};

			window.httpUploadErrorCallback = function(webexception) {
				errorCallback(webexception);
			};
		} catch (e) {}
	},
	httpDownload: function(url, filePath, onProgressChanged, onSuccessCallback, errorCallback) {
		try {
			window.external.Http_Download(url, filePath, "httpDownloadOnProgressChanged",
				"httpDownloadSuccessCallback", "httpDownloadErrorCallback");
			window.httpDownloadOnProgressChanged = function(bytesCopied, totalBytes) {
				onProgressChanged(bytesCopied, totalBytes);
			};
			window.httpDownloadSuccessCallback = function(headers) {
				onSuccessCallback(headers);
			};
			window.httpDownloadErrorCallback = function(webexception) {
				errorCallback(headers);
			};
		} catch (e) {}
	},
	printGetLodop: function() {
		try {
			return window.external.Printer_Lodop();
		} catch (e) {
			return jQuery.lodop.load();
		}
	}
};
/**
 * 配置信息
 */
jQuery.config = {
	get: function(key) {
		try {
			return window.external.GetConfig(key);
		} catch (e) {}
		return null;
	}
};
/**
 * 日志帮助类
 */
jQuery.log = {
	debug: function(debug) {
		try {
			window.external.Log_Debug(debug);
		} catch (e) {
			console.debug(debug);
		}
	},
	info: function(info) {
		try {
			window.external.Log_Info(info);
		} catch (e) {
			console.info(info);
		}
	},
	error: function(error) {
		try {
			window.external.Log_Error(error);
		} catch (e) {
			console.error(error);
		}
	}
};
