$().ready(function () {
console.log("test");
    $.post("/PsUserController/showFansCount", {"likeCollectionid": 2, "likeUserid": 2}, function (data) {
        console.log(data);
    });


});