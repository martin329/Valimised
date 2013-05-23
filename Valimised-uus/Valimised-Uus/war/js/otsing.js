function otsi() {
  "use strict";
  progress();
  var json;
  var ringkond = ($("#ringkond_select").val());
  var erakond = ($("#erakond_select").val());
  var nimi = ($("#otsing_nimevali").val());
  var data = $.param($("#searchForm").serializeArray());
  var i = 0;
  console.log(data);
  json = $.getJSON("/test", data, function (data) {
	console.log(data);
    var output = "";
    for ( var i in data.kandidaadid) {
      var isik = data.kandidaadid[i];
      output += "<tr id=\""+isik.id+"\"><td>" + isik.id + "</td><td>" + isik.eesnimi + "</td><td>" + isik.perenimi + 
      "</td><td>" + isik.erakond + "</td><td>" + isik.piirkond + "</td><td>" + isik.haali + "</td></tr>";
    }
    console.log(output);
    tabelisse(output);
  });
}

function tabelisse(input) {
  "use strict";
  $("#kandidaaditabel_keha").empty();
  $("#kandidaaditabel_keha").append(input);
  $("#kandidaatide-tabel").trigger("update");
}