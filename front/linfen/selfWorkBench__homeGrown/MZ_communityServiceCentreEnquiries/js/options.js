var areaList = [
	{"areaId":"1","areaCode":"310101","areaName":"黄浦区 "},
	{"areaId":"2","areaCode":"310104","areaName":"徐汇区 "},
	{"areaId":"3","areaCode":"310105","areaName":"长宁区 "},
	{"areaId":"4","areaCode":"310106","areaName":"静安区 "},
	{"areaId":"5","areaCode":"310107","areaName":"普陀区 "},
	{"areaId":"6","areaCode":"310109","areaName":"虹口区 "},
	{"areaId":"7","areaCode":"310110","areaName":"杨浦区 "},
	{"areaId":"8","areaCode":"310112","areaName":"闵行区 "},
	{"areaId":"9","areaCode":"310113","areaName":"宝山区 "},
	{"areaId":"10","areaCode":"310114","areaName":"嘉定区 "},
	{"areaId":"11","areaCode":"310115","areaName":"浦东新区 "},
	{"areaId":"12","areaCode":"310116","areaName":"金山区 "},
	{"areaId":"13","areaCode":"310117","areaName":"松江区 "},
	{"areaId":"14","areaCode":"310118","areaName":"青浦区 "},
	{"areaId":"15","areaCode":"310120","areaName":"奉贤区 "},
	{"areaId":"16","areaCode":"310230","areaName":"崇明区 "}
	]

var spaceInfoList = {
    "code": "0",
    "msg": "",
    "sign": "4F290F47C0CFDB6233DCD12879FF148C",
    "data": [
        {
            "organcode": "31010102100001",
            "organname": "打浦桥街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-63041102",
            "address": "南塘浜路103号",
            "opentime": "周一至周五8:30-16:30，中午时间段对外提供服务。双休日和国定节假日：8:30-11:30。"
        },
        {
            "organcode": "31010101300001",
            "organname": "外滩街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-63295277",
            "address": "河南中路568号",
            "opentime": "周一至周五上午8:30至下午16:30 （周六日国定假受理部分事项；上午8:30-下午11:30）；   外滩街道网上一门式公众号ID：waitanjiedao"
        },
        {
            "organcode": "31010102200001",
            "organname": "淮海中路街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-53832593",
            "address": "马当路349号",
            "opentime": "周一至周日 上午8：30--11:30 下午13:00-16:30(法定假日除外) 夏令时下午 13:30-16:30"
        },
        {
            "organcode": "31010101900001",
            "organname": "老西门街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-63769098",
            "address": "大吉路71号",
            "opentime": "（1）周一至周五：8：30—16：30，中午对外提供服务。（2）双休日和国定假日：8：30—11：30"
        },
        {
            "organcode": "31010100200001",
            "organname": "南京东路街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-63271866",
            "address": "江阴路101号",
            "opentime": "周一至周日8：30-16：30（法定节假日除外）"
        },
        {
            "organcode": "31010101700001",
            "organname": "小东门街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-63325638 021-63325639",
            "address": "白渡路252号一楼",
            "opentime": "周一到周日 8：30--16：30（国家法定节假日除外）"
        },
        {
            "organcode": "31010101500001",
            "organname": "半淞园路街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-63122105",
            "address": "西藏南路1360号",
            "opentime": "周一至周五8:30-16:30周六周日8:30-11:30（国定节假日除外）"
        },
        {
            "organcode": "31010102300001",
            "organname": "瑞金二路街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-53829109",
            "address": "皋兰路6号地下一层",
            "opentime": "工作日周一至周五 8:30-16:30；双休日及国定节假日 8:30-11:30"
        },
        {
            "organcode": "31010102000001",
            "organname": "五里桥街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-53013030*6111",
            "address": "瞿溪路768号",
            "opentime": "周一至周五：8:30--16:30（疫情防控期间，中午12:00-13:00暂停对外服务，开展环境消毒等卫生防控工作），双休日及国定节假日：8:30--11:30"
        },
        {
            "organcode": "31010101800001",
            "organname": "豫园街道社区事务受理服务中心",
            "orderexplain": "1、预约范围：当天之后7天（正常休息的周六、周日、法定休息日除外）；2、预约时间：8:30-16:30，按1个小时分为8个时间段，每个受理中心每个时间段最多可预约5个号，当天15:30之后不能预约第二天的时间段，疫情期间，受理中心需进行全面消毒，11:30--13:30不可预约。3、预约成功后3个工作日（含当日），预约人可确认预约或取消预约，如确认预约但未按时到预约的受理中心办理业务，则作为失约处理，失约将影响下次预约。",
            "telphone": "021-63365917",
            "address": "黄浦区河南南路288号",
            "opentime": "周一至周五8：30~16:30，双休日和国定假日 8:30～11:30"
        }
    ]
}