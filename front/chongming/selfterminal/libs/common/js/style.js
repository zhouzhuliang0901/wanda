$(document).ready(function () {
    //��ֱ����
    $(".vertical-middle").each(function () {
        $(this).css("position", "absolute");
        var heightP = $(this).parent().outerHeight();
        var heightS = $(this).outerHeight();
        $(this).css("top", (heightP - heightS) / 2);
    });
    //
    $(".horizontal-middle").each(function () {
        $(this).css("position", "absolute");
        var widthP = $(this).parent().outerWidth();
        var widthS = $(this).outerWidth();
        $(this).css("left", (widthP - widthS) / 2);
    });

});

/*����Ϊ��Ч*/
function Active(node) {
    $(node).siblings().removeClass('active');
    $(node).addClass('active');
}

/*ͼƬչʾ���*/
function ImageShow(className) {
    var $node = $(className);
    if (!$node.hasClass("done")) {
        var $node_ImageShow = $('<div class="imageBox"></div><a class="left"><img class="vertical-middle horizontal-middle" src="" width="30" /></a><a class= "right"><img  class="vertical-middle horizontal-middle" src="" width="30" /></a><span>0/0</span><div class="imageList"></div>');
        $node.append($node_ImageShow);
        $node.find(".vertical-middle").each(function () {
            $(this).css("position", "absolute");
            var heightP = $(this).parent().outerHeight();
            var heightS = $(this).outerHeight();
            $(this).css("top", (heightP - heightS) / 2);
        });
        $node.find(".horizontal-middle").each(function () {
            $(this).css("position", "absolute");
            var widthP = $(this).parent().outerWidth();
            var widthS = $(this).outerWidth();
            $(this).css("left", (widthP - widthS) / 2);
        });
    }
    this.SetUrls = function (urls) {
        $.each(urls, function (index, value) {
            $node.find(".imageList").append('<img onclick="JumpImageShow(this)"  src="' + value + '" />');
        });
        setTimeout('JumpImageShow($("' + className + '").find(".imageList>img").eq(0))');
    }

    $node.find(".left").click(function () {
        var num = $(this).parents(".imageShow").attr("nowImage");
        if (num > 0) {
            num -= 1;
            JumpImageShow($node.find(".imageList img").eq(num));
        }
    });
    $node.find(".right").click(function () {
        var num = $(this).parents(".imageShow").attr("nowImage");
        if ($node.find(".imageList img").length > num) {
            num++;
            JumpImageShow($node.find(".imageList img").eq(num));
        }
    });
    $node.find(".imageList img").click(function () {
        JumpImageShow(this);
    });
}

function JumpImageShow(node) {
    $node = $(node).parents(".imageShow");
    $node.find(".imageBox").empty();
    $node.find(".imageBox").append($('<img src="' + $(node).attr('src') + '" />'));
    $node.attr("nowImage", $(node).index());
    var width = $node.width() / 2;
    $node.find(".imageList img").each(function (index, value) {
        if (index == $(node).index()) {
            width -= $(this).width() / 2 + 5;
            return false;
        } else {
            width -= $(this).outerWidth() + 10;
        }
    });
    $node.find(".imageList img").each(function (index, value) {
        $(this).css("left", width + "px");
        width += $(this).width() + 10;
    });
    $node.find("span").html(($(node).index() + 1) + "/" + $node.find(".imageList img").length);
}

var $temp = new ImageShow(".imageShow");
$temp.SetUrls(["../img/temp/1.png", "../img/temp/2.jpg", "../img/temp/1.png", "../img/temp/2.jpg"]);