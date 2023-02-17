//公用单选
function PublicChoiceById(PcId) {
	console.log($("#" + PcId + " a"));
	$("#" + PcId + " a").click(function() {
		if($(this).attr('class') == 'in') {
			$(this).removeClass("in");
		} else {
			$("#" + PcId + " a").removeClass("in");
			$(this).addClass("in");
		}
	})
}
//公用多选
function PublicChoiceById2(PcId) {
	$("#" + PcId + " a").click(function() {
		if($(this).attr('class') == 'in') {
			$(this).removeClass("in");
		} else {
			//			$("#" + PcId + " a").removeClass("in");
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
//获取标签
function getZmlx(text) {
	switch(text) {
		case "曾被判处管制、拘役、有期徒刑、无期徒刑、死刑、罚金、剥夺政治权利、没收财产以及已构成犯罪，被人民法院判处免予刑事处罚的犯罪记录，包括缓刑、假释、暂予监外执行等执行方式":
			return "1"
			break;
		case "曾被处以收容教育、劳动教养、强制隔离戒毒（强制戒毒）、责令社区戒毒（限期戒毒）的违法信息，包括不执行、暂缓执行、所外执行等执行方式":
			return "2"
			break;
		case "曾被处以行政拘留、暂扣或吊销许可证的违法信息":
			return "3"
			break;
		case "曾被处以警告、罚款、没收的违法信息，不包括交通类违法信息":
			return "4"
			break;
	}
}