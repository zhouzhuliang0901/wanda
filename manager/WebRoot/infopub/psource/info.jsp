<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubPsourceExt"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubPsourceExt infopubPsource = (InfopubPsourceExt) 
        request.getAttribute(InfopubPsourceExt.INFOPUB_PSOURCE);
    if (infopubPsource == null)
        infopubPsource = new InfopubPsourceExt();
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
<title>样表管理</title>
</head>
<body>
    <div class="page-container">
        <form class="form form-horizontal" id="form-devicegroup-add">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span> 发布源名称：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="<%=infopubPsource.stPsourceName2Html()%>"
                        name="<%=InfopubPsourceExt.ST_PSOURCE_NAME%>" disabled="disabled">
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">是否离线：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="<%=infopubPsource.nmOffline2Html(0)%>" disabled="disabled"> 
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"> 资源内容：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="<%=infopubPsource.getStContent()%>" disabled="disabled">
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                                               备注：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="<%=infopubPsource.stDesc2Html()%>"
                        name="<%=InfopubPsourceExt.ST_DESC %>" disabled="disabled">
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
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>    
</body>
</html>