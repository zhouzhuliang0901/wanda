<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.statistics.bean.SelmStatistics"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    SelmStatistics selmStatistics = (SelmStatistics) request.getAttribute(SelmStatistics.SELM_STATISTICS);
    if (selmStatistics == null)
        selmStatistics = new SelmStatistics();    
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
			<input type="hidden" name="<%=SelmStatistics.ST_STATISTICS_ID%>"
				id="stSampleId" value="<%=selmStatistics.stStatisticsId2Html()%>" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">业务标识：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stNetFlag2Html() %>"
						name="<%=SelmStatistics.ST_NET_FLAG %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">业务子标识：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stNetSubFlag2Html() %>"
						name="<%=SelmStatistics.ST_NET_SUB_FLAG%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">业务名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stName2Html() %>"
						name="<%=SelmStatistics.ST_NAME %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">业务总数：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.nmCount2Html(0) %>"
						name="<%=SelmStatistics.NM_COUNT%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">是否是外设：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text" 
                      <% if (selmStatistics.getNmOdevice().intValue() ==1){%>
                       value="是"<% } else { %>value="否" <%} %> disabled="disabled">
                </div>
				<label class="form-label col-xs-4 col-sm-2">排序：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.nmSort2Html(0) %>"
						name="<%=SelmStatistics.NM_SORT %>"
						disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">扩展字段1：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stExt12Html() %>"
						name="<%=SelmStatistics.ST_EXT1%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">扩展字段2：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmStatistics.stExt22Html() %>"
						name="<%=SelmStatistics.ST_EXT2%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
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
/* $(function(){
      // 勾选已选择的数据
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
}); */
</script>
</body>
</html>