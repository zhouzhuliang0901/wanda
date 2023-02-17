

function DevprocessClass() {this.initlize();};

DevprocessClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ConfigFilePath':'C:/gwixfs/ocx/log.properties'};
		this.OnlyReadAttrList=[];
		this.EvtList={'ReadDevprocessOver':[{'name':'config','type':'obj'}],
				'executeOver':[{'name':'devdata','type':'obj'}],
				'OnReceive':[{'name':'data','type':'single'}],
				'OnNotifyNavigate':[{'name':'urlType','type':'single'}],
				'RobotCommandOver':[{'name':'data','type':'single'}],
				'executeJsonOver':[{'name':'data','type':'single'}]
							 };
	},
	
	/** read the config in devprocess.ini(async)*/
	ReadDevprocess:function(){
		this.execute('ReadDevprocess');
	},
	/**read the config in devprocess.ini(sync)*/
	ReadDevProcessSync:function() {
		return this.execute("ReadDevProcessSync");
	},
	/**record the log to D:\GWI_log\pagelog\*/
	LogWrite:function(loglevel, file, line, func, str) {
		return this.execute("LogWrite", loglevel, file, line, func, str);
	},
	LogAppInfo:function(FunctionName, ErrorCode, ErrorInfo) {
		return this.execute("LogAppInfo", FunctionName, ErrorCode, ErrorInfo);
	},
	/**execute (async)
	 * device: invoker id
	 * command: see below(also valid for ExecSync):
	 * 'readini',
	 * 'writeini',
	 * 'createfolder',
	 * 'deletefolder',
	 * 'checkfile',
	 * 'movefile',
	 * 'deletefile',
	 * 'copyfile',
	 * 'readfile',
	 * 'writefile',
	 * 'getsystime',
	 * 'setsystime',
	 * 'getlocalip',
	 * 'writereg',
	 * 'readreg',
	 * 'writeregarb',
	 * 'readregarb',
	 * 'sleep',
	 * 'readfile64',
	 * 'shutdown',
	 * 'restart',
	 * 'desktop',
	 * 'killprocess',
	 * 'exec',
	 * 'altpicdpi',
	 * 'rotatepic',
	 * 'bmp2sth',
	 * 'screencap',
	 * 'showmainwindow',
	 * 'createeventserver',
	 * 'stopeventserver',
	 * 'clientsendevent',
	 * 'systemsetstate',
	 * 'systeminit',
	 * 'systemfree',
	 * 'systembusy',
	 * 'systemstopservice',
	 * 'systempause',
	 * 'systemtopmost',
	 * 'systemnottopmost',
	 * 'systemmanaging'
	 * 
	 * args: the parameters for command, to refer to docs of XDevProcess.dll.
	 * */
	Exec:function(device, command, args) {
		this.execute("execute", device, command, args);
	},
	/**execute (sync)*/
	ExecSync:function(device, command, args) {
		
			return eval(this.execute("executeSync",device,command,args));
	},
	SystemInitSync: function(args) {// advice:: args:timerflag=disable
		return eval(this.execute("executeSync", "inner", "systeminit", args));
	},
	SystemFreeSync: function(args) {// advice:: args:timerflag=enable
		return eval(this.execute("executeSync", "inner", "systemfree", args));
	},
	SystemBusySync: function(args) {// advice:: args:timerflag=disable
		return eval(this.execute("executeSync", "inner", "systembusy", args));
	},
	SystemStopSync: function(args) {// advice:: args:timerflag=enable
		return eval(this.execute("executeSync", "inner", "systemstopservice", args));
	},
	SystemPauseSync: function(args) {// advice:: args:timerflag=enable
		return eval(this.execute("executeSync", "inner", "systempause", args));
	},
	SystemManagingSync: function(args) {// advice:: args:timerflag=disable
		return eval(this.execute("executeSync", "inner", "systemmanaging", args));
	},
	GetDevInfoSync:function() {
				return eval(this.execute("executeSync", "config", "tojson", "location=currentuser&path=Software\\\\icbc\\\\devInfo&key=devInfo&defaultvalue=0100000004"));
	},
	ZoomImage:function(PicturePath,ToPicturePath,ScanSize,lwidth,lheight,Quality){
				return this.execute("ZoomImageSync",PicturePath,ToPicturePath,ScanSize,lwidth,lheight,Quality);
	},
	OpenVNCSync:function(PassWord){
		//alert(PassWord);
		return this.execute("OpenVNCSync",PassWord);
	},
	CloseVNCSync:function(){
				return this.execute("CloseVNCSync");
	},
	ExecJson:function(command, args) {
		this.execute("ExecuteJson", command, args);
	},
	ExecJsonSync:function(command, args) {
		return eval(this.execute("ExecuteJsonSync", command, args));
	},
	createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});
devprocess=new DevprocessClass();
devprocess.setObjName('devprocess');
devprocess.loadOcx({clsid:'1A171B4F-2C69-4FB7-99B1-9D934839DC27',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
devprocess.createLisener();
devprocess.createSpecialLisener();	

