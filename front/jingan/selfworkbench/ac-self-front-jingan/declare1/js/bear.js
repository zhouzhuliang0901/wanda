
/*****脚本*****/

//30秒
function time() {
    var time = 60;
    var t = setInterval(function(){
        if (time == -1) {
            clearInterval(t);
            return;
        }
        $(".minute").text(time);
        time--;
    }, 1000)
	
}

//公用TAB
function PublicTab1(PcClass) {
    $(PcClass + " .tabul1 li").click(function () {
        var idx = $(this).index();
        $(PcClass + " .tabul1 li").eq(idx).addClass("in").siblings().removeClass("in")
        $(PcClass + " .tabBotbox1 .cont").eq(idx).show().siblings().hide();
    })
}
//公用多选
function Publicchoice1(PcClass) {
    $(PcClass + " a").click(function () {
        $(this).toggleClass("in");
    })
}

//公用单选
function Publicchoice2(PcClass) {
    $(PcClass + " a").click(function () {
        $(this).addClass("in").siblings().removeClass("in");
    })
}

//弹窗
$.extend({
    loadPopup: function (BoxName) {
        $huodong = $(BoxName);
        var hei = $huodong.height();
        var wid = $huodong.width();
        $(".backgroundPopup").show();
        $huodong.css({"marginTop": -(hei / 2), "marginLeft": -(wid / 2)}).show();
    },
    disablePopup: function (BoxName) {
        $(".backgroundPopup").hide();
        $huodong.hide();
    }
});
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


var checkList = function(json) { // 如果数据名称的文字过长 截取显示
	for(var i = 0; i < json.length; i++) {
		json[i].itemName = json[i].stItemName;
		if(json[i].itemName.length > 32) {
			json[i].itemName = json[i].itemName.substring(0, 32) + '...';
		}
	}
	return json;
}







































































