<%@page import="com.wondersgroup.business.bean.SelmAccessApply"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceType"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	 String webRoot = AppContext.webRootPath;
    SelmAccessApply selmAccessApply = (SelmAccessApply) request.getAttribute(SelmAccessApply.SELM_ACCESS_APPLY);
    if (selmAccessApply == null)
        selmAccessApply = new SelmAccessApply(); 

			
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
<link href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
    rel="stylesheet" />	
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
    rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>??????</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add" enctype="multipart/form-data">
		  <input type="hidden" name="stApplyUserId"
		   id="stSampleId" value="<%=(Object)request.getSession().getAttribute("smsUserId")%>" />          
          
          <div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-3 col-sm-4" style="width: 50%;">
					<input type="text" class="input-text"
						value="??????????????????????????????????????????????????????"
						name="applytitle" required>
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
				&nbsp;???????????????</label>
				<div class="formControls col-xs-3 col-sm-4" style="width: 50%;">
				 <textarea name="applycontent"  cols="" rows="" class="textarea"
                        placeholder="" datatype="*10-100" dragonfly="true"
                        nullmsg="?????????????????????" onKeyUp="textarealength(this,100)"></textarea>
					<%-- <input type="textarea" class="input-text"
						value="<%=selmAccessApply.stApplyContent2Html()%>"
						name="applycontent"> --%>
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
				<span
					class="c-red">*</span>????????????????????????</label>
				<div class="formControls col-xs-3 col-sm-4" style="width: 50%;">
					 <input type="file" name="file"
             			id="310100621000100file2" multiple="multiple">
				</div>
			</div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <!-- -->
                    <button  type="submit" class="btn btn-primary radius" >
                        <i class="Hui-iconfont">&#xe632;&nbsp;</i>??????
                    </button>
                    <button onClick="layer_close();" class="btn btn-default radius"
                        type="button">&nbsp;&nbsp;??????&nbsp;&nbsp;</button>
                      <%--   <a href= "<%=webRoot%>/selmApply/apply.docx"
						target="_self" class="btn btn-default radius">???????????????</a> --%>
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
<script src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/i18n/jquery-ui-timepicker-zh-CN.js"></script>   
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/init.js"></script>            
	<!--?????????????????????????????????????????????-->
<script type="text/javascript">
var isAdmin = false;
// ????????????????????????
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
   // ????????????
   $("#form-device-add").validate();
   
});
  

// ????????????     
$.validator.setDefaults({
    submitHandler: function() {
      // ????????????
      article_save_submit();
      
    }
});
// ????????????
function article_save_submit() {
 	var formData = new FormData($("#form-device-add")[0]);  
	$.ajax({
		type : "POST",
		url : webRoot + "/business/selmAccessApply/uploadStuff.do",
		//data : $('#form-device-add').serialize(),// ??????formid
		  data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
		error : function(request) {
                  layer.msg("??????????????????", {icon : 1, time : 1000});
		},
		success : function(data) {
			layer.msg("??????????????????", {icon : 1, time : 1000});
		}
	});
	setTimeout(function() {
		window.parent.location.reload();
	}, 1000);
}

function downLoad(){
var url = webRoot+"/ac-self-manager/WebRoot/selmApply/access.docx";
location.href=url;
}
</script>
</body>
</html>