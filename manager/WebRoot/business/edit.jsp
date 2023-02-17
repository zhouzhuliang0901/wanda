<%@page import="com.wondersgroup.business.bean.SelmAttach"%>
<%@page import="com.wondersgroup.business.bean.SelmQueryHis"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String webRoot = AppContext.webRootPath;
    SelmQueryHis selmQueryHis = (SelmQueryHis) request
            .getAttribute(SelmQueryHis.SELM_QUERY_HIS);
    SelmAttach selmAttach = (SelmAttach) request
            .getAttribute(SelmAttach.SELM_ATTACH);
    if (selmQueryHis == null)
        selmQueryHis = new SelmQueryHis();
    if (selmAttach == null)
        selmAttach = new SelmAttach();
     
    request.setAttribute("stAttachId1", selmQueryHis.getStAttachId1());
	request.setAttribute("stAttachId2", selmQueryHis.getStAttachId2());
	request.setAttribute("stAttachId3", selmQueryHis.getStAttachId3());
	request.setAttribute("stAttachId4", selmQueryHis.getStAttachId4());
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
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot) %>';</script>
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
<link rel="stylesheet" type="text/css"
    href="<%=webRoot%>/sms/lib/iconfont/iconfont.css" />
    
<%--<link rel="stylesheet" type="text/css"--%>
<%--    href="https://at.alicdn.com/t/font_1700164_scb7ybg4fd.css" />--%>
<%--<link rel="stylesheet" type="text/css"--%>
<%--    href="https://at.alicdn.com/t/font_1743964_q4n8sjtxs3.css" />--%>
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>查看</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
			<input type="hidden" name="<%=SelmQueryHis.ST_QUERY_HIS_ID%>"
				id="stSampleId" value="<%=selmQueryHis.stQueryHisId2Html()%>" />
			<div class="row cl">
				<%-- <label class="form-label col-xs-4 col-sm-2">设备编码：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmQueryHis.stMachineId2Html()%>"
						name="<%=SelmQueryHis.ST_MACHINE_ID%>" >
				</div> --%>
				<label class="form-label col-xs-4 col-sm-2">事项编码：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmQueryHis.stItemNo2Html() %>"
						name="<%=SelmQueryHis.ST_ITEM_NO%>">
				</div>
				<label class="form-label col-xs-4 col-sm-2">事项名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmQueryHis.stItemName2Html() %>"
						name="<%=SelmQueryHis.ST_ITEM_NAME%>" >
				</div>
			</div>
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">模块名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmQueryHis.stModuleName2Html() %>"
						name="<%=SelmQueryHis.ST_MODULE_NAME%>" >
				</div>
			
				<label class="form-label col-xs-4 col-sm-2">操作名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmQueryHis.stModuleOp2Html() %>"
						name="<%=SelmQueryHis.ST_MODULE_OP %>" >
				</div>
			</div>
				
			<div class="row cl">
				
				<label class="form-label col-xs-4 col-sm-2">操作结果：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmQueryHis.stOpResult2Html()%>" 
						name="<%=SelmQueryHis.ST_OP_RESULT%>">
				</div>
				<label class="form-label col-xs-4 col-sm-2">备注：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmQueryHis.stDesc2Html()%>" 
						name="<%=SelmQueryHis.ST_DESC%>">
				</div>
			</div>
			<%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"></label>
					<c:if test="${stAttachId1 != null}">
						&nbsp;&nbsp;&nbsp;&nbsp;<button  onClick="file_info('${stAttachId1}');" type="button">&nbsp;&nbsp;附件一&nbsp;&nbsp;</button>
					</c:if>
					<c:if test="${stAttachId2 !=null}">
						&nbsp;&nbsp;&nbsp;&nbsp;<button  onClick="file_info('${stAttachId2}');" type="button">&nbsp;&nbsp;附件二&nbsp;&nbsp;</button>
					</c:if>
					<c:if test="${stAttachId3 !=null}">
						&nbsp;&nbsp;&nbsp;&nbsp;<button onClick="file_info('${stAttachId3}');" type="button">&nbsp;&nbsp;附件三&nbsp;&nbsp;</button>
					</c:if>
					<c:if test="${stAttachId4 !=null}">
						&nbsp;&nbsp;&nbsp;&nbsp;<button onClick="file_info('${stAttachId4}');" type="button">&nbsp;&nbsp;附件四&nbsp;&nbsp;</button>
					</c:if>
			</div> --%>

			    <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit"
                        class="btn btn-primary radius" type="button">
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
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap-select.js"></script> 
<script type="text/javascript" 
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap.min.js"></script>
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
<script src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
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
$(function(){
   // 数据校验
   $("#form-device-add").validate();
   
});
  

// 数据提交     
$.validator.setDefaults({
    submitHandler: function() {
      // 提交数据
      article_save_submit();
    }
});
// 提交内容
function article_save_submit() {
	$.ajax({
		type : "POST",
		url : webRoot + "/business/selmQueryHis/save.do",
		data : $('#form-device-add').serialize(),// 你的formid
		error : function(request) {
                  layer.msg("办件信息修改失败", {icon : 1, time : 1000});
		},
		success : function(data) {
			layer.msg("办件信息修改成功", {icon : 1, time : 1000});
		}
	});
	setTimeout(function() {
		window.parent.location.reload();
	}, 1000);
}
function file_info(stAttachId){
     var index = layer.open({
        type: 2,
        //title: title,
        content: webRoot + '/business/selmQueryHis/file.do?ST_ATTACH_ID=' + stAttachId
    });
    layer.full(index);
}
</script>
</body>
</html>