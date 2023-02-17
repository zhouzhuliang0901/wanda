//给select增加失去焦点事件
function selectBlur() {
	$(".select1").bind("change", function() {
		this.blur();
	});
}
//判空
function isBlank(str) {
	if(str == "" || str == null || str == undefined) {
		return true;
	} else {
		return false;
	}
}
//根据节点key得到对应区县信息 
function filterByInfo(dataJsonp, condition) {
	console.log(dataJsonp);
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i][''] == condition) {
			return true
		}
	}
	return false;
}
function getKeyByChild(dataJson, value) {
	let result;
	value = value.substring(0, 2);
	for(var i = 0; i < dataJson.length; i++) {
		let str = dataJson[i].proId.substring(0, 2);
		if(str == value) {
			result = dataJson[i];
			console.log(result);
		}
	}
	return result;
}

//将yyyymmdd转为yyyy-mm-dd
function formatDateCustom(str) {
	console.log(str);
	//正则
	let pattern = /(\d{4})(\d{2})(\d{2})/;
	let result = str.replace(pattern, '$1-$2-$3');
	console.log(result);
	return result;
}

