function PublicchoiceById(PcId) {
    $("#" + PcId + " a").click(function () {
    	if($(this).attr('class')=='in'){
    		$(this).removeClass("in");
    	}else{
    		$("#" + PcId + " a").removeClass("in");
        	$(this).addClass("in");
    	}
    })
}