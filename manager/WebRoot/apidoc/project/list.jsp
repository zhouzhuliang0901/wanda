<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%
	String webRoot = AppContext.webRootPath;
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
<link rel="stylesheet" type="text/css",
	href="<%=webRoot%>/sms/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=webRoot%>/sms/lib/h-ui.admin/css/style.css" />

<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>项目管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		系统管理<span class="c-gray en">&gt;</span>项目列表<a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		<div class="text-c">
			日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;">
		    <input type="text" name="" id="searchName" placeholder="项目名称" style="width:250px"
				class="input-text">
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a class="btn btn-primary radius"
				onclick="project_add('添加 ','edit.jsp')" href="javascript:;"><i
					class="Hui-iconfont">&#xe600;</i> 添加项目</a> </span>
		</div>
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="groupList">
				<thead>
					<tr class="text-c">
						<th width="20" ><input name="" type="checkbox" ></th>
						<th width="80">项目名称</th>
						<th width="210">项目说明</th>
						<th width="80">创建时间</th>
						<th width="90">操作</th>
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
<script type="text/javascript">
var table = $('#groupList').dataTable({
	"aoColumnDefs": [
    	{"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stProjectId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                 "bSortable": false
                },
                { "data": "stProjectName" },
                { "data": "stRemark" },
                { "data": "dtCreate" ,
                  "render": function (data, type, full, meta) {
                       return setDateTime(data);
                  },},
                {
                 "data": "stProjectId",
                 "render": function (data, type, full, meta) {
                 return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"html_info('/apidoc/interface/allInterface.html','"+data+"')\" href=\"javascript:;\" title=\"查看html文档\"><i class=\"Hui-iconfont\">[html文档]</i></a>"+
                		"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"project_info('项目查看','/apidoc/project/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><i class=\"Hui-iconfont\">[查看]</i></a>"+
                  		"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"project_edit('项目编辑','/apidoc/project/edit.do','"+data+"')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">[编辑]</i></a>"+ 
                      	"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"project_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">[删除]</i></a>";
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
               },
     "bPaginate": true, //翻页功能
     "bLengthChange": true ,//改变每页显示数据数量
     "ajax": {
		    url:webRoot+'/apidoc/project/list.do',
		    type:"POST",
		    data: function(d){
		    	// ST_NAME
			    var stProjectName = $("#searchName").val();
			    d.stProjectName = stProjectName;
			    // 开始时间
		        var startDate = $("#startDate").val();
		        d.startDate = startDate;
		        // 结束时间
	            var endDate = $("#endDate").val();
	            d.endDate= endDate;
	    	},
	        error:function(){
	            window.parent.location.reload();
	        }
    	}
}); 

// 查找
function search () {
    table.api().ajax.reload();
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
    
    project_del('',idArray);
}


/*添加*/
function project_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url,
    });
    layer.full(index);
}

//html文档生成
function html_info(url,id){
	window.open(webRoot+url+"?ST_PROJECT_ID=" + id);
}
/*查看*/
function project_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+"?ST_PROJECT_ID="+id
    });
    layer.full(index);
}

/*编辑*/
function project_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+"?ST_PROJECT_ID="+id
    });
    layer.full(index);
}

/*删除*/
function project_del(obj,id){
    layer.confirm('删除该项目所有数据？',function(index){
         $.ajax({
            type : "POST",
            url : webRoot+'/apidoc/project/remove.do',
            dataType:"json",
            data:{"stProjectId":id},
            success : function(myObject) {
                 layer.msg(myObject.msg,{icon:1,time:1000});
             },
            error : function() {
                layer.msg(myObject.msg,{icon:2,time:1000});
            }
        });
       table.api().ajax.reload();
    });
}

// 时间转换
function setDateTime (timestamp ) {
     // 不存在的场合
    if (timestamp == null || timestamp == '') {
        return '';
   	// 时间存在的场合
    } else {
         var datetime = new Date();
         datetime.setTime(timestamp.time);
         var year = datetime.getFullYear();
         var month = datetime.getMonth() + 1;
         var date = datetime.getDate();
         var hours = timestamp.hours;
         var minutes = timestamp.minutes;
         if (month < 10) {
            month = "0"+month;
         }
         if (date < 10) {
            date = "0"+date;
         }
         if (hours < 10) {
            hours = "0"+hours;
         }
         if (minutes <10) {
            minutes = "0"+minutes;
         }
         return year + "-" + month + "-" + date+" "+hours+":"+minutes;
    }
 }
</script>
</body>
</html>
