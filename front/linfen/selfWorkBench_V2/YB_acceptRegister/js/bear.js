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
//根据父节点筛选对应区县
function filterByName(dataJsonp, condition) {
	var result = [];
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i]['parent-key'] == condition) {
			result.push(dataJsonp[i]);
		}
	}
	return result;
}

//根据父节点随机一个受理中心
function filterByNameRandom(dataJsonp, condition) {
	var result = [];
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i]['parent-key'] == condition) {
			result.push(dataJsonp[i]);
		}
	}
	var random = parseInt(Math.random() * result.length);
	console.log(random);
	console.log(result[random]);
	return result[random];
}
//根据节点key得到对应区县信息 
function filterByInfo(dataJsonp, condition) {
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i].key == condition) {
			result = dataJsonp[i];
		}
	}
	return result;
}

//根据节点value得到对应区县信息 
function filterGetValueByInfo(dataJsonp, condition) {
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i].value == condition) {
			result = dataJsonp[i];
		}
	}
	console.log(result);
	return result;
}
//判断是否为空
function isBlank(string) {
	if(string == "" || string == null || string == undefined) {
		return true;
	} else {
		return false;
	}
}

// 删除已上传的图片文件
function del(id) {
	$('#' + id).remove();
	//	$.ajax({
	//		url: $.getConfigMsg.preUrlSelf + '/selfapi/civilService/uploadArchiveInfo.do',
	//		type: "get",
	//		dataType: "json",
	//		jsonp: "jsonpCallback",
	//		data: {
	//			imgscans: id,
	//		},
	//		success: function(res) {
	//			if(res.code == 0) {
	//				$('.imgBox').find('#'+id).remove();
	//			}
	//		},
	//		error: function(error) {
	//		}
	//	});
};
//给select增加失去焦点事件
function selectBlur() {
	$(".select1").bind("change", function() {
		this.blur();
	});
}

//将数字金额转化为人民币大写
function convertCurrency(money) {
	//汉字的数字
	var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
	//基本单位
	var cnIntRadice = new Array('', '拾', '佰', '仟');
	//对应整数部分扩展单位
	var cnIntUnits = new Array('', '万', '亿', '兆');
	//对应小数部分单位
	var cnDecUnits = new Array('角', '分', '毫', '厘');
	//整数金额时后面跟的字符
	var cnInteger = '整';
	//整型完以后的单位
	var cnIntLast = '元';
	//最大处理的数字
	var maxNum = 999999999999999.9999;
	//金额整数部分
	var integerNum;
	//金额小数部分
	var decimalNum;
	//输出的中文金额字符串
	var chineseStr = '';
	//分离金额后用的数组，预定义
	var parts;
	if(money == '') {
		return '';
	}
	money = parseFloat(money);
	if(money >= maxNum) {
		//超出最大处理数字
		return '';
	}
	if(money == 0) {
		chineseStr = cnNums[0] + cnIntLast + cnInteger;
		return chineseStr;
	}
	//转换为字符串
	money = money.toString();
	if(money.indexOf('.') == -1) {
		integerNum = money;
		decimalNum = '';
	} else {
		parts = money.split('.');
		integerNum = parts[0];
		decimalNum = parts[1].substr(0, 4);
	}
	//获取整型部分转换
	if(parseInt(integerNum, 10) > 0) {
		var zeroCount = 0;
		var IntLen = integerNum.length;
		for(var i = 0; i < IntLen; i++) {
			var n = integerNum.substr(i, 1);
			var p = IntLen - i - 1;
			var q = p / 4;
			var m = p % 4;
			if(n == '0') {
				zeroCount++;
			} else {
				if(zeroCount > 0) {
					chineseStr += cnNums[0];
				}
				//归零
				zeroCount = 0;
				chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
			}
			if(m == 0 && zeroCount < 4) {
				chineseStr += cnIntUnits[q];
			}
		}
		chineseStr += cnIntLast;
	}
	//小数部分
	if(decimalNum != '') {
		var decLen = decimalNum.length;
		for(var i = 0; i < decLen; i++) {
			var n = decimalNum.substr(i, 1);
			if(n != '0') {
				chineseStr += cnNums[Number(n)] + cnDecUnits[i];
			}
		}
	}
	if(chineseStr == '') {
		chineseStr += cnNums[0] + cnIntLast + cnInteger;
	} else if(decimalNum == '') {
		chineseStr += cnInteger;
	}
	return chineseStr;
}