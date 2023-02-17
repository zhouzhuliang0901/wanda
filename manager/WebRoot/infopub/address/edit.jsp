<%@page import="com.wondersgroup.infopub.bean.InfopubAddress"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubArea"%>
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
    InfopubAddress infopubAddress = (InfopubAddress) request.getAttribute(InfopubAddress.INFOPUB_ADDRESS);
    InfopubArea infopubArea = (InfopubArea) request.getAttribute(InfopubArea.INFOPUB_AREA);
    if (infopubAddress == null)
        infopubAddress = new InfopubAddress(); 
    if (infopubArea == null)
        infopubArea = new InfopubArea(); 
    String province = infopubArea.getStAreaName();
    String city = infopubAddress.getStCity();
    String district = infopubAddress.getStDistrict();
    String street = infopubAddress.getStStreet();		
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
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=InfopubAddress.ST_ADDRESS_ID%>"
		   id="stSampleId" value="<%=infopubAddress.stAddressId2Html()%>" />          
          
          <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>地址别名：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.stLabel2Html() %>"
						name="<%=InfopubAddress.ST_LABEL%>" required>
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>经度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.nmLng2Html(10) %>"
						name="<%=InfopubAddress.NM_LNG %>" required>
				</div>
			</div>
			 <div class="row cl">
			
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>纬度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.nmLat2Html(10) %>"
						name="<%=InfopubAddress.NM_LAT%>" required>
				</div> 
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>省：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<select type="text" class="input-text" id="deviceProvince"
						name="deviceProvince" onchange="cityInit()" required>
					</select>
				</div> 
			</div>
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>市：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<select type="text" class="input-text" id="deviceCity" onchange="districtInit()"
						name="<%=InfopubAddress.ST_CITY%>" required>
					</select>
				</div> 
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>区：</label>
				<div class="formControls col-xs-4 col-sm-4">
						<select name="<%=InfopubAddress.ST_DISTRICT%>" 
						id="deviceDistrict" class="input-text" onchange="streetInit()" required>
						</select>
				</div>
				
			</div>
			<div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>街道：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<select name="<%=InfopubAddress.ST_STREET %>"  
						class="input-text" id="deviceStreet"  required>
					</select>

				</div>
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>详细地址：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubAddress.stAddress2Html() %>"
						name="<%=InfopubAddress.ST_ADDRESS %>" required>
				</div>
			</div>
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

$(function(){
    provinceInit();
});

//省
function provinceInit(){
	var province = "<%=province%>";
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do',
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceProvince = result.data.province_list;
			if (null != deviceProvince &&　deviceProvince.length > 0) {
	             for(var i = 0; i < deviceProvince.length; i++){
	             	if(province.toString() != "null"){
	             		if(province.toString() == deviceProvince[i].area_name){
	             			$("#deviceProvince").append("<option value='"+deviceProvince[i].area_id+"' selected='selected'>"+deviceProvince[i].area_name+"</option>");
	             		}else{
		             		$("#deviceProvince").append("<option value='"+deviceProvince[i].area_id+"'>"+deviceProvince[i].area_name+"</option>");
	             		}
	             	}else{
	             		if("" == deviceProvince[i].area_name){
	             			$("#deviceProvince").append("<option value='"+deviceProvince[i].area_id+"' selected='selected'>"+deviceProvince[i].area_name+"</option>");
	             		}else{
		             		$("#deviceProvince").append("<option value='"+deviceProvince[i].area_id+"'>"+deviceProvince[i].area_name+"</option>");
	             		}
	             	}	
	             }
	        }
	        cityInit();
		},
		error : function(){
		}	
	});
}

//市
function cityInit(){
	var city = "<%=city%>";
	var deviceProvince = $("#deviceProvince").val();
	if(deviceProvince == null){
		deviceProvince = "";
	}
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do?deviceProvince='+deviceProvince,
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceCity = result.data.city_list;
			//删除初始化时候的option
			$("#deviceCity").find("option").remove();
			//根据省id重新加载option
			if (null != deviceCity && deviceCity.length > 0) {
	             for(var i = 0; i < deviceCity.length; i++){
	             	if(city.toString() != "null"){
	             		if(city.toString() == deviceCity[i].area_name){
	             			$("#deviceCity").append("<option value='"+deviceCity[i].area_id+"' selected='selected'>"+deviceCity[i].area_name+"</option>");
	             		}else{
		             		$("#deviceCity").append("<option value='"+deviceCity[i].area_id+"'>"+deviceCity[i].area_name+"</option>");
	             		}
	             	}else{
	             		if("" == deviceCity[i].area_name){
	             			$("#deviceCity").append("<option value='"+deviceCity[i].area_id+"' selected='selected'>"+deviceCity[i].area_name+"</option>");
	             		}else{
		             		$("#deviceCity").append("<option value='"+deviceCity[i].area_id+"'>"+deviceCity[i].area_name+"</option>");
	             		}
	             	}	
	             }
	        }
	        districtInit();
		},
		error : function(){
		}	
	});
}


//区域查询
function districtInit(){
	var district = "<%=district%>";
	var deviceCity = $("#deviceCity").val();
	if(deviceCity == null){
		deviceCity = "";
	}
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do?deviceCity='+deviceCity,
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceDistrict = result.data.district_list;
			//console.log(deviceDistrict);
			//删除初始化时候的option
			$("#deviceDistrict").find("option").remove();
			//根据市id重新加载option
			if (deviceDistrict.length > 0) {
	             for(var i = 0; i < deviceDistrict.length; i++){
	             	if(district.toString() != "null"){
	             		if(district.toString() == deviceDistrict[i].area_name){
	             			$("#deviceDistrict").append("<option value='"+deviceDistrict[i].area_id+"' selected='selected'>"+deviceDistrict[i].area_name+"</option>");
	             		}else{
		             		$("#deviceDistrict").append("<option value='"+deviceDistrict[i].area_id+"'>"+deviceDistrict[i].area_name+"</option>");
	             		}
	             	}else{
	             		if("" == deviceDistrict[i].area_name){
	             			$("#deviceDistrict").append("<option value='"+deviceDistrict[i].area_id+"' selected='selected'>"+deviceDistrict[i].area_name+"</option>");
	             		}else{
		             		$("#deviceDistrict").append("<option value='"+deviceDistrict[i].area_id+"'>"+deviceDistrict[i].area_name+"</option>");
	             		}
	             	}	
	             }
	        }
	        streetInit();
		},
		error : function(){
		}	
	});
}

 
//街道查询	
function streetInit() {
	var street = "<%=street%>";
	var deviceDistrict = $("#deviceDistrict").val();
	if(deviceDistrict == null){
		deviceDistrict = "";
	}
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do?deviceDistrict='+deviceDistrict,
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceStreet = result.data.street_list;
			//console.log(deviceStreet);
			//删除初始化时候的option
			$("#deviceStreet").find("option").remove();
			//根据市id重新加载option
			if (null != deviceStreet && deviceStreet.length > 0) {
	             for(var i = 0; i < deviceStreet.length; i++){
	             	if(street.toString() != "null"){
	             		if(street.toString() == deviceStreet[i].area_name){
	             			$("#deviceStreet").append("<option value='"+deviceStreet[i].area_id+"' selected='selected'>"+deviceStreet[i].area_name+"</option>");
	             		}else{
		             		$("#deviceStreet").append("<option value='"+deviceStreet[i].area_id+"'>"+deviceStreet[i].area_name+"</option>");
	             		}
	             	}else{
	             		if("" == deviceStreet[i].area_name){
	             			$("#deviceStreet").append("<option value='"+deviceStreet[i].area_id+"' selected='selected'>"+deviceStreet[i].area_name+"</option>");
	             		}else{
		             		$("#deviceStreet").append("<option value='"+deviceStreet[i].area_id+"'>"+deviceStreet[i].area_name+"</option>");
	             		}
	             	}	
	             }
	        }
		},
		error : function(){
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
			url : webRoot + "/infopub/infopubAddress/save.do",
			data : $('#form-device-add').serialize(),// 你的formid
			error : function(request) {
				layer.msg("地址信息添加失败", {
					icon : 1,
					time : 1000
				});
			},
			success : function(data) {
				layer.msg("地址信息添加成功", {
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