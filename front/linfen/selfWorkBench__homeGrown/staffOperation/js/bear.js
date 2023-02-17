function PublicchoiceById(PcId) {
	console.log(1);
	console.log($("#" + PcId + " tr"));
    $("#" + PcId + " tr").click(function () {
    	if($(this).attr('class')=='in'){
    		$(this).removeClass("in");
    	}else{
    		$("#" + PcId + " tr").removeClass("in");
        	$(this).addClass("in");
    	}
    })
}