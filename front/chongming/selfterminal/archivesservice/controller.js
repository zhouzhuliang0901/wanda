app.controller("mainController", function(
	$scope,
	$route,
	$http,
	$location,
	data,
	$timeout
) {
	$scope.iskeyshow = $.getConfigMsg.iskeyshow;
});
//基本控制器
app.controller("baseController", function(
	$scope,
	$route,
	$http,
	$location,
	data,
	$timeout
) {
	$scope.frontImg = data.frontImg;
	$scope.backImg = data.backImg;
	$scope.currentImg = data.frontImg;
	$scope.type = "front";
	console.log($scope.currentImg)
	$scope.tabImg = function(type) {
		$scope.type = type;
		if($scope.type == "front") {
			$scope.currentImg = $scope.frontImg;
		} else if($scope.type === "back") {
			$scope.currentImg = $scope.backImg;
		}
	};
	//打印证照
	$scope.printBasePhoto = function() {
		$location.path("/pwait").search({
			imageType: "idcard"
		}); //直接打印
	};
});
//扩展控制器
app.controller("extController", function(
	$scope,
	$route,
	$http,
	$location,
	data,
	$timeout,
	customFetch
) {
	$scope.currentPage = 1;
	$scope.totalPages = 1;
	//隐藏删除按钮
	$scope.btnDeleted = false;
	$scope.showImgAdress = "../libs/common/images/failToLoad.png";
	$scope.configUrl = $.getConfigMsg.preUrl;
	$scope.dataType = data.dataType;
	$scope.isEmpty = true;
	$scope.getPersonalData = function() {
		PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
			fmd: "aci-archives",
			fdo: "getLicenseStuffList",
			stName: data.dataName,
			stIdNo: data.dataNumber,
			type: data.dataType //"0"
		}).then(function(result) {
			console.log(result);
			if(!result[0]) {
				result == "0";
				return;
			}
			$scope.isEmpty = false;
			$scope.personalData = $scope.filterWay(result);

			$scope.totalPages = Math.ceil($scope.personalData.length / 3);
			$scope.imgContainerStyle = {
				width: $scope.personalData.length * 240 + "px",
				left: ($scope.currentPage - 1) * 700 + "px"
			};
			$scope.showImgAdress = $scope.configUrl + $scope.personalData[0].imageUrl;

			$scope.btnDeleted = false;
			$scope.$apply();
		});
	};
	//下一页
	$scope.nextPage = function() {
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
	$scope.getPersonalData();
	//获取材料 （过滤身份证照方法）
	$scope.filterWay = function(array) {
		for(var i = 0; i < array.length; i++) {
			if(array[i].stIdnoCode === "idcard") {
				array.splice(i, 1);
			}
		}
		return array;
	};
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
		$scope.showImgAdress = $scope.configUrl + i.imageUrl;
		data.materialId = imgId; //备用删除
		data.imgSrc = $scope.showImgAdress; //存储打印图片
		$scope.licenseName = i.stFilename;
		$scope.btnDeleted = true;
		console.log(data.imgSrc)
	};
	//删除材料
	$scope.deletedPhoto = function() {
		layer.open({
			content: "您确定要删除此材料吗？",
			btn: ["确定", "取消"],
			yes: function(index) {
				PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
					fmd: "aci-archives",
					fdo: "delWorkLicenseStuffById",
					stPersonalDocument: data.materialId
				}).then(function(result) {
					if(result == 1) {
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
				layer.close(index);
				$route.reload();
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
			PROMISE_METHOD.httpDownImage(data.imgSrc, "d:/licenseImg.jpg")
				.then(function(fileName) {
					return PROMISE_METHOD.imageReSaveGray(fileName);
				})
				.then(function(res) {
					$location.path("/pwait").search({
						imageType: "licensePrint",
					});
					$scope.$apply();
				})
				.catch(function(err) {
					console.log(err)
				})
		} else {
			layer.msg("需要选中图片才能打印", {
				time: 300
			});
		}
	};
});
//市电子证照库
app.controller("licenseLibraryController", function(
	$scope,
	$route,
	$http,
	$location,
	data,
	$timeout
) {
	$scope.currentPage = 1;
	$scope.totalPages = 1;
	//隐藏删除按钮
	$scope.btnDeleted = false;
	$scope.showImgAdress = "../libs/common/images/failToLoad.png";
	$scope.configUrl = $.getConfigMsg.preUrl;
	$scope.dataType = data.dataType;
	$scope.dataNumber = data.idCardNumber;
	$scope.isEmpty = true;
	$scope.imagesBase64Con = [];
	$scope.imgContainer = [];
	$scope.isLoading = true;
	$scope.loadFailed = false;
	$scope.isLarge = false;
	//	$scope.isTestData = "91310104132660027E";
	//		$scope.isTestData = "362330199307205799";
	//		$scope.isTestType = "0";
	$scope.isTestData = undefined;
	$scope.isTestType = undefined;

	$scope.reloadData = function() {
		$route.reload();
	}
	$scope.openLargeView = function() {
		$scope.isLarge = true;
	};
	$scope.closeLargeView = function() {
		$scope.isLarge = false;
	};
	$scope.getPersonalData = function() {

		PROMISE_METHOD.fetchGet("/aci/autoterminal/dzzz/queryCertBaseDatas.do", {
				type: $scope.isTestType || $scope.dataType,
				certNo: $scope.isTestData || $scope.dataNumber,
				machineId: $.config.get('uniqueId'),
				itemName:'',
				itemCode:'',
				businessCode:'',
				startDay: data.startDay||'',
				endDay: data.endDay||'',
				name: data.userName||''
			})
			.then(function(result) {
				console.log(result)
				if(!result[0]) {
					result == "0";
					$scope.isLoading = false;
					$scope.$apply();
					return;
				}

				$scope.showImgAdress = "data:image/jpeg;base64," + result[0].str;
				for(var i = 0; i < result.length; i++) {

					if(!(result[i].str == '')) {
						$scope.imagesBase64Con.push("data:image/jpeg;base64," + result[i].str);
					} else {
						// $scope.imgLoadedCon.splice(i, 1);
					}
				}
				$scope.$apply();
				return PROMISE_METHOD.fetchGet("/aci/autoterminal/dzzz/queryCertBaseData.do", {
					type: $scope.isTestType || $scope.dataType,
					certNo: $scope.isTestData || $scope.dataNumber,
					machineId: $.config.get('uniqueId'),
					itemName:'',
					itemCode:'',
					businessCode:'',
					startDay: data.startDay||'',
					endDay: data.endDay||'',
					name: data.userName||''
				})
			})
			.then(function(result) {
				console.log("license: " + result);
				if(!result[0]) {
					result == "0";
					$scope.btnDeleted = false;

					return;
				}
				$scope.btnDeleted = true;
				$scope.isLoading = false;
				$scope.personalData = result;
				$scope.totalPages = Math.ceil($scope.personalData.length / 3);
				$scope.imgContainerStyle = {
					width: $scope.personalData.length * 240 + "px",
					left: ($scope.currentPage - 1) * 700 + "px"
				};
				for(var i = 0; i < $scope.personalData.length; i++) {
					$scope.imgContainer.push($scope.configUrl + $scope.personalData[i].pictureUrlForBytes);
				}
				$scope.imgActive = 0;
				$scope.isLoading = false;
				$scope.isEmpty = false;

				data.imgSrc = $scope.imgContainer[$scope.imgActive];
				$scope.$apply();
			})
			.catch(function(err) {
				$scope.loadFailed = true;
				console.log(err);
			})

	};
	//下一页
	$scope.nextPage = function() {
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
	$scope.getPersonalData();
	//选中某份材料并获取其ID
	$scope.imgActive = null;
	$scope.checkedIt = function(resource, index) {
		//选中出现红色边框的样式
		if(index == $scope.imgActive) {
			$scope.imgActive = null;
			$scope.btnDeleted = false;
			return;
		}
		$scope.imgActive = index;
		//选中出现 删除按钮
		$scope.showImgAdress = resource;
		data.imgSrc = $scope.imgContainer[index]; //存储打印图片

		$scope.btnDeleted = true;

	};
	//打印材料
	$scope.printBasePhoto = function() {
		if($scope.btnDeleted == true) {

			PROMISE_METHOD.httpDownImage(data.imgSrc, "d:/licenseImg.jpg")
				.then(function(fileName) {
					return PROMISE_METHOD.imageReSaveGray(fileName);
				})
				.then(function(res) {
					$location.path("/pwait").search({
						imageType: "licensePrint",
					});
					$scope.$apply();
				})
				.catch(function(err) {
					alert(err)

					console.log(err)
				})
		} else {
			layer.msg("需要选中图片才能打印", {
				time: 300
			});
		}
	};
});
//刷身份证控制器
app.controller("idcardController", function(
	$scope,
	$route,
	$http,
	$location,
	data,
	$timeout,
	$rootScope
) {

	//获取身份证信息
	$rootScope.isCloud = false;
	$scope.readIdCard = function() {
		$rootScope.isCloud = false;
		PROMISE_METHOD.getIdCardInfo()
			.then(function(dataObj) {
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
				data.dataNumber = list.Code;
				data.dataName = list.Name;
				data.dataCode = list.Code;
				if(data.dataType == '0') {

					$rootScope.personType = "个人";

				} else {

					$rootScope.personType = "法人";

				}

				$location.path("/licenseLibrary");
				$scope.$apply();
			})
			.catch(function(err) {
				console.log(err);
			});
	};
	$scope.readCitizenCloud = function() {
		$rootScope.isCloud = true;
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
						$http.jsonp($.getConfigMsg.preUrl + "/aci/window/getQrCodeInfoByElectronicCert.do", {
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
				console.log(dataObj)
				var idcardInfo = dataObj.result.data;
				console.log(JSON.stringify(dataObj));
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
				
				// 新增日期
				data.startDay = idcardInfo.VALIDSTARTDAY;
		    	data.endDay = idcardInfo.VALIDENDDAY;

				localStorage.setItem("portrait", $.getConfigMsg.preUrl + dataObj.url);
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

	// 
	if($location.search().from == 'legalPerson') {
		$scope.readIdCard.isOpen = true;
		data.dataType = "1";
		$rootScope.currentType = 'cloud';
		$scope.readCitizenCloud();
	} else {
		data.dataType = '0';
		$scope.readIdCard();
		// $scope.currentLogin = 'cloud'
		// $scope.readCitizenCloud();
	}
});
//拍照控制器
app.controller("tphotoController", function(
	$scope,
	$route,
	$http,
	$location,
	data,
	$timeout,
	customFetch
) {
	//显示高拍仪
	angular.element(document).ready(function() {
		OcxControl.scanOpen({
			left: 850,
			top: 100,
			height: 525,
			width: 742.5
		});
	}); //拍扩展材料情况
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
				title: "填写材料名称",
				formType: 0
			},
			function(licenceName, index) {
				layer.close(index);
				PROMISE_METHOD.fetchPost("/aci/autoterminal/forward.do", {
						fmd: "aci-archives",
						fdo: "saveLicenseStuff",
						stName: data.dataName,
						stIdNo: data.dataNumber,
						stLicenseName: licenceName,
						type: data.dataType,
						stApplyId: "",
						stShareCode: "",
						stPersonalDocument: "",
						FileData: $scope.imageData
					})
					.then(function(result) {
						layer.msg("上传成功：" + result, {
							time: 500
						});
						$location.path("/ext");
						$scope.$apply();
					})
					.catch(function(err) {
						console.log("err:" + err);
						layer.msg("上传完成：", {
							time: 500
						});
						$location.path("/ext");
						$scope.$apply();
					});
			}
		);
	};
	//拍证照库
});
//法人
app.controller("keyController", function(
	$rootScope,
	$scope,
	$route,
	$http,
	$location,
	$timeout,
	data
) {
	$scope.password = "";
	$scope.goCitizen = function() {
		$location.path("/idcard").search({
			from: 'legalPerson'
		});
	}
	$scope.validate1 = function() {
		alert("进入fairy测试环境");
		data.dataName = "fairy测试公司";
		data.dataNumber = "91310114786744772J";
		data.dataType = '1';
		$location.path("/space");
	}
	$scope.validate = function() {
		if(!$scope.password) {
			alert("密码不能为空！");
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
		SafeEngineCtl.SEH_InitialSession(
			$scope.DevNumber,
			$scope.strpripath,
			$scope.password,
			0,
			$scope.DevNumber,
			$scope.strpripath,
			""
		);
		console.log(SafeEngineCtl.ErrorCode)
		console.log($scope.password)
		if(SafeEngineCtl.ErrorCode != 0) {
			alert("验证不通过，请重新输入验证密码或者插入key");
			return;
		}

		//配置参数
		SafeEngineCtl.SEH_SetConfiguration($scope.ConfigurationNum);
		if(SafeEngineCtl.ErrorCode != 0) {
			alert("验证不通过，请重新输入验证密码或者插入key");
			SafeEngineCtl.SEH_ClearSession();
			return;
		}

		//获取证书内容
		$scope.strCert = SafeEngineCtl.SEH_GetSelfCertificate(
			$scope.DevNumber,
			$scope.strcertpath,
			""
		);
		if(SafeEngineCtl.ErrorCode != 0) {
			alert("验证不通过，请重新输入验证密码或者插入key");
			return;
		}

		//获取证书细目	14
		//获取证书中的企业名称
		$scope.companyName = SafeEngineCtl.SEH_GetCertDetail($scope.strCert, 14);
		//获取证书中企业编码
		$scope.stIdNo = SafeEngineCtl.SEH_GetCertInfoByOID(
			$scope.strCert,
			"1.2.156.112570.11.210"
		);

		SafeEngineCtl.SEH_ClearSession();
		if(
			$scope.companyName != undefined &&
			$scope.companyName != null &&
			$scope.companyName != "" &&
			$scope.companyName != " "
		) {

			data.dataName = $scope.companyName;
			data.dataNumber = $scope.stIdNo;
			data.dataType = '1';
			$location.path("/space");
		}
	};
});
app.controller("spaceController", function(
	$rootScope,
	$scope,
	$route,
	$http,
	$location,
	$timeout,
	data
) {
	$scope.currentPage = 1;
	//隐藏删除按钮
	$scope.btnDeleted = false;
	$scope.showImgAdress = "../libs/common/images/failToLoad.png";
	$scope.isEmpty = true;
	$scope.getPersonalData = function() {
		PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
				fmd: "aci-archives",
				fdo: "getLicenseStuffList",
				stName: data.dataName,
				stIdNo: data.dataNumber,
				type: "1"
			})
			.then(function(result) {
				console.log(result);
				if(!result[0]) {
					result == "0";
					return;
				}
				$scope.isEmpty = false;
				$scope.personalData = result;
				$scope.totalPages = Math.ceil($scope.personalData.length / 3);
				$scope.imgContainerStyle = {
					width: $scope.personalData.length * 240 + "px",
					left: ($scope.currentPage - 1) * 700 + "px"
				};
				$scope.$apply();
			})
			.catch(function(err) {
				console.log(err)
			})
	};
	//下一页
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			$scope.currentPage++;
			$scope.imgContainerStyle = {
				width: $scope.personalData.length * 240 + "px",
				left: ($scope.currentPage - 1) * -720 + "px"
			};
		}
	};
	//上一页
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.imgContainerStyle = {
				width: $scope.personalData.length * 240 + "px",
				left: ($scope.currentPage - 1) * -720 + "px"
			};
		}
	};
	$scope.getPersonalData();
	//获取材料 （过滤身份证照方法）
	$scope.filterWay = function(array) {
		for(var i = 0; i < array.length; i++) {
			if(array[i].stIdnoCode !== "") {
				array.splice(i, 1);
			}
		}
		return array;
	};
	//选中某份材料并获取其ID
	$scope.imgActive = null;
	$scope.checkedIt = function(i, index) {
		//选中出现红色边框的样式
		var imgId = i.stLicenseId;
		$scope.imgActive = index;
		//选中出现 删除按钮
		$scope.showImgAdress = $scope.configUrl + i.imageUrl;
		data.materialId = imgId; //备用删除
		data.imgSrc = $scope.configUrl + i.imageUrl; //存储打印图片
		$scope.btnDeleted = true;
	};
	//删除材料
	$scope.deletedPhoto = function() {
		layer.open({
			content: "您确定要删除此材料吗？",
			btn: ["确定", "取消"],
			yes: function(index) {
				PROMISE_METHOD.fetchGet("/aci/autoterminal/forward.do", {
					fmd: "aci-archives",
					fdo: "delWorkLicenseStuffById",
					stPersonalDocument: data.materialId
				}).then(function(result) {
					if(result == 1) {
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
			httpDownImage(data.imgSrc, "d:/networkImg.jpg")
				.then(function(name) {
					return imageReSaveGray(name);
				})
				.then(function(result) {
					$location.path("/pwait").search({
						imageType: "selfPrint"
					});
					$scope.$apply();
				})
				.catch(function(err) {
					console.log(err)
				})
		} else {
			layer.msg("需要选中图片才能打印", {
				time: 300
			});
		}
	};
});