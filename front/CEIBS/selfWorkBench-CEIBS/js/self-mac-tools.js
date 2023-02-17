
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
		if(heads.length)
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
	vendor: function() {
		return acBridgeMac.vendor();
	},
	imagePath: function() {
		return acBridgeMac.imagePath();
	},
	SarkInit: function() {
		window.external.SerialPort_Open("COM7", 9600, 8);
	},
	SarkOpen: function(CabinetNum) {
		var hexStr = null;
		var hasCabinetNum = false;
		var Custom = [
			//自定义柜号
			"101",
			"102",
			"103",
			"104",
			"105",
			"106",
			"107",
			"108",
			"109",
			"201",
			"202",
			"203",
			"204",
			"205",
			"206",
			"207",
			"208",
			"209"
		];
		var SarkNum = [
			//系统自带柜号
			"fff25501",
			"fff25502",
			"fff25503",
			"fff25504",
			"fff25505",
			"fff25506",
			"fff25507",
			"fff25508",
			"fff25509",
			"fef25501",
			"fef25502",
			"fef25503",
			"fef25504",
			"fef25505",
			"fef25506",
			"fef25507",
			"fef25508",
			"fef25509"
		];

		for(var i = 0; i < Custom.length; i++) {
			if(Custom[i] === CabinetNum) {
				hexStr = SarkNum[i];
				window.external.SerialPort_WriteHexStr(hexStr);
			}
		}
	},
	GoHome: function() {
		try {
			if(acBridgeMac.vendor() == "zhdevice") {
				var test = window.location.href;
				test = test.split('selfWorkBench_V2')[0] + "selfWorkBench_V2/index.html#/index-zh"
				window.location.href = test;
			} else if(acBridgeMac.vendor() == "nhdevice"){
				window.external.GoHome();
			}  else if(acBridgeMac.vendor() == 'pfdevice') {
				var test = window.location.href;
				test = test.split('selfWorkBench_V2')[0] + "selfWorkBench_V2/index.html#/index-pf"
				window.location.href = test;
			} else if(acBridgeMac.vendor() == 'jtdevice') {
				var test = window.location.href;
				test = test.split('selfWorkBench_V2')[0] + "selfWorkBench_V2/index.html#/index-jtyh"
				window.location.href = test;
			} else if(acBridgeMac.vendor() == 'nsdevice') {
				window.external.GoHome();
			} else{
				acBridgeMac.goHome(); //返回config 主页地址
			}
			this.senseSend("O(00,12,0)E");
			this.senseSend("O(00,14,0)E");
			this.senseSend("O(00,15,0)E");
			this.senseSend("O(00,11,0)E");
			this.senseSend("O(00,13,0)E");
		} catch(err) {
			$.log.debug("不支持 window.external.GoHome()");
		}
	},
	Camera_Init: function(width, height, x, y) {
		try {
			/*初始化摄像头 如果位置尺寸不变只需要调用一次*/
			//window.external.VideoCapture_Init(width, height, x, y);
			acBridgeMac.cameraInit(width, height, x, y);
		} catch(error) {}
	},
	Camera_Link: function(CameraName, ResolvingPowerIndex) {
		try {
			/*选择摄像头CameraName、分辨率ResolvingPower*/
			//window.external.VideoCapture_Link(CameraName, ResolvingPowerIndex);
			acBridgeMac.cameraLink(CameraName, ResolvingPowerIndex);
		} catch(error) {}
	},
	Camera_UnLink: function() {
		try {
			/*关闭摄像头*/
			//			window.external.VideoCapture_UnLink();
			acBridgeMac.cameraUnLink();
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
			//			window.external.VideoCapture_Show();
			acBridgeMac.cameraShow();
		} catch(error) {
			$.log.debug("摄像头没有准备好！");
		}
	},
	Camera_Hide: function(width, height, x, y) {
		try {
			/**关闭准备好的摄像头*/
			//			window.external.VideoCapture_Hide();
			acBridgeMac.cameraHide();
		} catch(error) {}
	},
	Camera_Close: function() {
		try {
			/*关闭摄像头*/
			//			window.external.VideoCapture_Close();
			acBridgeMac.cameraClose();
		} catch(error) {}
	},
	Camera_Base64: function(callback) {
		try {
			/**关闭准备好的摄像头*/
			//			return window.external.VideoCapture_Capture_Base64();
			acBridgeMac.cameraBase64(callback);
		} catch(error) {}
	},
	//活体检测
	Face_Show: function(width, height, x, y, callback) {
		try {
			//			window.external.Face_Show(width, height, x, y);
			//			window.faceCallBack = function(value) {
			//				callback(value);
			//			};
			var this_ = this;
			if(acBridgeMac.vendor() == 'wonders' && window.external.GetConfig('liveDetection') == 'N') {
				window.external.VideoCapture_Init(width, height, x, y);
				var CameraName = window.external.GetConfig('camera');
				var ResolvingPowerIndex = window.external.GetConfig('resolution') || 1;
				window.external.VideoCapture_Link(CameraName, ResolvingPowerIndex);
				window.external.VideoCapture_Show();
				setTimeout(function() {
					var dataBase64 = window.external.VideoCapture_Capture_Base64();
					this_.Face_Close();
					callback(dataBase64)
				}, 5000);
			} else if(acBridgeMac.vendor() == 'jhdevice') {
				openCamera();
				setTimeout(function() {
					takePhoto(function(dataBase64) {
						callback(dataBase64)
						closeCamera();
					});
				}, 10000);
			} else if(acBridgeMac.vendor() == 'zhdevice') {
				var device = new JSBridge.Device['faceIRDetect'];
				device.faceDetector().then(function(info) {
					callback(info.obj.faceImage);
				}).fail(function(err) {
					alert('check face err' + JSON.stringify(err));
				});
			} else if(acBridgeMac.vendor() == 'nhdevice') {
				onPageLoad();
				window.nhFaceCallBack = function(val) {
					callback(val);
			        nhcloseCamera();
				}
			} else if(acBridgeMac.vendor() == 'pfdevice') {
				spdb.faceShow(500, 500, 390, 262,callback)
			} else {
				acBridgeMac.faceShow(width, height, x, y, callback);
			}
		} catch(error) {
		}
	},
	//关闭活体检测
	Face_Close: function() {
		try {
			if(acBridgeMac.vendor() == 'wonders' && window.external.GetConfig('liveDetection') == 'N') {
				window.external.VideoCapture_UnLink();
				window.external.VideoCapture_Hide();
				window.external.VideoCapture_Close();
			} else if(acBridgeMac.vendor() == 'jhdevice') {
				var jhDome = document.querySelectorAll("#camera");
				if(jhDome.length > 0) {
					for(var item = 0; item < jhDome.length; item++) {
						var selectedItem = jhDome[item];        
						if (selectedItem.getAttribute("id").indexOf("c")  !=  -1)  { 
							selectedItem.removeNode(true);      
						}
					}
				}
			} else if(acBridgeMac.vendor() == 'pfdevice') {
				spdb.faceHide();
			} else {
				acBridgeMac.faceClose();
			}
		} catch(error) {}
	},
	//获取当前应用程序路径
	currentPath: function() {
		try {
			//			return window.external.GetCurrentPath();
			return acBridgeMac.currentPath();
		} catch(e) {
			return "C:";
		}
	},
	//灯控感应
	senseSend: function(cmd) {
		try {
			if(acBridgeMac.vendor() == 'wonders') {
				window.external.Sense_Send(cmd);
			}
		} catch(e) {}
	},
	senseCallBack: function(callback) {
		if(callback) {
			window.SenseCallBack = function(value) {
				callback(value);
			};
		}
	},
	// 制卡机阅读器
	dsDirectOpen: function(callback) {
		try {
			window.external.DSDirect_Open();
			window.dsdirectReadCallBack = function(value) {
				callback(value);
			};
		} catch(e) {}
	},
	dsDirectClose: function() {
		try {
			window.external.DSDirect_Close();
		} catch(e) {}
	},
	// 二维码
	
	qrCodeOpen: function(callback) {
		try {
			var this_ = this
			this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\citizen.wav");
			//			window.external.Hd_QrScanner_Open();
			//			window.qrScannerCallBack = function(value) {
			//				callback(value);
			//			};
			//			window.GetScannerCode = function(value) {
			//				callback(value);
			//			};
			if(acBridgeMac.vendor() == 'jhdevice') {
				getTDCode(callback);
			} else if(acBridgeMac.vendor() == 'nhdevice') {
				nhdeviceSum.nhqrCodeOpen(callback);
			} else if(acBridgeMac.vendor() == 'pfdevice') {
				spdb.qrScannerOpen(callback);
			} else {
				acBridgeMac.qrCodeOpen(callback);
			}
		} catch(e) {
		}
	},
	qrCodeClose: function() {
		try {
			//			window.external.Hd_QrScanner_Close();
			if(this.vendor() == 'nhdevice') {
				nhdeviceSum.nhqrCodeClose();
			} else if(acBridgeMac.vendor() == 'pfdevice') {
				spdb.qrScannerClose();
			} else {
				acBridgeMac.qrCodeClose();
			}
		} catch(e) {}
	},

	// 身份证阅读器
	idCardOpen: function(callback) {
		try {
			var this_ = this
			this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\idcard.wav");
			//			window.external.Hd_IdCard_Open();
			//			window.idCardCallBack = function(value) {
			//				callback(value);
			//			};
			//			window.HtmlUserInfo = function(value) {
			//				callback(value);
			//			};
			//			window.SMYHtmlUserInfo = function(value) {
			//				callback(value);
			//			};
			if(this.vendor() == 'jhdevice') {
				idCardOpen(callback);
			} else if(this.vendor() == 'nhdevice') {
				nhdeviceSum.nhidCardOpen(callback);
			} else if(this.vendor() == 'pfdevice') {
				spdb.idCardOpen(callback);
			} else {
				acBridgeMac.idCardOpen(callback);
			}
		} catch(e) {
		}
	},
	idCardClose: function() {
		try {
			//			window.external.Hd_IdCard_Close();
			if(this.vendor() == 'nhdevice') {
				nhdeviceSum.nhidCardClose();
			} else if(this.vendor() == 'pfdevice'){
				spdb.idCardClose();
			} else {
				acBridgeMac.idCardClose();
			}
		} catch(e) {}
	},
	// 校园卡阅读器
	dcrfOpen: function(callback) {
		try {
			window.external.Dcrf_Open();
			window.dcrfCallBack = function(value) {
				callback(value);
			};
		} catch(e) {}
	},
	dcrfClose: function() {
		try {
			window.external.Dcrf_Close();
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
			//			window.external.Hd_SsCard_Open();
			//			window.SsCardCallBack = function(value) {
			//				callback(value);
			//			};
			acBridgeMac.ssCardOpen(callback);
		} catch(e) {
		}
	},
	ssCardClose: function() {
		try {
			var this_ = this
			this_.senseSend("O(00,13,0)E")
			//			window.external.Hd_SsCard_Close();
			acBridgeMac.ssCardClose();
		} catch(e) {}
	},
	//居住证制卡
	dataCardCardIn: function() {
		try {
			acBridgeMac.dataCardCardIn();
		} catch(e) {}
	},
	dataCardRead: function(callback) {
		try {
			acBridgeMac.dataCardRead(callback);
		} catch(e) {}
	},
	dataCardPrint: function(top, left, width, height, strHTML, zhuofansoftArray, epointText) {
		try {
			acBridgeMac.dataCardPrint(top, left, width, height, strHTML, zhuofansoftArray, epointText);
		} catch(e) {}
	},
	dataCardCardOut: function() {
		try {
			acBridgeMac.dataCardCardOut();
		} catch(e) {}
	},
	//居住证查询 擦写
	dataCardQuery: function(chidcard, chname, successCallback, errorCallback) {
		try {
			if(this.vendor() == 'wonders') {
				window.external.DataCard_Query(chidcard, chname);
				window.dataCardCallBack = function(data) {
					successCallback(data);
				}
				window.errorCallBack = function(err) {
					errorCallback(err);
				}
			} else if(this.vendor() == 'zhuofansoft') {
				var zfInfo = ABMBusinessDllUtil.checkOnCardRenew(chidcard, chname);
				var zfInfoJson = JSON.parse(zfInfo);
				if(zfInfoJson.success) {
					successCallback(JSON.stringify(zfInfoJson.CardErasableInfoVO));
				} else {
					errorCallback("err");
				}
			} else if(this.vendor() == 'epoint') {
				var XDInfo = window.external.checkOnCardRenew(chidcard, chname);
				var XDinfoJson = JSON.parse(XDInfo);
			    if(XDinfoJson.success) {
			    	successCallback(JSON.stringify(XDinfoJson.CardErasableInfoVO));
			    } else {
					errorCallback("err");
				}
			}

		} catch(e) {}
	},
	dataCardErase: function(chidcard, chname, errorCallback) {
		try {
			if(this.vendor() == 'wonders') {
				window.external.DataCard_Erase(chidcard, chname);
				window.errorCallBack = function(err) {
					errorCallback(err);
				}
			} else if(this.vendor() == 'zhuofansoft') {
				ABMBusinessDllUtil.cardRenewApply(chidcard, chname);
			} else if(this.vendor() == 'epoint') {
				window.external.cardRenewApply(chidcard, chname);
			}

		} catch(e) {}
	},
	openUrlWindow:function(url){
		try {
			if(this.vendor() == 'wonders') {
				window.external.URL_EDGE_OPEN(200, 180, 1500, 700, url);
			} else if(this.vendor() == 'zhuofansoft') {
				InnerChromeBrowserUtil.CreateNewChromeForm(window.screen.availWidth - 280, window.screen.availHeight - 280, 180,180, url, 1)
			} else if(this.vendor() == 'epoint') {
				window.external.RedirectUrl(url);
			}
		} catch(e) {}
	},
	closeUrlWindow:function(){
		try {
			if(this.vendor() == 'wonders') {
				window.external.URL_EDGE_CLOSE();
			} else if(this.vendor() == 'zhuofansoft') {

			} else if(this.vendor() == 'epoint') {
				window.external.CloseRedirectUrl();
			}
		} catch(e) {}
	},
	//医保记录册打印
	medicalPrint: function(newBookBh, newBookKh, userXb, userName, userAge) {
		try {
			if(this.vendor() == 'wonders') {
				//				$.device.serialPortClose();
				//				var bookMedicalPort = "COM" + (window.external.GetConfig('bookMedicalPort') || "4");
				//				$.device.serialPortOpen(bookMedicalPort, 9600, 8, function() {}) //开启串口
				//			confirmWanda = function(bh, kh, xb, name, age) {
				//alert(bh + kh + xb + name + age);
				var bookMedicalName = window.external.GetConfig('medicalName') || "DASCOM DS-7860";
				window.external.Medical_Open();
				window.external.Medical_Move();
				var lodop = $.device.printGetLodop('medical');
				if(bookMedicalName == "DASCOM DS-7860") {
					lodop.SET_PRINTER_INDEX('DASCOM DS-7860');
					lodop.ADD_PRINT_TEXT(230, 160, 670, 50, newBookBh);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(270, 160, 670, 30, userName);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(305, 160, 670, 30, userXb);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(340, 160, 670, 30, newBookKh);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(375, 160, 670, 30, userAge);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.PRINT();
				} else if(bookMedicalName == "DN220P") {
					lodop.SET_PRINTER_INDEX("DN220P");
					lodop.ADD_PRINT_TEXT(160, 140, 670, 50, newBookBh);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(200, 140, 670, 30, userName);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(235, 140, 670, 30, userXb);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(270, 140, 670, 30, newBookKh);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.ADD_PRINT_TEXT(305, 140, 670, 30, userAge);
					lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
					lodop.PRINT();
				}
			} else if(this.vendor() == 'zhuofansoft') {
				//清楚打印机缓存
				var result = PrintVomitBookControl.ClearCache();
				var resultParse = JSON.parse(result);
				if(resultParse.success == false) {
					alert("打印机设置异常，请联系管理员")
				}
				var txt0 = '<div id="notifySheetPrint">';
				var txt1 = '<table cellspacing="0" style="font-size:16px;margin-left: 20%;margin-top: 120px;">';
				var txt2 = '<tr style="line-height: 30px;"><td></td></tr>';
				var txt3 = '<tr style="line-height: 30px;"><td></td></tr>';
				var txt4 = '<tr style="line-height: 30px;"><td></td></tr>';
				var txt5 = '<tr style="line-height: 30px;"><td></td></tr>';
				var txt6 = '<tr style="line-height: 30px;"><td></td></tr>';
				var txt7 = '</table>';
				var txt11 = '<table cellspacing="0" style="font-size:18px;margin-left: 20%;margin-top: 190px;">';
				var txt12 = '<tr style="line-height: 34px;"><td>' + newBookBh + '</td></tr>';
				var txt13 = '<tr style="line-height: 34px;"><td>' + userName + '</td></tr>';
				var txt14 = '<tr style="line-height: 34px;"><td>' + userXb + '</td></tr>';
				var txt15 = '<tr style="line-height: 34px;"><td>' + newBookKh + '</td></tr>';
				var txt16 = '<tr style="line-height: 34px;"><td>' + userAge + '</td></tr>';
				var txt17 = '</table></div>';
				//				var txt = $('#notifySheetPrint').html();
				var txt1 = txt0 + txt1 + txt2 + txt3 + txt4 + txt5 + txt6 + txt7 + txt11 + txt12 + txt13 + txt14 + txt15 + txt16 + txt17;
				txt = '<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head><body>' + '</head><body>' + txt1 + '</body></html>';

				var r = PrintVomitBookControl.PrintHtml(txt);
				setTimeout(function() {
					PrintVomitBookControl.SendEnter();
				}, 0)
			} else if(this.vendor() == 'epoint') {
				var jsondata = {
					"token": "Epoint_WebSerivce_**##0601",
					"params": {
						"bh": newBookBh,
						"xm": userName,
						"xb": userXb,
						"kh": newBookKh,
						"csrq": userAge,
						"centerguid": "ba4ae75c-aa9d-4877-a25c-a71597527ab1"
					}
				}
				var PathUrl = "http://10.81.16.48:8080/epoint-web-zwfwznsb/rest/yibao/getYiBaoDetailDoc";
				$.ajax({
					type: "post",
					url: PathUrl,
					dataType: 'json',
					contentType: 'application/json;charset=utf-8',
					data: JSON.stringify(jsondata),
					timeout: 10000,
					contenttype: "text/json",
					success: function(rtnData) {
						var taskdocattachguid = rtnData.custom.taskdocattachguid;
						window.external.printCardJYC("http://10.81.16.48:8080/epoint-web-zwfwznsb/rest/auditattach/readAttach?attachguid=" + taskdocattachguid);
					},
					error: function(err) {
						alert("err" + err)
					}
				});
			} else if(this.vendor() == 'jhdevice') {
				wgPrint(newBookBh, userName, userXb, newBookKh, userAge, function(val) {
					var wgPrintc = JSON.parse(val).status;
					if(wgPrintc != 1) {
						alert(val);
					}
				})
			}
		} catch(error) {}
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
			//			window.external.CmCapture_Show(width, height, x, y);
			acBridgeMac.cmCaptureShow(width, height, x, y);
		} catch(e) {
		}
	},
	cmCaptureSelectRect: function(x, y, xwidth, height) {
		try {
			//			window.external.CmCapture_SelectRect(x, y, xwidth, height);
			acBridgeMac.cmCaptureSelectRect(x, y, xwidth, height);
		} catch(e) {}
	},
	cmCaptureCaptureImageModel: function(callback) {
		try {
			//			window.external.CmCapture_Capture_ImageModel();
			//			window.cmCaptureImageCallBack = function(value) {
			//				callback(value);
			//			};
			acBridgeMac.cmCaptureCaptureImageModel(callback);
		} catch(e) {}
	},
	cmCaptureCaptureUrl: function(callback) {
		try {
			acBridgeMac.cmCaptureCaptureUrl(callback);
		} catch(e) {}
	},
	cmCaptureCaptureBase64: function(callback) {
		try {
			acBridgeMac.cmCaptureCaptureBase64(callback);
		} catch(e) {}
	},
	cmCaptureHide: function() {
		try {
			acBridgeMac.cmCaptureHide();
		} catch(e) {}
	},
	cmCaptureClose: function() {
		try {
			acBridgeMac.cmCaptureClose();
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
	audioSound: function(type) {
		try {
			var this_ = this;
			if(acBridgeMac.vendor() == 'wonders') {
				//身份证阅读器
				if(type == 'IdCard') {
					window.external.Hd_Audio_PPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\idcard.wav");
				}
				//高拍仪
				else if(type == 'CmCapture') {
					this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\cmCapture.wav");
				}
				//二维码扫码枪
				else if(type == 'QrScanner') {
					this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\citizen.wav");
				}
				//医保记录册打印
				else if(type == 'MedicalInsuranc') {
					this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\print.wav");
				}
				//A4打印机
				else if(type == 'A4Printer') {

				}
				//居住证擦写设备
				else if(type == 'ResidenceErased') {
					this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\residenceVisa.wav");
				} else if(type == "sscard") {
					this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\sscard.wav");
				} else if(type == "Camera") {
					this_.audioPPlay("Realtek High Definition Audio", this_.currentPath() + "\\resources\\audio\\recognition.wav");
				}
			} else if(this.vendor() == 'zhuofansoft') {

			} else if(this.vendor() == 'epoint') {
				//身份证阅读器
				if(type == 'IdCard') {
					window.external.playVoice("1", "身份证");
				}
				//高拍仪
				else if(type == 'CmCapture') {
					window.external.playVoice("1", "高拍仪");
				}
				//二维码扫码枪
				else if(type == 'QrScanner') {
					window.external.playVoice("1", "二维码");
				}
				//医保记录册打印
				else if(type == 'MedicalInsuranc') {
					window.external.playVoice("1", "就医册");
				}
				//A4打印机
				else if(type == 'A4Printer') {
					window.external.playVoice("2", "正在打印,请稍后");
				}
				//居住证擦写设备
				else if(type == 'ResidenceErased') {
					window.external.playVoice("1", "居住证");
				}
				//社保卡
				else if(type == "sscard") {
					window.external.playVoice("1", "社保卡");
				}
				//摄像头
				else if(type == "Camera") {
					window.external.playVoice("1", "摄像头");
				}
			}
		} catch(e) {}
	},
	audioStop: function() {
		try {
			window.external.Hd_Audio_Stop();
		} catch(e) {}
	},
	//文件操作

	fileOpen: function(callback) {
		try {
			window.external.File_Open();
			window.fileCallBack = function(value) {
				callback(value);
			};
		} catch(e) {
			window.external.debug("打开U盘失败");
		}
	},
	fileClose: function() {
		try {
			window.external.File_Close();
		} catch(e) {
			window.external.debug("关闭U盘失败");
		}
	},
	fileBase64: function(filePath, callback) {
		try {
			if(this.vendor() == 'jhdevice') {
				fileBase64(filePath, callback);
			} else if(acBridgeMac.vendor() == 'zhdevice') {
				callback(filePath);
			} else if(acBridgeMac.vendor() == 'nhdevice') {
				callback(filePath);
			} else if(acBridgeMac.vendor() == 'pfdevice') {
				spdb.fileBase64(filePath, callback);
			} else {
				acBridgeMac.fileBase64(filePath, callback);
			}

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
			acBridgeMac.pdfPrint(url);
		} catch(e) {}
	},
	foxitPdf_Print: function(url) {
		try {
			window.external.Office_Url_FoxitPdf_Print(url)
		} catch(e) {}
	},
	urlPdfPrint: function(url, filePath, callback, urlbase64) {
		try {
			var this_ = this
			this_.senseSend("O(00,12,2)E")
			setTimeout(function() {
				this_.senseSend("O(00,12,0)E")
			}, 5000);
			if(acBridgeMac.vendor() == 'jhdevice') {
				if(urlbase64 != undefined) {
					doPrint(urlbase64, "pdf");
				}
			} else if(acBridgeMac.vendor() == 'zhdevice') {
				if(urlbase64 != undefined) {
					acBridgeMac.pdfPrint(urlbase64);
				}
			} else if(acBridgeMac.vendor() == 'nhdevice') {
				if(urlbase64 != undefined) {
					nhdeviceSum.nhpdfPrint(urlbase64);
				}
			} else if(acBridgeMac.vendor() == 'pfdevice') {
				if(urlbase64 != undefined) {
					spdb.pdfPrint(urlbase64);
				}
			} else {
				acBridgeMac.urlPdfPrint(url, filePath, callback);
			}
		} catch(e) {}
	},
	//串口操作
	serialPortOpen: function(portName, baudRate, dataBits, callback) {
		$.log.debug('尝试打开串口...')
		try {
			window.external.SerialPort_Open(portName, baudRate, dataBits);
			window.serialPortCallback = function(value) {
				callback(value);
			};
		} catch(e) {}
	},
	serialPortWrite: function(send, offSet, count) {
		try {
			window.external.SerialPort_Write(send, offSet, count);
		} catch(e) {}
	},
	serialPortWriteString: function(content) {
		try {
			window.external.SerialPort_WriteString(content);
		} catch(e) {}
	},
	serialPortWriteHexStr: function(hexString) {
		try {
			window.external.SerialPort_WriteHexStr(hexString);
		} catch(e) {}
	},
	serialPortClose: function() {
		try {
			window.external.SerialPort_Close();
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
			acBridgeMac.httpUpload(url, fileKey, filePath, keyValueJsonStr, successCallback, errorCallback);
		} catch(e) {}
	},
	httpDownload: function(url, filePath, onProgressChanged, onSuccessCallback, errorCallback) {
		try {
			acBridgeMac.httpDownload(url, filePath, onProgressChanged, onSuccessCallback, errorCallback);
		} catch(e) {}
	},
	printGetLodop: function(type) {
		try {
			var this_ = this
			if(type == 'medical') { //医保打印
				this_.senseSend("O(00,14,2)E")
				setTimeout(function() {
					this_.senseSend("O(00,14,0)E")
				}, 5000);
			} else { //A4打印
				this_.senseSend("O(00,12,2)E")
				setTimeout(function() {
					this_.senseSend("O(00,12,0)E")
				}, 5000);
			}
			return acBridgeMac.getLodop();
		} catch(e) {
			return jQuery.lodop.load();
		}
	},
	//打开程序
	exeOpenAbsolute: function(processName, exePath) {
		try {
			window.external.Exe_Open_Absolute(processName, exePath)
		} catch(e) {}
	},
	exeOpenRelative: function(processName, exePath) {
		try {
			window.external.Exe_Open_Relative(processName, path)
		} catch(e) {}
	}
};
/**
 * 配置信息
 */
jQuery.config = {
	get: function(key) {
		try {
			if(key == "uniqueId" && acBridgeMac.vendor() == "zhdevice") {
				return JSON.parse(window.AppHost.getManage().getMachineInfo())['networkinterface'][0].mac.match(new RegExp("\\w{1," + 2 + "}", "g")).join("-");
			}else if(key == "uniqueId" && acBridgeMac.vendor() == "pfdevice"){
				return spdb.getConfig();
			}else if(key == "uniqueId" && acBridgeMac.vendor() == "jtdevice"){
				return $.deviceBocom.getDeviceMac();
			}else{
			    return window.external.GetConfig(key);
			}
		} catch(e) {}
		return null;
	},
	load: function(key, callback) {
		try {
			if(key == "uniqueId" && acBridgeMac.vendor() == "zhdevice") {
				callback(JSON.parse(window.AppHost.getManage().getMachineInfo())['networkinterface'][0].mac.match(new RegExp("\\w{1," + 2 + "}", "g")).join("-"));
			}else if(key == "uniqueId" && acBridgeMac.vendor() == "pfdevice"){
				callback(spdb.getConfig());
			}else if(key == "uniqueId" && acBridgeMac.vendor() == "jtdevice"){
				callback( $.deviceBocom.getDeviceMac())
			}else{
				acBridgeMac.loadConfig(key, callback);
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
			acBridgeMac.logDebug(debug);
		} catch(e) {
			console.debug(debug);
		}
	},
	info: function(info) {
		try {
			acBridgeMac.logInfo(info);
		} catch(e) {
			console.info(info);
		}
	},
	error: function(error) {
		try {
			acBridgeMac.logError(error);
		} catch(e) {
			console.error(error);
		}
	}
};