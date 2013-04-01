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
			}
			console.log(output);
			tabelisse(output);

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
			}
			console.log(output);
			tabelisse(output);

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
			}
			console.log(output);
			tabelisse(output);
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
				+ "</td><td>" + piirkond + "</td></tr>";
			}
			console.log(output);
			tabelisse(output);
		});
	}
}
function tabelisse(input) {
	$("#kandidaaditabel_keha").empty();
	$("#kandidaaditabel_keha").append(input);
	$("#kandidaatide-tabel").trigger("update");
}