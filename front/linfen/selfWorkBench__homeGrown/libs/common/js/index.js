//工作台埋点  首页点击访问量
function click_point(id) {
	if($.getConfigMsg.isPiwikLog) {
		trackEvent($('#'+id+' p').text(),"");
//		trackEventForQuery($('#'+id+' p').text());
	}
}
function click_point2(id) {
	if($.getConfigMsg.isPiwikLog) {
		trackEvent($('#'+id).text(),"");
//		trackEventForAffairs($('#'+id+' p').text());
//		trackEventForOrder($('#'+id+' p').text());
	}
}