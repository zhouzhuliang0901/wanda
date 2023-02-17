var _vlstatId = _vlstatId || [];
// 添加事件 ,['ext1','工作台']
function trackEvent(eventName) {
	_vlstatId.push(['urlCtl',
		'http://xzfwzx.jingan.gov.cn:8080/analytics/analytics/save.do'
	]);
	_vlstatId.push(['netFlag',eventName]);
	(function() {
		var ma = document.createElement('script');
		ma.type = 'text/javascript';
		ma.async = true;
//		ma.src = ('https:' == document.location.protocol ? 'https://' :
//				'http://') +
//			'101.230.224.65:8080/analytics/resources/vlstat/vlstat-min.js';
ma.src = 'libs/common/js/vlstat-min.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ma, s);
	})();
}

//线下办理埋点记录
function recordUsingHistory(modelName, modelOp, itemName, name, idCard, mobile, businessNo, organName,itemNo) {
	$.ajax({
		type: "post",
		url: $.getConfigMsg.preUrl + "/ext/aci/workPlatform/recordUsingHistory.do",
		dataType: "json",
		jsonp: "jsonpCallback",
		data: {
			account:"admin",
			ST_ORGAN_NAME:organName,
			ST_ASSIST_ID:'',
			ST_QUERY_HIS_ID: "",
			ST_MACHINE_ID:$.config.get('uniqueId') || '12-12-12-12-12',
			ST_MODULE_NAME: modelName,
			ST_MODULE_OP: modelOp,
			ST_ITEM_NAME: itemName,
			ST_NAME:name,
			ST_IDENTITY_NO: idCard,
			ST_MOBILE: mobile,
			ST_BUSINESS_NO:businessNo,
			ST_DESC: "",
			ST_OP_RESULT: 'SUCCESS',
			DT_CREATE: new Date(),
			ST_EXT1: '',
			ST_EXT2: "",
			ST_EXT3: "",
			ST_EXT4: "",
			ST_ITEM_NO:itemNo,
		},
		success: function(dataJson) {
			console.log(dataJson);
		},
		error: function(err) {
			console.log("workPlatform/recordUsingHistory.do err");
		}
	});
}