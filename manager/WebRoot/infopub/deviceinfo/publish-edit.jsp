<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubPublish"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	InfopubPublish infopubPublish = (InfopubPublish) request
			.getAttribute(InfopubPublish.INFOPUB_PUBLISH);
	if (infopubPublish == null)
		infopubPublish = new InfopubPublish();
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
<title>开关机时间设定</title>

</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-pulish-add">
		    <input type="hidden" name="<%=InfopubPublish.ST_PUBLISH_ID%>" value="<%=infopubPublish.stPublishId2Html()%>" />
			<input type="hidden" name="<%=InfopubPublish.ST_DEVICE_ID%>" value="<%=infopubPublish.stDeviceId2Html()%>" id="deviceId"/>
			<input type="hidden" value="<%=infopubPublish.stPsourceId2Html()%>" id="psourceId"/>
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span
                    class="c-red">*</span> 发布名称：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="<%=infopubPublish.stPublishName2Html()%>" 
                        name="<%=InfopubPublish.ST_PUBLISH_NAME%>" required>
                </div>
            </div>
            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">发布源：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <dl class="permission-list">
                        <dt><label>发布源</label></dt>
                     <dd id="psourceList">
                       <script id="psource" type="text/html">
                        {{each data}}
                            <label ><input type="radio" value="{{$value.stPsourceId}}" name="ST_PSOURCE_ID"
                                 {{if sourceId==$value.stPsourceId }}checked="checked"{{/if}}>{{$value.stPsourceName}}
                            </label>
                         {{/each}} 
                        </script>               
                     </dd>
                    </dl>
                </div>
            </div>
	
	        <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">相关备注：</label>
                <div class="formControls col-xs-8 col-sm-8">
                    <input type="text" class="input-text"
                        value="<%=infopubPublish.stDesc2Html()%>"
                        name="<%=InfopubPublish.ST_DESC%>">
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
        src="<%=webRoot%>/sms/lib/common/common.js"></script>           
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
	   var loadIndex;
		$(function() {
            loadIndex = layer.load(0, {
              shade: [1,'#FFFFFF'] 
            });
		
		    var request = getRequest();
		    if(typeof(request.ST_DEVICE_ID) != "undefined"){
		         $("#deviceId").val(request.ST_DEVICE_ID);
		    }
		   
			// 数据校验
			$("#form-pulish-add").validate();
            
            getPsource();
		});
		
		function getPsource(){
		  $.ajax({
	          type : "POST",
	          url : webRoot + "/infopub/psource/list.do",
	          dataType : "json",
	          error : function(request) {
	          },
	          success : function(result) {
	              var sourceId = $("#psourceId").val();
	               result.sourceId = sourceId;
	               $("#psourceList").html(template('psource', result));
	               layer.close(loadIndex);
	          }
	      });
		}

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
				url : webRoot + "/infopub/publish/save.do",
				data : $('#form-pulish-add').serialize(),// 你的formid
				error : function(request) {
					layer.msg("添加失败", {
						icon : 1,
						time : 1000
					});
				},
				success : function(data) {
					layer.msg("添加成功", {
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