$(document).ready(function () {

    //发起请求获取当前用户的id
    $.get("/PsUserController/show", function (data) {
        //获取用户头像,用户名
        $('.info_name').html(data.userName);
        $('.img-circle').attr('src', '../upload/' + data.userHeadphotoLocation);
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
        //显示个人作品
        $.post("/collection/userCollections", {id: userId}, function (data) {
            for (var i = 0; i < data.length; i++) {
                //创建图片格式
                var src ="../upload/"+ data[i].collectionPhotolocation;
                $('.photo_list').append("<li class='photo-item' id='pic"+i+"'>\n" +
                    "                    <img src='" + src + "'/>\n" +
                    "                </li>");
                // 图片加载速度慢，获取的图片图片大小一直为0，因此采用该方法
                $('#pic'+i).find('img').on("load",function () {
                    var w=$(this).width();
                    var h=$(this).height();
                    console.log("w:"+w+";h:"+h);
                    // 此处添加div
                    $('.photo_list').append();
                })
            }
        });
    });
//获取所有粉丝简略信息
    //获取所有关注简略信息
});