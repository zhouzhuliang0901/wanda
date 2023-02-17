<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
    InfopubDeviceInfo infopubDeviceInfo = (InfopubDeviceInfo) request
            .getAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO);
    if (infopubDeviceInfo == null)
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
	<!-- <nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		设备管理 <span class="c-gray en">&gt;</span>设备信息 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav> -->
	<div class="page-container">
		
		<div class="text-x" style="text-align: left;">
			创建日期： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
		   &nbsp;&nbsp;&nbsp; 设备地址：<input type="text" name="" id="searchDeviceName" placeholder=" 设备地址" style="width:250px"
				class="input-text">
		   <!-- &nbsp;&nbsp;&nbsp;设备分类：<input type="text" name="" id="searchTypeName" placeholder=" 设备分类" style="width:250px"
				class="input-text"> -->
		</div>	
		<br>
		<div class="text-x" style="text-align: left;">
		   <!--  &nbsp;设备分组：<input type="text" name="" id="searchGroupName" placeholder=" 设备分组" style="width:250px"
				class="input-text"> -->
				设备分类：
		   <select name="searchTypeId" id="deviceTypeSelect" class="input-text" style="width:250px">
		   		<script id="typeList" type="text/html">
                     {{each data}}
                        <option value="{{$value.stTypeId}}" {{if typeId == $value.stTypeId}} selected='selected' {{/if}}>
                            {{$value.stTypeName}}</option>
                     {{/each}} 
                </script>
		   
		   </select>
			<!--  &nbsp;设备分组：
		   <select name="searchGroupId" id="deviceGroupSelect" class="input-text" style="width:250px">
		   		<script id="groupList" type="text/html">
                     {{each data}}
                        <option value="{{$value.stGroupId}}" {{if groupId == $value.stGroupId}} selected='selected' {{/if}}>
                            {{$value.stGroupName}}</option>
                     {{/each}} 
                </script>
		   
		   </select>-->
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设备编号：<input type="text" name="" id="searchAreaName" placeholder="设备编号" style="width:250px"
				class="input-text">
			&nbsp;&nbsp;&nbsp;&nbsp;<button name="" id="search" class="btn btn-success" onclick="search()" >
				<i class="Hui-iconfont">&#xe665;</i>搜索
			</button>
		</div>
		   
		
		
		<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							批量删除</a>
					<a class="btn btn-primary radius" onclick="device_add('添加设备','add.jsp')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> 添加</a> 
		    </span>
		</div> -->
		
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="deviceList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<!-- <th width="100">设备名称</th> -->
						<th width="80">设备编码</th>
						<th width="100">详细地址</th>
						<th width="80">设备MAC</th>
						<th width="60">设备类型</th>
						<th width="80">创建日期</th>
						<th width="40">是否在线</th>
						<th width="10">信息操作</th>
						<!-- <th width="10">远程操作</th> -->
<!-- 						<th width="20">发布</th> -->
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
<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>
<script type="text/javascript">
var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
var table = $('#deviceList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 5, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                {
                 "sClass": "text-center",
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },
                
                { "data": "stDeviceCode",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                { "data": "stDeviceAddress",
                   "render":function(data, type, full, meta){
                      return  setNullData(data);
                }},
                { "data": "stDeviceMac",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stTypeId",
                "render":function(data, type, full, meta){
                      return  setNullData(data);
                  }},
                /* { "data": "stTypeId",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }}, */
                { "data": "dtCreate",
                "render":function (data, type, full, meta){
                       //return setDateTime(data);
                       return setDateFormat(data);
                }},
                { "data": "nmOnline",
                   "render":function(data, type, full, meta){
                       return setOnLine(data + "");
                },
                 "bSortable": false
                 },
                {
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_info('"+full.stDeviceName+"->查看','/infopub/deviceinfo/info.do','"+data+"')\" href=\"javascript:;\" title=\"查看设备\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                            //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_edit('"+full.stDeviceName+"->编辑','/infopub/deviceinfo/edit.do','"+data+"')\" href=\"javascript:;\" title=\"编辑设备\"><i class=\"icon iconfont icon-edit\"></i></a>";
                            //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_onoff__edit('"+full.stDeviceName+"->特定日开关机日期列表','"+data+"')\" href=\"javascript:;\" title=\"设置特定日开关机\"><i class=\"icon iconfont icon-icon09\"></i></a>";
                            //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除设备\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
                        return returnInfo;
                    },
                  "bSortable": false
                }
               /*  {
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
                           var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','snapshots')\" href=\"javascript:;\" title=\"截屏\"><i class=\"icon iconfont icon-jieping\"></i></a>";
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','runexe')\" href=\"javascript:;\" title=\"运行向日葵\"><i class=\"iconfont icon-yunhangchengxu\"></i></a>";//icon-yunhangchengxu
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','stopexe')\" href=\"javascript:;\" title=\"关闭向日葵\"><i class=\"iconfont icon-stop\"></i></a>";//icon-stop
                               //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','download')\" href=\"javascript:;\" title=\"下载文档\"><i class=\"iconfont icon-icon-\"></i></a>";//icon-icon-
                               //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','modify')\" href=\"javascript:;\" title=\"修改文档\"><i class=\"iconfont icon-xiugai\"></i></a>";//icon-xiugai
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_modify('修改xml文件','/infopub/deviceinfo/modify.do','"+data+"')\" href=\"javascript:;\" title=\"编辑设备\"><i class=\"iconfont icon-xiugai\"></i></a>";//icon-xiugai
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','reboot','重启')\" href=\"javascript:;\" title=\"重启\"><i class=\"icon iconfont icon-zhongqi\"></i></a>";
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','shutdown','关机')\" href=\"javascript:;\" title=\"关机\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                           if (typeof(full.stShutdownType) != 'undefined') {
                              if (full.stShutdownType == 'turnonoff') {
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','turnoff')\" href=\"javascript:;\" title=\"关机\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','turnon')\" href=\"javascript:;\" title=\"开机\"><i class=\"icon iconfont icon-kaiji\"></i></a>";                              
                              } else if (full.stShutdownType == 'openclosescrn') {
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','closescrn')\" href=\"javascript:;\" title=\"关机\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','openscrn')\" href=\"javascript:;\" title=\"开机\"><i class=\"icon iconfont icon-kaiji\"></i></a>";
                              } else if (full.stShutdownType == 'wakeup') {
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','shutdown')\" href=\"javascript:;\" title=\"关机\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','wakeup')\" href=\"javascript:;\" title=\"开机\"><i class=\"icon iconfont icon-kaiji\"></i></a>";
                              }
                           }
                        return returnInfo;
                    },
                  "bSortable": false
                } */
//                 {
//                  "data": "stDeviceId",
//                  "render": function (data, type, full, meta) {
//                          return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_publish('"+full.stDeviceName+"->设置特定日发布','/infopub/publish/edit.do','"+data+"')\" href=\"javascript:;\" title=\"信息发布\"><i class=\"icon iconfont icon-fabu\"></i></a>";
//                     },
//                   "bSortable": false
//                 }
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                    $(row).children('td').eq(6).attr('style', 'text-align: center;');
                   //$(row).children('td').eq(6).attr('style', 'padding:10px 5px ');
                   // $(row).children('td').eq(6).attr('style', 'right-padding: 0px;');
                    $(row).children('td').eq(7).attr('style', 'text-align: center;');
                   // $(row).children('td').eq(8).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/infopub/deviceinfo/list.do',
		    type:"POST",
		    data: function(d){
		    var username = user;
		      d.userName = username;
		    var stTypeName = '<%=infopubDeviceInfo.getStTypeId()%>';
		    console.log(stTypeName);
		     d.stTypeName = stTypeName; 
		    var staddressName = '<%=infopubDeviceInfo.stAddressId2Html()%>';
		     d.staddressName = staddressName; 
		     console.log(stTypeName)
		      var stAreaId = areaId;
		      d.stAreaId = stAreaId; 
		      var stPermission = permission;
		      d.stPermission = stPermission;  
		      var deviceName = $("#searchDeviceName").val();
		      d.deviceName = deviceName;
		      var typeId = $("#deviceTypeSelect").val();
		      d.typeId = typeId;
		      var groupId = $("#deviceGroupSelect").val();
		      d.groupId = groupId;
		      var codeName = $("#searchAreaName").val();
		      d.codeName = codeName;
	          var startDate = $("#startDate").val();
	          d.startDate = startDate;
              var endDate = $("#endDate").val();
              d.endDate= endDate;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#deviceList").removeAttr("style"); 
});

$(function(){
	initType();
	initGroup();
});

// 分类查询
function initType() {
			$.ajax({
				url : webRoot + '/infopub/devicetype/init.do',
				type : "POST",
				dataType : "json",
				data:{userName:user},
				success : function(result) {
					var devicetype = result.devicetype;
					devicetype.typeId = '';
					$("#deviceTypeSelect").html(
							template('typeList',devicetype));

				},
				error : function() {
				}
			});
}
// 分组查询
function initGroup() {
			$.ajax({
				url : webRoot + '/infopub/devicegroup/init.do',
				type : "POST",
				dataType : "json",
				data:{
					'stAreaId':areaId,
					'stPermission':permission
				},
				success : function(result) {
					var devicegroup = result.devicegroup;
					devicegroup.groupId = '';
					$("#deviceGroupSelect").html(
							template('groupList',devicegroup));

				},
				error : function() {
				}
			});
}

// 分类，分组条件只能选择一个，点击一个，另一个初始化
$("#deviceTypeSelect").on('click', function() {
	initGroup();
});
$("#deviceGroupSelect").on('click', function() {
	initType();
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
    
    device_del('',idArray);
}
// 添加
function device_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
// 开关机设置
function device_onoff__edit(title,id){
    layer_show(title, 'on-off-list.jsp?ST_DEVICE_ID='+id, '700', '600');
}
// 发布
function device_publish(title,url,id){
    layer_show(title, webRoot+url+'?ST_DEVICE_ID='+id, '700', '400');
}
// 查看
function device_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}
// 编辑
function device_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}

// 修改xml文档
function device_modify(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}
// 删除
function device_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
         $.ajax({
            type : 'POST',
            url : webRoot+'/infopub/deviceinfo/remove.do',
            dataType:'json',
            data:{'stDeviceId':id},
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
// 设备操作
function device_opt(data, opt,title){

if(title=="重启" || title=="关机"){
layer.confirm("确认要"+title+"吗？",function(index){
     $.ajax({
        type : 'POST',
        url : webRoot+'/infopub/deviceinfo/operate.do',
        dataType:'json',
        data:{'ST_DEVICE_ID':data,'type':opt},
        success : function(myObject) {
             layer.msg(myObject.msg,{icon:1,time:1000});
             if (opt == 'snapshots') {
                showShot(data);
             }
         },
        error : function() {
            layer.msg(myObject.msg,{icon:1,time:1000});
        }
    });
     });
     }else{
     $.ajax({
        type : 'POST',
        url : webRoot+'/infopub/deviceinfo/operate.do',
        dataType:'json',
        data:{'ST_DEVICE_ID':data,'type':opt},
        success : function(myObject) {
             layer.msg(myObject.msg,{icon:1,time:1000});
             if (opt == 'snapshots') {
                showShot(data);
             }
         },
        error : function() {
            layer.msg(myObject.msg,{icon:1,time:1000});
        }
    });
     }
}
// 查看截图、返回删除
function showShot(deviceId){
    var index = layer.open({
        type: 2,
        title: '查看截图',
        content: 'shot-pic-list.jsp?ST_DEVICE_ID='+deviceId,
        cancel:function(){
            $.ajax({
	        type : 'POST',
	        url : webRoot+'/infopub/deviceattachment/removeshot.do',
	        dataType:'json',
	        data:{'ST_DEVICE_ID':deviceId},
	        success : function(myObject) {
	         },
	        error : function() {
	        }
	    });
        }
    });
    layer.full(index);
}
// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\">信息未配置</span>';
    } else {
        return data;
    }
}
// 配置是否在线
function setOnLine(data){
    if(data=='1'){
    	return '<span class=\"label label-success radius\">在线</span>'
    	//return '<i class=\"iconfont icon-zaixian\" style=\"color:green\"></i>';
    }else if(data=='0'){
    	return '<span class=\"label label-danger radius\">离线</span>'
    	//return '<i class=\"iconfont icon-buzaixian\"></i>';
    }
}


 function setDateFormat(data){
 return (new Date(parseFloat(data.time))).format("yyyy-MM-dd");
} 
Date.prototype.format = function(format) {
            var o = {
                "M+" : this.getMonth() + 1,// month
                "d+" : this.getDate(),// day
                "h+" : this.getHours(),// hour
                "m+" : this.getMinutes(),// minute
                "s+" : this.getSeconds(),// second
                "q+" : Math.floor((this.getMonth() + 3) / 3),// quarter
                "S" : this.getMilliseconds()
            // millisecond
            };
            if (/(y+)/.test(format) || /(Y+)/.test(format)) {
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for ( var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                }
            }
            return format;
        };

</script>
</body>
</html>