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
		"areaCode": "01",
		"areaName": "黄浦区"
	}, {
		"areaCode": "04",
		"areaName": "徐汇区"
	},
	{
		"areaCode": "05",
		"areaName": "长宁区"
	},
	{
		"areaCode": "06",
		"areaName": "静安区"
	}, {
		"areaCode": "07",
		"areaName": "普陀区"
	},
	{
		"areaCode": "09",
		"areaName": "虹口区"
	}, {
		"areaCode": "10",
		"areaName": "杨浦区"
	},
	{
		"areaCode": "11",
		"areaName": "闵行区"
	}, {
		"areaCode": "12",
		"areaName": "宝山区"
	},
	{
		"areaCode": "13",
		"areaName": "嘉定区"
	}, {
		"areaCode": "14",
		"areaName": "浦东新区"
	}, {
		"areaCode": "16",
		"areaName": "奉贤区"
	}, {
		"areaCode": "17",
		"areaName": "松江区"
	},
	{
		"areaCode": "18",
		"areaName": "金山区"
	},
	{
		"areaCode": "19",
		"areaName": "青浦区"
	},
	{
		"areaCode": "20",
		"areaName": "崇明区"
	},
]

var deptType = [{
	"id":"1",
	"name":'企业'
},{
	"id":"2",
	"name":'事业'
},{
	"id":"3",
	"name":'社会团体'
},{
	"id":"4",
	"name":'其他组织'
}]

var isCrossArea = [{
	"id":"1",
	"name":'是'
},{
	"id":"2",
	"name":'否'
}]
