<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubPsource"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubAttachment"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	InfopubPsource infopubPsource = (InfopubPsource) request
			.getAttribute(InfopubPsource.INFOPUB_PSOURCE);
	InfopubAttachment infopubAttachment = (InfopubAttachment) request
			.getAttribute(InfopubAttachment.INFOPUB_ATTACHMENT);
	if (infopubPsource == null)
		infopubPsource = new InfopubPsource();

	if (infopubAttachment == null)
		infopubAttachment = new InfopubAttachment();
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
<title>发布管理</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-psource-add">
			<input type="hidden" name="<%=InfopubPsource.ST_PSOURCE_ID%>"
				value="<%=infopubPsource.stPsourceId2Html()%>" /> 		
					
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 发布源名称:</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text"
						value="<%=infopubPsource.stPsourceName2Html()%>"
						name="<%=InfopubPsource.ST_PSOURCE_NAME%>" required>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">是否离线:</label>
				<div class="formControls col-xs-8 col-sm-8">
				<span class="select-box"> 
                       <select name="<%=InfopubPsource.NM_OFFLINE %>" class="select">
                            <option value="1">是</option>
                            <option value="0">否</option>
                       </select> 
                    </span>          
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"> 资源内容:</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text"
						name="CL_CONTENT" value="<%=infopubAttachment.getClContent()==null? "":infopubAttachment.getClContent()%>" required>
				</div>
			</div>
			
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                                               备注：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="<%=infopubPsource.stDesc2Html()%>"
                        name="<%=InfopubPsource.ST_DESC %>" >
                </div>
            </div>

			<div class="row cl">
				<shiro:hasRole name="admin">
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2">所属空间:</label>
						<div class="formControls col-xs-8 col-sm-8">
							<span class="select-box"> <select
								name="<%=InfopubPsource.ST_WORKSPACE_ID%>" class="select"
								id="workSpaceSelect">
							</select> </span>
						</div>
					</div>
				</shiro:hasRole>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius" type="submit">
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
		var isAdmin = false;
		// 管理员权限的判定
		<shiro:hasRole name="admin">
		isAdmin = true;
		</shiro:hasRole>
		$(function() {
			// 管理员的场合
			if (isAdmin) {
				$.ajax({
					url : webRoot + '/infopub/workspace/list.do',
					type : "POST",
					dataType : "json",
					success : function(result) {
						var workspaceId = $("#workspace").val();
						var select = "";
						for ( var i = 0; i < result.data.length; i++) {
							if (result.data[i].stWorkspaceId == workspaceId) {
								select += "<option value="+result.data[i].stWorkspaceId+" selected='selected'>"
										+ result.data[i].stWorkspaceName
										+ "</option>";
							} else {
								select += "<option value="+result.data[i].stWorkspaceId+">"
										+ result.data[i].stWorkspaceName
										+ "</option>";
							}
						}
						$("#workSpaceSelect").html(select);
					},
					error : function() {
					}
				});
			}

			// 数据校验
			$("#form-psource-add").validate();
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
				url : webRoot + "/infopub/psource/save.do",
				data : $('#form-psource-add').serialize(),// 你的formid
				error : function(request) {
					layer.msg("空间信息添加失败", {
						icon : 2,
						time : 1000
					});
				},
				success : function(data) {
					layer.msg("空间信息添加成功", {
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