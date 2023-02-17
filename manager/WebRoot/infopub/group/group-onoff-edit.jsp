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
	InfopubOnoff infopubOnoff = (InfopubOnoff) request
			.getAttribute(InfopubOnoff.INFOPUB_ONOFF);
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
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>组别操作</title>
</head>
<body>
	<div class="page-container">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>开关机周期设定</span><span>特定日开关机设定</span>
			</div>
			<div class="tabCon">
				<form class="form form-horizontal" id="form-onoffweek-add">
					<input type="hidden" name="<%=InfopubOnoff.ST_PTYPE%>"
						maxLength="50" value="WEEK" />
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2"><span
							class="c-red">*</span>设备组别：</label>
						<div class="formControls col-xs-8 col-sm-8 selectGroup">
                              <dl class="permission-list">
                                <dt>
                                    <label>选择设备组</label>
                                </dt>
                                <dd>
                                </dd>
                            </dl>
						</div>
					</div>

					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2"><span
							class="c-red">*</span> 开机时间：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text"
								value="<%=infopubOnoff.stOnTime2Html()%>" id="time_on_week"
								name="<%=InfopubOnoff.ST_ON_TIME%>" required>
						</div>
					</div>

					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2"><span
							class="c-red">*</span> 关机时间：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text"
								value="<%=infopubOnoff.stOffTime2Html()%>" id="time_off_week"
								name="<%=InfopubOnoff.ST_OFF_TIME%>" required>
						</div>
					</div>

					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2">开机周期：</label>
						<div class="formControls col-xs-8 col-sm-8">
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
						</div>
					</div>

					<div class="row cl">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
							<button onClick="onoff_save_submit('form-onoffweek-add');"
								class="btn btn-primary radius" type="submit">
								<i class="Hui-iconfont">&#xe632;</i> 保存
							</button>
							<button onClick="layer_close();" class="btn btn-default radius"
								type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
						</div>
					</div>
				</form>

			</div>

			<div class="tabCon">
				<form class="form form-horizontal" id="form-onoffday-add">
					<input type="hidden" name="<%=InfopubOnoff.ST_PTYPE%>"
						maxLength="50" value="DAY" />
					<div class="row cl">
                        <label class="form-label col-xs-4 col-sm-2"><span
                            class="c-red">*</span>设备组别：</label>
                        <div class="formControls col-xs-8 col-sm-8 selectGroup">
                             <dl class="permission-list">
                                <dt>
                                    <label>选择设备组</label>
                                </dt>
                                <dd>
                                </dd>
                            </dl>
                        </div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2"><span
							class="c-red">*</span> 开机时间：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text"
								value="<%=infopubOnoff.stOnTime2Html()%>" id="time_on_day"
								name="<%=InfopubOnoff.ST_ON_TIME%>" required>
						</div>
					</div>

					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2"><span
							class="c-red">*</span> 关机时间：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text"
								value="<%=infopubOnoff.stOffTime2Html()%>" id="time_off_day"
								name="<%=InfopubOnoff.ST_OFF_TIME%>" required>
						</div>
					</div>

					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-2"><span
							class="c-red">*</span> 定时日期：</label>
						<div class="formControls col-xs-8 col-sm-8">
							<input type="text" class="input-text"
								value="<%=infopubOnoff.dtOnoff2Html("yyyy-MM-dd")%>"
								name="<%=InfopubOnoff.DT_ONOFF%>" id="date_yy-mm-dd" required>
						</div>
					</div>

					<div class="row cl">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
							<button onClick="onoff_save_submit('form-onoffday-add');"
								class="btn btn-primary radius" type="submit">
								<i class="Hui-iconfont">&#xe632;</i> 保存
							</button>
							<button onClick="layer_close();" class="btn btn-default radius"
								type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

    <script type="text/javascript"
        src="<%=webRoot%>/resources/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript"
        src="<%=webRoot%>/resources/layer/2.1/layer.js"></script>
    <script type="text/javascript"
        src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
    <script type="text/javascript"
        src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/validate-methods.js"></script>
    <script type="text/javascript"
        src="<%=webRoot%>/sms/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
    <script type="text/javascript"
        src="<%=webRoot%>/sms/lib/icheck/jquery.icheck.min.js"></script>
    <script type="text/javascript"
        src="<%=webRoot%>/sms/lib/h-ui/js/H-ui.js"></script>
    <script type="text/javascript"
        src="<%=webRoot%>/sms/lib/h-ui.admin/js/H-ui.admin.js"></script>
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
        $(function() {
            $("#form-onoffweek-add").validate();
            $("#form-onoffday-add").validate();

            $('.skin-minimal input').iCheck({
                checkboxClass : 'icheckbox-blue',
                radioClass : 'iradio-blue',
                increaseArea : '20%'
            });
            $.Huitab("#tab-system .tabBar span", "#tab-system .tabCon",
                    "current", "click", "0");

            $("#date_yy-mm-dd").prop("readonly", true).datepicker({
                changeMonth : true,
                dateFormat : "yy-mm-dd",
                onClose : function(selectedDate) {

                }
            });

            $("#time_off_week, #time_off_day,#time_on_week,#time_on_day").prop(
                    "readonly", true).timepicker({
                timeText : '时间',
                hourText : '小时',
                minuteText : '分钟',
                currentText : '现在',
                closeText : '完成',
                showSecond : false, //显示秒  
                timeFormat : 'HH:mm' //格式化时间  
            });

            getDeviceGroupInfo();
        });

        function getDeviceGroupInfo() {
            $.ajax({
                url : webRoot + '/infopub/devicegroup/list.do',
                type : "POST",
                dataType : "json",
                success : function(result) {
                    var select = "<dl class=\"permission-list\"><dt><label>选择设备组</label></dt><dd >";
                    for ( var i = 0; i < result.data.length; i++) {
                        select += "<label><input type=\"checkbox\" value="+result.data[i].stGroupId+
                        " name=\"groupId[]\">"+result.data[i].stGroupName+"</label>";
                    }
                    select += "</dd></dl>";
                    $(".selectGroup").html(select);
                },
                error : function() {
                }
            });
        }

        function onoff_save_submit(id) {
            var formData = $("#" + id).serialize();
           var dataArray = new Array();
           dataArray = formData.split("&");
           for (var i in dataArray) {
               var detail = dataArray[i].split("=");
               if (detail[1] =="") {
                    return;
               }
           }
            $.ajax({
                type : "POST",
                url : webRoot + "/infopub/devicegroup/deviceGroupOnoffSet.do",
                data : formData,// 你的formid
                error : function(request) {
                    layer.msg("添加失败", {
                        icon : 1,
                        time : 1000
                    });
                },
                success : function(data) {
                    layer.msg("添加成功", {
                        icon : 1,
                        time : 1000
                    });
                }
            });
            window.parent.location.reload();
        }
    </script>	
</body>
</html>