function HighcameraClass(){this.initlize();}
HighcameraClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ServiceName':'','InstanceName':'','wStartPosX':'','wStartPosY':'',
			'wWindowWidth':'','wWindowHeigh':''};
		this.OnlyReadAttrList=['StDeviceStatus'];
		this.EvtList={
									'StartTakePictureOver':[],
									'PauseTakePictureOver':[],
									'ResumeTakePictureOver':[],
									'StopTakePictureOver':[]
							 };
	},
	StartTakePicture:function(){if(siu){siu.ControlGuideLightSync(3,g_siu_speed);};this.execute('StartTakePicture');},
	GetPictureSync:function(pathname,wScanSize){return this.execute('GetPictureSync',pathname,wScanSize);},
	PauseTakePicture:function(){this.execute('PauseTakePicture');},
	ResumeTakePicture:function(){this.execute('ResumeTakePicture');},
	StopTakePicture:function(){this.execute('StopTakePicture');},
	createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});

highcamera=new HighcameraClass();
highcamera.setObjName('highcamera');
highcamera.loadOcx({clsid:'595E7B74-CE88-40A0-8FB2-02C431A45B62',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
highcamera.createLisener();
highcamera.createSpecialLisener();	

if(false){
		var highcamerademo={};
		highcamerademo.command='',highcamerademo.arg={};
		highcamerademo.execute=function(cmd){
		var async=true;
		switch (cmd){	case 'getAttribute':	case 'setAttribute':	async=false;	break;
				default:	highcamerademo.command=cmd+'Over';	break;
		}
		if(async)	setTimeout("highcamerademo.onEvent(highcamerademo.command,highcamerademo.arg)",100);		
		return 0;
	}
}