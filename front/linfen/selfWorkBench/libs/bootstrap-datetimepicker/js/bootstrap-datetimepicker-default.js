/**
 */
(function ($) {
    'use strict';

    $.fn.datetimepicker.DEFAULT = {
    	format: 'yyyy-mm-dd',
    	autoclose: true,
    	todayBtn: true,
    	todayHighlight: true,
    	language: 'zh-CN',
    	minView: 2,
    	fontAwesome: true
    };
    
    $.extend($.fn.datetimepicker.defaults, $.fn.datetimepicker.DEFAULT);
})(jQuery);
