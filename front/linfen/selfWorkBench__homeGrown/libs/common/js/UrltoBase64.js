//width、height调用时传入具体像素值，控制大小 ,不传则默认图像大小
function getBase64Image(img, width, height) {
	var canvas = document.createElement("canvas");
	canvas.width = width ? width : img.width;
	canvas.height = height ? height : img.height;
	var ctx = canvas.getContext("2d");
	ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	var dataURL = canvas.toDataURL();
	return dataURL;
}

function getCanvasBase64(img) {
	var image = new Image();
	//至关重要
	image.crossOrigin = '';
	image.src = img;
	//至关重要
	var deferred = $.Deferred();
	if(img) {
		image.onload = function() {
			deferred.resolve(getBase64Image(image)); //将base64传给done上传处理
			//document.getElementById("container2").appendChild(image);
		}
		return deferred.promise(); //问题要让onload完成后再return sessionStorage['imgTest']
	}
}
