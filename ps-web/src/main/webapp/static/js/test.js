$().ready(function () {
console.log("test");
    $.post("/Like/Like", {"likeCollectionid": 2, "likeUserid": 2}, function (data) {
        console.log("success");
    });

});