<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubArea"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
	InfopubArea infopubArea = (InfopubArea) request
			.getAttribute(InfopubArea.INFOPUB_AREA);
	if (infopubArea == null)
		infopubArea = new InfopubArea();
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
<title>区域信息</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-menu-add">
			<input type="hidden" name="<%=InfopubArea.ST_AREA_ID%>" id="areaId"
				value="<%=infopubArea.stAreaId2Html()%>" />

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 区域编号：</label>
				<div class="formControls  col-xs-8 col-sm-8">
					<input type="text" class="input-text areaCodeCheck"
						value="<%=infopubArea.stAreaCode2Html()%>"
						<%if (!StringUtils.trimToEmpty(infopubArea.getStAreaCode()).isEmpty()) {%>
						disabled="disabled" <%}%> name="<%=InfopubArea.ST_AREA_CODE%>"
						required>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">区域名称：</label>
				<div class="formControls  col-xs-8 col-sm-8">
					<input type="text" class="input-text"
						value="<%=infopubArea.stAreaName2Html()%>"
						name="<%=InfopubArea.ST_AREA_NAME%>" required>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">管理员：</label>
				<div class="formControls  col-xs-8 col-sm-8">
					<input type="text" class="input-text"
						value="<%=infopubArea.stUserName2Html()%>"
						name="<%=InfopubArea.ST_USER_NAME%>" required>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">联系方式：</label>
				<div class="formControls  col-xs-8 col-sm-8">
					<input type="text" class="input-text"
						value="<%=infopubArea.stPhone2Html()%>"
						name="<%=InfopubArea.ST_PHONE%>" required>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">排序号：</label>
				<div class="formControls  col-xs-8 col-sm-8">
					<input type="text" class="input-text" digits="true"
						value="<%=infopubArea.nmOrder2Html(0)%>"
						name="<%=InfopubArea.NM_ORDER%>">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">备注：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<textarea name="<%=InfopubArea.ST_DESC%>" cols="" rows=""
						class="textarea" placeholder="说点什么...最少输入10个字符" datatype="*10-100"
						dragonfly="true" nullmsg="备注不能为空！"
						onKeyUp="textarealength(this,100)"><%=infopubArea.stDesc2Html()%></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>
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
	<script type="text/javascript">
		$(function() {
			// 数据校验
			$("#form-menu-add").validate();

			// 资源编号唯一性检查
			$.validator.addMethod("areaCodeCheck", function(value, element) {
				var flag = false;
				$.ajax({
					type : "POST",
					url : webRoot + "/infopub/area/checkAreaCode.do",
					dataType : "json",
					async : false,
					data : {
						"areaCode" : value,
						"ST_AREA_ID" : $("#areaId").val()
					},
					error : function(request) {
					},
					success : function(result) {
						flag = !result.data;
					}
				});
				return flag;
			}, "区域编号已存在");
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
				url : webRoot + "/infopub/area/save.do",
				data : $('#form-menu-add').serialize(),// 你的formid
				error : function(request) {
					layer.msg("角色信息添加失败", {
						icon : 2,
						time : 1000
					});
				},
				success : function(data) {
					layer.msg("角色信息添加成功", {
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