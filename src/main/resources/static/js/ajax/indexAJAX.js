document.addEventListener('DOMContentLoaded', function() {

    var indexPage = 1;
    var numberItem = output.innerHTML;
    // load list book
    // /api/books?numberItem=2&indexPage=1&idCategories=-1&valueSort=0
    $.ajax({
        contentType: 'application/json',
        url: "/api/books?numberItem=6&indexPage=1&idCategories=-1&valueSort=0",
        type: "GET",
        dataType: 'json',
        success: function(data) {
            var elementListBook = document.getElementById("list-book");
            var dataHTML = "";
            data.forEach(function(element) {

                var dataAuthorHTML = "";
                element.authorDTOS.forEach(function(author) {
                    var dataItemAuthor = '<li class="author-item"><a href="/author/' + author.name + '" class="badge badge-dark">' + author.name + '</a></li>';
                    dataAuthorHTML += dataItemAuthor;
                });

                var dataItem = ' <div class="col-lg-4 item">' +
                    '<a href="/detail/' + element.id + '"><img src="' + element.linkImage + '" class="img-item"></a>' +
                    '<h5 class="title-item"><a href="#">' + element.title + '</a></h5>' +
                    '<div class="author">' +
                    '<ul class="list-author">' +
                    dataAuthorHTML +
                    '</ul>' +
                    '</div>' +
                    '</div>';
                dataHTML += dataItem;
            });
            elementListBook.innerHTML = dataHTML;


            // load index pagition agian for page indexCustom.js
            loadPaginationForPage(6, 1, -1, 0);

        },
        error: function(e) {
            console.log("error");
        }
    });

    // load categories
    $.ajax({
        contentType: 'application/json',
        url: "/api/category_books",
        type: "GET",
        dataType: 'json',
        success: function(data) {
            var elementListBook = document.getElementById("list-categories");
            var dataHTML = '<li class="categories-active categories-link" id="categories-all" onClick="getBookFollowCategories(this.id)">All Category</li>';

            data.forEach(function(element) {
                dataHTML += '<li class="categories-link"  onClick="getBookFollowCategories(this.id)" id="categories-' + element.id + '">' + element.name + '</li>';
            });

            elementListBook.innerHTML = dataHTML;
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

// search in page
document.getElementById('btn-search').addEventListener('click', function() {
    var valueSearch = document.getElementById("value-search").value;
    $.ajax({
        contentType: 'application/json',
        url: "/api/books/search?valueSearch=" + valueSearch,
        type: "GET",
        dataType: 'json',
        success: function(data) {

            if (data.length == 0) {
                var elementListBook = document.getElementById("list-book");
                elementListBook.innerHTML = "<h2 style='color:#ababab'>No results were found....</h2>"
            } else {
                var elementListBook = document.getElementById("list-book");
                var dataHTML = "";
                data.forEach(function(element) {

                    var dataAuthorHTML = "";
                    element.authorDTOS.forEach(function(author) {
                        var dataItemAuthor = '<li class="author-item badge badge-dark"><a href="#">' + author.name + '</a></li>';
                        dataAuthorHTML += dataItemAuthor;
                    });

                    var dataItem = ' <div class="col-lg-4 item">' +
                        '<a href="/detail/' + element.id + '"><img src="' + element.linkImage + '" class="img-item"></a>' +
                        '<h5 class="title-item"><a href="#">' + element.title + '</a></h5>' +
                        '<div class="author">' +
                        '<ul class="list-author">' +
                        dataAuthorHTML +
                        '</ul>' +
                        '</div>' +
                        '</div>';
                    dataHTML += dataItem;
                });
                elementListBook.innerHTML = dataHTML;
            }

        },
        error: function(e) {

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
            "&indexPage=" + 1 +
            "&idCategories=" + idCategories +
            "&valueSort=" + getSort(),
        type: "GET",
        dataType: 'json',
        success: function(data) {
            var elementListBook = document.getElementById("list-book");
            var dataHTML = "";
            data.forEach(function(element) {

                var dataAuthorHTML = "";
                element.authorDTOS.forEach(function(author) {
                    var dataItemAuthor = '<li class="author-item badge badge-dark" ><a href="/page/author/' + author.name + '">' + author.name + '</a></li>';
                    dataAuthorHTML += dataItemAuthor;
                });

                var dataItem = ' <div class="col-lg-4 item">' +
                    '<a href="/detail/' + element.id + '"><img src="' + element.linkImage + '" class="img-item"></a>' +
                    '<h5 class="title-item"><a href="#">' + element.title + '</a></h5>' +
                    '<div class="author">' +
                    '<ul class="list-author">' +
                    dataAuthorHTML +
                    '</ul>' +
                    '</div>' +
                    '</div>';
                dataHTML += dataItem;
            });
            elementListBook.innerHTML = dataHTML;

            loadPaginationForPage(getLengthItemBook(), 1, idCategories, getSort());


            // clear class active
            // clear active
            var elementListCategoriesPage = document.getElementsByClassName('categories-link');

            for (var i = 0; i < elementListCategoriesPage.length; ++i) {
                elementListCategoriesPage[i].classList.remove("categories-active");
            }
            // add active
            document.getElementById(Categories).classList.add('categories-active');

        },
        error: function(e) {

        }
    });

}

// sort item
document.getElementById('select-sort').addEventListener('change', function() {
    var valueSort = document.getElementById('select-sort').value;
    var valueSendToSort = 0;
    if (valueSort === "sort-item-new") {
        valueSendToSort = 1;
    } else if (valueSort === "sort-item-follow-name") {
        valueSendToSort = 2;
    }


    $.ajax({
        url: "/api/books?numberItem=" + getLengthItemBook() +
            "&indexPage=" + 1 +
            "&idCategories=" + getIdCategories() +
            "&valueSort=" + valueSendToSort,
        type: "GET",
        dataType: 'json',
        success: function(data) {
            var elementListBook = document.getElementById("list-book");
            var dataHTML = "";
            data.forEach(function(element) {

                var dataAuthorHTML = "";
                element.authorDTOS.forEach(function(author) {
                    var dataItemAuthor = '<li class="author-item"><a href="#" class="badge badge-dark">' + author.name + '</a></li>';
                    dataAuthorHTML += dataItemAuthor;
                });

                var dataItem = ' <div class="col-lg-4 item">' +
                    '<a href="/detail/' + element.id + '"><img src="' + element.linkImage + '" class="img-item"></a>' +
                    '<h5 class="title-item"><a href="#">' + element.title + '</a></h5>' +
                    '<div class="author">' +
                    '<ul class="list-author">' +
                    dataAuthorHTML +
                    '</ul>' +
                    '</div>' +
                    '</div>';
                dataHTML += dataItem;
            });
            elementListBook.innerHTML = dataHTML;
        },
        error: function(e) {

        }
    });

});

// event logout
document.getElementById("logout").addEventListener("click", function() {
    localStorage.removeItem('Authorization');
    $.ajax({
        url: "/api/auth",
        type: "GET",
        success: function(data) {
            document.getElementById("login-register").style.display = "block";
            document.getElementById("logout").style.display = "none";

            document.getElementById("btn-account").innerHTML = "";
        },
        error: function(e) {
            informationErrorLabel("ERROR!! logout error");
        }
    });
});