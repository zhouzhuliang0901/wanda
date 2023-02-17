try {
	var protocolVal = window.location.protocol;
	var hostVal = window.location.host;
	var stateUrl = protocolVal + "//" + hostVal;
} catch(e) {}
var baseUrl = stateUrl+"/ac-self-jw/";
console.log(baseUrl);
//var baseUrl = "http://192.168.37.96:8080/ac-self-jw/";
var requestGet = function(method, data, callback, error) {
	data.timestamp=new Date().getTime();
	$.ajax({
		url: baseUrl + method,
		type: "get",
		dataType: 'json',
//		timeout: 8000,
		//jsonp: "jsonpCallback",
		data: data,
		success: function(dataJsonp) {
			callback && callback(dataJsonp);
		},
		error: function(err) {
			error && error(err);
		}
//		,complete: function(XMLHttpRequest, status) {
//			console.log(status);
//			if(status == 'timeout' || status == 'error') {　
//				alert("超时");　　　　
//			}　　
//		}
	})
}
var requestPost = function(method, data, callback, error) {
	data.timestamp=new Date().getTime();
	$.ajax({
		url: baseUrl + method,
		type: "post",
		dataType: 'json',
//		timeout: 8000, //超时时间设置，单位毫秒
		data: data,
		success: function(dataJsonp) {
			callback && callback(dataJsonp);
		},
		error: function(err) {
			error && error(err);
		}
//		,complete: function(XMLHttpRequest, status) {
//			console.log(status);
//			if(status == 'timeout' || status == 'error') {　　
//				alert("超时");　　　　
//			}　　
//		}
	})
}
//捕获异常
try {
	$(function() {
		$.ajaxSetup({
			type: "POST",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				switch(jqXHR.status) {
					case(500):
						alert("服务器系统内部错误");
						break;
					case(401):
						alert("未登录");
						break;
					case(403):
						alert("无权限执行此操作");
						break;
					case(408):
						alert("请求超时");
						break;
					default:
						alert("未知错误");
				}
			},
			success: function(data) {
				console.log("操作成功");
			},
		});
	});
} catch(e) {}