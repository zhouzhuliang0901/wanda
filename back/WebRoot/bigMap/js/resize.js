/*宽度100%缩放 - 整屏*///缩放的DIV上CSS要加上transform-origin:top left;
function widthFull(shell) {
    var arr = $.type(shell) === 'array' ? shell : [shell];
    $("body").css({ overflow: "hidden" });
    $(window).resize(function () {
        var i = 0, $width = $(window).width(), $height = $(window).height();
        for (; i < arr.length; i++) {
            var wRate = $width / ($(arr[i]).width());
            var hRate = $height / ($(arr[i]).height());
            $(arr[i]).css({ transform: "scale(" + wRate + "," + hRate + ")" });
        }
    }).trigger('resize');

};