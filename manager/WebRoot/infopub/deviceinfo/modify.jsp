<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubOnoff"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubWorkspace"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="wfc.service.config.Config"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String webRoot = AppContext.webRootPath;
	InfopubDeviceInfo infopubDeviceInfo = (InfopubDeviceInfo) request
			.getAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO);
	if (infopubDeviceInfo == null)
		infopubDeviceInfo = new InfopubDeviceInfo();
	JSONObject obj = (JSONObject) JSONObject.parseObject(Config.get("device.type.0001"));
	///////////////////////////////////////////////////
	JSONObject jsonContent = JSONObject.parseObject((String)request.getAttribute("clContent"));
	JSONObject contentStr = new JSONObject();
	if(jsonContent!=null){
		if(jsonContent.getString("url") != null){contentStr.put("url", jsonContent.getString("url"));}else{contentStr.put("url", "empty");}
		if(jsonContent.getString("hasCmCapture") != null){contentStr.put("hasCmCapture", jsonContent.getString("hasCmCapture"));}else{contentStr.put("hasCmCapture", "empty");}
		if(jsonContent.getString("hasDsoFrame") != null){contentStr.put("hasDsoFrame", jsonContent.getString("hasDsoFrame"));}else{contentStr.put("hasDsoFrame", "empty");}
		if(jsonContent.getString("idCardType") != null){contentStr.put("idCardType", jsonContent.getString("idCardType"));}else{contentStr.put("idCardType", "empty");}
		if(jsonContent.getString("ssCardType") != null){contentStr.put("ssCardType", jsonContent.getString("ssCardType"));}else{contentStr.put("ssCardType", "empty");}
		if(jsonContent.getString("liveDetection") != null){contentStr.put("liveDetection", jsonContent.getString("liveDetection"));}else{contentStr.put("liveDetection", "empty");}
		if(jsonContent.getString("liveDetectionPort") != null){contentStr.put("liveDetectionPort", jsonContent.getString("liveDetectionPort"));}else{contentStr.put("liveDetectionPort", "empty");}
		if(jsonContent.getString("signaturePen") != null){contentStr.put("signaturePen", jsonContent.getString("signaturePen"));}else{contentStr.put("signaturePen", "empty");}
		if(jsonContent.getString("qrCodeType") != null){contentStr.put("qrCodeType", jsonContent.getString("qrCodeType"));}else{contentStr.put("qrCodeType", "empty");}
		if(jsonContent.getString("qrCodePort") != null){contentStr.put("qrCodePort", jsonContent.getString("qrCodePort"));}else{contentStr.put("qrCodePort", "empty");}
		if(jsonContent.getString("ukeyType") != null){contentStr.put("ukeyType", jsonContent.getString("ukeyType"));}else{contentStr.put("ukeyType", "empty");}
		if(jsonContent.getString("ukeyPort") != null){contentStr.put("ukeyPort", jsonContent.getString("ukeyPort"));}else{contentStr.put("ukeyPort", "empty");}
		if(jsonContent.getString("medicalType") != null){contentStr.put("medicalType", jsonContent.getString("medicalType"));}else{contentStr.put("medicalType", "empty");}
		if(jsonContent.getString("medicalPort") != null){contentStr.put("medicalPort", jsonContent.getString("medicalPort"));}else{contentStr.put("medicalPort", "empty");}
		if(jsonContent.getString("fileExts") != null){contentStr.put("fileExts", jsonContent.getString("fileExts"));}else{contentStr.put("fileExts", "empty");}
		if(jsonContent.getString("aiuiPort") != null){contentStr.put("aiuiPort", jsonContent.getString("aiuiPort"));}else{contentStr.put("aiuiPort", "empty");}
		if(jsonContent.getString("idleTime") != null){contentStr.put("idleTime", jsonContent.getString("idleTime"));}else{contentStr.put("idleTime", "empty");}
		if(jsonContent.getString("isKillTaskbar") != null){contentStr.put("isKillTaskbar", jsonContent.getString("isKillTaskbar"));}else{contentStr.put("isKillTaskbar", "empty");}
		if(jsonContent.getString("camera") != null){contentStr.put("camera", jsonContent.getString("camera"));}else{contentStr.put("camera", "empty");}
		if(jsonContent.getString("resolution") != null){contentStr.put("resolution", jsonContent.getString("resolution"));}else{contentStr.put("resolution", "empty");}
		if(jsonContent.getString("videoWeek") != null){contentStr.put("videoWeek", jsonContent.getString("videoWeek"));}else{contentStr.put("videoWeek", "empty");}
		if(jsonContent.getString("videoStartTime") != null){contentStr.put("videoStartTime", jsonContent.getString("videoStartTime"));}else{contentStr.put("videoStartTime", "empty");}
		if(jsonContent.getString("videoEndTime") != null){contentStr.put("videoEndTime", jsonContent.getString("videoEndTime"));}else{contentStr.put("videoEndTime", "empty");}
		if(jsonContent.getString("uniqueId") != null){contentStr.put("uniqueId", jsonContent.getString("uniqueId"));}else{contentStr.put("uniqueId", "empty");}
		if(jsonContent.getString("hasCall") != null){contentStr.put("hasCall", jsonContent.getString("hasCall"));}else{contentStr.put("hasCall", "empty");}
		if(jsonContent.getString("telephoneNumber") != null){contentStr.put("telephoneNumber", jsonContent.getString("telephoneNumber"));}else{contentStr.put("telephoneNumber", "empty");}
		if(jsonContent.getString("isCommunity") != null){contentStr.put("isCommunity", jsonContent.getString("isCommunity"));}else{contentStr.put("isCommunity", "empty");}
		
		if(jsonContent.getString("bookMedicalPort") != null){contentStr.put("bookMedicalPort", jsonContent.getString("bookMedicalPort"));}else{contentStr.put("bookMedicalPort", "empty");}
		if(jsonContent.getString("districtAndCounty") != null){contentStr.put("districtAndCounty", jsonContent.getString("districtAndCounty"));}else{contentStr.put("districtAndCounty", "empty");}
		if(jsonContent.getString("equipmentProvider") != null){contentStr.put("equipmentProvider", jsonContent.getString("equipmentProvider"));}else{contentStr.put("equipmentProvider", "empty");}
		if(jsonContent.getString("MAC") != null){contentStr.put("MAC", jsonContent.getString("MAC"));}else{contentStr.put("MAC", "empty");}
		if(jsonContent.getString("medicalName") != null){contentStr.put("medicalName", jsonContent.getString("medicalName"));}else{contentStr.put("medicalName", "empty");}
		if(jsonContent.getString("Outlets") != null){contentStr.put("Outlets", jsonContent.getString("Outlets"));}else{contentStr.put("Outlets", "empty");}
		if(jsonContent.getString("password") != null){contentStr.put("password", jsonContent.getString("password"));}else{contentStr.put("password", "empty");}
		if(jsonContent.getString("sensePort") != null){contentStr.put("sensePort", jsonContent.getString("sensePort"));}else{contentStr.put("sensePort", "empty");}
		if(jsonContent.getString("hasInternet") != null){contentStr.put("hasInternet", jsonContent.getString("hasInternet"));}else{contentStr.put("hasInternet", "empty");}		
	}else{
		contentStr.put("url", "");
		contentStr.put("hasCmCapture", "");
		contentStr.put("hasDsoFrame", "");
		contentStr.put("idCardType", "");
		contentStr.put("ssCardType", "");
		contentStr.put("liveDetection", "");
		contentStr.put("liveDetectionPort", "");
		contentStr.put("signaturePen", "");
		contentStr.put("qrCodeType", "");
		contentStr.put("qrCodePort", "");
		contentStr.put("ukeyType", "");
		contentStr.put("ukeyPort", "");
		contentStr.put("medicalType", "");
		contentStr.put("medicalPort", "");
		contentStr.put("fileExts", "");
		contentStr.put("aiuiPort", "");
		contentStr.put("idleTime", "");
		contentStr.put("isKillTaskbar", "");
		contentStr.put("camera", "");
		contentStr.put("resolution", "");
		contentStr.put("videoWeek", "");
		contentStr.put("videoStartTime", "");
		contentStr.put("videoEndTime", "");
		contentStr.put("uniqueId", "");
		contentStr.put("hasCall", "");
		contentStr.put("telephoneNumber", "");
		contentStr.put("isCommunity", "");
		
		contentStr.put("bookMedicalPort", "");
		contentStr.put("districtAndCounty", "");
		contentStr.put("equipmentProvider", "");
		contentStr.put("MAC", "");
		contentStr.put("medicalName", "");
		contentStr.put("Outlets", "");
		contentStr.put("password", "");
		contentStr.put("sensePort", "");
		contentStr.put("hasInternet", "");	
	}
	
	

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
<link
	href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
	rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>修改XML</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		<tbody id="tbody">  </tbody>
		<div class="row cl" id="tbody" >
		</div>
			<input type="hidden" name="<%=InfopubDeviceInfo.ST_DEVICE_ID%>"
			 value="<%=infopubDeviceInfo.stDeviceId2Html()%>" />
			
		<%-- 	<c:forEach items="${entrySet }" var="entry">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2" style = "width:30%"><span
						class="c-red"></span> ${entry.getKey() }：</label>
					<div class="formControls col-xs-4 col-sm-4">
						<input type="text" class="input-text"
							value=""
							name="${entry.getValue() }" >
					</div>
				</div>
			</c:forEach> --%>
				
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style = "padding-left: 125px">
					<button class="btn btn-primary radius" onclick="article_save_submit()">
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
		src="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script
		src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
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
/* {"list":[{"deptName":"应用程序加载的URL地址","empAnnualIncome":"url","empName":""},{"deptName":"是否有高拍仪","empAnnualIncome":"hasCmCapture","empName":"如果设置成Y则表示加载高拍仪驱动,其他则不加载，N：没有;","yes":"Y","no":"N"},{"deptName":"身份证驱动类型","empAnnualIncome":"idCardType","empName":"如果没有配置或者其他值均为默认"}]} */
var data = <%=obj%>;
var contentStr1 = <%=contentStr%>;
	var trStr = '';
	$.each(data,function(tmp,tb){
		$.each(tb,function(i,n){
			var empAnnualIncomeName = n.empAnnualIncome;
			var empAnnualIncomeValue = '';
			var temp = 'temp';
			$.each(contentStr1,function(k,v){
				if(k==empAnnualIncomeName){
					if(v == "empty"){
						temp = v;
					}else{
						empAnnualIncomeValue = v;
					}
				}
			});
			if(temp == "empty"){
				temp == 'temp';
				return;	
			}else{
				if(n.yes !=null){
					if(empAnnualIncomeValue=="Y"){
						trStr += '<tr height="25px" class="example">';//拼接处规范的表格形式
						trStr += '<td colspan="3" width="15%" align="center" style="padding-right: 19px;padding-bottom: 36px;">' + n.deptName + '</td>';
						trStr += '<td  class="formControls" style=" width: 20%;"><select class="input-text" name="' + empAnnualIncomeName +' " value="'+empAnnualIncomeValue+'"><option selected="selected">'+n.yes+'</option><option>'+n.no+'</option></td>';
			 			//trStr += '<td colspan="3" width="15%" align="center" style="padding-right: 19px;padding-bottom: 36px;">' + n.yes + '</td>';
			 			trStr += '<td style="padding-left: 19px">' + n.empName + '</td>';
			 			/* trStr += '<tr/>'; */
					}else if(empAnnualIncomeValue=="N"){
						trStr += '<tr height="25px" class="example">';//拼接处规范的表格形式
						trStr += '<td colspan="3" width="15%" align="center" style="padding-right: 19px;padding-bottom: 36px;">' + n.deptName + '</td>';
						trStr += '<td  class="formControls" style=" width: 20%;"><select class="input-text" name="' + empAnnualIncomeName +'"  value="'+empAnnualIncomeValue+'"><option>'+n.yes+'</option><option selected="selected">'+n.no+'</option></td>';
			 			trStr += '<td style="padding-left: 19px">' + n.empName + '</td>';
					}else{
						trStr += '<tr height="25px" class="example">';//拼接处规范的表格形式
						trStr += '<td colspan="3" width="15%" align="center" style="padding-right: 19px;padding-bottom: 36px;">' + n.deptName + '</td>';
						trStr += '<td  class="formControls" style=" width: 20%;"><select class="input-text" name="' + empAnnualIncomeName +'"  value="'+empAnnualIncomeValue+'"><option></option><option>'+n.yes+'</option><option>'+n.no+'</option></td>';
			 			trStr += '<td style="padding-left: 19px">' + n.empName + '</td>';
					}
	 			}else{
		 			trStr += '<tr height="25px" class="example">';//拼接处规范的表格形式
					trStr += '<td colspan="3" width="15%" align="center" style="padding-right: 19px;padding-bottom: 36px;">' + n.deptName + '</td>';
					trStr += '<td  class="formControls" style=" width: 20%;"><input  type="text"class="input-text" name="' + empAnnualIncomeName +'"  value="'+empAnnualIncomeValue+'"></td>';
		 			trStr += '<td style="padding-left: 19px">' + n.empName + '</td>';
	 			}
			}
			
			});
});
$("#tbody").html(trStr);
// 数据提交     
$.validator.setDefaults({
    submitHandler: function() {
      // 提交数据
      article_save_submit();
    }
});

/* $(function(){
	var htmlStr1 = '<select class="input-text" name="empAnnualIncome" style="width:250px">'
	+ '<option>"1"</option>'
	+ '<option>"2"</option>'
	+ '<option>"3"</option>'
	+ '<option>"4"</option>'
	+ '<option>"5"</option>'
	+ '</select>';
	$("#tbody").children('tr').eq(1).children('td').eq(1).html(htmlStr1);
	
	var htmlStr2 = '<select class="input-text" name="2222" style="width:250px">'
	+ '<option>"1"</option>'
	+ '<option>"2"</option>'
	+ '<option>"3"</option>'
	+ '<option>"4"</option>'
	+ '<option>"5"</option>'
	+ '</select>';
	$("#tbody").children('tr').eq(2).children('td').eq(1).html(htmlStr2); */
	//$("#tbody").children('tr').eq(1).children('td').eq(1).html();
    //console.log($("#tbody").children('tr').eq(1).children('td').eq(1).html());
    //console.log($("#tbody").children('tr').eq(1).html());
    //console.log($("tr:eq(1)  td:eq(1)").html());
//});



// 提交内容
function article_save_submit() {
	$.ajax({
		type : "POST",
		url : webRoot + "/infopub/deviceinfo/modifyoperate.do",
		data : $('#form-device-add').serialize(),// 你的formid
		
		success : function(data) {
			layer.msg("信息添加成功", {icon : 1, time : 1000});
		},
		error : function(request) {
            layer.msg("信息添加失败", {icon : 1, time : 1000});
		}
	});
	window.parent.location.reload();
}
</script>
</body>
</html>