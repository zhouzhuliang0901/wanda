function setDateTime(timestamp) {
	// 时间存在的场合
	if (timestamp == null || timestamp == '') {
		return '';
	} else {
		var datetime = new Date();
		datetime.setTime(timestamp.time);
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1;
		var date = datetime.getDate();
		var hours = timestamp.hours;
		var minutes = timestamp.minutes;
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		if (hours < 10) {
			hours = "0" + hours;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		return year + "-" + month + "-" + date + " " + hours + ":" + minutes;
	}
}

// 获取url传的参数
function getRequest() {
   var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
      }
   }
   return theRequest;
}