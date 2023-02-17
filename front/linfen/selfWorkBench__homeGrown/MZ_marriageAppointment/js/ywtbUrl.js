var perjsonStr1 = [
	    {
	        "stuffName": "婚姻登记预约",
	        "code": "310150127000",
	        "type":"hydjyy",
	        "img": "social.png",
	    },
];
var perjsonStr4 = [{
	  "stuffName": "结婚登记预约",
	        "code": "310150127000-01",
	        "type":"jhdjyy",
	        "img": "social.png",
	    },
		 {
		"stuffName": "离婚登记预约",
	        "code": "310150127000-03",
	        "type":"lhdjyy",
	        "img": "social.png",
	    }

];
//办证单位地址
var marriageAdressList = [
	{
		key:'000310113',
		val:'宝山婚姻登记管理所'
	},
	{
		key:'000310230',
		val:'崇明区婚姻收养登记中心'
	},
	{
		key:'000310120',
		val:'上海市奉贤区婚姻（收养）登记中心'
	},
	{
		key:'000310109',
		val:'上海市虹口区民政局'
	},
	{
		key:'000310101',
		val:'黄浦区婚姻（收养）登记处'
	},
	{
		key:'000310114',
		val:'上海市嘉定区民政局婚姻登记处'
	},
	{
		key:'000310116',
		val:'金山区婚姻（收养）登记中心'
	},
	{
		key:'000310106',
		val:'上海市静安区婚姻（收养）登记中心'
	},
	{
		key:'000310112',
		val:'闵行区婚姻（收养）登记中心'
	},
	{
		key:'000310241',
		val:'上海市浦东新区婚姻管理所（惠南镇）'
	},
	{
		key:'000310115',
		val:'上海市浦东新区婚姻管理所（浦东南路）'
	},
	{
		key:'000310107',
		val:'上海市普陀区婚姻收养登记中心'
	},
	{
		key:'000310118',
		val:'上海市青浦区婚姻登记管理中心'
	},
	{
		key:'000310117',
		val:'上海市松江区婚姻登记管理所'
	},
	{
		key:'000310104',
		val:'徐汇区婚姻收养登记中心'
	},
	{
		key:'000310110',
		val:'杨浦区婚姻（收养）登记中心'
	},
	{
		key:'000310105',
		val:'上海市长宁区婚姻（收养）登记中心'
	},
	{
		key:'000310999',
		val:'上海市婚姻（收养）登记中心'
	}
];
//国籍列表
var countryList = [
		{"key":"000000008","val":"阿尔巴尼亚"},
		{"key":"000000012","val":"阿尔及利亚"},
		{"key":"000000004","val":"阿富汗"},
		{"key":"000000032","val":"阿根廷"},
		{"key":"000000784","val":"阿拉伯联合酋长国"},
		{"key":"000000886","val":"阿拉伯也门共和国"},
		{"key":"000000512","val":"阿曼"},
		{"key":"000000818","val":"埃及"},
		{"key":"000000230","val":"埃塞俄比亚"},
		{"key":"000000372","val":"爱尔兰"},
		{"key":"000000020","val":"安道尔"},
		{"key":"000000024","val":"安哥拉"},
		{"key":"000000660","val":"安圭拉"},
		{"key":"000000028","val":"安提瓜和巴布达"},
		{"key":"000000040","val":"奥地利"},
		{"key":"000000036","val":"澳大利亚"},
		{"key":"000000052","val":"巴巴多斯"},
		{"key":"000000598","val":"巴布亚新几内亚"},
		{"key":"000000044","val":"巴哈马"},
		{"key":"000000586","val":"巴基斯坦"},
		{"key":"000000600","val":"巴拉圭"},
		{"key":"000000374","val":"巴勒斯坦"},
		{"key":"000000048","val":"巴林"},
		{"key":"000000590","val":"巴拿马"},
		{"key":"000000076","val":"巴西"},
		{"key":"000000112","val":"白俄罗斯"},
		{"key":"000000060","val":"百慕大"},
		{"key":"000000100","val":"保加利亚"},
		{"key":"000000204","val":"贝宁"},
		{"key":"000000056","val":"比利时"},
		{"key":"000000352","val":"冰岛"},
		{"key":"000000068","val":"玻利维亚"},
		{"key":"000000630","val":"波多黎各"},
		{"key":"000000616","val":"波兰"},
		{"key":"000000072","val":"博茨瓦那"},
		{"key":"000000084","val":"伯利茨"},
		{"key":"000000064","val":"不丹"},
		{"key":"000000854","val":"布基那法索"},
		{"key":"000000108","val":"布隆迪"},
		{"key":"000000074","val":"布维岛"},
		{"key":"000000408","val":"朝鲜"},
		{"key":"000000226","val":"赤道几内亚"},
		{"key":"000000208","val":"丹麦"},
		{"key":"000000280","val":"德国"},
		{"key":"000000626","val":"东帝汶"},
		{"key":"000000768","val":"多哥"},
		{"key":"000000212","val":"多米尼加"},
		{"key":"000000214","val":"多米尼加共和国"},
		{"key":"000000810","val":"俄罗斯"},
		{"key":"000000218","val":"厄瓜多尔"},
		{"key":"000000250","val":"法国"},
		{"key":"000000234","val":"法罗"},
		{"key":"000000258","val":"法属波利尼西亚"},
		{"key":"000000254","val":"法属圭亚那"},
		{"key":"000000260","val":"法属南部领土"},
		{"key":"000000608","val":"菲律宾"},
		{"key":"000000246","val":"芬兰"},
		{"key":"000000132","val":"佛得角"},
		{"key":"000000238","val":"福克兰群岛（马尔维纳斯)"},
		{"key":"000000270","val":"冈比亚"},
		{"key":"000000178","val":"刚果"},
		{"key":"000000170","val":"哥伦比亚"},
		{"key":"000000188","val":"哥斯达黎加"},
		{"key":"000000308","val":"格林纳达"},
		{"key":"000000304","val":"格陵兰"},
		{"key":"000000192","val":"古巴"},
		{"key":"000000312","val":"瓜德罗普"},
		{"key":"000000316","val":"关岛"},
		{"key":"000000328","val":"圭亚那"},
		{"key":"000000903","val":"哈萨克斯坦"},
		{"key":"000000332","val":"海地"},
		{"key":"000000410","val":"韩国"},
		{"key":"000000528","val":"荷兰"},
		{"key":"000000532","val":"荷属安的列斯"},
		{"key":"000000334","val":"赫德岛"},
		{"key":"000000340","val":"洪都拉斯"},
		{"key":"000000296","val":"基里巴斯"},
		{"key":"000000262","val":"吉布提"},
		{"key":"000000324","val":"几内亚"},
		{"key":"000000624","val":"几内亚比绍"},
		{"key":"000000124","val":"加拿大"},
		{"key":"000000288","val":"加纳"},
		{"key":"000000266","val":"加蓬"},
		{"key":"000000116","val":"柬埔寨"},
		{"key":"000000200","val":"捷克"},
		{"key":"000000716","val":"津巴布维"},
		{"key":"000000120","val":"喀麦隆"},
		{"key":"000000634","val":"喀塔尔"},
		{"key":"000000136","val":"开曼群岛"},
		{"key":"000000174","val":"科摩罗"},
		{"key":"000000901","val":"科特迪瓦"},
		{"key":"000000414","val":"科威特"},
		{"key":"000000166","val":"可可(基林)群岛"},
		{"key":"000000404","val":"肯尼亚"},
		{"key":"000000184","val":"库克群岛"},
		{"key":"000000426","val":"莱索托"},
		{"key":"000000418","val":"老挝"},
		{"key":"000000422","val":"黎巴嫩"},
		{"key":"000000430","val":"利比里亚"},
		{"key":"000000434","val":"利比亚"},
		{"key":"000000438","val":"列支敦士登"},
		{"key":"000000638","val":"留尼汪"},
		{"key":"000000442","val":"卢森堡"},
		{"key":"000000646","val":"卢旺达"},
		{"key":"000000642","val":"罗马尼亚"},
		{"key":"000000450","val":"马达加斯加"},
		{"key":"000000462","val":"马尔代夫"},
		{"key":"000000470","val":"马耳他"},
		{"key":"000000454","val":"马拉维"},
		{"key":"000000458","val":"马来西亚"},
		{"key":"000000466","val":"马里"},
		{"key":"000000888","val":"马其顿"},
		{"key":"000000474","val":"马提尼克"},
		{"key":"000000480","val":"毛理求斯"},
		{"key":"000000478","val":"毛里塔尼亚"},
		{"key":"000000840","val":"美国"},
		{"key":"000000016","val":"美属萨摩亚"},
		{"key":"000000849","val":"美属太平洋各群岛"},
		{"key":"000000850","val":"美属维尔京群岛"},
		{"key":"000000496","val":"蒙古"},
		{"key":"000000500","val":"蒙特塞拉特"},
		{"key":"000000050","val":"孟加拉国"},
		{"key":"000000604","val":"秘鲁"},
		{"key":"000000154","val":"缅甸"},
		{"key":"000000902","val":"摩尔多瓦"},
		{"key":"000000504","val":"摩洛哥"},
		{"key":"000000492","val":"摩纳哥"},
		{"key":"000000508","val":"莫桑比克"},
		{"key":"000000484","val":"墨西哥"},
		{"key":"000000516","val":"纳米比亚"},
		{"key":"000000710","val":"南非"},
		{"key":"000000010","val":"南极洲"},
		{"key":"000000890","val":"南斯拉夫"},
		{"key":"000000524","val":"尼泊尔"},
		{"key":"000000558","val":"尼加拉瓜"},
		{"key":"000000562","val":"尼日尔"},
		{"key":"000000566","val":"尼日利亚"},
		{"key":"000000570","val":"纽埃"},
		{"key":"000000578","val":"挪威"},
		{"key":"000000574","val":"诺福克岛"},
		{"key":"000000612","val":"皮特凯恩群岛"},
		{"key":"000000620","val":"葡萄牙"},
		{"key":"000000392","val":"日本"},
		{"key":"000000752","val":"瑞典"},
		{"key":"000000756","val":"瑞士"},
		{"key":"000000222","val":"萨尔瓦多"},
		{"key":"000000694","val":"塞拉利昂"},
		{"key":"000000686","val":"塞内加尔"},
		{"key":"000000196","val":"塞浦路斯"},
		{"key":"000000690","val":"塞舌尔"},
		{"key":"000000682","val":"沙特阿拉伯"},
		{"key":"000000162","val":"圣诞岛"},
		{"key":"000000678","val":"圣多美和普林西比"},
		{"key":"000000654","val":"圣赫勒拉"},
		{"key":"000000659","val":"圣克里斯托弗和尼维斯联邦"},
		{"key":"000000671","val":"圣马力诺"},
		{"key":"000000662","val":"圣卢西亚"},
		{"key":"000000666","val":"圣皮埃尔和密克隆"},
		{"key":"000000670","val":"圣文森特和格林纳丁斯"},
		{"key":"000000144","val":"斯里兰卡"},
		{"key":"000000744","val":"斯瓦巴德群岛"},
		{"key":"000000748","val":"斯威士兰"},
		{"key":"000000736","val":"苏丹"},
		{"key":"000000740","val":"苏里兰"},
		{"key":"000000706","val":"索马里"},
		{"key":"000000090","val":"所罗门群岛"},
		{"key":"000000764","val":"泰国"},
		{"key":"000000582","val":"太平洋群岛(托管地)"},
		{"key":"000000834","val":"坦桑尼亚"},
		{"key":"000000776","val":"汤加"},
		{"key":"000000796","val":"特克斯和凯科斯群岛"},
		{"key":"000000780","val":"特立尼达和多巴哥"},
		{"key":"000000788","val":"突尼斯"},
		{"key":"000000798","val":"图瓦卢"},
		{"key":"000000792","val":"土耳其"},
		{"key":"000000772","val":"托克劳"},
		{"key":"000000876","val":"瓦利斯和富图纳群岛"},
		{"key":"000000548","val":"瓦努阿图"},
		{"key":"000000872","val":"威克岛"},
		{"key":"000000320","val":"危地马拉"},
		{"key":"000000862","val":"委内瑞拉"},
		{"key":"000000096","val":"文莱"},
		{"key":"000000800","val":"乌干达"},
		{"key":"000000804","val":"乌克兰"},
		{"key":"000000858","val":"乌拉圭"},
		{"key":"000000999","val":"无国籍"},
		{"key":"000000724","val":"西班牙"},
		{"key":"000000732","val":"西撒哈垃"},
		{"key":"000000882","val":"西萨摩亚"},
		{"key":"000000300","val":"希腊"},
		{"key":"000000384","val":"象牙海岸"},
		{"key":"000000702","val":"新加坡"},
		{"key":"000000540","val":"新喀里多尼亚"},
		{"key":"000000554","val":"新西兰"},
		{"key":"000000348","val":"匈牙利"},
		{"key":"000000760","val":"叙利亚"},
		{"key":"000000388","val":"牙买加"},
		{"key":"000000720","val":"也门民主人民共和国"},
		{"key":"000000368","val":"伊拉克"},
		{"key":"000000364","val":"伊朗"},
		{"key":"000000376","val":"以色列"},
		{"key":"000000380","val":"意大利"},
		{"key":"000000356","val":"印度"},
		{"key":"000000360","val":"印度尼西亚"},
		{"key":"000000826","val":"英国"},
		{"key":"000000092","val":"英属维尔京群岛"},
		{"key":"000000086","val":"英属印度洋领土"},
		{"key":"000000400","val":"约旦"},
		{"key":"000000396","val":"约翰斯群岛"},
		{"key":"000000704","val":"越南"},
		{"key":"000000894","val":"赞比亚"},
		{"key":"000000180","val":"扎伊尔"},
		{"key":"000000148","val":"乍得"},
		{"key":"000000292","val":"直布罗陀"},
		{"key":"000000152","val":"智利"},
		{"key":"000000140","val":"中非"},
		{"key":"000000156","val":"中国"},
		{"key":"000000488","val":"中途岛"},
		{"key":"000000520","val":"瑙鲁"},
		{"key":"000000336","val":"梵帝冈"},
		{"key":"000000242","val":"斐济"},
		{"key":"000000191","val":"克罗地亚"},
		{"key":"000000904","val":"亚美尼亚共和国"},
		{"key":"000000905","val":"波斯尼亚和黑塞哥维那共和国"},
		{"key":"000000998","val":"斯洛伐克"},
		{"key":"000000997","val":"拉脱维亚"},
		{"key":"000000996","val":"土库曼斯坦"},
		{"key":"000000907","val":"刚果（金）"},
		{"key":"000000908","val":"塞尔维亚"},
		{"key":"000000909","val":"黑山"},
		{"key":"000000910","val":"吉尔吉斯斯坦"},
		{"key":"000001000","val":"阿塞拜疆"},
		{"key":"000000583","val":"密克罗尼西亚联邦"},
		{"key":"000000239","val":"南乔治亚岛和南桑德韦奇岛"},
		{"key":"000000807","val":"前南马其顿"},
		{"key":"000000585","val":"帕劳"},
		{"key":"000000705","val":"斯洛文尼亚"},
		{"key":"000000553","val":"阿鲁巴"},
		{"key":"000000268","val":"格鲁吉亚"},
		{"key":"000000232","val":"厄立特里亚"},
		{"key":"000000581","val":"美国本土外小岛屿"},
		{"key":"000000175","val":"马约特"},
		{"key":"000000584","val":"马绍尔群岛"},
		{"key":"000000440","val":"立陶宛"},
		{"key":"000000580","val":"北马里亚纳"},
		{"key":"000000233","val":"爱沙尼亚"},
		{"key":"000000762","val":"塔吉克斯坦"},
		{"key":"000000860","val":"乌兹别克斯坦'"}
];
// //证件类型
var cardList = [
	{"key":"000000001","val":"身份证"},
	{"key":"000000002","val":"军人证"},
	{"key":"000000008","val":"港澳居民往来大陆通行证"},
	{"key":"000000009","val":"台湾居民往来大陆通行证"},
	{"key":"000000003","val":"护照"},
	{"key":"000000004","val":"其他证件"}
]

//办证地址以及补充接口内容
var marriageAdressInfoList = [
    {
        "id": "000310112",
        "name": "闵行区婚姻（收养）登记中心",
        "desc": "<p>婚姻登记机关地址：闵行区七莘路750号</p><p>语音咨询电话：64984950</p><p>人工咨询电话：54530884</p><p>婚姻登记机关交通：91路、196路、189路</p><p>特色服务描述：1、为有需求的结婚登记新人提供“穿盛装、走红地毯、亲朋好友见证”的个性化颁证仪式；2、为有需求的婚姻当事人提供婚姻政策咨询、离婚劝和及调处婚姻家庭财产分割纠纷；3、免费为军人、残疾人及有特殊困难对象拍摄婚姻证件照</p><p>备注：经上级批准，自2016年3月21日起，闵行区婚姻（收养）登记中心对外接待时间作如下调整：结婚登记、补领婚姻登记证：周一下午      1：30-4：30 周二至周六全天8：30-16：30 离婚登记：周一至周五下午1：30-3：30  收养登记：周二、三上午  8：30-11：00</p>",
		"county": "00031011200000",
		"key": "310112000000"
    },
    {
        "id": "000310115",
        "name": "上海市浦东新区婚姻管理所（浦东南路）",
        "desc": "<p>婚姻登记机关地址：上海市浦东新区浦东南路2240号3楼</p><p>语音咨询电话：58012424</p><p>人工咨询电话：58828056</p><p>婚姻登记机关交通：地铁4号线塘桥站3号口、公交：610、581、818、787、82、1011</p><p>特色服务描述：58828056（结婚咨询）、58829805（离婚咨询）</p><p>备注：请您按照预约时间段前来办理，如无法按时前来，请您及时取消预约，以免影响您下次预约。</p>",
		"county": "00031011500000",
		"key": "310115000000"
    },
    {
        "id": "000310117",
        "name": "上海市松江区婚姻登记管理所",
        "desc": "<p>婚姻登记机关地址：上海市松江区乐都西路867-871号2号楼4楼</p><p>语音咨询电话：</p><p>人工咨询电话：</p><p>婚姻登记机关交通：</p><p>特色服务描述：</p><p>备注：</p>",
        "county": "00031011700000",
		"key": "310117000000"
    },
    {
        "id": "000310230",
        "name": "崇明区婚姻收养登记中心",
        "desc": "<p>婚姻登记机关地址：崇明区翠竹路1501号</p><p>语音咨询电话：59611302</p><p>人工咨询电话：39611574</p><p>婚姻登记机关交通：</p><p>特色服务描述：</p><p>备注：</p>",
        "county": "00031023000000",
		"key": "310230000000"
    },
    {
        "id": "000310101",
        "name": "黄浦区婚姻（收养）登记中心",
        "desc": "<p>婚姻登记机关地址：黄浦区江西中路250号</p><p>语音咨询电话：63185355</p><p>人工咨询电话：63777913</p><p>婚姻登记机关交通：公交20路，49路，17路，64路，66路等，轨道交通2号线，10号号线南京东路站</p><p>特色服务描述：</p><p>备注：结婚登记受理时间：周一~周三、周五、周六 上午9：00-11：30，下午13：30-16：30；周四上午9：00-11：30  一月一日、五月一日、十月一日 上午9：00-11：30，下午13：30-16：00离婚登记受理时间：周一~周三上午9：00-11：30，下午13：30-16：30；周四上午9：00-11：30</p>",
        "county": "00031010100000",
		"key": "310101000000"
    },
    {
        "id": "000310105",
        "name": "上海市长宁区婚姻（收养）登记处",
        "desc": "<p>婚姻登记机关地址：上海市长宁区金钟路631弄2号一层</p><p>语音咨询电话：021-62705220</p><p>人工咨询电话：021-62705589</p><p>婚姻登记机关交通：地铁2号线（淞虹路站），公交121 路，141路，825路</p><p>特色服务描述：</p><p>备注：</p>",
        "county": "00031010500000",
		"key": "310105000000"
    },
    {
        "id": "000310106",
        "name": "上海市静安区婚姻（收养）登记中心",
        "desc": "<p>婚姻登记机关地址：静安区秣陵路50号</p><p>语音咨询电话：021-53550041</p><p>人工咨询电话：021-53550041</p><p>婚姻登记机关交通：地铁1、3、4号线上海火车站南广场，隧道三线、104路、13路等</p><p>特色服务描述：</p><p>备注：请您按照预约时间段前来办理，如无法按时前来，请您及时取消预约，以免影响您下次预约。</p>",
        "county": "00031010600000",
		"key": "310106000000"
    },
    {
        "id": "000310107",
        "name": "上海市普陀区婚姻收养登记中心",
        "desc": "<p>婚姻登记机关地址：曹杨路510号（近白玉路）普陀区市民服务中心</p><p>语音咨询电话：52803612</p><p>人工咨询电话：62441118-3002</p><p>婚姻登记机关交通：地铁3、4、11号线曹杨路站5号出口或者公交876、62、63、717、923曹杨路白玉路</p><p>特色服务描述：免费为婚姻当事人提供心理、法律咨询，开设线上线下婚姻学堂、结婚登记颁证等婚姻家庭服务项目。免费开放上海婚姻文化展示馆。</p><p>备注：</p>",
        "county": "00031010700000",
		"key": "310107000000"
    },
    {
        "id": "000310109",
        "name": "上海市虹口区民政局",
        "desc": "<p>婚姻登记机关地址：巴林路76号</p><p>语音咨询电话：55127329</p><p>人工咨询电话：55127328</p><p>婚姻登记机关交通：123路、167路、933路</p><p>特色服务描述：周一至周六上午、下午婚姻登记对外接待(周五下午除外，国定节假日除外）</p><p>备注：疫情防控期间，本场所将实行人员限流。凡进入本场所人员需出示随身码、佩戴口罩、测量体温、实名登记，敬请谅解！</p>",
        "county": "00031010900000",
		"key": "310109000000"
    },
    {
        "id": "000310113",
        "name": "宝山婚姻登记管理所",
        "desc": "<p>婚姻登记机关地址：上海市宝山区樟岭路2号南楼</p><p>语音咨询电话：</p><p>人工咨询电话：56699902</p><p>婚姻登记机关交通：3号线（友谊路站）,宝山2路，8路，10路，15路，23路</p><p>特色服务描述：</p><p>备注：友情提醒：防疫期间，进入公共区域请佩戴口罩测量体温并出示随申码绿码（请提前下载app并注册认证），未佩戴口罩或体温超过37.3℃或随申码非绿码禁止入内。</p>",
        "county": "00031011300000",
		"key": "310113000000"
    },
    {
        "id": "000310114",
        "name": "上海市嘉定区民政局婚姻登记处",
        "desc": "<p>婚姻登记机关地址：上海市嘉定区澄浏中路2560号</p><p>语音咨询电话：59528196</p><p>人工咨询电话：59528242</p><p>婚姻登记机关交通：公交嘉定11路、公交嘉定3路、公交嘉泰线</p><p>特色服务描述：为弘扬优秀传统文化，宣传积极的婚姻理念，区婚登处每年举办“礼乐嘉定 让爱丰盈”特色主题颁证活动，欢迎新人关注“嘉定婚姻”微信公众号。也欢迎新人自编自创结婚誓言，内容健康向上、表达心声，经过婚姻登记员的审核，即可在颁证仪式上宣读。 *友情提醒：新人们！为使你们的结婚登记颁证宣誓仪式举行得庄严、神圣、隆重，敬请准备参加颁证仪式的新人可以身着喜庆的正装出席，谢谢！（可备好相机，记录下你们甜蜜感动的一刻）</p><p>备注：接待时间：结婚登记：除国定节假日外，每周一至周四、周六（上午：8：30—11：00；下午：13：30—16：00），周五（上午：8：30—11：00）；补领结婚证时间与结婚登记同步。离婚登记：除国定节假日外，每周一至周四（上午：8：30—11：00；下午：13：30—16：00），周五（上午：8：30—11：00）。</p>",
        "county": "00031011400000",
		"key": "310114000000"
    },
    {
        "id": "000310116",
        "name": "金山区婚姻（收养）登记中心",
        "desc": "<p>婚姻登记机关地址：金山区朱泾镇临源街620号</p><p>语音咨询电话：</p><p>人工咨询电话：57319540</p><p>婚姻登记机关交通：</p><p>特色服务描述：</p><p>备注：</p>",
        "county": "00031011600000",
		"key": "310116000000"
    },
    {
        "id": "000310118",
        "name": "上海市青浦区婚姻登记管理中心",
        "desc": "<p>婚姻登记机关地址：青浦区外青松公路6189号行政服务中心底楼婚姻登记处</p><p>语音咨询电话：69714231</p><p>人工咨询电话：69714230</p><p>婚姻登记机关交通：地铁17号线，公交沪青高速、沪青盈、5路</p><p>特色服务描述：结婚登记简易颁证仪式、“暖心阁”婚姻家庭辅导服务</p><p>备注：结婚登记咨询电话：69714230，离婚登记咨询电话：69713671</p>",
        "county": "00031011800000",
		"key": "310118000000"
    },
    {
        "id": "000310999",
        "name": "上海市婚姻（收养）登记中心",
        "desc": "<p>婚姻登记机关地址：上海市漕宝路80号3楼</p><p>语音咨询电话：64325087</p><p>人工咨询电话：64325088</p><p>婚姻登记机关交通：地铁1号线、12号线 漕宝路站</p><p>特色服务描述：提高婚姻家庭幸福力咨询服务、法律咨询服务。</p><p>备注：您如有需要可在预约日前携带规定证件、证明及相关材料来我中心,我们将帮助查看,以便您能在预约日顺利登记。</p>",
        "county": "00031099900000",
		"key": "310104000000"
    },
    {
        "id": "000310241",
        "name": "上海市浦东新区婚姻管理所（惠南镇）",
        "desc": "<p>婚姻登记机关地址：上海市浦东新区惠南镇城基路29号</p><p>语音咨询电话：58012424</p><p>人工咨询电话：68018414</p><p>婚姻登记机关交通：</p><p>特色服务描述：</p><p>备注：请您按照预约时间段前来办理，如无法按时前来，请您及时取消预约，以免影响您下次预约。</p>",
        "county": "00031011500000",
		"key": "310115000000"
    },
    {
        "id": "000310104",
        "name": "上海市徐汇区婚姻收养登记中心",
        "desc": "<p>婚姻登记机关地址：南宁路999号2号楼3楼</p><p>语音咨询电话：021-64325542</p><p>人工咨询电话：021-64325543</p><p>婚姻登记机关交通：地铁1号线、12号线、公交1204B</p><p>特色服务描述：中心以“亮丽、优质、高效、廉洁”的窗口形象为标准，统一着装，佩戴工号，亮台牌上岗，要求登记员始终保持良好的工作热情和耐心细致的工作作风，微笑服务，遵守岗位职责，责任到人，认真按照婚姻登记规范化建设标准接待每位当事人，并提供规范专业的服务。在工作中实行“首问责任制”，开展“优质服务、优秀素质、优良作风、优异成绩、优美环境”的“五优”和工作“一手清”活动。</p><p>备注：</p>",
        "county": "00031010400000",
		"key": "310104000000"
    },
    {
        "id": "000310120",
        "name": "上海市奉贤区婚姻（收养）登记中心",
        "desc": "<p>婚姻登记机关地址：上海市奉贤区南桥镇望园南路1529弄行政服务中心B座三楼</p><p>语音咨询电话：</p><p>人工咨询电话：57420620</p><p>婚姻登记机关交通：</p><p>特色服务描述：</p><p>备注：</p>",
        "county": "00031012000000",
		"key": "310120000000"
    },
    {
        "id": "000310110",
        "name": "杨浦区婚姻（收养）登记中心",
        "desc": "<p>婚姻登记机关地址：上海市济宁路252号（近江浦路）</p><p>语音咨询电话：96987520</p><p>人工咨询电话：25017777</p><p>婚姻登记机关交通：70路 843路</p><p>特色服务描述：“玫瑰颁证厅”仅对网上预约成功的部分新人免费开放。疫情期间，为了安全，玫瑰厅预约颁证服务暂时停止。如果已经预约成功的当事人，请致电25017777联系工作人员。</p><p>备注：1.3月25日起开放简易颁证服务，请致电25017777进行提前预约。2.疫情防控期间，请佩戴口罩，配合机构防控工作。3.离婚登记申请材料：户口簿、居民身份证、结婚证、离婚协议中涉及房产和车辆的请带好房产证原件和车辆登记证原件、本人近期正面免冠二寸彩照两张。敬请关注“杨浦区婚姻收养登记中心”微信公众号。</p>",
        "county": "00031011000000",
		"key": "310110000000"
    }
]

//测试预约日期列表
var appointmentDateList = {
    "date": [
        "2020-06-23",
        "2020-06-24",
        "2020-06-28",
        "2020-06-29",
        "2020-06-30",
        "2020-07-01",
        "2020-07-02",
        "2020-07-03",
        "2020-07-04",
        "2020-07-06",
        "2020-07-07",
        "2020-07-08",
        "2020-07-09",
        "2020-07-10",
        "2020-07-11",
        "2020-07-13",
        "2020-07-14",
        "2020-07-15",
        "2020-07-16",
        "2020-07-17",
        "2020-07-18",
        "2020-07-20",
        "2020-07-21",
        "2020-07-22"
    ],
    "fullDayList": [],
    "offDayList": [
        "2020-06-25",
        "2020-06-26",
        "2020-06-27",
        "2020-07-05",
        "2020-07-12",
        "2020-07-19"
    ]
}

//测试预约日期时间段以及可选席位
var appointmentTimeList = {
    "result": [
        {
            "num": "0",
            "dateTime": "08:00-08:30",
            "seatNum": "0",
            "dateTimeId": "00",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "08:30-09:00",
            "seatNum": "0",
            "dateTimeId": "01",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "09:00-09:30",
            "seatNum": "10",
            "dateTimeId": "02",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "09:30-10:00",
            "seatNum": "15",
            "dateTimeId": "03",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "10:00-10:30",
            "seatNum": "15",
            "dateTimeId": "04",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "10:30-11:00",
            "seatNum": "15",
            "dateTimeId": "05",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "11:00-11:30",
            "seatNum": "0",
            "dateTimeId": "06",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "11:30-12:00",
            "seatNum": "0",
            "dateTimeId": "07",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "12:00-12:30",
            "seatNum": "0",
            "dateTimeId": "08",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "12:30-13:00",
            "seatNum": "0",
            "dateTimeId": "09",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "13:00-13:30",
            "seatNum": "0",
            "dateTimeId": "10",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "13:30-14:00",
            "seatNum": "15",
            "dateTimeId": "11",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "14:00-14:30",
            "seatNum": "15",
            "dateTimeId": "12",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "14:30-15:00",
            "seatNum": "15",
            "dateTimeId": "13",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "15:00-15:30",
            "seatNum": "0",
            "dateTimeId": "14",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "15:30-16:00",
            "seatNum": "0",
            "dateTimeId": "15",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "16:00-16:30",
            "seatNum": "0",
            "dateTimeId": "16",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "16:30-17:00",
            "seatNum": "0",
            "dateTimeId": "17",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "17:00-17:30",
            "seatNum": "0",
            "dateTimeId": "18",
            "selectDate": "2020-06-24"
        },
        {
            "num": "0",
            "dateTime": "17:30-18:00",
            "seatNum": "0",
            "dateTimeId": "19",
            "selectDate": "2020-06-24"
        }
    ]
}
