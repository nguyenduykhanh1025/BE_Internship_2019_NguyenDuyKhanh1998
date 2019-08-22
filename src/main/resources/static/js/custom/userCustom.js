function loadListItemForPage(data){
    var dataHTML = "";


                    data.forEach(function(element) {
                        var dataHTMLTags = "";
                        element.categoriesDTOS.forEach(function(categories) {
                            var data = '<li><a href="#">' + categories.name + '</a></li>';
                            dataHTMLTags += data;
                        });

                        // get role of user
                        var btnElementEnable = "";
                        if (getCookie("ROLE").includes("ROLE_ADMIN")) {
                            if (!element.enable)
                                btnElementEnable = '<td class="col-lg-1 btn-enable-book"><button type="button" class="btn btn-warning" onClick="enableBook(this.id)" id=enable-' + element.id + '>Enable</button></td>';
                            else {
                                 btnElementEnable = '<td class="col-lg-1 btn-enable-book"></td>';
                            }
                        }
                        var data = '<tr class="row" id="post-' + element.id + '">' +
                            '<td class="title-blog col-lg-3"><h5>' + element.title + '</h5></td>' +
                            '<td class="tag-blog col-lg-2">' +
                                formatDate(new Date(element.createdAt)) +
                            '</td>' +
                            '<td class="tag-blog col-lg-2">' +
                                formatDate(new Date(element.updateAt)) +
                             '</td>' +
                            '<td class="img-blog col-lg-1"><img src="' + element.linkImage + '" alt="Image not found"></td>' +
                            '<td class="col-lg-1 btn-delete-blog"><button type="button" class="btn btn-danger btn-delete-post" onClick="deleteBookFollowID(this.id)" id=' + element.id + '>Delete</button></td>' +
                            '<td class="col-lg-1 btn-update-blog"><button type="button" class="btn btn-primary" onClick="editBookFollowID(this.id)" id=' + element.id + '>Edit</button></td>' +
                                btnElementEnable +
                            '<td class="col-lg-1 check-delete-item"><label class="checkbox-inline"><input type="checkbox" value="" class="cb-delete-item" id="cb-delete-'+ element.id +'"></label></td>' +
                            '</tr>';

                        dataHTML += data;
                    });
                    document.getElementById("body-list-post-user").innerHTML = dataHTML;

}

function getIndexCurrent(){
     var elementListIndexPage = document.getElementsByClassName("page-link");
            for(var i = 0; i< elementListIndexPage.length; ++i){
                if(elementListIndexPage[i].classList.contains("page-link-active")){
                    return i;
                }
            }
}
function getSort(){
    var valueSort = document.getElementById('select-sort').value;
        if (valueSort === "sort-item-new") {
            document.getElementById("sort-1").checked = true;
            return 1;
        } else if (valueSort === "sort-item-follow-name") {
            document.getElementById("sort-2").checked = true;
            return 2;
        }
        document.getElementById("sort-0").checked = true;

        return 0;
}
function getSortCheckBox(){
    var cbSort = document.getElementsByClassName('cb-sort');
    var valueSort = "";
    for(var i = 0; i< cbSort.length; ++i){
        if(cbSort[i].checked){
            valueSort = cbSort[i].value;
        }
    }

            if (valueSort === "sort-item-new") {
                return 1;
            } else if (valueSort === "sort-item-follow-name") {
                return 2;
       }
    return 0;
}
function getValueSearch(){
   return document.getElementById("value-search").value;
}

function loadPaginationForPage(numberItem,indexPage,valueSort) {
    $.ajax({
        headers: {
                    "Authorization": localStorage.getItem('Authorization')
                },
        url:    "/api/books/user/length?valueSort=" + valueSort
                +"&valueSearch=" + getValueSearch(),
        type: "GET",
        dataType: 'json',
        success: function(data) {

            var elementHTML = document.getElementById('pagination');
            var dataHTML = '<li class="page-item"><a class="page-link" onClick="onClickIndexPage(this.id)" id="index_previous">Previous</a></li>';

            var lengthIndexPage = data % numberItem === 0 ? data / numberItem : data / numberItem + 1;

            for (var i = 1; i <= lengthIndexPage; ++i) {
                if(i === indexPage){
                    dataHTML += '<li class="page-item"><a class="page-link page-link-active" onClick="onClickIndexPage(this.id)" id = index_' + i + '>'+ i +'</a></li>';
                }else {
                    dataHTML += '<li class="page-item"><a class="page-link"  onClick="onClickIndexPage(this.id)" id= index_' + i + '>' + i + '</a></li>';
                }
            }
            dataHTML += ' <li class="page-item"><a class="page-link" onClick="onClickIndexPage(this.id)" id="index_next">Next</a></li>';

            elementHTML.innerHTML = dataHTML;

        },
        error: function(e) {
            console.log("error + " + error);
        }
    });
}

function onClickIndexPage(elementIndexPag){
    var index = elementIndexPag.split('_')[1];

    if(index === "previous"){
        index = getIndexCurrent() === 1 ? 1 : getIndexCurrent() - 1;
    }
    if(index === "next"){
        index = getIndexCurrent() === document.getElementsByClassName("page-link").length - 2 ? getIndexCurrent() : getIndexCurrent() + 1;
    }

    $.ajax({
     headers: {
                        "Authorization": localStorage.getItem('Authorization')
                    },
        contentType: 'application/json',
         url:    "/api/books/user?numberItem=6"
                                                         + "&indexPage=" + index
                                                         +"&valueSort=" + getSort()
                                                         +"&valueSearch=" + getValueSearch(),
        type: "GET",
        dataType: 'json',
        success: function(data) {

            loadListItemForPage(data);
            // clear active
            var elementListIndexPage = document.getElementsByClassName('page-link');

            for (var i = 0; i < elementListIndexPage.length; ++i) {
                elementListIndexPage[i].classList.remove("page-link-active");
            }

            // add active
            document.getElementById("index_" + index).classList.add('page-link-active');

        },
        error: function(e) {
            console.log("error");
        }
    });
}

