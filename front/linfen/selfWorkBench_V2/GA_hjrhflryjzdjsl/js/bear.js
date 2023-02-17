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
    if (!myreg.test(phone)) {
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
	var random = parseInt(Math.random()*result.length);
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
	return result;
}
//判断是否为空
function isBlank(string){
	if(string == "" || string == null || string == undefined){
		return true;
	}else{
		return false;
	}
}

// 删除已上传的图片文件
function del(id) {
	$('#'+id).remove();
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