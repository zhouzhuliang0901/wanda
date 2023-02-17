//var urlHost = 'http://10.81.16.56:8080/ac-product';
var urlHost1 = 'http://hengshui.5uban.com/ac';
var urlHost = "http://hengshui.5uban.com/xhac";
//var urlHost = 'http://172.16.125.53/ac-product';
//var urlHost = 'http://192.168.1.142:8080/ac-product';
var JA_Extranet_urlHost = 'http://xzfwzx.jingan.gov.cn:8080/ac';   // 静安外网地址
//function qumethod(str){
//		switch(str){
//			case "黄浦区":
//				urlHost = "http://zwdt.huangpuqu.sh.cn:8080/ac";
//				break;
//			case "杨浦区":
//				urlHost = "http://zwdtyp.sh.gov.cn:8088/ac";
//				break;
//			case "浦东新区":
//				urlHost = "http://zwdtpd.sh.gov.cn:8082/ac";
//				break;
//			case "嘉定区":
//				urlHost = "http://218.202.254.222";
//				break;
//			case "普陀区":
//				urlHost = "";
//				break;
//			case "静安区":
//				urlHost = "http://xzfwzx.jingan.gov.cn:8080/ac";
//				break;
//			case "虹口区":
//				urlHost = "http://117.184.33.148:8080/ac-product";
//				break;
//		}
//	}
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
		url: urlHost + '/aci/declare/deleteStuffAttach.do',
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