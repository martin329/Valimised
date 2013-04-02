<<<<<<< HEAD
$( "#autocompleteScript" ).autocomplete({
  source: function(request, response) {
      $.getJSON("./autocomplete", { foo: request.term }, response);
    }
 });
=======

$(function() {
	$( "#tags" ).autocomplete({
		source: function(request, response) {
		    $.getJSON("./autocomplete", { foo: request.term }, response);
		  }
	});
});
>>>>>>> refs/remotes/origin/master
