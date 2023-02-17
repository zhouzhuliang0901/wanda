app.controller("mainController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.iskeyshow = $.getConfigMsg.iskeyshow;
	data = {};
});
//基本控制器
app.controller("baseController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.frontImg = data.frontImg;
	$scope.backImg = data.backImg;
	$scope.currentImg = data.frontImg;
	$scope.type = 'front';
	$scope.tabImg = function(type) {
		$scope.type = type;
		if($scope.type == 'front') {
			$scope.currentImg = $scope.frontImg;
		} else if($scope.type === "back") {
			$scope.currentImg = $scope.backImg;
		}
	};
	//打印证照
	$scope.printBasePhoto = function() {
		if($scope.currentImg.indexOf("http") > -1) {
			$location.path("/pwait").search({
				imageType: "licensePrint"
			}); //直接打印
		} else {
			$location.path("/pwait").search({
				imageType: "idcard"
			}); //直接打印
		}
	};

});
//扩展控制器
app.controller("extController", function($scope, $route, $http, $location, data, $timeout, customFetch) {
	if(data.ocxIsOpen){
		angular.element(document).ready(function () {
        	OcxControl.scanClose(null,null);
        	  })
	}
	$scope.currentPage = 1;
	$scope.totalPages = 1;
	//隐藏删除按钮
	$scope.btnDeleted = false;
	$scope.showImgAdress = "../libs/common/images/failToLoad.png";
	$scope.isEmpty = true;
	$scope.idCardName = data.dataName;
	$scope.dataCode = data.dataCode;
	$scope.dataSource = data.dataSource;
//	console.log("extController==111==data==="+JSON.stringify(data));
//	console.log("extController====data.dataName==="+JSON.stringify(data.dataName));
//	console.log("extController====data.dataCode==="+JSON.stringify(data.dataCode));
	$scope.getPersonalData = function() {
		console.log("extController=======getLicenseStuffList.do")
		var httpConfig = {
//			username: "yun",
//			password: "04b34557c2110962",
			jsonpprifx: "JSON_CALLBACK",
			stName: $scope.idCardName,
			stIdNo: $scope.dataCode,
			type: "0"
		};					
		$.ajax({
  			type: "POST",
			url: $.getConfigMsg.declareUrl + "/aci/autoterminal/archives/getLicenseStuffList.do",
//			url: "http://180.169.7.194:8080/ac-product/aci/autoterminal/archives/getLicenseStuffList.do",
  			data: {
//				jsonp: "jsonpcallback",
				jsonpprifx: "JSON_CALLBACK",
				stName: $scope.idCardName,
				stIdNo: $scope.dataCode,
				type: "0"
			},
  			dataType: "json",
  			success: function (dataJsonp) {
				if(!dataJsonp[0]) {
					dataJsonp == "0";
					return;
				}
				$scope.isEmpty = false;
				$scope.personalData = $scope.filterWay(dataJsonp);
				$scope.totalPages = Math.ceil($scope.personalData.length / 3);
				$scope.imgContainerStyle = {
					width: ($scope.personalData.length * 240) + "px",
					left: (($scope.currentPage - 1) * 700) + "px"
				};
				for (var i = 0;i < $scope.personalData.length;i++) {
					$scope.personalData[i].imageUrl = $.getConfigMsg.declareUrl+$scope.personalData[i].imageUrl;
				}
				$scope.showImgAdress = $scope.personalData[0].imageUrl;
				$scope.btnDeleted = false;
				$scope.$apply();
  			},
  			error: function (err) {
				alert("err="+JSON.stringify(err)); 
  			}
	  	});
	};
	//下一页
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			$scope.imgContainerStyle = {
				width: ($scope.personalData.length * 240) + "px",
				left: (($scope.currentPage - 1) * -720) + "px"
			};
		};
	};
	//上一页
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			$scope.imgContainerStyle = {
				width: ($scope.personalData.length * 240) + "px",
				left: (($scope.currentPage - 1) * -720) + "px"
			};
		}
	};
	$scope.getPersonalData();
	
	//获取材料 （过滤身份证照方法）
	$scope.filterWay = function(array) {
		for(var i = 0; i < array.length; i++) {
			if(array[i].stIdnoCode === "idcard") {
				array.splice(i, 1)
			}
		}
		return array;
	};
	//选中某份材料并获取其ID
	$scope.imgActive = 0;
	$scope.checkedIt = function(i, index) {
		//选中出现红色边框的样式
		if(index == $scope.imgActive) {
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			return;
		}
		var imgId = i.stPersonalDocument;
		$scope.imgActive = index;
		//选中出现 删除按钮
		$scope.showImgAdress = i.imageUrl;
		data.materialId = imgId; //备用删除
		data.imgSrc = i.imageUrl; //存储打印图片
		$scope.licenseName = i.stLicenseName;
		$scope.btnDeleted = true;
	};
	//删除材料
	$scope.deletedPhoto = function() {
		var httpConfig = {
			params: {
				username: "yun",
				password: "04b34557c2110962",
				jsonpprifx: "JSON_CALLBACK",
				stPersonalDocument: data.materialId
			}
		};
		layer.open({
			content: '您确定要删除此材料吗？',
			btn: ['确定', '取消'],
			yes: function(index) {
				$http.jsonp($.getConfigMsg.declareUrl + "/aci/autoterminal/archives/delWorkLicenseStuffById.do", httpConfig)
//				$http.jsonp("http://180.169.7.194:8080/ac-product/aci/autoterminal/archives/delWorkLicenseStuffById.do", httpConfig)
					.success(function(dataJsonp) {
						if(dataJsonp == 1) {
							layer.msg("删除成功", {
								time: 300
							});
						} else {
							layer.msg("删除失败", {
								time: 300
							});
						}
					});
				$scope.btnDeleted = false;
				$route.reload();
				layer.close(index);

			},
			no: function(index) {
				layer.close(index);
			}
		});
	};
	//上传材料
	$scope.uploadNewPhoto = function() {
		data.uploadType = 0;
		$timeout(function() {
			$location.path("/tphoto");
		}, 1);
	};
	//打印材料
	$scope.printBasePhoto = function() {
		if(data.materialId != null && data.materialId != undefined) {
			PROMISE_METHOD.httpDownImage(data.imgSrc, "c:/materia.jpg")
				.then(function(file) {
					$location.path("/pwait").search({
						printType: "img",
						imgfilepath: "c:/materia.jpg"
					}); //直接打印
				})
				.catch(function(err) {
					console.log(err);
					layer.msg("下载文档失败");
				})
		} else {
			layer.msg("需要选中图片才能打印", {
				time: 300
			});
		}
	}
});
//刷身份证控制器
app.controller("idcardController", function($scope, $rootScope, $route, $http, $location, data, $timeout) {
	console.log("idcardController=====333======"+JSON.stringify(data));
	//获取身份证信息
	data.licenseGather = undefined;
	$scope.readIdCard = function() {
		PROMISE_METHOD.getIdCardInfo()
			.then(function(dataObj) {
				console.log("idcardController===33====dataObj=" + JSON.stringify(dataObj));
				var list = JSON.parse(dataObj.identityInfo);
				// 格式化日期方法的参数 pattern
				var pattern = /(\d{4})(\d{2})(\d{2})/;
				data.dataSource = 'idcard';
				data.idCardNumber = list.Code;
				data.userName = list.Name;
				// 格式化日期
				data.ValidPeriod = list.ValidPeriod.split("-");
				data.startDay = data.ValidPeriod[0].replace(pattern, '$1-$2-$3');
				data.endDay = data.ValidPeriod[1].replace(pattern, '$1-$2-$3').trim();
				data.frontImg = "data:image/png;base64," + dataObj.frontImg;
				data.backImg = "data:image/png;base64," + dataObj.backImg;
				data.portrait = dataObj.portrait
				data.dataNumber = list.Code;
				data.dataName = list.Name;
				data.dataCode = list.Code;
				
				data.idCardInfo = list;
//				console.log("data.dataName==" + data.dataName);
//				console.log("data.dataCode==" + data.dataCode);
//				console.log("data.startDay==" + data.startDay);
//				console.log("data.endDay==" + data.endDay);
				if(data.dataType == '0') {
					$rootScope.personType = "个人";
				} else {
					$rootScope.personType = "法人";
				}
				$timeout(function() {
					//开始人脸识别！
					$location.path("/rlsb");
				}, 100);
			})
			.catch(function(err) {
				console.log(err);
			});
	};
	$scope.readCitizenCloud = function() {
		PROMISE_METHOD.getQrCodeInfo()
			.then(function(code) {
				return new Promise(function(resolve, reject) {
					data.dataSource = 'code';
					var maxLoginCount = 3;
					var httpConfig = {
						codeParam: code,
						jsonpCallback: "JSON_CALLBACK",
						using: "",
						lzAddress: "",
						machineId: $.config.get('uniqueId'),
						itemName: "",
						itemCode: "",
						bussinessCode: ""
					};

					function _login() {
						$http.jsonp($.getConfigMsg.declareUrl + "/aci/window/getQrCodeInfoByElectronicCert.do", {
					//	$http.jsonp("http://180.169.7.194:8080/ac-product/aci/window/getQrCodeInfoByElectronicCert.do", {
								params: httpConfig
							})
							.success(function(dataJsonp) {

								resolve(dataJsonp);
							})
							.error(function(err) {
								if(maxLoginCount > 0) {
									//最多maxLoginCount次重新登录
									--maxLoginCount;
									$timeout(function() {
										_login();
									}, 500);
								} else {
									reject("接口出错：" + err);
								}
							});
					}
					_login();
				})
			})
			.then(function(dataObj) {
				var idcardInfo = dataObj.result.data;
				console.log("内容是：" + JSON.stringify(dataObj));
				if(data.dataType == '0') {
					if(dataObj.url == "") {
						layer.msg("未识别到证照信息！", {
							time: 5000
						});
						$route.reload();
						throw Error("未识别到证照信息！");
					}
					if(dataObj.code !== idcardInfo.idcard) {
						layer.msg("二维码类型错误，请扫描市民亮证个人二维码", {
							time: 2000
						});
						$route.reload();
						throw Error("二维码类型错误，请扫描市民亮证个人二维码");
					}
				} else {
					if(dataObj.code == idcardInfo.idcard) {
						layer.msg("未识别到您的企业信息，请确认二维码正确后重新扫描！", {
							time: 5000
						});
						$route.reload();
						throw Error("未识别到您的企业信息，请确认二维码正确后重新扫描！");
					}
				}
				data.userName = idcardInfo.realname;
				data.idCardNumber = idcardInfo.idcard;

				data.dataName = idcardInfo.realname;
				data.dataCode = dataObj.code;

				data.startDay = idcardInfo.VALIDSTARTDAY;
				data.endDay = idcardInfo.VALIDENDDAY;

				localStorage.setItem("portrait", $.getConfigMsg.declareUrl + dataObj.url);
				return new Promise(function(resolve, reject) {
					resolve("licenseLibrary");
				})
			})
			.then(function(fileName) {
				data.backImg = data.frontImg = localStorage.getItem("portrait");
				if(data.dataType == '0') {

					$rootScope.personType = "个人";

				} else {

					$rootScope.personType = "法人";

				}

				$location.path("/licenseLibrary");
				$scope.$apply();

				return new Promise(function(resolve, reject) {
					resolve("licenseLibrary");
				});
			})
			.catch(function(err) {

				console.log(err);

			})
	};
	$scope.readCitizenCloud.isOpen = true;
	// $scope.cloudActive = true;
	if($location.search().from == 'legalPerson') {
		$scope.readIdCard.isClose = true;
		data.dataType = "1";
		$scope.readCitizenCloud();
	} else {
		data.dataType = '0';
		$scope.readIdCard();
		// $scope.currentLogin = 'cloud'
		// $scope.readCitizenCloud();
	}

//跳过666666=========================
//	if(data.dataType == '0') {
//
//		$rootScope.personType = "个人";
//
//	} else {
//
//		$rootScope.personType = "法人";
//
//	}
//
//	$location.path("/licenseLibrary");
//666666end=================
});


app.controller("rlsbController", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName == undefined ? "身份证登录":data.itemName;
	try {
		if(name.length > 10) {
			name = name.slice(0, 10) + '...'
		}
		$scope.itemName = name;
	} catch(e) {}
	$scope.idCardInfo = data.idCardInfo;
	$scope.idCardInfo.idCardName = data.dataName;
	$scope.idCardInfo.idCardNum = data.dataCode;
	$scope.HeadImg = data.portrait;
//	console.log("rlsbController==$scope.idCardInfo==:"+JSON.stringify($scope.idCardInfo))
//	console.log("rlsbController==data.idCardInfo====="+JSON.stringify(data.idCardInfo))
//	console.log("rlsbController==$scope.HeadImg==:"+JSON.stringify($scope.HeadImg))
	$scope.getResult = function() {
		console.log("getResult方法调用了============");
		$location.path("/licenseLibrary");
		$scope.$apply();
	}
});


//拍照控制器
app.controller("tphotoController", function($scope, $route, $http, $location, data, $timeout, customFetch) {
	 //显示高拍仪
    angular.element(document).ready(function () {
        OcxControl.scanOpen({
            left: 850,
            top: 310,
            height: 525,
            width: 742.5
        },function(res){
        	//定义如果直接返回，关闭高拍仪参数，在extController里面使用判断
        	data.ocxIsOpen = true;
            console.log(res)
        },function(err){
            console.log(err)
        })
    });
	//拍扩展材料情况
	$scope.savedPhoto = null; //拍摄图片容器
	$scope.capture = false; //控制拍照按钮和上传或重拍按钮
	$scope.hint = "请将材料放置在扫描区点击照相键"; //右边提示信息
	$scope.imageData = null;
	$scope.userName = data.userName;
	$scope.idCardNumber = data.idCardNumber;
	$scope.photograph = function() {
		$scope.capture = true; //关掉拍摄按钮
		OcxControl.scanSave(function(base64) {
			$scope.picture = "data:image/jpg;base64," + base64;
			$scope.imageData = base64;
			$scope.hint = "请预览下方照片，您是否确认保存";
			$scope.savedPhoto = true;
		});
	};
	$scope.prev = function() {
		$route.reload();
	};
	$scope.next = function() {
		layer.prompt({
			title: '填写材料名称',
			formType: 0
		}, function(licenceName, index) {
			layer.close(index);
//			console.log("tphotoController===");
			$.ajax({
				type: "post",
				url: $.getConfigMsg.declareUrl + "/aci/autoterminal/archives/saveLicenseStuff.do",  
//				url: "http://180.169.7.194:8080/ac-product/aci/autoterminal/archives/saveLicenseStuff.do",//测试使用
				dataType: "json",
				data: {
					username: "yun",
					password: "04b34557c2110962",
					stName: data.dataName,
					stIdNo: data.dataCode,
					stLicenseName: licenceName,
					type: data.dataType,
					stIdnoCode: "",
					FileData: $scope.imageData,
				},
				success: function(res) {
//					console.log("tphotoController===success");
//					console.log("tphotoController===success=====res"+JSON.stringify(res));
					layer.msg('上传成功：' + res, {
						time: 500,
					});
//					console.log("tphotoController===success=====data.dataType"+JSON.stringify(data.dataType));
					data.dataType == '0' ? $location.path("/ext") : $location.path("/space");
					$scope.$apply();
				},
				error: function(err) {
//					console.log("tphotoController===error");
					console.log("err:" + err);
					data.dataType == '0' ? $location.path("/ext") : $location.path("/space");
					$scope.$apply();
				}
			});
		});
	};
	//拍证照库
});
app.controller("licenseLibraryController", function(
	$scope,
	$route,
	$http,
	$location,
	data,
	$timeout
) {
	console.log("licenseLibraryController来了111111111")
	$scope.currentPage = 1;
	$scope.totalPages = 1;
	$scope.isLoading = true; //加载状态
	$scope.loadFailed = false; //是不是加载失败
	//隐藏删除按钮
	$scope.btnDeleted = false;
	$scope.showImgAdress = "../libs/common/images/failToLoad.png";
	$scope.configUrl = $.getConfigMsg.declareUrl;
	$scope.dataType = data.dataType;
	console.log("$scope.dataType========" + $scope.dataType)
	$scope.dataNumber = data.dataCode;
	$scope.dataSource = data.dataSource;
	$scope.imagesBase64Con = []; //证照base64
	$scope.personalData = []; //
	$scope.imgLoadedCon = []; //证照url
	$scope.isEmpty = false; //是不是为空
	$scope.imgActive = null; //是不是选中 <弃用>
	$scope.reloadData = function() {
		$route.reload();
	};
	$scope.getPersonalData = function() {
		var httpConfig = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: $scope.dataNumber,
			type: $scope.dataType,
			machineId: $.config.get('uniqueId'),
			itemName: '',
			itemCode: '',
			businessCode: '',
			startDay: data.startDay || '',
			endDay: data.endDay || '',
			name: data.userName || data.dataName || '',
			licenseType: 'cert'
		};
		var httpConfig1 = {
			jsonpCallback: "JSON_CALLBACK",
			certNo: '330621198805270014',
			type: '0',
			machineId: $.config.get('uniqueId'),
			itemName: '',
			itemCode: '',
			businessCode: '',
			startDay: '2021-10-09',
			endDay: '2041-10-09',
			name: '郭戌',
			licenseType: 'cert'
		};

		function fetchImageUrl() {
			console.log("fetchImageUrl=======")
			$http.jsonp($.getConfigMsg.declareUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(result) {
					console.log("fetchImageUrl=======success")
					if(!result[0]) {
						result == "0";
						$scope.isEmpty = true;
						return;
					}
					$scope.isEmpty = false;
					$scope.personalData = result;
					$scope.totalPages = Math.ceil($scope.personalData.length / 3);
					$scope.imgContainerStyle = {
						width: $scope.personalData.length * 240 + "px",
						left: ($scope.currentPage - 1) * 700 + "px"
					};

					for(var i = 0; i < $scope.personalData.length; i++) {
						$scope.imgLoadedCon.push($scope.configUrl + $scope.personalData[i].pictureUrl);
					}
					data.imgSrc = $scope.imgLoadedCon[0];
					fetchBase64();

				})
				.error(function(err) {
					console.log("fetchImageUrl=======error")
					console.log("fetchImageUrl=======error----" + "err:====" + err);
					$scope.loadFailed = true;
				});
		}
		var maxFetchCount = 10;

		function fetchBase64() {
			console.log("fetchBase64=========")
			$http.jsonp($.getConfigMsg.declareUrl + "/aci/autoterminal/dzzz/queryCertBaseData.do", {
					params: httpConfig
				})
				.success(function(result) {
					console.log("fetchBase64=========success")
					if(!result[0]) {
						result == "0";
						$scope.isEmpty = true;
						$scope.btnDeleted = false;
						return;
					}
					console.log(result.length);
					$scope.totalPages = Math.ceil(result.length / 6);
					$scope.showImgAdress = result[0].pictureUrlForBytes;
					for(var i = 0; i < result.length; i++) {
						console.log(i + "index: " + result[i].pictureUrlForBytes)
						if(!(result[i].derivePictureUrlForBytes == '')) {
							$scope.imagesBase64Con.push($.getConfigMsg.declareUrl + result[i].pictureUrlForBytes);
							$scope.imgLoadedCon.push($.getConfigMsg.declareUrl + result[i].derivePictureUrlForBytes);
						} else {
							// $scope.imgLoadedCon.splice(i, 1);
						}
					}
					data.licenseGather = {}; //证照集合
					data.licenseGather.licenseUrl = $scope.imgLoadedCon;
					data.licenseGather.licenseBase64 = $scope.imagesBase64Con;
					console.log("data.licenseGather.licenseUrl = " + $scope.imgLoadedCon);
					console.log("data.licenseGather.licenseBase64 = " + $scope.imagesBase64Con);
					$scope.isLoading = false;
					$scope.imgActive = 0;
				})
				.error(function(err) {
					console.log("fetchBase64=========error")
					if(maxFetchCount > 0) {
						fetchBase64();
						--maxFetchCount;
					}
					console.log("fetchBase64=========err===" + err);
					$scope.loadFailed = true;
				})
		}
		fetchBase64();
		// fetchImageUrl();
	};

	//下一页
	$scope.nextPage = function() {
		console.log("点击了下一页"+$scope.currentPage);
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			$scope.imgContainerStyle = {
				width: $scope.personalData.length * 240 + "px",
				left: ($scope.currentPage - 1) * -720 + "px"
			};
		}
	};
	//上一页
	$scope.prevPage = function() {
		console.log("点击了上一页"+$scope.currentPage);
		if($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			$scope.imgContainerStyle = {
				width: $scope.personalData.length * 240 + "px",
				left: ($scope.currentPage - 1) * -720 + "px"
			};
		}
	};
	$scope.isLarge = false;
	$scope.largeImage = '';
	$scope.takeTab = function(url) {
		data.imgSrc = url; //存储打印图片
		data.reviewLicense = url;
		$location.path("/review");
	};
	$scope.viewImage = function(url, index) {
		$scope.isLarge = true;
		$scope.largeImage = url;
		$scope.imgActive = index;
	}
	$scope.closeViewLarge = function() {
		$scope.isLarge = false;
		console.log($scope.isLarge)
	};
	console.log("data.licenseGather:=====" + data.licenseGather)
	if(data.licenseGather == undefined) { //检查是否缓存证照数据
		$scope.getPersonalData();
	} else {
		$scope.isLoading = false;
		$scope.imgLoadedCon = data.licenseGather.licenseUrl;
		$scope.imagesBase64Con = data.licenseGather.licenseBase64;
	}
	//选中某份材料并获取其ID
	$scope.btnDeleted = true;
	$scope.checkedIt = function(url, index) {
		//选中出现红色边框的样式
		if(index == $scope.imgActive) {
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			return;
		}
		$scope.imgActive = index;
		//选中出现 删除按钮
		$scope.showImgAdress = url;
		data.imgSrc = url; //存储打印图片
		$scope.btnDeleted = true;
	};
	//打印材料
	$scope.printBasePhoto = function() {
		if(data.imgSrc != null && data.imgSrc != undefined) {
			var lodop = $.device.printGetLodop();
			alert(data.dataType)
			if(data.dataType == '1') {
				lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + data.imgSrc + "'>");
			} else {
				// lodop.ADD_PRINT_IMAGE(550, 550, 600, 1300, "<img border='0' src='" + data.imgSrc + "'>");
				// lodop.ADD_PRINT_SETUP_BKIMG("<img border='0' src='" + data.imgSrc + "'>");
				// lodop.SET_SHOW_MODE("BKIMG_PRINT", 1);
			}
			lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
			lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
			lodop.SET_PRINT_STYLEA(0, "Angle", 50);
			lodop.SET_PRINT_STYLEA(0, "Repeat", true);
			lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式

			lodop.PREVIEW();
			// lodop.PRINT();
			$location.path("/pwait");
		} else {
			layer.msg("需要选中图片才能打印", {
				time: 300
			});
		}
	}
});

app.controller("reviewController", function($rootScope, $rootScope, $scope, $route, $http, $location, $timeout, data) {
	$scope.reviewLicense = data.reviewLicense || "../libs/common/images/failToLoad.png";
	$scope.$on("$routeChangeSuccess", function(ev, c, p) {
		$scope.formType = p.$$route.originalPath;
	})
	$scope.isLarge = false;
	$scope.openViewLarge = function() {
		$scope.isLarge = true;
	}
	$scope.closeViewLarge = function() {
		$scope.isLarge = false;
	}
	$scope.printBasePhoto = function() {
		if(data.imgSrc != null && data.imgSrc != undefined) {
			var lodop = $.device.printGetLodop();
			if(data.dataType == '1') {
				lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + data.imgSrc + "'>");
			} else {
				lodop.ADD_PRINT_IMAGE(240, 220, 330, 800, "<img border='0' src='" + data.imgSrc + "'>");
			}
			lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
			lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
			lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
			lodop.SET_PRINT_STYLEA(0, "Angle", 50);
			lodop.SET_PRINT_STYLEA(0, "Repeat", true);
			// lodop.PREVIEW();
			lodop.PRINT();
			$location.path("/pwait").search({
				whereToGo: $scope.formType
			});
		} else {
			layer.msg("需要选中图片才能打印", {
				time: 300
			});
		}
	}
});
//法人
app.controller("keyController", function($rootScope, $rootScope, $scope, $route, $http, $location, $timeout, data) {
	$scope.password = "";
	$scope.goCitizen = function() {
		$location.path("/idcard").search({
			from: "legalPerson"
		})
	};
	$scope.validate = function() {
		if(!$scope.password) {
			layer.msg("密码不能为空！");
			return;
		}
		/* Change the path and password below */
		$scope.strpripath = "com1";
		$scope.strcertpath = "com1";
		$scope.strcertchainpath = "com1";
		// 请输入证书验证参数
		$scope.ConfigurationNum = parseInt("1");
		// 请输入USB设备参数
		$scope.DevNumber = parseInt("10");

		//初始化函数
		SafeEngineCtl.SEH_InitialSession($scope.DevNumber, $scope.strpripath, $scope.password, 0, $scope.DevNumber, $scope.strpripath, "");
		if(SafeEngineCtl.ErrorCode != 0) {
			layer.msg("验证不通过，请重新输入验证密码或者插入key");
			return;
		}

		//配置参数
		SafeEngineCtl.SEH_SetConfiguration($scope.ConfigurationNum);
		if(SafeEngineCtl.ErrorCode != 0) {
			layer.msg("验证不通过，请重新输入验证密码或者插入key");
			SafeEngineCtl.SEH_ClearSession();
			return;
		}

		//获取证书内容
		$scope.strCert = SafeEngineCtl.SEH_GetSelfCertificate($scope.DevNumber, $scope.strcertpath, "");
		if(SafeEngineCtl.ErrorCode != 0) {
			layer.msg("验证不通过，请重新输入验证密码或者插入key");
			return;
		}

		//获取证书细目	14
		//获取证书中的企业名称
		$scope.companyName = SafeEngineCtl.SEH_GetCertDetail($scope.strCert, 14);
		//获取证书中企业编码
		$scope.stIdNo = SafeEngineCtl.SEH_GetCertInfoByOID($scope.strCert, "1.2.156.112570.11.210");

		SafeEngineCtl.SEH_ClearSession();
		if($scope.companyName != undefined && $scope.companyName != null && $scope.companyName != "" && $scope.companyName != " ") {
			data.companyName = $scope.companyName;
			data.companyStIdNo = $scope.stIdNo;
			data.dataType = '1';
			data.dataName = $scope.companyName;
			data.dataCode = $scope.stIdNo;
			$rootScope.personType = '法人';
			$location.path("/space");
		}
	};
});
app.controller("spaceController", function($rootScope, $scope, $route, $http, $location, $timeout, data, customFetch) {

	$scope.currentPage = 1;
	//隐藏删除按钮
	$scope.isEmpty = true;
	$scope.btnDeleted = false;
//	$scope.personalData = [];
	$scope.showImgAdress = "../libs/common/images/failToLoad.png";
	$scope.dataName = data.dataName;
	$scope.dataCode = data.dataCode;
	$scope.getPersonalData = function() {
		console.log("spaceController=======getLicenseStuffList.do")
		$.ajax({
  			type: "POST",
			url: $.getConfigMsg.declareUrl + "/aci/autoterminal/archives/getLicenseStuffList.do",
  			data: {
				jsonp: "jsonpcallback",
				stName: $scope.dataName,
				stIdNo: $scope.dataCode,
				type: data.dataType
			},
  			dataType: "json",
  			success: function (dataJsonp) {
				if(!dataJsonp[0]) {
					dataJsonp == "0";
					return;
				}
				$scope.isEmpty = false;
				$scope.$apply();
				$scope.personalData = $scope.filterWay(dataJsonp);
				$scope.totalPages = Math.ceil($scope.personalData.length / 3);
				$scope.imgContainerStyle = {
					width: ($scope.personalData.length * 240) + "px",
					left: (($scope.currentPage - 1) * 700) + "px"
				};
				for (var i = 0;i < $scope.personalData.length;i++) {
					$scope.personalData[i].imageUrl = $.getConfigMsg.declareUrl+$scope.personalData[i].imageUrl;
				}
				$scope.showImgAdress = $scope.personalData[0].imageUrl;
				$scope.btnDeleted = false;
				$scope.$apply();
  			},
  			error: function (err) {
				alert("err"); 
  			}
	  	});
	};
	//获取材料 （过滤身份证照方法）
	$scope.filterWay = function(array) {
		for(var i = 0; i < array.length; i++) {
			if(array[i].stIdnoCode === "idcard") {
				array.splice(i, 1)
			}
		}
		return array;
	};
	//下一页
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.imgContainerStyle = {
				width: ($scope.personalData.length * 240) + "px",
				left: (($scope.currentPage - 1) * -720) + "px",
			};
		};
	};
	//上一页
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.imgContainerStyle = {
				width: ($scope.personalData.length * 240) + "px",
				left: (($scope.currentPage - 1) * -720) + "px"
			};
		}
	};
	$scope.getPersonalData();
	//选中某份材料并获取其ID
	$scope.imgActive = null;
	$scope.checkedIt = function(i, index) {
		//选中出现红色边框的样式
		if(index == $scope.imgActive) {
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			return;
		}
		var imgId = i.stPersonalDocument;
		$scope.imgActive = index;
		//选中出现 删除按钮
		$scope.showImgAdress = i.imageUrl;
		data.materialId = imgId; //备用删除
		data.imgSrc = i.imageUrl; //存储打印图片
		$scope.licenseName = i.stLicenseName;
		$scope.btnDeleted = true;
//		alert(i.address)
	};
	//删除材料
	$scope.deletedPhoto = function() {
		var httpConfig = {
			params: {
				username: "yun",
				password: "04b34557c2110962",
				jsonpprifx: "JSON_CALLBACK",
				stPersonalDocument: data.materialId
			}
		};
		layer.open({
			content: '您确定要删除此材料吗？',
			btn: ['确定', '取消'],
			yes: function(index) {
				$http.jsonp($.getConfigMsg.declareUrl + "/aci/autoterminal/archives/delWorkLicenseStuffById.do", httpConfig)
					.success(function(dataJsonp) {
						if(dataJsonp == 1) {
							layer.msg("删除成功", {
								time: 300
							});
						} else {
							layer.msg("删除失败", {
								time: 300
							});
						}
					});
				$scope.btnDeleted = false;
				$route.reload();
				layer.close(index);
			},
			no: function(index) {
				layer.close(index);
			}
		});
	};
	//上传材料
	$scope.uploadNewPhoto = function() {
		data.uploadType = 1;
		$timeout(function() {
			$location.path("/tphoto");
		}, 1);
	};
	//打印材料
	$scope.printBasePhoto = function() {
		if(data.materialId != null && data.materialId != undefined) {
			PROMISE_METHOD.httpDownImage(data.imgSrc, "c:/materia.jpg")
				.then(function(file) {
					OcxControl.nativePrint(file, 1, "gray", function(res) {});
					$location.path("/pwait");
				})
				.catch(function(err) {
					console.log(err)
				})
		} else {
			layer.msg("需要选中图片才能打印", {
				time: 300
			});
		}
	}
});
