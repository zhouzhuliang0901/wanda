<%@page import="com.wondersgroup.delivery.bean.SelmDelivery"%>
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
    SelmDelivery selmDelivery = (SelmDelivery) request.getAttribute(SelmDelivery.SELM_DELIVERY);
    if (selmDelivery == null)
        selmDelivery = new SelmDelivery(); 
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
    console.log('<%=selmDelivery.getStMachineId()%>')
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
		  <input type="hidden" name="<%=SelmDelivery.ST_DELIVERY_ID%>"
		   id="stSampleId" value="<%=selmDelivery.stDeliveryId2Html()%>" />
		    <input type="hidden" name="<%=SelmDelivery.ST_MACHINE_ID%>"
		   id="stSampleId" value="<%=selmDelivery.getStMachineId()%>" />    
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">设备柜号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stCabinetNo2Html() %>"
						name="<%=SelmDelivery.ST_CABINET_NO%>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">收件人姓名：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stReceiverName2Html() %>"
						name="<%=SelmDelivery.ST_RECEIVER_NAME%>">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">收件人手机号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stReceiverPhone2Html() %>"
						name="<%=SelmDelivery.ST_RECEIVER_PHONE %>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">收件人身份证号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stReceiverIdcard2Html() %>"
						name="<%=SelmDelivery.ST_RECEIVER_IDCARD%>">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">投件人姓名：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stSenderName2Html() %>"
						name="<%=SelmDelivery.ST_SENDER_NAME %>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">投件人身份证号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stSenderId2Html() %>"
						name="<%=SelmDelivery.ST_SENDER_ID %>">
				</div>
			</div>
			 <div class="row cl">
			 <label class="form-label col-xs-4 col-sm-2">投件人手机号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stSenderPhone2Html() %>"
						name="<%=SelmDelivery.ST_SENDER_PHONE %>">
				</div>
				<label class="form-label col-xs-4 col-sm-2">状态：</label>
				<div class="formControls col-xs-4 col-sm-4">
				 <span class="select-box"> 
                       <select name="<%=SelmDelivery.NM_STATUS%>"  class="select" id ="nmStatus">
                            <option value="0">待存</option>
                            <option value="1">待取</option>
                       </select> 
                    </span>  
				</div>
			</div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit"
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> 保存
                    </button>
                   <!--  <button onClick="selmDelivery_del();" class="btn btn-danger radius"
						type="button">&nbsp;&nbsp;删除&nbsp;&nbsp;</button> -->
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
var tem = '<%=selmDelivery.getNmType()%>';
$("#nmType option[value='"+tem+"']").attr("selected","selected");

var nmStatus = '<%=selmDelivery.getNmStatus()%>';
$("#nmStatus option[value='"+nmStatus+"']").attr("selected","selected");
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
		submitHandler : function() {
			// 提交数据
			article_save_submit();
		}
	});
	// 提交内容
	function article_save_submit() {
		$.ajax({
			type : "POST",
			url : webRoot + "/delivery/selmDelivery/save.do",
			data : $('#form-device-add').serialize(),// 你的formid
			error : function(request) {
				layer.msg("快递柜信息添加失败", {
					icon : 1,
					time : 1000
				});
			},
			success : function(data) {
				layer.msg("快递柜信息添加成功", {
					icon : 1,
					time : 1000
				});
			}
		});
		setTimeout(function() {
			window.parent.location.reload();
		}, 1000);
	}
var id = '<%=selmDelivery.getStDeliveryId()%>'
// 删除
function selmDelivery_del(){
alert(id)
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/delivery/selmDelivery/remove.do',
            dataType:'json',
            data:{'stDeliveryId':id},
            success : function(myObject) {
                 layer.msg(myObject.msg,{icon:1,time:1000});
             },
            error : function() {
                layer.msg(myObject.msg,{icon:1,time:1000});
            }
        });
       setTimeout(function() {
			window.parent.location.reload();
		}, 1000);
    });
}
</script>
</body>
</html>