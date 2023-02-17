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
app.controller('index', function($state, $scope, appData, $http) {
	appData.upDataZ = appData.upDatafu = "待上传";
	$scope.operation = "请选择：个人-法人";
	$scope.isAlert = false;
	$scope.showlogin = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		console.log(type);
		if(type == "1") {
			$state.go("main");
		} else {
			$state.go("loginUkey");
		}
	}
	$scope.prevStep = function() {
		$state.go("index");
	}
});
app.controller('archivesLoginType', function($state, $scope, appData, $http) {
	appData.upDataZ = appData.upDatafu = "待上传";
	$scope.operation = "请选择登录方式";
	$scope.isAlert = false;
	$scope.showlogin = true;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('loginUkey', function($scope, $http, $state, appData, appFactory, $timeout) {
	appData.upDataZ = appData.upDatafu = "待上传";
	$scope.operation = "公共场所卫生许可-注销";
	$scope.isAlert = false;
	$scope.showlogin = false;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	addAnimate($('.scrollBox2'))
	$scope.choiceLogin = function(type) {
		appData.loginType = type;
		$state.go("login");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller('archivesLogin', function($scope, $http, $state, appData, appFactory, $timeout) {
	$scope.operation = "身份证登录";
	$scope.loginType = appData.loginType;
	$scope.loginBtn = false;
	$scope.isAlert = false;
	$scope.isLoding = true;
	appData.loginTargetType = '个人';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("index");
	}
	switch($scope.loginType) {
		case "idcard":
			$scope.operation = "身份证登录";
			break;
		case "cloud":
			$scope.operation = "随申办";
			break;
		case "ukey":
			$scope.loginBtn = true;
			appData.loginTargetType = '企业';
			$scope.operation = "法人一证通登录";
			break;
	}
	$scope.caLoginStatus = "";
	$scope.caLogin = function() { //登录
		$scope.caLoginStatus = 'login';
	}
	$scope.idcardLogin = function(info, images) {
		if(info) {
			$scope.faceImage = images;
			$scope.loginType = 'recognition';
			appData.archivesNumber = info.Number;
			appData.archivesName = info.Name;
		} else {
			layer.msg("没有获取到")
		}
	}
	$scope.getResult = function(img) {
		$scope.img = img;
		appData.img = img;
		$scope.loginType = '';
		//$state.go("loginUkey");
		$state.go("infoMarry");
	}

	$scope.prevStep = function() {
		$state.go("index");
	}
	$scope.citizenLogin = function(info) {
		var idcardInfo = info.result.data;
		appData.archivesName = idcardInfo.realname;
		appData.archivesNumber = idcardInfo.idcard;
		//$state.go("loginUkey");
		$state.go("infoMarry");
	}
	$scope.caInfo = function(companyName, companyNo) {
		if(companyName && companyNo) {
			appData.archivesNumberCA = companyNo;
			appData.archivesNameCA = companyName;
			$state.go("infoMarry");
			$scope.$apply();
		}
	}
});

app.controller("infoMarry", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	//removeAnimate($('.scrollBox2'))
	//addAnimate($('.scrollBox2'))
	$scope.isAlert = false;
	$scope.concel = "false";
	$scope.isLoding = true;
	$scope.nextText = "提交";
	$scope.Username = appData.archivesName||"";
	$scope.stIdCard = appData.archivesNumber||"";
	$scope.stBirth = "";
	$scope.stAddress = "";
	$scope.stWomen = "";
	$scope.ShowTarget = false;
	$scope.Mobile='';
	$scope.TargetLicenseNo='';
		$scope.stWomen = appData.archivesNumberCA;
		$scope.stBirth = appData.archivesNameCA;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("main");
	}
	$scope.stuffName = "基础信息";
	PublicchoiceById("useType");
	PublicchoiceById("archivesType");
	$scope.prevStep = function() {
		$state.go("main");
	}
	$scope.stData={};
	$scope.stTextContent={};
	$scope.getStData = function() {
		var dataS = {
			'catMainCode': '310105356000100',
			'certCode': '',
			'holderType': '统一社会信用代码',
			'holderCode': $scope.stWomen, //信用代码Key
			'holderName': $scope.stBirth, //企业名称（必填Key）
			'orgName': '静安区卫生健康委员会', //部门名称
			'username': 'AI', //用户名
			'itemName': '公共场所卫生许可注销', //事项名称
			'itemCode': '11310106093585879L331010214600005', //业务办理项编码
			'businessCode': '', //统一编码
		}
		$.ajax({
			type: "post",
			url: $.getConfigMsgJinan.productUrl02 + "/fromext/getLicenseFaceInfo.do",
			dataType: "text",
			//jsonp: "jsonpCallback",
			contentType: 'application/json',
			data: JSON.stringify(dataS),
			success: function(data) {
				$scope.isLoding = true;
				console.log(data);
				if(data.isSuccess == "true") {
					$scope.stTextContent = data.data;
				}else if(data.isSuccess == "false"){
					$scope.stTextContent = data;
				}
			},
			error: function(arg1) {
				console.log(arg1);
			}
		});
	}
	if(appData.loginTargetType=='企业'){
		$scope.ShowTarget = true;
		$scope.getStData();
	}
	$scope.nextStep = function() {
		$scope.alertConfirm = function() {
			$scope.isAlert = false;
		}
		//输入信息
		if($scope.stBirth.length < 1) {
			$scope.isAlert = true;
			$scope.msg = "请输入正确的单位名称！";
			return;
		}
		if($scope.stWomen.length < 1) {
			$scope.isAlert = true;
			$scope.msg = "请输入许可证号格式！";
			return;
		}
		if($scope.stAddress.length < 1) {
			$scope.isAlert = true;
			$scope.msg = "请输入经营场所地址！";
			return;
		}
		//新增
		if($scope.Username.length < 1) {
			$scope.isAlert = true;
			$scope.msg = "办理人姓名格式不正确！";
			return;
		}
		if($scope.stIdCard.length < 1) {
			$scope.isAlert = true;
			$scope.msg = "办理人身份证号格式不正确！";
			return;
		}
		if($scope.Mobile.length != 11) {
			$scope.isAlert = true;
			$scope.msg = "办理人手机号格式不正确！";
			return;
		}
		if(appData.loginTargetType=='企业'){
			$scope.stData = {
				"unitName": $scope.stBirth,
//				"SITEM": "",
//				"SEXPIRE": "",
				"SADDR": $scope.stAddress,
				"SLEGAL": appData.archivesName || '',
				"CERTCODE": $scope.stWomen
			}
			if($scope.TargetLicenseNo.length < 1) {
			   $scope.isAlert = true;
			   $scope.msg = "请输入组织机构代码格式！";
			   return;
		    }
		}
		$scope.stData.unitName=$scope.stBirth;
		$scope.stData.SLEGAL=appData.archivesName || '';
		console.log($scope.stData);
		var obj3 = $.extend({}, $scope.stData, $scope.stTextContent);
		console.log("obj3:"+obj3);
		appData.archivesNumber = $scope.stIdCard;
		appData.archivesName = $scope.Username;
		var dataTapy = {
			"ItemCode":"310102146000",
			"ItemId": "a0ec0000-fbe6-4dd5-a7ad-ed6b15b06c6a", //事项id
			'ItemName': '公共场所卫生许可-注销', //事项名称
			'Username': appData.archivesName || 'null', //申请人姓名 程云霞
			'LicenseType': '1', //申请人证件类型
			'LicenseNo': appData.archivesNumber || "null", //申请人证件号码
			'Mobile': $scope.Mobile||"null", //申请人手机号码
			'TargetType': appData.loginTargetType||'个人', //办理对象类型
			'TargetName': $scope.stBirth, //办理对象或企业名称
			'TargetLicenseType': '营业执照', //办理对象证件类型
			'TargetLicenseNo': $scope.TargetLicenseNo||"null", //办理对象证件号码
			'TargetSource': '自助终端', //办件来源
			'OpDepartName': '静安', //操作部门
			'OpDepartCode': '01', //操作部门Code
			'OpUsername': '静安自助终端', //操作人员
			'Result': '通过',
			"TextContent": JSON.stringify(obj3),
		}
		//请求
		$scope.isLoding = false;
		$.ajax({
			type: "post",
			url: $.getConfigMsgJinan.productUrl02 + "/support/toSave.do",
			dataType: "text",
			//jsonp: "jsonpCallback",
			contentType: 'application/x-www-form-urlencoded',
			data: dataTapy,
			success: function(data) {
				$scope.isLoding = true;
				data = JSON.parse(data);
				console.log(data);
				if(data.Message == "success") {
					appData.stMobile = $scope.stMobile;
					appData.stBirth = $scope.stBirth
					appData.ApplyId = data.ApplyId;
					$state.go("MaterialList");
				} else {
					$scope.isAlert = true;
					$scope.msg = "接口返回失败，请重试";
				}
			},
			error: function(arg1) {
				console.log(arg1);
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "提交接口失败，请重试";
				return;
			}
		});
		//提交
	};
});
app.controller("MaterialList", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = "公共场所卫生许可-注销";
	$scope.nextText = "下一步";
	$scope.nextBtn = '0';
	$scope.upDataZ = appData.upDataZ || '待上传';
	$scope.upDatafu = appData.upDatafu || '待上传';
	if($scope.upDatafu == "已上传" && appData.upDataZ == "已上传") {
		$scope.nextBtn = '1';
	}
	//appData.sun = 0;
	$scope.prevStep = function() {
		$state.go("main");
	};
	$scope.nextStep = function() {
		//下一步     上传办件环节信息
		$scope.isLoding = false;
		var data = {
			'ApplyId': appData.ApplyId, //办件ID
			'NodeName': '已收件', //当前环节名称
			'OpDepartName': '静安', //操作部门
			'OpDepartCode': '01', //操作部门Code
			'OpUsername': '静安自助终端', //操作人员
			'Result': '状态'
		};
		$.ajax({
			type: "post",
			url: $.getConfigMsgJinan.productUrl02 + "/support/changeNode.do",
			dataType: "text",
			//jsonp: "jsonpCallback",
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(data) {
				$scope.isLoding = true;
				data = JSON.parse(data);
				console.log(data);
				if(data.Code == 200) {
					$state.go("underway");
				} else {
					$scope.isAlert = true;
					$scope.msg = "接口返回失败，请重试";
				}
			},
			error: function(arg1) {
				console.log(arg1);
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "提交接口失败，请重试";
				return;
			}
		});
		//$state.go("submit");
	};
	$scope.UpScanFile = function(id) {
		appData.StuffName = id;
		console.log(appData.StuffName);
		//$state.go("fileUpload");
		$state.go("finish");
	};

});
app.controller("fileUpload", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = "公共场所卫生许可-注销-----材料上传";
	$scope.prevStep = function() {
		$state.go("MaterialList");
	};
	$scope.scanPhoto = function() {
		$state.go("finish");
	}
});
//base64转二进制
function dataURLToBlob(dataurl) {
	var arr = dataurl.split(',');
	var mime = arr[0].match(/:(.*?);/)[1];
	var bstr = atob(arr[1]);
	var n = bstr.length;
	var u8arr = new Uint8Array(n);
	while(n--) {
		u8arr[n] = bstr.charCodeAt(n);
	}
	return new Blob([u8arr], {
		type: mime
	});
}
app.controller("finish", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.operation = "公共场所卫生许可-注销";
	$scope.nextText = "确认上传";
	$scope.isLoading = true;
	$scope.isLoding = true;
	$scope.prevStep = function() {
		$.device.cmCaptureHide();
		$state.go("MaterialList");
	};
	var imgHTML = '';
	$.device.cmCaptureShow(680, 480, 210, 340);
	$.device.cmCaptureSelectRect(0, 0, 1920, 1920);
	$scope.next = function() {
		$scope.isLoding = false;
		$.device.cmCaptureCaptureBase64(function(Base64) {
			$scope.scanImgBase = "data:image/png;base64," + Base64;
			$scope.FileContentData = dataURLToBlob($scope.scanImgBase);
			//console.log($scope.FileContentData);
			imgHTML = '<img src="' + $scope.scanImgBase + '" width="170" height="200" />';
			$('.imgBox').html(imgHTML);
			$scope.isLoding = true;
			if(appData.StuffName == '注销申请书') {
				appData.ZImgBase = $scope.scanImgBase;
			} else {
				appData.FImgBase = $scope.scanImgBase;
			}
		});
	}
	$scope.nextStep = function() {
		$scope.alertConfirm = function() {
			$scope.isAlert = false;
		}
		if($scope.FileContentData==undefined) {
			$scope.isAlert = true;
			$scope.msg = "请先点击拍照按钮";
			return;
		}
		$scope.isLoding = false;
		$.device.cmCaptureHide();
		var formdata = new FormData(); //创建一个表单
		formdata.append("FileContent", $scope.FileContentData, 'time.jpg');
		formdata.append("ApplyId", appData.ApplyId); //办件ID
		formdata.append("StuffName", appData.StuffName); //材料名称
		formdata.append("StuffDesc", '');
		formdata.append("StuffSource", '静安区自助工作台');
		formdata.append("StuffStatus", 0); //材料状态   0为首次提交、2为补充材料
		formdata.append("TextContent", '文本内容');
		formdata.append("Remove", 0); //是否删除  0：否；1：是
		$.ajax({
			type: "post",
			url: $.getConfigMsgJinan.productUrl02 + "/support/toUpload_file.do",
			dataType: "json",
			data: formdata,
			contentType: "multipart/form-data",
			contentType: false,
			processData: false,
			success: function(data) {
				$scope.isLoding = true;
				console.log(data);
				if(data.Code == 200) {
					if(appData.StuffName == '注销申请书') {
						appData.StuffIdZ = data.StuffId;
						appData.upDataZ = '已上传';
					}
					if(appData.StuffName == '《上海市公共场所卫生许可证》原件') {
						appData.StuffIdF = data.StuffId;
						appData.upDatafu = '已上传';
					}
					$state.go("MaterialList");
				} else {
					$scope.isAlert = true;
					$scope.msg = "接口返回异常";
				}
			},
			error: function(arg1) {
				console.log(arg1);
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "提交接口失败，请重试";
				return;
			}
		});
	};
});
app.controller("submit", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	$scope.stuffName = "公共场所卫生许可-注销";
	$scope.nextText = "返回首页";
	$scope.IPnumber = appData.stMobile;
	$scope.Name = appData.archivesName;
	$scope.stBirth = appData.stBirth;
	//$scope.statetim = appData.dataListSubmit.NodeName || '等待';
	$scope.DataNIYURI = new Date();
	$scope.yue = $scope.DataNIYURI.getMonth() + 1;
	$scope.TimeNI = $scope.DataNIYURI.getFullYear() + '年' + $scope.yue + '月' + $scope.DataNIYURI.getDate() + '日';
	console.log($scope.TimeNI);
	$scope.messageAShow = true;
	$scope.showNext='0';
	//获取二维码信息
	$scope.Getqrcode = function() {
		var data = {
			"applyId": appData.SaveApplyId,
			"itemNo": "00003",
			"userName": "正三"
		};
		$.ajax({
			type: "get",
			url: "http://xzfwzx.jingan.gov.cn:8080/ac/aci/materialUp/toSubmit.do?",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: data,
			success: function(data) {
				console.log(data);
				if(data.msg == "请求成功!") {
					appData.CodeApplyNo = data.applyNo;
					$timeout(function() {
						$scope.print2('第1联：审批部门存');
						$scope.print2('第2联：申请人自存');
					}, 1000);
					console.log(data.applyNo);
				}
			},
			error: function(arg1) {
				console.log(arg1);
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "提交接口失败，请重试";
				return;
			}
		});
	};
	//保存事项
	$scope.SaveDoThing = function() {
		var data = {
			"loginName": "coral",
			"stWindowNo": "工作台",
			"stUnit": $scope.stBirth,
			"stUserName": $scope.Name,
			"stMobile": $scope.IPnumber,
			"societyNo": "233333333333333",
			"stStatusId": "3b944dd5-ab19-4884-8c02-c0505265435b",
			"stStatusName": "核发出版物经营许可证(零售)",
			"stExternalNo": "",
			"stProject": "项目信息",
			"stRecipient": "收件人",
			"getCertWay": "物流",
			"stRecipientPhone": "1511111111",
			"stRecipientAddress": "测试大道",
			"stItemId": "82ce4a05-4cb7-4d8b-b6fa-9e4fa8669e88",
			"stIdentityNo": "44444444444444444444",
			"province": "上海市",
			"city": "上海市",
			"area": "嘉定区"
		};
		$.ajax({
			type: "get",
			url: "http://xzfwzx.jingan.gov.cn:8080/ac/aci/materialUp/saveNew.do?",
			dataType: "jsonp",
			jsonp: "jsonpCallback",
			data: data,
			success: function(data) {
				console.log(data);
				if(data.success == true) {
					appData.SaveApplyId = data.applyId;
					console.log(data.applyId);
					$scope.Getqrcode();
				}
			},
			error: function(arg1) {
				console.log(arg1);
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "提交接口失败，请重试";
				return;
			}
		});
	};
	try {
		if(appData.dataListSubmit.NodeName == "预审通过") {
			$scope.showNext='1';
			$scope.statetim = "审批通过";
			$scope.messageA = "审批通过，申请人可在自助终端上打印公共场所卫生许可-注销，至证照柜扫描告知单二维码提交材料";
		} else {
			$scope.messageAShow = false;
			$scope.statetim = "审批不通过";
			//			var a1 = Object.entries(appData.dataListSubmit.MinorDesc);
			//			var a2 = a1[0];
			//			$scope.messageA = a2[0] + ":" + a2[1];
		}
	} catch(e) {}
	//打印
	$scope.print2 = function(name) {
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		//alert($scope.msg);
		var lodop = $.device.printGetLodop();
		lodop.ADD_PRINT_TEXT(40, 324, 250, 50, name);
		var strBodystyle = "<style>" + document.getElementById("style1").innerHTML + "</style>";
		var strBodyhtml = strBodystyle + "<style> dd{width:620px;} dt{font-weight:bold}</style><body>" + document.getElementById("print2").innerHTML + "</body>";
		lodop.ADD_PRINT_HTM(70, 45, "100%", "100%", strBodyhtml);
		lodop.ADD_PRINT_BARCODE(80, 50, 206, 78, "128B", appData.CodeApplyNo); //设置条码位置、宽高、字体、值
		lodop.ADD_PRINT_BARCODE(80, 600, 100, 100, "QRCode", appData.CodeApplyNo); //设置条码位置、宽高、字体、值
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
			$state.go("main");
		}, 5000);
	};
	//打印
	$scope.nextStep = function() {
		$scope.SaveDoThing();
	}

});
app.controller('underway', function($state, $scope, appData, $http) {
	$scope.stuffName = '公共场所卫生许可-注销';
	$scope.isAlert = false;
	$scope.Zhengimg = appData.ZImgBase;
	$scope.Fanimg = appData.FImgBase;
	$scope.cont = 0;
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go("main");
	}
	//
	$scope.ListDataWonders = [{
			'name': '单位名称',
			'value': '上海华阳文化用品总汇大宁路分店'
		},
		{
			'name': "法定代表人(负责人)",
			'value': '上海市桂平路481号20号楼5层'
		},
		{
			'name': '经营场所地址',
			'value': '张某某'
		},
		{
			'name': '经营项目',
			'value': ''
		},
		{
			'name': '有效期限',
			'value': ''
		},
//		{
//			'name': '证号',
//			'value': ''
//		}
	];
	$scope.p = 0;
	$scope.RightText = true;
	$scope.ListDataWonderT = [];
	$scope.xunhuan = function() {
		var interval = setInterval(function() {
			if($scope.p == $scope.ListDataWonders.length) {
				clearInterval(interval);
				//停止动画
				$(".donghua").removeClass("donghua");
				$scope.RightText = false;
				setTimeout(function() {
					$state.go("submit");
				}, 5000)
				return;
			}
			$scope.ListDataWonderT.push($scope.ListDataWonders[$scope.p]);
			//console.info($scope.ListDataWonderT);
			$scope.p = $scope.p + 1;
			$scope.$apply();
		}, 1500);
	};
	//$scope.xunhuan();
	$scope.GetNodeDescJson = function() {
		//批注信息查询
		var data = {
			"NodeId": appData.NodeId
		};
		$.ajax({
			type: "post",
			url: $.getConfigMsgJinan.productUrl02 + "/support/getNodeDescJson.do",
			dataType: "text",
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(data) {
				data = JSON.parse(data);
				console.log(data);
				if(data.Code == 200 && data.List.length > 0) {
					//clearInterval(GetNodeDescJsonSetI);
					data.List.forEach(function(val, index) {
						if(val.MainDesc.code == 200) {
							console.log(val.MainDesc.result);
							$scope.ListDataWonders[2].value = val.MainDesc.result.businessPlace.content;
							$scope.ListDataWonders[0].value = val.MainDesc.result.unitName.content;
							$scope.ListDataWonders[1].value = val.MainDesc.result.authorizedRepresentative.content;
							$scope.ListDataWonders[3].value = val.MainDesc.result.businessItem.content;
							$scope.ListDataWonders[4].value = val.MainDesc.result.expirationDate.content;
							//$scope.ListDataWonders[5].value = val.MainDesc.result.certificateNumber.content;
							$scope.$apply();
							$scope.xunhuan();
						}
					})
				} else {
					$scope.isAlert = true;
					$scope.msg = "查询返回错误";
				}
			},
			error: function(arg1) {
				console.log(arg1);
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "提交接口失败，请重试";
				return;
			}
		});
	}
	//请求核验结果
	$scope.ViewState = function() {
		var dataTapy = {
			"ApplyId": appData.ApplyId
			//"ApplyId": '24900808-bda2-4279-850f-e8715e98e6d2'
		};
		$.ajax({
			type: "post",
			url: $.getConfigMsgJinan.productUrl02 + "/support/getApplyNodeJson.do",
			dataType: "text",
			data: JSON.stringify(dataTapy),
			contentType: "application/json",
			success: function(data) {
				$scope.cont += 1;
				if($scope.cont > 6) {
					clearInterval($scope.test2);
					$scope.isAlert = true;
					$scope.msg = "识别超时，请重试";
				}
				data = JSON.parse(data);
				console.log(data);
				if(data.Code == 200) {
					for(var i = 0; i < data.List.length; i++) {
						if(data.List[i].NodeName == "卫生许可证识别") {
							appData.NodeId = data.List[i].NodeId;
						}
						if(data.List[i].NodeName == "预审通过" || data.List[i].NodeName == "预审不通过") {
							clearInterval($scope.test2);
							//开始打印	
							appData.dataListSubmit = data.List[i];
							setTimeout(function() {
								//$state.go("submit");
							}, 5000)
							$scope.GetNodeDescJson();
						}
					}
				}
			},
			error: function(arg1) {
				clearInterval($scope.test2);
				console.log(arg1);
				$scope.isLoding = true;
				$scope.isAlert = true;
				$scope.msg = "提交接口失败，请重试";
				return;
			}
		});
	};
	$scope.test2 = setInterval(function() {
		$scope.ViewState();
	}, 2000);
	console.info($scope.ListDataWonders);
	$scope.nextStep = function() {
		$state.go("submit");
	}
	$scope.prevStep = function() {
		$state.go("main");
	}
});
app.controller("preview", function($scope, $state, appData, appFactory, $http, $timeout, $rootScope) {
	//测试打印
	$scope.print2 = function() {
		//alert("正在打印");
		$scope.isAlert = true;
		$scope.msg = "正在打印...";
		var lodop = $.device.printGetLodop();
		var strBodystyle = "<style>" + document.getElementById("style1").innerHTML + "</style>";
		var strBodyhtml = strBodystyle + "<style> dd{width:620px;} dt{font-weight:bold}</style><body>" + document.getElementById("print2").innerHTML + "</body>";
		lodop.ADD_PRINT_HTM(70, 45, "100%", "100%", strBodyhtml);
		//lodop.PRINT();
		lodop.ADD_PRINT_BARCODE(120, 50, 206, 78, "128B", "110110110"); //设置条码位置、宽高、字体、值
		lodop.SET_PRINT_STYLEA(0, "FontSize", 18); //设置上面这个条码下方的文字字体大小
		//lodop.SET_PRINT_STYLEA(0, "Color", "#FF0000"); //设置当前条码以及条码下方字体的颜色
		//lodop.SET_PRINT_STYLEA(0, "Angle", 180); //设置旋转角度
		lodop.SET_PRINT_STYLEA(0, "ShowBarText", 0, "10000010000"); //设置是否显示下方的文字
		lodop.SET_PRINT_STYLEA(0, "AlignJustify", 2); //设置条码下方的文字相对于条码本身居中
		lodop.SET_PRINT_STYLEA(0, "AlignJustify", 1); //设置条码下方的文字相对于条码本身居左
		lodop.SET_PRINT_STYLEA(0, "AlignJustify", 3); //设置条码下方的文字相对于条码本身居右
		//lodop.SET_PRINT_STYLEA(0, "GroundColor", "#0080FF"); //设置条码的背景色　
		lodop.ADD_PRINT_BARCODE(120, 600, 100, 100, "QRCode", "110110110"); //设置条码位置、宽高、字体、值
		//lodop.PREVIEW();
		//lodop.PRINT_DESIGN
		lodop.PRINT();
		$timeout(function() {
			$scope.isAlert = false;
		}, 3000);
	};
	//$scope.print2();
});