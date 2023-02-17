var _vlstatId = _vlstatId || [];
// 添加事件
function trackEvent1(eventName) {}
//外设后台统计
/*
 * stOutDeviceCode 外设标识
 * nmException  是否异常
 * stCause  异常原因
 * nmNotice 是否通知
 * nmTotal 总量
 * nmRemain 剩余量
 * count 消耗数量
 */
function saveDeviceStatus(stOutDeviceCode, nmException, stCause, nmNotice, nmTotal, nmRemain, count) {}
/**
 * 打印工具类
 */
jQuery.jatools = {
	init: function() {},
	print: function(options) {},
	printPreview: function(options) {}
};
/**
 * 打印工具类Lodop
 */
jQuery.lodop = {
	needCLodop: function() {},
	load: function() {}
};
/**
 * 设备帮助类
 */
jQuery.device = {
	SarkInit: function() {
		//window.external.SerialPort_Open("COM7", 9600, 8);
	},
	SarkOpen: function(CabinetNum) {},
	GoHome: function() {
		try {
			console.log("返回首页");
			window.external.GoHome(); //返回config 主页地址
		} catch(err) {
			$.log.debug("不支持 window.external.GoHome()");
		}
	},
	Camera_Init: function(width, height, x, y) {
		try {
			/*初始化摄像头 如果位置尺寸不变只需要调用一次*/
			window.external.VideoCapture_Init(width, height, x, y);
		} catch(error) {}
	},
	Camera_Link: function(CameraName, ResolvingPowerIndex) {
		try {
			/*选择摄像头CameraName、分辨率ResolvingPower*/
			window.external.VideoCapture_Link(CameraName, ResolvingPowerIndex);
		} catch(error) {}
	},
	Camera_UnLink: function() {
		try {
			/*关闭摄像头*/
			window.external.VideoCapture_UnLink();
		} catch(error) {}
	},
	Camera_Show: function() {
		try {
			/**打开准备好的摄像头  前置两个方法
			 * window.external.VideoCapture_Init(width,height,x,y);
			 * window.external.VideoCapture_Link(CameraName, ResolvingPowerIndex);
			 */
			window.external.VideoCapture_Show();
		} catch(error) {
			$.log.debug("摄像头没有准备好！");
		}
	},
	Camera_Hide: function(width, height, x, y) {
		try {
			/**关闭准备好的摄像头*/
			window.external.VideoCapture_Hide();
		} catch(error) {}
	},
	Camera_Close: function() {
		try {
			/*关闭摄像头*/
			window.external.VideoCapture_Close();
		} catch(error) {}
	},
	Camera_Base64: function(width, height, x, y) {
		try {
			/**关闭准备好的摄像头*/
			return window.external.VideoCapture_Capture_Base64();
		} catch(error) {}
	},
	//活体检测
	Face_Show: function(width, height, x, y, callback) {
		try {
			if(true) {
				setTimeout(function() {
					window.external.VideoCapture_Init(width, height, x, y);
				}, 1000)
				setTimeout(function() {
					window.getConfigCallBack = function(CameraName) {
						window.external.VideoCapture_Link(CameraName, 3);
					}
					window.external.GetConfig("camera");
					//window.external.VideoCapture_Link("Techshino TCF261 Col", 3);
				}, 2000)
				setTimeout(function() {
					window.external.VideoCapture_Show();
				}, 3000)
				setTimeout(function() {
					window.external.VideoCapture_Capture_Base64();
					window.videoCaptureBase64Callback = function(data) {
						callback(data)
					}
				}, 16000)
				setTimeout(function() {
					window.external.VideoCapture_UnLink();
					window.external.VideoCapture_Close();
				}, 17000)
			} else {
				window.external.Face_Show(width, height, x, y);
				window.faceCallBack = function(value) {
					callback(value);
				};
			}
		} catch(error) {}
	},
	//关闭活体检测
	Face_Close: function() {
		try {
			window.external.VideoCapture_UnLink();
			window.external.VideoCapture_Hide();
			window.external.VideoCapture_Close();
			window.external.Face_Close();
		} catch(error) {}
	},
	//获取当前应用程序路径
	currentPath: function(callback) {
		try {
			//return window.external.GetCurrentPath();
			window.getCurrentPathCallBack = function(data) {
				callback(data);
			}
			window.external.GetCurrentPath();
		} catch(e) {
			return "C:";
		}
	},
	//灯控感应
	senseSend: function(cmd) {
		try {
			//window.external.Sense_Send(cmd);
		} catch(e) {}
	},
	senseCallBack: function(callback) {
		if(callback) {
			window.SenseCallBack = function(value) {
				callback(value);
			};
		}
	},
	// 二维码
	qrCodeOpen: function(callback) {
		try {
			window.external.Hd_QrScanner_Open();
			window.qrScannerCallBack = function(value) {
				callback(value);
			};
			window.GetScannerCode = function(value) {
				callback(value);
			};
			saveDeviceStatus("QrScanner", 0, "正常", 0, 0, 0, 0);
		} catch(e) {
			saveDeviceStatus("QrScanner", 1, "未正常调用", 0, 0, 0, 0);
		}
	},
	qrCodeClose: function() {
		try {
			window.external.Hd_QrScanner_Close();
		} catch(e) {}
	},

	// 身份证阅读器
	idCardOpen: function(callback) {
		try {
			window.external.Hd_IdCard_Open();
			window.idCardCallBack = function(value) {
				var c={};
				c=JSON.parse(value)
				c.Number=c.IDCard;
				c.Name=c.PeopleName
				callback(JSON.stringify(c));
			};
			window.HtmlUserInfo = function(value) {
				callback(value);
			};
			window.SMYHtmlUserInfo = function(value) {
				callback(value);
			};
			saveDeviceStatus("IdCard", 0, "正常", 0, 0, 0, 0);
		} catch(e) {
			saveDeviceStatus("IdCard", 1, "未正常调用", 0, 0, 0, 0);
		}
	},
	idCardClose: function() {
		try {
			window.external.Hd_IdCard_Close();
		} catch(e) {}
	},
	// 社保卡阅读器
	ssCardOpen: function(callback) {
		try {
			//			window.external.Hd_SsCard_Open();
			//			window.SsCardCallBack = function(value) {
			//				callback(value);
			//			};
		} catch(e) {}
	},
	ssCardClose: function() {
		try {
			//			window.external.Hd_SsCard_Close();
		} catch(e) {}
	},
	//居住证制卡
	dataCardCardIn: function() {},
	dataCardRead: function(callback) {},
	dataCardPrint: function(top, left, width, height, strHTML, zhuofansoftArray, epointText) {},
	dataCardCardOut: function() {},
	//居住证查询 擦写
	dataCardQuery: function(chidcard, chname, successCallback, errorCallback) {},
	dataCardErase: function(chidcard, chname, errorCallback) {},
	//医保记录册打印
	medicalPrint: function(newBookBh, newBookKh, userXb, userName, userAge) {},
	// 高拍仪
	cmCaptureShow: function(width, height, x, y) {
		try {
			var this_ = this
			this_.senseSend("O(00,15,2)E")
			setTimeout(function() {
				this_.senseSend("O(00,15,0)E")
			}, 5000);
			window.external.CmCapture_Show(width, height, x, y);
			saveDeviceStatus("CmCapture", 0, "正常", 0, 0, 0, 0);
		} catch(e) {
			saveDeviceStatus("CmCapture", 1, "未正常打开", 0, 0, 0, 0);
		}
	},
	cmCaptureSelectRect: function(x, y, xwidth, height) {
		try {
			window.external.CmCapture_SelectRect(x, y, xwidth, height);
		} catch(e) {}
	},
	cmCaptureCaptureImageModel: function(callback) {
		try {
			window.external.CmCapture_Capture_ImageModel();
			window.cmCaptureImageCallBack = function(value) {
				callback(value);
			};
		} catch(e) {}
	},
	cmCaptureCaptureUrl: function() {
		try {
			return window.external.CmCapture_Capture_Url();
		} catch(e) {}
	},
	cmCaptureCaptureBase64: function(callback) {
		try {
			window.external.CmCapture_Capture_Base64();
			window.cmCaptureImageCallBack = function(value) {
				callback(value);
			};
		} catch(e) {}
	},
	cmCaptureHide: function() {
		try {
			window.external.CmCapture_Hide();
		} catch(e) {}
	},
	cmCaptureClose: function() {
		try {
			window.external.CmCapture_Close();
		} catch(e) {}
	},

	//智能语音
	//文件操作

	fileOpen: function(callback) {
		try {
			//			window.external.File_Open();
			//			window.fileCallBack = function(value) {
			//				callback(value);
			//			};
		} catch(e) {
			window.external.debug("打开U盘失败");
		}
	},
	fileClose: function() {
		try {
			//			window.external.File_Close();
		} catch(e) {
			window.external.debug("关闭U盘失败");
		}
	},
	fileBase64: function(filePath, callback) {
		try {
			window.fileBase64CallBack = function(base64) {
				callback(base64)
			}
			window.external.File_Base64(filePath);
		} catch(e) {}
		return "";
	},
	// Office操作
	officeOpen: function(path) {},
	officeReadOnlyOpen: function(path) {},
	officeOpenRelative: function(fileName) {},
	officeReadOnly: function(isRead) {},
	officeShow: function(width, height, x, y) {},
	officeSetStringValue: function(field, value) {},
	officeFrame: function() {},
	officeSetJpgValue: function(field, value) {},
	officePrint: function() {},
	officeDocSet: function(name, json1, json2) {},
	officeDocPrint: function(url) {},
	officeClose: function() {},
	pdfPrint: function(url) {
		try {
			//window.external.Office_Pdf_Print(url);
			window.external.Office_Pdf_AdobeReader_Print(url);
		} catch(e) {}
	},
	urlPdfPrint: function(url, filePath, callback, urlbase64) {
		try {
			//acBridgeMac.urlPdfPrint(url, filePath, callback);
			window.external.Office_Url_Pdf_Print(url);
			//var filePath='/home/casic/'+Date.parse(new Date())+'.pdf';
			//this.httpDownload(url,filePath,function(onProgressChanged){},function(onSuccessCallback){
			//		alert(onSuccessCallback);
			//		setTimeout(function(){
			//			window.external.Office_Pdf_AdobeReader_Print(filePath);
			//		},1000)
			//},function(errorCallback){})
			trackEvent1("打印");
		} catch(e) {}
	},
	pdfAdobeReaderPrint: function(url) {
		try {
			window.external.Office_Pdf_AdobeReader_Print(url);
		} catch(e) {}
	},
	printerHtml: function(htmls) {
		try {
			window.external.Printer_Html(htmls);
		} catch(e) {}
	},
	//串口操作
	// HTTP相关
	httpDownload: function(url, filePath, onProgressChanged, onSuccessCallback, errorCallback) {
		try {
			window.external.Http_Download(url, filePath, "httpDownloadOnProgressChanged", "httpDownloadSuccessCallback", "httpDownloadErrorCallback");
			window.httpDownloadOnProgressChanged = function(bytesCopied, totalBytes) {
				onProgressChanged(bytesCopied, totalBytes);
			};
			window.httpDownloadSuccessCallback = function(headers) {
				onSuccessCallback(headers);
			};
			window.httpDownloadErrorCallback = function(webexception) {
				errorCallback(headers);
			};
		} catch(e) {}
	},
	printGetLodop: function(type) {},
	//打开程序
	exeOpenAbsolute: function(processName, exePath) {
		try {
			//window.external.Exe_Open_Absolute(processName, exePath)
		} catch(e) {}
	},
	exeOpenRelative: function(processName, exePath) {
		try {
			//window.external.Exe_Open_Relative(processName, path)
		} catch(e) {}
	}
};
/**
 * 配置信息
 */
jQuery.config = {
	get: function(key, callback) {
		try {
			window.external.GetConfig(key);
			window.getConfigCallBack = function(data) {
				callback(data)
			}
		} catch(e) {}
		return null;
	},
	load: function(key, callback) {
		try {
			window.external.GetConfig(key);
			window.getConfigCallBack = function(data) {
				callback(data)
			}
		} catch(e) {}
	}
};
/**
 * 日志帮助类
 */
jQuery.log = {
	debug: function(debug) {
		try {
			window.external.Log_Debug(debug);
		} catch(e) {
			console.debug(debug);
		}
	},
	info: function(info) {
		try {
			window.external.Log_Info(info);
		} catch(e) {
			console.info(info);
		}
	},
	error: function(error) {
		try {
			window.external.Log_Error(error);
		} catch(e) {
			console.error(error);
		}
	}
};