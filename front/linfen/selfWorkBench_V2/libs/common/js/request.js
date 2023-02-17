//全局的ajax访问，处理ajax清求时异常
try {
	$.ajaxSetup({
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		ifModified: true,
		//请求发送之前
		beforeSend: function(xhr) {
			//针对非服务器请求
			try {
				var requestUrlData = arguments[1].url.match(new RegExp(/(\w+):\/\/([^/:]+)(:\d*)?/))[2];
				var configUrlData = $.getConfigMsg.preUrl.match(new RegExp(/(\w+):\/\/([^/:]+)(:\d*)?/))[2];
				if(requestUrlData != configUrlData) return;
			} catch(e) {}
			//<!--end-->
			arguments[1].dataType = 'json';
			if(arguments[1].type == "GET") {
				var params = arguments[1].url.split('?')[1];
			} else if(arguments[1].type == "POST") {
				var params = arguments[1].data;
			}
			if(params != "" && params != null && params != undefined) {
				var paramsJson = {};
				//将urlcode编码转化为json
				try {
					var paramsData1=params.split('&');
					if(paramsData1.length>0){
						for(a in paramsData1){
							var paramsData2=paramsData1[a].split('=');
							if(paramsData2.length==2){
								paramsJson[paramsData2[0]]=decodeURIComponent(paramsData2[1]);
							}
						}
					}else{
						params.replace(/([^?&]+)=([^?&]+)/g, function(s, v, k) {
							paramsJson[v] = decodeURIComponent(k);
							return k + '=' + v;
						});
					}
					// params.replace(/([^?&]+)=([^?&]+)/g, function(s, v, k) {
					// 	paramsJson[v] = decodeURIComponent(k);
					// 	return k + '=' + v;
					// });
					// params.replace(/([^?&]+)=([$?&])/g, function(s, v, k) {
					// 	if(k=='&'){
					// 		paramsJson[v] = decodeURIComponent("");
					// 	}
					// });
				} catch(e) {}

				//    AES加密
				//AES 秘钥
				var key = CryptoJS.enc.Utf8.parse("8NONwyJtHesysWpD");
				var plaintText = JSON.stringify(paramsJson); // 明文
				//加密
				var encryptedData = CryptoJS.AES.encrypt(plaintText, key, {
					mode: CryptoJS.mode.ECB,
					padding: CryptoJS.pad.Pkcs7
				});
				//转16进制
				encryptedData = encryptedData.ciphertext.toString();
				var encryptedHexStr = CryptoJS.enc.Hex.parse(encryptedData);
				paramsData = {
					key: 'AES',
					data: encryptedHexStr
				}
				var paramsString = "";
				//将json格式转化为urlcode编码格式的数据
				for(var key in paramsData) {
					paramsString = paramsString + key + "=" + paramsData[key] + "&";
				}
				paramsString = paramsString.substr(0, paramsString.length - 1);
				if(arguments[1].type == "GET") {
					var urlRequest = arguments[1].url.split('?')[0];
					arguments[1].url = urlRequest + "?" + paramsString;

				} else if(arguments[1].type == "POST") {
					arguments[1].data = paramsString;
				}
			}
		},
		dataFilter: function(data, type) {
			try {
				var result = JSON.parse(arguments[0]);
				try {
					if(result.code === 0 && result.data.length == 64) return arguments[0];
				} catch(e) {}
				var encryptedHexStr = result.data;
				//AES 秘钥
				var key = CryptoJS.enc.Utf8.parse("8NONwyJtHesysWpD");
				//转为16进制
				var encryptedBase64Str = CryptoJS.enc.Hex.parse(encryptedHexStr);
				//将16进制转为base64
				encryptedBase64Str = CryptoJS.enc.Base64.stringify(encryptedBase64Str);
				//AES解密
				var decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key, {
					mode: CryptoJS.mode.ECB,
					padding: CryptoJS.pad.Pkcs7
				});
				//转utf-8格式输出
				var decryptedStr = decryptedData.toString(CryptoJS.enc.Utf8);
				arguments[0] = decryptedStr;
			} catch(e) {}
			return arguments[0];
		},
		complete: function(XMLHttpRequest, textStatus) {
			//console.log(textStatus);
			//通过XMLHttpRequest取得响应结果
			var res = XMLHttpRequest.responseText;
			try {
				var jsonData = JSON.parse(res);
				return jsonData;
				if(jsonData.state == -1) {
					//如果超时就处理 ，指定要跳转的页面(比如登陆页)
					alert(jsonData.msg);
					//window.location.replace("/login/index.php");
				} else if(jsonData.state == 0) {
					//其他的异常情况,给个提示。
					alert(jsonData.msg);
				} else {
					//正常情况就不统一处理了
				}
			} catch(e) {}
		}
	});
} catch(e) {}