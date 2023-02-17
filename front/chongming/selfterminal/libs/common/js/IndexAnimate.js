
$(".index .mainIndex").click(function () {
    subIndex($(".index .mainIndex").index(this));
});

$(".subIndex .before").click(function () {
    subIndex(mainIndex-1);
});

$(".subIndex .after").click(function () {
    subIndex(mainIndex + 1);
});

var mainIndex = 0;
function subIndex(index) {
    $(".home").fadeIn();
    $(".logo").animate({ left: "120px"});
    mainIndex = index;
    var $this = $(".index").eq(index);
    $(".index .sideIndex").fadeOut();
    $(".index .icon").fadeIn();
    $(".index .subIndex").fadeOut();
    $(".subIndex .before").remove();
    $(".subIndex .after").remove();
    $(".index").each(function (i, n) {
        if (index != i) {
            $(this).fadeOut();
        }
    });
    if (index > 0) {
        $this.find(".subIndex").append($("<div class='before' onclick='subIndex(" + (mainIndex - 1) + ")' />"));
    }
    if (index < $(".index").length - 1) {
        $this.find(".subIndex").append($("<div class='after' onclick='subIndex(" + (mainIndex + 1) + ")' />"));
    }
    $(".index .mainIndex").fadeOut(function () {
        $this.find(".subIndex").fadeIn();
        var widthNum = Math.ceil($this.find(".subContainer > div").length / 3);
        $this.find(".icon").animate({ width: "250px", left: "250px", top: "180px" });
        $this.animate({ width: widthNum * 250 + 580 + "px", left: 670 - widthNum * 125 });
        $this.find(".subIndex>.subContainer").width(widthNum * 250 + 30);

        $(".index").each(function (i, n) {
            if (index - 1 == i) {
                $(this).find(".sideIndex").fadeIn();
                $(this).find(".icon").css("display", "none");
                $(this).css("left", "0px");
                $(this).css("width", "150px");
                $(this).fadeIn();
            }
            if (index + 1 == i) {
                $(this).find(".sideIndex").fadeIn();
                $(this).find(".icon").css("display", "none");
                $(this).css("left", "1770px");
                $(this).css("width", "150px");
                $(this).fadeIn();
            }
        });
    });
}

function IndexReset() {
    $(".home").fadeOut();
    $(".logo").animate({ left: "550px" });
    $(".index .subIndex").eq(mainIndex).fadeOut(function () {
        $(".index").each(function (i, n) {
            if (i != mainIndex) {
                $(this).fadeOut();
            }
        });
        setTimeout('IndexReset_sub()',500);
    });
}
function IndexReset_sub() {
    $(".index .sideIndex").fadeOut();
    $(".index .icon").fadeIn();
    $(".index").animate({ width: "400px" });
    $(".index>.icon").animate({ left: "200px", top: "120px", width: "240px" });
    $(".index").each(function (i, n) {
        $(this).animate({ left: (i * 420 + 120) + "px"});
        $(this).removeClass("side");
    });
    setTimeout('$(".index").fadeIn();$(".index .mainIndex").fadeIn();', 200);
}

function subIndex_2(index) {
    $(".index").each(function (i, n) {
        if (index != i){
            $(".index .subIndex").fadeOut();
            $(".index .sideIndex").fadeIn();
        }

    });
}

IndexReset();