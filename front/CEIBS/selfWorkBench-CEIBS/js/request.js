var baseUrl = "http://172.16.128.58:8080/ceibs/api/";
var requestGet = function(method, data, callback, error) {
	if(VERSION == "Chinese"){
		data.locale = "zh_CN";
	}else if(VERSION == "English"){
		data.locale = "en_US";
	}
	$.ajax({
		url: baseUrl + method,
		type: "get",
		dataType: 'json',
//		jsonp: "jsonpCallback",
		data: data,
		success: function(dataJsonp) {
			callback && callback(dataJsonp);
		},
		error: function(err) {
			error && error(err);
		}
	})
}
var requestPost = function(method, data, callback, error) {
	data = JSON.parse(data);
	if(VERSION == "Chinese"){
		data.locale = "zh_CN";
	}else if(VERSION == "English"){
		data.locale = "en_US";
	}
	data = JSON.stringify(data);
	$.ajax({
		url: baseUrl + method,
		type: "post",
		dataType: 'json',
		contentType:'application/json;charset=UTF-8',
		data: data,
		success: function(dataJsonp) {
			callback && callback(dataJsonp);
		},
		error: function(err) {
			error && error(err);
		}
	})
}