<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.wondersgroup.business.bean.SelmQueryHis"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String date = (String)format.format(new Date());
	SelmQueryHis selmQueryHis = (SelmQueryHis) request
            .getAttribute(SelmQueryHis.SELM_QUERY_HIS);
    if (selmQueryHis == null)
        selmQueryHis = new SelmQueryHis();
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
<%--    href="https://at.alicdn.com/t/font_1700164_scb7ybg4fd.css" />--%>
<%--<link rel="stylesheet" type="text/css"--%>
<%--    href="https://at.alicdn.com/t/font_1743964_q4n8sjtxs3.css" />--%>
    
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>设备管理 </title>
</head>
<body>
	<%if(selmQueryHis.getStMachineId()==null){ %>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		统计分析<span class="c-gray en">&gt;</span>办件查询 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav><%} %>
	<div class="page-container">
		<div class="text-x" style="text-align: left;">
				&nbsp;&nbsp;日期范围： 
			<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'2020-01-08'})" value="" id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'2020-01-08'})" value="" id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
		    &nbsp;&nbsp;&nbsp;事项名称： <input type="text" name="" id="searchItemName" placeholder=" 事项名称" style="width:250px"
				class="input-text">
		    
				&nbsp;&nbsp;&nbsp;操作名称： <input type="text" name="" id="stModuleOp" placeholder="操作名称" style="width:250px"
				class="input-text">
				
				
		</div>
		<br>
		<div class="text-x" style="text-align: left;">
			   &nbsp;&nbsp;姓&nbsp;&nbsp;名： &nbsp;&nbsp;&nbsp;&nbsp;
			   <input type="text" name="" id="searchName" placeholder=" 姓名" style="width:250px"
				class="input-text">
			  &nbsp;&nbsp;&nbsp;&nbsp;手机号：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="" id="searchPhone" placeholder=" 手机号" style="width:250px"
						class="input-text">
			 &nbsp;&nbsp;&nbsp;&nbsp;证件号：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="" id="searchCardId" placeholder=" 证件号" style="width:250px"
						class="input-text">
		<%if(selmQueryHis.getStMachineId()==null){ %>
			 
		</div>
		<br>
		<div class="text-x" style="text-align: left;">
			&nbsp;&nbsp;
			 <span id="searchAreaSpan">所属区：&nbsp;&nbsp; <input type="text" name="" id="searchArea" placeholder=" 所属区" style="width:250px"
						class="input-text"></span>
			  &nbsp;&nbsp;&nbsp;&nbsp;所属街道： <input type="text" name="" id="searchStreet" placeholder=" 街道" style="width:250px"
						class="input-text">
			&nbsp;&nbsp;&nbsp;&nbsp;设备MAC：<input type="text" name="" id="searchMac" placeholder=" 设备MAC" style="width:250px"
						class="input-text"><%} %>
		</div>
		<br>
		<div class="text-x" style="text-align: left;">				
           &nbsp;&nbsp;辅助人员：
		   <select name="searchAssist" id="assistId" class="input-text" style="width:250px">
		   		<!--  <script id="typeList" type="text/html">
                </script>-->
                <option></option>
                <option value="0">是</option>
                <option value="1">否</option>
		   </select>
					&nbsp;&nbsp;&nbsp;<button name="" id="search" class="btn btn-success" onclick="search()" >
						<i class="Hui-iconfont">&#xe665;</i> 搜索
					</button>
					<%if(selmQueryHis.getStExt1()==null){ %>
					<button name="" id="search" class="btn btn-primary" onclick="searchExcle()" >
						<i class="Hui-iconfont">&#xe640;</i> 下载
					</button>
					<%} %>
					
		</div>
		
		<div class="mt-20">
		
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="businessList">
				<thead>
					<tr class="text-c">
						<!-- <th width="15" ><input name="" type="checkbox" ></th> -->
						<th width="100" >事项名称</th>
						<th width="70" >模块名称</th>
						<th width="40" >操作名称</th>
						<th width="40" >姓名</th>
						<th width="60" >证件号</th>
						<th width="50" >所属区</th>
						<th width="50" >所属街道</th>
						<th width="40" >创建时间</th>
						<th width="50" >业务唯一标识</th>
						<th width="40" >操作结果</th>
						<th width="40" >辅助人员</th>
						<th width="30" >信息操作</th>
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
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
var table = $('#businessList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 7,"desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                /* {
                 "sClass": "text-center",
                 "data": "stQueryHisId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                }, */
                { "data": "stItemName",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                { "data": "stModuleName",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                   { "data": "stModuleOp",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                { "data": "stName",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stIdentityNo",
                   "render":function(data, type, full, meta){
                      // return setNullData(data);
                      return setidCardData(data);
                }},
              /*   { "data": "stMobile",
                   "render":function(data, type, full, meta){
                       return setMobileData(data);
                }}, */
                 { "data": "stExt3",
                   "render":function(data, type, full, meta){
                       return setMobileData(data);
                },
                  "bSortable": false}, 
                { "data": "stExt4",
                   "render":function(data, type, full, meta){
                       return setMobileData(data);
                },
                  "bSortable": false},
                { "data": "dtCreate",
                "render":function (data, type, full, meta){
                       return setDateTime(data);
                }},
                { "data": "stBusinessNo",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stOpResult",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stAssistId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                {
                 "data": "stQueryHisId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"business_info('业务详细信息','/business/selmQueryHis/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看业务\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                            //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"business_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除业务\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
                        return returnInfo;
                    },
                  "bSortable": false
                }
                
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
                     $(row).children('td').eq(12).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显s示数据数量
       "ajax": {
		    url:webRoot+'/business/selmQueryHis/list.do',
		    type:"POST",
		    data: function(d){
		    var stAreaId = areaId;
		      d.stAreaId = stAreaId; 
		      var stPermission = permission;
		      d.stPermission = stPermission;  
		     /*  var stModuleName = $("#searchModuleName").val();
		      d.stModuleName = stModuleName; */
		      var stItemName = $("#searchItemName").val();
		      d.stItemName = stItemName;
		      d.stModuleOp = $("#stModuleOp").val();
		      var stName = $("#searchName").val();
		      d.stName = stName;
		      var stIdentityNo = $("#searchCardId").val();
		      d.stIdentityNo = stIdentityNo;
		      var stMobile = $("#searchPhone").val();
		      d.stMobile = stMobile;
		      var stStreet = $("#searchStreet").val();
		      d.stStreet = stStreet;
		       var stDistrict = $("#searchArea").val();
		      d.stDistrict = stDistrict;
		      var stMachineId = '<%=selmQueryHis.getStMachineId()%>';
		      if('' == stMachineId || null == stMachineId || 'null' == stMachineId){
				d.stDeviceMac = $("#searchMac").val();
			  }else{
				d.stDeviceMac = stMachineId;
			  }
		      
	          var startDate = $("#startDate").val();
	          if('' == startDate){
	          	startDate = dateInfo();
	          }
	          d.startDate = startDate;
              var endDate = $("#endDate").val();
              if('' == endDate){
	          	endDate = dateInfo();
	          }
              d.endDate= endDate;
	          console.log(startDate+"---"+endDate);
               var stAssistId = $("#assistId").val();
		      d.stAssistId = stAssistId;
		       var username = user;
		      d.username = username;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#businessList").removeAttr("style"); 
    //初始化默认时间
    initDate();
    //设置区域隐藏
    if(permission == 'area'){
		$("#searchAreaSpan").hide();
	}else{
		$("#searchAreaSpan").show();
	}
});

function dateInfo(){
	var date = new Date;
	var day = date.getDate();
	if(day<10){
		day = "0"+day;
	}
	var mouth = date.getMonth()+1;
	if(mouth<10){
	    mouth = "0"+mouth;
	}
	return date.getFullYear()+"-"+mouth+"-"+day;
}


function initDate(){
	var time = dateInfo();
	$("#startDate").val(time);
	$("#endDate").val(time);
}



// 查找
function search () {
    table.api().ajax.reload();
}

// 查询下载
function searchExcle(){
	var itemName = $("#searchItemName").val();
	var stName = $("#searchName").val();
	var stAssistId = $("#assistId").val();
	var searchMac = '';
	var	stStreet = '';
	var	stDistrict = '';
	var stMachineId='<%=selmQueryHis.getStMachineId()%>';
	if('' == stMachineId || null == stMachineId || 'null' == stMachineId){
		searchMac = $("#searchMac").val();
		stStreet = $("#searchStreet").val();
		stDistrict = $("#searchArea").val();
		
	}else{
		searchMac = stMachineId;
	}
	var stModuleOp = $("#stModuleOp").val();
	var endDate = $("#endDate").val();
    if('' == endDate){
   		endDate = dateInfo();
   	}
   	var startDate = $("#startDate").val();
    if('' == startDate){
   		startDate = dateInfo();
   	}
	
	var url = webRoot+"/business/selmQueryHis/listExcel.do?username="+user+"&stItemName="
	+itemName+"&stName="+stName+"&stIdentityNo="+$("#searchCardId").val()+"&stMobile="+$("#searchPhone").val()
	+"&startDate="+startDate+"&endDate="+endDate+"&stStreet="+stStreet+"&stDistrict="
	+stDistrict+"&stAreaId="+areaId+"&stPermission="+permission+"&stDeviceMac="+searchMac
	+"&stAssistId="+stAssistId+"&stModuleOp="+stModuleOp;
	location.href=url;
}


// 批量删除
function datadel () {
    var selectList = $(".checkchild:checked");
    var idArray = [];
    // 未选择的场合
    if (selectList.length == 0) {
        layer.msg('请选择需要删除的数据',{icon:2,time:1000});
        return;
    }
    for (var i = 0; i< selectList.length; i++){
        idArray.push(selectList[i].value);
    } 
    
    device_del('',idArray);
}

// 查看
function business_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_QUERY_HIS_ID='+id
    });
    layer.full(index);
}



// 删除
function business_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/business/selmQueryHis/remove.do',
            dataType:'json',
            data:{'stQueryHisId':id},
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

// 未配置数据
function setNullData(data){
    if(data == '' || data == 'null' || null == data){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}

// 脱敏处理
function setMobileData(data){
    if(data == ''){
        return '';
    } else {
    	return data.replace(/^(.{3})(?:\w+)(.{4})$/, "\$1****\$2");
    }
}

// 脱敏处理
function setidCardData(data){
    if(data == ''){
        return '';
    } else {
    	return data.replace(/^(.{1})(?:\w+)(.{1})$/, "\$1****************\$2");
    }
}

</script>
</body>
</html>