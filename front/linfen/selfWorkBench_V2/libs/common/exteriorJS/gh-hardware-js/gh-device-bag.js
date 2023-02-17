//js调用
var objectDumpException = document.createElement("object"); //硬件调用ocx
objectDumpException.setAttribute("id", "DumpException");
objectDumpException.setAttribute("type", "application/x-oleobject");
objectDumpException.setAttribute("style", "LEFT: 0px; TOP: 0px; display:none");
objectDumpException.setAttribute("classid", "CLSID:00F003A2-DA0E-4252-B330-1E1F2F9BEA96");
document.body.appendChild(objectDumpException);

/**
 * 身份证初始化
 */
function idcardInfoRefine(info) {
	try {
		var data = info,
			c = {},
			p, s, e;
		data = data.replace(new RegExp("=", "g"), '":"');
		data = data.replace(new RegExp("\\|", "g"), '","');
		data = '{"' + data + '"}';
		data = JSON.parse(data);
		c.Name = data.Name;
		c.Sex = data.Sex;
		c.Address = data.Address;
		c.Number = data.IDCardNo;
		c.CardImagePath = data.PhotoFileName;
		p = data.Nation;
		c.People = p.substring(0, 4) + "-" + p.substring(4, 6) + "-" + p.substring(6, 8);
		c.Birthday = data.Bom;
		s = data.UserLifeBegin;
		c.ValidtermOfStart = s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8);
		e = data.UserLifeEnd;
		c.ValidtermOfEnd = e.substring(0, 4) + "-" + e.substring(4, 6) + "-" + e.substring(6, 8);
		return JSON.stringify(c);
	} catch(e) {
		return JSON.stringify("");
	}
}

function ghWinPathtoBase64(filePath, callback) {
	try {
		var lpBuffer = '{\"ImagePath\":\"' + filePath + '"}';
		var devprocess = document.getElementById("devprocess");
		var base64Data1 = devprocess.ExecuteJsonSync("_Image2Code", lpBuffer);
		if(base64Data1) {
			base64Data1 = base64Data1.replace("(", "");
			base64Data1 = base64Data1.replace(")", "");
			base64Data1 = JSON.parse(base64Data1).param;
			base64Data1 = JSON.parse(base64Data1).Data;
			callback(base64Data1);
		} else {
			callback("");
		}
	} catch(e) {}
}
window.onload = function() {
	InitWindow();
}

function InitWindow() {
	try {
		DumpException.StartCatch();
	} catch(e) {}
	show("建立连接");
}

function show(str) {
	//$(".infolog").append("<br>" + str);
	console.log(str);
}
try {
	idcard.onEvent = function(cmd, args) {
		switch(cmd) {
			case 'OpenConnectionOver':
				show("open ok!");
				AcceptAndReadTracks();
				break;
			case 'AcceptAndReadTracksOver':
				show("卡读到FORM或者磁道数据");
				var status = eval(args).chipdata.status;
				if(status == "DATAOK") {
					show(eval(args).chipdata.datas);
					ghidCardCallBack(idcardInfoRefine(eval(args).chipdata.datas));
				} else {
					show("读取身份证信息失败!");
				}
				show("读取完毕！");
				closedevice();
				break;
			case 'ResetOver':
				show("复位ok");
				break;
			case 'EjectOver':
				show("退卡OK!");
				break;
			case 'CardAccepted':
				show("读卡器读取到数据!");
				break;
			case 'CardInvalid':
				show("检测到非法磁道数据!");
				break;
			case 'CardAcceptCancelled':
				show("异步进卡被取消!");
				break;
			case 'CardTaken':
				show("卡片被取走!");
				break;
			case 'CloseConnectionOver':
				show("close ok!");
				break;
			case 'DeviceError':
				show("执行:" + args.cmdName + "出错" + args.errorcode);
				closedevice();
				break;
			case 'CardInserted':
				show("有卡插入!");
				break;
			case 'Timeout':
				show("操作" + args.cmdName + "超时");
				break;
			case 'ErrorInfoReceived':
				show("系统硬件错误:" + args);
				break;
			case 'StatusChanged':
				show("状态改变" + args.newStatus);
				break;
			default:
				show('cmd :' + cmd + ' args :' + args);
				break;
		}
	}
} catch(e) {}
/**
 * 打开设备
 */
function opendev() {
	InitWindow();
	show("正在连接设备－－－－－－－－");
	show("ServiceName:" + idcard.getAttribute('ServiceName'));
	idcard.OpenConnection('IDCardReader310', 0);
}

/**
 * 读取证件
 */
function AcceptAndReadTracks() {
	show("Reading......");
	idcard.setAttribute('StReadDataType', 0);
	var track = 776;
	var timeout = 0;
	idcard.AcceptAndReadTracks(track, timeout);

}

function closedevice() {
	show("正在关闭设备－－－－－－－－");
	idcard.CloseConnection();
}
//二维码
try {
	barcode.onEvent = function(cmd, args) {
		switch(cmd) {
			case 'OpenConnectionOver':
				show("打开设备 ok!");
				break;
			case 'CloseConnectionOver':
				show("关闭设备 ok!");
				break;
			case 'ScanBarcodeCancelled':
				show("cancelread ok!");
				break;
			case 'ScanBarcodeOver':
				show("条码扫描结果:" + eval(eval(args)[0]).BarcodeData);
				ghqrScannerCallBack(eval(eval(args)[0]).BarcodeData);
				break;
			case 'ResetOver':
				show("复位成功");
				break;
			case 'ResetOverSync':
				show("复位成功");
				break;
			case 'DeviceError':
				show("执行:" + args.cmdName + "出错" + args.errorcode);
				break;
			case 'Timeout':
				show("操作" + args.cmdName + "超时");
				break;
			case 'ErrorInfoReceived':
				show("系统硬件错误:" + args);
				break;
			case 'StatusChanged':
				show("状态改变" + args.newStatus);
				break;
			default:
				show('cmd :' + cmd + ' args :' + args);
				break;
		}
	}
} catch(e) {}

function opendevicebarcode() {
	show("正在打开条码扫描仪－－－－－－－－");
	barcode.OpenConnection('BarcodeReader', 0);
	show("ServiceName:" + barcode.getAttribute('ServiceName'));
}

function readcode() {
	InitWindow();
	opendevicebarcode();
	show("正在读条码－－－－－－－－");
	setTimeout(function() {
		barcode.ScanBarcode(0, 0);
	}, 1000)
}

function Cancelread() {
	show("正在取消扫描条码－－－－－－－－");
	barcode.CancelScan();

}

function closedevicebarcodegh() {
	show("正在关闭条码扫描仪－－－－－－－－");
	barcode.CloseConnection(); //关闭要重新连接
}
//拍照
try {
	camera.onEvent = function(cmd, args) {
		switch(cmd) {
			case 'OpenConnectionOver':
				show("打开设备 ok!");
				break;
			case 'CloseConnectionOver':
				show("关闭设备 ok!");
				break;
			case 'SetVideoDisplayOver':
				show("设置图像显示完成!");
				break;
			case 'TakePictureOver':
				show("拍照完成!");
				//document.getElementById("pic").src = "c:\\1.jpg?" + Math.random();
				break;
			case 'RecordMediaStatusChanged':
				show("记录媒介状态改变");
				break;
			case 'ResetOver':
				show("复位成功");
				break;
			case 'ResetOverSync':
				show("复位成功");
				break;
			case 'DeviceError':
				show("执行:" + args.cmdName + "出错" + args.errorcode);
				break;
			case 'Timeout':
				show("操作" + args.cmdName + "超时");
				break;
			case 'ErrorInfoReceived':
				show("系统硬件错误:" + args);
				break;
			case 'StatusChanged':
				show("状态改变" + args.newStatus);
				break;
			default:
				show('cmd :' + cmd + ' args :' + args);
				break;

		}
	}
} catch(e) {}

function opendevicecamera() {
	show("正在打开摄像头－－－－－－－－");
	camera.OpenConnection('Camera310', 0);
	show("ServiceName:" + camera.getAttribute('ServiceName'));
}

var iwidth = 600;
var iheight = 400;
var itop = 300;
var ileft = 600;

function SetProperty() {
	InitWindow();
	opendevicecamera();
	show("设置窗口...");
	var st = "({width:'" + iwidth + "',height:'" + iheight + "',top:'" + itop + "',left:'" + ileft + "'})";
	show(st);
	camera.updateOcxStlye(st);
}

function suspend() {
	show("---开始暂停窗口---");
	var result = camera.SetVideoDisplay(0, 2, iwidth, iheight, ileft, itop); //暂停窗口显示
	show("result:" + result);
}

function starttakepicture(callback) {
	show("开始拍照-----------------------");
	camera.SetVideoDisplay(0, 2, iwidth, iheight, ileft, itop); //暂停窗口显示
	var result = camera.TakePicture(0, "c:\\\\1.jpg", 8);
	if(result == 0) {
		callback("c:\\1.jpg");
	} else {
		callback('')
	}
	show("result:" + result);
	try{
		console.log("工行置顶state")
		var objghzd = document.createElement("object"); //mac 判读设备标识
		objghzd.setAttribute("id", "devprocess");
		objghzd.setAttribute("width", "0");
		objghzd.setAttribute("height", "0");
		objghzd.setAttribute("classid", "clsid:1A171B4F-2C69-4FB7-99B1-9D934839DC27");
		document.body.appendChild(objghzd);
		var ret = objghzd["executeSync"]("","WindowsIsTop","windowName=Internet Explorer&isTop=true");
		console.info(ret);
		console.log("工行置顶end")
	}catch(e){}

}

function redisplay() {
	show("-------开始恢复窗口-----");
	var result = camera.SetVideoDisplay(0, 3, iwidth, iheight, ileft, itop); //恢复显示
	show("result:" + result);
}

function windowdisplay() {
	show("开始显示窗口------------------------");
	var result = camera.SetVideoDisplay(0, 0, iwidth, iheight, ileft, itop);
	show("result:" + result);
}

function closewindow() {
	show("开始关闭窗口------------------");
	var result = camera.SetVideoDisplay(0, 1, iwidth, iheight, ileft, itop);
	show("result:" + result);
}

function getstatus() {
	show("正在获取摄像头状态－－－－－－－－");
	show("设备状态StDeviceStatus:" + camera.getAttribute('StDeviceStatus'));
}

function closedevice1() {
	show("正在关闭摄像头－－－－－－－－");
	closewindow();
	setTimeout(function(){
		camera.CloseConnection();
	},1000);
	setTimeout(function(){
		var st = "({width:'" + 0 + "',height:'" + 0 + "',top:'" + 0 + "',left:'" + 0 + "'})";
	    camera.updateOcxStlye(st);
	},1200);
	try{
		console.log("工行置顶state")
		var objghzd = document.createElement("object"); //mac 判读设备标识
		objghzd.setAttribute("id", "devprocess");
		objghzd.setAttribute("width", "0");
		objghzd.setAttribute("height", "0");
		objghzd.setAttribute("classid", "clsid:1A171B4F-2C69-4FB7-99B1-9D934839DC27");
		document.body.appendChild(objghzd);
		var ret = objghzd["executeSync"]("","WindowsIsTop","windowName=Internet Explorer&isTop=true");
		console.info(ret);
		console.log("工行置顶end")
	}catch(e){}
}

//打印机 base64
/**
 * 打开设备
 */
function opendevPdfPrint() {
	show("正在连接激光打印机－－－－－－－－");
	laserprint.OpenConnection('HtmPrinter310', 0);
	show("ServiceName:" + laserprint.getAttribute('ServiceName'));
}
try {
	devprocess.onEvent = function(cmd, args) {
		switch(cmd) {
			case 'ReadDevprocessOver':
				show("读取成功");
			default:
				show('cmd :' + cmd + ' args :' + args);
				break;
		}
	}
} catch(e) {}
try {
	laserprint.onEvent = function(cmd, args) {
		switch(cmd) {
			case 'OpenConnectionOver': //连接设备事件
				show("open ok!初始化");
				break;
			case 'CloseConnectionOver':
				show("close ok!");
				break;
			case 'WaitCancelled':
				show("取消指令" + args.cmdName + "成功");
				break;
			case 'ResetOver': //复位方法事件
				show("reset ok!");
				break;
			case 'PrintRawFileOver': //打印完成事件	
				//show('print ok');
				show("打印文件完成");
				CloseConnection();
				break;
			case 'MediaInserted': //插入媒介事件	
				show('检测到插入了纸张等媒介');
				break;
			case 'MediaTaken': //取走纸张事件	
				show('纸张被弹出后，检测到纸被取走');
				break;
			case 'RetractBinThreshHold': //回收箱阀值事件
				if(args == 'OK') {
					show('回收箱正常');
				} else if(args == 'FULL') {
					show('回收箱满');
				} else if(args == 'HIGH') {
					show('回收箱将满');
				}
				break;
			case 'PaperThreshHold': //纸张事件
				show("收到纸张事件:" + args.paperStatus);
				break;
			case 'TonerThreshHold': //纸张事件
				if(args.tonerStaus == 'TONERFULL') {
					show('墨满');
				} else if(args.tonerStaus == 'TONERLOW') {
					show('少墨');
				} else if(args.tonerStaus == 'TONEROUT') {
					show('无墨');
				}
				break;
			case 'PrintTextOver':
				show("打印完成");
				CloseConnection();
				break;
			case 'DeviceError':
				show("执行:" + args.cmdName + "出错" + args.errorcode);
				break;
			case 'Timeout':
				show("操作" + args.cmdName + "超时");
				break;
			case 'ErrorInfoReceived':
				show("系统硬件错误:" + args);
				break;
			case 'StatusChanged':
				show("状态改变" + args.newStatus);
				break;
			default:
				show('cmd :' + cmd + ' args :' + args);
				break;
		}
	}
} catch(e) {}

/**
 * base64数据传输转换文件
 * pInInfo filename=生成文件绝对路径 content=数据流
 * 
 * */
function base64tofile(pdfDatabase64) {
	show("文件转换中.......");
	var filename ="C:\\Test.pdf";
	var content=pdfDatabase64;
	var devprocess = document.getElementById("devprocess");
    var pOutInfo = devprocess.executeJsonSync("_Code2Image", "{\"Data\":\""+content+"\","+"\"DataLen\":\""+content.length+"\","+"\"ImagePath\":\""+filename+"\"}");
	var jsondata = eval(pOutInfo);
	if(jsondata.ok != '0'){//转换文件失败
		alert("fail!");
	}else{//转换文件成功
		printpaper('');
	}
}

/**
 * 打印纸张
 * */
function printpaper(pathUrl) {
	show("printing...");
	if(pathUrl==''){
		laserprint.PrintRawFile("C:\\\\Test.pdf");
		show("打印Starting(C:\\Test.pdf)....");
	}else{
		laserprint.PrintRawFile(pathUrl);
		show("打印Starting(.pdf)...."+pathUrl);
	}
}

function CloseConnection() {
	show("正在删除文件并关闭连接－－－－－－－－");
	var pInInfo = "filename=C:/Test.pdf";
	var devprocess = document.getElementById("devprocess");
	var pOutInfo = devprocess.executeSync('', 'deletefile', pInInfo);
	var jsondata = eval(pOutInfo);
	if(jsondata.ok != true) { //转换文件失败
		alert("fail!");
	} else { //转换文件成功
		laserprint.CloseConnection();
	}

}
/**
 * 记录册打印
 * */
function printrawdata(newBookBh, newBookKh, userXb, userName, userAge) {
	show("缓存打印数据");
	var cfg = {
		'PaperNum': '10',
		'PrintType': '1',
		'Stamp': '1',
		'Mode': 'buffer'
	};
	var files = "{'transactionID':'SHTS30306','data': {'SerialNumber':'" + newBookBh + "','Name':'" + userName + "','Sex':'" + userXb + "','CardNo':'" + newBookKh + "','Birthday':'" + userAge + "'}}";
	show("打印数据:" + files);
	laserprint.PrintText(files);
}

function openMedicalPrinter(newBookBh, newBookKh, userXb, userName, userAge) {
	show("正在连接医保打印机－－－－－－－－");
	laserprint.OpenConnection('MedicalPrinter', 0);
	show("ServiceName:" + laserprint.getAttribute('ServiceName'));
	setTimeout(function() {
		printrawdata(newBookBh, newBookKh, userXb, userName, userAge);
	}, 3000)
}
/**
 * 高拍仪
 * */
try {
	highcamera.onEvent = function(cmd, args) {
		switch(cmd) {
			case 'OpenConnectionOver':
				show("open ok!");
				ghSetProperty();
				break;
			case 'CloseConnectionOver':
				show("close ok!");
				break;
			case 'StartTakePictureOver':
				show("打开窗口ok");
				break;
			case 'PauseTakePictureOver':
				show("暂停拍照ok");
				break;
			case 'ResumeTakePictureOver':
				show("恢复拍照ok");
				break;
			case 'StopTakePictureOver':
				show("停止拍照ok");
				break;
			case 'DeviceError':
				show("执行:" + args.cmdName + "出错" + args.errorcode);
				break;
			case 'Timeout':
				show("操作" + args.cmdName + "超时");
				break;
			case 'StatusChanged':
				show("状态改变" + args.newStatus);
				break;
			case 'ErrorInfoReceived':
				show("系统硬件错误:" + args);
				break;
			default:
				show('cmd :' + cmd + ' args :' + args);
				break;
		}

	}
} catch(e) {}

function ghCaptureOpendevice() {
	show("正在连接高拍仪－－－－－－－－");
	highcamera.OpenConnection('HighCamera310', 0);
	show("ServiceName:" + highcamera.getAttribute('ServiceName'));
}

function ghSetProperty() {
	show("设置窗口－－－－－－－－");
	var iwidth = 460;
	var iheight = 370;
	var itop = 300;
	var ileft = 130;
	var st = "({width:'" + iwidth + "',height:'" + iheight + "',top:'" + itop + "',left:'" + ileft + "'})";
	//highcamera.updateOcxStlye(st);
	show(st);
	highcamera.setAttribute("wStartPosX", ileft);
	highcamera.setAttribute("wStartPosY", itop);
	highcamera.setAttribute("wWindowWidth", iwidth);
	highcamera.setAttribute("wWindowHeigh", iheight);
	show("设置窗口完成－－－－－－－－");
}

function ghStartdisplay() {
	ghCaptureOpendevice();
	show("开始显示视频画面－－－－－－－－");
	setTimeout(function() {
		highcamera.StartTakePicture();
	}, 500)
}

function ghStopdisplay() {
	try{
		show("停止拍照－－－－－－－－");
	    highcamera.StopTakePicture();
	}catch(e){}
}

function Propert() {
	show("正在获取设备状态－－－－－－－－.");
	show("StDeviceStatus:" + highcamera.getAttribute('StDeviceStatus'));
}

function Capture() {
	show("正在获取画面－－－－－－－－");
	var re = highcamera.GetPictureSync("c:\\\\11.jpg", 0);
	show("result:" + re);
	if(re == 0) {
		return "c:\\\\11.jpg";
	} else {
		alert('获取画面err')
	}

}

function ghCaptureClosedevice() {
	ghStopdisplay();
	setTimeout(function(){
		try{
		    show("正在关闭高拍仪－－－－－－－－");
	        highcamera.CloseConnection();
	    }catch(e){}
	},1000);
}
/**
 * 高拍仪end
 * */
function sendMessage(type) {
	var clickdatas, inputdatas, datas;
	clickdatas = {
		action: 'clickEvent',
		keyBoardType: 0,
		elementId: ''
	}
	inputdatas = {
		action: 'inputEvent',
		keyBoardType: 0,
		elementId: ''
	}
	try {
		datas = type == 'input' ? inputdatas : clickdatas;
		console.log(datas)
		window.parent.postMessage(datas, "*");
	} catch(e) {}

}
window.addEventListener("click", function(event) {
	console.log(event.target);
	if((event.target.localName == 'input')||(event.target.localName == 'textarea')) {
		sendMessage('input')
	} else {
		sendMessage('click')
	}
});

function getlocalmacgh() {
	try {
		var devprocessMacgh = document.getElementById("devprocess");
		var jsondataMacgh = eval(devprocessMacgh.executeSync('', 'getlocalmac', ""));
		if(jsondataMacgh.ok != true) {
			alert("fail!");
		}
		return jsondataMacgh.param[0].split('|')[0];
	} catch(e) {
		alert("ghMac")
	}
}