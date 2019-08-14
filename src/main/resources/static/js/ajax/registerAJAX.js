document.getElementById('btn-register').addEventListener('click', function() {

    // clear information
        document.getElementById('information-email-register').textContent = '';
        document.getElementById('information-password-register').textContent = '';
        document.getElementById('information-first-name-register').textContent = '';
        document.getElementById('information-last-name-register').textContent = '';
        document.getElementById('information-link-avatar-register').textContent = '';


    var email = document.getElementById('number-phone-register').value;
    var password = document.getElementById('pass-word-register').value;
    var firstName = document.getElementById('first-name-register').value;
    var lastName = document.getElementById('last-name-register').value;
    var linkAvatar = document.getElementById('link-avatar-register').value;

    if (!validateEmail(email)) {
        document.getElementById('information-email-register').textContent = '*this is not email';
    } else if (isBlankOrSpecialCharacters(password)) {
        document.getElementById('information-password-register').textContent = '*this is not be blank or special char';
    } else if (isBlankOrSpecialCharacters(firstName)) {
        document.getElementById('information-first-name-register').textContent = '*this is not be blank or special char';
    } else if (isBlankOrSpecialCharacters(lastName)) {
        document.getElementById('information-last-name-register').textContent = '*this is not be blank or special char';
    } else if (isBlankOrSpecialCharacters(lastName)) {
        document.getElementById('information-link-avatar-register').textContent = '*this is not be blank or special char';
    } else {
        if (validateEmail(email)) {
            if (!isBlankOrSpecialCharacters(password) || !isBlankOrSpecialCharacters(firstName) || !isBlankOrSpecialCharacters(lastName)) {
                var register = {
                    "email": email,
                    "password": password,
                    "first_name": firstName,
                    "last_name": lastName,
                    "link_avatar": linkAvatar
                }
                $.ajax({
                    contentType: 'application/json',
                    url: "/api/register",
                    type: "POST",
                    data: JSON.stringify(register),
                    dataType: 'json',
                    success: function(data) {
                        informationSuccessLabel('check email. please!!!');
                    },
                    error: function(e) {
                        informationErrorLabel("user is exist!!!");
                    }
                });
            }
        }
    }
});

