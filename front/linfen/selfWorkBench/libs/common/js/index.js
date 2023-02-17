//工作台埋点  首页点击访问量
function click_point(id) {
	if($.getConfigMsg.isPiwikLog) {
		if(id=="one"){
			trackEvent("办理门急诊就医记录册");
		}else if(id=="three"){
			trackEvent("参加个人城镇基本养老保险缴费情况");
		}else{
			trackEvent($('#'+id+' p').text());
		}
	}
}
function click_point2(id) {
	if($.getConfigMsg.isPiwikLog) {
		trackEvent($('#'+id).text());
	}
}