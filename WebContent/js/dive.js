var navColor = $(".navbar");
var navItem = $(".navbar-nav .nav-item .nav-link");

var prevScrollpos = window.pageYOffset;
$(document).ready(function() {
    $(window).on("scroll", function() {
    var currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
        //   show
        document.getElementById("nav").style.top = "0";
        $(".navbar").addClass("nav_color");
        $(".navbar-nav .nav-item .nav-link").addClass("nav_link_color");
    } else { 
        //   hide
        document.getElementById("nav").style.top = "-100px";
    } 
    
    if(currentScrollPos === 0){
        $(".navbar").removeClass("nav_color");
        $(".navbar-nav .nav-item .nav-link").removeClass("nav_link_color");
    }
        prevScrollpos = currentScrollPos;
    })


    $("button.navbar-toggler").on("click", function(){
        $(this).closest("nav").toggleClass("-open");
    });
});
