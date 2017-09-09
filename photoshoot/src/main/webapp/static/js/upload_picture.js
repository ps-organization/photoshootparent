$(document).ready(function () {
    //定一个计数器，统计页面上添加的li数量，即图片数量
    var count = 0;
    $("body").on('change', '#file', function () {
        var formData = new FormData();
        var ffileTags = document.getElementById('file').files;
        // liID为图片的id
        liID = "li_index_" + count;
        console.log("lenth:" + ffileTags.length);
        //判断没有文件,一个文件还是多个
        if (ffileTags.length == 0) {
            return false;
        } else if (ffileTags.length == 1) {
            formData.append("file", ffileTags[0]);
            // formData.append("token", token_value);
            // var imgurl =  ffile.getAsDataURL();
            console.log(count);
            // 创建临时图片显示的url
            var src = window.URL.createObjectURL(ffileTags[0]);
            //创建图片相应的节点.为图片装上src,并显示
            creatImage(src, count);
            //计数器count加1
            count++;
            //多文件处理
        } else {
            console.log("come!");
            for (var i = 0, ffileTagsLen = ffileTags.length; i < ffileTagsLen; i++) {
                formData.append("file", ffileTags[i]);
                // 创建临时图片显示的url
                var src = window.URL.createObjectURL(ffileTags[i]);
                // 为图片装上src和id
                creatImage(src, count);
                //计数器count加1
                count++;
            }
        }
        $.ajax({
            url: "/image/uploadPic",
            type: "POST",
            data: formData,
            cache: false,
            processData: false, // 不要对data参数进行序列化处理，默认为true
            contentType: false, // 不要设置Content-Type请求头，因为文件数据是以 multipart/form-data 来编码
            xhr: function () {
                myXhr = $.ajaxSettings.xhr();
                //判断监听函数是否为函数.如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
                if (myXhr.upload) {
                    myXhr.upload.addEventListener('progress', uploadd, false);
                }
                return myXhr;
            },
            success: function (res) {
                // 请求成功
                console.log("upload success!")

                //根据后台返回传送成功的标志此处去掉图片的透明度
                //此时图片已上传完毕,去掉透明度
                // $('#test_img').css("opacity", "1");
            },
            error: function (res) {
                // 请求失败
                console.log(res);
            }
        });
    });

//此处起初是为了清空formdata中的文件,但不起作用,其实已经通过new在事件中l解决了，但如果为空是否会被容易回收，提高性能?
//     document.getElementById('file').value="";

});

function uploadd(e) {       //类似这种写法 xhr.upload.onprogress=function(e){}
    if (e.lengthComputable) {
        var percent = Math.floor(e.loaded / e.total * 100);
        //打开进度条
        $('.cust_progress').css("display", "inline-block");
        //设置进度
        $('#example_progress').attr("style", "width:" + percent + "%");
        if (percent <= 100) {
            $("#progress").html('已上传：' + percent + '%');
            //以下为图片处的进度
            $("i[name='progress']").html('已上传：' + percent + '%');

        }
        if (percent >= 100) {
            //延迟执行,让用户看到进度条加载完那一刻
            setTimeout(function () {
                // $("#progress").html('文件上传完毕，请等待...');
                $('.cust_progress').css("display", "none");
            }, 500);
        }
    }
}

//去掉透明
function outLucency() {

}

//创建图片的节点以及给图片添加，src和id
function creatImage(src, count) {
    var id="li_index_"+count;

    $('.upload_tips').before("<li class=\"photo-item\" id=\"" + id + "\" style=\"\">\n" +
        "<i name=\"progress\" class=\"icon-add\">已上传：</i>\n" +
        "<img src=\"" + src + "\" style=\"width: 250px;opacity: 0.4;\"/>\n" +
        "</li>");
}