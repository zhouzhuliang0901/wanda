
/*****脚本*****/

/* 登录页整体大小 */
function loginResize(){
	var winWidth = $(window).width()/80;
	$("html").css("font-size",winWidth+"px");
}

/* 菜单下拉 */
function menuTool(){
	//展开收起	切换
	$("body").on("click",".shell-col",function(){
		if($(".shell").hasClass("col")){
			$(".menupop-mask").remove();
			$(".menupop").remove();
			$(".shell-m>li h4").removeClass("view");
			$(".shell").removeClass("col");
			$(".shell-m a.active").parents("li").addClass("active")
		}else{
			$(".shell-m li").removeClass("active");
			$(".shell").addClass("col");
		}
	});
	//展开状态	点击菜单
	$("body").on("click",".shell:not(.col) .shell-m h4",function(){
		$(this).parent().toggleClass("active");
		$(this).parent().parent().siblings().children("dt").removeClass("active");
	});
	//展开状态	点击链接
	$("body").on("click",".shell-m a",function(){
		$("#top").css("display","none");
		$("#iframe_box").css("display","block");
		$(".shell-m a").removeClass("active");
		$(this).addClass("active");
		var newPaging = false;
		var thisAds = $(this).attr("_href");
		var thisSymbol = $(this).attr("index");
		var thisText = $(this).html();
		$(".open-paging a").each(function(){
			if($(this).attr("index")==thisSymbol){
				newPaging = true;
			}
		});
		$(".open-paging li").removeClass("active");
		if(newPaging){
			$(".open-paging a[index="+thisSymbol+"]").parent().addClass("active");
		}else{
			$(".open-paging").append("<li class='active'><a href='"+thisAds+"' index='"+thisSymbol+"' target='mainIframe'>"+thisText+"</a><i>&times;</i></li>");
		}
	});
	//收起状态	点击菜单
	$("body").on("click",".shell.col .shell-m h4",function(){
		$(".menupop-mask").remove();
		$(".menupop").remove();
		if($(this).hasClass("view")){
			$(this).removeClass("view");
		}else{
			$(".shell-m h4").removeClass("view");
			$(this).addClass("view");
			var menuvalue = $(this).parent().index();
			//var menupop = $(this).siblings("ul").html();
			//$("body").append("<div class='menupop-mask'></div><ul class='menupop'>"+menupop+"</ul>");
			//$(".menupop").css("top",70+42*menuvalue+"px");
			//$(".menupop").css("top","0px");
		}
	});
	//收起状态	浮出菜单
	$("body").on("click",".menupop h4",function(){
		if($(this).parent().hasClass("view")){
			$(this).parent().removeClass("view");
		}else{
			$(".menupop li").removeClass("view");
			$(this).parent().addClass("view");
		}
	});
	//收起状态	点击链接
	$("body").on("click",".menupop a",function(){
		$(".menupop a").removeClass("active");
		$(this).addClass("active");
		var newPaging = false;
		var thisAds = $(this).attr("_href");
		var thisSymbol = $(this).attr("index");
		var thisText = $(this).html();
		$(".shell-m a").removeClass("active");
		$(".shell-m a[index="+thisSymbol+"]").addClass("active");
		$(".open-paging a").each(function(){
			if($(this).attr("index")==thisSymbol){
				newPaging = true;
			}
		});
		$(".open-paging li").removeClass("active");
		if(newPaging){
			$(".open-paging a[index="+thisSymbol+"]").parent().addClass("active");
		}else{
			$(".open-paging").append("<li class='active'><a href='"+thisAds+"' index='"+thisSymbol+"' target='mainIframe'>"+thisText+"</a><i>&times;</i></li>");
		}
	});
	//收起状态	点击任意	收起菜单浮框
	$("body").on("click",".menupop-mask",function(){
		$(this).remove();
		$(".menupop").remove();
		$(".shell-m h4").removeClass("view");
	});
}

/* 多开栏 */
function openPaging(){
	//切换
	$(".open-paging").on("click","li a",function(){
		$("#top").css("display","block");
		$("#iframe_box").css("display","none");
		var thisSymbol = $(this).attr("index");
		$(this).parent().addClass("active").siblings().removeClass("active");
		$(".shell-m a").removeClass("active");
		$(".shell-m a[index="+thisSymbol+"]").addClass("active");
	});
	//删除
	$(".open-paging").on("click","li i",function(){
		$(this).parent().remove();
	});
}

/* 系统操作 下拉 */
function vdrop(){
	$(".vdrop").on("click","h4",function(){
		$(this).parent().toggleClass("view");
	});
	$(".vdrop").on("click",".vdmask,a",function(){
		$(this).parents(".vdrop").removeClass("view");
	});
}