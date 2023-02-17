var returnCord = function(dataList, name) {
	if(Array.isArray(dataList)) {
		for(var i = 0; i < dataList.length; i++) {
			if(dataList[i].itemName == name) {
				return dataList[i];
			}
		}
	}
};
var _vlstatId = _vlstatId || [];
// 添加事件
function trackEvent(eventName, childEventName) {
	console.log(eventName + '--' + childEventName);
	_vlstatId.push(['urlCtl',
		'http://' + $.getConfigMsg.piwikurl + '/analytics/analytics/save.do'
	]);
	_vlstatId.push(['netFlag', eventName], ['netSubFlag', childEventName], ['ext1', '工作台']);
	(function() {
		var ma = document.createElement('script');
		ma.type = 'text/javascript';
		ma.async = true;
		ma.src = ('https:' == document.location.protocol ? 'https://' :
				'http://') +
			$.getConfigMsg.piwikurl + '/analytics/resources/vlstat/vlstat-min.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ma, s);
	})();
}
//wonders埋点 初始化
function wondersLogInit() {
	$.ajax({
		type: "get",
		url: $.getConfigMsg.preUrlSelfApi + "/infopub/deviceinfo/listMac.do", //http://10.81.16.56:8088
		dataType: "json",
		data: {
			stDeviceMac: jQuery.getConfigMsg.uniqueId || ""
		},
		success: function(dataJson) {
			//wonders埋点
			if(typeof wondersLog != "undefined") {
				try {
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
								$unique_code: dataJson.data[0].stDeviceMac,
								$address_code: dataJson.data[0].stDeviceCode,
								$address: dataJson.data[0].stDeviceAddress,
								$longitude: dataJson.data[0].nmLng,
								$latitude: dataJson.data[0].nmLat
							});
						}
					});
				} catch(e) {}
			}
		},
		error: function(err) {
			console.log(err);
		}
	});
}
wondersLogInit();

//查询类
function trackEventForQuery(eventName, itemCode, itemName, deptName, name, idCard, mobile) {
	if(typeof wondersLog != "undefined") {
		try {
			wondersLog.track_event('SSM_QUERY', {
				$event_name: eventName, // 事项名称
				$item_code: itemCode, // 办理项代码
				$item_name: itemName, // 办理项名称
				$dept_name: deptName, // 受理部门名称
				$user_type: idCard == "" ? "" : 1, // 用户类型
				$project_target: name, // 办件对象
				$license_no: idCard, // 证件号
				$mobile: mobile || "" // 办理人手机号
			});
		} catch(e) {}
	}
}

//办理类
function trackEventForAffairs(itemCode, itemName, deptName, name, idCard, mobile) {
	if(typeof wondersLog != "undefined") {
		try {
			wondersLog.track_event('SSM_AFFAIRS', {
				$item_code: itemCode, // 办理项代码
				$item_name: itemName, // 办理项名称
				$dept_name: deptName, // 受理部门名称
				$user_type: idCard == "" ? "" : 1, // 用户类型
				$project_target: name, // 办件对象
				$license_no: idCard, // 证件号
				$mobile: mobile || "" // 办理人手机号
			});
		} catch(e) {}
	}
}

//预约类
function trackEventForOrder(eventName) {
	if(typeof wondersLog != "undefined") {
		wondersLog.track_event('SSM_ORDER', {
			$item_code: 'SH00SH123456', // 事项代码
			$item_name: eventName, // 事项名称
			$dept_name: '浦东新区派出所', // 受理部门名称
			$user_type: 1, // 用户类型
			$project_target: '张三', // 办件对象
			$license_no: '3101112020112121234', // 证件号
			$mobile: '18888888888', //办理人手机号
			$order_start_time: '2020-01-01 08:00:00', // 预约时段开始
			$order_end_time: '2020-01-01 17:00:00', // 预约时段结束
		});
	}
}

/*
 * 2、办事提交/查询结果保存
 */
function saveApplyResult(id, content) {
	try {
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/saveApplyResult.do",
			dataType: "json",
			//jsonp: "jsonpCallback",
			data: {
				linkId: id,
				content: encodeURI(content),
			},
			success: function(dataJson) {
				console.log(dataJson);
			},
			error: function(err) {
				console.log("workPlatform/saveApplyResult.do err");
			}
		});
	} catch(e) {}
}
/*
 *3、附件上传
 */
function uploadStuff(id, file) {
	$.ajax({
		type: "post",
		url: $.getConfigMsg.preUrl + "/aci/workPlatform/uploadStuff.do",
		dataType: "json",
		data: {
			file: file,
			linkId: id,
		},
		success: function(dataJson) {},
		error: function(err) {
			console.log("workPlatform/uploadStuff.do err");
		}
	});
}

/**
 * 工作台使用模块记录接口
 * 1、模块使用记录保存
 */
function recordUsingHistory(modelName, modelOp, itemName, name, idCard, mobile, businessNo, content) {
	console.log(modelName + "-" + modelOp + "-" + itemName + "-" + name + "-" + idCard + "-" + mobile + "-" + businessNo + "-" + content);
	var url = window.document.location.href;
	var itemNo = returnCord(dataEvent, itemName).itemCode || "";
	var piwikName = returnCord(dataEvent, itemName).piwikName || "";
	console.log(piwikName + "----" + itemNo);
	content = JSON.parse(content);
	var ST_ASSIST_ID = content.data.managerID;
	content = JSON.stringify(content)
	if(jQuery.getConfigMsg.uniqueId == undefined) {
		$.config.load('uniqueId', function(f) {
			jQuery.getConfigMsg.uniqueId = f||"12-12-12-12-12";
			console.log(jQuery.getConfigMsg.uniqueId);
		});
	}
	$.ajax({
		type: "post",
		url: $.getConfigMsg.preUrl + "/selfapi/workPlatform/recordUsingHistory.do",
		dataType: "json",
		jsonp: "jsonpCallback",
		data: {
			account: "admin",
			ST_ASSIST_ID: ST_ASSIST_ID,
			ST_QUERY_HIS_ID: "",
			ST_MACHINE_ID: jQuery.getConfigMsg.uniqueId || '',
			ST_MODULE_NAME: encodeURI(modelName),
			ST_MODULE_OP: encodeURIComponent(modelOp),
			ST_ITEM_NAME: encodeURI(piwikName || itemName),
			ST_NAME: encodeURI(name),
			ST_IDENTITY_NO: idCard,
			ST_MOBILE: mobile,
			ST_BUSINESS_NO: (itemName == "办理就医记录册的申领、更换、补发") ? "" : businessNo,
			ST_DESC: "",
			ST_OP_RESULT: 'SUCCESS',
			DT_CREATE: new Date(),
			ST_EXT1: url,
			ST_EXT2: "",
			ST_EXT3: "",
			ST_EXT4: "",
			ST_ITEM_NO: itemNo,
		},
		success: function(dataJson) {
			try {
				console.log(dataJson);
				if(dataJson.SUCCESS == "true") {
					saveApplyResult(dataJson.HISTORYID, content);
					if(itemName == "办理就医记录册的申领、更换、补发") {
						uploadStuff(dataJson.HISTORYID, businessNo);
					}
				}
			} catch(e) {}
		},
		error: function(err) {
			console.log("workPlatform/recordUsingHistory.do err");
		}
	});
}

//静安线下办理埋点记录
function recordUsingHistoryToJingAn(modelName, modelOp, itemName, name, idCard, mobile, businessNo, organName, itemNo) {
	$.ajax({
		type: "post",
		url: "http://xzfwzx.jingan.gov.cn:8080/ac/ext/aci/workPlatform/recordUsingHistory.do",
		dataType: "json",
		jsonp: "jsonpCallback",
		data: {
			account: "admin",
			ST_ORGAN_NAME: organName,
			ST_ASSIST_ID: '',
			ST_QUERY_HIS_ID: "",
			ST_MACHINE_ID: $.config.get('uniqueId') || '12-12-12-12-12',
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
			ST_EXT1: '',
			ST_EXT2: "",
			ST_EXT3: "",
			ST_EXT4: "",
			ST_ITEM_NO: itemNo,
		},
		success: function(dataJson) {
			console.log(dataJson);
		},
		error: function(err) {
			console.log("workPlatform/recordUsingHistory.do err");
		}
	});
}