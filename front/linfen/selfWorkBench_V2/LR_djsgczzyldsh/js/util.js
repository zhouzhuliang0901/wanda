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

//判空
function isBlank(str) {
	if(str == "" || str == null || str == undefined) {
		return true;
	} else {
		return false;
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

var areaList = [{
		"areaCode": "a",
		"areaName": "黄浦区"
	}, {
		"areaCode": "b",
		"areaName": "徐汇区"
	},
	{
		"areaCode": "c",
		"areaName": "长宁区"
	},
	{
		"areaCode": "d",
		"areaName": "静安区"
	}, {
		"areaCode": "e",
		"areaName": "普陀区"
	},
	{
		"areaCode": "g",
		"areaName": "虹口区"
	}, {
		"areaCode": "h",
		"areaName": "杨浦区"
	},
	{
		"areaCode": "i",
		"areaName": "闵行区"
	}, {
		"areaCode": "j",
		"areaName": "宝山区"
	},
	{
		"areaCode": "k",
		"areaName": "嘉定区"
	}, {
		"areaCode": "l",
		"areaName": "浦东新区"
	}, {
		"areaCode": "p",
		"areaName": "奉贤区"
	}, {
		"areaCode": "n",
		"areaName": "松江区"
	},
	{
		"areaCode": "m",
		"areaName": "金山区"
	},
	{
		"areaCode": "o",
		"areaName": "青浦区"
	},
	{
		"areaCode": "f",
		"areaName": "崇明区"
	},
]

var deptType = [{
	"id":"a",
	"name":'企业'
},{
	"id":"b",
	"name":'事业'
},{
	"id":"c",
	"name":'社会团体'
},{
	"id":"d",
	"name":'其他组织'
}]

var isCrossArea = [{
	"id":"a",
	"name":'是'
},{
	"id":"b",
	"name":'否'
}]
