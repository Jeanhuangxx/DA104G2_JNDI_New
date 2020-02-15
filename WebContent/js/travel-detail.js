var navBar = $("#navbar-example2");
var hdrHeight = $("header").height();


    $(window).scroll(function() {
        if( $(this).scrollTop() > hdrHeight + 100) {
          navBar.addClass("navScrolled");
        } else {
          navBar.removeClass("navScrolled");
        }
      });