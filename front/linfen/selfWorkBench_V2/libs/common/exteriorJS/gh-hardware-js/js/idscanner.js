function IdScannerClass(){this.initlize();}
IdScannerClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ServiceName':'','InstanceName':'','WriteMethod':'AUTO'};
		this.OnlyReadAttrList=['CpBinSize','CpCanCapture','CpCanEject','CpCanReadTrack1','CpCanReadTrack2','CpCanReadTrack3','StMediaStatus',
		'CpCanWriteTrack1','CpCanWriteTrack2','CpCanWriteTrack3','CpSecurity','CpVariant','StBinCount','StBinStatus','StDeviceStatus','StChipStatus'];
		this.EvtList={'CardInserted':[],
									'CardAccepted':[],
									'CardInvalid':[],
									'CardAcceptCancelled':[],
									'CardTaken':[],
									'CardCaptured':[],
									'CaptureOver':[],
									'EjectOver':[],
									'AcceptAndReadTracksOver':[{'name':'datas','type':'obj'}],
									'ResetOver':[]
								 };
	},
	Reset:function(action){this.execute('Reset',action);},
	Eject:function(position){this.execute('Eject',position);},
	AcceptAndReadTracks:function(trackid,timeout){this.execute('AcceptAndReadTracks',trackid,timeout);},
	CancelAccept:function(){this.execute('CancelAccept');},
    createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});

idscanner=new IdScannerClass();
idscanner.setObjName('idscanner');
idscanner.loadOcx({clsid:'42BD9D24-7574-499D-A9EC-DA139047C953',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
idscanner.createLisener();
idscanner.createSpecialLisener();	


if(idscanner.isDbg()){
		var idscannerdemo={};
		idscannerdemo.command='',idscannerdemo.arg={};
		idscanner.execute=function(cmd){
		var async=true;
		switch (cmd){	case 'getAttribute':	case 'setAttribute':	async=false;	break;
				case 'AcceptAndReadTracks':idscannerdemo.arg={"track1":{"datas":"111111111111111111","type":"nomatch"},"track2":{"datas":"22222222222222"}};idscannerdemo.command=cmd+'Over';	break;
				default:	idscannerdemo.command=cmd+'Over';	break;
		}
		//alert('execute:command='+idscannerdemo.command);
		//if(async)	setTimeout("idscanner.onEvent(idscannerdemo.command,idscannerdemo.arg)",100);
		if(async){
			var exestr = "";
			exestr = 'idscanner.'+idscannerdemo.command;
			if(eval(exestr)){
				exestr += "('"+idscannerdemo.arg+"')";
				eval(exestr);
			}else{
				exestr = "idscanner.onEvent";
				if(eval(exestr)){
					exestr +="('"+idscannerdemo.command+"','"+idscannerdemo.arg+"')";
					eval(exestr);
				}
			}
		}
		
		return 0;
	}
}