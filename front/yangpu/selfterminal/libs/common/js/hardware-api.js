window.onbeforeunload = function () {
  OcxControl.Light.allLightClose();
  OcxControl.OCXDeviceClose();
};
document.onreadystatechange = function () {
  if (document.readyState == "complete") {
    var IndexUrl = null;
    try {
      IndexUrl = window.external.GetConfig("url");
    } catch (error) {}
    if (IndexUrl == window.location.href) {
      OcxControl.Light.idCardLightOpen();
      OcxControl.Light.idCardLightClose();
    } else {
      try {
        OCX_Scan.DeinitDevs(); //反初始化高拍仪
        OcxControl.variable.EloamGlobalRet = OCX_Scan.InitDevs(); //初始化高拍仪
      } catch (error) {
        console.log("高拍仪控件错误!")
      }
      try {
        var status = OCX_idCard.Open("IdCardReader", 100000);

      } catch (error) {
        console.log("身份证错误")
      }
      try {
        OCX_Barcode.Open("BCR", 10000);
      } catch (error) {
        console.log("二维码控件错误");
      }
      try {
        var result = OCX_Camera.Open("Camera", 10000);
      } catch (error) {
        console.log("camera open is failed!");
      }

    }
  }
};
window.OcxControl = {
  variable: {
    OCX_idCard: "OCX_idCard",
    OCX_Barcode: "OCX_Barcode",
    OCX_Light: "OCX_Light",
    OCX_Shield: "OCX_Shield",
    OCX_LaserPrint: "OCX_LaserPrint",
    OCX_ReceiptPrint: "OCX_ReceiptPrint",
    OCX_Scan: "OCX_Scan",
    OCX_Camera: "OCX_Camera",
    EloamGlobalRet: null,
    EloamGlobal: null,
    EloamDevice: null,
    EloamVideo: null,
    captureSaveFile: "d:/highCapture.jpg",
    idCardSaveUrl: "d:\\",
    cameraPhotograph: "d:/cameraPhotograph.png"
  },
  reSaveGray: function (fileName, success) {
    //图片灰化
    var EloamGlobal = document.getElementById("OCX_Scan");
    var srcImg = EloamGlobal.CreateImageFromFile(fileName, 0);

    var h = srcImg.GetHeight();
    var w = srcImg.GetWidth();
    var srcImgRcDest = EloamGlobal.CreateRect(0, 0, w, h);
    var srcImgRcSrc = EloamGlobal.CreateRect(0, 0, w, h);
    var newImg = EloamGlobal.CreateImage(w + 50, h + 50, 3);
    var result = newImg.Blend(srcImgRcDest, srcImg, srcImgRcSrc, 0, 0);
    if (result == true) {
      newImg.ToGray();
      newImg.Save(fileName, 0);
      success && success(fileName);
    }
  },
  scanSaveRotate: function (fileName, success) {
    //图片宽大于高时 顺时针旋转90度
    var EloamGlobal = document.getElementById("OCX_Scan");
    var ret = EloamGlobal.InitDevs();
    var srcImg = EloamGlobal.CreateImageFromFile(fileName, 0);
    var h = srcImg.GetHeight();
    var w = srcImg.GetWidth();
    if (w > h) {
      srcImg.Rotate(-90.0, 0x80, 1);
      srcImg.Save(fileName, 0);
      success && success(fileName);
    }
  },
  mergeImages: function (imgsObj, success, error) {
    //合并图像
    //imgsObj<fileName> 图片路径 frontImgfile backImgfile 目标存储 targetFile
    var EloamGlobal = document.getElementById("OCX_Scan");
    var frontImg = EloamGlobal.CreateImageFromFile(imgsObj.frontImgfile, 0);
    var backImg = EloamGlobal.CreateImageFromFile(imgsObj.backImgfile, 0);
    var mergeImg = EloamGlobal.CreateImage(1100, 1300, 3);

    var frontRcDest = EloamGlobal.CreateRect(0, 0, 1100, 634);
    var frontRcSrc = EloamGlobal.CreateRect(0, 0, 1010, 634);

    var backRcDest = EloamGlobal.CreateRect(0, 650, 1100, 634);
    var backRcSrc = EloamGlobal.CreateRect(0, 0, 1000, 634);

    // console.log(mergeImg)
    if (mergeImg) {
      var result1 = mergeImg.Blend(frontRcDest, frontImg, frontRcSrc, 0, 0);
      if (result1 === true) {
        var result2 = mergeImg.Blend(backRcDest, backImg, backRcSrc, 0, 0);
        if (result2 === true) {
          mergeImg.ToGray();
          mergeImg.Save(imgsObj.targetFile, 0);

          success && success(imgsObj.targetFile);
        } else {
          error && error("result2 is error!");
        }
      } else {
        error && error("result1 is error!");
      }
    }
  },
  nativePrint: function (filePath, num, type, success) {
    //filePath绝对路径 type颜色模式 gray和color  success成功回调
    var EloamGlobal = document.getElementById("OCX_Scan");
    var img = EloamGlobal.CreateImageFromFile(filePath, 0);
    var result = null;
    var printNum = num || 1;
    var that = this;
    that.Light.highCaptureLightOpen();
    if (type === "gray" || type === undefined) {
      result = img.ToGray();
      var status = img.Save(filePath, 0);
      console.log(status);
      if (result == true) {
        for (var i = 0; i < printNum; i++) {
          var status = img.AdaptivePrintByDPI("");
        }
        that.Light.highCaptureLightClose();
        success && success(status);
      }
    } else if (type === "color") {
      if (result == true) {
        for (var i = 0; i < printNum; i++) {
          var status = img.AdaptivePrintByDPI("");
        }
        that.Light.highCaptureLightClose();

        success && success(status);
      }
    } else {
      that.Light.highCaptureLightClose();
      alert("打印颜色模式不正确！");
      return;
    }
  },
  changeColorType: function (filePath, type, success) {
    //filePath绝对路径 type颜色模式 gray和color  success成功回调
    var EloamGlobal = document.getElementById("OCX_Scan");
    var img = EloamGlobal.CreateImageFromFile(filePath, 0);
    var result = null;
    console.log(img);
    if (type === "gray") {
      result = img.ToGray();
      img.Save(filePath, 0);
      success && success(img.GetBase64(0, 0));
    } else if (type === "color") {
      result = img.ToColor();
      success && success(img.GetBase64(0, 0));
    }
  },
  idCardRead: function (response, error) {
    var that = this;
    var OCX_idCard = document.getElementById("OCX_idCard");
    var EloamGlobal = document.getElementById("OCX_Scan");
    that.Light.idCardLightOpen();
    try {
      OCX_idCard.ReadImage(100000, 100000, that.variable.idCardSaveUrl);
    } catch (error) {}
    OCX_idCard.attachEvent("OpenCompleted", function () {
      // that.Light.idCardLightOpen();
      // try {
      //   OCX_idCard.ReadImage(100000, 100000, that.variable.idCardSaveUrl);
      // } catch (error) {}
    });
    OCX_idCard.attachEvent("FatalError", function () {
      error && error("FatalError");
    });
    OCX_idCard.attachEvent("TimeOut", function () {
      error && error("TimeOut");
    });
    OCX_idCard.attachEvent("ReadImageComplete", function (data) {
      var portrait = EloamGlobal.CreateImageFromFile(
        "C:/weirong/atap/bin/Front_head.bmp",
        0
      );
      var frontImg = EloamGlobal.CreateImageFromFile(
        that.variable.idCardSaveUrl + "Front.bmp",
        0
      );
      var backImg = EloamGlobal.CreateImageFromFile(
        that.variable.idCardSaveUrl + "Back.bmp",
        0
      );
      that.reSaveGray(that.variable.idCardSaveUrl + "Front.bmp");
      that.reSaveGray(that.variable.idCardSaveUrl + "Back.bmp");
      try {
        OCX_idCard.Eject(-1); //吐出身份证
      } catch (error) {}
      //成功回调函数 data图片数据
      response &&
        response({
          identityInfo: data,
          portrait: portrait.GetBase64(0, 0),
          frontImg: frontImg.GetBase64(0, 0),
          backImg: backImg.GetBase64(0, 0)
        });
      OcxControl.idCardClose();
    });
    OCX_idCard.attachEvent("MediaInserted", function () {
      console.log("MediaInserted");
    });

    OCX_idCard.attachEvent("PrintTaken", function () {
      console.log("PrintTaken");
    });
    OCX_idCard.attachEvent("DeviceError", function () {
      error && error("DeviceError");
    });
    OCX_idCard.attachEvent("ConnectionClosed", function () {
      console.log("ConnectionClosed");
    });
    if (status == -1) {
      error && error();
      return;
    } else {
      console.log("打开成功!")
    }
  },
  idCardClose: function (response, error) {
    //关闭身份证读卡器
    this.Light.idCardLightClose();
    var result = OCX_idCard.CancelAccept();
    OCX_idCard.attachEvent("AcceptCancelled", function () {
      console.log("取消成功");
    });
    if (result == 0) {
      response && response();
    } else if (result == -1) {
      response && error();
    }
  },
  Barcode: function (response, error) {
    //打开二维码
    var that = this;
    var result = OCX_Barcode.ReadBarcode(1000000);
    try {
      that.Light.qrcodeLightOpen();
    } catch (error) {}
    OCX_Barcode.attachEvent("OpenCompleted", function () {
      console.log("OpenCompleted");

    });
    OCX_Barcode.attachEvent("TimeOut", function () {
      error && error("TimeOut");
    });
    OCX_Barcode.attachEvent("BarcodeRead", function (data) {
      //获取到扫描数据
      response && response(data);
      try {
        that.Light.qrcodeLightClose();
      } catch (error) {}
    });
    OCX_Barcode.attachEvent("ReadBarcodeCanceled", function () {
      console.log("ReadBarcodeCanceled");
    });
    OCX_Barcode.attachEvent("DeviceError", function () {
      error && error("DeviceError");
    });
    OCX_Barcode.attachEvent("fatalerror", function () {
      error && error("fatalerror");
    });

  },
  BarcodeClose: function (response, error) {
    this.Light.qrcodeLightClose();
    //关闭二维码扫描
    var result = OCX_Barcode.CancelReadBarcode();
    OCX_Barcode.attachEvent("ReadBarcodeCanceled", function () {
      error && error("fatalerror");
    });
    if (OCX_Barcode) {
      if (result === 0) {
        response && response();
      } else {
        error && error("close failed!");
      }
    } else {
      error && error("close failed!");
    }
  },
  scanOpen: function (pos, response, error) {
    var that = this;
    var View = document.getElementById("EloamView");
    View.style.height = parseInt(pos.height) + "px";
    View.style.width = parseInt(pos.width) + "px";
    View.style.marginLeft = parseInt(pos.left) + "px";
    View.style.marginTop = parseInt(pos.top) + "px";
    that.variable.EloamGlobal = document.getElementById("OCX_Scan");

    if (this.variable.EloamGlobalRet) {
      that.variable.EloamDevice = that.variable.EloamGlobal.CreateDevice(1, 0); //创建设备|____
      that.variable.EloamVideo = that.variable.EloamDevice.CreateVideo(0, 0);
      EloamView.SelectVideo(that.variable.EloamVideo); //
      EloamView.SetText("打开高拍仪中，请等待...", 0); //
      setTimeout(function () {
        that.Light.highCaptureLightOpen();
        that.variable.EloamVideo.EnableDeskew(0); //自动裁切
      }, 4000);
      response && response("open success!");
    } else {
      error && error("scan init is failed!");
    }
  },
  scanSave: function (response, error) {
    //保存图片  返回图片base64 以及图片名称
    var that = this;
    var image = that.variable.EloamVideo.CreateImage(0, EloamView.GetView());
    if (image) {
      image.Save(that.variable.captureSaveFile, 0);
      var _image = that.variable.EloamGlobal.CreateImageFromFile(
        that.variable.captureSaveFile,
        0
      );
      var result = _image.GetBase64(2, 0);
      that.scanSaveRotate(that.variable.captureSaveFile);
      that.reSaveGray(that.variable.captureSaveFile);
      if (result) {
        that.scanClose();
      }
      response && response(result, that.variable.captureSaveFile);
    } else {
      error && error("EloamVideo is not obj");
    }
  },
  scanClose: function (response, error) {
    var that = this;
    var EloamView = document.getElementById("EloamView");
    if (that.variable.EloamVideo) {
      EloamView.SetText("", 0);
      EloamView.style.height = "0px";
      EloamView.style.width = "0px";
      that.variable.EloamVideo.Destroy();
      that.variable.EloamVideo = null;
      that.Light.highCaptureLightClose();
      response && response("close success!");
    } else {
      error && error("failed!");
    }
  },
  sendShield: function (response, error) {
    var that = this;
    OCX_Shield.attachEvent("OpenCompleted", function () {
      var status = OCX_Shield.Reset("NOACTION"); //reset
      console.log("status:" + status);
      if (status != 0) {
        error && error("resert Ukey Error!");
        return;
      }

      console.log("OpenCompleted");
    });

    OCX_Shield.attachEvent("ResetComplete", function () {
      that.Light.ukeyLightOpen();
      var status = OCX_Shield.Dispense(2, true, 10000); //send
      console.log("send:" + status);
      if (status == -1) {
        error && error("dispense Ukey failed!");
      }
      console.log("ResetComplete");
    });
    OCX_Shield.attachEvent("TimeOut", function () {
      error && error("TimeOut");
    });

    OCX_Shield.attachEvent("MediaInserted", function () {
      console.log("MediaInserted");
    });

    OCX_Shield.attachEvent("CardDispensed", function () {
      that.Light.ukeyLightClose();
      response && response("CardDispensed");
    });
    OCX_Shield.attachEvent("CardTaken", function () {
      console.log("CardTaken");
    });
    OCX_Shield.attachEvent("CardUnitError", function () {
      console.log("CardUnitError");
    });
    OCX_Shield.attachEvent("DeviceError", function () {
      console.log("DeviceError");
    });

    OCX_Shield.attachEvent("FatalError", function () {
      console.log("FatalError");
    });

    OCX_Shield.attachEvent("ConnectionClosed", function () {
      console.log("ConnectionClosed");
    });
    var result = OCX_Shield.Open("Ukey", 30000); //open
  },
  getShields: function (response, error) {
    //查询U盾数量
    OCX_Shield.attachEvent("OpenCompleted", function () {
      var res = OCX_Shield.getCardUnitInfo();
      res = JSON.parse(res);
      if (res) {
        response && response(res.CardUnit[1].CUCurrentCount);
      } else {
        error && error("search Ukey error!");
      }
    });
    OCX_Shield.attachEvent("CardUnitError", function () {
      console.log("CardUnitError");
    });
    OCX_Shield.attachEvent("DeviceError", function () {
      console.log("DeviceError");
    });

    OCX_Shield.attachEvent("FatalError", function () {
      console.log("FatalError");
    });
    var result = OCX_Shield.Open("Ukey", 30000);
  },
  initShields: function (num, response, error) {
    var setData = {
      InitialCount: [0, parseInt(num, 10)],
      Count: [0, parseInt(num, 10)]
    };
    var result = OCX_Shield.Open("Ukey", 30000);
    var status = OCX_Shield.SetCardUnitInfo(JSON.stringify(setData));
    if (status == 0) {
      response && response("setShields success!");
    } else {
      error && error("setShields failed!");
    }
  },
  receiptPrint: function (data, response, error) {
    //传入打印数据
    var that = this;
    OCX_ReceiptPrint.attachEvent("OpenCompleted", function () {
      console.log("OpenCompleted!");
      var status = OCX_ReceiptPrint.PrintRawData(data);
      that.Light.receiptprinterLightOpen();
      console.log(status);
    });
    OCX_ReceiptPrint.attachEvent("PrintComplete", function () {
      var result = OCX_ReceiptPrint.ControlMedia("EJECT,CUT", -1);
      if (result == 0) {
        that.Light.receiptprinterLightClose();
        response && response();
      } else {
        error && error("Print Error!");
      }
    });
    OCX_ReceiptPrint.attachEvent("DeviceError", function () {
      error && error("DeviceError!");
    });
    OCX_ReceiptPrint.attachEvent("PrintTaken", function () {
      //取出身份证
      console.log("PrintTaken");
    });
    OCX_ReceiptPrint.attachEvent("TimeOut", function () {
      error && error("TimeOut!");
    });
    //回执打印
    var result = OCX_ReceiptPrint.Open("ReceiptPrinter", 100000);
    if (result == -1) {
      error && error();
    }
  },
  receiptPrintClose: function (response, error) {
    this.Light.receiptprinterLightClose();
    var result = OCX_ReceiptPrint.CloseConnection();
    if (result == 0) {
      response && response("success!");
    } else {
      error && error("close receiptPrint is failed!");
    }
  },
  laserPrint: function (type, printNums, response, error) {
    //file得是绝对路径
    var that = this;
    var quantity = printNums || 1;
    OCX_LaserPrint.attachEvent("OpenCompleted", function () {
      console.log("OpenCompleted");
      var status = OCX_LaserPrint.PrintRawData(
        "papercount=1;file1=d:/wondersPrint/" + type + ".html"
      );
      that.Light.documentprinterLightOpen();
    });
    OCX_LaserPrint.attachEvent("PrintComplete", function () {
      if (quantity <= 1) {
        that.Light.documentprinterLightClose();

        response && response("laserPrint is response!");
      } else {
        var status = OCX_LaserPrint.PrintRawData(
          "papercount=1;file1=d:/wondersPrint/" + type + ".html"
        );
        quantity--;
      }
    });
    OCX_LaserPrint.attachEvent("TimeOut", function () {
      error && error("TimeOut");
    });
    OCX_LaserPrint.attachEvent("DeviceError", function () {
      error && error("DeviceError");
    });
    var status = OCX_LaserPrint.Open("StatementPrinter", 10000);
    if (status == -1) {
      error && error("laserPrint open failed!");
    }
  },
  cameraOpen: function (pos, response, error) {
    var _result = OCX_Camera.StartDisplay(
      "EXTRA",
      parseInt(pos.left, 10),
      parseInt(pos.top, 10),
      parseInt(pos.width, 10),
      parseInt(pos.height, 10)
    );
    OCX_Camera.attachEvent("OpenCompleted", function () {

    });
    OCX_Camera.attachEvent("DisplayStarted", function () {
      response && response("打开成功!");
    });
    OCX_Camera.attachEvent("DeviceError", function () {
      error && error("DeviceError");
    });
    OCX_Camera.attachEvent("TimeOut", function () {
      error && error("TimeOut");
    });
    OCX_Camera.attachEvent("DisplayStoped", function () {
      error && error("DisplayStoped");
    });
    OCX_Camera.attachEvent("FatalError", function () {
      error && error("FatalError");
    });

  },
  cameraPhotograph: function (response, error) {
    var result = OCX_Camera.TakePicture(
      "EXTRA",
      this.variable.cameraPhotograph
    );
    var EloamGlobal = document.getElementById(this.variable.OCX_Scan);
    var that = this;
    OCX_Camera.attachEvent("TakePicture", function () {
      var image = EloamGlobal.CreateImageFromFile(
        that.variable.cameraPhotograph,
        0
      );
      that.cameraClose(function (res) {
        console.log(res);
      });
      var imageBase64 = image.GetBase64(13, 0);
      /**
       * 等待getBase64
       */
      setTimeout(function () {
        if (image) {
          response && response(imageBase64);
        }
      }, 100);
      /*---------*/
    });
    OCX_Camera.attachEvent("MediaThreshold", function () {
      error && error("MediaThreshold");
    });
    OCX_Camera.attachEvent("DeviceError", function () {
      error && error("DeviceError");
    });
    OCX_Camera.attachEvent("InvalidData", function () {
      error && error("InvalidData");
    });
  },
  cameraClose: function (response, error) {
    if (OCX_Camera) {
      var result = OCX_Camera.StopDisplay("EXTRA");
      if (result == 0) {
        response && response("camera closed!");
      }
    }
  },
  Light: {
    //灯光
    LightInit: function (response, error) {
      var result = OCX_Light.Open("GuideLights", 50000);
      OCX_Light.SetGuidLigtEx("CAMERA", "SLOW");
    },
    //身份证
    idCardLightOpen: function (response, error) {
      var result = OCX_Light.Open("GuideLights", 50000);
      OCX_Light.SetCardReaderLight("SLOW");
    },
    idCardLightClose: function (response, error) {
      OCX_Light.SetCardReaderLight("OFF");
    },
    //高拍仪
    highCaptureLightOpen: function () {
      var result = OCX_Light.Open("GuideLights", 50000);
      OCX_Light.SetGuidLigtEx("NOTESDISPENSER", "CONTINUOUS");
    },
    highCaptureLightClose: function () {
      OCX_Light.SetGuidLigtEx("NOTESDISPENSER", "OFF");
    },
    //ukey
    ukeyLightOpen: function () {
      var result = OCX_Light.Open("GuideLights", 50000);
      OCX_Light.SetGuidLigtEx("UKEY", "SLOW");
    },
    ukeyLightClose: function () {
      OCX_Light.SetGuidLigtEx("UKEY", "OFF");
    },
    //凭条打印机
    receiptprinterLightOpen: function () {
      var result = OCX_Light.Open("GuideLights", 50000);
      OCX_Light.SetGuidLigtEx("RECEIPTPRINTER", "SLOW");
    },
    receiptprinterLightClose: function () {
      OCX_Light.SetGuidLigtEx("RECEIPTPRINTER", "OFF");
    },
    //激光打印机

    documentprinterLightOpen: function () {
      var result = OCX_Light.Open("GuideLights", 50000);
      OCX_Light.SetGuidLigtEx("DOCUMENTPRINTER", "SLOW");
    },
    documentprinterLightClose: function () {
      OCX_Light.SetGuidLigtEx("DOCUMENTPRINTER", "OFF");
    },
    //二维码
    qrcodeLightOpen: function () {
      var result = OCX_Light.Open("GuideLights", 50000);
      OCX_Light.SetGuidLigtEx("SCANNER", "SLOW");
    },
    qrcodeLightClose: function () {
      var result = OCX_Light.SetGuidLigtEx("SCANNER", "OFF");
    },
    //关闭所有灯
    allLightClose: function () {
      var that = OcxControl.Light;
      that.qrcodeLightClose();
      that.idCardLightClose();
      that.highCaptureLightClose();
      that.receiptprinterLightClose();
      that.documentprinterLightClose();
      that.ukeyLightClose();
    }
  },
  OCXDeviceClose: function () {
    try {
      OcxControl.cameraClose(function(res){},function(err){
        console.log(err)
      });
    } catch (error) {
      
    }
    // 关闭所有设备
    try {
      var cameraStatus = OCX_Camera.CloseConnection();
    } catch (error) {

    }
    try {
      OCX_idCard.CloseConnection();
    } catch (error) {}
    try {
      var Print = OCX_ReceiptPrint.CloseConnection();
    } catch (error) {}
    try {
      var Barcode = OCX_Barcode.CloseConnection();
    } catch (error) {}
    try {
      var idcardLaserPrint = OCX_LaserPrint.CloseConnection();
    } catch (error) {}
    try {
      var Shield = OCX_Shield.CloseConnection();
    } catch (error) {}
    try {
      OcxControl.scanClose();
    } catch (error) {

    }
  }
};