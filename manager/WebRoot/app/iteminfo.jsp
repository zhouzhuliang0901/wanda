<%@page import="com.wondersgroup.app.bean.SelmItem"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    SelmItem selmItem = (SelmItem) request
            .getAttribute(SelmItem.SELM_ITEM);
    if (selmItem == null)
        selmItem = new SelmItem();
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
<title>??????</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-item-add">
			<input type="hidden" name="<%=SelmItem.ST_ITEM_ID%>"
				id="stSampleId" value="<%=selmItem.stItemId2Html()%>" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stMainName2Html() %>"
						name="<%=SelmItem.ST_MAIN_NAME%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stItemNo2Html() %>"
						name="<%=SelmItem.ST_ITEM_NO %>" disabled="disabled">
				</div>
				
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stWorkUrl2Html() %>"
						name="<%=SelmItem.ST_WORK_URL%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stWorkType2Html() %>"
						name="<%=SelmItem.ST_WORK_TYPE%>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stItemType2Html()%>"
						name="<%=SelmItem.ST_ITEM_TYPE%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stOrganId2Html() %>"
						name="<%=SelmItem.ST_ORGAN_ID %>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stExt22Html()%>"
						name="<%=SelmItem.ST_EXT2%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.nmSort2Html(0) %>"
						name="<%=SelmItem.NM_SORT %>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItem.stExt12Html() %>"
						name="<%=SelmItem.ST_EXT1 %>" disabled="disabled">
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
	<!--?????????????????????????????????????????????-->
	<script type="text/javascript">

</script>
</body>
</html>