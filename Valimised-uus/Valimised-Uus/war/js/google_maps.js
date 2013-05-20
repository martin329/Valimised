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
	disableDefaultUI:true,
    zoom: 7,
    center: new google.maps.LatLng(58.887619, 25.965186),
    mapTypeControl: true,
    mapTypeControlOptions:
    {
      style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
    },
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  map = new google.maps.Map(document.getElementById("kaart"),
    myOptions);
  google.maps.event.addListener(map, 'click', function ()
  {
    infowindow.close();
  });
  var pointList = [
		  ['Tallinna Haabersti, Põhja-Tallinn, Kristiine',[59.425896, 24.722563],'',''],		  
		  ['Tallinna Kesklinna, Lasnamäe ja Pirita linnaosa',[59.452618, 24.763858],'',''],
		  ['Tallinna Mustamäe ja Nõmme linnaosa',[59.389341, 24.646306],'',''],
		  ['Harju- ja Raplamaa',[59.094204, 24.54895],'',''],
		  ['Hiiu-, Lääne- ja Saaremaa',[58.9468, 23.5316],'',''],
		  ['Lääne-Virumaa',[59.3495, 26.348],'',''],
		  ['Ida-Virumaa',[59.3583, 27.413001],'',''],
		  ['Järva- ja Viljandimaa',[58.642653, 25.52124],'',''],
		  ['Jõgeva- ja Tartumaa',[58.545328, 26.707764],'',''],
		  ['Tartu linn',[58.369034, 26.729319],'',''],
		  ['Võru-, Valga- ja Põlvamaa',[57.955674, 26.575928],'',''],
		  ['Pärnumaa',[58.383189, 24.496478],'','']		    
  ]
  var erakondList = [
                     ["Eesti Iseseisvuspartei",'699BE0'],
                     ["Eesti Keskerakond",'35B535'],
                     ["Eesti Konservatiivne Rahvaerakond",'E9ED6B'],
                     ["Eesti Reformierakond",'FFEA00'],
                     ["Eesti Vabaduspartei  Põllumeeste Kogu",'81E6D8'],
                     ["Eestimaa \xDChendatud Vasakpartei",'B51010'],
                     ["Erakond Eesti Kristlikud Demokraadid",'FFFFFF'],
                     ["Erakond Eestimaa Rohelised",'41FA1B'],
                     ["Erakond Isamaa ja Res Publica Liit",'AB44BD'],
                     ["Sotsiaaldemokraatlik Erakond",'ED87CF'],
                     ["\xDCksikkandidaadid",'A8D4E0']
                     ]

  var json = $.getJSON("/piirkonnad", function (data) {
	    for ( var i in data) {
	        var info = '<p>Liider: ' + erakondList[data[i].liiderId-1][0] + '<br />Liidri hääli: ' + data[i].liiderHaali +
	         '<br />Piirkonnas hääli: ' + data[i].piirkondHaali + 
	         '<br />Liidri protsent häältest: ' + data[i].liiderHaali/data[i].piirkondHaali*100+'%</p>';
	        pointList[data[i].id-1][2] = info;
	        pointList[data[i].id-1][3] = erakondList[data[i].liiderId-1][1];
	       
	    }
	    for (var i in pointList){
	  	  createMarker(pointList[i][0],pointList[i][1],pointList[i][2], pointList[i][3]);
	    }
	    var legend = document.getElementById('legend');
	    for (var e in erakondList) {
	          var name = erakondList[e][0];
	          var colour = erakondList[e][1];
	          var div = document.createElement('div');
	          div.innerHTML = '<img src="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|' + colour + '"> ' + name;
	          legend.appendChild(div);
	        }
	    map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legend);
	  });
  
}
var infowindow = new google.maps.InfoWindow( {
  size: new google.maps.Size(150, 50)
});


function createMarker(piirkond, koord, info, colour) {
  "use strict";
  if (colour === '') {
	  colour = 'FF0000';
  };
  if (info === ''){
	  info = "<p>Selles piirkonnas pole hääli antud.</p>";
  };
  var pinColor = colour;
  var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
      new google.maps.Size(21, 34),
      new google.maps.Point(0,0),
      new google.maps.Point(10, 34));
  var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
      new google.maps.Size(40, 37),
      new google.maps.Point(0, 0),
      new google.maps.Point(12, 35));
  var content = info;
  var latlng = new google.maps.LatLng(koord[0],koord[1]);
  var marker = new google.maps.Marker(
  {
    position: latlng,
    map: map,
    zIndex: Math.round(latlng.lat() * -100000) << 5,
    icon:pinImage,
    shadow:pinShadow
    
  });
  google.maps.event.addListener(marker, 'click', function ()
  {
    infowindow.setContent(piirkond + '\n' + content);
    infowindow.open(map, marker);
  });
}
//]]>