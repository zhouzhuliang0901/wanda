document.write('<link rel="stylesheet" href="../libs/common/css/cart.css">');
//获取证书
function getCert() {
	console.log("获取证书");
	$.ajax({
		type: "get",
		url: "http://127.0.0.1:18495/cert/token",
		data: {},
		dataType: "json",
		success: function(result) {
			console.log(result);
			if (result.code == 0) {
				GetDataMac(result.data.tokenBase64);
				//cartToken(result.data.tokenBase64);
			} else {
				showHint("获取证书失败");
			}
		},
		error: function(err) {
			showHint("您好，请启动证书校验客户端。<br>如未安装请联系终端运维厂商并于12月15日前完成证书激活");
		},
	});
}
getCert();
//获取标识
function GetDataMac(token) {
	$.post("http://127.0.0.1:18495/cert/getMac",{},function(data, textStatus, jqXHR){
		console.log(JSON.parse(jqXHR.responseText));
		var result=JSON.parse(jqXHR.responseText)
		if(result){
			if(result.data){
				cartToken(token,result.data);
			}else{
				showHint("获取证书getMac失败");
			}
		}else{
			showHint("获取证书getMac失败");
		}
	});
	// fetch('http://127.0.0.1:18495/cert/getMac', {
	// 	method: 'post'
	// }).then(r => r.json()).then((result) => {
	// 	console.log(result);
	// 	if (result) {
	// 		if (result.data) {
	// 			cartToken(token, result.data);
	// 		}
	// 	} else {
	// 		showHint("获取证书getMac失败");
	// 	}
	// })
	// $.ajax({
	// 	type: "post",
	// 	url: "http://127.0.0.1:18495/cert/getMac",
	// 	data: {},
	// 	dataType: "json",
	// 	success: function(result) {
	// 		console.log(result);
	// 		if(result.data){
	// 			cartToken(token,result.data);
	// 		}
	// 	},
	// 	error: function(err) {
	// 		console.log(err);
	// 	},
	// });   
}
//对比证书
function cartToken(value, mac) {
	var dataS = {
		'machineId': mac,
		'token': value
	};
	$.ajax({
		type: "post",
		url: $.getConfigMsg.preUrlSelf+"/selfapi/authentication.do",
		dataType: "json",
		//jsonp: "jsonpCallback",
		timeout: 5000,
		data: dataS,
		success: function(result) {
			console.log(result);
			if (result.success == true) {
				console.log(result.data);
			} else {
				showHint("您好，您的认证证书已失效，<br>请您联系终端运维厂商并于12月15日前完成证书更新。");
			}
		},
		error: function(err) {
			console.log(err);
			showHint("验证错误");
		},
	});
};
//弹出层
function showHint(tet) {
	hideHint();
	var Qd = 0,
		Qx = 1;
	var tet = tet || '错误提示';
	var div = document.createElement('div');
	var Lodingtext = '<p id="alertTxt">' + tet + '</p>';
	var btn = '<div id="alertcontBtn"><button style="background-color:#33cbfc;" onclick="OnNotarize(' + Qd +
		')">确定</button><button onclick="OnNotarize(' + Qx + ')">取消</button></div>';
	div.innerHTML = '<div id="alertTxtk"><div id="alertcont">' + Lodingtext + btn + '</div></div>';
	document.body.appendChild(div);
	//动画效果
	var obj = document.getElementById("alertcont");
	obj.style.animation = "myfirst 1s"
}
//取消弹出层
function hideHint() {
	try {
		var _element = document.getElementById("alertTxtk");
		//动画效果
		var obj = document.getElementById("alertcont");
		if (obj) {
			obj.style.animation = "hidemyfirst 1s"
		}
		if (_element) {
			setTimeout(function() {
				_element.parentNode.removeChild(_element);
			}, 1000)
		}
	} catch (e) {}
}
//关闭浏览器当前页面
function OnNotarize(val) {
	try {
		if (val === 0) {
			hideHint();
			console.log("返回首页");
			// $.device.GoHome();
		} else if (val === 1) {
			hideHint();
			setTimeout(function() {
				// getCert();
			}, 1050);
		}
	} catch (e) {
		OnNotarize(0);
	}
}
