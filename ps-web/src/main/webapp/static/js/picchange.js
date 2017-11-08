$().ready(function () {
    var cArr=["p7","p6","p5","p4","p3","p2","p1"];
    var index=0;
//上一张
    function previmg(){
        cArr.unshift(cArr[6]);
        cArr.pop();
        //i是元素的索引，从0开始
        //e为当前处理的元素
        //each循环，当前处理的元素移除所有的class，然后添加数组索引i的class
        $(".list li").each(function(i,e){
            $(e).removeClass().addClass(cArr[i]);
        })
        index--;
        if (index<0) {
            index=6;
        }
    }

//下一张
    function nextimg(){
        cArr.push(cArr[0]);
        cArr.shift();
        $(".list li").each(function(i,e){
            $(e).removeClass().addClass(cArr[i]);
        })
        index++;
        if (index>6) {
            index=0;
        }

    }

//点击class为p2的元素触发上一张照片的函数
    $(document).on("click",".p2",function(){
        previmg();
        return false;//返回一个false值，让a标签不跳转
    });

//点击class为p4的元素触发下一张照片的函数
    $(document).on("click",".p4",function(){
        nextimg();
        return false;
    });

//			鼠标移入box时清除定时器
    $(".box").mouseover(function(){
        clearInterval(timer);
    })

//			鼠标移出box时开始定时器
    $(".box").mouseleave(function(){
        timer=setInterval(nextimg,4000);
    })

//			进入页面自动开始定时器
    timer=setInterval(nextimg,4000);
    //select card
    $(function(){

        /*
         o1: 标签元素
         o2: 内容元素
         c : 标签元素显示用样式
         e : 触发事件 如 click, mouseover
         出现方式：show()无效果，fadeIn()淡入
         */
        function tab(o1,o2,c,e){
            o1.each(function(i){
                $(this).bind(e,function(){
                    o2.hide().eq(i).show();
                    o1.removeClass(c);
                    $(this).addClass(c);
                })
                if ($(this).hasClass(c)) {
                    $(this).addClass(c);
                    o2.hide().eq(i).show();
                }
            })
        }
//调用函数
        tab($(".navTabs li"),$(".tabPane"),"active","click");

    });

});
