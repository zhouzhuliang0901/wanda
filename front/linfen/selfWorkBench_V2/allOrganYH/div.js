console.log($(window).height()); //浏览器当前窗口可视区域高度
　　
console.log($(document).height()); //浏览器当前窗口文档的高度
　　
console.log($(document.body).height()); //浏览器当前窗口文档body的高度
　　
console.log($(document.body).outerHeight(true)); //浏览器当前窗口文档body的总高度 包括border padding margin

　　
console.log($(window).width()); //浏览器当前窗口可视区域宽度
　　
console.log($(document).width()); //浏览器当前窗口文档对象宽度
　　
console.log($(document.body).width()); //浏览器当前窗口文档body的宽度
　　
console.log($(document.body).outerWidth(true)); //浏览器当前窗口文档body的总宽度 包括border padding margin
var h1 = '<----浏览器当前窗口可视区域高度:' + $(window).height() + '---->';
var h2 = '<----浏览器当前窗口文档的高度:' + $(document).height() + '---->';
var h3 = '<----浏览器当前窗口文档body的高度:' + $(document.body).height() + '---->';
var h4 = '<----浏览器当前窗口文档body的总高度 包括border padding margin:' + $(document.body).outerHeight(true) + '---->';
var w1 = '<----浏览器当前窗口可视区域宽度:' + $(window).width() + '---->';
var w2 = '<----浏览器当前窗口文档对象宽度:' + $(document).width() + '---->';
var w3 = '<----浏览器当前窗口文档body的宽度:' + $(document.body).width() + '---->';
var w4 = '<----浏览器当前窗口文档body的总宽度 包括border padding margin:' + $(document.body).outerWidth(true) + '---->';
setTimeout(function() {
	$('#addtextH').append(h1 + w1);
	$('#addtextH').append("<br>" + h2 + w2);
	$('#addtextH').append("<br>" + h3 + w3);
	$('#addtextH').append("<br>" + h4 + w4);
}, 3000)