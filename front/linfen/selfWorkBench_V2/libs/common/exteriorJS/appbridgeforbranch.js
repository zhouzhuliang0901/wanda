// JavaScript source code

function getdevice(drivertype) {
	if(window.AppHost)
		return AppHost.getdevice(drivertype);
	else
		return null;
}

function getEngagedWebAPI(apiname) {
	if(window.AppHost) {
		return AppHost.getEngagedApi(apiname);
	} else {
		return null;
	}
}

function getManage() {
	if(window.AppHost) {
		return AppHost.getManage();
	} else {
		return null;
	}
}

var Device = function() {
	var self;

	/**
	 * 身份证
	 */
	var idcard = function() {
		this.device = getdevice("idcard");
		console.log("idcard");
	};
	idcard.prototype.reset = function(json) {
		console.log("reset");
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.getIDType = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getIDType(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.readIDCard = function(json) {
		return $.Deferred(function(deferred) {
			this.device.readIDCard(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.ejectIDCard = function(json) {
		return $.Deferred(function(deferred) {
			this.device.ejectIDCard(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.cancelaccept = function(json) {
		return $.Deferred(function(deferred) {
			this.device.cancelaccept(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.getOrgScannerModule = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getOrgScannerModule(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/** 
	 * 激光打印机
	 */
	var hdprint = function() {
		this.device = getdevice("hdprint");
	};
	hdprint.prototype.hdprinttemplate = function(json) {
		return $.Deferred(function(deferred) {
			this.device.hdprinttemplate(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.getprintjobinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getprintjobinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.getpaperboxstatus = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getpaperboxstatus(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/** 
	 * 影像平台
	 */
	var icms = function() {
		this.api = getEngagedWebAPI("icms");
	};
	icms.prototype.savefile2local = function(json) {
		return $.Deferred(function(deferred) {
			this.api.savefile2local(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	icms.prototype.getbase64strfrompic = function(json) {
		return $.Deferred(function(deferred) {
			this.api.getbase64strfrompic(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 活检
	 */
	var faceIRDetect = function() {
		this.device = getdevice("faceirdetect");
	}

	faceIRDetect.prototype.faceDetector = function() {
		var mgr = new Manage;
		var machineInfo = JSON.parse(mgr.getAppData('machineInfo') || "{}");
		var cameraInex = {
			faceCameraIndex: machineInfo.UserCamera || 2,
			infraredCameraIndex: machineInfo.InfraredCamera || 0,
			irfaceQualityScore: machineInfo.irfaceQualityScore || 0.55,
			irfaceMinQualityScore: machineInfo.irfaceMinQualityScore || 0.45,
			culture:'zh-CN'
		};
		return $.Deferred(function(deferred) {
			this.device.faceDetector(JSON.stringify(cameraInex), function(info) {
				var retJson = JSON.parse(info);
				if(retJson.Result) {
					// 调用成功，获取人脸Base64字符串
					var api = new icms;
					api.getbase64strfrompic({
						PicName: retJson.FileName,
						IsFullPath: true
					}).always(function(data) {
						if(data && data.code == 1) {
							setTimeout(() => {
								deferred.resolve({
									success: true,
									msg: "操作成功",
									obj: {
										faceImage: data.FileContent
									}
								})
							}, 50);
						} else {
							setTimeout(() => {
								deferred.reject({
									success: false,
									msg: data.msg,
									obj: {}
								});
							}, 50);
						}
					});
				} else {
					// 失败
					setTimeout(() => {
						deferred.reject({
							success: false,
							msg: retJson.Msg,
							obj: {}
						});
					}, 50);
				}
			});
		}.bind(this)).promise();
	};

	/**
	 * 签字版
	 */
	var signtablet = function() {
		this.device = getdevice("signtablet");
	};
	signtablet.prototype.sign = function(json) {
		var deferred = $.Deferred(function(deferred) {
			this.device.call(JSON.stringify(json), function(info) { //done
				var msg_json = JSON.parse(info);
				if(msg_json.result == "ok") {
					setTimeout(function() {
						deferred.resolve(JSON.parse(info))
					}, 50);
				} else if(msg_json.result == "cancel") {
					setTimeout(function() {
						deferred.reject(JSON.parse(info))
					}, 50);
				}
			}, function(state) { //progress
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) { //fail
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this));

		return deferred.promise();
	};

	/** 
	 * 打开uwp窗口
	 */
	var uwpbridge = function() {
		this.device = getdevice("uwpbridge");
	};
	uwpbridge.prototype.showuwpform = function(json) {
		return $.Deferred(function(deferred) {
			this.device.showuwpform(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 凭条打印
	 */
	var print = function() {
		this.device = getdevice("print");
	};
	print.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	print.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	print.prototype.printform = function(json) {
		return $.Deferred(function(deferred) {
			this.device.printform(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	print.prototype.getmediastatus = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getmediastatus(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 条码扫描
	 */
	var barcode = function() {
		this.device = getdevice("barcode");
		console.log('barcode');
	};
	barcode.prototype.readBarcode = function(json) {
		return $.Deferred(function(deferred) {
			this.device.readBarcode(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	barcode.prototype.cancelRead = function(json) {
		return $.Deferred(function(deferred) {
			this.device.cancelRead(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	barcode.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	barcode.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	// 第三方网站
	var customwebview = function() {
		this.device = getdevice("customwebview");
	};
	customwebview.prototype.navigateToCustPage = function(json) {
		this.device.navigateToCustPage(JSON.stringify(json));
	};
	customwebview.prototype.closeCustPage = function() {
		this.device.closeCustPage();
	};

	/**
	 * 通用
	 */
	var Manage = function() {
		this.manage = getManage();
	};
	Manage.prototype.getAppData = function(key) {
		if(window.AppHost) {
			return this.manage.getAppData(key);
		}
		return "";
	};
	Manage.prototype.file2local = function(json) {
		return $.Deferred(function(deferred) {
			this.manage.file2local(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	Manage.prototype.getMachineInfo = function() {
		if(window.AppHost) {
			return JSON.parse(this.manage.getMachineInfo());
		}
		return "";
	};
	Manage.prototype.pdf2img = function(json) {
		return $.Deferred(function(deferred) {
			this.manage.pdf2img(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	Manage.prototype.pdfToimgByContent = function(json) {
		return $.Deferred(function(deferred) {
			this.manage.pdfToimgByContent(JSON.stringify(json), function(info) {
				setTimeout(function() {
					var retJson = JSON.parse(info);
					var imgInfo = {
						success: true,
						msg: '转换成功',
						obj: {}
					};

					var imgList = [];
					for(var ix = 0; ix < retJson.imgcount; ix++) {
						imgList.push(retJson.imgs[ix].img);
					}
					imgInfo.obj.imgList = imgList;

					deferred.resolve(imgInfo);
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					var errObj = JSON.parse(err);
					deferred.reject({
						success: false,
						msg: errObj.msg
					});
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 指示灯
	 */
	var siu = function() {
		this.device = getdevice("siu");
	};
	siu.prototype.controlGuideLightSync = function(json) {
		return $.Deferred(function(deferred) {
			this.device.controlGuideLightSync(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	siu.prototype.closeAllSync = function(json) {
		return $.Deferred(function(deferred) {
			this.device.closeAllSync(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	return {
		idcard: idcard,
		hdprint: hdprint,
		icms: icms,
		faceIRDetect: faceIRDetect,
		signtablet: signtablet,
		uwpbridge: uwpbridge,
		print: print,
		manage: Manage,
		customwebview: customwebview,
		siu: siu,
		barcode: barcode
	};
};

var JSBridge = {
	Device: Device()
};

/////////////////////////////////////////////////////////////////////
// vkbsettings.js
// 虚拟键盘
/////////////////////////////////////////////////////////////////////

var touchkbIsShow = false;

function openTouchKeyboard(kbLayout) {
	if(window.AppHost) {
		try {
			touchkbIsShow = true;
			vkbService.vkIsShow = true; //ggz
			window.AppHost.getLogHelper().log("show touch keyboard  " + touchkbIsShow);
			window.AppHost.openTouchKeyboard(kbLayout);
		} catch(e) {
			window.AppHost.getLogHelper().error("openTouchKeyboard error " + kbLayout + JSON.stringify(e));
		}
	}
}

function closeTouchKeyboard() {
	if(window.AppHost) {
		window.AppHost.getLogHelper().log("close touch keyboard");
		window.AppHost.closeTouchKeyboard();
		touchkbIsShow = false; //ggz
		vkbService.vkIsShow = false; //ggz
	}
}
var lastFocus = null;
var lastFocusEle = null;

function handleTouchKeyboard(event) {
	deal();
}

function deal() {
	var ele = event.target;
	window.AppHost.getLogHelper().log("touchkbIsShow " + touchkbIsShow);
	if(ele.tagName === "INPUT" || ele.tagName === "TEXTAREA") {
		event.stopPropagation();
		window.AppHost.getLogHelper().log(ele.tagName + " trigger handleTouchKeyboard");
		var isReadonly = ele.hasAttribute("readonly");
		var eleType = ele.getAttribute("type");
		var kbLayout = ele.getAttribute("kb-layout") || "cn";
		if(isReadonly === false &&
			(eleType === null ||
				eleType.toUpperCase() === "TEXT" ||
				eleType.trim() === ""
			)) {
			if(touchkbIsShow === false) {
				let now = Date.now();
				var timer = setTimeout(function() {
					clearTimeout(timer);
					if(lastFocus == null ||
						(lastFocus != null &&
							lastFocusEle === event.target &&
							now - lastFocus > 1000)
					) {
						if(ele === document.activeElement) {
							document.body.style.overflowY = "auto";
							openTouchKeyboard(kbLayout);
							// var timer2 = setTimeout(function () {
							//     clearTimeout(timer2);
							//     ele.focus();
							//     if (!isElementVisible(ele))
							//         ele.scrollIntoView(true);
							// }, 200);
						}
					} else {
						lastFocus = now;
						lastFocusEle = event.target;
					}

				}, 200);

			}
		} else {
			closeTouchKeyboard();
		}
	} else {
		closeTouchKeyboard();
	}
}

function handleInputClick() {
	if(!touchkbIsShow) {
		deal();
	}
}
//ggz
function handleBodyClick(e) {
	var activeElement = $(document.activeElement);
	window.AppHost.getLogHelper().log("activeElement:" + activeElement[0]);
	window.AppHost.getLogHelper().log("activeElement isReadonly:" + activeElement.attr("readonly"));
	window.AppHost.getLogHelper().log("activeElement:" + activeElement[0].toString());

	if(activeElement[0].toString() == "[object HTMLInputElement]" || activeElement[0].toString() == "[object HTMLTextAreaElement]") {
		window.AppHost.getLogHelper().log("HTMLInputElement||HTMLTextAreaElement");
	} else {
		//Close Virtual Keyboard
		if(window.AppHost) {
			if(vkbService.vkIsShow == true) {
				closeTouchKeyboard();
				onKeyboardClosed();
			}
		}
	}
}
//C# 关闭键盘回调
function KeyboardClosedCallback() {
	window.AppHost.getLogHelper().log("close touch keyboard");
	touchkbIsShow = false; //ggz
	vkbService.vkIsShow = false; //ggz
	onKeyboardClosed();
}
//ggz end
function onKeyboardClosed() {
	window.AppHost.getLogHelper().log("onKeyboardClosed");
	touchkbIsShow = false;
	vkbService.vkIsShow = false; //ggz
	document.body.style.overflowY = "hidden";
}

function setKbStatus(status) {
	sessionStorage.setItem("_uwpKbStatus_", status);
}

function enableTouchKbService() {
	try {
		window.AppHost.enableTouchKbService();
	} catch(ex) {
		window.AppHost.getLogHelper().error("changeUwpServicesStatus failed," + ex);
	}
}

function disableKb(showMessage) {
	$("body").off("focus", "input", handleTouchKeyboard);
	$("body").off("click", "input", handleInputClick);
	$("body").off("focus", "textarea", handleTouchKeyboard);
	window.onKeyboardClosed = null;
}

function getKbStatus(status) {
	if(window.AppHost) {
		return window.AppHost.getKbStatus();
	} else {
		return false;
	}
}

function isElementVisible(el) {
	var rect = el.getBoundingClientRect(),
		vWidth = window.innerWidth || doc.documentElement.clientWidth,
		vHeight = window.innerHeight || doc.documentElement.clientHeight,
		efp = function(x, y) {
			return document.elementFromPoint(x, y)
		};

	// Return false if it's not in the viewport
	if(rect.right < 0 || rect.bottom < 0 ||
		rect.left > vWidth || rect.top > vHeight)
		return false;

	// Return true if any of its four corners are visible
	return(
		el.contains(efp(rect.left, rect.top)) ||
		el.contains(efp(rect.right, rect.top)) ||
		el.contains(efp(rect.right, rect.bottom)) ||
		el.contains(efp(rect.left, rect.bottom))
	);
}

function isCoverByKb(el) {
	var wHeight = window.innerHeight,
		eHeight = el.clientHeight,
		rect = el.getBoundingClientRect(),
		eTop = parseInt(rect.top);
	if(eTop + eHeight > wHeight)
		return true;
	else return false;

}

function onSizeChange() {
	window.AppHost.getLogHelper().log("onSizeChange");
	var el = document.activeElement;
	if(el) {
		setTimeout(function() {
			if(isCoverByKb(el)) {
				el.scrollIntoView(true);
			}
		}, 100);
	}
}
var vkbService = {
	vkIsShow: false, //ggz
	Disable: function() {
		//ggz
		try {
			enableTouchKbService();
			disableKb();
			window.onresize = null;
			window.AppHost.getLogHelper().log("切换系统键盘成功");
		} catch(e) {
			window.AppHost.getLogHelper().error("切换为系统键盘失败");
		}

		//ggz end
	},
	init: function() {
		try {
			var kbStatus = getKbStatus();
			if(kbStatus) {
				$("body").on("focus", "input", function(){
					if($(this).attr("readonly")=="readonly")return;
					handleTouchKeyboard();
				});
				$("body").on("click", "input", handleInputClick);
				$("body").on("focus", "textarea", handleTouchKeyboard);
				//ggz
				$("body").on("click", handleBodyClick);
				window.KeyboardClosedCallback = KeyboardClosedCallback;
				//ggz end
				window.onKeyboardClosed = onKeyboardClosed;
				window.onresize = onSizeChange;
			}
		} catch(e) {
			window.AppHost.getLogHelper().error("init uwp keyboard failed,error:" + JSON.stringify(e));
		}
	},
}

// 初始化键盘
vkbService.init();

function winGetdevInfo() {
	try {
		var nInfo = JSON.parse(window.AppHost.getManage().getAppData('machineInfo')).MachineId;
		if(nInfo.length == 8) {
			window.AppHostzh = "zhdevice";
		}
	} catch(e) {
		//TODO handle the exception
	}
}
winGetdevInfo();