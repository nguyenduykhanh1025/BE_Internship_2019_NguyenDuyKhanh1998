document.addEventListener('DOMContentLoaded', function() {
    // load categories first
    loadCategoriesForPage();

    var queryUrl = "/api/books?numberItem=6&indexPage=1&idCategories=-1&valueSort=0&valueSearch=";
    // check if in localsession is have author tag
    if (sessionStorage.getItem("nameSearch") != null) {
        queryUrl = "/api/books?numberItem=6" +
            "&indexPage=1" +
            "&idCategories=-1" +
            "&valueSort=0" +
            "&valueSearch=" + sessionStorage.getItem("nameSearch");

        document.getElementById("name-book-of").innerHTML = sessionStorage.getItem("nameSearch");
        document.getElementById("value-search").value = sessionStorage.getItem("nameSearch");
        sessionStorage.removeItem("nameSearch");
    }else {
        document.getElementById("div-book-of").style.display = "none";
    }

    var indexPage = 1;
    var numberItem = output.innerHTML;
    // load list book
    // /api/books?numberItem=2&indexPage=1&idCategories=-1&valueSort=0
    $.ajax({
        contentType: 'application/json',
        url: queryUrl,
        type: "GET",
        dataType: 'json',
        success: function(data) {
            loadDataForPage(data);

            // load index pagition agian for page indexCustom.js
            loadPaginationForPage(6, 1, -1, 0);

        },
        error: function(e) {
            console.log("error");
        }
    });




    // load user if login
    if (localStorage.getItem("Authorization") != null) {
        var elementLoginRegister = document.getElementsByClassName("login-register");
        for (var i = 0; i < elementLoginRegister.length; ++i) {
            elementLoginRegister[i].style.display = "none";
        }
    }
});

////
// search in page
document.getElementById('btn-search').addEventListener('click', function() {

    $.ajax({
        contentType: 'application/json',
        url: "/api/books?numberItem=" + getLengthItemBook() +
            "&indexPage=1" +
            "&idCategories=" + getIdCategories() +
            "&valueSort=" + getSort() +
            "&valueSearch=" + getValueSearch(),
        type: "GET",
        dataType: 'json',
        success: function(data) {

            loadDataForPage(data);

            loadPaginationForPage(getLengthItemBook(), 1, getIdCategories(), getSort());



        },
        error: function(e) {
            console.log("e " + e);
        }
    });
});

function getBookFollowCategories(Categories) {

    var idCategories = Categories.split("-")[Categories.split("-").length - 1];

    if (idCategories === "all") {
        idCategories = -1;
    }
    $.ajax({
        contentType: 'application/json',
        url: "/api/books?numberItem=" + getLengthItemBook() +
            "&indexPage=1" +
            "&idCategories=" + idCategories +
            "&valueSort=" + getSort() +
            "&valueSearch=" + getValueSearch(),
        type: "GET",
        dataType: 'json',
        success: function(data) {
            loadDataForPage(data);
            // clear class active
            // clear active
            var elementListCategoriesPage = document.getElementsByClassName('categories-link');

            for (var i = 0; i < elementListCategoriesPage.length; ++i) {
                elementListCategoriesPage[i].classList.remove("categories-active");
            }
            // add active
            document.getElementById(Categories).classList.add('categories-active');

            loadPaginationForPage(getLengthItemBook(), 1, getIdCategories(), getSort());

        },
        error: function(e) {
            console.log("e " + e);
        }
    });

}

// sort item
document.getElementById('select-sort').addEventListener('change', function() {
    $.ajax({
        url: "/api/books?numberItem=" + getLengthItemBook() +
            "&indexPage=1" +
            "&idCategories=" + getIdCategories() +
            "&valueSort=" + getSort() +
            "&valueSearch=" + getValueSearch(),
        type: "GET",
        dataType: 'json',
        success: function(data) {
            loadDataForPage(data);
        },
        error: function(e) {

        }
    });

});

function onClickGetListAuthor(elementAuthorTag) {
    document.getElementById("value-search").value = elementAuthorTag.innerHTML;
    $.ajax({
        contentType: 'application/json',
        url: "/api/books?numberItem=" + getLengthItemBook() +
            "&indexPage=1" +
            "&idCategories=" + getIdCategories() +
            "&valueSort=" + getSort() +
            "&valueSearch=" + getValueSearch(),
        type: "GET",
        dataType: 'json',
        success: function(data) {

            loadDataForPage(data);

            loadPaginationForPage(getLengthItemBook(), 1, getIdCategories(), getSort());

            document.getElementById("value-search").value = "";

        },
        error: function(e) {
            console.log("e " + e);
        }
    });
}

function getValueSearch() {
    return document.getElementById("value-search").value;
}