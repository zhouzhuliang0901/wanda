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
<title>编辑</title>
</head>
<body>
<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;margin-bottom: -14px;"">基本信息</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;margin-bottom: -5px;border-color: #a9a9a9;""/>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		<input type="hidden" name="deviceApplyId"
		   id="ST_DEVICE_APPLY_ID" value="<%=deviceApplyId%>" />
		   
		  <input type="hidden" name="<%=SelmDeviceAlink.ST_MACHINE_ID%>"
		   id="ST_MACHINE_ID" value="<%=selmDeviceAlink.stMachineId2Html()%>" />
          <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
					<span class="c-red">*</span>所在区：
				</label>
				<div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> 
						<select name="ST_AREA" class="select" id="areaSelect" >
							<option value=""></option>
						</select> 
					</span>
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>所在街道：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_ADDRESS" required >
				</div>
				
			</div>
			
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>具体地址：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_SPECINICADDRESS" required >
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>网点名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_NETWORK" required >
				</div>				
			</div>
			
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				MAC：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_MAC" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				IP：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_IP">
				</div>				
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>设备厂商：</label>
				<div class="formControls col-xs-4 col-sm-4">
						<span class="select-box"> 
							<select name="ST_PRODUCER" class="select" id="companyTypeSelect">
								<option value=""></option>
							</select> 
						</span>
				</div>				
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>终端类型：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> 
						<select name="ST_DEVICETYPE" class="select" id="deviceTypeSelect">
							<option value=""></option>
						</select> 
					</span>
				</div>
				
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>医保制册机：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_YBZC" type="radio" value="1" >有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_YBZC" type="radio" value="0" >无
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>上传纸质申请扫描件：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="file" name="file"  multiple="multiple"  required="required">
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>高拍仪：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_GPY" type="radio" value="1" >有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_GPY" type="radio" value="0" >无
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>居住证签注机：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_JZZQU" type="radio" value="1" >有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_JZZQU" type="radio" value="0" >无
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>居住证制卡机：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_JZZZK" type="radio" value="1" >有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_JZZZK" type="radio" value="0" >无
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>网络情况：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_NETWORK" type="radio" value="政务外网" >政务外网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_NETWORK" type="radio" value="互联网" >互联网
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>有无人员值守：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_DUTY" type="radio" value="1" >有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_DUTY" type="radio" value="0" >无
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>是否是24小时设备：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input name="NM_24HOURS" type="radio" value="1" >是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="NM_24HOURS" type="radio" value="0" >否
				</div>	
			</div>
			
            <div class="row cl" style="text-align:center;">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit"  
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> 添加
                    </button>
                   
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button onClick="layer_close();" class="btn btn-danger radius"
                        type="button">&nbsp;&nbsp;关闭&nbsp;&nbsp;</button>
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
	<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
var deviceApplyId = "<%=deviceApplyId%>";
var stDeviceId = "<%=selmDeviceAlink.stMachineId2Html()%>";
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
var isAdmin = false;
// 管理员权限的判定
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
	initArea();
	initCompanyAndType();
   	// 数据校验
   	$("#form-device-add").validate();
});


function initArea(){
	$.ajax({
		url:webRoot+'/infopub/deviceArea/init.do',
        type:"get",
        dataType:"json",
        success : function(data) {
           	for(var i = 0; i < data.area.length; i++){
           		$("#areaSelect").append("<option>"+data.area[i]+"</option>");
           	}
        },
        error : function() {
        }
	});
}

function initCompanyAndType(){
	$.ajax({
		url:webRoot+'/infopub/deviceCompWithType/init.do',
        type:"get",
        dataType:"json",
        success : function(data) {
           	for(var i = 0; i < data.company.length; i++){
           		$("#companyTypeSelect").append("<option>"+data.company[i]+"</option>");
           	}
        },
        error : function() {
        }
	});
}

$("#companyTypeSelect").change(function(){
	$("#deviceTypeSelect").children("option").remove();
	$.ajax({
		url:webRoot+'/infopub/deviceCompWithType/init.do',
        type:"get",
        dataType:"json",
        success : function(data) {
           	var name = $("#companyTypeSelect").val();
           	for(var i = 0; i < data.compWithType.length; i++){
           		if(name == data.compWithType[i].cname){
           			var ctype = data.compWithType[i].type;
           			for(var j = 0; j < ctype.length; j++){
           				if(ctype[j].indexOf('行') == -1){
           					$("#deviceTypeSelect").append("<option>"+ctype[j]+"</option>");
           				}
           			}
           			
           		}
           	}
        },
        error : function() {
        }
	});
	
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
	
		var nmYbzc = $("input[name='NM_YBZC']:checked").val();
		if(nmYbzc == " " || nmYbzc == null){
			alert("请勾选医保制册机");
			return;
		} 
		
		var nmGpy = $("input[name='NM_GPY']:checked").val();
		if(nmGpy == " " || nmGpy == null){
			alert("请勾选高拍仪");
			return;
		} 
		
		var nmJzzqu = $("input[name='NM_JZZQU']:checked").val();
		if(nmJzzqu == " " || nmJzzqu == null){
			alert("请勾选居住证签注机");
			return;
		} 
		
		var nmJzzk = $("input[name='NM_JZZZK']:checked").val();
		if(nmJzzk == " " || nmJzzk == null){
			alert("请勾选居住证制卡机");
			return;
		} 
		
		var nmNetwork = $("input[name='NM_NETWORK']:checked").val();
		if(nmNetwork == " " || nmNetwork == null){
			alert("请勾选网络情况");
			return;
		} 
		
		var nmDuty = $("input[name='NM_DUTY']:checked").val();
		if(nmDuty == " " || nmDuty == null){
			alert("请勾选有无人员值守");
			return;
		}
		
		var nm24hours = $("input[name='NM_24HOURS']:checked").val();
		if(nm24hours == " " || nm24hours == null){
			alert("请勾选是否是24小时设备");
			return;
		}
		
		var file = $("input[name='file']").val();
		if("" != file){
			checkImgType();//检查突破按类型
		}else{
			save();
		}
}

//保存数据
function save(){
	var formData = new FormData($("#form-device-add")[0]);
	$.ajax({
		type : "POST",
		url : webRoot + "/serverApply/selmDeviceAlink/save.do",
		data: formData,
		async: false,  
        cache: false,  
       	contentType: false,  
       	processData: false,
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
	setTimeout(function() {
		window.parent.location.reload();
	}, 1000);
}
	
//校验图片类型
function checkImgType(){
	var filepath = $("input[name='file']").val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
       alert("图片限于bmp,png,jpeg,jpg格式");
	}else{
		checkImgSize();
	}
}

//校验图片大小
function checkImgSize(){
	var size =  $("input[name='file']")[0].files[0].size;
    var filesize = (size / 1024).toFixed(2); //K
    if(filesize > 1000){
    	alert("图片大小限于1000KB");
    }else{
    	save();
    }
}
</script>
</body>
</html>