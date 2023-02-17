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

//判空
function isBlank(str) {
	if(str == "" || str == null || str == undefined) {
		return true;
	} else {
		return false;
	}
}


var applyObj = [{
	"id":'1',
	"name":"个人"
},{
	"id":'2',
	"name":"法人"
}]

var documentType = [{
	"id":"1",
	"name":'身份证'
},{
	"id":"2",
	"name":'护照'
},{
	"id":"3",
	"name":'军官证'
}]
