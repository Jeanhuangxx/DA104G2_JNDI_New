$(document).ready(function () {
    $("ul.menu > li > a").click(function (e) {
        e.preventDefault();
        let el = e.target.nodeName;
        if (el != "A") {
            return;
        }
        // $(this).next().toggleClass("next-menu",3000);
        // $(this).parent().siblings().find("ul").removeClass("next-menu",3000);
        $(this).next().slideToggle(500);
        $(this).parent().siblings().find('ul').slideUp(500);
        
    });
    // copy right year update
    (function(){
        let year = new Date().getFullYear();
        $("#year").text(year);
    }());
})