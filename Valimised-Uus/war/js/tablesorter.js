$(document).ready(function () {
  "use strict";
  $(function () {
    $("#kandidaatide-tabel").tablesorter( {
      sortList: [
        [0, 0]
      ],
      widthFixed: true
    });
    console.log("tabel");
  });
});