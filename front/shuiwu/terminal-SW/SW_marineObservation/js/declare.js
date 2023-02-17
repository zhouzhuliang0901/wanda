// 切换图片
function ImageShow(className) {
	var $node = $(className);
	if(!$node.hasClass("done")) {
		var $node_ImageShow = $('<div class="imageBox"></div><a class="left"><img class="vertical-middle horizontal-middle" src="images/left.png" width="30" /></a><a class= "right"><img  class="vertical-middle horizontal-middle" src="images/right.png" width="30" /></a><span class="currentPage">0/0</span><div class="imageList" style="opacity: 0"></div>');
		$node.append($node_ImageShow);
		$node.find(".vertical-middle").each(function() {
			$(this).css("position", "absolute");
			var heightP = $(this).parent().outerHeight();
			var heightS = $(this).outerHeight();
			$(this).css("top", (heightP - heightS) / 2);
		});
		$node.find(".horizontal-middle").each(function() {
			$(this).css("position", "absolute");
			var widthP = $(this).parent().outerWidth();
			var widthS = $(this).outerWidth();
			$(this).css("left", (widthP - widthS) / 2);
		});
	}
	this.SetUrls = function(urls) {
		$.each(urls, function(index, value) {
			// http://31.0.1.212:8080/ac
			$node.find(".imageList").append('<img onclick="JumpImageShow(this)"  src="' + value.address + '" />');
		});
		setTimeout('JumpImageShow($("' + className + '").find(".imageList>img").eq(0))');
	}
	$node.find(".left").click(function() {
		var num = $(this).parents(".imageShow").attr("nowImage");
		if(num > 0) {
			num -= 1;
			JumpImageShow($node.find(".imageList img").eq(num));
		}
	});
	$node.find(".right").click(function() {
		var num = $(this).parents(".imageShow").attr("nowImage");
		if($node.find(".imageList img").length > num) {
			num++;
			JumpImageShow($node.find(".imageList img").eq(num));
		}
	});
	$node.find(".imageList img").click(function() {
		JumpImageShow(this);
	});
}

function JumpImageShow(node) {
	$node = $(node).parents(".imageShow");
	$node.find(".imageBox").empty();
	$node.find(".imageBox").append($('<img src="' + $(node).attr('src') + '" />'));
	$node.attr("nowImage", $(node).index());
	var width = $node.width() / 2;
	$node.find(".imageList img").each(function(index, value) {
		if(index == $(node).index()) {
			width -= $(this).width() / 2 + 5;
			return false;
		} else {
			width -= $(this).outerWidth() + 10;
		}
	});
	$node.find(".imageList img").each(function(index, value) {
		$(this).css("left", width + "px");
		width += $(this).width() + 10;
	});
	$node.find("span").html(($(node).index() + 1) + "/" + $node.find(".imageList img").length);
}
var checkList = function(json) { // 如果数据名称的文字过长 截取显示
	for(var i = 0; i < json.length; i++) {
		json[i].itemName = json[i].stItemName;
		if(json[i].itemName.length > 32) {
			json[i].itemName = json[i].itemName.substring(0, 32) + '...';
		}
	}
	return json;
}
// 删除已上传的图片文件
function del(id) {
	$.ajax({
		url: $.getConfigMsg.declareUrl + '/aci/declare/deleteStuffAttach.do',
		type: "get",
		dataType: "jsonp",
		jsonp: "jsonpCallback",
		data: {
			attachId: id,
		},
		success: function(res) {
			if(res.isSuccess) {
				$('.imgBox').find('#'+id).remove();
			}
		},
		error: function(error) {
		}
	});
};

// 动态创建select元素
function createOptions(optionsArr){
	var selectString = '';
	for(var i = 0; i < optionsArr.length; i++){
		selectString += "<option value="+ i +">"+ optionsArr[i] +"</option>"
	}
	return selectString;
}

// 动态的创建info界面元素
function creatInfoElement(contentArr) {
	var eleString = '';
	// 遍历创建每一项元素
	for(var i = 0; i < contentArr.length; i++){
		if(contentArr[i].elementType == 'input'){
			eleString += "<div class='inputBox'>"+
							 "<label class='"+contentArr[i].required+"'>"+contentArr[i].labelName+"</label>"+
							 "<input type='text' id='"+contentArr[i].id+"' name='"+contentArr[i].id+"'>"+
						 "</div>";
		} else if (contentArr[i].elementType == 'select') {
			eleString += "<div class='inputBox'>"+
							 "<label class='"+contentArr[i].required+"'>"+contentArr[i].labelName+"</label>"+
							 "<select class='select' name='"+contentArr[i].id+"' >"+
							 	createOptions(contentArr[i].selectItem)+
						 	 "</select>"+
						 "</div>";
		} else {
			eleString += '';
		}
	}
	return eleString;
}

// 拼接info页面所需的元素
function createInfoContent(itemId) {
	// 1.根据itemId查询要创建的具体元素
	for(var i = 0; i < infoData.length; i++){
		if(itemId == infoData[i].itemId){
			// 创建所需要创建的元素
			return creatInfoElement(infoData[i].itemContent);
		}
	}
}

// 判断填堵河道的审批的事项
function getRoute(index) {
	if(index=="" || index==null){
		return "info";
	}
	return "riverInfo"+index;
}
// 获取填堵河道的审批的相关材料列表
function getRiverStuffList(index) {
	return riverStuffList[index];
}

// 表单元素判空操作
function isInputBlack(infoArr) {
	var flag = true;
	for (var i = 0; i < infoArr.length; i++){
		if(infoArr[i].value == "" || infoArr[i].value == null || infoArr[i].value == undefined){
			flag = false;
			break;
		} else {			
			flag = true;
		}
	}
	return flag;
}
