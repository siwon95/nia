$(function(){
	/* 모바일 경우 */
	if ($(window).width() <= "768"){
		$("#allMenu>ul>li:first-child>div>a").addClass("on");
			var target = $("#allMenu li div ul");
			var target1 = $("#allMenu>ul>li>div>a");
			var target2 = $("#allMenu li div ul ul");
			var target3 = $("#allMenu>ul>li>div>ul>li>ul").parent().find(">a");
				target3.addClass("m_down");
			$("#allMenu li div a").click(function(e){
				var index = $("#allMenu li div>a").index(this);
				if ($(this).next().html() == undefined){}else{
					e.preventDefault();
					if ($(this).next().css("display") == "none"){				
						if (index != -1){
							$(this).next().show();
							target1.not($(this)).removeClass("on");
							$(this).addClass("on");
							target.not($(this).siblings()).hide();
						}else{
							$(this).next().slideDown();
							target3.not($(this)).addClass("m_down");
							$(this).removeClass("m_down");
							$(this).addClass("m_up");
							target2.not($(this).siblings()).slideUp();
						}				
					}
				}
		});
	}

	injeinc.init(); //페이지기본이벤트
	injeinc.modal(); //모달관련함수
	injeinc.slider(); //슬라이드관련함수
	$(window).scroll(function(){
		var scrollTop = $(this).scrollTop();
		var h = $("#header").height();
		var s_h = h - scrollTop;
		if (h <= scrollTop ){
			$(".top_search").css("top",0+"px");
		}else{
			$(".top_search").css("top",s_h+"px");
		}
	});
	
	$(".btn_loginformClose").click(function(){
		$("#overlay").hide();
		$(".member_login").removeClass("active");
	});

	/* 상단검색 */	
	$(".search_open").on("click",function(){
		$(".top_search").slideDown();
	});

	$(".btn_search_close").on("click",function(){
		$(".top_search").slideUp();
	});
	
	/* 상단메뉴 */
	var menu1 = $("#gnb>div>ul>li>a");
	$(menu1).on("mouseover focus",function(){
		var index = $(menu1).index(this);
		$("#gnb>div>ul>li.menu_"+index).addClass("on");
		$("#gnb>div>ul>li:not(.menu_"+index).removeClass("on");
	});
	
	$("#gnb>div>ul>li:last-child li:last-child>a").focusout(function(){
		$("#gnb>div>ul>li ul").hide();

	});

	$("#gnb>div>ul>li>div, #gnb").on("mouseleave" ,function(){
		$("#gnb>div>ul>li").removeClass("on");			
	});
	
	/* 전체메뉴 */
	//$("#allMenu>ul>li>div>ul").css("position","absolute");
	$(".all_mune_view").click(function(){
		if ($(window).width() <= "768"){
			$("#allMenu").animate({right: "0%"});
			//$("#allMenu>ul>li>div>ul").css("position","fixed");
		}else{			
			$("#allMenu").show();
		}
		$("#overlay").show();
	});

	$(".all_mune_close").click(function(){
		
		if ($(window).width() <= "768"){
			$("#allMenu").animate({right: "-100%"});
			//$("#allMenu>ul>li>div>ul").css("position","absolute");
		}else{
			$("#allMenu").hide();
		}
		
		$("#overlay").hide();
	});
	
	$("#overlay").click(function(){
		$("#allMenu").hide();
	});
	
	
	//공지 슬라이드 시작
	$(".gongi .gongi_start").click(function(){
		$(".gongi .gongi_stop").removeClass("active");
		$(this).addClass("active");
	});
	//공지 슬라이드 멈춤
	$(".gongi .gongi_stop").click(function(){
		$(".gongi .gongi_start").removeClass("active");
		$(this).addClass("active");
	});

	/* 배너 슬라이드 시작*/
	$(".useSlick_start").click(function(){	
		$(".useSlick_stop").removeClass("active");
		$(this).addClass("active");
		$(".useSlick ul").slick("slickPlay");
	});
	/* 배너 슬라이드 멈춤*/
	$(".useSlick_stop").click(function(){
		$(".useSlick_start").removeClass("active");
		$(this).addClass("active");
		$(".useSlick ul").slick("slickPause");
	});

	/* 관련법령 */
	$(".related>li>div").hide();
	$(".related>li>button").addClass("related_minus");
	var acroTarget = $(".related>li>button");
	$(".related>li>button").click(function(){
		if ($(this).parent().children(".related>li>div").css("display") == "none" ){
			$(this).parent().children(".related>li>div").slideDown();
			$(this).removeClass("related_minus");
			$(this).addClass("related_plus");
			$(this).find("em").addClass("on");

			acroTarget.not($(this)).parent().children(".related>li>div").slideUp();
			acroTarget.not($(this)).addClass("related_minus");
			acroTarget.not($(this)).find("em").removeClass("on");
		}else{
			$(this).parent().children(".related>li>div").slideUp();
			$(this).removeClass("related_plus");
			$(this).addClass("related_minus");			
			$(this).find("em").removeClass("on");
		}
	});

	/* 자주묻는질문 */
	$(".qna>li>div").hide();
	$(".qna>li>button").addClass("related_minus");
	var acroTarget = $(".qna>li>button");
	$(".qna>li>button").click(function(){
		if ($(this).parent().children(".qna>li>div").css("display") == "none" ){
			$(this).parent().children(".qna>li>div").slideDown();
			$(this).removeClass("related_minus");
			$(this).addClass("related_plus");
			acroTarget.not($(this)).parent().children(".qna>li>div").slideUp();
			acroTarget.not($(this)).addClass("related_minus");
		}else{
			$(this).parent().children(".qna>li>div").slideUp();
			$(this).removeClass("related_plus");
			$(this).addClass("related_minus");
		}
	});

	
	/* 라디오및 체크박스 체크할때 온 */
	$(".l_wrap").each(function(i){
		var $this = $(this);
		$this.children(":radio").on("focusin focusout", function() {
			$this.toggleClass("radio_focus");
		});	
	});
	$(".l_wrap :radio").each(function(i) {
		var $radio = $(this);
		_onChangeRadio($radio);
		$radio.change(function(){
			_onChangeRadio($radio);
			$(".l_wrap :radio[name='"+$radio.attr('name')+"']").not($radio).each(function() {
				var $otherRadio = $(this);
				_onChangeRadio($otherRadio);
			})
		})
	});	
	function _onChangeRadio($radio) {
		if($radio.is(":checked")) {	$radio.parent().addClass("radio_on"); } else { 	$radio.parent().removeClass("radio_on"); }
	}
	$(".c_wrap").each(function(i){
		var $this = $(this);
		$this.children(":checkbox").on("focusin focusout", function() {
			$this.toggleClass("checkbox_focus");
		});	
	});
	$(".c_wrap :checkbox").each(function() {
		var $checkbox = $(this);
		_onChangeCheckbox($checkbox);	
		$checkbox.change(function(){
			_onChangeCheckbox($checkbox);
		})
	});
	function _onChangeCheckbox($checkbox) {
		if($checkbox.is(":checked")) {	$checkbox.parent().addClass("checkbox_on"); } else { 	$checkbox.parent().removeClass("checkbox_on"); }
	}

   /* 20200831 서브 네비게이션 추가 */
   $(document).on("click", ".location > ol > li > a", function(e){
		e.preventDefault();
		if($(this).parent("li").hasClass("active")){
			$(".location > ol > li").removeClass("active");
		}else{
			$(".location > ol > li").removeClass("active");
			$(this).parent("li").addClass("active");
		};
		  
	});
   $(document).on("click", ".location > div > .sharing", function(e){
		e.preventDefault();
		$(this).parent("div").toggleClass("active");
	});
   $(".sharing_wrap a").click(function(){
        shareAct(this);
    });
});  
function shareAct(a){
  
    var snsCode = $(a).attr('id');
      
    
    /*var cUrl = "http://192.168.50.68:9081/site/DRP0000/main.do?cbIdx=1144";*/
    var cUrl = document.location.href;
    var testUrl = encodeURIComponent($(location).attr('href'));
        switch(snsCode){
        
	        case"iconFb":
        	//페이스북
        	cUrl = 'http://www.facebook.com/sharer/sharer.php?u='+cUrl;
        	
        	window.open(cUrl,'','width=600,height=300,top=100,left=100,scrollbars=yes');
        	
        	break;
          
            case"iconTw":
            //트위터
            cUrl = 'https://twitter.com/intent/tweet?text=페이지제목:&url='+cUrl;
            
            window.open(cUrl,'','width=600,height=300,top=100,left=100,scrollbars=yes');
            
            break;
  
            case"iconKs":
            
            /*cUrl = 'http://story.kakao.com/s/share?text=내용 테스트&url=' + testUrl;
            
            window.open(cUrl,'','width=600,height=300,top=100,left=100,scrollbars=yes');	*/

            //카카오스토리
            Kakao.init('2e2281473e8826a2595bd09ec1a16389');	
            Kakao.Story.share({
    		  url: testUrl,
    		  text: '내용 테스트 '
    		});
	    	
            break;
        }
    }

var clock = setInterval("time()",3000);	
	
function sstop(){
	clearInterval(clock);
}

function start(){
	clock = setInterval("time()",3000);
}

function pre(){
	sstop();
	$(".gongi li").first().animate({"margin-top":-50+"px"}, function(){
		$(".gongi ul").append($(".gongi>div>ul li").first().css("margin-top","0"));
	});
}
function next(){
	sstop();
	$(".gongi ul").prepend($(".gongi ul li").last().css({"margin-top":"-50px"}));
		$(".gongi li").first().animate({"margin-top":"0"});
}

function time(){		
	$(".gongi li").first().animate({"margin-top":-50+"px"}, function(){
		$(".gongi li").first().appendTo($(".gongi>div>ul"));
		$(".gongi li").last().css("margin-top","0");
	});
}

var default_Bxslider = {auto:false,autoControls:false,controls:true,pager:false,};
//20200901
var default_Slick = {
	autoplay:true,dots:false,infinite:true,slidesToShow:2,slidesToScroll:1,rows:3,pauseOnHover: true,
	responsive:[{
		breakpoint:1024,
		settings:{autoplay:true,slidesToShow:2}
	},
	{
		breakpoint:767,
		settings:{autoplay:true,slidesToShow:3,rows:2}
	},
	{
		breakpoint:480,
		settings:{autoplay:true,slidesToShow:2,rows:2}
	}]
};
var sliderItem = [];
var injeinc = {
	'init':function(){
		injeinc.tab();
	},
	'tab':function(){
		$(".tabContent").each(function(){
			var tabBar = $(".tabBar");
			var tabPage = $(".tabPage");
			if(!$(this).hasClass("notUsed")){
				if(tabBar.children("li.active").length == 0 && !tabBar.find("li").eq(0).children("a").hasClass("useLink")){
					injeinc.tabReset($(this));
					tabBar.children("li").eq(0).addClass("active");
					tabPage.eq(0).addClass("active");
				}
				tabBar.find("a").unbind().click(function(e){ //탭버튼 클릭이벤트
					if(!$(this).hasClass("useLink")){
						e.preventDefault();
						injeinc.tabReset($(this));
						$(this).parent().parent().siblings(".tabPage").eq($(this).parent().index()).addClass("active");
						$(this).parent().addClass("active");
					}
				}).keydown(function(e){
					if($(this).parent().hasClass("active") && e.keyCode == 9){
						var focusItem = injeinc.findFocusItem($(this).parents(".tabBar").eq(0).siblings(".tabPage.active"));
						if(focusItem.length == 0 || $(this).hasClass("useLink")){
							if($(this).parent().next().children("a").length > 0){
								e.preventDefault();
								$(this).parent().next().children("a").trigger("click").focus();
							}
						}else{
							e.preventDefault();
							focusItem.eq(0).focus();
						}
					}
				});
			}
		});
		$(".tabPage").each(function(){
			if(!$(this).parent(".tabContent").hasClass("notUsed")){
				var focusItem = injeinc.findFocusItem($(this)); //탭페이지 포커스 이동
				if(focusItem.length > 0){
					focusItem.last().unbind().keydown(function(e){
						var inTabPage = $(this).parents(".tabPage").eq(0);
						var inTabBar = inTabPage.siblings(".tabBar");
						var target = inTabBar.children("li.active").next();
						if(e.keyCode == 9 && target.length > 0){
							e.preventDefault();
							if(!target.children("a").hasClass("useLink")){
								target.children("a").trigger("click");
							}else{
								inTabBar.children("li").removeClass("active");
								target.addClass("active");
							}
							target.children("a").focus();
						}
					});
				}
			}
		});
	},
	'tabReset':function(tabItem){
		if(tabItem.hasClass("tabContent")){
			tabItem.children(".tabBar").find("li.active").removeClass("active");
			tabItem.children(".tabPage").removeClass("active");
		}else{
			tabItem.parents(".tabContent").eq(0).children(".tabBar").find("li.active").removeClass("active");
			tabItem.parents(".tabContent").eq(0).children(".tabPage").removeClass("active");
		}
	},
	'modal':function(){
		$(document).on("click",".btn_modalOpen",function(e){
			e.preventDefault();
			var targetModal = $(this).attr("href");
			injeinc.modalOpen(targetModal);
		});
		$(document).on("click","#overlay, .btn_modalClose",function(e){
			injeinc.modalClose();
		});
	},
	'modalOpen':function(id){
		$(window).scrollTop(0);
		$("#overlay").show();
		$(id).addClass("active");
		$(id).find(".btn_modalClose").eq(0).focus();
	},
	'modalClose':function(){
		var modalId = $(".modalWrap.active").attr("id");
		$(".modalWrap").removeClass("active");
		$("#overlay").hide();
		$("a.btn_modalOpen[href='#"+modalId+"']").focus();
	},
	'slider':function(){
		if($(".slider.useBx").length > 0){
			$(".slider.useBx").each(function(){
				injeinc.sliderBx($(this).children("ul"));
			});
		}
		if($(".slider.useSlick").length > 0){
			$(".slider.useSlick").each(function(){
				injeinc.sliderSlick($(this).children("ul"));
			});
		}
		$(".bx-viewport ul").each(function(){
			$(this).find("li a").focus(function(){
				//$(this).parents(".bx-wrapper").eq(0).find(".bx-stop").trigger("click");		
				var slideNo = $(this).parent().index();
				if(slideNo < $(this).parent().parent().children("li").length - 3){
					var sliderNo = parseInt($(this).parent().parent().attr("data-sliderNo"));
					sliderItem[sliderNo].goToSlide(slideNo);
				}
			});
		});
	},
	'sliderBx':function(ul, option){
		var sliderOption = default_Bxslider; 
		if(option) sliderOption = option;
		var sliderNo = sliderItem.length + 1;
		ul.attr("data-sliderNo",sliderNo);
		sliderItem[sliderNo] = ul.bxSlider(sliderOption);
	},
	'sliderSlick':function(ul, option){
		var sliderOption = default_Slick;
		if(option) sliderOption = option;
		ul.slick(sliderOption);
	},
	'findFocusItem':function(area){
		return area.find("input, select, textarea, button, a");
	}
	
};
/*form태그 onsubmit 2020.08.05 박종범*/
var targetPage;
var searchFunc;
var searchForm;
function doSearch(f){
	doSearchForm = f;
	if(f.pageIndex){
		if(targetPage){
			f.pageIndex.value = targetPage;
			targetPage = "";
		}else{
			f.pageIndex.value = 1;
		}
	}
	
	if(searchFunc){
		var searchFuncResult = searchFunc(f);
		if(searchFuncResult == false){
			return false;
		}
		searchFunc = "";
	}
	return true;
}

function doPageMove(bbsId, pageIndex){
	targetPage = pageIndex;
	$("#"+bbsId).submit();
}

function doContentPrint() {
	$( "#container" ).focus();
	window.print();
}

