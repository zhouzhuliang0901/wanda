//筛选数组中你要的信息
/*
 * dataJsonp  数组
 * condition   条件
 * key  对应的key值
 */
function filterByInfo(dataJsonp, condition, condition1, key, key1) {
	let array = [];
	let result = [];
	for(let i = 0; i < dataJsonp.length; i++) {
		if(i == (dataJsonp.length - 1)) {
			for(let j = 0; j < array.length; j++) {
				if(array[j][key1] == condition1) {
					result.push(array[j]);
				}
			}
		} else {
			if(dataJsonp[i][key] == condition) {
				array.push(dataJsonp[i]);
			}
		}
	}
	return result;
}