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
/*获取当前办事人的年龄*/
function judgeOld(licenseNum) {
	var myDate = new Date();
	var Year = myDate.getFullYear();
	var Month = myDate.getMonth() + 1;
	var Day = myDate.getDate();
	var sonYear = parseInt(licenseNum.substr(6, 4));
	var sonMonth = parseInt(licenseNum.substr(10, 2));
	var sonDay = parseInt(licenseNum.substr(12, 2));
	return Year - sonYear;
}