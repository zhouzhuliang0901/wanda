//公用单选
function PublicchoiceById(PcId) {
	$("#" + PcId + " a").click(function() {
		if($(this).attr('class') == 'in') {
			$(this).removeClass("in");
		} else {
			$("#" + PcId + " a").removeClass("in");
			$(this).addClass("in");
		}
	})
}

//保留小数点后两位
function toDecimal2(x) {
	var f = Math.round(x * 100) / 100;
	var s = f.toString();
	var rs = s.indexOf('.');
	if(rs < 0) {
		rs = s.length;
		s += '.';
	}
	while(s.length <= rs + 2) {
		s += '0';
	}
	return s;
}
/*
 校验手机号
 * */
function isPhoneAvailable(phone) {
	var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
	if(!myreg.test(phone)) {
		//layer.msg('请输入正确的手机号码！');
		return false;
	} else {
		return true;
	}
}

//获取时间格式 yyyy-mm-dd HH:MM:SS
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var minutes = date.getMinutes();
	var hours = date.getHours();
	if(month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if(strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	if(minutes >= 1 && minutes <= 9) {
		minutes = "0" + minutes;
	}
	if(hours >= 1 && hours <= 9) {
		hours = "0" + hours;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate +
		" " + hours + seperator2 + minutes +
		seperator2 + date.getSeconds();
	return currentdate;
}

//根据节点key得到对应区县信息 
function getListByName(dataJsonp, condition) {
	var NameList = [];　　
	dataJsonp.map(function(item, index) {　　
		if(item.kskm.match(condition)) {　　
			NameList.push(item);　　
		}
	});
	return NameList;
}