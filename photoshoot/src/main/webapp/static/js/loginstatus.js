// 用于右上角的登录状态
$(document).ready(function () {
    //发起请求获取当前用户的id
    $.ajax({
        url: "/PsUserController/show",
        success: function (data) {
            console.log("username:" + data.userName);
            // 如果有用户登录的话，要去掉登录,注册按钮
            if (typeof data.userName==='undefined') {
            }
            else {
                $('#login-li').remove();
                $('#register-li').remove();
                //创建一个装头像和名字 的li放在导航栏,并获取用户头像,用户名;为登录状态处添加下拉菜单
                $('.navbar-right').append("<li id='loginstatus-li' class='dropdown'>" +
                    "<a href='/templates/user_default.html' id='loginstatus-a' class='dropdown-toggle'>" +
                    "<img class='img-circle 'style='width: 30px;height:30px;margin-right: 10px;' src=../upload/"
                    + data.userHeadphotoLocation + ">" + data.userName + "</a>" +
                    "<ul class='dropdown-menu'>\n" +
                    "                <li><a href='#'>个人作品</a></li>\n" +
                    "                <li><a href='#'>个人资料</a></li>\n" +
                    "                <li><a href='/PsUserController/logout'>登出账户</a></li>\n" +
                    "            </ul></li>")

                $('#loginstatus-li').mouseover(function () {
                    clearTimeout(timeout);
                    //交互延迟90ms
                    timeout = setTimeout(function () {
                        $('#loginstatus-li').addClass("open");
                    }, 90);
                }).mouseout(function () {
                    clearTimeout(timeout);
                    timeout = setTimeout(function () {
                        $('#loginstatus-li').removeClass("open");
                    }, 90);
                })
            }
        },
        error: function (data) {
            console.log("request user fail");
        }
    });
});