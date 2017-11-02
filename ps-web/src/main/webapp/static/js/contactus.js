$().ready(function () {
});

function contactAjax() {
    $.ajax({
        url:"/contact/contact",
        type:"POST",
        data:$("#contact").serialize(),
        success:function(resut){
            console.log(resut);
        },
        error:function (resut) {
            console.log(resut);
        }
    });
    return false;
}
