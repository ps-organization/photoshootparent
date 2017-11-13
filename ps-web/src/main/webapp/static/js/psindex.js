var timeout;
$(document).ready(function () {
    // 点击登录注册后的事件
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

    // $('.tabs-list > li').on('click', function () {
    //     var tabIndex = $(this).index();
    //     $(this).addClass('active').siblings().removeClass('active');
    //     $('.tabs-pic-show > li').eq(tabIndex).addClass('active').siblings().removeClass('active');
    // });

    //点击开始了解后的滑动
    $('.about').on('click', function() {
        $("html,body").animate({
                scrollTop: $("#start").offset().top},
            1000);
    });

    //点击注册按钮提交表单
    $('#regbtn').click(function () {
        var userName = $('#username').val();
        var userPassword = $('#vpassword').val();
        if (userName == "" || userName == null ) {
            //跳出该函数
            return false;
        }
        var user = {"userName": userName, "userPassword": userPassword};
        $.ajax({
            url: "/PsUserController/newuser",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(user),
            success: function (res) {
                alert("upload success!");
                window.location.href="/templates/user_default.html";
            }
        })
    });

    //    推荐标签
    $(".tabs-list > li").click(function () {
        $(this).addClass('active').siblings().removeClass('active');
        // 图片名称
        var picName = $(this).text();
        var flag = getFlag(picName);
        var pics = $('.pic');
        // 所有图片a标签
        for (var i = 0; i < pics.length; i++) {
            // 设置图片路径
            var picpath = "../img/" + flag + i + ".jpg";
            var uurl = "url(" + picpath + ")";
            $('.grid li').eq(i).find('img').attr('src', picpath);
        }
    })
});


//获取一个显示图片的指示
function getFlag(value) {
    if (value == "人物") {
        flag = "r";
    } else if (value == "动物") {
        flag = "d";
    } else if (value == "风景") {
        flag = "f";
    } else if (value == "汽车") {
        flag = "q";
    }
    return flag;
}