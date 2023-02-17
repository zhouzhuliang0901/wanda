//工作台埋点  首页点击访问量
function click_point(id) {
	if($.getConfigMsg.isPiwikLog) {
		console.log($('#'+id+' p').text());
		if(id == "twenty-eight"){
			trackEvent($.config.get('uniqueId'),"疫情专窗");
			trackEventSelf("疫情专窗","");
		}else{
			trackEvent($.config.get('uniqueId'),$('#'+id+' p').text());
			trackEventSelf($('#'+id+' p').text(),"");
		}
	}
}
function click_point2(id) {
	if($.getConfigMsg.isPiwikLog) {
		console.log($('#'+id).text());
		trackEvent($.config.get('uniqueId'),$('#'+id).text());
		trackEventSelf($('#'+id).text(),"");
	}
}

//function click_point(id) {
//	if($.getConfigMsg.isPiwikLog) {
//		switch(id) {
//			case "one":
//				trackEvent($.config.get('uniqueId'), "自助申报");
//				break;
//			case "two":
//				trackEvent($.config.get('uniqueId'), "店小二主题式服务");
//				break;
//			case "three":
//				trackEvent($.config.get('uniqueId'), "CA事务专区");
//				break;
//			case "four":
//				trackEvent($.config.get('uniqueId'), "综合通办服务");
//				break;
//			case "five":
//				trackEvent($.config.get('uniqueId'), "电子证照打印");
//				break;
//			case "six":
//				trackEvent($.config.get('uniqueId'), "办事指南");
//				break;
//			case "seven":
//				trackEvent($.config.get('uniqueId'), "自助填表");
//				break;
//			case "eight":
//				trackEvent($.config.get('uniqueId'), "自助预约");
//				break;
//			case "nine":
//				trackEvent($.config.get('uniqueId'), "进度查询");
//				break;
//			case "ten":
//				trackEvent($.config.get('uniqueId'), "证明打印");
//				break;
//			case "eleven":
//				trackEvent($.config.get('uniqueId'), "工商银行");
//				break;
//			case "twelve":
//				trackEvent($.config.get('uniqueId'), "建设银行");
//				break;
//			case "thirteen":
//				trackEvent($.config.get('uniqueId'), "浦发银行");
//				break;
//			case "fourteen":
//				trackEvent($.config.get('uniqueId'), "厦门银行");
//				break;
//			case "fifteen":
//				trackEvent($.config.get('uniqueId'), "材料复印");
//				break;
//			case "sxiteen":
//				trackEvent($.config.get('uniqueId'), "评价及建议");
//				break;
//			case "seventeen":
//				trackEvent($.config.get('uniqueId'), "身份证复印");
//				break;
//			case "eighteen":
//				trackEvent($.config.get('uniqueId'), "中国银行");
//				break;
//			case "ninth":
//				trackEvent($.config.get('uniqueId'), "政策推送服务");
//				break;
//			case "twenty":
//				trackEvent($.config.get('uniqueId'), "视频咨询");
//				break;
//			case "twenty-one":
//				trackEvent($.config.get('uniqueId'), "帮办助手");
//				break;
//			case "twenty-two":
//				trackEvent($.config.get('uniqueId'), "疫情专窗");
//				break;
//			case "twenty-three":
//				trackEvent($.config.get('uniqueId'), "无人干预");
//				break;
//		}
//
//	}
//
//}