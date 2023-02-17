/**
 * 打印工具类
 */
jQuery.jatools = {
	init : function() {
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
	print : function(options) {
		var defaults = {
			settings : {
				topMargin : 1,
				leftMargin : 1,
				bottomMargin : 1,
				rightMargin : 1
			}, // 设置上下左距页边距为10毫米，注意，单位是 1/10毫米
			documents : document,
			classesReplacedWhenPrint : new Array(
					'.only_for_print{display:block}'),
			copyrights : '杰创软件拥有版权  www.jatools.com'
		};
		var myDoc = $.extend(defaults, options);
		document.getElementById("jatoolsPrinter").print(myDoc, false);
	},
	printPreview : function(options) {
		var defaults = {
			settings : {
				topMargin : 1,
				leftMargin : 1,
				bottomMargin : 1,
				rightMargin : 1
			}, // 设置上下左距页边距为10毫米，注意，单位是 1/10毫米
			documents : document,
			classesReplacedWhenPrint : new Array(
					'.only_for_print{display:block}'),
			copyrights : '杰创软件拥有版权  www.jatools.com'
		};
		var myDoc = $.extend(defaults, options);
		document.getElementById("jatoolsPrinter").printPreview(myDoc, false);
	}
};
/**
 * 打印工具类Lodop
 */
jQuery.lodop = {
	load : function() {
		var CreatedOKLodop7766 = null;
		var strHtmInstall = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
		var strHtmUpdate = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
		var strHtm64_Install = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
		var strHtm64_Update = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
		var strHtmFireFox = "<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
		var strHtmChrome = "<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
		var LODOP;
		try {
			// =====判断浏览器类型:===============
			var isIE = (navigator.userAgent.indexOf('MSIE') >= 0)
					|| (navigator.userAgent.indexOf('Trident') >= 0);
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
			if ((LODOP == null) || (typeof (LODOP.VERSION) == "undefined")) {
				if (navigator.userAgent.indexOf('Chrome') >= 0)
					document.documentElement.innerHTML = strHtmChrome
							+ document.documentElement.innerHTML;
				if (navigator.userAgent.indexOf('Firefox') >= 0)
					document.documentElement.innerHTML = strHtmFireFox
							+ document.documentElement.innerHTML;
				if (is64IE)
					document.write(strHtm64_Install);
				else if (isIE)
					document.write(strHtmInstall);
				else
					document.documentElement.innerHTML = strHtmInstall
							+ document.documentElement.innerHTML;
				return LODOP;
			} else if (LODOP.VERSION < "6.1.8.7") {
				if (is64IE)
					document.write(strHtm64_Update);
				else if (isIE)
					document.write(strHtmUpdate);
				else
					document.documentElement.innerHTML = strHtmUpdate
							+ document.documentElement.innerHTML;
				return LODOP;
			}
			// =====如下空白位置适合调用统一功能(如注册码、语言选择等):====

			// ============================================================
			return LODOP;
		} catch (err) {
			if (is64IE)
				document.documentElement.innerHTML = "Error:"
						+ strHtm64_Install + document.documentElement.innerHTML;
			else
				document.documentElement.innerHTML = "Error:" + strHtmInstall
						+ document.documentElement.innerHTML;
		}
		return LODOP;
	}
};
/**
 * 设备帮助类
 */
jQuery.device = {

	// 二维码
	qrCodeOpen : function(callback) {
		try {
			window.external.Hd_QrScanner_Open();
			window.qrScannerCallBack = function(value) {
				callback(value);
			};
		} catch (e) {
		}
	},
	qrCodeClose : function() {
		try {
			window.external.Hd_QrScanner_Close();
		} catch (e) {
		}
	},

	// 身份证阅读器
	idCardOpen : function(callback) {
		try {
			window.external.Hd_IdCard_Open();
			window.idCardCallBack = function(value) {
				callback(value);
			};
		} catch (e) {
		}
	},
	idCardClose : function() {
		try {
			window.external.Hd_IdCard_Close();
		} catch (e) {
		}
	}
};
/**
 * 日志帮助类
 */
jQuery.log = {
	debug : function(debug) {
		try {
			window.external.Log_Debug(debug);
		} catch (e) {
			console.debug(debug);
		}
	},
	info : function(info) {
		try {
			window.external.Log_Info(info);
		} catch (e) {
			console.info(info);
		}
	},
	error : function(error) {
		try {
			window.external.Log_Error(error);
		} catch (e) {
			console.error(error);
		}
	}
};
