app.controller("mainController", function ($scope,data) {
	$scope.haveList = true;
    $scope.questiones = [
    	{
    		question: "哪些项目需要办理排水许可证？",
    		answer: "从2018年1月1日开始，在本市行政区域内，从事工业、建筑、餐饮、医疗、畜禽养殖、屠宰、有消毒排水的宾馆酒店服务、有化学实验排水的科研以及列车、轨道交通车辆、汽车的修理等活动，"
    				+"向城镇排水设施排放污水的企业事业单位、个体工商户，应当依法申请领取排水许可证。"
					+"即属于以上11个特定行业的需办理排水许可证，不属于特定行业的，无需办理排水许可证，可直接申请排水接入。",
    		id: 1
    	},
    	{
    		question: "对排水户内部雨、污水管网设计和施工时有何要求?",
    		answer: "排水户内部雨、污水管网应按雨、污分流设计；污水水质须达标排放，特别对于第一类污染物的废水须经有效处理达标后方可纳管；施工时，不得将雨、污水管道混接；施工前，须至区（县）水务部门办理施工期间的《排水许可证》。",
    		id: 2
    	},
    	{
    		question: "如何计算排水户的污水量？ ",
    		answer: "污水是生活污水与产业废水的统称。一般情况，污水量是用水量的0.9倍；冷却水、锅炉用水与绿化用水等不计入其内，不排入市政污水管网。",
    		id: 3
    	},
    	{
    		question: "什么是排放口？",
    		answer: "排放口是排水户自建排水管网接入城市排水管网前末端井位置，根据《上海市排水管理条例》第十七条规定，污水总排放口处需设置排水专用检测井。",
    		id: 4
    	},
    	{
    		question: "关于排水户排放的污、废水适用的排放标准？",
    		answer: "上海目前执行国家标准和行业标准，它们分别是："
					+"（1）GB/T 31962-2015《污水排入城镇下水道水质标准》、DB31/199-2018《污水综合排放标准》"
					+"（2）GB18466-2005《医疗机构水污染物排放标准》、GB25463-2010《油墨工业水污染物排放标准》、GB25461-2010《淀粉工业水污染物排放标准》、GB4287-2012《纺织染整工业水污染物排放标准》等相关行业标准.",
    		id: 5
    	}
    ]
    // 展示问题答案
    data.questiones = $scope.questiones;
    $scope.showAnswer = function(index){
    	$scope.haveList = false;
    	$scope.answer = data.questiones[index].answer;
    }
    // 初始化滚动条
    angular.element(document).ready(function() {
		$(".inner-box").mCustomScrollbar({
			theme: "dark-thin",
			scrollInertia: 400
		});
	});
    //滚动条上移
	$scope.moveUp = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "+=" + $scope.rollingSpeed);
	};
	//滚动条下移
	$scope.moveDown = function() {
		$(".inner-box").mCustomScrollbar("scrollTo", "-=" + $scope.rollingSpeed);
	}
	// 返回问题列表
	$scope.returnQ = function(){
		$scope.haveList = true;
	}
});