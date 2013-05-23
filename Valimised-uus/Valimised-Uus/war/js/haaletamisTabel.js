$(document).ready(function ()
{
  "use strict";
  $("#haaletusTabel").tablesorter();
  haaletamisOtsing($(".haaletaminePiirkondValik").val());
  $("#haaletusTabel_keha").delegate("tr", "click", function ()
  {
    $(".haaletamisTabelValitud").toggleClass(false);
    $(this).toggleClass("haaletamisTabelValitud");
    var valitud = $(".haaletamisTabelValitud").html();
    var arr;
    arr = valitud.split("<td>");
    var valitudList = [arr[1].substring(0, arr[1].indexOf("<")),
      arr[2].substring(0, arr[2].indexOf("<")),
      arr[3].substring(0, arr[3].indexOf("<"))
    ];
    var asendus = '<div id="kandValik">kandidaat nr:' + valitudList[0] + '</p>' + '<h4>' + valitudList[1] + '</h4>' + valitudList[2] + '</div>';
    $("#kandValik").replaceWith(asendus);
    $("#kandidaatNr").val(valitudList[0]);
    $("#haaletajaNr").val(localStorage.getItem('id'));
  });
  $('.haaletaminePiirkondValik').change(function ()
  {
    haaletamisOtsing($(".haaletaminePiirkondValik").val());
  });
});



function haaletamisOtsing(piirkond)
{
  "use strict";
  var url = {
    Erakond: "Koik",
    Ringkond: piirkond,
    Text1: ""
  };
  var kodeeritud = $.param(url);
  var json = $.getJSON("/test", kodeeritud, function (data)
  {
    var output = "";
    for (var i in data.kandidaadid){
      var isik = data.kandidaadid[i];
      output += "<tr><td>" + isik.id + "</td><td>" + isik.eesnimi + " " + isik.perenimi + "</td><td>" + isik.erakond + "</td></tr>";
    }
    console.log(output);
    $("#haaletusTabel_keha").empty();
    $("#haaletusTabel_keha").append(output);
    $("#haaletusTabel").trigger("update");
  });
}