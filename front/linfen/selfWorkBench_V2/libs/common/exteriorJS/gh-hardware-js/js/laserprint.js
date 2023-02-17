function LaserprintClass(){this.initlize();}




LaserprintClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ServiceName':'','Binnum':'0','InstanceName':''};
		this.OnlyReadAttrList=['StMedia','StDeviceStatus','StPaper','StToner','StInk'];
		this.EvtList={'MediaInserted':[],
									'MediaTaken':[],
									'RetractBinThreshHold':[{'name':'binStatus','type':'single'}],
									'PaperThreshHold':[{'name':'paperStaus','type':'single'}],
									'TonerThreshHold':[{'name':'tonerStaus','type':'single'}],
									'ResetOver':[],
									'PrintTextOver':[],
									'WaitCancelled':[{'name':'cmdName','type':'single'}],
									'ExtendCommandOver':[{'name':'lpstrOutString','type':'obj'}],
									'PrintRawFileOver':[],
									'SetRawDataOver':[],
									'ControlMediaOver':[{'name':'mediaCtrol','type':'single'}],
									'MediaPresented':[{'name':'paperinfo','type':'obj'} ]
							 };

		this.__PAGE_FORMATS = {
			'1' : {'height' : '9.7CM'}, 
			'2' : {'height' : '20CM'}, 
			'3' : {'height' : '14CM'}, 
			'4' : {'height' : '28.5CM'}, 
			'5' : {'height' : '28.5CM'}, 
			'6' : {'height' : '28.5CM'},
			'7' : {'height' : '28.5CM'},
			'8' : {'height' : '28.5CM'}, 
			'9' : {'height' : '28.5CM'}
		};
		this.__page_formats = this.__PAGE_FORMATS;

		this.__cur_all_file_list = new Array();// to backup the current file list to print, including all cfg and file path
		this.__cur_paper_num = 0;// need to print paper count.
	},

	insertPageFormat: function (page_id, page_format) {
		this.__page_formats[page_id] = page_format;
	},

	deletePageFormat: function (page_id) {
		return delete this.__page_formats[page_id];
	},

	clearPageFormats: function () {
		this.__page_formats = {};
	},

	resetPageFormats: function() {
		this.__page_formats = this.__PAGE_FORMATS;
	},

	mergePageFormats: function(page_formats) {
		for(id in page_formats) {
			this.__page_formats[id] = page_formats[id];
		}	
	},

	getPageFormats: function() {
		return this.__page_formats;
	},

	clearAllFileList: function() {
		this.__cur_all_file_list = new Array();// to backup the current file list to print, including all cfg and file path
		this.__cur_paper_num = 0;// need to print paper count.
	},
	
	BackupPrint: function() {
		var thisObj = this;
		
		this.portion.rgr(this);
			
		var printedPaper = thisObj.CheckPaperCountSync();// the printed paper out
		var paperCount = 0;
		var allFileList = thisObj.__cur_all_file_list;

		if(allFileList && allFileList.length > 0) {// enter the backup print routine.
			
			// 1.remeber to shrink the printed page out first!!!
			for(var curIdx =0; curIdx < allFileList.length; curIdx++) {
				if(printedPaper < (paperCount += allFileList[curIdx].cfg.PaperNum)) {// shrink the files array, rebuild the allFileList.
					var curFilePrinted = paperCount - printedPaper;
					var curType = allFileList.shift();
					if(0 == curType.cfg.BkIdx) {
						curType.cfg.BkIdx++;
					} else if(!curType.cfg.BkIdx) {// undefined or null 
						curType.cfg.BkIdx = 1;
					} else {
						curType.cfg.BkIdx++;
					}
					
					if(curType.cfg.BkIdx >= curType.cfg.PrintType.length) {// no backup box available
						return 0;	
					}
					curType.cfg.PaperNum = curType.cfg.PaperNum - curFilePrinted;
					curType.files = curType.files.splice(0, curFilePrinted);// shrink the first printed files.
					allFileList.unshift(curType);// unshift the modified elements to the allFileList
					break;
				} else {// remove the printed type.
					allFileList.shift();
				}
			}
			
			
			// 2.reflush the file to print to sp.

			for(var curIdx =0; curIdx < allFileList.length; curIdx++) {
				if("multifile" != allFileList[curIdx].cfg.FileMode) {
					concatAndFlush(allFileList);
				} else {
					// just to flush the file, not to concat it
					justToFlush(allFileList);
				}
			}
			
			// 3.to invoke the ControlMedia to start print job.
			thisObj.__cur_paper_num -= printedPaper;
			thisObj.ControlMedia(thisObj.__cur_paper_num, 1);
			return 1;
		} else {// just to indicate the app to quit the print routine.
			return 0;
		}
	},

	
	
	UpdatePrintConfigSync:function(cfg){return !this.execute('UpdatePrintConfigSync', cfg);},

	SetRawData:function(cfg, files) {
		/*if(watermarkflag == false) {
			//alert("watermarkflag false");
			try{
				this.execute('OpenLineAndBoost');
			}catch(e){			
			}
		}
		else {
			//alert("watermarkflag true");
			try{				
				this.execute('CloseLineAndBoost');
				watermarkflag = false;
			}catch(e){			
			}
		}	
		*/
		var thisObj = this;
		var paperNum = 0;

		var allFileList = new Array();
		
		this.portion.rgr(this);
		
		try {
			if(thisObj.portion.is(cfg.PrintType, "Array")) {// contains backup print routine
				if(!cfg.BkIdx && 0 != cfg.BkIdx) {// to set the backup print type index to zero
					cfg.BkIdx = 0;
				}
				thisObj.portion.top_setLaserprintHooks();
			}
			
			if('file' == cfg.Mode) {
				paperNum = files.length;
				if(true == cfg.MultiPage) {
					paperNum = cfg.PaperNum;
				}
				
				cfg.PaperNum = paperNum;

				thisObj.portion.registerBatchCfg(allFileList, cfg);
					
				thisObj.portion.cacheFileNameList(allFileList, files);

				if("multifile" != cfg.FileMode) {
					thisObj.portion.concatAndFlush(allFileList);
					
				} else {
					thisObj.portion.justToFlush(allFileList);	
				}
				
			} else {
				
				var startTag = "<html>";
				var endTag = "</html>";
				var spliterLen = startTag.length;
				var insertT1 = "<table height='20px'><tr><td></td></tr></table>";
				var insertT2 = "<table height='37px'><tr><td></td></tr></table>";
				var offset = 1;
				var paperCount = 0;
				
				var allSinglePaperNum = 0;

				var allPrintData = new Array();
				for(var i = 0; i < files.length; i++) {
					var ar = files[i].split(endTag, -1);
					ar.pop();
					var size = ar.length;
					allSinglePaperNum += size;
					allPrintData = allPrintData.concat(ar);
				}
				

				var curPrintType = (thisObj.portion.is(cfg.PrintType, "Array") ? cfg.PrintType[cfg.BkIdx] : cfg.PrintType);
				if(4 == curPrintType) {
					offset = 3;
					paperNum = Math.floor((allSinglePaperNum) / 3) + ((0 == (allSinglePaperNum) % 3) ? 0:1);
					cfg.PaperNum = paperNum - ((0 == (allSinglePaperNum) % 3) ? 0:1);
					
					if(cfg.PaperNum > 0) {
						thisObj.portion.registerBatchCfg(allFileList, cfg);
					}
					
				} else {
					offset = 1;
					paperNum = allSinglePaperNum;
					cfg.PaperNum = paperNum;
					
					thisObj.portion.registerBatchCfg(allFileList, cfg);
				}
				
				for(var index = 0; index < allSinglePaperNum; index += offset) {
					if(1 == offset) {
						paperCount++;
						
						thisObj.portion.cacheFileList(allFileList, paperCount, allPrintData[index] + endTag);
						
					} else if(3 == offset) {
						curPrintType = 5;
						
						var combined  = allPrintData[index];
						
						if ((index + 1 < allSinglePaperNum) && null != allPrintData[index+1]
						&& allPrintData[index+1].length >= spliterLen ) {
							combined  += insertT1 + allPrintData[index+1].substring(allPrintData[index+1].indexOf(startTag) + spliterLen);
							curPrintType = 6;
						}
						
						if ((index + 2 < allSinglePaperNum) && null != allPrintData[index+2]
						&& allPrintData[index+2].length >= spliterLen ) {
							combined  += insertT2 + allPrintData[index+2].substring(allPrintData[index+2].indexOf(startTag) + spliterLen);
							curPrintType = 4;
						}
						
						combined += endTag;
						
						if(5 == curPrintType || 6 == curPrintType) {
							
							cfg.PaperNum = 1;
							
							if(thisObj.portion.is(cfg.PrintType, "Array")) {
								cfg.PrintType[BkIdx] = 	curPrintType;
								cfg.PrintType[BkIdx+1] = curPrintType + 5;// hard encode 10, 11, no other way?
							} else {
								cfg.PrintType = curPrintType;
							}
							thisObj.portion.registerBatchCfg(allFileList, cfg);
						}
						paperCount++;
						
						thisObj.portion.cacheFileList(allFileList, paperCount, combined);
					}
				}

				if("multifile" != cfg.FileMode) {
					thisObj.portion.concatAndFlush(allFileList);
				} else {
					thisObj.portion.justToFlush(allFileList);
				}
			}
		} catch(e) {
			paperNum = 0;
		}
		
		if(thisObj.portion.is(cfg.PrintType, "Array")) {
			thisObj.__cur_paper_num += paperNum;
			thisObj.__cur_all_file_list = thisObj.__cur_all_file_list.concat(allFileList);// to record the file list to laserprint member variable
		}
		
		return paperNum;

	},
	ControlMedia:function(paperNum, mediaCtrol) {
		if(0==paperNum){
			this.execute('ControlMedia', 0x20);
			return;
		}
		if(1 == mediaCtrol) {
			try {
				siu.ControlGuideLightSync(10,8);
			} catch(ex) {
			}

			this.execute('SetRawData','WaitNum=' + paperNum + ';');
			
			this.execute('ControlMedia', mediaCtrol);
		} else {
            if(32 == mediaCtrol) {
            	if(siu){siu.ControlGuideLightSync(10,1);};
            }
			this.execute('ControlMedia', mediaCtrol);
                        
		}
		
	},
	ControlMedia_smart:function(mediaCtrol) {
		if(1 == mediaCtrol) {
			try {
				siu.ControlGuideLightSync(10,8);
			} catch(ex) {
			}			
			this.execute('ControlMedia', mediaCtrol);
		} else {
            if(32 == mediaCtrol) {
            	if(siu){siu.ControlGuideLightSync(10,1);};
            }
			this.execute('ControlMedia', mediaCtrol); 
		}		
	},
	CheckPaperCountSync:function(){return this.execute('CheckPaperCountSync');},
	
	
	Reset:function(action){	return !this.execute('Reset',action,this.AttrList.Binnum);},
	PrintRawFile:function(bstFileName){if(siu){siu.ControlGuideLightSync(10,g_siu_speed);};	this.execute('PrintRawFile',bstFileName);},
	PrintText:function(prtText){if(siu){siu.ControlGuideLightSync(10,g_siu_speed);};this.execute('PrintText',prtText);},
    CloseLineAndBoost:function(){this.execute('CloseLineAndBoost');},
	OpenLineAndBoost:function(){this.execute('OpenLineAndBoost');},
	functionExist:function(functionName){
		if(typeof(this.obj[functionName])=='undefined'){
			//alert(this.obj+'.'+FunctionName+' undefined!');
			return false;
		}else{
			return true;
		}
	},
	ExtendCommand:function(lpstrExtendString){	this.execute("ExtendCommand", lpstrExtendString);},
	CancelWait:function(){this.execute('CancelWait');},
	GetPrintType:function(type){return this.execute('GetPrintType',type);},
	createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});

laserprint=new LaserprintClass();
laserprint.setObjName('laserprint');
laserprint.loadOcx({clsid:'A154B77C-416A-4D66-8710-127AABFD9346',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
laserprint.createLisener();
laserprint.createSpecialLisener();	

 

LaserprintClass.prototype.portion = {
	
	
	rgr: function(exObj) {
		this.__exObj = exObj;	
	},

	is: function(obj,type) {
		return (type === "Null" && obj === null) || 
			(type === "Undefined" && obj === void 0 ) || 
			(type === "Number" && isFinite(obj)) || 
			Object.prototype.toString.call(obj).slice(8,-1) === type;
	},

	genCfgStr: function (cfg) {
		var thisObj = this;
		var c = '';
		if(!cfg.BkIdx && 0 != cfg.BkIdx) {// to set the backup print type index to zero
			cfg.BkIdx = 0;
		}

		for(elem in cfg) {
			if('Mode' != elem && 'MultiPage' != elem && 'FileMode' != elem && 'BkIdx' != elem) {
				if('PrintType' == elem) {
					c += (elem + "=" + (thisObj.is(cfg.PrintType, "Array") ? cfg.PrintType[cfg.BkIdx] : cfg.PrintType) + ";");
				} else {
					c += (elem +"=" + cfg[elem] + ";");
				}
			}
		}
		return c;
	},
		
	getLogTime: function(){
		
		Date.prototype.Format = function(formatStr)    
		{    
		    var str = formatStr;    
		    var Week = ['SUN','MON','TUE','WED','THU','FRI','SAT'];   
		    str=str.replace(/yyyy|YYYY/,this.getFullYear());    
		    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));    
		    str=str.replace(/MM/,(this.getMonth() + 1)>9?(this.getMonth() + 1).toString():'0' + (this.getMonth() + 1));    
		    str=str.replace(/M/g,(this.getMonth() + 1));
		    str=str.replace(/w|W/g,Week[this.getDay()]);    
		    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());    
		    str=str.replace(/d|D/g,this.getDate());    
		    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());    
		    str=str.replace(/h|H/g,this.getHours());    
		    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());    
		    str=str.replace(/m/g,this.getMinutes());    
		    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());    
		    str=str.replace(/s|S/g,this.getSeconds()); 
		    return str;    
		};

		var str="yyyyMMddHHmmss";
		var now = new Date().Format(str);
		return now;
	},
		
	genRandomFileName: function(seq) {
		var file = "";
		var path = "C:\\\\ssts\\\\app\\\\sp\\\\report\\\\";
		var suffix = ".html";
		file += (path + "report_" + this.getLogTime() + "_" + seq + suffix);
		return file;
	},
		
		
	registerBatchCfg: function(fileList, cfg) {
		var bactchCfg = {PaperNum:0,PrintType:0,Stamp:1};
		bactchCfg.PaperNum = cfg.PaperNum;
		bactchCfg.PrintType = cfg.PrintType;
		bactchCfg.Stamp = cfg.Stamp;
		fileList.push({"cfg" : bactchCfg, "files" : []});
	},
	
	savePrintBuffer: function(paperCount, buffer) {
		var fileName = this.genRandomFileName(paperCount);
		this.__exObj.execute('SaveFileDataSync', fileName, buffer);
		this.__exObj.execute('SetRawData', "File[0]=" + fileName);
	},

	cacheFileList: function(fileList, paperCount, buffer) {
		var fileName = this.genRandomFileName(paperCount);
		this.__exObj.execute('SaveFileDataSync', fileName, buffer);
		
		fileList[fileList.length -1].files.push(fileName);
	},

	cacheFileNameList: function(fileList, files) {
		for(var i = 0; i < files.length; i++) {
			fileList[fileList.length -1].files.push(files[i]);
		}
	},

	concatAndFlush: function(fileList) {
		var thisObj = this;
		for(var i = 0; i < fileList.length; i++) {
			var type = fileList[i];
			thisObj.__exObj.execute('SetRawData',thisObj.genCfgStr(type.cfg));
			
			thisObj.concatFiles(type);
		}
	},

	justToFlush: function(fileList) {
		var thisObj = this;
		for(var i = 0; i < fileList.length; i++) {
			var type = fileList[i];
			thisObj.__exObj.execute('SetRawData',thisObj.genCfgStr(type.cfg));
			
			for(var j = 0; j < type.files.length; j++) {
				thisObj.__exObj.execute('SetRawData','File[0]=' + type.files[j]);
			}	
		}
	},

	concatFiles: function(type) {
		
		var FORMAT = this.__exObj.__page_formats;
		
		var insertT3 = "<table height='0.00CM'><tr><td>&nbsp;</td></tr></table>";
	
		var page = '';
		
		var pageStart = '<html>\\n' + 
						'<head>\\n' + 
						'<meta http-equiv="Content-Type" content="text/html; charset=GB2312" />\\n' +
						'<style type="text/css" media="print">\\n' + 
						'body {border:NONE; padding:0px; margin:0px;}\\n' + 
						'.PageEnd { PAGE-BREAK-AFTER: always;}\\n' +
						'</style>\\n' + 
						'</head>\\n' +
						'<body>\\n';
		
		var pageEnd = '</body></html>';
		
		var divStartBreak = '<div class="PageEnd">\\n';
		
		var divStart = '<div style="overflow:hidden; height:'+ ((FORMAT[type.cfg.PrintType]) ? FORMAT[type.cfg.PrintType].height : FORMAT['2'].height) + ';">\\n';
		
		var divEnd = '</div>\\n';
		
		page += pageStart;
		
		
		for(var index = 0; index < type.files.length; index++) {
			var iframe = '<iframe id="' + '" width="100%" style="height:' + ((FORMAT[type.cfg.PrintType]) ? FORMAT[type.cfg.PrintType].height : FORMAT['2'].height) + '" frameborder="0" scrolling="no" src="' + (type.files[index]) + '"></iframe>\\n';
			
			if(0 != index) {
				
			}
			if(type.files.length - 1 == index) {
				page += divStart;
			} else {
				page += divStartBreak;
			}
			
			page += iframe;
			page += divEnd;
		}
		
		page += pageEnd;
		this.savePrintBuffer("type_" + type.cfg.PrintType, page);
	},
	
	top_paperOutHook: function(param) {// the hook to handle the paper out event.
		return this.__exObj.BackupPrint();
	},

	top_DeviceErrorHook: function(param) {// the hook to handle the paper out event.
		this.__exObj.clearAllFileList();
	},
	
	top_ControlMediaOverHook: function(param) {
		this.__exObj.clearAllFileList();
	},
	
	
	__bHookSet: false,
	
	top_setLaserprintHooks: function() {
		var thisObj = this;
		
		if(thisObj.__bHookSet) {
			return;
		}
		var pageOnEvent = thisObj.__exObj.onEvent;
	
		thisObj.__exObj.onEvent = function(evt, args) {
			var param = eval(args);                                                                      
			switch (evt) {                                                                                                                                                                   
			case 'ControlMediaOver':
				thisObj.top_ControlMediaOverHook(param);                                                           
				break;
			case 'DeviceError':
				if((-123 == param.errorcode || -102 == param.errorcode) && thisObj.top_paperOutHook(param)) {// if paperout and exist backup print handle, to handle it
					return;
				}
				thisObj.top_DeviceErrorHook(param);
				break;                                                                                 
			default:                                                                                     
				break;                                                                                     
			}
			if(pageOnEvent) {// go on to execute the page event 
				pageOnEvent(evt, args);
			}
		}	
		thisObj.__bHookSet = true;
	}
};


if(laserprint.isDbg()){
	laserprint.execute = function(func) {
		var async = true;
		var laserprintdemo = {};
		laserprintdemo.func = func + "Over";
		laserprintdemo.args = {'result' : 0, 'errorcode' : 0, 'cmdName' : func};
		if(func.indexOf('Sync', -1) >= 0) {
			async = false;
		}
		switch(func) {
		case "OpenConnection":
			break;
		case "CloseConnection":
			break;
		case "Reset":
			break;
		case "ControlMedia":
			break;
		case "UpdatePrintConfigSync":
			async = false;
			laserprintdemo.args = true;
			break;
		case "ResetSync":
			async = false;
			break;
		case "SetRawData":
			break;
		case "CheckPaperCountSync":
			async = false;
			laserprintdemo.args = 999;
			break;
		case "getAttribute":
			async = false;
			if("ServiceName" == arguments[1]) {
				laserprintdemo.args = "HtmPrinter310";
			} else if("StDeviceStatus" == arguments[1]) {
				laserprintdemo.args = "HEALTHY";
			} else if("StDeviceExStatus" == arguments[1]) {
				laserprintdemo.args = "HEALTHY|HEALTHY|ExStatus";
			} else if("StMedia" == arguments[1]) {
				laserprintdemo.args = "NOTPRESENT";
			} else if("StPaperEx" == arguments[1]) {
				laserprintdemo.args = "PAPERFULL|PAPERFULL|PAPERFULL|PAPERFULL";
			} else if("StToner" == arguments[1]) {
				laserprintdemo.args = "TONERFULL";
			} else if("StInk" == arguments[1]) {
				laserprintdemo.args = "INKFULL";
			}
			break;
		case "setAttribute":
			async = false;
			laserprintdemo.args = true;
			break;
		default:
			break;
		}
		if (async) {
			setTimeout(function() {laserprint.onEvent(laserprintdemo.func,laserprintdemo.args);}, 1000);		
		} else {
			return laserprintdemo.args;
		}
		return 0;
	}
}