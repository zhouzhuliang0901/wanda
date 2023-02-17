//表单数据带入单选
function getCurrentIndex(condition, array, key) {
	var index = null;
	for(var i = 0; i < array.length; i++) {
		if(array[i][key] == condition) {
			index = i;
		}
	}
	return index;
}
//通过id获取name
function getNameByID(condition, array, key) {
	var name = null;
	for(var i = 0; i < array.length; i++) {
		if(array[i][key] == condition) {
			name = array[i].name;
		}
	}
	return name;
}
//判空
function isBlank(str) {
	if(str == "" || str == null || str == undefined) {
		return true;
	} else {
		return false;
	}
}

//校园卡号翻转  C35CF332  -->  32F35CC3
function convert(str) {
	var str1 = ""
	for(var i = 8; i > 0; i = i - 2) {
		str1 += str.slice(i - 2, i)
	}
	// console.log(parseInt(str1, 16));
	return parseInt(str1, 16)
}

//手机号脱敏

function hideMobile(phone){
	var str = "";
	str = phone.substring(0,3) + '****' +phone.substring(7,phone.length);
	console.log(str)
	return str;
}

//身份证号脱敏
function hideIdCard(idCard){
	var str = "";
	str = idCard.substring(0,6) + '******' +idCard.substring(14,idCard.length);
	console.log(str)
	return str;
}
//绘图
function dataURLtoBlob(dataurl) {

	var arr = dataurl.split(','),
		mime = arr[0].match(/:(.*?);/)[1],

		bstr = atob(arr[1]),
		n = bstr.length,
		u8arr = new Uint8Array(n);

	while(n--) {

		u8arr[n] = bstr.charCodeAt(n);

	}

	return new Blob([u8arr], {
		type: mime
	});

}

function downloadFile(url, name) {

	var a = document.createElement("a")

	a.setAttribute("href", url)

	a.setAttribute("download", name)

	a.setAttribute("target", "_blank")

	let clickEvent = document.createEvent("MouseEvents");

	clickEvent.initEvent("click", true, true);

	a.dispatchEvent(clickEvent);

}

function downloadFileByBase64(base64, name) {

	var myBlob = dataURLtoBlob(base64)
	//兼容ie
	if(window.navigator && window.navigator.msSaveOrOpenBlob) {
		window.navigator.msSaveOrOpenBlob(myBlob, name+'.png');
	} else {
		var myUrl = URL.createObjectURL(myBlob)
		downloadFile(myUrl, name)
	}

}