<%@page import="wfc.service.config.Config"%>
<%@page import="com.wondersgroup.business.bean.SelmAccessApply"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	 SelmAccessApply selmAccessApply = (SelmAccessApply) request.getAttribute(SelmAccessApply.SELM_ACCESS_APPLY);
    if (selmAccessApply == null)
        selmAccessApply = new SelmAccessApply(); 
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
<script type="text/javascript">var webRoot = '<%=StringEscapeUtils.escapeJavaScript(webRoot) %>';</script>
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
    
<%--<link rel="stylesheet" type="text/css"--%>
<%--    href="https://at.alicdn.com/t/font_1743964_iij8bsmpiw.css" />--%>
    
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>???????????? </title>
</head>
<body>
<%if (selmAccessApply.getStAccessApplyId()==null){%>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> ?????? <span class="c-gray en">&gt;</span>
		???????????? <span class="c-gray en">&gt;</span>???????????? <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="??????"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav><%}%>
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
					<a class="btn btn-primary radius" onclick="device_add('???????????????','edit.jsp')" href="javascript:;">
					     <i class="Hui-iconfont"></i>???????????????</a> 
                        <a href= "<%=webRoot%>/selmApply/apply.docx"
						target="_self" class="btn btn-success radius">????????????</a>
		    </span>
		</div>
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<!-- <th width="80">??????ID</th> -->
						<th width="80">??????</th>
						<th width="40">?????????</th>
						<th width="80">????????????</th>
						<th width="80">??????</th>
						<th width="80">????????????</th>
						<th width="40">????????????</th>
						<!-- <th width="40">???????????????</th> -->
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
var UserId = "<%= (Object)request.getSession().getAttribute("smsUserId")%>";
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
var stAccessApply = "<%=selmAccessApply.getStAccessApplyId()%>";
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
                 "data": "stApplyUserId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stApplyTitle",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                { "data": "stApplyUserName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
               
                { "data": "dtCreate",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }},
                 { "data": "nmStatus",
                "render":function (data, type, full, meta){
                       return setNullStatus(data);
                }},
                { "data": "stExt1",
                "render":function (data, type, full, meta){
                       return setNullData(data);
                }},
                 {
                 "data": "stAttachId",
                 "render": function (data, type, full, meta,text) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deviceAddress_down('/business/selmAccessApply/download.do','"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"Hui-iconfont\">&#xe640;</i></a>";
                        return returnInfo;
                    },
                  "bSortable": false
                }
              /*   {
                 "data": "stAccessApplyId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deviceAddress_edit('"+full.stApplyUserName+"->??????','/business/selmAccessApply/add.do','"+data+"')\" href=\"javascript:;\" title=\"??????\"><i class=\"Hui-iconfont\">&#xe684;</i></a>";
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
                    $(row).children('td').eq(6).attr('style', 'text-align: center;');
                    $(row).children('td').eq(7).attr('style', 'text-align: center;');
                    $(row).children('td').eq(8).attr('style', 'text-align: center;');
                    $(row).children('td').eq(9).attr('style', 'text-align: center;');
                    $(row).children('td').eq(10).attr('style', 'text-align: center;');
                    $(row).children('td').eq(11).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		    url:webRoot+'/business/selmAccessApply/list.do',
		    type:"POST",
		    data: function(d){
		   	 var smsUserId = UserId;
		      d.smsUserId = smsUserId; 
		      var stPermission = permission;
		      d.stPermission = stPermission;  
		      var stAccessApplyId = stAccessApply;
		      d.stAccessApplyId = stAccessApplyId;
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
    
    deviceAddress_del('',idArray);
}
// ??????
function device_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        area: ['60%', '60%'], //??????
        content: url
    });
    //layer.full(index);
}

// ??????
function deviceAddress_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_ADDRESS_ID='+id
    });
    layer.full(index);
}
// ??????
function deviceAddress_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_ACCESS_APPLY_ID='+id
    });
    layer.full(index);
}

// ??????
function deviceAddress_down(url1,id){
var url = webRoot+"/business/selmAccessApply/download.do?stAttachId="+id;
location.href=url;
}
// ??????
function deviceAddress_del(obj,id){
console.log(id)
    layer.confirm('?????????????????????',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/infopub/infopubAddress/remove.do',
            dataType:'json',
            data:{'stAddressId':id},
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
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}
// ??????
function setNullStatus(data){
    if(data == ''){
        return '<span>?????????</span>';
    } else if(data == '0'){
        return '<span>?????????</span>';
    }else if(data == '1'){
        return '<span>?????????</span>';
    }else if(data == '2'){
        return '<span>?????????</span>';
    }else if(data == '3'){
        return '<span>????????????</span>';
    }
}
</script>
</body>
</html>