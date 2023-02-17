//通过部门id获取事项列表
function getItemByID(condition, array) {
	var result = [];
	for(var i = 0; i < array.length; i++) {
		if(array[i].pid == condition) {
			result.push(array[i]);
		}
	}
	return result;
}