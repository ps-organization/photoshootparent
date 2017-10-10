var timeout;
$(document).ready(function () {

    $('#nav-find').mouseover(function () {
        clearTimeout(timeout);
        //交互延迟90ms
        timeout = setTimeout(function () {
            $('#nav-find').addClass("open");
        }, 90);
    }).mouseout(function () {
        clearTimeout(timeout);
        timeout = setTimeout(function () {
            $('#nav-find').removeClass("open");
        }, 90);
    })


    // 导航栏右侧,打开或关闭登录框
    $("#loginbtn").click(function () {
        $(".login-dialog").css("display", "block")
    });
    $(".close").click(function () {
        $(".login-dialog").css("display", "none")
    });


    // 导航栏右侧,打开或关闭注册框
    $("#registerbtn").click(function () {
        $(".register-dialog").css("display", "block")
    });
    $(".close").click(function () {
        $(".register-dialog").css("display", "none")
    });

    //点击注册按钮提交表单
    $('#regbtn').click(function () {
        var userName = $('#username').val();
        var userPassword = $('#vpassword').val();
        if (userName == "" || userName == null) {
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
    $(".reco-tags li a").click(function () {
        $(this).parent().siblings().removeClass("active");
        $(this).parent().addClass("active");

        // 图片名称
        var flag = getFlag(this.innerHTML);

        // 所有图片a标签
        var pics = $('.pic');

        for (var i = 0; i < pics.length; i++) {
            // 设置图片路径
            var picpath = "../img/" + flag + i + ".jpg";
            var uurl = "url(" + picpath + ")";
            pics[i].style.cssText = "background-image:" + uurl;
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