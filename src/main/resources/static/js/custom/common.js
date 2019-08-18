function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function isBlankOrSpecialCharacters(password){
	var regularQuery = /[^A-Za-z0-9]/;
	if(password.match(regularQuery) != null || password == ""){
		return true;
	}
	return false;
}

function isBlankString(value){
    var regularQuery = /[A-Za-z0-9_]/;
    if(value.match(regularQuery)){
        return false;
    }
    return true;
}


document.addEventListener('DOMContentLoaded', function() {
    if(localStorage.getItem('Authorization') != null){
    $.ajax({
            headers: {
                            "Authorization": localStorage.getItem('Authorization')
                        },
            url: "/api/users",
            type: "GET",
            dataType: 'json',
            success: function(data) {
                document.getElementById("btn-account").innerHTML = '<img src="'+ data.linkAvatar +'">';
                document.getElementById("login-register").style.display = "none";
                document.getElementById("logout").style.display = "block";
            },
            error: function(e) {

            }
        });
    }
});
function informationErrorLabel(information){
    document.getElementById("alert-information").innerHTML = '<div class="alert alert-danger">' +
                                '<strong>ERROR!</strong>' + information +
                                '</div>';
}

function informationSuccessLabel(information){
    document.getElementById("alert-information").innerHTML = '<div class="alert alert-success">' +
                                '<strong>SUCCESS!</strong>' + information +
                                '</div>';
}

//cookie
function setCookie(cname,cvalue,exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires=" + d.toGMTString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function checkCookie() {
  var user=getCookie("username");
  if (user != "") {
    alert("Welcome again " + user);
  } else {
     user = prompt("Please enter your name:","");
     if (user != "" && user != null) {
       setCookie("username", user, 30);
     }
  }
}