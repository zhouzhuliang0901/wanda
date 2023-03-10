<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfoExt"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubOnoff"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubDeviceInfo infopubDeviceInfo = (InfopubDeviceInfo) request
            .getAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO);
    InfopubOnoff infopubOnoff = (InfopubOnoff) request
            .getAttribute(InfopubOnoff.INFOPUB_ONOFF);
    if (infopubDeviceInfo == null)
        infopubDeviceInfo = new InfopubDeviceInfo();
    if (infopubOnoff == null)
        infopubOnoff = new InfopubOnoff();      
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
<title>??????</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
			<input type="hidden" name="<%=InfopubDeviceInfo.ST_DEVICE_ID%>"
				id="stSampleId" value="<%=infopubDeviceInfo.stDeviceId2Html()%>" />
			<input type="hidden" value="<%=infopubOnoff.stPeriod2Html()%>"
				id="weekMsg" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceName2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_NAME %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceCode2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_CODE%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">??????IP???</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceIp2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_IP %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">??????MAC???</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceMac2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_MAC%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceAddress2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_ADDRESS %>"
						disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stChannel2Html() %>"
						name="<%=InfopubDeviceInfo.ST_CHANNEL%>" disabled="disabled">
				</div>

			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.nmLng2Html(0) %>"
						name="<%=InfopubDeviceInfo.NM_LNG%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.nmLat2Html(0) %>"
						name="<%=InfopubDeviceInfo.NM_LAT%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOnoff.stOnTime2Html()%>" id="time_on"
						name="<%=InfopubOnoff.ST_ON_TIME%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOnoff.stOffTime2Html()%>" id="time_off"
						name="<%=InfopubOnoff.ST_OFF_TIME%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<dl class="permission-list">
						<dt>
							<label>??????</label>
						</dt>
						<dd>
							<label><input type="checkbox" value="1" name="week[]"
								disabled="disabled">?????? </label> <label><input
								type="checkbox" value="2" name="week[]" disabled="disabled">??????
							</label> <label><input type="checkbox" value="3" name="week[]"
								disabled="disabled">?????? </label> <label><input
								type="checkbox" value="4" name="week[]" disabled="disabled">??????
							</label> <label><input type="checkbox" value="5" name="week[]"
								disabled="disabled">?????? </label> <label><input
								type="checkbox" value="6" name="week[]" disabled="disabled">??????</label>
							<label><input type="checkbox" value="7" name="week[]"
								disabled="disabled">??????</label>
						</dd>
					</dl>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.dtCreate2Html("yyyy-MM-dd HH:mm")%>"
						disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.dtUpdate2Html("yyyy-MM-dd HH:mm") %>"
						disabled="disabled">
				</div>
			</div>

		   <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stTypeId2Html() %>"
						disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
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
		src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
	<!--?????????????????????????????????????????????-->
	<script type="text/javascript">
$(function(){
      // ????????????????????????
     $("input:checkbox").each(function(){ 
          var weekMsg = $("#weekMsg").val();
          var weeks= new Array();
          weeks = weekMsg.split("");
          for (var i in weeks) {
              var day = 1;
              if (weeks[i] == 1){
                  day = parseInt(i)+1;
              } else {
                  day = 0;
              }
              if ($(this).attr('value') == day){
                $(this).prop("checked",true);
              }
          }
      }); 
});
</script>
</body>
</html>