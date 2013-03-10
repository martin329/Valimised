function progress() {
	function showdivs() {
		x.style.visibility = "visible";
		y.style.visibility = "visible";
		z.style.visibility = "hidden";
	}
	var x = document.getElementById("statistika_kandidaadid");
	x.style.visibility = "hidden";
	var y = document.getElementById("kandidaat_nupud");
	y.style.visibility = "hidden";
	var z = document.getElementById("loaderimg");
	z.style.visibility = "visible";
	setTimeout(function(){showdivs()}, 1000);
	
	
}

function validate() {
	var selectPiirkond = (document.getElementById("piirkond").selectedIndex);
	var selectErakond = (document.getElementById("erakond").selectedIndex);
	if (selectPiirkond == 0) {
		document.getElementById("piirkondValidator").innerHTML="Piirkond valimata";
		document.getElementById("piirkond").style.backgroundColor='#99CCFF';
		if (selectErakond == 0) {
			document.getElementById("erakondValidator").innerHTML="Erakond valimata";
			document.getElementById("erakond").style.backgroundColor='#99CCFF';
		}
		else {
			document.getElementById("erakondValidator").innerHTML="";
			document.getElementById("erakond").style.backgroundColor='#ffffff';
		}
	}
	else if (selectErakond == 0) {
		document.getElementById("erakondValidator").innerHTML="Erakond valimata";
		document.getElementById("piirkondValidator").innerHTML="";
		document.getElementById("erakond").style.backgroundColor='#99DDFF'
		document.getElementById("piirkond").style.backgroundColor='#ffffff'
	}
	else {
		window.location.href = "lisamiseTagasiside.html";
	}
}
$( document ).ready(function() {
	var vaade = "erakond";
	$("#erakond_vaade_nupp").click(function erakond(){
		console.log("vaade1");
		if (vaade != "erakond"){
			vaade = "erakond";
			console.log("muutus1");
			$("#parem_konteiner").load("../html/erakond_vaade.html #erakond_vaade");
		}
	});
	$("#kandidaadid_vaade_nupp").click(function kandidaadid(){
		console.log("vaade2");
		if (vaade != "kandidaadid"){
			vaade = "kandidaadid";
			console.log("muutus2");
			$("#parem_konteiner").load("../html/kandidaat_vaade.html");
			
		}
	});
	
	$("#logi_valja").click(function logi_valja(){
		$( "#logimise_konteiner").load("../html/logimine.html #autentimata");
	});

	

});