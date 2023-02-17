function removeAnimate(ele) {
	//	$(ele).css({
	//		"transform": "translateY(0px)",
	//		"top": 0
	//	}).removeClass('transformto')
}

function addAnimate(ele) {
	$(ele).css({
		'margin-top': '300px',
		'opacity': '0'
	});
	$(ele).animate({
		marginTop: '0',
		opacity: '1'
	}, 1000);
}
var VERSION = "";
app.controller("main", function($scope, $state, appData, $sce) {
	$scope.content = "English";
	VERSION = $scope.version = "Chinese";
	$scope.changeVersion = function() {
		if ($scope.content == "English") {
			$scope.content = "中文版"
			VERSION = $scope.version = "English";
		} else if ($scope.content == "中文版") {
			$scope.content = "English"
			VERSION = $scope.version = "Chinese";
		}
		console.log(VERSION);
	}
	$scope.choiceType = function(type) {
		appData.type = type;
		appData.version = $scope.version;
		if (type == "iCard") {
			$state.go(appData.type)
		} else {
			$state.go('loginType');
		}
	}

});
app.controller("loginType", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.choiceType = function(type) {
		appData.loginType = type;
		$state.go('login');
	}
	$scope.prevStep = function() {
		$state.go('main')
	}
});
app.
controller("login", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.isAlert = false;
	$scope.concel = 'false';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
		$state.go('loginType');
	}
	$scope.imgSrc = "img/card.png";
	$scope.prevStep = function() {
		$state.go('loginType')
	}

	$scope.nextStep = function() {
		$.device.dcrfClose();
		if (appData.type == 'iStd') {
			if (appData.loginType == "card") {
				//根据卡号查询学生信息
				$scope.method = 'studentBycard/vo';
				$scope.params = {
					card: $scope.cardNo || 'FM20221001'
				}
			} else if (appData.loginType == "code") {
				//根据学生userId查询学生信息
				$scope.method = 'studentPrintVoByUserId/vo';
				$scope.params = {
					userId: $scope.uesrId || 'cwangyu.d21'
				}
			}
		} else if (appData.type == 'iTeacher') {
			if (appData.loginType == "card") {
				//根据卡号查询学生信息
				$scope.method = 'employeeBycard/vo';
				$scope.params = {
					card: $scope.cardNo, // || 'C379'
				}
			} else if (appData.loginType == "code") {
				//根据教师userId查询学生信息
				$scope.method = 'employeePrintVoByuserId/vo';
				$scope.params = {
					userId: $scope.uesrId || 'lbill'
				}
			}
		}
		requestGet($scope.method, $scope.params, function(res) {
			console.log(res);
			if (res.code == 'SUCCESS') {
				appData.resultData = res.data;
				$state.go(appData.type)
			} else {
				$scope.isAlert = true;
				$scope.msg = res.message;
				$scope.$apply();
			}
		}, function(err) {
			console.log(err);
		})
	}
	// $scope.nextStep()
	//读校园卡
	$scope.drcf = function() {
		$.device.dcrfOpen(function(val) {
			$scope.cardNo = convert(val);
			$.log.debug('drcf----' + $scope.cardNo);
			$scope.nextStep();
		})
	}
	//扫码
	$scope.qrCode = function() { 
		$.device.qrCodeOpen(function(code) {
			code = code.replace(/[\r\n]/g, "");
			code = code.trim();
			requestGet('QRcode/verification', {
				token: encodeURI(code),
			}, function(res) {
				console.log(res);
				if (res.code == 'SUCCESS') {
					$scope.uesrId = res.data;
					$scope.nextStep();
				} else {
					$scope.isAlert = true;
					$scope.msg = res.message;
					$scope.$apply();
				}
			}, function(err) {
				console.log(err);
				$scope.isAlert = true;
				if ($scope.version == "Chinese") {
					$scope.msg = '扫码接口异常，请重试';
				} else if ($scope.version == "English") {
					$scope.msg = 'QR Code scanning error, please try again.';
				}
				$scope.$apply();
			})
		})
	}

	switch (appData.loginType) {
		case 'card':
			if ($scope.version == "English") {
				$scope.imgSrc = "img/card-English.png"
			} else if ($scope.version == "Chinese") {
				$scope.imgSrc = "img/card.png"
			}
			$scope.drcf();
			break;
		case 'code':
			if ($scope.version == "English") {
				$scope.imgSrc = "img/code-English.png"
			} else if ($scope.version == "Chinese") {
				$scope.imgSrc = "img/code.png"
			}
			$scope.qrCode();
			break;
	}

});
app.controller("iStd", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.status = appData.resultData.status;
	$scope.isAlert = false;
	$scope.concel = 'false';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.choiceType = function(type, itemName) {
		if ($scope.status == '0') {
			if (itemName == '成绩单' || itemName == "在读证明" || itemName == "学习进度表") {
				appData.itemName = itemName;
				$state.go(type);
			} else {
				$scope.isAlert = true;
				if ($scope.version == "Chinese") {
					$scope.msg = "您的学籍状态暂不能打印此证明"
				} else if ($scope.version == "English") {
					$scope.msg =
						"Your student status temporarily not support this certificate";
				}
			}
		} else if ($scope.status == '1') {
			if (itemName == "在读证明") {
				$scope.isAlert = true;
				if ($scope.version == "Chinese") {
					$scope.msg = "您的学籍状态暂不能打印此证明"
				} else if ($scope.version == "English") {
					$scope.msg =
						"Your student status temporarily not support this certificate";
				}
			} else {
				appData.itemName = itemName;
				$state.go(type);
			}
		} else {
			$scope.isAlert = true;
			if ($scope.version == "Chinese") {
				$scope.msg = "您的学籍状态暂不支持在自助终端进行办理，请联系教务长办公室"
			} else if ($scope.version == "English") {
				$scope.msg =
					"Your student status temporarily not support self-printing, please contact Dean's Office.";
			}

		}
	}
	$scope.prevStep = function() {
		$state.go('main')
	}
});
app.controller("iTeacher", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.choiceType = function(type, itemName, printState) {
		appData.itemName = itemName;
		appData.printState = printState;
		if (itemName == "收入证明") {
			$state.go('choice');
		} else {
			$state.go(type);
		}

	}
	$scope.prevStep = function() {
		$state.go('main')
	}
});
app.controller("choice", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.choiceType = function(type, itemName, printState) {
		appData.itemName = itemName;
		appData.printState = printState;
		$state.go(type);
	}
	$scope.prevStep = function() {
		$state.go('iTeacher')
	}
});
app.controller("iCard", function($scope, $state, appData, $sce) {
	$scope.nextStep = function() {
		html2canvas(document.querySelector("#capture")).then(function(canvas) {
			var oImg = new Image();
			oImg.src = canvas.toDataURL(); // 导出图片
			console.log(oImg.src)
			downloadFileByBase64(oImg.src, '测试');
		});
	}
	$scope.prevStep = function() {
		$state.go('main')
	}
});
//学生证明类信息表单
app.controller("stdCertificateInfo", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.isAlert = false;
	$scope.concel = 'false';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.resultData = appData.resultData;
	if ($scope.version == "Chinese") {
		$scope.sexList = sexList;
		$scope.typeCertificateList = typeCertificateList;
		$scope.printStateList = printStateList;
		$scope.stateList = stateList;
	} else if ($scope.version == "English") {
		$scope.sexList = sexList_English;
		$scope.typeCertificateList = typeCertificateList_English;
		$scope.printStateList = printStateList_English;
		$scope.stateList = stateList_English;
	}
	//课程和状态
	$scope.sourseStatusList = [];
	$scope.sourseStatus = $scope.resultData.sourseStatus;
	for (var i = 0; i < $scope.sourseStatus.length; i++) {
		$scope.sourseStatusList.push({
			userType: $scope.sourseStatus[i].split(',')[0],
			status: $scope.sourseStatus[i].split(',')[1]
		})
	}
	console.log($scope.sourseStatusList);
	$scope.itemName = appData.itemName;
	//证明对应接口
	switch (appData.itemName) {
		case '成绩单':
			$scope.method = 'academicTranscript';
			break;
		case '学籍证明':
			$scope.method = 'student/certificate';
			break;
		case '学习经历证明':
			$scope.method = 'learn/experience/proof';
			break;
		case '在读证明':
			$scope.method = 'certificateEnrollment';
			break;
		case '学位证书丢失证明':
			$scope.method = 'certificateLoss/degree/certificate';
			break;
		case '学习进度表':
			$scope.method = 'LearningProgress';
			break;
	}

	//基本信息
	$scope.cname = $scope.resultData.cname;
	$scope.sno = $scope.resultData.sno;
	$scope.birthday = $scope.resultData.birthday;
	$scope.email = $scope.resultData.email;
	$scope.mobile = $scope.resultData.mobile;
	$scope.idPassport = $scope.resultData.idPassport;
	$scope.state = $scope.resultData.state;
	$scope.gender = $scope.resultData.gender;
	$scope.typeCertificate = $scope.resultData.typeCertificate;

	//课程默认选择第一个 所对应属性
	$scope.current2 = 0;
	$scope.current3 = 0;
	$scope.printState = printStateList[0].id;
	$scope.userType = $scope.sourseStatusList[0].userType;
	$scope.statusID = $scope.sourseStatusList[0].status;
	$scope.status = getNameByID($scope.statusID, $scope.stateList, 'id');
	appData.status = $scope.status;
	appData.userType = $scope.userType;
	console.log(appData.userType + "" + appData.status)
	//选择器不可更改
	$scope.current = getCurrentIndex($scope.gender, sexList, 'id');
	$scope.current1 = getCurrentIndex($scope.typeCertificate, typeCertificateList, 'id');

	$scope.choice = function(index, item, type) {
		if (type == "userType") {
			$scope.current2 = index;
			$scope.userType = item.userType;
			$scope.statusID = item.status;
			$scope.status = getNameByID(item.status, $scope.stateList, 'id');
			appData.status = $scope.status;
			appData.userType = $scope.userType;
		} else if (type == "printState") {
			$scope.current3 = index;
			$scope.printState = item.id;
		}
	}

	$scope.prevStep = function() {
		$state.go('iStd')
	}
	//提交信息
	$scope.flag = true;
	$scope.nextStep = function() {
		if (!$scope.flag) {
			return ''
		}
		var condFlag = false;
		do {
			if (isBlank($scope.statusID)) {
				$scope.isAlert = true;
				if ($scope.version == "Chinese") {
					$scope.msg = '您的课程状态暂无法办理，请去教务处咨询！';
				} else if ($scope.version == "English") {
					$scope.msg =
						"Your current programme status cannot be handled temporarily, Please go to Dean's Office for help";
				}
				$scope.$apply();
				return;
			}
		} while (condFlag);
		var parsms = {
			cname: $scope.cname,
			ename: $scope.resultData.ename,
			sno: $scope.sno,
			birthday: $scope.birthday,
			gender: $scope.gender,
			email: $scope.email,
			mobile: $scope.mobile,
			typeCertificate: $scope.typeCertificate,
			idPassport: $scope.idPassport,
			userType: $scope.userType,
			state: $scope.statusID.toString(),
		}

		requestPost($scope.method, JSON.stringify(parsms), function(res) {
			console.log(res);
			if (res.code == 'SUCCESS') {
				appData.printResult = res.data;
				appData.printResult.printState = $scope.printState;
				$state.go('previewResult');
			} else {
				$scope.isAlert = true;
				$scope.msg = res.message;
				$scope.$apply();
			}
		}, function(err) {
			console.log(err)
		})
	}
});
//旅游须知承诺
app.controller("tchTravelExplain", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.isAlert = false;
	$scope.concel = 'false';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$('.swearYes').click(function() {
		if ($(this).hasClass("checked")) {
			$(this).removeClass("checked");
		} else {
			$(this).addClass("checked");
		}
	})
	$scope.prevStep = function() {
		$state.go('iTeacher');
	}
	$scope.nextStep = function() {
		var value;
		if ($scope.version == "Chinese") {
			value = $('.chinese .swearYes').attr('class')
		} else if ($scope.version == "English") {
			value = $('.english .swearYes').attr('class')
		}
		console.log(value);
		if (value.indexOf('checked') == -1) {
			$scope.isAlert = true;
			if ($scope.version == "Chinese") {
				$scope.msg = '请选择已读并承诺';
			} else if ($scope.version == "English") {
				$scope.msg =
					'Please select read and commit.';
			}
		} else {
			$state.go('tchBasicInfo');
		}
	}
});

//教师基本信息表单
app.controller("tchBasicInfo", function($scope, $state, appData, $sce, $interval) {
	$scope.version = appData.version;
	$scope.isAlert = false;
	$scope.concel = 'false';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.code = true;
	console.log(appData.itemName);
	//证明对应接口
	switch (appData.itemName) {
		case '在职证明（带薪）':
			$scope.method = 'incumbency/certification/salary';
			break;
		case '在职证明（普通）':
			$scope.method = 'incumbency/certification/noSalary';
			break;
		case '在职证明（旅游签证用）':
			$scope.travleSign = true;
			$scope.method = 'incumbency/certification/travel/eno';
			break;
		case '收入证明':
			$scope.method = 'incumbency/certification/income/salary';
			break;
	}
	//日期选择器
	$('#travelStartTime').datetimepicker({
		timepicker: false,
		format: 'Y-m-d'
	});
	$('#travelEndTime').datetimepicker({
		timepicker: false,
		format: 'Y-m-d'
	});
	$('#travelStartTime_en').datetimepicker({
		timepicker: false,
		format: 'Y-m-d'
	});
	$('#travelEndTime_en').datetimepicker({
		timepicker: false,
		format: 'Y-m-d'
	});
	$.datetimepicker.setLocale('zh');
	$scope.resultData = appData.resultData;
	//基本信息
	$scope.cname = $scope.resultData.cname;
	$scope.eno = $scope.resultData.eno;
	$scope.idcardno = hideIdCard($scope.resultData.idcardno);
	$scope.passportno = $scope.resultData.passportno;
	$scope.mobile = hideMobile($scope.resultData.mobile);

	$scope.prevStep = function() {
		$state.go('iTeacher')
	}

	//获取验证码
	$scope.getCode = function() {
		$scope.code = false;
		$scope.time = 60;
		$scope.timer = null;
		$scope.timeCount = function() {
			$interval.cancel($scope.timer);
			$scope.timer = $interval(function() {
				$scope.time--;
				if ($scope.time < 1) {
					$interval.cancel($scope.timer);
					$scope.code = true;
				}
			}, 1000);
			$scope.params = {
				mobileNumber: $scope.resultData.mobile,
			};
			requestGet('sms/sendsms', $scope.params, function(res) {
				console.log(res);
			}, function(err) {
				console.log(err)
			})
		}
		$scope.timeCount();
	}

	//提交信息
	$scope.flag = true;
	$scope.nextStep = function() {
		console.log($('#travelStartTime').val());
		if (!$scope.flag) {
			return ''
		}
		$scope.verificationCode = function() {
			//验证短信
			$scope.params = {
				mobileNumber: $scope.resultData.mobile || "",
				code: $scope.messageCode || "",
			};
			requestGet('sms/sendsms/verification', $scope.params, function(res) {
				if (res.code == "100001") {
					$scope.isAlert = true;
					if ($scope.version == "Chinese") {
						$scope.msg = '输入的手机号或者验证码为空';
					} else if ($scope.version == "English") {
						$scope.msg = 'Please input your phone number or verification code';
					}
				} else if (res.code == "100002") {
					$scope.isAlert = true;
					if ($scope.version == "Chinese") {
						$scope.msg = '验证码输入有误';
					} else if ($scope.version == "English") {
						$scope.msg = 'Your verification code is wrong.';
					}
				} else if (res.code == "100003") {
					$scope.isAlert = true;
					if ($scope.version == "Chinese") {
						$scope.msg = '手机号错误或者验证码已经过期，请重新发送';
					} else if ($scope.version == "English") {
						$scope.msg =
							'The phone number is wrong or the verification code has expired, please resend.';
					}
				} else if (res.code == "SUCCESS") {
					requestPost($scope.method, JSON.stringify(parsms), function(res) {
						if (res.code == 'SUCCESS') {
							appData.printResult = res.data;
							appData.travelInfo = {
								travelStartTime: $('#travelStartTime').val() || $(
									'#travelStartTime_en').val(),
								travelEndTime: $('#travelEndTime').val() || $(
									'#travelEndTime_en').val(),
								travelDestination_EN: $scope.travelPlaceEN,
								travelDestination_CN: $scope.travelPlaceCN,
								passportno: $scope.passportno
							}
							$state.go('previewResult');
						} else {
							$scope.isAlert = true;
							$scope.msg = res.message;
							$scope.$apply();
						}
					}, function(err) {
						console.log(err)
					})
				}
			}, function(err) {
				console.log(err)
			})
		}
		if (isBlank($scope.messageCode)) {
			$scope.isAlert = true;
			if ($scope.version == "Chinese") {
				$scope.msg = '请输入验证码';
			} else if ($scope.version == "English") {
				$scope.msg = 'Please input verfication code.';
			}
			return;
		}
		if (appData.itemName == "在职证明（旅游签证用）") {
			var condFlag = false;
			do {
				if (isBlank($scope.passportno)) {
					$scope.isAlert = true;
					if ($scope.version == "Chinese") {
						$scope.msg = '请输入护照号';
					} else if ($scope.version == "English") {
						$scope.msg = 'Please input passport number.';
					}
					return;
				}
				if (isBlank($('#travelStartTime').val()) && $scope.version == "Chinese") {
					$scope.isAlert = true;
					$scope.msg = '请选择休假起始日期';
					return;
				}
				if (isBlank($('#travelEndTime').val()) && $scope.version == "Chinese") {
					$scope.isAlert = true;
					$scope.msg = '请选择休假结束日期';
					return;
				}
				if (isBlank($('#travelStartTime_en').val()) && $scope.version == "English") {
					$scope.isAlert = true;
					$scope.msg = 'Please input Leave start date.';
					return;
				}
				if (isBlank($('#travelEndTime_en').val()) && $scope.version == "English") {
					$scope.isAlert = true;
					$scope.msg = 'Please input Leave end date.';
					return;
				}
				if (isBlank($scope.travelPlaceCN)) {
					$scope.isAlert = true;
					if ($scope.version == "Chinese") {
						$scope.msg = '旅游目的地（中文）！';
					} else if ($scope.version == "English") {
						$scope.msg = 'Please input Tourism Destination (Chinese).';
					}
					return;
				}
				if (isBlank($scope.travelPlaceEN)) {
					$scope.isAlert = true;
					if ($scope.version == "Chinese") {
						$scope.msg = '旅游目的地（英文）！';
					} else if ($scope.version == "English") {
						$scope.msg = 'Please input Tourism Destination (English).';
					}
					return;
				}
			} while (condFlag);
			var parsms = {
				cname: $scope.cname,
				ename: $scope.resultData.ename,
				eno: $scope.eno,
				idcardno: $scope.resultData.idcardno,
				passportno: $scope.passportno,
				travelStartTime: $('#travelStartTime').val(),
				travelEndTime: $('#travelEndTime').val(),
				travelDestination_EN: $scope.travelPlaceEN,
				travelDestination_CN: $scope.travelPlaceCN
			}
		} else {
			var parsms = {
				cname: $scope.cname,
				ename: $scope.resultData.ename,
				eno: $scope.eno,
				idcardno: $scope.resultData.idcardno,
			}
		}
		$scope.verificationCode();
	}
});
//结果预览
app.controller("previewResult", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.isAlert = false;
	$scope.concel = 'false';
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	$scope.isLoading = true;
	$scope.currentPage = 1; //当前页
	$scope.totalPages = 1; //总页数
	$scope.previewImgList = []; //预览图片
	$scope.emptyPreviewImgList = []; //存在空值的数组
	$scope.totalList = [];
	$scope.base = "data:image/png;base64,"
	$scope.currentList = function(current) {
		if (current === undefined) {
			current = $scope.currentPage;
		}
		$("#jq22 img").remove();
		$scope.currentImgIndex = null;
		$scope.startPos = (current - 1) * 1;
		$scope.endPos = $scope.startPos + 1;
		$scope.emptyPreviewImgList = $scope.totalList.slice($scope.startPos, $scope.endPos);
		$scope.totalPages = Math.ceil($scope.totalList.length / 1);
		$scope.emptyPreviewImgList.length = 1;
		for (var i in $scope.emptyPreviewImgList) {
			if ($scope.emptyPreviewImgList[i] != undefined) {
				$scope.previewImgList.push($scope.emptyPreviewImgList[i]);
			}
			$("#jq22").append('<img data-original="' + $scope.base + $scope.emptyPreviewImgList[i] +
				'" src="' + $scope.base + $scope.emptyPreviewImgList[i] + '" alt="">');
		}
		$scope.$apply();
		//图片显示
		var viewer = new Viewer(document.getElementById('jq22'), {
			url: 'data-original',
		});
	}

	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.totalPages) {
			++$scope.currentPage;
			$scope.currentList();
		} else {
			if ($scope.version == "Chinses") {
				layer.msg('已经是最后一页！', {
					time: 3000,
				});
			} else if ($scope.version == "English") {
				layer.msg("It's the last page!", {
					time: 3000,
				});
			}

		}
	};
	$scope.prevPage = function() {
		if ($scope.currentPage > 1) {
			--$scope.currentPage;
			$scope.currentList();
		} else {
			if ($scope.version == "Chinses") {
				layer.msg('已经是第一页！', {
					time: 3000,
				});
			} else if ($scope.version == "English") {
				layer.msg("It's the first page！", {
					time: 3000,
				});
			}
		}
	};
	if (appData.itemName == "收入证明" || appData.itemName == "在职证明（带薪）" || appData.itemName == "在职证明（普通）") {
		$scope.method = 'incumbency/certification/download';
		appData.printResult.printState = appData.printState;
	} else if (appData.itemName == "在职证明（旅游签证用）") {
		$scope.method = 'incumbency/certification/download';
		appData.printResult.printState = 'travelSalary'
		appData.printResult.travelStartTime = appData.travelInfo.travelStartTime;
		appData.printResult.travelEndTime = appData.travelInfo.travelEndTime;
		appData.printResult.travelDestination_EN = appData.travelInfo.travelDestination_EN;
		appData.printResult.travelDestination_CN = appData.travelInfo.travelDestination_CN;
		appData.printResult.passportno = appData.travelInfo.passportno;
	} else if (appData.itemName == "成绩单") {
		$scope.method = 'academicTranscript/download';
	} else if (appData.itemName == "学籍证明") {
		$scope.method = 'student/certificate/download';
	} else if (appData.itemName == "学习经历证明") {
		$scope.method = 'learn/experience/proof/download';
	} else if (appData.itemName == "学位证书丢失证明") {
		$scope.method = 'certificateLoss/degree/certificate/download';
	} else if (appData.itemName == "学位证书复印件盖章") {
		$scope.method = 'degreeCertificateCopySeal/download';
	} else if (appData.itemName == "在读证明") {
		$scope.method = 'certificateEnrollment/download';
	} else if (appData.itemName == "学习进度表") {
		$scope.method = 'LearningProgress/download';
	}

	$scope.preview = function() {
		requestPost($scope.method, JSON.stringify(appData.printResult), function(res) {
			console.log(res);
			if (res.code == 'SUCCESS') {
				$scope.isLoading = false;
				$scope.totalList = res.data;
				$scope.currentList();
			} else {
				$scope.isLoading = false;
				$scope.isAlert = true;
				$scope.msg = res.message;
				$scope.$apply();
			}
		}, function(err) {
			console.log(err);
		})
	}
	$scope.preview();
	$scope.prevStep = function() {
		window.history.go(-1);
	}
	$scope.nextStep = function() {
		//		$state.go('schoolReportPrint')
		$state.go('provePrinting')

	}
});
//打印份数选择
app.controller("schoolReportPrint", function($scope, $state, appData, $sce) {
	$scope.itemName = appData.itemName;
	$scope.status = appData.status;
	$scope.userType = appData.userType;
	$scope.printCount = 1;
	$scope.printCountList = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

	$scope.sub = function() {
		if ($scope.printCount > 1) {
			$scope.printCount--;
		}
	}
	$scope.add = function() {
		if ($scope.printCount < 10) {
			$scope.printCount++;
		}
	}
	$scope.change = function(num) {
		$scope.printCount = num;
	}
	$scope.prevStep = function() {
		$state.go('previewResult')
	}
	$scope.nextStep = function() {
		//		$scope.sumPrice = $scope.printCount * 50;
		//		var params = {
		//			name: appData.printResult.cname,
		//			item: appData.itemName,
		//			type: appData.itemName,
		//			amount: 0.01, //$scope.sumPrice,
		//			currency: '',
		//			pageCallback: 'http://' + window.location.host + '/selfWorkBench-CEIBS/index.html#/provePrinting'
		//		};
		//		requestPost('payment', JSON.stringify(params), function(res) {
		//			console.log(res);
		//			if(res.code == 'SUCCESS') {
		//				window.location.href = res.data;
		//			} else {
		//				$scope.isLoading = false;
		//				layer.msg(res.message, {
		//					time: 5000,
		//				});
		//			}
		//		}, function(err) {
		//			console.log(err);
		//		})
		$state.go('provePrinting')
	}
});
//打印中
app.controller("provePrinting", function($scope, $state, appData, $sce) {
	$scope.version = appData.version;
	$scope.isAlert = true;
	$scope.concel = 'false';
	if ($scope.version == "Chinese") {
		$scope.msg = "请等待约10秒后，至自助终端正下方打印出口取出证明";
	} else if ($scope.version == "English") {
		$scope.msg =
			"Please wait about 10 seconds and take out the certificate at the printing exit directly below the self-service terminal";
	}
	$scope.alertConfirm = function() {
		$scope.isAlert = false;
	}
	
	//埋点记录
	$scope.params = {
		stMachineMac:$.config.get("uniqueId") || "12-12-12-12-12",
		stMachineAddress:$.config.get("address") || "中欧国际学院",
		stModuleName:appData.itemName,
		stPrintName:appData.status||appData.printState,
		stUserName:appData.resultData.cname,
		stUserType:appData.type == 'iStd'?'1':'2',
		stUserNo:appData.resultData.sno||appData.resultData.eno,
		stContent:'',
		isTest:'0',
		stAttachId:"",
		stExt1:"",
		stExt2:"",
		stExt3:""
	}
	requestPost('savePrintInfo', JSON.stringify($scope.params), function(res) {
		console.log(res);
		if (res.code == 'SUCCESS') {
			$scope.isLoading = false;
			$scope.totalList = res.data;
			$scope.currentList();
		} else {
			$scope.isLoading = false;
			$scope.isAlert = true;
			$scope.msg = res.message;
			$scope.$apply();
		}
	}, function(err) {
		console.log(err);
	})
	
	if (appData.itemName == "收入证明" || appData.itemName == "在职证明（带薪）" || appData.itemName == "在职证明（普通）" ||
		appData.itemName == "在职证明（旅游签证用）") {
		$scope.pdfPrint = baseUrl + 'incumbency/print';
	} else if (appData.itemName == "成绩单") {
		$scope.pdfPrint = baseUrl + 'academicTranscript/print';
	} else if (appData.itemName == "学籍证明") {
		$scope.pdfPrint = baseUrl + 'student/certificate/print';
	} else if (appData.itemName == "学习经历证明") {
		$scope.pdfPrint = baseUrl + 'learn/experience/proof/print';
	} else if (appData.itemName == "学位证书丢失证明") {
		$scope.pdfPrint = baseUrl + 'certificateLoss/degree/certificate/print';
	} else if (appData.itemName == "学位证书复印件盖章") {
		$scope.pdfPrint = baseUrl + 'degreeCertificateCopySeal/print';
	} else if (appData.itemName == "在读证明") {
		$scope.pdfPrint = baseUrl + 'certificateEnrollment/print';
	}else if (appData.itemName == "学习进度表") {
		$scope.pdfPrint = baseUrl + 'LearningProgress/print';
	}
	$.device.foxitPdf_Print($scope.pdfPrint + '?email=' + appData.resultData.email);
});
