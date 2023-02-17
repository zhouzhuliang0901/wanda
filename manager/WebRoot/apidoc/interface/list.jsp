<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="coral.base.app.AppContext"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="wfc.facility.tool.autocode.Transformer4Request"%>
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
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
        接口管理 <span class="c-gray en">&gt;</span> 接口列表
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<div class="text-c">
			模块名称: <span type="text"   style="width:120px;" class="select-box input-text" > <select
				class="select" size="1" id="moduleName"
				name="ST_MODULE_NAME>">
			</select> </span>
		         接口名称: <input type="text" name="" id="interfaceName" placeholder="接口名称" style="width:120px"
				class="input-text">
			日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;">
			<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;" onclick="copyMore()"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe6ea;</i>
					复制接口</a> <a class="btn btn-primary radius" id="add"
				onclick="interface_add('添加 ','/apidoc/interface/detail.jsp')" href="javascript:;"><i
					class="Hui-iconfont">&#xe600;</i> 添加接口</a></span> 
		</div>
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="moduleList">
				<thead>
					<tr class="text-c">
						<th width="20" ><input name="" type="checkbox" ></th>
						<th width="80">接口名称</th>
						<th width="80">所属模块</th>
						<th width="220">接口说明</th>
						<th width="55">创建时间</th>
						<th width="30">排序号</th>
						<th width="100">操作</th>
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
getList();
var table = $('#moduleList').dataTable({
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
                 "data": "stInterfaceId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                { "data": "stInterfaceName" },
                { "data": "stExt1" },
                { "data": "clRemark" },
                { "data": "dtCreate" ,
                  "render": function (data, type, full, meta) {
                       return setDateTime(data);
                  	},
                },
                { "data": "nmOrder" },
                {
                 "data": "stInterfaceId",
                 "render": function (data, type, full, meta) {
                  return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"html_info('/apidoc/interface/preview.html','"+data+"')\" href=\"javascript:;\" title=\"预览接口文档\"><i class=\"Hui-iconfont\">[预览]</i></a>"+
                  		"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"interface_info('接口详情','/apidoc/interface/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看接口详情\"><i class=\"Hui-iconfont\">[查看]</i></a>"+
                  		"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"interface_edit('接口编辑','/apidoc/interface/detail.do','"+data+"')\" href=\"javascript:;\" title=\"编辑接口\"><i class=\"Hui-iconfont\">[编辑]</i></a>"+ 
                      	"<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"interface_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除接口\"><i class=\"Hui-iconfont\">[删除]</i></a>";
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
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/apidoc/interface/list.do',
		    type:"POST",
		    data: function(d){
		    	// stInterfaceName
			    var stInterfaceName = $("#interfaceName").val();
			    d.stInterfaceName = stInterfaceName;
		    	// stModuleName
			    var stModuleName = $("#moduleName").val();
			    d.stModuleName = stModuleName;
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
    interface_del('',idArray);
}

// 批量删除
function copyMore () {
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
    interface_copy('',idArray);
}

/*添加*/
function interface_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url
    });
    layer.full(index);
} 

/*查看*/
function interface_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+"?ST_INTERFACE_ID="+id
    });
    layer.full(index);
}

/*编辑*/
function interface_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+"?ST_INTERFACE_ID="+id
    });
    layer.full(index);
}
//复制
function interface_copy(obj,id){
	 layer.confirm('复制接口后请重新将接口关联模块！',function(index){
	$.ajax({
            type : "POST",
            url : webRoot+'/apidoc/interface/copy.do',
            dataType:"json",
            data:{"stInterfaceId":id},
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
/*删除*/
function interface_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : "POST",
            url : webRoot+'/apidoc/interface/remove.do',
            dataType:"json",
            data:{"stInterfaceId":id},
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

//预览
function html_info(url,id){
	var str = getRequest();
	window.open(webRoot+url+"?ST_INTERFACE_ID="+id);
}
//静态化
function interface_html_info(url){
	var str = getRequest();
	window.open(webRoot+url+"?ST_MODULE_ID="+str[1]);
}
//获取query string parameters stProjectId
function getRequest() {
	//获取url中"?"符后的字串
	var url = location.search; 
	//判断是否有参数
	if (url.indexOf("?") != -1) {   
	//从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
	var str = url.substr(1);
	//用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔） 
	strs = str.split(/[&=]/);
	//直接返回第一个参数 （如果有多个参数 还要进行循环的）   
	return strs;         
  }
  return null;
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
 
function getList() {
	var templateObj = $("#moduleName");
	templateObj.html("");
	$.ajax({
		url : webRoot + "/apidoc/interface/getModule.do",
		dataType : "JSON",
		type : "POST",
		success : function(data) {
			var item = data.module;
			 var msg = "";
			msg += "<option value=''></option>";
			for ( var i = 0; i < item.length; i++) {
				msg += "<option value='"+item[i].stModuleName+"'>" + item[i].stModuleName + "</option>";
			}
			templateObj.html(msg); 
		}
	});
}
</script>
</body>
</html>
