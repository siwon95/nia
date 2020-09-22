var projectSlider;

$(function(){
	sectionScrolled();
	//sliderInit();

	$(".sectionOption ul li a").click(function(e){
		e.preventDefault();
		var sectionId = $(this).attr("href");
		sectionScrollMove(sectionId);
	});

	$(".btn_nextSection").click(function(e){
		e.preventDefault();
		var sectionId = $(this).attr("href");
		sectionScrollMove(sectionId);
	});
});

$(window).resize(function(){
	//sliderInit();
});

function sliderInit(){
	if(windowWidth < 480){
		if($("#mainSection3 .bx-wrapper").length == 0){
			projectSlider = $('#mainSection3 .projectList').bxSlider({
				auto:false,
				autoControls:false,
				touchEnabled:false,
				slideWidth:((windowWidth-20)/2),
				moveSlides:2,
				minSlides:2,
				maxSlides:2
			});
		}
	}else{
		if($("#mainSection3 .bx-wrapper").length > 0 && projectSlider){
			projectSlider.destroySlider();
		}
	}
}

$(window).scroll(function(){
	sectionScrolled();
});

function sectionScrolled(){
	var _scrollTop = $(window).scrollTop();
	var targetNo;
	$(".sectionOption ul li").removeClass("active");
	for(i=0; i<5; i++){
		var targetItems = $(".mainContent > section");
		var targetItem = targetItems.eq(i);
		if(targetItem.length > 0){
			var targetTop = targetItem.offset().top - 200;
			if(_scrollTop > targetTop){
				targetNo = i;
			}
		}
	}
	$(".sectionOption ul li").eq(targetNo).addClass("active");
	if(_scrollTop > 200){
		$("#wrap.main").addClass("scrolled");
	}else{
		$("#wrap.main").removeClass("scrolled");
	}
}
function sectionScrollMove(sectionId){
	var _section = $(sectionId);
	var targetTop = _section.offset().top - 80;
	$("html, body").animate({
		scrollTop:targetTop
	},500);
}