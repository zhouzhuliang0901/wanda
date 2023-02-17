//字典项
let nation = [{
	"id": "1",
	"name": "汉族"
}, {
	"id": "2",
	"name": "蒙古族"
}, {
	"id": "3",
	"name": "回族"
}, {
	"id": "4",
	"name": "藏族"
}, {
	"id": "5",
	"name": "维吾尔族"
}, {
	"id": "6",
	"name": "苗族"
}, {
	"id": "7",
	"name": "彝族"
}, {
	"id": "8",
	"name": "壮族"
}, {
	"id": "9",
	"name": "布依族"
}, {
	"id": "10",
	"name": "朝鲜族"
}, {
	"id": "11",
	"name": "满族"
}, {
	"id": "12",
	"name": "侗族"
}, {
	"id": "13",
	"name": "瑶族"
}, {
	"id": "14",
	"name": "白族"
}, {
	"id": "15",
	"name": "土家族"
}, {
	"id": "16",
	"name": "哈尼族"
}, {
	"id": "17",
	"name": "哈萨克族"
}, {
	"id": "18",
	"name": "傣族"
}, {
	"id": "19",
	"name": "黎族"
}, {
	"id": "20",
	"name": "傈僳族"
}, {
	"id": "21",
	"name": "佤族"
}, {
	"id": "22",
	"name": "畲族"
}, {
	"id": "23",
	"name": "高山族"
}, {
	"id": "24",
	"name": "拉祜族"
}, {
	"id": "25",
	"name": "水族"
}, {
	"id": "26",
	"name": "东乡族"
}, {
	"id": "27",
	"name": "纳西族"
}, {
	"id": "28",
	"name": "景颇族"
}, {
	"id": "29",
	"name": "柯尔克孜族"
}, {
	"id": "30",
	"name": "土族"
}, {
	"id": "31",
	"name": "达斡尔族"
}, {
	"id": "32",
	"name": "仫佬族"
}, {
	"id": "33",
	"name": "羌族"
}, {
	"id": "34",
	"name": "布朗族"
}, {
	"id": "35",
	"name": "撒拉族"
}, {
	"id": "36",
	"name": "毛南族"
}, {
	"id": "37",
	"name": "仡佬族"
}, {
	"id": "38",
	"name": "锡伯族"
}, {
	"id": "39",
	"name": "阿昌族"
}, {
	"id": "40",
	"name": "普米族"
}, {
	"id": "41",
	"name": "塔吉克族"
}, {
	"id": "42",
	"name": "怒族"
}, {
	"id": "43",
	"name": "乌孜别克族"
}, {
	"id": "44",
	"name": "俄罗斯族"
}, {
	"id": "45",
	"name": "鄂温克族"
}, {
	"id": "46",
	"name": "德昂族"
}, {
	"id": "47",
	"name": "保安族"
}, {
	"id": "48",
	"name": "裕固族"
}, {
	"id": "49",
	"name": "京族"
}, {
	"id": "50",
	"name": "塔塔尔族"
}, {
	"id": "51",
	"name": "独龙族"
}, {
	"id": "52",
	"name": "鄂伦春族"
}, {
	"id": "53",
	"name": "赫哲族"
}, {
	"id": "54",
	"name": "门巴族"
}, {
	"id": "55",
	"name": "珞巴族"
}, {
	"id": "56",
	"name": "基诺族"
}, {
	"id": "91",
	"name": "其他族"
}, {
	"id": "92",
	"name": "外国血统"
}, {
	"id": "98",
	"name": "外国民族"
}, {
	"id": "99",
	"name": "不明"
}]
let province = [{
		"id": '310000',
		"shortname": '上海市',
		"parentid": 'sh'
	}, {
		"id": '320000',
		"shortname": '江苏省',
		"parentid": 'js'
	},
	{
		"id": '330000',
		"shortname": '浙江省',
		"parentid": 'zj'
	}, {
		"id": '340000',
		"shortname": '安徽省',
		"parentid": 'ah'
	}
];

// 市
let city = [{
		"id": '119900',
		"shortname": '北京市',
		"parentid": '110000'
	}, {
		"id": '129900',
		"shortname": '天津市',
		"parentid": '120000'
	}, {
		"id": '130100',
		"shortname": '石家庄市',
		"parentid": '130000'
	}, {
		"id": '130200',
		"shortname": '唐山市',
		"parentid": '130000'
	}, {
		"id": '130300',
		"shortname": '秦皇岛市',
		"parentid": '130000'
	}, {
		"id": '130400',
		"shortname": '邯郸市',
		"parentid": '130000'
	}, {
		"id": '130600',
		"shortname": '保定市',
		"parentid": '130000'
	}, {
		"id": '130700',
		"shortname": '张家口市',
		"parentid": '130000'
	},
	{
		"id": '130800',
		"shortname": '承德市',
		"parentid": '130000'
	}, {
		"id": '130900',
		"shortname": '沧州市',
		"parentid": '130000'
	}, {
		"id": '131000',
		"shortname": '廊坊市',
		"parentid": '130000'
	}, {
		"id": '131100',
		"shortname": '衡水市',
		"parentid": '130000'
	}, {
		"id": '130500',
		"shortname": '邢台市',
		"parentid": '130000'
	}, {
		"id": '140100',
		"shortname": '太原市',
		"parentid": '140000'
	}, {
		"id": '140200',
		"shortname": '大同市',
		"parentid": '140000'
	}, {
		"id": '140300',
		"shortname": '阳泉市',
		"parentid": '140000'
	}, {
		"id": '140400',
		"shortname": '长治市',
		"parentid": '140000'
	}, {
		"id": '140500',
		"shortname": '晋城市',
		"parentid": '140000'
	}, {
		"id": '140600',
		"shortname": '朔州市',
		"parentid": '140000'
	}, {
		"id": '140700',
		"shortname": '晋中市',
		"parentid": '140000'
	}, {
		"id": '140800',
		"shortname": '运城市',
		"parentid": '140000'
	}, {
		"id": '140900',
		"shortname": '忻州市',
		"parentid": '140000'
	}, {
		"id": '141000',
		"shortname": '临汾市',
		"parentid": '140000'
	}, {
		"id": '141100',
		"shortname": '吕梁市',
		"parentid": '140000'
	}, {
		"id": '150800',
		"shortname": '巴彦淖尔市',
		"parentid": '150000'
	}, {
		"id": '150900',
		"shortname": '乌兰察布市',
		"parentid": '150000'
	}, {
		"id": '152200',
		"shortname": '兴安盟',
		"parentid": '150000'
	}, {
		"id": '152500',
		"shortname": '锡林郭勒盟',
		"parentid": '150000'
	}, {
		"id": '152900',
		"shortname": '阿拉善盟',
		"parentid": '150000'
	}, {
		"id": '150100',
		"shortname": '呼和浩特市',
		"parentid": '150000'
	}, {
		"id": '150200',
		"shortname": '包头市',
		"parentid": '150000'
	}, {
		"id": '150300',
		"shortname": '乌海市',
		"parentid": '150000'
	}, {
		"id": '150400',
		"shortname": '赤峰市',
		"parentid": '150000'
	}, {
		"id": '150500',
		"shortname": '通辽市',
		"parentid": '150000'
	}, {
		"id": '150600',
		"shortname": '鄂尔多斯市',
		"parentid": '150000'
	}, {
		"id": '150700',
		"shortname": '呼伦贝尔市',
		"parentid": '150000'
	}, {
		"id": '210200',
		"shortname": '大连市',
		"parentid": '210000'
	}, {
		"id": '210300',
		"shortname": '鞍山市',
		"parentid": '210000'
	}, {
		"id": '210400',
		"shortname": '抚顺市',
		"parentid": '210000'
	}, {
		"id": '210500',
		"shortname": '本溪市',
		"parentid": '210000'
	}, {
		"id": '210600',
		"shortname": '丹东市',
		"parentid": '210000'
	}, {
		"id": '210700',
		"shortname": '锦州市',
		"parentid": '210000'
	}, {
		"id": '210800',
		"shortname": '营口市',
		"parentid": '210000'
	}, {
		"id": '210900',
		"shortname": '阜新市',
		"parentid": '210000'
	}, {
		"id": '211000',
		"shortname": '辽阳市',
		"parentid": '210000'
	}, {
		"id": '211100',
		"shortname": '盘锦市',
		"parentid": '210000'
	}, {
		"id": '211200',
		"shortname": '铁岭市',
		"parentid": '210000'
	}, {
		"id": '211300',
		"shortname": '朝阳市',
		"parentid": '210000'
	}, {
		"id": '211400',
		"shortname": '葫芦岛市',
		"parentid": '210000'
	}, {
		"id": '210100',
		"shortname": '沈阳市',
		"parentid": '210000'
	}, {
		"id": '220100',
		"shortname": '长春市',
		"parentid": '220000'
	}, {
		"id": '220200',
		"shortname": '吉林市',
		"parentid": '220000'
	}, {
		"id": '220300',
		"shortname": '四平市',
		"parentid": '220000'
	}, {
		"id": '220400',
		"shortname": '辽源市',
		"parentid": '220000'
	}, {
		"id": '220500',
		"shortname": '通化市',
		"parentid": '220000'
	}, {
		"id": '220600',
		"shortname": '白山市',
		"parentid": '220000'
	}, {
		"id": '220700',
		"shortname": '松原市',
		"parentid": '220000'
	}, {
		"id": '220800',
		"shortname": '白城市',
		"parentid": '220000'
	}, {
		"id": '222200',
		"shortname": '长白山管委会',
		"parentid": '220000'
	}, {
		"id": '222400',
		"shortname": '延边朝鲜族自治州',
		"parentid": '220000'
	}, {
		"id": '230200',
		"shortname": '齐齐哈尔市',
		"parentid": '230000'
	}, {
		"id": '230300',
		"shortname": '鸡西市',
		"parentid": '230000'
	}, {
		"id": '230400',
		"shortname": '鹤岗市',
		"parentid": '230000'
	}, {
		"id": '230500',
		"shortname": '双鸭山市',
		"parentid": '230000'
	}, {
		"id": '230600',
		"shortname": '大庆市',
		"parentid": '230000'
	}, {
		"id": '230700',
		"shortname": '伊春市',
		"parentid": '230000'
	}, {
		"id": '230800',
		"shortname": '佳木斯市',
		"parentid": '230000'
	}, {
		"id": '230900',
		"shortname": '七台河市',
		"parentid": '230000'
	}, {
		"id": '231000',
		"shortname": '牡丹江市',
		"parentid": '230000'
	}, {
		"id": '231100',
		"shortname": '黑河市',
		"parentid": '230000'
	}, {
		"id": '231200',
		"shortname": '绥化市',
		"parentid": '230000'
	}, {
		"id": '232700',
		"shortname": '大兴安岭地区',
		"parentid": '230000'
	}, {
		"id": '232800',
		"shortname": '省森工地区',
		"parentid": '230000'
	}, {
		"id": '232900',
		"shortname": '省农垦总局',
		"parentid": '230000'
	}, {
		"id": '230100',
		"shortname": '哈尔滨市',
		"parentid": '230000'
	}, {
		"id": '310000000000',
		"shortname": '上海市',
		"parentid": '310000'
	}, {
		"id": '321200',
		"shortname": '泰州市',
		"parentid": '320000'
	}, {
		"id": '321300',
		"shortname": '宿迁市',
		"parentid": '320000'
	}, {
		"id": '320100',
		"shortname": '南京市',
		"parentid": '320000'
	}, {
		"id": '320200',
		"shortname": '无锡市',
		"parentid": '320000'
	}, {
		"id": '320300',
		"shortname": '徐州市',
		"parentid": '320000'
	}, {
		"id": '320400',
		"shortname": '常州市',
		"parentid": '320000'
	}, {
		"id": '320500',
		"shortname": '苏州市',
		"parentid": '320000'
	}, {
		"id": '320600',
		"shortname": '南通市',
		"parentid": '320000'
	}, {
		"id": '320700',
		"shortname": '连云港市',
		"parentid": '320000'
	}, {
		"id": '320800',
		"shortname": '淮安市',
		"parentid": '320000'
	}, {
		"id": '320900',
		"shortname": '盐城市',
		"parentid": '320000'
	}, {
		"id": '321000',
		"shortname": '扬州市',
		"parentid": '320000'
	}, {
		"id": '321100',
		"shortname": '镇江市',
		"parentid": '320000'
	}, {
		"id": '330100',
		"shortname": '杭州市',
		"parentid": '330000'
	}, {
		"id": '330300',
		"shortname": '温州市',
		"parentid": '330000'
	}, {
		"id": '330400',
		"shortname": '嘉兴市',
		"parentid": '330000'
	}, {
		"id": '330500',
		"shortname": '湖州市',
		"parentid": '330000'
	}, {
		"id": '330600',
		"shortname": '绍兴市',
		"parentid": '330000'
	}, {
		"id": '330700',
		"shortname": '金华市',
		"parentid": '330000'
	}, {
		"id": '330800',
		"shortname": '衢州市',
		"parentid": '330000'
	}, {
		"id": '330900',
		"shortname": '舟山市',
		"parentid": '330000'
	}, {
		"id": '331100',
		"shortname": '丽水市',
		"parentid": '330000'
	}, {
		"id": '330200',
		"shortname": '宁波市',
		"parentid": '330000'
	}, {
		"id": '331000',
		"shortname": '台州市',
		"parentid": '330000'
	}, {
		"id": '340100',
		"shortname": '合肥市',
		"parentid": '340000'
	}, {
		"id": '340200',
		"shortname": '芜湖市',
		"parentid": '340000'
	}, {
		"id": '340300',
		"shortname": '蚌埠市',
		"parentid": '340000'
	}, {
		"id": '340400',
		"shortname": '淮南市',
		"parentid": '340000'
	}, {
		"id": '340500',
		"shortname": '马鞍山市',
		"parentid": '340000'
	}, {
		"id": '340600',
		"shortname": '淮北市',
		"parentid": '340000'
	}, {
		"id": '340700',
		"shortname": '铜陵市',
		"parentid": '340000'
	}, {
		"id": '340800',
		"shortname": '安庆市',
		"parentid": '340000'
	}, {
		"id": '341000',
		"shortname": '黄山市',
		"parentid": '340000'
	}, {
		"id": '341100',
		"shortname": '滁州市',
		"parentid": '340000'
	}, {
		"id": '341200',
		"shortname": '阜阳市',
		"parentid": '340000'
	}, {
		"id": '341300',
		"shortname": '宿州市',
		"parentid": '340000'
	}, {
		"id": '341400',
		"shortname": '巢湖市',
		"parentid": '340000'
	}, {
		"id": '341500',
		"shortname": '六安市',
		"parentid": '340000'
	}, {
		"id": '341600',
		"shortname": '亳州市',
		"parentid": '340000'
	}, {
		"id": '341700',
		"shortname": '池州市',
		"parentid": '340000'
	}, {
		"id": '341800',
		"shortname": '宣城市',
		"parentid": '340000'
	}, {
		"id": '350100',
		"shortname": '福州市',
		"parentid": '350000'
	}, {
		"id": '350200',
		"shortname": '厦门市',
		"parentid": '350000'
	}, {
		"id": '350300',
		"shortname": '莆田市',
		"parentid": '350000'
	}, {
		"id": '350400',
		"shortname": '三明市',
		"parentid": '350000'
	}, {
		"id": '350500',
		"shortname": '泉州市',
		"parentid": '350000'
	}, {
		"id": '350600',
		"shortname": '漳州市',
		"parentid": '350000'
	}, {
		"id": '350700',
		"shortname": '南平市',
		"parentid": '350000'
	}, {
		"id": '350800',
		"shortname": '龙岩市',
		"parentid": '350000'
	}, {
		"id": '350900',
		"shortname": '宁德市',
		"parentid": '350000'
	}, {
		"id": '360100',
		"shortname": '南昌市',
		"parentid": '360000'
	}, {
		"id": '360200',
		"shortname": '景德镇市',
		"parentid": '360000'
	}, {
		"id": '360300',
		"shortname": '萍乡市',
		"parentid": '360000'
	}, {
		"id": '360400',
		"shortname": '九江市',
		"parentid": '360000'
	}, {
		"id": '360500',
		"shortname": '新余市',
		"parentid": '360000'
	}, {
		"id": '360600',
		"shortname": '鹰潭市',
		"parentid": '360000'
	}, {
		"id": '360700',
		"shortname": '赣州市',
		"parentid": '360000'
	}, {
		"id": '360800',
		"shortname": '吉安市',
		"parentid": '360000'
	}, {
		"id": '360900',
		"shortname": '宜春市',
		"parentid": '360000'
	}, {
		"id": '361000',
		"shortname": '抚州市',
		"parentid": '360000'
	}, {
		"id": '361100',
		"shortname": '上饶市',
		"parentid": '360000'
	}, {
		"id": '370100',
		"shortname": '济南市',
		"parentid": '370000'
	}, {
		"id": '370200',
		"shortname": '青岛市',
		"parentid": '370000'
	}, {
		"id": '370300',
		"shortname": '淄博市',
		"parentid": '370000'
	}, {
		"id": '370400',
		"shortname": '枣庄市',
		"parentid": '370000'
	}, {
		"id": '370500',
		"shortname": '东营市',
		"parentid": '370000'
	}, {
		"id": '370600',
		"shortname": '烟台市',
		"parentid": '370000'
	}, {
		"id": '370700',
		"shortname": '潍坊市',
		"parentid": '370000'
	}, {
		"id": '370800',
		"shortname": '济宁市',
		"parentid": '370000'
	}, {
		"id": '370900',
		"shortname": '泰安市',
		"parentid": '370000'
	}, {
		"id": '371000',
		"shortname": '威海市',
		"parentid": '370000'
	}, {
		"id": '371100',
		"shortname": '日照市',
		"parentid": '370000'
	}, {
		"id": '371200',
		"shortname": '莱芜市',
		"parentid": '370000'
	}, {
		"id": '371300',
		"shortname": '临沂市',
		"parentid": '370000'
	}, {
		"id": '371400',
		"shortname": '德州市',
		"parentid": '370000'
	}, {
		"id": '371500',
		"shortname": '聊城市',
		"parentid": '370000'
	}, {
		"id": '371600',
		"shortname": '滨州市',
		"parentid": '370000'
	}, {
		"id": '371700',
		"shortname": '菏泽市',
		"parentid": '370000'
	}, {
		"id": '410700',
		"shortname": '新乡市',
		"parentid": '410000'
	}, {
		"id": '410100',
		"shortname": '郑州市',
		"parentid": '410000'
	}, {
		"id": '410200',
		"shortname": '开封市',
		"parentid": '410000'
	}, {
		"id": '410300',
		"shortname": '洛阳市',
		"parentid": '410000'
	}, {
		"id": '410400',
		"shortname": '平顶山市',
		"parentid": '410000'
	}, {
		"id": '410500',
		"shortname": '安阳市',
		"parentid": '410000'
	}, {
		"id": '410600',
		"shortname": '鹤壁市',
		"parentid": '410000'
	}, {
		"id": '410900',
		"shortname": '濮阳市',
		"parentid": '410000'
	}, {
		"id": '411000',
		"shortname": '许昌市',
		"parentid": '410000'
	}, {
		"id": '411100',
		"shortname": '漯河市',
		"parentid": '410000'
	}, {
		"id": '411200',
		"shortname": '三门峡市',
		"parentid": '410000'
	}, {
		"id": '411300',
		"shortname": '南阳市',
		"parentid": '410000'
	}, {
		"id": '411400',
		"shortname": '商丘市',
		"parentid": '410000'
	}, {
		"id": '411500',
		"shortname": '信阳市',
		"parentid": '410000'
	}, {
		"id": '411600',
		"shortname": '周口市',
		"parentid": '410000'
	}, {
		"id": '411700',
		"shortname": '驻马店市',
		"parentid": '410000'
	}, {
		"id": '411800',
		"shortname": '济源市',
		"parentid": '410000'
	}, {
		"id": '410800',
		"shortname": '焦作市',
		"parentid": '410000'
	}, {
		"id": '420600',
		"shortname": '襄阳市',
		"parentid": '420000'
	}, {
		"id": '429000',
		"shortname": '省直辖县级行政区划',
		"parentid": '420000'
	}, {
		"id": '420100',
		"shortname": '武汉市',
		"parentid": '420000'
	}, {
		"id": '420200',
		"shortname": '黄石市',
		"parentid": '420000'
	}, {
		"id": '420300',
		"shortname": '十堰市',
		"parentid": '420000'
	}, {
		"id": '420500',
		"shortname": '宜昌市',
		"parentid": '420000'
	}, {
		"id": '420700',
		"shortname": '鄂州市',
		"parentid": '420000'
	}, {
		"id": '420800',
		"shortname": '荆门市',
		"parentid": '420000'
	}, {
		"id": '420900',
		"shortname": '孝感市',
		"parentid": '420000'
	}, {
		"id": '421000',
		"shortname": '荆州市',
		"parentid": '420000'
	}, {
		"id": '421100',
		"shortname": '黄冈市',
		"parentid": '420000'
	}, {
		"id": '421200',
		"shortname": '咸宁市',
		"parentid": '420000'
	}, {
		"id": '421300',
		"shortname": '随州市',
		"parentid": '420000'
	}, {
		"id": '422800',
		"shortname": '恩施土家族苗族自治州',
		"parentid": '420000'
	}, {
		"id": '430500',
		"shortname": '邵阳市',
		"parentid": '430000'
	}, {
		"id": '430600',
		"shortname": '岳阳市',
		"parentid": '430000'
	}, {
		"id": '430700',
		"shortname": '常德市',
		"parentid": '430000'
	}, {
		"id": '430800',
		"shortname": '张家界市',
		"parentid": '430000'
	}, {
		"id": '430900',
		"shortname": '益阳市',
		"parentid": '430000'
	}, {
		"id": '431000',
		"shortname": '郴州市',
		"parentid": '430000'
	}, {
		"id": '431100',
		"shortname": '永州市',
		"parentid": '430000'
	},
	{
		"id": '431200',
		"shortname": '怀化市',
		"parentid": '430000'
	}, {
		"id": '431300',
		"shortname": '娄底市',
		"parentid": '430000'
	}, {
		"id": '433100',
		"shortname": '湘西土家族苗族自治州',
		"parentid": '430000'
	}, {
		"id": '430100',
		"shortname": '长沙市',
		"parentid": '430000'
	}, {
		"id": '430200',
		"shortname": '株洲市',
		"parentid": '430000'
	}, {
		"id": '430300',
		"shortname": '湘潭市',
		"parentid": '430000'
	}, {
		"id": '430400',
		"shortname": '衡阳市',
		"parentid": '430000'
	}, {
		"id": '440100',
		"shortname": '广州市',
		"parentid": '440000'
	}, {
		"id": '440200',
		"shortname": '韶关市',
		"parentid": '440000'
	}, {
		"id": '440300',
		"shortname": '深圳市',
		"parentid": '440000'
	}, {
		"id": '440400',
		"shortname": '珠海市',
		"parentid": '440000'
	}, {
		"id": '440500',
		"shortname": '汕头市',
		"parentid": '440000'
	}, {
		"id": '440600',
		"shortname": '佛山市',
		"parentid": '440000'
	}, {
		"id": '440700',
		"shortname": '江门市',
		"parentid": '440000'
	}, {
		"id": '440800',
		"shortname": '湛江市',
		"parentid": '440000'
	}, {
		"id": '440900',
		"shortname": '茂名市',
		"parentid": '440000'
	}, {
		"id": '441200',
		"shortname": '肇庆市',
		"parentid": '440000'
	}, {
		"id": '441300',
		"shortname": '惠州市',
		"parentid": '440000'
	}, {
		"id": '441400',
		"shortname": '梅州市',
		"parentid": '440000'
	}, {
		"id": '441500',
		"shortname": '汕尾市',
		"parentid": '440000'
	}, {
		"id": '441600',
		"shortname": '河源市',
		"parentid": '440000'
	}, {
		"id": '441700',
		"shortname": '阳江市',
		"parentid": '440000'
	}, {
		"id": '441800',
		"shortname": '清远市',
		"parentid": '440000'
	}, {
		"id": '441900',
		"shortname": '东莞市',
		"parentid": '440000'
	}, {
		"id": '442000',
		"shortname": '中山市',
		"parentid": '440000'
	}, {
		"id": '445100',
		"shortname": '潮州市',
		"parentid": '440000'
	}, {
		"id": '445200',
		"shortname": '揭阳市',
		"parentid": '440000'
	}, {
		"id": '445300',
		"shortname": '云浮市',
		"parentid": '440000'
	}, {
		"id": '450100',
		"shortname": '南宁市',
		"parentid": '450000'
	}, {
		"id": '450200',
		"shortname": '柳州市',
		"parentid": '450000'
	}, {
		"id": '450300',
		"shortname": '桂林市',
		"parentid": '450000'
	}, {
		"id": '450400',
		"shortname": '梧州市',
		"parentid": '450000'
	}, {
		"id": '450500',
		"shortname": '北海市',
		"parentid": '450000'
	}, {
		"id": '450600',
		"shortname": '防城港市',
		"parentid": '450000'
	}, {
		"id": '450700',
		"shortname": '钦州市',
		"parentid": '450000'
	}, {
		"id": '450800',
		"shortname": '贵港市',
		"parentid": '450000'
	}, {
		"id": '450900',
		"shortname": '玉林市',
		"parentid": '450000'
	}, {
		"id": '451000',
		"shortname": '百色市',
		"parentid": '450000'
	}, {
		"id": '451100',
		"shortname": '贺州市',
		"parentid": '450000'
	}, {
		"id": '451200',
		"shortname": '河池市',
		"parentid": '450000'
	}, {
		"id": '451300',
		"shortname": '来宾市',
		"parentid": '450000'
	}, {
		"id": '451400',
		"shortname": '崇左市',
		"parentid": '450000'
	}, {
		"id": '460100',
		"shortname": '海口市',
		"parentid": '460000'
	}, {
		"id": '460200',
		"shortname": '三亚市',
		"parentid": '460000'
	}, {
		"id": '462000',
		"shortname": '洋浦经济开发区',
		"parentid": '460000'
	}, {
		"id": '462100',
		"shortname": '省农垦',
		"parentid": '460000'
	}, {
		"id": '469000',
		"shortname": '省直辖县级行政区划',
		"parentid": '460000'
	}, {
		"id": '509900',
		"shortname": '重庆市',
		"parentid": '500000'
	}, {
		"id": '511000',
		"shortname": '内江市',
		"parentid": '510000'
	}, {
		"id": '511100',
		"shortname": '乐山市',
		"parentid": '510000'
	}, {
		"id": '511300',
		"shortname": '南充市',
		"parentid": '510000'
	}, {
		"id": '511400',
		"shortname": '眉山市',
		"parentid": '510000'
	}, {
		"id": '511500',
		"shortname": '宜宾市',
		"parentid": '510000'
	}, {
		"id": '511600',
		"shortname": '广安市',
		"parentid": '510000'
	}, {
		"id": '511700',
		"shortname": '达州市',
		"parentid": '510000'
	}, {
		"id": '511800',
		"shortname": '雅安市',
		"parentid": '510000'
	}, {
		"id": '511900',
		"shortname": '巴中市',
		"parentid": '510000'
	}, {
		"id": '512000',
		"shortname": '资阳市',
		"parentid": '510000'
	}, {
		"id": '513200',
		"shortname": '阿坝藏族羌族自治州',
		"parentid": '510000'
	}, {
		"id": '513300',
		"shortname": '甘孜藏族自治州',
		"parentid": '510000'
	}, {
		"id": '513400',
		"shortname": '凉山彝族自治州',
		"parentid": '510000'
	}, {
		"id": '510100',
		"shortname": '成都市',
		"parentid": '510000'
	}, {
		"id": '510300',
		"shortname": '自贡市',
		"parentid": '510000'
	}, {
		"id": '510400',
		"shortname": '攀枝花市',
		"parentid": '510000'
	}, {
		"id": '510500',
		"shortname": '泸州市',
		"parentid": '510000'
	}, {
		"id": '510600',
		"shortname": '德阳市',
		"parentid": '510000'
	}, {
		"id": '510700',
		"shortname": '绵阳市',
		"parentid": '510000'
	}, {
		"id": '510800',
		"shortname": '广元市',
		"parentid": '510000'
	}, {
		"id": '510900',
		"shortname": '遂宁市',
		"parentid": '510000'
	}, {
		"id": '520100',
		"shortname": '贵阳市',
		"parentid": '520000'
	}, {
		"id": '520200',
		"shortname": '六盘水市',
		"parentid": '520000'
	}, {
		"id": '520300',
		"shortname": '遵义市',
		"parentid": '520000'
	}, {
		"id": '520400',
		"shortname": '安顺市',
		"parentid": '520000'
	}, {
		"id": '522200',
		"shortname": '铜仁地区',
		"parentid": '520000'
	}, {
		"id": '522300',
		"shortname": '黔西南布依族苗族自治州',
		"parentid": '520000'
	}, {
		"id": '522600',
		"shortname": '黔东南苗族侗族自治州',
		"parentid": '520000'
	}, {
		"id": '522700',
		"shortname": '黔南布依族苗族自治州',
		"parentid": '520000'
	}, {
		"id": '522400',
		"shortname": '毕节地区',
		"parentid": '520000'
	}, {
		"id": '532300',
		"shortname": '楚雄彝族自治州',
		"parentid": '530000'
	}, {
		"id": '530700',
		"shortname": '丽江市',
		"parentid": '530000'
	}, {
		"id": '530800',
		"shortname": '普洱市',
		"parentid": '530000'
	}, {
		"id": '530900',
		"shortname": '临沧市',
		"parentid": '530000'
	}, {
		"id": '530100',
		"shortname": '昆明市',
		"parentid": '530000'
	}, {
		"id": '530300',
		"shortname": '曲靖市',
		"parentid": '530000'
	}, {
		"id": '530400',
		"shortname": '玉溪市',
		"parentid": '530000'
	}, {
		"id": '530500',
		"shortname": '保山市',
		"parentid": '530000'
	}, {
		"id": '530600',
		"shortname": '昭通市',
		"parentid": '530000'
	}, {
		"id": '532500',
		"shortname": '红河哈尼族彝族自治州',
		"parentid": '530000'
	}, {
		"id": '532600',
		"shortname": '文山壮族苗族自治州',
		"parentid": '530000'
	}, {
		"id": '532800',
		"shortname": '西双版纳傣族自治州',
		"parentid": '530000'
	}, {
		"id": '532900',
		"shortname": '大理白族自治州',
		"parentid": '530000'
	}, {
		"id": '533100',
		"shortname": '德宏傣族景颇族自治州',
		"parentid": '530000'
	}, {
		"id": '533300',
		"shortname": '怒江傈僳族自治州',
		"parentid": '530000'
	}, {
		"id": '533400',
		"shortname": '迪庆藏族自治州',
		"parentid": '530000'
	}, {
		"id": '540100',
		"shortname": '拉萨市',
		"parentid": '540000'
	}, {
		"id": '542100',
		"shortname": '昌都地区',
		"parentid": '540000'
	}, {
		"id": '542200',
		"shortname": '山南地区',
		"parentid": '540000'
	}, {
		"id": '542300',
		"shortname": '日喀则地区',
		"parentid": '540000'
	}, {
		"id": '542400',
		"shortname": '那曲地区',
		"parentid": '540000'
	}, {
		"id": '542500',
		"shortname": '阿里地区',
		"parentid": '540000'
	}, {
		"id": '542600',
		"shortname": '林芝地区',
		"parentid": '540000'
	}, {
		"id": '610300',
		"shortname": '宝鸡市',
		"parentid": '610000'
	}, {
		"id": '610400',
		"shortname": '咸阳市',
		"parentid": '610000'
	}, {
		"id": '610500',
		"shortname": '渭南市',
		"parentid": '610000'
	}, {
		"id": '610600',
		"shortname": '延安市',
		"parentid": '610000'
	}, {
		"id": '610700',
		"shortname": '汉中市',
		"parentid": '610000'
	}, {
		"id": '610800',
		"shortname": '榆林市',
		"parentid": '610000'
	}, {
		"id": '610900',
		"shortname": '安康市',
		"parentid": '610000'
	}, {
		"id": '611000',
		"shortname": '商洛市',
		"parentid": '610000'
	}, {
		"id": '610100',
		"shortname": '西安市',
		"parentid": '610000'
	}, {
		"id": '610200',
		"shortname": '铜川市',
		"parentid": '610000'
	}, {
		"id": '611100',
		"shortname": '杨凌示范区',
		"parentid": '610000'
	}, {
		"id": '620100',
		"shortname": '兰州市',
		"parentid": '620000'
	}, {
		"id": '620200',
		"shortname": '嘉峪关市',
		"parentid": '620000'
	}, {
		"id": '620300',
		"shortname": '金昌市',
		"parentid": '620000'
	}, {
		"id": '620400',
		"shortname": '白银市',
		"parentid": '620000'
	}, {
		"id": '620500',
		"shortname": '天水市',
		"parentid": '620000'
	}, {
		"id": '620600',
		"shortname": '武威市',
		"parentid": '620000'
	}, {
		"id": '620700',
		"shortname": '张掖市',
		"parentid": '620000'
	}, {
		"id": '620800',
		"shortname": '平凉市',
		"parentid": '620000'
	}, {
		"id": '620900',
		"shortname": '酒泉市',
		"parentid": '620000'
	}, {
		"id": '621000',
		"shortname": '庆阳市',
		"parentid": '620000'
	}, {
		"id": '621100',
		"shortname": '定西市',
		"parentid": '620000'
	}, {
		"id": '621200',
		"shortname": '陇南市',
		"parentid": '620000'
	}, {
		"id": '622900',
		"shortname": '临夏回族自治州',
		"parentid": '620000'
	}, {
		"id": '623000',
		"shortname": '甘南藏族自治州',
		"parentid": '620000'
	}, {
		"id": '627000',
		"shortname": '甘肃矿区',
		"parentid": '620000'
	}, {
		"id": '632600',
		"shortname": '果洛藏族自治州',
		"parentid": '630000'
	}, {
		"id": '632700',
		"shortname": '玉树藏族自治州',
		"parentid": '630000'
	}, {
		"id": '632800',
		"shortname": '海西蒙古族藏族自治州',
		"parentid": '630000'
	}, {
		"id": '630100',
		"shortname": '西宁市',
		"parentid": '630000'
	}, {
		"id": '632100',
		"shortname": '海东地区',
		"parentid": '630000'
	}, {
		"id": '632200',
		"shortname": '海北藏族自治州',
		"parentid": '630000'
	}, {
		"id": '632300',
		"shortname": '黄南藏族自治州',
		"parentid": '630000'
	}, {
		"id": '632500',
		"shortname": '海南藏族自治州',
		"parentid": '630000'
	}, {
		"id": '640100',
		"shortname": '银川市',
		"parentid": '640000'
	}, {
		"id": '640200',
		"shortname": '石嘴山市',
		"parentid": '640000'
	}, {
		"id": '640300',
		"shortname": '吴忠市',
		"parentid": '640000'
	}, {
		"id": '640400',
		"shortname": '固原市',
		"parentid": '640000'
	}, {
		"id": '640500',
		"shortname": '中卫市',
		"parentid": '640000'
	}, {
		"id": '650100',
		"shortname": '乌鲁木齐市',
		"parentid": '650000'
	}, {
		"id": '650200',
		"shortname": '克拉玛依市',
		"parentid": '650000'
	}, {
		"id": '652100',
		"shortname": '吐鲁番地区',
		"parentid": '650000'
	}, {
		"id": '652200',
		"shortname": '哈密地区',
		"parentid": '650000'
	}, {
		"id": '652300',
		"shortname": '昌吉回族自治州',
		"parentid": '650000'
	}, {
		"id": '652700',
		"shortname": '博尔塔拉蒙古自治州',
		"parentid": '650000'
	}, {
		"id": '652800',
		"shortname": '巴音郭楞蒙古自治州',
		"parentid": '650000'
	}, {
		"id": '652900',
		"shortname": '阿克苏地区',
		"parentid": '650000'
	}, {
		"id": '653000',
		"shortname": '克孜勒苏柯尔克孜自治州',
		"parentid": '650000'
	}, {
		"id": '653100',
		"shortname": '喀什地区',
		"parentid": '650000'
	}, {
		"id": '653200',
		"shortname": '和田地区',
		"parentid": '650000'
	}, {
		"id": '654000',
		"shortname": '伊犁哈萨克自治州',
		"parentid": '650000'
	},
	{
		"id": '654200',
		"shortname": '塔城地区',
		"parentid": '650000'
	}, {
		"id": '654300',
		"shortname": '阿勒泰地区',
		"parentid": '650000'
	}
];

// 区县
let county = [{
		"id": '110101',
		"shortname": '东城区',
		"parentid": '119900'
	},
	{
		"id": '110102',
		"shortname": '西城区',
		"parentid": '119900'
	},
	{
		"id": '110103',
		"shortname": '崇文区',
		"parentid": '119900'
	},
	{
		"id": '110104',
		"shortname": '宣武区',
		"parentid": '119900'
	},
	{
		"id": '110105',
		"shortname": '朝阳区',
		"parentid": '119900'
	},
	{
		"id": '110106',
		"shortname": '丰台区',
		"parentid": '119900'
	},
	{
		"id": '110107',
		"shortname": '石景山区',
		"parentid": '119900'
	},
	{
		"id": '110108',
		"shortname": '海淀区',
		"parentid": '119900'
	},
	{
		"id": '110109',
		"shortname": '门头沟区',
		"parentid": '119900'
	},
	{
		"id": '110111',
		"shortname": '房山区',
		"parentid": '119900'
	},
	{
		"id": '110112',
		"shortname": '通州区',
		"parentid": '119900'
	},
	{
		"id": '110113',
		"shortname": '顺义区',
		"parentid": '119900'
	},
	{
		"id": '110114',
		"shortname": '昌平区',
		"parentid": '119900'
	},
	{
		"id": '110115',
		"shortname": '大兴区',
		"parentid": '119900'
	},
	{
		"id": '110116',
		"shortname": '怀柔区',
		"parentid": '119900'
	},
	{
		"id": '110117',
		"shortname": '平谷区',
		"parentid": '119900'
	},
	{
		"id": '110140',
		"shortname": '经济技术开发区',
		"parentid": '119900'
	},
	{
		"id": '110228',
		"shortname": '密云县',
		"parentid": '119900'
	},
	{
		"id": '110229',
		"shortname": '延庆县',
		"parentid": '119900'
	},
	{
		"id": '120141',
		"shortname": '保税区',
		"parentid": '129900'
	},
	{
		"id": '120142',
		"shortname": '科技园区',
		"parentid": '129900'
	},
	{
		"id": '120223',
		"shortname": '静海县',
		"parentid": '129900'
	},
	{
		"id": '120225',
		"shortname": '蓟县',
		"parentid": '129900'
	},
	{
		"id": '120114',
		"shortname": '武清区',
		"parentid": '129900'
	},
	{
		"id": '120115',
		"shortname": '宝坻区',
		"parentid": '129900'
	},
	{
		"id": '120140',
		"shortname": '开发区',
		"parentid": '129900'
	},
	{
		"id": '120101',
		"shortname": '和平区',
		"parentid": '129900'
	},
	{
		"id": '120102',
		"shortname": '河东区',
		"parentid": '129900'
	},
	{
		"id": '120103',
		"shortname": '河西区',
		"parentid": '129900'
	},
	{
		"id": '120104',
		"shortname": '南开区',
		"parentid": '129900'
	},
	{
		"id": '120105',
		"shortname": '河北区',
		"parentid": '129900'
	},
	{
		"id": '120106',
		"shortname": '红桥区',
		"parentid": '129900'
	},
	{
		"id": '120107',
		"shortname": '塘沽区',
		"parentid": '129900'
	},
	{
		"id": '120108',
		"shortname": '汉沽区',
		"parentid": '129900'
	},
	{
		"id": '120109',
		"shortname": '大港区',
		"parentid": '129900'
	},
	{
		"id": '120110',
		"shortname": '东丽区',
		"parentid": '129900'
	},
	{
		"id": '120111',
		"shortname": '西青区',
		"parentid": '129900'
	},
	{
		"id": '120112',
		"shortname": '津南区',
		"parentid": '129900'
	},
	{
		"id": '120113',
		"shortname": '北辰区',
		"parentid": '129900'
	},
	{
		"id": '120221',
		"shortname": '宁河县',
		"parentid": '129900'
	},
	{
		"id": '130103',
		"shortname": '桥东区',
		"parentid": '130100'
	},
	{
		"id": '130104',
		"shortname": '桥西区',
		"parentid": '130100'
	},
	{
		"id": '130105',
		"shortname": '新华区',
		"parentid": '130100'
	},
	{
		"id": '130107',
		"shortname": '井陉矿区',
		"parentid": '130100'
	},
	{
		"id": '130108',
		"shortname": '裕华区',
		"parentid": '130100'
	},
	{
		"id": '130121',
		"shortname": '井陉县',
		"parentid": '130100'
	},
	{
		"id": '130123',
		"shortname": '正定县',
		"parentid": '130100'
	},
	{
		"id": '130124',
		"shortname": '栾城县',
		"parentid": '130100'
	},
	{
		"id": '130125',
		"shortname": '行唐县',
		"parentid": '130100'
	},
	{
		"id": '130126',
		"shortname": '灵寿县',
		"parentid": '130100'
	},
	{
		"id": '130127',
		"shortname": '高邑县',
		"parentid": '130100'
	},
	{
		"id": '130128',
		"shortname": '深泽县',
		"parentid": '130100'
	},
	{
		"id": '130129',
		"shortname": '赞皇县',
		"parentid": '130100'
	},
	{
		"id": '130130',
		"shortname": '无极县',
		"parentid": '130100'
	},
	{
		"id": '130131',
		"shortname": '平山县',
		"parentid": '130100'
	},
	{
		"id": '130132',
		"shortname": '元氏县',
		"parentid": '130100'
	},
	{
		"id": '130133',
		"shortname": '赵县',
		"parentid": '130100'
	},
	{
		"id": '130140',
		"shortname": '开发区',
		"parentid": '130100'
	},
	{
		"id": '130141',
		"shortname": '正定新区',
		"parentid": '130100'
	},
	{
		"id": '130181',
		"shortname": '辛集市',
		"parentid": '130100'
	},
	{
		"id": '130182',
		"shortname": '藁城市',
		"parentid": '130100'
	},
	{
		"id": '130183',
		"shortname": '晋州市',
		"parentid": '130100'
	},
	{
		"id": '130184',
		"shortname": '新乐市',
		"parentid": '130100'
	},
	{
		"id": '130185',
		"shortname": '鹿泉市',
		"parentid": '130100'
	},
	{
		"id": '130102',
		"shortname": '长安区',
		"parentid": '130100'
	},
	{
		"id": '130202',
		"shortname": '路南区',
		"parentid": '130200'
	},
	{
		"id": '130203',
		"shortname": '路北区',
		"parentid": '130200'
	},
	{
		"id": '130204',
		"shortname": '古冶区',
		"parentid": '130200'
	},
	{
		"id": '130205',
		"shortname": '开平区',
		"parentid": '130200'
	},
	{
		"id": '130207',
		"shortname": '丰南区',
		"parentid": '130200'
	},
	{
		"id": '130208',
		"shortname": '丰润区',
		"parentid": '130200'
	},
	{
		"id": '130223',
		"shortname": '滦县',
		"parentid": '130200'
	},
	{
		"id": '130224',
		"shortname": '滦南县',
		"parentid": '130200'
	},
	{
		"id": '130225',
		"shortname": '乐亭县',
		"parentid": '130200'
	},
	{
		"id": '130227',
		"shortname": '迁西县',
		"parentid": '130200'
	},
	{
		"id": '130229',
		"shortname": '玉田县',
		"parentid": '130200'
	},
	{
		"id": '130230',
		"shortname": '唐海县',
		"parentid": '130200'
	},
	{
		"id": '130240',
		"shortname": '高新技术开发区',
		"parentid": '130200'
	},
	{
		"id": '130241',
		"shortname": '南堡开发区',
		"parentid": '130200'
	},
	{
		"id": '130242',
		"shortname": '海港开发区',
		"parentid": '130200'
	},
	{
		"id": '130243',
		"shortname": '芦台开发区',
		"parentid": '130200'
	},
	{
		"id": '130244',
		"shortname": '汉沽开发区',
		"parentid": '130200'
	},
	{
		"id": '130245',
		"shortname": '曹妃甸工业区',
		"parentid": '130200'
	},
	{
		"id": '130281',
		"shortname": '遵化市',
		"parentid": '130200'
	},
	{
		"id": '130283',
		"shortname": '迁安市',
		"parentid": '130200'
	},
	{
		"id": '130302',
		"shortname": '海港区',
		"parentid": '130300'
	},
	{
		"id": '130303',
		"shortname": '山海关区',
		"parentid": '130300'
	},
	{
		"id": '130304',
		"shortname": '北戴河区',
		"parentid": '130300'
	},
	{
		"id": '130321',
		"shortname": '青龙满族自治县',
		"parentid": '130300'
	},
	{
		"id": '130322',
		"shortname": '昌黎县',
		"parentid": '130300'
	},
	{
		"id": '130323',
		"shortname": '抚宁县',
		"parentid": '130300'
	},
	{
		"id": '130324',
		"shortname": '卢龙县',
		"parentid": '130300'
	},
	{
		"id": '130340',
		"shortname": '开发区',
		"parentid": '130300'
	},
	{
		"id": '130440',
		"shortname": '开发区',
		"parentid": '130400'
	},
	{
		"id": '130441',
		"shortname": '马头生态工业城',
		"parentid": '130400'
	},
	{
		"id": '130481',
		"shortname": '武安市',
		"parentid": '130400'
	},
	{
		"id": '130402',
		"shortname": '邯山区',
		"parentid": '130400'
	},
	{
		"id": '130403',
		"shortname": '丛台区',
		"parentid": '130400'
	},
	{
		"id": '130404',
		"shortname": '复兴区',
		"parentid": '130400'
	},
	{
		"id": '130406',
		"shortname": '峰峰矿区',
		"parentid": '130400'
	},
	{
		"id": '130421',
		"shortname": '邯郸县',
		"parentid": '130400'
	},
	{
		"id": '130423',
		"shortname": '临漳县',
		"parentid": '130400'
	},
	{
		"id": '130424',
		"shortname": '成安县',
		"parentid": '130400'
	},
	{
		"id": '130425',
		"shortname": '大名县',
		"parentid": '130400'
	},
	{
		"id": '130426',
		"shortname": '涉县',
		"parentid": '130400'
	},
	{
		"id": '130427',
		"shortname": '磁县',
		"parentid": '130400'
	},
	{
		"id": '130428',
		"shortname": '肥乡县',
		"parentid": '130400'
	},
	{
		"id": '130429',
		"shortname": '永年县',
		"parentid": '130400'
	},
	{
		"id": '130430',
		"shortname": '邱县',
		"parentid": '130400'
	},
	{
		"id": '130431',
		"shortname": '鸡泽县',
		"parentid": '130400'
	},
	{
		"id": '130432',
		"shortname": '广平县',
		"parentid": '130400'
	},
	{
		"id": '130433',
		"shortname": '馆陶县',
		"parentid": '130400'
	},
	{
		"id": '130434',
		"shortname": '魏县',
		"parentid": '130400'
	},
	{
		"id": '130435',
		"shortname": '曲周县',
		"parentid": '130400'
	},
	{
		"id": '130581',
		"shortname": '南宫市',
		"parentid": '130500'
	},
	{
		"id": '130582',
		"shortname": '沙河市',
		"parentid": '130500'
	},
	{
		"id": '130502',
		"shortname": '桥东区',
		"parentid": '130500'
	},
	{
		"id": '130503',
		"shortname": '桥西区',
		"parentid": '130500'
	},
	{
		"id": '130521',
		"shortname": '邢台县',
		"parentid": '130500'
	},
	{
		"id": '130522',
		"shortname": '临城县',
		"parentid": '130500'
	},
	{
		"id": '130523',
		"shortname": '内丘县',
		"parentid": '130500'
	},
	{
		"id": '130524',
		"shortname": '柏乡县',
		"parentid": '130500'
	},
	{
		"id": '130525',
		"shortname": '隆尧县',
		"parentid": '130500'
	},
	{
		"id": '130526',
		"shortname": '任县',
		"parentid": '130500'
	},
	{
		"id": '130527',
		"shortname": '南和县',
		"parentid": '130500'
	},
	{
		"id": '130528',
		"shortname": '宁晋县',
		"parentid": '130500'
	},
	{
		"id": '130529',
		"shortname": '巨鹿县',
		"parentid": '130500'
	},
	{
		"id": '130530',
		"shortname": '新河县',
		"parentid": '130500'
	},
	{
		"id": '130531',
		"shortname": '广宗县',
		"parentid": '130500'
	},
	{
		"id": '130532',
		"shortname": '平乡县',
		"parentid": '130500'
	},
	{
		"id": '130533',
		"shortname": '威县',
		"parentid": '130500'
	},
	{
		"id": '130534',
		"shortname": '清河县',
		"parentid": '130500'
	},
	{
		"id": '130535',
		"shortname": '临西县',
		"parentid": '130500'
	},
	{
		"id": '130540',
		"shortname": '高开区',
		"parentid": '130500'
	},
	{
		"id": '130541',
		"shortname": '大曹庄',
		"parentid": '130500'
	},
	{
		"id": '130602',
		"shortname": '竞秀区',
		"parentid": '130600'
	},
	{
		"id": '130604',
		"shortname": '南市区',
		"parentid": '130600'
	},
	{
		"id": '130621',
		"shortname": '满城县',
		"parentid": '130600'
	},
	{
		"id": '130622',
		"shortname": '清苑县',
		"parentid": '130600'
	},
	{
		"id": '130623',
		"shortname": '涞水县',
		"parentid": '130600'
	},
	{
		"id": '130624',
		"shortname": '阜平县',
		"parentid": '130600'
	},
	{
		"id": '130625',
		"shortname": '徐水县',
		"parentid": '130600'
	},
	{
		"id": '130626',
		"shortname": '定兴县',
		"parentid": '130600'
	},
	{
		"id": '130627',
		"shortname": '唐县',
		"parentid": '130600'
	},
	{
		"id": '130628',
		"shortname": '高阳县',
		"parentid": '130600'
	},
	{
		"id": '130629',
		"shortname": '容城县',
		"parentid": '130600'
	},
	{
		"id": '130630',
		"shortname": '涞源县',
		"parentid": '130600'
	},
	{
		"id": '130631',
		"shortname": '望都县',
		"parentid": '130600'
	},
	{
		"id": '130632',
		"shortname": '安新县',
		"parentid": '130600'
	},
	{
		"id": '130633',
		"shortname": '易县',
		"parentid": '130600'
	},
	{
		"id": '130634',
		"shortname": '曲阳县',
		"parentid": '130600'
	},
	{
		"id": '130635',
		"shortname": '蠡县',
		"parentid": '130600'
	},
	{
		"id": '130636',
		"shortname": '顺平县',
		"parentid": '130600'
	},
	{
		"id": '130637',
		"shortname": '博野县',
		"parentid": '130600'
	},
	{
		"id": '130638',
		"shortname": '雄县',
		"parentid": '130600'
	},
	{
		"id": '130640',
		"shortname": '温泉城经济区',
		"parentid": '130600'
	},
	{
		"id": '130641',
		"shortname": '高新区',
		"parentid": '130600'
	},
	{
		"id": '130681',
		"shortname": '涿州市',
		"parentid": '130600'
	},
	{
		"id": '130682',
		"shortname": '定州市',
		"parentid": '130600'
	},
	{
		"id": '130683',
		"shortname": '安国市',
		"parentid": '130600'
	},
	{
		"id": '130684',
		"shortname": '高碑店市',
		"parentid": '130600'
	},
	{
		"id": '130603',
		"shortname": '北市区',
		"parentid": '130600'
	},
	{
		"id": '130702',
		"shortname": '桥东区',
		"parentid": '130700'
	},
	{
		"id": '130703',
		"shortname": '桥西区',
		"parentid": '130700'
	},
	{
		"id": '130705',
		"shortname": '宣化区',
		"parentid": '130700'
	},
	{
		"id": '130706',
		"shortname": '下花园区',
		"parentid": '130700'
	},
	{
		"id": '130721',
		"shortname": '宣化县',
		"parentid": '130700'
	},
	{
		"id": '130722',
		"shortname": '张北县',
		"parentid": '130700'
	},
	{
		"id": '130723',
		"shortname": '康保县',
		"parentid": '130700'
	},
	{
		"id": '130724',
		"shortname": '沽源县',
		"parentid": '130700'
	},
	{
		"id": '130725',
		"shortname": '尚义县',
		"parentid": '130700'
	},
	{
		"id": '130726',
		"shortname": '蔚县',
		"parentid": '130700'
	},
	{
		"id": '130727',
		"shortname": '阳原县',
		"parentid": '130700'
	},
	{
		"id": '130728',
		"shortname": '怀安县',
		"parentid": '130700'
	},
	{
		"id": '130729',
		"shortname": '万全县',
		"parentid": '130700'
	},
	{
		"id": '130730',
		"shortname": '怀来县',
		"parentid": '130700'
	},
	{
		"id": '130731',
		"shortname": '涿鹿县',
		"parentid": '130700'
	},
	{
		"id": '130732',
		"shortname": '赤城县',
		"parentid": '130700'
	},
	{
		"id": '130733',
		"shortname": '崇礼县',
		"parentid": '130700'
	},
	{
		"id": '130740',
		"shortname": '高新区',
		"parentid": '130700'
	},
	{
		"id": '130741',
		"shortname": '察北管理区',
		"parentid": '130700'
	},
	{
		"id": '130742',
		"shortname": '塞北管理区',
		"parentid": '130700'
	},
	{
		"id": '130802',
		"shortname": '双桥区',
		"parentid": '130800'
	},
	{
		"id": '130803',
		"shortname": '双滦区',
		"parentid": '130800'
	},
	{
		"id": '130804',
		"shortname": '鹰手营子矿区',
		"parentid": '130800'
	},
	{
		"id": '130821',
		"shortname": '承德县',
		"parentid": '130800'
	},
	{
		"id": '130822',
		"shortname": '兴隆县',
		"parentid": '130800'
	},
	{
		"id": '130823',
		"shortname": '平泉县',
		"parentid": '130800'
	},
	{
		"id": '130824',
		"shortname": '滦平县',
		"parentid": '130800'
	},
	{
		"id": '130825',
		"shortname": '隆化县',
		"parentid": '130800'
	},
	{
		"id": '130826',
		"shortname": '丰宁满族自治县',
		"parentid": '130800'
	},
	{
		"id": '130827',
		"shortname": '宽城满族自治县',
		"parentid": '130800'
	},
	{
		"id": '130828',
		"shortname": '围场满族蒙古族自治县',
		"parentid": '130800'
	},
	{
		"id": '130941',
		"shortname": '沧州临港经济技术开发区',
		"parentid": '130900'
	},
	{
		"id": '130942',
		"shortname": '沧州市南大港管理区',
		"parentid": '130900'
	},
	{
		"id": '130943',
		"shortname": '沧州经济技术开发区',
		"parentid": '130900'
	},
	{
		"id": '130981',
		"shortname": '泊头市',
		"parentid": '130900'
	},
	{
		"id": '130982',
		"shortname": '任丘市',
		"parentid": '130900'
	},
	{
		"id": '130983',
		"shortname": '黄骅市',
		"parentid": '130900'
	},
	{
		"id": '130984',
		"shortname": '河间市',
		"parentid": '130900'
	},
	{
		"id": '130902',
		"shortname": '新华区',
		"parentid": '130900'
	},
	{
		"id": '130903',
		"shortname": '运河区',
		"parentid": '130900'
	},
	{
		"id": '130921',
		"shortname": '沧县',
		"parentid": '130900'
	},
	{
		"id": '130922',
		"shortname": '青县',
		"parentid": '130900'
	},
	{
		"id": '130923',
		"shortname": '东光县',
		"parentid": '130900'
	},
	{
		"id": '130924',
		"shortname": '海兴县',
		"parentid": '130900'
	},
	{
		"id": '130925',
		"shortname": '盐山县',
		"parentid": '130900'
	},
	{
		"id": '130926',
		"shortname": '肃宁县',
		"parentid": '130900'
	},
	{
		"id": '130927',
		"shortname": '南皮县',
		"parentid": '130900'
	},
	{
		"id": '130928',
		"shortname": '吴桥县',
		"parentid": '130900'
	},
	{
		"id": '130929',
		"shortname": '献县',
		"parentid": '130900'
	},
	{
		"id": '130930',
		"shortname": '孟村回族自治县',
		"parentid": '130900'
	},
	{
		"id": '130940',
		"shortname": '黄骅港开发区',
		"parentid": '130900'
	},
	{
		"id": '131002',
		"shortname": '安次区',
		"parentid": '131000'
	},
	{
		"id": '131003',
		"shortname": '广阳区',
		"parentid": '131000'
	},
	{
		"id": '131022',
		"shortname": '固安县',
		"parentid": '131000'
	},
	{
		"id": '131023',
		"shortname": '永清县',
		"parentid": '131000'
	},
	{
		"id": '131024',
		"shortname": '香河县',
		"parentid": '131000'
	},
	{
		"id": '131025',
		"shortname": '大城县',
		"parentid": '131000'
	},
	{
		"id": '131026',
		"shortname": '文安县',
		"parentid": '131000'
	},
	{
		"id": '131028',
		"shortname": '大厂回族自治县',
		"parentid": '131000'
	},
	{
		"id": '131040',
		"shortname": '开发区',
		"parentid": '131000'
	},
	{
		"id": '131081',
		"shortname": '霸州市',
		"parentid": '131000'
	},
	{
		"id": '131082',
		"shortname": '三河市',
		"parentid": '131000'
	},
	{
		"id": '131102',
		"shortname": '桃城区',
		"parentid": '131100'
	},
	{
		"id": '131121',
		"shortname": '枣强县',
		"parentid": '131100'
	},
	{
		"id": '131122',
		"shortname": '武邑县',
		"parentid": '131100'
	},
	{
		"id": '131123',
		"shortname": '武强县',
		"parentid": '131100'
	},
	{
		"id": '131124',
		"shortname": '饶阳县',
		"parentid": '131100'
	},
	{
		"id": '131125',
		"shortname": '安平县',
		"parentid": '131100'
	},
	{
		"id": '131126',
		"shortname": '故城县',
		"parentid": '131100'
	},
	{
		"id": '131127',
		"shortname": '景县',
		"parentid": '131100'
	},
	{
		"id": '131128',
		"shortname": '阜城县',
		"parentid": '131100'
	},
	{
		"id": '131140',
		"shortname": '开发区',
		"parentid": '131100'
	},
	{
		"id": '131181',
		"shortname": '冀州市',
		"parentid": '131100'
	},
	{
		"id": '131182',
		"shortname": '深州市',
		"parentid": '131100'
	},
	{
		"id": '140105',
		"shortname": '小店区',
		"parentid": '140100'
	},
	{
		"id": '140106',
		"shortname": '迎泽区',
		"parentid": '140100'
	},
	{
		"id": '140107',
		"shortname": '杏花岭区',
		"parentid": '140100'
	},
	{
		"id": '140108',
		"shortname": '尖草坪区',
		"parentid": '140100'
	},
	{
		"id": '140109',
		"shortname": '万柏林区',
		"parentid": '140100'
	},
	{
		"id": '140110',
		"shortname": '晋源区',
		"parentid": '140100'
	},
	{
		"id": '140122',
		"shortname": '阳曲县',
		"parentid": '140100'
	},
	{
		"id": '140123',
		"shortname": '娄烦县',
		"parentid": '140100'
	},
	{
		"id": '140181',
		"shortname": '古交市',
		"parentid": '140100'
	},
	{
		"id": '140121',
		"shortname": '清徐县',
		"parentid": '140100'
	},
	{
		"id": '140202',
		"shortname": '城区',
		"parentid": '140200'
	},
	{
		"id": '140203',
		"shortname": '矿区',
		"parentid": '140200'
	},
	{
		"id": '140211',
		"shortname": '南郊区',
		"parentid": '140200'
	},
	{
		"id": '140221',
		"shortname": '阳高县',
		"parentid": '140200'
	},
	{
		"id": '140222',
		"shortname": '天镇县',
		"parentid": '140200'
	},
	{
		"id": '140223',
		"shortname": '广灵县',
		"parentid": '140200'
	},
	{
		"id": '140224',
		"shortname": '灵丘县',
		"parentid": '140200'
	},
	{
		"id": '140226',
		"shortname": '左云县',
		"parentid": '140200'
	},
	{
		"id": '140227',
		"shortname": '大同县',
		"parentid": '140200'
	},
	{
		"id": '140240',
		"shortname": '开发区',
		"parentid": '140200'
	},
	{
		"id": '140212',
		"shortname": '新荣区',
		"parentid": '140200'
	},
	{
		"id": '140225',
		"shortname": '浑源县',
		"parentid": '140200'
	},
	{
		"id": '140302',
		"shortname": '城区',
		"parentid": '140300'
	},
	{
		"id": '140303',
		"shortname": '矿区',
		"parentid": '140300'
	},
	{
		"id": '140311',
		"shortname": '郊区',
		"parentid": '140300'
	},
	{
		"id": '140322',
		"shortname": '盂县',
		"parentid": '140300'
	},
	{
		"id": '140340',
		"shortname": '开发区',
		"parentid": '140300'
	},
	{
		"id": '140321',
		"shortname": '平定县',
		"parentid": '140300'
	},
	{
		"id": '140402',
		"shortname": '城区',
		"parentid": '140400'
	},
	{
		"id": '140411',
		"shortname": '郊区',
		"parentid": '140400'
	},
	{
		"id": '140421',
		"shortname": '长治县',
		"parentid": '140400'
	},
	{
		"id": '140423',
		"shortname": '襄垣县',
		"parentid": '140400'
	},
	{
		"id": '140424',
		"shortname": '屯留县',
		"parentid": '140400'
	},
	{
		"id": '140425',
		"shortname": '平顺县',
		"parentid": '140400'
	},
	{
		"id": '140426',
		"shortname": '黎城县',
		"parentid": '140400'
	},
	{
		"id": '140427',
		"shortname": '壶关县',
		"parentid": '140400'
	},
	{
		"id": '140428',
		"shortname": '长子县',
		"parentid": '140400'
	},
	{
		"id": '140429',
		"shortname": '武乡县',
		"parentid": '140400'
	},
	{
		"id": '140430',
		"shortname": '沁县',
		"parentid": '140400'
	},
	{
		"id": '140431',
		"shortname": '沁源县',
		"parentid": '140400'
	},
	{
		"id": '140440',
		"shortname": '开发区',
		"parentid": '140400'
	},
	{
		"id": '140481',
		"shortname": '潞城市',
		"parentid": '140400'
	},
	{
		"id": '140502',
		"shortname": '城区',
		"parentid": '140500'
	},
	{
		"id": '140521',
		"shortname": '沁水县',
		"parentid": '140500'
	},
	{
		"id": '140522',
		"shortname": '阳城县',
		"parentid": '140500'
	},
	{
		"id": '140524',
		"shortname": '陵川县',
		"parentid": '140500'
	},
	{
		"id": '140525',
		"shortname": '泽州县',
		"parentid": '140500'
	},
	{
		"id": '140581',
		"shortname": '高平市',
		"parentid": '140500'
	},
	{
		"id": '140602',
		"shortname": '朔城区',
		"parentid": '140600'
	},
	{
		"id": '140603',
		"shortname": '平鲁区',
		"parentid": '140600'
	},
	{
		"id": '140621',
		"shortname": '山阴县',
		"parentid": '140600'
	},
	{
		"id": '140622',
		"shortname": '应县',
		"parentid": '140600'
	},
	{
		"id": '140623',
		"shortname": '右玉县',
		"parentid": '140600'
	},
	{
		"id": '140624',
		"shortname": '怀仁县',
		"parentid": '140600'
	},
	{
		"id": '140702',
		"shortname": '榆次区',
		"parentid": '140700'
	},
	{
		"id": '140721',
		"shortname": '榆社县',
		"parentid": '140700'
	},
	{
		"id": '140722',
		"shortname": '左权县',
		"parentid": '140700'
	},
	{
		"id": '140723',
		"shortname": '和顺县',
		"parentid": '140700'
	},
	{
		"id": '140724',
		"shortname": '昔阳县',
		"parentid": '140700'
	},
	{
		"id": '140725',
		"shortname": '寿阳县',
		"parentid": '140700'
	},
	{
		"id": '140726',
		"shortname": '太谷县',
		"parentid": '140700'
	},
	{
		"id": '140727',
		"shortname": '祁县',
		"parentid": '140700'
	},
	{
		"id": '140728',
		"shortname": '平遥县',
		"parentid": '140700'
	},
	{
		"id": '140729',
		"shortname": '灵石县',
		"parentid": '140700'
	},
	{
		"id": '140740',
		"shortname": '榆次经济技术开发区',
		"parentid": '140700'
	},
	{
		"id": '140781',
		"shortname": '介休市',
		"parentid": '140700'
	},
	{
		"id": '140830',
		"shortname": '芮城县',
		"parentid": '140800'
	},
	{
		"id": '140881',
		"shortname": '永济市',
		"parentid": '140800'
	},
	{
		"id": '140882',
		"shortname": '河津市',
		"parentid": '140800'
	},
	{
		"id": '140802',
		"shortname": '盐湖区',
		"parentid": '140800'
	},
	{
		"id": '140821',
		"shortname": '临猗县',
		"parentid": '140800'
	},
	{
		"id": '140822',
		"shortname": '万荣县',
		"parentid": '140800'
	},
	{
		"id": '140823',
		"shortname": '闻喜县',
		"parentid": '140800'
	},
	{
		"id": '140824',
		"shortname": '稷山县',
		"parentid": '140800'
	},
	{
		"id": '140825',
		"shortname": '新绛县',
		"parentid": '140800'
	},
	{
		"id": '140826',
		"shortname": '绛县',
		"parentid": '140800'
	},
	{
		"id": '140827',
		"shortname": '垣曲县',
		"parentid": '140800'
	},
	{
		"id": '140828',
		"shortname": '夏县',
		"parentid": '140800'
	},
	{
		"id": '140829',
		"shortname": '平陆县',
		"parentid": '140800'
	},
	{
		"id": '140902',
		"shortname": '忻府区',
		"parentid": '140900'
	},
	{
		"id": '140921',
		"shortname": '定襄县',
		"parentid": '140900'
	},
	{
		"id": '140922',
		"shortname": '五台县',
		"parentid": '140900'
	},
	{
		"id": '140923',
		"shortname": '代县',
		"parentid": '140900'
	},
	{
		"id": '140924',
		"shortname": '繁峙县',
		"parentid": '140900'
	},
	{
		"id": '140925',
		"shortname": '宁武县',
		"parentid": '140900'
	},
	{
		"id": '140926',
		"shortname": '静乐县',
		"parentid": '140900'
	},
	{
		"id": '140927',
		"shortname": '神池县',
		"parentid": '140900'
	},
	{
		"id": '140928',
		"shortname": '五寨县',
		"parentid": '140900'
	},
	{
		"id": '140929',
		"shortname": '岢岚县',
		"parentid": '140900'
	},
	{
		"id": '140930',
		"shortname": '河曲县',
		"parentid": '140900'
	},
	{
		"id": '140931',
		"shortname": '保德县',
		"parentid": '140900'
	},
	{
		"id": '140932',
		"shortname": '偏关县',
		"parentid": '140900'
	},
	{
		"id": '140981',
		"shortname": '原平市',
		"parentid": '140900'
	},
	{
		"id": '141002',
		"shortname": '尧都区',
		"parentid": '141000'
	},
	{
		"id": '141021',
		"shortname": '曲沃县',
		"parentid": '141000'
	},
	{
		"id": '141022',
		"shortname": '翼城县',
		"parentid": '141000'
	},
	{
		"id": '141023',
		"shortname": '襄汾县',
		"parentid": '141000'
	},
	{
		"id": '141024',
		"shortname": '洪洞县',
		"parentid": '141000'
	},
	{
		"id": '141025',
		"shortname": '古县',
		"parentid": '141000'
	},
	{
		"id": '141027',
		"shortname": '浮山县',
		"parentid": '141000'
	},
	{
		"id": '141028',
		"shortname": '吉县',
		"parentid": '141000'
	},
	{
		"id": '141029',
		"shortname": '乡宁县',
		"parentid": '141000'
	},
	{
		"id": '141030',
		"shortname": '大宁县',
		"parentid": '141000'
	},
	{
		"id": '141031',
		"shortname": '隰县',
		"parentid": '141000'
	},
	{
		"id": '141032',
		"shortname": '永和县',
		"parentid": '141000'
	},
	{
		"id": '141033',
		"shortname": '蒲县',
		"parentid": '141000'
	},
	{
		"id": '141034',
		"shortname": '汾西县',
		"parentid": '141000'
	},
	{
		"id": '141081',
		"shortname": '侯马市',
		"parentid": '141000'
	},
	{
		"id": '141082',
		"shortname": '霍州市',
		"parentid": '141000'
	},
	{
		"id": '141026',
		"shortname": '安泽县',
		"parentid": '141000'
	},
	{
		"id": '141130',
		"shortname": '交口县',
		"parentid": '141100'
	},
	{
		"id": '141125',
		"shortname": '柳林县',
		"parentid": '141100'
	},
	{
		"id": '141126',
		"shortname": '石楼县',
		"parentid": '141100'
	},
	{
		"id": '141127',
		"shortname": '岚县',
		"parentid": '141100'
	},
	{
		"id": '141128',
		"shortname": '方山县',
		"parentid": '141100'
	},
	{
		"id": '141129',
		"shortname": '中阳县',
		"parentid": '141100'
	},
	{
		"id": '141181',
		"shortname": '孝义市',
		"parentid": '141100'
	},
	{
		"id": '141182',
		"shortname": '汾阳市',
		"parentid": '141100'
	},
	{
		"id": '141102',
		"shortname": '离石区',
		"parentid": '141100'
	},
	{
		"id": '141121',
		"shortname": '文水县',
		"parentid": '141100'
	},
	{
		"id": '141122',
		"shortname": '交城县',
		"parentid": '141100'
	},
	{
		"id": '141123',
		"shortname": '兴县',
		"parentid": '141100'
	},
	{
		"id": '141124',
		"shortname": '临县',
		"parentid": '141100'
	},
	{
		"id": '150102',
		"shortname": '新城区',
		"parentid": '150100'
	},
	{
		"id": '150103',
		"shortname": '回民区',
		"parentid": '150100'
	},
	{
		"id": '150104',
		"shortname": '玉泉区',
		"parentid": '150100'
	},
	{
		"id": '150105',
		"shortname": '赛罕区',
		"parentid": '150100'
	},
	{
		"id": '150121',
		"shortname": '土默特左旗',
		"parentid": '150100'
	},
	{
		"id": '150122',
		"shortname": '托克托县',
		"parentid": '150100'
	},
	{
		"id": '150123',
		"shortname": '和林格尔县',
		"parentid": '150100'
	},
	{
		"id": '150124',
		"shortname": '清水河县',
		"parentid": '150100'
	},
	{
		"id": '150125',
		"shortname": '武川县',
		"parentid": '150100'
	},
	{
		"id": '150202',
		"shortname": '东河区',
		"parentid": '150200'
	},
	{
		"id": '150203',
		"shortname": '昆都仑区',
		"parentid": '150200'
	},
	{
		"id": '150204',
		"shortname": '青山区',
		"parentid": '150200'
	},
	{
		"id": '150205',
		"shortname": '石拐区',
		"parentid": '150200'
	},
	{
		"id": '150206',
		"shortname": '白云鄂博矿区',
		"parentid": '150200'
	},
	{
		"id": '150207',
		"shortname": '九原区',
		"parentid": '150200'
	},
	{
		"id": '150221',
		"shortname": '土默特右旗',
		"parentid": '150200'
	},
	{
		"id": '150222',
		"shortname": '固阳县',
		"parentid": '150200'
	},
	{
		"id": '150223',
		"shortname": '达尔罕茂明安联合旗',
		"parentid": '150200'
	},
	{
		"id": '150240',
		"shortname": '稀土高新技术产业开发区',
		"parentid": '150200'
	},
	{
		"id": '150302',
		"shortname": '海勃湾区',
		"parentid": '150300'
	},
	{
		"id": '150303',
		"shortname": '海南区',
		"parentid": '150300'
	},
	{
		"id": '150304',
		"shortname": '乌达区',
		"parentid": '150300'
	},
	{
		"id": '150402',
		"shortname": '红山区',
		"parentid": '150400'
	},
	{
		"id": '150403',
		"shortname": '元宝山区',
		"parentid": '150400'
	},
	{
		"id": '150404',
		"shortname": '松山区',
		"parentid": '150400'
	},
	{
		"id": '150421',
		"shortname": '阿鲁科尔沁旗',
		"parentid": '150400'
	},
	{
		"id": '150422',
		"shortname": '巴林左旗',
		"parentid": '150400'
	},
	{
		"id": '150423',
		"shortname": '巴林右旗',
		"parentid": '150400'
	},
	{
		"id": '150424',
		"shortname": '林西县',
		"parentid": '150400'
	},
	{
		"id": '150425',
		"shortname": '克什克腾旗',
		"parentid": '150400'
	},
	{
		"id": '150426',
		"shortname": '翁牛特旗',
		"parentid": '150400'
	},
	{
		"id": '150428',
		"shortname": '喀喇沁旗',
		"parentid": '150400'
	},
	{
		"id": '150429',
		"shortname": '宁城县',
		"parentid": '150400'
	},
	{
		"id": '150430',
		"shortname": '敖汉旗',
		"parentid": '150400'
	},
	{
		"id": '150502',
		"shortname": '科尔沁区',
		"parentid": '150500'
	},
	{
		"id": '150521',
		"shortname": '科尔沁左翼中旗',
		"parentid": '150500'
	},
	{
		"id": '150522',
		"shortname": '科尔沁左翼后旗',
		"parentid": '150500'
	},
	{
		"id": '150523',
		"shortname": '开鲁县',
		"parentid": '150500'
	},
	{
		"id": '150524',
		"shortname": '库伦旗',
		"parentid": '150500'
	},
	{
		"id": '150525',
		"shortname": '奈曼旗',
		"parentid": '150500'
	},
	{
		"id": '150526',
		"shortname": '扎鲁特旗',
		"parentid": '150500'
	},
	{
		"id": '150581',
		"shortname": '霍林郭勒市',
		"parentid": '150500'
	},
	{
		"id": '150602',
		"shortname": '东胜区',
		"parentid": '150600'
	},
	{
		"id": '150621',
		"shortname": '达拉特旗',
		"parentid": '150600'
	},
	{
		"id": '150622',
		"shortname": '准格尔旗',
		"parentid": '150600'
	},
	{
		"id": '150623',
		"shortname": '鄂托克前旗',
		"parentid": '150600'
	},
	{
		"id": '150624',
		"shortname": '鄂托克旗',
		"parentid": '150600'
	},
	{
		"id": '150625',
		"shortname": '杭锦旗',
		"parentid": '150600'
	},
	{
		"id": '150626',
		"shortname": '乌审旗',
		"parentid": '150600'
	},
	{
		"id": '150627',
		"shortname": '伊金霍洛旗',
		"parentid": '150600'
	},
	{
		"id": '150640',
		"shortname": '康巴什新区',
		"parentid": '150600'
	},
	{
		"id": '150702',
		"shortname": '海拉尔区',
		"parentid": '150700'
	},
	{
		"id": '150721',
		"shortname": '阿荣旗',
		"parentid": '150700'
	},
	{
		"id": '150722',
		"shortname": '莫力达瓦达斡尔族自治旗',
		"parentid": '150700'
	},
	{
		"id": '150723',
		"shortname": '鄂伦春自治旗',
		"parentid": '150700'
	},
	{
		"id": '150724',
		"shortname": '鄂温克族自治旗',
		"parentid": '150700'
	},
	{
		"id": '150725',
		"shortname": '陈巴尔虎旗',
		"parentid": '150700'
	},
	{
		"id": '150726',
		"shortname": '新巴尔虎左旗',
		"parentid": '150700'
	},
	{
		"id": '150727',
		"shortname": '新巴尔虎右旗',
		"parentid": '150700'
	},
	{
		"id": '150740',
		"shortname": '海拉尔农牧场',
		"parentid": '150700'
	},
	{
		"id": '150741',
		"shortname": '大兴安岭农牧场',
		"parentid": '150700'
	},
	{
		"id": '150781',
		"shortname": '满洲里市',
		"parentid": '150700'
	},
	{
		"id": '150782',
		"shortname": '牙克石市',
		"parentid": '150700'
	},
	{
		"id": '150783',
		"shortname": '扎兰屯市',
		"parentid": '150700'
	},
	{
		"id": '150784',
		"shortname": '额尔古纳市',
		"parentid": '150700'
	},
	{
		"id": '150785',
		"shortname": '根河市',
		"parentid": '150700'
	},
	{
		"id": '150821',
		"shortname": '五原县',
		"parentid": '150800'
	},
	{
		"id": '150822',
		"shortname": '磴口县',
		"parentid": '150800'
	},
	{
		"id": '150823',
		"shortname": '乌拉特前旗',
		"parentid": '150800'
	},
	{
		"id": '150824',
		"shortname": '乌拉特中旗',
		"parentid": '150800'
	},
	{
		"id": '150825',
		"shortname": '乌拉特后旗',
		"parentid": '150800'
	},
	{
		"id": '150826',
		"shortname": '杭锦后旗',
		"parentid": '150800'
	},
	{
		"id": '150802',
		"shortname": '临河区',
		"parentid": '150800'
	},
	{
		"id": '150902',
		"shortname": '集宁区',
		"parentid": '150900'
	},
	{
		"id": '150921',
		"shortname": '卓资县',
		"parentid": '150900'
	},
	{
		"id": '150922',
		"shortname": '化德县',
		"parentid": '150900'
	},
	{
		"id": '150923',
		"shortname": '商都县',
		"parentid": '150900'
	},
	{
		"id": '150924',
		"shortname": '兴和县',
		"parentid": '150900'
	},
	{
		"id": '150925',
		"shortname": '凉城县',
		"parentid": '150900'
	},
	{
		"id": '150926',
		"shortname": '察哈尔右翼前旗',
		"parentid": '150900'
	},
	{
		"id": '150927',
		"shortname": '察哈尔右翼中旗',
		"parentid": '150900'
	},
	{
		"id": '150928',
		"shortname": '察哈尔右翼后旗',
		"parentid": '150900'
	},
	{
		"id": '150929',
		"shortname": '四子王旗',
		"parentid": '150900'
	},
	{
		"id": '150940',
		"shortname": '察哈尔工业园区',
		"parentid": '150900'
	},
	{
		"id": '150981',
		"shortname": '丰镇市',
		"parentid": '150900'
	},
	{
		"id": '152223',
		"shortname": '扎赉特旗',
		"parentid": '152200'
	},
	{
		"id": '152224',
		"shortname": '突泉县',
		"parentid": '152200'
	},
	{
		"id": '152201',
		"shortname": '乌兰浩特市',
		"parentid": '152200'
	},
	{
		"id": '152202',
		"shortname": '阿尔山市',
		"parentid": '152200'
	},
	{
		"id": '152221',
		"shortname": '科尔沁右翼前旗',
		"parentid": '152200'
	},
	{
		"id": '152222',
		"shortname": '科尔沁右翼中旗',
		"parentid": '152200'
	},
	{
		"id": '152501',
		"shortname": '二连浩特市',
		"parentid": '152500'
	},
	{
		"id": '152502',
		"shortname": '锡林浩特市',
		"parentid": '152500'
	},
	{
		"id": '152522',
		"shortname": '阿巴嘎旗',
		"parentid": '152500'
	},
	{
		"id": '152523',
		"shortname": '苏尼特左旗',
		"parentid": '152500'
	},
	{
		"id": '152524',
		"shortname": '苏尼特右旗',
		"parentid": '152500'
	},
	{
		"id": '152525',
		"shortname": '东乌珠穆沁旗',
		"parentid": '152500'
	},
	{
		"id": '152526',
		"shortname": '西乌珠穆沁旗',
		"parentid": '152500'
	},
	{
		"id": '152527',
		"shortname": '太仆寺旗',
		"parentid": '152500'
	},
	{
		"id": '152528',
		"shortname": '镶黄旗',
		"parentid": '152500'
	},
	{
		"id": '152529',
		"shortname": '正镶白旗',
		"parentid": '152500'
	},
	{
		"id": '152530',
		"shortname": '正蓝旗',
		"parentid": '152500'
	},
	{
		"id": '152531',
		"shortname": '多伦县',
		"parentid": '152500'
	},
	{
		"id": '152540',
		"shortname": '乌拉盖开发区',
		"parentid": '152500'
	},
	{
		"id": '152921',
		"shortname": '阿拉善左旗',
		"parentid": '152900'
	},
	{
		"id": '152922',
		"shortname": '阿拉善右旗',
		"parentid": '152900'
	},
	{
		"id": '152923',
		"shortname": '额济纳旗',
		"parentid": '152900'
	},
	{
		"id": '152940',
		"shortname": '阿拉善经济开发区',
		"parentid": '152900'
	},
	{
		"id": '210102',
		"shortname": '和平区',
		"parentid": '210100'
	},
	{
		"id": '210103',
		"shortname": '沈河区',
		"parentid": '210100'
	},
	{
		"id": '210104',
		"shortname": '大东区',
		"parentid": '210100'
	},
	{
		"id": '210105',
		"shortname": '皇姑区',
		"parentid": '210100'
	},
	{
		"id": '210106',
		"shortname": '铁西区',
		"parentid": '210100'
	},
	{
		"id": '210111',
		"shortname": '苏家屯区',
		"parentid": '210100'
	},
	{
		"id": '210113',
		"shortname": '沈北新区',
		"parentid": '210100'
	},
	{
		"id": '210114',
		"shortname": '于洪区',
		"parentid": '210100'
	},
	{
		"id": '210122',
		"shortname": '辽中县',
		"parentid": '210100'
	},
	{
		"id": '210123',
		"shortname": '康平县',
		"parentid": '210100'
	},
	{
		"id": '210124',
		"shortname": '法库县',
		"parentid": '210100'
	},
	{
		"id": '210140',
		"shortname": '经济技术开发区',
		"parentid": '210100'
	},
	{
		"id": '210141',
		"shortname": '浑南新区',
		"parentid": '210100'
	},
	{
		"id": '210142',
		"shortname": '棋盘山',
		"parentid": '210100'
	},
	{
		"id": '210143',
		"shortname": '蒲河新城',
		"parentid": '210100'
	},
	{
		"id": '210181',
		"shortname": '新民市',
		"parentid": '210100'
	},
	{
		"id": '210112',
		"shortname": '浑南区',
		"parentid": '210100'
	},
	{
		"id": '210202',
		"shortname": '中山区',
		"parentid": '210200'
	},
	{
		"id": '210203',
		"shortname": '西岗区',
		"parentid": '210200'
	},
	{
		"id": '210204',
		"shortname": '沙河口区',
		"parentid": '210200'
	},
	{
		"id": '210211',
		"shortname": '甘井子区',
		"parentid": '210200'
	},
	{
		"id": '210212',
		"shortname": '旅顺口区',
		"parentid": '210200'
	},
	{
		"id": '210213',
		"shortname": '金州区',
		"parentid": '210200'
	},
	{
		"id": '210224',
		"shortname": '长海县',
		"parentid": '210200'
	},
	{
		"id": '210240',
		"shortname": '开发区',
		"parentid": '210200'
	},
	{
		"id": '210241',
		"shortname": '保税区',
		"parentid": '210200'
	},
	{
		"id": '210242',
		"shortname": '长兴岛临港工业区',
		"parentid": '210200'
	},
	{
		"id": '210243',
		"shortname": '花园口经济区',
		"parentid": '210200'
	},
	{
		"id": '210281',
		"shortname": '瓦房店市',
		"parentid": '210200'
	},
	{
		"id": '210282',
		"shortname": '普兰店市',
		"parentid": '210200'
	},
	{
		"id": '210283',
		"shortname": '庄河市',
		"parentid": '210200'
	},
	{
		"id": '210302',
		"shortname": '铁东区',
		"parentid": '210300'
	},
	{
		"id": '210303',
		"shortname": '铁西区',
		"parentid": '210300'
	},
	{
		"id": '210304',
		"shortname": '立山区',
		"parentid": '210300'
	},
	{
		"id": '210311',
		"shortname": '千山区',
		"parentid": '210300'
	},
	{
		"id": '210321',
		"shortname": '台安县',
		"parentid": '210300'
	},
	{
		"id": '210323',
		"shortname": '岫岩满族自治县',
		"parentid": '210300'
	},
	{
		"id": '210381',
		"shortname": '海城市',
		"parentid": '210300'
	},
	{
		"id": '210402',
		"shortname": '新抚区',
		"parentid": '210400'
	},
	{
		"id": '210403',
		"shortname": '东洲区',
		"parentid": '210400'
	},
	{
		"id": '210404',
		"shortname": '望花区',
		"parentid": '210400'
	},
	{
		"id": '210411',
		"shortname": '顺城区',
		"parentid": '210400'
	},
	{
		"id": '210421',
		"shortname": '抚顺县',
		"parentid": '210400'
	},
	{
		"id": '210422',
		"shortname": '新宾满族自治县',
		"parentid": '210400'
	},
	{
		"id": '210423',
		"shortname": '清原满族自治县',
		"parentid": '210400'
	},
	{
		"id": '210440',
		"shortname": '抚顺经济开发区',
		"parentid": '210400'
	},
	{
		"id": '210502',
		"shortname": '平山区',
		"parentid": '210500'
	},
	{
		"id": '210503',
		"shortname": '溪湖区',
		"parentid": '210500'
	},
	{
		"id": '210504',
		"shortname": '明山区',
		"parentid": '210500'
	},
	{
		"id": '210505',
		"shortname": '南芬区',
		"parentid": '210500'
	},
	{
		"id": '210521',
		"shortname": '本溪满族自治县',
		"parentid": '210500'
	},
	{
		"id": '210522',
		"shortname": '桓仁满族自治县',
		"parentid": '210500'
	},
	{
		"id": '210602',
		"shortname": '元宝区',
		"parentid": '210600'
	},
	{
		"id": '210603',
		"shortname": '振兴区',
		"parentid": '210600'
	},
	{
		"id": '210604',
		"shortname": '振安区',
		"parentid": '210600'
	},
	{
		"id": '210624',
		"shortname": '宽甸满族自治县',
		"parentid": '210600'
	},
	{
		"id": '210681',
		"shortname": '东港市',
		"parentid": '210600'
	},
	{
		"id": '210682',
		"shortname": '凤城市',
		"parentid": '210600'
	},
	{
		"id": '210702',
		"shortname": '古塔区',
		"parentid": '210700'
	},
	{
		"id": '210703',
		"shortname": '凌河区',
		"parentid": '210700'
	},
	{
		"id": '210711',
		"shortname": '太和区',
		"parentid": '210700'
	},
	{
		"id": '210726',
		"shortname": '黑山县',
		"parentid": '210700'
	},
	{
		"id": '210727',
		"shortname": '义县',
		"parentid": '210700'
	},
	{
		"id": '210740',
		"shortname": '开发区',
		"parentid": '210700'
	},
	{
		"id": '210741',
		"shortname": '松山新区',
		"parentid": '210700'
	},
	{
		"id": '210742',
		"shortname": '锦州龙栖湾新区',
		"parentid": '210700'
	},
	{
		"id": '210781',
		"shortname": '凌海市',
		"parentid": '210700'
	},
	{
		"id": '210782',
		"shortname": '北镇市',
		"parentid": '210700'
	},
	{
		"id": '210802',
		"shortname": '站前区',
		"parentid": '210800'
	},
	{
		"id": '210803',
		"shortname": '西市区',
		"parentid": '210800'
	},
	{
		"id": '210804',
		"shortname": '鲅鱼圈区',
		"parentid": '210800'
	},
	{
		"id": '210811',
		"shortname": '老边区',
		"parentid": '210800'
	},
	{
		"id": '210840',
		"shortname": '建筑企业',
		"parentid": '210800'
	},
	{
		"id": '210881',
		"shortname": '盖州市',
		"parentid": '210800'
	},
	{
		"id": '210882',
		"shortname": '大石桥市',
		"parentid": '210800'
	},
	{
		"id": '210921',
		"shortname": '阜新蒙古族自治县',
		"parentid": '210900'
	},
	{
		"id": '210902',
		"shortname": '海州区',
		"parentid": '210900'
	},
	{
		"id": '210903',
		"shortname": '新邱区',
		"parentid": '210900'
	},
	{
		"id": '210904',
		"shortname": '太平区',
		"parentid": '210900'
	},
	{
		"id": '210905',
		"shortname": '清河门区',
		"parentid": '210900'
	},
	{
		"id": '210911',
		"shortname": '细河区',
		"parentid": '210900'
	},
	{
		"id": '210922',
		"shortname": '彰武县',
		"parentid": '210900'
	},
	{
		"id": '210940',
		"shortname": '开发区',
		"parentid": '210900'
	},
	{
		"id": '210941',
		"shortname": '矿区',
		"parentid": '210900'
	},
	{
		"id": '210942',
		"shortname": '高新区',
		"parentid": '210900'
	},
	{
		"id": '211002',
		"shortname": '白塔区',
		"parentid": '211000'
	},
	{
		"id": '211003',
		"shortname": '文圣区',
		"parentid": '211000'
	},
	{
		"id": '211004',
		"shortname": '宏伟区',
		"parentid": '211000'
	},
	{
		"id": '211005',
		"shortname": '弓长岭区',
		"parentid": '211000'
	},
	{
		"id": '211011',
		"shortname": '太子河区',
		"parentid": '211000'
	},
	{
		"id": '211021',
		"shortname": '辽阳县',
		"parentid": '211000'
	},
	{
		"id": '211081',
		"shortname": '灯塔市',
		"parentid": '211000'
	},
	{
		"id": '211102',
		"shortname": '双台子区',
		"parentid": '211100'
	},
	{
		"id": '211103',
		"shortname": '兴隆台区',
		"parentid": '211100'
	},
	{
		"id": '211121',
		"shortname": '大洼县',
		"parentid": '211100'
	},
	{
		"id": '211122',
		"shortname": '盘山县',
		"parentid": '211100'
	},
	{
		"id": '211202',
		"shortname": '银州区',
		"parentid": '211200'
	},
	{
		"id": '211204',
		"shortname": '清河区',
		"parentid": '211200'
	},
	{
		"id": '211221',
		"shortname": '铁岭县',
		"parentid": '211200'
	},
	{
		"id": '211223',
		"shortname": '西丰县',
		"parentid": '211200'
	},
	{
		"id": '211224',
		"shortname": '昌图县',
		"parentid": '211200'
	},
	{
		"id": '211281',
		"shortname": '调兵山市',
		"parentid": '211200'
	},
	{
		"id": '211282',
		"shortname": '开原市',
		"parentid": '211200'
	},
	{
		"id": '211302',
		"shortname": '双塔区',
		"parentid": '211300'
	},
	{
		"id": '211303',
		"shortname": '龙城区',
		"parentid": '211300'
	},
	{
		"id": '211321',
		"shortname": '朝阳县',
		"parentid": '211300'
	},
	{
		"id": '211322',
		"shortname": '建平县',
		"parentid": '211300'
	},
	{
		"id": '211324',
		"shortname": '喀喇沁左翼蒙古族自治县',
		"parentid": '211300'
	},
	{
		"id": '211340',
		"shortname": '朝阳经济技术开发区',
		"parentid": '211300'
	},
	{
		"id": '211381',
		"shortname": '北票市',
		"parentid": '211300'
	},
	{
		"id": '211382',
		"shortname": '凌源市',
		"parentid": '211300'
	},
	{
		"id": '211402',
		"shortname": '连山区',
		"parentid": '211400'
	},
	{
		"id": '211403',
		"shortname": '龙港区',
		"parentid": '211400'
	},
	{
		"id": '211404',
		"shortname": '南票区',
		"parentid": '211400'
	},
	{
		"id": '211421',
		"shortname": '绥中县',
		"parentid": '211400'
	},
	{
		"id": '211422',
		"shortname": '建昌县',
		"parentid": '211400'
	},
	{
		"id": '211481',
		"shortname": '兴城市',
		"parentid": '211400'
	},
	{
		"id": '220102',
		"shortname": '南关区',
		"parentid": '220100'
	},
	{
		"id": '220103',
		"shortname": '宽城区',
		"parentid": '220100'
	},
	{
		"id": '220104',
		"shortname": '朝阳区',
		"parentid": '220100'
	},
	{
		"id": '220105',
		"shortname": '二道区',
		"parentid": '220100'
	},
	{
		"id": '220106',
		"shortname": '绿园区',
		"parentid": '220100'
	},
	{
		"id": '220112',
		"shortname": '双阳区',
		"parentid": '220100'
	},
	{
		"id": '220122',
		"shortname": '农安县',
		"parentid": '220100'
	},
	{
		"id": '220140',
		"shortname": '开发区',
		"parentid": '220100'
	},
	{
		"id": '220181',
		"shortname": '九台市',
		"parentid": '220100'
	},
	{
		"id": '220183',
		"shortname": '德惠市',
		"parentid": '220100'
	},
	{
		"id": '220182',
		"shortname": '榆树市',
		"parentid": '220100'
	},
	{
		"id": '220202',
		"shortname": '昌邑区',
		"parentid": '220200'
	},
	{
		"id": '220203',
		"shortname": '龙潭区',
		"parentid": '220200'
	},
	{
		"id": '220204',
		"shortname": '船营区',
		"parentid": '220200'
	},
	{
		"id": '220211',
		"shortname": '丰满区',
		"parentid": '220200'
	},
	{
		"id": '220221',
		"shortname": '永吉县',
		"parentid": '220200'
	},
	{
		"id": '220282',
		"shortname": '桦甸市',
		"parentid": '220200'
	},
	{
		"id": '220283',
		"shortname": '舒兰市',
		"parentid": '220200'
	},
	{
		"id": '220284',
		"shortname": '磐石市',
		"parentid": '220200'
	},
	{
		"id": '220281',
		"shortname": '蛟河市',
		"parentid": '220200'
	},
	{
		"id": '220302',
		"shortname": '铁西区',
		"parentid": '220300'
	},
	{
		"id": '220303',
		"shortname": '铁东区',
		"parentid": '220300'
	},
	{
		"id": '220322',
		"shortname": '梨树县',
		"parentid": '220300'
	},
	{
		"id": '220381',
		"shortname": '公主岭市',
		"parentid": '220300'
	},
	{
		"id": '220382',
		"shortname": '双辽市',
		"parentid": '220300'
	},
	{
		"id": '220323',
		"shortname": '伊通满族自治县',
		"parentid": '220300'
	},
	{
		"id": '220402',
		"shortname": '龙山区',
		"parentid": '220400'
	},
	{
		"id": '220403',
		"shortname": '西安区',
		"parentid": '220400'
	},
	{
		"id": '220421',
		"shortname": '东丰县',
		"parentid": '220400'
	},
	{
		"id": '220422',
		"shortname": '东辽县',
		"parentid": '220400'
	},
	{
		"id": '220502',
		"shortname": '东昌区',
		"parentid": '220500'
	},
	{
		"id": '220521',
		"shortname": '通化县',
		"parentid": '220500'
	},
	{
		"id": '220523',
		"shortname": '辉南县',
		"parentid": '220500'
	},
	{
		"id": '220524',
		"shortname": '柳河县',
		"parentid": '220500'
	},
	{
		"id": '220581',
		"shortname": '梅河口市',
		"parentid": '220500'
	},
	{
		"id": '220582',
		"shortname": '集安市',
		"parentid": '220500'
	},
	{
		"id": '220503',
		"shortname": '二道江区',
		"parentid": '220500'
	},
	{
		"id": '220622',
		"shortname": '靖宇县',
		"parentid": '220600'
	},
	{
		"id": '220623',
		"shortname": '长白朝鲜族自治县',
		"parentid": '220600'
	},
	{
		"id": '220681',
		"shortname": '临江市',
		"parentid": '220600'
	},
	{
		"id": '220602',
		"shortname": '浑江区',
		"parentid": '220600'
	},
	{
		"id": '220605',
		"shortname": '江源区',
		"parentid": '220600'
	},
	{
		"id": '220621',
		"shortname": '抚松县',
		"parentid": '220600'
	},
	{
		"id": '220702',
		"shortname": '宁江区',
		"parentid": '220700'
	},
	{
		"id": '220721',
		"shortname": '前郭尔罗斯蒙古族自治县',
		"parentid": '220700'
	},
	{
		"id": '220722',
		"shortname": '长岭县',
		"parentid": '220700'
	},
	{
		"id": '220723',
		"shortname": '乾安县',
		"parentid": '220700'
	},
	{
		"id": '220724',
		"shortname": '扶余县',
		"parentid": '220700'
	},
	{
		"id": '220802',
		"shortname": '洮北区',
		"parentid": '220800'
	},
	{
		"id": '220821',
		"shortname": '镇赉县',
		"parentid": '220800'
	},
	{
		"id": '220822',
		"shortname": '通榆县',
		"parentid": '220800'
	},
	{
		"id": '220881',
		"shortname": '洮南市',
		"parentid": '220800'
	},
	{
		"id": '220882',
		"shortname": '大安市',
		"parentid": '220800'
	},
	{
		"id": '222401',
		"shortname": '延吉市',
		"parentid": '222400'
	},
	{
		"id": '222402',
		"shortname": '图们市',
		"parentid": '222400'
	},
	{
		"id": '222404',
		"shortname": '珲春市',
		"parentid": '222400'
	},
	{
		"id": '222405',
		"shortname": '龙井市',
		"parentid": '222400'
	},
	{
		"id": '222406',
		"shortname": '和龙市',
		"parentid": '222400'
	},
	{
		"id": '222424',
		"shortname": '汪清县',
		"parentid": '222400'
	},
	{
		"id": '222426',
		"shortname": '安图县',
		"parentid": '222400'
	},
	{
		"id": '222403',
		"shortname": '敦化市',
		"parentid": '222400'
	},
	{
		"id": '230102',
		"shortname": '道里区',
		"parentid": '230100'
	},
	{
		"id": '230103',
		"shortname": '南岗区',
		"parentid": '230100'
	},
	{
		"id": '230104',
		"shortname": '道外区',
		"parentid": '230100'
	},
	{
		"id": '230108',
		"shortname": '平房区',
		"parentid": '230100'
	},
	{
		"id": '230109',
		"shortname": '松北区',
		"parentid": '230100'
	},
	{
		"id": '230110',
		"shortname": '香坊区',
		"parentid": '230100'
	},
	{
		"id": '230111',
		"shortname": '呼兰区',
		"parentid": '230100'
	},
	{
		"id": '230112',
		"shortname": '阿城区',
		"parentid": '230100'
	},
	{
		"id": '230123',
		"shortname": '依兰县',
		"parentid": '230100'
	},
	{
		"id": '230124',
		"shortname": '方正县',
		"parentid": '230100'
	},
	{
		"id": '230125',
		"shortname": '宾县',
		"parentid": '230100'
	},
	{
		"id": '230126',
		"shortname": '巴彦县',
		"parentid": '230100'
	},
	{
		"id": '230127',
		"shortname": '木兰县',
		"parentid": '230100'
	},
	{
		"id": '230128',
		"shortname": '通河县',
		"parentid": '230100'
	},
	{
		"id": '230129',
		"shortname": '延寿县',
		"parentid": '230100'
	},
	{
		"id": '230182',
		"shortname": '双城市',
		"parentid": '230100'
	},
	{
		"id": '230183',
		"shortname": '尚志市',
		"parentid": '230100'
	},
	{
		"id": '230184',
		"shortname": '五常市',
		"parentid": '230100'
	},
	{
		"id": '230202',
		"shortname": '龙沙区',
		"parentid": '230200'
	},
	{
		"id": '230203',
		"shortname": '建华区',
		"parentid": '230200'
	},
	{
		"id": '230204',
		"shortname": '铁锋区',
		"parentid": '230200'
	},
	{
		"id": '230205',
		"shortname": '昂昂溪区',
		"parentid": '230200'
	},
	{
		"id": '230206',
		"shortname": '富拉尔基区',
		"parentid": '230200'
	},
	{
		"id": '230207',
		"shortname": '碾子山区',
		"parentid": '230200'
	},
	{
		"id": '230208',
		"shortname": '梅里斯达斡尔族区',
		"parentid": '230200'
	},
	{
		"id": '230221',
		"shortname": '龙江县',
		"parentid": '230200'
	},
	{
		"id": '230223',
		"shortname": '依安县',
		"parentid": '230200'
	},
	{
		"id": '230224',
		"shortname": '泰来县',
		"parentid": '230200'
	},
	{
		"id": '230225',
		"shortname": '甘南县',
		"parentid": '230200'
	},
	{
		"id": '230227',
		"shortname": '富裕县',
		"parentid": '230200'
	},
	{
		"id": '230229',
		"shortname": '克山县',
		"parentid": '230200'
	},
	{
		"id": '230230',
		"shortname": '克东县',
		"parentid": '230200'
	},
	{
		"id": '230231',
		"shortname": '拜泉县',
		"parentid": '230200'
	},
	{
		"id": '230281',
		"shortname": '讷河市',
		"parentid": '230200'
	},
	{
		"id": '230302',
		"shortname": '鸡冠区',
		"parentid": '230300'
	},
	{
		"id": '230303',
		"shortname": '恒山区',
		"parentid": '230300'
	},
	{
		"id": '230304',
		"shortname": '滴道区',
		"parentid": '230300'
	},
	{
		"id": '230305',
		"shortname": '梨树区',
		"parentid": '230300'
	},
	{
		"id": '230306',
		"shortname": '城子河区',
		"parentid": '230300'
	},
	{
		"id": '230307',
		"shortname": '麻山区',
		"parentid": '230300'
	},
	{
		"id": '230321',
		"shortname": '鸡东县',
		"parentid": '230300'
	},
	{
		"id": '230381',
		"shortname": '虎林市',
		"parentid": '230300'
	},
	{
		"id": '230382',
		"shortname": '密山市',
		"parentid": '230300'
	},
	{
		"id": '230402',
		"shortname": '向阳区',
		"parentid": '230400'
	},
	{
		"id": '230403',
		"shortname": '工农区',
		"parentid": '230400'
	},
	{
		"id": '230404',
		"shortname": '南山区',
		"parentid": '230400'
	},
	{
		"id": '230405',
		"shortname": '兴安区',
		"parentid": '230400'
	},
	{
		"id": '230406',
		"shortname": '东山区',
		"parentid": '230400'
	},
	{
		"id": '230407',
		"shortname": '兴山区',
		"parentid": '230400'
	},
	{
		"id": '230421',
		"shortname": '萝北县',
		"parentid": '230400'
	},
	{
		"id": '230422',
		"shortname": '绥滨县',
		"parentid": '230400'
	},
	{
		"id": '230502',
		"shortname": '尖山区',
		"parentid": '230500'
	},
	{
		"id": '230503',
		"shortname": '岭东区',
		"parentid": '230500'
	},
	{
		"id": '230505',
		"shortname": '四方台区',
		"parentid": '230500'
	},
	{
		"id": '230506',
		"shortname": '宝山区',
		"parentid": '230500'
	},
	{
		"id": '230521',
		"shortname": '集贤县',
		"parentid": '230500'
	},
	{
		"id": '230522',
		"shortname": '友谊县',
		"parentid": '230500'
	},
	{
		"id": '230523',
		"shortname": '宝清县',
		"parentid": '230500'
	},
	{
		"id": '230524',
		"shortname": '饶河县',
		"parentid": '230500'
	},
	{
		"id": '230622',
		"shortname": '肇源县',
		"parentid": '230600'
	},
	{
		"id": '230623',
		"shortname": '林甸县',
		"parentid": '230600'
	},
	{
		"id": '230624',
		"shortname": '杜尔伯特蒙古族自治县',
		"parentid": '230600'
	},
	{
		"id": '230602',
		"shortname": '萨尔图区',
		"parentid": '230600'
	},
	{
		"id": '230603',
		"shortname": '龙凤区',
		"parentid": '230600'
	},
	{
		"id": '230604',
		"shortname": '让胡路区',
		"parentid": '230600'
	},
	{
		"id": '230605',
		"shortname": '红岗区',
		"parentid": '230600'
	},
	{
		"id": '230606',
		"shortname": '大同区',
		"parentid": '230600'
	},
	{
		"id": '230621',
		"shortname": '肇州县',
		"parentid": '230600'
	},
	{
		"id": '230702',
		"shortname": '伊春区',
		"parentid": '230700'
	},
	{
		"id": '230703',
		"shortname": '南岔区',
		"parentid": '230700'
	},
	{
		"id": '230704',
		"shortname": '友好区',
		"parentid": '230700'
	},
	{
		"id": '230705',
		"shortname": '西林区',
		"parentid": '230700'
	},
	{
		"id": '230706',
		"shortname": '翠峦区',
		"parentid": '230700'
	},
	{
		"id": '230707',
		"shortname": '新青区',
		"parentid": '230700'
	},
	{
		"id": '230709',
		"shortname": '金山屯区',
		"parentid": '230700'
	},
	{
		"id": '230711',
		"shortname": '乌马河区',
		"parentid": '230700'
	},
	{
		"id": '230712',
		"shortname": '汤旺河区',
		"parentid": '230700'
	},
	{
		"id": '230713',
		"shortname": '带岭区',
		"parentid": '230700'
	},
	{
		"id": '230714',
		"shortname": '乌伊岭区',
		"parentid": '230700'
	},
	{
		"id": '230715',
		"shortname": '红星区',
		"parentid": '230700'
	},
	{
		"id": '230716',
		"shortname": '上甘岭区',
		"parentid": '230700'
	},
	{
		"id": '230722',
		"shortname": '嘉荫县',
		"parentid": '230700'
	},
	{
		"id": '230781',
		"shortname": '铁力市',
		"parentid": '230700'
	},
	{
		"id": '230708',
		"shortname": '美溪区',
		"parentid": '230700'
	},
	{
		"id": '230710',
		"shortname": '五营区',
		"parentid": '230700'
	},
	{
		"id": '230803',
		"shortname": '向阳区',
		"parentid": '230800'
	},
	{
		"id": '230804',
		"shortname": '前进区',
		"parentid": '230800'
	},
	{
		"id": '230805',
		"shortname": '东风区',
		"parentid": '230800'
	},
	{
		"id": '230811',
		"shortname": '郊区',
		"parentid": '230800'
	},
	{
		"id": '230822',
		"shortname": '桦南县',
		"parentid": '230800'
	},
	{
		"id": '230826',
		"shortname": '桦川县',
		"parentid": '230800'
	},
	{
		"id": '230828',
		"shortname": '汤原县',
		"parentid": '230800'
	},
	{
		"id": '230833',
		"shortname": '抚远县',
		"parentid": '230800'
	},
	{
		"id": '230881',
		"shortname": '同江市',
		"parentid": '230800'
	},
	{
		"id": '230882',
		"shortname": '富锦市',
		"parentid": '230800'
	},
	{
		"id": '230902',
		"shortname": '新兴区',
		"parentid": '230900'
	},
	{
		"id": '230903',
		"shortname": '桃山区',
		"parentid": '230900'
	},
	{
		"id": '230904',
		"shortname": '茄子河区',
		"parentid": '230900'
	},
	{
		"id": '230921',
		"shortname": '勃利县',
		"parentid": '230900'
	},
	{
		"id": '231002',
		"shortname": '东安区',
		"parentid": '231000'
	},
	{
		"id": '231003',
		"shortname": '阳明区',
		"parentid": '231000'
	},
	{
		"id": '231004',
		"shortname": '爱民区',
		"parentid": '231000'
	},
	{
		"id": '231005',
		"shortname": '西安区',
		"parentid": '231000'
	},
	{
		"id": '231024',
		"shortname": '东宁县',
		"parentid": '231000'
	},
	{
		"id": '231025',
		"shortname": '林口县',
		"parentid": '231000'
	},
	{
		"id": '231081',
		"shortname": '绥芬河市',
		"parentid": '231000'
	},
	{
		"id": '231083',
		"shortname": '海林市',
		"parentid": '231000'
	},
	{
		"id": '231084',
		"shortname": '宁安市',
		"parentid": '231000'
	},
	{
		"id": '231085',
		"shortname": '穆棱市',
		"parentid": '231000'
	},
	{
		"id": '231102',
		"shortname": '爱辉区',
		"parentid": '231100'
	},
	{
		"id": '231121',
		"shortname": '嫩江县',
		"parentid": '231100'
	},
	{
		"id": '231123',
		"shortname": '逊克县',
		"parentid": '231100'
	},
	{
		"id": '231124',
		"shortname": '孙吴县',
		"parentid": '231100'
	},
	{
		"id": '231181',
		"shortname": '北安市',
		"parentid": '231100'
	},
	{
		"id": '231182',
		"shortname": '五大连池市',
		"parentid": '231100'
	},
	{
		"id": '231202',
		"shortname": '北林区',
		"parentid": '231200'
	},
	{
		"id": '231221',
		"shortname": '望奎县',
		"parentid": '231200'
	},
	{
		"id": '231222',
		"shortname": '兰西县',
		"parentid": '231200'
	},
	{
		"id": '231223',
		"shortname": '青冈县',
		"parentid": '231200'
	},
	{
		"id": '231224',
		"shortname": '庆安县',
		"parentid": '231200'
	},
	{
		"id": '231225',
		"shortname": '明水县',
		"parentid": '231200'
	},
	{
		"id": '231226',
		"shortname": '绥棱县',
		"parentid": '231200'
	},
	{
		"id": '231281',
		"shortname": '安达市',
		"parentid": '231200'
	},
	{
		"id": '231282',
		"shortname": '肇东市',
		"parentid": '231200'
	},
	{
		"id": '231283',
		"shortname": '海伦市',
		"parentid": '231200'
	},
	{
		"id": '232701',
		"shortname": '加格达奇区',
		"parentid": '232700'
	},
	{
		"id": '232702',
		"shortname": '松岭区',
		"parentid": '232700'
	},
	{
		"id": '232703',
		"shortname": '新林区',
		"parentid": '232700'
	},
	{
		"id": '232704',
		"shortname": '呼中区',
		"parentid": '232700'
	},
	{
		"id": '232721',
		"shortname": '呼玛县',
		"parentid": '232700'
	},
	{
		"id": '232722',
		"shortname": '塔河县',
		"parentid": '232700'
	},
	{
		"id": '232723',
		"shortname": '漠河县',
		"parentid": '232700'
	},
	{
		"id": '232740',
		"shortname": '加格达奇林业局',
		"parentid": '232700'
	},
	{
		"id": '232741',
		"shortname": '十八站林业局',
		"parentid": '232700'
	},
	{
		"id": '232742',
		"shortname": '图强林业局',
		"parentid": '232700'
	},
	{
		"id": '232743',
		"shortname": '阿木尔林业局',
		"parentid": '232700'
	},
	{
		"id": '232744',
		"shortname": '韩家园林业局',
		"parentid": '232700'
	},
	{
		"id": '232745',
		"shortname": '林管局农工商联合',
		"parentid": '232700'
	},
	{
		"id": '232801',
		"shortname": '牡丹江森工地区',
		"parentid": '232800'
	},
	{
		"id": '232802',
		"shortname": '松花江森工地区',
		"parentid": '232800'
	},
	{
		"id": '232803',
		"shortname": '合江森工地区',
		"parentid": '232800'
	},
	{
		"id": '232901',
		"shortname": '农垦总局直属地区',
		"parentid": '232900'
	},
	{
		"id": '232902',
		"shortname": '农垦宝泉岭分局',
		"parentid": '232900'
	},
	{
		"id": '232903',
		"shortname": '农垦红兴隆分局',
		"parentid": '232900'
	},
	{
		"id": '232904',
		"shortname": '农垦建三江分局',
		"parentid": '232900'
	},
	{
		"id": '232905',
		"shortname": '农垦牡丹江分局',
		"parentid": '232900'
	},
	{
		"id": '232906',
		"shortname": '农垦北安分局',
		"parentid": '232900'
	},
	{
		"id": '232907',
		"shortname": '农垦九三分局',
		"parentid": '232900'
	},
	{
		"id": '232908',
		"shortname": '农垦齐齐哈尔分局',
		"parentid": '232900'
	},
	{
		"id": '232909',
		"shortname": '农垦绥化分局',
		"parentid": '232900'
	},
	{
		"id": '232910',
		"shortname": '农垦哈尔滨分局',
		"parentid": '232900'
	},
	{
		"id": '320102',
		"shortname": '玄武区',
		"parentid": '320100'
	},
	{
		"id": '320103',
		"shortname": '白下区',
		"parentid": '320100'
	},
	{
		"id": '320104',
		"shortname": '秦淮区',
		"parentid": '320100'
	},
	{
		"id": '320105',
		"shortname": '建邺区',
		"parentid": '320100'
	},
	{
		"id": '320106',
		"shortname": '鼓楼区',
		"parentid": '320100'
	},
	{
		"id": '320107',
		"shortname": '下关区',
		"parentid": '320100'
	},
	{
		"id": '320111',
		"shortname": '浦口区',
		"parentid": '320100'
	},
	{
		"id": '320113',
		"shortname": '栖霞区',
		"parentid": '320100'
	},
	{
		"id": '320114',
		"shortname": '雨花台区',
		"parentid": '320100'
	},
	{
		"id": '320116',
		"shortname": '六合区',
		"parentid": '320100'
	},
	{
		"id": '320124',
		"shortname": '溧水县',
		"parentid": '320100'
	},
	{
		"id": '320125',
		"shortname": '高淳县',
		"parentid": '320100'
	},
	{
		"id": '320115',
		"shortname": '江宁区',
		"parentid": '320100'
	},
	{
		"id": '320202',
		"shortname": '崇安区',
		"parentid": '320200'
	},
	{
		"id": '320203',
		"shortname": '南长区',
		"parentid": '320200'
	},
	{
		"id": '320204',
		"shortname": '北塘区',
		"parentid": '320200'
	},
	{
		"id": '320205',
		"shortname": '锡山区',
		"parentid": '320200'
	},
	{
		"id": '320211',
		"shortname": '滨湖区',
		"parentid": '320200'
	},
	{
		"id": '320281',
		"shortname": '江阴市',
		"parentid": '320200'
	},
	{
		"id": '320282',
		"shortname": '宜兴市',
		"parentid": '320200'
	},
	{
		"id": '320213',
		"shortname": '梁溪区',
		"parentid": '320200'
	},
	{
		"id": '320206',
		"shortname": '惠山区',
		"parentid": '320200'
	},
	{
		"id": '320302',
		"shortname": '鼓楼区',
		"parentid": '320300'
	},
	{
		"id": '320303',
		"shortname": '云龙区',
		"parentid": '320300'
	},
	{
		"id": '320304',
		"shortname": '九里区',
		"parentid": '320300'
	},
	{
		"id": '320305',
		"shortname": '贾汪区',
		"parentid": '320300'
	},
	{
		"id": '320311',
		"shortname": '泉山区',
		"parentid": '320300'
	},
	{
		"id": '320321',
		"shortname": '丰县',
		"parentid": '320300'
	},
	{
		"id": '320322',
		"shortname": '沛县',
		"parentid": '320300'
	},
	{
		"id": '320323',
		"shortname": '铜山县',
		"parentid": '320300'
	},
	{
		"id": '320324',
		"shortname": '睢宁县',
		"parentid": '320300'
	},
	{
		"id": '320381',
		"shortname": '新沂市',
		"parentid": '320300'
	},
	{
		"id": '320382',
		"shortname": '邳州市',
		"parentid": '320300'
	},
	{
		"id": '320402',
		"shortname": '天宁区',
		"parentid": '320400'
	},
	{
		"id": '320404',
		"shortname": '钟楼区',
		"parentid": '320400'
	},
	{
		"id": '320405',
		"shortname": '戚墅堰区',
		"parentid": '320400'
	},
	{
		"id": '320411',
		"shortname": '新北区',
		"parentid": '320400'
	},
	{
		"id": '320412',
		"shortname": '武进区',
		"parentid": '320400'
	},
	{
		"id": '320481',
		"shortname": '溧阳市',
		"parentid": '320400'
	},
	{
		"id": '320482',
		"shortname": '金坛市',
		"parentid": '320400'
	},
	{
		"id": '320502',
		"shortname": '沧浪区',
		"parentid": '320500'
	},
	{
		"id": '320503',
		"shortname": '平江区',
		"parentid": '320500'
	},
	{
		"id": '320504',
		"shortname": '金阊区',
		"parentid": '320500'
	},
	{
		"id": '320506',
		"shortname": '吴中区',
		"parentid": '320500'
	},
	{
		"id": '320507',
		"shortname": '相城区',
		"parentid": '320500'
	},
	{
		"id": '320508',
		"shortname": '姑苏区',
		"parentid": '320500'
	},
	{
		"id": '320540',
		"shortname": '苏州工业园区',
		"parentid": '320500'
	},
	{
		"id": '320581',
		"shortname": '常熟市',
		"parentid": '320500'
	},
	{
		"id": '320582',
		"shortname": '张家港市',
		"parentid": '320500'
	},
	{
		"id": '320583',
		"shortname": '昆山市',
		"parentid": '320500'
	},
	{
		"id": '320584',
		"shortname": '吴江市',
		"parentid": '320500'
	},
	{
		"id": '320585',
		"shortname": '太仓市',
		"parentid": '320500'
	},
	{
		"id": '320505',
		"shortname": '虎丘区',
		"parentid": '320500'
	},
	{
		"id": '320602',
		"shortname": '崇川区',
		"parentid": '320600'
	},
	{
		"id": '320611',
		"shortname": '港闸区',
		"parentid": '320600'
	},
	{
		"id": '320612',
		"shortname": '通州区',
		"parentid": '320600'
	},
	{
		"id": '320621',
		"shortname": '海安县',
		"parentid": '320600'
	},
	{
		"id": '320623',
		"shortname": '如东县',
		"parentid": '320600'
	},
	{
		"id": '320681',
		"shortname": '启东市',
		"parentid": '320600'
	},
	{
		"id": '320682',
		"shortname": '如皋市',
		"parentid": '320600'
	},
	{
		"id": '320684',
		"shortname": '海门市',
		"parentid": '320600'
	},
	{
		"id": '320703',
		"shortname": '连云区',
		"parentid": '320700'
	},
	{
		"id": '320705',
		"shortname": '新浦区',
		"parentid": '320700'
	},
	{
		"id": '320706',
		"shortname": '海州区',
		"parentid": '320700'
	},
	{
		"id": '320721',
		"shortname": '赣榆县',
		"parentid": '320700'
	},
	{
		"id": '320722',
		"shortname": '东海县',
		"parentid": '320700'
	},
	{
		"id": '320723',
		"shortname": '灌云县',
		"parentid": '320700'
	},
	{
		"id": '320724',
		"shortname": '灌南县',
		"parentid": '320700'
	},
	{
		"id": '320802',
		"shortname": '清河区',
		"parentid": '320800'
	},
	{
		"id": '320804',
		"shortname": '淮阴区',
		"parentid": '320800'
	},
	{
		"id": '320811',
		"shortname": '清浦区',
		"parentid": '320800'
	},
	{
		"id": '320826',
		"shortname": '涟水县',
		"parentid": '320800'
	},
	{
		"id": '320829',
		"shortname": '洪泽县',
		"parentid": '320800'
	},
	{
		"id": '320830',
		"shortname": '盱眙县',
		"parentid": '320800'
	},
	{
		"id": '320831',
		"shortname": '金湖县',
		"parentid": '320800'
	},
	{
		"id": '320840',
		"shortname": '淮安经济开发区',
		"parentid": '320800'
	},
	{
		"id": '320803',
		"shortname": '淮安区',
		"parentid": '320800'
	},
	{
		"id": '320902',
		"shortname": '亭湖区',
		"parentid": '320900'
	},
	{
		"id": '320903',
		"shortname": '盐都区',
		"parentid": '320900'
	},
	{
		"id": '320921',
		"shortname": '响水县',
		"parentid": '320900'
	},
	{
		"id": '320922',
		"shortname": '滨海县',
		"parentid": '320900'
	},
	{
		"id": '320923',
		"shortname": '阜宁县',
		"parentid": '320900'
	},
	{
		"id": '320924',
		"shortname": '射阳县',
		"parentid": '320900'
	},
	{
		"id": '320925',
		"shortname": '建湖县',
		"parentid": '320900'
	},
	{
		"id": '320940',
		"shortname": '经济开发区',
		"parentid": '320900'
	},
	{
		"id": '320981',
		"shortname": '东台市',
		"parentid": '320900'
	},
	{
		"id": '320982',
		"shortname": '大丰市',
		"parentid": '320900'
	},
	{
		"id": '321002',
		"shortname": '广陵区',
		"parentid": '321000'
	},
	{
		"id": '321003',
		"shortname": '邗江区',
		"parentid": '321000'
	},
	{
		"id": '321011',
		"shortname": '维扬区',
		"parentid": '321000'
	},
	{
		"id": '321023',
		"shortname": '宝应县',
		"parentid": '321000'
	},
	{
		"id": '321081',
		"shortname": '仪征市',
		"parentid": '321000'
	},
	{
		"id": '321084',
		"shortname": '高邮市',
		"parentid": '321000'
	},
	{
		"id": '321088',
		"shortname": '江都市',
		"parentid": '321000'
	},
	{
		"id": '321102',
		"shortname": '京口区',
		"parentid": '321100'
	},
	{
		"id": '321111',
		"shortname": '润州区',
		"parentid": '321100'
	},
	{
		"id": '321112',
		"shortname": '丹徒区',
		"parentid": '321100'
	},
	{
		"id": '321181',
		"shortname": '丹阳市',
		"parentid": '321100'
	},
	{
		"id": '321182',
		"shortname": '扬中市',
		"parentid": '321100'
	},
	{
		"id": '321183',
		"shortname": '句容市',
		"parentid": '321100'
	},
	{
		"id": '321202',
		"shortname": '海陵区',
		"parentid": '321200'
	},
	{
		"id": '321203',
		"shortname": '高港区',
		"parentid": '321200'
	},
	{
		"id": '321281',
		"shortname": '兴化市',
		"parentid": '321200'
	},
	{
		"id": '321282',
		"shortname": '靖江市',
		"parentid": '321200'
	},
	{
		"id": '321283',
		"shortname": '泰兴市',
		"parentid": '321200'
	},
	{
		"id": '321284',
		"shortname": '姜堰市',
		"parentid": '321200'
	},
	{
		"id": '321302',
		"shortname": '宿城区',
		"parentid": '321300'
	},
	{
		"id": '321311',
		"shortname": '宿豫区',
		"parentid": '321300'
	},
	{
		"id": '321322',
		"shortname": '沭阳县',
		"parentid": '321300'
	},
	{
		"id": '321323',
		"shortname": '泗阳县',
		"parentid": '321300'
	},
	{
		"id": '321324',
		"shortname": '泗洪县',
		"parentid": '321300'
	},
	{
		"id": '330122',
		"shortname": '桐庐县',
		"parentid": '330100'
	},
	{
		"id": '330127',
		"shortname": '淳安县',
		"parentid": '330100'
	},
	{
		"id": '330182',
		"shortname": '建德市',
		"parentid": '330100'
	},
	{
		"id": '330183',
		"shortname": '富阳市',
		"parentid": '330100'
	},
	{
		"id": '330102',
		"shortname": '上城区',
		"parentid": '330100'
	},
	{
		"id": '330103',
		"shortname": '下城区',
		"parentid": '330100'
	},
	{
		"id": '330104',
		"shortname": '江干区',
		"parentid": '330100'
	},
	{
		"id": '330105',
		"shortname": '拱墅区',
		"parentid": '330100'
	},
	{
		"id": '330106',
		"shortname": '西湖区',
		"parentid": '330100'
	},
	{
		"id": '330108',
		"shortname": '滨江区',
		"parentid": '330100'
	},
	{
		"id": '330110',
		"shortname": '余杭区',
		"parentid": '330100'
	},
	{
		"id": '330185',
		"shortname": '临安市',
		"parentid": '330100'
	},
	{
		"id": '330109',
		"shortname": '萧山区',
		"parentid": '330100'
	},
	{
		"id": '330203',
		"shortname": '海曙区',
		"parentid": '330200'
	},
	{
		"id": '330204',
		"shortname": '江东区',
		"parentid": '330200'
	},
	{
		"id": '330205',
		"shortname": '江北区',
		"parentid": '330200'
	},
	{
		"id": '330206',
		"shortname": '北仑区',
		"parentid": '330200'
	},
	{
		"id": '330211',
		"shortname": '镇海区',
		"parentid": '330200'
	},
	{
		"id": '330212',
		"shortname": '鄞州区',
		"parentid": '330200'
	},
	{
		"id": '330225',
		"shortname": '象山县',
		"parentid": '330200'
	},
	{
		"id": '330226',
		"shortname": '宁海县',
		"parentid": '330200'
	},
	{
		"id": '330240',
		"shortname": '东钱湖区',
		"parentid": '330200'
	},
	{
		"id": '330241',
		"shortname": '开发区',
		"parentid": '330200'
	},
	{
		"id": '330242',
		"shortname": '保税区',
		"parentid": '330200'
	},
	{
		"id": '330243',
		"shortname": '大榭开发区',
		"parentid": '330200'
	},
	{
		"id": '330244',
		"shortname": '科技院区     ',
		"parentid": '330200'
	},
	{
		"id": '330281',
		"shortname": '余姚市',
		"parentid": '330200'
	},
	{
		"id": '330282',
		"shortname": '慈溪市',
		"parentid": '330200'
	},
	{
		"id": '330283',
		"shortname": '奉化市',
		"parentid": '330200'
	},
	{
		"id": '330302',
		"shortname": '鹿城区',
		"parentid": '330300'
	},
	{
		"id": '330303',
		"shortname": '龙湾区',
		"parentid": '330300'
	},
	{
		"id": '330304',
		"shortname": '瓯海区',
		"parentid": '330300'
	},
	{
		"id": '330322',
		"shortname": '洞头县',
		"parentid": '330300'
	},
	{
		"id": '330324',
		"shortname": '永嘉县',
		"parentid": '330300'
	},
	{
		"id": '330326',
		"shortname": '平阳县',
		"parentid": '330300'
	},
	{
		"id": '330327',
		"shortname": '苍南县',
		"parentid": '330300'
	},
	{
		"id": '330328',
		"shortname": '文成县',
		"parentid": '330300'
	},
	{
		"id": '330340',
		"shortname": '开发区',
		"parentid": '330300'
	},
	{
		"id": '330381',
		"shortname": '瑞安市',
		"parentid": '330300'
	},
	{
		"id": '330382',
		"shortname": '乐清市',
		"parentid": '330300'
	},
	{
		"id": '330329',
		"shortname": '泰顺县',
		"parentid": '330300'
	},
	{
		"id": '330421',
		"shortname": '嘉善县',
		"parentid": '330400'
	},
	{
		"id": '330424',
		"shortname": '海盐县',
		"parentid": '330400'
	},
	{
		"id": '330481',
		"shortname": '海宁市',
		"parentid": '330400'
	},
	{
		"id": '330483',
		"shortname": '桐乡市',
		"parentid": '330400'
	},
	{
		"id": '330482',
		"shortname": '平湖市',
		"parentid": '330400'
	},
	{
		"id": '330521',
		"shortname": '德清县',
		"parentid": '330500'
	},
	{
		"id": '330522',
		"shortname": '长兴县',
		"parentid": '330500'
	},
	{
		"id": '330523',
		"shortname": '安吉县',
		"parentid": '330500'
	},
	{
		"id": '330621',
		"shortname": '绍兴县',
		"parentid": '330600'
	},
	{
		"id": '330624',
		"shortname": '新昌县',
		"parentid": '330600'
	},
	{
		"id": '330682',
		"shortname": '上虞市',
		"parentid": '330600'
	},
	{
		"id": '330683',
		"shortname": '嵊州市',
		"parentid": '330600'
	},
	{
		"id": '330681',
		"shortname": '诸暨市',
		"parentid": '330600'
	},
	{
		"id": '330723',
		"shortname": '武义县',
		"parentid": '330700'
	},
	{
		"id": '330726',
		"shortname": '浦江县',
		"parentid": '330700'
	},
	{
		"id": '330727',
		"shortname": '磐安县',
		"parentid": '330700'
	},
	{
		"id": '330781',
		"shortname": '兰溪市',
		"parentid": '330700'
	},
	{
		"id": '330783',
		"shortname": '东阳市',
		"parentid": '330700'
	},
	{
		"id": '330784',
		"shortname": '永康市',
		"parentid": '330700'
	},
	{
		"id": '330782',
		"shortname": '义乌市',
		"parentid": '330700'
	},
	{
		"id": '330802',
		"shortname": '柯城区',
		"parentid": '330800'
	},
	{
		"id": '330803',
		"shortname": '衢江区',
		"parentid": '330800'
	},
	{
		"id": '330822',
		"shortname": '常山县',
		"parentid": '330800'
	},
	{
		"id": '330824',
		"shortname": '开化县',
		"parentid": '330800'
	},
	{
		"id": '330825',
		"shortname": '龙游县',
		"parentid": '330800'
	},
	{
		"id": '330881',
		"shortname": '江山市',
		"parentid": '330800'
	},
	{
		"id": '330903',
		"shortname": '普陀区',
		"parentid": '330900'
	},
	{
		"id": '330921',
		"shortname": '岱山县',
		"parentid": '330900'
	},
	{
		"id": '330922',
		"shortname": '嵊泗县',
		"parentid": '330900'
	},
	{
		"id": '331002',
		"shortname": '椒江区',
		"parentid": '331000'
	},
	{
		"id": '331003',
		"shortname": '黄岩区',
		"parentid": '331000'
	},
	{
		"id": '331004',
		"shortname": '路桥区',
		"parentid": '331000'
	},
	{
		"id": '331021',
		"shortname": '玉环县',
		"parentid": '331000'
	},
	{
		"id": '331022',
		"shortname": '三门县',
		"parentid": '331000'
	},
	{
		"id": '331023',
		"shortname": '天台县',
		"parentid": '331000'
	},
	{
		"id": '331024',
		"shortname": '仙居县',
		"parentid": '331000'
	},
	{
		"id": '331081',
		"shortname": '温岭市',
		"parentid": '331000'
	},
	{
		"id": '331082',
		"shortname": '临海市',
		"parentid": '331000'
	},
	{
		"id": '331102',
		"shortname": '莲都区',
		"parentid": '331100'
	},
	{
		"id": '331121',
		"shortname": '青田县',
		"parentid": '331100'
	},
	{
		"id": '331122',
		"shortname": '缙云县',
		"parentid": '331100'
	},
	{
		"id": '331123',
		"shortname": '遂昌县',
		"parentid": '331100'
	},
	{
		"id": '331124',
		"shortname": '松阳县',
		"parentid": '331100'
	},
	{
		"id": '331125',
		"shortname": '云和县',
		"parentid": '331100'
	},
	{
		"id": '331126',
		"shortname": '庆元县',
		"parentid": '331100'
	},
	{
		"id": '331127',
		"shortname": '景宁畲族自治县',
		"parentid": '331100'
	},
	{
		"id": '331181',
		"shortname": '龙泉市',
		"parentid": '331100'
	},
	{
		"id": '340102',
		"shortname": '瑶海区',
		"parentid": '340100'
	},
	{
		"id": '340103',
		"shortname": '庐阳区',
		"parentid": '340100'
	},
	{
		"id": '340104',
		"shortname": '蜀山区',
		"parentid": '340100'
	},
	{
		"id": '340111',
		"shortname": '包河区',
		"parentid": '340100'
	},
	{
		"id": '340121',
		"shortname": '长丰县',
		"parentid": '340100'
	},
	{
		"id": '340122',
		"shortname": '肥东县',
		"parentid": '340100'
	},
	{
		"id": '340123',
		"shortname": '肥西县',
		"parentid": '340100'
	},
	{
		"id": '340202',
		"shortname": '镜湖区',
		"parentid": '340200'
	},
	{
		"id": '340203',
		"shortname": '弋江区',
		"parentid": '340200'
	},
	{
		"id": '340207',
		"shortname": '鸠江区',
		"parentid": '340200'
	},
	{
		"id": '340208',
		"shortname": '三山区',
		"parentid": '340200'
	},
	{
		"id": '340221',
		"shortname": '芜湖县',
		"parentid": '340200'
	},
	{
		"id": '340222',
		"shortname": '繁昌县',
		"parentid": '340200'
	},
	{
		"id": '340223',
		"shortname": '南陵县',
		"parentid": '340200'
	},
	{
		"id": '340240',
		"shortname": '芜湖经济技术开发区',
		"parentid": '340200'
	},
	{
		"id": '340321',
		"shortname": '怀远县',
		"parentid": '340300'
	},
	{
		"id": '340322',
		"shortname": '五河县',
		"parentid": '340300'
	},
	{
		"id": '340323',
		"shortname": '固镇县',
		"parentid": '340300'
	},
	{
		"id": '340421',
		"shortname": '凤台县',
		"parentid": '340400'
	},
	{
		"id": '340406',
		"shortname": '潘集区',
		"parentid": '340400'
	},
	{
		"id": '340521',
		"shortname": '当涂县',
		"parentid": '340500'
	},
	{
		"id": '340602',
		"shortname": '杜集区',
		"parentid": '340600'
	},
	{
		"id": '340604',
		"shortname": '烈山区',
		"parentid": '340600'
	},
	{
		"id": '340621',
		"shortname": '濉溪县',
		"parentid": '340600'
	},
	{
		"id": '340702',
		"shortname": '铜官山区',
		"parentid": '340700'
	},
	{
		"id": '340703',
		"shortname": '狮子山区',
		"parentid": '340700'
	},
	{
		"id": '340711',
		"shortname": '郊区',
		"parentid": '340700'
	},
	{
		"id": '340721',
		"shortname": '铜陵县',
		"parentid": '340700'
	},
	{
		"id": '340822',
		"shortname": '怀宁县',
		"parentid": '340800'
	},
	{
		"id": '340823',
		"shortname": '枞阳县',
		"parentid": '340800'
	},
	{
		"id": '340824',
		"shortname": '潜山县',
		"parentid": '340800'
	},
	{
		"id": '340825',
		"shortname": '太湖县',
		"parentid": '340800'
	},
	{
		"id": '340826',
		"shortname": '宿松县',
		"parentid": '340800'
	},
	{
		"id": '340827',
		"shortname": '望江县',
		"parentid": '340800'
	},
	{
		"id": '340828',
		"shortname": '岳西县',
		"parentid": '340800'
	},
	{
		"id": '340881',
		"shortname": '桐城市',
		"parentid": '340800'
	},
	{
		"id": '341002',
		"shortname": '屯溪区',
		"parentid": '341000'
	},
	{
		"id": '341003',
		"shortname": '黄山区',
		"parentid": '341000'
	},
	{
		"id": '341004',
		"shortname": '徽州区',
		"parentid": '341000'
	},
	{
		"id": '341021',
		"shortname": '歙县',
		"parentid": '341000'
	},
	{
		"id": '341022',
		"shortname": '休宁县',
		"parentid": '341000'
	},
	{
		"id": '341023',
		"shortname": '黟县',
		"parentid": '341000'
	},
	{
		"id": '341024',
		"shortname": '祁门县',
		"parentid": '341000'
	},
	{
		"id": '341102',
		"shortname": '琅琊区',
		"parentid": '341100'
	},
	{
		"id": '341122',
		"shortname": '来安县',
		"parentid": '341100'
	},
	{
		"id": '341124',
		"shortname": '全椒县',
		"parentid": '341100'
	},
	{
		"id": '341125',
		"shortname": '定远县',
		"parentid": '341100'
	},
	{
		"id": '341126',
		"shortname": '凤阳县',
		"parentid": '341100'
	},
	{
		"id": '341181',
		"shortname": '天长市',
		"parentid": '341100'
	},
	{
		"id": '341182',
		"shortname": '明光市',
		"parentid": '341100'
	},
	{
		"id": '341221',
		"shortname": '临泉县',
		"parentid": '341200'
	},
	{
		"id": '341222',
		"shortname": '太和县',
		"parentid": '341200'
	},
	{
		"id": '341225',
		"shortname": '阜南县',
		"parentid": '341200'
	},
	{
		"id": '341226',
		"shortname": '颍上县',
		"parentid": '341200'
	},
	{
		"id": '341282',
		"shortname": '界首市',
		"parentid": '341200'
	},
	{
		"id": '341302',
		"shortname": '埇桥区',
		"parentid": '341300'
	},
	{
		"id": '341321',
		"shortname": '砀山县',
		"parentid": '341300'
	},
	{
		"id": '341322',
		"shortname": '萧县',
		"parentid": '341300'
	},
	{
		"id": '341323',
		"shortname": '灵璧县',
		"parentid": '341300'
	},
	{
		"id": '341324',
		"shortname": '泗县',
		"parentid": '341300'
	},
	{
		"id": '341402',
		"shortname": '居巢区',
		"parentid": '341400'
	},
	{
		"id": '341421',
		"shortname": '庐江县',
		"parentid": '341400'
	},
	{
		"id": '341422',
		"shortname": '无为县',
		"parentid": '341400'
	},
	{
		"id": '341423',
		"shortname": '含山县',
		"parentid": '341400'
	},
	{
		"id": '341424',
		"shortname": '和县',
		"parentid": '341400'
	},
	{
		"id": '341502',
		"shortname": '金安区',
		"parentid": '341500'
	},
	{
		"id": '341503',
		"shortname": '裕安区',
		"parentid": '341500'
	},
	{
		"id": '341521',
		"shortname": '寿县',
		"parentid": '341500'
	},
	{
		"id": '341522',
		"shortname": '霍邱县',
		"parentid": '341500'
	},
	{
		"id": '341523',
		"shortname": '舒城县',
		"parentid": '341500'
	},
	{
		"id": '341524',
		"shortname": '金寨县',
		"parentid": '341500'
	},
	{
		"id": '341525',
		"shortname": '霍山县',
		"parentid": '341500'
	},
	{
		"id": '341540',
		"shortname": '叶集',
		"parentid": '341500'
	},
	{
		"id": '341621',
		"shortname": '涡阳县',
		"parentid": '341600'
	},
	{
		"id": '341622',
		"shortname": '蒙城县',
		"parentid": '341600'
	},
	{
		"id": '341623',
		"shortname": '利辛县',
		"parentid": '341600'
	},
	{
		"id": '341702',
		"shortname": '贵池区',
		"parentid": '341700'
	},
	{
		"id": '341721',
		"shortname": '东至县',
		"parentid": '341700'
	},
	{
		"id": '341722',
		"shortname": '石台县',
		"parentid": '341700'
	},
	{
		"id": '341723',
		"shortname": '青阳县',
		"parentid": '341700'
	},
	{
		"id": '341740',
		"shortname": '九华山风景区',
		"parentid": '341700'
	},
	{
		"id": '341802',
		"shortname": '宣州区',
		"parentid": '341800'
	},
	{
		"id": '341821',
		"shortname": '郎溪县',
		"parentid": '341800'
	},
	{
		"id": '341822',
		"shortname": '广德县',
		"parentid": '341800'
	},
	{
		"id": '341823',
		"shortname": '泾县',
		"parentid": '341800'
	},
	{
		"id": '341824',
		"shortname": '绩溪县',
		"parentid": '341800'
	},
	{
		"id": '341825',
		"shortname": '旌德县',
		"parentid": '341800'
	},
	{
		"id": '341881',
		"shortname": '宁国市',
		"parentid": '341800'
	},
	{
		"id": '350105',
		"shortname": '马尾区',
		"parentid": '350100'
	},
	{
		"id": '350102',
		"shortname": '鼓楼区',
		"parentid": '350100'
	},
	{
		"id": '350103',
		"shortname": '台江区',
		"parentid": '350100'
	},
	{
		"id": '350104',
		"shortname": '仓山区',
		"parentid": '350100'
	},
	{
		"id": '350111',
		"shortname": '晋安区',
		"parentid": '350100'
	},
	{
		"id": '350121',
		"shortname": '闽侯县',
		"parentid": '350100'
	},
	{
		"id": '350122',
		"shortname": '连江县',
		"parentid": '350100'
	},
	{
		"id": '350123',
		"shortname": '罗源县',
		"parentid": '350100'
	},
	{
		"id": '350124',
		"shortname": '闽清县',
		"parentid": '350100'
	},
	{
		"id": '350125',
		"shortname": '永泰县',
		"parentid": '350100'
	},
	{
		"id": '350128',
		"shortname": '平潭县',
		"parentid": '350100'
	},
	{
		"id": '350181',
		"shortname": '福清市',
		"parentid": '350100'
	},
	{
		"id": '350182',
		"shortname": '长乐市',
		"parentid": '350100'
	},
	{
		"id": '350203',
		"shortname": '思明区',
		"parentid": '350200'
	},
	{
		"id": '350205',
		"shortname": '海沧区',
		"parentid": '350200'
	},
	{
		"id": '350206',
		"shortname": '湖里区',
		"parentid": '350200'
	},
	{
		"id": '350211',
		"shortname": '集美区',
		"parentid": '350200'
	},
	{
		"id": '350212',
		"shortname": '同安区',
		"parentid": '350200'
	},
	{
		"id": '350213',
		"shortname": '翔安区',
		"parentid": '350200'
	},
	{
		"id": '350302',
		"shortname": '城厢区',
		"parentid": '350300'
	},
	{
		"id": '350303',
		"shortname": '涵江区',
		"parentid": '350300'
	},
	{
		"id": '350304',
		"shortname": '荔城区',
		"parentid": '350300'
	},
	{
		"id": '350305',
		"shortname": '秀屿区',
		"parentid": '350300'
	},
	{
		"id": '350322',
		"shortname": '仙游县',
		"parentid": '350300'
	},
	{
		"id": '350340',
		"shortname": '湄洲岛',
		"parentid": '350300'
	},
	{
		"id": '350341',
		"shortname": '湄洲湾北岸',
		"parentid": '350300'
	},
	{
		"id": '350429',
		"shortname": '泰宁县',
		"parentid": '350400'
	},
	{
		"id": '350430',
		"shortname": '建宁县',
		"parentid": '350400'
	},
	{
		"id": '350481',
		"shortname": '永安市',
		"parentid": '350400'
	},
	{
		"id": '350402',
		"shortname": '梅列区',
		"parentid": '350400'
	},
	{
		"id": '350403',
		"shortname": '三元区',
		"parentid": '350400'
	},
	{
		"id": '350421',
		"shortname": '明溪县',
		"parentid": '350400'
	},
	{
		"id": '350423',
		"shortname": '清流县',
		"parentid": '350400'
	},
	{
		"id": '350424',
		"shortname": '宁化县',
		"parentid": '350400'
	},
	{
		"id": '350425',
		"shortname": '大田县',
		"parentid": '350400'
	},
	{
		"id": '350426',
		"shortname": '尤溪县',
		"parentid": '350400'
	},
	{
		"id": '350427',
		"shortname": '沙县',
		"parentid": '350400'
	},
	{
		"id": '350428',
		"shortname": '将乐县',
		"parentid": '350400'
	},
	{
		"id": '350502',
		"shortname": '鲤城区',
		"parentid": '350500'
	},
	{
		"id": '350503',
		"shortname": '丰泽区',
		"parentid": '350500'
	},
	{
		"id": '350504',
		"shortname": '洛江区',
		"parentid": '350500'
	},
	{
		"id": '350505',
		"shortname": '泉港区',
		"parentid": '350500'
	},
	{
		"id": '350521',
		"shortname": '惠安县',
		"parentid": '350500'
	},
	{
		"id": '350524',
		"shortname": '安溪县',
		"parentid": '350500'
	},
	{
		"id": '350525',
		"shortname": '永春县',
		"parentid": '350500'
	},
	{
		"id": '350526',
		"shortname": '德化县',
		"parentid": '350500'
	},
	{
		"id": '350527',
		"shortname": '金门县',
		"parentid": '350500'
	},
	{
		"id": '350581',
		"shortname": '石狮市',
		"parentid": '350500'
	},
	{
		"id": '350582',
		"shortname": '晋江市',
		"parentid": '350500'
	},
	{
		"id": '350583',
		"shortname": '南安市',
		"parentid": '350500'
	},
	{
		"id": '350602',
		"shortname": '芗城区',
		"parentid": '350600'
	},
	{
		"id": '350603',
		"shortname": '龙文区',
		"parentid": '350600'
	},
	{
		"id": '350622',
		"shortname": '云霄县',
		"parentid": '350600'
	},
	{
		"id": '350623',
		"shortname": '漳浦县',
		"parentid": '350600'
	},
	{
		"id": '350624',
		"shortname": '诏安县',
		"parentid": '350600'
	},
	{
		"id": '350625',
		"shortname": '长泰县',
		"parentid": '350600'
	},
	{
		"id": '350626',
		"shortname": '东山县',
		"parentid": '350600'
	},
	{
		"id": '350627',
		"shortname": '南靖县',
		"parentid": '350600'
	},
	{
		"id": '350628',
		"shortname": '平和县',
		"parentid": '350600'
	},
	{
		"id": '350629',
		"shortname": '华安县',
		"parentid": '350600'
	},
	{
		"id": '350640',
		"shortname": '常山开发区',
		"parentid": '350600'
	},
	{
		"id": '350681',
		"shortname": '龙海市',
		"parentid": '350600'
	},
	{
		"id": '350781',
		"shortname": '邵武市',
		"parentid": '350700'
	},
	{
		"id": '350782',
		"shortname": '武夷山市',
		"parentid": '350700'
	},
	{
		"id": '350783',
		"shortname": '建瓯市',
		"parentid": '350700'
	},
	{
		"id": '350784',
		"shortname": '建阳市',
		"parentid": '350700'
	},
	{
		"id": '350702',
		"shortname": '延平区',
		"parentid": '350700'
	},
	{
		"id": '350721',
		"shortname": '顺昌县',
		"parentid": '350700'
	},
	{
		"id": '350722',
		"shortname": '浦城县',
		"parentid": '350700'
	},
	{
		"id": '350723',
		"shortname": '光泽县',
		"parentid": '350700'
	},
	{
		"id": '350724',
		"shortname": '松溪县',
		"parentid": '350700'
	},
	{
		"id": '350725',
		"shortname": '政和县',
		"parentid": '350700'
	},
	{
		"id": '350802',
		"shortname": '新罗区',
		"parentid": '350800'
	},
	{
		"id": '350821',
		"shortname": '长汀县',
		"parentid": '350800'
	},
	{
		"id": '350822',
		"shortname": '永定县',
		"parentid": '350800'
	},
	{
		"id": '350823',
		"shortname": '上杭县',
		"parentid": '350800'
	},
	{
		"id": '350824',
		"shortname": '武平县',
		"parentid": '350800'
	},
	{
		"id": '350825',
		"shortname": '连城县',
		"parentid": '350800'
	},
	{
		"id": '350881',
		"shortname": '漳平市',
		"parentid": '350800'
	},
	{
		"id": '350902',
		"shortname": '蕉城区',
		"parentid": '350900'
	},
	{
		"id": '350921',
		"shortname": '霞浦县',
		"parentid": '350900'
	},
	{
		"id": '350922',
		"shortname": '古田县',
		"parentid": '350900'
	},
	{
		"id": '350923',
		"shortname": '屏南县',
		"parentid": '350900'
	},
	{
		"id": '350924',
		"shortname": '寿宁县',
		"parentid": '350900'
	},
	{
		"id": '350925',
		"shortname": '周宁县',
		"parentid": '350900'
	},
	{
		"id": '350926',
		"shortname": '柘荣县',
		"parentid": '350900'
	},
	{
		"id": '350981',
		"shortname": '福安市',
		"parentid": '350900'
	},
	{
		"id": '350982',
		"shortname": '福鼎市',
		"parentid": '350900'
	},
	{
		"id": '360112',
		"shortname": '新建区',
		"parentid": '360100'
	},
	{
		"id": '360102',
		"shortname": '东湖区',
		"parentid": '360100'
	},
	{
		"id": '360103',
		"shortname": '西湖区',
		"parentid": '360100'
	},
	{
		"id": '360104',
		"shortname": '青云谱区',
		"parentid": '360100'
	},
	{
		"id": '360105',
		"shortname": '湾里区',
		"parentid": '360100'
	},
	{
		"id": '360106',
		"shortname": '红谷滩新区',
		"parentid": '360100'
	},
	{
		"id": '360111',
		"shortname": '青山湖区',
		"parentid": '360100'
	},
	{
		"id": '360113',
		"shortname": '高新区',
		"parentid": '360100'
	},
	{
		"id": '360121',
		"shortname": '南昌县',
		"parentid": '360100'
	},
	{
		"id": '360122',
		"shortname": '新建县',
		"parentid": '360100'
	},
	{
		"id": '360123',
		"shortname": '安义县',
		"parentid": '360100'
	},
	{
		"id": '360124',
		"shortname": '进贤县',
		"parentid": '360100'
	},
	{
		"id": '360150',
		"shortname": '英雄开发区',
		"parentid": '360100'
	},
	{
		"id": '360151',
		"shortname": '桑海开发区',
		"parentid": '360100'
	},
	{
		"id": '360202',
		"shortname": '昌江区',
		"parentid": '360200'
	},
	{
		"id": '360203',
		"shortname": '珠山区',
		"parentid": '360200'
	},
	{
		"id": '360222',
		"shortname": '浮梁县',
		"parentid": '360200'
	},
	{
		"id": '360281',
		"shortname": '乐平市',
		"parentid": '360200'
	},
	{
		"id": '360302',
		"shortname": '安源区',
		"parentid": '360300'
	},
	{
		"id": '360313',
		"shortname": '湘东区',
		"parentid": '360300'
	},
	{
		"id": '360321',
		"shortname": '莲花县',
		"parentid": '360300'
	},
	{
		"id": '360322',
		"shortname": '上栗县',
		"parentid": '360300'
	},
	{
		"id": '360323',
		"shortname": '芦溪县',
		"parentid": '360300'
	},
	{
		"id": '360340',
		"shortname": '萍乡经济开发区',
		"parentid": '360300'
	},
	{
		"id": '360402',
		"shortname": '濂溪区',
		"parentid": '360400'
	},
	{
		"id": '360403',
		"shortname": '浔阳区',
		"parentid": '360400'
	},
	{
		"id": '360421',
		"shortname": '九江县',
		"parentid": '360400'
	},
	{
		"id": '360423',
		"shortname": '武宁县',
		"parentid": '360400'
	},
	{
		"id": '360424',
		"shortname": '修水县',
		"parentid": '360400'
	},
	{
		"id": '360425',
		"shortname": '永修县',
		"parentid": '360400'
	},
	{
		"id": '360426',
		"shortname": '德安县',
		"parentid": '360400'
	},
	{
		"id": '360427',
		"shortname": '星子县',
		"parentid": '360400'
	},
	{
		"id": '360428',
		"shortname": '都昌县',
		"parentid": '360400'
	},
	{
		"id": '360429',
		"shortname": '湖口县',
		"parentid": '360400'
	},
	{
		"id": '360430',
		"shortname": '彭泽县',
		"parentid": '360400'
	},
	{
		"id": '360440',
		"shortname": '开发区',
		"parentid": '360400'
	},
	{
		"id": '360441',
		"shortname": '庐山局',
		"parentid": '360400'
	},
	{
		"id": '360442',
		"shortname": '共青城开放开发区',
		"parentid": '360400'
	},
	{
		"id": '360481',
		"shortname": '瑞昌市',
		"parentid": '360400'
	},
	{
		"id": '360502',
		"shortname": '渝水区',
		"parentid": '360500'
	},
	{
		"id": '360521',
		"shortname": '分宜县',
		"parentid": '360500'
	},
	{
		"id": '360540',
		"shortname": '仙女湖区',
		"parentid": '360500'
	},
	{
		"id": '360541',
		"shortname": '新余高新技术产业园区',
		"parentid": '360500'
	},
	{
		"id": '360542',
		"shortname": '孔目江生态经济区',
		"parentid": '360500'
	},
	{
		"id": '360602',
		"shortname": '月湖区',
		"parentid": '360600'
	},
	{
		"id": '360622',
		"shortname": '余江县',
		"parentid": '360600'
	},
	{
		"id": '360681',
		"shortname": '贵溪市',
		"parentid": '360600'
	},
	{
		"id": '360721',
		"shortname": '赣县',
		"parentid": '360700'
	},
	{
		"id": '360722',
		"shortname": '信丰县',
		"parentid": '360700'
	},
	{
		"id": '360723',
		"shortname": '大余县',
		"parentid": '360700'
	},
	{
		"id": '360724',
		"shortname": '上犹县',
		"parentid": '360700'
	},
	{
		"id": '360725',
		"shortname": '崇义县',
		"parentid": '360700'
	},
	{
		"id": '360726',
		"shortname": '安远县',
		"parentid": '360700'
	},
	{
		"id": '360727',
		"shortname": '龙南县',
		"parentid": '360700'
	},
	{
		"id": '360728',
		"shortname": '定南县',
		"parentid": '360700'
	},
	{
		"id": '360729',
		"shortname": '全南县',
		"parentid": '360700'
	},
	{
		"id": '360730',
		"shortname": '宁都县',
		"parentid": '360700'
	},
	{
		"id": '360731',
		"shortname": '于都县',
		"parentid": '360700'
	},
	{
		"id": '360732',
		"shortname": '兴国县',
		"parentid": '360700'
	},
	{
		"id": '360733',
		"shortname": '会昌县',
		"parentid": '360700'
	},
	{
		"id": '360734',
		"shortname": '寻乌县',
		"parentid": '360700'
	},
	{
		"id": '360735',
		"shortname": '石城县',
		"parentid": '360700'
	},
	{
		"id": '360781',
		"shortname": '瑞金市',
		"parentid": '360700'
	},
	{
		"id": '360782',
		"shortname": '南康市',
		"parentid": '360700'
	},
	{
		"id": '360702',
		"shortname": '章贡区',
		"parentid": '360700'
	},
	{
		"id": '360802',
		"shortname": '吉州区',
		"parentid": '360800'
	},
	{
		"id": '360803',
		"shortname": '青原区',
		"parentid": '360800'
	},
	{
		"id": '360821',
		"shortname": '吉安县',
		"parentid": '360800'
	},
	{
		"id": '360822',
		"shortname": '吉水县',
		"parentid": '360800'
	},
	{
		"id": '360823',
		"shortname": '峡江县',
		"parentid": '360800'
	},
	{
		"id": '360824',
		"shortname": '新干县',
		"parentid": '360800'
	},
	{
		"id": '360825',
		"shortname": '永丰县',
		"parentid": '360800'
	},
	{
		"id": '360826',
		"shortname": '泰和县',
		"parentid": '360800'
	},
	{
		"id": '360827',
		"shortname": '遂川县',
		"parentid": '360800'
	},
	{
		"id": '360828',
		"shortname": '万安县',
		"parentid": '360800'
	},
	{
		"id": '360829',
		"shortname": '安福县',
		"parentid": '360800'
	},
	{
		"id": '360830',
		"shortname": '永新县',
		"parentid": '360800'
	},
	{
		"id": '360881',
		"shortname": '井冈山市',
		"parentid": '360800'
	},
	{
		"id": '360902',
		"shortname": '袁州区',
		"parentid": '360900'
	},
	{
		"id": '360921',
		"shortname": '奉新县',
		"parentid": '360900'
	},
	{
		"id": '360922',
		"shortname": '万载县',
		"parentid": '360900'
	},
	{
		"id": '360923',
		"shortname": '上高县',
		"parentid": '360900'
	},
	{
		"id": '360924',
		"shortname": '宜丰县',
		"parentid": '360900'
	},
	{
		"id": '360925',
		"shortname": '靖安县',
		"parentid": '360900'
	},
	{
		"id": '360926',
		"shortname": '铜鼓县',
		"parentid": '360900'
	},
	{
		"id": '360981',
		"shortname": '丰城市',
		"parentid": '360900'
	},
	{
		"id": '360982',
		"shortname": '樟树市',
		"parentid": '360900'
	},
	{
		"id": '360983',
		"shortname": '高安市',
		"parentid": '360900'
	},
	{
		"id": '361021',
		"shortname": '南城县',
		"parentid": '361000'
	},
	{
		"id": '361022',
		"shortname": '黎川县',
		"parentid": '361000'
	},
	{
		"id": '361023',
		"shortname": '南丰县',
		"parentid": '361000'
	},
	{
		"id": '361024',
		"shortname": '崇仁县',
		"parentid": '361000'
	},
	{
		"id": '361025',
		"shortname": '乐安县',
		"parentid": '361000'
	},
	{
		"id": '361026',
		"shortname": '宜黄县',
		"parentid": '361000'
	},
	{
		"id": '361027',
		"shortname": '金溪县',
		"parentid": '361000'
	},
	{
		"id": '361028',
		"shortname": '资溪县',
		"parentid": '361000'
	},
	{
		"id": '361029',
		"shortname": '东乡县',
		"parentid": '361000'
	},
	{
		"id": '361030',
		"shortname": '广昌县',
		"parentid": '361000'
	},
	{
		"id": '361040',
		"shortname": '金巢区',
		"parentid": '361000'
	},
	{
		"id": '361002',
		"shortname": '临川区',
		"parentid": '361000'
	},
	{
		"id": '361128',
		"shortname": '鄱阳县',
		"parentid": '361100'
	},
	{
		"id": '361102',
		"shortname": '信州区',
		"parentid": '361100'
	},
	{
		"id": '361121',
		"shortname": '上饶县',
		"parentid": '361100'
	},
	{
		"id": '361122',
		"shortname": '广丰县',
		"parentid": '361100'
	},
	{
		"id": '361123',
		"shortname": '玉山县',
		"parentid": '361100'
	},
	{
		"id": '361124',
		"shortname": '铅山县',
		"parentid": '361100'
	},
	{
		"id": '361125',
		"shortname": '横峰县',
		"parentid": '361100'
	},
	{
		"id": '361126',
		"shortname": '弋阳县',
		"parentid": '361100'
	},
	{
		"id": '361127',
		"shortname": '余干县',
		"parentid": '361100'
	},
	{
		"id": '361129',
		"shortname": '万年县',
		"parentid": '361100'
	},
	{
		"id": '361130',
		"shortname": '婺源县',
		"parentid": '361100'
	},
	{
		"id": '361181',
		"shortname": '德兴市',
		"parentid": '361100'
	},
	{
		"id": '370102',
		"shortname": '历下区',
		"parentid": '370100'
	},
	{
		"id": '370103',
		"shortname": '市中区',
		"parentid": '370100'
	},
	{
		"id": '370104',
		"shortname": '槐荫区',
		"parentid": '370100'
	},
	{
		"id": '370105',
		"shortname": '天桥区',
		"parentid": '370100'
	},
	{
		"id": '370112',
		"shortname": '历城区',
		"parentid": '370100'
	},
	{
		"id": '370113',
		"shortname": '长清区',
		"parentid": '370100'
	},
	{
		"id": '370124',
		"shortname": '平阴县',
		"parentid": '370100'
	},
	{
		"id": '370125',
		"shortname": '济阳县',
		"parentid": '370100'
	},
	{
		"id": '370126',
		"shortname": '商河县',
		"parentid": '370100'
	},
	{
		"id": '370140',
		"shortname": '济南高新技术产业开发区',
		"parentid": '370100'
	},
	{
		"id": '370181',
		"shortname": '章丘市',
		"parentid": '370100'
	},
	{
		"id": '370202',
		"shortname": '市南区',
		"parentid": '370200'
	},
	{
		"id": '370203',
		"shortname": '市北区',
		"parentid": '370200'
	},
	{
		"id": '370205',
		"shortname": '四方区',
		"parentid": '370200'
	},
	{
		"id": '370211',
		"shortname": '黄岛区',
		"parentid": '370200'
	},
	{
		"id": '370212',
		"shortname": '崂山区',
		"parentid": '370200'
	},
	{
		"id": '370213',
		"shortname": '李沧区',
		"parentid": '370200'
	},
	{
		"id": '370214',
		"shortname": '城阳区',
		"parentid": '370200'
	},
	{
		"id": '370281',
		"shortname": '胶州市',
		"parentid": '370200'
	},
	{
		"id": '370282',
		"shortname": '即墨市',
		"parentid": '370200'
	},
	{
		"id": '370283',
		"shortname": '平度市',
		"parentid": '370200'
	},
	{
		"id": '370284',
		"shortname": '胶南市',
		"parentid": '370200'
	},
	{
		"id": '370285',
		"shortname": '莱西市',
		"parentid": '370200'
	},
	{
		"id": '370240',
		"shortname": '保税区',
		"parentid": '370200'
	},
	{
		"id": '370302',
		"shortname": '淄川区',
		"parentid": '370300'
	},
	{
		"id": '370304',
		"shortname": '博山区',
		"parentid": '370300'
	},
	{
		"id": '370305',
		"shortname": '临淄区',
		"parentid": '370300'
	},
	{
		"id": '370306',
		"shortname": '周村区',
		"parentid": '370300'
	},
	{
		"id": '370321',
		"shortname": '桓台县',
		"parentid": '370300'
	},
	{
		"id": '370322',
		"shortname": '高青县',
		"parentid": '370300'
	},
	{
		"id": '370323',
		"shortname": '沂源县',
		"parentid": '370300'
	},
	{
		"id": '370340',
		"shortname": '高新区',
		"parentid": '370300'
	},
	{
		"id": '370303',
		"shortname": '张店区',
		"parentid": '370300'
	},
	{
		"id": '370402',
		"shortname": '市中区',
		"parentid": '370400'
	},
	{
		"id": '370403',
		"shortname": '薛城区',
		"parentid": '370400'
	},
	{
		"id": '370404',
		"shortname": '峄城区',
		"parentid": '370400'
	},
	{
		"id": '370406',
		"shortname": '山亭区',
		"parentid": '370400'
	},
	{
		"id": '370481',
		"shortname": '滕州市',
		"parentid": '370400'
	},
	{
		"id": '370405',
		"shortname": '台儿庄区',
		"parentid": '370400'
	},
	{
		"id": '370502',
		"shortname": '东营区',
		"parentid": '370500'
	},
	{
		"id": '370503',
		"shortname": '河口区',
		"parentid": '370500'
	},
	{
		"id": '370521',
		"shortname": '垦利县',
		"parentid": '370500'
	},
	{
		"id": '370522',
		"shortname": '利津县',
		"parentid": '370500'
	},
	{
		"id": '370523',
		"shortname": '广饶县',
		"parentid": '370500'
	},
	{
		"id": '370611',
		"shortname": '福山区',
		"parentid": '370600'
	},
	{
		"id": '370612',
		"shortname": '牟平区',
		"parentid": '370600'
	},
	{
		"id": '370613',
		"shortname": '莱山区',
		"parentid": '370600'
	},
	{
		"id": '370634',
		"shortname": '长岛县',
		"parentid": '370600'
	},
	{
		"id": '370640',
		"shortname": '开发区',
		"parentid": '370600'
	},
	{
		"id": '370641',
		"shortname": '高新区',
		"parentid": '370600'
	},
	{
		"id": '370681',
		"shortname": '龙口市',
		"parentid": '370600'
	},
	{
		"id": '370682',
		"shortname": '莱阳市',
		"parentid": '370600'
	},
	{
		"id": '370683',
		"shortname": '莱州市',
		"parentid": '370600'
	},
	{
		"id": '370684',
		"shortname": '蓬莱市',
		"parentid": '370600'
	},
	{
		"id": '370685',
		"shortname": '招远市',
		"parentid": '370600'
	},
	{
		"id": '370686',
		"shortname": '栖霞市',
		"parentid": '370600'
	},
	{
		"id": '370687',
		"shortname": '海阳市',
		"parentid": '370600'
	},
	{
		"id": '370602',
		"shortname": '芝罘区',
		"parentid": '370600'
	},
	{
		"id": '370704',
		"shortname": '坊子区',
		"parentid": '370700'
	},
	{
		"id": '370705',
		"shortname": '奎文区',
		"parentid": '370700'
	},
	{
		"id": '370724',
		"shortname": '临朐县',
		"parentid": '370700'
	},
	{
		"id": '370725',
		"shortname": '昌乐县',
		"parentid": '370700'
	},
	{
		"id": '370740',
		"shortname": '潍坊滨海经济开发区',
		"parentid": '370700'
	},
	{
		"id": '370781',
		"shortname": '青州市',
		"parentid": '370700'
	},
	{
		"id": '370782',
		"shortname": '诸城市',
		"parentid": '370700'
	},
	{
		"id": '370783',
		"shortname": '寿光市',
		"parentid": '370700'
	},
	{
		"id": '370784',
		"shortname": '安丘市',
		"parentid": '370700'
	},
	{
		"id": '370785',
		"shortname": '高密市',
		"parentid": '370700'
	},
	{
		"id": '370786',
		"shortname": '昌邑市',
		"parentid": '370700'
	},
	{
		"id": '370702',
		"shortname": '潍城区',
		"parentid": '370700'
	},
	{
		"id": '370703',
		"shortname": '寒亭区',
		"parentid": '370700'
	},
	{
		"id": '370802',
		"shortname": '市中区',
		"parentid": '370800'
	},
	{
		"id": '370811',
		"shortname": '任城区',
		"parentid": '370800'
	},
	{
		"id": '370826',
		"shortname": '微山县',
		"parentid": '370800'
	},
	{
		"id": '370827',
		"shortname": '鱼台县',
		"parentid": '370800'
	},
	{
		"id": '370828',
		"shortname": '金乡县',
		"parentid": '370800'
	},
	{
		"id": '370829',
		"shortname": '嘉祥县',
		"parentid": '370800'
	},
	{
		"id": '370830',
		"shortname": '汶上县',
		"parentid": '370800'
	},
	{
		"id": '370831',
		"shortname": '泗水县',
		"parentid": '370800'
	},
	{
		"id": '370832',
		"shortname": '梁山县',
		"parentid": '370800'
	},
	{
		"id": '370881',
		"shortname": '曲阜市',
		"parentid": '370800'
	},
	{
		"id": '370882',
		"shortname": '兖州市',
		"parentid": '370800'
	},
	{
		"id": '370883',
		"shortname": '邹城市',
		"parentid": '370800'
	},
	{
		"id": '370902',
		"shortname": '泰山区',
		"parentid": '370900'
	},
	{
		"id": '370911',
		"shortname": '岱岳区',
		"parentid": '370900'
	},
	{
		"id": '370921',
		"shortname": '宁阳县',
		"parentid": '370900'
	},
	{
		"id": '370923',
		"shortname": '东平县',
		"parentid": '370900'
	},
	{
		"id": '370982',
		"shortname": '新泰市',
		"parentid": '370900'
	},
	{
		"id": '370983',
		"shortname": '肥城市',
		"parentid": '370900'
	},
	{
		"id": '371002',
		"shortname": '环翠区',
		"parentid": '371000'
	},
	{
		"id": '371040',
		"shortname": '高技术产业开发区',
		"parentid": '371000'
	},
	{
		"id": '371041',
		"shortname": '经济技术开发区',
		"parentid": '371000'
	},
	{
		"id": '371081',
		"shortname": '文登市',
		"parentid": '371000'
	},
	{
		"id": '371082',
		"shortname": '荣成市',
		"parentid": '371000'
	},
	{
		"id": '371083',
		"shortname": '乳山市',
		"parentid": '371000'
	},
	{
		"id": '371102',
		"shortname": '东港区',
		"parentid": '371100'
	},
	{
		"id": '371103',
		"shortname": '岚山区',
		"parentid": '371100'
	},
	{
		"id": '371121',
		"shortname": '五莲县',
		"parentid": '371100'
	},
	{
		"id": '371122',
		"shortname": '莒县',
		"parentid": '371100'
	},
	{
		"id": '371140',
		"shortname": '经济开发区',
		"parentid": '371100'
	},
	{
		"id": '371141',
		"shortname": '山海天旅游度假区',
		"parentid": '371100'
	},
	{
		"id": '371202',
		"shortname": '莱城区',
		"parentid": '371200'
	},
	{
		"id": '371203',
		"shortname": '钢城区',
		"parentid": '371200'
	},
	{
		"id": '371324',
		"shortname": '兰陵县',
		"parentid": '371300'
	},
	{
		"id": '371302',
		"shortname": '兰山区',
		"parentid": '371300'
	},
	{
		"id": '371311',
		"shortname": '罗庄区',
		"parentid": '371300'
	},
	{
		"id": '371312',
		"shortname": '河东区',
		"parentid": '371300'
	},
	{
		"id": '371321',
		"shortname": '沂南县',
		"parentid": '371300'
	},
	{
		"id": '371322',
		"shortname": '郯城县',
		"parentid": '371300'
	},
	{
		"id": '371323',
		"shortname": '沂水县',
		"parentid": '371300'
	},
	{
		"id": '371325',
		"shortname": '费县',
		"parentid": '371300'
	},
	{
		"id": '371326',
		"shortname": '平邑县',
		"parentid": '371300'
	},
	{
		"id": '371327',
		"shortname": '莒南县',
		"parentid": '371300'
	},
	{
		"id": '371328',
		"shortname": '蒙阴县',
		"parentid": '371300'
	},
	{
		"id": '371329',
		"shortname": '临沭县',
		"parentid": '371300'
	},
	{
		"id": '371402',
		"shortname": '德城区',
		"parentid": '371400'
	},
	{
		"id": '371421',
		"shortname": '陵县',
		"parentid": '371400'
	},
	{
		"id": '371422',
		"shortname": '宁津县',
		"parentid": '371400'
	},
	{
		"id": '371423',
		"shortname": '庆云县',
		"parentid": '371400'
	},
	{
		"id": '371424',
		"shortname": '临邑县',
		"parentid": '371400'
	},
	{
		"id": '371425',
		"shortname": '齐河县',
		"parentid": '371400'
	},
	{
		"id": '371426',
		"shortname": '平原县',
		"parentid": '371400'
	},
	{
		"id": '371427',
		"shortname": '夏津县',
		"parentid": '371400'
	},
	{
		"id": '371428',
		"shortname": '武城县',
		"parentid": '371400'
	},
	{
		"id": '371481',
		"shortname": '乐陵市',
		"parentid": '371400'
	},
	{
		"id": '371482',
		"shortname": '禹城市',
		"parentid": '371400'
	},
	{
		"id": '371502',
		"shortname": '东昌府区',
		"parentid": '371500'
	},
	{
		"id": '371521',
		"shortname": '阳谷县',
		"parentid": '371500'
	},
	{
		"id": '371522',
		"shortname": '莘县',
		"parentid": '371500'
	},
	{
		"id": '371523',
		"shortname": '茌平县',
		"parentid": '371500'
	},
	{
		"id": '371524',
		"shortname": '东阿县',
		"parentid": '371500'
	},
	{
		"id": '371525',
		"shortname": '冠县',
		"parentid": '371500'
	},
	{
		"id": '371526',
		"shortname": '高唐县',
		"parentid": '371500'
	},
	{
		"id": '371581',
		"shortname": '临清市',
		"parentid": '371500'
	},
	{
		"id": '371602',
		"shortname": '滨城区',
		"parentid": '371600'
	},
	{
		"id": '371621',
		"shortname": '惠民县',
		"parentid": '371600'
	},
	{
		"id": '371622',
		"shortname": '阳信县',
		"parentid": '371600'
	},
	{
		"id": '371623',
		"shortname": '无棣县',
		"parentid": '371600'
	},
	{
		"id": '371624',
		"shortname": '沾化县',
		"parentid": '371600'
	},
	{
		"id": '371625',
		"shortname": '博兴县',
		"parentid": '371600'
	},
	{
		"id": '371626',
		"shortname": '邹平县',
		"parentid": '371600'
	},
	{
		"id": '371640',
		"shortname": '开发区',
		"parentid": '371600'
	},
	{
		"id": '371722',
		"shortname": '单县',
		"parentid": '371700'
	},
	{
		"id": '371723',
		"shortname": '成武县',
		"parentid": '371700'
	},
	{
		"id": '371724',
		"shortname": '巨野县',
		"parentid": '371700'
	},
	{
		"id": '371725',
		"shortname": '郓城县',
		"parentid": '371700'
	},
	{
		"id": '371726',
		"shortname": '鄄城县',
		"parentid": '371700'
	},
	{
		"id": '371727',
		"shortname": '定陶县',
		"parentid": '371700'
	},
	{
		"id": '371728',
		"shortname": '东明县',
		"parentid": '371700'
	},
	{
		"id": '371702',
		"shortname": '牡丹区',
		"parentid": '371700'
	},
	{
		"id": '371721',
		"shortname": '曹县',
		"parentid": '371700'
	},
	{
		"id": '410102',
		"shortname": '中原区',
		"parentid": '410100'
	},
	{
		"id": '410103',
		"shortname": '二七区',
		"parentid": '410100'
	},
	{
		"id": '410104',
		"shortname": '管城回族区',
		"parentid": '410100'
	},
	{
		"id": '410105',
		"shortname": '金水区',
		"parentid": '410100'
	},
	{
		"id": '410106',
		"shortname": '上街区',
		"parentid": '410100'
	},
	{
		"id": '410108',
		"shortname": '惠济区',
		"parentid": '410100'
	},
	{
		"id": '410122',
		"shortname": '中牟县',
		"parentid": '410100'
	},
	{
		"id": '410181',
		"shortname": '巩义市',
		"parentid": '410100'
	},
	{
		"id": '410182',
		"shortname": '荥阳市',
		"parentid": '410100'
	},
	{
		"id": '410183',
		"shortname": '新密市',
		"parentid": '410100'
	},
	{
		"id": '410184',
		"shortname": '新郑市',
		"parentid": '410100'
	},
	{
		"id": '410185',
		"shortname": '登封市',
		"parentid": '410100'
	},
	{
		"id": '410202',
		"shortname": '龙亭区',
		"parentid": '410200'
	},
	{
		"id": '410203',
		"shortname": '顺河回族区',
		"parentid": '410200'
	},
	{
		"id": '410204',
		"shortname": '鼓楼区',
		"parentid": '410200'
	},
	{
		"id": '410205',
		"shortname": '禹王台区',
		"parentid": '410200'
	},
	{
		"id": '410211',
		"shortname": '金明区',
		"parentid": '410200'
	},
	{
		"id": '410221',
		"shortname": '杞县',
		"parentid": '410200'
	},
	{
		"id": '410222',
		"shortname": '通许县',
		"parentid": '410200'
	},
	{
		"id": '410223',
		"shortname": '尉氏县',
		"parentid": '410200'
	},
	{
		"id": '410224',
		"shortname": '开封县',
		"parentid": '410200'
	},
	{
		"id": '410225',
		"shortname": '兰考县',
		"parentid": '410200'
	},
	{
		"id": '410302',
		"shortname": '老城区',
		"parentid": '410300'
	},
	{
		"id": '410303',
		"shortname": '西工区',
		"parentid": '410300'
	},
	{
		"id": '410304',
		"shortname": '瀍河回族区',
		"parentid": '410300'
	},
	{
		"id": '410305',
		"shortname": '涧西区',
		"parentid": '410300'
	},
	{
		"id": '410306',
		"shortname": '吉利区',
		"parentid": '410300'
	},
	{
		"id": '410311',
		"shortname": '洛龙区',
		"parentid": '410300'
	},
	{
		"id": '410322',
		"shortname": '孟津县',
		"parentid": '410300'
	},
	{
		"id": '410323',
		"shortname": '新安县',
		"parentid": '410300'
	},
	{
		"id": '410324',
		"shortname": '栾川县',
		"parentid": '410300'
	},
	{
		"id": '410325',
		"shortname": '嵩县',
		"parentid": '410300'
	},
	{
		"id": '410326',
		"shortname": '汝阳县',
		"parentid": '410300'
	},
	{
		"id": '410327',
		"shortname": '宜阳县',
		"parentid": '410300'
	},
	{
		"id": '410328',
		"shortname": '洛宁县',
		"parentid": '410300'
	},
	{
		"id": '410329',
		"shortname": '伊川县',
		"parentid": '410300'
	},
	{
		"id": '410340',
		"shortname": '高新技术开发区',
		"parentid": '410300'
	},
	{
		"id": '410381',
		"shortname": '偃师市',
		"parentid": '410300'
	},
	{
		"id": '410402',
		"shortname": '新华区',
		"parentid": '410400'
	},
	{
		"id": '410403',
		"shortname": '卫东区',
		"parentid": '410400'
	},
	{
		"id": '410404',
		"shortname": '石龙区',
		"parentid": '410400'
	},
	{
		"id": '410411',
		"shortname": '湛河区',
		"parentid": '410400'
	},
	{
		"id": '410421',
		"shortname": '宝丰县',
		"parentid": '410400'
	},
	{
		"id": '410422',
		"shortname": '叶县',
		"parentid": '410400'
	},
	{
		"id": '410423',
		"shortname": '鲁山县',
		"parentid": '410400'
	},
	{
		"id": '410425',
		"shortname": '郏县',
		"parentid": '410400'
	},
	{
		"id": '410481',
		"shortname": '舞钢市',
		"parentid": '410400'
	},
	{
		"id": '410482',
		"shortname": '汝州市',
		"parentid": '410400'
	},
	{
		"id": '410502',
		"shortname": '文峰区',
		"parentid": '410500'
	},
	{
		"id": '410503',
		"shortname": '北关区',
		"parentid": '410500'
	},
	{
		"id": '410505',
		"shortname": '殷都区',
		"parentid": '410500'
	},
	{
		"id": '410506',
		"shortname": '龙安区',
		"parentid": '410500'
	},
	{
		"id": '410522',
		"shortname": '安阳县',
		"parentid": '410500'
	},
	{
		"id": '410523',
		"shortname": '汤阴县',
		"parentid": '410500'
	},
	{
		"id": '410526',
		"shortname": '滑县',
		"parentid": '410500'
	},
	{
		"id": '410527',
		"shortname": '内黄县',
		"parentid": '410500'
	},
	{
		"id": '410540',
		"shortname": '开发区',
		"parentid": '410500'
	},
	{
		"id": '410581',
		"shortname": '林州市',
		"parentid": '410500'
	},
	{
		"id": '410602',
		"shortname": '鹤山区',
		"parentid": '410600'
	},
	{
		"id": '410603',
		"shortname": '山城区',
		"parentid": '410600'
	},
	{
		"id": '410611',
		"shortname": '淇滨区',
		"parentid": '410600'
	},
	{
		"id": '410621',
		"shortname": '浚县',
		"parentid": '410600'
	},
	{
		"id": '410622',
		"shortname": '淇县',
		"parentid": '410600'
	},
	{
		"id": '410640',
		"shortname": '开发区',
		"parentid": '410600'
	},
	{
		"id": '410702',
		"shortname": '红旗区',
		"parentid": '410700'
	},
	{
		"id": '410703',
		"shortname": '卫滨区',
		"parentid": '410700'
	},
	{
		"id": '410704',
		"shortname": '凤泉区',
		"parentid": '410700'
	},
	{
		"id": '410711',
		"shortname": '牧野区',
		"parentid": '410700'
	},
	{
		"id": '410721',
		"shortname": '新乡县',
		"parentid": '410700'
	},
	{
		"id": '410724',
		"shortname": '获嘉县',
		"parentid": '410700'
	},
	{
		"id": '410725',
		"shortname": '原阳县',
		"parentid": '410700'
	},
	{
		"id": '410726',
		"shortname": '延津县',
		"parentid": '410700'
	},
	{
		"id": '410727',
		"shortname": '封丘县',
		"parentid": '410700'
	},
	{
		"id": '410728',
		"shortname": '长垣县',
		"parentid": '410700'
	},
	{
		"id": '410740',
		"shortname": '开发区',
		"parentid": '410700'
	},
	{
		"id": '410781',
		"shortname": '卫辉市',
		"parentid": '410700'
	},
	{
		"id": '410782',
		"shortname": '辉县市',
		"parentid": '410700'
	},
	{
		"id": '410802',
		"shortname": '解放区',
		"parentid": '410800'
	},
	{
		"id": '410803',
		"shortname": '中站区',
		"parentid": '410800'
	},
	{
		"id": '410804',
		"shortname": '马村区',
		"parentid": '410800'
	},
	{
		"id": '410811',
		"shortname": '山阳区',
		"parentid": '410800'
	},
	{
		"id": '410821',
		"shortname": '修武县',
		"parentid": '410800'
	},
	{
		"id": '410822',
		"shortname": '博爱县',
		"parentid": '410800'
	},
	{
		"id": '410825',
		"shortname": '温县',
		"parentid": '410800'
	},
	{
		"id": '410840',
		"shortname": '高新区',
		"parentid": '410800'
	},
	{
		"id": '410882',
		"shortname": '沁阳市',
		"parentid": '410800'
	},
	{
		"id": '410883',
		"shortname": '孟州市',
		"parentid": '410800'
	},
	{
		"id": '410823',
		"shortname": '武陟县',
		"parentid": '410800'
	},
	{
		"id": '410902',
		"shortname": '华龙区',
		"parentid": '410900'
	},
	{
		"id": '410922',
		"shortname": '清丰县',
		"parentid": '410900'
	},
	{
		"id": '410923',
		"shortname": '南乐县',
		"parentid": '410900'
	},
	{
		"id": '410926',
		"shortname": '范县',
		"parentid": '410900'
	},
	{
		"id": '410927',
		"shortname": '台前县',
		"parentid": '410900'
	},
	{
		"id": '410928',
		"shortname": '濮阳县',
		"parentid": '410900'
	},
	{
		"id": '410940',
		"shortname": '高新区',
		"parentid": '410900'
	},
	{
		"id": '411002',
		"shortname": '魏都区',
		"parentid": '411000'
	},
	{
		"id": '411023',
		"shortname": '许昌县',
		"parentid": '411000'
	},
	{
		"id": '411024',
		"shortname": '鄢陵县',
		"parentid": '411000'
	},
	{
		"id": '411025',
		"shortname": '襄城县',
		"parentid": '411000'
	},
	{
		"id": '411081',
		"shortname": '禹州市',
		"parentid": '411000'
	},
	{
		"id": '411082',
		"shortname": '长葛市',
		"parentid": '411000'
	},
	{
		"id": '411102',
		"shortname": '源汇区',
		"parentid": '411100'
	},
	{
		"id": '411103',
		"shortname": '郾城区',
		"parentid": '411100'
	},
	{
		"id": '411104',
		"shortname": '召陵区',
		"parentid": '411100'
	},
	{
		"id": '411121',
		"shortname": '舞阳县',
		"parentid": '411100'
	},
	{
		"id": '411122',
		"shortname": '临颍县',
		"parentid": '411100'
	},
	{
		"id": '411202',
		"shortname": '湖滨区',
		"parentid": '411200'
	},
	{
		"id": '411221',
		"shortname": '渑池县',
		"parentid": '411200'
	},
	{
		"id": '411222',
		"shortname": '陕县',
		"parentid": '411200'
	},
	{
		"id": '411224',
		"shortname": '卢氏县',
		"parentid": '411200'
	},
	{
		"id": '411281',
		"shortname": '义马市',
		"parentid": '411200'
	},
	{
		"id": '411282',
		"shortname": '灵宝市',
		"parentid": '411200'
	},
	{
		"id": '411302',
		"shortname": '宛城区',
		"parentid": '411300'
	},
	{
		"id": '411303',
		"shortname": '卧龙区',
		"parentid": '411300'
	},
	{
		"id": '411321',
		"shortname": '南召县',
		"parentid": '411300'
	},
	{
		"id": '411322',
		"shortname": '方城县',
		"parentid": '411300'
	},
	{
		"id": '411323',
		"shortname": '西峡县',
		"parentid": '411300'
	},
	{
		"id": '411324',
		"shortname": '镇平县',
		"parentid": '411300'
	},
	{
		"id": '411325',
		"shortname": '内乡县',
		"parentid": '411300'
	},
	{
		"id": '411326',
		"shortname": '淅川县',
		"parentid": '411300'
	},
	{
		"id": '411327',
		"shortname": '社旗县',
		"parentid": '411300'
	},
	{
		"id": '411328',
		"shortname": '唐河县',
		"parentid": '411300'
	},
	{
		"id": '411329',
		"shortname": '新野县',
		"parentid": '411300'
	},
	{
		"id": '411330',
		"shortname": '桐柏县',
		"parentid": '411300'
	},
	{
		"id": '411381',
		"shortname": '邓州市',
		"parentid": '411300'
	},
	{
		"id": '411402',
		"shortname": '梁园区',
		"parentid": '411400'
	},
	{
		"id": '411403',
		"shortname": '睢阳区',
		"parentid": '411400'
	},
	{
		"id": '411421',
		"shortname": '民权县',
		"parentid": '411400'
	},
	{
		"id": '411422',
		"shortname": '睢县',
		"parentid": '411400'
	},
	{
		"id": '411423',
		"shortname": '宁陵县',
		"parentid": '411400'
	},
	{
		"id": '411424',
		"shortname": '柘城县',
		"parentid": '411400'
	},
	{
		"id": '411425',
		"shortname": '虞城县',
		"parentid": '411400'
	},
	{
		"id": '411426',
		"shortname": '夏邑县',
		"parentid": '411400'
	},
	{
		"id": '411440',
		"shortname": '开发区',
		"parentid": '411400'
	},
	{
		"id": '411481',
		"shortname": '永城市',
		"parentid": '411400'
	},
	{
		"id": '411502',
		"shortname": '浉河区',
		"parentid": '411500'
	},
	{
		"id": '411503',
		"shortname": '平桥区',
		"parentid": '411500'
	},
	{
		"id": '411521',
		"shortname": '罗山县',
		"parentid": '411500'
	},
	{
		"id": '411522',
		"shortname": '光山县',
		"parentid": '411500'
	},
	{
		"id": '411523',
		"shortname": '新县',
		"parentid": '411500'
	},
	{
		"id": '411524',
		"shortname": '商城县',
		"parentid": '411500'
	},
	{
		"id": '411525',
		"shortname": '固始县',
		"parentid": '411500'
	},
	{
		"id": '411526',
		"shortname": '潢川县',
		"parentid": '411500'
	},
	{
		"id": '411527',
		"shortname": '淮滨县',
		"parentid": '411500'
	},
	{
		"id": '411528',
		"shortname": '息县',
		"parentid": '411500'
	},
	{
		"id": '411540',
		"shortname": '南湖湾风景区',
		"parentid": '411500'
	},
	{
		"id": '411541',
		"shortname": '鸡公山管理区',
		"parentid": '411500'
	},
	{
		"id": '411542',
		"shortname": '上天梯非金属矿管理区',
		"parentid": '411500'
	},
	{
		"id": '411543',
		"shortname": '工业城',
		"parentid": '411500'
	},
	{
		"id": '411544',
		"shortname": '羊山新区',
		"parentid": '411500'
	},
	{
		"id": '411545',
		"shortname": '潢川经济技术开发区',
		"parentid": '411500'
	},
	{
		"id": '411602',
		"shortname": '川汇区',
		"parentid": '411600'
	},
	{
		"id": '411621',
		"shortname": '扶沟县',
		"parentid": '411600'
	},
	{
		"id": '411622',
		"shortname": '西华县',
		"parentid": '411600'
	},
	{
		"id": '411623',
		"shortname": '商水县',
		"parentid": '411600'
	},
	{
		"id": '411624',
		"shortname": '沈丘县',
		"parentid": '411600'
	},
	{
		"id": '411625',
		"shortname": '郸城县',
		"parentid": '411600'
	},
	{
		"id": '411626',
		"shortname": '淮阳县',
		"parentid": '411600'
	},
	{
		"id": '411627',
		"shortname": '太康县',
		"parentid": '411600'
	},
	{
		"id": '411640',
		"shortname": '黄泛区',
		"parentid": '411600'
	},
	{
		"id": '411681',
		"shortname": '项城市',
		"parentid": '411600'
	},
	{
		"id": '411628',
		"shortname": '鹿邑县',
		"parentid": '411600'
	},
	{
		"id": '411702',
		"shortname": '驿城区',
		"parentid": '411700'
	},
	{
		"id": '411721',
		"shortname": '西平县',
		"parentid": '411700'
	},
	{
		"id": '411722',
		"shortname": '上蔡县',
		"parentid": '411700'
	},
	{
		"id": '411723',
		"shortname": '平舆县',
		"parentid": '411700'
	},
	{
		"id": '411724',
		"shortname": '正阳县',
		"parentid": '411700'
	},
	{
		"id": '411725',
		"shortname": '确山县',
		"parentid": '411700'
	},
	{
		"id": '411726',
		"shortname": '泌阳县',
		"parentid": '411700'
	},
	{
		"id": '411727',
		"shortname": '汝南县',
		"parentid": '411700'
	},
	{
		"id": '411728',
		"shortname": '遂平县',
		"parentid": '411700'
	},
	{
		"id": '411729',
		"shortname": '新蔡县',
		"parentid": '411700'
	},
	{
		"id": '411740',
		"shortname": '高新区',
		"parentid": '411700'
	},
	{
		"id": '420112',
		"shortname": '东西湖区',
		"parentid": '420100'
	},
	{
		"id": '420113',
		"shortname": '汉南区',
		"parentid": '420100'
	},
	{
		"id": '420114',
		"shortname": '蔡甸区',
		"parentid": '420100'
	},
	{
		"id": '420115',
		"shortname": '江夏区',
		"parentid": '420100'
	},
	{
		"id": '420116',
		"shortname": '黄陂区',
		"parentid": '420100'
	},
	{
		"id": '420117',
		"shortname": '新洲区',
		"parentid": '420100'
	},
	{
		"id": '420140',
		"shortname": '东湖高新开发区',
		"parentid": '420100'
	},
	{
		"id": '420141',
		"shortname": '经济开发区',
		"parentid": '420100'
	},
	{
		"id": '420102',
		"shortname": '江岸区',
		"parentid": '420100'
	},
	{
		"id": '420103',
		"shortname": '江汉区',
		"parentid": '420100'
	},
	{
		"id": '420104',
		"shortname": '硚口区',
		"parentid": '420100'
	},
	{
		"id": '420105',
		"shortname": '汉阳区',
		"parentid": '420100'
	},
	{
		"id": '420106',
		"shortname": '武昌区',
		"parentid": '420100'
	},
	{
		"id": '420107',
		"shortname": '青山区',
		"parentid": '420100'
	},
	{
		"id": '420111',
		"shortname": '洪山区',
		"parentid": '420100'
	},
	{
		"id": '420202',
		"shortname": '黄石港区',
		"parentid": '420200'
	},
	{
		"id": '420203',
		"shortname": '西塞山区',
		"parentid": '420200'
	},
	{
		"id": '420204',
		"shortname": '下陆区',
		"parentid": '420200'
	},
	{
		"id": '420205',
		"shortname": '铁山区',
		"parentid": '420200'
	},
	{
		"id": '420222',
		"shortname": '阳新县',
		"parentid": '420200'
	},
	{
		"id": '420281',
		"shortname": '大冶市',
		"parentid": '420200'
	},
	{
		"id": '420302',
		"shortname": '茅箭区',
		"parentid": '420300'
	},
	{
		"id": '420303',
		"shortname": '张湾区',
		"parentid": '420300'
	},
	{
		"id": '420321',
		"shortname": '郧县',
		"parentid": '420300'
	},
	{
		"id": '420322',
		"shortname": '郧西县',
		"parentid": '420300'
	},
	{
		"id": '420323',
		"shortname": '竹山县',
		"parentid": '420300'
	},
	{
		"id": '420324',
		"shortname": '竹溪县',
		"parentid": '420300'
	},
	{
		"id": '420325',
		"shortname": '房县',
		"parentid": '420300'
	},
	{
		"id": '420340',
		"shortname": '武当山特区',
		"parentid": '420300'
	},
	{
		"id": '420381',
		"shortname": '丹江口市',
		"parentid": '420300'
	},
	{
		"id": '420502',
		"shortname": '西陵区',
		"parentid": '420500'
	},
	{
		"id": '420503',
		"shortname": '伍家岗区',
		"parentid": '420500'
	},
	{
		"id": '420504',
		"shortname": '点军区',
		"parentid": '420500'
	},
	{
		"id": '420505',
		"shortname": '猇亭区',
		"parentid": '420500'
	},
	{
		"id": '420506',
		"shortname": '夷陵区',
		"parentid": '420500'
	},
	{
		"id": '420525',
		"shortname": '远安县',
		"parentid": '420500'
	},
	{
		"id": '420526',
		"shortname": '兴山县',
		"parentid": '420500'
	},
	{
		"id": '420527',
		"shortname": '秭归县',
		"parentid": '420500'
	},
	{
		"id": '420528',
		"shortname": '长阳土家族自治县',
		"parentid": '420500'
	},
	{
		"id": '420529',
		"shortname": '五峰土家族自治县',
		"parentid": '420500'
	},
	{
		"id": '420540',
		"shortname": '开发区',
		"parentid": '420500'
	},
	{
		"id": '420581',
		"shortname": '宜都市',
		"parentid": '420500'
	},
	{
		"id": '420583',
		"shortname": '枝江市',
		"parentid": '420500'
	},
	{
		"id": '420582',
		"shortname": '当阳市',
		"parentid": '420500'
	},
	{
		"id": '420607',
		"shortname": '襄州区',
		"parentid": '420600'
	},
	{
		"id": '420602',
		"shortname": '襄城区',
		"parentid": '420600'
	},
	{
		"id": '420606',
		"shortname": '樊城区',
		"parentid": '420600'
	},
	{
		"id": '420624',
		"shortname": '南漳县',
		"parentid": '420600'
	},
	{
		"id": '420625',
		"shortname": '谷城县',
		"parentid": '420600'
	},
	{
		"id": '420626',
		"shortname": '保康县',
		"parentid": '420600'
	},
	{
		"id": '420682',
		"shortname": '老河口市',
		"parentid": '420600'
	},
	{
		"id": '420683',
		"shortname": '枣阳市',
		"parentid": '420600'
	},
	{
		"id": '420684',
		"shortname": '宜城市',
		"parentid": '420600'
	},
	{
		"id": '420640',
		"shortname": '高新技术开发区',
		"parentid": '420600'
	},
	{
		"id": '420702',
		"shortname": '梁子湖区',
		"parentid": '420700'
	},
	{
		"id": '420703',
		"shortname": '华容区',
		"parentid": '420700'
	},
	{
		"id": '420704',
		"shortname": '鄂城区',
		"parentid": '420700'
	},
	{
		"id": '420740',
		"shortname": '葛店开发区',
		"parentid": '420700'
	},
	{
		"id": '420741',
		"shortname": '长港农场',
		"parentid": '420700'
	},
	{
		"id": '420802',
		"shortname": '东宝区',
		"parentid": '420800'
	},
	{
		"id": '420804',
		"shortname": '掇刀区',
		"parentid": '420800'
	},
	{
		"id": '420821',
		"shortname": '京山县',
		"parentid": '420800'
	},
	{
		"id": '420822',
		"shortname": '沙洋县',
		"parentid": '420800'
	},
	{
		"id": '420840',
		"shortname": '屈家岭管理区',
		"parentid": '420800'
	},
	{
		"id": '420881',
		"shortname": '钟祥市',
		"parentid": '420800'
	},
	{
		"id": '420902',
		"shortname": '孝南区',
		"parentid": '420900'
	},
	{
		"id": '420921',
		"shortname": '孝昌县',
		"parentid": '420900'
	},
	{
		"id": '420922',
		"shortname": '大悟县',
		"parentid": '420900'
	},
	{
		"id": '420923',
		"shortname": '云梦县',
		"parentid": '420900'
	},
	{
		"id": '420981',
		"shortname": '应城市',
		"parentid": '420900'
	},
	{
		"id": '420982',
		"shortname": '安陆市',
		"parentid": '420900'
	},
	{
		"id": '420984',
		"shortname": '汉川市',
		"parentid": '420900'
	},
	{
		"id": '421002',
		"shortname": '沙市区',
		"parentid": '421000'
	},
	{
		"id": '421003',
		"shortname": '荆州区',
		"parentid": '421000'
	},
	{
		"id": '421022',
		"shortname": '公安县',
		"parentid": '421000'
	},
	{
		"id": '421023',
		"shortname": '监利县',
		"parentid": '421000'
	},
	{
		"id": '421024',
		"shortname": '江陵县',
		"parentid": '421000'
	},
	{
		"id": '421081',
		"shortname": '石首市',
		"parentid": '421000'
	},
	{
		"id": '421083',
		"shortname": '洪湖市',
		"parentid": '421000'
	},
	{
		"id": '421087',
		"shortname": '松滋市',
		"parentid": '421000'
	},
	{
		"id": '421102',
		"shortname": '黄州区',
		"parentid": '421100'
	},
	{
		"id": '421121',
		"shortname": '团风县',
		"parentid": '421100'
	},
	{
		"id": '421122',
		"shortname": '红安县',
		"parentid": '421100'
	},
	{
		"id": '421123',
		"shortname": '罗田县',
		"parentid": '421100'
	},
	{
		"id": '421124',
		"shortname": '英山县',
		"parentid": '421100'
	},
	{
		"id": '421125',
		"shortname": '浠水县',
		"parentid": '421100'
	},
	{
		"id": '421126',
		"shortname": '蕲春县',
		"parentid": '421100'
	},
	{
		"id": '421127',
		"shortname": '黄梅县',
		"parentid": '421100'
	},
	{
		"id": '421181',
		"shortname": '麻城市',
		"parentid": '421100'
	},
	{
		"id": '421182',
		"shortname": '武穴市',
		"parentid": '421100'
	},
	{
		"id": '421202',
		"shortname": '咸安区',
		"parentid": '421200'
	},
	{
		"id": '421221',
		"shortname": '嘉鱼县',
		"parentid": '421200'
	},
	{
		"id": '421222',
		"shortname": '通城县',
		"parentid": '421200'
	},
	{
		"id": '421223',
		"shortname": '崇阳县',
		"parentid": '421200'
	},
	{
		"id": '421224',
		"shortname": '通山县',
		"parentid": '421200'
	},
	{
		"id": '421281',
		"shortname": '赤壁市',
		"parentid": '421200'
	},
	{
		"id": '421302',
		"shortname": '曾都区',
		"parentid": '421300'
	},
	{
		"id": '421321',
		"shortname": '随县',
		"parentid": '421300'
	},
	{
		"id": '421381',
		"shortname": '广水市',
		"parentid": '421300'
	},
	{
		"id": '422801',
		"shortname": '恩施市',
		"parentid": '422800'
	},
	{
		"id": '422802',
		"shortname": '利川市',
		"parentid": '422800'
	},
	{
		"id": '422822',
		"shortname": '建始县',
		"parentid": '422800'
	},
	{
		"id": '422823',
		"shortname": '巴东县',
		"parentid": '422800'
	},
	{
		"id": '422825',
		"shortname": '宣恩县',
		"parentid": '422800'
	},
	{
		"id": '422826',
		"shortname": '咸丰县',
		"parentid": '422800'
	},
	{
		"id": '422827',
		"shortname": '来凤县',
		"parentid": '422800'
	},
	{
		"id": '422828',
		"shortname": '鹤峰县',
		"parentid": '422800'
	},
	{
		"id": '429004',
		"shortname": '仙桃市',
		"parentid": '429000'
	},
	{
		"id": '429005',
		"shortname": '潜江市',
		"parentid": '429000'
	},
	{
		"id": '429006',
		"shortname": '天门市',
		"parentid": '429000'
	},
	{
		"id": '429021',
		"shortname": '神农架林区',
		"parentid": '429000'
	},
	{
		"id": '430102',
		"shortname": '芙蓉区',
		"parentid": '430100'
	},
	{
		"id": '430103',
		"shortname": '天心区',
		"parentid": '430100'
	},
	{
		"id": '430104',
		"shortname": '岳麓区',
		"parentid": '430100'
	},
	{
		"id": '430105',
		"shortname": '开福区',
		"parentid": '430100'
	},
	{
		"id": '430111',
		"shortname": '雨花区',
		"parentid": '430100'
	},
	{
		"id": '430121',
		"shortname": '长沙县',
		"parentid": '430100'
	},
	{
		"id": '430122',
		"shortname": '望城县',
		"parentid": '430100'
	},
	{
		"id": '430124',
		"shortname": '宁乡县',
		"parentid": '430100'
	},
	{
		"id": '430181',
		"shortname": '浏阳市',
		"parentid": '430100'
	},
	{
		"id": '430202',
		"shortname": '荷塘区',
		"parentid": '430200'
	},
	{
		"id": '430203',
		"shortname": '芦淞区',
		"parentid": '430200'
	},
	{
		"id": '430204',
		"shortname": '石峰区',
		"parentid": '430200'
	},
	{
		"id": '430211',
		"shortname": '天元区',
		"parentid": '430200'
	},
	{
		"id": '430221',
		"shortname": '株洲县',
		"parentid": '430200'
	},
	{
		"id": '430223',
		"shortname": '攸县',
		"parentid": '430200'
	},
	{
		"id": '430224',
		"shortname": '茶陵县',
		"parentid": '430200'
	},
	{
		"id": '430225',
		"shortname": '炎陵县',
		"parentid": '430200'
	},
	{
		"id": '430240',
		"shortname": '云龙示范区',
		"parentid": '430200'
	},
	{
		"id": '430281',
		"shortname": '醴陵市',
		"parentid": '430200'
	},
	{
		"id": '430302',
		"shortname": '雨湖区',
		"parentid": '430300'
	},
	{
		"id": '430304',
		"shortname": '岳塘区',
		"parentid": '430300'
	},
	{
		"id": '430321',
		"shortname": '湘潭县',
		"parentid": '430300'
	},
	{
		"id": '430340',
		"shortname": '高新区',
		"parentid": '430300'
	},
	{
		"id": '430381',
		"shortname": '湘乡市',
		"parentid": '430300'
	},
	{
		"id": '430382',
		"shortname": '韶山市',
		"parentid": '430300'
	},
	{
		"id": '430405',
		"shortname": '珠晖区',
		"parentid": '430400'
	},
	{
		"id": '430406',
		"shortname": '雁峰区',
		"parentid": '430400'
	},
	{
		"id": '430407',
		"shortname": '石鼓区',
		"parentid": '430400'
	},
	{
		"id": '430408',
		"shortname": '蒸湘区',
		"parentid": '430400'
	},
	{
		"id": '430412',
		"shortname": '南岳区',
		"parentid": '430400'
	},
	{
		"id": '430421',
		"shortname": '衡阳县',
		"parentid": '430400'
	},
	{
		"id": '430422',
		"shortname": '衡南县',
		"parentid": '430400'
	},
	{
		"id": '430423',
		"shortname": '衡山县',
		"parentid": '430400'
	},
	{
		"id": '430424',
		"shortname": '衡东县',
		"parentid": '430400'
	},
	{
		"id": '430426',
		"shortname": '祁东县',
		"parentid": '430400'
	},
	{
		"id": '430481',
		"shortname": '耒阳市',
		"parentid": '430400'
	},
	{
		"id": '430482',
		"shortname": '常宁市',
		"parentid": '430400'
	},
	{
		"id": '430502',
		"shortname": '双清区',
		"parentid": '430500'
	},
	{
		"id": '430503',
		"shortname": '大祥区',
		"parentid": '430500'
	},
	{
		"id": '430511',
		"shortname": '北塔区',
		"parentid": '430500'
	},
	{
		"id": '430521',
		"shortname": '邵东县',
		"parentid": '430500'
	},
	{
		"id": '430522',
		"shortname": '新邵县',
		"parentid": '430500'
	},
	{
		"id": '430523',
		"shortname": '邵阳县',
		"parentid": '430500'
	},
	{
		"id": '430524',
		"shortname": '隆回县',
		"parentid": '430500'
	},
	{
		"id": '430525',
		"shortname": '洞口县',
		"parentid": '430500'
	},
	{
		"id": '430527',
		"shortname": '绥宁县',
		"parentid": '430500'
	},
	{
		"id": '430528',
		"shortname": '新宁县',
		"parentid": '430500'
	},
	{
		"id": '430529',
		"shortname": '城步苗族自治县',
		"parentid": '430500'
	},
	{
		"id": '430581',
		"shortname": '武冈市',
		"parentid": '430500'
	},
	{
		"id": '430602',
		"shortname": '岳阳楼区',
		"parentid": '430600'
	},
	{
		"id": '430603',
		"shortname": '云溪区',
		"parentid": '430600'
	},
	{
		"id": '430611',
		"shortname": '君山区',
		"parentid": '430600'
	},
	{
		"id": '430621',
		"shortname": '岳阳县',
		"parentid": '430600'
	},
	{
		"id": '430623',
		"shortname": '华容县',
		"parentid": '430600'
	},
	{
		"id": '430624',
		"shortname": '湘阴县',
		"parentid": '430600'
	},
	{
		"id": '430626',
		"shortname": '平江县',
		"parentid": '430600'
	},
	{
		"id": '430640',
		"shortname": '开发区',
		"parentid": '430600'
	},
	{
		"id": '430641',
		"shortname": '屈原区',
		"parentid": '430600'
	},
	{
		"id": '430681',
		"shortname": '汨罗市',
		"parentid": '430600'
	},
	{
		"id": '430682',
		"shortname": '临湘市',
		"parentid": '430600'
	},
	{
		"id": '430702',
		"shortname": '武陵区',
		"parentid": '430700'
	},
	{
		"id": '430721',
		"shortname": '安乡县',
		"parentid": '430700'
	},
	{
		"id": '430722',
		"shortname": '汉寿县',
		"parentid": '430700'
	},
	{
		"id": '430723',
		"shortname": '澧县',
		"parentid": '430700'
	},
	{
		"id": '430724',
		"shortname": '临澧县',
		"parentid": '430700'
	},
	{
		"id": '430725',
		"shortname": '桃源县',
		"parentid": '430700'
	},
	{
		"id": '430726',
		"shortname": '石门县',
		"parentid": '430700'
	},
	{
		"id": '430741',
		"shortname": '西湖区',
		"parentid": '430700'
	},
	{
		"id": '430781',
		"shortname": '津市市',
		"parentid": '430700'
	},
	{
		"id": '430703',
		"shortname": '鼎城区',
		"parentid": '430700'
	},
	{
		"id": '430740',
		"shortname": '洞庭湖区',
		"parentid": '430700'
	},
	{
		"id": '430802',
		"shortname": '永定区',
		"parentid": '430800'
	},
	{
		"id": '430811',
		"shortname": '武陵源区',
		"parentid": '430800'
	},
	{
		"id": '430821',
		"shortname": '慈利县',
		"parentid": '430800'
	},
	{
		"id": '430822',
		"shortname": '桑植县',
		"parentid": '430800'
	},
	{
		"id": '430903',
		"shortname": '赫山区',
		"parentid": '430900'
	},
	{
		"id": '430921',
		"shortname": '南县',
		"parentid": '430900'
	},
	{
		"id": '430922',
		"shortname": '桃江县',
		"parentid": '430900'
	},
	{
		"id": '430923',
		"shortname": '安化县',
		"parentid": '430900'
	},
	{
		"id": '430940',
		"shortname": '大通湖区',
		"parentid": '430900'
	},
	{
		"id": '430981',
		"shortname": '沅江市',
		"parentid": '430900'
	},
	{
		"id": '430902',
		"shortname": '资阳区',
		"parentid": '430900'
	},
	{
		"id": '431024',
		"shortname": '嘉禾县',
		"parentid": '431000'
	},
	{
		"id": '431025',
		"shortname": '临武县',
		"parentid": '431000'
	},
	{
		"id": '431026',
		"shortname": '汝城县',
		"parentid": '431000'
	},
	{
		"id": '431028',
		"shortname": '安仁县',
		"parentid": '431000'
	},
	{
		"id": '431081',
		"shortname": '资兴市',
		"parentid": '431000'
	},
	{
		"id": '431002',
		"shortname": '北湖区',
		"parentid": '431000'
	},
	{
		"id": '431003',
		"shortname": '苏仙区',
		"parentid": '431000'
	},
	{
		"id": '431021',
		"shortname": '桂阳县',
		"parentid": '431000'
	},
	{
		"id": '431022',
		"shortname": '宜章县',
		"parentid": '431000'
	},
	{
		"id": '431023',
		"shortname": '永兴县',
		"parentid": '431000'
	},
	{
		"id": '431027',
		"shortname": '桂东县',
		"parentid": '431000'
	},
	{
		"id": '431125',
		"shortname": '江永县',
		"parentid": '431100'
	},
	{
		"id": '431126',
		"shortname": '宁远县',
		"parentid": '431100'
	},
	{
		"id": '431127',
		"shortname": '蓝山县',
		"parentid": '431100'
	},
	{
		"id": '431128',
		"shortname": '新田县',
		"parentid": '431100'
	},
	{
		"id": '431129',
		"shortname": '江华瑶族自治县',
		"parentid": '431100'
	},
	{
		"id": '431140',
		"shortname": '回龙圩区',
		"parentid": '431100'
	},
	{
		"id": '431102',
		"shortname": '零陵区',
		"parentid": '431100'
	},
	{
		"id": '431103',
		"shortname": '冷水滩区',
		"parentid": '431100'
	},
	{
		"id": '431121',
		"shortname": '祁阳县',
		"parentid": '431100'
	},
	{
		"id": '431122',
		"shortname": '东安县',
		"parentid": '431100'
	},
	{
		"id": '431124',
		"shortname": '道县',
		"parentid": '431100'
	},
	{
		"id": '431123',
		"shortname": '双牌县',
		"parentid": '431100'
	},
	{
		"id": '431202',
		"shortname": '鹤城区',
		"parentid": '431200'
	},
	{
		"id": '431221',
		"shortname": '中方县',
		"parentid": '431200'
	},
	{
		"id": '431222',
		"shortname": '沅陵县',
		"parentid": '431200'
	},
	{
		"id": '431223',
		"shortname": '辰溪县',
		"parentid": '431200'
	},
	{
		"id": '431224',
		"shortname": '溆浦县',
		"parentid": '431200'
	},
	{
		"id": '431225',
		"shortname": '会同县',
		"parentid": '431200'
	},
	{
		"id": '431226',
		"shortname": '麻阳苗族自治县',
		"parentid": '431200'
	},
	{
		"id": '431227',
		"shortname": '新晃侗族自治县',
		"parentid": '431200'
	},
	{
		"id": '431228',
		"shortname": '芷江侗族自治县',
		"parentid": '431200'
	},
	{
		"id": '431229',
		"shortname": '靖州苗族侗族自治县',
		"parentid": '431200'
	},
	{
		"id": '431230',
		"shortname": '通道侗族自治县',
		"parentid": '431200'
	},
	{
		"id": '431240',
		"shortname": '洪江区',
		"parentid": '431200'
	},
	{
		"id": '431281',
		"shortname": '洪江市',
		"parentid": '431200'
	},
	{
		"id": '431302',
		"shortname": '娄星区',
		"parentid": '431300'
	},
	{
		"id": '431321',
		"shortname": '双峰县',
		"parentid": '431300'
	},
	{
		"id": '431322',
		"shortname": '新化县',
		"parentid": '431300'
	},
	{
		"id": '431381',
		"shortname": '冷水江市',
		"parentid": '431300'
	},
	{
		"id": '431382',
		"shortname": '涟源市',
		"parentid": '431300'
	},
	{
		"id": '433101',
		"shortname": '吉首市',
		"parentid": '433100'
	},
	{
		"id": '433122',
		"shortname": '泸溪县',
		"parentid": '433100'
	},
	{
		"id": '433123',
		"shortname": '凤凰县',
		"parentid": '433100'
	},
	{
		"id": '433124',
		"shortname": '花垣县',
		"parentid": '433100'
	},
	{
		"id": '433125',
		"shortname": '保靖县',
		"parentid": '433100'
	},
	{
		"id": '433126',
		"shortname": '古丈县',
		"parentid": '433100'
	},
	{
		"id": '433127',
		"shortname": '永顺县',
		"parentid": '433100'
	},
	{
		"id": '433130',
		"shortname": '龙山县',
		"parentid": '433100'
	},
	{
		"id": '440103',
		"shortname": '荔湾区',
		"parentid": '440100'
	},
	{
		"id": '440104',
		"shortname": '越秀区',
		"parentid": '440100'
	},
	{
		"id": '440105',
		"shortname": '海珠区',
		"parentid": '440100'
	},
	{
		"id": '440106',
		"shortname": '天河区',
		"parentid": '440100'
	},
	{
		"id": '440111',
		"shortname": '白云区',
		"parentid": '440100'
	},
	{
		"id": '440112',
		"shortname": '黄埔区',
		"parentid": '440100'
	},
	{
		"id": '440113',
		"shortname": '番禺区',
		"parentid": '440100'
	},
	{
		"id": '440114',
		"shortname": '花都区',
		"parentid": '440100'
	},
	{
		"id": '440115',
		"shortname": '南沙区',
		"parentid": '440100'
	},
	{
		"id": '440116',
		"shortname": '萝岗区',
		"parentid": '440100'
	},
	{
		"id": '440183',
		"shortname": '增城市',
		"parentid": '440100'
	},
	{
		"id": '440184',
		"shortname": '从化市',
		"parentid": '440100'
	},
	{
		"id": '440203',
		"shortname": '武江区',
		"parentid": '440200'
	},
	{
		"id": '440204',
		"shortname": '浈江区',
		"parentid": '440200'
	},
	{
		"id": '440222',
		"shortname": '始兴县',
		"parentid": '440200'
	},
	{
		"id": '440224',
		"shortname": '仁化县',
		"parentid": '440200'
	},
	{
		"id": '440229',
		"shortname": '翁源县',
		"parentid": '440200'
	},
	{
		"id": '440232',
		"shortname": '乳源瑶族自治县',
		"parentid": '440200'
	},
	{
		"id": '440233',
		"shortname": '新丰县',
		"parentid": '440200'
	},
	{
		"id": '440281',
		"shortname": '乐昌市',
		"parentid": '440200'
	},
	{
		"id": '440205',
		"shortname": '曲江区',
		"parentid": '440200'
	},
	{
		"id": '440282',
		"shortname": '南雄市',
		"parentid": '440200'
	},
	{
		"id": '440304',
		"shortname": '福田区',
		"parentid": '440300'
	},
	{
		"id": '440507',
		"shortname": '龙湖区',
		"parentid": '440500'
	},
	{
		"id": '440511',
		"shortname": '金平区',
		"parentid": '440500'
	},
	{
		"id": '440512',
		"shortname": '濠江区',
		"parentid": '440500'
	},
	{
		"id": '440513',
		"shortname": '潮阳区',
		"parentid": '440500'
	},
	{
		"id": '440514',
		"shortname": '潮南区',
		"parentid": '440500'
	},
	{
		"id": '440515',
		"shortname": '澄海区',
		"parentid": '440500'
	},
	{
		"id": '440523',
		"shortname": '南澳县',
		"parentid": '440500'
	},
	{
		"id": '440604',
		"shortname": '禅城区',
		"parentid": '440600'
	},
	{
		"id": '440605',
		"shortname": '南海区',
		"parentid": '440600'
	},
	{
		"id": '440606',
		"shortname": '顺德区',
		"parentid": '440600'
	},
	{
		"id": '440607',
		"shortname": '三水区',
		"parentid": '440600'
	},
	{
		"id": '440608',
		"shortname": '高明区',
		"parentid": '440600'
	},
	{
		"id": '440703',
		"shortname": '蓬江区',
		"parentid": '440700'
	},
	{
		"id": '440704',
		"shortname": '江海区',
		"parentid": '440700'
	},
	{
		"id": '440781',
		"shortname": '台山市',
		"parentid": '440700'
	},
	{
		"id": '440783',
		"shortname": '开平市',
		"parentid": '440700'
	},
	{
		"id": '440784',
		"shortname": '鹤山市',
		"parentid": '440700'
	},
	{
		"id": '440785',
		"shortname": '恩平市',
		"parentid": '440700'
	},
	{
		"id": '440705',
		"shortname": '新会区',
		"parentid": '440700'
	},
	{
		"id": '440802',
		"shortname": '赤坎区',
		"parentid": '440800'
	},
	{
		"id": '440803',
		"shortname": '霞山区',
		"parentid": '440800'
	},
	{
		"id": '440804',
		"shortname": '坡头区',
		"parentid": '440800'
	},
	{
		"id": '440811',
		"shortname": '麻章区',
		"parentid": '440800'
	},
	{
		"id": '440823',
		"shortname": '遂溪县',
		"parentid": '440800'
	},
	{
		"id": '440825',
		"shortname": '徐闻县',
		"parentid": '440800'
	},
	{
		"id": '440881',
		"shortname": '廉江市',
		"parentid": '440800'
	},
	{
		"id": '440882',
		"shortname": '雷州市',
		"parentid": '440800'
	},
	{
		"id": '440883',
		"shortname": '吴川市',
		"parentid": '440800'
	},
	{
		"id": '440902',
		"shortname": '茂南区',
		"parentid": '440900'
	},
	{
		"id": '440903',
		"shortname": '茂港区',
		"parentid": '440900'
	},
	{
		"id": '440923',
		"shortname": '电白县',
		"parentid": '440900'
	},
	{
		"id": '440981',
		"shortname": '高州市',
		"parentid": '440900'
	},
	{
		"id": '440982',
		"shortname": '化州市',
		"parentid": '440900'
	},
	{
		"id": '440983',
		"shortname": '信宜市',
		"parentid": '440900'
	},
	{
		"id": '441202',
		"shortname": '端州区',
		"parentid": '441200'
	},
	{
		"id": '441203',
		"shortname": '鼎湖区',
		"parentid": '441200'
	},
	{
		"id": '441223',
		"shortname": '广宁县',
		"parentid": '441200'
	},
	{
		"id": '441224',
		"shortname": '怀集县',
		"parentid": '441200'
	},
	{
		"id": '441225',
		"shortname": '封开县',
		"parentid": '441200'
	},
	{
		"id": '441226',
		"shortname": '德庆县',
		"parentid": '441200'
	},
	{
		"id": '441240',
		"shortname": '高新区',
		"parentid": '441200'
	},
	{
		"id": '441283',
		"shortname": '高要市',
		"parentid": '441200'
	},
	{
		"id": '441284',
		"shortname": '四会市',
		"parentid": '441200'
	},
	{
		"id": '441302',
		"shortname": '惠城区',
		"parentid": '441300'
	},
	{
		"id": '441303',
		"shortname": '惠阳区',
		"parentid": '441300'
	},
	{
		"id": '441322',
		"shortname": '博罗县',
		"parentid": '441300'
	},
	{
		"id": '441323',
		"shortname": '惠东县',
		"parentid": '441300'
	},
	{
		"id": '441324',
		"shortname": '龙门县',
		"parentid": '441300'
	},
	{
		"id": '441340',
		"shortname": '大亚湾经济开发区',
		"parentid": '441300'
	},
	{
		"id": '441402',
		"shortname": '梅江区',
		"parentid": '441400'
	},
	{
		"id": '441421',
		"shortname": '梅县',
		"parentid": '441400'
	},
	{
		"id": '441422',
		"shortname": '大埔县',
		"parentid": '441400'
	},
	{
		"id": '441423',
		"shortname": '丰顺县',
		"parentid": '441400'
	},
	{
		"id": '441424',
		"shortname": '五华县',
		"parentid": '441400'
	},
	{
		"id": '441426',
		"shortname": '平远县',
		"parentid": '441400'
	},
	{
		"id": '441427',
		"shortname": '蕉岭县',
		"parentid": '441400'
	},
	{
		"id": '441481',
		"shortname": '兴宁市',
		"parentid": '441400'
	},
	{
		"id": '441502',
		"shortname": '城区',
		"parentid": '441500'
	},
	{
		"id": '441521',
		"shortname": '海丰县',
		"parentid": '441500'
	},
	{
		"id": '441523',
		"shortname": '陆河县',
		"parentid": '441500'
	},
	{
		"id": '441540',
		"shortname": '红海湾',
		"parentid": '441500'
	},
	{
		"id": '441541',
		"shortname": '农垦',
		"parentid": '441500'
	},
	{
		"id": '441542',
		"shortname": '华侨管理区',
		"parentid": '441500'
	},
	{
		"id": '441580',
		"shortname": '陆丰市',
		"parentid": '441500'
	},
	{
		"id": '441602',
		"shortname": '源城区',
		"parentid": '441600'
	},
	{
		"id": '441621',
		"shortname": '紫金县',
		"parentid": '441600'
	},
	{
		"id": '441622',
		"shortname": '龙川县',
		"parentid": '441600'
	},
	{
		"id": '441623',
		"shortname": '连平县',
		"parentid": '441600'
	},
	{
		"id": '441624',
		"shortname": '和平县',
		"parentid": '441600'
	},
	{
		"id": '441625',
		"shortname": '东源县',
		"parentid": '441600'
	},
	{
		"id": '441702',
		"shortname": '江城区',
		"parentid": '441700'
	},
	{
		"id": '441721',
		"shortname": '阳西县',
		"parentid": '441700'
	},
	{
		"id": '441723',
		"shortname": '阳东县',
		"parentid": '441700'
	},
	{
		"id": '441740',
		"shortname": '岗侨区',
		"parentid": '441700'
	},
	{
		"id": '441741',
		"shortname": '农垦',
		"parentid": '441700'
	},
	{
		"id": '441742',
		"shortname": '海陵区',
		"parentid": '441700'
	},
	{
		"id": '441780',
		"shortname": '阳春市',
		"parentid": '441700'
	},
	{
		"id": '441802',
		"shortname": '清城区',
		"parentid": '441800'
	},
	{
		"id": '441821',
		"shortname": '佛冈县',
		"parentid": '441800'
	},
	{
		"id": '441825',
		"shortname": '连山壮族瑶族自治县',
		"parentid": '441800'
	},
	{
		"id": '441826',
		"shortname": '连南瑶族自治县',
		"parentid": '441800'
	},
	{
		"id": '441827',
		"shortname": '清新县',
		"parentid": '441800'
	},
	{
		"id": '441881',
		"shortname": '英德市',
		"parentid": '441800'
	},
	{
		"id": '441882',
		"shortname": '连州市',
		"parentid": '441800'
	},
	{
		"id": '441823',
		"shortname": '阳山县',
		"parentid": '441800'
	},
	{
		"id": '445102',
		"shortname": '湘桥区',
		"parentid": '445100'
	},
	{
		"id": '445122',
		"shortname": '饶平县',
		"parentid": '445100'
	},
	{
		"id": '445121',
		"shortname": '潮安县',
		"parentid": '445100'
	},
	{
		"id": '445202',
		"shortname": '榕城区',
		"parentid": '445200'
	},
	{
		"id": '445221',
		"shortname": '揭东县',
		"parentid": '445200'
	},
	{
		"id": '445222',
		"shortname": '揭西县',
		"parentid": '445200'
	},
	{
		"id": '445224',
		"shortname": '惠来县',
		"parentid": '445200'
	},
	{
		"id": '445240',
		"shortname": '东山区',
		"parentid": '445200'
	},
	{
		"id": '445241',
		"shortname": '实验区',
		"parentid": '445200'
	},
	{
		"id": '445242',
		"shortname": '大南山区',
		"parentid": '445200'
	},
	{
		"id": '445243',
		"shortname": '普侨区',
		"parentid": '445200'
	},
	{
		"id": '445281',
		"shortname": '普宁市',
		"parentid": '445200'
	},
	{
		"id": '445302',
		"shortname": '云城区',
		"parentid": '445300'
	},
	{
		"id": '445321',
		"shortname": '新兴县',
		"parentid": '445300'
	},
	{
		"id": '445322',
		"shortname": '郁南县',
		"parentid": '445300'
	},
	{
		"id": '445323',
		"shortname": '云安县',
		"parentid": '445300'
	},
	{
		"id": '445381',
		"shortname": '罗定市',
		"parentid": '445300'
	},
	{
		"id": '450102',
		"shortname": '兴宁区',
		"parentid": '450100'
	},
	{
		"id": '450103',
		"shortname": '青秀区',
		"parentid": '450100'
	},
	{
		"id": '450105',
		"shortname": '江南区',
		"parentid": '450100'
	},
	{
		"id": '450107',
		"shortname": '西乡塘区',
		"parentid": '450100'
	},
	{
		"id": '450108',
		"shortname": '良庆区',
		"parentid": '450100'
	},
	{
		"id": '450109',
		"shortname": '邕宁区',
		"parentid": '450100'
	},
	{
		"id": '450122',
		"shortname": '武鸣县',
		"parentid": '450100'
	},
	{
		"id": '450123',
		"shortname": '隆安县',
		"parentid": '450100'
	},
	{
		"id": '450124',
		"shortname": '马山县',
		"parentid": '450100'
	},
	{
		"id": '450125',
		"shortname": '上林县',
		"parentid": '450100'
	},
	{
		"id": '450126',
		"shortname": '宾阳县',
		"parentid": '450100'
	},
	{
		"id": '450127',
		"shortname": '横县',
		"parentid": '450100'
	},
	{
		"id": '450202',
		"shortname": '城中区',
		"parentid": '450200'
	},
	{
		"id": '450203',
		"shortname": '鱼峰区',
		"parentid": '450200'
	},
	{
		"id": '450204',
		"shortname": '柳南区',
		"parentid": '450200'
	},
	{
		"id": '450205',
		"shortname": '柳北区',
		"parentid": '450200'
	},
	{
		"id": '450221',
		"shortname": '柳江县',
		"parentid": '450200'
	},
	{
		"id": '450222',
		"shortname": '柳城县',
		"parentid": '450200'
	},
	{
		"id": '450223',
		"shortname": '鹿寨县',
		"parentid": '450200'
	},
	{
		"id": '450224',
		"shortname": '融安县',
		"parentid": '450200'
	},
	{
		"id": '450225',
		"shortname": '融水苗族自治县',
		"parentid": '450200'
	},
	{
		"id": '450226',
		"shortname": '三江侗族自治县',
		"parentid": '450200'
	},
	{
		"id": '450331',
		"shortname": '荔浦县',
		"parentid": '450300'
	},
	{
		"id": '450302',
		"shortname": '秀峰区',
		"parentid": '450300'
	},
	{
		"id": '450303',
		"shortname": '叠彩区',
		"parentid": '450300'
	},
	{
		"id": '450304',
		"shortname": '象山区',
		"parentid": '450300'
	},
	{
		"id": '450305',
		"shortname": '七星区',
		"parentid": '450300'
	},
	{
		"id": '450311',
		"shortname": '雁山区',
		"parentid": '450300'
	},
	{
		"id": '450321',
		"shortname": '阳朔县',
		"parentid": '450300'
	},
	{
		"id": '450322',
		"shortname": '临桂县',
		"parentid": '450300'
	},
	{
		"id": '450323',
		"shortname": '灵川县',
		"parentid": '450300'
	},
	{
		"id": '450324',
		"shortname": '全州县',
		"parentid": '450300'
	},
	{
		"id": '450325',
		"shortname": '兴安县',
		"parentid": '450300'
	},
	{
		"id": '450326',
		"shortname": '永福县',
		"parentid": '450300'
	},
	{
		"id": '450327',
		"shortname": '灌阳县',
		"parentid": '450300'
	},
	{
		"id": '450328',
		"shortname": '龙胜各族自治县',
		"parentid": '450300'
	},
	{
		"id": '450329',
		"shortname": '资源县',
		"parentid": '450300'
	},
	{
		"id": '450330',
		"shortname": '平乐县',
		"parentid": '450300'
	},
	{
		"id": '450332',
		"shortname": '恭城瑶族自治县',
		"parentid": '450300'
	},
	{
		"id": '450481',
		"shortname": '岑溪市',
		"parentid": '450400'
	},
	{
		"id": '450403',
		"shortname": '万秀区',
		"parentid": '450400'
	},
	{
		"id": '450404',
		"shortname": '蝶山区',
		"parentid": '450400'
	},
	{
		"id": '450405',
		"shortname": '长洲区',
		"parentid": '450400'
	},
	{
		"id": '450421',
		"shortname": '苍梧县',
		"parentid": '450400'
	},
	{
		"id": '450422',
		"shortname": '藤县',
		"parentid": '450400'
	},
	{
		"id": '450423',
		"shortname": '蒙山县',
		"parentid": '450400'
	},
	{
		"id": '450502',
		"shortname": '海城区',
		"parentid": '450500'
	},
	{
		"id": '450503',
		"shortname": '银海区',
		"parentid": '450500'
	},
	{
		"id": '450512',
		"shortname": '铁山港区',
		"parentid": '450500'
	},
	{
		"id": '450521',
		"shortname": '合浦县',
		"parentid": '450500'
	},
	{
		"id": '450602',
		"shortname": '港口区',
		"parentid": '450600'
	},
	{
		"id": '450603',
		"shortname": '防城区',
		"parentid": '450600'
	},
	{
		"id": '450621',
		"shortname": '上思县',
		"parentid": '450600'
	},
	{
		"id": '450681',
		"shortname": '东兴市',
		"parentid": '450600'
	},
	{
		"id": '450702',
		"shortname": '钦南区',
		"parentid": '450700'
	},
	{
		"id": '450703',
		"shortname": '钦北区',
		"parentid": '450700'
	},
	{
		"id": '450721',
		"shortname": '灵山县',
		"parentid": '450700'
	},
	{
		"id": '450722',
		"shortname": '浦北县',
		"parentid": '450700'
	},
	{
		"id": '450802',
		"shortname": '港北区',
		"parentid": '450800'
	},
	{
		"id": '450803',
		"shortname": '港南区',
		"parentid": '450800'
	},
	{
		"id": '450804',
		"shortname": '覃塘区',
		"parentid": '450800'
	},
	{
		"id": '450821',
		"shortname": '平南县',
		"parentid": '450800'
	},
	{
		"id": '450881',
		"shortname": '桂平市',
		"parentid": '450800'
	},
	{
		"id": '450921',
		"shortname": '容县',
		"parentid": '450900'
	},
	{
		"id": '450922',
		"shortname": '陆川县',
		"parentid": '450900'
	},
	{
		"id": '450923',
		"shortname": '博白县',
		"parentid": '450900'
	},
	{
		"id": '450924',
		"shortname": '兴业县',
		"parentid": '450900'
	},
	{
		"id": '450981',
		"shortname": '北流市',
		"parentid": '450900'
	},
	{
		"id": '450902',
		"shortname": '玉州区',
		"parentid": '450900'
	},
	{
		"id": '451021',
		"shortname": '田阳县',
		"parentid": '451000'
	},
	{
		"id": '451022',
		"shortname": '田东县',
		"parentid": '451000'
	},
	{
		"id": '451023',
		"shortname": '平果县',
		"parentid": '451000'
	},
	{
		"id": '451024',
		"shortname": '德保县',
		"parentid": '451000'
	},
	{
		"id": '451025',
		"shortname": '靖西县',
		"parentid": '451000'
	},
	{
		"id": '451026',
		"shortname": '那坡县',
		"parentid": '451000'
	},
	{
		"id": '451027',
		"shortname": '凌云县',
		"parentid": '451000'
	},
	{
		"id": '451029',
		"shortname": '田林县',
		"parentid": '451000'
	},
	{
		"id": '451030',
		"shortname": '西林县',
		"parentid": '451000'
	},
	{
		"id": '451031',
		"shortname": '隆林各族自治县',
		"parentid": '451000'
	},
	{
		"id": '451002',
		"shortname": '右江区',
		"parentid": '451000'
	},
	{
		"id": '451028',
		"shortname": '乐业县',
		"parentid": '451000'
	},
	{
		"id": '451102',
		"shortname": '八步区',
		"parentid": '451100'
	},
	{
		"id": '451121',
		"shortname": '昭平县',
		"parentid": '451100'
	},
	{
		"id": '451122',
		"shortname": '钟山县',
		"parentid": '451100'
	},
	{
		"id": '451123',
		"shortname": '富川瑶族自治县',
		"parentid": '451100'
	},
	{
		"id": '451221',
		"shortname": '南丹县',
		"parentid": '451200'
	},
	{
		"id": '451222',
		"shortname": '天峨县',
		"parentid": '451200'
	},
	{
		"id": '451223',
		"shortname": '凤山县',
		"parentid": '451200'
	},
	{
		"id": '451224',
		"shortname": '东兰县',
		"parentid": '451200'
	},
	{
		"id": '451225',
		"shortname": '罗城仫佬族自治县',
		"parentid": '451200'
	},
	{
		"id": '451226',
		"shortname": '环江毛南族自治县',
		"parentid": '451200'
	},
	{
		"id": '451227',
		"shortname": '巴马瑶族自治县',
		"parentid": '451200'
	},
	{
		"id": '451228',
		"shortname": '都安瑶族自治县',
		"parentid": '451200'
	},
	{
		"id": '451229',
		"shortname": '大化瑶族自治县',
		"parentid": '451200'
	},
	{
		"id": '451281',
		"shortname": '宜州市',
		"parentid": '451200'
	},
	{
		"id": '451202',
		"shortname": '金城江区',
		"parentid": '451200'
	},
	{
		"id": '451302',
		"shortname": '兴宾区',
		"parentid": '451300'
	},
	{
		"id": '451321',
		"shortname": '忻城县',
		"parentid": '451300'
	},
	{
		"id": '451322',
		"shortname": '象州县',
		"parentid": '451300'
	},
	{
		"id": '451323',
		"shortname": '武宣县',
		"parentid": '451300'
	},
	{
		"id": '451324',
		"shortname": '金秀瑶族自治县',
		"parentid": '451300'
	},
	{
		"id": '451381',
		"shortname": '合山市',
		"parentid": '451300'
	},
	{
		"id": '451421',
		"shortname": '扶绥县',
		"parentid": '451400'
	},
	{
		"id": '451422',
		"shortname": '宁明县',
		"parentid": '451400'
	},
	{
		"id": '451423',
		"shortname": '龙州县',
		"parentid": '451400'
	},
	{
		"id": '451424',
		"shortname": '大新县',
		"parentid": '451400'
	},
	{
		"id": '451425',
		"shortname": '天等县',
		"parentid": '451400'
	},
	{
		"id": '451481',
		"shortname": '凭祥市',
		"parentid": '451400'
	},
	{
		"id": '451402',
		"shortname": '江州区',
		"parentid": '451400'
	},
	{
		"id": '460105',
		"shortname": '秀英区',
		"parentid": '460100'
	},
	{
		"id": '460106',
		"shortname": '龙华区',
		"parentid": '460100'
	},
	{
		"id": '460107',
		"shortname": '琼山区',
		"parentid": '460100'
	},
	{
		"id": '460108',
		"shortname": '美兰区',
		"parentid": '460100'
	},
	{
		"id": '469001',
		"shortname": '五指山市',
		"parentid": '469000'
	},
	{
		"id": '469002',
		"shortname": '琼海市',
		"parentid": '469000'
	},
	{
		"id": '469003',
		"shortname": '儋州市',
		"parentid": '469000'
	},
	{
		"id": '469005',
		"shortname": '文昌市',
		"parentid": '469000'
	},
	{
		"id": '469006',
		"shortname": '万宁市',
		"parentid": '469000'
	},
	{
		"id": '469007',
		"shortname": '东方市',
		"parentid": '469000'
	},
	{
		"id": '469021',
		"shortname": '定安县',
		"parentid": '469000'
	},
	{
		"id": '469022',
		"shortname": '屯昌县',
		"parentid": '469000'
	},
	{
		"id": '469023',
		"shortname": '澄迈县',
		"parentid": '469000'
	},
	{
		"id": '469024',
		"shortname": '临高县',
		"parentid": '469000'
	},
	{
		"id": '469025',
		"shortname": '白沙黎族自治县',
		"parentid": '469000'
	},
	{
		"id": '469026',
		"shortname": '昌江黎族自治县',
		"parentid": '469000'
	},
	{
		"id": '469027',
		"shortname": '乐东黎族自治县',
		"parentid": '469000'
	},
	{
		"id": '469028',
		"shortname": '陵水黎族自治县',
		"parentid": '469000'
	},
	{
		"id": '469029',
		"shortname": '保亭黎族苗族自治县',
		"parentid": '469000'
	},
	{
		"id": '469030',
		"shortname": '琼中黎族苗族自治县',
		"parentid": '469000'
	},
	{
		"id": '500101',
		"shortname": '万州区',
		"parentid": '509900'
	},
	{
		"id": '500102',
		"shortname": '涪陵区',
		"parentid": '509900'
	},
	{
		"id": '500103',
		"shortname": '渝中区',
		"parentid": '509900'
	},
	{
		"id": '500104',
		"shortname": '大渡口区',
		"parentid": '509900'
	},
	{
		"id": '500105',
		"shortname": '江北区',
		"parentid": '509900'
	},
	{
		"id": '500106',
		"shortname": '沙坪坝区',
		"parentid": '509900'
	},
	{
		"id": '500107',
		"shortname": '九龙坡区',
		"parentid": '509900'
	},
	{
		"id": '500108',
		"shortname": '南岸区',
		"parentid": '509900'
	},
	{
		"id": '500109',
		"shortname": '北碚区',
		"parentid": '509900'
	},
	{
		"id": '500112',
		"shortname": '渝北区',
		"parentid": '509900'
	},
	{
		"id": '500113',
		"shortname": '巴南区',
		"parentid": '509900'
	},
	{
		"id": '500114',
		"shortname": '黔江区',
		"parentid": '509900'
	},
	{
		"id": '500115',
		"shortname": '长寿区',
		"parentid": '509900'
	},
	{
		"id": '500116',
		"shortname": '江津区',
		"parentid": '509900'
	},
	{
		"id": '500117',
		"shortname": '合川区',
		"parentid": '509900'
	},
	{
		"id": '500118',
		"shortname": '永川区',
		"parentid": '509900'
	},
	{
		"id": '500119',
		"shortname": '南川区',
		"parentid": '509900'
	},
	{
		"id": '500142',
		"shortname": '北部新区',
		"parentid": '509900'
	},
	{
		"id": '500222',
		"shortname": '綦江县',
		"parentid": '509900'
	},
	{
		"id": '500110',
		"shortname": '綦江区',
		"parentid": '509900'
	},
	{
		"id": '500111',
		"shortname": '大足区',
		"parentid": '509900'
	},
	{
		"id": '500223',
		"shortname": '潼南县',
		"parentid": '509900'
	},
	{
		"id": '500224',
		"shortname": '铜梁县',
		"parentid": '509900'
	},
	{
		"id": '500225',
		"shortname": '大足县',
		"parentid": '509900'
	},
	{
		"id": '500226',
		"shortname": '荣昌县',
		"parentid": '509900'
	},
	{
		"id": '500227',
		"shortname": '璧山县',
		"parentid": '509900'
	},
	{
		"id": '500228',
		"shortname": '梁平县',
		"parentid": '509900'
	},
	{
		"id": '500229',
		"shortname": '城口县',
		"parentid": '509900'
	},
	{
		"id": '500230',
		"shortname": '丰都县',
		"parentid": '509900'
	},
	{
		"id": '500231',
		"shortname": '垫江县',
		"parentid": '509900'
	},
	{
		"id": '500232',
		"shortname": '武隆县',
		"parentid": '509900'
	},
	{
		"id": '500233',
		"shortname": '忠县',
		"parentid": '509900'
	},
	{
		"id": '500234',
		"shortname": '开县',
		"parentid": '509900'
	},
	{
		"id": '500235',
		"shortname": '云阳县',
		"parentid": '509900'
	},
	{
		"id": '500236',
		"shortname": '奉节县',
		"parentid": '509900'
	},
	{
		"id": '500237',
		"shortname": '巫山县',
		"parentid": '509900'
	},
	{
		"id": '500238',
		"shortname": '巫溪县',
		"parentid": '509900'
	},
	{
		"id": '500240',
		"shortname": '石柱土家族自治县',
		"parentid": '509900'
	},
	{
		"id": '500241',
		"shortname": '秀山土家族苗族自治县',
		"parentid": '509900'
	},
	{
		"id": '500242',
		"shortname": '酉阳土家族苗族自治县',
		"parentid": '509900'
	},
	{
		"id": '500243',
		"shortname": '彭水苗族土家族自治县',
		"parentid": '509900'
	},
	{
		"id": '510104',
		"shortname": '锦江区',
		"parentid": '510100'
	},
	{
		"id": '510105',
		"shortname": '青羊区',
		"parentid": '510100'
	},
	{
		"id": '510106',
		"shortname": '金牛区',
		"parentid": '510100'
	},
	{
		"id": '510107',
		"shortname": '武侯区',
		"parentid": '510100'
	},
	{
		"id": '510108',
		"shortname": '成华区',
		"parentid": '510100'
	},
	{
		"id": '510112',
		"shortname": '龙泉驿区',
		"parentid": '510100'
	},
	{
		"id": '510113',
		"shortname": '青白江区',
		"parentid": '510100'
	},
	{
		"id": '510114',
		"shortname": '新都区',
		"parentid": '510100'
	},
	{
		"id": '510115',
		"shortname": '温江区',
		"parentid": '510100'
	},
	{
		"id": '510121',
		"shortname": '金堂县',
		"parentid": '510100'
	},
	{
		"id": '510122',
		"shortname": '双流县',
		"parentid": '510100'
	},
	{
		"id": '510124',
		"shortname": '郫县',
		"parentid": '510100'
	},
	{
		"id": '510129',
		"shortname": '大邑县',
		"parentid": '510100'
	},
	{
		"id": '510131',
		"shortname": '蒲江县',
		"parentid": '510100'
	},
	{
		"id": '510132',
		"shortname": '新津县',
		"parentid": '510100'
	},
	{
		"id": '510140',
		"shortname": '高新区',
		"parentid": '510100'
	},
	{
		"id": '510181',
		"shortname": '都江堰市',
		"parentid": '510100'
	},
	{
		"id": '510182',
		"shortname": '彭州市',
		"parentid": '510100'
	},
	{
		"id": '510183',
		"shortname": '邛崃市',
		"parentid": '510100'
	},
	{
		"id": '510184',
		"shortname": '崇州市',
		"parentid": '510100'
	},
	{
		"id": '510302',
		"shortname": '自流井区',
		"parentid": '510300'
	},
	{
		"id": '510303',
		"shortname": '贡井区',
		"parentid": '510300'
	},
	{
		"id": '510304',
		"shortname": '大安区',
		"parentid": '510300'
	},
	{
		"id": '510311',
		"shortname": '沿滩区',
		"parentid": '510300'
	},
	{
		"id": '510321',
		"shortname": '荣县',
		"parentid": '510300'
	},
	{
		"id": '510322',
		"shortname": '富顺县',
		"parentid": '510300'
	},
	{
		"id": '510402',
		"shortname": '东区',
		"parentid": '510400'
	},
	{
		"id": '510403',
		"shortname": '西区',
		"parentid": '510400'
	},
	{
		"id": '510411',
		"shortname": '仁和区',
		"parentid": '510400'
	},
	{
		"id": '510421',
		"shortname": '米易县',
		"parentid": '510400'
	},
	{
		"id": '510422',
		"shortname": '盐边县',
		"parentid": '510400'
	},
	{
		"id": '510502',
		"shortname": '江阳区',
		"parentid": '510500'
	},
	{
		"id": '510503',
		"shortname": '纳溪区',
		"parentid": '510500'
	},
	{
		"id": '510504',
		"shortname": '龙马潭区',
		"parentid": '510500'
	},
	{
		"id": '510521',
		"shortname": '泸县',
		"parentid": '510500'
	},
	{
		"id": '510522',
		"shortname": '合江县',
		"parentid": '510500'
	},
	{
		"id": '510524',
		"shortname": '叙永县',
		"parentid": '510500'
	},
	{
		"id": '510525',
		"shortname": '古蔺县',
		"parentid": '510500'
	},
	{
		"id": '510603',
		"shortname": '旌阳区',
		"parentid": '510600'
	},
	{
		"id": '510623',
		"shortname": '中江县',
		"parentid": '510600'
	},
	{
		"id": '510626',
		"shortname": '罗江县',
		"parentid": '510600'
	},
	{
		"id": '510681',
		"shortname": '广汉市',
		"parentid": '510600'
	},
	{
		"id": '510683',
		"shortname": '绵竹市',
		"parentid": '510600'
	},
	{
		"id": '510682',
		"shortname": '什邡市',
		"parentid": '510600'
	},
	{
		"id": '510703',
		"shortname": '涪城区',
		"parentid": '510700'
	},
	{
		"id": '510704',
		"shortname": '游仙区',
		"parentid": '510700'
	},
	{
		"id": '510722',
		"shortname": '三台县',
		"parentid": '510700'
	},
	{
		"id": '510723',
		"shortname": '盐亭县',
		"parentid": '510700'
	},
	{
		"id": '510724',
		"shortname": '安县',
		"parentid": '510700'
	},
	{
		"id": '510726',
		"shortname": '北川羌族自治县',
		"parentid": '510700'
	},
	{
		"id": '510727',
		"shortname": '平武县',
		"parentid": '510700'
	},
	{
		"id": '510740',
		"shortname": '高新区',
		"parentid": '510700'
	},
	{
		"id": '510781',
		"shortname": '江油市',
		"parentid": '510700'
	},
	{
		"id": '510725',
		"shortname": '梓潼县',
		"parentid": '510700'
	},
	{
		"id": '510811',
		"shortname": '昭化区',
		"parentid": '510800'
	},
	{
		"id": '510802',
		"shortname": '利州区',
		"parentid": '510800'
	},
	{
		"id": '510812',
		"shortname": '朝天区',
		"parentid": '510800'
	},
	{
		"id": '510822',
		"shortname": '青川县',
		"parentid": '510800'
	},
	{
		"id": '510823',
		"shortname": '剑阁县',
		"parentid": '510800'
	},
	{
		"id": '510824',
		"shortname": '苍溪县',
		"parentid": '510800'
	},
	{
		"id": '510821',
		"shortname": '旺苍县',
		"parentid": '510800'
	},
	{
		"id": '510903',
		"shortname": '船山区',
		"parentid": '510900'
	},
	{
		"id": '510904',
		"shortname": '安居区',
		"parentid": '510900'
	},
	{
		"id": '510921',
		"shortname": '蓬溪县',
		"parentid": '510900'
	},
	{
		"id": '510922',
		"shortname": '射洪县',
		"parentid": '510900'
	},
	{
		"id": '510923',
		"shortname": '大英县',
		"parentid": '510900'
	},
	{
		"id": '511002',
		"shortname": '市中区',
		"parentid": '511000'
	},
	{
		"id": '511011',
		"shortname": '东兴区',
		"parentid": '511000'
	},
	{
		"id": '511024',
		"shortname": '威远县',
		"parentid": '511000'
	},
	{
		"id": '511025',
		"shortname": '资中县',
		"parentid": '511000'
	},
	{
		"id": '511028',
		"shortname": '隆昌县',
		"parentid": '511000'
	},
	{
		"id": '511102',
		"shortname": '市中区',
		"parentid": '511100'
	},
	{
		"id": '511111',
		"shortname": '沙湾区',
		"parentid": '511100'
	},
	{
		"id": '511112',
		"shortname": '五通桥区',
		"parentid": '511100'
	},
	{
		"id": '511113',
		"shortname": '金口河区',
		"parentid": '511100'
	},
	{
		"id": '511123',
		"shortname": '犍为县',
		"parentid": '511100'
	},
	{
		"id": '511124',
		"shortname": '井研县',
		"parentid": '511100'
	},
	{
		"id": '511126',
		"shortname": '夹江县',
		"parentid": '511100'
	},
	{
		"id": '511132',
		"shortname": '峨边彝族自治县',
		"parentid": '511100'
	},
	{
		"id": '511133',
		"shortname": '马边彝族自治县',
		"parentid": '511100'
	},
	{
		"id": '511181',
		"shortname": '峨眉山市',
		"parentid": '511100'
	},
	{
		"id": '511129',
		"shortname": '沐川县',
		"parentid": '511100'
	},
	{
		"id": '511302',
		"shortname": '顺庆区',
		"parentid": '511300'
	},
	{
		"id": '511303',
		"shortname": '高坪区',
		"parentid": '511300'
	},
	{
		"id": '511304',
		"shortname": '嘉陵区',
		"parentid": '511300'
	},
	{
		"id": '511322',
		"shortname": '营山县',
		"parentid": '511300'
	},
	{
		"id": '511323',
		"shortname": '蓬安县',
		"parentid": '511300'
	},
	{
		"id": '511324',
		"shortname": '仪陇县',
		"parentid": '511300'
	},
	{
		"id": '511325',
		"shortname": '西充县',
		"parentid": '511300'
	},
	{
		"id": '511381',
		"shortname": '阆中市',
		"parentid": '511300'
	},
	{
		"id": '511321',
		"shortname": '南部县',
		"parentid": '511300'
	},
	{
		"id": '511402',
		"shortname": '东坡区',
		"parentid": '511400'
	},
	{
		"id": '511421',
		"shortname": '仁寿县',
		"parentid": '511400'
	},
	{
		"id": '511423',
		"shortname": '洪雅县',
		"parentid": '511400'
	},
	{
		"id": '511424',
		"shortname": '丹棱县',
		"parentid": '511400'
	},
	{
		"id": '511425',
		"shortname": '青神县',
		"parentid": '511400'
	},
	{
		"id": '511422',
		"shortname": '彭山县',
		"parentid": '511400'
	},
	{
		"id": '511502',
		"shortname": '翠屏区',
		"parentid": '511500'
	},
	{
		"id": '511521',
		"shortname": '宜宾县',
		"parentid": '511500'
	},
	{
		"id": '511522',
		"shortname": '南溪县',
		"parentid": '511500'
	},
	{
		"id": '511523',
		"shortname": '江安县',
		"parentid": '511500'
	},
	{
		"id": '511524',
		"shortname": '长宁县',
		"parentid": '511500'
	},
	{
		"id": '511525',
		"shortname": '高县',
		"parentid": '511500'
	},
	{
		"id": '511526',
		"shortname": '珙县',
		"parentid": '511500'
	},
	{
		"id": '511527',
		"shortname": '筠连县',
		"parentid": '511500'
	},
	{
		"id": '511528',
		"shortname": '兴文县',
		"parentid": '511500'
	},
	{
		"id": '511529',
		"shortname": '屏山县',
		"parentid": '511500'
	},
	{
		"id": '511540',
		"shortname": '临港开发区',
		"parentid": '511500'
	},
	{
		"id": '511602',
		"shortname": '广安区',
		"parentid": '511600'
	},
	{
		"id": '511621',
		"shortname": '岳池县',
		"parentid": '511600'
	},
	{
		"id": '511622',
		"shortname": '武胜县',
		"parentid": '511600'
	},
	{
		"id": '511623',
		"shortname": '邻水县',
		"parentid": '511600'
	},
	{
		"id": '511681',
		"shortname": '华蓥市',
		"parentid": '511600'
	},
	{
		"id": '511702',
		"shortname": '通川区',
		"parentid": '511700'
	},
	{
		"id": '511721',
		"shortname": '达县',
		"parentid": '511700'
	},
	{
		"id": '511722',
		"shortname": '宣汉县',
		"parentid": '511700'
	},
	{
		"id": '511723',
		"shortname": '开江县',
		"parentid": '511700'
	},
	{
		"id": '511724',
		"shortname": '大竹县',
		"parentid": '511700'
	},
	{
		"id": '511725',
		"shortname": '渠县',
		"parentid": '511700'
	},
	{
		"id": '511781',
		"shortname": '万源市',
		"parentid": '511700'
	},
	{
		"id": '511802',
		"shortname": '雨城区',
		"parentid": '511800'
	},
	{
		"id": '511821',
		"shortname": '名山县',
		"parentid": '511800'
	},
	{
		"id": '511822',
		"shortname": '荥经县',
		"parentid": '511800'
	},
	{
		"id": '511823',
		"shortname": '汉源县',
		"parentid": '511800'
	},
	{
		"id": '511824',
		"shortname": '石棉县',
		"parentid": '511800'
	},
	{
		"id": '511825',
		"shortname": '天全县',
		"parentid": '511800'
	},
	{
		"id": '511826',
		"shortname": '芦山县',
		"parentid": '511800'
	},
	{
		"id": '511827',
		"shortname": '宝兴县',
		"parentid": '511800'
	},
	{
		"id": '511902',
		"shortname": '巴州区',
		"parentid": '511900'
	},
	{
		"id": '511921',
		"shortname": '通江县',
		"parentid": '511900'
	},
	{
		"id": '511922',
		"shortname": '南江县',
		"parentid": '511900'
	},
	{
		"id": '511923',
		"shortname": '平昌县',
		"parentid": '511900'
	},
	{
		"id": '512002',
		"shortname": '雁江区',
		"parentid": '512000'
	},
	{
		"id": '512021',
		"shortname": '安岳县',
		"parentid": '512000'
	},
	{
		"id": '512022',
		"shortname": '乐至县',
		"parentid": '512000'
	},
	{
		"id": '512081',
		"shortname": '简阳市',
		"parentid": '512000'
	},
	{
		"id": '513221',
		"shortname": '汶川县',
		"parentid": '513200'
	},
	{
		"id": '513222',
		"shortname": '理县',
		"parentid": '513200'
	},
	{
		"id": '513223',
		"shortname": '茂县',
		"parentid": '513200'
	},
	{
		"id": '513224',
		"shortname": '松潘县',
		"parentid": '513200'
	},
	{
		"id": '513225',
		"shortname": '九寨沟县',
		"parentid": '513200'
	},
	{
		"id": '513226',
		"shortname": '金川县',
		"parentid": '513200'
	},
	{
		"id": '513227',
		"shortname": '小金县',
		"parentid": '513200'
	},
	{
		"id": '513228',
		"shortname": '黑水县',
		"parentid": '513200'
	},
	{
		"id": '513229',
		"shortname": '马尔康县',
		"parentid": '513200'
	},
	{
		"id": '513230',
		"shortname": '壤塘县',
		"parentid": '513200'
	},
	{
		"id": '513231',
		"shortname": '阿坝县',
		"parentid": '513200'
	},
	{
		"id": '513232',
		"shortname": '若尔盖县',
		"parentid": '513200'
	},
	{
		"id": '513233',
		"shortname": '红原县',
		"parentid": '513200'
	},
	{
		"id": '513240',
		"shortname": '卧龙特区',
		"parentid": '513200'
	},
	{
		"id": '513321',
		"shortname": '康定县',
		"parentid": '513300'
	},
	{
		"id": '513322',
		"shortname": '泸定县',
		"parentid": '513300'
	},
	{
		"id": '513323',
		"shortname": '丹巴县',
		"parentid": '513300'
	},
	{
		"id": '513324',
		"shortname": '九龙县',
		"parentid": '513300'
	},
	{
		"id": '513325',
		"shortname": '雅江县',
		"parentid": '513300'
	},
	{
		"id": '513326',
		"shortname": '道孚县',
		"parentid": '513300'
	},
	{
		"id": '513327',
		"shortname": '炉霍县',
		"parentid": '513300'
	},
	{
		"id": '513328',
		"shortname": '甘孜县',
		"parentid": '513300'
	},
	{
		"id": '513329',
		"shortname": '新龙县',
		"parentid": '513300'
	},
	{
		"id": '513330',
		"shortname": '德格县',
		"parentid": '513300'
	},
	{
		"id": '513331',
		"shortname": '白玉县',
		"parentid": '513300'
	},
	{
		"id": '513332',
		"shortname": '石渠县',
		"parentid": '513300'
	},
	{
		"id": '513333',
		"shortname": '色达县',
		"parentid": '513300'
	},
	{
		"id": '513334',
		"shortname": '理塘县',
		"parentid": '513300'
	},
	{
		"id": '513335',
		"shortname": '巴塘县',
		"parentid": '513300'
	},
	{
		"id": '513336',
		"shortname": '乡城县',
		"parentid": '513300'
	},
	{
		"id": '513337',
		"shortname": '稻城县',
		"parentid": '513300'
	},
	{
		"id": '513338',
		"shortname": '得荣县',
		"parentid": '513300'
	},
	{
		"id": '513437',
		"shortname": '雷波县',
		"parentid": '513400'
	},
	{
		"id": '513401',
		"shortname": '西昌市',
		"parentid": '513400'
	},
	{
		"id": '513422',
		"shortname": '木里藏族自治县',
		"parentid": '513400'
	},
	{
		"id": '513423',
		"shortname": '盐源县',
		"parentid": '513400'
	},
	{
		"id": '513424',
		"shortname": '德昌县',
		"parentid": '513400'
	},
	{
		"id": '513425',
		"shortname": '会理县',
		"parentid": '513400'
	},
	{
		"id": '513426',
		"shortname": '会东县',
		"parentid": '513400'
	},
	{
		"id": '513427',
		"shortname": '宁南县',
		"parentid": '513400'
	},
	{
		"id": '513428',
		"shortname": '普格县',
		"parentid": '513400'
	},
	{
		"id": '513429',
		"shortname": '布拖县',
		"parentid": '513400'
	},
	{
		"id": '513430',
		"shortname": '金阳县',
		"parentid": '513400'
	},
	{
		"id": '513431',
		"shortname": '昭觉县',
		"parentid": '513400'
	},
	{
		"id": '513432',
		"shortname": '喜德县',
		"parentid": '513400'
	},
	{
		"id": '513434',
		"shortname": '越西县',
		"parentid": '513400'
	},
	{
		"id": '513435',
		"shortname": '甘洛县',
		"parentid": '513400'
	},
	{
		"id": '513436',
		"shortname": '美姑县',
		"parentid": '513400'
	},
	{
		"id": '513433',
		"shortname": '冕宁县',
		"parentid": '513400'
	},
	{
		"id": '520102',
		"shortname": '南明区',
		"parentid": '520100'
	},
	{
		"id": '520103',
		"shortname": '云岩区',
		"parentid": '520100'
	},
	{
		"id": '520111',
		"shortname": '花溪区',
		"parentid": '520100'
	},
	{
		"id": '520112',
		"shortname": '乌当区',
		"parentid": '520100'
	},
	{
		"id": '520113',
		"shortname": '白云区',
		"parentid": '520100'
	},
	{
		"id": '520114',
		"shortname": '小河区',
		"parentid": '520100'
	},
	{
		"id": '520121',
		"shortname": '开阳县',
		"parentid": '520100'
	},
	{
		"id": '520122',
		"shortname": '息烽县',
		"parentid": '520100'
	},
	{
		"id": '520123',
		"shortname": '修文县',
		"parentid": '520100'
	},
	{
		"id": '520181',
		"shortname": '清镇市',
		"parentid": '520100'
	},
	{
		"id": '520201',
		"shortname": '钟山区',
		"parentid": '520200'
	},
	{
		"id": '520203',
		"shortname": '六枝特区',
		"parentid": '520200'
	},
	{
		"id": '520221',
		"shortname": '水城县',
		"parentid": '520200'
	},
	{
		"id": '520222',
		"shortname": '盘县',
		"parentid": '520200'
	},
	{
		"id": '520302',
		"shortname": '红花岗区',
		"parentid": '520300'
	},
	{
		"id": '520303',
		"shortname": '汇川区',
		"parentid": '520300'
	},
	{
		"id": '520321',
		"shortname": '遵义县',
		"parentid": '520300'
	},
	{
		"id": '520322',
		"shortname": '桐梓县',
		"parentid": '520300'
	},
	{
		"id": '520323',
		"shortname": '绥阳县',
		"parentid": '520300'
	},
	{
		"id": '520324',
		"shortname": '正安县',
		"parentid": '520300'
	},
	{
		"id": '520325',
		"shortname": '道真仡佬族苗族自治县',
		"parentid": '520300'
	},
	{
		"id": '520326',
		"shortname": '务川仡佬族苗族自治县',
		"parentid": '520300'
	},
	{
		"id": '520327',
		"shortname": '凤冈县',
		"parentid": '520300'
	},
	{
		"id": '520328',
		"shortname": '湄潭县',
		"parentid": '520300'
	},
	{
		"id": '520329',
		"shortname": '余庆县',
		"parentid": '520300'
	},
	{
		"id": '520330',
		"shortname": '习水县',
		"parentid": '520300'
	},
	{
		"id": '520381',
		"shortname": '赤水市',
		"parentid": '520300'
	},
	{
		"id": '520382',
		"shortname": '仁怀市',
		"parentid": '520300'
	},
	{
		"id": '520402',
		"shortname": '西秀区',
		"parentid": '520400'
	},
	{
		"id": '520421',
		"shortname": '平坝县',
		"parentid": '520400'
	},
	{
		"id": '520422',
		"shortname": '普定县',
		"parentid": '520400'
	},
	{
		"id": '520423',
		"shortname": '镇宁布依族苗族自治县',
		"parentid": '520400'
	},
	{
		"id": '520424',
		"shortname": '关岭布依族苗族自治县',
		"parentid": '520400'
	},
	{
		"id": '520425',
		"shortname": '紫云苗族布依族自治县',
		"parentid": '520400'
	},
	{
		"id": '520440',
		"shortname": '经济技术开发区',
		"parentid": '520400'
	},
	{
		"id": '520441',
		"shortname": '黄果树管委会',
		"parentid": '520400'
	},
	{
		"id": '522201',
		"shortname": '铜仁市',
		"parentid": '522200'
	},
	{
		"id": '522222',
		"shortname": '江口县',
		"parentid": '522200'
	},
	{
		"id": '522223',
		"shortname": '玉屏侗族自治县',
		"parentid": '522200'
	},
	{
		"id": '522224',
		"shortname": '石阡县',
		"parentid": '522200'
	},
	{
		"id": '522225',
		"shortname": '思南县',
		"parentid": '522200'
	},
	{
		"id": '522226',
		"shortname": '印江土家族苗族自治县',
		"parentid": '522200'
	},
	{
		"id": '522227',
		"shortname": '德江县',
		"parentid": '522200'
	},
	{
		"id": '522228',
		"shortname": '沿河土家族自治县',
		"parentid": '522200'
	},
	{
		"id": '522229',
		"shortname": '松桃苗族自治县',
		"parentid": '522200'
	},
	{
		"id": '522230',
		"shortname": '万山特区',
		"parentid": '522200'
	},
	{
		"id": '522301',
		"shortname": '兴义市',
		"parentid": '522300'
	},
	{
		"id": '522322',
		"shortname": '兴仁县',
		"parentid": '522300'
	},
	{
		"id": '522323',
		"shortname": '普安县',
		"parentid": '522300'
	},
	{
		"id": '522324',
		"shortname": '晴隆县',
		"parentid": '522300'
	},
	{
		"id": '522325',
		"shortname": '贞丰县',
		"parentid": '522300'
	},
	{
		"id": '522326',
		"shortname": '望谟县',
		"parentid": '522300'
	},
	{
		"id": '522327',
		"shortname": '册亨县',
		"parentid": '522300'
	},
	{
		"id": '522328',
		"shortname": '安龙县',
		"parentid": '522300'
	},
	{
		"id": '522340',
		"shortname": '顶效经济技术开发区',
		"parentid": '522300'
	},
	{
		"id": '522401',
		"shortname": '毕节市',
		"parentid": '522400'
	},
	{
		"id": '522422',
		"shortname": '大方县',
		"parentid": '522400'
	},
	{
		"id": '522423',
		"shortname": '黔西县',
		"parentid": '522400'
	},
	{
		"id": '522424',
		"shortname": '金沙县',
		"parentid": '522400'
	},
	{
		"id": '522425',
		"shortname": '织金县',
		"parentid": '522400'
	},
	{
		"id": '522426',
		"shortname": '纳雍县',
		"parentid": '522400'
	},
	{
		"id": '522427',
		"shortname": '威宁彝族回族苗族自治县',
		"parentid": '522400'
	},
	{
		"id": '522428',
		"shortname": '赫章县',
		"parentid": '522400'
	},
	{
		"id": '522440',
		"shortname": '百里杜鹃管理区',
		"parentid": '522400'
	},
	{
		"id": '522601',
		"shortname": '凯里市',
		"parentid": '522600'
	},
	{
		"id": '522622',
		"shortname": '黄平县',
		"parentid": '522600'
	},
	{
		"id": '522623',
		"shortname": '施秉县',
		"parentid": '522600'
	},
	{
		"id": '522624',
		"shortname": '三穗县',
		"parentid": '522600'
	},
	{
		"id": '522625',
		"shortname": '镇远县',
		"parentid": '522600'
	},
	{
		"id": '522626',
		"shortname": '岑巩县',
		"parentid": '522600'
	},
	{
		"id": '522627',
		"shortname": '天柱县',
		"parentid": '522600'
	},
	{
		"id": '522628',
		"shortname": '锦屏县',
		"parentid": '522600'
	},
	{
		"id": '522629',
		"shortname": '剑河县',
		"parentid": '522600'
	},
	{
		"id": '522630',
		"shortname": '台江县',
		"parentid": '522600'
	},
	{
		"id": '522631',
		"shortname": '黎平县',
		"parentid": '522600'
	},
	{
		"id": '522632',
		"shortname": '榕江县',
		"parentid": '522600'
	},
	{
		"id": '522633',
		"shortname": '从江县',
		"parentid": '522600'
	},
	{
		"id": '522634',
		"shortname": '雷山县',
		"parentid": '522600'
	},
	{
		"id": '522635',
		"shortname": '麻江县',
		"parentid": '522600'
	},
	{
		"id": '522636',
		"shortname": '丹寨县',
		"parentid": '522600'
	},
	{
		"id": '522701',
		"shortname": '都匀市',
		"parentid": '522700'
	},
	{
		"id": '522702',
		"shortname": '福泉市',
		"parentid": '522700'
	},
	{
		"id": '522722',
		"shortname": '荔波县',
		"parentid": '522700'
	},
	{
		"id": '522723',
		"shortname": '贵定县',
		"parentid": '522700'
	},
	{
		"id": '522725',
		"shortname": '瓮安县',
		"parentid": '522700'
	},
	{
		"id": '522726',
		"shortname": '独山县',
		"parentid": '522700'
	},
	{
		"id": '522727',
		"shortname": '平塘县',
		"parentid": '522700'
	},
	{
		"id": '522728',
		"shortname": '罗甸县',
		"parentid": '522700'
	},
	{
		"id": '522729',
		"shortname": '长顺县',
		"parentid": '522700'
	},
	{
		"id": '522730',
		"shortname": '龙里县',
		"parentid": '522700'
	},
	{
		"id": '522731',
		"shortname": '惠水县',
		"parentid": '522700'
	},
	{
		"id": '522732',
		"shortname": '三都水族自治县',
		"parentid": '522700'
	},
	{
		"id": '530102',
		"shortname": '五华区',
		"parentid": '530100'
	},
	{
		"id": '530103',
		"shortname": '盘龙区',
		"parentid": '530100'
	},
	{
		"id": '530111',
		"shortname": '官渡区',
		"parentid": '530100'
	},
	{
		"id": '530112',
		"shortname": '西山区',
		"parentid": '530100'
	},
	{
		"id": '530113',
		"shortname": '东川区',
		"parentid": '530100'
	},
	{
		"id": '530121',
		"shortname": '呈贡县',
		"parentid": '530100'
	},
	{
		"id": '530122',
		"shortname": '晋宁县',
		"parentid": '530100'
	},
	{
		"id": '530124',
		"shortname": '富民县',
		"parentid": '530100'
	},
	{
		"id": '530125',
		"shortname": '宜良县',
		"parentid": '530100'
	},
	{
		"id": '530126',
		"shortname": '石林彝族自治县',
		"parentid": '530100'
	},
	{
		"id": '530127',
		"shortname": '嵩明县',
		"parentid": '530100'
	},
	{
		"id": '530128',
		"shortname": '禄劝彝族苗族自治县',
		"parentid": '530100'
	},
	{
		"id": '530129',
		"shortname": '寻甸回族彝族自治县',
		"parentid": '530100'
	},
	{
		"id": '530181',
		"shortname": '安宁市',
		"parentid": '530100'
	},
	{
		"id": '530302',
		"shortname": '麒麟区',
		"parentid": '530300'
	},
	{
		"id": '530321',
		"shortname": '马龙县',
		"parentid": '530300'
	},
	{
		"id": '530322',
		"shortname": '陆良县',
		"parentid": '530300'
	},
	{
		"id": '530323',
		"shortname": '师宗县',
		"parentid": '530300'
	},
	{
		"id": '530324',
		"shortname": '罗平县',
		"parentid": '530300'
	},
	{
		"id": '530326',
		"shortname": '会泽县',
		"parentid": '530300'
	},
	{
		"id": '530328',
		"shortname": '沾益县',
		"parentid": '530300'
	},
	{
		"id": '530381',
		"shortname": '宣威市',
		"parentid": '530300'
	},
	{
		"id": '530325',
		"shortname": '富源县',
		"parentid": '530300'
	},
	{
		"id": '530402',
		"shortname": '红塔区',
		"parentid": '530400'
	},
	{
		"id": '530421',
		"shortname": '江川县',
		"parentid": '530400'
	},
	{
		"id": '530422',
		"shortname": '澄江县',
		"parentid": '530400'
	},
	{
		"id": '530424',
		"shortname": '华宁县',
		"parentid": '530400'
	},
	{
		"id": '530425',
		"shortname": '易门县',
		"parentid": '530400'
	},
	{
		"id": '530426',
		"shortname": '峨山彝族自治县',
		"parentid": '530400'
	},
	{
		"id": '530427',
		"shortname": '新平彝族傣族自治县',
		"parentid": '530400'
	},
	{
		"id": '530428',
		"shortname": '元江哈尼族彝族傣族自治县',
		"parentid": '530400'
	},
	{
		"id": '530423',
		"shortname": '通海县',
		"parentid": '530400'
	},
	{
		"id": '530502',
		"shortname": '隆阳区',
		"parentid": '530500'
	},
	{
		"id": '530522',
		"shortname": '腾冲县',
		"parentid": '530500'
	},
	{
		"id": '530523',
		"shortname": '龙陵县',
		"parentid": '530500'
	},
	{
		"id": '530524',
		"shortname": '昌宁县',
		"parentid": '530500'
	},
	{
		"id": '530521',
		"shortname": '施甸县',
		"parentid": '530500'
	},
	{
		"id": '530602',
		"shortname": '昭阳区',
		"parentid": '530600'
	},
	{
		"id": '530621',
		"shortname": '鲁甸县',
		"parentid": '530600'
	},
	{
		"id": '530622',
		"shortname": '巧家县',
		"parentid": '530600'
	},
	{
		"id": '530624',
		"shortname": '大关县',
		"parentid": '530600'
	},
	{
		"id": '530625',
		"shortname": '永善县',
		"parentid": '530600'
	},
	{
		"id": '530626',
		"shortname": '绥江县',
		"parentid": '530600'
	},
	{
		"id": '530627',
		"shortname": '镇雄县',
		"parentid": '530600'
	},
	{
		"id": '530628',
		"shortname": '彝良县',
		"parentid": '530600'
	},
	{
		"id": '530629',
		"shortname": '威信县',
		"parentid": '530600'
	},
	{
		"id": '530630',
		"shortname": '水富县',
		"parentid": '530600'
	},
	{
		"id": '530623',
		"shortname": '盐津县',
		"parentid": '530600'
	},
	{
		"id": '530702',
		"shortname": '古城区',
		"parentid": '530700'
	},
	{
		"id": '530721',
		"shortname": '玉龙纳西族自治县',
		"parentid": '530700'
	},
	{
		"id": '530722',
		"shortname": '永胜县',
		"parentid": '530700'
	},
	{
		"id": '530723',
		"shortname": '华坪县',
		"parentid": '530700'
	},
	{
		"id": '530724',
		"shortname": '宁蒗彝族自治县',
		"parentid": '530700'
	},
	{
		"id": '530802',
		"shortname": '思茅区',
		"parentid": '530800'
	},
	{
		"id": '530821',
		"shortname": '宁洱哈尼族彝族自治县',
		"parentid": '530800'
	},
	{
		"id": '530822',
		"shortname": '墨江哈尼族自治县',
		"parentid": '530800'
	},
	{
		"id": '530823',
		"shortname": '景东彝族自治县',
		"parentid": '530800'
	},
	{
		"id": '530824',
		"shortname": '景谷傣族彝族自治县',
		"parentid": '530800'
	},
	{
		"id": '530825',
		"shortname": '镇沅彝族哈尼族拉祜族自治县',
		"parentid": '530800'
	},
	{
		"id": '530826',
		"shortname": '江城哈尼族彝族自治县',
		"parentid": '530800'
	},
	{
		"id": '530827',
		"shortname": '孟连傣族拉祜族佤族自治县',
		"parentid": '530800'
	},
	{
		"id": '530828',
		"shortname": '澜沧拉祜族自治县',
		"parentid": '530800'
	},
	{
		"id": '530829',
		"shortname": '西盟佤族自治县',
		"parentid": '530800'
	},
	{
		"id": '530902',
		"shortname": '临翔区',
		"parentid": '530900'
	},
	{
		"id": '530921',
		"shortname": '凤庆县',
		"parentid": '530900'
	},
	{
		"id": '530922',
		"shortname": '云县',
		"parentid": '530900'
	},
	{
		"id": '530923',
		"shortname": '永德县',
		"parentid": '530900'
	},
	{
		"id": '530924',
		"shortname": '镇康县',
		"parentid": '530900'
	},
	{
		"id": '530925',
		"shortname": '双江拉祜族佤族布朗族傣族自治县',
		"parentid": '530900'
	},
	{
		"id": '530926',
		"shortname": '耿马傣族佤族自治县',
		"parentid": '530900'
	},
	{
		"id": '530927',
		"shortname": '沧源佤族自治县',
		"parentid": '530900'
	},
	{
		"id": '532301',
		"shortname": '楚雄市',
		"parentid": '532300'
	},
	{
		"id": '532322',
		"shortname": '双柏县',
		"parentid": '532300'
	},
	{
		"id": '532324',
		"shortname": '南华县',
		"parentid": '532300'
	},
	{
		"id": '532325',
		"shortname": '姚安县',
		"parentid": '532300'
	},
	{
		"id": '532326',
		"shortname": '大姚县',
		"parentid": '532300'
	},
	{
		"id": '532327',
		"shortname": '永仁县',
		"parentid": '532300'
	},
	{
		"id": '532328',
		"shortname": '元谋县',
		"parentid": '532300'
	},
	{
		"id": '532329',
		"shortname": '武定县',
		"parentid": '532300'
	},
	{
		"id": '532331',
		"shortname": '禄丰县',
		"parentid": '532300'
	},
	{
		"id": '532323',
		"shortname": '牟定县',
		"parentid": '532300'
	},
	{
		"id": '532501',
		"shortname": '个旧市',
		"parentid": '532500'
	},
	{
		"id": '532502',
		"shortname": '开远市',
		"parentid": '532500'
	},
	{
		"id": '532522',
		"shortname": '蒙自县',
		"parentid": '532500'
	},
	{
		"id": '532523',
		"shortname": '屏边苗族自治县',
		"parentid": '532500'
	},
	{
		"id": '532524',
		"shortname": '建水县',
		"parentid": '532500'
	},
	{
		"id": '532525',
		"shortname": '石屏县',
		"parentid": '532500'
	},
	{
		"id": '532526',
		"shortname": '弥勒县',
		"parentid": '532500'
	},
	{
		"id": '532527',
		"shortname": '泸西县',
		"parentid": '532500'
	},
	{
		"id": '532528',
		"shortname": '元阳县',
		"parentid": '532500'
	},
	{
		"id": '532529',
		"shortname": '红河县',
		"parentid": '532500'
	},
	{
		"id": '532530',
		"shortname": '金平苗族瑶族傣族自治县',
		"parentid": '532500'
	},
	{
		"id": '532531',
		"shortname": '绿春县',
		"parentid": '532500'
	},
	{
		"id": '532532',
		"shortname": '河口瑶族自治县',
		"parentid": '532500'
	},
	{
		"id": '532621',
		"shortname": '文山县',
		"parentid": '532600'
	},
	{
		"id": '532622',
		"shortname": '砚山县',
		"parentid": '532600'
	},
	{
		"id": '532623',
		"shortname": '西畴县',
		"parentid": '532600'
	},
	{
		"id": '532624',
		"shortname": '麻栗坡县',
		"parentid": '532600'
	},
	{
		"id": '532625',
		"shortname": '马关县',
		"parentid": '532600'
	},
	{
		"id": '532626',
		"shortname": '丘北县',
		"parentid": '532600'
	},
	{
		"id": '532627',
		"shortname": '广南县',
		"parentid": '532600'
	},
	{
		"id": '532628',
		"shortname": '富宁县',
		"parentid": '532600'
	},
	{
		"id": '532801',
		"shortname": '景洪市',
		"parentid": '532800'
	},
	{
		"id": '532822',
		"shortname": '勐海县',
		"parentid": '532800'
	},
	{
		"id": '532823',
		"shortname": '勐腊县',
		"parentid": '532800'
	},
	{
		"id": '532901',
		"shortname": '大理市',
		"parentid": '532900'
	},
	{
		"id": '532922',
		"shortname": '漾濞彝族自治县',
		"parentid": '532900'
	},
	{
		"id": '532923',
		"shortname": '祥云县',
		"parentid": '532900'
	},
	{
		"id": '532924',
		"shortname": '宾川县',
		"parentid": '532900'
	},
	{
		"id": '532925',
		"shortname": '弥渡县',
		"parentid": '532900'
	},
	{
		"id": '532926',
		"shortname": '南涧彝族自治县',
		"parentid": '532900'
	},
	{
		"id": '532927',
		"shortname": '巍山彝族回族自治县',
		"parentid": '532900'
	},
	{
		"id": '532928',
		"shortname": '永平县',
		"parentid": '532900'
	},
	{
		"id": '532929',
		"shortname": '云龙县',
		"parentid": '532900'
	},
	{
		"id": '532930',
		"shortname": '洱源县',
		"parentid": '532900'
	},
	{
		"id": '532931',
		"shortname": '剑川县',
		"parentid": '532900'
	},
	{
		"id": '532932',
		"shortname": '鹤庆县',
		"parentid": '532900'
	},
	{
		"id": '533103',
		"shortname": '芒市',
		"parentid": '533100'
	},
	{
		"id": '533102',
		"shortname": '瑞丽市',
		"parentid": '533100'
	},
	{
		"id": '533122',
		"shortname": '梁河县',
		"parentid": '533100'
	},
	{
		"id": '533123',
		"shortname": '盈江县',
		"parentid": '533100'
	},
	{
		"id": '533124',
		"shortname": '陇川县',
		"parentid": '533100'
	},
	{
		"id": '533321',
		"shortname": '泸水县',
		"parentid": '533300'
	},
	{
		"id": '533323',
		"shortname": '福贡县',
		"parentid": '533300'
	},
	{
		"id": '533324',
		"shortname": '贡山独龙族怒族自治县',
		"parentid": '533300'
	},
	{
		"id": '533325',
		"shortname": '兰坪白族普米族自治县',
		"parentid": '533300'
	},
	{
		"id": '533421',
		"shortname": '香格里拉县',
		"parentid": '533400'
	},
	{
		"id": '533422',
		"shortname": '德钦县',
		"parentid": '533400'
	},
	{
		"id": '533423',
		"shortname": '维西傈僳族自治县',
		"parentid": '533400'
	},
	{
		"id": '540102',
		"shortname": '城关区',
		"parentid": '540100'
	},
	{
		"id": '540121',
		"shortname": '林周县',
		"parentid": '540100'
	},
	{
		"id": '540122',
		"shortname": '当雄县',
		"parentid": '540100'
	},
	{
		"id": '540123',
		"shortname": '尼木县',
		"parentid": '540100'
	},
	{
		"id": '540124',
		"shortname": '曲水县',
		"parentid": '540100'
	},
	{
		"id": '540125',
		"shortname": '堆龙德庆县',
		"parentid": '540100'
	},
	{
		"id": '540126',
		"shortname": '达孜县',
		"parentid": '540100'
	},
	{
		"id": '540127',
		"shortname": '墨竹工卡县',
		"parentid": '540100'
	},
	{
		"id": '542121',
		"shortname": '昌都县',
		"parentid": '542100'
	},
	{
		"id": '542122',
		"shortname": '江达县',
		"parentid": '542100'
	},
	{
		"id": '542123',
		"shortname": '贡觉县',
		"parentid": '542100'
	},
	{
		"id": '542124',
		"shortname": '类乌齐县',
		"parentid": '542100'
	},
	{
		"id": '542125',
		"shortname": '丁青县',
		"parentid": '542100'
	},
	{
		"id": '542126',
		"shortname": '察雅县',
		"parentid": '542100'
	},
	{
		"id": '542127',
		"shortname": '八宿县',
		"parentid": '542100'
	},
	{
		"id": '542128',
		"shortname": '左贡县',
		"parentid": '542100'
	},
	{
		"id": '542129',
		"shortname": '芒康县',
		"parentid": '542100'
	},
	{
		"id": '542132',
		"shortname": '洛隆县',
		"parentid": '542100'
	},
	{
		"id": '542133',
		"shortname": '边坝县',
		"parentid": '542100'
	},
	{
		"id": '542221',
		"shortname": '乃东县',
		"parentid": '542200'
	},
	{
		"id": '542222',
		"shortname": '扎囊县',
		"parentid": '542200'
	},
	{
		"id": '542223',
		"shortname": '贡嘎县',
		"parentid": '542200'
	},
	{
		"id": '542224',
		"shortname": '桑日县',
		"parentid": '542200'
	},
	{
		"id": '542225',
		"shortname": '琼结县',
		"parentid": '542200'
	},
	{
		"id": '542226',
		"shortname": '曲松县',
		"parentid": '542200'
	},
	{
		"id": '542227',
		"shortname": '措美县',
		"parentid": '542200'
	},
	{
		"id": '542228',
		"shortname": '洛扎县',
		"parentid": '542200'
	},
	{
		"id": '542229',
		"shortname": '加查县',
		"parentid": '542200'
	},
	{
		"id": '542231',
		"shortname": '隆子县',
		"parentid": '542200'
	},
	{
		"id": '542232',
		"shortname": '错那县',
		"parentid": '542200'
	},
	{
		"id": '542233',
		"shortname": '浪卡子县',
		"parentid": '542200'
	},
	{
		"id": '542322',
		"shortname": '南木林县',
		"parentid": '542300'
	},
	{
		"id": '542323',
		"shortname": '江孜县',
		"parentid": '542300'
	},
	{
		"id": '542324',
		"shortname": '定日县',
		"parentid": '542300'
	},
	{
		"id": '542325',
		"shortname": '萨迦县',
		"parentid": '542300'
	},
	{
		"id": '542326',
		"shortname": '拉孜县',
		"parentid": '542300'
	},
	{
		"id": '542327',
		"shortname": '昂仁县',
		"parentid": '542300'
	},
	{
		"id": '542328',
		"shortname": '谢通门县',
		"parentid": '542300'
	},
	{
		"id": '542330',
		"shortname": '仁布县',
		"parentid": '542300'
	},
	{
		"id": '542331',
		"shortname": '康马县',
		"parentid": '542300'
	},
	{
		"id": '542332',
		"shortname": '定结县',
		"parentid": '542300'
	},
	{
		"id": '542333',
		"shortname": '仲巴县',
		"parentid": '542300'
	},
	{
		"id": '542334',
		"shortname": '亚东县',
		"parentid": '542300'
	},
	{
		"id": '542335',
		"shortname": '吉隆县',
		"parentid": '542300'
	},
	{
		"id": '542336',
		"shortname": '聂拉木县',
		"parentid": '542300'
	},
	{
		"id": '542337',
		"shortname": '萨嘎县',
		"parentid": '542300'
	},
	{
		"id": '542338',
		"shortname": '岗巴县',
		"parentid": '542300'
	},
	{
		"id": '542301',
		"shortname": '日喀则市',
		"parentid": '542300'
	},
	{
		"id": '542329',
		"shortname": '白朗县',
		"parentid": '542300'
	},
	{
		"id": '542421',
		"shortname": '那曲县',
		"parentid": '542400'
	},
	{
		"id": '542422',
		"shortname": '嘉黎县',
		"parentid": '542400'
	},
	{
		"id": '542423',
		"shortname": '比如县',
		"parentid": '542400'
	},
	{
		"id": '542424',
		"shortname": '聂荣县',
		"parentid": '542400'
	},
	{
		"id": '542425',
		"shortname": '安多县',
		"parentid": '542400'
	},
	{
		"id": '542426',
		"shortname": '申扎县',
		"parentid": '542400'
	},
	{
		"id": '542427',
		"shortname": '索县',
		"parentid": '542400'
	},
	{
		"id": '542428',
		"shortname": '班戈县',
		"parentid": '542400'
	},
	{
		"id": '542429',
		"shortname": '巴青县',
		"parentid": '542400'
	},
	{
		"id": '542430',
		"shortname": '尼玛县',
		"parentid": '542400'
	},
	{
		"id": '542521',
		"shortname": '普兰县',
		"parentid": '542500'
	},
	{
		"id": '542522',
		"shortname": '札达县',
		"parentid": '542500'
	},
	{
		"id": '542523',
		"shortname": '噶尔县',
		"parentid": '542500'
	},
	{
		"id": '542524',
		"shortname": '日土县',
		"parentid": '542500'
	},
	{
		"id": '542525',
		"shortname": '革吉县',
		"parentid": '542500'
	},
	{
		"id": '542526',
		"shortname": '改则县',
		"parentid": '542500'
	},
	{
		"id": '542527',
		"shortname": '措勤县',
		"parentid": '542500'
	},
	{
		"id": '542621',
		"shortname": '林芝县',
		"parentid": '542600'
	},
	{
		"id": '542622',
		"shortname": '工布江达县',
		"parentid": '542600'
	},
	{
		"id": '542623',
		"shortname": '米林县',
		"parentid": '542600'
	},
	{
		"id": '542624',
		"shortname": '墨脱县',
		"parentid": '542600'
	},
	{
		"id": '542625',
		"shortname": '波密县',
		"parentid": '542600'
	},
	{
		"id": '542626',
		"shortname": '察隅县',
		"parentid": '542600'
	},
	{
		"id": '542627',
		"shortname": '朗县',
		"parentid": '542600'
	},
	{
		"id": '610102',
		"shortname": '新城区',
		"parentid": '610100'
	},
	{
		"id": '610103',
		"shortname": '碑林区',
		"parentid": '610100'
	},
	{
		"id": '610104',
		"shortname": '莲湖区',
		"parentid": '610100'
	},
	{
		"id": '610111',
		"shortname": '灞桥区',
		"parentid": '610100'
	},
	{
		"id": '610112',
		"shortname": '未央区',
		"parentid": '610100'
	},
	{
		"id": '610113',
		"shortname": '雁塔区',
		"parentid": '610100'
	},
	{
		"id": '610115',
		"shortname": '临潼区',
		"parentid": '610100'
	},
	{
		"id": '610116',
		"shortname": '长安区',
		"parentid": '610100'
	},
	{
		"id": '610122',
		"shortname": '蓝田县',
		"parentid": '610100'
	},
	{
		"id": '610124',
		"shortname": '周至县',
		"parentid": '610100'
	},
	{
		"id": '610125',
		"shortname": '户县',
		"parentid": '610100'
	},
	{
		"id": '610126',
		"shortname": '高陵县',
		"parentid": '610100'
	},
	{
		"id": '610141',
		"shortname": '经济开发区',
		"parentid": '610100'
	},
	{
		"id": '610114',
		"shortname": '阎良区',
		"parentid": '610100'
	},
	{
		"id": '610140',
		"shortname": '高新技术产业开发区',
		"parentid": '610100'
	},
	{
		"id": '610202',
		"shortname": '王益区',
		"parentid": '610200'
	},
	{
		"id": '610203',
		"shortname": '印台区',
		"parentid": '610200'
	},
	{
		"id": '610204',
		"shortname": '耀州区',
		"parentid": '610200'
	},
	{
		"id": '610222',
		"shortname": '宜君县',
		"parentid": '610200'
	},
	{
		"id": '610302',
		"shortname": '渭滨区',
		"parentid": '610300'
	},
	{
		"id": '610303',
		"shortname": '金台区',
		"parentid": '610300'
	},
	{
		"id": '610304',
		"shortname": '陈仓区',
		"parentid": '610300'
	},
	{
		"id": '610322',
		"shortname": '凤翔县',
		"parentid": '610300'
	},
	{
		"id": '610323',
		"shortname": '岐山县',
		"parentid": '610300'
	},
	{
		"id": '610324',
		"shortname": '扶风县',
		"parentid": '610300'
	},
	{
		"id": '610326',
		"shortname": '眉县',
		"parentid": '610300'
	},
	{
		"id": '610327',
		"shortname": '陇县',
		"parentid": '610300'
	},
	{
		"id": '610328',
		"shortname": '千阳县',
		"parentid": '610300'
	},
	{
		"id": '610329',
		"shortname": '麟游县',
		"parentid": '610300'
	},
	{
		"id": '610330',
		"shortname": '凤县',
		"parentid": '610300'
	},
	{
		"id": '610331',
		"shortname": '太白县',
		"parentid": '610300'
	},
	{
		"id": '610423',
		"shortname": '泾阳县',
		"parentid": '610400'
	},
	{
		"id": '610424',
		"shortname": '乾县',
		"parentid": '610400'
	},
	{
		"id": '610425',
		"shortname": '礼泉县',
		"parentid": '610400'
	},
	{
		"id": '610426',
		"shortname": '永寿县',
		"parentid": '610400'
	},
	{
		"id": '610427',
		"shortname": '彬县',
		"parentid": '610400'
	},
	{
		"id": '610428',
		"shortname": '长武县',
		"parentid": '610400'
	},
	{
		"id": '610429',
		"shortname": '旬邑县',
		"parentid": '610400'
	},
	{
		"id": '610430',
		"shortname": '淳化县',
		"parentid": '610400'
	},
	{
		"id": '610431',
		"shortname": '武功县',
		"parentid": '610400'
	},
	{
		"id": '610481',
		"shortname": '兴平市',
		"parentid": '610400'
	},
	{
		"id": '610402',
		"shortname": '秦都区',
		"parentid": '610400'
	},
	{
		"id": '610404',
		"shortname": '渭城区',
		"parentid": '610400'
	},
	{
		"id": '610422',
		"shortname": '三原县',
		"parentid": '610400'
	},
	{
		"id": '610502',
		"shortname": '临渭区',
		"parentid": '610500'
	},
	{
		"id": '610521',
		"shortname": '华县',
		"parentid": '610500'
	},
	{
		"id": '610522',
		"shortname": '潼关县',
		"parentid": '610500'
	},
	{
		"id": '610523',
		"shortname": '大荔县',
		"parentid": '610500'
	},
	{
		"id": '610524',
		"shortname": '合阳县',
		"parentid": '610500'
	},
	{
		"id": '610525',
		"shortname": '澄城县',
		"parentid": '610500'
	},
	{
		"id": '610526',
		"shortname": '蒲城县',
		"parentid": '610500'
	},
	{
		"id": '610527',
		"shortname": '白水县',
		"parentid": '610500'
	},
	{
		"id": '610528',
		"shortname": '富平县',
		"parentid": '610500'
	},
	{
		"id": '610540',
		"shortname": '高新技术产业开发区',
		"parentid": '610500'
	},
	{
		"id": '610581',
		"shortname": '韩城市',
		"parentid": '610500'
	},
	{
		"id": '610582',
		"shortname": '华阴市',
		"parentid": '610500'
	},
	{
		"id": '610602',
		"shortname": '宝塔区',
		"parentid": '610600'
	},
	{
		"id": '610621',
		"shortname": '延长县',
		"parentid": '610600'
	},
	{
		"id": '610622',
		"shortname": '延川县',
		"parentid": '610600'
	},
	{
		"id": '610623',
		"shortname": '子长县',
		"parentid": '610600'
	},
	{
		"id": '610624',
		"shortname": '安塞县',
		"parentid": '610600'
	},
	{
		"id": '610625',
		"shortname": '志丹县',
		"parentid": '610600'
	},
	{
		"id": '610626',
		"shortname": '吴起县',
		"parentid": '610600'
	},
	{
		"id": '610627',
		"shortname": '甘泉县',
		"parentid": '610600'
	},
	{
		"id": '610628',
		"shortname": '富县',
		"parentid": '610600'
	},
	{
		"id": '610629',
		"shortname": '洛川县',
		"parentid": '610600'
	},
	{
		"id": '610630',
		"shortname": '宜川县',
		"parentid": '610600'
	},
	{
		"id": '610631',
		"shortname": '黄龙县',
		"parentid": '610600'
	},
	{
		"id": '610632',
		"shortname": '黄陵县',
		"parentid": '610600'
	},
	{
		"id": '610702',
		"shortname": '汉台区',
		"parentid": '610700'
	},
	{
		"id": '610721',
		"shortname": '南郑县',
		"parentid": '610700'
	},
	{
		"id": '610722',
		"shortname": '城固县',
		"parentid": '610700'
	},
	{
		"id": '610723',
		"shortname": '洋县',
		"parentid": '610700'
	},
	{
		"id": '610724',
		"shortname": '西乡县',
		"parentid": '610700'
	},
	{
		"id": '610725',
		"shortname": '勉县',
		"parentid": '610700'
	},
	{
		"id": '610726',
		"shortname": '宁强县',
		"parentid": '610700'
	},
	{
		"id": '610727',
		"shortname": '略阳县',
		"parentid": '610700'
	},
	{
		"id": '610728',
		"shortname": '镇巴县',
		"parentid": '610700'
	},
	{
		"id": '610729',
		"shortname": '留坝县',
		"parentid": '610700'
	},
	{
		"id": '610730',
		"shortname": '佛坪县',
		"parentid": '610700'
	},
	{
		"id": '610802',
		"shortname": '榆阳区',
		"parentid": '610800'
	},
	{
		"id": '610821',
		"shortname": '神木县',
		"parentid": '610800'
	},
	{
		"id": '610822',
		"shortname": '府谷县',
		"parentid": '610800'
	},
	{
		"id": '610823',
		"shortname": '横山县',
		"parentid": '610800'
	},
	{
		"id": '610824',
		"shortname": '靖边县',
		"parentid": '610800'
	},
	{
		"id": '610825',
		"shortname": '定边县',
		"parentid": '610800'
	},
	{
		"id": '610826',
		"shortname": '绥德县',
		"parentid": '610800'
	},
	{
		"id": '610827',
		"shortname": '米脂县',
		"parentid": '610800'
	},
	{
		"id": '610828',
		"shortname": '佳县',
		"parentid": '610800'
	},
	{
		"id": '610829',
		"shortname": '吴堡县',
		"parentid": '610800'
	},
	{
		"id": '610830',
		"shortname": '清涧县',
		"parentid": '610800'
	},
	{
		"id": '610831',
		"shortname": '子洲县',
		"parentid": '610800'
	},
	{
		"id": '610902',
		"shortname": '汉滨区',
		"parentid": '610900'
	},
	{
		"id": '610921',
		"shortname": '汉阴县',
		"parentid": '610900'
	},
	{
		"id": '610922',
		"shortname": '石泉县',
		"parentid": '610900'
	},
	{
		"id": '610923',
		"shortname": '宁陕县',
		"parentid": '610900'
	},
	{
		"id": '610924',
		"shortname": '紫阳县',
		"parentid": '610900'
	},
	{
		"id": '610925',
		"shortname": '岚皋县',
		"parentid": '610900'
	},
	{
		"id": '610926',
		"shortname": '平利县',
		"parentid": '610900'
	},
	{
		"id": '610927',
		"shortname": '镇坪县',
		"parentid": '610900'
	},
	{
		"id": '610928',
		"shortname": '旬阳县',
		"parentid": '610900'
	},
	{
		"id": '610929',
		"shortname": '白河县',
		"parentid": '610900'
	},
	{
		"id": '611022',
		"shortname": '丹凤县',
		"parentid": '611000'
	},
	{
		"id": '611023',
		"shortname": '商南县',
		"parentid": '611000'
	},
	{
		"id": '611024',
		"shortname": '山阳县',
		"parentid": '611000'
	},
	{
		"id": '611025',
		"shortname": '镇安县',
		"parentid": '611000'
	},
	{
		"id": '611026',
		"shortname": '柞水县',
		"parentid": '611000'
	},
	{
		"id": '611002',
		"shortname": '商州区',
		"parentid": '611000'
	},
	{
		"id": '611021',
		"shortname": '洛南县',
		"parentid": '611000'
	},
	{
		"id": '620121',
		"shortname": '永登县',
		"parentid": '620100'
	},
	{
		"id": '620122',
		"shortname": '皋兰县',
		"parentid": '620100'
	},
	{
		"id": '620123',
		"shortname": '榆中县',
		"parentid": '620100'
	},
	{
		"id": '620102',
		"shortname": '城关区',
		"parentid": '620100'
	},
	{
		"id": '620104',
		"shortname": '西固区',
		"parentid": '620100'
	},
	{
		"id": '620105',
		"shortname": '安宁区',
		"parentid": '620100'
	},
	{
		"id": '620111',
		"shortname": '红古区',
		"parentid": '620100'
	},
	{
		"id": '620103',
		"shortname": '七里河区',
		"parentid": '620100'
	},
	{
		"id": '620302',
		"shortname": '金川区',
		"parentid": '620300'
	},
	{
		"id": '620321',
		"shortname": '永昌县',
		"parentid": '620300'
	},
	{
		"id": '620402',
		"shortname": '白银区',
		"parentid": '620400'
	},
	{
		"id": '620403',
		"shortname": '平川区',
		"parentid": '620400'
	},
	{
		"id": '620421',
		"shortname": '靖远县',
		"parentid": '620400'
	},
	{
		"id": '620422',
		"shortname": '会宁县',
		"parentid": '620400'
	},
	{
		"id": '620423',
		"shortname": '景泰县',
		"parentid": '620400'
	},
	{
		"id": '620502',
		"shortname": '秦州区',
		"parentid": '620500'
	},
	{
		"id": '620503',
		"shortname": '麦积区',
		"parentid": '620500'
	},
	{
		"id": '620521',
		"shortname": '清水县',
		"parentid": '620500'
	},
	{
		"id": '620522',
		"shortname": '秦安县',
		"parentid": '620500'
	},
	{
		"id": '620523',
		"shortname": '甘谷县',
		"parentid": '620500'
	},
	{
		"id": '620524',
		"shortname": '武山县',
		"parentid": '620500'
	},
	{
		"id": '620525',
		"shortname": '张家川回族自治县',
		"parentid": '620500'
	},
	{
		"id": '620602',
		"shortname": '凉州区',
		"parentid": '620600'
	},
	{
		"id": '620621',
		"shortname": '民勤县',
		"parentid": '620600'
	},
	{
		"id": '620622',
		"shortname": '古浪县',
		"parentid": '620600'
	},
	{
		"id": '620623',
		"shortname": '天祝藏族自治县',
		"parentid": '620600'
	},
	{
		"id": '620702',
		"shortname": '甘州区',
		"parentid": '620700'
	},
	{
		"id": '620721',
		"shortname": '肃南裕固族自治县',
		"parentid": '620700'
	},
	{
		"id": '620722',
		"shortname": '民乐县',
		"parentid": '620700'
	},
	{
		"id": '620724',
		"shortname": '高台县',
		"parentid": '620700'
	},
	{
		"id": '620725',
		"shortname": '山丹县',
		"parentid": '620700'
	},
	{
		"id": '620723',
		"shortname": '临泽县',
		"parentid": '620700'
	},
	{
		"id": '620802',
		"shortname": '崆峒区',
		"parentid": '620800'
	},
	{
		"id": '620821',
		"shortname": '泾川县',
		"parentid": '620800'
	},
	{
		"id": '620822',
		"shortname": '灵台县',
		"parentid": '620800'
	},
	{
		"id": '620823',
		"shortname": '崇信县',
		"parentid": '620800'
	},
	{
		"id": '620824',
		"shortname": '华亭县',
		"parentid": '620800'
	},
	{
		"id": '620825',
		"shortname": '庄浪县',
		"parentid": '620800'
	},
	{
		"id": '620826',
		"shortname": '静宁县',
		"parentid": '620800'
	},
	{
		"id": '620902',
		"shortname": '肃州区',
		"parentid": '620900'
	},
	{
		"id": '620921',
		"shortname": '金塔县',
		"parentid": '620900'
	},
	{
		"id": '620922',
		"shortname": '瓜州县',
		"parentid": '620900'
	},
	{
		"id": '620923',
		"shortname": '肃北蒙古族自治县',
		"parentid": '620900'
	},
	{
		"id": '620924',
		"shortname": '阿克塞哈萨克族自治县',
		"parentid": '620900'
	},
	{
		"id": '620981',
		"shortname": '玉门市',
		"parentid": '620900'
	},
	{
		"id": '620982',
		"shortname": '敦煌市',
		"parentid": '620900'
	},
	{
		"id": '621002',
		"shortname": '西峰区',
		"parentid": '621000'
	},
	{
		"id": '621021',
		"shortname": '庆城县',
		"parentid": '621000'
	},
	{
		"id": '621022',
		"shortname": '环县',
		"parentid": '621000'
	},
	{
		"id": '621023',
		"shortname": '华池县',
		"parentid": '621000'
	},
	{
		"id": '621024',
		"shortname": '合水县',
		"parentid": '621000'
	},
	{
		"id": '621025',
		"shortname": '正宁县',
		"parentid": '621000'
	},
	{
		"id": '621026',
		"shortname": '宁县',
		"parentid": '621000'
	},
	{
		"id": '621027',
		"shortname": '镇原县',
		"parentid": '621000'
	},
	{
		"id": '621102',
		"shortname": '安定区',
		"parentid": '621100'
	},
	{
		"id": '621121',
		"shortname": '通渭县',
		"parentid": '621100'
	},
	{
		"id": '621122',
		"shortname": '陇西县',
		"parentid": '621100'
	},
	{
		"id": '621123',
		"shortname": '渭源县',
		"parentid": '621100'
	},
	{
		"id": '621124',
		"shortname": '临洮县',
		"parentid": '621100'
	},
	{
		"id": '621125',
		"shortname": '漳县',
		"parentid": '621100'
	},
	{
		"id": '621126',
		"shortname": '岷县',
		"parentid": '621100'
	},
	{
		"id": '621202',
		"shortname": '武都区',
		"parentid": '621200'
	},
	{
		"id": '621221',
		"shortname": '成县',
		"parentid": '621200'
	},
	{
		"id": '621222',
		"shortname": '文县',
		"parentid": '621200'
	},
	{
		"id": '621223',
		"shortname": '宕昌县',
		"parentid": '621200'
	},
	{
		"id": '621224',
		"shortname": '康县',
		"parentid": '621200'
	},
	{
		"id": '621225',
		"shortname": '西和县',
		"parentid": '621200'
	},
	{
		"id": '621226',
		"shortname": '礼县',
		"parentid": '621200'
	},
	{
		"id": '621227',
		"shortname": '徽县',
		"parentid": '621200'
	},
	{
		"id": '621228',
		"shortname": '两当县',
		"parentid": '621200'
	},
	{
		"id": '622901',
		"shortname": '临夏市',
		"parentid": '622900'
	},
	{
		"id": '622921',
		"shortname": '临夏县',
		"parentid": '622900'
	},
	{
		"id": '622922',
		"shortname": '康乐县',
		"parentid": '622900'
	},
	{
		"id": '622923',
		"shortname": '永靖县',
		"parentid": '622900'
	},
	{
		"id": '622924',
		"shortname": '广河县',
		"parentid": '622900'
	},
	{
		"id": '622925',
		"shortname": '和政县',
		"parentid": '622900'
	},
	{
		"id": '622926',
		"shortname": '东乡族自治县',
		"parentid": '622900'
	},
	{
		"id": '622927',
		"shortname": '积石山保安族东乡族撒拉族自治县',
		"parentid": '622900'
	},
	{
		"id": '623001',
		"shortname": '合作市',
		"parentid": '623000'
	},
	{
		"id": '623021',
		"shortname": '临潭县',
		"parentid": '623000'
	},
	{
		"id": '623022',
		"shortname": '卓尼县',
		"parentid": '623000'
	},
	{
		"id": '623023',
		"shortname": '舟曲县',
		"parentid": '623000'
	},
	{
		"id": '623024',
		"shortname": '迭部县',
		"parentid": '623000'
	},
	{
		"id": '623025',
		"shortname": '玛曲县',
		"parentid": '623000'
	},
	{
		"id": '623026',
		"shortname": '碌曲县',
		"parentid": '623000'
	},
	{
		"id": '623027',
		"shortname": '夏河县',
		"parentid": '623000'
	},
	{
		"id": '630102',
		"shortname": '城东区',
		"parentid": '630100'
	},
	{
		"id": '630103',
		"shortname": '城中区',
		"parentid": '630100'
	},
	{
		"id": '630104',
		"shortname": '城西区',
		"parentid": '630100'
	},
	{
		"id": '630105',
		"shortname": '城北区',
		"parentid": '630100'
	},
	{
		"id": '630121',
		"shortname": '大通回族土族自治县',
		"parentid": '630100'
	},
	{
		"id": '630122',
		"shortname": '湟中县',
		"parentid": '630100'
	},
	{
		"id": '630123',
		"shortname": '湟源县',
		"parentid": '630100'
	},
	{
		"id": '632121',
		"shortname": '平安县',
		"parentid": '632100'
	},
	{
		"id": '632122',
		"shortname": '民和回族土族自治县',
		"parentid": '632100'
	},
	{
		"id": '632123',
		"shortname": '乐都县',
		"parentid": '632100'
	},
	{
		"id": '632126',
		"shortname": '互助土族自治县',
		"parentid": '632100'
	},
	{
		"id": '632127',
		"shortname": '化隆回族自治县',
		"parentid": '632100'
	},
	{
		"id": '632128',
		"shortname": '循化撒拉族自治县',
		"parentid": '632100'
	},
	{
		"id": '632221',
		"shortname": '门源回族自治县',
		"parentid": '632200'
	},
	{
		"id": '632222',
		"shortname": '祁连县',
		"parentid": '632200'
	},
	{
		"id": '632223',
		"shortname": '海晏县',
		"parentid": '632200'
	},
	{
		"id": '632224',
		"shortname": '刚察县',
		"parentid": '632200'
	},
	{
		"id": '632321',
		"shortname": '同仁县',
		"parentid": '632300'
	},
	{
		"id": '632322',
		"shortname": '尖扎县',
		"parentid": '632300'
	},
	{
		"id": '632323',
		"shortname": '泽库县',
		"parentid": '632300'
	},
	{
		"id": '632324',
		"shortname": '河南蒙古族自治县',
		"parentid": '632300'
	},
	{
		"id": '632340',
		"shortname": '李家峡',
		"parentid": '632300'
	},
	{
		"id": '632521',
		"shortname": '共和县',
		"parentid": '632500'
	},
	{
		"id": '632522',
		"shortname": '同德县',
		"parentid": '632500'
	},
	{
		"id": '632523',
		"shortname": '贵德县',
		"parentid": '632500'
	},
	{
		"id": '632524',
		"shortname": '兴海县',
		"parentid": '632500'
	},
	{
		"id": '632525',
		"shortname": '贵南县',
		"parentid": '632500'
	},
	{
		"id": '632540',
		"shortname": '龙羊峡',
		"parentid": '632500'
	},
	{
		"id": '632621',
		"shortname": '玛沁县',
		"parentid": '632600'
	},
	{
		"id": '632622',
		"shortname": '班玛县',
		"parentid": '632600'
	},
	{
		"id": '632623',
		"shortname": '甘德县',
		"parentid": '632600'
	},
	{
		"id": '632624',
		"shortname": '达日县',
		"parentid": '632600'
	},
	{
		"id": '632625',
		"shortname": '久治县',
		"parentid": '632600'
	},
	{
		"id": '632626',
		"shortname": '玛多县',
		"parentid": '632600'
	},
	{
		"id": '632721',
		"shortname": '玉树县',
		"parentid": '632700'
	},
	{
		"id": '632722',
		"shortname": '杂多县',
		"parentid": '632700'
	},
	{
		"id": '632723',
		"shortname": '称多县',
		"parentid": '632700'
	},
	{
		"id": '632724',
		"shortname": '治多县',
		"parentid": '632700'
	},
	{
		"id": '632725',
		"shortname": '囊谦县',
		"parentid": '632700'
	},
	{
		"id": '632726',
		"shortname": '曲麻莱县',
		"parentid": '632700'
	},
	{
		"id": '632801',
		"shortname": '格尔木市',
		"parentid": '632800'
	},
	{
		"id": '632802',
		"shortname": '德令哈市',
		"parentid": '632800'
	},
	{
		"id": '632821',
		"shortname": '乌兰县',
		"parentid": '632800'
	},
	{
		"id": '632822',
		"shortname": '都兰县',
		"parentid": '632800'
	},
	{
		"id": '632823',
		"shortname": '天峻县',
		"parentid": '632800'
	},
	{
		"id": '632840',
		"shortname": '茫崖行委',
		"parentid": '632800'
	},
	{
		"id": '632841',
		"shortname": '大柴旦行委',
		"parentid": '632800'
	},
	{
		"id": '632842',
		"shortname": '冷湖行委',
		"parentid": '632800'
	},
	{
		"id": '632843',
		"shortname": '省石油管理局',
		"parentid": '632800'
	},
	{
		"id": '640121',
		"shortname": '永宁县',
		"parentid": '640100'
	},
	{
		"id": '640122',
		"shortname": '贺兰县',
		"parentid": '640100'
	},
	{
		"id": '640181',
		"shortname": '灵武市',
		"parentid": '640100'
	},
	{
		"id": '640221',
		"shortname": '平罗县',
		"parentid": '640200'
	},
	{
		"id": '640303',
		"shortname": '红寺堡区',
		"parentid": '640300'
	},
	{
		"id": '640323',
		"shortname": '盐池县',
		"parentid": '640300'
	},
	{
		"id": '640324',
		"shortname": '同心县',
		"parentid": '640300'
	},
	{
		"id": '640381',
		"shortname": '青铜峡市',
		"parentid": '640300'
	},
	{
		"id": '640422',
		"shortname": '西吉县',
		"parentid": '640400'
	},
	{
		"id": '640423',
		"shortname": '隆德县',
		"parentid": '640400'
	},
	{
		"id": '640424',
		"shortname": '泾源县',
		"parentid": '640400'
	},
	{
		"id": '640425',
		"shortname": '彭阳县',
		"parentid": '640400'
	},
	{
		"id": '640521',
		"shortname": '中宁县',
		"parentid": '640500'
	},
	{
		"id": '640522',
		"shortname": '海原县',
		"parentid": '640500'
	},
	{
		"id": '650102',
		"shortname": '天山区',
		"parentid": '650100'
	},
	{
		"id": '650103',
		"shortname": '沙依巴克区',
		"parentid": '650100'
	},
	{
		"id": '650104',
		"shortname": '新市区',
		"parentid": '650100'
	},
	{
		"id": '650105',
		"shortname": '水磨沟区',
		"parentid": '650100'
	},
	{
		"id": '650106',
		"shortname": '头屯河区',
		"parentid": '650100'
	},
	{
		"id": '650107',
		"shortname": '达坂城区',
		"parentid": '650100'
	},
	{
		"id": '650109',
		"shortname": '米东区',
		"parentid": '650100'
	},
	{
		"id": '650121',
		"shortname": '乌鲁木齐县',
		"parentid": '650100'
	},
	{
		"id": '650140',
		"shortname": '经济技术开发区',
		"parentid": '650100'
	},
	{
		"id": '650202',
		"shortname": '独山子区',
		"parentid": '650200'
	},
	{
		"id": '650203',
		"shortname": '克拉玛依区',
		"parentid": '650200'
	},
	{
		"id": '650204',
		"shortname": '白碱滩区',
		"parentid": '650200'
	},
	{
		"id": '650205',
		"shortname": '乌尔禾区',
		"parentid": '650200'
	},
	{
		"id": '652101',
		"shortname": '吐鲁番市',
		"parentid": '652100'
	},
	{
		"id": '652122',
		"shortname": '鄯善县',
		"parentid": '652100'
	},
	{
		"id": '652123',
		"shortname": '托克逊县',
		"parentid": '652100'
	},
	{
		"id": '652201',
		"shortname": '哈密市',
		"parentid": '652200'
	},
	{
		"id": '652222',
		"shortname": '巴里坤哈萨克自治县',
		"parentid": '652200'
	},
	{
		"id": '652223',
		"shortname": '伊吾县',
		"parentid": '652200'
	},
	{
		"id": '652301',
		"shortname": '昌吉市',
		"parentid": '652300'
	},
	{
		"id": '652302',
		"shortname": '阜康市',
		"parentid": '652300'
	},
	{
		"id": '652323',
		"shortname": '呼图壁县',
		"parentid": '652300'
	},
	{
		"id": '652324',
		"shortname": '玛纳斯县',
		"parentid": '652300'
	},
	{
		"id": '652325',
		"shortname": '奇台县',
		"parentid": '652300'
	},
	{
		"id": '652327',
		"shortname": '吉木萨尔县',
		"parentid": '652300'
	},
	{
		"id": '652328',
		"shortname": '木垒哈萨克自治县',
		"parentid": '652300'
	},
	{
		"id": '652722',
		"shortname": '精河县',
		"parentid": '652700'
	},
	{
		"id": '652723',
		"shortname": '温泉县',
		"parentid": '652700'
	},
	{
		"id": '652701',
		"shortname": '博乐市',
		"parentid": '652700'
	},
	{
		"id": '652801',
		"shortname": '库尔勒市',
		"parentid": '652800'
	},
	{
		"id": '652822',
		"shortname": '轮台县',
		"parentid": '652800'
	},
	{
		"id": '652823',
		"shortname": '尉犁县',
		"parentid": '652800'
	},
	{
		"id": '652824',
		"shortname": '若羌县',
		"parentid": '652800'
	},
	{
		"id": '652826',
		"shortname": '焉耆回族自治县',
		"parentid": '652800'
	},
	{
		"id": '652827',
		"shortname": '和静县',
		"parentid": '652800'
	},
	{
		"id": '652828',
		"shortname": '和硕县',
		"parentid": '652800'
	},
	{
		"id": '652829',
		"shortname": '博湖县',
		"parentid": '652800'
	},
	{
		"id": '652825',
		"shortname": '且末县',
		"parentid": '652800'
	},
	{
		"id": '652924',
		"shortname": '沙雅县',
		"parentid": '652900'
	},
	{
		"id": '652901',
		"shortname": '阿克苏市',
		"parentid": '652900'
	},
	{
		"id": '652922',
		"shortname": '温宿县',
		"parentid": '652900'
	},
	{
		"id": '652923',
		"shortname": '库车县',
		"parentid": '652900'
	},
	{
		"id": '652925',
		"shortname": '新和县',
		"parentid": '652900'
	},
	{
		"id": '652926',
		"shortname": '拜城县',
		"parentid": '652900'
	},
	{
		"id": '652927',
		"shortname": '乌什县',
		"parentid": '652900'
	},
	{
		"id": '652928',
		"shortname": '阿瓦提县',
		"parentid": '652900'
	},
	{
		"id": '652929',
		"shortname": '柯坪县',
		"parentid": '652900'
	},
	{
		"id": '653001',
		"shortname": '阿图什市',
		"parentid": '653000'
	},
	{
		"id": '653022',
		"shortname": '阿克陶县',
		"parentid": '653000'
	},
	{
		"id": '653023',
		"shortname": '阿合奇县',
		"parentid": '653000'
	},
	{
		"id": '653024',
		"shortname": '乌恰县',
		"parentid": '653000'
	},
	{
		"id": '653121',
		"shortname": '疏附县',
		"parentid": '653100'
	},
	{
		"id": '653122',
		"shortname": '疏勒县',
		"parentid": '653100'
	},
	{
		"id": '653123',
		"shortname": '英吉沙县',
		"parentid": '653100'
	},
	{
		"id": '653124',
		"shortname": '泽普县',
		"parentid": '653100'
	},
	{
		"id": '653125',
		"shortname": '莎车县',
		"parentid": '653100'
	},
	{
		"id": '653126',
		"shortname": '叶城县',
		"parentid": '653100'
	},
	{
		"id": '653127',
		"shortname": '麦盖提县',
		"parentid": '653100'
	},
	{
		"id": '653128',
		"shortname": '岳普湖县',
		"parentid": '653100'
	},
	{
		"id": '653129',
		"shortname": '伽师县',
		"parentid": '653100'
	},
	{
		"id": '653130',
		"shortname": '巴楚县',
		"parentid": '653100'
	},
	{
		"id": '653131',
		"shortname": '塔什库尔干塔吉克自治县',
		"parentid": '653100'
	},
	{
		"id": '653101',
		"shortname": '喀什市',
		"parentid": '653100'
	},
	{
		"id": '653201',
		"shortname": '和田市',
		"parentid": '653200'
	},
	{
		"id": '653221',
		"shortname": '和田县',
		"parentid": '653200'
	},
	{
		"id": '653222',
		"shortname": '墨玉县',
		"parentid": '653200'
	},
	{
		"id": '653223',
		"shortname": '皮山县',
		"parentid": '653200'
	},
	{
		"id": '653224',
		"shortname": '洛浦县',
		"parentid": '653200'
	},
	{
		"id": '653225',
		"shortname": '策勒县',
		"parentid": '653200'
	},
	{
		"id": '653226',
		"shortname": '于田县',
		"parentid": '653200'
	},
	{
		"id": '653227',
		"shortname": '民丰县',
		"parentid": '653200'
	},
	{
		"id": '654002',
		"shortname": '伊宁市',
		"parentid": '654000'
	},
	{
		"id": '654003',
		"shortname": '奎屯市',
		"parentid": '654000'
	},
	{
		"id": '654021',
		"shortname": '伊宁县',
		"parentid": '654000'
	},
	{
		"id": '654022',
		"shortname": '察布查尔锡伯自治县',
		"parentid": '654000'
	},
	{
		"id": '654023',
		"shortname": '霍城县',
		"parentid": '654000'
	},
	{
		"id": '654024',
		"shortname": '巩留县',
		"parentid": '654000'
	},
	{
		"id": '654025',
		"shortname": '新源县',
		"parentid": '654000'
	},
	{
		"id": '654026',
		"shortname": '昭苏县',
		"parentid": '654000'
	},
	{
		"id": '654027',
		"shortname": '特克斯县',
		"parentid": '654000'
	},
	{
		"id": '654028',
		"shortname": '尼勒克县',
		"parentid": '654000'
	},
	{
		"id": '654040',
		"shortname": '霍尔果斯特殊经济开发区',
		"parentid": '654000'
	},
	{
		"id": '654201',
		"shortname": '塔城市',
		"parentid": '654200'
	},
	{
		"id": '654202',
		"shortname": '乌苏市',
		"parentid": '654200'
	},
	{
		"id": '654221',
		"shortname": '额敏县',
		"parentid": '654200'
	},
	{
		"id": '654223',
		"shortname": '沙湾县',
		"parentid": '654200'
	},
	{
		"id": '654224',
		"shortname": '托里县',
		"parentid": '654200'
	},
	{
		"id": '654225',
		"shortname": '裕民县',
		"parentid": '654200'
	},
	{
		"id": '654226',
		"shortname": '和布克赛尔蒙古自治县',
		"parentid": '654200'
	},
	{
		"id": '654301',
		"shortname": '阿勒泰市',
		"parentid": '654300'
	},
	{
		"id": '654321',
		"shortname": '布尔津县',
		"parentid": '654300'
	},
	{
		"id": '654322',
		"shortname": '富蕴县',
		"parentid": '654300'
	},
	{
		"id": '654323',
		"shortname": '福海县',
		"parentid": '654300'
	},
	{
		"id": '654324',
		"shortname": '哈巴河县',
		"parentid": '654300'
	},
	{
		"id": '654325',
		"shortname": '青河县',
		"parentid": '654300'
	},
	{
		"id": '654326',
		"shortname": '吉木乃县',
		"parentid": '654300'
	},
	{
		"id": '310104000000',
		"shortname": '徐汇区',
		"parentid": '310000000000'
	},
	{
		"id": '310105000000',
		"shortname": '长宁区',
		"parentid": '310000000000'
	},
	{
		"id": '310106000000',
		"shortname": '静安区',
		"parentid": '310000000000'
	},
	{
		"id": '310107000000',
		"shortname": '普陀区',
		"parentid": '310000000000'
	},
	{
		"id": '310108000000',
		"shortname": '闸北区',
		"parentid": '310000000000'
	},
	{
		"id": '310109000000',
		"shortname": '虹口区',
		"parentid": '310000000000'
	},
	{
		"id": '310110000000',
		"shortname": '杨浦区',
		"parentid": '310000000000'
	},
	{
		"id": '310112000000',
		"shortname": '闵行区',
		"parentid": '310000000000'
	},
	{
		"id": '310113000000',
		"shortname": '宝山区',
		"parentid": '310000000000'
	},
	{
		"id": '310114000000',
		"shortname": '嘉定区',
		"parentid": '310000000000'
	},
	{
		"id": '310115000000',
		"shortname": '浦东新区',
		"parentid": '310000000000'
	},
	{
		"id": '310116000000',
		"shortname": '金山区',
		"parentid": '310000000000'
	},
	{
		"id": '310117000000',
		"shortname": '松江区',
		"parentid": '310000000000'
	},
	{
		"id": '310118000000',
		"shortname": '青浦区',
		"parentid": '310000000000'
	},
	{
		"id": '310120000000',
		"shortname": '奉贤区',
		"parentid": '310000000000'
	},
	{
		"id": '310230000000',
		"shortname": '崇明区',
		"parentid": '310000000000'
	},
	{
		"id": '310101000000',
		"shortname": '黄浦区',
		"parentid": '310000000000'
	}
]