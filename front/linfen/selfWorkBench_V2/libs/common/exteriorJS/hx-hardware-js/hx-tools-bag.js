// 依赖jQ
function appendHXOCXobject(){
	try{
		var hxOCXFace =
			'<object id="AamCtrlControl" classid="CLSID:EEB2E1CF-2661-442F-A149-F1B3C78B71F8" width="640" height="480" title="AamCtrlControl">' +
			'</' + 'object>';
		var div = "<div id='hxOCXShowYLK' style='width:640px;height:480px;position:absolute;top:200px;left:50%;margin-left:-320px;'>" +
			hxOCXFace + "</div>"
		$('body').append(div);
		$('#hxOCXShowYLK').css('display','none');
	}catch(e){}
}
try{
	var cdpJSCallBack = 'console.log("11"); try{JS_CaptureFinish(code, imagePath1, imagePath2)}catch(e){}';
	var str =
		'<script language="javascript" type="text/javascript" FOR="AamCtrlControl" event="OnCaptureImageFinish(code, imagePath1, imagePath2)">' +
		cdpJSCallBack + '</' + 'script>';
	document.write(str);
}catch(e){}

// 
function JS_ShowLog(logMsg) {
	$.log.debug('航信活体日志'+logMsg)
}

function JS_OpenDevice() {
	try{
		$('#hxOCXShowYLK').css('display','block');
	}catch(e){}
	var ret = AamCtrlControl.OpenCamera();
	if (ret != 0) {
		JS_ShowErrInfo(ret);
	} else {
		JS_ShowLog("打开摄像头成功");
		JS_CaptureImage();
	}
}

function JS_CloseDevice() {
	
	var ret = AamCtrlControl.CloseCamera();
	if (ret != 0) {
		JS_ShowErrInfo(ret);
	} else {
		JS_ShowLog("关闭摄像头成功");
	}
}

function JS_CaptureImage() {
	// 开始活体拍照;
	var ret = AamCtrlControl.StartCapture("D:\\1.jpg", 60000);
	if (ret != 0) {
		JS_ShowErrInfo(ret);
	} else {
		JS_ShowLog("开始进行活体拍照...");
	}
}

function JS_StopCapture() {
	// 停止活体拍照;
	try{
		$('#hxOCXShowYLK').css('display','none');
	}catch(e){}
	var ret = AamCtrlControl.StopCapture();
	if (ret != 0) {
		JS_ShowErrInfo(ret);
	} else {
		JS_ShowLog("停止活体拍照成功");
	}
}

function JS_ShowErrInfo(errCode) {
	var strLog = "";
	switch (errCode) {
		case -11: {
			strLog = ("引擎初始化失败");
		}
		break;
	case -22: {
		strLog = ("摄像头已经打开");
	}
	break;
	case -24: {
		strLog = ("可见光摄像头不存在");
	}
	break;
	case -25: {
		strLog = ("红外摄像头不存在");
	}
	break;
	case -32: {
		strLog = ("开始活体检测，摄像头没有打开");
	}
	break;
	case -36: {
		strLog = ("开始活体检测，输入参数不正确");
	}
	break;
	case -53: {
		strLog = ("摄像头个数不正确");
	}
	break;
	case -99: {
		strLog = ("未知错误");
	}
	break;
	default: {
		strLog = ("未知错误，错误码：" + errCode);
	}
	break;
	}
	JS_ShowLog(strLog);
}
