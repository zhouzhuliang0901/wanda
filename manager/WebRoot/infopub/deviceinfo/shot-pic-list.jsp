<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot) %>';</script>
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/html5.js"></script>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/respond.min.js"></script>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css"
    href="<%=webRoot%>/sms/lib/viewer/css/viewer.min.css" />
 <style>
 .viewer-close{
    display:none;
 }
 </style>
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>设备管理 </title>
</head>
<body  >
     <ul id="shotPic" style="display:none;">

    </ul>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/viewer/js/viewer.min.js"></script>
<script type="text/javascript"
        src="<%=webRoot%>/sms/lib/common/common.js"></script>       
<script type="text/javascript">
var viewer;
var loadIndex;
$(function(){
     loadIndex = layer.load(1, {
      shade: [0.1,'#000'] 
    });

    getShotPic();
});

function getShotPic(){
    var linkId = getRequest();
     $.ajax({
       type : "post",
       url : webRoot+'/infopub/deviceinfo/getAttachIds.do',
       data : {"ST_LINK_ID" : linkId.ST_DEVICE_ID,"ST_LINK_TABLE":"INFOPUB_DEVICE_INFO"},
       dataType : "json",
       success : function(result) {
          if(result.isSuccess == false){
            setTimeout("getShotPic()",3000);
            return;
          }
          layer.close(loadIndex);  
          var data = result.data;
           var pic = "<li><img data-original="+webRoot+"/infopub/common/getImage.do?picId="+data+
            " src="+webRoot+"/infopub/common/getImage.do?picId="+data+"></li> ";
            $("#shotPic").html(pic);
           viewer = new Viewer(document.getElementById('shotPic'), {
                 url: 'data-original'
            });
            viewer.show();
       }
   });
}
</script>
</body>
</html>