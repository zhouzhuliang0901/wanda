<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfoExt"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubOnoff"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceType"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubDeviceType infopubDeviceType = (InfopubDeviceType) request
			.getAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE);
	if (infopubDeviceType == null)
		infopubDeviceType = new InfopubDeviceType();      
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
<script type="text/javascript">
    var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot)%>';
</script>
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
    href="<%=webRoot%>/sms/lib/iconfont/iconfont.css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>查看</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=InfopubDeviceType.ST_TYPE_ID%>" id="stSampleId" value="<%=infopubDeviceType.stTypeId2Html()%>" /> 
          <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                 外设名称：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stTypeName2Html() %>" 
                        name="<%=InfopubDeviceType.ST_TYPE_NAME %>" required>
                </div>
               <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                    外设编号：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stTypeCode2Html() %>"
                        name="<%=InfopubDeviceType.ST_TYPE_CODE%>"  required>
                </div>
           </div>     
           
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                 设备图标：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stIcon2Html() %>" 
                        name="<%=InfopubDeviceType.ST_ICON %>" required>
                </div>
               <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                    设备样式：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stClass2Html() %>"
                        name="<%=InfopubDeviceType.ST_CLASS%>"  required>
                </div>
           </div>  
           
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                排序号：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.nmOrder2Html(0) %>" 
                        name="<%=InfopubDeviceType.NM_ORDER %>" required>
                </div>
   				<label class="form-label col-xs-4 col-sm-2">
                                               备注：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stDesc2Html()%>"
                        name="<%=InfopubDeviceType.ST_DESC %>" required>
                </div>
           </div>
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                创建时间：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.dtUpdate2Html("yyyy-MM-dd HH:mm") %>" 
                        name="<%=InfopubDeviceType.DT_UPDATE %>" required>
                </div>
   				<label class="form-label col-xs-4 col-sm-2">
                                               更新时间：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.dtCreate2Html("yyyy-MM-dd HH:mm")%>"
                        name="<%=InfopubDeviceType.DT_CREATE %>" required>
                </div>
           </div>
           
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button onClick="layer_close();" class="btn btn-default radius"
                        type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
                                         
		</form>
	</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>     
	<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/* $(function(){
      // 勾选已选择的数据
     $("input:checkbox").each(function(){ 
          var weekMsg = $("#weekMsg").val();
          var weeks= new Array();
          weeks = weekMsg.split("");
          for (var i in weeks) {
              var day = 1;
              if (weeks[i] == 1){
                  day = parseInt(i)+1;
              } else {
                  day = 0;
              }
              if ($(this).attr('value') == day){
                $(this).prop("checked",true);
              }
          }
      }); 
}); */
</script>
</body>
</html>

