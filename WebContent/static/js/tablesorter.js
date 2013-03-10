$(function() {
		$("#kandidaatide-tabel").tablesorter({theme:'blue',sortList:[[0,0],[2,1]],widgets:['zebra'],widthFixed:true});
		$(".compatibility").tablesorter({theme:'blue',sortList:[[0,0]],widthFixed:true});
		$("table.options").tablesorter({theme:'blue',sortList:[[0,0]],headers:{3:{sorter:false},4:{sorter:false}},widgets:['stickyHeaders'],widthFixed:true});
		$("#method,#event").tablesorter({theme:'blue',sortList:[[0,0]],headers:{1:{sorter:false},2:{sorter:false}},widgets:['stickyHeaders'],widthFixed:true});
	});
	
