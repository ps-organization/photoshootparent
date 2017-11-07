$().ready(function () {
    //currentPicId:加载最后一张图片的ID，picLoadNum:每次请求图片数量
    importNav();
    $(window).on('scroll', showBack);
    $('.back-to-top').on('click', backToTop);
    var currentPicId = 0;
    var picLoadNum = 20;
    // var count = 0;
    //第一次请求图片
    requestPic(currentPicId, picLoadNum);//20张
    console.log("img:"+$('img').length);
    /*var count = 0;
    $('img').on('load', function() {
        count++;
        console.log(count);
        if (!(count % 2)) {
            imgLoad();
        }
    });*/
//Ajax请求图片function
//     function requestPic(currentPicId, picLoadNum) {
//         $.post("/collection/allCollection", {"currentPicId": currentPicId, "picLoadNum": picLoadNum}, function (data) {
//             for (var i = 0; i < data.length; i++) {
//                 /*console.log("id:" + data[i].collectionId + ";location:" + data[i].collectionPhotolocation);
//                 console.log("userName:" + data[i].userName + ";likeCount:" + data[i].likeCount);
//                 console.log("collectionPhotoname:" + data[i].collectionPhotoname);*/
//                 $('ul').append("<li>\n" +
//                     "            <figure>\n" +
//                     "                <a href=\"https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-575188.png\" target=\"_new\">\n" +
//                     "                    <div class='pic'>\n" +
//                     "                        <img class='lazyload' data-src='../upload/" + data[i].collectionPhotolocation + "' alt=''>\n" +
//                     "                    </div>\n" +
//                     "                </a>\n" +
//                     "                <figcaption>\n" +
//                     "                    <h3><a href=\"http://www.baidu.com\" target='_new'>" + data[i].collectionPhotoname + "</a></h3>\n" +
//                     "                    <div class='status'>\n" +
//                     "                        <div class='author'>\n" +
//                     "                            <a href='#'><img src='' alt=''><span>" + data[i].userName + "</span></a>\n" +
//                     "                        </div>\n" +
//                     "                        <div class=\"thumbUp\">\n" +
//                     "                            <a href='javascript:void(0);' class='like'><svg t=\"1509418554370\" class=\"icon\" style=\"\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"2688\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"200\" height=\"200\"><defs><style type=\"text/css\"></style></defs><path d=\"M706.56 136.533333c-75.093333 0-150.186667 40.96-194.56 98.986667C467.626667 174.08 392.533333 136.533333 317.44 136.533333c-129.706667 0-225.28 95.573333-225.28 225.28 0 157.013333 136.533333 283.306667 368.64 491.52l34.133333 30.72C498.346667 887.466667 505.173333 887.466667 512 887.466667s13.653333-3.413333 20.48-6.826667l34.133333-30.72c228.693333-208.213333 368.64-334.506667 368.64-491.52C931.84 232.106667 836.266667 136.533333 706.56 136.533333z\" p-id=\"2689\" fill=\"#8a8a8a\"></path></svg><span>" + data[i].likeCount + "</span></a>\n" +
//                     "                        </div>\n" +
//                     "                    </div>\n" +
//                     "                </figcaption>\n" +
//                     "            </figure>\n" +
//                     "        </li>");
//                 console.log("likeStatus:"+data[i].likeStatus);
//             }
//             //添加多次点赞提示
//             $('ul').append("<p class=\"tip\"><span>认真点的赞不能取消哟</span></p>");
//             //点赞功能的实现
//             // console.log($('.like').length);
//             $('.like').on('click', function () {
//                 console.log('okok');
//                 var that = $(this);
//                 var svgColor = $(this).find('svg path').attr('fill');
//                 console.log($(this).find('svg path').attr('fill'));
//                 if (svgColor == '#8a8a8a') {
//                     //改变点赞图标颜色，并给点赞数+1
//                     addLike(that);
//                     //插入点赞记录的ajax
//
//                 } else if (svgColor == 'blue') {
//                     noRepeatLike(that);
//                 }
//             });
// //图片加载，循环迭代函数
//             $('img').on('load', function () {
//                 console.log("添加load事件！");
//                 count++;
//                 console.log("加载图片ID："+count);
//                 //如果加载完所有图片(count是picLoadNum倍数)，就发起请求
//                 if (!(count % picLoadNum)) {
//                     //Ajax请求图片function
//                     requestPic(currentPicId, picLoadNum);
//                     //请求图片后，也增加最后一张图片的ID;
//                     currentPicId=currentPicId+picLoadNum;
//                     console.log("请求成功，currentPicId："+currentPicId);
//                 }
//             });
//         });
//     }
});
//加载导航栏公共模块
function importNav() {
    $('#navbar').load('../../templates/nav.html');
}

var count = 0;
function requestPic(currentPicId, picLoadNum) {
    $.post("/collection/allCollection", {"currentPicId": currentPicId, "picLoadNum": picLoadNum}, function (data) {
        for (var i = 0; i < data.length; i++) {
            /*console.log("id:" + data[i].collectionId + ";location:" + data[i].collectionPhotolocation);
            console.log("userName:" + data[i].userName + ";likeCount:" + data[i].likeCount);
            console.log("collectionPhotoname:" + data[i].collectionPhotoname);*/
            $('.grid').append("<li>\n" +
                "            <figure>\n" +
                "                <a href=\"https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-575188.png\" target=\"_new\">\n" +
                "                    <div class='pic'>\n" +
                "                        <img class='lazyload' data-src='../upload/" + data[i].collectionPhotolocation + "' alt=''>\n" +
                "                    </div>\n" +
                "                </a>\n" +
                "                <figcaption>\n" +
                "                    <h3><a href=\"http://www.baidu.com\" target='_new'>" + data[i].collectionPhotoname + "</a></h3>\n" +
                "                    <div class='status'>\n" +
                "                        <div class='author'>\n" +
                "                            <a href='#'><img src='' alt=''><span>" + data[i].userName + "</span></a>\n" +
                "                        </div>\n" +
                "                        <div class=\"thumbUp\">\n" +
                "                            <a href='javascript:void(0);' class='like'><svg t=\"1509418554370\" class=\"icon\" style=\"\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"2688\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"200\" height=\"200\"><defs><style type=\"text/css\"></style></defs><path d=\"M706.56 136.533333c-75.093333 0-150.186667 40.96-194.56 98.986667C467.626667 174.08 392.533333 136.533333 317.44 136.533333c-129.706667 0-225.28 95.573333-225.28 225.28 0 157.013333 136.533333 283.306667 368.64 491.52l34.133333 30.72C498.346667 887.466667 505.173333 887.466667 512 887.466667s13.653333-3.413333 20.48-6.826667l34.133333-30.72c228.693333-208.213333 368.64-334.506667 368.64-491.52C931.84 232.106667 836.266667 136.533333 706.56 136.533333z\" p-id=\"2689\" fill=\"#8a8a8a\"></path></svg><span>" + data[i].likeCount + "</span></a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </figcaption>\n" +
                "            </figure>\n" +
                "        </li>");
            console.log("likeStatus:"+data[i].likeStatus);
        }
        //添加多次点赞提示
        // $('ul').append("<p class=\"tip\"><span>认真点的赞不能取消哟</span></p>");
        //点赞功能的实现
        // console.log($('.like').length);
        $('.like').on('click', function () {
            console.log('okok');
            var that = $(this);
            var svgColor = $(this).find('svg path').attr('fill');
            console.log($(this).find('svg path').attr('fill'));
            if (svgColor == '#8a8a8a') {
                //改变点赞图标颜色，并给点赞数+1
                addLike(that);
                //插入点赞记录的ajax

            } else if (svgColor == 'blue') {
                noRepeatLike(that);
            }
        });
    //图片加载，循环迭代函数
        $('img').on('load', function () {
            console.log("添加load事件！");
                count++;//hei
                console.log("加载图片ID："+count);
                //如果加载完所有图片(count是picLoadNum倍数)，就发起请求
                if (!(count % picLoadNum)) {
                    //Ajax请求图片function

                    requestPic(currentPicId, picLoadNum);

                    //请求图片后，也增加最后一张图片的ID;
                    currentPicId=currentPicId+picLoadNum;
                    console.log("请求成功，currentPicId："+currentPicId);
                }

        });
    });
}


//改变点赞图标颜色，并给点赞数+1
function addLike(e) {
    var likeCount = e.find('span');
    e.find('svg').stop().animate({width: '0', height: '0'}, 50);
    e.find('svg').animate({width: '18px', height: '18px'}, 'slow').children().attr({fill: 'blue'});
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
    // console.log(disLeft);
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