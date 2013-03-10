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