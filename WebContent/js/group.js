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




// 上傳預覽
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#blah').attr('src', e.target.result);
		}

		reader.readAsDataURL(input.files[0]);
	}
}
$("#imgInp").change(function() {
	readURL(this);
});

// 新增、修改日曆
$.datetimepicker.setLocale('zh');

$('#f_date1').datetimepicker({
	format : 'Y-m-d H:00:00',
	timepicker : true,
	step : 60,
	minDate : new Date(),
});

$('#f_date2').datetimepicker(
		{
			format : 'Y-m-d H:00:00',
			timepicker : true,
			step : 60,
			onShow : function(ct) {
				this.setOptions({
					minDate : jQuery('#f_date1').val() ? jQuery('#f_date1')
							.val() : false
				})
			},
		});

$('#f_date3').datetimepicker(
		{
			format : 'Y-m-d H:00:00',
			timepicker : true,
			step : 60,
			onShow : function(ct) {
				this.setOptions({
					minDate : new Date(),
					maxDate : jQuery('#f_date1').val() ? jQuery('#f_date1')
							.val() : false
				})
			},
		});

// 指定縣市選單預設值
new TwCitySelector({
	el : '.city-selector-set',
	elCounty : '.county', // 在 el 裡查找 element
	elDistrict : '.district', // 在 el 裡查找 element
	elZipcode : '.zipcode' // 在 el 裡查找 element
});
