(function(log) {
	window.loginLog = log();

})(
	function() {
		var __obj = {};
		__obj.setLoginRecord = function(data) {
			if(!localStorage.getItem("localLog")) {
				localStorage.setItem("localLog", JSON.stringify([data]));
			} else {
				var logArr = JSON.parse(localStorage.getItem("localLog"));
				logArr.push(data);
				localStorage.setItem("localLog", JSON.stringify(logArr));

			}
		}
		__obj.getLoginRecord = function() {
			return localStorage.getItem("localLog")
		}
		return __obj;
	}
)