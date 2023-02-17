app.controller("listController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.idRead = false;
	//街道下主题
	$scope.getThemeInStreet = function(){
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp(urlHost+'/aci/declare/getThemeInStreet.do',{
			params:tConfig
		}).success(function(dataJson){
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			$scope.itemName.unshift({
				itemTypeName:"000",
				itemTypeCode:"全部"
			});
		}).error(function(err){
			console.log(err);
		});
	}
	$scope.getThemeInStreet();

	$scope.toMaterials = function(itemTypeCode, itemTypeName, index) {
		data.itemTypeCode = itemTypeName;
		data.itemTypeName = itemTypeCode;
		$location.path("/itemlist");
	};
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("itemlistController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
		//通过主题获取事项
	$scope.getItemOfThemeInStreet = function(code){
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			themeCode:code
		}
		$http.jsonp(urlHost+'/aci/declare/getItemOfThemeInStreet.do',{
			params:tConfig
		}).success(function(dataJson){
			$scope.itemName = dataJson.data;
		}).error(function(err){
			console.log(err);
		});
	}
	if(data.itemTypeName == "全部"){
		$scope.getItemOfThemeInStreet("");
	}else{
		$scope.getItemOfThemeInStreet(data.itemTypeCode);
	}
	
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.setItemCode = function(code) {
		data.stItemNo = code;
		data.itemName = "街道";
		$location.path("/select");
	}
	$scope.prev = function(){
		$location.path("/list");
	}
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, data, $timeout, $rootScope, appFactory) {
	data.itemName = "敬老卡申领、发放";
	var name = data.itemName;
	$scope.itemName = name;
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.guideInfo = {
		clName: "<p>敬老卡申领、发放 </p >", //事项名称
		clDept: "市公安局", //主管部门
		clApplyConds: "年满65周岁的本市户籍市民", //申报条件
		clApprovalMater: "<p>申请对象需带齐以下材料：</p ><p>&nbsp;&nbsp;1、有效身份证原件</p ><p>",
		clApprovalProcess: "<p>1、该项业务全市通办，申请对象可在满65周岁生日前一个月带齐所需材料至就近的街（镇）社区事务受理服务中心办理</p ><p>2、材料齐全，无特殊情况当场受理</p ><p>3、转市审批30个工作日后，本人凭有效身份证原件领卡，若委托他人领卡的，还需提供被委托人有效身份证原件及申请对象签名或盖章的书面委托书</p ><p>4、目前有2家银行（中国工商银行、上海银行）代为发放综合津贴，居民可在受理时自行选择</p ><p>5、市民政热线：962200  卡面信息有误请拨打市社会保障卡服务中心热线：962222</p ><p>&nbsp;&nbsp;津贴标准：</p ><p>&nbsp;&nbsp;65-69 周岁每月75元      70-79周岁每月150元     80-89周岁每月180元</p ><p>&nbsp;&nbsp;90-99周岁每月350元      100周岁以上每月600元</p >",
	};

	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_TEXT(40, 354, 250, 50, "办事指南");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.ADD_PRINT_HTM(70, 45, "100%", "100%", "<style> dd{width:620px;} dt{font-weight:bold}</style><body>" + document.getElementById("scrollBox2").innerHTML + "</body>");
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.next = function() {
		$location.path("/select");
		data.clDept = $scope.guideInfo.clDept;
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};

});
app.controller("selectController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	data.imgId = "";
	$.device.Camera_Hide();
	$.device.qrCodeClose();
	var name = data.itemName || $location.search().itemName;
	data.itemName = name;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$rootScope.isAlert = false;
	appFactory.runtime();
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	$scope.prev = function() {
		if(data.itemName == "就业补贴") {
			$location.path("/guidelineJybt");
		} else if(data.itemName == "敬老卡申领、发放") {
			$location.path("/guideline");
		} else if(data.itemName == "生育保险") {
			$location.path("/guidelineSybx");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/guidelineJygx");
		} else if(data.itemName == "街道") {
			$location.path("/itemlist");
		}
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	data.idCardNum = "";
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;
	$scope.isLoding = true;
	$rootScope.isAlert = false;
	appFactory.runtime();
	$scope.SisAlert = false;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
		$location.path("/select");
	}

	$scope.choice = function() {
		$scope.isLoding = false;
		if(data.itemName == "就业补贴") {
			$location.path("/infoJybt");
		} else if(data.itemName == "敬老卡申领、发放") {
			//			$http.jsonp("http://10.1.93.168:8080/ac/aci/workPlatform/DZCert/checkElderlyCard.do", {
			//				params: {
			//					jsonpCallback: "JSON_CALLBACK",
			//					identNo: data.idCardNum
			//				}
			//			}).success(function(dataJson) {
			//				if(dataJson.type == "false") {
			//					$scope.SisAlert = true;
			//					$scope.Smsg = "<p>&nbsp</p><p>" + dataJson.age + "</p><p>" + dataJson.nation + "</p><p>" + dataJson.havingElderlyCard + "</p>";
			////					$scope.$on('changeModel',function(){
			////				           $scope.SisAlert = false;
			////				    })
			//				} else {
			$location.path("/info");
			//				}
			//			}).error(function(err) {
			//				layer.msg("未获取到敬老卡信息，请重试！");
			//				$location.path("/select");
			//				console.log(err);
			//			});
		} else if(data.itemName == "生育保险") {
			data.idCardNum = '310225199010194040'; //;
			data.idCardName = '严燕';
			$scope.sex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
			if($scope.sex == "男") {
				$scope.SisAlert = true;
				$scope.Smsg = "抱歉，该业务仅限生育女性申请。"
			} else {
				//				$http.jsonp("http://10.1.93.168:8080/ac/aci/workPlatform/DZCert/checkBirthBenefits.do", {
				//					params: {
				//						jsonpCallback: "JSON_CALLBACK",
				//						identNo: data.idCardNum
				//					}
				//				}).success(function(dataJson) {
				//					if(dataJson.type == "false") {
				//						$scope.SisAlert = true;
				//						$scope.Smsg = "<style> p{font-size:40px;}</style><p>" + dataJson.birthDate + "</p><p>" + dataJson.firstMarriage + "</p><p>" + dataJson.marriageCity + "</p><p>" + dataJson.birthCity + "</p><p>" + dataJson.birthBenefits + "</p><p>" + dataJson.InsuredStateThisMonth + "</p>";
				//					} else {
				$location.path("/infoSybx");
				//					}
				//				}).error(function(err) {
				//					layer.msg("未获取到生育信息，请重试！");
				//					$location.path("/select");
				//					console.log(err);
				//				});
			}
		} else if(data.itemName == "跨省异地就医登记备案") {
			$.ajax({
				type: "get",
				url: urlHost+"/aci/workPlatform/ybj/chooseApplyType.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					identNo: data.idCardNum,
					type: "0"
				},
				success: function(dataJson) {
					if(dataJson.type == "false") {
						$scope.SisAlert = true;
						$scope.Smsg = "<p style='padding-top:30px;'>" + dataJson.msg + "</p>";
					} else {
						data.jygxsm = dataJson.jygxsm;
						data.ybbfsm = dataJson.ybbfsm;
						data.zhh = dataJson.zhh;
						$location.path("/infoJygx");
					}
				},
				error: function(err) {
					$scope.SisAlert = true;
					$scope.Smsg = "未获取到就医信息，请重试！";
				}
			});
		}else if(data.itemName == "街道") {
			$location.path("/iframe");
		}
	}

	$scope.getIdcard = function(info, images) {
		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
		data.nation = info.People;
		if(data.nation.lastIndexOf('族') < 0) {
			data.nation = data.nation + '族';
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$scope.choice();
	}
//	data.idCardName = "王梅华";//"邹天奇";//
//	data.idCardNum = "310109194911262065"; //"430426199804106174";//"310104197308010412"; //""; //"";//"320831199503150013";//"310105197805313613"; //
//	data.mobile = "18692067056";
//	data.nation = "汉族";
//	$scope.choice();

});
app.controller("citizenController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$rootScope.isAlert = false;
	appFactory.runtime();
	$scope.SisAlert = false;
	$scope.isLoding = true;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
		$timeout(function() {
			$location.path('/select');
		}, 100);
	}
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	$scope.choice = function() {
		$scope.isLoding = false;
		if(data.itemName == "就业补贴") {
			$location.path("/infoJybt");
		} else if(data.itemName == "敬老卡申领、发放") {
			//			$http.jsonp("http://10.1.93.168:8080/ac/aci/workPlatform/DZCert/checkElderlyCard.do", {
			//				params: {
			//					jsonpCallback: "JSON_CALLBACK",
			//					identNo: data.idCardNum
			//				}
			//			}).success(function(dataJson) {
			//				if(dataJson.type == "false") {
			//					$scope.SisAlert = true;
			//					$scope.Smsg = "<p>&nbsp</p><p>" + dataJson.age + "</p><p>" + dataJson.nation + "</p><p>" + dataJson.havingElderlyCard + "</p>";
			//				} else {
			$location.path("/info");
			//				}
			//			}).error(function(err) {
			//				layer.msg("未获取到敬老卡信息，请重试！");
			//				$location.path("/select");
			//				console.log(err);
			//			});
		} else if(data.itemName == "生育保险") {
			//data.idCardNum = '310225199010194040';
			//data.idCardName = '严燕';
			$scope.sex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
			if($scope.sex == "男") {
				$scope.SisAlert = true;
				$scope.Smsg = "抱歉，该业务仅限生育女性申请。"
			} else {
				//				$http.jsonp("http://10.1.93.168:8080/ac/aci/workPlatform/DZCert/checkBirthBenefits.do", {
				//					params: {
				//						jsonpCallback: "JSON_CALLBACK",
				//						identNo: data.idCardNum
				//					}
				//				}).success(function(dataJson) {
				//					if(dataJson.type == "false") {
				//						$scope.SisAlert = true;
				//						$scope.Smsg = "<style> p{font-size:40px;}</style><p>" + dataJson.birthDate + "</p><p>" + dataJson.firstMarriage + "</p><p>" + dataJson.marriageCity + "</p><p>" + dataJson.birthCity + "</p><p>" + dataJson.birthBenefits + "</p><p>" + dataJson.InsuredStateThisMonth + "</p>";
				//					} else {
				$location.path("/infoSybx");
				//					}
				//				}).error(function(err) {
				//					layer.msg("未获取到生育信息，请重试！");
				//					$location.path("/select");
				//					console.log(err);
				//				});
			}
		} else if(data.itemName == "跨省异地就医登记备案") {
			$http.jsonp(urlHost+"/aci/workPlatform/ybj/chooseApplyType.do", {
				params: {
					jsonpCallback: "JSON_CALLBACK",
					identNo: data.idCardNum,
					type: "1"
				}
			}).success(function(dataJson) {
				if(dataJson.type == "false") {
					$scope.SisAlert = true;
					$scope.Smsg = "<p style='padding-top:30px;'>" + dataJson.msg + "</p>";
				} else {
					data.jygxsm = dataJson.jygxsm;
					data.ybbfsm = dataJson.ybbfsm;
					data.zhh = dataJson.zhh;
					$location.path("/infoJygx");
				}
			}).error(function(err) {
				$scope.SisAlert = true;
				$scope.Smsg = "未获取到就医信息，请重试！";
				console.log(err);
			});
		} else if(data.itemName == "街道") {
			$location.path("/iframe");
		}
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({
			url: urlHost + "/aci/window/getInfoByCodeForLogin.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success === false) {
					$scope.SisAlert = true;
					$scope.Smsg = dataJsonp.result.msg;
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}
				data.idCardName = dataJsonp.result.data.realname;
				data.idCardNum = dataJsonp.result.data.idcard;
				data.mobile = dataJsonp.result.data.mobile;
				$timeout(function() {
					$scope.choice();
				}, 100);
			},
			error: function(err) {
				$scope.SisAlert = true;
				$scope.Smsg = "二维码已过期！";
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
});
app.controller("iframeController", function($scope, $route, $http, $location, data, $timeout, $rootScope, $sce, appFactory) {
	$rootScope.isAlert = false;
	appFactory.runtime();
	$scope.data = {
		idCard: data.idCardNum //"310115198606194014" //
	}
	$scope.encryption = function(idCard) {
		//公钥
		var PUBLIC_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDOvG8syfBm/UYl7CazBHWkbluHZC7cA7XMHPkQundV9YueyaHpKJO+plset/foZzvYwlJw6bTTevrKsfY2XTUrYMq6Rw6qKpQ7+QI77B3lMKijTTtVDDymGU+Gy7qIcFA7Tlyj7OW74oXiPvBVj9dEqojZGVadIInoU3JmRIQ9QIDAQAB';
		//私钥
		var PRIVATE_KEY = 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALrx0HvokdmeimzVsMdHhra0HP4jT2PZpY5LM54FUZsmzgcjRfA8J8VUDerZ0s2lAR0MQc3AODcz/LnS47KCnVzJcbXYbLVL5BypKXQGAY4ff1qgnlzJGSiMtIqTOiXrvSx8StfTj3FC+6rvvAXOe8ed9DMeBCQRAuomeRisW+vZAgMBAAECgYB5lRGRtLU+woSmuefqA1PS+ZstkcttVjz9KV2dtTnY3Uj7jW5MCuOWy87tYdNfGaR6vuEBLrWg+XexZz3deGNcu7t0B4IRHu/54RPPXQszvoN0AcqcDaQ/sUHKE4MDwX5ij5wwA+V2TOUducBGH9+5or5N/IUzdLtKcnWlKoieMQJBANu2wk9xknXaH+dg4x1dVDzYpG1+rix8qA+dok2vF1twS7sdJwhngS7nsMSBjAIETGd2w94ZBz6VBGA9kx1C7K0CQQDZ0ZuhHPV70GhSG2ZyOI4hGI+lEAsCQe7nEKclK2U3oXZR2BNCAj+AZwO1GXqPguC+c5SBwbTTciMGysIwoNVdAkAsX7jWuqVN0APpgxPbdmHw+AAdbRxYN8TpgnipH9ejzAY/gB/F/sGEa56z0UYpkhysOLxOOtfPt+DuXwE7Q6zxAkEAzeCIsOemP7jkYXb0hdFexXlpjCJ1xVR8cnoTAdbafJJoO0N4MFPfoYW8w1epuCuEMX8dRufH+nNPGARdN4lNIQJAVtROB3lmtVYizbeMEEHFSIT3hEUQBTIcFekRgXcX4XYzv+tH35VKWjIH0IOLUvj4FOuTAuwdHs6Ux8HeHM4hHg==';
		//使用公钥加密
		var encrypt = new JSEncrypt();
		//encrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
		encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
		var encrypted = encrypt.encrypt(idCard);
		console.log('加密后数据:%o', encrypted);
		console.log('加密后数据URI:%o', encodeURIComponent(encrypted));
		return encodeURIComponent(encrypted);
	}
	console.log($scope.data);
	$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpredirect.do?data=" + $scope.encryption(JSON.stringify($scope.data)) + "&redirect_uri=http://ywtb.sh.gov.cn:18018/ac-product-net/netapply/apply.do?itemCode=" + data.stItemNo;
	console.info($scope.applyUrl);
	window.external.URL_OPEN(50, 180, 1800, 760, $scope.applyUrl);

	// 设置url被angular信任 正常跳转
	$scope.prev = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		$location.path("/itemlist");
	}
});
app.controller("infoController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$.device.idCardClose();
	var name = data.itemName;
	$scope.itemName = name;
	City();
	shouliCenter();
	$scope.SisAlert = false;
	$scope.concel = "false";
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}

	$scope.info = function() {
		$scope.name = data.idCardName;
		$scope.idCard = data.idCardNum;
		$scope.mobile = data.mobile || "";
		$scope.shebaoCard = data.idCardNum;
		$scope.sex = ((parseInt(data.idCardNum.substring(16, 17)) % 2) == 0) ? "女" : "男";
		$scope.date = (data.idCardNum).substring(6, 10) + "-" + (data.idCardNum).substring(10, 12) + "-" + (data.idCardNum).substring(12, 14);
		
		$scope.next2 = function(){
			//身份证信息
			var manIdcard = $.ajax({
				type: "get",
				url:urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
				/*url写异域的请求地址*/
				dataType: "jsonp",
				timeout:5000,
				/*加上datatype*/
				jsonp: "jsonpCallback",
				/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
				data: {
					identNo: data.idCardNum,
					catMainCode:"310105109000100"
				},
				success:function(dataJsonp) {
					console.info(dataJsonp);
					setSelectCheckedByText("nations", dataJsonp.SETHNIC);
				},error:function(err) {
					console.info("queryMarriageCertificateInfo error");
				},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
			　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
			 　　　　　manIdcard .abort();
			　　　　　$scope.next2();
			　　　　}
			　　}
			})
		}
		
		var config = {
			jsonpCallback: "JSON_CALLBACK",
			idno: data.idCardNum
		}
		$http.jsonp(urlHost+"/aci/workPlatform/elderlyCard/getApplicantInfo.do", {
			params: config
		}).success(function(dataJsonp) {
			setCenter();
			if(dataJsonp.rtnData) {
				data.rtnData = dataJsonp.rtnData;
				setHjAdress('province', 'city', 'county', 'street', dataJsonp.rtnData.hjprovince, dataJsonp.rtnData.hjcity, dataJsonp.rtnData.hjregion, dataJsonp.rtnData.hjneighborhood);
				setHjAdress('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', dataJsonp.rtnData.jzprovince||"310000", dataJsonp.rtnData.jzcity||"", dataJsonp.rtnData.jzregion||"", dataJsonp.rtnData.jzneighborhood||"");
			} else {
				$scope.next1() = function(){
					var nConfig = {
						identNo: data.idCardNum,
						catMainCode: "310105105000100"
					}
					var manLicense = $.ajax({
						type: "get",
						url:urlHost + "/aci/workPlatform/DZCert/queryMarriageCertificateInfo.do",
						/*url写异域的请求地址*/
						dataType: "jsonp",
						timeout:5000,
						/*加上datatype*/
						jsonp: "jsonpCallback",
						/*设置一个回调函数，名字随便取，和下面的函数里的名字相同就行*/
						data: nConfig,
						success:function(dataJsonp) {
							$scope.hjc = (dataJsonp.SADDR.split('市')[1]).split('区')[0];
							$scope.hjs = (dataJsonp.SADDR.split('市')[1]).split('区')[1]
							setHjAdressText('province', 'city', 'county', 'street', "上海市", "上海市", $scope.hjc + '区', "");
							setHjAdressText('liveProvince', 'liveCity', 'liveCounty', 'liveStreet', "上海市", "上海市", $scope.hjc + '区', "");
							$scope.next2();
						},
						error:function(err) {
							console.info("queryMarriageCertificateInfo error");
							$scope.next2();
						},complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
					　　　　if(status=='timeout'){//超时,status还有success,error等值的情况
					 　　　　　manLicense .abort();
					　　　　　$scope.next1();
					　　　　}
					　　}
					})
				}
				$scope.next1();
			}
		}).error(function(err) {
			setCenter();
			console.log("getApplicantInfo error");
		})
	}
	$scope.info();
	$scope.synchronize = function() {
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
			$scope.lStreet + (($scope.stLxAddress == undefined) ? "" : $scope.stLxAddress);
		$('#stLxAddress').val($scope.address);
	}
	//判断是哪个银行
	$scope.bank = function(str) {
		$scope.src = "";
		switch(str) {
			case "工商银行":
				$scope.src = "1";
				$scope.srcName = "中国工商银行";
				break;
			case "上海银行":
				$scope.src = "6";
				$scope.srcName = "上海银行";
				break;
		}
	}

	// 保存数据
	$scope.flag = true;
	$scope.next = function() {
		$scope.bank($(".in li").text());
		console.log($scope.src);
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#name').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的姓名！";
				return;
			}
			if(!checkIdCard($('#idCard').val())) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的身份证信息！";
				return;
			}
			if($('#sex').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的性别！";
				return;
			}
			if($('#shebaoCard').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的社保卡号！";
				return;
			}
			//			if(!isPhoneAvailable($('#mobile').val())) {
			//				$scope.SisAlert = true;
			//				$scope.Smsg = "请输入正确的手机号！";
			//				return;
			//			}
			if($('#address').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的地址！";
				return;
			}
			if($scope.src == "" || $scope.src == undefined) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择银行！";
				return;
			}
		} while (condFlag);
		$scope.flag = false;
		//提交所需字段
		data.centerCode = $('#stCenter').attr('vId');
		data.centerAddress = $('#stCenter').attr('vName');
		$scope.lStreet = ($('#liveStreet').attr('vName') == "选择街/镇") ? "" : $('#liveStreet').attr('vName');
		$scope.address = $('#liveProvince').attr('vName') + $('#liveCity').attr('vName') + $('#liveCounty').attr('vName') +
		$scope.lStreet + (($scope.stLxAddress == undefined) ? "" : $scope.stLxAddress);
		data.address = $scope.address;
		data.mobile = $('#mobile').val();
		data.bankid = $scope.src;
		data.sex = $('#sex').val();
		data.date = $scope.date;
		data.bank = $scope.srcName;
		data.jzAddress = {
			liveProvince:$('#liveProvince').attr('vId'),
			liveCity:$('#liveCity').attr('vId'),
			liveCounty:$('#liveCounty').attr('vId'),
			liveStreet:$('#liveStreet').attr('vId')
		}
		data.hjAddress = {
			province:$('#province').attr('vId'),
			city:$('#city').attr('vId'),
			county:$('#county').attr('vId'),
			street:$('#street').attr('vId')
		}
		console.log(data.hjAddress);
		console.log(data.jzAddress);
		//判断是否有身份证照
		$scope.nextload1 = function(){
			appFactory.pro_fetch(data.idCardNum, '310105109000100', 'GA2011_010001','GA2011_01','居民身份证','0',function(dataJson) {
				if(dataJson.length <= 0) {
					$scope.SisAlert = true;
					$scope.Smsg = "没有该证照!";
					$scope.$apply();
				}
				try {
					$scope.imgUrl = $.getConfigMsg.preUrl + dataJson[0].pictureUrlForBytes;
					data.imgStr = $.getConfigMsg.preUrl + dataJson[0].pictureUrl;
					if(data.isUpload.length <= 0) {
						data.isUpload.push({
							index: 0,
							stuffName: "居民身份证",
							img: $scope.imgUrl,
							status: 1,
							method: "高拍仪"
						});
					}
				} catch(e) {}
				$location.path("/materialList");
				$scope.$apply();
			},function(status){
				if(status=='timeout'){
		 　　　　　$scope.nextload1();
		　　　　}
			},function(dataJson){
				data.imgId = dataJson.rtnData.imgid;
			})
		}
		$scope.nextload1();
	};
	//公用单选
	Publicchoice2('.singleselect2');
	//滚动条
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("uploadMethodController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$scope.statusName = data.statusName;
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U盘上传
	$scope.takePhoto = function() {
		layer.confirm("<em style='color:black'>" + '请确认是否插入U盘！' + "</em>", {
			btn: ['已插入U盘', '未插入U盘'] //按钮
		}, function() {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function() {
				$location.path('/takePhoto/U');
			}, 20);
		}, function() {
			$('.layui-layer-shade').hide();
			$('.layui-layer').hide();
			$timeout(function() {
				layer.msg('请选择其他上传方式！');
			}, 20);
		});
	};
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/materialPic');
	};
	//返回
	$scope.prve = function() {
		if(data.itemName == "就业补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领、发放") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}
});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.imgUrls = "";
	$rootScope.isAlert = false;
	appFactory.runtime();
	$scope.profileShow = function() {
		$.ajax({
			url: "http://117.184.33.148:8080/ac-product/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				jsonpCallback: "JSON_CALLBACK",
				certNo: data.idCardNum, // "340881199303145313", //
				type: 0
			},
			success: function(json) {
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson) { // !dataJson[0].address
					layer.msg("没有数据，请重新选择上传方式!");
					$timeout(function() {
						$location.path('/uploadMethod');
					}, 1000);
				} else {
					$scope.imgUrls = dataJson;
					$scope.$apply();
					console.log($scope.imgUrls)
				}
			},
			error: function(err) {
				console.log(err)
			}
		});
	};
	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		$scope.current = index;
	}

	$scope.goNext = function() {
		data.selectImg = "http://117.184.33.148:8080/ac-product" + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.jsonData = {
			archivescode: data.archivescode,
			affairscode: data.affairscode,
			archivesname: data.stStuffName,
			needflag: data.needflag
		};
		$scope.jsonData = JSON.stringify($scope.jsonData);
		console.log($scope.jsonData);
		$.device.httpDownload( // 调用壳的方法下载服务器上的图片，再上传到另一接口中		urlHost + 
			$scope.waitUploadImgUrl,
			"C:\\waitUploadImg.jpg",
			//将选中图片下载
			function(bytesCopied, totalBytes) {
				console.log(bytesCopied + "," + totalBytes);
			},
			function(result) {
				//将选中图片上传到服务器
				$.device.httpUpload(urlHost+'/aci/workPlatform/elderlyCard/uploadArchiveInfo.do', "img", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						layer.msg("上传成功");
						if(data.isUpload[data.currentIndex].length > 0) {
							data.isUpload[data.currentIndex] = "";
						}
						data.isUpload[data.currentIndex] = {
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: scanImg,
							status: 0,
							method: "个人档案"
						};
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};

});
app.controller("takePhotoController", function($scope, $route, $http, $location, $rootScope, data, $timeout, $routeParams, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$rootScope.isAlert = false;
	appFactory.runtime();
	try {
		$.device.fileOpen(function(value) {
			$timeout(function() {
				$scope.UData = value; //"E://123.txt";// 
			}, 100)
		});
	} catch(e) {
		$timeout(function() {
			layer.msg("请插入U盘后操作");
		}, 100)
		$location.path("/uploadMethod");
	}
	// 上传
	$scope.highCapture = function() {
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				archivescode: data.archivescode,
				affairscode: data.affairscode,
				archivesname: data.stStuffName,
				needflag: data.needflag
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost+"/aci/workPlatform/elderlyCard/uploadArchiveInfo.do", "img", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.fileName.push($scope.UData);
					if(data.isUpload[data.currentIndex].length > 0) {
						data.isUpload[data.currentIndex] = "";
					}
					data.isUpload[data.currentIndex] = {
						index: data.currentIndex,
						stuffName: data.stStuffName,
						img: scanImg,
						status: 0,
						method: "U盘上传"
					};
					$timeout(function() {
						$location.path('/materialList');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
	};
});
app.controller("finishController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	$scope.SisAlert = false;
	$scope.SalertConfirm = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialSybxList');
	}
	$scope.finish = [];
	var name = data.itemName;
	$scope.itemName = name;
	$.device.cmCaptureShow(700, 480, 90, 375);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	$scope.ff = function() {
		if(data.itemName == "就业补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领、发放") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var scanImg1 = "";
	$scope.next = function() {
		layer.msg("正在上传中，请稍候");
		var scanImg = $.device.cmCaptureCaptureUrl();
		//var scanImg = "data:image/png;base64,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";
		scanImg1 = $.device.cmCaptureCaptureBase64();
		//scanImg = undefined;
		if(scanImg == undefined){
			$scope.SisAlert = true;
			$scope.Smsg = "请聚焦并对准材料后再拍照";
			$scope.SalertConfirm = function() {
				$scope.SisAlert = false;
			}
		}else{
			$.ajax({
				url: urlHost+'/aci/workPlatform/elderlyCard/uploadArchiveInfo.do',
				type: "post",
				dataType: "json",
				data: {
					archivescode: data.archivescode,
					affairscode: data.affairscode,
					archivesname: data.stStuffName,
					needflag: data.needflag,
					img: scanImg1
				},
				success: function(dataJson) {
					data.uploadStuffId = data.archivescode; //dataJson.data.stuffId  ;
					data.imgStr = scanImg1;
					data.imgId = data.imgId + ","+dataJson.rtnData.imgid;
					if(data.isUpload[data.currentIndex]) {
						data.isUpload[data.currentIndex] = "";
					}
					$scope.finish.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						img: scanImg,
						status: 0,
						method: "高拍仪"
					});
					imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
					$('.imgBox').html(imgHTML);
					$scope.isFinish = true;
				},
				error: function(err) {
					layer.msg("上传材料失败")
				}
			});
		}
	};
	//取下标
	$scope.indexVf = function(array,str){
		for(var i=0;i<array.length;i++){
			if(array[i]=str){
				return i;
			}
		}
	}
	// 完成拍照
	$scope.finishUpload = function() {
		for(var i = 0; i < data.isUpload.length; i++) {
			if(data.currentIndex == data.isUpload[i].index) {
				data.isUpload[i]="";
			}
		}
		for(var i=0;i<$scope.finish.length;i++){
			data.isUpload.push($scope.finish[i]);
		}
		$(".next").attr("disabled", true);
		$.device.cmCaptureHide(); // 关闭高拍仪
		if(data.currentIndex == 4) {
			var formData = new FormData();
			formData.append('imgFile', scanImg1);
			$.ajax({
				url: urlHost+'/aci/workPlatform/util/readImgToText.do',
				type: 'POST',
				cache: false,
				data: formData,
				processData: false,
				contentType: false
			}).done(function(res) {
				try {
					res = JSON.parse(res);
				} catch(e) {}
				if(res.bank_card_number != undefined && res.bank_card_number != "") {
					$scope.SisAlert = true;
					$scope.Smsg = "该银行卡为申请人（" + data.idCardName + "）所有，银行卡号为" + res.bank_card_number;
					$scope.$apply();
				} else {
					layer.msg("银行卡号为空");
					$location.path('/materialSybxList');
				}
			}).fail(function(res) {
				layer.msg("未识别出银行卡号");
				$location.path('/materialSybxList');
			});
		} else {
			$timeout(function() {
				$.device.cmCaptureHide(); // 关闭高拍仪
				$scope.ff();
			}, 20);
		}

	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$scope.ff();
	}
});
app.controller("materialListController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	if(data.isUpload.length > 0) {
		for(var i=0;i<data.isUpload.length;i++){
			if(data.isUpload[i].status == 1) {
				$scope.show = true;
				$scope.show1 = false;
			} else if(data.isUpload[i].status == 0) {
				$scope.show1 = true;
				$scope.show = false;
			}
		}
	} else {
		$scope.show = false;
		$scope.show1 = false;
	}
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$rootScope.isAlert = false;
	appFactory.runtime();
	//必传材料列表
	data.currentIndex = 0;
	$scope.mustUpload = [];
	$scope.current = 0;
	$scope.mustUpload.push({
		'index': 0,
		'stuffName': "居民身份证"
	});
	data.listImg = {
		'index': 0,
		'stuffName': "居民身份证"
	};
	
	console.log(data.isUpload);

	// 材料上传 
	data.currentIndex++;
	$scope.toUploadMaterial = function() {
		data.stStuffName = "居民身份证";
		data.archivescode = 'GA2011_010001';
		data.affairscode = 'GA2011_01';
		data.needflag = '0';
		data.currentIndex = 0;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//重新上传

	//查看
	$scope.view = function() {
		data.currentIndex = 0;
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function() {
		$location.path('/signature');
	};

	//自助填表
	$scope.autoForm = function() {
		$location.path("/autoForm");
	}

});
app.controller("autoFormController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$scope.name = data.idCardName;
	$scope.xing = pinyin.getFullChars((data.idCardName).substring(0, 1));
	$scope.ming = pinyin.getFullChars((data.idCardName).substring(1, data.idCardName.length));
	$scope.idCard = data.idCardNum;
	$scope.mobile = data.mobile;
	$scope.date = data.idCardNum.substring(6, 10) + "-" + data.idCardNum.substring(10, 12) + "-" + data.idCardNum.substring(12, 14);
	$scope.newAddress = data.address?data.address:"";
	$scope.pNewAddress = pinyin.getFullChars($scope.newAddress);
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.SisAlert = false;
	$scope.SalertConfirm = function() {
		$scope.SisAlert = false;
	}

	$scope.flag = true;
	$scope.next = function() {
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($('#name').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的姓名！";
				return;
			}
			if($('#date').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的日期！";
				return;
			}
			if($('#juming').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请选择一个税收模式！";
				return;
			}
			if($('#xing').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的姓！";
				return;
			}
			if($('ming').val() < 1) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的名！";
				return;
			}
			if(!checkIdCard($('#idCard').val())) {
				$scope.SisAlert = true;
				$scope.Smsg = "请输入正确的身份证信息！";
				return;
			}
		} while (condFlag);
		$scope.flag = false;
		$location.path("/materialList");
	}
})
app.controller("soundController", function($scope, $http, $location, $rootScope, data, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.next = function() {
		$location.path("/signatureSybx");
	}
})
app.controller("signatureController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$rootScope.isAlert = false;
	appFactory.runtime();
	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		var name = data.itemName;
		$scope.itemName = name;
		$scope.signature = null;
		$scope.signatureFlag = false;
		$scope.SignatureBoardPlug = new SignatureBoardPlug({
			canvas: "#signature",
			clearBtn: ".clearRect",
			getSigntrue: ".saveImg",
			color: "black"
		});
	});

	$scope.isSignature = function() {
		$scope.signatureFlag = true;
	};
	$scope.notSignature = function() {
		$scope.signatureFlag = false;
	}
	$scope.saveSignature = function() {
		$scope.signature = $scope.SignatureBoardPlug.Signatrue;
		if($scope.signatureFlag === false) {
			alert("请先在屏幕上签名!");
			return;
		}
		data.picStr = $scope.signature.split(",")[1];
		$location.path("/infoFinish");
	};
})
app.controller("materialViewController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$rootScope.isAlert = false;
	appFactory.runtime();
	//当页显示图片
	$scope.currentList = function(current) {
		if(current === undefined) {
			current = $scope.currentPage;
		}
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 3; //0 -3  3 - 6 
		$scope.endPos = $scope.startPos + 3;
		console.log($scope.stuffList);
		$scope.showImgList = $scope.stuffList.slice($scope.startPos, $scope.endPos);
		console.log($scope.showImgList);
		$scope.totalPages = Math.ceil($scope.stuffList.length / 3);
	}

	$scope.$watch('$viewContentLoaded', function(newValue, oldValue) {
		console.log(data.view);
		for(var i = 0; i < data.view.length; i++) {
			if(data.currentIndex == data.view[i].index) {
				$scope.stuffList.push(data.view[i]);
				$scope.currentList();
			}
		}
		console.log($scope.stuffList);
		if($scope.stuffList[0].method === "高拍仪") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		} else if($scope.stuffList[0].method === "U盘上传") {
			$scope.scanShow = false;
			$scope.upanShow = true;
		} else if($scope.stuffList[0].method === "个人档案") {
			$scope.scanShow = true;
			$scope.upanShow = false;
		}
	});
	//下一页
	$scope.nextPage = function() {
		if($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		}
	};
	//上一页
	$scope.prevPage = function() {
		if($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
			console.log($scope.showImgList);
		}
	};

	$scope.next = function() {
		if(data.itemName == "就业补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领、发放") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}

	//图片显示
	$scope.closeFlag = true;
	$scope.imgShow = function(imgUrl) {
		$scope.largeImg = imgUrl;
		$scope.closeFlag = !$scope.closeFlag;
	}
	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch(e) {
			layer.msg("未找到此文件");
		}
	}
});
app.controller("infoFinishController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	$scope.urladdress = "";
	//	$scope.$watch("viewContentLoaded", function(event, toState, toParams, fromState, fromParams) {
	//		//$scope.isLoding = true;
	//		$scope.loding();
	//	});
	//	$scope.loding = function() {
	if(data.itemName == "就业补贴") {
		$scope.show = false;
		$scope.stuffName = '请凭办理凭条将"劳动手册"到自助取证柜进行材料投递';
		$('.successBox1').css("margin-left", "520px");
		$('.successBox1 p').css("margin-left", "10px");
		$scope.applyNo = "751021900100501";
	} else if(data.itemName == "敬老卡申领、发放") {
		$scope.show = true;
		$scope.applyNo = "751021900100401";
	} else if(data.itemName == "生育保险") {
		$scope.stuffName = '请凭办理凭条将"生育医学证明"到自助取证柜进行材料投递';
		$('.successBox1').css("margin-left", "520px");
		$('.successBox1 p').css("margin-left", "10px");
		$scope.applyNo = "751021900100301";
	} else if(data.itemName == "跨省异地就医登记备案") {
		$scope.show = false;
		$('.successBox1').css("margin-left", "520px");
		$('.successBox1 p').css("margin-left", "10px");
		$scope.applyNo = "751021900100201";
	}
	$scope.urladdress = urlHost+"/aci/selfWorkbench-Data/sqfw/index.html#/qrCode?applyNo=" + $scope.applyNo + "&itemName=" + data.itemName + "&name=" + data.idCardName;
	//	}
	var name = data.itemName;
	$scope.itemName = name;
	data.rtnData = {
		idno: data.idCardNum,
		fullname: data.idCardName,
		sex: (data.sex == '男') ? '1' : '2',
		birthday: data.date,
		nationality: data.nations||"",
		cellno: data.mobile,
		hjprovince: data.hjAddress.province || "",
		hjcity: data.hjAddress.city|| "",
		hjregion: data.hjAddress.county|| "",
		hjneighborhood: data.hjAddress.street|| "",
		jzprovince: data.jzAddress.liveProvince|| "",
		jzcity: data.jzAddress.liveCity|| "",
		jzregion: data.jzAddress.liveCounty|| "",
		jzneighborhood: data.jzAddress.liveStreet|| ""
	}
	console.log(data.hjAddress);
	console.log(data.jzAddress);
	console.log(data.rtnData);
	var date = new Date();
	console.log(date);
	// 生成办件编码
	$scope.submitApply = function() {
		$.ajax({
			url:'http://192.168.1.142:8080/ac-product/aci/workPlatform/elderlyCard/getDictionaryCodeByName.do',
			type:'get',
			dataType:'jsonp',
			data:{
				dictionaryDept:'WJ',
				dictionaryNames:data.hjAddressName.province+','+data.hjAddressName.county+','+data.hjAddressName.street+','
				+data.jzAddressName.liveProvince+','+data.jzAddressName.liveCounty+','+data.jzAddressName.liveStreet+','
				+data.mhjAddressName.province+','+data.mhjAddressName.county+','+data.mhjAddressName.street+','
				+data.mjzAddressName.liveProvince+','+data.mjzAddressName.liveCounty+','+data.mjzAddressName.liveStreet
			},
			success:function(dataJson){
				console.log(dataJson);
			},
			error:function(err){
				console.log(err);
			}
		});
		$scope.finishData = "";
		if(data.itemName == "敬老卡申领、发放"){
			//敬老卡
			$scope.finishData = {
				applicant: data.rtnData,
				ywtbAffairsapply: {
					platform: '4',
					affairscode: "GA2011_01",
					affairsname: "敬老卡申领",
					Itemcode: "",
					subtime: date,
					suid: "751111111111111",
					suborgancode: data.centerCode,
					suborganname: data.centerAddress,
					formdata: [{
				     	name: data.rtnData.fullname,
					    pid: data.rtnData.idno,
					    sex: data.rtnData.sex,
					    enrolid:data.rtnData.idno || "",
					    birthday: data.rtnData.birthday,
					    telephone: data.mobile,
					    commaddress: data.address,
					    bankid: data.bankid,
					    dbphone:"",
					    archivesdata: [{
					    	archivescode: " GA2011_010001 ",
					      	affirscode: " GA2011_01 ",
					      	archivesname: "居民身份证",
					      	needflag: "0",
					      	imgscans: data.imgId
					    }],
					    display: {
						  	"姓名": data.rtnData.fullname,
						  	"身份证": data.rtnData.idno,
						  	"性别": (data.rtnData.sex == '1')?'男':'女',
						 	"出生年月": data.rtnData.birthday,
						  	"联系电话": data.mobile,
						  	"联系地址": data.address,
						  	"发放银行": data.bank,
						  	"联系电话": data.mobile,
					    }
				    }]
				},
				jsonpCallback: "JSON_CALLBACK",
			};
		}else if(data.itemName == "就业补贴"){
			//就业补贴
			$scope.finishData = {
				applicant: data.rtnData,
				ywtbAffairsapply: {
					platform: '4',
					affairscode: "YJS0001",
					affairsname: "就业补贴",
					Itemcode: "",
					subtime: date,
					suid: "751111111111111",
					suborgancode: data.centerCode,
					suborganname: data.centerAddress,
					formdata: [{
						person_name:data.rtnData.fullname,
						cetf_id:data.rtnData.idno,
						sign_png:data.picStr,
						archivesdata: [{
							archivescode: "YJS00010003",
							affirscode: "YJS0001",
							archivesname: "居民身份证",
							needflag: "1",
							imgscans: data.imgId
						}],
						display: {
							姓名: data.rtnData.fullname,
							身份证: data.rtnData.idno
						}
					}]
					
				}
			}
		}else if(data.itemName == "生育保险"){
			//生育保险
			$scope.finishData = {
				applicant: data.rtnData,
				ywtbAffairsapply: {
					platform: '4',
					affairscode: "YJS0002",
					affairsname: "生育保险",
					Itemcode: "",
					subtime: date,
					suid: "751111111111111",
					suborgancode: data.centerCode,
					suborganname: data.centerAddress,
					formdata: [{
						"name":data.rtnData.fullname,
						"sex":data.rtnData.sex,
						"idcard":data.rtnData.idno,
						"nationc":data.nations,
						"isdszv":"1",
						"phone":data.mobile,
						"hjprovince":data.rtnData.hjprovince,
						"hjcity":data.rtnData.hjregion,
						"regAddrCodeA":data.rtnData.hjneighborhood,
						"regAddrCode":"",
						"regfulladdr":data.address,
						"jzprovince":data.rtnData.jzprovince,
						"jzcity":data.rtnData.jzregion,
						"resaddrcodeA":data.rtnData.jzneighborhood,
						"resaddrcode":"",
						"resfulladdr":data.liveAddress,
						"boyn":"1",
						"girln":"0",
						"spousename":data.maleName,
						"spousesex":"2",
						"spouseidcard":data.maleIdCard,
						"spousebirth":(data.maleIdCard).substring(6, 10) + "-" + (data.maleIdCard).substring(10, 12) + "-" + (data.maleIdCard).substring(12, 14),
						"spouseisdszv":"1",
						"spousephone":data.maleMobile,
						"pohjprovince":data.mhjAddress.province,
						"pohjcity":data.mhjAddress.county,
						"spouseregaddrcodeA":data.mhjAddress.street,
						"spouseregaddrcode":"",
						"spouseregfulladdr":data.maddress,
						"pojzprovince":data.mjzAddress.liveProvince,
						"pojzcity":data.mjzAddress.liveCounty,
						"spouseresaddrcodeA":data.mjzAddress.liveStreet,
						"spouseresaddrcode":"",
						"spouseresfulladdr":data.mliveAddress,
						"spouseqsboy":"0",
						"spouseqsgirl":"0",
						"applytype":"0",
						"qsboy":"0",
						"qsgirl":"0",
						"isbl":"0",
						"zcc":"6",
						"iswy":data.iswy,
						"sqrznxx":[{
						" znname ":data.childName,
						" zncardid ":data.childIdCard,
						" relationCodeN ":"亲生",
						" relationCode ":"1"
						}],
						"archivesdata": [{
							"archivescode": "YJS00020001 ",
							"affirscode": " YJS0002",
							"archivesname": "女方居民身份证",
							"needflag": "1",
							"imgscans": data.imgStr1
						},
						{
							"archivescode": "YJS00020003 ",
							"affirscode": " YJS0002",
							"archivesname": "男方居民身份证",
							"needflag": "1",
							"imgscans": data.imgStr2
						},
						{
							"archivescode": "YJS00020002 ",
							"affirscode": " YJS0002",
							"archivesname": "女方户籍证明",
							"needflag": "1",
							"imgscans": data.imgStr3
						},
						{
							"archivescode": "YJS00020004 ",
							"affirscode": " YJS0002",
							"archivesname": "男方户籍证明",
							"needflag": "1",
							"imgscans": data.imgStr4
						}],
						"display": {
							"姓名":data.rtnData.fullname,
							"性别":(data.rtnData.sex == '1')?'男':'女',
							"身份证号":data.rtnData.idno,
							"民族":data.nationName,
							"是否独生子女":"是",
							"联系电话":data.mobile,
							"户籍省市":data.hjAddressName.province,
							"户籍区":data.hjAddressName.county,
							"户籍街道":data.hjAddressName.street,
							"户籍居委":"",
							"户籍详细地址":data.address,
							"居住省市":data.jzAddressName.liveProvince,
							"居住区":data.jzAddressName.liveCounty,
							"居住街道":data.jzAddressName.liveStreet,
							"居住居委":"",
							"住详细地址":data.liveAddress,
							"本次生育男孩数":"1",
							"本次生育女孩数":"0",
							"配偶姓名":data.maleName,
							"配偶性别":"男",
							"配偶身份证号":data.maleIdCard,
							"配偶出生日期":(data.maleIdCard).substring(6, 10) + "-" + (data.maleIdCard).substring(10, 12) + "-" + (data.maleIdCard).substring(12, 14),
							"配偶是否独生子女":"是",
							"配偶联系电话":data.maleMobile,
							"配偶户籍省市":data.mhjAddressName.province,
							"配偶户籍区":data.mhjAddressName.county,
							"配偶户籍街道":data.mhjAddressName.street,
							"配偶户籍居委":"",
							"配偶户籍详细地址":data.maddresss,
							"配偶居住省市":data.mjzAddressName.liveProvince,
							"配偶居住区":data.mjzAddressName.liveCounty,
							"配偶居住街道":data.mjzAddressName.liveStreet,
							"配偶居住居委":"",
							"配偶居住详细地址":data.mliveAddress,
							"配偶生育（流产）前男孩数":"0",
							"配偶生育（流产）前女孩数":"0",
							"业务办理类型":"生育",
							"生育（流产）前男孩数":"0",
							"生育（流产）前女孩数":"0",
							"是否委托办理":"否",
							"生育行为":"符合政策生育第一胎",
							"是否晚育":(data.iswy == 1)?"是":"否",
							"子女信息":"(姓名:"+data.childName+",身份证号:"+data.childIdCard+",与子女关系:亲生)"
						}
					}]
				}
			}
		}
		console.log(JSON.stringify($scope.finishData));
//		$.ajax({
//			url: 'http://192.168.1.142:8080/ac-product/aci/workPlatform/elderlyCard/sendYwtbApplyInfo.do',
//			type: "post",
//			dataType: "json",
//			data: {
//				jsonStr: JSON.stringify($scope.finishData)
//			},
//			success: function(dataJson) {
//				console.log(dataJson);
//				// $scope.print();
//			},
//			error: function(e) {
//				console.log(e);
//			},
//		});

	}
	
	$scope.prev = function() {
		if(data.itemName == "就业补贴") {
			$location.path("/materialJybtList");
		} else if(data.itemName == "敬老卡申领、发放") {
			$location.path("/materialList");
		} else if(data.itemName == "生育保险") {
			$location.path("/materialSybxList");
		} else if(data.itemName == "跨省异地就医登记备案") {
			$location.path("/materialJygxList");
		}
	}
	$scope.submitApply();
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	$scope.print = function() {
		var lodop = $.device.printGetLodop();
		if(data.itemName == "就业补贴") {
			lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900100501");
		} else if(data.itemName == "敬老卡申领、发放") {
			lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900200401");
		} else if(data.itemName == "生育保险") {
			lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900300301");
		} else if(data.itemName == "跨省异地就医登记备案") {
			lodop.ADD_PRINT_BARCODE(38, 34, 307, 47, "128A", "751021900400201");
		}
		//		lodop.ADD_PRINT_TEXT(120, 34, 250, 50, "职能单位：");
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 18);
		//		lodop.ADD_PRINT_TEXT(120, 150, 250, 50, "上海"+data.clDept);
		//		lodop.SET_PRINT_STYLEA(0, "FontSize", 18);
		lodop.ADD_PRINT_TEXT(170, 574, 200, 30, "扫一扫,查进度");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 10);
		lodop.ADD_PRINT_BARCODE(38, 550, 168, 146, "QRCode", $scope.urladdress);
		lodop.ADD_PRINT_TEXT(190, 165, 600, 50, "上海市政务服务申请材料 收件凭证");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "申请事项：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(250, 125, 600, 30, data.itemName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请人：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 125, 600, 30, data.idCardName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 480, 600, 30, "联系电话：");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(280, 580, 600, 30, data.mobile);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(330, 65, 700, 30, "经核查，您（单位）提交的申请材料齐全，符合法定形式，现予收件。");
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(370, 25, 700, 120, "    根据规定，承办部门将在出具本凭证之日起5个工作日内，作出受理或不予受理的决定，申请材料不齐全或者不符合法定形式的，将在5个工作日内一次告知需要补正的材料。如在5个工作日内未被告知需要补正材料的，则视为正式受理，本凭证即为受理凭证。");
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(500, 65, 670, 150, "收件材料清单附后");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "BOLD", 1);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		lodop.ADD_PRINT_TEXT(560, 480, 300, 30, "收件日期：" + date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.SET_PRINT_STYLEA(0, "FontName", "仿宋");
		if(data.itemName == "就业补贴") {
			lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>居民身份证</td><td>电子文件</td></tr><tr><td>2</td><td>劳动手册</td><td>原件(待放入自助取证柜)</td></tr></table>");
		} else if(data.itemName == "敬老卡申领、发放") {
			lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>居民身份证</td><td>电子文件</td></tr><tr><td>2</td><td>个税声明</td><td>原件(待放入自助取证柜)</td></tr></table>");
		} else if(data.itemName == "生育保险") {
			lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>女方居民身份证</td><td>电子文件</td></tr><tr><td>2</td><td>男方居民身份证</td><td>电子文件</td></tr><tr><td>3</td><td>女方户籍证明</td><td>电子文件</td></tr><tr><td>4</td><td>男方户籍证明</td><td>电子文件</td></tr><tr><td>5</td><td>生育医学证明</td><td>原件(待放入自助取证柜)</td></tr><tr><td>6</td><td>银行卡</td><td>电子文件</td></tr></table>");
		} else if(data.itemName == "跨省异地就医登记备案") {
			lodop.ADD_PRINT_TABLE(700, 50, 700, 1000, "<style>table{font-family:'FangSong'}table td{border:1px solid} tr td:nth-child(1){text-align:center}tr td:nth-child(2){padding-left:20px;}tr td:nth-child(3){padding-left:20px;} table th{border:1px solid} table{border-collapse: collapse}</style><table><tr><th style='width:60px'>序号</th><th style='width:300px'>申请材料</th><th style='width:250px'>材料来源</th></tr><tr><td>1</td><td>居民身份证</td><td>电子文件</td></tr></table>");
		}
		lodop.PRINT();
	};
	//$scope.print();
});
app.controller("qrCodeController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	var date = new Date();

	function formatDate(date) {
		var date = new Date(date);
		var YY = date.getFullYear() + '-';
		var MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
		var DD = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
		var hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
		var mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
		var ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
		return YY + MM + DD + " " + hh + mm + ss;
	}
	$scope.applyNo = $location.search().applyNo;
	$scope.itemName = $location.search().itemName;
	$scope.name = $location.search().name;
	$scope.date = formatDate(date);
});