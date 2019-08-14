document.getElementById('btn-to-login').addEventListener('click', function() {

    // clearn infomation
    document.getElementById('information-password-login').textContent = "";
    document.getElementById('information-email-login').textContent = "";


    var email = document.getElementById('email-login').value;
    var password = document.getElementById('password-login').value;

    if (validateEmail(email)) {
        if (!isBlankOrSpecialCharacters(password)) {

            var login = {
                "email": email,
                "password": password
            }

            $.ajax({
                contentType: 'application/json',
                url: "/api/auth",
                type: "POST",
                data: JSON.stringify(login),
                dataType: 'json',
                success: function(data) {
                    document.cookie = "access_token=" + data.token;
                    window.localStorage.setItem("Authorization", "Bearer " + data.token);
                    location.replace("/");

                },
                error: function(e) {
                    console.log(e);
                    if(e.status === 423){
                        informationErrorLabel("User is disable. please contact to admin!!!");
                    }
                    else {
                        informationErrorLabel("User is validated. please!!!");
                    }
                }
            });
        } else {
            document.getElementById('information-password-login').textContent = '*this is not be blank or not email';
        }
    } else {
        document.getElementById('information-email-login').textContent = '*this is not email';
    }

})