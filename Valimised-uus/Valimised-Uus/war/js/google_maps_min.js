function displayMap(){"use strict";document.getElementById("kaart").style.display="block";initialize()}function initialize(){"use strict";var e={zoom:7,center:new google.maps.LatLng(58.887619,25.565186),mapTypeControl:true,mapTypeControlOptions:{style:google.maps.MapTypeControlStyle.DROPDOWN_MENU},navigationControl:true,mapTypeId:google.maps.MapTypeId.ROADMAP},t=new google.maps.Map(document.getElementById("kaart"),e);google.maps.event.addListener(t,"click",function(){infowindow.close()});var n=new google.maps.LatLng(58.383189,24.496478);var r=createMarker(n,"Pärnumaa");var n=new google.maps.LatLng(57.955674,26.575928);var r=createMarker(n,"Võru-, Valga- ja Põlvamaa");var n=new google.maps.LatLng(58.369034,26.729319);var r=createMarker(n,"Tartu linn");var n=new google.maps.LatLng(58.545328,26.707764);var r=createMarker(n,"Jõgeva- ja Tartumaa");var n=new google.maps.LatLng(58.642653,25.52124);var r=createMarker(n,"Järva- ja Viljandimaa");var n=new google.maps.LatLng(59.3583,27.413001);var r=createMarker(n,"Ida-Virumaa");var n=new google.maps.LatLng(59.3495,26.348);var r=createMarker(n,"Lääne-Virumaa");var n=new google.maps.LatLng(58.9468,23.5316);var r=createMarker(n,"Hiiu-, Lääne- ja Saaremaa");var n=new google.maps.LatLng(59.094204,24.54895);var r=createMarker(n,"Harju- ja Raplamaa");var n=new google.maps.LatLng(59.409341,24.676306);var r=createMarker(n,"Tallinna Mustamäe ja Nõmme linnaosa");var n=new google.maps.LatLng(59.432618,24.743858);var r=createMarker(n,"Tallinna Kesklinna, Lasnamäe ja Pirita linnaosa");var n=new google.maps.LatLng(59.425896,24.722563);var r=createMarker(n,"Tallinna Haabersti, Põhja-Tallinn, Kristiine")}function createMarker(e,t){"use strict";var n=t;var r=new google.maps.Marker({position:e,map:map,zIndex:Math.round(e.lat()*-1e5)<<5});google.maps.event.addListener(r,"click",function(){infowindow.setContent(n);infowindow.open(map,r)})}var map=null;var infowindow=new google.maps.InfoWindow({size:new google.maps.Size(150,50)})