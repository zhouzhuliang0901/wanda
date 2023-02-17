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
<title>查看</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
			<input type="hidden" name="<%=InfopubDeviceInfo.ST_DEVICE_ID%>"
				id="stSampleId" value="<%=infopubDeviceInfo.stDeviceId2Html()%>" />
			<input type="hidden" value="<%=infopubOnoff.stPeriod2Html()%>"
				id="weekMsg" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">设备名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceName2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_NAME %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">设备编号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceCode2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_CODE%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">设备IP：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceIp2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_IP %>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">设备MAC：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceMac2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_MAC%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">详细地址：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceAddress2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_ADDRESS %>"
						disabled="disabled">
				</div>
				<%-- <label class="form-label col-xs-4 col-sm-2">消息通道：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stChannel2Html() %>"
						name="<%=InfopubDeviceInfo.ST_CHANNEL%>" disabled="disabled">
				</div> --%>

			</div>

			<%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">经度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.nmLng2Html(10) %>"
						name="<%=InfopubDeviceInfo.NM_LNG%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">纬度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.nmLat2Html(10) %>"
						name="<%=InfopubDeviceInfo.NM_LAT%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">开机时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOnoff.stOnTime2Html()%>" id="time_on"
						name="<%=InfopubOnoff.ST_ON_TIME%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">关机时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOnoff.stOffTime2Html()%>" id="time_off"
						name="<%=InfopubOnoff.ST_OFF_TIME%>" disabled="disabled">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">开机周期：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<dl class="permission-list">
						<dt>
							<label>周期</label>
						</dt>
						<dd>
							<label><input type="checkbox" value="1" name="week[]"
								disabled="disabled">周一 </label> <label><input
								type="checkbox" value="2" name="week[]" disabled="disabled">周二
							</label> <label><input type="checkbox" value="3" name="week[]"
								disabled="disabled">周三 </label> <label><input
								type="checkbox" value="4" name="week[]" disabled="disabled">周四
							</label> <label><input type="checkbox" value="5" name="week[]"
								disabled="disabled">周五 </label> <label><input
								type="checkbox" value="6" name="week[]" disabled="disabled">周六</label>
							<label><input type="checkbox" value="7" name="week[]"
								disabled="disabled">周日</label>
						</dd>
					</dl>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">创建时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.dtCreate2Html("yyyy-MM-dd HH:mm")%>"
						disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2">更新时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.dtUpdate2Html("yyyy-MM-dd HH:mm") %>"
						disabled="disabled">
				</div>
			</div>

		   <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">设备类型：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stTypeId2Html() %>"
						disabled="disabled">
				</div>
			</div> --%>
				<div class="mt-20">
				<table width="100%"
					class="table table-border table-bordered table-bg table-hover table-sort"
					id="itemList1">
					<thead>
						<tr class="text-c">
							<th width="15"><input name="" type="checkbox">
							</th>
							<th width="80">事项编码</th>
							<th width="100">事项名称</th>
							<!-- <th width="40">操作</th> -->
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<!-- <div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="layer_close();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div> -->

		</form>
	</div>
	<script type="text/javascript"
			src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript"
			src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
		<script type="text/javascript"
			src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=webRoot%>/sms/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
		<script type="text/javascript"
			src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
		<script type="text/javascript"
			src="<%=webRoot%>/sms/lib/common/common.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
	var table1 = $('#itemList1').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 1, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild1"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stItemNo",
                   "render":function(data, type, full, meta){
                      return  setNullData1(data);
                }},
                { "data": "stMainName",
                   "render":function(data, type, full, meta){
                       return setNullData1(data) ;
                }},
                /* {
                 "data": "stItemId",
                 "render": function (data, type, full, meta) {
                        var returnInfo1 = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"item_remove('"+full.stMainName+"->移除','/app/oauth2ClientItem/remove.do','"+data+"')\" href=\"javascript:;\" title=\"移除\">移除</a>";
                        return returnInfo1;
                    },
                  "bSortable": false
                } */
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    //$(row).children('td').eq(3).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/app/oauth2Client/deviceOCItemlist.do',
		    type:"POST",
		    data: function(d){
		      var stItemNo1 = $("#searchCode1").val();
		      d.stItemNo1 = stItemNo1;
		      var stMainName1 = $("#searchName1").val();
		      d.stMainName1 = stMainName1;
              var stDeviceId1 = '<%=infopubDeviceInfo.stDeviceId2Html()%>';
				d.stDeviceId1 = stDeviceId1;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#itemList1").removeAttr("style"); 
});
// 未配置数据
function setNullData1(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}
	
	
	
	
$(function(){
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
});
</script>
</body>
</html>