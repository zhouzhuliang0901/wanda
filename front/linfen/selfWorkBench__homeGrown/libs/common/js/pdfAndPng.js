//拼接地址
//var data={
//	'certUuid':'',
//	'sessionId':'',
//	'machineId':'',
//	'itemName':'',
//	'itemCode':'',
//	'businessCode':'',
//}
function returnPDFAndPNGdataS(typeName,data){
	console.log(typeName);
	console.log(data);
	var certUuid=data.certUuid;
	if(certUuid==undefined)return;
	certUuid = certUuid.replace(/\+/g, "-");
    certUuid = certUuid.replace(/\=/g, ",");
	var urlS='';
	var urltow='certUuid='+certUuid;
	var urlthree='&sessionId='+data.sessionId;
	var urlfour='&machineId='+data.machineId;
	var urlfive='&itemName='+encodeURI(data.itemName, "UTF-8");
	var urlsix='&itemCode='+data.itemCode;
	var urlseven="&businessCode="+data.businessCode;
	var urlone=urltow+urlthree+urlfour+urlfive+urlsix+urlseven;
	var base64type="&type=base64";
	if(typeName=='png'){
		urlS='/selfapi/dzzz/humanSocietyPNGForBytes.do?';
		urlS=urlS+urlone;
	}else if(typeName=='pdfurl'){
		urlS='/selfapi/dzzz/humanSocietyPDF.do?';
		urlS=urlS+urlone;
	}else if(typeName=='base64url'){
		urlS='/selfapi/dzzz/humanSocietyPDF.do?';
		urlS=urlS+urlone+base64type;
	}
	return urlS;
}

