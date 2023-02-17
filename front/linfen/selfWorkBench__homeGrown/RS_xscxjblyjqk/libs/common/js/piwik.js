var _vlstatId = _vlstatId || [];
// 添加事件
function trackEvent(eventName, childEventName) {
	_vlstatId.push(['urlCtl',
		'http://10.81.16.56:8080/analytics/analytics/save.do'
	]);
	_vlstatId.push(['netFlag', eventName], ['netSubFlag', childEventName], ['ext1', '工作台']);
	(function() {
		var ma = document.createElement('script');
		ma.type = 'text/javascript';
		ma.async = true;
		ma.src = ('https:' == document.location.protocol ? 'https://' :
				'http://') +
			'10.81.16.56:8080/analytics/resources/vlstat/vlstat-min.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ma, s);
	})();
}

//wonders埋点
if(typeof wondersLog != "undefined") {
	wondersLog.init('ywtb', {
		SPA: {
			//TODO: 是否是单页面应用
			is: true,
			//TODO: 若是单页面，则需要设置使用的技术手段hash or history
			mode: 'hash'
		},
		loaded: function(sdk) {
			//TODO: 设置自助机默认属性
			sdk.register_event_super_properties({
				$unique_code: 'gfdgdfgf-543-fdgfd',
				$address_code: 'PD001',
				$address: '上海市静安区',
				$longitude: '123.231232512331',
				$latitude: '45.1231232131232'
			});
		}
	});
}
//查询类
function trackEventForQuery(eventName) {
	if(typeof wondersLog != "undefined") {
		wondersLog.track_event('SSM_QUERY', {
			$event_name: eventName,
			$item_code: 'SH00SH123456',
			$item_name: '身份证办理',
			$dept_name: '浦东新区派出所',
			$user_type: 1,
			$project_target: '张三',
			$license_no: '3101112020112121234',
			$mobile: '18888888888'
		});
	}
}

//办理类
function trackEventForAffairs(eventName) {
	if(typeof wondersLog != "undefined") {
		wondersLog.track_event('SSM_AFFAIRS', {
			$item_code: 'SH00SH123456',
			$item_name: eventName,
			$dept_name: '浦东新区派出所',
			$user_type: 1,
			$project_target: '张三',
			$license_no: '3101112020112121234',
			$mobile: '18888888888'
		});
	}
}

//预约类
function trackEventForOrder(eventName) {
	if(typeof wondersLog != "undefined") {
		wondersLog.track_event('SSM_ORDER', {
			$item_code: 'SH00SH123456',
			$item_name: eventName,
			$dept_name: '浦东新区派出所',
			$user_type: 1,
			$project_target: '张三',
			$license_no: '3101112020112121234',
			$mobile: '18888888888',
			$order_start_time: '2020-01-01 08:00:00',
			$order_end_time: '2020-01-01 17:00:00',
		});
	}
}

/*
 * 2、办事提交/查询结果保存
 */
function saveApplyResult(id, content) {
	$.ajax({
		type: "get",
		url: $.getConfigMsg.preUrl + "/aci/workPlatform/saveApplyResult.do",
		dataType: "jsonp",
		jsonp: "jsonpCallback",
		data: {
			linkId: id,
			content: content,
		},
		success: function(dataJson) {
			console.log(dataJson);
		},
		error: function(err) {
			console.log("workPlatform/saveApplyResult.do err");
		}
	});
}
/*
 *3、附件上传
 */
function uploadStuff() {
	$.ajax({
		type: "get",
		url: $.getConfigMsg.preUrl + "/aci/workPlatform/uploadStuff.do",
		dataType: "jsonp",
		jsonp: "jsonpCallback",
		data: {
			file: "",
			linkId: "",
		},
		success: function(dataJson) {
			callback && callback(dataJson);
		},
		error: function(err) {
			console.log("workPlatform/uploadStuff.do err");
		}
	});
}

/**
 * 工作台使用模块记录接口 
 * 1、模块使用记录保存
 */
function recordUsingHistory(modelName, modelOp, itemName, name, idCard, mobile, businessNo,content) {
	$.ajax({
		type: "get",
		url: $.getConfigMsg.preUrl + "/aci/workPlatform/recordUsingHistory.do",
		dataType: "jsonp",
		jsonp: "jsonpCallback",
		data: {
			ST_QUERY_HIS_ID: "",
			ST_MACHINE_ID: $.config.get("uniqueId") || '12-12-12-12',
			ST_MODULE_NAME: modelName,
			ST_MODULE_OP: modelOp,
			ST_ITEM_NAME: itemName,
			ST_NAME: name,
			ST_IDENTITY_NO: idCard,
			ST_MOBILE: mobile,
			ST_BUSINESS_NO: businessNo,
			ST_DESC: "",
			ST_OP_RESULT: 'SUCCESS',
			DT_CREATE: new Date(),
			ST_EXT1: "",
			ST_EXT2: "",
			ST_EXT3: "",
			ST_EXT4: "",
		},
		success: function(dataJson) {
			console.log(dataJson);
			saveApplyResult(dataJson.HISTORYID,content);
		},
		error: function(err) {
			console.log("workPlatform/recordUsingHistory.do err");
		}
	});
}
