<%@page import="com.wondersgroup.serverApply.bean.SelmServerApply"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    SelmServerApply selmServerApply = (SelmServerApply) request.getAttribute(SelmServerApply.SELM_SERVER_APPLY);
    if (selmServerApply == null)
        selmServerApply = new SelmServerApply(); 
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
		 <input type="hidden" name="<%=SelmServerApply.ST_APPLY_ID%>"
		   id="stSampleId" value="<%=selmServerApply.stApplyId2Html()%>" />    
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				申请单位：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stApplyOrganName2Html() %>"
						name="<%=SelmServerApply.ST_APPLY_ORGAN_NAME%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				联系人：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserName2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_NAME%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				手机：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserPhone2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_PHONE%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				固定电话：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserMobile2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_MOBILE%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				电子邮箱：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserEmail2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_EMAIL%>" disabled="disabled">
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				申请情况说明：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerContent2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_CONTENT%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				计划上线时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.dtUpCreate2Html("yyyy-MM-dd")%>"
						name="<%=SelmServerApply.DT_UP_CREATE%>" disabled="disabled">
				</div>
					<%-- <label class="form-label col-xs-4 col-sm-2">
				状态：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.nmStatus2Html(0) %>"
						name="<%=SelmServerApply.NM_STATUS%>"disabled="disabled" >
				</div> --%>
				<label class="form-label col-xs-4 col-sm-2">
				备注：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stExt12Html() %>"
						name="<%=SelmServerApply.ST_EXT1%>"disabled="disabled" >
				</div>
			</div>
			 <%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				备注：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stExt12Html() %>"
						name="<%=SelmServerApply.ST_EXT1%>"disabled="disabled" >
				</div>
				</div> --%>
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