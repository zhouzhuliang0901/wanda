/**
 * 打印工具类Lodop
 */
jQuery.lodop = {
	needCLodop: function() {
		try {
			var ua = navigator.userAgent;
			if(ua.match(/Windows\sPhone/i))
				return true;
			if(ua.match(/iPhone|iPod|iPad/i))
				return true;
			if(ua.match(/Android/i))
				return true;
			if(ua.match(/Edge\D?\d+/i))
				return true;

			var verTrident = ua.match(/Trident\D?\d+/i);
			var verIE = ua.match(/MSIE\D?\d+/i);
			var verOPR = ua.match(/OPR\D?\d+/i);
			var verFF = ua.match(/Firefox\D?\d+/i);
			var x64 = ua.match(/x64/i);
			if((!verTrident) && (!verIE) && (x64))
				return true;
			else if(verFF) {
				verFF = verFF[0].match(/\d+/);
				if((verFF[0] >= 41) || (x64))
					return true;
			} else if(verOPR) {
				verOPR = verOPR[0].match(/\d+/);
				if(verOPR[0] >= 32)
					return true;
			} else if((!verTrident) && (!verIE)) {
				var verChrome = ua.match(/Chrome\D?\d+/i);
				if(verChrome) {
					verChrome = verChrome[0].match(/\d+/);
					if(verChrome[0] >= 41)
						return true;
				}
			}
			return false;
		} catch(err) {
			return true;
		}
	},
	load: function() {
		var oOBJECT, oEMBED;
		var CreatedOKLodop7766 = null,
			CLodopIsLocal;
		//====页面引用CLodop云打印必须的JS文件,用双端口(8000和18000）避免其中某个被占用：====
		if(jQuery.lodop.needCLodop()) {
			var src1 = "http://localhost:8000/CLodopfuncs.js?priority=1";
			var src2 = "http://localhost:18000/CLodopfuncs.js?priority=0";
			var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
			var oscript = document.createElement("script");
			oscript.src = src1;
			head.insertBefore(oscript, head.firstChild);
			oscript = document.createElement("script");
			oscript.src = src2;
			head.insertBefore(oscript, head.firstChild);
			CLodopIsLocal = !!((src1 + src2).match(/\/\/localho|\/\/127.0.0./i));
		}
		var strHtmInstall = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
		var strHtmUpdate = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
		var strHtm64_Install = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
		var strHtm64_Update = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
		var strHtmFireFox = "<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
		var strHtmChrome = "<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
		var strCLodopInstall_1 = "<br><font color='#FF00FF'>Web打印服务CLodop未安装启动，点击这里<a href='CLodop_Setup_for_Win32NT.exe' target='_self'>下载执行安装</a>";
		var strCLodopInstall_2 = "<br>（若此前已安装过，可<a href='CLodop.protocol:setup' target='_self'>点这里直接再次启动</a>）";
		var strCLodopInstall_3 = "，成功后请刷新本页面。</font>";
		var strCLodopUpdate = "<br><font color='#FF00FF'>Web打印服务CLodop需升级!点击这里<a href='CLodop_Setup_for_Win32NT.exe' target='_self'>执行升级</a>,升级后请刷新页面。</font>";
		var LODOP;
		try {
			var ua = navigator.userAgent;
			var isIE = !!(ua.match(/MSIE/i)) || !!(ua.match(/Trident/i));
			if(jQuery.lodop.needCLodop()) {
				try {
					LODOP = getCLodop();
				} catch(err) {}
				if(!LODOP && document.readyState !== "complete") {
					alert("网页还没下载完毕，请稍等一下再操作.");
					return;
				}
				if(!LODOP) {
					document.body.innerHTML = strCLodopInstall_1 + (CLodopIsLocal ? strCLodopInstall_2 : "") + strCLodopInstall_3 + document.body.innerHTML;
					return;
				} else {
					if(CLODOP.CVERSION < "3.0.8.3") {
						document.body.innerHTML = strCLodopUpdate + document.body.innerHTML;
					}
					if(oEMBED && oEMBED.parentNode)
						oEMBED.parentNode.removeChild(oEMBED);
					if(oOBJECT && oOBJECT.parentNode)
						oOBJECT.parentNode.removeChild(oOBJECT);
				}
			} else {
				var is64IE = isIE && !!(ua.match(/x64/i));
				//=====如果页面有Lodop就直接使用，没有则新建:==========
				if(oOBJECT || oEMBED) {
					if(isIE)
						LODOP = oOBJECT;
					else
						LODOP = oEMBED;
				} else if(!CreatedOKLodop7766) {
					LODOP = document.createElement("object");
					LODOP.setAttribute("width", 0);
					LODOP.setAttribute("height", 0);
					LODOP.setAttribute("style", "position:absolute;left:0px;top:-100px;width:0px;height:0px;");
					if(isIE)
						LODOP.setAttribute("classid", "clsid:2105C259-1E0C-4534-8141-A753534CB4CA");
					else
						LODOP.setAttribute("type", "application/x-print-lodop");
					document.documentElement.appendChild(LODOP);
					CreatedOKLodop7766 = LODOP;
				} else
					LODOP = CreatedOKLodop7766;
				//=====Lodop插件未安装时提示下载地址:==========
				if((!LODOP) || (!LODOP.VERSION)) {
					if(ua.indexOf('Chrome') >= 0)
						document.body.innerHTML = strHtmChrome + document.body.innerHTML;
					if(ua.indexOf('Firefox') >= 0)
						document.body.innerHTML = strHtmFireFox + document.body.innerHTML;
					document.body.innerHTML = (is64IE ? strHtm64_Install : strHtmInstall) + document.body.innerHTML;
					return LODOP;
				}
			}
			if(LODOP.VERSION < "6.2.2.6") {
				if(!needCLodop())
					document.body.innerHTML = (is64IE ? strHtm64_Update : strHtmUpdate) + document.body.innerHTML;
			}
			//===如下空白位置适合调用统一功能(如注册语句、语言选择等):==

			//=======================================================
			return LODOP;
		} catch(err) {
			console.info("getLodop出错:" + err);
		}
	}
};
/**
 * 设备帮助类
 */
jQuery.device = {
	// 摄像头
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
			var this_ = this
			this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\recognition.wav");
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
			saveDeviceStatus("摄像头", 0, "正常", 0, 0, 0, 0);
			window.external.Face_Show(width, height, x, y);
			window.faceCallBack = function(value) {
				callback(value);
			};
		} catch(error) {
			saveDeviceStatus("摄像头", 1, "未正常调用", 0, 0, 0, 0);
		}
	},
	//关闭活体检测
	Face_Close: function(width, height, x, y) {
		try {
			window.external.Face_Close();
		} catch(error) {}
	},

	// 二维码
	qrCodeOpen: function(callback) {
		try {
			var this_ = this
			this_.audioPPlay("Realtek High Definition Audio", window.external.GetCurrentPath() + "\\resources\\audio\\citizen.wav");
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
			var this_ = this
			this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\idcard.wav");
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
			var this_ = this
			this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\sscard.wav");
			this_.senseSend("O(00,13,2)E")
			setTimeout(function() {
				this_.senseSend("O(00,13,0)E")
			}, 5000);
			window.external.Hd_SsCard_Open();
			window.SsCardCallBack = function(value) {
				callback(value);
			};
			saveDeviceStatus("社保卡", 0, "正常", 0, 0, 0, 0);
		} catch(e) {
			saveDeviceStatus("社保卡", 1, "未正常调用", 0, 0, 0, 0);
		}
	},
	ssCardClose: function() {
		try {
			var this_ = this
			this_.senseSend("O(00,13,0)E")
			window.external.Hd_SsCard_Close();
		} catch(e) {}
	},
	// 高拍仪
	cmCaptureShow: function(width, height, x, y) {
		try {
			var this_ = this
			//this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\cmCapture.wav");
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
	cmCaptureCaptureBase64: function() {
		try {
			return window.external.CmCapture_Capture_Base64();
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
		} catch(e) {}
	},
	audioPlay: function(path) {
		try {
			$.log.debug(path);
			window.external.Hd_Audio_Play(path);
		} catch(e) {}
	},
	audioPPlay: function(productName, path) {
		try {
			$.log.debug(path);
			window.external.Hd_Audio_PPlay(productName, path);
		} catch(e) {}
	},
	audioStop: function() {
		try {
			window.external.Hd_Audio_Stop();
		} catch(e) {}
	},
	
	// Office操作
	officeOpen: function(path) {
		try {
			window.external.Office_Open(path);
		} catch(e) {}
	},
	officeReadOnlyOpen: function(path) {
		try {
			window.external.Office_ReadOnly_Open(path);
		} catch(e) {}
	},
	officeOpenRelative: function(fileName) {
		try {
			window.external.Office_Open_Relative(fileName);
		} catch(e) {}
	},
	officeReadOnly: function(isRead) {
		try {
			window.external.Office_ReadOnly(isRead);
		} catch(e) {}
	},
	officeShow: function(width, height, x, y) {
		try {
			window.external.Office_Show(width, height, x, y);
		} catch(e) {}
	},
	officeSetStringValue: function(field, value) {
		try {
			window.external.Office_SetStringValue(field, value);
		} catch(e) {}
	},
	officeFrame: function() {
		try {
			window.external.Office_Frame();
		} catch(e) {}
	},
	officeSetJpgValue: function(field, value) {
		try {
			window.external.Office_SetJpgValue(field, value);
		} catch(e) {}
	},
	officePrint: function() {
		try {
			window.external.Office_Print();
		} catch(e) {}
	},
	officeDocSet: function(name, json1, json2) {
		try {
			window.external.Office_Doc_Set(name, json1, json2);
		} catch(e) {}
	},
	officeDocPrint: function(url) {
		try {
			window.external.office_Doc_Print(url);
		} catch(e) {}
	},
	officeClose: function() {
		try {
			window.external.Office_Close();
		} catch(e) {}
	},
	pdfPrint: function(url) {
		try {
			var this_ = this
			this_.senseSend("O(00,12,2)E")
			setTimeout(function() {
				this_.senseSend("O(00,12,0)E")
			}, 5000);
			window.external.Office_Pdf_Print(url);
			trackEvent1("打印");
		} catch(e) {}
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
		} catch(e) {;
		}
	},
	httpUpload: function(url, fileKey, filePath, keyValueJsonStr, successCallback, errorCallback) {
		try {
			window.external.Http_Upload(url, fileKey, filePath, keyValueJsonStr, "httpUploadSuccessCallback", "httpUploadErrorCallback");
			window.httpUploadSuccessCallback = function(result) {
				successCallback(result);
			};

			window.httpUploadErrorCallback = function(webexception) {
				errorCallback(webexception);
			};
		} catch(e) {}
	},
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
	printGetLodop: function(type) {
		try {
			var this_ = this
			if(type=='medical') { //医保打印
				this_.senseSend("O(00,14,2)E")
				setTimeout(function() {
					this_.senseSend("O(00,14,0)E")
				}, 5000);
				trackEvent1("医保记录册打印");
				saveDeviceStatus("MedicalInsuranc", 0, "正常", 0, 0, 0, 0);
			} else { //A4打印
				this_.senseSend("O(00,12,2)E")
				setTimeout(function() {
					this_.senseSend("O(00,12,0)E")
				}, 5000);
				trackEvent1("A4打印");
				saveDeviceStatus("A4Printer", 0, "正常", 0, 0, 0, 0);
			}
			return window.external.Printer_Lodop();
		} catch(e) {
			return jQuery.lodop.load();
		}
	},

};
/**
 * 配置信息
 */
jQuery.config = {
	get: function(key) {
		try {
			return window.external.GetConfig(key);
		} catch(e) {}
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