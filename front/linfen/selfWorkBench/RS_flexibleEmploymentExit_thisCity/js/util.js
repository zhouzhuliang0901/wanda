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
	console.log(dataJsonp);
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i][key] == condition) {
			result = dataJsonp[i];
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