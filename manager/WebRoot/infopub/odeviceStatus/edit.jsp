<%@page import="com.wondersgroup.infopub.bean.InfopubOdeviceStatus"%>
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
    InfopubOdeviceStatus infopubOdeviceStatus = (InfopubOdeviceStatus) request.getAttribute(InfopubOdeviceStatus.INFOPUB_ODEVICE_STATUS);
    if (infopubOdeviceStatus == null)
        infopubOdeviceStatus = new InfopubOdeviceStatus(); 

			
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
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=InfopubOdeviceStatus.ST_OUT_DEVICE_RESULT_ID%>" id="stSampleId" value="<%=infopubOdeviceStatus.stOutDeviceResultId2Html()%>" />          
          <input type="hidden" value="<%=infopubOdeviceStatus.stDeviceId2Html()%>"
				id="deviceInfo"/>
          <div class="row cl">
				<%-- <label class="form-label col-xs-4 col-sm-2">??????ID???</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.stDeviceId2Html() %>"
						name="<%=InfopubOdeviceStatus.ST_DEVICE_ID %>" required>
				</div> --%>
				<label class="form-label col-xs-4 col-sm-2"><!-- <span
					class="c-red">*</span> --> ???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> <select
						name="<%=InfopubOdeviceStatus.ST_DEVICE_ID %>" class="select"
						id="deviceInfoSelect">
							<script id="deviceinfoList" type="text/html">
                                        {{each data}}
                                            <option value="{{$value.stDeviceMac}}" {{if stDeviceId == $value.stDeviceMac}} selected='selected' {{/if}}>
                                                    {{$value.stDeviceCode}}</option>
                                        {{/each}} 
                            </script>
					</select> </span>
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.stOutDeviceCode2Html() %>"
						name="<%=InfopubOdeviceStatus.ST_OUT_DEVICE_CODE%>" required>
				</div>
			</div>

			<%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmException2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_EXCEPTION %>" required>
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <span class="select-box"> 
                       <select name="<%=InfopubOdeviceStatus.NM_EXCEPTION %>" class="select">
                            <option value="1">???</option>
                            <option value="0">??? </option>
                       </select> 
                    </span>                        
                </div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.stCause2Html() %>"
						name="<%=InfopubOdeviceStatus.ST_CAUSE%>" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmNotice2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_NOTICE %>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmTotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_TOTAL%>" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">??????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmRemain2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_REMAIN %>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmHisTotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_HIS_TOTAL%>" >
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmHisStotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_HIS_STOTAL %>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOdeviceStatus.nmHisFtotal2Html(0) %>"
						name="<%=InfopubOdeviceStatus.NM_HIS_FTOTAL%>" >
				</div>
				
			</div> --%>

			<!-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value=""
						name="count" >
				</div>
			</div> -->
           
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit"
                        class="btn btn-primary radius" type="button">
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
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
var isAdmin = false;
// ????????????????????????
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
   // ????????????
   initData();
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
	$.ajax({
		type : "POST",
		url : webRoot + "/infopub/odeviceStatus/saveupdate.do",
		data : $('#form-device-add').serialize(),// ??????formid
		error : function(request) {
                  layer.msg("????????????????????????", {icon : 1, time : 1000});
		},
		success : function(data) {
			layer.msg("????????????????????????", {icon : 1, time : 1000});
		}
	});
	setTimeout(function() {
		window.parent.location.reload();
	}, 1000);
}
		var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
		var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
		function initData() {
			$.ajax({
				url : webRoot + '/infopub/deviceinfo/init.do',
				type : "POST",
				data : {
					'stAreaId':areaId,
					//'stPermission':permission
					'stPermission':'project_admin',
					userName:user
				},
				dataType : "json",
				success : function(result) {
					var deviceinfo = result.deviceinfo;
					deviceinfo.stDeviceId = $("#deviceInfo").val();
					$("#deviceInfoSelect").html(
							template('deviceinfoList', deviceinfo));
				},
				error : function() {
				}
			});
		}

</script>
</body>
</html>