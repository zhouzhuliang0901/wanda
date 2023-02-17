//公用多选
function PublicChoiceById2(PcId) {
	$("#" + PcId + " a").click(function() {
		if($(this).attr('class') == 'choice') {
			$(this).removeClass("choice");
		} else {
			//			$("#" + PcId + " a").removeClass("in");
			$(this).addClass("choice");
		}
	})
}
//获取出生日期  性别   年龄
function IdCard(UUserCard, num) {
	if(num == 1) {
		//获取出生日期
		birth = UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
		return birth;
	}
	if(num == 2) {
		//获取性别
		if(parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
			//男
			return "男";
		} else {
			//女
			return "女";
		}
	}
	if(num == 3) {
		//获取年龄
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
		if(UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
			age++;
		}
		return age;
	}
}

//给select增加失去焦点事件
function selectBlur() {
	$(".select1").bind("change", function() {
		this.blur();
	});
}
//判空
function isBlank(str) {
	if(str == "" || str == null || str == undefined) {
		return true;
	} else {
		return false;
	}
}

//根据节点key得到对应区县信息 
function filterByInfo(dataJsonp, condition, key) {
	var result = [];
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i][key] == condition) {
			result.push(dataJsonp[i]);
		}
	}
	console.log(result);
	return result;
}

//将yyyymmdd转为yyyy-mm-dd
function formatDateCustom(str) {
	let date = new Date(str);
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	month = month < 10 ? "0" + month : month;
	day = day < 10 ? "0" + day : day;
	str = year + '-' + month + '-' + day;
	return str;
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
//获取当前日期  yyyy-mm-dd HH:MM:ss
function getCurrentDate(format) {
	var now = new Date();
	var year = now.getFullYear(); //得到年份
	var month = now.getMonth(); //得到月份
	var date = now.getDate(); //得到日期
	var day = now.getDay(); //得到周几
	var hour = now.getHours(); //得到小时
	var minu = now.getMinutes(); //得到分钟
	var sec = now.getSeconds(); //得到秒
	month = month + 1;
	if(month < 10) month = "0" + month;
	if(date < 10) date = "0" + date;
	if(hour < 10) hour = "0" + hour;
	if(minu < 10) minu = "0" + minu;
	if(sec < 10) sec = "0" + sec;
	var time = "";
	//精确到天
	if(format == 1) {
		time = year + "-" + month + "-" + date;
	}
	//精确到分
	else if(format == 2) {
		time = year + "-" + month + "-" + date + " " + hour + ":" + minu + ":" + sec;
	}
	return time;
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