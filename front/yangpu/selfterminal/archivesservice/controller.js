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
	$scope.frontImg = "data:image/png;base64," + data.frontImg;
	$scope.backImg = "data:image/png;base64," + data.backImg;
	$scope.currentImg = "data:image/png;base64," + data.frontImg;
	$scope.type = "front";
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
			stName: data.userName,
			stIdNo: data.idCardNumber,
			type: "0"
		}).then(function(result) {
			console.log(JSON.stringify(result));
			
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
console.log($scope.showImgAdress)
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
			$.device.httpDownload(
				data.imgSrc,
				"c:/materia.jpg",
				function(bytesCopied, totalBytes) {
					//alert(bytesCopied + "," + totalBytes);
				},
				function(result) {
					$location.path("/pwait").search({
						printType: "img",
						imgfilepath: "c:/materia.jpg"
					}); //直接打印
				},
				function(webexception) {
					alert("下载文档失败");
				}
			);
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
	$scope.dataNumber = data.dataNumber;
	$scope.isEmpty = true;
	$scope.imagesBase64Con = [];
	$scope.imgContainer = [];
	$scope.isLoading = true;
	$scope.loadFailed = false;
	$scope.isLarge = false;
//	$scope.isTestData = "91310104132660027E";
//	$scope.isTestType = "1";
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
				type: $scope.isTestType||$scope.dataType,
				certNo: $scope.isTestData || $scope.dataNumber
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
					console.log(i + "index: " + result[i].str)
					if(!(result[i].str == '')) {
						$scope.imagesBase64Con.push("data:image/jpeg;base64," + result[i].str);
					} else {
						// $scope.imgLoadedCon.splice(i, 1);
					}
				}
				$scope.$apply();
				return PROMISE_METHOD.fetchGet("/aci/autoterminal/dzzz/queryCertBaseData.do", {
					type: $scope.isTestType||$scope.dataType,
					certNo: $scope.isTestData || $scope.dataNumber
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
	$timeout
) {
	//获取身份证信息
	$scope.readIdCard = function() {
		if(!OCX_idCard.attachEvent) {
			console.log("控件导入错误！");
		} else {
			PROMISE_METHOD.getIdCardInfo()
				.then(function(result) {
					var list = JSON.parse(result.identityInfo);
					data.idCardNumber = list.Code;
					data.userName = list.Name;
					data.frontImg = result.frontImg;
					data.backImg = result.backImg;
					data.dataName = data.userName;
					data.dataNumber = data.idCardNumber;
					data.dataType = '0';
					$location.path("/base");
					$scope.$apply();
				})
				.catch(function(err) {
					console.log(err);
				});
		}
	};
	$scope.readIdCard();
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
						layer.msg("上传成功：" + res, {
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