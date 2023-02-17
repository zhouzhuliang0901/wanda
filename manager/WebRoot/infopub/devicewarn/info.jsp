<%@page import="com.wondersgroup.infopub.bean.InfopubOdeviceStatus"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubOdeviceStatus infopubOdeviceStatus = (InfopubOdeviceStatus) request.getAttribute(InfopubOdeviceStatus.INFOPUB_ODEVICE_STATUS);
    if (infopubOdeviceStatus == null)
        infopubOdeviceStatus = new InfopubOdeviceStatus();    
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
			<input type="hidden" name="<%=InfopubOdeviceStatus.ST_OUT_DEVICE_RESULT_ID%>"
				id="stSampleId" value="<%=infopubOdeviceStatus.stOutDeviceResultId2Html()%>" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">设备ID：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.stDeviceId2Html() %>"
						name="<%=InfopubOdeviceStatus.ST_DEVICE_ID %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">外设标识：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.stOutDeviceCode2Html() %>"
						name="<%=InfopubOdeviceStatus.ST_OUT_DEVICE_CODE%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">是否异常：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmException2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_EXCEPTION %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">异常原因：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.stCause2Html() %>"
						name="<%=InfopubOdeviceStatus.ST_CAUSE%>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">是否已经通知：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmNotice2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_NOTICE %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">当前总量：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmTotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_TOTAL%>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">当前剩余量：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmRemain2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_REMAIN %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">历史总量：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmHisTotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_HIS_TOTAL%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">当前成功次数：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmHisStotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_HIS_STOTAL %>"
						disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">当前失败次数：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmHisFtotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_HIS_FTOTAL%>" disabled="disabled">
				</div>

			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">更新日期：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.dtUpdate2Html("yyyy-MM-dd HH:mm") %>"
						name="<%=InfopubOdeviceStatus.DT_UPDATE%>" disabled="disabled">
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