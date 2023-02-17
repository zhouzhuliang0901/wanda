function ghdeviceICBCjudge() {
	try{
		if(window.external.GetConfig("dimagepath") == 'nhdevice')return;
	}catch(e){}
	try{
		if(window.name=='SHYHITM')return;
	}catch(e){}
	try{
		if((window.isBocomDev && 1 == window.isBocomDev) ||(window.isBocomDev && "trans" == window.isBocomDev) ||(window.isBocomDev && "print" == window.isBocomDev))return;
	}catch(e){}
	try{
		if(window.navigator.userAgent.indexOf("SHRCB")>0)return;
	}catch(e){}
	try{
		if(JSON.parse(window.AppHost.getManage().getAppData('machineInfo')).MachineId)return;
	}catch(e){}
	try{
		if(0<window.navigator.userAgent.indexOf("紫金浏览器")||0<window.navigator.userAgent.indexOf("SPDBBROWSER"))return;
	}catch(e){}
	try{
		if("unknown" == typeof window.external.InvokeFromJsPro)return;
	}catch(e){}
	try{
		if(/^Com.Hero.BMClient.Forms.*/.test(window.external) ||window.external.Hd_IdCard_Open)return;
	}catch(e){}
	try{
		if(window.PayCamLive instanceof Object)return;
	}catch(e){}
	try {
		//工行
		var objectDevprocess = document.createElement("object"); //mac 判读设备标识
		objectDevprocess.setAttribute("id", "devprocess");
		objectDevprocess.setAttribute("width", "1");
		objectDevprocess.setAttribute("height", "1");
		objectDevprocess.setAttribute("classid", "clsid:1A171B4F-2C69-4FB7-99B1-9D934839DC27");
		//objectDevprocess.setAttribute("style", "display: none;");
		document.body.appendChild(objectDevprocess);
		try {
			var devprocess = document.getElementById("devprocess");
			var jsondata = eval(devprocess.executeSync('', 'getdevicetype', ""));
			if(jsondata.ok != true) {
				alert("fail!");
			} //jsondata.param
			if(jsondata.param == 'ICBC') {
				window.AppHostgh = "ghdevice";
				if(/allOrganYH/.test(window.location.href.split('selfWorkBench_V2')[1].split("/")[1]))return;
				if(/CSJ_householdRegisterALL/.test(window.location.href.split('selfWorkBench_V2')[1].split("/")[1]))return;
				if(/CSJ_isCrimeProveALL/.test(window.location.href.split('selfWorkBench_V2')[1].split("/")[1]))return;
				if(/index.html#/.test(window.location.href.split('selfWorkBench_V2')[1].split("/")[1]))return;
				
				try {
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/device.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/siu.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/idcard.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/idscanner.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/devprocess.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/barcode.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/camera.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/laserprint.js'><\/script>");
					document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/js/highcamera.js'><\/script>");
				} catch(e) {}
				document.write("<script src='../libs/common/exteriorJS/gh-hardware-js/gh-device-bag.js'><\/script>");
			}
		} catch(e) {}
	} catch(e) {}
}
ghdeviceICBCjudge();