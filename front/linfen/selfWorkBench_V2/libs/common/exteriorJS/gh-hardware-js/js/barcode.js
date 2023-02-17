function BarcodeClass(){this.initlize();}
BarcodeClass.prototype=(new DeviceClass()).extend({
	initlize:function()
	{
		this.AttrList={'ServiceName':'Barcode310','InstanceName':''};
		this.OnlyReadAttrList=['StDeviceStatus','StDeviceName','StScannerStatus','CpFilterSymbol','CpSymbologies'];
		this.EvtList={'ScanBarcodeOver':[{'name':'Data','type':'obj'}],
									'ResetOver':[],
				'ScanBarcodeCancelled':[]
							 };
	},
	Reset:function(action){	this.execute('Reset');},
	ScanBarcode:function(BarcodeDataMode,TimeOut){this.execute('ScanBarcode',BarcodeDataMode,TimeOut);},
	CancelScan:function(){	this.execute('CancelScan');},
	createSpecialLisener:function(){this.WriteEventScript(this.id,this.name,this.EvtList);}
});

barcode=new BarcodeClass();
barcode.setObjName('barcode');
barcode.loadOcx({clsid:'64D2ACE6-6EAF-4B9C-99CF-1F2A376AABE7',param:[{name:'_Version',value:'120'},{name:'_StockProps',value:'0'}]});
barcode.createLisener();
barcode.createSpecialLisener();	


if(barcode.isDbg()){
		var barcodedemo={};
		barcodedemo.asyncval = '';
		barcodedemo.command='',barcodedemo.arg={};
		
		barcode.execute=function(cmd){
			var async=true;
			if(cmd.substr(cmd.length-4)=='Sync')	async=false;
			switch (cmd){
				case 'getAttribute':	
					if(arguments[1] == "StDeviceStatus"){
						barcodedemo.asyncval = "HEALTHY";
					}
				case 'setAttribute':	
						async=false;	
						break;
				case 'ScanBarcode':	
						barcodedemo.command=cmd+'Over';
						barcodedemo.arg='([{"BarcodeData":"http://weixin.qq.com/r/FfVEXHTEWSkmrTA-96AH","Symbology":"0000"}])';
						break;
				case 'ScanBarcodeCancelled':
						barcodedemo.command='ScanBarcodeCancelled'; break;
				default:	barcodedemo.command=cmd+'Over'; break;
			}
			if(async)
			{
				var exestr = "";
				exestr = 'barcode.'+barcodedemo.command;
				if(eval(exestr)){
					exestr += "('"+barcodedemo.arg+"')";
					eval(exestr);
				}else{
					exestr = "barcode.onEvent";
					if(eval(exestr)){
						exestr +="('"+barcodedemo.command+"','"+barcodedemo.arg+"')";
						eval(exestr);
					}
				}
			}
			else{
				return barcodedemo.asyncval;
			}
			return 0;
	};
}