function click_point(id) {
	if($.getConfigMsg.isPiwikLog) {
		switch(id) {
			case "one":
				trackEvent("自助办理");
				break;
			case "two":
				trackEvent("自助预约");
				break;
			case "three":
				trackEvent("数字证书");
				break;
			case "four":
				trackEvent("我的证照");
				break;
			case "five":
				trackEvent("证明开具");
				break;
			case "six":
				trackEvent("进度查询");
				break;
			case "seven":
				trackEvent("办事指南");
				break;
			case "eight":
				trackEvent("智能引导");
				break;
			case "nine":
				trackEvent("我要评");
				break;
			case "ten":
				trackEvent("自助填表");
				break;
			case "eleven":
				trackEvent("材料复印");
				break;
			case "twelve":
				trackEvent("身份证复印");
				break;
			case "thirteen":
				trackEvent("银行服务");
				break;
		}
	}
}