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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	 String webRoot = AppContext.webRootPath;
	 String stMachineId = (String)request.getAttribute("stMachineId");
	 String deviceApplyId = (String)request.getAttribute("stApplyId");
	 String base64 = (String)request.getAttribute("base64");
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
<title>??????</title>
</head>
<body>
<br>
<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;margin-bottom: -14px;"">????????????</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;margin-bottom: -5px;border-color: #a9a9a9;""/>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		<%-- <input type="hidden" name="deviceApplyId"
		   id="ST_DEVICE_APPLY_ID" value="<%=deviceApplyId%>" /> --%>
		   
		  <input type="hidden" name="<%=SelmDeviceAlink.ST_MACHINE_ID%>"
		   id="ST_MACHINE_ID" value="<%=selmDeviceAlink.stMachineId2Html()%>" />
          <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_AREA" value="<%=selmDeviceAlink.stAreaId2Html()%>" >
					
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_ADDRESS" value="<%=selmDeviceAlink.stAddressId2Html()%>" >
				</div>
				
			</div>
			
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_SPECINICADDRESS" value="<%=selmDeviceAlink.stDeviceAddress2Html()%>" >
				</div>
				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_NETWORK" value="<%=selmDeviceAlink.stDeviceName2Html()%>" >
				</div>				
			</div>
			
			 <div class="row cl">
			 	<label class="form-label col-xs-4 col-sm-2">
				MAC???</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_MAC" value="<%=selmDeviceAlink.stDeviceMac2Html() %>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				IP???</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_IP" value="<%=selmDeviceAlink.stDeviceIp2Html()%>">
				</div>				
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_PRODUCER" value="<%=selmDeviceAlink.stDesc2Html()%>" >
						
				</div>				
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text" name="ST_DEVICETYPE" value="<%=selmDeviceAlink.stTypeId2Html()%>" >
						
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>??????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<c:set var="ybzc"><%=selmDeviceAlink.getNmYbzc() %></c:set>
					<c:choose>
						<c:when test="${ ybzc == '1' }">
							<input name="NM_YBZC" type="radio" value="1" checked="checked">???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_YBZC" type="radio" value="0" >???	
						</c:when>
						<c:otherwise>
							<input name="NM_YBZC" type="radio" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_YBZC" type="radio" value="0" checked="checked">???	
						</c:otherwise>
					</c:choose>
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red"></span>??????????????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="button" value="??????" style="border: 1px solid rgb(133,133,133); border-radius:3px;height:24px;width:45px;" onclick="setImageData('<%=base64%>')"></input>
				</div>		
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<c:set var="gpy"><%=selmDeviceAlink.getNmGpy() %></c:set>
					<c:choose>
						<c:when test="${ gpy == '1'}">
							<input name="NM_GPY" type="radio" value="1" checked="checked">???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_GPY" type="radio" value="0" >???	
						</c:when>
						<c:otherwise>
							<input name="NM_GPY" type="radio" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_GPY" type="radio" value="0" checked="checked">???	
						</c:otherwise>
					</c:choose>
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<c:set var="jzzqz"><%=selmDeviceAlink.getNmJzzqz() %></c:set>
					<c:choose>
						<c:when test="${ jzzqz == '1'}">
							<input name="NM_JZZQU" type="radio" value="1" checked="checked">???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_JZZQU" type="radio" value="0" >???	
						</c:when>
						<c:otherwise>
							<input name="NM_JZZQU" type="radio" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_JZZQU" type="radio" value="0" checked="checked">???	
						</c:otherwise>
					</c:choose>
					
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<c:set var="jzzzk"><%=selmDeviceAlink.getNmJzzzk() %></c:set>
					<c:choose>
						<c:when test="${ jzzzk == '1'}">
							<input name="NM_JZZZK" type="radio" value="1" checked="checked">???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_JZZZK" type="radio" value="0" >???	
						</c:when>
						<c:otherwise>
							<input name="NM_JZZZK" type="radio" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_JZZZK" type="radio" value="0" checked="checked">???	
						</c:otherwise>
					</c:choose>
					
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<c:set var="net"><%=selmDeviceAlink.getStNetwork() %></c:set>
					<c:choose>
						<c:when test="${ net  == '????????????'}">
							<input name="NM_NETWORK" type="radio" value="????????????" checked="checked">????????????&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_NETWORK" type="radio" value="?????????" >?????????	
						</c:when>
						<c:otherwise>
							<input name="NM_NETWORK" type="radio" value="????????????" >????????????&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_NETWORK" type="radio" value="?????????" checked="checked">?????????	
						</c:otherwise>
					</c:choose>
					
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<c:set var="duty"><%=selmDeviceAlink.getNmDuty() %></c:set>
					<c:choose>
						<c:when test="${ duty == '1'}">
							<input name="NM_DUTY" type="radio" value="1" checked="checked">???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_DUTY" type="radio" value="0" >???	
						</c:when>
						<c:otherwise>
							<input name="NM_DUTY" type="radio" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_DUTY" type="radio" value="0" checked="checked">???	
						</c:otherwise>
					</c:choose>
					
				</div>	
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????24????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<c:set var="hours"><%=selmDeviceAlink.getNm24Hours() %></c:set>
					<c:choose>
						<c:when test="${ hours == '1'}">
							<input name="NM_24HOURS" type="radio" value="1" checked="checked">???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_24HOURS" type="radio" value="0" >???
						</c:when>
						<c:otherwise>
							<input name="NM_24HOURS" type="radio" value="1" >???&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="NM_24HOURS" type="radio" value="0" checked="checked">???	
						</c:otherwise>
					</c:choose>
					
				</div>	
			</div>
			
           <!--  <div class="row cl" style="text-align:center;">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit"  
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> ??????
                    </button>
                    <a name="addDeviceInfo" id="addDeviceInfo" class="btn btn-primary radius" onclick="article_save_submit()" >
				 <i class="Hui-iconfont">&#xe632;</i>??????
				</a>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button onClick="layer_close();" class="btn btn-danger radius"
                        type="button">&nbsp;&nbsp;??????&nbsp;&nbsp;</button>
                </div>
            </div> -->
                                         
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

var stDeviceId = "<%=selmDeviceAlink.stMachineId2Html()%>";

var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
var isAdmin = false;
// ????????????????????????
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>

// ??????????????????
function setImageData(data){
   	layer.open({
         type: 1,//Page?????????  ???1??????????????????   ???2??????????????????????????????????????????
         title: false,
         closeBtn: 1, //0????????????????????? 1??????
         shadeClose: true, //??????????????????
         area: ["85%","90%"],//???????????????
         content: '<div style=\"text-align:center;padding-top:40px;padding-bottom:40px;\"><img src=data:image/png;base64,'+data+' alt=\"?????????\" style=\"width: 1200px;height: 1600px; \"></div>'//??????
    });
}
	
</script>
</body>
</html>