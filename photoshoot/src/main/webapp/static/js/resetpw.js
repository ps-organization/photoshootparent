/*手机验证*/
$().ready(function () {
    $('#rgs-get-code').on('click', function() {
        var _this = this;
        var loopTime = 59;
        // 修改样式
        $(_this).prop('disabled', true).removeClass('aui-btn-warning').addClass('rgs-get-code').html('重新获取(60s)');
        // 定时器
        var countDown = setInterval(function() {
            if (loopTime == 0) {
                $(_this).prop('disabled', false).removeClass('rgs-get-code').addClass('aui-btn-warning').html('获取验证码');
                // 清除定时器
                window.clearInterval(countDown);
            }else {
                $(_this).html('重新获取(' + loopTime + 's)');
                loopTime -= 1;
            }
        }, 1000);
    });
});