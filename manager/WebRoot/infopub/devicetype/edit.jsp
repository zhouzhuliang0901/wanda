<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceType"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
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
<link href="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.css"
    rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
    rel="stylesheet" />	
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
    rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>编辑</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=InfopubDeviceType.ST_TYPE_ID%>" id="stSampleId" value="<%=infopubDeviceType.stTypeId2Html()%>" />          
          <input type="hidden" id="stParentTypeId" value="<%=infopubDeviceType.stCompanyId2Html()%>" />
          <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                类型名称：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stTypeName2Html() %>" 
                        name="<%=InfopubDeviceType.ST_TYPE_NAME %>" required>
                </div>
               <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                   类型代码：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text typeCodeCheck"
                        value="<%=infopubDeviceType.stTypeCode2Html() %>"
                        name="<%=InfopubDeviceType.ST_TYPE_CODE%>"  required>
                </div>
           </div>     
           
           <div class="row cl">
                <%-- <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                 设备图标：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stIcon2Html() %>" 
                        name="<%=InfopubDeviceType.ST_ICON %>" required>
                </div> --%>
                 <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>
                                                    设备类型：</label>
                <div class="formControls col-xs-4 col-sm-4">
                <span class="select-box"> 
                       <select name="<%=InfopubDeviceType.NM_DTYPE%>"  class="select" id ="nmDtype">
                            <option value="0">政务终端</option>
                            <option value="1">社会化终端</option>
                       </select> 
                    </span>      
                </div>
                <label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>设备厂商：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> <select
						name="<%=InfopubDeviceType.ST_COMPANY_ID %>" class="select"
						id="deviceTypeSelect">
							<script id="typeListCompany" type="text/html">
                                        {{each data}}
                                            <option value="{{$value.stCompanyId}}" {{if typeId == $value.stCompanyId}} selected='selected' {{/if}}>
                                                    {{$value.stCompanyName}}</option>
                                        {{/each}} 
                            </script>
					</select> </span>
				</div>
           </div>
           <div class="row cl">
           <%-- <label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>设备厂商：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> <select
						name="<%=InfopubDeviceType.ST_COMPANY_ID %>" class="select"
						id="deviceTypeSelect">
							<script id="typeListCompany" type="text/html">
                                        {{each data}}
                                            <option value="{{$value.stCompanyId}}" {{if typeId == $value.stCompanyId}} selected='selected' {{/if}}>
                                                    {{$value.stCompanyName}}</option>
                                        {{/each}} 
                            </script>
					</select> </span>
				</div> --%>
                 <label class="form-label col-xs-4 col-sm-2">
                                               备注：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stDesc2Html()%>"
                        name="<%=InfopubDeviceType.ST_DESC %>" >
                </div>
                <label class="form-label col-xs-3 col-sm-2">
				<span
					class="c-red">*</span>上传图标：</label>
					&nbsp;&nbsp;&nbsp; <input type="file" name="file" accept="image/*" 
             			 multiple="multiple">
           </div>    
           
           <%-- <div class="row cl">
               <label class="form-label col-xs-4 col-sm-2"><span class="c-red"></span>
                                                排序号：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.nmOrder2Html(0) %>" 
                        name="<%=InfopubDeviceType.NM_ORDER %>" >
                </div> 
                <label class="form-label col-xs-4 col-sm-2">
                                               备注：</label>
                <div class="formControls col-xs-4 col-sm-4">
                    <input type="text" class="input-text"
                        value="<%=infopubDeviceType.stDesc2Html()%>"
                        name="<%=InfopubDeviceType.ST_DESC %>" >
                </div> 
           </div>--%>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button type="submit"
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> 保存
                    </button>
                    <button onClick="layer_close();" class="btn btn-default radius"
                        type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
                                         
		</form>
		<!-- <div class="mt-20">
			<table width="100%"
				class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
				<thead>
					<tr class="text-c">
						<th width="15" ><input name="" type="checkbox" ></th>
						<th width="100">外设名称</th>
						<th width="100">外设代码</th>
						<th width="40">设备图标</th>
						<th width="40">设备样式</th>
						<th width="40">创建时间</th>
						<th width="60">信息操作</th>						
						<th width="20">关机类型</th>						
					</tr>
				</thead> 
				<tbody>
				</tbody>
			</table>
		</div> -->
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
    
<%-- <script type="text/javascript"
	src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script> --%>
<%-- <script type="text/javascript"
	src="<%=webRoot%>/resources/layer/2.1/layer.js"></script> --%>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>       
<%-- <script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>   --%>  
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/viewer/js/template.js"></script>      
<script type="text/javascript"
     src="<%=webRoot%>/sms/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/i18n/jquery-ui-timepicker-zh-CN.js"></script>   
<script type="text/javascript"
    src="<%=webRoot%>/sms/lib/jQuery-Timepicker/init.js"></script>            
	<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
var tem = '<%=infopubDeviceType.getNmDtype()%>';
$("#nmDtype option[value='"+tem+"']").attr("selected","selected");
var table = $('#typeList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 4, "desc" ]],
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
                { "data": "stIcon",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
               /*  { "data": "stClass",
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
                        var returnInfo = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_info('"+full.stTypeName+"->信息查看','/infopub/deviceinfotype/check.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><i class=\"icon iconfont icon-chakan\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_edit('"+full.stTypeName+"->维护','/infopub/deviceinfotype/edit.do','"+data+"')\" href=\"javascript:;\" title=\"维护\"><i class=\"icon iconfont icon-edit\"></i></a>";
                            returnInfo += "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"device_del(this,'"+data+"')\" href=\"javascript:;\" title=\"删除\"><i class=\"icon iconfont icon-shanchu-copy\"></i></a>";
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
                    //$(row).children('td').eq(5).attr('style', 'text-align: center;');
                   // $(row).children('td').eq(6).attr('style', 'text-align: center;');
                    //$(row).children('td').eq(7).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/infopub/deviceinfotype/odevicelist.do',
		    type:"POST",
		    data: function(d){
		     /*  var deviceTypeName = $("#searchName").val();
		      d.deviceTypeName = deviceTypeName;
	          var startDate = $("#startDate").val();
	          d.startDate = startDate;
              var endDate = $("#endDate").val();
              d.endDate= endDate; */
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
        content: webRoot+url+'?ST_TYPE_ID='+'<%=infopubDeviceType.stTypeId2Html()%>'
    });
    layer.full(index);
}

// 查看
function device_info(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_TYPE_ID='+id
    });
    layer.full(index);
}
// 编辑
function device_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_TYPE_ID='+id
    });
    layer.full(index);
}
// 删除
function device_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
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
// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\">信息未配置</span>';
    } else {
        return data;
    }
}


var isAdmin = false;
// 管理员权限的判定
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
   initData();
   // 数据校验
   $("#form-device-add").validate();
   
   // 类型代码唯一性检查
    /* $.validator.addMethod("typeCodeCheck", function(value, element) { 
	  var flag = false;
	  $.ajax({
          type : "POST",
          url : webRoot + "/infopub/checkTypeCode.do",
          dataType : "json",
          async: false,
          data:{"typeCode":value,"ST_TYPE_ID":$("#stSampleId").val()},
          error : function(request) {
          },
          success : function(result) {
            flag = !result.data;
          }
      }); 
      return flag;
    }, "类型编号已存在"); */
});

function initData(){
 $.ajax({
         url:webRoot+'/infopub/deviceinfotype/init.do',
         type:"POST",
         dataType:"json",
         success : function(result) {
             var infopubCompany = result.infopubCompany;
             infopubCompany.typeId = $("#stParentTypeId").val();
             console.log(infopubCompany.typeId)
             $("#deviceTypeSelect").html(template('typeListCompany', infopubCompany));
          },
         error : function() {
         }
     }); 
}
  

// 数据提交     
$.validator.setDefaults({
    submitHandler: function() {
      	// 提交数据
     	article_save_submit();
    }
});




// 提交内容
function article_save_submit() {
	//验证图片
	var filepath = $("input[name='file']").val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
	        alert("图片限于bmp,png,gif,jpeg,jpg格式");
	        $("#fileType").text("");
	       return false;
	} 

	var size = $("input[name='file']")[0].files[0].size;
    var filesize = (size / 1024).toFixed(2); //K
    if(filesize > 2048){
    	alert("图片大小限于2MB");
    	return false;
    }

	var formData = new FormData($("#form-device-add")[0]);

	$.ajax({
		type : "POST",
		url : webRoot + "/infopub/deviceinfotype/save.do",
		//data : $('#form-device-add').serialize(),// 你的formid
		 data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false, 
		error : function(request) {
                  layer.msg("设备类型信息添加失败", {icon : 1, time : 1000});
		},
		success : function(data) {
			layer.msg("设备类型信息添加成功", {icon : 1, time : 1000});
		}
	});
	setTimeout(function() {
		window.parent.location.reload();
	}, 1000);
}




</script>
</body>
</html>