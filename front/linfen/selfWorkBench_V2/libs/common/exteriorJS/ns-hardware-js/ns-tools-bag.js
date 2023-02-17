//定义包  农商行 依赖jq
/**
 *  通讯类
 * **/
// eslint-disable-next-line no-undef
(function ($) {
    var NATIVE_WS_URL_DEFAULT = 'ws://127.0.0.1:12347';
    var NATIVE_WS_URL_HWANG = 'ws://127.0.0.1:20206';
    var NATIVE_HTTP_URL_DEFAULT = 'http://127.0.0.1:12346/CallDevice';

    var WM_USER = 0x400;
    var AFS_OPEN_COMPLETE = WM_USER + 1;
    var AFS_CLOSE_COMPLETE = WM_USER + 2;
    var AFS_FATAL_ERROR = WM_USER + 3;
    var AFS_DEVICE_ERROR = WM_USER + 4;
    var AFS_TIMEOUT = WM_USER + 5;
    var AFS_RESET_COMPLETE = WM_USER + 6;

    var NATIVE_HTTP_TIMEOUT_DEFAULT = 30000;
    // var NATIVE_ASYNC_TIMEOUT_DEFAULT = 70000;

    var stateCallback = [];//中间过程回调函数
    var completeCallback = [];//完成回调函数
    var webHWSocket;
    let isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
    //汉王输入法linux通过websocket方式通讯
    if(!isWin){
        initWebSocketHW();
    }

    // initWebSocket();
    //需要将事件类型由结束事件，转换成中间事件处理的事件集合
    var changeToProcessCallbackAction = ["EjectOver"];
    //需要将事件类型由中间事件，转换成结束事件处理的事件集合
    var changeToCompleteCallbackEvent = ["CardTaken"]

    /**
     * 初始化websocket
     */
    function initWebSocket() {
        var lockReconnect = false;
        try {
            var socket = new window.WebSocket(NATIVE_WS_URL_DEFAULT);
            socket.onclose = function(e)
            {
                reconnect();
                console.warn("websocket closed. code:" + e.code + ' reason:' + e.reason + ' isUserClose:' + e.wasClean);
            };
            socket.onerror = function(e)
            {
                console.warn("websocket error. code:" + e.code + ' reason:' + e.reason + ' isUserClose:' + e.wasClean);
            };
            socket.onopen = function() {
                console.info('socket.onopen');
            };
            socket.onmessage = function(message) {
                //console.info('Web socket data:' + JSON.stringify(message.data));
                var msgData = JSON.parse(message.data);
                if(msgData&&msgData.devName)
                {
                    if(typeof(stateCallback[msgData.devName]) === 'function') {

                        if(changeToCompleteCallbackEvent.indexOf(msgData.operation)>-1)
                        {
                            completeCallback[msgData.devName](message.data);
                        }else
                        {
                            stateCallback[msgData.devName](message.data);
                        }

                    }else
                    {
                        console.info(msgData.devName + ' no stateCallback function(WebSocket)');
                    }
                }else{
                    console.error('parse JSON data error!');
                }

            };
        } catch (error) {
            console.error(error);
        }
        /**
         * websocket自动重连
         */
        function reconnect() {
            if(lockReconnect) {
                return;
            }
            lockReconnect = true;
            setTimeout(initWebSocket, 10000);
        }
    }

    /**
     * 初始化websocketHW
     */
    function initWebSocketHW() {
        var lockHWReconnect = false;
        try {
            webHWSocket =  new WebSocket(NATIVE_WS_URL_HWANG);
            webHWSocket.onclose = function(e)
            {
                reconnectHW();
                console.warn("webHWSocket closed. code:" + e.code + ' reason:' + e.reason + ' isUserClose:' + e.wasClean);
            };
            webHWSocket.onerror = function(e)
            {
                reconnectHW();
                console.warn("webHWSocket error. code:" + e.code + ' reason:' + e.reason + ' isUserClose:' + e.wasClean);
            };
            webHWSocket.onopen = function() {
                console.info('webHWSocket.onopen');
            };
            /*
            webHWSocket.onmessage = function(message) {
             //暂不处理输入法的按键回调
            };
             */
        } catch (error) {
            console.error(error);
        }
        /**
         * websocket自动重连
         */
        function reconnectHW() {
            if(lockHWReconnect) {
                return;
            }
            lockHWReconnect = true;
            setTimeout(initWebSocketHW, 10000);
        }
    }

    /**
     * http方式通讯
     * @param {*} sendData 发送数据
     * @param {Boolean} bAsync 是否异步请求
     */
    function sendHttpData (sendData, bAsync, successCallback, errorCallback, timeout,stateCallback) {
        var response = "";
        var xmlhttp = new XMLHttpRequest();
        if(timeout > 0) {
            timeout += 10000;//通讯本身超时事件多加10秒
        }
        if(bAsync)
        {
            try {
                xmlhttp.timeout = timeout;
                xmlhttp.ontimeout = function(e)
                {
                    console.warn("sendHttpWebSocket.ontimeout: "+timeout);
                    console.warn(e);
                    var resJson = {};
                    resJson.errorCode = -1;
                    resJson.errDesc = "sendHttpWebSocket.ontimeout["+timeout+"]";
                    errorCallback(JSON.stringify(resJson));
                };
            }
            catch (error)
            {
                console.error(error);
            }
        }
        xmlhttp.onerror = function(e)
        {
            console.error("sendHttpWebSocket.onerror: ");
            console.error(e);
            if(bAsync) {
                var resJson = {};
                resJson.errorCode = -1;
                resJson.errDesc = "sendHttpWebSocket.onerror";
                errorCallback(JSON.stringify(resJson));
            }
        };

        var baseUrl = ''
        if (location.search != "")
        {
            var matched = /[?&]httpBaseUrl=([A-Za-z0-9\-:/.]+)/.exec(location.search);
            if(matched && matched.length > 1) {
                baseUrl = (matched[1]);
            } else {
                baseUrl = NATIVE_HTTP_URL_DEFAULT;
            }
        } else {
            baseUrl = NATIVE_HTTP_URL_DEFAULT;
        }

        baseUrl = NATIVE_HTTP_URL_DEFAULT || baseUrl;
        xmlhttp.onreadystatechange = function()
        {
            //console.info("xmlhttp.status:"+xmlhttp.status)
            if((xmlhttp.status == 200 || xmlhttp.status == 0)  && xmlhttp.readyState == 4) {
                response = xmlhttp.responseText;
                if(response && bAsync && errorCallback) {
                    //console.info("Http result:"+response+"====="+successCallback.constructor.name);
                    var responseJson = JSON.parse(response);
                    if(changeToProcessCallbackAction.indexOf(responseJson.msgName)>-1)
                    {
                        if(typeof(stateCallback) === 'function')
                        {
                            stateCallback(response);
                        }else
                        {
                            console.info(stateCallback + ' no stateCallback function(HTTP)');
                        }
                    }
                    else
                    {
                        try
                        {
                            var responseResult = responseJson.param.result;
                            if(responseResult == true || responseResult == "ok" ||responseResult == 0)
                            {
                                successCallback(response);
                            }else if(responseResult==undefined&&responseJson.result==0){
                                successCallback(response);
                            }
                                // else if((responseResult == -4 ||responseResult == -48)&&(responseJson.msgName != "signOver"
                                //     && responseJson.msgName != "takePicturePreview" && responseJson.msgName != "faceDetector"
                                //     && responseJson.msgName != "AcceptAndReadFeature"&& responseJson.msgName != "ReadIDFeature"
                                //     && responseJson.msgName != "cancelaccept"&& responseJson.msgName != "readIDPassport")){
                                //     console.warn("Method Canceled or TimeOut");
                            // }
                            else if(responseJson.operation == "AcceptAndReadTracks"||responseJson.operation == "AcceptAndReadFeature"||responseJson.operation == "ReadIDFeature")//指纹仪全complete回调，签字的取消也走complete回调
                            {
                                console.warn("Method callback by Complete!");
                                console.warn(response.operation);
                                successCallback(response);
                            }
                            else
                            {
                                errorCallback(response);
                            }
                        }
                        catch (error)
                        {
                            console.warn("parce responseJson error: ");
                            console.warn(error);
                            errorCallback(response);
                        }


                    }
                }
            } else if(xmlhttp.status == 400) {
                console.warn("http 400 error");
                if(bAsync && errorCallback)
                {
                    var resJson = {};
                    resJson.errorCode = 400;
                    resJson.errDesc = "http 400 error";
                    errorCallback(JSON.stringify(resJson));
                }
            } else if(xmlhttp.status == 500)
            {
                console.warn("http 500 error)");
                if(bAsync && errorCallback)
                {
                    var resJson = {};
                    resJson.errorCode = 500;
                    resJson.errDesc = "http 500 error";
                    errorCallback(JSON.stringify(resJson));
                } else {
                    console.warn(xmlhttp.status);
                }
            }else{
                console.warn(xmlhttp.status);
            }
        }
        //console.debug("Http send post data["+baseUrl+"],data["+sendData+"]["+xmlhttp.responseURL+"]");
        xmlhttp.open("POST", baseUrl, bAsync);
        xmlhttp.setRequestHeader("Content-type","application/json;charset=utf-8");
        xmlhttp.send(sendData);
        return response;
    }

    var getTimeout = function(param){
        try
        {
            var paramJSON = JSON.parse(param);
            var myTimeout = paramJSON.TimeOut;
            var timeout1 = paramJSON.Timeout;
            if(myTimeout!=undefined)
            {
                return myTimeout;
            }else if(timeout1!=undefined){
                return timeout1;
            }else
            {
                return NATIVE_HTTP_TIMEOUT_DEFAULT;
            }
        }
        catch (e)
        {
            return NATIVE_HTTP_TIMEOUT_DEFAULT;
        }

    }

    var callID = 0;
    function packageCommond(devName, actionName, param,bAsync)
    {
        callID = callID > 0 ? callID + 1 : Math.floor(Math.random()*20000);
        var obj = {
            methodType: bAsync?1:0,//1异步0同步
            callID: callID,
            devName: devName,
            actionName: actionName,
            pluginMethod:bAsync?"exec":"getInfo",
            param: param
        }
        return obj;
    }
    function packagePriorityCommond(devName, actionName, param)
    {
        callID = callID > 0 ? callID + 1 : Math.floor(Math.random()*20000);
        var obj = {
            methodType: 3,//1异步0同步3获取属性
            callID: callID,
            devName: devName,
            actionName: actionName,
            pluginMethod:"getPriority",
            param: param
        }
        return obj;
    }
    var commonMethodBridge = function(logicName,methodName,async,param, successCallback, processCallback, errorCallback){
        if(async){
            stateCallback[logicName] = processCallback;
        }
        return sendHttpData(JSON.stringify(packageCommond(logicName,
            methodName, param, async)), async,
            successCallback, errorCallback, getTimeout(param),processCallback);
    };
    var commonDeferred = function(logicName,methodName,async,param){
        if(async){
            return $.Deferred(function (deferred) {
                commonMethodBridge(logicName,methodName,async,JSON.stringify(param)
                    , function (info) {
                        setTimeout(function () {
                            deferred.resolve(JSON.parse(info))
                        }, 50);
                    }
                    , function (state) {
                        setTimeout(function () {
                            deferred.notify(JSON.parse(state))
                        }, 50);
                    }
                    , function (err) {
                        setTimeout(function () {
                            deferred.reject(JSON.parse(err))
                        }, 50);
                    });
            }.bind(this)).promise();
        }else{
            return commonMethodBridge(logicName,methodName,async,JSON.stringify(param));
        }

    }
    var hideWindowCommond = function(methodName,param){
        callID = callID > 0 ? callID + 1 : Math.floor(Math.random()*20000);
        var obj = {
            methodType: 0,
            pluginName: "GWI_Plugin",
            callID: callID,
            pluginMethod: "execute",
            devName: "GWI_Plugin",
            actionName: methodName,
            param:JSON.stringify(param)
        }
        return sendHttpData(JSON.stringify(obj), false,
            null, null, 10000,null,false);
    }
    var sendWebsocket = function(content){
        webHWSocket.send(content);
    }


    $.baseBridge = {
        commonDeferred:commonDeferred,
        hideWindowCommond:hideWindowCommond,
        packagePriorityCommond:packagePriorityCommond,
        stateCallback:stateCallback,
        completeCallback:completeCallback,
        changeToProcessCallbackAction:changeToProcessCallbackAction,
        changeToCompleteCallbackEvent:changeToCompleteCallbackEvent,
        sendHttpData:sendHttpData,
        packageCommond:packageCommond,
        getTimeout:getTimeout,
        AFS_OPEN_COMPLETE  : AFS_OPEN_COMPLETE   ,
        AFS_CLOSE_COMPLETE : AFS_CLOSE_COMPLETE  ,
        AFS_FATAL_ERROR    : AFS_FATAL_ERROR     ,
        AFS_DEVICE_ERROR   : AFS_DEVICE_ERROR    ,
        AFS_TIMEOUT        : AFS_TIMEOUT         ,
        AFS_RESET_COMPLETE : AFS_RESET_COMPLETE,
        sendWebsocket:sendWebsocket,
    };
})(jQuery);

(function($) {
	//$.getScript("ns-hardware-js/BaseBridge.js");
	$.baseUtil = {
        getLogicNameByMethod:function (methodName) {
            var logicName;
            switch (methodName) {
                case "idCardOpen":
                    logicName = "IDCardReader";
                    break;
				case "idCardClose":
                    logicName = "IDCardReader";
                    break;
				case "qrCodeOpen":
                    logicName = "Barcode";
                    break;
				case "qrCodeClose":
                    logicName = "Barcode";
                    break;
                case "Camera_Show":
                    logicName = "FaceCamera";
                    break;
                case "Camera_Base64":
                    logicName = "FaceCamera";
                    break;
                case "Camera_Hide":
                    logicName = "FaceCamera";
                    break;
                case "printerInit":
                    logicName = "DocumentPrinter";
                    break;
                case "printerPrint":
                    logicName = "DocumentPrinter";
                    break;
            }
            return logicName;
        },
    }
	//判断是windows还是linux
	let isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
	//如果上送MAC和设备信息频繁，可通过增加缓存处理
	//let isConfig = false;
	let deleteFile = function(path) {
		let param = {};
		param.filename = path;
		$.baseBridge.commonDeferred('GWI_Plugin', 'deletefile', false, param);
	}
	$.nsdeviceSum = {
		idCardOpen: function(callback) {
            let paramJson = {
				DevType : 4,
				TrackMap : 776,
				TimeOut : 0
			};
            if(isWin){
                paramJson.TrackMap= 772
            }
            let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("idCardOpen"),
                'AcceptAndReadTracks', true, paramJson);
            deferred.done(function (response) {
                //$("#result").text("idCardOpen done:"+JSON.stringify(response))
                let idData = "";
                if(isWin){
                    idData = response.param.track3.datas;
                }else{
                    idData = response.param.chipdata.datas;
                }
                let idDataJson = idData.split("|");
                let result ={};
				result.Result = 0;
				result.Desc = "成功";
				result.Data={};
				result.Data.Name = idDataJson[0].split("=")[1];
				result.Data.Sex = idDataJson[1].split("=")[1];
				result.Data.People = idDataJson[2].split("=")[1];
				result.Data.Birthday = idDataJson[3].split("=")[1];
				result.Data.Address = idDataJson[4].split("=")[1];
				result.Data.Number = idDataJson[5].split("=")[1];
				result.Data.StartDate = idDataJson[7].split("=")[1];
				result.Data.EndDate = idDataJson[8].split("=")[1];
				result.Data.CardImagePath = idDataJson[9].split("=")[1];
                callback(result);
				$.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("idCardOpen"),
                'Eject', false, paramJson);
            })
            .fail(function () {
                //$("#result").text("idCardOpen fail:"+JSON.stringify(result))
                let result ={};
                result.Result = 1;
                result.Desc = "失败";
                callback(result);
            })
        },
		idCardClose: function(callback) {
			let paramJson = {};
			let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("idCardClose"),
				'CancelAsyncRequest', true, paramJson);
			deferred.done(function() {
					//$("#result").text("idCardClose done:"+JSON.stringify(result));
					let result = {};
					result.Result = 0;
					result.Desc = "成功";
					result.Data = {};
					callback(result);
				})
				.fail(function() {
					//$("#result").text("idCardClose fail:"+JSON.stringify(result))
					let result = {};
					result.Result = 1;
					result.Desc = "失败";
					callback(result);
				})
		},
		qrCodeOpen: function(callback) {
			let paramJson = {};
			let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("qrCodeOpen"),
				'ScanBarcode', true, paramJson);
			deferred.done(function(response) {
					//$("#result").text("qrCodeOpen done:"+JSON.stringify(response))
					let result = {};
					result.Result = 0;
					result.Desc = "成功";
					result.Data = {};
					result.Data.BarcodeData = response.param.ScanData[0].BarcodeData;
					result.Data.Symbology = response.param.ScanData[0].Symbology;
					callback(result);
				})
				.fail(function() {
					//$("#result").text("qrCodeOpen fail:"+JSON.stringify(result))
					let result = {};
					result.Result = 1;
					result.Desc = "失败";
					callback(result);
				})
		},
		qrCodeClose: function(callback) {
			let paramJson = {};
			let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("qrCodeClose"),
				'CancelScan', true, paramJson);
			deferred.done(function() {
					//$("#result").text("qrCodeClose done:"+JSON.stringify(result));
					let result = {};
					result.Result = 0;
					result.Desc = "成功";
					result.Data = {};
					callback(result);
				})
				.fail(function() {
					//$("#result").text("qrCodeClose fail:"+JSON.stringify(result))
					let result = {};
					result.Result = 1;
					result.Desc = "失败";
					callback(result);
				})
		},
		hideWindow: function(callback) {
            //先置顶怡化应用，windows应用名"JColsShellApp",信创应用名"YHBrowser"
            let paramJson = {};
            if(isWin){
                paramJson = {
                    windowname: "JColsShellApp",
                    control:"top",
                };
            }else{
                paramJson = {
                    windowname: "YHBrowser",
                    control:"top",
                };
            }
            $.baseBridge.hideWindowCommond('ctrlwindow', paramJson);
            let result ={};
            result.Result = 0;
            result.Desc = "成功";
            result.Data ={};
            //通知应用切换窗口
            paramJson = {
                "type": "cn-platform-on-hide",
                "id": "2"
            };
            $.baseBridge.hideWindowCommond('ReplyWebSocket', paramJson);
            callback(result);
        },
		Camera_Show: function(width, height, x, y, callback) {
			let paramJson = {
				Camera: 1,
				Action: 0,
				Width: width, //窗口的宽
				Height: height, //窗口的高
				X: x, //窗口的横坐标
				Y: y, //窗口的纵坐标
				TimeOut: 0,
			};
			let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("Camera_Show"),
				'SetVideoDisplay', true, paramJson);
			deferred.done(function() {
					let result = {};
					result.Result = 0;
					result.Desc = "成功";
					callback(result);
				})
				.fail(function() {
					let result = {};
					result.Result = 1;
					result.Desc = "失败";
					callback(result);
				})
		},
		Camera_Base64: function(callback) {
			//路径需要判断是windows还是linux
			//let isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
			let path = "";
			if(isWin) {
				path = "C:\\KIOSK\\PyDeviceService\\1.jpg"
			} else {
				path = "/usr/KIOSK/1.jpg"
			}
			let paramJson = {
				Camera: 1,
				path: path,
				TimeOut: 0
			};
			let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("Camera_Base64"),
				'TakePicture', true, paramJson);
			deferred.done(function() {
					//获取base64
					let base64json = {
						filename: path
					};
					let sendResult = $.baseBridge.commonDeferred('GWI_Plugin',
						'readfile64', false, base64json);
					let base64Json = JSON.parse(sendResult);
					let result = {};
					//删除照片
					deleteFile(path);
					if(base64Json.success == 'true') {
						result.Result = 0;
						result.Desc = "成功";
						result.Base64 = base64Json.data;
					} else {
						result.Result = 1;
						result.Desc = "失败";
						result.Base64 = "";
					}
					callback(result);
				})
				.fail(function() {
					let result = {};
					result.Result = 1;
					result.Desc = "失败";
					callback(result);
				})
		},
		Camera_Hide: function(callback) {
            let paramJson = {
                Camera : 1,
                Action : 1,
                TimeOut : 0
            };
            let sendResult = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("Camera_Hide"),
                'SetVideoDisplay', false, paramJson);
            let sendResultJson = JSON.parse(sendResult);
            if(sendResultJson.result == 0) {
                let result ={};
                result.Result = 0;
                result.Desc = "成功";
                callback(result);
            }else{
                let result ={};
                result.Result = 1;
                result.Desc = "失败";
                callback(result);
            }
        },
		getIME: function() {
			//windows与linux调用汉王输入法不同
			if(isWin) {
				let message = "550,530;740,300;255;5"; //英文小写
				//let message = "550,530;740,300;255;0";//手写
				//是否要根据输入框展示数字还是英文
				//let message = "550,530;740,300;255;11";//数字
				let paramJson = {
					Message: message,
					TimeOut: 0
				};
				$.baseBridge.commonDeferred("DevProcess",
					'SendMessage', false, paramJson);
			} else {
				//是否需要根据输入展示数字还是英文
				//let  showType = "5";//5:英文小写，1:数字，4:手写
				//$.baseBridge.sendWebsocket("{\"sequence\": 1,\"device\": \"SOFTKEYBOARD\",\"method\": \"setKeyboardType\",\"args\": { \"type\": " + showType + "} }");
				//打开键盘
				$.baseBridge.sendWebsocket("{\"sequence\": 1,\"device\": \"SOFTKEYBOARD\",\"method\": \"showWnd\"}");
			}

		},
		getConfig: function(callback) {
			let paramJson = {
				all: false
			};
			let sendResult = $.baseBridge.commonDeferred("GWI_Plugin",
				'getipmac', false, paramJson);
			let sendResultJson = JSON.parse(sendResult);
			if(sendResultJson.success == "true") {
				let result = {};
				result.Result = 0;
				result.Desc = "获取设备信息成功";
				result.Bank = "SHRCB";
				//一台设备至少有俩个MAC，一个通讯，一个打印机，需要区分，目前截取10.开头的表示通讯
				for(let i = 0; i < sendResultJson.data.length; i++) {
					console.info("sendResultJson.data.length")
					if(sendResultJson.data[i].IP.indexOf("10.") == 0) {
						result.Mac = sendResultJson.data[i].MAC;
					}
				}
				callback(result);
			} else {
				let result = {};
				result.Result = 1;
				result.Desc = "失败";
				result.Bank = "SHRCB";
				result.Mac = "";
				callback(result);
			}
		},
		hideWindow2: function(callback) {
			let webYHSocket = new WebSocket("ws://127.0.0.1:54216");
			webYHSocket.onopen = function() {
				console.info('socket.onopen');
				let param = {
					type: "cn-platform-show",
					payload: {
						name: "home"
					},
					id: "1"
				}
				webYHSocket.send(JSON.stringify(param));
			};
			webYHSocket.onmessage = function(message) {
				console.info('Web socket data:' + JSON.stringify(message.data));
			}
		},
		printerInit: function(callback) {
			let paramJson = {};
			let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("printerInit"),
				'OpenConnection', true, paramJson);
			deferred.done(function() {
					let result = {};
					result.Result = 0;
					result.Desc = "打印机初始化成功";
					callback(result);
				})
				.fail(function() {
					let result = {};
					result.Result = 1;
					result.Desc = "打印机初始化失败";
					callback(result);
				})
		},
		printerPrint: function(data, callback) {
            //传入的data为base64，需要转换
            let path = "";
            if(isWin){
                path = "C:\\KIOSK\\PyDeviceService\\print.pdf";
            }else{
                path = "/usr/KIOSK/print.pdf";
            }
            let paramBase64 = {};
            paramBase64.base64 = data;
            paramBase64.filename = path;
            let sendResult = $.baseBridge.commonDeferred('GWI_Plugin','64tofile', false, paramBase64);
            let sendResultJson = JSON.parse(sendResult);
            if(sendResultJson.success){
                //传入打印数据
                let paramJson = {};
                let startPrint="";
                let endPrint ="";
                if(isWin){
                    //判断是怡化还是长城的智柜，cc表示长城，yh表示怡化
                    let paramType = {};
                    paramType.filename = "C:/KIOSK/PyDeviceService/config/filename.txt";
                    let resultFile = $.baseBridge.commonDeferred('GWI_Plugin','readfile', false,paramType);
                    let resultFileJson = JSON.parse(resultFile);
                    if(resultFileJson.data == "yh"){
                        //怡化智柜打印数据
                        startPrint ="1|1|0|";
                        endPrint = "|0|0|0|0";
                    }else {
                        //长城智柜打印数据
                        startPrint ="pagesource=A4;copies=1;file[0]=";
                        endPrint = ";stamp=0;duplex=1;color=0;direction=0";
                    }
                }else{
                    startPrint = "PaperNum=1;PrintType=4;Stamp=0;File[0]=";
                    endPrint = ";WaitNum=1";
                }
                paramJson.prtText   = startPrint+path+endPrint;
                paramJson.TimeOut = 0;
                let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("printerPrint"),
                    'PrintText', true, paramJson);
                deferred.done(function () {
                    //删除文件
                    deleteFile(path);
                    //打印数据传入成功后出纸
                    let param = {};
                    param.mediaCtrol  = 1;
                    param.TimeOut = 0;
                    let deferred = $.baseBridge.commonDeferred($.baseUtil.getLogicNameByMethod("printerPrint"),
                        'PrintText', true, paramJson);
                    deferred.done(function () {
                        let result ={};
                        result.Result = 0;
                        result.Desc = "打印成功";
                        result.Data ={};
                        callback(result);
                    })
                    .fail(function () {
                        //删除文件
                        deleteFile(path);
                        let result ={};
                        result.Result = 1;
                        result.Desc = "打印失败";
                        callback(result);
                    })
                })
                .fail(function () {
                    //删除文件
                    deleteFile(path);
                    let result ={};
                    result.Result = 1;
                    result.Desc = "打印失败";
                    callback(result);
                })
            }else{
                let result ={};
                result.Result = 1;
                result.Desc = "打印失败";
                callback(result);
            }

        },
		copyfile: function(callback) {
			let paramJson = {
				filename: "D:/123.jpg",
				targetname: "D:/213/345.jpg",
			};
			let sendResult = $.baseBridge.commonDeferred("GWI_Plugin",
				'copyfile', false, paramJson);
			let sendResultJson = JSON.parse(sendResult);

			if(sendResultJson.result == 0) {
				let result = {};
				result.Result = 0;
				result.Desc = "获取设备信息成功";
				result.Bank = "SHRCB";
				callback(result);
			} else {
				let result = {};
				result.Result = 1;
				result.Desc = "失败";
				result.Bank = "SHRCB";
				result.Mac = "";
				callback(result);
			}
		},
		getbase64: function(path) {
			let base64json = {
				filename: path
			};
			let sendResult = $.baseBridge.commonDeferred('GWI_Plugin',
				'readfile64', false, base64json);
			let base64Json = JSON.parse(sendResult);
			return base64Json.data
		},

	};
})(jQuery);

window.addEventListener("click", function(event) {
	console.log(event.target);
	if(event.target.localName == 'input') {
		$.nsdeviceSum.getIME();
	} else {

	}
});
(function() {
	try{
		$('body').append('<script src="http://localhost:8000/CLodopFuncs.js"></' + 'script>');
	}catch(e){}
})();