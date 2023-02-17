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
/*校验手机号*/
var TEL_REGEXP = /^1([38]\d|5[0-35-9]|7[3678])\d{8}$/;
function validateTel (tel){
      if(TEL_REGEXP.test(tel)){
        return true;
      }
      return false;
}
/*获取当前办事人的年龄*/
function judgeOld(licenseNum){
	var myDate = new Date();
	var Year = myDate.getFullYear();
	var Month = myDate.getMonth()+1;
	var Day = myDate.getDate();
	var sonYear = parseInt(licenseNum.substr(6,4));
	var sonMonth = parseInt(licenseNum.substr(10,2));
	var sonDay = parseInt(licenseNum.substr(12,2));
	// 判断年龄是否是16周岁以上
	if(Year - sonYear >= 16){
		if(Year - sonYear > 16){
			return true;
		}else{
			if(Month - sonMonth >= 0){
				if(Month - sonMonth == 0){
					if(Day - sonDay >=0){
						return true;
					} else {
						return false;
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
	}else {
		return false;
	}
}

// 判断社保卡开通状态
function isOpen(num,list){
	var flag = 0;
	var cardState = parseInt(num);
	var stateCode = list;
	for(var i = 0; i < stateCode.length; i++){
		if(cardState == stateCode[i]) {
			flag = 1;
			break;
		} else {
			flag = 0;
		}
	}
	if(flag){
		return true;
	}else {
		return false;
	}
}

//判断是否为空
function isBlank(string){
	if(string == "" || string == null || string == undefined){
		return true;
	}else{
		return false;
	}
}

//根据节id得到对应区县信息 
function filterByInfo(dataJsonp, condition) {
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i].id == condition) {
			result = dataJsonp[i];
		}
	}
	return result.name;
}