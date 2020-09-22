/* Var */
var targetPage;
var ajaxIng = Array();
var titleNavRepeat;

/* Event */
$(document).ready(function(){

	pageEvent();
	responsiveAction();
	treeLocation();

	$(".btn_gnbOpen").click(function(e){
		e.preventDefault();
		if($(this).hasClass("active")){
			$(".btn_gnbOpen, #gnb").removeClass("active");
			$(".btn_gnbOpen").focus();
		}else{
			$(".btn_gnbOpen, #gnb").addClass("active");
			if($("#gnb > ul > li.active").length == 0){
				$("#gnb a").eq(0).focus();
				$("#gnb > ul > li").eq(0).addClass("active");	
			}
		}
	});
	$("#gnb a").click(function(e){
		if($(this).parent().children("ul").length > 0){
			e.preventDefault();
			$(this).parent().siblings().removeClass("active");
			$(this).parent().addClass("active");
		}
	});
	
	$("#btn_logout").click(function(e){
		e.preventDefault();
		if (!confirm("로그아웃하시겠습니까?")) {
			return;
		}
		location.href = "/login/Logout.do";
	});
	
	$(".btn_alarmToggle").click(function(e){
		e.preventDefault();
		$(".btn_alarmToggle, .alarmList").toggleClass("active");
	});

	$(document).on("click",".btn_modalOpen",function(e){
		e.preventDefault();
		var targetModal = $(this).attr("href");
		modalOpen(targetModal);
	});
	$(document).on("click","#overlay, .btn_modalClose",function(e){
		e.preventDefault();
		if($(this).parents(".modalWrap").eq(0).hasClass("popup")){
			window.close();
		}else{
			modalClose();	
		}
	});
	$(document).on("click",".btn_asideToggle",function(e){
		e.preventDefault();
		$(".aside").toggleClass("active");
	});
	
	$(".previewBox a").mouseover(function(){
		$(this).parents(".previewBox").eq(0).addClass("active");
	});
	$(".previewBox").mouseleave(function(){
		$(this).removeClass("active");
	});
	
	$(document).on("click",".useConfirm",function(e){
		var  actionText = $(this).text();
		if(!confirm(actionText+" 하시겠습니까?")){
			e.preventDefault();
			return;
		}
	});
	
	$(".btn_windowPopup").click(function(e){
		e.preventDefault();
		var url = $(this).attr("data-url");
		/*if(url.indexOf("http://") == -1 && url.indexOf("https://") == -1){
			url = "http://"+url;
		}*/
		var option = $(this).attr("data-option");
		window.open(url, '미리보기', option);
	});
	
    titleNavRepeat = setInterval(function(){
    	if(ajaxIng.indexOf(true) == -1){
    	    $("#gnb li.active").each(function(){
    	    	$(this).parents("li").addClass("active");
    	    });
    	    titleNavInsert();
    		clearInterval(titleNavRepeat);
    	}
    }, 200);
    
    $(document).on("click", ".titleNav > ul > li > a", function(e){
		e.preventDefault();
		if(!$(this).parent().hasClass("active")){
			$(".titleNav > ul > li").removeClass("active");
			$(this).parent().addClass("active");
		}else{
			$(this).parent().removeClass("active");
		}
	});
    $(document).on("click", ".titleNav > ul > li > ul > li > a", function(e){
    	if($(this).attr("href") == "#" || $(this).attr("href") == ""){
    		e.preventDefault();
    		if($(this).siblings("ul").eq(0).find("a").eq(0).attr("href") != "#" && $(this).siblings("ul").eq(0).find("a").eq(0).attr("href") != ""){
    			location.href = $(this).siblings("ul").eq(0).find("a").eq(0).attr("href");
    		}else{
    			location.href = $(this).siblings("ul").eq(0).find("ul a").eq(0).attr("href");
    		}
    	}
	});
    
    /*영역외클릭*/
    $(document).on("click", function(e){
    	if(!$(".titleNav").has(e.target).length){
    		$(".titleNav > ul > li").removeClass("active");
    	}
    	if(!$("#header").has(e.target).length && $("#gnb").hasClass("active")){
			$(".btn_gnbOpen, #gnb").removeClass("active");
			$(".btn_gnbOpen").focus();
    	}
    });
});

function titleNavInsert(){
    var gnbDepth1 = $("#gnb > ul").clone();
    gnbDepth1.find("script").remove();
    gnbDepth1.find("img").remove();
    var gnbDepth1A = gnbDepth1.find("li.active").eq(0).children("a");
    $(".titleNav > ul").append('<li class="depth1"></li>');
    $(".titleNav .depth1").append(gnbDepth1A);
    $(".titleNav .depth1").append(gnbDepth1);

    var gnbDepth2 = $("#gnb > ul > li.active > ul").clone();
    if(gnbDepth2.find("li").length > 1){
        gnbDepth2.find("script").remove();
        gnbDepth2.find("img").remove();
        var gnbDepth2A = gnbDepth2.find("li.active").eq(0).children("a");
        $(".titleNav > ul").append('<li class="depth2"></li>');
        $(".titleNav .depth2").append(gnbDepth2A);
        $(".titleNav .depth2").append(gnbDepth2);
    }
    
    var gnbDepth3 = $("#gnb > ul > li.active > ul > li.active > ul").clone();
    if(gnbDepth3.find("li").length > 1){
        gnbDepth3.find("script").remove();
        gnbDepth3.find("img").remove();
        var gnbDepth3A = gnbDepth3.find("li.active").eq(0).children("a");
        $(".titleNav > ul").append('<li class="depth3"></li>');
        $(".titleNav .depth3").append(gnbDepth3A);
        $(".titleNav .depth3").append(gnbDepth3);
    }
}