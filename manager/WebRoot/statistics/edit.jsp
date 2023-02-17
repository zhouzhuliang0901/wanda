<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	 String webRoot = AppContext.webRootPath;
    SelmStatistics selmStatistics = (SelmStatistics) request.getAttribute(SelmStatistics.SELM_STATISTICS);
    if (selmStatistics == null)
        selmStatistics = new SelmStatistics(); 
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
<title>样表编辑</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-statistics-add">
			<input type="hidden" name="<%=SelmStatistics.ST_STATISTICS_ID%>"
				id="stSampleId" value="<%=selmStatistics.stStatisticsId2Html()%>" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">业务标识：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stNetFlag2Html()%>"
						<%if (!StringUtils.trimToEmpty(selmStatistics.getStNetFlag()).isEmpty()) {%>
						disabled="disabled" <%}%> name="<%=SelmStatistics.ST_NET_FLAG%>"
						required>
				</div>
				<label class="form-label col-xs-4 col-sm-2">业务子标识：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stNetSubFlag2Html()%>"
						name="<%=SelmStatistics.ST_NET_SUB_FLAG%>">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">业务名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stName2Html()%>"
						<%if (!StringUtils.trimToEmpty(selmStatistics.getStNetFlag()).isEmpty()) {%>
						disabled="disabled" <%}%> name="<%=SelmStatistics.ST_NAME%>" required>
				</div>
				<label class="form-label col-xs-4 col-sm-2">是否外设：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <span class="select-box"> 
                       <select name="<%=SelmStatistics.NM_ODEVICE %>" class="select">
                            <option value="1">是</option>
                            <option value="0">否 </option>
                       </select> 
                    </span>                        
                </div>
				
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">扩展字段1：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stExt12Html()%>"
						name="<%=SelmStatistics.ST_EXT1%>">
				</div>
				<label class="form-label col-xs-4 col-sm-2">扩展字段2：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stExt22Html()%>"
						name="<%=SelmStatistics.ST_EXT2%>">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">排序：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.nmSort2Html(0)%>"
						name="<%=SelmStatistics.NM_SORT%>">
				</div>
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
			//initData();
			// 数据校验
			$("#form-statistics-add").validate();
		});

		/* function initData() {
			$.ajax({
				url : webRoot + '/infopub/deviceinfo/init.do',
				type : "POST",
				dataType : "json",
				success : function(result) {
					var devicetype = result.devicetype;
					devicetype.typeId = $("#deviceType").val();
					$("#deviceTypeSelect").html(
							template('typeList', devicetype));

					if (isAdmin) {
						var workspace = result.workspace;
						workspace.workspace = $("#workspace").val();
						$("#workSpaceSelect").html(
								template('workspaceList', workspace));
					}
				},
				error : function() {
				}
			});
		} */

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
				url : webRoot + "/statistics/selmStatistics/save.do",
				data : $('#form-statistics-add').serialize(),// 你的formid
				error : function(request) {
					layer.msg("样表信息添加失败", {
						icon : 1,
						time : 1000
					});
				},
				success : function(data) {
					layer.msg("样表信息添加成功", {
						icon : 1,
						time : 1000
					});
				}
			});
			setTimeout(function() {
				window.parent.location.reload();
			}, 1000);
		}
	</script>
</body>
</html>