var picLoadNum = 30; //图片每次请求的数量
var count = 0;      //统计已加载图片的数量
let a = {ex: 0};    //ex为页面当前图片的最大峰值

$().ready(function () {
    // importNav();
    $(window).on('scroll', showBack);
    $('.back-to-top').on('click', backToTop);
    // //监听count的数值
    Object.defineProperty(a, 'ex', {
        set: function (value) {
            ex = value;
            if (!(value % picLoadNum)) {
                // Ajax请求图片，每次picLoadNum张
                requestPic(a.ex, picLoadNum);
                console.log("请求成功，a.ex：" + a.ex);
            }
        },
        get: function () {
            return ex;
        }
    });
    a.ex = count;

});

//加载导航栏公共模块
// function importNav() {
//     $('#navbar').load('../../templates/nav.html');
// }

function requestPic(currentPicId, picLoadNum) {
    $.post("/collection/allCollection", {"currentPicId": currentPicId, "picLoadNum": picLoadNum}, function (data) {
        //生成图片标签
        for (var i = 0; i < data.length; i++) {
            $('.grid').append("<li>\n" +
                "            <figure>\n" +
                "                <a href='/templates/picSingle.html?" + data[i].collectionId + "&" + data[i].likeStatus+ "'target='_new'>\n" +
                "                    <div class='pic'>\n" +
                "                        <img class='lazyload'  data-src='../upload/" + data[i].collectionPhotolocation + "' alt=''>\n" +
                "                    </div>\n" +
                "                </a>\n" +
                "                <figcaption>\n" +
                "                    <h3><a href='http://www.baidu.com' target='_new'>" + data[i].collectionPhotoname + "</a></h3>\n" +
                "                    <div class='status'>\n" +
                "                        <div class='author'>\n" +
                "                            <a href='#'><img src='' alt=''><span>" + data[i].userName + "</span></a>\n" +
                "                        </div>\n" +
                "                        <div class='thumbUp'>\n" +
                "                            <a href='javascript:void(0);' id='" + data[i].collectionId + "' class='like'><svg t=\"1509418554370\" class=\"icon\" style=\"\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"2688\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"200\" height=\"200\"><defs><style type=\"text/css\"></style></defs><path d=\"M706.56 136.533333c-75.093333 0-150.186667 40.96-194.56 98.986667C467.626667 174.08 392.533333 136.533333 317.44 136.533333c-129.706667 0-225.28 95.573333-225.28 225.28 0 157.013333 136.533333 283.306667 368.64 491.52l34.133333 30.72C498.346667 887.466667 505.173333 887.466667 512 887.466667s13.653333-3.413333 20.48-6.826667l34.133333-30.72c228.693333-208.213333 368.64-334.506667 368.64-491.52C931.84 232.106667 836.266667 136.533333 706.56 136.533333z\" p-id=\"2689\" fill=\"#8a8a8a\"></path></svg><span>" + data[i].likeCount + "</span></a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </figcaption>\n" +
                "            </figure>\n" +
                "        </li>");
            //此处给点赞图片改变颜色
            // console.log('output:'  + data[i].likeStatus);
            if (data[i].likeStatus == 1) {
                console.log('likeid:   '+$('#'+data[i].collectionId).attr('id'));
                $('#'+data[i].collectionId).find('svg path').attr({fill: '#ca3530'});
                // console.log($('.like').parents('li:nth-child(li)'));
            }
        }//for循环结束

        //清除点赞事件，防止多次请求叠加
        $('.like').unbind('click');
        // 点赞功能
        thumbUp();

        //监听图片加载，数量，为下次请求图片做准备
        $('img').on('load',imgLoad);
    });
}

//给ex赋值，为下次请求图片做准备
function imgLoad() {
    count++;//统计图片数量
    console.log("count：" + count);
    //如果加载完所有图片(count是picLoadNum倍数)，就发起请求
    if (!(count % picLoadNum)) {
        //请求图片后，也增加最后一张图片的ID;
        a.ex = count;
    }
}



//点赞功能的实现
function thumbUp() {
    $('.like').on('click', function () {
        var that = $(this);
        var likeId = that.attr('id');
        console.log("likeId:" + likeId);
        var svgColor = that.find('svg path').attr('fill');
        console.log(that.find('svg path').attr('fill'));
        if (svgColor == '#8a8a8a') {
            console.log("likeAjax come in!");
            //改变点赞图标颜色，并给点赞数+1
            addLike(that);
            addOneLike(that);
            $.ajax({
                url: "/like/likeRecord",
                type: "POST",
                contentType: "application/json",
                dataType: 'json',
                data: JSON.stringify({"likeCollectionid": likeId}),
                success: function (resut) {
                    // console.log('successsssss');
                    var imgHref=$('#' + likeId).parents('li').find('figure a');
                    // console.log("likeId"+likeId);
                    imgHref.attr('href',imgHref.attr('href').split('&')[0]+"&1");
                },
                error: function (resut) {
                    console.log("test erro----------");
                    console.log(resut);
                }
            });
        } else if (svgColor == '#ca3530') {
            noRepeatLike(that);
        }
    });
}


//改变点赞图标：添加动态变化过程、颜色
function addLike(e) {
    e.find('svg').stop().animate({width: '0', height: '0'}, 50);
    e.find('svg').animate({width: '18px', height: '18px'}, 'slow').children().attr({fill: '#ca3530'});
}

// 给点赞数+1
function addOneLike(e) {
    var likeCount = e.find('span');
    likeCount.html(parseInt(likeCount.html()) + 1);
}


//提示禁止取消赞
function noRepeatLike(e) {
    var posLeft = e.parents('li').offset().left;
    var disLeft = ($('.tip').outerWidth() - e.parents('li').outerWidth()) / 2;
    var l = posLeft - disLeft;
    var posTop = e.parents('li').offset().top;
    var disTop = ($('.tip').outerHeight() - e.parents('li').outerHeight()) / 2;
    var t = posTop - disTop;
    $('.tip').css({'left': l + 'px', 'top': t + 'px'}).stop(true, true).fadeIn(500).fadeOut(1500);
}

//返回顶部操作
function backToTop() {
    $('body, html').animate({
        scrollTop: 0
    }, 500);
}

//根据滚动条判断返回顶部按钮的显式与隐藏
function showBack() {
    if ($(window).scrollTop() > $(window).height() / 2) {
        $('.back-to-top').fadeIn();
    } else {
        $('.back-to-top').fadeOut();
    }
}