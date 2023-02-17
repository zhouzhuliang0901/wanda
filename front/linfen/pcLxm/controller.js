//等比缩放
var s;

function resize() {
	if(document.body.clientWidth < 1280) return;
	s = document.body.clientWidth / 1920;
	if(document.body.clientWidth > 800) {
		console.log(s);
		document.body.style.transformOrigin = '0 0';
		document.body.style.transform = 'scale(' + s + ',' + s + ')';
		document.body.style.width = window.innerWidth / s + 'px';
		document.body.style.height = window.innerHeight / s + 'px';
	}
}
window.onload = function() {
	resize();
}
app.controller("main", function($scope, $state, appData, $sce) {
	$scope.goTo = function() {
		$state.go('password');
	}
});
app.controller("password", function($scope, $state, appData, $sce) {
	$scope.textName = '';
	$scope.textNumber = '';
	$scope.alertlayerA = function(msg) {
		layer.msg('<span style="font-size: 28px;line-height: 60px;">' + msg + '</span>', {
			offset: ['calc(50% - 150px)', 'calc(50% - 400px)'],
			area: ['800px', '300px'],
		});
	}
	$scope.loginClick = function() {
		if(!checkIdCard($scope.textNumber)) {
			$scope.alertlayerA('身份证格式不正确');
			return;
		}
		if($scope.textName == '') {
			$scope.alertlayerA('姓名不能为空');
			return;
		}
		appData.textNumber = $scope.textNumber;
		appData.textName = $scope.textName;
		var data = {
			"idcard": $scope.textNumber,
			"name": encodeURI($scope.textName)
		};
		$.ajax({
			url: baseUrl + "/jw/login.do",
			type: "post",
			dataType: 'jsonp',
			jsonp: 'jsonpCallback',
			timeout: 8000,
			data: data,
			success: function(dataJsonp) {
				try {
					if(dataJsonp.success == true) {
						if(dataJsonp.message == '成功') {
							$state.go('loginType');
						} else {
							$scope.alertlayerA('参数返回错误03');
						}
					} else if(dataJsonp.success == false) {
						$scope.alertlayerA(dataJsonp.message);
					} else {
						$scope.alertlayerA('参数返回错误02');
					}
				} catch(e) {
					$scope.alertlayerA('参数返回错误01');
				}
			},
			error: function(err) {
				$scope.alertlayerA('登录地址报错！！！');
			},
			complete: function(XMLHttpRequest, status) {
				console.log(status);
				if(status == 'timeout' || status == 'error') {　　
					alert("超时");　　　　
				}　　
			}
		})
	}

	$scope.goHomeMain = function() {
		$state.go('main');
	}
});
app.controller("loginType", function($scope, $state, appData, $sce) {
	$scope.choiceLogin = function() {
		$state.go('login');
	}
});
app.controller("login", function($scope, $state, appData, $sce) {
	$scope.readIdCard = function() {
		$.device.idCardOpen(function(list) {
			var data = JSON.parse(list);
			$.log.debug(list);
			$scope.idcardInfo = data;
			appData.licenseNumber = $scope.idcardInfo.Number;
			appData.licenseName = $scope.idcardInfo.Name;
			$state.go('preview');
		})
	};
	$scope.readIdCard();
	$scope.nextStep = function() {
		$state.go('preview');
	}
});
app.controller("preview", function($scope, $state, appData, $sce) {
	//	appData.licenseNumber = "430426199804106174";
	//	appData.licenseName = "邹天奇";
	$scope.isAlert = false;
	$scope.concel = 'false';

	//记录
	$scope.saveApplyInfo = function(type) {
		requestGet('jw/applyInfo/saveApplyInfo.do', {
			ST_APPLY_ITEM_NAME: encodeURI('离线随申码'),
			ST_APPLY_ITEM_CODE: '',
			ST_APPLY_ITEM_ID: '',
			ST_APPLY_NO: '',
			ST_APPLY_TYPE: encodeURI(type),
			ST_APPLY_NAME: encodeURI(appData.licenseName),
			ST_APPLY_IDENTITY: appData.licenseNumber,
			ST_APPLY_PHONE: '',
			ST_WORK_NAME: encodeURI(appData.textName),
			ST_WORK_IDENTITY: appData.textNumber,
			ST_WORK_ID: '',
			ST_APPLY_ADDRESS: '',
		}, function(res) {
			console.log(res)
		}, function(err) {
			console.log(err);
		})
	}

	$scope.alertToos = function(msg, ishome) {
		$scope.isAlert = true;
		$scope.msg = msg;
		$scope.alertConfirm = function() {
			$scope.isAlert = false;
			if(ishome != false) {
				$state.go('loginType');
			}
		}
	}
	$scope.alertlayer = function(msg, idhome) {
		var msgdata = '<span style="font-size: 28px;line-height: 60px;">' + msg + '</span>';
		if(idhome) {
			layer.msg(msgdata, {
				offset: ['calc(50% - 150px)', 'calc(50% - 400px)'],
				area: ['800px', '480px'],
				time: 0, //20s后自动关闭
				btn: ['确定']
			}, function() {
				$state.go('loginType');
			});
		} else {
			layer.msg(msgdata, {
				offset: ['calc(50% - 150px)', 'calc(50% - 400px)'],
				area: ['800px', '400px'],
			});

		}

	}
	$scope.downloadEvt = function(url, fileName) {
		var el = document.createElement('a');
		el.style.display = 'none';
		el.setAttribute('target', '_blank');
		fileName && el.setAttribute('download', fileName);
		el.href = url;
		console.log(el);
		document.body.appendChild(el);
		el.click();
		document.body.removeChild(el);
	}
	$scope.PrintWEb = function(urlS) {
		$scope.pdfPrint = urlS;
		$.log.debug($scope.pdfPrint);
		if(acBridgeMac.vendor() == 'wonders') {
			try {
				$scope.pathurl = window.external.GetCurrentPath() + '\\pdfFile\\' + appData.licenseNumber + appData.licenseName + '.pdf';
			} catch(e) {
				$scope.pathurl = 'D:\\pdfFile\\' + appData.licenseNumber + appData.licenseName + '.pdf';
			}
			$.device.httpDownload(
				$scope.pdfPrint,
				$scope.pathurl,
				function(bytesCopied, totalBytes) {
					//alert("01");
				},
				function(result) {
					$scope.alertlayer("pdf已下载成功,文件存储在" + $scope.pathurl);
				},
				function(webexception) {
					//alert("03");
				}
			);
		}
		if(acBridgeMac.vendor() == 'epoint') {
			$scope.pathurl = 'D:\\pdfFile\\' + appData.licenseNumber + appData.licenseName + '.pdf';
			$.device.httpDownload(
				$scope.pdfPrint,
				$scope.pathurl,
				function(bytesCopied, totalBytes) {
					//alert("01");
				},
				function(result) {
					$scope.alertlayer("pdf已下载成功,文件存储在" + $scope.pathurl);
				},
				function(webexception) {
					//alert("03");
				}
			);
		} else {
			//$scope.pathurl = 'window.external.GetCurrentPath() + \\img\\' + appData.licenseNumber + appData.licenseName + '.pdf';
			//$scope.alertlayer("pdf已下载成功,文件存储在" + $scope.pathurl, true);
			$scope.downloadEvt($scope.pdfPrint, 'm');
		}
		$scope.saveApplyInfo('打印');
	}

	//查询离线码状态
	$scope.queryCodeStatus = function() {
		requestPost('selfapi/offlineCode/queryOfflineCodeStatus.do', {
				idCard: appData.licenseNumber,
				name: encodeURI(appData.licenseName),
				type: '1',
			}, function(res) {
				if(res.code == '0') {
					$scope.info = res.data;
					$scope.isLxm = true;
					$scope.imgSrc = 'img/lxm2.png'
					$scope.$apply();
				} else if((res.code == "8" || res.code == "9") && res.code != "" && res.code != undefined && res.code != null) {
					$scope.alertlayer('用户身份认证失败',true);
				} else if(res.code == "10" && res.code != "" && res.code != undefined && res.code != null) {
					$scope.alertlayer('用户身份认证超时',true);
				} else if(res.code == "11" && res.code != "" && res.code != undefined && res.code != null) {
					$scope.alertlayer('您不符合申领条件',true);
				} else if(res.code == "6" && res.code != "" && res.code != undefined && res.code != null) {
					$scope.alertlayer('当前离线码已失效',true);
				} else {
					$scope.alertlayer('无效离线码，请联系工作人员',true);
				}
			},
			function(err) {
				$scope.alertToos(err);
			})
	}
	$scope.queryCodeStatus();

	//	//申领离线码
	//	$scope.applyOfflineCode = function() {
	//		requestGet('api/offlineCode/applyOfflineCode.do', {
	//			name: encodeURI(appData.licenseName),
	//			idCard: appData.licenseNumber,
	//			type: '1', //仅做申领
	//		}, function(res) {
	//			if(res.code == '0') {
	//				$scope.alertlayer('申领成功')
	//		$scope.PrintWEb(baseUrl + "selfapi/offlineCode/priviewOfflineCode.do?name=" + encodeURI(encodeURI(appData.licenseName)) + "&idCard=" + appData.licenseNumber);
	//			} else {
	//				$scope.alertlayer(res.message);
	//			}
	//		}, function(err) {
	//			$scope.alertToos('申领失败，请联系工作人员');
	//		})
	//	}

	//挂失离线码
	$scope.reportlossOfflineCodeHttp = function() {
		requestGet('selfapi/offlineCode/reportlossOfflineCode.do', {
			name: encodeURI(appData.licenseName),
			idCard: appData.licenseNumber,
		}, function(res) {
			if(res.code == '0') {
				$scope.alertlayer('挂失成功', true);
				$scope.saveApplyInfo('挂失');
				//				$scope.queryCodeStatus();
			} else {
				$scope.alertlayer(res.message);
			}
		}, function(err) {
			$scope.alertToos('挂失失败，请联系工作人员');
		})
	}
	$scope.reportlossOfflineCode = function() {
		var msgdata = '<span style="font-size: 28px;">挂失后已申领的“离线随申码”将失效，是否确定挂失?</span>'
		layer.msg(msgdata, {
			offset: ['calc(50% - 150px)', 'calc(50% - 400px)'],
			area: ['800px', '480px'],
			time: 0, //20s后自动关闭
			btn: ['确定', '取消'],
			yes: function() {
				$scope.reportlossOfflineCodeHttp();
			},
			btn2: function() {}
		});
	}
	//下载离线码
	$scope.downloadOfflineCode = function() {
		$scope.PrintWEb(baseUrl + "selfapi/offlineCode/priviewOfflineCode.do?name=" + encodeURI(encodeURI(appData.licenseName)) + "&idCard=" + appData.licenseNumber);
		//		requestGet('offlineCode/downloadOfflineCode.do', {
		//			name: encodeURI(appData.licenseName),
		//			idCard: appData.licenseNumber,
		//		}, function(res) {
		//			console.log(res);
		//		}, function(err) {
		//			console.log(err);
		//		})
	}

	//	$scope.nextStep = function(){
	//		$state.go('preview');
	//	}
});