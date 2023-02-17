var webRoot = "";
var stProjectId = "";
var stModuleId = "";
var category = "Y";
$(function(){
	webRoot = getRootPath_web();
	var str = getRequest();
	if("ST_PROJECT_ID" == str[0]){
		stProjectId = str[1];
		if(str.length > 2){
			category = str[3];
		}
		getData();
	}else if("ST_MODULE_ID" == str[0]){
		stModuleId = str[1];
		if(str.length > 2){
			category = str[3];
		}
		getData1();	
	}
});
//按项目展示
function getData(){
	$.ajax({
		type: 'post',
		url : webRoot + '/apidoc/project/getInfo.do',
		dataType: 'json',
		data:{'stProjectId':stProjectId},
		success:function(data){
			document.title = data.allInfo.stProjectName;
			show(data);
		},
		error : function() {
		}
	});
}
//按模块展示
function getData1(){
	$.ajax({
		type: 'post',
		url : webRoot + '/apidoc/module/getAllInfo.do',
		dataType: 'json',
		data:{'stModuleId':stModuleId},
		success:function(data){
			document.title = data.allInfo.stModuleName;
			show(data);
		},
		error : function() {
		}
	});
}

//数据显示 
function show(data){
	layui.use('laytpl', function(){
		var laytpl = layui.laytpl;
		var getTpl = interfaceList.innerHTML
		,view = document.getElementById('view');
		data.isShow = category;
		laytpl(getTpl).render(data, function(html){
		  view.innerHTML = html;
		  index(6);
		}); 
	});
}

// 获取当前根目录
function getRootPath_web() {
     //获取当前网址
     var curWwwPath = window.document.location.href;
     //获取主机地址之后的目录
     var pathName = window.document.location.pathname;
     var pos = curWwwPath.indexOf(pathName);
     //获取主机地址
     var localhostPath = curWwwPath.substring(0, pos);
     //获取带"/"的项目名
     var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
     return (localhostPath + projectName);
} 

//获取query string parameters stModuleId
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

function index(num){
	var level = new Array();
	var node = new Array()
	for(i=0;i<num;i++){
		level[i] = 0;
	}
	$("article").children().each(function(i){
		switch (this.tagName){
			case "H2":
				level[0]++;
				for(i=1;i<num;i++)
					level[i] = 0;
				var idValue = "level_"+level[0];
				this.id = idValue;
				var name = $(this).text();
				var a = name.split("、").join(" ");
				var $valueNode = $('<li onclick="IndexChange(this);"><a href="#'+idValue+'">'+a+'</a>\
				   <ul>\
					</ul>\
				</li>');
				$(".mainIndex>div>ul").append($valueNode);
				node[0] = $valueNode;
				break;
			case "H3":if(num > 1){
				level[1]++;
				for(i=2;i<num;i++)
					level[i] = 0;
				var idValue = "level_"+level[0]+"_"+level[1];
				this.id = idValue;
				var $valueNode = $('<li><a href="#'+idValue+'">'+$(this).text()+'</a><ul></ul></li>');
				node[0].children("ul").append($valueNode);
				node[1] = $valueNode;
				}break;
			case "H4":if(num > 2){
				level[2]++;
				for(i=3;i<num;i++)
					level[i] = 0;
				var idValue = "level_"+level[0]+"_"+level[1]+"_"+level[2];
				this.id = idValue;
				var $valueNode = $('<li><a href="#'+idValue+'">'+$(this).text()+'</a><ul></ul></li>');
				node[1].children("ul").append($valueNode);
				node[2] = $valueNode;
				}break;
			case "H5":if(num > 3){
				level[3]++;
				for(i=4;i<num;i++)
					level[i] = 0;
				var idValue = "level_"+level[0]+"_"+level[1]+"_"+level[2]+"_"+level[3];
				this.id = idValue;
				var $valueNode = $('<li><a href="#'+idValue+'">'+$(this).text()+'</a><ul></ul></li>');
				node[2].children("ul").append($valueNode);
				node[3] = $valueNode;
				}break;
			case "H6":if(num > 4){
				level[4]++;
				for(i=5;i<num;i++)
					level[i] = 0;
				var idValue = "level_"+level[0]+"_"+level[1]+"_"+level[2]+"_"+level[3]+"_"+level[4];
				this.id = idValue;
				var $valueNode = $('<li><a href="#'+idValue+'">'+$(this).text()+'</a><ul></ul></li>');
				node[3].children("ul").append($valueNode);
				node[4] = $valueNode;
				}break;
			case "H7":if(num > 5){
				level[5]++;
				for(i=6;i<num;i++)
					level[i] = 0;
				var idValue = "level_"+level[0]+"_"+level[1]+"_"+level[2]+"_"+level[3]+"_"+level[4]+"_"+level[5];
				this.id = idValue;
				var $valueNode = $('<li><a href="#'+idValue+'">'+$(this).text()+'</a><ul></ul></li>');
				node[4].children("ul").append($valueNode);
				node[5] = $valueNode;
				}break;
		}
	});
	$(".mainIndex>div>ul>li:first-child").addClass("active");
}


function IndexChange(obj){
	$(".mainIndex>div>ul").children().each(function(i){
		$(this).removeClass("active");
	});
	$(obj).addClass("active");	
}