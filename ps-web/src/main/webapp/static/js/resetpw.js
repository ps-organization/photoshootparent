$().ready(function () {
    var username = null;
    /*step 1*/
    var button1="<button id='emailf-button'></button>";
    var button2="<button id='phonef-button'></button>";
    var button3="<button id='before-button'>返回</button>";
    var page="<div> <input type='text' placeholder='请输入邮箱' id='ver-email'>" +
        "<button id='rgs-get-code' class='aui-btn-warning'>获取验证码</button></div>" +
        "<input type='text' placeholder='请输验证码' id='ver-code'>"+
        "<input type='text' placeholder='请输入新密码（最少8位最多16位）' id='pwd'>" +
        "<input type='text' placeholder='请与上述密码保持一致' id='repwd'>" +
        "<button id='before-button' type='button'>返回</button>" +
        "<button id='submit' type='submit'>提交</button>"
    $.getScript("../static/js/common.js");
    $('#next-button').click(function () {
    if (!isEmpty($('#username').val())){
        $('#tips')[0].innerHTML="用户名不能为空";
        $('#username').addClass('wrong');
    }
    else{
        username = $("#username").val();
        //发送请求，查询该用户是否存在
         $.ajax({
            url:"/resetpw/resetuserpw",
            type:"POST",
            data:"username="+username,
            success:function(resut){
                console.log(resut);
                if(resut){
                    $('#pic2').css("background-position","50% 82%");
                    $('#pic1').css("background-position","10% 14%");
                    $('#tips')[0].innerHTML="请选择找回方式";
                    return true;
                }else {
                    $('#tips')[0].innerHTML="用户名不正确";
                    $('#username').addClass('wrong');
                    return false;
                }
            },
            error:function (resut) {
                console.log(resut);
                $('#tips')[0].innerHTML="用户名不正确";
                $('#username').addClass('wrong');
                return false;
            }
        });
    }
        $("input").remove("#username");
        $("button").remove("#next-button");
        $('.contain').append(button1,button2,button3);

        $('#before-button').click(function () {
            parent.location.reload();
        });
    /*step 2*/

        $('#emailf-button').click(function () {
            $('#pic2').css("background-position","50% 14%");
            $('#pic3').css("background-position","95% 82%");
            $('#tips')[0].innerHTML="";
            $("button").remove("#emailf-button");
            $("button").remove("#phonef-button");
            $("button").remove("#before-button");
            $('.contain').append(page);
            $('#before-button').click(function () {
                parent.location.reload();
            });
            $('#submit').click(function () {
                if (!isEmpty($('#ver-code').val())||!isEmpty($('#pwd').val())||!isEmpty($('#repwd').val())){
                    $('#tips')[0].innerHTML="选项不能为空";
                }else if (!isPwd($('#pwd').val())){
                    $('#tips')[0].innerHTML="密码格式错误";
                }
                else if($('#pwd').val()!=$('#repwd').val()){
                    $('#tips')[0].innerHTML="两处密码不一致";
                    $('#pwd').addClass("wrong");
                    $('#repwd').addClass("wrong");
                }
                var emailcode = $('#ver-code').val();
                var pw = $('#pwd').val();
                var rpw = $('#repwd').val();
                $.ajax({
                    url:"/resetpw/EmailCodeResetPw",
                    type:"POST",
                    data:{"userName":username,"emailcode":emailcode,"userPassword":pw,"rpw":rpw},
                    success:function(resut){
                        console.log(resut);
                            alert("简陋提示：密码修改成功！");
                        return true;
                    },
                    error:function (resut) {
                        console.log(resut);
                        alert("密码修改失败");
                        return false;
                    }
                });
            });

            /*getcode*/
            $('#rgs-get-code').on('click', function() {
                var veremail = $("#ver-email").val();
                $.ajax({
                    url:"/resetpw/sendEmailCode",
                    type:"POST",
                    data:{"username":username,"email":veremail},
                    success:function(resut){
                        console.log(resut);
                        if(resut.eq('success')){

                        }else {
                            alert('邮箱错误！');
                        }
                        return true;
                    },
                    error:function (resut) {
                        console.log(resut);
                        return false;
                    }
                });
                var _this = this;
                var loopTime = 4;
                // 修改样式
                $(_this).prop('disabled', true).removeClass('aui-btn-warning').html('重新获取(5s)');
                // 定时器
                var countDown = setInterval(function() {
                    if (loopTime == 0) {
                        $(_this).prop('disabled', false).addClass('aui-btn-warning').html('获取验证码');
                        // 清除定时器
                        window.clearInterval(countDown);
                    }else {
                        $(_this).html('重新获取(' + loopTime + 's)');
                        loopTime -= 1;
                    }
                }, 1000);
            });
        });


        $('#phonef-button').click(function () {
            $('#pic2').css("background-position","50% 14%");
            $('#pic3').css("background-position","95% 82%");
            $('#tips')[0].innerHTML="";
            $("button").remove("#emailf-button");
            $("button").remove("#phonef-button");
            $("button").remove("#before-button");
            $('.contain').append(page);
            $('#before-button').click(function () {
                parent.location.reload();
            });
            $('#submit').click(function () {
                if (!isEmpty($('#ver-code').val())||!isEmpty($('#pwd').val())||!isEmpty($('#repwd').val())){
                    $('#tips')[0].innerHTML="选项不能为空";
                }else if (!isPwd($('#pwd').val())){
                    $('#tips')[0].innerHTML="密码格式错误";
                }
                else if($('#pwd').val()!=$('#repwd').val()){
                    $('#tips')[0].innerHTML="两处密码不一致";
                    $('#pwd').addClass("wrong");
                    $('#repwd').addClass("wrong");
                }
            });
        });


    });
});