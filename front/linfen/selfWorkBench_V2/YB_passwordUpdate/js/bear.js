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