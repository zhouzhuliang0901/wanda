var _vlstatId = _vlstatId || [];
// 添加事件 ,['ext1','工作台']
function trackEvent(eventName) {
	_vlstatId.push(['urlCtl',
		'http://12.113.230.10:8080/analytics/analytics/save.do'
	]);
	_vlstatId.push(['netFlag',eventName]);
	(function() {
		var ma = document.createElement('script');
		ma.type = 'text/javascript';
		ma.async = true;
		ma.src = ('https:' == document.location.protocol ? 'https://' :
				'http://') +
			'12.113.230.10:8080/analytics/resources/vlstat/vlstat-min.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ma, s);
	})();
}