let zhuofanItemList = [{
	itemName: '疫苗接种点查询',
	url: 'http://10.81.16.200:8081/front/multiport/adaptation/vaccinationCenterToMain.do;jsessionid=D1CB8DF3D6600193442B4ED71112BEEB?funcationOid=&source=wd'
}]

let getUrlForZhuofanItem = function(n) {
	if(Array.isArray(zhuofanItemList)) {
		for(var i = 0; i < zhuofanItemList.length; i++) {
			if(zhuofanItemList[i].itemName == n) {
				return zhuofanItemList[i].url;
			}
		}
	}
}