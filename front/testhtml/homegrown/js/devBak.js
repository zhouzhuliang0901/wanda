jQuery.device = {
	idCardOpen: function(callback) {
		try {
			window.external.Hd_IdCard_Open();
			alert("00");
			window.idCardCallBack = function(value) {
				alert(value);
				callback(value);
			};
			alert("01");
			window.HtmlUserInfo = function(value) {
				alert(value+"------02");
				callback(value);
			};
			alert("02");
			window.SMYHtmlUserInfo = function(value) {
				alert(value+"------03");
				callback(value);
			};
			alert("03");
		} catch(e) {}
	},
	idCardClose: function() {
		try {
			window.external.Hd_IdCard_Close();
		} catch(e) {}
	},
	//二维码
	qrCodeOpen: function(callback) {
		try {
			window.external.Hd_QrScanner_Open();
			window.qrScannerCallBack = function(value) {
				callback(value);
			};
			window.GetScannerCode = function(value) {
				callback(value);
			};
		} catch(e) {}
	},
	qrCodeClose: function() {
		try {
			window.external.Hd_QrScanner_Close();
		} catch(e) {}
	}
}