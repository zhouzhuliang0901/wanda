function CameraClass(){this.initlize();}
CameraClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ServiceName':'Camera310','InstanceName':''};
		this.OnlyReadAttrList=['StDeviceStatus'];
		this.EvtList={'MediaInserted':[],
									'SetVideoDisplayOver':[],
									'ResetSyncOver':[],
									'TakePictureOver':[{'name':'cmdName','type':'obj'}],
									'RecordMediaStatusChanged':[],
									'GetIrisTemplateOver':[{'name':'status','type':'single'},{'name':'datalen','type':'single'},{'name':'outdata','type':'single'}],
									'IrisRecognitionOver':[{'name':'status','type':'single'},{'name':'datalen','type':'single'},{'name':'outdata','type':'single'}]
							 };
	},
	ResetSync:function(){return	this.execute('ResetSync');},
	SetVideoDisplay:function(num,action,width,height,x,y){return this.execute('SetVideoDisplay',num,action,width,height,x,y);},
	TakePicture :function(num,path,command){return this.execute('TakePicture',num,path,command);},
	ZoomImage:function(PicturePath,ToPicturePath,ScanSize){this.execute('ZoomImage',PicturePath,ToPicturePath,ScanSize)},
	TakePictureFace:function(num,path,TimeOut){return this.execute('TakePictureFace',num,path,TimeOut);},
	TakePictureLive:function(num,action,path,TimeOut)
	{
		var actionnum = action * 100 + 100 + num;
		return this.execute('TakePictureFace',actionnum,path,TimeOut);
	},
	GetIrisTemplate:function(Camera,Mode,EyePath,Width,Height,X,Y){return this.execute('GetIrisTemplate',Camera,Mode,EyePath,Width,Height,X,Y)},
	IrisRecognition:function(Camera,EyeDataPath,Width,Height,X,Y){return this.execute('IrisRecognition',Camera,EyeDataPath,Width,Height,X,Y)},
	createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});

camera=new CameraClass();
camera.setObjName('camera');
camera.loadOcx({clsid:'5DCCFD23-29F4-418B-824E-4B1ACA5F8599',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
camera.createLisener();
camera.createSpecialLisener();	

if(false){
	var camerademo={};
	camerademo.command='',camerademo.arg={};
	camerademo.asyncval = "";
	camera.execute=function(cmd){
		var async=true;
		switch (cmd){	
			case 'getAttribute':	
				if(arguments[1] == "StDeviceStatus"){
					camerademo.asyncval = "HEALTHY";
				}
			case 'setAttribute':	async=false;	break;
			case 'AcceptAndReadTracks':camerademo.arg='{"poweron":{"datas":"9558880200001851475D49121205929991230","status":"DATAOK"},"track1":{"datas":"111111111111111111","type":"nomatch"},"track2":{"datas":"9558880200001851475D49121205929991230","status":"DATAOK"}}';camerademo.command=cmd+'Over';	break;
			default:	camerademo.command=cmd+'Over';	break;
		}
		
		if(async){
			var exestr = "";
			exestr = 'camera.'+camerademo.command;
			if(eval(exestr)){
				exestr += "('"+camerademo.arg+"')";
				eval(exestr);
				//setTimeout(function(){eval(exestr);},1000);
			}else{
				exestr = "camera.onEvent";
				if(eval(exestr)){
					exestr +="('"+camerademo.command+"'";
					if(camerademo.arg && camerademo.arg.length>0){
						exestr += ",'"+camerademo.arg+"'";
					}
					exestr += ")";
					eval(exestr);
					
					//setTimeout(function(){eval(exestr);},1000);
				}
			}
		}else{
			return camerademo.asyncval;
		}
	}
}