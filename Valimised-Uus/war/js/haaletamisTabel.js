$(document).ready(function(){
	$("#haaletusTabel").tablesorter();
	haaletamisOtsing($(".haaletaminePiirkondValik").val());

    $("#haaletusTabel_keha").delegate("tr", "click", function(){
        $(".haaletamisTabelValitud").toggleClass(false);
        $(this).toggleClass("haaletamisTabelValitud");
        var valitud = $(".haaletamisTabelValitud").html();
        var arr;
        arr = valitud.split("<td>");
        var asendus ='<div id="kandValik">kandidaat nr. ' + arr[1].substring(0,arr[1].indexOf("<"))
        +'<h4>'+arr[2].substring(0,arr[2].indexOf("<"))+'</h4>'
		+ arr[3].substring(0,arr[3].indexOf("<"))   
		+'</div>';
        $("#kandValik").replaceWith(asendus  
        );
        
    });
    $('.haaletaminePiirkondValik').change(function() {
    	haaletamisOtsing($(".haaletaminePiirkondValik").val());
	});
	
    
});
function haaletamisOtsing(piirkond){
var url = {
	   Erakond:"Koik",
    	Ringkond:piirkond,
    	Text1:""
};
var kodeeritud = $.param(url);

json = $.getJSON("/test", kodeeritud, function(data) {
			var output = "";
			for ( var i in data.kandidaadid) {
				var isik = data.kandidaadid[i];
				output += "<tr><td>" + isik.id + "</td><td>" + isik.eesnimi 
				+ " " + isik.perenimi +"</td><td>" + isik.erakond
				+ "</td></tr>";
			}
	$("#haaletusTabel_keha").empty();
	$("#haaletusTabel_keha").append(output);
	$("#haaletusTabel").trigger("update");
	});
}