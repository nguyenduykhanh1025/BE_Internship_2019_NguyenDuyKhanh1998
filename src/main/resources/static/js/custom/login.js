
// event
document.getElementById('btn-content-login').addEventListener('click', function function_name() {
    document.getElementById('content-login').style.display = "block";
    document.getElementById('content-register').style.display = "none";

    document.getElementById('btn-content-login').style.color = '#ff4136';
    document.getElementById('btn-content-register').style.color = 'black';


});

document.getElementById('btn-content-register').addEventListener('click', function function_name() {
    document.getElementById('content-login').style.display = "none";
    document.getElementById('content-register').style.display = "block";

    document.getElementById('btn-content-login').style.color = 'black';
    document.getElementById('btn-content-register').style.color = '#ff4136';
});

