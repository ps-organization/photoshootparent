$().ready(function () {
    console.log($('#t-img').find('img').width());
    console.log($('#t-img img').height());


    $.post("/collection/allCollection", function (data) {
        for(var i=0;i<data.length;i++) {
            console.log("id:" + data[i].collectionId + ";location:" + data[i].collectionPhotolocation);
            console.log("userName:" + data[i].userName + ";likeCount:" + data[i].likeCount);
            console.log("collectionPhotoname:" + data[i].collectionPhotoname);
        }

    });

});