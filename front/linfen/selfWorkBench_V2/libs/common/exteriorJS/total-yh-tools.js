try {
	//中行设备js引入
	if(JSON.parse(window.AppHost.getManage().getAppData('machineInfo')).MachineId) {
		var script = document.createElement('script');
		script.src = "../libs/common/js/appbridgeforbranch.js";
		document.getElementsByTagName('head')[0].appendChild(script);
		window.AppHostzh = "zhdevice";
	}
} catch(e) {}
//农行
try {
	window.onload = function() {
		console.log(acBridgeMac.vendor());
		if(acBridgeMac.vendor() == 'nhdevice') {
			var script = document.createElement('script');
			script.src = getProjectFileName().ProjectFileStateUrl+"/libs/common/exteriorJS/nh-hardware-js/nh-tools-bag.js";
			document.getElementsByTagName('body')[0].appendChild(script);
		}else if(acBridgeMac.vendor() == 'nsdevice') {
			var script = document.createElement('script');
			script.src = getProjectFileName().ProjectFileStateUrl+"/libs/common/exteriorJS/ns-hardware-js/ns-tools-bag.js";
			document.getElementsByTagName('body')[0].appendChild(script);
		}
		//加载win不用需要硬件文件
		try {
			if(window.external.GetConfig("isAddWinNoHardwareFile")=='Y') {
				var script = document.createElement('script');
				script.src = "../libs/common/exteriorJS/winNoHardwareFile.js";
				document.getElementsByTagName('body')[0].appendChild(script);
			}
		} catch(e) {}
		//加载航信ocx
		try {
			if(acBridgeMac.vendor() == 'wonders' && window.external.GetConfig('liveDetection') == 'hxdevice') {
				appendHXOCXobject();
			}
		} catch(e) {}
	};
} catch(e) {}

function openDebugShow() {
	try {
		$.post("http://localhost:54212/application/appShow", {}, function(e) {
			console.log(e)
		})
	} catch(e) {}
}

function openDebugHide() {
	try {
		$.post("http://localhost:54212/application/appHide", {}, function(e) {
			console.log(e)
		})
	} catch(e) {}
}

function wsOnMessage(data) {
	var huif = {
		"errcode": 0,
		"replyId": "2",
		"payload": {
			"type": "cn-platform-show"
		},
		"msg": ""
	}
	try {
		if(JSON.parse(data).type == 'cn-platform-show') {
			//收到消息打开程序
			openDebugShow();
			window.external.Ws_SendMsg(JSON.stringify(huif));
			window.nhdeviceYH = 'nhdeviceYH';
		}
	} catch(e) {}
}

function WebSocketDebugHide() {
	openDebugHide();
	var data = {
		"type": "cn-platform-on-hide",
		"id": "3"
	}
	try {
		//关闭程序，并回复消息
		window.external.Ws_SendMsg(JSON.stringify(data))
	} catch(e) {}
}
function getProjectFileName() {
	var strURL = window.location.href;
	try {
		var MList = strURL.split('/');
		var returndata;
		for (let var1 in MList) {
			var sname = MList[var1].match(new RegExp('selfWorkBench_V2', 'i'));
			if (sname) returndata = sname.input;
		}
		var ProjectFile = {};
		ProjectFile.ProjectFileName = returndata || 'selfWorkBench_V2';
		ProjectFile.ProjectFileStateUrl = strURL.split(ProjectFile.ProjectFileName)[0] + ProjectFile.ProjectFileName;
		return ProjectFile;
	} catch (e) {}
}
//引入ajax拦截器  request.js
try {
	var script = document.createElement('script');
	script.src = "../libs/common/CryptoJS-master/rollups/aes.js";
	document.getElementsByTagName('body')[0].appendChild(script);
	//		var script1 = document.createElement('script');
	//		script1.src = "../libs/common/CryptoJS-master/components/mode-ecb.js";
	//		document.getElementsByTagName('body')[0].appendChild(script1);
	var script2 = document.createElement('script');
	script2.src = "../libs/common/js/request.js";
	document.getElementsByTagName('body')[0].appendChild(script2);
} catch(e) {}
(function(){
	// is hx face detection hxocxLoadScriptToolsBag
	try{
		if (window.external.GetConfig('liveDetection') == 'hxdevice') {
			document.write("<script src='../libs/common/exteriorJS/hx-hardware-js/hx-tools-bag.js'><\/script>");
		}
	}catch(e){}
})();