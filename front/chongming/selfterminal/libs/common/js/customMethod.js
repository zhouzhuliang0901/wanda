(function(global) {
	function PROMISE_METHOD() {

	};
	PROMISE_METHOD.fetchGet = function(method, data) {
		//get jsonp
		return new Promise(function(resolve, reject) {
			$.ajax({
				url: $.getConfigMsg.preUrl + method,
				type: "get",
				dataType: "jsonp",
				jsonp: "jsonpCallback",
				data: data,
				success: function(data) {
					resolve(data);
				},
				error: function(err) {
					reject("数据请求出错:" + err);
				}
			});
		});
	};
	PROMISE_METHOD.fetchPost = function(method, data) {
		//post
		return new Promise(function(resolve, reject) {
			$.ajax({
				url: $.getConfigMsg.preUrl + method,
				type: "post",
				dataType: "json",
				data: data,
				success: function(data) {
					resolve(data);
				},
				error: function(err) {
					reject("上传请求出错:" + JSON.stringify(err));
				}
			});
		});
	};
	PROMISE_METHOD.imageReSaveGray = function(targetName) {
		//将图片存为灰度图片
		return new Promise(function(resolve, reject) {
			OcxControl.reSaveGray(
				targetName,
				function(res) {
					resolve(res);
				},
				function(err) {
					reject(err);
				}
			);
		});
	};
	PROMISE_METHOD.httpDownImage = function(ImgUrl, targetName) {
		return new Promise(function(resolve, reject) {
			$.device.httpDownload(
				ImgUrl,
				targetName,
				function(c, t) {},
				function(result) {
					resolve(targetName);
				},
				function(err) {
					reject("图片下载错误:" + err);
				}
			);
		});
	};
	PROMISE_METHOD.getIdCardInfo = function() {
		//刷身份证 返回身份证信息和图片
		return new Promise(function(resolve, reject) {
			if(!OCX_idCard.attachEvent) {
				reject("身份证控件导入错误！");
			} else {
				setTimeout(function() {
					OcxControl.idCardRead(
						function(dataObj) {
							OcxControl.mergeImages({
									frontImgfile: "d:/Front.bmp",
									backImgfile: "d:/Back.bmp",
									targetFile: "d:/merge.bmp"
								},
								function(res) {
									resolve(dataObj);
								},
								function(err) {
									reject("合并身份证图片出错:" + err);
								}
							);
						},
						function(err) {
							debugger
							reject("身份证读取错误:" + err);
						}
					);
				}, 300);
			}
		});
	};
	PROMISE_METHOD.mergeIdCardImages = function() {
		//合并身份证图片
		return new Promise(function(resolve, reject) {
			OcxControl.mergeImages({
					frontImgfile: "d:/Front.bmp",
					backImgfile: "d:/Back.bmp",
					targetFile: "d:/merge.bmp"
				},
				function(res) {
					resolve(res);
				},
				function(err) {
					reject(err);
				}
			);
		});
	};
	PROMISE_METHOD.getQrCodeInfo = function() {
		//扫二维码
		return new Promise(function(resolve, reject) {
			if(!OCX_Barcode.attachEvent) {
				reject("二维码控件导入错误！");
			} else {
				setTimeout(function() {
					OcxControl.Barcode(
						function(value) {
							resolve(value);
						},
						function(err) {
							reject("二维码读取错误:" + err);
						}
					);
				}, 300);
			}
		});
	};
	PROMISE_METHOD.imagesStartLoad = function(imageArr) {
		return new Promise(function(resolve, reject) {
			var current = 0;
			if(!imageArr instanceof Array) {
				reject("加载失败！");
			}

			function load(index) {
				var Img = new Image();
				Img.src = imageArr[index];
				Img.onload = function() {
					current++;
					console.log("success")
					console.log(Img.src)

					if(current <= imageArr.length - 1) {
						load(current);
					} else {
						resolve("加载完成!");
					}
				};
				Img.onerror = function() {
					current++;
					console.log("err")
					console.log(Img.src)
					if(current <= imageArr.length - 1) {
						load(current);
					} else {
						resolve("加载失败!");
					}
				};
			}
			load();
		});
	};
	global.PROMISE_METHOD = PROMISE_METHOD;
})(window);