var _paq = _paq || [];
// tracker methods like "setCustomDimension" should be called before "trackPageView"

_paq.push(['trackPageView']);
_paq.push(['enableLinkTracking']);
(function() {
	var u = $.getConfigMsg.piwikurl;
	_paq.push(['setTrackerUrl', u + 'piwik.php']);
	_paq.push(['setSiteId', $.getConfigMsg.piwikvalue]);
	var d = document,
		g = d.createElement('script'),
		s = d.getElementsByTagName('script')[0];
	g.type = 'text/javascript';
	g.async = true;
	g.defer = true;
	g.src = u + 'piwik.js';
	s.parentNode.insertBefore(g, s);
})();

// 添加事件
function trackEvent(eventName, eventType){
	try{
		_paq.push(['trackEvent', eventName, eventType]);
	}catch(e){
		console.log("error");
	}
}

// 添加数据统计事件
function trackEvent(eventName, eventType,name,value){
	try{
		_paq.push(['trackEvent', eventName, eventType,name,value]);
	}catch(e){
		console.log("error");
	}
}

// 添加检索
function trackSiteSearch(keyWord){
	try{
		_paq.push(['trackSiteSearch', keyWord]);
	}catch(e){
		console.log("error");
	}
}

// 页面标题
function trackPageView(title){
	try{
		_paq.push(['trackPageView',title]);
	}catch(e){
		console.log("error");
	}
}

//自建表  埋点
var _vlstatId = _vlstatId || [];
// 添加事件
function trackEventSelf(eventName, childEventName) {
	_vlstatId.push(['urlCtl',
		'http://zwdt.huangpuqu.sh.cn:8080/analytics/analytics/save.do'
	]);
	_vlstatId.push(['netFlag', eventName], ['netSubFlag', childEventName], ['ext1', '工作台']);
	(function() {
		var ma = document.createElement('script');
		ma.type = 'text/javascript';
		ma.async = true;
		ma.src = ('https:' == document.location.protocol ? 'https://' :
				'http://') +
			'zwdt.huangpuqu.sh.cn:8080/analytics/resources/vlstat/vlstat-min.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ma, s);
	})();
}