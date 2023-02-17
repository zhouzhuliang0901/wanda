var scriptfaceinfo = document.createElement('script');
scriptfaceinfo.src = "../libs/common/exteriorJS/nh-hardware-js/facejs/faceinfo.js";
document.getElementsByTagName('body')[0].appendChild(scriptfaceinfo);
var scriptdetect = document.createElement('script');
scriptdetect.src = "../libs/common/exteriorJS/nh-hardware-js/facejs/detect.js";
document.getElementsByTagName('body')[0].appendChild(scriptdetect);
var scriptcommon = document.createElement('script');
scriptcommon.src = "../libs/common/exteriorJS/nh-hardware-js/facejs/common.js";
document.getElementsByTagName('body')[0].appendChild(scriptcommon);
var scriptjson2 = document.createElement('script');
scriptjson2.src = "../libs/common/exteriorJS/nh-hardware-js/facejs/json2.js";
document.getElementsByTagName('body')[0].appendChild(scriptjson2);


//定义包  农行 依赖jq


var nhdeviceSum = {
	// 身份证阅读器
	nhidCardOpen: function(callback) {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/idcard",
				dataType: "json",
				data: {
					timeout: 30
				},
				success: function(data) {
					if (data.success == true) {
						var c = {};
						var a = data.obj;
						var s, e;
						c.Name = a.name;
						c.Sex = a.sex;
						c.Number = a.idcode;
						c.Address = a.addr;
						c.CardImagePath = a.imageStr;
						c.People = a.nation;
						c.Birthday = a.birthday;
						s = a.startDate;
						c.ValidtermOfStart = s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s
							.substring(6, 8);
						e = a.endDate;
						c.ValidtermOfEnd = e.substring(0, 4) + "-" + e.substring(4, 6) + "-" + e
							.substring(6, 8);
						callback(JSON.stringify(c))
					} else {
						alert(JSON.stringify(data));
					}
				},
				error: function(err) {
					alert(JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	//停止身份证读取
	nhidCardClose: function() {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/stopIdcard",
				dataType: "json",
				data: {},
				success: function(data) {
					console.log(data);
				},
				error: function(err) {
					alert(JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	//二维码打开
	nhqrCodeOpen: function(callback) {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/barCodeScan",
				dataType: "json",
				data: {
					timeout: 30
				},
				success: function(data) {
					if (data.success == true) {
						callback(data.obj.content)
					} else {
						alert(JSON.stringify(data));
					}
				},
				error: function(err) {
					alert(JSON.stringify(err));
				},
			});
		} catch (e) {

		}
	},
	//关闭二维码
	nhqrCodeClose: function() {
		try {
			//			$.ajax({
			//				type: "get",
			//				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/cancelBarCodeScan",
			//				dataType: "json",
			//				data: {},
			//				success: function(data) {
			//					console.log(data);
			//				},
			//				error: function(err) {
			//					alert(JSON.stringify(err));
			//				},
			//			});
		} catch (e) {}
	},
	//pdf打印
	nhpdfPrint: function(fileBase64) {
		try {
			var paramData = JSON.stringify({
				"tranType": 1,
				"fileExtent": "pdf",
				"fileContent": fileBase64
			});
			$.ajax({
				type: "POST",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/printNew",
				dataType: "json",
				data: paramData,
				success: function(data) {
					console.log(data);
					//alert(JSON.stringify(data));
				},
				error: function(err) {
					alert(JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	//Mac
	nhgetMac: function(callback) {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/devInfo",
				dataType: "json",
				data: {},
				success: function(data) {
					if (data.success == true) {
						callback(data.obj.mac.match(new RegExp("\\w{1," + 2 + "}", "g")).join("-"));
					}
				},
				error: function(err) {
					alert(JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	//开启软键盘
	getIME: function() {
		try {
			let myShell = new ActiveXObject("wscript.shell");

			if (myShell) {
				console.info("Creat WScript.Shell object success.");
				// let isSuccess = myShell.run("osk.exe",0,true);
				// console.info("Result:" + isSuccess);
				let path = "C:\\Program\" \"Files\\Common\" \"Files\\microsoft\" \"shared\\ink\\TabTip.exe"
				console.info(path);
				let command = "C:\\Windows\\System32\\cmd.exe /c start " + path;

				myShell.run(command, 0, true);
			} else {
				console.error("Create WScript.Shell object fail.");
			}

			// location.replace("tabkey:"); 
		} catch (exp) {
			console.error(exp);
		}
	},
	//关闭软键盘
	closeIME: function(callback) {
		try {
			let myShell = new ActiveXObject("WScript.Shell");

			if (myShell) {
				console.info("Creat WScript.Shell object success.");

				// let isSuccess = myShell.run("wmic process where name='osk.exe' delete",0);
				let isSuccess = myShell.run("wmic process where name='tabtip.exe' delete", 0);

				console.info("Result:" + isSuccess);
				callback(isSuccess);
			} else {
				console.error("Create WScript.Shell object fail.");
			}
		} catch (exp) {
			console.error(exp);
		}
	},
	// 二期新增
	sleftclose: function() {
		try {
			$.ajax({
				type: "POST",
				url: "http://127.0.0.1:7999/exe/sleftclose",
				dataType: "json",
				data: {},
				success: function(data) {
					// alert("退出APPsuccess==" + JSON.stringify(data));
				},
				error: function(err) {
					alert("退出APPerr==" + JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	nhGoHome: function() {
		try {
			$.ajax({
				type: "POST",
				url: "http://127.0.0.1:7999/exe/goHome",
				dataType: "json",
				data: {},
				success: function(data) {
					// alert("退出APPsuccess==" + JSON.stringify(data));
					if(data.url){
						location.href=data.url;
					}
				},
				error: function(err) {
					alert("退出APPerr==" + JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	getFaceImage: function(callback) {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/getFaceImage",
				dataType: "json",
				data: {},
				success: function(data) {
					callback(data.Data)
				},
				error: function(err) {
					alert("打开摄像头err==" + JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	Phaseiidevice: function() {
		try {
			return 0 < window.navigator.userAgent.indexOf("QtWebEngine")
		} catch (e) {
			return false
		}
	},
	PhaseiidevInfo: function(callback) {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/devInfo",
				dataType: "json",
				data: {},
				success: function(data) {
					console.log(data);
					if (data.success == true) {
						callback(data.obj);
					}
				},
				error: function(err) {
					// alert(JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	nhopenKeyBoard: function() {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/openKeyBoard",
				dataType: "json",
				data: {},
				success: function(data) {
					// alert("退出APPsuccess==" + JSON.stringify(data));
				},
				error: function(err) {
					alert("调用openKeyBoard失败===" + JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	// 打开高拍仪
	nhopenSnCamera: function(width, height, x, y) {
		try{
			if(width==undefined){
				width=550;
				height=370;
				x=335;
				y=285;
			}
		}catch(e){}
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/openSnCamera",
				dataType: "json",
				data: {
					"snCameraX": x,
					"snCameraY": y,
					"snCameraH": height,
					"snCameraW": width,
				},
				success: function(data) {
					console.log(data);
				},
				error: function(err) {
					alert("打开高拍仪err==" + JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	// 高拍仪拍照
	nhsnCameraBeginSna: function(callback) {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/snCameraBeginSna",
				dataType: "json",
				data: {},
				success: function(data) {
					console.log(data);
					callback(data.Data)
				},
				error: function(err) {
					alert("高拍仪拍照err==" + JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	// 高拍仪关闭
	nhcloseSnCamera: function() {
		try {
			$.ajax({
				type: "get",
				url: "http://127.0.0.1:7999/inteGration/hardwareControl/api/closeSnCamera",
				dataType: "json",
				data: {},
				success: function(data) {
					console.log(data);
				},
				error: function(err) {
					alert("高拍仪关闭err==" + JSON.stringify(err));
				},
			});
		} catch (e) {}
	},
	getConfigDataInfo: {} //储存事项全局变量
};
// 获取储存配置项信息
try {
	if (nhdeviceSum.Phaseiidevice()) {
		nhdeviceSum.getConfigDataInfo.prov = '09'; //默认上海09
		nhdeviceSum.getConfigDataInfo.isShowLxmPrint = 'Y';
		nhdeviceSum.PhaseiidevInfo(function(data) {
			nhdeviceSum.getConfigDataInfo.prov = data.prov;
			nhdeviceSum.getConfigDataInfo.isShowLxmPrint = data.isShowLxmPrint;
			// nhdeviceSum.getConfigDataInfo.url=data.url;
			// nhdeviceSum.getConfigDataInfo.mac=data.mac;
		})
	}
} catch (e) {}
window.addEventListener("click", function(event) {
	if ((event.target.localName == 'input') || (event.target.localName == 'textarea')) {
		if (nhdeviceSum.Phaseiidevice()) {
			nhdeviceSum.nhopenKeyBoard();
		} else {
			nhdeviceSum.getIME();
		}
	} else {}
});
