// 获取当前根目录
function getRootPath_web() {
     //获取当前网址
     var curWwwPath = window.document.location.href;
     //获取主机地址之后的目录
     var pathName = window.document.location.pathname;
     var pos = curWwwPath.indexOf(pathName);
     //获取主机地址
     var localhostPaht = curWwwPath.substring(0, pos);
     //获取带"/"的项目名
     var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
     return (localhostPaht + projectName);
}  

// 获取url传的参数
function getRequest() {
   var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
      }
   }
   return theRequest;
}

// 时间转换
function setDateTime (timestamp ) {
	// 时间存在的场合
	if (timestamp != '') {
	     var datetime = new Date();
	     datetime.setTime(timestamp);
	     var year = datetime.getFullYear();
	     var month = datetime.getMonth() + 1;
	     var date = datetime.getDate();
	     return year + "-" + month + "-" + date;
	// 不存在的场合
	} else {
		return '';
	}
 }

// 页面点击标志位
var bodyClickFlag = false;
// 页面被点击
$("body").click(function (){
	bodyClickFlag = true;
});

// 页面倒计时
function timeCount (second) {
	// 页面被点击的场合
	if (bodyClickFlag) {
		second = 70;
	}
	// 标志位回复
	bodyClickFlag = false;
	second --; 
	
	// 少于一分钟的场合
	if (second < 60) {
		$("#timeMark").html(second);
	} else {
		$("#timeMark").html("");
	}
	// 页面跳转
	if (second <= 0) { 
		location.href="index.html"; 
	}; 
	
	setTimeout("timeCount("+second+")",1000); 
}


var scrollFunc=function(e){ 
  e=e || window.event; 
  if(e.wheelDelta && event.ctrlKey){//IE/Opera/Chrome 
	  event.returnValue=false;
  }else if(e.detail){//Firefox 
	  event.returnValue=false; 
  } 
 }  
	  
 //注册事件 
 if(document.addEventListener){ 
	 document.addEventListener('DOMMouseScroll',scrollFunc,false); 
 }
 //IE/Opera/Chrome/Safari
 window.onmousewheel=document.onmousewheel=scrollFunc;