$(document).ready(function () {

    $.get("/PsUserController/show", function (data) {
        $('.info_name').html(data.userName);
        $('.img-circle').attr('src', '../' + data.userHeadphotoLocation + '.jpg');
        var userId=data.userId;
        $.get("/PsUserController/showFansCount",{ id: userId}, function (data) {
            $('.info_detail li:eq(2) a').html(" 粉丝 "+data.watchFansid);
        });

        $.get("/PsUserController/showWatchUserCount",{ id: userId}, function (data) {
            $('.info_detail li:eq(1) a').html(" 关注 "+data.watchUserid);
        });

    });



});