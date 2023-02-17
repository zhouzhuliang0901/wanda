function PublicchoiceById(PcId) {
	$("#" + PcId + " a").click(function() {
		if($(this).attr('class') == 'in') {
			$(this).removeClass("in");
		} else {
			$("#" + PcId + " a").removeClass("in");
			$(this).addClass("in");
		}
	})
}

//让光标定位到文本框字符串末尾
function setCaretPosition(id) {
	var tObj = document.getElementById(id);
	console.log(tObj);
	var sPos = tObj.value.length;
	if(tObj.setSelectionRange) {
		setTimeout(function() {
			tObj.setSelectionRange(sPos, sPos);
			tObj.focus();
		}, 0);
	} else if(tObj.createTextRange) {
		var rng = tObj.createTextRange();
		rng.move('character', sPos);
		rng.select();
	}
}

//根据父节点筛选对应区县
function filterByName(dataJsonp, condition) {
	var result = [];
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i]['parentkey'] == condition) {
			result.push(dataJsonp[i]);
		}
	}
	return result;
}

//根据父节点随机一个受理中心
function filterByNameRandom(dataJsonp, condition) {
	var result = [];
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i]['parentkey'] == condition) {
			result.push(dataJsonp[i]);
		}
	}
	var random = parseInt(Math.random() * result.length);
	console.log(random);
	console.log(result[random]);
	return result[random];
}
//根据节点key得到对应区县信息 
function filterByInfo(dataJsonp, condition) {
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i].key == condition) {
			result = dataJsonp[i];
		}
	}
	return result;
}

//根据节点value得到对应区县信息 
function filterGetValueByInfo(dataJsonp, condition) {
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i].value == condition) {
			result = dataJsonp[i];
		}
	}
	return result;
}

//替换小数点
function replacePoint(str) {
	if(str) {
		str = str.replace(/\./g, "");
	}
	return str;
}