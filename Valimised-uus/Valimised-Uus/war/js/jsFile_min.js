function progress(){"use strict";function n(){e.style.visibility="visible";t.style.visibility="hidden"}var e=document.getElementById("kandidaatide-tabel"),t=document.getElementById("loaderimg");e.style.visibility="hidden";t.style.visibility="visible";setTimeout(function(){n()},1e3)}function validate(){"use strict";document.getElementById("kasutajaID").value=localStorage.id;var e=document.getElementById("piirkond").selectedIndex,t=document.getElementById("erakond").selectedIndex;if(e===0){document.getElementById("piirkondValidator").innerHTML="Piirkond valimata";document.getElementById("piirkond").style.backgroundColor="#99CCFF";if(t===0){document.getElementById("erakondValidator").innerHTML="Erakond valimata";document.getElementById("erakond").style.backgroundColor="#99CCFF"}else{document.getElementById("erakondValidator").innerHTML="";document.getElementById("erakond").style.backgroundColor="#ffffff"}}else if(t===0){document.getElementById("erakondValidator").innerHTML="Erakond valimata";document.getElementById("piirkondValidator").innerHTML="";document.getElementById("erakond").style.backgroundColor="#99DDFF";document.getElementById("piirkond").style.backgroundColor="#ffffff"}else{document.getElementById("LisaKandidaat").submit()}}$(document).ready(function(){"use strict";var e="erakond";$("#erakond_vaade_nupp").click(function(){console.log("vaade1");if(e!=="erakond"){e="erakond";console.log("muutus1");$("#parem_konteiner").load("../html/erakond_vaade.html #erakond_vaade")}});$("#kandidaadid_vaade_nupp").click(function(){console.log("vaade2");if(e!=="kandidaadid"){e="kandidaadid";console.log("muutus2");$("#parem_konteiner").load("kandidaat_vaade.html #kandidaat_vaade>div",function(){$("#kandidaatide-tabel").tablesorter({sortList:[[0,0]],widthFixed:true})});console.log("tabel1")}});$("#logi_valja").click(function(){$("#logimise_konteiner").load("../html/logimine.html #autentimata")})})