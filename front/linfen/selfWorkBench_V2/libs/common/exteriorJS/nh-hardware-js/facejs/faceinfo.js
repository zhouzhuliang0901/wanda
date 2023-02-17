
var cloudwalkobj = document.createElement("object")
var width = 800;	//640  400  320
var height = width * 3 / 4;	//宽高比为4：3

var n_ok = 0;
var n_fail = 0;
var rotatemode = 0;
var g_width = 0;
var g_height = 0;
var g_opt = 1;
var strLicense = "";

function showDownloadNotice() {
	//alert("人脸识别控件未安装");
	$("#downloadNotice").removeClass("hide");
	$("#downloadNotice").addClass("show");
	$("#rside").addClass("hide");
}

function showNewVersionNotice() {
	$("#newVersionNotice").removeClass("hide");
	$("#newVersionNotice").addClass("show");
	$("#rside").addClass("hide");
}


function addDIVcloudwalkwebobj() {
	var createDiv = document.createElement("div");
		createDiv.id = "cloudwalkwebobj";
		createDiv.style.position="absolute";
		createDiv.style.top="30%";
		createDiv.style.left='50%';
		createDiv.style.marginLeft="-400px";
		document.body.appendChild(createDiv);
} 
function onPageLoad() {
	addDIVcloudwalkwebobj();
	setTimeout(function(){
		var el = document.getElementById("cloudwalkwebobj");
		createPlugin(el);
	},200);
}

function createPlugin(targetE) {

	if (0 == rotatemode) {
		cloudwalkobj.width = width;
		cloudwalkobj.height = height;
	} else {
		cloudwalkobj.width = height;
		cloudwalkobj.height = width;
	}

	cloudwalkobj.id = "CloudWalkSDKPlugin";

	var bDet = BrowserDetect.browser;
	if ("Explorer" == bDet) {
		cloudwalkobj.classid = "CLSID:B1597418-A51E-4140-8698-EE865439755C";	//IE
	} else {
		//firefox, chrome;
		cloudwalkobj.type = "application/x-cloudface-sdk3.0";
	}

	targetE.appendChild(cloudwalkobj);

	if (!cloudwalkobj.valid) {
		showDownloadNotice();
		return false;
	}

	registerCallBack(cloudwalkobj, "cwFaceCallBack", FaceCallBack);

	var config = cloudwalkobj.cwGetConfig();
	ParseConfig(config);

	var nret = cloudwalkobj.cwInit(strLicense);
	if (nret != 0) {
		var msg = "初始化SDK失败错误码：" + nret;
		alert(msg);
		return;
	}

	openCamera();
	cloudwalkobj.cwFaceInfoStart();
}

function openCamera() {
	//alert(cloudwalkobj.cwQueryCamera());
	//打开摄像头时，OCX 会再次尝试创建检测器。
	var nRet = cloudwalkobj.cwStartCamera(0, rotatemode);

	if (nRet != 0) {
		var msg;
		switch (nRet) {
			case CW_ERR_CameraNotOpen:
				msg = "摄像头未打开！";
				break;
			case CW_ERR_CameraOpenError:
				msg = "摄像头打开失败！";
				break;
			case CW_ERR_CameraOpenAdy:
				msg = "摄像头已经打开！";
				break;
			default:
				msg = "摄像头打开失败 ！错误码：" + nRet;
				break;
		}
		alert(msg);
		window.location.href = "../main.html";
	}
	return nRet;
}

function nhcloseCamera() {
	//cwStopCamera: 关闭摄像头设备。（将会停止产生帧数据）
	//关闭摄像头以后，OCX 没有释放算法检测器。这时可能会占用较高的 CPU。
	cloudwalkobj.cwStopCamera();
	//
	// cwDestory: 释放 OCX 内部的算法检测器。
	// 关闭摄像头以后，释放算法检测器，可以让 CPU 占用率回到正常水平。
	// 当再次调用 cwStartCamera 时，OCX 会再次创建算法检测器。
	// 注意，cwDestory 接口名称已经拼错！只能将错就错。
	//（正确的英文拼写应为 cwDestroy ，但定义接口的人拼错了)
	// 原来的人英文太差。-- comment by jinfude
	//
	cloudwalkobj.cwDestory();
	var e = document.getElementById('cloudwalkwebobj');
	document.body.removeChild(e);
}

function FaceCallBack(strJson) {
	var jsonObj = $.parseJSON(strJson);
	if (jsonObj != "null") {
		var bestFace = cloudwalkobj.cwGetBestFace();
		var jsonObj = $.parseJSON(bestFace);
		if (jsonObj["result"] == 0) {
			faceImage = jsonObj["data"];
			//alert(faceImage);
			nhFaceCallBack(faceImage);
			cloudwalkobj.cwBase64ToFile(faceImage, "C:\\cloudwalk\\", "test.jpg");
		}

	}
}

function ParseConfig(strJson) {
	var jsonObj = $.parseJSON(strJson);
	if (jsonObj != "null") {
		strLicense = jsonObj["License"];
	}
}
