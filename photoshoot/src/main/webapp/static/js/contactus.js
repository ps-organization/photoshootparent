$().ready(function () {


    $('#contact-submit').click(
        contactAjax
    )

});

function contactAjax() {
    console.log("ss");
    // $.post("/contact/contact", function (data) {
    //         console.log(data);
    //     }
    // )
}