var areaList = [ {
	"areaCode": "01",
	"areaName": "全部"
},{
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
}]
var animalCert= [
	{
	 sName: "上海顽皮家族宠物有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区虹桥路2381号38号楼", 
	 issue_date: "2018-12-28", 
	 end_date: "2021-12-31", 
	 licence_code: "沪市动诊证2018-016"
	}, 
	{
	 sName: "上海贝康宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "长宁区安顺路356-1号", 
	 issue_date: "2019-01-29", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第005号"
	}, 
	{
	 sName: "益格（上海）宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区威宁路500号", 
	 issue_date: "2019-02-20", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第008号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司徐汇分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "辛耕路77号", 
	 issue_date: "2018-12-28", 
	 end_date: "2021-12-31", 
	 licence_code: "沪市动诊证2018-020"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司茶陵路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "茶陵路222号", 
	 issue_date: "2018-12-28", 
	 end_date: "2021-12-31", 
	 licence_code: "沪市动诊证2018-019"
	}, 
	{
	 sName: "上海交农动物医疗有限公司静安分公司", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市常德路1022、1026号", 
	 issue_date: "2018-07-31", 
	 end_date: "2021-06-30", 
	 licence_code: "沪动诊证（2018）第08号"
	}, 
	{
	 sName: "上海宝康宠物诊所", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市杨浦区政立路167号", 
	 issue_date: "2018-07-01", 
	 end_date: "2021-06-30", 
	 licence_code: "沪动诊证2018第02号"
	}, 
	{
	 sName: "上海芭芭拉宠物有限公司", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "黄浦区中华路1150号", 
	 issue_date: "2018-08-20", 
	 end_date: "2021-06-30", 
	 licence_code: "沪市动诊证2018-009"
	}, 
	{
	 sName: "上海岛戈宠物有限公司", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市田东路392号", 
	 issue_date: "2018-07-31", 
	 end_date: "2021-06-30", 
	 licence_code: "沪动诊证（2018）第07号"
	}, 
	{
	 sName: "上海心安宠物诊疗有限公司", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市杨浦区周家嘴路2410号一层", 
	 issue_date: "2018-07-11", 
	 end_date: "2021-06-30", 
	 licence_code: "沪动诊证（2018）第04号"
	}, 
	{
	 sName: "上海安安东巨宠物诊所有限公司", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市杨浦区政立路1551、1553号", 
	 issue_date: "2018-10-08", 
	 end_date: "2021-10-31", 
	 licence_code: "沪市动诊证2018-011"
	}, 
	{
	 sName: "上海顺心宠物诊疗有限公司东汉阳路店", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市虹口区东汉阳路281号101室-2", 
	 issue_date: "2018-10-08", 
	 end_date: "2021-10-31", 
	 licence_code: "沪市动诊证2018-010"
	}, 
	{
	 sName: "上海凌睿宠物有限公司", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市长宁区虹古路118号-30", 
	 issue_date: "2018-11-19", 
	 end_date: "2021-04-30", 
	 licence_code: "沪市动诊证2018-014"
	}, 
	{
	 sName: "上海茜茜宠物医院", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市虹口区新同心路86号、90号底层", 
	 issue_date: "2018-10-31", 
	 end_date: "2021-10-31", 
	 licence_code: "沪市动诊证2018-012"
	}, 
	{
	 sName: "上海地利宠物诊所有限公司 ", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市徐汇区中山南二路1001号-3临", 
	 issue_date: "2018-07-10", 
	 end_date: "2020-10-19", 
	 licence_code: "沪动诊证（2018）第06号"
	}, 
	{
	 sName: "上海申丰畜牧兽医科技有限公司动物门诊部", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "斜土路2451号", 
	 issue_date: "2018-11-11", 
	 end_date: "2021-11-30", 
	 licence_code: "沪市动诊证2018-013"
	}, 
	{
	 sName: "上海天使宠物医院有限公司", 
	 sArea: "动物诊疗", 
	 sBusinessAddr: "上海市杨浦区中原路172号", 
	 issue_date: "2018-11-29", 
	 end_date: "2021-11-30", 
	 licence_code: "沪市动诊证2018-015"
	}, 
	{
	 sName: "上海申普兽医技术有限公司闸北宠物诊疗所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区共和新路4445号101室", 
	 issue_date: "2019-01-29", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第003号"
	}, 
	{
	 sName: "上海安琴动物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区凯旋路1265号6幢底层", 
	 issue_date: "2019-03-25", 
	 end_date: "2022-03-31", 
	 licence_code: "沪动诊证（2019）第012号"
	}, 
	{
	 sName: "上海安安易谦宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "同丰路622号、624号", 
	 issue_date: "2018-12-28", 
	 end_date: "2021-12-31", 
	 licence_code: "沪市动诊证2018-017"
	}, 
	{
	 sName: "上海天使宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区洛川中路612号", 
	 issue_date: "2018-12-28", 
	 end_date: "2021-12-31", 
	 licence_code: "沪市动诊证2018-021"
	}, 
	{
	 sName: "上海仁和宠物保健有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "杨浦区隆昌路669号", 
	 issue_date: "2019-01-22", 
	 end_date: "2022-01-31", 
	 licence_code: "沪动诊证（2019）第001号"
	}, 
	{
	 sName: "上海心愿宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "黄浦区瞿溪路906号", 
	 issue_date: "2019-01-29", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第006号"
	}, 
	{
	 sName: "上海申普兽医技术有限公司申普宠物医院", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "黄浦区徐家汇路565号", 
	 issue_date: "2019-01-29", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第004号"
	}, 
	{
	 sName: "上海双仪宠物保健有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市密云路528弄542号乙", 
	 issue_date: "2019-01-31", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第007号"
	}, 
	{
	 sName: "上海艺菲宠物诊所有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市静安区共和新路478号", 
	 issue_date: "2019-02-27", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第010号"
	}, 
	{
	 sName: "上海依嘉宠物诊所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市杨浦区辽源东路15号", 
	 issue_date: "2019-04-15", 
	 end_date: "2022-03-31", 
	 licence_code: "沪动诊证（2019）第016号"
	}, 
	{
	 sName: "上海市虹口区爱轩宠物诊所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市虹口区中山北一路22号", 
	 issue_date: "2019-04-15", 
	 end_date: "2021-01-11", 
	 licence_code: "沪动诊证（2019）第015号"
	}, 
	{
	 sName: "上海小精灵宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市黄浦区武胜路449-453号、457号后部", 
	 issue_date: "2019-04-28", 
	 end_date: "2022-03-31", 
	 licence_code: "沪动诊证（2019）第019号"
	}, 
	{
	 sName: "上海贝肯菲宠物诊所有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市", 
	 issue_date: "2019-02-28", 
	 end_date: "2022-02-28", 
	 licence_code: "沪动诊证（2019）第009号"
	}, 
	{
	 sName: "艾贝尔（上海）宠物医院管理有限公司汾西路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市静安区汾西路41号1-2层", 
	 issue_date: "2019-03-20", 
	 end_date: "2021-05-21", 
	 licence_code: "沪动诊证（2019）第011号"
	}, 
	{
	 sName: "上海笛垄宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市虹口区水电路1079号", 
	 issue_date: "2019-05-16", 
	 end_date: "2021-11-02", 
	 licence_code: "沪动诊证（2019）第020号"
	}, 
	{
	 sName: "上海佩兹动物医疗有限公司黄浦分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市黄浦区东青莲街99号1层", 
	 issue_date: "2019-05-20", 
	 end_date: "2022-05-20", 
	 licence_code: "沪动诊证（2019）第021号"
	}, 
	{
	 sName: "上海市虹口区慧心澜宠物医院", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市虹口区宝山路639、641、643、645、647号101室", 
	 issue_date: "2019-05-20", 
	 end_date: "2022-04-27", 
	 licence_code: "沪动诊证（2019）第022号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司黄兴路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市杨浦区黄兴路1625号301-319室", 
	 issue_date: "2019-05-20", 
	 end_date: "2022-06-02", 
	 licence_code: "沪动诊证（2019）第023号"
	}, 
	{
	 sName: "上海菲丽丝宠物诊所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市虹口区曲阳路102号丙、乙室底层", 
	 issue_date: "2019-04-28", 
	 end_date: "2022-03-31", 
	 licence_code: "沪动诊证（2019）第017号"
	}, 
	{
	 sName: "上海天使宠物医院有限公司周家嘴路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市杨浦区周家嘴路1915-1921号", 
	 issue_date: "2019-04-28", 
	 end_date: "2021-03-07", 
	 licence_code: "沪动诊证（2019）第018号"
	}, 
	{
	 sName: "上海晶明宠物有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区梅川路1275号1幢底层B35室", 
	 issue_date: "2019-03-28", 
	 end_date: "2021-12-31", 
	 licence_code: "沪动诊证（2019）第014号"
	}, 
	{
	 sName: "上海医达宠物诊所（普通合伙）", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市徐汇区中山南二路1011号一层", 
	 issue_date: "2019-03-27", 
	 end_date: "2021-12-31", 
	 licence_code: "沪动诊证（2019）第013号"
	}, 
	{
	 sName: "艾贝尔（上海）宠物医院管理有限公司周家嘴路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市杨浦区周家嘴路3102-3106号底层", 
	 issue_date: "2019-06-10", 
	 end_date: "2021-05-01", 
	 licence_code: "沪动诊证（2019）第025号"
	}, 
	{
	 sName: "上海芭比堂宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市徐汇区上中路285号-3", 
	 issue_date: "2019-06-10", 
	 end_date: "2022-05-13", 
	 licence_code: "沪动诊证（2019）第024号"
	}, 
	{
	 sName: "上海市闸北区彭浦新村街道爱其宠物诊所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市静安区保德路1266号", 
	 issue_date: "2019-08-09", 
	 end_date: "2022-08-08", 
	 licence_code: "沪动诊证（2019）第027号"
	}, 
	{
	 sName: "上海琦凡宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区永和路1173号1-2层", 
	 issue_date: "2019-09-16", 
	 end_date: "2022-09-15", 
	 licence_code: "沪动诊证（2019）第030号"
	}, 
	{
	 sName: "上海派迪宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区运城路311号101、201室，313号101、201室", 
	 issue_date: "2019-08-23", 
	 end_date: "2022-08-22", 
	 licence_code: "沪动诊证（2019）第028号"
	}, 
	{
	 sName: "上海霍夫动物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区凯旋北路1768-1号", 
	 issue_date: "2019-08-26", 
	 end_date: "2022-09-14", 
	 licence_code: "沪动诊证（2019）第 029号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司四平路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "虹口区四平路762号101室", 
	 issue_date: "2017-10-20", 
	 end_date: "2020-10-19", 
	 licence_code: "沪动诊证（2017）第08号"
	}, 
	{
	 sName: "上海青藤宠物诊所有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区昌化路567号", 
	 issue_date: "2018-07-10", 
	 end_date: "2021-06-30", 
	 licence_code: "沪动诊证（2018）第05号"
	}, 
	{
	 sName: "上海仁和浩华宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "虹口区车站南路182号", 
	 issue_date: "2018-12-28", 
	 end_date: "2021-12-31", 
	 licence_code: "沪动诊证（2018）第018号"
	}, 
	{
	 sName: "上海关爱宠物诊所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "虹口区岳州路377号", 
	 issue_date: "2019-09-24", 
	 end_date: "2022-09-23", 
	 licence_code: "沪动诊证（2019）第031号"
	}, 
	{
	 sName: "格雷思（上海）宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "徐汇区斜土路1298号、1308号", 
	 issue_date: "2019-10-24", 
	 end_date: "2022-11-27", 
	 licence_code: "沪动诊证（2019）第032号"
	}, 
	{
	 sName: "上海爱趣哆趣宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区鸿兴路38号1-2层", 
	 issue_date: "2019-10-24", 
	 end_date: "2022-10-25", 
	 licence_code: "沪动诊证（2019）第033号"
	}, 
	{
	 sName: "派菲尔德（上海）宠物有限公司普陀分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "普陀区兰溪路900弄15号108室", 
	 issue_date: "2019-10-24", 
	 end_date: "2022-10-31", 
	 licence_code: "沪动诊证（2019）第034号"
	}, 
	{
	 sName: "信科动物医院（上海）有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "黄浦区斜土东路70号、76号、80号", 
	 issue_date: "2019-10-29", 
	 end_date: "2022-11-15", 
	 licence_code: "沪动诊证（2019）第035号"
	}, 
	{
	 sName: "上海爱恒宠物有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区中山北路808-10号", 
	 issue_date: "2019-11-09", 
	 end_date: "2022-11-10", 
	 licence_code: "沪动诊证（2019）第036号"
	}, 
	{
	 sName: "上海爱侣宠物有限公司爱侣宠物医院", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "普陀区灵石路1539号F1-F14", 
	 issue_date: "2019-11-20", 
	 end_date: "2022-11-19", 
	 licence_code: "沪动诊证（2019）第037号"
	}, 
	{
	 sName: "上海睿诣花城宠物诊所有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "普陀区石泉东路120号1、2层", 
	 issue_date: "2019-12-03", 
	 end_date: "2022-12-02", 
	 licence_code: "沪动诊证（2019）第039号"
	}, 
	{
	 sName: "上海宠爱晴安宠物医院有限公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "静安区昌化路48号1-2层", 
	 issue_date: "2019-12-12", 
	 end_date: "2020-12-11", 
	 licence_code: "沪动诊证（2019）第L32号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司黄浦分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "黄浦区蒙自路610号", 
	 issue_date: "2019-12-12", 
	 end_date: "2022-12-27", 
	 licence_code: "沪动诊证（2019）第040号"
	}, 
	{
	 sName: "上海市静安区蓝石宠物医院", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "静安区天目中路267号201室", 
	 issue_date: "2018-07-06", 
	 end_date: "2021-06-30", 
	 licence_code: "沪动诊证（2018）第03号"
	}, 
	{
	 sName: "艾贝尔（上海）宠物医院管理有限公司哈密路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区哈密路440号201室", 
	 issue_date: "2020-08-03", 
	 end_date: "2023-07-06", 
	 licence_code: "沪动诊证（2020）第025号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司政芳路分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市杨浦区政芳路72号1-2层", 
	 issue_date: "2020-02-28", 
	 end_date: "2021-02-27", 
	 licence_code: "沪动诊证（2020）第011号"
	}, 
	{
	 sName: "上海宁安宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区长宁路1802号底层", 
	 issue_date: "2020-03-16", 
	 end_date: "2021-03-15", 
	 licence_code: "沪动诊证（2020）第015号"
	}, 
	{
	 sName: "上海隽达宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市杨浦区淞沪路937号一层", 
	 issue_date: "2020-04-23", 
	 end_date: "2023-04-22", 
	 licence_code: "沪动诊证（2020）第018号"
	}, 
	{
	 sName: "上海市徐汇区缘来宠物诊所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市徐汇区沪闵路9214号9214-02室", 
	 issue_date: "2020-05-28", 
	 end_date: "2023-07-10", 
	 licence_code: "沪动诊证（2020）第022号"
	}, 
	{
	 sName: "上海鼎源宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区金鼎路396号一层", 
	 issue_date: "2020-01-07", 
	 end_date: "2023-01-16", 
	 licence_code: "沪动诊证（2020）第003号"
	}, 
	{
	 sName: "上海呱呱宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市虹口区甘河路8号102室", 
	 issue_date: "2020-05-11", 
	 end_date: "2023-05-19", 
	 licence_code: "沪动诊证（2020）第021号"
	}, 
	{
	 sName: "上海启益宠物服务有限公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市长宁区新泾镇广顺路78号101室", 
	 issue_date: "2020-02-28", 
	 end_date: "2021-02-27", 
	 licence_code: "沪动诊证（2020）第010号"
	}, 
	{
	 sName: "上海关爱宠物诊所虹口区海拉尔路分所", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市虹口区海拉尔路182号2层、186号1-2层", 
	 issue_date: "2020-06-12", 
	 end_date: "2023-07-10", 
	 licence_code: "沪动诊证（2020）第023号"
	}, 
	{
	 sName: "上海动物保健有限公司长宁分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市长宁区虹井路855弄30号2幢101室", 
	 issue_date: "2020-07-14", 
	 end_date: "2021-07-24", 
	 licence_code: "沪动诊证（2020）第030号"
	}, 
	{
	 sName: "上海动物保健有限公司杨浦分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市杨浦区抚顺路206号", 
	 issue_date: "2020-07-20", 
	 end_date: "2021-07-30", 
	 licence_code: "沪动诊证（2020）第031号"
	}, 
	{
	 sName: "上海湃皓思宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区光复西路331号1-2层", 
	 issue_date: "2020-07-07", 
	 end_date: "2023-07-06", 
	 licence_code: "沪动诊证（2020）第026号"
	}, 
	{
	 sName: "上海申普兽医技术有限公司普陀宠物医院", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区大渡河路1501号213-1至215室", 
	 issue_date: "2020-02-03", 
	 end_date: "2023-02-28", 
	 licence_code: "沪动诊证（2020）第006号"
	}, 
	{
	 sName: "上海香榭园宠物有限公司长宁店", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区天山路185号", 
	 issue_date: "2020-02-03", 
	 end_date: "2023-02-28", 
	 licence_code: "沪动诊证（2020）第007号"
	}, 
	{
	 sName: "上海铭华宠物医院有限公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市普陀区金沙江路1999号102室", 
	 issue_date: "2020-02-03", 
	 end_date: "2021-02-02", 
	 licence_code: "沪动诊证（2020）第008号"
	}, 
	{
	 sName: "上海斯嘉宠物诊所有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区中山北路3219号、3221号一层", 
	 issue_date: "2020-02-03", 
	 end_date: "2023-01-31", 
	 licence_code: "沪动诊证（2020）第009号"
	}, 
	{
	 sName: "上海朋朋宠物有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区法华镇路520号", 
	 issue_date: "2020-03-27", 
	 end_date: "2023-03-26", 
	 licence_code: "沪动诊证（2020）第016号"
	}, 
	{
	 sName: "上海景源宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区广泉路236号", 
	 issue_date: "2020-01-03", 
	 end_date: "2023-01-02", 
	 licence_code: "沪动诊证（2020）第001号"
	}, 
	{
	 sName: "上海安安宠物有限公司新村路分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市普陀区新村路609号", 
	 issue_date: "2020-01-03", 
	 end_date: "2021-01-02", 
	 licence_code: "沪动诊证（2020）第002号"
	}, 
	{
	 sName: "上海安安宠物有限公司澳门路分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市普陀区澳门路768号裙房103、201室", 
	 issue_date: "2020-01-10", 
	 end_date: "2021-01-09", 
	 licence_code: "沪动诊证（2020）第004号"
	}, 
	{
	 sName: "上海盛泰宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市杨浦区营口路823号底楼", 
	 issue_date: "2020-01-13", 
	 end_date: "2023-02-28", 
	 licence_code: "沪动诊证（2020）第005号"
	}, 
	{
	 sName: "上海动物保健有限公司普陀分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市普陀区水泉路35、37、47、51、55号", 
	 issue_date: "2020-04-10", 
	 end_date: "2021-04-09", 
	 licence_code: "沪动诊证（2020）第017号"
	}, 
	{
	 sName: "上海爱侣宠物有限公司分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市普陀区梅川路805-807号", 
	 issue_date: "2020-04-23", 
	 end_date: "2021-04-22", 
	 licence_code: "沪动诊证（2020）第019号"
	}, 
	{
	 sName: "上海西西宠物诊疗有限公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市长宁区古北路1325号1-2层", 
	 issue_date: "2020-04-23", 
	 end_date: "2020-12-11", 
	 licence_code: "沪动诊证（2019）第L31号"
	}, 
	{
	 sName: "上海动物保健有限公司闸北分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市静安区大宁路723-727号", 
	 issue_date: "2020-07-20", 
	 end_date: "2021-07-30", 
	 licence_code: "沪动诊证（2020）第032号"
	}, 
	{
	 sName: "上海悦乐宠物诊疗有限公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市杨浦区临青路430号12幢、15幢", 
	 issue_date: "2020-06-12", 
	 end_date: "2021-06-11", 
	 licence_code: "沪动诊证（2020）第024号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司殷行路分公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市杨浦区殷行路855、857号一层", 
	 issue_date: "2019-12-25", 
	 end_date: "2020-12-24", 
	 licence_code: "沪动诊证（2019）第L33号"
	}, 
	{
	 sName: "上海优倍津申宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市普陀区富平路900号、902号1-2层", 
	 issue_date: "2020-07-07", 
	 end_date: "2023-07-30", 
	 licence_code: "沪动诊证（2020）第028号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司新闸路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市黄浦区新闸路435、437号1-2层", 
	 issue_date: "2020-07-07", 
	 end_date: "2023-07-18", 
	 licence_code: "沪动诊证（2020）第029号"
	}, 
	{
	 sName: "上海悠奈动物医疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市徐汇区华济路87、89、91号1-2层", 
	 issue_date: "2020-04-30", 
	 end_date: "2023-05-15", 
	 licence_code: "沪动诊证（2020）第020号"
	}, 
	{
	 sName: "上海有游宠物诊所有限公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市徐汇区漕宝路51号109室", 
	 issue_date: "2020-07-07", 
	 end_date: "2023-07-10", 
	 licence_code: "沪动诊证（2020）第027号"
	}, 
	{
	 sName: "上海慧慧宠物诊疗有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市杨浦区松花江路313-1号1层", 
	 issue_date: "2020-03-12", 
	 end_date: "2023-03-11", 
	 licence_code: "沪动诊证（2020）第012号"
	}, 
	{
	 sName: "上海领华宠物医院有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市徐汇区田林路132号-1A、-1B", 
	 issue_date: "2020-03-12", 
	 end_date: "2023-04-02", 
	 licence_code: "沪动诊证（2020）第013号"
	}, 
	{
	 sName: "上海地利宠物诊所有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市徐汇区中山南二路1001号-3临", 
	 issue_date: "2020-09-08", 
	 end_date: "2023-10-19", 
	 licence_code: "沪动诊证（2020）第034号"
	}, 
	{
	 sName: "上海瑞鹏宠物医院有限公司宜川路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市静安区宜川路712、718号201-1室", 
	 issue_date: "2018-05-21", 
	 end_date: "2021-05-20", 
	 licence_code: "沪动诊证（2018）第01号"
	}, 
	{
	 sName: "上海顽皮家族宠物有限公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市长宁区虹桥路2381号38号楼", 
	 issue_date: "2020-08-18", 
	 end_date: "2021-12-31", 
	 licence_code: "沪动诊证（2018）第016号"
	}, 
	{
	 sName: "艾贝尔（上海）宠物医院管理有限公司晶华宠物医院管理分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市静安区陕西北路1177号、1179号", 
	 issue_date: "2020-09-02", 
	 end_date: "2023-03-31", 
	 licence_code: "沪动诊证（2020）第014号"
	}, 
	{
	 sName: "上海满满爱心宠物诊所有限公司", 
	 sArea: "宠物诊疗（未获许可不得使用放射性同位素与射线装置）", 
	 sBusinessAddr: "上海市杨浦区国和路888弄69号102、201室", 
	 issue_date: "2020-09-02", 
	 end_date: "2021-09-01", 
	 licence_code: "沪动诊证（2020）第033号"
	}, 
	{
	 sName: "艾贝尔（上海）宠物医院管理有限公司欧阳路分公司", 
	 sArea: "宠物诊疗", 
	 sBusinessAddr: "上海市虹口区欧阳路78-80号底层", 
	 issue_date: "2020-09-03", 
	 end_date: "2022-12-31", 
	 licence_code: "沪动诊证（2019）第038号"
	}
   ]
