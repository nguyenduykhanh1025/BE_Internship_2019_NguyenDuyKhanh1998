document.addEventListener('DOMContentLoaded', function() {
    // load detail item of book
    var url = window.location.href;
    var idItem = url.split("/")[url.split("/").length - 1];
    $.ajax({
        headers: {
                        "Authorization": localStorage.getItem('Authorization')
                    },
        contentType: 'application/json',
        url: "/api/books/" + idItem,
        type: "GET",
        dataType: 'json',
        success: function(data) {
            console.log(data);
            // list author
            var dataAuthorList = "";
            data.authorDTOS.forEach(function(element) {
                dataAuthorList += '<li class="author-item"><span class="badge badge-dark" onClick="onClickSearchTag(this)">' + element.name + '</span></li>';
            });

            // list categories
            var dataCategoriesList = "";
            data.categoriesDTOS.forEach(function(element) {
                dataCategoriesList = '<li class="author-item"><span class="badge badge-dark" onClick="onClickSearchTag(this)" id="categories-' + element.id + '">' + element.name + '</span></li>';
            });
            var dataHTML = '<div class="content-item row">' +
                '<div class="image-item col-lg-4">' +
                '<img src="' + data.linkImage + '" class="imge-big" alt="Image not found">' +
                '<div class="list-image-small">' +
                '<img src="https://auteur.g5plus.net/wp-content/uploads/2018/11/product-19-330x462.jpg" class="image-small-item">' +
                '<img src="https://auteur.g5plus.net/wp-content/uploads/2018/11/product-04-330x462.jpg" class="image-small-item">' +
                '</div>' +
                '</div>' +
                '<div class="content-detail-item col-lg-8">' +
                '<h1 class="tittle">' + data.title + '</h1>' +
                '<hr class="hr-title">' +
                '<div class="date-poster">' +
                '<div class="poster"><i class="fa fa-user"></i> <a href="/poster/' + data.namePoster + '">' + data.namePoster + '</a> <i class="fa fa-clock-o"></i> <time datetime="2008-02-14 20:00">' + formatDate(new Date(data.createdAt)) + '</time></div>' +
                '<div class="descript">' +
                data.description +
                '</div>' +
                '<a href="https://www.amazon.com/s?k=' + data.title + '&ref=nb_sb_noss" class="btn btn-outline-danger" id="btn-find-buy" target="_blank">Tìm mua</a>' +
                '<div class="author">' +
                '<p class="title-infomation">Authors: </p>' +
                '<ul class="list-author">' +
                dataAuthorList +
                '</ul>' +
                '</div>' +
                '<div class="Category">' +
                '<p class="title-infomation">Category: </p>' +
                '<ul class="list-category">' +
                dataCategoriesList +
                '</ul>' +
                '</div>' +
                '</div>';

            document.getElementById('content-detail-item').innerHTML = dataHTML;

            // load comment
            var elementCommentHTML = document.getElementById("comment-holder");
            var listComment = "";

            data.commentDTOList.forEach(function(element) {


                var elementIcon = '';
                if (element.registerDTO.email === getUserName()) {
                    elementIcon = '<div class="icon-comment">' +
                        '<i class="fa fa-times-circle" onclick="deleteCommentFollowID(this.id)" id="icon-delete-comment-' + element.id + '"></i>' +
                        '<i class="fa fa-edit"  onclick="editCommentFollowID(this.id)" id="icon-edit-comment-' + element.id + '"></i>' +
                        '</div>';
                }
                var commentBlock = '<div class="comment-blok row" id="comment-block-' + element.id + '">' +
                    '<img src="' + element.registerDTO.linkAvatar + '" class="avartar-user-comment">' +
                    '<div class="content-top-comment">' +
                    '<p class="name-user-comment"><a href="#">' + element.registerDTO.email + '</a></p>' +
                    elementIcon +
                    '<time datetime="2008-02-14 20:00">' + formatDate(new Date(element.updateAt)) + '</time>' +
                    '</div>' +
                    '<div class="content-center-comment">' +
                    '<input class="content-comment" value="' + element.message + '" disabled id="content-comment-' + element.id + '"></input>' +
                    '</div>' +
                    '</div>';
                listComment += commentBlock;
            });
            elementCommentHTML.innerHTML = listComment;
        },
        error: function(e) {
            if(e.status === 404){
                informationErrorLabel(e.responseText);
            }
        }
    });
});


document.getElementById("btn-comment").addEventListener('click', function() {
    var url = window.location.href;
    var idItem = url.split("/")[url.split("/").length - 1];
    var messageComment = document.getElementById("message-comment").value;

    if (!isBlankString(messageComment)) {
        var comment = {
            "message": messageComment
        }
        $.ajax({
            headers: {
                "Authorization": localStorage.getItem('Authorization')
            },
            contentType: 'application/json',
            url: "/api/comments/" + idItem,
            type: "POST",
            data: JSON.stringify(comment),
            dataType: 'json',
            success: function(data) {
                console.log(data);
                var elementCommentHTML = document.getElementById("comment-holder");
                var listComment = elementCommentHTML.innerHTML;
                var elementIcon = '<div class="icon-comment">' +
                    '<i class="fa fa-times-circle" onclick="deleteCommentFollowID(this.id)" id="icon-delete-comment-' + data.id + '"></i>' +
                    '<i class="fa fa-edit"  onclick="editCommentFollowID(this.id)" id="icon-edit-comment-' + data.id + '"></i>' +
                    '</div>';
                var commentBlock = '<div class="comment-blok row" id="comment-block-' + data.id + '">' +
                    '<img src="' + data.registerDTO.linkAvatar + '" class="avartar-user-comment">' +
                    '<div class="content-top-comment">' +
                    '<p class="name-user-comment"><a href="#">' + data.registerDTO.email + '</a></p>' +
                    elementIcon +
                    '<time >' + formatDate(new Date(data.updateAt)) + '</time>' +
                    '</div>' +
                    '<div class="content-center-comment">' +
                    '<input class="content-comment" value="' + data.message + '" disabled id="content-comment-' + data.id + '"></input>' +
                    '</div>' +
                    '</div>';
                listComment += commentBlock;

                elementCommentHTML.innerHTML = listComment;

                // clear comment input
                document.getElementById("message-comment").value = "";
            },
            error: function(e) {
                console.log(e);
                if(e.status === 403){
                   informationErrorLabel('You are not logged in!!! please');
                }
            }
        });
    }

});

// delete comment
function deleteCommentFollowID(id) {
    var idComment = id.split("-")[id.split("-").length - 1];
    $('#exampleModal').modal('show');

    document.getElementById("btn-modal-ok").addEventListener('click', function() {
        $.ajax({
            headers: {
                "Authorization": localStorage.getItem('Authorization')
            },
            contentType: 'application/json',
            url: "/api/comments/" + idComment,
            type: "DELETE",
            success: function() {
                var idBlockComment = "comment-block-" + idComment;
                document.getElementById(idBlockComment).style.display = 'none';

            },
            error: function(e) {
                console.log("error");
            }
        });
        $('#exampleModal').modal('hide');

    });



}

function editCommentFollowID(id) {
    var idComment = id.split("-")[id.split("-").length - 1];

    var elementInputComment = document.getElementById('content-comment-' + idComment);
    elementInputComment.disabled = false;

    elementInputComment.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
            var valueComment = elementInputComment.value;
            var comment = {
                "message": valueComment
            }

            $.ajax({
                headers: {
                            "Authorization": localStorage.getItem('Authorization')
                },
                contentType: 'application/json',
                url: "/api/comments/" + idComment,
                type: "PUT",
                data: JSON.stringify(comment),
                dataType: 'json',
                success: function(data) {
                    elementInputComment.disabled = true;
                },
                error: function(e) {
                    console.log("error");
                }
            });
        }
    });

}



function getUserName(){
    if(parseJwt() == null){
        return null;
    }
    var valueToken = parseJwt();
    return valueToken.sub;
}

function parseJwt () {
    if(localStorage.getItem('Authorization') == null){
        return null;
    }
    var token = localStorage.getItem('Authorization').split(" ")[localStorage.getItem('Authorization').split(" ").length - 1];
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};

function onClickSearchTag(data){
    sessionStorage.setItem("nameSearch", data.innerHTML);
    location.replace("/");
}
