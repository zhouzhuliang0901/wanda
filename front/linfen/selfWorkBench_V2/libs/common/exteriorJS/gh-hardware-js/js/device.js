Object.extend=function($,_){
		for(o in _)$[o]=_[o];
		return $
};

Global=new Object();
Global.onRTLError=function(_,$){alert("RTL Error:\nErrMsg: "+_+"\nErrMsgSource: "+$)};
Global.isEnv=function($){
	if(navigator.appName.indexOf("Microsoft")!=-1){
		if($=="Microsoft"||$=="IE")return true;else return false}
		else if(navigator.appName.indexOf("Netscape")!=-1){
			if($=="Netscape"||$=="NN")return true;
			else return false
		}else if(navigator.appName.indexOf($)!=-1)return true;
		else return false
};
window.onerror=function($,A,_){Global.onRTLError($,"[window.onerror] URL:"+A+" Line:"+_)};

var currentdevlist = '';
function DeviceClass(){this.init()}
DeviceClass.prototype={
	extend:function(o)
	{
		return Object.extend.apply(this,[this,o]);
	},
	init:function(){
		this.__isIE=navigator.appName.indexOf("Microsoft")!=-1;
		this.__isNN=navigator.appName.indexOf("Netscape")!=-1;
		this.id='OCX';
		this.name='';
		this.ParameterList={};	
		this.AttrList={'ServiceName':'','InstanceName':''};	
		this.BaseEvtList={'LockDeviceOver':[],
									'UnLockDeviceOver':[],
									'OpenConnectionOver':[],
									'CloseConnectionOver':[],
									'Timeout':[{'name':'cmdName','type':'single'}],	
									'StatusChanged':[{'name':'newStatus','type':'single'}],
									'DeviceError':[{'name':'cmdName','type':'single'},{'name':'errorcode','type':'single'}],
									'ErrorInfoReceived':[{'name':'errorinfo','type':'obj'}]																										
								 };
	},
	getObjName:function(){	return this.name;},
	setObjName:function(n){	this.name=n;	this.id=this.name+'OCX';},
	getAttribute:function(A){
		var attr = eval('this.obj.'+A);
    
		if(attr!=undefined)			return attr;
		else	alert(this.obj+'.'+A+' undefined!');
	},
	setAttribute:function(A,V){		
		if(this.obj[A]!=undefined)	this.obj[A]=V;
		else	alert(this.obj+'.'+A+' undefined!');	
	},
	SetParameterList:function(v){	for(a in v)	if(a in this.ParameterList) this.ParameterList[a]=v[a];	},
	SetDefaultAttributeList:function(v){	for(a in v)	if(a in this.obj) this.obj[a]=v[a];	},	
	LockDevice:function(timeout){		this.execute('LockDevice',timeout);	},
	UnlockDevice:function(){		this.execute('LockDevice');},
	OpenConnection:function(device,timeout){this.execute('setAttribute','ServiceName',device);return this.execute('OpenConnection',timeout);},
	
	CloseConnection:function(){	this.execute('CloseConnection');},
	execute:function(command){
		var r;
		if(this.obj==null || this.obj=='undefined'){alert('obj undefined!'); return -1;}
		if(command.length<1){alert('execute call no right!'); return -1;}
		if(command=='getAttribute'){return this.getAttribute(arguments[1]);}
		if(command=='setAttribute'){this.setAttribute(arguments[1],arguments[2]);return 0;}
		if(typeof(this.obj[command])=='undefined'){alert(this.obj+'.'+command+' undefined!');}
		else{	
			try{
				var funcstr='this.obj[command]'+'(';
				for(var i=1;i<arguments.length;i++){ 
					var arg=arguments[i];
					if(typeof(arg)=='string')	
					{
						var restr=/\"/g;
						arg=arg.replace(restr,"'");						
						arg='"'+arg+'"';
					}
					else
					{
						arg = 'arguments['+i+']';
					}
					if(i==1)	funcstr+=arg;
					else	funcstr+=','+arg;	
				}
				funcstr+=')';
				if(command != 'OpenConnectionSync' && command != 'OpenConnection')	//
				{
					if(currentdevlist.indexOf(this.name)<0)
						currentdevlist+=this.name+',';
						//alert('currentdevlist='+currentdevlist);
				}
				
				if((!this.obj['Active']||this.obj['Active']=="undefined") && (command!='OpenConnectionSync' && command!='OpenConnection')){
					r=this.obj['OpenConnectionSync'](3000);
					if(r!=0){	if(command.substr(command.length-4)!='Sync') eval(this.name+'.onEvent(\'DeviceError\',{"cmdName":command,"errorcode":r})'); return r;}		
				}
				r=eval(funcstr);

				if(typeof(r)!='string' && command.substr(command.length-4)!='Sync' && r!=0){eval(this.name+'.onEvent(\'DeviceError\',{"cmdName":command,"errorcode":r})');}
			}catch(ex){alert('device: '+this.name+' command: '+command+' error: '+ex.message);}
		}
		return r;
	},
	loadOcx:function(p)
	{
		var s=eval(p);		
		var $='<object classid=\"clsid:'+s.clsid+'\" id=\"'+this.id+'\" width=\"0\" height=\"0\">\n';
		for(var i=0;i<s.param.length;i++){ var a=eval(s.param[i]); $+='<param name=\"'+a.name+'\" value=\"'+a.value+'\">';}
		$+='</object>\n'; document.write($); this.obj=document[this.id];
	},
	updateStlye:function(st)
	{
		//for(s in st){this.obj.style[s]=st[s];}
		
		var s=eval(st);	
		this.obj.style.width=s.width;           
		this.obj.style.height=s.height;               
		this.obj.style.left=s.left;              
		this.obj.style.top=s.top;             
		this.obj.style.position="absolute";

	},
	updateOcxStlye:function(st)
	{
		
		var s=eval(st);	
		this.obj.width=s.width;           
		this.obj.height=s.height;               
		this.obj.hspace=s.left;              
		this.obj.vspace=s.top;             
		this.obj.style.position="absolute";
		
	},
	createLisener:function(){ this.WriteEventScript(this.id,this.name,this.BaseEvtList);},
	WriteEventScript:function(id,n,s)
	{
		var str='';
		for(a in s)
		{
			var c='(',v='{',flag=true,dot;
			for(b=0;b<s[a].length;b++)
			{
				if(b==0)	dot='';
				else	dot=',';	
				c+=dot+s[a][b].name;		
				if(s[a][b].type=='single')
				{
					v+=dot+'\''+s[a][b].name+'\':'+s[a][b].name;
				}
				else if(s[a][b].type=='obj')
				{
					if(s[a].length==1){v=s[a][b].name;	flag=false;}
					else	v+=dot+'\''+s[a][b].name+'\':'+s[a][b].name;
				}
				else{	alert(s[a][b].name+' not support type:'+s[a][b].type);}
			}
			c+=')';			if(flag)	v+='}';
			str+='<SCRIPT FOR=\"'+id+'\" EVENT=\"'+a+c+'\">\n';
			str+='try{'+n+'.onEvent(\''+a+'\','+v+');}\ncatch(ex){'+n+'.onEvent=function(){alert(ex.message)}}\n';
			str+='</SCRIPT>\n';				
		}
		document.write(str);
	},
	onEvent:function(cmd,args){/*	alert('cmd='+cmd+' args='+args);*/}
}
DeviceClass.prototype.__dbg = false;
DeviceClass.prototype.setDbg = function(dbgFlg) {
	this.__dbg = dbgFlg;
};

DeviceClass.prototype.isDbg = function() {
	return this.__dbg;
};

// var debug=false;
var g_siu_speed=4;
var g_pboc_path = "C:\\gwixfs\\ocx\\";
