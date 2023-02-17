//*需要在每一个csj事项引入，返回到csjindex页面
(function() {
	var judge=false;
	try{
		var judgeName=['nhdevice','jhdevice','zhdevice','jtdevice','ghdevice']
		if(judgeName.indexOf(acBridgeMac.vendor())>-1)judge=true;
	}catch(e){}
	if(judge) {
		try {
			localStorage.clear();
		} catch(e) {}
		try {
			localStorage.setItem("yhPublicItemdevice", 'csjindex');
		} catch(e) {}
	}
})();