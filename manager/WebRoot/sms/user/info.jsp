<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.sms.user.bean.SmsUser"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
    SmsUser smsUser = (SmsUser) request.getAttribute(SmsUser.SMS_USER);
    if (smsUser == null)
        smsUser = new SmsUser();
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

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>????????????</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-sample-add">
			<input type="hidden" name="<%=SmsUser.ST_USER_ID%>"  value="<%=smsUser.stUserId2Html() %>" /> 
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=smsUser.stLoginName2Html() %>"  disabled="disabled">
				</div>
                 <label class="form-label col-xs-4 col-sm-2">?????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stUserCode2Html() %>" disabled="disabled">
                </div>
			</div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">?????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stUserName2Html() %>" disabled="disabled">
                </div>
                 <label class="form-label col-xs-4 col-sm-2">?????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stPinyin2Html() %>" disabled="disabled">
                </div>               
                
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">?????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stMobile2Html() %>" disabled="disabled">
                </div>
                <label class="form-label col-xs-4 col-sm-2">?????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stEmail2Html() %>" disabled="disabled">
                </div>                
            </div>    
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">???????????????????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text" 
                      <% if (smsUser.getNmReceiveEmail().intValue() ==1){%>
                       value="???"<% } else { %>value="???" <%} %> disabled="disabled">
                </div>
                <label class="form-label col-xs-4 col-sm-2">????????????????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                      <% if (smsUser.getNmLocked().intValue() ==1){%>
                       value="???"<% } else { %>value="???" <%} %> disabled="disabled" >
                </div>                
            </div>    
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stOrganId2Html() %>" disabled="disabled" >
                </div>
                <label class="form-label col-xs-4 col-sm-2">???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.stAreaId2Html() %>" disabled="disabled">
                </div>                
            </div> 
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.dtCreate2Html("yyyy-MM-dd HH:mm") %>" 
                        disabled="disabled" >
                </div>
                <label class="form-label col-xs-4 col-sm-2">???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=smsUser.dtUpdate2Html("yyyy-MM-dd HH:mm") %>"
                         disabled="disabled">
                </div>                
            </div>             
            
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="layer_close();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;??????&nbsp;&nbsp;</button>
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
</body>
</html>