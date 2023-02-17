//将yyyymmdd转为yyyy-mm-dd
function formatDateCustom(str) {
	let date = new Date(str);
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	month = month < 10 ? "0" + month : month;
	day = day < 10 ? "0" + day : day;
	str = year + '-' + month + '-' + day;
	return str;
}