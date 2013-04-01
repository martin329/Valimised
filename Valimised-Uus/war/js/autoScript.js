$( "#tags" ).autocomplete({
	source: function(req, resp) {
    $.getJSON("./autocomplete", { foo: req.term }, resp);
    }
 });