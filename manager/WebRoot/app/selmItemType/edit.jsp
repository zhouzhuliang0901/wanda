<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.app.bean.SelmItemType"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
    SelmItemType selmItemType = (SelmItemType) request.getAttribute(SelmItemType.SELM_ITEM_TYPE);
    if (selmItemType == null)
        selmItemType = new SelmItemType();
      /* selmStatistics.getNmOdevice(); */
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
<link href="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.css"
	rel="stylesheet" />
<link
	href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
	rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>分组编辑</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-statistics-add">
			<input type="hidden" name="<%=SelmItemType.ST_ITEM_TYPE_ID%>"
				id="stSampleId" value="<%=selmItemType.stItemTypeId2Html()%>" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">事项分组名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.stItemTypeName2Html() %>"
						 name="<%=SelmItemType.ST_ITEM_TYPE_NAME%>" required>
				</div>
				<label class="form-label col-xs-4 col-sm-2">排序：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.nmSort2Html(0)%>"
						name="<%=SelmItemType.NM_SORT%>">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">备注1：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.stExt12Html()%>"
						name="<%=SelmItemType.ST_EXT1%>">
				</div>
				<%-- <label class="form-label col-xs-4 col-sm-2">备注2：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmItemType.stExt22Html()%>"
						name="<%=SelmItemType.ST_EXT2%>">
				</div> --%>
				</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button type="submit" class="btn btn-primary radius" type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>
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
		src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script
		src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jQuery-Timepicker/i18n/jquery-ui-timepicker-zh-CN.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jQuery-Timepicker/init.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
		var isAdmin = false;
		// 管理员权限的判定
		<shiro:hasRole name="admin">
		isAdmin = true;
		</shiro:hasRole>
		$(function() {
			// 数据校验
			$("#form-statistics-add").validate();
		});


		// 数据提交     
		$.validator.setDefaults({
			submitHandler : function() {
				// 提交数据
				article_save_submit();
			}
		});
		// 提交内容
		function article_save_submit() {
			$.ajax({
				type : "POST",
				url : webRoot + "/app/selmItemType/save.do",
				data : $('#form-statistics-add').serialize(),// 你的formid
				error : function(request) {
					layer.msg("事项分组添加失败", {
						icon : 1,
						time : 1000
					});
				},
				success : function(data) {
					layer.msg("事项分组添加成功", {
						icon : 1,
						time : 1000
					});
				}
			});
			window.parent.location.reload();
		}
	</script>
</body>
</html>