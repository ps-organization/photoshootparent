//获取显示div的id
var findResult
$(document).ready(function () {
    //获取按钮,点击事件
    $("#searchSubmit").click(function () {
        //获取文本框的值
        var searchInfo = $("#search").val();
        //点击前，移除已查询的图片
        $('#show').children().remove();
        //找到图片所在行,为开启显示准备
        findResult = $("#findResult");

        //ajax
        $.get("/PsUserController/findUser", {id: searchInfo}, function (data) {
            findResult.css("display", "block");
            // 获取插入图片节点
            $("#show").append("<img src=\"../" + data.userHeadphotoLocation + ".jpg\" />");
            // 设置图片样式
            $("#img").css({'height':'80','width':'80',});
            // 设置图片数据
            $("#nickname").html(data.userNickname);
            $("#age").html(data.userAge);
            $("#sex").html(verdictSex(data.userSex));
        });
    })

//判断性别
    function verdictSex(userSex) {
        return (userSex == 1 ? "男" : "女");
    }
});
