<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
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
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>组别操作</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>设备组别：</label>
				<div class="formControls col-xs-8 col-sm-8 selectGroup"
					id="optGroupId">
					<dl class="permission-list">
						<dt>
							<label>选择设备组</label>
						</dt>
						<dd></dd>
					</dl>
				</div>
			</div>

			<div class="row cl">

				<label class="form-label col-xs-4 col-sm-2">执行动作：</label>
				<div class="formControls col-xs-8 col-sm-10">
					<div class="radio-box">
						<input name="opt" type="radio" value="close"> <label
							for="sex-1">关闭设备</label>
					</div>
					<div class="radio-box">
						<input type="radio" name="opt" value="open"> <label
							for="sex-2">打开设备</label>
					</div>
					<div class="radio-box">
						<input type="radio" name="opt" value="reboot"> <label
							for="sex-3">重启设备</label>
					</div>
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="device_group_opt();"
						class="btn btn-primary radius" type="button">
						<i class="Hui-iconfont">&#xe632;</i>确定
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
		src="<%=webRoot%>/sms/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
		$(function() {
			getDeviceGroupInfo();
		});

		function getDeviceGroupInfo() {
			$.ajax({
				url : webRoot + '/infopub/devicegroup/list.do',
				type : "POST",
				dataType : "json",
				success : function(result) {
					var select = "<dl class=\"permission-list\"><dt><label>选择设备组</label></dt><dd >";
					for ( var i = 0; i < result.data.length; i++) {
						select += "<label><input type=\"checkbox\" value="+result.data[i].stGroupId+
                                " name=\"groupId[]\">"+ result.data[i].stGroupName+ "</label>";
					}
					select += "</dd></dl>";
					$(".selectGroup").html(select);
				},
				error : function() {
				}
			});
		}

		function device_group_opt() {
			var opt = $('input:radio:checked').val();
			var chk_value = [];
			$('#optGroupId input:checkbox:checked').each(function() {
				chk_value.push($(this).val());
			});
		     if (chk_value != '' && typeof(opt) != 'undefined') {
	             $.ajax({
	                type : "POST",
	                url : webRoot + '/infopub/devicegroup/operate.do',
	                dataType : "json",
	                data : {
	                    "groupId" : chk_value,
	                    "type" : opt
	                },
	                success : function(myObject) {
	                    layer.msg(myObject.msg, {
	                        icon : 1,
	                        time : 1000
	                    });
	                },
	                error : function() {
	                    layer.msg(myObject.msg, {
	                        icon : 1,
	                        time : 1000
	                    });
	                }
	            });
		     } else {
                 layer.msg("请选择设备组和需要的进行的操作", {
                      icon : 2,
                      time : 2000
                  });
		     }
		}
	</script>
</body>
</html>