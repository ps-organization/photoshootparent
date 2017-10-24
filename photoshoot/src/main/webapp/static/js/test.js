$().ready(function () {
    console.log($('#t-img').find('img').width());
    console.log($('#t-img img').height());

    $.post("/collection/allCollection", function (data) {
        for(var i=0;i<data.length;i++) {
            /*console.log("id:" + data[i].collectionId + ";location:" + data[i].collectionPhotolocation);
            console.log("userName:" + data[i].userName + ";likeCount:" + data[i].likeCount);
            console.log("collectionPhotoname:" + data[i].collectionPhotoname);*/
            $('ul').append("<li>\n" +
                "            <figure>\n" +
                "                <a href=\"https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-575188.png\" target=\"_new\">\n" +
                "                    <div class=\"pic\">\n" +
                "                        <img class='lazyload' data-src=\"../upload/"+ data[i].collectionPhotolocation +"\" alt=\"\">\n" +
                "                    </div>\n" +
                "                </a>\n" +
                "                <figcaption>\n" +
                "                    <h3><a href=\"http://www.baidu.com\" target=\"_new\">namsdffffffffffffsdfffffffffsdfsdfsdfsfffffe</a></h3>\n" +
                "                    <div class=\"status\">\n" +
                "                        <div class=\"author\">\n" +
                "                            <a href=\"#\"><img src=\"../20170727.jpg\" alt=\"\"><span>author</span></a>\n" +
                "                        </div>\n" +
                "                        <div class=\"collection\">\n" +
                "                            <a href=\"#\"><img src=\"../20170727.jpg\" alt=\"\"><span>author</span></a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </figcaption>\n" +
                "            </figure>\n" +
                "        </li>");
        }

    });

});