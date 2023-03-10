<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfoExt"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubOnoff"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceType"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%
    String webRoot = AppContext.webRootPath;
    InfopubDeviceType infopubDeviceType = (InfopubDeviceType) request
			.getAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE);
	if (infopubDeviceType == null)
		infopubDeviceType = new InfopubDeviceType();      
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
<link rel="stylesheet" type="text/css"
    href="<%=webRoot%>/sms/lib/iconfont/iconfont.css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>??????</title>
</head>
<body>
	<div class="page-container">
		
		<!-- <div class="text-c">
			??????????????? <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
		    <input type="text" name="" id="searchName" placeholder=" ????????????" style="width:250px"
				class="input-text">
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> ??????
			</button><a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="??????"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
		</div> -->
		
		<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							????????????</a>
					<a class="btn btn-primary radius" onclick="device_add('????????????','/infopub/deviceinfotype/add.do')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> ??????</a> 
		    </span>
		</div> -->
		
		
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=InfopubDeviceType.ST_TYPE_ID%>" id="stSampleId" value="<%=infopubDeviceType.stTypeId2Html()%>" />          
          <%-- <input type="hidden" name="<%=InfopubDeviceType.ST_PARENT_TYPE_ID%>" id="stParentTypeId" value="<%=infopubDeviceType.stParentTypeId2Html()%>" /> --%>
          <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><!-- <span class="c-red">*</span> -->
                                                  ???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stTypeName2Html() %>" 
                        name="<%=InfopubDeviceType.ST_TYPE_NAME %>" disabled="disabled">
                </div>
               <label class="form-label col-xs-4 col-sm-2"><!-- <span class="c-red">*</span> -->
                                                  ???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text typeCodeCheck"
                        value="<%=infopubDeviceType.stTypeCode2Html() %>"
                        name="<%=InfopubDeviceType.ST_TYPE_CODE%>"  disabled="disabled">
                </div>
           </div>     
           
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><!-- <span class="c-red">*</span> -->
                                                 ???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stIcon2Html() %>" 
                        name="<%=InfopubDeviceType.ST_ICON %>" disabled="disabled">
                </div>
               <%-- <label class="form-label col-xs-4 col-sm-2"><!-- <span class="c-red">*</span> -->
                                                    ???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stClass2Html() %>"
                        name="<%=InfopubDeviceType.ST_CLASS%>"  disabled="disabled">
                </div> --%>
                 <label class="form-label col-xs-4 col-sm-2">
                                               ?????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stDesc2Html()%>"
                        name="<%=InfopubDeviceType.ST_DESC %>" disabled="disabled">
                </div>
           </div>
           <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                                                 ???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stCompanyId2Html() %>" 
                        name="<%=InfopubDeviceType.ST_COMPANY_ID %>" disabled="disabled">
                </div>
             <label class="form-label col-xs-4 col-sm-2">
                                                 ???????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                    <%if (infopubDeviceType.getNmDtype().intValue()==0) {%>
                        value="????????????" <%}else{ %>
                        value="???????????????"<%}%>
                        <%-- value="<%=infopubDeviceType.nmDtype2Html(0) %>"  --%>
                        name="<%=InfopubDeviceType.NM_DTYPE %>" disabled="disabled">
                </div>
           </div>    
           
          <%--  <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                                                ????????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.nmOrder2Html(0) %>" 
                        name="<%=InfopubDeviceType.NM_ORDER %>" disabled="disabled">
                </div> 
                <label class="form-label col-xs-4 col-sm-2">
                                               ?????????</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stDesc2Html()%>"
                        name="<%=InfopubDeviceType.ST_DESC %>" disabled="disabled">
                </div>
           </div> --%>
           
            <!-- <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit"
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> ??????
                    </button>
                    <button onClick="layer_close();" class="btn btn-default radius"
                        type="button">&nbsp;&nbsp;??????&nbsp;&nbsp;</button>
                </div>
            </div> -->
                                         
		</form>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="100">????????????</th>
						<th width="100">????????????</th>
						<!-- <th width="40">????????????</th> -->
						<!-- <th width="40">????????????</th> -->
						<th width="40">????????????</th>
						<!-- <th width="60">????????????</th>	 -->					
						<!-- <th width="20">????????????</th>	 -->					
					</tr>
				</thead> 
				<tbody>
				</tbody>
			</table>
		</div>
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
<script type="text/javascript">
var table = $('#typeList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// ????????????????????????
    ],
    "order": [[ 3, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stTypeId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stTypeName",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                { "data": "stTypeCode",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                /* { "data": "stIcon",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }}, */
                /* { "data": "stClass",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }}, */
                { "data": "dtCreate",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }}
                /* {
                 "data": "stTypeId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_info('"+full.stTypeName+"->????????????','/infopub/deviceinfotype/check.do','"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_edit('"+full.stTypeName+"->??????','/infopub/deviceinfotype/edit.do','"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-edit\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_del(this,'"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
                        return returnInfo;
                        
                    },
                  "bSortable": false
                } */
              
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                    //$(row).children('td').eq(6).attr('style', 'text-align: center;');
                    //$(row).children('td').eq(7).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		    url:webRoot+'/infopub/deviceinfotype/odevicelist.do',
		    type:"POST",
		    data: function(d){
		      var deviceTypeName = $("#searchName").val();
		      d.deviceTypeName = deviceTypeName;
	          var startDate = $("#startDate").val();
	          d.startDate = startDate;
              var endDate = $("#endDate").val();
              d.endDate= endDate;
              var stTypeId = '<%=infopubDeviceType.stTypeId2Html()%>';
              d.stTypeId= stTypeId;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#typeList").removeAttr("style"); 
});

// ??????
function search () {
    table.api().ajax.reload();
}

// ????????????
function datadel () {
    var selectList = $(".checkchild:checked");
    var idArray = [];
    // ??????????????????
    if (selectList.length == 0) {
        layer.msg('??????????????????????????????',{icon:2,time:1000});
        return;
    }
    for (var i = 0; i< selectList.length; i++){
        idArray.push(selectList[i].value);
    } 
    
    device_del('',idArray);
}
// ??????
function device_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_TYPE_ID='+'<%=infopubDeviceType.stTypeId2Html()%>'
    });
    layer.full(index);
}

// ??????
function device_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_TYPE_ID='+id
    });
    layer.full(index);
}
// ??????
function device_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_TYPE_ID='+id
    });
    layer.full(index);
}
// ??????
function device_del(obj,id){
    layer.confirm('?????????????????????',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/infopub/deviceinfotype/remove.do',
            dataType:'json',
            data:{'stTypeId':id},
            success : function(myObject) {
                 layer.msg(myObject.msg,{icon:1,time:1000});
             },
            error : function() {
                layer.msg(myObject.msg,{icon:1,time:1000});
            }
        });
       table.api().ajax.reload();
    });
}
// ???????????????
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\">???????????????</span>';
    } else {
        return data;
    }
}
</script>
</body>
</html>