// <div id="fb-root"></div>
// <script>
// Additional JS functions here
window.fbAsyncInit = function () {
  "use strict";
  FB.init({
    appId: '550454328332604', // App ID
    channelUrl: '//localhost:8888/channel.html', // Channel File
    status: true, // check login status
    cookie: true, // enable cookies to allow the server to access the
    // session
    xfbml: true
    // parse XFBML
  });
  FB.getLoginStatus(function (response) {
    if (response.status === 'connected') {
      // connected
      console.log("1");
      localStorage.isAuthenticated = 1;
      $("#logimise_konteiner").load("../html/logimine.html #autenditud");
      testAPI();
      //			logout();
    } else if (response.status === 'not_authorized') {
      // not_authorized
      localStorage.isAuthenticated = 0;
      $("#logimise_konteiner").load("../html/logimine.html #autentimata");
      //			login();
    } else {
      // not_logged_in
      localStorage.isAuthenticated = 0;
      $("#logimise_konteiner").load("../html/logimine.html #autentimata");
      //			login();
    }
  });
  // Additional init code here
};



function login() {
  "use strict";
  FB.login(function (response) {
    if (response.authResponse) {
      // connected
      console.log("2");
      localStorage.isAuthenticated = 1;
      $("#logimise_konteiner").load("../html/logimine.html #autenditud");
      testAPI();
      localStorage.inIsik = 0;
    } else {
      console.log("4");
      // cancelled
    }
  });
}



function testAPI() {
  "use strict";
  console.log('Welcome!  Fetching your information.... ');
  FB.api('/me', function (response) {
    console.log('Good to see you, ' + response.name + '.');
    console.log('ID: ' + response.id);
    console.log('Name: ' + response.name);
    console.log('First name: ' + response.first_name);
    console.log('Last name: ' + response.last_name);
    console.log('Link: ' + response.link);
    console.log('Username: ' + response.username);
    console.log('Gender: ' + response.gender);
    console.log('Locale: ' + response.locale);
    console.log('Age range: ' + response.age_range);
    localStorage.name = response.name;
    localStorage.id = response.id;
    $("#kasutajaId").val(response.id);
    $("#kasutajaId2").val(response.id);
    $("#kasutajaE").val(response.first_name);
    $("#kasutajaP").val(response.last_name);
    document.getElementById("kasutaja").innerHTML = "<br />" + response.name + "<br /><br />";
    if (localStorage.inIsik !== 1) {
      localStorage.inIsik = 1;
      document.getElementById("checkIfInIsik").submit();
    }
  });
}
// Load the SDK Asynchronously
(function (d) {
  "use strict";
  var js, id = 'facebook-jssdk',
    ref = d.getElementsByTagName('script')[0];
  if (d.getElementById(id)) {
    return;
  }
  js = d.createElement('script');
  js.id = id;
  js.async = true;
  js.src = "//connect.facebook.net/en_US/all.js";
  ref.parentNode.insertBefore(js, ref);
}(document));
// </script>



function logout() {
  "use strict";
  FB.logout(function (response) {
    console.log('User is now logged out');
    localStorage.isAuthenticated = 0;
    $("#logimise_konteiner").load("../html/logimine.html #autentimata");
    //        location.reload();
  });
}