function otsi() {
	progress();
	var json;
	var ringkond = ($("#ringkond_select").val());
	var erakond = ($("#erakond_select").val());
	var nimi = ($("#otsing_nimevali").val());
	var data = $.param($("#searchForm").serializeArray());
	console.log(data);
		json = $.getJSON("/test", data, function(data) {
			var output = "";
			for ( var i in data.kandidaadid) {
				var isik = data.kandidaadid[i];
				output += "<tr><td>" + isik.id + "</td><td>" + isik.eesnimi
				+ "</td><td>" + isik.perenimi + "</td><td>" + isik.erakond
				+ "</td><td>" + isik.piirkond + "</td></tr>";
			}
			console.log(output);
			tabelisse(output);
		})
}
function tabelisse(input) {
	$("#kandidaaditabel_keha").empty();
	$("#kandidaaditabel_keha").append(input);
	$("#kandidaatide-tabel").trigger("update");
}