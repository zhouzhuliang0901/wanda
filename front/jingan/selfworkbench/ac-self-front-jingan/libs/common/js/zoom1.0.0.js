var s;

function resize() {
	s = document.body.clientWidth / 1920;
//	console.log(s);
	document.body.style.transformOrigin = '0 0';
	document.body.style.transform = 'scale(' + s + ',' + s + ')';
	document.body.style.width = window.innerWidth / s + 'px';
	document.body.style.height = window.innerHeight / s + 'px';
}
//console.log(acBridgeMac.vendor());
if(acBridgeMac.vendor() == "wonders") {
	window.onresize = function() {
		resize();
	}
	resize();
}