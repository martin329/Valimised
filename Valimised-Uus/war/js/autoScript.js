
$(function() {
	$( "#tags" ).autocomplete({
		source: function(request, response) {
		    $.getJSON("./autocomplete", { foo: request.term }, response);
		  }
	});
});