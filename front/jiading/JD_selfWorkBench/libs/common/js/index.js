/*one、办事查询 two、办事预约  three、办事档案  four、自助取表 five、材料复印 six、身份证复印 seven、公积金查询
	eight、办事指南  nine、智能问答  ten、医保查询  eleven、便民服务  twelve、办事指南文山 */
function click_point(id){
	if($.getConfigMsg.isPiwikLog){
		trackEvent($('#'+id+' span').html(),$('#'+id+' p').html());
	}
	switch (id){
		case "one":
			window.location.href = "eventquery/index.html";
			break;
		case "two":
			window.location.href = "reservation/index.html";
			break;
		case "three":
			window.location.href = "archivesservice/index.html";
			break;
		case "four":
			window.location.href = "autoform/index.html";
			break;
		case "five":
			window.location.href = "materialcopy/index.html";
			break;
		case "six":
			window.location.href = "idcardcopy/index.html";
			break;
		case "seven":
			window.location.href = "providentfound/index.html";
			break;
		case "eight":
			window.location.href = "serviceguide/index.html";
			break;
		case "nine":
			window.location.href = "xiaoirobot/xiaoIrobot.html";
			break;
		case "ten":
			window.location.href = "medicalInsurance/medicalInsurance.html";
			break;
		case "eleven":
			window.location.href = "Convenienceservices/Convenience_services.html";
			break;
		case "twelve":
			window.location.href = "serviceguidews/index.html";
			break;
	}
}