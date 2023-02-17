<%@page import="com.wondersgroup.delivery.bean.SelmDelivery"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
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
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>查看</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=SelmDelivery.ST_DELIVERY_ID%>"
		   id="stSampleId" value="<%=selmDelivery.stDeliveryId2Html()%>" />   
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">设备柜号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stCabinetNo2Html() %>"
						name="<%=SelmDelivery.ST_CABINET_NO%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">收件人姓名：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stReceiverName2Html() %>"
						name="<%=SelmDelivery.ST_RECEIVER_NAME%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">收件人手机号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stReceiverPhone2Html() %>"
						name="<%=SelmDelivery.ST_RECEIVER_PHONE %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">收件人身份证号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stReceiverIdcard2Html() %>"
						name="<%=SelmDelivery.ST_RECEIVER_IDCARD%>" disabled="disabled">
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">投件人姓名：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stSenderName2Html() %>"
						name="<%=SelmDelivery.ST_SENDER_NAME %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">投件人身份证号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stSenderId2Html() %>"
						name="<%=SelmDelivery.ST_SENDER_ID %>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">投件人手机号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.stSenderPhone2Html() %>"
						name="<%=SelmDelivery.ST_SENDER_PHONE %>"disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">状态：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
					<%if (selmDelivery.getNmStatus().intValue()==0) {%>
                        value="待存" <%}else if (selmDelivery.getNmStatus().intValue()==1){ %>
                        value="待取"<%}else { %>
                        value="已取"<%}%>
                        name="<%=SelmDelivery.NM_STATUS %>" disabled="disabled">
						<%-- value="<%=selmDelivery.nmStatus2Html(0) %>"
						name="<%=SelmDelivery.NM_STATUS %>" disabled="disabled"> --%>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">创建时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.dtCreate2Html("yyyy-MM-dd HH:mm") %>"
						name="<%=SelmDelivery.DT_CREATE %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">投放时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.dtStore2Html("yyyy-MM-dd HH:mm") %>"
						name="<%=SelmDelivery.DT_STORE %>" disabled="disabled">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">取走时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmDelivery.dtTake2Html("yyyy-MM-dd HH:mm") %>"
						name="<%=SelmDelivery.DT_TAKE %>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
						<!-- <button onClick="selmDelivery_edit();" class="btn btn-primary radius"
						type="button">&nbsp;&nbsp;编辑&nbsp;&nbsp;</button>
						<button onClick="selmDelivery_del();" class="btn btn-danger radius"
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
		src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
var id = '<%=selmDelivery.getStDeliveryId()%>'
var title = '<%=selmDelivery.getStCabinetNo()%>'
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
// 编辑
function selmDelivery_edit(){
    var index = layer.open({
        type: 2,
        title: title+"编辑",
        content: webRoot+'/delivery/selmDelivery/edit.do?ST_DELIVERY_ID='+id
    });
    layer.full(index);
}
</script>
</body>
</html>