let licenseType = [{
	"id": '1',
	"name": '房产证'
}, {
	"id": '2',
	"name": '不动产证'
}]
let year = [];
let date = new Date().getFullYear();
for(let i = 10; i > 0; i--) {
	year.push({
		'id': '',
		'name': date--,
	})
}
console.log(year);
let area = [{
	"id": 'all',
	"name": '宝'
}, {
	"id": '01',
	"name": '崇'
}, {
	"id": '02',
	"name": '奉'
}, {
	"id": '03',
	"name": '虹'
}, {
	"id": '99',
	"name": '黄'
}, {
	"id": 'all',
	"name": '嘉'
}, {
	"id": '01',
	"name": '金'
}, {
	"id": '02',
	"name": '静'
}, {
	"id": '03',
	"name": '闵'
}, {
	"id": '99',
	"name": '浦'
}, {
	"id": '01',
	"name": '普'
}, {
	"id": '02',
	"name": '青'
}, {
	"id": '03',
	"name": '松'
}, {
	"id": '99',
	"name": '徐'
}, {
	"id": '03',
	"name": '杨'
}, {
	"id": '99',
	"name": '长'
}]

var cardType = [{
	"id": '01',
	"name": '身份证'
}, {
	"id": '09',
	"name": '香港身份证'
}, {
	"id": '10',
	"name": '澳门身份证'
}, {
	"id": '11',
	"name": '台湾身份证'
}, {
	"id": '12',
	"name": '港澳通行证'
}, {
	"id": '13',
	"name": '台胞证'
}, {
	"id": '14',
	"name": '护照'
}]
