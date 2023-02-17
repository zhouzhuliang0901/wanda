app.directive("slideFollow", function($timeout) {
	return {
		restrict: 'E',
		replace: true,
		scope: {
			id: "@",
			datasetData: "="
		},
		template: "<li ng-repeat = 'data in datasetData'>{{data.option}}&emsp;|&emsp;fairy&emsp;|&emsp;临汾路街道社区事务中心&emsp;|&emsp;2020-05-21&emsp;17:23:41</li>",
		link: function(scope, elem, attrs) {
			$timeout(function() {
				var className = $("." + $(elem).parent()[0].className);
				var i = 0,
					sh;
				var liLength = className.children("li").length;
				var liHeight = className.children("li").height() + parseInt(className.children("li").css('border-bottom-width'));
				className.html(className.html() + className.html());
				// 开启定时器
				sh = setInterval(slide, 2000);

				function slide() {
					if(parseInt(className.css("margin-top")) > (-liLength * liHeight)) {
						i++;
						className.animate({
							marginTop: -liHeight *  i + "px"
						}, "slow");
					} else {
						i = 0;
						className.css("margin-top", "0px");
					}
				}
				// 清除定时器
				className.hover(function() {
					clearInterval(sh);
				}, function() {
					clearInterval(sh);
					sh = setInterval(slide, 2000);
				})
			}, 0)
		}
	}
})
// <div class="slide">
//					<ul class="slideUl">
//						<!-- 指令 -->
//						<slide-follow id="slide" dataset-data = "datasetData">
//						</slide-follow>
//					</ul>
//				</div>