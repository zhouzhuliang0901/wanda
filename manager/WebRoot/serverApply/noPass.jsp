<%@page import="com.wondersgroup.serverApply.bean.SelmServerApply"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceType"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	 String webRoot = AppContext.webRootPath;
	String stItemId = (String)request.getAttribute("stItemId");
    SelmServerApply selmServerApply = (SelmServerApply) request.getAttribute(SelmServerApply.SELM_SERVER_APPLY);
    if (selmServerApply == null)
        selmServerApply = new SelmServerApply(); 
        
       InfopubDeviceInfo infopubDeviceInfo = (InfopubDeviceInfo)request.getAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO);
    if(infopubDeviceInfo == null)
    	infopubDeviceInfo = new InfopubDeviceInfo();  
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
    <script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>  
<link  href="<%=webRoot%>/infopub/deviceinfo/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" 
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=webRoot%>/infopub/deviceinfo/css/bootstrap-select.css">
<%-- <link rel="stylesheet" type="text/css" href="<%=webRoot%>/resources/bootstrap-3.3.5/css/bootstrap-select.css"> --%>
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>??????</title>
</head>
<body>
<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;margin-bottom: -14px;">???????????????????????????</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;margin-bottom: -5px;border-color: #a9a9a9;""/>
	<div class="page-container" style="overflow-x:hidden;">
		
		<form class="form form-horizontal" id="form-device-add">
		
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>??????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:250%;height:180px;padding-top:1px;font-size:14px;" type="textarea" class="input-text"
						value=""
						name="stResult" placeholder="??????1000???" required></textarea>
				</div>
				
			</div>
		 
			 <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-6" >
                   
                   <button type="submit"
                        class="btn btn-primary radius" type="button" onclick="save()">
                        <i class="Hui-iconfont">&#xe632;</i> ??????
                    </button>
                </div>
            </div>
          </form>                             
		
	</div>
</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>  
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<%-- <script type="text/javascript" 
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap-select.js"></script> 
<script type="text/javascript" 
	src="<%=webRoot%>/infopub/deviceinfo/js/bootstrap.min.js"></script> --%>
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
var stItemId = '<%=stItemId%>';
var applyId = '<%=selmServerApply.getStApplyId()%>';
//console.log('applyId:'+applyId);
var result = $("#result").val();
//console.log('result:'+result);
var stDeviceId = '<%=infopubDeviceInfo.getStDeviceId()%>';
$(function(){
	//initData();
   // ????????????
   $("#form-device-add").validate();
   
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
	var formData = new FormData($("#form-device-add")[0]);
		$.ajax({
			type : "POST",
			url : webRoot + '/serverApply/selmServerApply/saveNoPassReason.do?stItemId='+stItemId+'&stDeviceId='+stDeviceId+'&stApplyId='+applyId,
			data: $('#form-device-add').serialize(),
			error : function(request) {
				layer.msg("????????????", {
					icon : 1,
					time : 1000
				});
				nopass();
			},
			success : function(data) {
				layer.msg("????????????", {
					icon : 1,
					time : 1000
				});
				nopass();
			}
		});
		setTimeout(function() {
			window.parent.location.reload();
		}, 1000);
	}
	
function nopass() {
		$.ajax({
			type : "POST",
			url : webRoot + '/serverApply/selmServerApply/saveNoPass.do?stItemId='+stItemId+'&stDeviceId='+stDeviceId+'&stApplyId='+applyId,
			error : function(request) {
				layer.msg("????????????", {
					icon : 1,
					time : 1000
				});
			},
			success : function(data) {
				layer.msg("????????????", {
					icon : 1,
					time : 1000
				});
			}
		});
		
}
	

</script>
</body>
</html>