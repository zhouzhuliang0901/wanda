<%@page import="com.wondersgroup.serverApply.bean.SelmServerApply"%>
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
    SelmServerApply selmServerApply = (SelmServerApply) request.getAttribute(SelmServerApply.SELM_SERVER_APPLY);
    if (selmServerApply == null)
        selmServerApply = new SelmServerApply(); 
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
<title>编辑</title>
</head>
<body>
<div style="padding-top: 10px;font-size: 18px;padding-left: 95px;font-weight: 600;margin-bottom: -14px;">基本信息</div>
<hr style="width: 90%;margin-left: 96px;font-weight: 600;margin-bottom: -5px;border-color: #a9a9a9;""/>
	<div class="page-container" style="overflow-x:hidden;">
		<form class="form form-horizontal" id="form-device-add">
		  <input type="hidden" name="<%=SelmServerApply.ST_APPLY_ID%>"
		   id="stSampleId" value="<%=selmServerApply.stApplyId2Html()%>" />
          <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>申请单位：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stApplyOrganName2Html() %>"
						name="<%=SelmServerApply.ST_APPLY_ORGAN_NAME%>" required>
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>联系人：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserName2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_NAME%>" required>
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>手机：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserPhone2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_PHONE%>" required>
				</div>
					<label class="form-label col-xs-4 col-sm-2">
				固定电话：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserMobile2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_MOBILE%>" >
				</div>
			</div>
			 <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				电子邮箱：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=selmServerApply.stServerUserEmail2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_USER_EMAIL%>" >
				</div>
				<label class="form-label col-xs-4 col-sm-2">
				<span
					class="c-red">*</span>计划上线时间：</label>
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
					class="c-red">*</span>申请情况说明：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:260%;height:100px;padding-top:1px;font-size:14px;" type="textarea" class="input-text"
						value="<%=selmServerApply.stServerContent2Html() %>"
						name="<%=SelmServerApply.ST_SERVER_CONTENT%>" placeholder="申请情况说明" required><%=selmServerApply.stServerContent2Html() %></textarea>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				备注：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<textarea rows="10" style="width:260%;height:100px;padding-top:1px;font-size:14px;" type="text" class="input-text"
						value="<%=selmServerApply.stExt12Html() %>"
						name="<%=SelmServerApply.ST_EXT1%>" placeholder="备注"><%=selmServerApply.stExt12Html() %></textarea>
				</div>
			</div>
			<div style="padding-top: 10px;font-size: 18px;padding-left: 79px;font-weight: 600;margin-bottom: -14px;"">设备信息</div>
			<hr style="width: 93%;margin-left: 78px;font-weight: 600;border-color: #a9a9a9;"/>
			
			<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
					
					<button type="button"
	                        class="btn btn-success radius" onclick="device_add('设备添加','/serverApply/selmServerApply/choiceDevice.do','<%=selmServerApply.stApplyId2Html()%>')" href="javascript:;">
	                         <i class="Hui-iconfont">&#xe600;</i>添加设备
	                    </button>
	                </label>
					<label class="form-label col-xs-4 col-sm-1">
					<button type="button"
	                        class="btn btn-primary radius" onclick="batchAddItem('批量添加','/serverApply/selmServerApply/addItem.do')" href="javascript:;">
	                         <i class="Hui-iconfont">&#xe600;</i>批量添加事项
	                </button>
	                </label>
			</div>
			
			<div class="mt-20" style="width: 94%; margin-left: 74px;">
				<table width="100%"
					class="table table-border table-bordered table-bg table-hover table-sort" id="typeList">
					<thead>
						<tr class="text-c">
							<th width="6%" ><input name="" type="checkbox" ></th>
							<th width="14%">网点</th>
							<th width="14%">详细地址</th>
							<th width="11%">MAC</th>
							<th width="9%">是否绑定证书</th>					
							<th width="9%">医保册制册机</th>
							<th width="9%">居住证签注机</th>
							<th width="9%">居住证制卡机</th>
							<th width="7%">服务申请数</th>
							<th width="13%">操作</th>
						</tr>
					</thead> 
					<tbody>
					</tbody>
				</table>
			</div>
			
			 <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-5">
                    <button type="submit"
                        class="btn btn-primary radius" type="button">
                        <i class="Hui-iconfont">&#xe632;</i> 保存
                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button onClick="exit();" class="btn btn-default radius"
                        type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
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
	<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
var stApplyId = "<%=selmServerApply.stApplyId2Html()%>";
var isAdmin = false;
// 管理员权限的判定
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
	//initData()
   // 数据校验
   $("#form-device-add").validate();
   $("#time_off,#time_on").prop("readonly", true).timepicker({
				timeText : '时间',
				hourText : '小时',
				minuteText : '分钟',
				currentText : '现在',
				closeText : '完成',
				showSecond : false, //显示秒  
				timeFormat : 'HH:mm' //格式化时间  
			});
			// 勾选已选择的数据
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



	// 数据提交     
	$.validator.setDefaults({
		submitHandler : function() {
			// 提交数据
			article_save_submit();
			removeSession();
		}
	});
	// 提交内容
	function article_save_submit() {
	var formData = new FormData($("#form-device-add")[0]);
		$.ajax({
			type : "POST",
			url : webRoot + "/serverApply/selmServerApply/save.do",
			data: formData,  
          	async: false,  
          	cache: false,  
          	contentType: false,  
          processData: false,
			error : function(request) {
				layer.msg("服务申请修改失败", {
					icon : 1,
					time : 1000
				});
			},
			success : function(data) {
				layer.msg("服务申请修改成功", {
					icon : 1,
					time : 1000
				});
			}
		});
		setTimeout(function() {
			window.parent.location.reload();
		}, 1000);
	}
	

// 添加
function device_add(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: webRoot+url+'?ST_APPLY_ID='+id
    });
    layer.full(index);
}

var table = $('#typeList').DataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 9, "desc" ]],
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
                       return "暂无";
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
                 { "data": "stAuditUserName",
                   "render":function(data, type, full, meta){
                       return data;
                }},
                
                {
                 "data": "stMachineId",
                 "render": function (data, type, full, meta) {
                  		 	var returnInfo = "<a style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"operationDevice('"+full.stAuditUserId+"网点审批情况','/serverApply/selmServerApply/checkItemStatus.do','"+data+"')\" href=\"javascript:;\" title=\"查看\"><u>查看</u>&nbsp;</a>";
                  		 	returnInfo += "<a style=\"text-decoration:none;color:blue;\" class=\"ml-5\" onClick=\"operationDevice('添加事项','/serverApply/selmServerApply/addItem.do','"+data+"')\" href=\"javascript:;\" title=\"添加事项\"><u>添加事项</u>&nbsp;</a>";
                  			returnInfo += "<a style=\"text-decoration:none;color:red;\" class=\"ml-5\" onClick=\"removeDevice('删除','/serverApply/selmServerApply/removeDevice.do','"+data+"')\" href=\"javascript:;\" title=\"删除设备\"><u>删除</u></a>";
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
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/serverApply/selmServerApply/stDeviceList.do',
		    type:"POST",
		    data: function(d){
		      d.stApplyId = stApplyId;
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#typeList").removeAttr("style"); 
});

//刷新申请id的session
	function removeSession(){
		$.ajax({
			type:"POST",
			url:webRoot+"/serverApply/selmServerApply/edit.do?stServerApplyIdSession=ff",
			error:function(){
			},
			success:function(){
			}
		});
	}
	
	// 未配置数据
	function setNullData(data){
	    if(data == ''){
	        return '<span class=\"label label-danger radius\"></span>';
	    } else {
	        return data;
	    }
	}
	
	function setNullDataStatus(data){
	    if(data == 0){
	        return '否';
	    } else if(data == 1) {
	        return '是';
	    }
	}
	
	//取消
	function exit(){
		removeSession();
		layer_close();
	}
	
	//操作设备
	function operationDevice(title,url,id){
		var index = layer.open({
	        type: 2,
	        title: title,
	        content: webRoot+url+'?ST_DEVICE_ID='+id+'&ST_APPLY_ID='+stApplyId,
	    });
	    layer.full(index);
	}
	
	// 删除
	function removeDevice(title,url,id){
	    layer.confirm('确认要删除吗？',function(index){
	         $.ajax({
	            type : 'POST',
	            url : webRoot+url,
	            dataType:'json',
	            data:{'ST_DEVICE_ID':id, 'ST_APPLY_ID':'<%=selmServerApply.stApplyId2Html()%>'},
	            success : function(myObject) {
	                 layer.msg(myObject.msg,{icon:1,time:1000});
	             },
	            error : function(myObject) {
	                layer.msg(myObject.msg,{icon:1,time:1000});
	            }
	        });
	      	setTimeout(function(){
	      		table.ajax.reload();
	      	},500);
	    });
	}
	
	//批量添加事项
	function batchAddItem(title,url) {
		var selectList = $(".checkchild:checked");
		var idArray = [];
		// 未选择的场合
		if (selectList.length == 0) {
   			layer.msg('请选择需要授权的数据',{icon:2,time:1000});
  			 return;
 		}
 		for (var i = 0; i< selectList.length; i++){
   			idArray.push(selectList[i].value);
		} 
	 	item_add(title,url,idArray);
	}
				
	function item_add(title,url,id){
	    var index = layer.open({
	        type: 2,
	        title: title,
	        content: webRoot+url+'?stDeviceIdList='+id+'&ST_APPLY_ID='+stApplyId,
	    });
	    layer.full(index);
	}


</script>
</body>
</html>