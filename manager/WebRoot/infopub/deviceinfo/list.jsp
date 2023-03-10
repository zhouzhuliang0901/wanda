<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="coral.base.app.AppContext"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" uri="/sms/permission"%>
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
<title>???????????? </title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> ?????? <span class="c-gray en">&gt;</span>
		???????????? <span class="c-gray en">&gt;</span>???????????? <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="??????"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		
		<div class="text-x" style="text-align: left;">
			??????????????? <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d \'}'})"
				id="startDate" class="input-text Wdate" style="width:120px;" autocomplete="off"> -
			<input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d'})"
				id="endDate" class="input-text Wdate" style="width:120px;" autocomplete="off">
				
		   &nbsp;&nbsp;&nbsp; 
		   	???????????????<input type="text"  id="deviceAddress" placeholder=" ????????????" style="width:250px"
				class="input-text">
				
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			???????????????<input type="text"  id="deviceCode" placeholder="????????????" style="width:250px"
				class="input-text">
		</div>	
		<br>
		<div class="text-x" style="text-align: left;">
			???????????????
		   <select id="deviceType" class="input-text" style="width:250px">
		   		<script id="typeList" type="text/html">
                     {{each data}}
                        <option value="{{$value.stTypeId}}" {{if typeId == $value.stTypeId}} selected='selected' {{/if}}>
                            {{$value.stTypeName}}</option>
                     {{/each}} 
                </script>
		   
		   </select>
			
		 	<span class="area_del">&nbsp;&nbsp;&nbsp;</span>
			<span class="area_del">???????????????</span>
			<select id="deviceProvince" style="width:250px"class="input-text area_del" onchange="cityInit()">
				<script id="provinceList" type="text/html">
					 {{each province_list}}
                        <option value="{{$value.area_id}}" {{if $value.area_id == ""}} selected='selected' {{/if}}>
										{{$value.area_name}}</option>
                     {{/each}} 
				</script>
			</select>
			
			<span class="area_del">&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<span class="area_del">???????????????</span>
			<select id="deviceCity"  style="width:250px"class="input-text area_del" onchange="districtInit()" >
				<script id="cityList" type="text/html">
					 {{each city_list}}
                        <option value="{{$value.area_id}}" {{if $value.area_id == ""}} selected='selected' {{/if}}>
										{{$value.area_name}}</option>
                     {{/each}} 
				</script>
			</select>
		</div>
		
		<br>
		<div class="text-x" style="text-align: left;">
			<span class="area_del">???????????????</span>
			<select  id="deviceDistrict" class="input-text area_del" style="width:250px" onchange="streetInit()">
				<script id="districtList" type="text/html">
					 {{each district_list}}
                        <option value="{{$value.area_id}}" {{if $value.area_id == ""}} selected='selected' {{/if}}>
										{{$value.area_name}}</option>
                     {{/each}} 
				</script>
			
			</select>
			
			 <span class="area_del">&nbsp;&nbsp;&nbsp;</span>
			 
			 <span class="area_del">???????????????</span>
			 <select  id="deviceStreet" type="text" class="input-text area_del" style="width:250px">
			 	<script id="streetList" type="text/html">
					 {{each street_list}}
                        <option value="{{$value.area_id}}" {{if $value.area_id == ""}} selected='selected' {{/if}}>
										{{$value.area_name}}</option>
                     {{/each}} 
				</script>
			 </select>
			
			<span class="area_del">&nbsp;&nbsp;&nbsp;&nbsp;</span>
			
			??????MAC???<input type="text"  id="deviceMac" placeholder="??????MAC" style="width:250px"
				class="input-text">
		</div>
		
		<br>
		<div>
			<button name="" id="search" class="btn btn-success" onclick="search()" style="float:right">
					<i class="Hui-iconfont">&#xe665;</i>??????
				</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		   
		
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
					<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
					     <i class="Hui-iconfont">&#xe6e2;</i>
							????????????</a>
					<a class="btn btn-primary radius" onclick="device_add('????????????','add.jsp')" href="javascript:;">
					     <i class="Hui-iconfont">&#xe600;</i> ??????</a> 
		    </span>
		</div>
		<div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="deviceList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<!-- <th width="100">????????????</th> -->
						<th width="80">????????????</th>
						<th width="100">????????????</th>
						<th width="80">??????MAC</th>
						<th width="60">????????????</th>
						<th width="80">????????????</th>
						<th width="50">????????????</th>
						<th width="50">????????????</th>
						<th width="40">??????????????????</th>
						<th width="10">????????????</th>
						<!-- <th width="10">????????????</th> -->
<!-- 						<th width="20">??????</th> -->
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
var permission = "<%= (Object)request.getSession().getAttribute("??????")%>";
<%-- var companyId = "<%= (Object)request.getSession().getAttribute("companyCodeId")%>"; --%>
var table = $('#deviceList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// ????????????????????????
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
                 { "data": "stCertKey",
                "render":function(data, type, full, meta){
                      //return  setNullData(data);
                      return subData(data);
                  }},
                {
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_info('"+full.stDeviceName+"->??????','/infopub/deviceinfo/info.do','"+data+"')\" href=\"javascript:;\" title=\"????????????\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                        	//if(permission=="project_admin"){
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_edit('"+full.stDeviceName+"->??????','/infopub/deviceinfo/edit.do','"+data+"')\" href=\"javascript:;\" title=\"????????????\"><i class=\"icon iconfont icon-edit\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_onoff__edit('"+full.stDeviceName+"->??????????????????????????????','"+data+"')\" href=\"javascript:;\" title=\"????????????????????????\"><i class=\"icon iconfont icon-icon09\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_del(this,'"+data+"')\" href=\"javascript:;\" title=\"????????????\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
                       // }
                        return returnInfo;
                    },
                  "bSortable": false
                }
               /*  {
                 "data": "stDeviceId",
                 "render": function (data, type, full, meta) {
                           var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','snapshots')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-jieping\"></i></a>";
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','runexe')\" href=\"javascript:;\" title=\"???????????????\"><i class=\"iconfont icon-yunhangchengxu\"></i></a>";//icon-yunhangchengxu
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','stopexe')\" href=\"javascript:;\" title=\"???????????????\"><i class=\"iconfont icon-stop\"></i></a>";//icon-stop
                               //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','download')\" href=\"javascript:;\" title=\"????????????\"><i class=\"iconfont icon-icon-\"></i></a>";//icon-icon-
                               //returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','modify')\" href=\"javascript:;\" title=\"????????????\"><i class=\"iconfont icon-xiugai\"></i></a>";//icon-xiugai
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_modify('??????xml??????','/infopub/deviceinfo/modify.do','"+data+"')\" href=\"javascript:;\" title=\"????????????\"><i class=\"iconfont icon-xiugai\"></i></a>";//icon-xiugai
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','reboot','??????')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-zhongqi\"></i></a>";
                               returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','shutdown','??????')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                           if (typeof(full.stShutdownType) != 'undefined') {
                              if (full.stShutdownType == 'turnonoff') {
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','turnoff')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','turnon')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-kaiji\"></i></a>";                              
                              } else if (full.stShutdownType == 'openclosescrn') {
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','closescrn')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','openscrn')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-kaiji\"></i></a>";
                              } else if (full.stShutdownType == 'wakeup') {
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','shutdown')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-guanji\"></i></a>";
                                   returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_opt('"+data+"','wakeup')\" href=\"javascript:;\" title=\"??????\"><i class=\"icon iconfont icon-kaiji\"></i></a>";
                              }
                           }
                        return returnInfo;
                    },
                  "bSortable": false
                } */
//                 {
//                  "data": "stDeviceId",
//                  "render": function (data, type, full, meta) {
//                          return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_publish('"+full.stDeviceName+"->?????????????????????','/infopub/publish/edit.do','"+data+"')\" href=\"javascript:;\" title=\"????????????\"><i class=\"icon iconfont icon-fabu\"></i></a>";
//                     },
//                   "bSortable":  false
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
                    $(row).children('td').eq(7).attr('style', 'text-align: center;');
                     $(row).children('td').eq(8).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //????????????
      "bLengthChange": true ,//??????????????????????????????
       "ajax": {
		    url:webRoot+'/infopub/deviceinfo/list.do',
		    type:"POST",
		    data: function(d){
		      var username = user;
		      d.userName = username;
		      var stAreaId = areaId;
		      d.stAreaId = stAreaId; 
		      var stPermission = permission;
		      d.stPermission = stPermission;  
		      var deviceAddress = $("#deviceAddress").val();
		      d.deviceAddress = deviceAddress;
		      var deviceProvince = $("#deviceProvince").val();
              d.deviceProvince = deviceProvince;
              var deviceCity = $("#deviceCity").val();
              d.deviceCity = deviceCity;
              var deviceDistrict = $("#deviceDistrict").val();
              d.deviceDistrict = deviceDistrict;
              var deviceStreet = $("#deviceStreet").val();
              d.deviceStreet = deviceStreet;
		      var typeId = $("#deviceType").val();
		      d.typeId = typeId;
		      var deviceCode = $("#deviceCode").val();
		      d.deviceCode = deviceCode;
		      var deviceMac = $("#deviceMac").val();
		      d.deviceMac = deviceMac;
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
    if("area" == permission){
    	$(".area_del").hide();
    }
});

$(function(){
	initType();
	provinceInit();//????????????
	
});

// ????????????
function initType() {
			$.ajax({
				url : webRoot + '/infopub/devicetype/init.do',
				type : "POST",
				dataType : "json",
				data:{userName:user},
				success : function(result) {
					var devicetype = result.devicetype;
					devicetype.typeId = '';
					$("#deviceType").html(
							template('typeList',devicetype));

				},
				error : function() {
				}
			});
}

//????????????
function provinceInit(){
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do',
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceProvince = result.data;
			$("#deviceProvince").html(template('provinceList',deviceProvince));
		},
		error : function(){
		}	
	});
}

//????????????
function cityInit(){
	var deviceProvince = $("#deviceProvince").val();
	if(deviceProvince == null){
		deviceProvince = "";
	}
	$("#deviceCity").val("");
	$("#deviceDistrict").val("");
	$("#deviceStreet").val("");
	streetInit();
	
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do?deviceProvince='+deviceProvince,
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceCity = result.data;
			$("#deviceCity").html(template('cityList',deviceCity));
		},
		error : function(){
		}	
	});
}

//????????????
function districtInit(){
	var deviceCity = $("#deviceCity").val();
	if(deviceCity == null){
		deviceCity = "";
	}
	$("#deviceDistrict").val("");
	$("#deviceStreet").val("");
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do?deviceCity='+deviceCity,
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceDistrict = result.data;
			$("#deviceDistrict").html(template('districtList',deviceDistrict));
		},
		error : function(){
		}	
	});
}


//????????????
function streetInit(){
	var deviceDistrict = $("#deviceDistrict").val();
	if(deviceDistrict == null){
		deviceDistrict = "";
	}
	$("#deviceStreet").val("");
	$.ajax({
		url : webRoot+'/infopub/deviceAddress/init.do?deviceDistrict='+deviceDistrict,
		type : "POST",
		dataType : "json",
		success : function(result){
			var deviceStreet = result.data;
			$("#deviceStreet").html(template('streetList',deviceStreet));
		},
		error : function(){
		}
	});
}


// ????????????
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


$("#deviceGroupSelect").on('click', function() {
	initType();
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
        content: url
    });
    layer.full(index);
}
// ???????????????
function device_onoff__edit(title,id){
    layer_show(title, 'on-off-list.jsp?ST_DEVICE_ID='+id, '700', '600');
}
// ??????
function device_publish(title,url,id){
    layer_show(title, webRoot+url+'?ST_DEVICE_ID='+id, '700', '400');
}
// ??????
function device_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}
// ??????
function device_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}

// ??????xml??????
function device_modify(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_DEVICE_ID='+id
    });
    layer.full(index);
}
// ??????
function device_del(obj,id){
    layer.confirm('?????????????????????',function(index){
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
// ????????????
function device_opt(data, opt,title){

if(title=="??????" || title=="??????"){
layer.confirm("?????????"+title+"??????",function(index){
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
// ???????????????????????????
function showShot(deviceId){
    var index = layer.open({
        type: 2,
        title: '????????????',
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
// ???????????????
function setNullData(data){
    if(data == ''){
        //return '<span class=\"label label-danger radius\">???????????????</span>';
        return "";
    } else {
        return data;
    }
}
// ??????????????????
function setOnLine(data){
    if(data=='1'){
    	return '<span class=\"label label-success radius\">??????</span>'
    	//return '<i class=\"iconfont icon-zaixian\" style=\"color:green\"></i>';
    }else if(data=='0'){
    	return '<span class=\"label label-danger radius\">??????</span>'
    	//return '<i class=\"iconfont icon-buzaixian\"></i>';
    }
}
function subData(data){
	if(data==''){
		return '<span class=\"label label-danger radius\">?????????</span>'
	}else{
		if(data.length>8){
			//var data = data.substring(0,8);
			//return data+"...";
			return '<span class=\"label label-success radius\">?????????</span>'
		}
	}
	return data;
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