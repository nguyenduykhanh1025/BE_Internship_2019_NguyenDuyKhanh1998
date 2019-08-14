// set event for process bar of phan trang
var slider = document.getElementById("myRange");
var output = document.getElementById("output-range");
slider.value = 6;
output.innerHTML = slider.value;

slider.oninput = function() {
    output.innerHTML = this.value;
    if (this.value < 6) {
        output.innerHTML = 6;
    }
    var indexPage = 1;
    var numberItem = output.innerHTML;
    // load list book
    $.ajax({
        contentType: 'application/json',
        url: "/api/books?numberItem=" + numberItem +
                    "&indexPage=" + 1 +
                    "&idCategories=" + getIdCategories() +
                    "&valueSort=" + getSort(),
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


            // load index pagition agian for page
            loadPaginationForPage(numberItem,1, getIdCategories(), getSort());

        },
        error: function(e) {
            console.log("error");
        }
    });
}

function loadPaginationForPage(numberItem,indexPage,idCategories,valueSort) {

    var numberItem = output.innerHTML;
    $.ajax({
        url:    "/api/books?numberItem=" + numberItem
                + "&indexPage=" + indexPage
                +"&idCategories=" + idCategories
                +"&valueSort=" + valueSort,
        type: "GET",
        dataType: 'json',
        success: function(data) {

            var elementHTML = document.getElementById('pagination');
            var dataHTML = '<li class="page-item"><a class="page-link">Previous</a></li>';
            dataHTML += '<li class="page-item"><a class="page-link page-link-active" onClick="onClickIndexPage(this.id)" id = index_' + 1 + '>1</a></li>';

            var lengthIndexPage = data.length / numberItem % 2 == 0 ? data.length / numberItem : data.length / numberItem + 1;
            for (var i = 2; i <= lengthIndexPage; ++i) {
                dataHTML += '<li class="page-item"><a class="page-link" onClick="onClickIndexPage(this.id)" id= index_' + i + '>' + i + '</a></li>';
            }
            dataHTML += ' <li class="page-item"><a class="page-link">Next</a></li>';

            elementHTML.innerHTML = dataHTML;

        },
        error: function(e) {
            console.log("error");
        }
    });
}

function onClickIndexPage(elementIndexPag){
    var index = elementIndexPag.split('_')[1];

    $.ajax({
        contentType: 'application/json',
         url:    "/api/books?numberItem=" + getLengthItemBook()
                        + "&indexPage=" + index
                        +"&idCategories=" + getIdCategories()
                        +"&valueSort=" + getSort(),
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


            // clear active
            var elementListIndexPage = document.getElementsByClassName('page-link');

            for (var i = 0; i < elementListIndexPage.length; ++i) {
                elementListIndexPage[i].classList.remove("page-link-active");
            }

            // add active
            document.getElementById(elementIndexPag).classList.add('page-link-active');

        },
        error: function(e) {
            console.log("error");
        }
    });
}


function getLengthItemBook(){
    return numberItem = output.innerHTML;
}
function getIndexPage(){
    return document.querySelector(".page-link-active").innerHTML;
}
function getSort(){
    var valueSort = document.getElementById('select-sort').value;
        if (valueSort === "sort-item-new") {
            return 1;
        } else if (valueSort === "sort-item-follow-name") {
            return 2;
        }
        return 0;
}

function getIdCategories(){
    var elementActive = document.querySelector(".categories-active");

    if(elementActive.id.split("-")[elementActive.id.split("-").length - 1] === "all"){
        return -1;
    }
    return elementActive.id.split("-")[elementActive.id.split("-").length - 1];
}