
/*****脚本*****/


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

//公用单选2
function Publicchoice3(PcClass) {
    $(PcClass + " a").click(function () {
        $(PcClass + " a").removeClass("in");
        $(this).addClass("in");
    })
}

//公用单选3
function PublicchoiceById(PcId) {
    $("#" + PcId + " a").click(function () {
    	if($(this).attr('class')=='in'){
    		$(this).removeClass("in");
    	}else{
    		$("#" + PcId + " a").removeClass("in");
        	$(this).addClass("in");
    	}
    })
}

//省市三级联动
function City(){
	var option = "";
		for(var i = 0; i < nations.length; i++) {
			option += '<option  class="col1" value="' + nations[i].id + '">' + nations[i].shortname + '</option>'; 
		} 
		$(option).appendTo("#nations");
		
		//上海区县		
		var option1 = "";
		for(var i = 0; i < shCounty.length; i++) {
			option1 += '<option  class="col1" value="' + shCounty[i].id + '">' +shCounty[i].shortname + '</option>'; 
		} 
		$(option1).appendTo("#shCounty");

		//省		
		var option2 = "";
		for(var i = 0; i < province.length; i++) {
			option2 += '<option  class="col1" value="' + province[i].id + '">' +province[i].shortname + '</option>'; 
		} 
		$(option2).appendTo("#province");
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
































$(function () {
    //30秒





















})











































































