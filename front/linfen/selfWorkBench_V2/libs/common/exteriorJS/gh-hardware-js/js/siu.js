function SiuClass(){this.initlize();}
SiuClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ServiceName':"SIU310",'InstanceName':''};
		this.OnlyReadAttrList=['ProximityStatus'];
		this.EvtList={
									'GetSensorStatusOver':[{'name':'Status','type':'single'}],
									'UPSGetStatusOver':[{'name':'Status','type':'single'}],
									'PortStatusChanged':[{'name':'PortType','type':'single'},{'name':'PortIndex','type':'single'},{'name':'PortStatus','type':'single'}]
							 };
	},
	GetSensorStatusSync:function(){ return this.execute('GetSensorStatusSync');},
	GetSIUStatusSync:function(PortType,PortIndex){return	this.execute('GetSIUStatusSync',PortType,PortIndex);},
	UPSGetStatusSync:function(){return this.execute('UPSGetStatusSync');},
	CloseAllSync:function(){return	this.execute('CloseAllSync');},
	SetSIUEventSync:function(EventType,EventIndex,Value){return	this.execute('SetSIUEventSync',EventType,EventIndex,Value);},
	ControlGuideLightSync :function(wGuideLight,fwCommand){return	this.execute('ControlGuideLightSync',wGuideLight,fwCommand);},
	ControlIndicatorSync :function(Index,fwCommand){return	this.execute('ControlIndicatorSync',Index,fwCommand);},
	ControlFasciaLightSync:function(fwCommand){return	this.execute('ControlFasciaLightSync',fwCommand);},
	ControlRemoteStatusLightSync:function(fwCommand){return	this.execute('ControlRemoteStatusLightSync',fwCommand);},
	ResetSync:function(){return this.execute('ResetSync');},
	UPSPowerDownSync:function(){return this.execute('UPSPowerDownSync');},
	UPSPowerUpSync:function(){return this.execute('UPSPowerUpSync');},
	createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});

siu=new SiuClass();
siu.setObjName('siu');
siu.loadOcx({clsid:'FD64E37E-0119-49CC-B69F-32AA64C1BC93',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
siu.createLisener();
siu.createSpecialLisener();
siu.setAttribute('ServiceName',siu.AttrList.ServiceName);

if(siu.isDbg()){
		var siudemo={};
		siudemo.command='',siudemo.arg={};
		siu.execute=function(cmd){
			//alert(cmd+arguments[1]);
		var async=true;
		if(cmd.substr(cmd.length-4)=='Sync')	async=false;
		switch (cmd){
		        case 'getAttribute':
		        	if(arguments[1] == "StDeviceStatus"){
		        		siudemo.asyncval = "HEALTHY";
					}
		        case 'setAttribute':	
		        	async=false;	break;
				default:	
					siudemo.command=cmd+'Over';	break;
		}
		//if(async)	setTimeout("siu.onEvent(siudemo.command,siudemo.arg)",100);		
		//return 0;
		if(async){
			var exestr = "";
			exestr = 'siu.'+siudemo.command;
			if(eval(exestr)){
				exestr += "('"+siudemo.arg+"')";
				eval(exestr);
			}else{
				exestr = "siu.onEvent";
				if(eval(exestr)){
					exestr +="('"+siudemo.command+"'";
					if(siudemo.arg && siudemo.arg.length>0){
						exestr += ",'"+siudemo.arg+"'";
					}
					exestr += ")";
					eval(exestr);
				}
			}
		}else{
			return siudemo.asyncval;
		}
	}
}