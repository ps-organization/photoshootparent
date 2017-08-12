//获取显示div的id

$(document).ready(function () {

    $('#testPic').imgAreaSelect({aspectRatio: '3:3', handles: true});


    //上传图片部分
    // $("#registerHeadPhoto").change(function () {
    // $("body").on('change', '#registerHeadPhoto', function () {
    $("body").on('click', '#registerHeadPhoto', function () {
        if (isPic($('#registerHeadPhoto').val())) {
            // 需要链接到服务器地址
            $.ajaxFileUpload({
                url: '/file/upload?timestamp=' + new Date().getTime(),// 需要链接到服务器地址
                fileElementId: 'registerHeadPhoto',// 文件选择框的id属性
                dataType: 'json',// 服务器返回的格式，可以是json
                type: 'post',
                cache: false,
                ifModified: true,

                success: function (data) {
                    var imgPath = data;
                    $('#modal-picture').modal();
                    $('#preview-img').remove();
                    $('<img  id="preview-img"  />').insertAfter($('#width'));

                    $("#preview-img").attr("src", data['path']);

                    // ---根据高宽 设置 设置图片等比例，尽量填满div
                    $("#preview-img").removeAttr("style");
                    if (data['scale'] > 1.0) {
                        $("#preview-img").css(data['style'], "100%");
                    }


                    /*打开模态框*/
                    // $('#modal-picture').modal();
                    $('#preview-img').imgAreaSelect({
                        aspectRatio: '1:1', handles: true,
                        x1: 0, y1: 0, x2: 30, y2: 30, /*默认提交的值*/
                        onSelectEnd: function (img, selection) {
                            $('#x').val(selection.x1 * data['scale']);
                            $('#y').val(selection.y1 * data['scale']);
                            $('#width').val(selection.width * data['scale']);
                            $('#height').val(selection.height * data['scale']);
                        }
                    });
                    $('#pic-tailor').click(function () {
                        cutImage(data['path']);
                    });

                    $('#pic-address').val(data['path']);
                },
                error: function (data) {
                    alert("操作出现错误,请联系管理员!");
                }
            });

        } else {
            $('#myModal').modal();
        }
    });


    //获取提交表单按钮,点击事件
    $("#submitBtn").click(function () {
        //获取文本框的值
        var registerAccount = $("#registerAccount").val();
        var registerPassword = $("#registerPassword").val();

        var submitBtn = $("#submitBtn").val();

        //ajax
        $.post("/PsUserController/register", {
            userName: registerAccount,
            userPassword: registerPassword
        }, function (data) {
            //返回插入是否成功标志1/0
            alert(data);
        });


    });


});


// 判断是否是图片
function isPic(picValue) {
    // 获取上传文件后缀，后3个字母
    var allowExtention = new Array('jpg', 'jpeg', 'png', 'gif');
    var len = picValue.length;
    var math = picValue.substring(len - 3, len);
    // 定义判定变量，true为是图片
    var flag = false;
    for (var i = 0; i < allowExtention.length; i++) {
        if (allowExtention[i] == math) {
            flag = true;
        }
    }
    if (!flag) {
        // $.notify({
        //     title: '<strong>Failure! </strong>',
        //     message: 'Please choose correct image format.'
        // }, {
        //     type: 'danger',
        //     z_index: 2000
        // });
    }
    return flag;
};


//---图片裁剪方法----
function cutImage(path) {
    $.ajax({
        type: "POST",
        url: "/file/imgCrop",
        dateType: "json",
        data: {
            "x": $('#x').val(),
            "y": $('#y').val(),
            "width": $('#width').val(),
            "height": $('#height').val(),
            "path": path

        },
        cache: false,
        ifModified: true,

        success: function (data) {
            var ttamp = (new Date()).valueOf();
            var pathRootSec = "http://localhost:8080/";
            var picSrc = pathRootSec + data + "?" + ttamp;
            // $('#pic-cut-show').replaceWith('<img id=\"pic-cut-show\" />')
            $('#pic-cut-show').attr('src', picSrc);
            $('#preview-img').imgAreaSelect({instance: true}).cancelSelection();
        }
    });
};

function convertURL(url) {
    var ttamp = (new Date()).valueOf();

    if (url.indexOf("?") >= 0) {
        url = url + "&t=" + ttamp;
    } else {
        url = url + "?t=" + ttamp;
    }
    return url;
}


$("#modal-picture").on('hide.bs.modal', function () {
    $('#preview-img').imgAreaSelect({instance: true}).cancelSelection();
    $("#preview-img").remove();

    //---no handle --
    $.ajax({
        'url': 'restaurant/picture/delete',
        type: 'POST',
        data: {
            path: $('#pic-address').val()
        }
    });
});