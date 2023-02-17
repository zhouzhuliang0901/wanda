jQuery.customAjax = {
	get: function(url, data, callback, error) {
		// data.timestamp=new Date().getTime();
		$.ajax({
			url: url,
			type: "get",
			dataType: 'json',
			data: data,
			success: function(dataJsonp) {
				callback && callback(dataJsonp);
			},
			error: function(err) {
				error && error(err);
			}
		})
	},
	getText: function(url, data, callback, error) {
		// data.timestamp=new Date().getTime();
		$.ajax({
			url: url,
			type: "get",
			dataType: 'text',
			data: data,
			success: function(dataJsonp) {
				callback && callback(dataJsonp);
			},
			error: function(err) {
				error && error(err);
			}
		})
	},
	postJson: function(url, data, callback, error) {
		// data.timestamp=new Date().getTime();
		$.ajax({
			url: url,
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
	},
	post: function(url, data, callback, error) {
		// data.timestamp=new Date().getTime();
		$.ajax({
			url: url,
			type: "post",
			dataType: 'json',
			data: data,
			success: function(dataJsonp) {
				callback && callback(dataJsonp);
			},
			error: function(err) {
				error && error(err);
			}
		})
	},
	getJsonp: function(url, data, callback, error) {
		// data.timestamp=new Date().getTime();
		$.ajax({
			url: url,
			type: "get",
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			data: data,
			success: function(dataJsonp) {
				callback && callback(dataJsonp);
			},
			error: function(err) {
				error && error(err);
			}
		})
	},
	postJsonp: function(url, data, callback, error) {
		// data.timestamp=new Date().getTime();
		$.ajax({
			url: url,
			type: "post",
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			data: data,
			success: function(dataJsonp) {
				callback && callback(dataJsonp);
			},
			error: function(err) {
				error && error(err);
			}
		})
	}
}
