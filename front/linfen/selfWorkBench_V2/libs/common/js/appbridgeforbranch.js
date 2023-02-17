// JavaScript source code

var FakeDevice = function(drivertype) {
	this.drivertype = drivertype;
};

FakeDevice.prototype.call = function(p_jsonstr, p_done, p_progress, p_fail) {
	var p_json = JSON.parse(p_jsonstr);
	setTimeout(function() {
		if (p_json.progress) {
			if (p_json.progressArray) {
				p_json.progressArray.forEach(function(item) {
					setTimeout(function() {
						p_progress(JSON.stringify(item));
					}, item.timespan);
				});

			} else {
				p_progress(p_jsonstr);
			}
		}

		setTimeout(function() {
			if (p_json.done) p_done(p_jsonstr);
			else if (p_json.fail) p_fail(p_jsonstr);
		}, p_json.done || p_json.fail || 50);

	}, p_json.progress || 50);
};

FakeDevice.prototype.synDevAPI = FakeDevice.prototype.call;
FakeDevice.prototype.asynDevAPI = FakeDevice.prototype.call;
FakeDevice.prototype.faceDetector = FakeDevice.prototype.call;
FakeDevice.prototype.getbase64strfrompic = FakeDevice.prototype.call;
FakeDevice.prototype.reset = FakeDevice.prototype.call;
FakeDevice.prototype.readcard = FakeDevice.prototype.call;
FakeDevice.prototype.ejectcard = FakeDevice.prototype.call;
FakeDevice.prototype.cancelaccept = FakeDevice.prototype.call;
FakeDevice.prototype.capturecard = FakeDevice.prototype.call;
FakeDevice.prototype.getdevinfo = FakeDevice.prototype.call;
FakeDevice.prototype.getmediastatus = FakeDevice.prototype.call;
FakeDevice.prototype.getcandidatelist = FakeDevice.prototype.call;
FakeDevice.prototype.selectapplication = FakeDevice.prototype.call;
FakeDevice.prototype.initiateapplicationinfo = FakeDevice.prototype.call;
FakeDevice.prototype.selectaccounttype = FakeDevice.prototype.call;
FakeDevice.prototype.selecttransaction = FakeDevice.prototype.call;
FakeDevice.prototype.inputamount = FakeDevice.prototype.call;
FakeDevice.prototype.getecupperlimit = FakeDevice.prototype.call;
FakeDevice.prototype.getelebalance = FakeDevice.prototype.call;
FakeDevice.prototype.getmutitagfieldvalue = FakeDevice.prototype.call;
FakeDevice.prototype.gettransdetail = FakeDevice.prototype.call;
FakeDevice.prototype.getqlog = FakeDevice.prototype.call;
FakeDevice.prototype.excutecoredispose = FakeDevice.prototype.call;
FakeDevice.prototype.getscriptresults = FakeDevice.prototype.call;
FakeDevice.prototype.writearpcandscript = FakeDevice.prototype.call;
FakeDevice.prototype.getunitinfo = FakeDevice.prototype.call;
FakeDevice.prototype.setunitinfo = FakeDevice.prototype.call;
FakeDevice.prototype.chipio = FakeDevice.prototype.call;
FakeDevice.prototype.geticfullinfo = FakeDevice.prototype.call;
FakeDevice.prototype.getIDType = FakeDevice.prototype.call;
FakeDevice.prototype.readIDCard = FakeDevice.prototype.call;
FakeDevice.prototype.ejectIDCard = FakeDevice.prototype.call;
FakeDevice.prototype.getOrgScannerModule = FakeDevice.prototype.call;
FakeDevice.prototype.hdprinttemplate = FakeDevice.prototype.call;
FakeDevice.prototype.getprintjobinfo = FakeDevice.prototype.call;
FakeDevice.prototype.getpaperboxstatus = FakeDevice.prototype.call;
FakeDevice.prototype.savefile2local = FakeDevice.prototype.call;
FakeDevice.prototype.takePhoto = FakeDevice.prototype.call;
FakeDevice.prototype.sign = FakeDevice.prototype.call;
FakeDevice.prototype.showuwpform = FakeDevice.prototype.call;
FakeDevice.prototype.printform = FakeDevice.prototype.call;
FakeDevice.prototype.readBarcode = FakeDevice.prototype.call;
FakeDevice.prototype.navigateToCustPage = function(json) {}
FakeDevice.prototype.closeCustPage = function() {};
FakeDevice.prototype.file2local = FakeDevice.prototype.call;
FakeDevice.prototype.pdf2img = FakeDevice.prototype.call;
FakeDevice.prototype.pdfToimgByContent = FakeDevice.prototype.call;
FakeDevice.prototype.controlGuideLightSync = FakeDevice.prototype.call;
FakeDevice.prototype.closeAllSync = FakeDevice.prototype.call;
FakeDevice.prototype.deviceEventsRemove = function(p_jsonstr, p_done, p_progress, p_fail) {};
FakeDevice.prototype.deviceEventsRemoveAll = function(p_jsonstr, p_done, p_progress, p_fail) {};
FakeDevice.prototype.takeHSpeedphoto = FakeDevice.prototype.call;

function getdevice(drivertype) {
	if (window.AppHost)
		return AppHost.getdevice(drivertype);
	else
		return new FakeDevice(drivertype);
}

function getEngagedWebAPI(apiname) {
	if (window.AppHost) {
		return AppHost.getEngagedApi(apiname);
	} else {
		return new FakeDevice();
	}
}

function getManage() {
	if (window.AppHost) {
		return AppHost.getManage();
	} else {
		return new FakeDevice();
	}
}

var Device = function() {
	var self;
	/**
	 * 电动读卡器
	 */
	var card = function() {
		this.device = getdevice("card");
	};
	card.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.readcard = function(json) {
		return $.Deferred(function(deferred) {
			this.device.readcard(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.ejectcard = function(json) {
		return $.Deferred(function(deferred) {
			this.device.ejectcard(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.cancelaccept = function(json) {
		return $.Deferred(function(deferred) {
			this.device.cancelaccept(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.capturecard = function(json) {
		return $.Deferred(function(deferred) {
			this.device.capturecard(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getmediastatus = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getmediastatus(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getcandidatelist = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getcandidatelist(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.selectapplication = function(json) {
		return $.Deferred(function(deferred) {
			this.device.selectapplication(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.initiateapplicationinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.initiateapplicationinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.selectaccounttype = function(json) {
		return $.Deferred(function(deferred) {
			this.device.selectaccounttype(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.selecttransaction = function(json) {
		return $.Deferred(function(deferred) {
			this.device.selecttransaction(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.inputamount = function(json) {
		return $.Deferred(function(deferred) {
			this.device.inputamount(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getecupperlimit = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getecupperlimit(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getelebalance = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getelebalance(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getmutitagfieldvalue = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getmutitagfieldvalue(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.gettransdetail = function(json) {
		return $.Deferred(function(deferred) {
			this.device.gettransdetail(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getqlog = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getqlog(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.excutecoredispose = function(json) {
		return $.Deferred(function(deferred) {
			this.device.excutecoredispose(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getscriptresults = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getscriptresults(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.writearpcandscript = function(json) {
		return $.Deferred(function(deferred) {
			this.device.writearpcandscript(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.getunitinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getunitinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.setunitinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.setunitinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.chipio = function(json) {
		return $.Deferred(function(deferred) {
			this.device.chipio(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	card.prototype.geticfullinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.geticfullinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	/**
	 * 身份证
	 */
	var idcard = function() {
		this.device = getdevice("idcard");
	};
	idcard.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.getIDType = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getIDType(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.readIDCard = function(json) {
		return $.Deferred(function(deferred) {
			this.device.readIDCard(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.ejectIDCard = function(json) {
		return $.Deferred(function(deferred) {
			this.device.ejectIDCard(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.cancelaccept = function(json) {
		return $.Deferred(function(deferred) {
			this.device.cancelaccept(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	idcard.prototype.getOrgScannerModule = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getOrgScannerModule(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/** 
	 * 激光打印机
	 */
	var hdprint = function() {
		this.device = getdevice("hdprint");
	};
	hdprint.prototype.hdprinttemplate = function(json) {
		return $.Deferred(function(deferred) {
			this.device.hdprinttemplate(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.getprintjobinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getprintjobinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	hdprint.prototype.getpaperboxstatus = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getpaperboxstatus(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/** 
	 * 影像平台
	 */
	var icms = function() {
		this.api = getEngagedWebAPI("icms");
	};
	icms.prototype.savefile2local = function(json) {
		return $.Deferred(function(deferred) {
			this.api.savefile2local(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	icms.prototype.getbase64strfrompic = function(json) {
		return $.Deferred(function(deferred) {
			this.api.getbase64strfrompic(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 活检
	 */
	var faceIRDetect = function() {
		this.device = getdevice("faceirdetect");
	}

	faceIRDetect.prototype.faceDetector = function(json) {
		var mgr = new Manage;
		var machineInfo = JSON.parse(mgr.getAppData('machineInfo') || "{}");
		var cameraInex = {
			faceCameraIndex: machineInfo.UserCamera || 2,
			infraredCameraIndex: machineInfo.InfraredCamera || 0,
			irfaceQualityScore: machineInfo.irfaceQualityScore || 0.55,
			irfaceMinQualityScore: machineInfo.irfaceMinQualityScore || 0.45
		};
		$.extend(cameraInex, json);
		return $.Deferred(function(deferred) {
			this.device.faceDetector(JSON.stringify(cameraInex), function(info) {
				var retJson = JSON.parse(info);
				if (retJson.Result) {
					// 调用成功，获取人脸Base64字符串
					var api = new icms;
					var faceInfo = {
						PicName: retJson.FileName,
						IsFullPath: true
					};
					$.extend(faceInfo, json);
					api.getbase64strfrompic(faceInfo).always(function(data) {
						if (data && data.code == 1) {
							setTimeout(() => {
								deferred.resolve({
									success: true,
									msg: "操作成功",
									obj: {
										faceImage: data.FileContent
									}
								})
							}, 50);
						} else {
							setTimeout(() => {
								deferred.reject({
									success: false,
									msg: data.msg,
									obj: {}
								});
							}, 50);
						}
					});
				} else {
					// 失败
					setTimeout(() => {
						deferred.reject({
							success: false,
							msg: retJson.Msg,
							obj: {}
						});
					}, 50);
				}
			});
		}.bind(this)).promise();
	};
	/**
	 * 高拍仪
	 * */
	var highCamera = function() {
		this.device = getdevice("highcamera");
	}
	highCamera.prototype.takePhoto = function(json) {
		var input = JSON.parse(json);
		var mgr = new Manage;
		var machineInfo = JSON.parse(mgr.getAppData('machineInfo') || "{}");
		var cameraInex = {
			CameraIndex: machineInfo.ScannerCamera || 0,
			txtMsg: input.text || "请将待扫描文件放到指定区域",
			culture: input.culture || "zh-CN"
		};
		return $.Deferred(function(deferred) {
			this.device.takephoto(JSON.stringify(cameraInex), function(info) {
				var retJson = JSON.parse(info);
				if (retJson.Result) {
					// 调用成功
					setTimeout(() => {
						deferred.resolve({
							success: true,
							msg: "操作成功",
							obj: {
								scannerImage: retJson.FileContent
							}
						});
					}, 50);

				} else {
					// 失败
					setTimeout(() => {
						deferred.reject({
							success: false,
							msg: retJson.Msg,
							obj: {}
						});
					}, 50);
				}
			});
		}.bind(this)).promise();
	};
	highCamera.prototype.takeHSpeedphoto = function(json) {

		return $.Deferred(function(deferred) {
			this.device.takeHSpeedphoto(JSON.stringify(json), function(info) {
				var retJson = JSON.parse(info);
				if (retJson.result == true) {
					// 调用成功
					setTimeout(() => {
						deferred.resolve(retJson);
					}, 50);

				} else {
					// 失败
					setTimeout(() => {
						deferred.reject(retJson);
					}, 50);
				}
			});
		}.bind(this)).promise();
	};
	/**
	 * 签字版
	 */
	var signtablet = function() {
		this.device = getdevice("signtablet");
	};
	signtablet.prototype.sign = function(json) {
		var deferred = $.Deferred(function(deferred) {
			this.device.call(JSON.stringify(json), function(info) { //done
				var msg_json = JSON.parse(info);
				if (msg_json.result == "ok") {
					setTimeout(function() {
						deferred.resolve(JSON.parse(info))
					}, 50);
				} else if (msg_json.result == "cancel") {
					setTimeout(function() {
						deferred.reject(JSON.parse(info))
					}, 50);
				}
			}, function(state) { //progress
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) { //fail
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this));

		return deferred.promise();
	};

	/** 
	 * 打开uwp窗口
	 */
	var uwpbridge = function() {
		this.device = getdevice("uwpbridge");
	};
	uwpbridge.prototype.showuwpform = function(json) {
		return $.Deferred(function(deferred) {
			this.device.showuwpform(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 凭条打印
	 */
	var print = function() {
		this.device = getdevice("print");
	};
	print.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	print.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	print.prototype.printform = function(json) {
		return $.Deferred(function(deferred) {
			this.device.printform(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	print.prototype.getmediastatus = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getmediastatus(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 条码扫描
	 */
	var barcode = function() {
		this.device = getdevice("barcode");
	};
	barcode.prototype.readBarcode = function(json) {
		return $.Deferred(function(deferred) {
			this.device.readBarcode(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	barcode.prototype.cancelRead = function(json) {
		return $.Deferred(function(deferred) {
			this.device.cancelRead(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	barcode.prototype.reset = function(json) {
		return $.Deferred(function(deferred) {
			this.device.reset(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	barcode.prototype.getdevinfo = function(json) {
		return $.Deferred(function(deferred) {
			this.device.getdevinfo(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	// 第三方网站
	var customwebview = function() {
		this.device = getdevice("customwebview");
	};
	customwebview.prototype.navigateToCustPage = function(json) {
		this.device.navigateToCustPage(JSON.stringify(json));
	};
	customwebview.prototype.closeCustPage = function() {
		this.device.closeCustPage();
	};

	/**
	 * 通用
	 */
	var Manage = function() {
		this.manage = getManage();
	};
	Manage.prototype.getAppData = function(key) {
		if (window.AppHost) {
			return this.manage.getAppData(key);
		}
		return "";
	};
	Manage.prototype.file2local = function(json) {
		return $.Deferred(function(deferred) {
			this.manage.file2local(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	Manage.prototype.getMachineInfo = function() {
		if (window.AppHost) {
			return JSON.parse(this.manage.getMachineInfo());
		}
		return "";
	};
	Manage.prototype.pdf2img = function(json) {
		return $.Deferred(function(deferred) {
			this.manage.pdf2img(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	Manage.prototype.pdfToimgByContent = function(json) {
		return $.Deferred(function(deferred) {
			this.manage.pdfToimgByContent(JSON.stringify(json), function(info) {
				setTimeout(function() {
					var retJson = JSON.parse(info);
					var imgInfo = {
						success: true,
						msg: '转换成功',
						obj: {}
					};

					var imgList = [];
					for (var ix = 0; ix < retJson.imgcount; ix++) {
						imgList.push(retJson.imgs[ix].img);
					}
					imgInfo.obj.imgList = imgList;

					deferred.resolve(imgInfo);
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					var errObj = JSON.parse(err);
					deferred.reject({
						success: false,
						msg: errObj.msg
					});
				}, 50);
			});
		}.bind(this)).promise();
	};

	/**
	 * 指示灯
	 */
	var siu = function() {
		this.device = getdevice("siu");
	};
	siu.prototype.controlGuideLightSync = function(json) {
		return $.Deferred(function(deferred) {
			this.device.controlGuideLightSync(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};
	siu.prototype.closeAllSync = function(json) {
		return $.Deferred(function(deferred) {
			this.device.closeAllSync(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(JSON.parse(info))
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(JSON.parse(state))
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(JSON.parse(err))
				}, 50);
			});
		}.bind(this)).promise();
	};

	//通用服务
	var unifieddevice = function() {
		this.device = getdevice("unifieddevice");
	};
	//同步
	unifieddevice.prototype.synDevAPI = function(json) {
		return $.Deferred(function(deferred) {
			this.device.synDevAPI(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.resolve(info)
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.resolve(state)
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(err)
				}, 50);
			});
		}.bind(this)).promise();
	};
	//异步
	unifieddevice.prototype.asynDevAPI = function(json) {
		return $.Deferred(function(deferred) {
			this.device.asynDevAPI(JSON.stringify(json), function(info) {
				setTimeout(function() {
					deferred.notify(info)
				}, 50);
			}, function(state) {
				setTimeout(function() {
					deferred.notify(state)
				}, 50);
			}, function(err) {
				setTimeout(function() {
					deferred.reject(err)
				}, 50);
			});
		}.bind(this)).promise();
	};
	unifieddevice.prototype.deviceEventsRemove = function(json) {
		return this.device.deviceEventsRemove(json.DevName);
	};
	unifieddevice.prototype.deviceEventsRemoveAll = function(json) {
		return $.Deferred(function(deferred) {
			this.device.deviceEventsRemoveAll();
		}.bind(this)).promise();
	};

	return {
		card: card,
		idcard: idcard,
		hdprint: hdprint,
		icms: icms,
		faceIRDetect: faceIRDetect,
		signtablet: signtablet,
		uwpbridge: uwpbridge,
		print: print,
		manage: Manage,
		customwebview: customwebview,
		siu: siu,
		highCamera: highCamera,
		unifieddevice: unifieddevice,
		barcode: barcode
	};
};

var JSBridge = {
	Device: Device()
};

/////////////////////////////////////////////////////////////////////
// vkbsettings.js
// 虚拟键盘
/////////////////////////////////////////////////////////////////////

var touchkbIsShow = false;

function openTouchKeyboard(kbLayout) {
	if (window.AppHost) {
		try {
			touchkbIsShow = true;
			vkbService.vkIsShow = true; //ggz
			window.AppHost.getLogHelper().log("show touch keyboard  " + touchkbIsShow);
			window.AppHost.openTouchKeyboard(kbLayout);
		} catch (e) {
			window.AppHost.getLogHelper().error("openTouchKeyboard error " + kbLayout + JSON.stringify(e));
		}
	}
}

function closeTouchKeyboard() {
	if (window.AppHost) {
		window.AppHost.getLogHelper().log("close touch keyboard");
		window.AppHost.closeTouchKeyboard();
		touchkbIsShow = false; //ggz
		vkbService.vkIsShow = false; //ggz
	}
}
var lastFocus = null;
var lastFocusEle = null;

function handleTouchKeyboard(event) {
	deal();
}

function deal() {
	var ele = event.target;
	window.AppHost.getLogHelper().log("touchkbIsShow " + touchkbIsShow);
	if (ele.tagName === "INPUT" || ele.tagName === "TEXTAREA") {
		event.stopPropagation();
		window.AppHost.getLogHelper().log(ele.tagName + " trigger handleTouchKeyboard");
		var isReadonly = ele.hasAttribute("readonly");
		var eleType = ele.getAttribute("type");
		var kbLayout = ele.getAttribute("kb-layout") || "cn";
		if (isReadonly === false &&
			(eleType === null ||
				eleType.toUpperCase() === "TEXT" ||
				eleType.trim() === ""
			)) {
			if (touchkbIsShow === false) {
				let now = Date.now();
				var timer = setTimeout(function() {
					clearTimeout(timer);
					if (lastFocus == null ||
						(lastFocus != null &&
							lastFocusEle === event.target &&
							now - lastFocus > 1000)
					) {
						if (ele === document.activeElement) {
							document.body.style.overflowY = "auto";
							openTouchKeyboard(kbLayout);
							// var timer2 = setTimeout(function () {
							//     clearTimeout(timer2);
							//     ele.focus();
							//     if (!isElementVisible(ele))
							//         ele.scrollIntoView(true);
							// }, 200);
						}
					} else {
						lastFocus = now;
						lastFocusEle = event.target;
					}

				}, 200);

			}
		} else {
			closeTouchKeyboard();
		}
	} else {
		closeTouchKeyboard();
	}
}

function handleInputClick() {
	if (!touchkbIsShow) {
		deal();
	}
}
//ggz
function handleBodyClick(e) {
	var activeElement = $(document.activeElement);
	window.AppHost.getLogHelper().log("activeElement:" + activeElement[0]);
	window.AppHost.getLogHelper().log("activeElement isReadonly:" + activeElement.attr("readonly"));
	window.AppHost.getLogHelper().log("activeElement:" + activeElement[0].toString());

	if (activeElement[0].toString() == "[object HTMLInputElement]" || activeElement[0].toString() ==
		"[object HTMLTextAreaElement]") {
		window.AppHost.getLogHelper().log("HTMLInputElement||HTMLTextAreaElement");
	} else {
		//Close Virtual Keyboard
		if (window.AppHost) {
			if (vkbService.vkIsShow == true) {
				closeTouchKeyboard();
				onKeyboardClosed();
			}
		}
	}
}
//C# 关闭键盘回调
function KeyboardClosedCallback() {
	window.AppHost.getLogHelper().log("close touch keyboard");
	touchkbIsShow = false; //ggz
	vkbService.vkIsShow = false; //ggz
	onKeyboardClosed();
}
//ggz end
function onKeyboardClosed() {
	window.AppHost.getLogHelper().log("onKeyboardClosed");
	touchkbIsShow = false;
	vkbService.vkIsShow = false; //ggz
	document.body.style.overflowY = "hidden";
}

function setKbStatus(status) {
	sessionStorage.setItem("_uwpKbStatus_", status);
}

function enableTouchKbService() {
	try {
		window.AppHost.enableTouchKbService();
	} catch (ex) {
		window.AppHost.getLogHelper().error("changeUwpServicesStatus failed," + ex);
	}
}

function disableKb(showMessage) {
	$("body").off("focus", "input", handleTouchKeyboard);
	$("body").off("click", "input", handleInputClick);
	$("body").off("focus", "textarea", handleTouchKeyboard);
	window.onKeyboardClosed = null;
}

function getKbStatus(status) {
	if (window.AppHost) {
		return window.AppHost.getKbStatus();
	} else {
		return false;
	}
}

function isElementVisible(el) {
	var rect = el.getBoundingClientRect(),
		vWidth = window.innerWidth || doc.documentElement.clientWidth,
		vHeight = window.innerHeight || doc.documentElement.clientHeight,
		efp = function(x, y) {
			return document.elementFromPoint(x, y)
		};

	// Return false if it's not in the viewport
	if (rect.right < 0 || rect.bottom < 0 ||
		rect.left > vWidth || rect.top > vHeight)
		return false;

	// Return true if any of its four corners are visible
	return (
		el.contains(efp(rect.left, rect.top)) ||
		el.contains(efp(rect.right, rect.top)) ||
		el.contains(efp(rect.right, rect.bottom)) ||
		el.contains(efp(rect.left, rect.bottom))
	);
}

function isCoverByKb(el) {
	var wHeight = window.innerHeight,
		eHeight = el.clientHeight,
		rect = el.getBoundingClientRect(),
		eTop = parseInt(rect.top);
	if (eTop + eHeight > wHeight)
		return true;
	else return false;

}

function onSizeChange() {
	window.AppHost.getLogHelper().log("onSizeChange");
	var el = document.activeElement;
	if (el) {
		setTimeout(function() {
			if (isCoverByKb(el)) {
				el.scrollIntoView(true);
			}
		}, 100);
	}
}
var vkbService = {
	vkIsShow: false, //ggz
	Disable: function() {
		//ggz
		try {
			enableTouchKbService();
			disableKb();
			window.onresize = null;
			window.AppHost.getLogHelper().log("切换系统键盘成功");
		} catch (e) {
			window.AppHost.getLogHelper().error("切换为系统键盘失败");
		}

		//ggz end
	},
	init: function() {
		try {
			var kbStatus = getKbStatus();
			if (kbStatus) {
				$("body").on("focus", "input", function() {
					// 日历控件兼容处理
					if ($(this).attr("readonly") == "readonly") return;
					handleTouchKeyboard();
				});
				$("body").on("click", "input", handleInputClick);
				$("body").on("focus", "textarea", handleTouchKeyboard);
				//ggz
				$("body").on("click", handleBodyClick);
				window.KeyboardClosedCallback = KeyboardClosedCallback;
				//ggz end
				window.onKeyboardClosed = onKeyboardClosed;
				window.onresize = onSizeChange;
			}
		} catch (e) {
			window.AppHost.getLogHelper().error("init uwp keyboard failed,error:" + JSON.stringify(e));
		}
	},
}

// 初始化键盘
vkbService.init();

function winGetdevInfo() {
	try {
		var nInfo = JSON.parse(window.AppHost.getManage().getAppData('machineInfo')).MachineId;
		if (nInfo.length == 8) {
			window.AppHostzh = "zhdevice";
		}
	} catch (e) {
		//TODO handle the exception
	}
}
winGetdevInfo();

// 制卡机方法定义start
function zhDownloadImgtoCpan(data, callback) {
	// 通过base64图片下载到本地  data:base64,callback(图片路径)
	try {
		var icms = new JSBridge.Device['icms'];
		var param = {
			iscontent: true,
			file_content: data,
			file_name: Date.parse(new Date())+'.png',
		};
		icms.savefile2local(param).then(function(info) {
			try {
				callback(info.file_path)
			} catch (e) {
				window.AppHost.getLogHelper().error(info);
			}
		}).fail(function(err) {
			window.AppHost.getLogHelper().error('check face err' + JSON.stringify(err));
		});
	} catch (e) {
		window.AppHost.getLogHelper().error('base64下载本地文件有误');
	}
}

function zhPrintFormFunc(imageUrl) {
	// 打印图片 imageUrl:图片的磁盘路径
	try {
		let dfd = $.Deferred();
		let info = '';
		let device = new JSBridge.Device['unifieddevice']();
		device.asynDevAPI({
			DevName: 'shepcodeptr',
			ActionName: 'PrintForm',
			InPara: {
				Timeout: 180000,
				formName: "CardPrinterForm_Card",
				mediaName: "CardPrinterForm_Card",
				fields: "Print_Pic=" + imageUrl,
			},
			iTimeout: 0
		}).then(null, function(err) {
			info += '\r\n' + err;
			dfd.reject(err);
		}, function(msg) {
			var retJson = JSON.parse(msg);
			// window.AppHost.getLogHelper().log("打印卡片成功"+msg);
			if (retJson.EventName === 'PrintFormOver') {
				if (retJson.OutPara && (retJson.OutPara.hResult === 0 || retJson.OutPara.hResult === 99)) {
					try {
						window.AppHost.getLogHelper().log("打印卡片成功");
					} catch (e) {}
					dfd.resolve(retJson.OutPara);
				} else {
					try {
						window.AppHost.getLogHelper().log("打印卡片失败");
					} catch (e) {}
					dfd.reject(retJson.OutPara);
				}
			}
		});
		return dfd.promise();
	} catch (e) {}
};
// 读卡
function zhDispenseCardSync() {
	let dfd = $.Deferred();
	let info = '';
	let device = new JSBridge.Device['unifieddevice']();
	device.synDevAPI({
		DevName: 'shepcodecrd',
		ActionName: 'DispenseCardSync',
		InPara: {
			usNumber: 1, // 例如 1
			bPresent: false, //true-发卡到卡口；false-发卡到通道
			TimeOut: 60000
		},
		iTimeout: 0
	}).then(function(msg) {
		info += '\r\n' + msg;
		var retJson = JSON.parse(msg);
		if (retJson.OutPara && (retJson.OutPara.hResult === 0 || retJson.OutPara.hResult === 99)) {
			dfd.resolve(retJson.OutPara);
		} else {
			info += '\r\n' + '发卡失败';
			dfd.reject(info);
		}
	}, function(err) {
		info += '\r\n' + err;
		dfd.reject(info);
	});
	return dfd.promise();
};
// 退卡
function zhEjectCardFunc() {
	let dfd = $.Deferred();
	let info = '';
	let device = new JSBridge.Device['unifieddevice']();
	device.asynDevAPI({
		DevName: 'shepcodecrd',
		ActionName: 'Eject',
		InPara: {
			ejectpos: 1, // 退卡位置 1-进卡口；2-传输通道；
			TimeOut: 60000 //
		},
		iTimeout: 0
	}).then(null, function(err) {
		info += '\r\n' + err;
		dfd.reject(info);
	}, function(msg) {
		info += '\r\n' + msg;
		var retJson = JSON.parse(msg);
		if (retJson.EventName === ' EjectOver') {
			if (retJson.OutPara && (retJson.OutPara.hResult === 0 || retJson.OutPara.hResult === 99)) {
				info += '\r\n' + '退卡成功';
				dfd.resolve(info);
			} else {
				info += '\r\n' + '退卡失败';
				dfd.reject(info);
			}
		}
	});
	return dfd.promise();
};
// 制卡 zt
function getStatusSyncFunc() {
	try {
		let dfd = $.Deferred();
		let info = '';
		let device = new JSBridge.Device['unifieddevice']();
		device.synDevAPI({
			DevName: 'shepcodeptr',
			ActionName: 'GetStatusSync',
			InPara: {
				pszPara: ""
			},
			iTimeout: 0
		}).then(function(msg) {
			info += '\r\n' + msg;
			var retJson = JSON.parse(msg);
			if (retJson.OutPara && (retJson.OutPara.hResult === 0 || retJson.OutPara.hResult === 99)) {
				dfd.resolve(retJson.OutPara);
			} else {
				info += '\r\n' + '获取状态失败';
				dfd.resolve('zhStateFail');
				//dfd.reject(info);
			}
		}, function(err) {
			info += '\r\n' + err;
			dfd.resolve('zhStateFail');
			// dfd.reject(info);
		});
		return dfd.promise();
	} catch (e) {}
}
// 制卡机方法定义end
// 中行
var zhcmCapture = {
	currentTime: function() {
		try {
			var date = new Date()
			// 获取当前月份
			var nowMonth = date.getMonth() + 1;
			// 获取当前是几号
			var strDate = date.getDate();
			// 添加分隔符“-”
			var seperator = "/";
			// 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
			var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate;
			// 获取的是前一天日期
			var nowHours = date.getHours().toString(); //获取当前⼩时数
			var nowMin = date.getMinutes().toString(); //获取当前分钟数
			var nowSecond = date.getSeconds().toString(); //获取当前秒数写
			var TimeSFM = nowHours + ':' + nowMin + ":" + nowSecond;
			return nowDate + ' ' + TimeSFM;
		} catch (e) {
			return '----';
		}
	},
	//高拍仪开启
	cmCaptureShow_zh: function(x, y, width, height, callback) {
		var hiCam = new JSBridge.Device['highCamera'];
		hiCam.takeHSpeedphoto({
			ActionType: 1,
			Left: x,
			Top: y,
			Width: width,
			Height: height,
			//VideoDom: 'video',
		}).then(function(info) {
			if (info.result) {
				callback(info.msg);
			} else {
				if (window.AppHost) window.AppHost.getLogHelper().error(zhcmCapture.currentTime() +
					'开启（高拍仪）失败:' + info.msg);
			}
		}, function(err) {
			if (window.AppHost) window.AppHost.getLogHelper().error(zhcmCapture.currentTime() +
				'开启（高拍仪）err' + err.msg);
		});

	},
	//高拍仪拍照
	cmCaptureCaptureBase64_zh: function(callback) {
		var hiCam = new JSBridge.Device['highCamera'];
		hiCam.takeHSpeedphoto({
			ActionType: 2,
			Clipped: true,
		}).then(function(info) {
			if (info.result) {
				callback(info.base64);
			} else {
				if (window.AppHost) window.AppHost.getLogHelper().error(zhcmCapture.currentTime() +
					'拍照失败（高拍仪）' + info.msg);
			}
		}, function(err) {
			if (window.AppHost) window.AppHost.getLogHelper().error(zhcmCapture.currentTime() +
				'拍照失败（高拍仪）' + err.msg);
		});
	},
	//高拍仪关闭，隐藏
	cmCaptureHide_zh: function(callback) {
		var hiCam = new JSBridge.Device['highCamera'];
		hiCam.takeHSpeedphoto({
			ActionType: 3
		}).then(function(info) {
			if (info.result) {
				callback(info.msg);
			} else {
				if (window.AppHost) window.AppHost.getLogHelper().error(zhcmCapture.currentTime() +
					'关闭（高拍仪）失败' + info.msg);
			}
		}, function(err) {
			if (window.AppHost) window.AppHost.getLogHelper().error(zhcmCapture.currentTime() +
				'关闭（高拍仪）失败' + err.msg);
		});
	},
	zhOffice_Base_PrintCard: function(base64Value) {
		//一键制卡
		zhDispenseCardSync().then(function(resultMsg) {
			// 进卡 
			if (resultMsg.hResult === 0 || resultMsg.hResult === 99) {
				// 下载base64到本地
				zhDownloadImgtoCpan(base64Value, function(value) {
					// 打印本地路径
					zhPrintFormFunc(value).then(function(resultMsg) {
						if (resultMsg.hResult === 0 || resultMsg.hResult === 99) {
							// 退卡
							zhEjectCardFunc().then(function(resultMsg) {});
						}
					});
				})
			}
		});
	},
	zh_getStatusSyncFunc: function(callback) {
		// 获取制卡机状态
		try {
			getStatusSyncFunc().then(function(resultMsg) {
				if(resultMsg=='zhStateFail'){
					callback('false');
				}else{
					if (resultMsg.fwDevice == 0) {
						callback('true');
					} else {
						callback('false');
					}
				}
			});
		} catch (e) {
			callback('false');
		}
	},
	zh_LXM_Print: function(pdfbase64) {
		// 离线码打印base64
		try {
			$.post('http://localhost:10210/inteGration/hardwareControl/api/printNew', {
				tranType: 1,
				fileExtent: 'pdf',
				prnFlag: 0,
				fileContent: pdfbase64
			}).done(function(info) {}).fail(function(err) {
				if (window.AppHost) window.AppHost.getLogHelper().error(err);
			});
		} catch (e) {}
	},
}
