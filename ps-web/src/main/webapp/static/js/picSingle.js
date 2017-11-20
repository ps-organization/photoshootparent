$(function () {
    // $('#navbar').load('../../templates/nav.html');
    var curId = parseInt(location.search.substring(1));
    getSinglePic(curId);
});
function getSinglePic(curId) {
    $.post("/collection/singleColletion", {"collectionId": curId}, function (data) {
        $('.pic-content > img').attr('src', '../upload/' + data.collectionPhotolocation);
        $('.author-avatar > img').attr('src', '../upload/' + data.psUser.userHeadphotoLocation);
        $('.author-name').html(data.psUser.userNickname);
        $(".pic-introduction").html(data.collectionPhotointroduction);
        $('.like-all').attr('data-attr', data.likeCount);
        $('.pic-name').html(data.collectionPhotoname);
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
$('.like-all').on('click', function () {
    var bgColor = $(this).css('background-color');
    if (bgColor == 'rgb(255, 46, 46)') {
        $(this).css('background-color', 'black');
    } else {
        $(this).css('background-color', 'rgb(255, 46, 46');
    }
});