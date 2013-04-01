function progress() {
	var x = document.getElementById("kandidaatide-tabel");
	var z = document.getElementById("loaderimg");
	function showdivs() {
		x.style.visibility = "visible";
		z.style.visibility = "hidden";
	}
	x.style.visibility = "hidden";
	z.style.visibility = "visible";
	setTimeout(function() {
		showdivs()
	}, 1000);

}

function validate() {
	var selectPiirkond = (document.getElementById("piirkond").selectedIndex);
	var selectErakond = (document.getElementById("erakond").selectedIndex);
	if (selectPiirkond == 0) {
		document.getElementById("piirkondValidator").innerHTML = "Piirkond valimata";
		document.getElementById("piirkond").style.backgroundColor = '#99CCFF';
		if (selectErakond == 0) {
			document.getElementById("erakondValidator").innerHTML = "Erakond valimata";
			document.getElementById("erakond").style.backgroundColor = '#99CCFF';

		} else {
			document.getElementById("erakondValidator").innerHTML = "";
			document.getElementById("erakond").style.backgroundColor = '#ffffff';
		}
	} else if (selectErakond == 0) {
		document.getElementById("erakondValidator").innerHTML = "Erakond valimata";
		document.getElementById("piirkondValidator").innerHTML = "";
		document.getElementById("erakond").style.backgroundColor = '#99DDFF'
		document.getElementById("piirkond").style.backgroundColor = '#ffffff'
	} else {
		document.getElementById("LisaKandidaat").submit();
		// window.location.href = "lisamiseTagasiside.html";
	}
}
function otsi() {
	progress();
	var json;
	var ringkond = ($("#ringkond_select").val());
	var erakond = ($("#erakond_select").val());
	var nimi = ($("#otsing_nimevali").val());
	var data = $.param($("#searchForm").serializeArray());
	console.log(data);
	if (ringkond === "Kogu Eesti" && erakond === "Koik" && nimi !== "") {
		console.log("nimi");
		data = data + "&type=4";
		json = $.getJSON("/test", data, function(data) {
			var output = "";
			for ( var i in data.kandidaadid) {
				var isik = data.kandidaadid[i];
				output += "<tr><td>" + isik.id + "</td><td>" + isik.eesnimi
						+ "</td><td>" + isik.perenimi + "</td><td>" + erakond
						+ "</td><td>" + isik.piirkond + "</td></tr>";
				console.log(output);
				tabelisse(output);
			}
		});
	} else if (ringkond !== "Kogu Eesti" && erakond === "Koik" && nimi === "") {
		console.log("piirkond");
		data = data + "&type=2";
		json = $.getJSON("/test", data, function(data) {
			var output = "";
			for ( var i in data.kandidaadid) {
				var isik = data.kandidaadid[i];
				output += "<tr><td>" + isik.id + "</td><td>" + isik.eesnimi
						+ "</td><td>" + isik.perenimi + "</td><td>" + erakond
						+ "</td><td>" + isik.piirkond + "</td></tr>";
				console.log(output);
				tabelisse(output);
			}
		});
	} else if (ringkond === "Kogu Eesti" && erakond !== "Koik" && nimi === "") {
		console.log("erakond");
		data = data + "&type=1";
		json = $.getJSON("/test", data, function(data) {
			var output = "";
			for ( var i in data.kandidaadid) {
				var isik = data.kandidaadid[i];
				output += "<tr><td>" + isik.id + "</td><td>" + isik.eesnimi
						+ "</td><td>" + isik.perenimi + "</td><td>" + erakond
						+ "</td><td>" + isik.piirkond + "</td></tr>";
				console.log(output);
				tabelisse(output);
			}
		});
	} else if (ringkond !== "Kogu Eesti" && erakond !== "Koik") {
		console.log("piirkond+erakond");
		data = data + "&type=3";
		json = $.getJSON("/test", data, function(data) {
			var output = "";
			for ( var i in data.kandidaadid) {
				var isik = data.kandidaadid[i];
				output += "<tr><td>" + isik.id + "</td><td>" + isik.eesnimi
						+ "</td><td>" + isik.perenimi + "</td><td>" + erakond
						+ "</td><td>" + isik.piirkond + "</td></tr>";
				console.log(output);
				tabelisse(output);
			}
		});
	}
}
function tabelisse(input) {
	$("#kandidaaditabel_keha").empty();
	$("#kandidaaditabel_keha").append(input);
	$("#kandidaatide-tabel").trigger("update");
}
$(document)
		.ready(
				function() {
					var vaade = "erakond";
					$("#erakond_vaade_nupp")
							.click(
									function erakond() {
										console.log("vaade1");
										if (vaade != "erakond") {
											vaade = "erakond";
											console.log("muutus1");
											$("#parem_konteiner")
													.load(
															"../html/erakond_vaade.html #erakond_vaade");
										}
									});
					$("#kandidaadid_vaade_nupp")
							.click(
									function kandidaadid() {
										console.log("vaade2");
										if (vaade != "kandidaadid") {
											vaade = "kandidaadid";
											console.log("muutus2");
											$("#parem_konteiner")
													.load(
															"kandidaat_vaade.html #kandidaat_vaade>div",
															function() {
																$(
																		"#kandidaatide-tabel")
																		.tablesorter(
																				{
																					sortList : [ [
																							0,
																							0 ] ],
																					widthFixed : true
																				});
															});
											console.log("tabel1");
										}
									});

					$("#logi_valja").click(
							function logivalja() {
								$("#logimise_konteiner").load(
										"../html/logimine.html #autentimata");
							});

				});
