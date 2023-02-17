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

//根据许可证内区局筛选对应事项编码信息
function filterByInfo(dataJsonp, condition) {
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i].areaName == condition) {
			result = dataJsonp[i];
		}
	}
	return result;
}


var areaList = [{
		"itemCodeTY": "TE3101010103101010331010207000007",
		"itemCodeHFYY":'TE3101010103101010331010207000004',
		"areaName": "黄浦区局"
	}, {
		"itemCodeTY": "TE3101040103101040331010207000007",
		"itemCodeHFYY":'TE3101040103101040331010207000004',
		"areaName": "徐汇区局"
	},
	{
		"itemCodeTY": "TE3101050103101050331010207000007",
		"itemCodeHFYY":'TE3101050103101050331010207000004',
		"areaName": "长宁区局"
	},
	{
		"itemCodeTY": "TE3101060103101060331010207000007",
		"itemCodeHFYY":'TE3101060103101060331010207000004',
		"areaName": "静安区局"
	}, {
		"itemCodeTY": "TE3101070103101070331010207000007",
		"itemCodeHFYY":'TE3101070103101070331010207000004',
		"areaName": "普陀区局"
	},
	{
		"itemCodeTY": "TE3101090103101090331010207000007",
		"itemCodeHFYY":'TE3101090103101090331010207000004',
		"areaName": "虹口区局"
	}, {
		"itemCodeTY": "TE3101100103101100331010207000007",
		"itemCodeHFYY":'TE3101100103101100331010207000004',
		"areaName": "杨浦区局"
	},
	{
		"itemCodeTY": "TE3101120103101120331010207000007",
		"itemCodeHFYY":'TE3101120103101120331010207000004',
		"areaName": "闵行区局"
	}, {
		"itemCodeTY": "TE3101130103101130331010207000007",
		"itemCodeHFYY":'TE3101130103101130331010207000004',
		"areaName": "宝山区局"
	},
	{
		"itemCodeTY": "TE3101140103101140331010207000007",
		"itemCodeHFYY":'TE3101140103101140331010207000004',
		"areaName": "嘉定区局"
	}, {
		"itemCodeTY": "TE3101150103101150331010207000007",
		"itemCodeHFYY":'TE3101150103101150331010207000004',
		"areaName": "浦东新区局"
	}, {
		"itemCodeTY": "TE3101200103101200331010207000007",
		"itemCodeHFYY":'TE3101200103101200331010207000004',
		"areaName": "奉贤区局"
	}, {
		"itemCodeTY": "TE3101170103101170331010207000007",
		"itemCodeHFYY":'TE3101170103101170331010207000004',
		"areaName": "松江区局"
	},
	{
		"itemCodeTY": "TE3101160103101160331010207000007",
		"itemCodeHFYY":'TE3101160103101160331010207000004',
		"areaName": "金山区局"
	},
	{
		"itemCodeTY": "TE3101180103101180331010207000007",
		"itemCodeHFYY":'TE3101180103101180331010207000004',
		"areaName": "青浦区局"
	},
	{
		"itemCodeTY": "TE3101510103102010331010207000007",
		"itemCodeHFYY":'TE3101510103102010331010207000004',
		"areaName": "崇明区局"
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
