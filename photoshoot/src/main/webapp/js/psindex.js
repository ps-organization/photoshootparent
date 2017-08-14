$(document).ready(function () {
    // 登录框
    $(".close").click(function () {
        $(".login-dialog").css("display", "none")
    });
    $("#loginbtn").click(function () {
        $(".login-dialog").css("display", "block")
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

    $('#logbtn').submit(function () {
        alert("dsfsd");
        var account=$("input[name='account']").val();
        console.log(account);
        $.get("/PsUserController/check", {userName: account}, function (data) {

        });
    });


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