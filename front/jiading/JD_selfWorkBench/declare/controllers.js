var t;
var machineId = $.config.get('uniqueId');

function removeAnimate(ele) {
	$(ele).css({
		"transform": "translateY(0px)",
		"top": 0
	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).addClass('transformto').on("animationend", function() {
		$(ele).removeClass('transformto')
	})
}

function time() {
	/*var time = 60;
	t = setInterval(function() {
		if(time == 0) {
			clearInterval(t);
			 $.device.GoHome();
		}
		$(".minute").text(time);
		time--;
	}, 1000)*/
}
app.controller("startController", function($scope, $location, $http, data, $rootScope, $timeout, $interval) {
	data.ocode = "";
	data.themeCode = "";
	data.organCode = "";
	$scope.goToApp = function(address) {

		if(address.indexOf("http") != -1) {
			window.location.href = address;
		} else {
			window.location.href = address;
		}
	};
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data) {
	$scope.idRead = true;
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	//	$scope.areaCode = $location.search().areaCode;
	$scope.areaCode = "SH00JD";
	$scope.current = 0;
	$scope.searchType = ["个人", "法人", "部门"];
	$scope.current = data.matindex || 0;
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.organCode = data.ocode || null;
	$scope.itemName = "";
	data.areaL = "SH";
	$scope.type = "1";
	$scope.isLoading = false;

	//------  打印卡片测试------
	$.device.serialPortOpen("COM4", 9600, 8, function() {}) //开启串口
	$scope.printCard = function() {
		var lodop = $.device.printGetLodop()
		//		LODOP.SET_PRINT_MODE("WINDOW_DEFPRINTER","DASCOM DS-7860");
		//		LODOP.PRINT_INIT("XPS");
		alert('开始指定打印机')
		lodop.SET_PRINTER_INDEX('DASCOM DS-7860');
		alert('指定打印机成功')
		//		lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", '751122028600008');
		//		lodop.ADD_PRINT_BARCODE(28, 200, 200, 146, "QRCode", 'ahhhhhh');
		lodop.ADD_PRINT_TEXT(240, 350, 670, 50, "123456790");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(270, 350, 670, 30, "李华熙");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(305, 350, 670, 30, "男");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(340, 350, 670, 30, "9876543210");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.ADD_PRINT_TEXT(380, 350, 670, 30, "1995-06");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 16);
		lodop.PRINT(); //打印
		setTimeout(function() {
			$.device.serialPortWriteString("S0001#") //发送指令

		}, 1000)
		//		alert('打印完毕，关闭串口')
		//		$.device.serialPortClose();		
	}

	$scope.closeXPS = function() {
		try {
			$.device.serialPortClose();
		} catch(e) {
			alert("端口关闭异常")
		}
	}
	// ------   结束    ------

	//	
	//阅签选择
	$scope.getMatterCon = function(index, type) {
		$scope.current = index;
		switch(type) {
			case "部门":
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.getDepartment();
				$scope.dept = true;
				break;
			case "法人":
				$scope.isLoading = false;
				$scope.type = "2";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.corporationItem();
				$scope.dept = false;
				break;
			case "个人":
				$scope.isLoading = false;
				$scope.type = "1";
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.personalItem();
				$scope.dept = false;
		}
	};
	//个人
	$scope.personalItem = function() {
		removeAnimate($('.linkbox1'))
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp(urlHost + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: $scope.areaCode,
				type: '1'
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "全部",
				itemTypeName: "000",
				type: "1"
			});
			$scope.isLoading = true;
			addAnimate($('.linkbox1'))
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function() {
		removeAnimate($('.linkbox1'))
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp(urlHost + '/aci/declare/getItemTheme.do', {
			params: {
				jsonpCallback: "JSON_CALLBACK",
				areaCode: $scope.areaCode,
				type: '2'
			}
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemType = dataJson.data;
			$scope.itemType.unshift({
				itemTypeCode: "全部",
				itemTypeName: "000",
				type: "2"
			});
			$scope.isLoading = true;
			addAnimate($('.linkbox1'))
		}).error(function(err) {
			console.log("getItemTheme error");
		});
	}
	//获取所有部门
	$scope.getDepartment = function() {
		removeAnimate($('.linkbox1'))
		$scope.matterVal = '';
		$scope.current = 2;
		$scope.deptindex = 0;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode
		};
		$http.jsonp(urlHost + '/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
			addAnimate($('.linkbox1'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code, type) {
		removeAnimate($('.linkbox1'))
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode,
			themeCode: code,
			type: type
		};
		$http.jsonp(urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('.linkbox1'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}

	//个人所有事项
	$scope.getPersonItemList = function() {
		removeAnimate($('.linkbox1'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode,
			themeCode: "",
			type: "1"
		};
		$http.jsonp(urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			console.log('请求成功')
			addAnimate($('.linkbox1'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//法人所有事项
	$scope.getCorporationItemList = function() {
		removeAnimate($('.linkbox1'))
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: $scope.areaCode,
			themeCode: "",
			type: "2"
		};
		$http.jsonp(urlHost + '/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
			addAnimate($('.linkbox1'))
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}
	//查询事项 
	$scope.getSearchMatter = function(current) { // 查询事项
		$scope.dept = true;
		$scope.organCode = 'search';
		var vConfig = {
			itemName: $scope.matterVal,
			jsonpCallback: "JSON_CALLBACK",
		};
		if($scope.matterVal) {
			$scope.current = 1;
			$http.jsonp(urlHost + '/aci/declare/getItemListByItemNameForPage.do', {
				params: vConfig
			}).success(function(dataJson) {
				console.log(dataJson)
				$('.tabBotbox1inner').find('a:first').addClass('active').siblings().removeClass('active');
				$('.linkbox1').addClass('matter')
				$scope.itemName = dataJson.itemSetList;
				//$scope.$apply();
			}).error(function() {
				console.log('getOrganListForDeclarePage error')
			})
		} else {
			layer.msg('请输入事项名称');
		}
	};
	//通过部门id获取部门事项
	$scope.getItemByOrganCode = function(code) {
		removeAnimate($('.linkBox1'))
		$scope.itemName = "";
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			addAnimate($('.linkBox1'))
			$scope.itemName = dataJson.itemSetList;
			$scope.isLoading = true;
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};

	$scope.$on('$viewContentLoaded', function() {
		$scope.prev = false;
		if(data.themeCode) {
			$scope.prev = true;
			$scope.getItemListByType(data.themeCode, data.type);
			$scope.isLoading = true;
			$scope.dept = true;
			if(data.type == "1") {
				$scope.current = 0;
			} else if(data.type == "2") {
				$scope.current = 1;
			}
		} else if(data.organCode) {
			$scope.prev = true;
			$scope.getItemByOrganCode(data.organCode);
			$scope.isLoading = true;
		} else {
			$scope.personalItem();
			$scope.dept = false;
		}
	});

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.toItemTypeMaterials = function(name, code, type) {
		if(type) {
			if(code == "全部" && type == "1") {
				console.log(1);
				$scope.getPersonItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			} else if(code == "全部" && type == "2") {
				$scope.getCorporationItemList();
				$scope.dept = true;
				$scope.deptindex = 1;
			}
		} else {
			data.themeCode = name;
			data.type = $scope.type;
			$scope.getItemListByType(name, $scope.type);
			$scope.dept = true;
		}
	}
	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		addAnimate($('.linkbox1'))
		$scope.isLoading = false;
		if(itemName) {
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			data.areaCode = $scope.areaCode;
			$location.path("/matter");
		} else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.dept = true;
		}
	};
	$scope.lastStep = function() {
		$scope.isLoading = false;
		$scope.personalItem();
		$scope.dept = false;
		$scope.prev = false;
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
});
app.controller("streetController", function($scope, $route, $location, $http, $rootScope, data) {
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.idRead = false;
	//街道下主题
	$scope.getThemeInStreet = function() {
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp('http://hengshui.5uban.com/ac/aci/declare/getThemeInStreet.do', {
			params: tConfig
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.itemName = dataJson.data;
			$scope.itemName.unshift({
				itemTypeName: "000",
				itemTypeCode: "全部"
			});
		}).error(function(err) {
			console.log(err);
		});
	}
	$scope.getThemeInStreet();
	//通过主题获取事项
	$scope.getItemOfThemeInStreet = function(code) {
		$scope.current = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			themeCode: code
		}
		$http.jsonp('http://hengshui.5uban.com/ac/aci/declare/getItemOfThemeInStreet.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function(err) {
			console.log(err);
		});
	}

	$scope.toMaterials = function(itemTypeCode, itemTypeName, stItemName, stItemNo, organCode) {
		if(itemTypeCode == "全部") {
			$scope.getItemOfThemeInStreet("");
		} else {
			$scope.getItemOfThemeInStreet(itemTypeName);
		}
		if(stItemName) {
			data.itemNo = stItemNo;
			data.organCode = organCode;
			data.itemName = "街道";
			$location.path("/select");
		}
	};
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
	$scope.prevStep = function() {
		$location.path("/start");
	}
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout, $rootScope) {
	var name = data.itemName;
	$scope.isLoading = true;
	if(name.length > 30) {
		name = name.slice(0, 30) + '...'
	}
	$scope.itemName = name;
	$scope.allName = data.itemName;

	$scope.getMatterCond = function() {
		removeAnimate($('#wrapper'))
		var oConfig = {
			itemNo: data.itemNo,
			deptCode: data.organCode,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getWindowItemStatusList.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemList = dataJson.windowItemStatusList;
			addAnimate($('#wrapper'))
		}).error(function() {
			console.log('getWindowItemStatusList error')
		})
	};
	$scope.getMatterCond();
	$scope.getSubItem = function(id, name, description, tenNo, itemNo) {
		$scope.isLoading = false;
		data.itemId = id;
		data.statusName = name;
		data.description = description;
		data.itemTenNo = tenNo;
		data.itemNo = itemNo;
		//		 		data.applyNo = "751565219600005";
		//		 		$location.path("/materialList");
		$location.path("/guideline");
	};
	$scope.prevStep = function() {
		data.matindex = 1;
		data.ocode = data.organCode;
		$location.path("/list").search({
			areaCode: data.areaCode
		});
	}

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, data, $timeout) {
	removeAnimate($('.scrollBox2'))
	$scope.isLoading = true
	try {
		window.external.URL_CLOSE();
	} catch(e) {

	}
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	if(data.statusName.length > 25) {
		data.statusName = data.statusName.slice(0, 25) + '...'
	}
	$scope.description = data.description;
	$scope.statusName = data.statusName;
	$scope.ItemStuffList = "";
	clearInterval(t);
	time();
	var lodop = $.device.printGetLodop();
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideInfo = dataJson.guide;
			$scope.clRange = dataJson.guide.clRange;
			addAnimate($('.scrollBox2'))
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	//办事指南二维码地址
	var itemStr = data.itemNo;
	itemStr = itemStr.substring(0, itemStr.length - 1);
	var SHCODE = "SH00JD" + itemStr + 1;
	$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=嘉定区&_organCode_=SH00JD&_organType_=other&_itemId=" + SHCODE + "&_itemType=审批&_stSubitemId=" + data.itemId;
	console.log($scope.codeUrl);
	var qrcode = new QRCode("code", {
		text: $scope.codeUrl,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});

	$scope.print = function() {
		lodop.ADD_PRINT_HTM(24, 0, "100%", "90%", "<style> .widthLimit {width:720px;}</style><body>" + document.getElementById("scrollBox2").innerHTML + "</body>");
		lodop.PRINT();
	};

	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: false,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

	$scope.getGuieInfo();

	$scope.prevStep = function() {
		$location.path("/matter");
	}
	$scope.nextStep = function() {
		$scope.isLoading = false
		$location.path("/precautions");
	}

});

app.controller("precautionsController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.TextBox'))
	$scope.isLoading = true
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	if(data.statusName.length > 25) {
		data.statusName = data.statusName.slice(0, 25) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	addAnimate($('.TextBox'))
	// 继续 
	$scope.nextStep = function() {
		$scope.isLoading = false
		// 判断事项是否通过别的渠道跳转
		var oConfig = {
			itemCode: data.itemTenNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemAppplyInfo.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.isLoading = true;
			if(dataJson) {
				if(dataJson.data.molder == 1) { // 为1 时，走官网一网通办的流程
					localStorage.applyUrl = dataJson.data.itemApplyUrl;
					$location.path("/apply");
				} else { // 为0时，走正常流程
					$location.path("/select");
				}
			}
		}).error(function() {
			$scope.isLoading = true;
			console.log('getItemAppplyInfo error')
		});
	}
	$scope.isScroll = function() {

		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();

	$scope.prevStep = function() {
		$location.path("/guideline");
	}
});

app.controller("applyController", function($scope, $route, $http, $location, data, $timeout, $sce) {
	$scope.isLoading = "true"
	var name = data.itemName;
	$scope.itemName = name;
	// 设置跳转链接
	if(data.itemName == "街道") {
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
		$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/naturalUser/jumpredirect.do?data=" + $scope.encryption(JSON.stringify($scope.data)) + "&redirect_uri=http://ywtb.sh.gov.cn:18018/ac-product-net/netapply/apply.do?itemCode=" + data.itemNo;
		console.info($scope.applyUrl);
		window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);
	} else {
		$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/login/login.jsp?self=my&redirect_uri=http://ywtb.sh.gov.cn:18018" + localStorage.applyUrl;
		console.log($scope.applyUrl);
		window.external.URL_OPEN(200, 150, 1500, 750, $scope.applyUrl);
	}

	// 设置url被angular信任 正常跳转
	$scope.prevStep = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {}
		if(data.itemName == "街道") {
			$location.path("street");
		}
		$location.path("/guideline");
	}

	// 设置url被angular信任 正常跳转
});

app.controller("selectController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.linkBox1'))
	$scope.isLoading = true
	try {
		window.external.URL_CLOSE();
	} catch(e) {}
	$.device.Camera_Hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	addAnimate($('.linkBox1'))
	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$scope.isLoading = false
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$scope.isLoading = false
		$location.path('/citizen');
	}
	$scope.prevStep = function() {
		$scope.isLoading = false
		$location.path("/precautions");
	}
});

app.controller("idCardController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	$scope.isLoading = true
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;
	clearInterval(t);
	time();
	addAnimate($('.main2'))
	$scope.getIdcard = function(info, images) {

		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
		data.idCardSex = info.Sex;
		data.address = info.Address;

		if(!info.ValidtermOfStart || !info.ValidtermOfEnd) {
			alert('没有获取到证件有效期')
		}
		data.startDay = info.ValidtermOfStart;
		data.endDay = info.ValidtermOfEnd;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		if(data.itemName == "街道") {
			$location.path("/apply");
		} else {
			$scope.isLoading = false
			$location.path("/info");
		}
	}

//	测试信息
//	data.idCardName = "梁超";
//	data.idCardNum = "310114198609271218";
//	data.idCardSex = "男";
//	data.address = "上海市嘉定区迎园路351弄26号501室";
//	data.startDay = "2018-10-13";
//	data.endDay = "2038-10-13";
//	$location.path("/info");

	$scope.prevStep = function() {
		$scope.isLoading = false
		$location.path("/select");
	}
});

app.controller("citizenController", function($scope, $route, $http, $location, data, $timeout, $rootScope) {
	$scope.isLoading = true
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}

	$scope.getUserInfoByAccessToken = function() {
		function ClearBr2(key) {
			key = key.replace(/\+/g, "-");
			key = key.replace(/\#/g, ",");
			return key;
		}
		$.ajax({
			type: "get",
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getUserInfoByAccessToken.do",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				accessToken: data.token
			},
			success: function(dataJson) {
				console.log(dataJson);
				if(dataJson != undefined && dataJson != null && dataJson != "") {
					data.idCardName = dataJson.zwdtsw_name;
					data.idCardNum = dataJson.zwdtsw_cert_id;
					data.encrypt_identity = ClearBr2(dataJson.encrypt_identity);
					$timeout(function() {
						if(data.itemName == "街道") {
							$location.path("/apply");
						} else {
							$location.path("/info");
						}
					}, 100);
				} else {
					$scope.SisAlert = true;
					$scope.Smsg = "扫码失败请重试！";
				}
			},
			error: function(err) {}
		});
	}
	//获取token ------2、比对成功后，根据tokenSNO获取access_token
	$scope.getAccessToken = function(tokenSNO) {
		var rec = $.ajax({
			url: $.getConfigMsg.preUrl + "/aci/workPlatform/getAccessToken.do",
			type: "post",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			timeout: 5000,
			data: {
				tokenSNO: tokenSNO,
			},
			success: function(res) {
				console.log(res);
				if(res.SUCCESS === true) {
					data.token = res.accessToken;
					$scope.getUserInfoByAccessToken();
				} else {
					$scope.SisAlert = true;
					$scope.Smsg = "扫码失败请重试！";
				}
			},
			error: function(err) {},
		})
	}

	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		code = code.replace(/[\r\n]/g, "");
		if(code.indexOf("http") != -1) {
			// 记录随申码扫描
			SsmUsedCounter(code)
			$scope.cloud = false;
			$scope.load = true;
			$scope.tipsImage = "../libs/common/images/loadings.gif";
			$.ajax({
				url: $.getConfigMsg.preUrl + "/selfapi/loginService/getTokenSNOByQrCode.do",
				dataType: 'jsonp',
				jsonp: "jsonpCallback",
				data: {
					certQrCode: encodeURIComponent(code),
				},
				success: function(dataJonsp) {
					$scope.loading = false;
					$.log.debug(code);
					if(dataJonsp != null && dataJonsp != undefined && dataJonsp.encrypted == true) {
						layer.msg('正在加载数据，请稍候...')
						$scope.getAccessToken(dataJonsp.biz_response.tokenSNO);
					} else {
						layer.msg('扫码失败请重试')
						$timeout(function() {
							$location.path('/select');
						}, 100);
					}
				},
				error: function(err) {
					layer.msg('扫码失败请重试')
				}
			})
		} else {
			var __code = $scope.ClearBr(code);
			$.ajax({
				url: "http://10.237.16.72/aci/window/getQrCodeInfoByElectronicCert.do",
				dataType: 'jsonp',
				jsonp: "jsonpCallback",
				data: {
					machineId: machineId || '',
					itemName: data.itemName || '',
					itemCode: data.itemId || '',
					businessCode: data.applyId || '',
					using: '',
					codeParam: __code
				},
				success: function(dataJsonp) {
					if(dataJsonp.result.success === false) {
						layer.msg(dataJsonp.result.msg);
						$timeout(function() {
							$location.path('/select');
						}, 100);
					}
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					data.startDay = dataJsonp.result.data.VALIDSTARTDAY;
					data.endDay = dataJsonp.result.data.VALIDENDDAY;
					$.log.debug(data.startDay + ' --开始结束-- ' + data.endDay)
					//data.mobile = dataJsonp.result.data.mobile;
					$timeout(function() {
						if(data.itemName == "街道") {
							$location.path("/apply");
						} else {
							$location.path("/info");
						}
					}, 100);
				},
				error: function(err) {
					console.log("二维码已过期！")
				}
			});
		}
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
	$scope.prevStep = function() {
		$scope.isLoading = false
		$location.path("/select");
	}
});

app.controller("infoController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.scrollBox2'))
	$scope.isLoading = true
	$.device.Camera_Hide();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.fullItemName = name + "--" + data.statusName;
	clearInterval(t);
	time();
	addAnimate($('.scrollBox2'))
	$scope.targetTypeName = '个人';
	$scope.targetTips = '申请人身份证号';
	$scope.targetName = '申请人姓名';
	$scope.concel = "false";
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	// $wathc 监听 targetTypeName 
	$scope.$watch('targetTypeName', function(newValue, oldValue) {
		$scope.targetTypeName == '个人' ? $scope.targetTips = '申请人身份证号' : $scope.targetTips = '统一社会信用代码';
		$scope.targetTypeName == '个人' ? $scope.targetName = '申请人姓名' : $scope.targetName = '企业名称';
		$scope.targetTypeName == '个人' ? $('#targetName').val(data.idCardName) : $('#targetName').val('');
		$scope.targetTypeName == '个人' ? $('#targetNo').val(data.idCardNum) : $('#targetNo').val('');
	});

	$('#username').val(data.idCardName);
	$('#licenseNo').val(data.idCardNum);
	if(data.mobile) {
		$('#mobile').val(data.mobile);
	}

	// 保存数据
	$scope.flag = true;
	$scope.prevStep = function() {
		$location.path("/idCard");
	}
	$scope.goNext = function() {
		/*$location.path("/materialUpload");*/
		if(!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if($scope.targetTypeName == '个人') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的申请人姓名！";
					return;
				}
				if(!checkIdCard($('#targetNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的身份证信息！";
					return;
				}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的经办人姓名！";
					return;
				}
				if(!checkIdCard($('#licenseNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的身份证信息！";
					return;
				}

				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的手机号！";
					return;
				}
			} else if($scope.targetTypeName == '法人') {
				if($('#targetName').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的企业名称！";
					return;
				}
				if($('#targetNo').val().length < 17) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的统一社会信用代码！";
					return;
				} else {
					data.licenseNo = $('#targetNo').val()
				}
				if($('#username').val() < 1) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的经办人姓名！";
					return;
				}
				if(!checkIdCard($('#licenseNo').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的身份证信息！";
					return;
				}

				if(!isPhoneAvailable($('#mobile').val())) {
					$scope.isAlert = true;
					$scope.msg = "请输入正确的手机号！";
					return;
				}

			}
			$scope.isLoading = false
		} while (condFlag);
//		var from = $('#infoForm').serialize();
		var fConfig = {
			applyNo: '',
			targetTypeName: $scope.targetTypeName,
			targetName: $('#targetName').val() || data.idCardName,
			targetNo: $('#targetNo').val() || data.idCardNum,
			itemCode: data.itemTenNo, // data.itemTenNo	'0101220000-00-00-2'
			itemName: data.itemName,
			userId: '',
			username: data.idCardName,
			licenseNo: data.idCardNum,
			mobile: $('#mobile').val(),
			source: '网上申请',
			departCode: data.organCode,
			departName: data.organName,
			content: $('#content').val() || '',
			jsonpCallback: "JSON_CALLBACK",
		};
//		alert(JSON.stringify(fConfig))
		$http.jsonp(urlHost + '/aci/declare/saveApply.do?', {
//		$http.jsonp('http://10.2.104.249:8080/ac-product/aci/declare/saveApply.do?', {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			data.applyNo = dataJson.applyNo;
			data.mobile = $('#mobile').val();
			if(($('#username').val()) !== null) {
				data.username = $('#username').val();
			} else if(($('#targetName').val()) !== null) {
				data.username = $('#targetName').val();
			}
			$location.path("/materialList");
		}).error(function(e) {
			console.log(e)
		});
		$scope.flag = false;
	};
});

app.controller("materialUploadController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.main2'))
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$.device.fileClose();
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	addAnimate($('.main2'))
	//上传材料信息
	$scope.stStuffName = data.stuffImg.stuffName;
	$scope.stuffImg = data.sample;
	$scope.test = function() {
		$('#test').viewer({
			url: 'data-original',
		});
	}
	$scope.upload = function() {
		$location.path("/uploadMethod");
	}
	$scope.prevStep = function() {
		$location.path("/materialList");
	}
	$scope.nextStep = function() {
		$location.path("/uploadMethod");
	}

});
app.controller("uploadMethodController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.scrollBox2'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$scope.statusName = data.statusName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	clearInterval(t);
	time();
	addAnimate($('.scrollBox2'))
	// 扫描上传
	$scope.scanPhoto = function() {
		$location.path('/finish');
	};
	// U盘上传
	$scope.isAlert = false;
	$scope.msg = '请确认是否插入U盘';
	$scope.takePhoto = function() {
		$scope.isAlert = true;
		//		layer.confirm("<em style='color:black'>" + '请确认是否插入U盘！' + "</em>", {
		//			btn: ['已插入U盘', '未插入U盘'] //按钮
		//		}, function() {
		//			$('.layui-layer-shade').hide();
		//			$('.layui-layer').hide();
		//			$timeout(function() {
		//				$location.path('/takePhoto/U');
		//			}, 20);
		//		}, function() {
		//			$('.layui-layer-shade').hide();
		//			$('.layui-layer').hide();
		//			$timeout(function() {
		//				layer.msg('请选择其他上传方式！');
		//			}, 20);
		//		});

	};
	$scope.alertConfirm = function() {
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			$location.path('/takePhoto/U');
		}, 20);
	}
	$scope.alertCancel = function() {
		$scope.isAlert = false;
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			/*layer.msg('请选择其他上传方式！');*/
		}, 20);
	}
	// 档案库上传
	$scope.materialPic = function() {
		$location.path('/history');
	};
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
});
app.controller("historyController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('.linkBox1'))
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();
	var name = data.itemName;
	$.device.fileClose();
	$scope.statusName = data.statusName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	// 显示材料名称
	$scope.stStuffName = data.stStuffName;
	$scope.stuffNameShow = data.isShowStuffName;
	clearInterval(t);
	time();
	addAnimate($('.linkBox1'))
	// 个人电子证照上传
	$scope.scanPhoto = function() {
		$location.path('/materialPic');
	};
	// 法人电子证照上传
	$scope.businessPhoto = function() {
		$location.path('/materialPic2');
	};
	// 档案库上传
	$scope.materialPic = function() {
		$scope.isLoading = false;
		$location.path('/materialPic1');
	};
	$scope.prevStep = function() {
		$location.path('/uploadMethod');
	}
});
app.controller("materialPicController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.isLoading = false;
	$scope.isShowView = false; // 是否显示预览框
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	$scope.itemName = name;
	$scope.imgUrls = "";
	clearInterval(t);
	time();
	$scope.profileShow = function() {
		$.ajax({
			url: "http://10.237.16.72/aci/autoterminal/dzzz/queryCertBaseData.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				machineId: machineId || '',
				itemName: data.itemName || '',
				itemCode: data.itemId || '',
				businessCode: data.applyId || '',
				jsonpCallback: "JSON_CALLBACK",
				certNo: data.encrypt_identity||data.idCardNum, //"340881199303145313" || 
				type: 0,
				//新增参数
				name: data.idCardName || '',
				startDay: data.startDay || '',
				endDay: data.endDay || ''
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
					console.log($scope.imgUrls)
				}
				$scope.$apply();
				$scope.isLoading = true;
			},
			error: function(err) {
				console.log(err)
				layer.msg("请求失败!");
			}
		});
	};
	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		if($scope.current == index) {
			$scope.isShowView = true;
		}
		$scope.current = index;
		$scope.electImg = " http://10.237.16.72" + $scope.imgUrls[$scope.current].pictureUrlForBytes;
	}
	$scope.isLoading = true;
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
	$scope.goNext = function() {
		data.selectImg = "http://10.237.16.72" + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			'applyNo': data.applyNo, //  '751122018600008'
			'stuffId': data.stStuffId,
			'stuffCode': data.stStuffCode,
			'stuffName': data.stStuffName,
			'stuffType': 0,
			'stuffStatus': 0,
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
				$.device.httpUpload(urlHost + '/aci/declare/uploadStuff.do', "file", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						layer.msg("上传成功");
						$scope.isLoading = true;
						//						data.isUpload.push({
						//							index: data.currentIndex,
						//							stuffName: data.stStuffName,
						//							img:$scope.waitUploadImgUrl,
						//							method:"个人档案"
						//						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};

	$scope.viewClose = function() {
		$scope.isShowView = false;
		$scope.zoomCount = 1;
		$scope.rotateCount = 0;
	};

	$scope.zoomIn = function() {
		$scope.zoomCount += 0.5;
		if($scope.zoomCount > 5) {
			$scope.zoomCount = 5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.zoomOut = function() {
		$scope.zoomCount -= 0.5;
		if($scope.zoomCount < 0.5) {
			$scope.zoomCount = 0.5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateLeft = function() {
		$scope.rotateCount -= 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateRight = function() {
		$scope.rotateCount += 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

});

app.controller("materialPic1Controller", function($scope, $route, $http, $location, data, $timeout) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	var Btn = document.getElementById("consureOnclick");
	Btn.style.display = "block";
	$scope.itemName = name;
	$scope.imgUrls = "";
	clearInterval(t);
	time();
	$scope.profileShow = function() {
		$.ajax({
			//			url: "http://10.237.16.72/aci/autoterminal/dzzz/queryCertBaseData.do",
			url: urlHost + "/aci/autoterminal/forward.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				//				jsonpCallback: "JSON_CALLBACK",
				//				certNo: data.idCardNum, //"340881199303145313" || idCardNum  idCardName
				//				type: 0
				fmd: "aci-archives",
				fdo: "getLicenseStuffList",
				jsonpCallback: "JSON_CALLBACK",
				stName: data.idCardName, // "夏雷" ||"340881199303145313" || 
				stIdNo: data.idCardNum,
				type: 0
			},
			success: function(json) {
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson || dataJson == '') { // !dataJson[0].address
					layer.msg("没有数据，请重新选择上传方式!");
					$timeout(function() {
						$location.path('/uploadMethod');
					}, 1000);
				} else {
					$scope.imgUrls = dataJson;
					console.log($scope.imgUrls)
				}
				$scope.isLoading = true;
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
				layer.msg("请求失败!");
			}
		});
	};

	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		$scope.current = index;

	}

	$scope.goNext = function() {

		$timeout(function() {
			layer.msg('上传超时，请重新上传！')
			$location.path('/materialList');
		}, 12000)

		//剔除最后一张电子照片----避免闪退
		//if($scope.current == $scope.imgUrls.length-1){
		//	alert('电子照片无法上传，请重新选择！')
		//	return;
		//}

		//		Btn.style.display = "none";//-----------------------
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].imageUrl;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			fileName: "waitUploadImg.jpg",
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
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
				$.device.httpUpload(urlHost + '/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					//				$.device.httpUpload('http://10.2.100.128:8080/ac-product/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						data.resetData = "";
						angular.element(document.querySelector('.imgStyle')).addClass('uploaded')
						//						layer.msg("上传成功，选中并点击确认继续上传，上传完成请点击“返回列表”");
						layer.alert('上传成功！点击"确认"继续上传,上传完成请点击"返回列表"!', {
							skin: 'layui-layer-lan',
							closeBtn: 1,
							anim: 5 //动画类型
						});
						$scope.isLoading = true;
						data.isUploadForPush.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						//						$timeout(function() {
						//							$location.path('/materialList');
						//						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};

});

app.controller("materialPic2Controller", function($scope, $route, $http, $location, data, $timeout) {
	$scope.isShowView = false; // 是否显示预览框
	$scope.isLoading = false;
	//检测到没有同意识别码
	var certNo = data.licenseNo;
	if(certNo == '' || !certNo) {
		layer.msg('请手动输入统一识别码！');
		$timeout(function() {
			$location.path('/info')
		}, 2000);
	}
	console.log(certNo)
	$scope.zoomCount = 1;
	$scope.rotateCount = 0;
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	var Btn = document.getElementById("consureOnclick");
	Btn.style.display = "block";
	$scope.itemName = name;
	$scope.imgUrls = "";
	clearInterval(t);
	time();
	$scope.profileShow = function() {
		$.ajax({
			url: urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do",
			//url: "http://10.237.16.72/aci/autoterminal/forward.do",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: {
				machineId: machineId || '',
				itemName: data.itemName || '',
				itemCode: data.itemId || '',
				businessCode: data.applyId || '',
				//jsonpCallback: "JSON_CALLBACK",
				certNo: certNo, //91310114MA1GUDX96C//"340881199303145313" || idCardNum  idCardName
				type: 1,
				//新增参数
				name: data.idCardName || '',
				startDay: data.startDay || '',
				endDay: data.endDay || ''
				/*fmd: "aci-archives",
				fdo: "getLicenseStuffList",
				jsonpCallback: "JSON_CALLBACK",
				stName: data.idCardName, // "夏雷" ||"340881199303145313" || 
				stIdNo: data.idCardNum,
				type: 0*/
			},
			success: function(json) {
				console.log(json)
				var dataJson = eval("(" + JSON.stringify(json) + ")");
				if(!dataJson || dataJson == '') { // !dataJson[0].address
					console.log('没有数据,请核验统一识别码！')
					$timeout(function() {
						$location.path('/info');
					}, 2000);
				} else {
					$scope.imgUrls = dataJson;
					console.log($scope.imgUrls)
				}
				$scope.isLoading = true;
				$scope.$apply();
			},
			error: function(err) {
				console.log(err)
				layer.msg("请求失败!");
			}
		});
	};

	$scope.profileShow();
	$scope.current = 0;
	$scope.select = function(index) {
		if($scope.current == index) {
			$scope.isShowView = true;
		}
		$scope.current = index;
		$scope.electImg = " http://10.237.16.72" + $scope.imgUrls[$scope.current].pictureUrlForBytes;

	}

	$scope.goNext = function() {

		$timeout(function() {
			layer.msg('上传超时，请重新上传！')
			$location.path('/materialList');
		}, 12000)

		//剔除最后一张电子照片----避免闪退
		//if($scope.current == $scope.imgUrls.length-1){
		//	alert('电子照片无法上传，请重新选择！')
		//	return;
		//}

		//		Btn.style.display = "none";//-----------------------
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.isLoading = false;
		$scope.jsonData = {
			applyId: data.applyId,
			/*"205fae3a-beee-492e-826e-7dd86513b2a8",*/
			stuffId: data.stStuffId,
			reset: data.resetData,
			fileName: "waitUploadImg.jpg",
			type: "2",
			itemId: data.itemId,
			stuffName: data.stStuffName,
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
				$.device.httpUpload(urlHost + '/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					//				$.device.httpUpload('http://10.2.100.128:8080/ac-product/aci/materialUp/uploadFile.do', "fileInput", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						data.resetData = "";
						angular.element(document.querySelector('.imgStyle')).addClass('uploaded')
						layer.msg("上传成功!”");
						$scope.isLoading = true;
						data.isUploadForPush.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						$scope.isfinishUp.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
						for(var i = 0; i < data.isUpload.length; i++) {
							if(data.currentIndex == data.isUpload[i].index) {
								data.isUpload[i] = "";
							}
						}
						for(var i = 0; i < $scope.isfinishUp.length; i++) {
							data.isUpload.push($scope.isfinishUp[i]);
						}
						$timeout(function() {
							$location.path('/materialList');
						}, 1000);
					},
					function(webexception) {
						$scope.isLoading = true;
						layer.msg("上传失败");
					});
			},
			function(webexception) {
				alert("下载文档失败");
			}
		);

	};
	$scope.viewClose = function() {
		$scope.isShowView = false;
		$scope.zoomCount = 1;
		$scope.rotateCount = 0;
	};

	$scope.zoomIn = function() {
		$scope.zoomCount += 0.5;
		if($scope.zoomCount > 5) {
			$scope.zoomCount = 5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.zoomOut = function() {
		$scope.zoomCount -= 0.5;
		if($scope.zoomCount < 0.5) {
			$scope.zoomCount = 0.5;
		}
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateLeft = function() {
		$scope.rotateCount -= 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

	$scope.rotateRight = function() {
		$scope.rotateCount += 90;
		$(".view-container .img-container img")[0].style.transform = "scale(" + $scope.zoomCount + "," + $scope.zoomCount + ")" + " " + "rotate(" + $scope.rotateCount + "deg" + ")";
	};

});
app.controller("takePhotoController", function($scope, $route, $http, $location, data, $timeout, $routeParams) {
	var name = data.itemName;

	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	$scope.itemName = name;
	clearInterval(t);
	time();
	try {
		$.device.fileOpen(function(value) {
			alert(value);
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
				'applyNo': data.applyNo, //   '751122018600008'
				'stuffId': data.stStuffId,
				'stuffCode': data.stStuffCode,
				'stuffName': data.stStuffName,
				'stuffType': 0,
				'stuffStatus': 0
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/declare/uploadStuff.do", "file", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.fileName.push($scope.UData);
					//					data.isUpload.push({
					//						index: data.currentIndex,
					//						stuffName: data.stStuffName,
					//						fileName:$scope.UData,
					//						method:"U盘上传"
					//					});
					$scope.isfinishUp.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					for(var i = 0; i < data.isUpload.length; i++) {
						if(data.currentIndex == data.isUpload[i].index) {
							data.isUpload[i] = "";
						}
					}
					for(var i = 0; i < $scope.isfinishUp.length; i++) {
						data.isUpload.push($scope.isfinishUp[i]);
					}
					$timeout(function() {
						$location.path('/materialList');
					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
	};
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
	// U盘上传
	$scope.takePhoto = function() {
		if($scope.UData == null) {
			layer.msg("未获取到U盘数据");
		} else {
			$scope.jsonData1 = {
				'applyNo': data.applyNo, //   '751122018600008'
				'stuffId': data.stStuffId,
				'stuffCode': data.stStuffCode,
				'stuffName': data.stStuffName,
				'stuffType': 0,
				'stuffStatus': 0
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload(urlHost + "/aci/declare/uploadStuff.do", "file", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.fileName.push($scope.UData);
					//					data.isUpload.push({
					//						index: data.currentIndex,
					//						stuffName: data.stStuffName,
					//						fileName:$scope.UData,
					//						method:"U盘上传"
					//					});
					$scope.isfinishUp.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
					//					for(var i = 0; i < data.isUpload.length; i++) {
					//						if(data.currentIndex == data.isUpload[i].index) {
					//							data.isUpload[i] = "";
					//						}
					//					}
					//					for(var i = 0; i < $scope.isfinishUp.length; i++) {
					//						data.isUpload.push($scope.isfinishUp[i]);
					//					}
					//					$timeout(function() {
					//						$location.path('/materialList');
					//					}, 100);
				},
				function(webexception) {
					layer.msg("上传失败");
				});
		}
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
	};
	$scope.alertConfirm = function() {
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			$location.path('/takePhoto/U');
		}, 20);
	}
	$scope.alertCancel = function() {
		alert('取消')
		$scope.isAlert = false;
		$('.layui-layer-shade').hide();
		$('.layui-layer').hide();
		$timeout(function() {
			layer.msg('请选择其他上传方式！');
		}, 20);
	}
	//	----未定
});
app.controller("finishController", function($scope, $route, $http, $location, data, $timeout, $compile) {
	$('.layui-layer-shade').hide();
	$('.layui-layer').hide();

	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.isfinishUp = [];
	$scope.itemName = name;
	clearInterval(t);
	time();
	$.device.cmCaptureShow(680, 530, 210, 340);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	// 
	$scope.isFinish = false;
	$scope.isLoading = true;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	var isShow = true;
	$scope.next = function() {
		if(isShow == false) {
			$.device.cmCaptureShow(680, 530, 210, 340); // 开启高拍仪
			isShow = true;
			return;
		}
		$.device.cmCaptureHide(); // 关闭高拍仪
		$scope.isLoading = false;
		var scanImg = $.device.cmCaptureCaptureUrl();
		$scope.jsonData1 = {
			'applyNo': data.applyNo, //   '751122018600008'
			'stuffId': data.stStuffId,
			'stuffCode': data.stStuffCode,
			'stuffName': data.stStuffName,
			'stuffType': 0,
			'stuffStatus': 0
		};
		$scope.jsonData1 = JSON.stringify($scope.jsonData1);
		$.device.httpUpload(urlHost + "/aci/declare/uploadStuff.do", "file", scanImg,
			$scope.jsonData1,
			function(result) {
				layer.msg("上传材料成功！");
				data.uploadStuffId = data.stStuffId;
				//$.log.debug("这是为了测试是否上传成功scanImg:"+scanImg);
				//$.log.debug("这是为了测试是否上传成功材料id:"+data.uploadStuffId );
				if(data.listImg.length < 1) {
					data.currentIndex++; // 没有材料列表时   文件下标+1 
				}
				$scope.isfinishUp.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				console.log(data.isUpload);
				data.fileName.push('扫描文件');
				$.log.debug("scanImg:" + scanImg);
				/*动态生成照片盒子*/
				imgHTML +=
					'<div ng-click="show()" class="img" id="' + data.uploadStuffId +
					'"><img src="' + scanImg + '" width="150" height="200" /></div>';
				$('.imgBox').html($compile(imgHTML)($scope));
				$scope.isFinish = true;
				$scope.isLoading = true;
				$.device.cmCaptureShow(680, 530, 210, 340); // 开启高拍仪
				data.fileName.push('scanImg');
				$scope.$apply();
			},
			function(webexception) {
				$scope.isLoading = true;
				layer.msg("上传材料失败");
				$timeout(function() {
					$location.path('/finish');
				}, 10000);
			});
	};
	$scope.show = function() {
		$.device.cmCaptureHide();
		$(".img").viewer({
			url: "src",
		});
		isShow = false;
	};
	// 完成拍照
	$scope.finishUpload = function() {
		for(var i = 0; i < data.isUpload.length; i++) {
			if(data.currentIndex == data.isUpload[i].index) {
				data.isUpload[i] = "";
			}
		}
		for(var i = 0; i < $scope.isfinishUp.length; i++) {
			data.isUpload.push($scope.isfinishUp[i]);
		}
		$timeout(function() {
			$.device.cmCaptureHide(); // 关闭高拍仪
			$location.path('/materialList');
		}, 20);
	};

	$scope.prevStep = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialList');
	}
});
app.controller("materialListController", function($scope, $route, $http, $location, data, $timeout) {
	removeAnimate($('#wrapper>div'))
	$scope.isLoading = true
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$.device.fileClose();
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	clearInterval(t);
	time();
	addAnimate($('#wrapper>div'))
	$scope.current = 0;
	console.log(data.isUpload);
	// 获取材料列表  	
	var fConfig = {
		itemCode: data.itemTenNo, //"0145652000",//
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp(urlHost + '/aci/declare/getItemStuffList.do', {
		params: fConfig
	}).success(function(dataJson) {
		$scope.stuffList = dataJson.data;
		if(data.listImg == 0) {
			for(var i = 0; i < $scope.stuffList.length; i++) {
				data.listImg[i] = {
					'activeImg': null,
					'index': i,
					'stuffName': $scope.stuffList[i].stuffName,
					'upload': true,
					'upload2': false,
				}
			}
		}
		//设置上传文件 按钮变化
		if(data.isUpload != "") {
			for(var i = 0; i < data.isUpload.length; i++) {
				for(var j = 0; j < data.listImg.length; j++) {
					if(data.listImg[j].upload != false) {
						if(data.isUpload[i].stuffName == data.listImg[j].stuffName) {
							data.listImg[data.isUpload[i].index].upload = false;
							data.listImg[data.isUpload[i].index].upload2 = true;
						}
					}
				}
			}
		}
		$scope.listImg = data.listImg;
	}).error(function() {
		console.log('queryStuffList error')
	})

	// 材料上传 
	data.currentIndex++;
	$scope.toUploadMaterial = function(index, id, code, name) {
		data.stStuffId = id;
		data.stStuffCode = code;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}
	//重新上传
	$scope.toNewUploadMaterial = function(index, id, code, name) {
		for(var i = 0; i < data.isUpload.length; i++) {
			if(index == data.isUpload[i].index) {
				//data.isUpload[i] = "";
			}
		}
		data.stStuffId = id;
		data.stStuffCode = code;
		data.stStuffName = name;
		data.currentIndex = index;
		data.stuffImg = data.listImg[data.currentIndex];
		$location.path("/uploadMethod");
	}

	//查看
	$scope.view = function(index, code, name) {
		data.currentIndex = index;
		data.view = data.isUpload;
		$location.path("/materialView");
	}

	//提交办件
	$scope.submit = function() {
		$scope.isLoading = false
		$location.path('/infoFinish');
	};

	$scope.prevStep = function() {
		$location.path('/info');
	}

	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: false,
			bounce: true,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("materialViewController", function($scope, $http, $location, data) {
	$scope.stuffList = [];
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	for(var i = 0; i < data.view.length; i++) {
		if(data.currentIndex == data.view[i].index) {
			$scope.stuffList.push(data.view[i]);
		}
	}

	if($scope.stuffList[0].method === "高拍仪") {
		$scope.scanShow = true;
		$scope.upanShow = false;
		$scope.picShow = false;
	} else if($scope.stuffList[0].method === "U盘上传") {
		$scope.scanShow = false;
		$scope.upanShow = true;
		$scope.picShow = false;
	} else if($scope.stuffList[0].method === "个人档案") {
		$scope.scanShow = false;
		$scope.upanShow = false;
		$scope.picShow = true;
	}
	//放大样张
	$scope.showWord = function() {
		$(".word").viewer({
			url: "data-original",
		});
	}
	//放大已上传材料
	$scope.showScan = function() {
		$(".scan").viewer({
			url: "data-original",
		});
	}
	//打开文件
	$scope.open = function() {
		try {
			$.device.officeOpen($scope.stuffList[0].fileName);
		} catch(e) {
			layer.msg("未找到此文件");
		}
	}
	$scope.prevStep = function() {
		$location.path('/materialList');
	}
});
app.controller("infoFinishController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.isLoading = true
	$scope.allName = data.itemName + '--' + data.statusName;
	var lodop = $.device.printGetLodop();
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	clearInterval(t);
	time();
	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = {
			applyNo: data.applyNo, // '751122018600008'
			subItemCodes: '', // data.itemId
			//applyId: data.applyId,
			//userName: data.idCardName,
			//subitemNos: "",
			//itemNo: data.itemNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/submitApply.do', {
			//$http.jsonp(urlHost + '/aci/materialUp/toSubmit.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			$scope.applyNo = data.applyNo;
			console.log(dataJson);
		}).error(function(e) {
			console.log(e)
		});
	}

	$scope.submitApply();
	$scope.applyNo = data.applyNo;
	console.log(data.applyNo);
	$scope.statusText = '打印凭证';
	var code = "http://218.202.254.222/aci/ac-self-front/declare/index.html#/qrCode?applyNo=" + data.applyNo;
	//var code = urlHost + "/aci/workapply/getNewQueueInfo.do?t=b&bid=" + data.applyNo;
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	var f = 0;
	$scope.print = function() {
		f++;
		$scope.statusText = '返回首页';
		if($scope.statusText == '返回首页' && f == 2) {
			$.device.GoHome();
		} else {
			lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
			lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
			lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "统一审批编码：" + data.applyNo);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.username);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "联系方式：" + data.mobile);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(340, 28, 600, 30, "申请事项：" + $scope.allName);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			//lodop.ADD_PRINT_TEXT(400, 28, 450, 30, "请您携带办理材料至嘉定区行政服务中心进行办理。");
			//lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(430, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(510, 28, 670, 30, "亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.PRINT();

			lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
			lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
			lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "统一审批编码：" + data.applyNo);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.username);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "联系方式：" + data.mobile);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(340, 28, 600, 30, "申请事项：" + $scope.allName);
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			//lodop.ADD_PRINT_TEXT(400, 28, 450, 30, "请您携带办理材料至嘉定区行政服务中心进行办理。");
			//lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(430, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.ADD_PRINT_TEXT(510, 28, 670, 30, "亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
			lodop.PRINT();
		}
	};
	$scope.print()
});
app.controller("qrCodeController", function($scope, $route, $http, $location, data, $timeout) {
	$scope.applyNo = $location.search().applyNo;
	$scope.isLoading = false;
	$scope.process = function() {
		var pConfig = {
			stApplyNo: $scope.applyNo,
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp('http://218.202.254.222/aci/autoterminal/eventquery/getApplyInfoByStApplyNo.do', {
			params: pConfig
		}).success(function(dataJson) {
			console.log(dataJson)
			$scope.applyNo = dataJson.stApplyNo
			$scope.itemName = dataJson.stItemName;
			$scope.name = dataJson.stName || dataJson.stUnit;
			$scope.date = dataJson.stApplyStr;
			$scope.status = dataJson.stFinalState;
			$scope.isLoading = true;
		}).error(function(err) {
			console.log('getApplyInfoByStApplyNo error');
		});
	}
	$scope.process();
});