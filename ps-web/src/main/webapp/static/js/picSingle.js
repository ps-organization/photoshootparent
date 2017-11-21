$(function () {
    // $('#navbar').load('../../templates/nav.html');
    var query = location.search.substring(1).split("&"),
        curId = parseInt(query[0]),
        curLikeStatus = parseInt(query[1]);
    getSinglePic(curId);
    if (curLikeStatus == 1) {
        $('.like-all').css('background-color', 'black');
    } else {
        $('.like-all').css('background-color', 'red');
    }
    thumbUp();
});
function getSinglePic(curId) {
    //Ajax
    $.post("/collection/singleColletion", {"collectionId": curId}, function (data) {
        $('.pic-content > img').attr('src', '../upload/' + data.collectionPhotolocation);
        $('.author-avatar > img').attr('src', '../upload/' + data.psUser.userHeadphotoLocation);
        $('.author-name').html(data.psUser.userNickname);
        $(".pic-introduction").html(data.collectionPhotointroduction);
        $('.like-all').attr('id', data.collectionId);
        $('.like-all').attr('data-attr', data.likeCount);
        $('.pic-name').html(data.collectionPhotoname);
        if (data.likeStuts==1){
            $('.like-all').css('background-color', 'red');
        }else {
            $('.like-all').css('background-color', 'black');
        }
    });
}
$('.reply-btn').on('click', function () {
    $(this).siblings('.reply-panel').fadeIn();
    $(this).siblings('.up-btn').fadeIn();
});
$('.up-btn').on('click', function () {
    $(this).siblings('.reply-panel').fadeOut();
    $(this).fadeOut();
});
//点赞
function thumbUp() {
    $('.like-all').on('click', function () {
        var that = $(this);
        var bgColor = $(this).css('background-color');
        var likeId = $(this).attr('id');
        console.log(bgColor);
        if (bgColor == 'rgb(255, 0, 0)') {
            // $(this).css('background-color', 'black');
            addLikeColor(that);
            addLikeCount(that);
            $.ajax({
                url: "/like/likeRecord",
                type: "POST",
                contentType: "application/json",
                dataType: 'json',
                data: JSON.stringify({"likeCollectionid": likeId}),
                success: function (resut) {
                    var imgHref = window.location.href;
                    imgHref.attr('href',imgHref.attr('href').split('&')[0]+"&1");
                },
                error: function (resut) {
                    console.log(resut);
                }
            });

        } else {
            noReapeatLike();
        }
    });
}
//点赞后的颜色改变
function addLikeColor(e) {
    console.log('lsdjflsdjkfjsdlfj');
    e.css('background-color', 'black');
}
//点赞数+1
function addLikeCount(e) {
    var likeCount = parseInt(e.attr('data-attr'));
    e.attr('data-attr', likeCount+1);
}
//不能重复点赞
function noReapeatLike() {
    $('.tip').stop(true, true).fadeIn(500).fadeOut(1500);
}