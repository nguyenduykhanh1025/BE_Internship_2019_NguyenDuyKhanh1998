document.addEventListener('DOMContentLoaded', function() {
    var url = window.location.href;
        var tagName = url.split("/")[url.split("/").length - 1];
        getListPost(tagName);
});

//get list post follow name tag
function getListPost(nameTag) {
     // load list book
            $.ajax({
                url: "/api/books/poster/" + nameTag,
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
                            '<a href="/page/detail/' + element.id + '"><img src="' + element.linkImage + '" class="img-item"></a>' +
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
                    loadPaginationForPage();

                },
                error: function(e) {
                    console.log("error");
                }
            });
}