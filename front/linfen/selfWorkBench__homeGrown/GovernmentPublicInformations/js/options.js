//字典项
let ascceptanceArea = [{
	"id": "1",
	"name": "上海市"
}, {
	"id": "2",
	"name": "浙江省"
}, {
	"id": "3",
	"name": "江苏省"
}, {
	"id": "4",
	"name": "安徽省"
}]

let secondMenu = [{
		"id": "1",
		"name": "政策文件"
	},
	{
		"id": "2",
		"name": "法定主动公开内容"
	},
	{
		"id": "3",
		"name": "政府信息公开制度"
	},
	{
		"id": "4",
		"name": "政府信息公开年报"
	}, {
		"id": "4",
		"name": "市政府公报"
	}
]

let secondMenuFirstChild = [{
	"id": "0001-2407",
	"name": "沪府令"
}, {
	"id": "0001-11407",
	"name": "沪府发文件"
}, {
	"id": "0001-11408",
	"name": "沪府办发文件"
}, {
	"id": "0001-39220",
	"name": "沪府文件"
}, {
	"id": "0001-39221",
	"name": "沪府办文件"
}, {
	"id": "0001-42944",
	"name": "沪府规文件"
}, {
	"id": "0001-42850",
	"name": "沪府办规文件"
}, {
	"id": "0001-44931",
	"name": "沪府任文件"
}, {
	"id": "0001-44010",
	"name": "沪府公告"
}]

let secondMenuSecondChild = [{
	"id": "0001-2407",
	"name": "市政府规章"
}, {
	"id": "0001-2407",
	"name": "市政府领导",
	"path": "leaderPhotoes"
}, {
	"id": "0001-2405",
	"name": "工作机构",
	"path": 'workOrganization'
}, {
	"id": "",
	"name": "规划信息",
	"path": "planInfo"
}, {
	"id": "",
	"name": "预算结算",
	"path": "budgetChoice"
}, {
	"id": "",
	"name": "收费清单",
	"path": "chargeList"
}, {
	"id": "0001-12336",
	"name": "政府工作报告",
	"path": "workReport"
}, {
	"id": "",
	"name": "公共服务事项",
	"path": "publicService"
}]

let secondMenuThirdChild = [{
	"id": "0001-44398",
	"name": "法律法规"
}, {
	"id": "0001-44399",
	"name": "国家文件"
}, {
	"id": "0001-44400",
	"name": "本市文件"
}]

let secondMenuFourthChild = [{
	"id": "",
	"name": "市政府办公厅"
}]

let secondMenuFifthChild = [{
	"id": "2019年上海市人民政府办公厅政府信息公开工作年度报告",
	"name": "市政府公报"
}]


let secondMenuChild = [{
	"name": "政策文件",
	"value": secondMenuFirstChild
}, {
	"name": "法定主动公开内容",
	"value": secondMenuSecondChild
}, {
	"name": "政府信息公开制度",
	"value": secondMenuThirdChild
}, {
	"name": "政府信息公开年报",
	"value": secondMenuFourthChild
}, {
	"name": "市政府公报",
	"value": secondMenuFifthChild
}]

let PhotoList = [{
	"url": "./images/leader/image38.png",
	"leaderName": "常务副市长：陈寅",
	"id": "0001-44734",
	"childId": '0001-44742'
}, {
	"url": "./images/leader/image39.png",
	"leaderName": "副市长：吴清",
	"id": "0001-44735",
	"childId": '0001-44743'
}, {
	"url": "./images/leader/image40.png",
	"leaderName": "副市长：舒庆",
	"id": "70876f2b0b184b2991d5182933c1c5af",
	"childId": 'c1e2e76f95e343dfab665269489e6d57'
}, {
	"url": "./images/leader/image41.png",
	"leaderName": "副市长：彭沉雷",
	"id": "0001-44737",
	"childId": '0001-44745'
}, {
	"url": "./images/leader/image42.png",
	"leaderName": "副市长：陈群",
	"id": "0001-44738",
	"childId": '0001-44746'
}, {
	"url": "./images/leader/image43.png",
	"leaderName": "副市长：宗明",
	"id": "0001-44740",
	"childId": '0001-44748'
}, {
	"url": "./images/leader/image44.jpeg",
	"leaderName": "副市长：汤志平",
	"id": "0001-48041",
	"childId": '0001-48042'
}, {
	"url": "./images/leader/image45.jpeg",
	"leaderName": "副市长：陈通",
	"id": "bcf6d29f2a4c4dcb9b380db482aee375",
	"childId": '78c16a7045b2404e93d7fe4214a7108a'
},{
    "url": "./images/leader/image46.jpg",
    "leaderName": "副市长：张为",
    "id": "d2cef557562743d09c34660a5e87090d",
    "childId": '97894622ccad45e79cb271fa917f4858'
}]
let work = [{
	"name": "我的简历",
	"id": ""
}, {
	"name": "我的分工",
	"id": ""
}, {
	"name": "我的工作",
	"id": ""
}]

//规划信息
/*
 * childId  纲要ID
 */
let planInfoList = [{
	"id": "0001-39378",
	"name": "上海市国民经济和社会发展第十三个五年规划纲要",
	"childId": '0001-39379'
}, {
	"id": "0001-22400",
	"name": "上海市经济和社会发展计划",
	"childId": ''
}, {
	"id": "0001-22403",
	"name": "专项规划",
	"childId": ''
}, {
	"id": "0001-22401",
	"name": "上海市国民经济和社会发展第十二个五年规划纲要",
	"childId": '0001-22402'
}]

//预算决算
let budget = [{
	"id": "bed99ddc55fe4a91a015de2cd802fc51",
	"name": "市级部门预决算-部门预算"
}, {
	"id": "1445b7df8a6241d58432885172304a2f",
	"name": "市级部门预决算-部门决算"
}, {
	"id": "843b9686c5104a5aaf833e6730be94e2",
	"name": "市级部门预算绩效-绩效目标"
}, {
	"id": "0551d173b11c49b2ba3eeed8005be5d1",
	"name": "市级部门预决算-绩效评价结果"
}, {
	"id": "0001-41584",
	"name": "专项资金"
}]

// 公共服务事项
let serviceList = [{
	"id": "ee55d6431c8a4047837c2544fd388722",
	"name": "幼有所育"
}, {
	"id": "dc50e52425b045db9b29189ce738b8cf",
	"name": "学有所教"
}, {
	"id": "f1bf3e5243f54b09b486c435980d06d8",
	"name": "劳有所得"
}, {
	"id": "a8d99472d47a4917946e44de23647ab5",
	"name": "病有所医"
}, {
	"id": "3c2e88c91dfa42ca8838bee2bdd5d428",
	"name": "老有所养"
}, {
	"id": "75dca6ba0bd847d7918814cff7889d79",
	"name": "住有所居"
}, {
	"id": "2b01a2a1911648a6addc5d3aae2c8437",
	"name": "弱有所扶"
}, {
	"id": "a443673529b04a59a1eaab78328794ef",
	"name": "优军优抚服务"
}, {
	"id": "fad5304c0c1f45a283ffa414cff5c35e",
	"name": "文化体育保障"
}]

let content = "<p style='text-align: center;font-weight: bold;'>2019年上海市人民政府办公厅政府信息公开工作年度报告</p>	<p style='text-align: center'>发布日期：2020-01-31</p><p>本年度报告根据《中华人民共和国政府信息公开条例》（以下简称《条例》）的要求编制，全文包括总体情况、主动公开政府信息情况、收到和处理政府信息公开申请情况、因政府信息公开工作被申请行政复议和提起行政诉讼情况、政府信息公开工作存在的主要问题及改进情况及其他需要报告的事项。本年度报告中所列数据统计期限从2019年1月1日到12月31日止。本年度报告电子版可从“中国上海”门户网站（www.shanghai.gov.cn）下载。</p><p>一、总体情况</p><p>2019年，上海市人民政府办公厅坚持以习近平新时代中国特色社会主义思想为指导，深入贯彻习近平总书记考察上海重要讲话精神，严格落实党中央、国务院关于全面推进政务公开工作的部署，全面落实《条例》要求，围绕市委、市政府中心工作，聚焦社会公众需求，不断提高政府工作透明度，切实提升人民群众获得感和满意度。</p><p>（一）主动公开方面</p><p>2019年，通过“中国上海”门户网站、《上海市人民政府公报》等平台主动公开规章13件，行政规范性文件60件，市政府其他文件162件，人事任免文件141件。紧扣机构改革进程，及时发布调整后的机关职能、机构设置、办公地址、联系方式等信息。及时更新政府信息公开指南，明确公众获取主动公开和依申请公开信息的主要渠道和具体方式。准确发布市政府办公厅2019年部门预算、2018年部门决算信息，以及2019年度财政支出项目绩效目标、2018年度财政支出项目绩效评价结果等财政信息。</p><p>聚焦中央交给上海的增设中国（上海）自由贸易试验区新片区、在上海证券交易所设立科创板并试点注册制、长江三角洲区域一体化发展三项新的重大任务，权威准确公开《中国（上海）自由贸易试验区临港新片区管理办法》《关于促进中国（上海）自由贸易试验区临港新片区高质量发展实施特殊支持政策的若干意见》《关于着力发挥资本市场作用促进本市科创企业高质量发展的实施意见》《上海市进一步优化营商环境实施计划》《上海市贯彻&lt;长江三角洲区域一体化发展规划纲要&gt;实施方案》等重要政策文件。</p><p>围绕“一网通办”改革重点任务，持续提升“一网通办”总门户服务能级，统一受理平台接入53个部门2261项事项，涵盖行政审批、其他行政权力及公共服务事项等范围。抓好全市权责清单、审批中介服务清单、随机抽查事项清单、政务服务办事指南等的统一管理和动态调整，持续拓展“一网通办”公共服务事项范围，切实维护各类清单和办事指南的严肃性、权威性。发布《关于开展建立政务服务“好差评”制度不断提升企业群众办事满意度工作的通知》，建立政务服务“好差评”制度，并向社会主动公开评价结果。政务服务“好差评”系统于10月29日正式上线运行，从评价结果看，差评数占评价量总数的0.34%，好评数占评价总数99.58%。</p><p>做好政策解读工作，精准传递政策意图。严格落实解读材料和政策文件“同步起草、同步审批、同步发布”（三同步）工作制度，对规章、规范性文件和提交市政府常务会议审议的政策文件均开展了解读,通过“中国上海”门户网站发布政策解读共132件。围绕“一网通办”改革、生活垃圾分类管理、公共数据开放等重点热点领域，市政府办公厅主要负责人及相关分管负责人通过参加新闻发布会带头解读。进一步丰富解读形式，积极采用专家解读、图示图解、视频解读等开展解读，重点阐明政策措施的背景依据、目标任务、主要内容、涉及范围、执行标准等，确保政策内涵透明、信号清晰。</p><p>推进决策公开。严格落实《重大行政决策程序暂行条例》，邀请包括人大代表、政协委员、高校学者、律师、企业负责人等在内的利益相关方列席市政府常务会议，进一步推动了政府决策的公开透明和科学民主。对市政府常务会议的重要议题进行权威发布共计38次，“中国上海”门户网站开设市政府会议公开专栏，以日历形式展现，方便公众根据每次会议日期“按图索骥”查看历史会议内容，通过“一图读懂”的解读方式，提升市政府会议内容的展示度。在市政府实事项目立项过程中，广泛听取市民群众、基层干部、人大代表和政协委员的意见建议，并通过“中国上海”门户网站、“上海发布”、“随申办”移动终端、“12345”市民服务热线收集市民意见建议等，问需于民，问计于民，确保实事项目“看得见、摸得着、能感受、更实在”，让市民群众更好感受到城市温度。</p><p>（二）依申请公开方面</p><p>严格执行新出台的《条例》，进一步规范政府信息公开申请办理工作。及时修订全市“依申请办理示范文书”，优化依申请工作流程。加强公开服务点当面咨询的接待和服务，依申请办理过程中注意与申请人进行电话沟通，精准了解相关诉求，提高便民服务水平。实现全市依申请统一受理入口和统一办理功能，进一步提高了受理、办理、流转、审批等流程的规范化水平，并使办理全过程留痕。在“中国上海”门户网站依申请受理页面增加办理状态查询、在线补正、申请撤回等功能，方便公众使用查询。</p><p>市政府办公厅代表市政府共受理政府信息公开申请859件，申请数量较去年回落12.3%；答复政府信息公开申请805件（含上年度结转申请18件，另有72件申请按照《条例》顺延到下年度答复），均在法定期限内予以答复。予以公开及部分公开258件，占33.9%；不予公开35件，占4.34%；因本机关不掌握等原因无法提供265件，占32.9%；不予处理158件，占19.6%；其他处理0件。信息公开类行政复议7件，诉讼9件，均获维持，继续保持了省级政府零败诉的记录，维护了市政府依法行政、公开透明的良好形象。</p><p>（三）政府信息管理方面</p><p>严格落实《上海市人民政府公文公开发布实施办法》，不断完善市政府公文的公开属性源头认定和发布审查工作机制，在拟制公文时必须明确公开属性、公开时间和方式等，随公文一并报批。坚持应公开尽公开原则，严格审查把关，对拟不公开的，必须依法依规说明理由，不断提升市政府公文的公开比例。经政务公开工作机构审核的市政府公文主动公开率达90.2%。</p><p>不断扩大政务公开标准化规范化工作覆盖范围。指导普陀、金山、徐汇、虹口、浦东五个国家试点区进一步拓展试点覆盖范围，在年内开展了农村综合帮扶、教育、环境保护、应急事故救援、公共文化体育等新领域的政务公开标准化规范化工作。同时将五个试点区的经验做法和典型应用在各区复制推广，其他十一个区在消化吸收试点区的经验和成果基础上，已完成了首轮试点八个领域的政务公开标准化规范化工作。持续推进重点市级部门的全领域公开事项和标准梳理，有关单位均开设了政务公开标准化规范化工作专栏，并相应优化更新了本单位的主动公开基本目录。</p><p>启用上海市政务公开工作平台，加强政府信息公开工作的信息化管理。4月9日，新建设的上海市政务公开工作平台上线运行，新平台具备依申请办理、通知公告、公文备案、统计分析等功能模块。通过与各行政机关门户网站和OA系统进行数据对接，可以准确统计出各机关公文主动公开率，实时监测门户网站信息更新量、政策解读情况等，通过信息化手段提升信息公开工作实效。</p><p>（四）平台建设方面</p><p>全面推进政府网站集约化平台建设。持续完善建设标准，建成本市政府网站集约化平台，实现管理、数据、服务、运维、安全等集约化各项功能。开展“中国上海”英文版改版工作。围绕信息发布、营商环境、形象宣传、服务指引等功能板块，提升英文版宣传服务能级。不断深化政务公开板块改版升级,强化政策解读、回应关切、公众参与等板块建设,充分体现“五公开”要求。系统做好栏目展示设计,提高权责清单、财政预决算、决策草案意见征集反馈、双公示、会议公开等公开专项工作内容的可见性、易查性。优化栏目内搜索引擎功能,优先显示最新政策文件、便民类信息等搜索结果。</p><p>按时编辑出版《上海市人民政府公报》，全年共编发公报24期，刊登政府规章、规范性文件等112件，部门规范性文件45件，政策解读83件。根据《国务院办公厅关于做好政府公报工作的通知》要求，在市政府公报中增加了与人民群众密切相关的涉及社保、医疗、教育等领域的规章、规范性文件的政策解读内容，并将市政府各部门的规范性文件和政策解读材料纳入了公报选编范围，政府公报权威性政府政策文本的功能进一步凸显。</p><p>积极发挥“上海发布”新媒体平台特点和传播优势，创新发布方式，营造了良好的舆论氛围。第二届进博会期间，“上海发布”第一时间发布《习近平在上海考察调研》图文及视频，两条微信阅读量即达180万次，运用抖音号报道进博会，41条短视频播放量超2300万，在抖音相关话题所有播放量中占比26%。两会期间，“上海发布”微信公众号推送《现在为您导航！2019上海再出发》H5作品，采用模拟手机导航软件的方式，串联起临港新片区、上海证券交易所、国家会展中心等重要场所，为市民深入浅出、形象生动地讲解市政府工作报告主要内容，受到了广泛好评。“上海发布”微信公众号用户已突破550万，月影响力稳居全国各省区市政务微信总榜第一。微博粉丝突破770万，在人民日报政务微博影响力报告中，“上海发布”新浪微博排名位列全国党政新闻发布第一名。</p><p>（五）监督保障方面</p><p>充分发挥考核促进工作的良性作用。围绕政务公开重点工作、薄弱环节和瓶颈问题加大政务公开绩效考核力度，提升主动公开等工作的考核分值比重。采取市政府办公厅测评、组织相关部门共同考核、委托第三方机构开展社会评估等评估方式相结合的办法，尽量提高量化评分比重，以保证考核的客观公正。严格落实中办《关于统筹规范督查检查考核工作的通知》有关规定，依托政务公开工作平台开展无纸化线上考核，减轻被考核单位工作负担。考核结果向社会主动公开。</p><p>加强督促指导。针对国务院办公厅政务公开工作评估以及上一年度考核评估中发现的问题，首次以《工作建议书》的形式督促相关责任部门按照细化的问题清单，逐一对照整改，充分发挥考核评估“照镜子”的作用，推进全市公开工作水平整体提升。对考核落后的部门和区政府，主动上门指导，帮助整改落实。年中赴市政府各部门及各区政府开展中期检查和调研，对发现的问题当场进行反馈，指导整改。做好本市政府网站和政务新媒体检查工作。每季度对本市处于正常运行状态的70家政府网站开展全面检查，对市、区、乡镇政府（含街道办事处）以及市政府各部门开设的政务新媒体账号开展检查。均已形成检查报告向社会公布。</p><p>抓好业务培训工作。结合新《条例》出台，针对政务公开工作面临的新要求和新问题，组织开展了全市政务公开业务培训，邀请专家学者围绕《条例》修订、政务公开新形势新要求、依申请办理等方面内容开展专题授课，不断提升全市政府信息公开工作人员的工作能力和水平。</p><p>二、主动公开政府信息情况</p><table align=\"center\" border=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"5\" nowrap=\"nowrap\" style=\"width:559px;\"><p align=\"center\">第二十条第（一）项</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p align=\"center\">信息内容</p></td><td nowrap=\"nowrap\" style=\"width:149px;\"><p align=\"center\">本年新<br />制作数量</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">本年新<br />公开数量</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">对外公开总数量</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>规章</p></td><td nowrap=\"nowrap\" style=\"width:149px;\"><p align=\"center\">13</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">13</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">524</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>规范性文件</p></td><td nowrap=\"nowrap\" style=\"width:149px;\"><p align=\"center\">60</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">60</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">368</p></td></tr><tr><td colspan=\"5\" nowrap=\"nowrap\" style=\"width:559px;\"><p align=\"center\">第二十条第（五）项</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p align=\"center\">信息内容</p></td><td nowrap=\"nowrap\" style=\"width:149px;\"><p align=\"center\">上一年项目数量</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">本年增/减</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">处理决定数量</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>行政许可</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">0</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>其他对外管理服务事项</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">0</p></td></tr><tr><td colspan=\"5\" nowrap=\"nowrap\" style=\"width:559px;\"><p align=\"center\">第二十条第（六）项</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p align=\"center\">信息内容</p></td><td nowrap=\"nowrap\" style=\"width:149px;\"><p align=\"center\">上一年项目数量</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">本年增/减</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">处理决定数量</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>行政处罚</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">0</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>行政强制</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:101px;\"><p align=\"center\">0</p></td><td nowrap=\"nowrap\" style=\"width:89px;\"><p align=\"center\">0</p></td></tr><tr><td colspan=\"5\" nowrap=\"nowrap\" style=\"width:559px;\"><p align=\"center\">第二十条第（八）项</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p align=\"center\">信息内容</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">上一年项目数量</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:190px;\"><p align=\"center\">本年增/减</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>行政事业性收费</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">0</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:190px;\"><p align=\"center\">0</p></td></tr><tr><td colspan=\"5\" nowrap=\"nowrap\" style=\"width:559px;\"><p align=\"center\">第二十条第（九）项</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p align=\"center\">信息内容</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">采购项目数量</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:190px;\"><p align=\"center\">采购总金额</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:219px;\"><p>政府集中采购</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:150px;\"><p align=\"center\">6</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:190px;\"><p align=\"center\">251000元</p></td></tr></tbody></table><p>三、收到和处理政府信息公开申请情况</p><table align=\"center\" border=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"3\" nowrap=\"nowrap\" rowspan=\"3\" style=\"width:217px;\"><p align=\"center\">（本列数据的勾稽关系为：第一项加第二项之和，等于第三项加第四项之和）</p></td><td colspan=\"7\" nowrap=\"nowrap\" style=\"width:355px;\"><p align=\"center\">申请人情况</p></td></tr><tr><td nowrap=\"nowrap\" rowspan=\"2\" style=\"width:38px;\"><p align=\"center\">自然人</p></td><td colspan=\"5\" nowrap=\"nowrap\" style=\"width:255px;\"><p align=\"center\">法人或其他组织</p></td><td nowrap=\"nowrap\" rowspan=\"2\" style=\"width:62px;\"><p align=\"center\">总计</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">商业企业</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">科研机构</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">社会公益组织</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">法律服务机构</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">其他</p></td></tr><tr><td colspan=\"3\" nowrap=\"nowrap\" style=\"width:217px;\"><p>一、本年新收政府信息公开申请数量</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">821</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">35</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">2</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">1</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">859</p></td></tr><tr><td colspan=\"3\" nowrap=\"nowrap\" style=\"width:217px;\"><p>二、上年结转政府信息公开申请数量</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">18</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">18</p></td></tr><tr><td nowrap=\"nowrap\" rowspan=\"20\" style=\"width:23px;\"><p align=\"center\">三、本年度办理结果</p></td><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:194px;\"><p>（一）予以公开</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">258</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">12</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">1</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">1</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:194px;\"><p>（二）部分公开（区分处理的，只计这一情形，不计其他情形）</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">3</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" rowspan=\"8\" style=\"width:57px;\"><p>（三）不予公开</p></td><td nowrap=\"nowrap\" style=\"width:137px;\"><p>1.属于国家秘密</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">14</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>2.其他法律行政法规禁止公开</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>3.危及“三安全一稳定”</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>4.保护第三方合法权益</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>5.属于三类内部事务信息</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">5</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>6.属于四类过程性信息</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">3</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">1</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>7.属于行政执法案卷</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>8.属于行政查询事项</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">10</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">2</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" rowspan=\"3\" style=\"width:57px;\"><p>（四）无法提供</p></td><td nowrap=\"nowrap\" style=\"width:137px;\"><p>1.本机关不掌握相关政府信息</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">252</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">12</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">1</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>2.没有现成信息需要另行制作</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">44</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">3</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>3.补正后申请内容仍不明确</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">25</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" rowspan=\"5\" style=\"width:57px;\"><p>（五）不予处理</p></td><td nowrap=\"nowrap\" style=\"width:137px;\"><p>1.信访举报投诉类申请</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">148</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">1</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>2.重复申请</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">5</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">4</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>3.要求提供公开出版物</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>4.无正当理由大量反复申请</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td nowrap=\"nowrap\" style=\"width:137px;\"><p>5.要求行政机关确认或重新出具已获取信息</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:194px;\"><p>（六）其他处理</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">&nbsp;</p></td></tr><tr><td colspan=\"2\" nowrap=\"nowrap\" style=\"width:194px;\"><p>（七）总计</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">767</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">35</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">2</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">1</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">805</p></td></tr><tr><td colspan=\"3\" nowrap=\"nowrap\" style=\"width:217px;\"><p>四、结转下年度继续办理</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">72</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:47px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:66px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:57px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:38px;\"><p align=\"center\">&nbsp;</p></td><td nowrap=\"nowrap\" style=\"width:62px;\"><p align=\"center\">72</p></td></tr></tbody></table><p><br />四、政府信息公开行政复议、行政诉讼情况</p><table align=\"center\" border=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"5\" style=\"width:190px;\"><p>行政复议</p></td><td colspan=\"10\" style=\"width:383px;\"><p>行政诉讼</p></td></tr><tr><td rowspan=\"2\" style=\"width:28px;\"><p>结果维持</p></td><td rowspan=\"2\" style=\"width:40px;\"><p>结果纠正</p></td><td rowspan=\"2\" style=\"width:40px;\"><p>其他结果</p></td><td rowspan=\"2\" style=\"width:40px;\"><p>尚未审结</p></td><td rowspan=\"2\" style=\"width:43px;\"><p>总计</p></td><td colspan=\"5\" style=\"width:196px;\"><p>未经复议直接起诉</p></td><td colspan=\"5\" style=\"width:187px;\"><p>复议后起诉</p></td></tr><tr><td style=\"width:36px;\"><p>结果维持</p></td><td style=\"width:40px;\"><p>结果纠正</p></td><td style=\"width:40px;\"><p>其他结果</p></td><td style=\"width:40px;\"><p>尚未审结</p></td><td style=\"width:40px;\"><p>总计</p></td><td style=\"width:40px;\"><p>结果维持</p></td><td style=\"width:40px;\"><p>结果纠正</p></td><td style=\"width:40px;\"><p>其他结果</p></td><td style=\"width:40px;\"><p>尚未审结</p></td><td style=\"width:27px;\"><p>总计</p></td></tr><tr><td style=\"width:28px;\"><p>5</p></td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:40px;\"><p>2</p></td><td style=\"width:43px;\"><p>7</p></td><td style=\"width:36px;\"><p>8</p></td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:40px;\"><p>1</p></td><td style=\"width:40px;\"><p>9</p></td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:40px;\">&nbsp;</td><td style=\"width:27px;\">&nbsp;</td></tr></tbody></table><p>五、政府信息公开工作存在的主要问题及改进情况</p><p>（一）主要问题</p><p>一是新《条例》实施后，专业领域的理论学习仍需加强，提升对《条例》新内容的准确把握；二是决策公开仍需进一步加强，公众参与具体举措需进一步细化完善；三是政府网站集约化建设需持续推进，公开专栏规范化建设有待加强；四是政策解读方式不够多元，精准解读效果仍需提高。</p><p>（二）改进措施</p><p>一是深入做好《条例》的贯彻落实。抓紧修订出台《上海市政府信息公开规定》，细化《条例》主体具体适用，规范政府信息公开指南、目录和工作年报编制，完善依申请办理工作全链条管理，动态拓展主动公开范围。二是进一步深化解读回应。重点做好精准解读工作，提高政策解读的针对性。对“一网通办”中的个人和法人，根据用户特点和实际需要，精准推送相关政策解读。三是深化公众参与。制订出台重大行政决策公众参与具体措施，严格履行重大行政决策公众参与程序,继续开展多种形式的公众参与活动。四是全面推进政务公开标准化规范化建设。高水平做好国家试点领域标准指引落地应用，全面开展政务公开事项梳理和目录编制，推进办事服务全过程精准公开。五是着力提升平台渠道管理和服务水平。强化政府网站集约化管理和公开专栏规范化建设，加强线下公开场所和公开渠道建设,统一全市政务公开工作平台使用。六是严格强化工作保障和监督。进一步健全工作领导和协调机制，分级分类抓好工作指导培训，强化工作监督和考核评估。</p><p>六、其他需要报告的事项</p><p>无。</p>"
