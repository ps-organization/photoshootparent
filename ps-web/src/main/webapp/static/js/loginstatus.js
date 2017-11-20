// 用于右上角的登录状态
$(document).ready(function () {
    //发起请求获取当前用户的id
    $.ajax({
        url: "/PsUserController/show",
        success: function (data) {
            console.log("username:" + data.userName);
            // 如果有用户登录的话，要去掉登录,注册按钮
            if (typeof data.userName==='undefined') {
                $('#navbar').load('../../templates/nav.html', function () {
                    $('.login').on('click', function () {
                        $('.mask').fadeIn();
                        $('.login-modal').fadeIn();
                    });
                    $('.reg').on('click', function () {
                        $('.mask').fadeIn();
                        $('.reg-modal').fadeIn();
                    });
                    $('.mask').on('click', function () {
                        $(this).fadeOut();
                        $('.login-modal').fadeOut();
                        $('.reg-modal').fadeOut();
                    });
                    $('input').blur(function () {
                        var $this = $(this);
                        if ($this.val()) {
                            $this.addClass('used');
                        }
                        else {
                            $this.removeClass('used');
                        }
                    });
                });
            }
            else {
                $('#navbar').load('../../templates/nav.html',function () {
                    $('.reg').remove();
                    $('.login').remove();
                    $('.nav-right').append("<li><a href='/templates/user_default.html' style='padding: 0 0;'>" +
                        "<img class='img-circle ' src=../upload/"
                        + data.userHeadphotoLocation + "></a></li><li class='drop-hover'><a href='/templates/user_default.html' >" + data.userName + "</a>" +
                        "<ul class='drop-menu'>" +
                        "                                    <li><a href='/templates/user_default.html'>个人作品</a></li>" +
                        "                                   <li><a href='#'>个人资料</a></li>" +
                        "                                    <li><a href='/PsUserController/logout'>登出账户</a></li>" +
                        "                                </ul></li>");
                });

                //创建一个装头像和名字 的li放在导航栏,并获取用户头像,用户名;为登录状态处添加下拉菜单
            }
        },
        error: function (data) {
            console.log("request user fail");
        }
    });
});