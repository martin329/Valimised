/*jslint bitwise: true*/
//<<[
var map = null;



function displayMap() {
  "use strict";
  document.getElementById('kaart').style.display = "block";
  initialize();
}



function initialize() {
  "use strict";
  var myOptions = {
    zoom: 7,
    center: new google.maps.LatLng(58.887619, 25.565186),
    mapTypeControl: true,
    mapTypeControlOptions:
    {
      style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
    },
    navigationControl: true,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  },
  map = new google.maps.Map(document.getElementById("kaart"),
    myOptions);
  google.maps.event.addListener(map, 'click', function ()
  {
    infowindow.close();
  });
  // Add markers to the map
  // Set up three markers with info windows 
  var point = new google.maps.LatLng(58.383189, 24.496478);
  var marker = createMarker(point, 'Pärnumaa');
  var point = new google.maps.LatLng(57.955674, 26.575928);
  var marker = createMarker(point, 'Võru-, Valga- ja Põlvamaa');
  var point = new google.maps.LatLng(58.369034, 26.729319);
  var marker = createMarker(point, 'Tartu linn');
  var point = new google.maps.LatLng(58.545328, 26.707764);
  var marker = createMarker(point, 'Jõgeva- ja Tartumaa');
  var point = new google.maps.LatLng(58.642653, 25.52124);
  var marker = createMarker(point, 'Järva- ja Viljandimaa');
  var point = new google.maps.LatLng(59.3583, 27.413001);
  var marker = createMarker(point, 'Ida-Virumaa');
  var point = new google.maps.LatLng(59.3495, 26.348);
  var marker = createMarker(point, 'Lääne-Virumaa');
  var point = new google.maps.LatLng(58.9468, 23.5316);
  var marker = createMarker(point, 'Hiiu-, Lääne- ja Saaremaa');
  var point = new google.maps.LatLng(59.094204, 24.54895);
  var marker = createMarker(point, 'Harju- ja Raplamaa');
  var point = new google.maps.LatLng(59.409341, 24.676306);
  var marker = createMarker(point, 'Tallinna Mustamäe ja Nõmme linnaosa');
  var point = new google.maps.LatLng(59.432618, 24.743858);
  var marker = createMarker(point, 'Tallinna Kesklinna, Lasnamäe ja Pirita linnaosa');
  var point = new google.maps.LatLng(59.425896, 24.722563);
  var marker = createMarker(point, 'Tallinna Haabersti, Põhja-Tallinn, Kristiine');
}
var infowindow = new google.maps.InfoWindow( {
  size: new google.maps.Size(150, 50)
});



function createMarker(latlng, html) {
  "use strict";
  var contentString = html;
  var marker = new google.maps.Marker(
  {
    position: latlng,
    map: map,
    zIndex: Math.round(latlng.lat() * -100000) << 5
  });
  google.maps.event.addListener(marker, 'click', function ()
  {
    infowindow.setContent(contentString);
    infowindow.open(map, marker);
  });
}
// This Javascript is based on code provided by the
// Community Church Javascript Team
// http://www.bisphamchurch.org.uk/   
// http://econym.org.uk/gmap/
// from the v2 tutorial page at:
// http://econym.org.uk/gmap/basic1.htm 
//]]>