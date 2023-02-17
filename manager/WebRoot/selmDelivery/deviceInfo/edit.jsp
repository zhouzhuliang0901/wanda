<%@page import="wfc.service.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubDeviceInfo"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubOnoff"%>
<%@page import="com.wondersgroup.infopub.bean.InfopubWorkspace"%>
<%@page import="coral.base.util.StringUtil"%>
<%@page import="coral.base.app.AppContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="permission" uri="/sms/permission"%>
<%
	String webRoot = AppContext.webRootPath;
	InfopubDeviceInfo infopubDeviceInfo = (InfopubDeviceInfo) request
			.getAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO);
    InfopubOnoff infopubOnoff = (InfopubOnoff) request
            .getAttribute(InfopubOnoff.INFOPUB_ONOFF);
	if (infopubDeviceInfo == null)
		infopubDeviceInfo = new InfopubDeviceInfo();
    if (infopubOnoff == null)
        infopubOnoff = new InfopubOnoff();		
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
<link
	href="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css"
	rel="stylesheet" />
<link href="<%=webRoot%>/sms/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
	<script type="text/javascript"
		src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
<link  href="css/bootstrap.min.css" rel="stylesheet">
<!-- <script type="text/javascript" src="http://www.daimajiayuan.com/download/jquery/jquery-1.10.2.min.js"></script> -->
<!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.css"> -->
<!-- <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script> -->
<!-- <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script> -->
<!-- <link href="css/bootstrap.min.css" rel="stylesheet">-->
<!--  -->
<link rel="stylesheet" type="text/css" href="css/bootstrap-select.css">
<script src="js/bootstrap.min.js"></script> 
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>样表编辑</title>
</head>
<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-device-add">
			<input type="hidden" name="<%=InfopubDeviceInfo.ST_DEVICE_ID%>"
				id="stSampleId" value="<%=infopubDeviceInfo.stDeviceId2Html()%>" />
			<input type="hidden" name="<%=InfopubOnoff.ST_DEVICE_ID%>"
				maxLength="50" value="<%=infopubOnoff.stDeviceId2Html()%>" /> <input
				type="hidden" name="<%=InfopubOnoff.ST_ONOFF_ID%>" maxLength="50"
				value="<%=infopubOnoff.stOnoffId2Html()%>" /> <input type="hidden"
				value="<%=infopubOnoff.stPeriod2Html()%>" id="weekMsg" /> <input
				type="hidden" name="<%=InfopubOnoff.ST_PTYPE%>" maxLength="50"
				value="WEEK" />
			<%-- <input type="hidden" value="<%=infopubDeviceInfo.stWorkspaceId2Html()%>" id="workspace"  /> --%>
			<input type="hidden" value="<%=infopubDeviceInfo.stTypeId2Html()%>"
				id="deviceType" />
			<input type="hidden" value="<%=infopubDeviceInfo.stAddressId2Html()%>"
				id="addressInfo" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 设备名称：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceName2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_NAME %>" required>
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 设备IP：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceIp2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_IP %>" required>
				</div>
				<%-- <label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 设备编号：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceCode2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_CODE%>" required>
				</div> --%>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 设备MAC：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text MacCheck"
						value="<%=infopubDeviceInfo.stDeviceMac2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_MAC%>" disabled="disabled">
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 详细地址：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<!-- <span class="select-box"> --> <select
						name="<%=InfopubDeviceInfo.ST_ADDRESS_ID %>" class="selectpicker show-tick form-control"
						id=id_select  multiple data-live-search="true" data-max-options="1">
							<script id="addressList" type="text/html">
                                        {{each data}}
                                            <option value="{{$value.stAddressId}}" {{if addressId == $value.stAddressId}} selected='selected' {{/if}}>
                                                    {{$value.stCity}}{{$value.stDistrict}}{{$value.stStreet}}{{$value.stAddress}}</option>
                                        {{/each}} 
                            </script>
					</select> <!-- </span> -->
				</div>
				<%-- <label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 详细地址：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stDeviceAddress2Html() %>"
						name="<%=InfopubDeviceInfo.ST_DEVICE_ADDRESS %>" required>
				</div>--%>
			</div> 

			<%-- <div class="row cl">
				
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>消息通道：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stChannel2Html() %>"
						name="<%=InfopubDeviceInfo.ST_CHANNEL%>" required>
				</div>
			</div>
 --%>
			<%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>经度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.nmLng2Html(10) %>"
						name="<%=InfopubDeviceInfo.NM_LNG%>" required>
				</div>
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>纬度：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.nmLat2Html(10) %>"
						name="<%=InfopubDeviceInfo.NM_LAT%>" required>
				</div>
			</div> --%>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span> 设备类型：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<span class="select-box"> <select
						name="<%=InfopubDeviceInfo.ST_TYPE_ID %>" class="select"
						id="deviceTypeSelect">
							<script id="typeList" type="text/html">
                                        {{each data}}
                                            <option value="{{$value.stTypeId}}" {{if typeId == $value.stTypeId}} selected='selected' {{/if}}>
                                                    {{$value.stTypeName}}</option>
                                        {{/each}} 
                            </script>
					</select> </span>
				</div>
		<label class="form-label col-xs-4 col-sm-2">证书唯一标识：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.stCertKey2Html()%>"
						name="<%=InfopubDeviceInfo.ST_CERT_KEY%>">
				</div>
			</div>

			<%-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">开机时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOnoff.stOnTime2Html()%>" id="time_on"
						name="<%=InfopubOnoff.ST_ON_TIME%>">
				</div>
				<label class="form-label col-xs-4 col-sm-2">关机时间：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubOnoff.stOffTime2Html()%>" id="time_off"
						name="<%=InfopubOnoff.ST_OFF_TIME%>">
				</div>
			</div> --%>

			<div class="row cl">
				<!-- <label class="form-label col-xs-4 col-sm-2">开机周期：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<dl class="permission-list">
						<dt>
							<label>周期</label>
						</dt>
						<dd>
							<label><input type="checkbox" value="1" name="week[]">周一
							</label> <label><input type="checkbox" value="2" name="week[]">周二
							</label> <label><input type="checkbox" value="3" name="week[]">周三
							</label> <label><input type="checkbox" value="4" name="week[]">周四
							</label> <label><input type="checkbox" value="5" name="week[]">周五
							</label> <label><input type="checkbox" value="6" name="week[]">周六</label>
							<label><input type="checkbox" value="7" name="week[]">周日</label>
						</dd>
					</dl>
				</div> -->
				<label class="form-label col-xs-4 col-sm-2">
                                                    设备子类型：</label>
                <div class="formControls col-xs-4 col-sm-4">
                <span class="select-box"> 
                       <select name="<%=InfopubDeviceInfo.NM_SDTYPE%>"  class="select" id ="nmSdype">
                            <option value="2"></option>
                            <option value="0">中心政务终端</option>
                            <option value="1">延伸政务终端</option>
                       </select> 
                    </span>      
                </div>
                <label class="form-label col-xs-4 col-sm-2">排序：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input type="text" class="input-text"
						value="<%=infopubDeviceInfo.nmOrder2Html(0) %>"
						name="<%=InfopubDeviceInfo.NM_ORDER%>">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button type="submit" class="btn btn-primary radius" type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>
					<button onClick="layer_close();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>

		</form>
	</div>
	<script type="text/javascript"
		src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-select.js"></script>   
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
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
	<script
		src="<%=webRoot%>/sms/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jQuery-Timepicker/i18n/jquery-ui-timepicker-zh-CN.js"></script>
	<script type="text/javascript"
		src="<%=webRoot%>/sms/lib/jQuery-Timepicker/init.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
	 $(window).on('load', function () {
            $('.selectpicker').selectpicker({
                'selectedText': 'cat'
            });
 
        });
var tem = '<%=infopubDeviceInfo.getNmSdtype()%>';
$("#nmSdype option[value='"+tem+"']").attr("selected","selected");
var isAdmin = false;
// 管理员权限的判定
<shiro:hasRole name="admin">
   isAdmin = true;
</shiro:hasRole>
$(function(){
		initData();
			// 数据校验
			$("#form-device-add").validate();
			// 资源编号唯一性检查
			/* $.validator.addMethod("MacCheck", function(value, element) {
				var flag = false;
				$.ajax({
					type : "POST",
					url : webRoot + "/infopub/deviceinfo/MacCheck.do",
					dataType : "json",
					async : false,
					data : {
						"Mac" : value,
						"ST_DEVICE_ID" : $("#stSampleId").val()
					},
					error : function(request) {
					},
					success : function(result) {
						flag = !result.data;
					}
				});
				return flag;
			}, "设备MAC已存在"); */

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
		
		
		var areaId = "<%= (Object)request.getSession().getAttribute("smsUserStAreaId")%>";
		var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";
		var user= "<%= (String)request.getSession().getAttribute("loginName")%>";
		function initData() {
			$.ajax({
				url : webRoot + '/infopub/deviceinfo/init.do',
				type : "POST",
				data : {
					'stAreaId':areaId,
					'stPermission':permission,
					userName:user
				},
				dataType : "json",
				success : function(result) {
					var devicetype = result.devicetype;
					devicetype.typeId = $("#deviceType").val();
					$("#deviceTypeSelect").html(
							template('typeList', devicetype));
					//设备地址
					var address = result.address;
					address.addressId = $("#addressInfo").val();
					$("#id_select").html(
							template('addressList', address));
				//使用refresh方法更新UI以匹配新状态。
 				$('#id_select').selectpicker('refresh');
 				//render方法强制重新渲染引导程序 - 选择ui。
 				$('#id_select').selectpicker('render');
				},
				error : function() {
				}
			});
		}

		// 数据提交     
		$.validator.setDefaults({
			submitHandler : function() {
				// 提交数据
				article_save_submit();
			}
		});
		// 提交内容
		function article_save_submit() {
			$.ajax({
				type : "POST",
				url : webRoot + "/infopub/deviceinfo/save.do",
				data : $('#form-device-add').serialize(),// 你的formid
				error : function(request) {
					layer.msg("设备信息修改失败", {
						icon : 1,
						time : 1000
					});
				},
				success : function(data) {
					layer.msg("设备信息修改成功", {
						icon : 1,
						time : 1000
					});
				}
			});
			setTimeout(function() {
				window.parent.location.reload();
			}, 1000);
		}
	</script>
</body>
</html>