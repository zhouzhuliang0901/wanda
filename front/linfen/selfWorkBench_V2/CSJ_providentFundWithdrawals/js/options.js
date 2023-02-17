//字典项
//婚姻状况
var marryType = [{
	id: "10",
	name: '未婚'
}, {
	id: "20",
	name: '已婚'
}]

//户籍省市区
var hjList = [{
	'name': '上海市',
	'code': '310000'
}, {
	'name': '江苏省',
	'code': '320000'
}, {
	'name': '浙江省',
	'code': '330000'
}, {
	'name': '安徽省',
	'code': '340000'
},{
	'name': '其他',
	'code': '999999'
}];

//房屋类型
var houseType = [{
	id: "0",
	name: '新房'
}, {
	id: "1",
	name: '二手房'
}];

//缴存地

var countyOrgProvince = [{
	"provinceName": "上海市",
	"provinceCode": "310000",
}, {
	"provinceName": "江苏省",
	"provinceCode": "320000",
}, {
	"provinceName": "浙江省",
	"provinceCode": "330000",
}, {
	"provinceName": "安徽省",
	"provinceCode": "340000",
}]

var countyOrg = [{
		"provinceName": "上海市",
		"provinceCode": "310000",
		"cityName": "市辖区",
		"cityCode": "",
		"depositName": "上海市公积金管理中心",
		"depositCode": "310000000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "南京市",
		"cityCode": "",
		"depositName": "南京住房公积金管理中心",
		"depositCode": "320100000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "无锡市",
		"cityCode": "",
		"depositName": "无锡市住房公积金管理中心",
		"depositCode": "320200000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "苏州市",
		"cityCode": "",
		"depositName": "苏州市住房公积金管理中心",
		"depositCode": "320500000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "嘉兴市",
		"cityCode": "",
		"depositName": "嘉兴市住房公积金管理中心",
		"depositCode": "330400000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "衢州市",
		"cityCode": "",
		"depositName": "衢州市住房公积金管理中心",
		"depositCode": "330800000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "合肥市",
		"cityCode": "",
		"depositName": "合肥市住房公积金管理中心",
		"depositCode": "340100000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "芜湖市",
		"cityCode": "",
		"depositName": "芜湖市住房公积金管理中心",
		"depositCode": "340200000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "杭州市",
		"cityCode": "",
		"depositName": "浙江省直单位住房公积金管理中心",
		"depositCode": "330101000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "杭州市",
		"cityCode": "",
		"depositName": "杭州住房公积金管理中心",
		"depositCode": "330100000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "宁波市",
		"cityCode": "",
		"depositName": "宁波市住房公积金管理中心",
		"depositCode": "330200000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "温州市",
		"cityCode": "",
		"depositName": "温州市住房公积金管理中心",
		"depositCode": "330300000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "湖州市",
		"cityCode": "",
		"depositName": "湖州市住房公积金管理中心",
		"depositCode": "330500000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "舟山市",
		"cityCode": "",
		"depositName": "绍兴市住房公积金管理中心",
		"depositCode": "330600000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "金华市",
		"cityCode": "",
		"depositName": "金华市住房公积金管理中心",
		"depositCode": "330700000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "舟山市",
		"cityCode": "",
		"depositName": "舟山市住房公积金管理中心",
		"depositCode": "330900000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "台州市",
		"cityCode": "",
		"depositName": "台州市住房公积金管理中心",
		"depositCode": "331000000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "丽水市",
		"cityCode": "",
		"depositName": "丽水市住房公积金管理中心",
		"depositCode": "331100000000000"
	},
	{
		"provinceName": "浙江省",
		"provinceCode": "330000",
		"cityName": "义乌市",
		"cityCode": "",
		"depositName": "义乌市住房公积金管理中心",
		"depositCode": "331200000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "合肥市",
		"cityCode": "",
		"depositName": "安徽省省直住房公积金管理分中心",
		"depositCode": "340101000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "蚌埠市",
		"cityCode": "",
		"depositName": "蚌埠市住房公积金管理中心",
		"depositCode": "340300000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "马鞍山市",
		"cityCode": "",
		"depositName": "马鞍山市住房公积金管理中心",
		"depositCode": "340500000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "马鞍山市",
		"cityCode": "",
		"depositName": "马鞍山市住房公积金管理中心马钢（集团）控股有限公司分中心",
		"depositCode": "340501000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "淮北市",
		"cityCode": "",
		"depositName": "淮北市住房公积金管理中心",
		"depositCode": "340600000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "淮北市",
		"cityCode": "",
		"depositName": "淮北市住房公积金管理中心皖北煤电集团公司分中心",
		"depositCode": "340601000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "淮北市",
		"cityCode": "",
		"depositName": "淮北矿业集团住房公积金管理分中心",
		"depositCode": "340602000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "铜陵市",
		"cityCode": "",
		"depositName": "铜陵市住房公积金管理中心",
		"depositCode": "340700000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "安庆市",
		"cityCode": "",
		"depositName": "安庆市住房公积金管理中心",
		"depositCode": "340800000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "黄山市",
		"cityCode": "",
		"depositName": "黄山市住房公积金管理中心",
		"depositCode": "341000000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "池州市",
		"cityCode": "",
		"depositName": "池州市住房公积金管理中心",
		"depositCode": "341700000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "宣城市",
		"cityCode": "",
		"depositName": "宣城市住房公积金管理中心",
		"depositCode": "341800000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "南京市",
		"cityCode": "",
		"depositName": "江苏省省级机关住房资金管理中心",
		"depositCode": "320101000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "徐州市",
		"cityCode": "",
		"depositName": "徐州市住房公积金管理中心",
		"depositCode": "320300000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "常州市",
		"cityCode": "",
		"depositName": "常州市住房公积金管理中心",
		"depositCode": "320400000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "南通市",
		"cityCode": "",
		"depositName": "南通市住房公积金管理中心",
		"depositCode": "320600000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "连云港市",
		"cityCode": "",
		"depositName": "连云港市住房公积金管理中心",
		"depositCode": "320700000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "淮安市",
		"cityCode": "",
		"depositName": "淮安市住房公积金管理中心",
		"depositCode": "320800000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "盐城市",
		"cityCode": "",
		"depositName": "盐城市住房公积金管理中心",
		"depositCode": "320900000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "扬州市",
		"cityCode": "",
		"depositName": "扬州市住房公积金管理中心",
		"depositCode": "321000000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "镇江市",
		"cityCode": "",
		"depositName": "镇江市住房公积金管理中心",
		"depositCode": "321100000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "泰州市",
		"cityCode": "",
		"depositName": "泰州市住房公积金管理中心",
		"depositCode": "321200000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "宿迁市",
		"cityCode": "",
		"depositName": "宿迁市住房公积金管理中心",
		"depositCode": "321300000000000"
	},
	{
		"provinceName": "江苏省",
		"provinceCode": "320000",
		"cityName": "苏州市",
		"cityCode": "",
		"depositName": "苏州工业园区社会保险基金和公积金管理中心",
		"depositCode": "321400000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "淮南市",
		"cityCode": "",
		"depositName": "淮南市住房公积金管理中心",
		"depositCode": "340400000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "淮南市",
		"cityCode": "",
		"depositName": "淮南市住房公积金管理中心矿业集团分中心",
		"depositCode": "340401000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "滁州市",
		"cityCode": "",
		"depositName": "滁州市住房公积金管理中心",
		"depositCode": "341100000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "阜阳市",
		"cityCode": "",
		"depositName": "阜阳市住房公积金管理中心",
		"depositCode": "341200000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "宿州市",
		"cityCode": "",
		"depositName": "宿州市住房公积金管理中心",
		"depositCode": "341300000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "六安市",
		"cityCode": "",
		"depositName": "六安市住房公积金中心",
		"depositCode": "341500000000000"
	},
	{
		"provinceName": "安徽省",
		"provinceCode": "340000",
		"cityName": "亳州市",
		"cityCode": "",
		"depositName": "亳州市住房公积金管理中心",
		"depositCode": "341600000000000"
	}

]