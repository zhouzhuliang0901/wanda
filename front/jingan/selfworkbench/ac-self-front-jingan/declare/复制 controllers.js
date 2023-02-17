app.controller("startController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	data.ocode = "";
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
app.controller("mainController", function($scope, $location, $http, data, $rootScope, $timeout, $interval, appFactory) {
	$scope.areaList = [];
	$scope.getAreaList = function() {
		var aConfig = {
			jsonpCallback: "JSON_CALLBACK"
		}
		$http.jsonp("http://10.1.93.168:8080/ac/aci/declare/getAllAreaInShanghai.do", {
			params: aConfig
		}).success(function(data) {
			for(var i = 1; i < data.organSetList.length; i++) {
				$scope.areaList.push(data.organSetList[i]);
			}
		}).error(function() {
			console.log('getAreaList error');
		});
	}
	$scope.getAreaList();
	$scope.choice = function(areaName, areaCode) {
		data.areaName = areaName;
		data.areaCode = areaCode;
		$location.path("/list");
	}
	$rootScope.goHome = function() {
		window.external.URL_CLOSE();
		$.device.cmCaptureHide();
		$.device.Camera_Hide();
		$.device.qrCodeClose();
		$.device.GoHome();
	};
	$scope.isScroll = function() {
		new iScroll("wrapper", {
			vScrollbar: true,
			hScrollbar: false,
			hideScrollbar: true,
			bounce: true,
			click: true,
			taps: true,
			preventDefault: false,
			hScroll: false,
			checkDOMChanges: true
		});
	};
	$scope.isScroll();
});
app.controller("listController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	$scope.current = data.matindex || 0;
	$scope.searchType = ["按部门", "按事项"];
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.organCode = data.ocode || null;
	$scope.matterVal = '';
	data.areaL = "SH";

	//查询事项
	$scope.getSearchMatter = function(current) { // 查询事项
		$scope.current = 1;
		$scope.organCode = 'search';
		var vConfig = {
			itemName: $scope.matterVal,
			jsonpCallback: "JSON_CALLBACK",
		};
		if($scope.matterVal) {
			$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getItemListByItemNameForPage.do', {
				params: vConfig
			}).success(function(dataJson) {
				$('.tabBotbox1inner').find('a:first').addClass('active').siblings().removeClass('active');
				$scope.itemName = dataJson.itemSetList;
			}).error(function() {
				console.log('getOrganListForDeclarePage error')
			})
		} else {
			layer.msg('请输入事项名称');
		}
	};
	//获取所有事项
	$scope.getMatter = function() {
		$scope.current = 1;
		$scope.matterVal = '';
		$scope.active = null;
		$scope.organCode = null;
		var config = {
			jsonpCallback: "JSON_CALLBACK",
		}
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getAllItemListForPage.do', {
			params: config
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
		}).error(function() {
			console.log('getAllItemListForPage error')
		})
	};
	//获取所有部门
	$scope.getDepartment = function() {
		$scope.matterVal = '';
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode: data.areaCode
		};
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过部门id获取部门事项
	$scope.getItemByOrganCode = function(code) {
		$scope.current = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};

	$scope.$on('$viewContentLoaded', function() {
		if($scope.organCode !== null) {
			$scope.getItemByOrganCode($scope.organCode);
		} else {
			$scope.getDepartment();
			$scope.idRead = false;
		}
	});

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

	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		if(itemName) {
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			$location.path("/matter");
		} else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.idRead = true;
		} else if(organName == "全部") {
			$scope.getMatter();
			$scope.idRead = true;
		}
	};
});
app.controller("citylistController", function($scope, $route, $location, $http, $rootScope, data, appFactory) {
	$scope.current = 0;
	$scope.searchType = ["个人","法人","部门"];
	data.areaL = "SH00SH";
	data.isUpload = [];
	data.listImg = [];
	data.fileName = [];
	$scope.current = data.matindex || 0;
	$scope.itemName = [];
	$scope.currentMatters = [];
	$scope.matterVal = '';
	$scope.organCode = data.ocode || null;
	$scope.itemName = "";
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
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.corporationItem();
				$scope.dept = false;
				break;
			case "个人":
				$scope.itemName = "";
				$scope.itemType = "";
				$scope.personalItem();
				$scope.dept = false;
		}
	};
	//个人
	$scope.personalItem = function(){
		$scope.deptindex = 0;
		$scope.current = 0;
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getItemTheme.do',{
			params:{
				jsonpCallback: "JSON_CALLBACK",
				areaCode : 'SH00SH',
				type:'1'
			}
		}).success(function(dataJson){
			console.log(dataJson);
			$scope.itemType = dataJson.data;
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//法人
	$scope.corporationItem = function(){
		$scope.deptindex = 0;
		$scope.current = 1;
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getItemTheme.do',{
			params:{
				jsonpCallback: "JSON_CALLBACK",
				areaCode : 'SH00SH',
				type:'2'
			}
		}).success(function(dataJson){
			console.log(dataJson);
			$scope.itemType = dataJson.data;
		}).error(function(err){
			console.log("getItemTheme error");
		});
	}
	//获取所有部门
	$scope.getDepartment = function() {
		$scope.matterVal = '';
		$scope.current = 2;
		$scope.deptindex = 0;
		var organConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:'SH00SH'
		};
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getDeptInArea.do', {
			params: organConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.organSetList;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	};
	//通过主题查事项
	$scope.getItemListByType = function(code){
		$scope.deptindex = 1;
		var tConfig = {
			jsonpCallback: "JSON_CALLBACK",
			areaCode:'SH00SH',
			themeCode:code
		};
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getItemInTheme.do', {
			params: tConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.data;
		}).error(function() {
			console.log('getOrganListForDeclarePage error')
		})
	}

	//查询事项
	$scope.getSearchMatter = function(current) { // 查询事项
		$scope.current = 1;
		$scope.isDept = false;
		$scope.organCode = 'search';
		var vConfig = {
			itemName: $scope.matterVal,
			jsonpCallback: "JSON_CALLBACK",
		};
		if($scope.matterVal) {
			$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getItemListByItemNameForPage.do', {
				params: vConfig
			}).success(function(dataJson) {
				$('.tabBotbox1inner').find('a:first').addClass('active').siblings().removeClass('active');
				$scope.itemName = dataJson.itemSetList;
			}).error(function() {
				console.log('getOrganListForDeclarePage error')
			})
		} else {
			layer.msg('请输入事项名称');
		}
	};
	//	//获取所有事项
	//	$scope.getMatter = function() {
	//		$scope.current = 1;
	//		$scope.isDept = false;
	//		$scope.matterVal = '';
	//		$scope.active = null;
	//		$scope.organCode = null;
	//		var config = {
	//			jsonpCallback: "JSON_CALLBACK",
	//		}
	//		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getAllItemListForPage.do', {
	//			params: config
	//		}).success(function(dataJson) {
	//			$scope.itemName = dataJson.itemSetList;
	//		}).error(function() {
	//			console.log('getAllItemListForPage error')
	//		})
	//	};
	//	//获取所有部门
	//	$scope.getDepartment = function() {
	//		$scope.matterVal = '';
	//		$scope.itemName = "";
	//		$scope.current = 0;
	//		$scope.isDept = true;
	//		var organConfig = {
	//			jsonpCallback: "JSON_CALLBACK",
	//			areaCode:'SH00SH'
	//		};
	//		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getDeptInArea.do', {
	//			params: organConfig
	//		}).success(function(dataJson) {
	//			$scope.itemName = dataJson.organSetList;
	//		}).error(function() {
	//			console.log('getOrganListForDeclarePage error')
	//		})
	//	};
	//通过部门id获取部门事项
	$scope.getItemByOrganCode = function(code){
		$scope.current = 2;
		$scope.dept = true;
		$scope.deptindex = 1;
		var oConfig = {
			organCode: code,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp('http://10.1.93.168:8080/ac/aci/declare/getItemListByOrganCodeForPage.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemName = dataJson.itemSetList;
		}).error(function() {
			console.log('getItemListByOrganCodeForPage error')
		})
	};
	
	$scope.$on('$viewContentLoaded', function() {
		$scope.personalItem();
		$scope.dept = false;
	});
	
	$scope.isScroll = function(){
		
		new iScroll("wrapper",{
			vScrollbar: true,
			hScrollbar:false,
			hideScrollbar:false,
			bounce:true,
			hScroll: false,
			checkDOMChanges:true
		});
	};
	$scope.isScroll();
	
	$scope.toItemTypeMaterials = function(name,code){
		if(name){
			$scope.getItemListByType(name);
			$scope.dept = true;
		}
	}
	$scope.toMaterials = function(itemName, organCode, organName, itemNo) {
		if(itemName){
			data.itemName = itemName;
			data.organCode = organCode;
			data.organName = organName;
			data.itemNo = itemNo;
			$location.path("/matter");
		}else if(organCode) {
			$scope.getItemByOrganCode(organCode);
			$scope.dept=true;
		}
	};
});
app.controller("matterController", function($scope, $route, $http, $location, data, $timeout, $rootScope, appFactory) {
	$scope.allName = data.itemName;
	var name = data.itemName;
	$scope.itemName = name;;
	$scope.getMatterCond = function() {
		var oConfig = {
			itemNo: data.itemNo,
			deptCode: data.organCode,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1 + '/aci/declare/getWindowItemStatusList.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.itemList = dataJson.windowItemStatusList;
		}).error(function() {
			console.log('getWindowItemStatusList error')
		})
	};
	$scope.getMatterCond();
	$scope.getSubItem = function(id, name, description, tenNo, itemNo) {
		data.itemId = id;
		data.statusName = name;
		data.description = description;
		data.itemTenNo = tenNo;
		data.itemNo = itemNo;
		$location.path("/guideline");
	};
	$scope.prev = function() {
		data.matindex = 1;
		if(data.areaL == "SH00SH") {
			data.ocode = data.organCode;
			$location.path("/citylist");
		} else if(data.areaL == "SH") {
			data.ocode = data.organCode;
			$location.path("/list");
		}

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
});
app.controller("guidelineController", function($scope, $route, $http, $location, $sce, $rootScope, data, $timeout, appFactory) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {

	}
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
	$scope.getGuieInfo = function() {
		var oConfig = {
			stZhallId: data.itemId,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost1 + '/aci/declare/getGuideInfoByZhallId.do', {
			params: oConfig
		}).success(function(dataJson) {
			$scope.guideInfo = dataJson.guide;
			$scope.guideInfo.stPromiseTime = $sce.trustAsHtml("<p>法定办结时限:" + $scope.guideInfo.stPromiseTime + "</p><p>承诺办结时限:" + $scope.guideInfo.stLegalTime + "</p>");
			$scope.clRange = dataJson.guide.clRange;
		}).error(function() {
			console.log('getGuideInfoByZhallId error')
		});
	};
	//办事指南二维码地址
	var itemStr = data.itemNo;
	itemStr = itemStr.substring(0, itemStr.length - 1);
	var SHCODE = "SH00HK" + itemStr + 1;
	$scope.codeUrl = "http://zwdt.sh.gov.cn/govPortals/bsfw/findBsfw.do?_organName_=虹口区&_organCode_=SH00HK&_organType_=other&_itemId=" + SHCODE + "&_itemType=审批&_stSubitemId=" + data.itemId;
	console.log($scope.codeUrl);
	var qrcode = new QRCode("code", {
		text: $scope.codeUrl,
		width: 200,
		height: 200,
		correctLevel: 0,
		render: "table"
	});

	$scope.print = function() {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		var lodop = $.device.printGetLodop();
		var style = "<style>table tr>td{text-align:center}table tr>td:nth-child(1){text-align:left}" +
			"table tr>td:nth-child(1){width:920px;}table tr>td:nth-child(2){display: none;}" +
			"table tr>th:nth-child(2){display: none;}table tr>td:nth-child(3){width:200px;margin-left: 20px;}" +
			"table tr>td:nth-child(4){width:130px;margin-left: 20px;}table tr>td:nth-child(5){width:150px;margin-left: 20px;}</style>";
		var html = style + "<body>" + document.getElementById("lodop").innerHTML + "</body>";
		lodop.ADD_PRINT_TEXT(50, 0, "100%", 100, $scope.itemName + "--材料清单");
		lodop.SET_PRINT_STYLEA(0, "Alignment", 2);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_HTM(150, 0, "100%", "100%", html);
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};

	//继续
	$scope.next = function() {
		// 判断事项是否通过别的渠道跳转
		var oConfig = {
			itemCode: data.itemTenNo,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/getItemAppplyInfo.do', {
			params: oConfig
		}).success(function(dataJson) {
			if(dataJson) {
				if(dataJson.data.molder == 1) { // 为1 时，走官网一网通办的流程
					localStorage.applyUrl = dataJson.data.itemApplyUrl;
					$location.path("/apply");
				} else { // 为0时，走正常流程
					$location.path("/select");
				}
			}
		}).error(function() {
			console.log('getItemAppplyInfo error')
		});
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

	$scope.getGuieInfo();
});
app.controller("applyController", function($scope, $route, $http, $location, data, $timeout, $rootScope, $sce, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	// 设置跳转链接

	//	  //公钥
	//      var PUBLIC_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC68dB76JHZnops1bDHR4a2tBz+I09j2aWOSzOeBVGbJs4HI0XwPCfFVA3q2dLNpQEdDEHNwDg3M/y50uOygp1cyXG12Gy1S+QcqSl0BgGOH39aoJ5cyRkojLSKkzol670sfErX049xQvuq77wFznvHnfQzHgQkEQLqJnkYrFvr2QIDAQAB';
	//      //私钥
	//      var PRIVATE_KEY = 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALrx0HvokdmeimzVsMdHhra0HP4jT2PZpY5LM54FUZsmzgcjRfA8J8VUDerZ0s2lAR0MQc3AODcz/LnS47KCnVzJcbXYbLVL5BypKXQGAY4ff1qgnlzJGSiMtIqTOiXrvSx8StfTj3FC+6rvvAXOe8ed9DMeBCQRAuomeRisW+vZAgMBAAECgYB5lRGRtLU+woSmuefqA1PS+ZstkcttVjz9KV2dtTnY3Uj7jW5MCuOWy87tYdNfGaR6vuEBLrWg+XexZz3deGNcu7t0B4IRHu/54RPPXQszvoN0AcqcDaQ/sUHKE4MDwX5ij5wwA+V2TOUducBGH9+5or5N/IUzdLtKcnWlKoieMQJBANu2wk9xknXaH+dg4x1dVDzYpG1+rix8qA+dok2vF1twS7sdJwhngS7nsMSBjAIETGd2w94ZBz6VBGA9kx1C7K0CQQDZ0ZuhHPV70GhSG2ZyOI4hGI+lEAsCQe7nEKclK2U3oXZR2BNCAj+AZwO1GXqPguC+c5SBwbTTciMGysIwoNVdAkAsX7jWuqVN0APpgxPbdmHw+AAdbRxYN8TpgnipH9ejzAY/gB/F/sGEa56z0UYpkhysOLxOOtfPt+DuXwE7Q6zxAkEAzeCIsOemP7jkYXb0hdFexXlpjCJ1xVR8cnoTAdbafJJoO0N4MFPfoYW8w1epuCuEMX8dRufH+nNPGARdN4lNIQJAVtROB3lmtVYizbeMEEHFSIT3hEUQBTIcFekRgXcX4XYzv+tH35VKWjIH0IOLUvj4FOuTAuwdHs6Ux8HeHM4hHg==';
	//      //使用公钥加密
	//      var encrypt = new JSEncrypt();
	//		  //encrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	//      encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	//      var encrypted = encrypt.encrypt();
	//      console.log('加密后数据:%o', encrypted);
	//      //使用私钥解密
	//      var decrypt = new JSEncrypt();
	//		//decrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	//      decrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	//      var uncrypted = decrypt.decrypt(encrypted);
	//      console.log('解密后数据:%o', uncrypted);
	//
	$scope.applyUrl = "https://zwdtuser.sh.gov.cn/uc/login/login.jsp?self=my&redirect_uri=http://ywtb.sh.gov.cn:18018" + localStorage.applyUrl;
	console.log($scope.applyUrl);
	window.external.URL_OPEN(50, 180, 1800, 760, $scope.applyUrl);

	// 设置url被angular信任 正常跳转
	$scope.prev = function() {
		try {
			window.external.URL_CLOSE();
		} catch(e) {

		}
		$location.path("/guideline");
	}
});
app.controller("selectController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	try {
		window.external.URL_CLOSE();
	} catch(e) {}
	$.device.Camera_Hide();
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

	// 刷身份证获取信息
	$scope.scanIdcard = function() {
		$location.path('/idCard');
	}
	// 市民云亮证
	$scope.citizen = function() {
		$location.path('/citizen');
	}
	$scope.prev = function() {
		$location.path("/guideline");
	}
});
app.controller("idCardController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	if(name.length > 10) {
		name = name.slice(0, 10) + '...'
	}
	$scope.itemName = name;
	$scope.statusName = data.statusName;
	$scope.isRead = true;

	$scope.getIdcard = function(info, images) {

		$scope.faceImage = images;
		$scope.isRead = false; //faceImg
		$scope.$apply();
		data.idCardName = info.Name;
		data.idCardNum = info.Number;
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		$location.path("/info");
	}
	// 	data.idCardName = "zoutianqi";
	// 	data.idCardNum = "430426199804106174";
	// 	$location.path("/info");

});
app.controller("citizenController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

	$scope.ClearBr = function(key) {
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	// 扫描二维码
	$.device.qrCodeOpen(function(code) {
		var __code = $scope.ClearBr(code);
		$.ajax({
			url: "http://hengshui.5uban.com/xhac/aci/window/getInfoByCodeTest.do",
			dataType: 'jsonp',
			jsonp: "jsonpCallback",
			data: {
				codeParam: __code
			},
			success: function(dataJsonp) {
				if(dataJsonp.result.success) {
					data.idCardName = dataJsonp.result.data.realname;
					data.idCardNum = dataJsonp.result.data.idcard;
					data.mobile = dataJsonp.result.data.mobile;
					$timeout(function() {
						$location.path('/info');
					}, 100);
				} else {
					layer.msg(dataJsonp.result.msg);
					$timeout(function() {
						$location.path('/select');
					}, 100);
				}

			},
			error: function(err) {
				console.log("二维码已过期！")
			}
		});
	}, function(err) {
		console.log("二维码扫描出错：" + err)
	});
});
app.controller("infoController", function($scope, $route, $http, $location, data, $rootScope, $timeout, appFactory) {
	$.device.Camera_Hide();
	$scope.fullItemName = data.itemName + "--" + data.statusName;
	var name = data.itemName;
	$scope.itemName = name;

	$scope.list = [{
		obj: '个人'
	}, {
		obj: '法人'
	}];
	$scope.targetTypeName = '个人';
	$scope.targetTips = '申请人身份证号';
	$scope.targetName = '申请人姓名';
	$scope.isAlert = false;
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
		} while (condFlag);
		var from = $('#infoForm').serialize();
		var fConfig = {
			'applyNo': '',
			'itemCode': data.itemTenNo, // data.itemTenNo	'0101220000-00-00-2'
			'itemName': data.itemName,
			'userId': '',
			'source': '网上申请',
			'departCode': data.organCode,
			'departName': data.organName,
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/saveApply.do?' + from, {
			params: fConfig
		}).success(function(dataJson) {
			$scope.flag = true;
			data.applyNo = dataJson.data.applyNo;
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
app.controller("materialUploadController", function($scope, $route, $http, $location, $rootScope, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

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

});
app.controller("uploadMethodController", function($scope, $route, $rootScope, $http, $location, data, $timeout, appFactory) {
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
});
app.controller("materialPicController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.imgUrls = "";

	$scope.profileShow = function() {
		$.ajax({
			url: urlHost + "/aci/autoterminal/dzzz/queryCertBaseData.do",
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
	$scope.url = urlHost;

	$scope.goNext = function() {
		layer.msg("上传中 请稍侯");
		data.selectImg = urlHost + $scope.imgUrls[$scope.current].pictureUrlForBytes;
		$scope.waitUploadImgUrl = data.selectImg;
		$scope.jsonData = {
			'applyNo': data.applyNo, //  '751122018600008'
			'stuffId': '',
			'stuffCode': data.stuffCode,
			'stuffName': data.stuffName,
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
				$.device.httpUpload('http://hengshui.5uban.com/xhac/aci/declare/uploadStuff.do', "file", "C:/waitUploadImg.jpg",
					$scope.jsonData,
					function(result) {
						layer.msg("上传成功");
						data.isUpload.push({
							index: data.currentIndex,
							stuffName: data.stStuffName,
							img: $scope.waitUploadImgUrl,
							method: "个人档案"
						});
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
app.controller("takePhotoController", function($scope, $route, $http, $rootScope, $location, data, $timeout, $routeParams, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;

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
				'applyNo': data.applyNo, //   '751122018600008'
				'stuffId': "",
				'stuffCode': data.stStuffCode,
				'stuffName': data.stStuffName,
				'stuffType': 0,
				'stuffStatus': 0
			};
			$scope.jsonData1 = JSON.stringify($scope.jsonData1);
			$.device.httpUpload("http://hengshui.5uban.com/xhac/aci/declare/uploadStuff.do", "file", $scope.UData,
				$scope.jsonData1,
				function(result) {
					layer.msg("上传成功");
					data.fileName.push($scope.UData);
					data.isUpload.push({
						index: data.currentIndex,
						stuffName: data.stStuffName,
						fileName: $scope.UData,
						method: "U盘上传"
					});
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

	var name = data.itemName;
	$scope.itemName = name;

	$.device.cmCaptureShow(700, 480, 90, 375);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.isFinish = false;
	// 拍照
	var imgHTML = "";
	var imgIndex = 0;
	$scope.next = function() {
		layer.msg("正在上传中，请稍候");
		var scanImg = $.device.cmCaptureCaptureUrl();
		$.ajax({
			url: 'http://hengshui.5uban.com/xhac/aci/declare/uploadStuff.do',
			type: "post",
			dataType: "json",
			data: {
				'applyNo': data.applyNo, //   '751122018600008'
				'stuffId': "",
				'stuffCode': data.stStuffCode,
				'stuffName': data.stStuffName,
				'stuffType': 0,
				'stuffStatus': 0,
				'file': scanImg
			},
			success: function(dataJson) {
				data.uploadStuffId = dataJson.data.stuffId;
				if(data.listImg.length < 1) {
					data.currentIndex++; // 没有材料列表时   文件下标+1 
				}
				data.isUpload.push({
					index: data.currentIndex,
					stuffName: data.stStuffName,
					img: scanImg,
					method: "高拍仪"
				});
				data.fileName.push('扫描文件');
				imgHTML += '<div class="img" id="' + data.uploadStuffId + '"><img src="' + scanImg + '" width="150" height="200" /></div>';
				$('.imgBox').html(imgHTML);
				$scope.isFinish = true;
				$scope.$apply();
			},
			error: function(err) {
				layer.msg("上传材料失败")
			}
		});
	};
	// 完成拍照
	$scope.finishUpload = function() {
		$timeout(function() {
			$.device.cmCaptureHide(); // 关闭高拍仪
			$location.path('/materialList');
		}, 20);
	};

	$scope.last = function() {
		$.device.cmCaptureHide(); // 关闭高拍仪
		$location.path('/materialList');
	}
});
app.controller("materialListController", function($scope, $route, $http, $rootScope, $location, data, $timeout, appFactory) {
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

	//必传材料列表
	$scope.mustUpload = [];
	$scope.current = 0;
	// 获取材料列表  	
	var fConfig = {
		itemCode: data.itemTenNo, //"0101361000",//
		jsonpCallback: "JSON_CALLBACK",
	};
	$http.jsonp(urlHost + '/aci/declare/getItemStuffList.do', {
		params: fConfig
	}).success(function(dataJson) {
		$scope.stuffList = dataJson.data;
		for(var s = 0; i < $scope.stuffList.length; i++) {
			if($scope.stuffList[i].isMust == 1) {
				$scope.mustUpload.push({
					index: i,
					stuffName: $scope.stuffList[i].stuffName
				});
			}
		}

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
					if(data.listImg[i].upload != false) {
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
				data.isUpload[i] = "";
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
		var a = 0;
		if($scope.mustUpload.length == 0) {
			$location.path('/infoFinish');
		} else {
			if($scope.mustUpload.length <= data.isUpload.length) {
				for(var i = 0; i < data.isUpload.length; i++) {
					for(var j = 0; j < $scope.mustUpload.length; j++) {
						if(data.isUpload[i].stuffName == $scope.mustUpload.stuffName) {
							a++;
						}
					}
				}
				if(a >= $scope.mustUpload.length) {
					$location.path('/infoFinish');
				} else {
					layer.msg("请提交必上传材料");
				}
			} else {
				layer.msg("请提交必上传材料");
			}
		}
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
});
app.controller("materialViewController", function($scope, $http, $location, $rootScope, data, appFactory) {
	$scope.stuffList = []; //所有图片的容器
	$scope.showImgList = []; //显示图片的容器
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	var name = data.itemName;
	$scope.itemName = name;
	$scope.statusName = data.statusName;

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
	for(var i = 0; i < data.view.length; i++) {
		if(data.currentIndex == data.view[i].index) {
			$scope.stuffList.push(data.view[i]);
			$scope.currentList();
		}
	}

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
app.controller("infoFinishController", function($scope, $route, $rootScope, $http, $location, data, $timeout, appFactory) {
	$scope.allName = data.itemName + '--' + data.statusName;
	var lodop = $.device.printGetLodop();
	var name = data.itemName;
	$scope.itemName = name;

	// 生成办件编码
	$scope.submitApply = function() {
		$scope.finishData = {
			applyNo: data.applyNo, // '751122018600008'
			subItemCodes: '', // data.itemId
			jsonpCallback: "JSON_CALLBACK",
		};
		$http.jsonp(urlHost + '/aci/declare/submitApply.do', {
			params: $scope.finishData
		}).success(function(dataJson) {
			console.log(dataJson);
			$scope.print();
		}).error(function(e) {
			console.log(e)
		});
	}

	$scope.submitApply();
	$scope.applyNo = data.applyNo;
	$scope.statusText = '打印凭证';
	var code = "http://zwdtmob.sh.gov.cn/zwdtSW/spnetQuery/bjxqxg.jsp?ST_WF_ID=" + data.applyNo + "&IdOrCode=" + encodeURI(data.username);
	var date = new Date();
	var month = date.getMonth() + 1;
	// 打印凭条
	$scope.print = function() {
		lodop.ADD_PRINT_BARCODE(28, 34, 307, 47, "128A", data.applyNo);
		lodop.ADD_PRINT_BARCODE(28, 550, 168, 146, "QRCode", code);
		lodop.ADD_PRINT_TEXT(170, 260, 250, 50, "申报提交确认单");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.ADD_PRINT_TEXT(250, 28, 600, 30, "统一审批编码：" + data.applyNo);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(280, 28, 600, 30, "申请单位/申请人：" + data.username);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(310, 28, 600, 30, "申请事项：" + $scope.allName);
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(370, 28, 450, 30, "请您携带办理材料至虹口区行政服务中心进行办理。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(400, 551, 168, 30, date.getFullYear() + "年" + month + "月" + date.getDate() + "日");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.ADD_PRINT_TEXT(480, 28, 670, 30, "亲民提示：您可凭统一审批编码至“一网通办”总门户或扫描右侧二维码查询办件进度。");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 14);
		lodop.PRINT();
	};
});