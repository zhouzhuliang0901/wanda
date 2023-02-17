/*****脚本*****/

//30秒
//function time() {
//  var time = 30;
//  var t = setInterval(function(){
//      if (time == -1) {
//          clearInterval(t);
//          return;
//      }
//      $(".minute").text(time);
//      time--;
//  }, 1000)
//}

//公用TAB
function PublicTab1(PcClass) {
	$(PcClass + " .tabul1 li").click(function() {
		var idx = $(this).index();
		$(PcClass + " .tabul1 li").eq(idx).addClass("in").siblings().removeClass("in")
		$(PcClass + " .tabBotbox1 .cont").eq(idx).show().siblings().hide();
	})
}
//公用多选
function Publicchoice1(PcClass) {
	$(PcClass + " a").click(function() {
		$(this).toggleClass("in");
	})
}

//公用单选
function Publicchoice2(PcClass) {
	$(PcClass + " a").click(function() {
		$(this).addClass("in").siblings().removeClass("in");
	})
}

//公用单选2
function Publicchoice3(PcClass) {
	$(PcClass + " a").click(function() {
		$(PcClass + " a").removeClass("in");
		$(this).addClass("in");
	})
}

//公用单选3
function PublicchoiceById(PcId) {
	$("#" + PcId + " a").click(function() {
		$("#" + PcId + " a").removeClass("in");
		$(this).addClass("in");
	})
}
//通过身份证获取出生日期
function getBirthForIdCard(str){
	return (str).substring(6, 10) + "-" + (str).substring(10, 12) + "-" + (str).substring(12, 14);
}

//省市三级联动
function City() {
	//民族		
	var option = "";
	for(var i = 0; i < nations.length; i++) {
		option += '<option  class="col1" value="' + nations[i].id + '">' + nations[i].shortname + '</option>';
	}
	$(option).appendTo("#nations");
	$(option).appendTo("#maleNations");

	//省		
	var option2 = "";
	for(var i = 0; i < province.length; i++) {
		option2 += '<option  class="col1" value="' + province[i].id + '">' + province[i].shortname + '</option>';
	}
	$(option2).appendTo("#province");
	$(option2).appendTo("#liveProvince");
	$(option2).appendTo("#maleProvince");
	$(option2).appendTo("#mLiveProvince");
}

function City1() {
	//民族		
	var option = "";
	for(var i = 0; i < nations.length; i++) {
		option += '<option  class="col1" value="' + nations[i].id + '">' + nations[i].shortname + '</option>';
	}
	$(option).appendTo("#nations");
	$(option).appendTo("#maleNations");

	//省		
	var option2 = "";
		// 省份
var province = [{
		"id": '310000',
		"shortname": '上海市',
		"parentid": ''
	}, {
		"id": '320000',
		"shortname": '江苏省',
		"parentid": ''
	},
	{
		"id": '330000',
		"shortname": '浙江省',
		"parentid": ''
	}, {
		"id": '340000',
		"shortname": '安徽省',
		"parentid": ''
	}
];
	for(var i = 0; i < province.length; i++) {
		option2 += '<option  class="col1" value="' + province[i].id + '">' + province[i].shortname + '</option>';
	}
	$(option2).appendTo("#province");
	$(option2).appendTo("#liveProvince");
	$(option2).appendTo("#maleProvince");
	$(option2).appendTo("#mLiveProvince");
}

function shouliCenter() {
	//事务受理中心-区县	
	var option = "";
	for(var i = 0; i < shCounty.length; i++) {
		option += '<option  class="col1" value="' + shCounty[i].id + '">' + shCounty[i].shortname + '</option>';
	}
	$(option).appendTo("#stCenterCounty");
}

// 就业补贴 - 取证方式
function PublicChoiceForJob(PcId) {
	$("#" + PcId + " a").click(function() {
		$("#" + PcId + " a").removeClass("in");
		$(this).addClass("in");

		var type = $(this).text();
		if("物流寄递" == type) {
			$("#wl").css("display", "block");
			$("#zq").css("display", "none");
			$('#stRecipientPostCode').prop("disabled", false);
			$('#stRecipientPostAddress').prop("disabled", false);
			$('#stRecipientMomile').prop("disabled", false);
			$("#stCenterCounty").prop("disabled",true);
			$("#stCenter").prop("disabled",true);
		} else if("社区事务受理中心自取"==type){
			$("#wl").css("display", "none");
			$("#zq").css("display", "block");
			$('#stRecipientPostCode').prop("disabled", true);
			$('#stRecipientPostAddress').prop("disabled", true);
			$('#stRecipientMomile').prop("disabled", true);
			$('#stCenterCounty').prop("disabled",false);
			$('#stCenter').prop("disabled",false);
		}
	})
}

//弹窗
$.extend({
	loadPopup: function(BoxName) {
		$huodong = $(BoxName);
		var hei = $huodong.height();
		var wid = $huodong.width();
		$(".backgroundPopup").show();
		$huodong.css({
			"marginTop": -(hei / 2),
			"marginLeft": -(wid / 2)
		}).show();
	},
	disablePopup: function(BoxName) {
		$(".backgroundPopup").hide();
		$huodong.hide();
	}
});
//根据父节点筛选对应区县
function filterByName(dataJsonp, condition) {
	var result = [];
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i]['parentid'] == condition) {
			result.push(dataJsonp[i]);
		}
	}
	return result;
}
//根据节点名称得到对应区县信息 
function filterByInfo(dataJsonp, condition) {
	var result;
	for(var i = 0; i < dataJsonp.length; i++) {
		if(dataJsonp[i].shortname == condition) {
			result = dataJsonp[i];
		}
	}
	return result;
}
//$(function () {
//  //30秒
//  time();
//

//
//
//})
//
//