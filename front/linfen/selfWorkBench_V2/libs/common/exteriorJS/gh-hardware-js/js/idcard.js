function IdCardClass(){this.initlize();}
IdCardClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ServiceName':'IDCardReader310','InstanceName':'','WriteMethod':'AUTO'};
		this.OnlyReadAttrList=['CpBinSize','CpCanCapture','CpCanEject','CpCanReadTrack1','CpCanReadTrack2','CpCanReadTrack3','StMediaStatus',
		'CpCanWriteTrack1','CpCanWriteTrack2','CpCanWriteTrack3','CpSecurity','CpVariant','StBinCount','StBinStatus','StDeviceStatus','StChipStatus'];
		this.EvtList={'CardInserted':[],
                                                                        'EjectOver':[],
                                                                        'CardInvalid':[],
									'CardAccepted':[],
									'CardAcceptCancelled':[],
									'CardTaken':[],
									'AcceptAndReadTracksOver':[{'name':'datas','type':'obj'}],
									'ResetOver':[],
									'StartCtidKJOver':[{'name':'datas','type':'single'}]
								 };
	},
    Reset:function(action){this.execute('Reset',action);},
	Eject:function(outtime){this.execute('Eject',outtime);},
	AcceptAndReadTracks:function(trackid,timeout){if(siu){siu.ControlGuideLightSync(15,g_siu_speed);};this.execute('AcceptAndReadTracks',trackid,timeout);},
	CancelAccept:function(){if(siu){siu.ControlGuideLightSync(15,1);};this.execute('CancelAccept');},
	StartCtidKJ:function(){return this.execute('StartCtidKJ');},
	KillCtidKJ:function(){return this.execute('KillCtidKJ');},
	getApplyDataSync:function(Apply){return this.execute('getApplyDataSync',Apply);},
	getAuthQRCodeDataSync:function(randomNumber,QRCode){return this.execute('getAuthQRCodeDataSync',randomNumber,QRCode);},
    createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});

idcard=new IdCardClass();
idcard.setObjName('idcard');
idcard.loadOcx({clsid:'42BD9D24-7574-499D-A9EC-DA139047C953',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
idcard.createLisener();
idcard.createSpecialLisener();	

if(idcard.isDbg()){
	
		var idcarddemo={};
		idcarddemo.command='',idcarddemo.arg={};
		idcarddemo.asyncval = "";
		idcard.execute=function(cmd){
		var async=true;
		if(cmd != 'OpenConnectionSync' && cmd != 'OpenConnection')
				{
					if(currentdevlist.indexOf(this.name)<0)
						currentdevlist+=this.name+',';
				}
				if(cmd.indexOf('Sync')>0)
					async=false;
					
		switch (cmd){	
			case 'getAttribute':	
				if(arguments[1] == "StDeviceStatus"){
					idcarddemo.asyncval = "HEALTHY";
				}
			case 'setAttribute':	async=false;	break;
			case 'CancelAccept':	idcarddemo.command='CardAcceptCancelled';	break;
			case 'AcceptAndReadTracks':
					
					//idcarddemo.arg='{"track3":{"status":"DATAOK","datas":"Name=LiBin|Sex=woman|Nation=Han|Born=19860626|Address=Beijing XiCheng panzheng street No.7|IDCardNo=43022419850626423X|GrantDept=Beijing Xicheng police|UserLifeBegin=20080731|UserLifeEnd=20180731|PhotoFileName=C:\head.jpg" ,"len":210},"frontimage":{"status":"DATAOK"," datas":"c:\abc.bmp","len":10},"backimage": {"status":"DATAOK"," datas":"c:\abc.bmp","len":10}}';
					idcarddemo.arg='({"chipdata":{"status":"DATAOK","datas":"Name=LiBinff|Sex=woman|Nation=Han|Born=19860626|Address=Beijing XiCheng panzheng street No.7|IDCardNo=43022419850626423X|GrantDept=Beijing Xicheng police|UserLifeBegin=20080731|UserLifeEnd=20180731|PhotoFileName=C:/head.jpg" ,"len":210},"frontimage":{"status":"DATAOK"," datas":"c:/abc.bmp","len":10},"backimage": {"status":"DATAOK"," datas":"c:/abc.bmp","len":10}})';
					idcarddemo.command=cmd+'Over';	
					setTimeout(function(){idcard.onEvent('CardTaken','{}');},3000);
					break;
				default:	idcarddemo.command=cmd+'Over';	break;
		}
		//alert('execute:command='+idcarddemo.command);
		//if(async)	setTimeout("idcard.onEvent(idcarddemo.command,idcarddemo.arg)",100);
		if(async){
			var exestr = "";
			exestr = 'idcard.'+idcarddemo.command;
			if(eval(exestr)){
				exestr += "('"+idcarddemo.arg+"')";
				eval(exestr);
			}else{
				exestr = "idcard.onEvent";
				if(eval(exestr)){
					exestr +="('"+idcarddemo.command+"'";
					if(idcarddemo.arg && idcarddemo.arg.length>0){
						exestr += ",'"+idcarddemo.arg+"'";
					}
					exestr += ")";
					eval(exestr);
				}
			}
		}else{
			return idcarddemo.asyncval;
		}
	}
}