(function(fun) {
	window.timeLog = fun();
})(function() {
	var timeLog = {};
	timeLog.timeStart = function() {
		timeLog.start = (new Date()).getTime();
	};
	timeLog.timeEnd = function() {
		var timeConsuming = (new Date()).getTime() - timeLog.start;
		console.log("default:" + timeConsuming);
		return timeConsuming;
	}
	return timeLog
})