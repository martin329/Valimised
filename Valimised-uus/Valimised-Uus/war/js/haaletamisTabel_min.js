function haaletamisOtsing(e){"use strict";var t={Erakond:"Koik",Ringkond:e,Text1:""};var n=$.param(t);var json=$.getJSON("/test",n,function(e){var t="";for(var n=0;n<e.kandidaadid;n++){var r=e.kandidaadid[n];t+="<tr><td>"+r.id+"</td><td>"+r.eesnimi+" "+r.perenimi+"</td><td>"+r.erakond+"</td></tr>"}$("#haaletusTabel_keha").empty();$("#haaletusTabel_keha").append(t);$("#haaletusTabel").trigger("update")})}$(document).ready(function(){"use strict";$("#haaletusTabel").tablesorter();haaletamisOtsing($(".haaletaminePiirkondValik").val());$("#haaletusTabel_keha").delegate("tr","click",function(){$(".haaletamisTabelValitud").toggleClass(false);$(this).toggleClass("haaletamisTabelValitud");var e=$(".haaletamisTabelValitud").html();var t;t=e.split("<td>");var n=[t[1].substring(0,t[1].indexOf("<")),t[2].substring(0,t[2].indexOf("<")),t[3].substring(0,t[3].indexOf("<"))];var r='<div id="kandValik">kandidaat nr:'+n[0]+"</p>"+"<h4>"+n[1]+"</h4>"+n[2]+"</div>";$("#kandValik").replaceWith(r);$("#kandidaatNr").val(n[0]);$("#haaletajaNr").val(localStorage.getItem("id"))});$(".haaletaminePiirkondValik").change(function(){haaletamisOtsing($(".haaletaminePiirkondValik").val())})})