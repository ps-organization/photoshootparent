$().ready(function () {
    /*step 1*/
    var button1="<button id='emailf-button'></button>";
    var button2="<button id='phonef-button'></button>";
    $.getScript("../static/js/common.js");
    $('#next-button').click(function () {
    if (!isEmpty($('#username').val())){
        $('#tips')[0].innerHTML="用户名不能为空";
        $('#username').addClass('wrong');
    }
    else{
        $('#pic2').css("background-position","50% 82%");
        $('#pic1').css("background-position","10% 14%");
        $('#tips')[0].innerHTML="请选择找回方式";
        $("input").remove("#username");
        $("button").remove("#next-button");
        $('.contain').append(button1,button2);
    }/*step 2*/
        $('#emailf-button').click(function () {
            $('#pic2').css("background-position","50% 14%");
            $('#pic3').css("background-position","95% 82%");
            $('#tips')[0].innerHTML="";
            $("button").remove("#emailf-button");
            $("button").remove("#phonef-button");
            var page="<div> <input type='text' placeholder='请输入邮箱验证码' id='ver-code'>" +
                "<button id='rgs-get-code' class='aui-btn-warning'>获取验证码</button></div>" +
                "<input type='text' placeholder='请输入新密码（最少8位最多16位）' id='pwd'>" +
                "<input type='text' placeholder='请与上述密码保持一致' id='repwd'>" +
                "<button id='submit' type='submit'>提交</button>"
            $('.contain').append(page);
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

            /*getcode*/
            $('#rgs-get-code').on('click', function() {
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
            var page="<div> <input type='text' placeholder='请输入短信验证码' id='ver-code'>" +
                "<button id='rgs-get-code' class='aui-btn-warning'>获取验证码</button></div>" +
                "<input type='text' placeholder='请输入新密码（最少8位最多16位）' id='pwd'>" +
                "<input type='text' placeholder='请与上述密码保持一致' id='repwd'>" +
                "<button id='submit' type='submit'>提交</button>"
            $('.contain').append(page);
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