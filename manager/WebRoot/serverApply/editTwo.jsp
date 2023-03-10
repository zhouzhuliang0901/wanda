<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="com.wondersgroup.serverApply.bean.SelmDeviceAlink"%>
<%@page import="com.wondersgroup.serverApply.bean.SelmDeviceApply"%>
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
	 String deviceApplyId = (String)request.getSession().getAttribute("deviceApplyId");
    SelmDeviceAlink selmDeviceAlink = (SelmDeviceAlink) request.getAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK);
    if (selmDeviceAlink == null)
        selmDeviceAlink = new SelmDeviceAlink();
   
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
<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;margin-bottom: -14px;"">????????????</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;margin-bottom: -5px;border-color: #a9a9a9;""/>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		<input type="hidden" name="stDeviceApplyId"
		   id="ST_DEVICE_APPLY_ID" value="<%=deviceApplyId%>" />
		   
		  <input type="hidden" name="<%=SelmDeviceAlink.ST_MACHINE_ID%>"
		   id="ST_MACHINE_ID" value="<%=selmDeviceAlink.stMachineId2Html()%>" />
          <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<select type="text" class="input-text" name="ST_AREA">
						<option></option>
						<option value="?????????">?????????</option>
						<option value="?????????">?????????</option>
						<option value="????????????">????????????</option>
					</select>
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_ADDRESS">
				</div>
				
			</div>
			
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_SPECINICADDRESS">
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_NETWORK">
				</div>				
			</div>
			
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				MAC???</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_MAC">
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				IP???</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_IP">
				</div>				
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<select type="text" class="input-text" name="ST_PRODUCER">
						<option></option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
					</select>
				</div>				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<select type="text" class="input-text" name="ST_DEVICETYPE">
						<option></option>
						<option value="????????????????????????????????????">????????????????????????????????????</option>
						<option value="????????????????????????2????????????">????????????????????????2????????????</option>
						<option value="????????????????????????????????????">????????????????????????????????????</option>
						<option value="?????????????????????????????????">?????????????????????????????????</option>
						<option value="????????????????????????????????????">????????????????????????????????????</option>
					</select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>??????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_YBZC" type="checkbox" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_YBZC" type="checkbox" value="0" >???
				</div>
					
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_GPY" type="checkbox" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_GPY" type="checkbox" value="0" >???
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_JZZQU" type="checkbox" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_JZZQU" type="checkbox" value="0" >???
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_JZZZK" type="checkbox" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_JZZZK" type="checkbox" value="0" >???
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_NETWORK" type="checkbox" value="????????????" >????????????&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_NETWORK" type="checkbox" value="?????????" >?????????
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_DUTY" type="checkbox" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_DUTY" type="checkbox" value="0" >???
				</div>	
			</div>
			
			
            <div class="row cl" style="text-align:center;">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit" 
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> ??????
                    </button>
                    <!-- <a name="addDeviceInfo" id="addDeviceInfo" class="btn btn-primary radius" onclick="article_save_submit()" >
				 <i class="Hui-iconfont">&#xe632;</i>??????
				</a> -->
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button onClick="layer_close();" class="btn btn-danger radius"
                        type="button">&nbsp;&nbsp;??????&nbsp;&nbsp;</button>
                </div>
            </div>
                                         
		</form>
	</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>  
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
//console.log("<%=deviceApplyId%>");
var deviceApplyId = "<%=deviceApplyId%>";
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
var isAdmin = false;
// ????????????????????????
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){

	//initData()
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
		$.ajax({
			type : "POST",
			url : webRoot + "/serverApply/selmDeviceAlink/save.do",
			data: $('#form-device-add').serialize(),
			error : function(request) {
				layer.msg("????????????????????????????????????", {
					icon : 1,
					time : 1000
				});
			},
			success : function(data) {
				layer.msg("????????????????????????????????????", {
					icon : 1,
					time : 1000
				});
			}
		});
		setTimeout(function() {
			window.parent.location.reload();
		}, 1000);
	}
	
/* function initData(){
 $.ajax({
         url:webRoot+'/sms/organ/organList.do',
         type:"POST",
         dataType:"json",
         success : function(result) {
             var organ = result.organ;
             $("#organListSelect").html(template('typeList', organ));
             
          },
         error : function() {
         }
     }); 
} */

// ??????
/* function device_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url
    });
    layer.full(index);
} */


//??????????????????
function addDeviceInfo(title, url){
	var index = layer.open({
		type:2,
		title:title,
		content:webRoot+url
	});
	layer.full(index);
}
</script>
</body>
</html>