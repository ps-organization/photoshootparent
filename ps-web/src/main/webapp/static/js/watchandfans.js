$(document).ready(function () {
    // 获取当前用户的id
    var userId = getQueryString("id");
    alert(userId);
    //获取用户头像,用户名
    $.get("/PsUserController/psUser/" + userId, function (data) {
        $('.info_name').html(data.userName);
        $('.img-circle').attr('src', '../' + data.userHeadphotoLocation);

        //获取粉丝数目
        $.get("/PsUserController/showFansCount", {id: userId}, function (data) {
            $('.info_detail li:eq(2) a').html(" 粉丝 " + data.watchFansid);
        });
        // 获取关注数目
        $.get("/PsUserController/showWatchUserCount", {id: userId}, function (data) {
            $('.info_detail li:eq(1) a').html(" 关注 " + data.watchUserid);
        });
        //显示个人作品
        $.post("/collection/userCollections", {id: userId}, function (data) {
            for (var i = 0; i < data.length; i++) {
                //创建图片格式
                var src = "http://localhost:8080/upload/images/" + data[i].collectionPhotolocation;
                $('.photo_list').append("<li class=\"photo-item\" >\n" +
                    "                    <img src=\"" + src + "\"/>\n" +
                    "                </li>")
            }
        });
    });
})


function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}