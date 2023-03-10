<%@page import="com.wondersgroup.serverApply.bean.SelmServerApply"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceType"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	 String webRoot = AppContext.webRootPath;
	
    SelmServerApply selmServerApply = (SelmServerApply) request.getAttribute(SelmServerApply.SELM_SERVER_APPLY);
    if (selmServerApply == null)
        selmServerApply = new SelmServerApply(); 
        
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
<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;margin-bottom: -14px;">????????????</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;margin-bottom: -5px;border-color: #a9a9a9;""/>
	<div class="page-container" style="overflow-x:hidden;">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=SelmServerApply.ST_APPLY_ID%>"
		   id="stSampleId" value="<%=selmServerApply.stApplyId2Html()%>" />
          <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stApplyOrganName2Html() %>"
						name="<%=SelmServerApply.ST_APPLY_ORGAN_NAME%>" required>
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserName2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_NAME%>" required>
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserPhone2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_PHONE%>" required>
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserMobile2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_MOBILE%>" >
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				???????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserEmail2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_EMAIL%>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
				 <input type="text"
				onfocus="WdatePicker()"
				id="startDate" class="input-text Wdate" style="width:100%;" autocomplete="off" 
				value="<%=selmServerApply.dtUpCreate2Html("yyyy-MM-dd")%>"
						name="<%=SelmServerApply.DT_UP_CREATE%>" required>
					
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>?????????????????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:260%;height:100px;padding-top:1px;font-size:14px;" type="textarea" class="input-text"
						value="<%=selmServerApply.stServerContent2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_CONTENT%>" placeholder="??????????????????" required><%=selmServerApply.stServerContent2Html() %></textarea>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				?????????</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:260%;height:100px;padding-top:1px;font-size:14px;" type="text" class="input-text"
						value="<%=selmServerApply.stExt12Html() %>"
						name="<%=SelmServerApply.ST_EXT1%>" placeholder="??????"><%=selmServerApply.stExt12Html() %></textarea>
				</div>
			</div>
				<div style="padding-top: 10px;font-size: 18px;padding-left: 79px;font-weight: 600;margin-bottom: -14px;"">????????????</div>
			<hr style="width: 93%;margin-left: 78px;font-weight: 600;border-color: #a9a9a9;"/>
			
			
			
			<div class="mt-20" style="width: 94%; margin-left: 74px;">
				<table width="100%"
					class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
					<thead>
						<tr class="text-c">
							<th width="6%" ><input name="" type="checkbox" ></th>
							<th width="14%">??????</th>
							<th width="18%">????????????</th>
							<th width="11%">MAC</th>
							<th width="9%">??????????????????</th>					
							<th width="9%">??????????????????</th>
							<th width="9%">??????????????????</th>
							<th width="9%">??????????????????</th>
							
						</tr>
					</thead> 
					<tbody>
					</tbody>
				</table>
			</div>
			
			 <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5">
                    <button type="submit"
                        class="btn btn-primary radius" type="button" onClick="layer_close();">
                        <i class="Hui-iconfont">&#xe632;</i>??????
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   
                </div>
            </div>
                                        
		</form>
	</div>
</div>
<script type="text/javascript"
	src="<%=webRoot%>/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>  
<script type="text/javascript"
	src="<%=webRoot%>/sms/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
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

var isAdmin = false;
// ????????????????????????
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
	//initData()
   // ????????????
   $("#form-device-add").validate();
   $("#time_off,#time_on").prop("readonly", true).timepicker({
				timeText : '??????',
				hourText : '??????',
				minuteText : '??????',
				currentText : '??????',
				closeText : '??????',
				showSecond : false, //?????????  
				timeFormat : 'HH:mm' //???????????????  
			});
			// ????????????????????????
			$("input:checkbox").each(function() {
				var weekMsg = $("#weekMsg").val();
				var weeks = new Array();
				weeks = weekMsg.split("");
				for ( var i in weeks) {
					var day = 1;
					if (weeks[i] == 1) {
						day = parseInt(i) + 1;
					} else {
						day = 0;
					}
					if ($(this).attr('value') == day) {
						$(this).prop("checked", true);
					}
				}
			});
		});





var table = $('#typeList').DataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// ????????????????????????
    ],
    "order": [[ 4, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stMachineId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stAuditUserId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stApplyId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stExt1",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stMachineId",
                   "render":function(data, type, full, meta){
                       return "??????";
                }},
                { "data": "nmStatus",
                   "render":function(data, type, full, meta){
                       return setNullDataStatus(data);
                }},
                { "data": "stExt2",
                   "render":function(data, type, full, meta){
                       return setNullDataStatus(data);
                }},
                 { "data": "stDesc",
                   "render":function(data, type, full, meta){
                       return setNullDataStatus(data);
                }},
                 
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                    $(row).children('td').eq(6).attr('style', 'text-align: center;');
                    $(row).children('td').eq(7).attr('style', 'text-align: center;');
                                   
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		    url:webRoot+'/serverApply/selmServerApply/stDeviceList.do',
		    type:"POST",
		    data: function(d){
		      d.stApplyId = "<%=selmServerApply.stApplyId2Html()%>";
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#typeList").removeAttr("style"); 
});


// ???????????????
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}

function setNullDataStatus(data){
    if(data == 0){
        return '???';
    } else if(data == 1) {
        return '???';
    }
}
	
	
function checkItem(title,url,id){
		var index = layer.open({
	        type: 2,
	        title: title,
	        content: webRoot+url+'?ST_DEVICE_ID='+id,
	    });
	    layer.full(index);
	}

</script>
</body>
</html>