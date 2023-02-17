<%@page import="com.wondersgroup.infopub.bean.InfopubAddress"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubArea"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubAddress infopubAddress = (InfopubAddress) request.getAttribute(InfopubAddress.INFOPUB_ADDRESS);
    InfopubArea infopubArea = (InfopubArea) request.getAttribute(InfopubArea.INFOPUB_AREA);
    if (infopubArea == null)
        infopubArea = new InfopubArea(); 
    if (infopubAddress == null)
        infopubAddress = new InfopubAddress();  
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
<title>查看</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=InfopubAddress.ST_ADDRESS_ID%>"
		   id="stSampleId" value="<%=infopubAddress.stAddressId2Html()%>" />   
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">地址别名：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.stLabel2Html() %>"
						name="<%=InfopubAddress.ST_LABEL%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">经度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.nmLng2Html(10) %>"
						name="<%=InfopubAddress.NM_LNG %>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">纬度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.nmLat2Html(10) %>"
						name="<%=InfopubAddress.NM_LAT%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">省：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubArea.stAreaName2Html()%>"
						name="<%=InfopubArea.ST_AREA_NAME %>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">市：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.stCity2Html() %>"
						name="<%=InfopubAddress.ST_CITY %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">区：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.stDistrict2Html() %>"
						name="<%=InfopubAddress.ST_DISTRICT%>" disabled="disabled">
				</div>
				
			</div>
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">街道：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.stStreet2Html() %>"
						name="<%=InfopubAddress.ST_STREET %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">详细地址：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.stAddress2Html() %>"
						name="<%=InfopubAddress.ST_ADDRESS %>" disabled="disabled">
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

</body>
</html>