$(document).ready(function () {
    //发起请求获取当前用户的id
    $.get("/PsUserController/show", function (data) {
        //获取用户头像,用户名
        $('.info_name').html(data.userName);
        $('.img-circle').attr('src', '../' + data.userHeadphotoLocation + '.jpg');
        //此处没有var是声明全局变量
        userId = data.userId;
        //获取粉丝数目
        $.get("/PsUserController/showFansCount", {id: userId}, function (data) {
            $('.info_detail li:eq(2) a').html(" 粉丝 " + data.watchFansid);
        });
        // 获取关注数目
        $.get("/PsUserController/showWatchUserCount", {id: userId}, function (data) {
            $('.info_detail li:eq(1) a').html(" 关注 " + data.watchUserid);
        });
        //获取所有关注简略信息
        $.post("/psWatch/psWatchUser", {id: userId}, function (data) {
            for (var i = 0; i < data.length; i++) {
                $('.fans-list').append("<li class=\"fans-item\">\n" +
                    "                    <p>" + data[i].psUser.userHeadphotoLocation + "</p>\n" +
                    "                    <p>" + data[i].psUser.userName + "</p>\n" +
                    "                </li>")
            }
        });

    });
});