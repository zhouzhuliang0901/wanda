//清楚打印机缓存
jQuery.clearCache = {
	getOCXName: function(id) {
		try {
			//获取医保记录册打印机
			return document.getElementById(id||'DSPrinterLibOCX') || '';
		} catch(e) {
			//TODO handle the exception
		}
	},
	getUSB: function(DSPrinterLibOCX) {
		try {
			//枚举USB设置并打开设备
			if(DSPrinterLibOCX == '') return;
			var str = DSPrinterLibOCX.DSEnumPrinter(); //枚举
			var pos = str.indexOf("@USB");
			str = str.substr(pos + 1, 6);
			var value = str;
			DSPrinterLibOCX.DSOpenPrinter(value); //打开
		} catch(e) {
			//TODO handle the exception
		}
	},
	clear: function(DSPrinterLibOCX) {
		//清除医保记录册缓存
		try {
			if(DSPrinterLibOCX == '') return;
			if(DSPrinterLibOCX) {
				GetUSB();
				DSPrinterLibOCX.DSclearcacheA();
			}
		} catch(e) {}
	},
	closeClear: function(DSPrinterLibOCX) {
		try {
			if(DSPrinterLibOCX == '') return;
			if(DSPrinterLibOCX) {
				DSPrinterLibOCX.DSClosePrinter();
			}
		} catch(e) {}
	},

}