<%@page import="java.text.SimpleDateFormat"%>
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
<link rel="stylesheet" href="http://v3.bootcss.com/dist/css/bootstrap.min.css" />
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

<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="<%=webRoot%>/sms/lib/ie/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>满意度评价</title>
<style>
.tr{
	height:50px;
}
.tr td{
	font-size:28px;
}
.tr th{
	font-size:30px;
}
</style>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		统计分析<span class="c-gray en">&gt;</span>满意度评价 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="page-container">
		<div class="mt-20">
		<table class="table table-bordered table-hover table-striped table-condensed">
            <tr>
                <td colspan='3'>
                    <h1 style="text-align:center;">“一网通办”自助设备用户满意度评价</h1>
                </td>
            </tr>
            <tr class="tr">
                <th width="33%">评价项目</th>
                <th width="33%">星级</th>
                <th width="33%">满意度</th>
            </tr>
            <tr class="tr" >
                <td>设备满意度</td>
                <td id="machine">
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                </td>
                <td class="score"></td>
            </tr>
            <tr class="tr">
                <td>外观满意度</td>
                <td id="app">
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                </td>
                <td class="score"></td>
            </tr>
            <tr class="tr" >
                <td>操作便携满意度</td>
                <td id="operation">
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                </td>
                <td class="score"></td>
            </tr>
            <tr class="tr" >
                <td>屏幕角度满意度</td>
                <td id="screen">
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                </td>
                <td class="score"></td>
            </tr>
        </table>
        </div>
       
        <div class="mt-20">
        	<table width="100%" class="table table-border table-bordered table-bg table-hover table-sort" id="businessList">
				<thead>
					<tr class="text-c">
						<th width="" >评价设备</th>
						<th width="" >手机号</th>
						<th width="" >评价时间</th>
						<th width="" >设备满意度</th>
						<th width="" >外观满意度</th>
						<th width="" >操作便携满意度</th>
						<th width="" >屏幕角度满意度</th>
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
var permission = "<%= (Object)request.getSession().getAttribute("权限")%>";

var table = $('#businessList').dataTable({
     "aoColumnDefs": [
      {"orderable":false,"aTargets":[0]}// 制定列不参与排序
    ],
    "order": [[ 2, "desc" ]],
    "processing" : true,  
    "serverSide" : true,  
    "searching": false,
    "paginationType":"full_numbers",
    "aoColumns": [
                /*  {
                 "sClass": "text-center",
                 "data": "stQueryHisId",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                  "bSortable": false
                },  */
               
                { "data": "stEvaluateMachineMAC",
                   "render":function(data, type, full, meta){
                       return setNullData(data);
                }},
                { "data": "stEvaluatePhone",
                   "render":function(data, type, full, meta){
                       return setPhone(data);
                }},
                { "data": "dtEvaluateTime",
                   "render":function(data, type, full, meta){
                       return timeInit(data);
                }},
                { "data": "nmSatisfactionMachine",
                   "render":function(data, type, full, meta){
                       return setStatistics(data);
                }},
                { "data": "nmSatisfactionAppearacne",
                   "render":function(data, type, full, meta){
                       return setStatistics(data);
                }},
                { "data": "nmSatisfactionOperation",
                   "render":function(data, type, full, meta){
                       return setStatistics(data);
                }},
                { "data": "nmSatisfactionScreen",
                   "render":function(data, type, full, meta){
                       return setStatistics(data);
                }}
                
                
            ],
       "createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(0).attr('style', 'text-align: center;');
                    $(row).children('td').eq(1).attr('style', 'text-align: center;');
                    $(row).children('td').eq(2).attr('style', 'text-align: center;');
                    $(row).children('td').eq(3).attr('style', 'text-align: center;');
                    $(row).children('td').eq(4).attr('style', 'text-align: center;');
                    $(row).children('td').eq(5).attr('style', 'text-align: center;');
                    $(row).children('td').eq(6).attr('style', 'text-align: center;');
                },
      "bPaginate": true, //翻页功能
      "bLengthChange": true ,//改变每页显示数据数量
       "ajax": {
		    url:webRoot+'/statistics/selmStatistics/selmSatisfactionInfo.do',
		    type:"POST",
		    data: function(d){
		    /* var stAreaId = areaId;
		      d.stAreaId = stAreaId; 
		      var stPermission = permission;
		      d.stPermission = stPermission;  
		      var stItemName = $("#searchItemName").val();
		      d.stItemName = stItemName;
		      var stAreaName = $("#searchAreaName").val();
		      d.stAreaName = stAreaName;
		      console.log($("#startDate").val())
	          var startDate = $("#startDate").val();
	          d.startDate = startDate;
              var endDate = $("#endDate").val();
              d.endDate= endDate; */
		    },
		    error:function(){
		      window.parent.location.reload();
		    }
	    }
}); 

$(function(){
    $("#businessList").removeAttr("style"); 
});


// 查找
function search () {
    table.api().ajax.reload();
}


// 未配置数据
function setNullData(data){
    if(data == ''){
        return '<span class=\"label label-danger radius\"></span>';
    } else {
        return data;
    }
}

function setPhone(data){
	var front = data.substr(0,3);
	var after = data.substr(7,4);
	return front+'****'+after;
}

function setStatistics(data){
	if(data == 5){
		return '非常满意';
	}else if(data == 4){
		return '比较满意';
	}else if(data == 3){
		return '满意';
	}else if(data == 2){
		return '不满意';
	}else if(data == 1){
		return '非常不满意';
	}else{
		return '';
	}
}

function timeInit(data){
	var time = data.time;
	var date = new Date(parseFloat(time)).format("yyyy-MM-dd hh:mm:ss");
	return date;
}

//格式化时间
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

$(function(){
 		$.ajax({
 			type : 'POST',
            url : webRoot+'/statistics/selmStatistics/selmSatisfactionInfoList.do',
            dataType:'json',
            data:{},
            success : function(data) {
                 star(data);
            },
            error : function() {
                
            }
 		
 		});
 
})

function star(data){
	//console.log(data);
	var tip_text = '';
	//设备满意度
	var mac = data.machine;
    $("#machine").children(":lt("+ parseInt(mac) +")").removeClass('glyphicon-star-empty').addClass('glyphicon-star');
    $("#machine").children(":gt("+ eval(parseInt(mac)-1) + ")").removeClass('glyphicon-star').addClass('glyphicon-star-empty');
    tip_text = indexText(mac);
    $("#machine").siblings('.score').html(mac+'(' + tip_text + ')');
   	//外观满意度
    var app = data.app;
    $("#app").children(":lt("+ parseInt(app) +")").removeClass('glyphicon-star-empty').addClass('glyphicon-star');
    $("#app").children(":gt("+ eval(parseInt(app)-1) + ")").removeClass('glyphicon-star').addClass('glyphicon-star-empty');
    tip_text = indexText(app);
    $("#app").siblings('.score').html(app+'(' + tip_text + ')');
    //操作满意度
    var ope = data.operation;
    $("#operation").children(":lt("+ parseInt(ope) +")").removeClass('glyphicon-star-empty').addClass('glyphicon-star');
    $("#operation").children(":gt("+ eval(parseInt(ope)-1) + ")").removeClass('glyphicon-star').addClass('glyphicon-star-empty');
    tip_text = indexText(ope);
    $("#operation").siblings('.score').html(ope+'(' + tip_text + ')');
    //屏幕满意度
    var scr = data.screen;
    $("#screen").children(":lt("+ parseInt(scr) +")").removeClass('glyphicon-star-empty').addClass('glyphicon-star');
    $("#screen").children(":gt("+ eval(parseInt(scr)-1) + ")").removeClass('glyphicon-star').addClass('glyphicon-star-empty');
 	tip_text = indexText(scr);
    $("#screen").siblings('.score').html(scr+'(' + tip_text + ')');
}

//封装文字提示函数
function indexText(i){
    switch(i){
        case 1:
            return '非常不满意';
        case 2:
            return '不满意';
        case 3:
            return '满意';
        case 4:
            return '比较满意';
        case 5:
            return '非常满意';
    }
}
</script>
</body>
</html>