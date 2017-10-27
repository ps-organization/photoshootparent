$().ready(function () {

    $.post("/collection/singleColletion", {"collectionId":1},function (data) {
        console.log("data:"+data);
        console.log("data:"+data.psUser.userNickname);
    });

});