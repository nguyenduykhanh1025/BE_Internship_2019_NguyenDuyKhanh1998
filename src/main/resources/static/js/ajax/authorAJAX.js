document.addEventListener('DOMContentLoaded', function() {
    var url = window.location.href;
        var tagName = url.split("/")[url.split("/").length - 1];
        getListPost(tagName);
});

//get list post follow name tag
function getListPost(nameTag) {
     // load list book
            $.ajax({
                url: "/api/books/author/" + nameTag,
                type: "GET",
                dataType: 'json',
                success: function(data) {
                    var elementListBook = document.getElementById("list-book");
                    var dataHTML = "";

                    console.log(nameTag);
                    dataHTML += '<h2 style="text-align:center;width: 100%;"><p  style="display:inline;font-size: 18px;">book of author: </p>' + nameTag + '</h2>';
                    data.forEach(function(element) {


                        var dataItem = ' <div class="col-lg-4 item">' +
                            '<a href="/detail/' + element.id + '"><img src="' + element.linkImage + '" class="img-item"></a>' +
                            '<h5 class="title-item"><a href="#">' + element.title + '</a></h5>' +
                            '<div class="author">' +
                            '<ul class="list-author">' +
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