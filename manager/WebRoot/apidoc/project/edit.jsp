<%@page import="wfc.service.config.Config"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.api.bean.ApidocProject"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@page import="wfc.facility.tool.autocode.Transformer4Request"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String webRoot = AppContext.webRootPath;
	ApidocProject apidocProject = (ApidocProject) request.getAttribute(ApidocProject.APIDOC_PROJECT);
	if (apidocProject == null)
		apidocProject = new ApidocProject();
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
	<form class="form form-horizontal" id="form-workspace-add">
		<input type="hidden" name="<%=ApidocProject.ST_PROJECT_ID %>" value="<%=apidocProject.stProjectId2Html() %>" /> 
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span
			class="c-red">*</span>???????????????</label>
			<div class="formControls col-xs-6 col-sm-6">
				<input type="text" class="input-text"
					value="<%=apidocProject.stProjectName2Html() %>"
					name="<%=ApidocProject.ST_PROJECT_NAME %>" >
			</div>
		</div>
		<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2">???????????????</label>
				<div class="formControls col-xs-6 col-sm-6">
					<textarea name="<%=ApidocProject.ST_REMARK %>" cols="" rows="" class="textarea"
						style="overflow-x:hidden" placeholder="????????????..."  dragonfly="true"
						onKeyUp="textarealength(this,100)"><%=apidocProject.stRemark2Html()%></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
			</div>
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">????????????</label>
			<div class="formControls col-xs-6 col-sm-6">
				<input type="text" class="input-text" digits="true"
					value="<%=apidocProject.nmOrder2Html(0)%>"
					name="<%=ApidocProject.NM_ORDER %>" >
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button class="btn btn-primary radius" type="submit">
					<i class="Hui-iconfont">&#xe632;</i> ??????
				</button>
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
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>
<script type="text/javascript">
$(function() {
	// ????????????
	$("#form-workspace-add").validate();
});

// ????????????     
$.validator.setDefaults({
	submitHandler : function() {
		// ????????????
		article_save_submit();
	}
});

// ????????????
function article_save_submit() {
	$.ajax({
		type : "POST",
		url : webRoot + "/apidoc/project/save.do",
		data : $('#form-workspace-add').serialize(),// ??????formid
		error : function(request) {
			layer.msg("?????????????????????", {
				icon : 2,
				time : 1000
			});
		},
		success : function(data) {
			layer.msg("?????????????????????", {
				icon : 1,
				time : 1000
			});
			parent.search();
			layer_close();
		}
	});
}
</script>
</body>
</html>
