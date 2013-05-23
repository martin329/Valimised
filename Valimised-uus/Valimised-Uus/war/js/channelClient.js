function onOpened() {
	connected = true;
	console.log('channel opened'); 
};

function onMessage(m) {
	console.log(m);
	var msg = m.data.split("_");
	msg[1]=msg[1].substring(0,msg[1].indexOf("\r"));
	console.log(msg);
	var json = $.getJSON("/pushJson", ("kandidaat="+msg[1]), function (data) {
		console.log(data);
	    var output = "";
	    var isik = data;
	    output += "<tr id=\""+isik.id+"\"><td>" + isik.id + "</td><td>" + isik.eesnimi + "</td><td>" + isik.perenimi + 
	      "</td><td>" + isik.erakond + "</td><td>" + isik.piirkond + "</td><td>" + isik.haali + "</td></tr>";
		if($("#kandidaatide-tabel") != null){
			switch(msg[0])
			{
			case "k":
		      $("#kandidaaditabel_keha").append(output);
			  break;
			case "h":
			  $("#"+isik.id).replaceWith(output);
			  break;
			case "d":
			  $("#"+isik.id).replaceWith(output);
			  break;
			default:
			  
			}
		
		$("#kandidaatide-tabel").trigger("update");
		}
	});
}
function onError() {
	var token = getToken();
	Open(token);
};
function getToken(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/getToken', false);
	xhr.send(null);
	if (xhr.status == 200) {
		return(xhr.responseText);
	} 
};

function openChannel(token){
	var channel = new goog.appengine.Channel(token);
	var handler = {
		'onopen': onOpened,
		'onmessage': onMessage,
		'onerror': onError,
		'onclose': function() {}
	};
	var socket = channel.open(handler);
};

function Open(token){
	
	if ( token != null&& token != 'error' && token !='') {
		 console.log(token);
		//strip newline from returned token
		 var cleantoken =token.replace("\n", "", "g");
		 openChannel(cleantoken);
	 } 
	 else{
		 console.log('error fetching token, creating new.');
		 var newToken = getToken();
		 Open(newToken);
		 
	 }
	
}
$(document).ready(function(){
	console.log(document.cookie);
	 var token = document.cookie;
	 Open(token);
	});