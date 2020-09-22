var windowWidth;
var windowHeight;

$(function(){
	resposiveSize();
	$(".btn_navAllOpen").click(function(e){
		e.preventDefault();
		$(this).toggleClass("active");
		$("#navAll").toggleClass("active");
	});
	
	$("#btn_logout").click(function(e){
		e.preventDefault();
		if (!confirm("로그아웃하시겠습니까?")) {
			return;
		}
		location.href = "/site/demo/login/Logout.do";
	});
	$("#btn_login").click(function(e){
		e.preventDefault();
		if (!confirm("로그인하시겠습니까?")) {
			return;
		}
		location.href = "/site/demo/login/Login.do";
	});
	
	$("#btn_mod").click(function(e){
		e.preventDefault();
		if (!confirm("마이페이지이동 하시겠습니까?")) {
			return;
		}
		location.href = "/site/demo/user/UserPwCheck.do";
	});

	$("#gnb > ul > li").each(function(){
		$(this).addClass("item"+($(this).index()+1));
	});
	$("#gnb > ul > li > a").mouseover(function(){
		$("#gnb > ul > li").removeClass("active");
		$(this).parent().addClass("active");
	});
	$("#gnb").mouseleave(function(){
		$("#gnb > ul > li").removeClass("active");
	});
	$("#subHeader #subNav li.active a").click(function(e){
		if(windowWidth < 1200){
			e.preventDefault();
			$("#subHeader #subNav").toggleClass("active");
		}else{
		}
	});
	$("#subNav").each(function(){
		$(this).addClass("cols"+($(this).children("ul").children("li").length));
	});

	$(".tabWrap").each(function(){
		if($(this).find(".tabList li.active").length == 0){
			$(this).find(".tabList li").eq(0).addClass("active");
			$(this).find(".tabContent").eq(0).addClass("active");
		}
	});
	$(".tabList li a").click(function(e){
		e.preventDefault();
		if(!$(this).parent().hasClass("active")){
			var tabIndex = $(this).parent().index();
			$(this).parent().parent().children("li").removeClass("active");
			$(this).parent().addClass("active");
			$(this).parent().parent().parent().find(".tabContent").removeClass("active");
			$(this).parent().parent().parent().find(".tabContent").eq(tabIndex).addClass("active");
		}
	});

	$("ul.projectList").each(function(){
		$(this).addClass("active");
		if($(this).parents("#wrap.main").length == 0){
			$(this).children("li").children("a").click(function(e){
				e.preventDefault();
				var param = $(this).attr("data-param");
				$.ajax({
					type:"POST",
					url:"/content/project/project_view.asp",
					dataType:"text",
					data:{"param":param},
					timeout:10000,
					success:function(data){
						$(".projectDetail").children(".modalContent").html(unescape(data));
						$("html, body").css({"height":windowHeight+"px","overflow":"hidden"});
						$("#overlay").show();
						$(".projectDetail").fadeIn(200);
					},
					error: function(response){
					}
				});
			});
		}
	});
	if($("ul.projectList").length > 0){
		if(window.location.search.indexOf("no=") != -1){
			var no = parseInt(window.location.search.replace("?","").split("no=")[1].split("&")[0]);
			$.ajax({
				type:"POST",
				url:"/content/project/project_view.asp",
				dataType:"text",
				data:{"param":no},
				timeout:10000,
				success:function(data){
					$(".projectDetail").children(".modalContent").html(unescape(data));
					$("html, body").css({"height":windowHeight+"px","overflow":"hidden"});
					$("#overlay").show();
					$(".projectDetail").fadeIn(200);
				},
				error: function(response){
				}
			});
		}
	}

	$(document).on("click",".btn_modalClose",function(e){
		e.preventDefault();
		$("html,body").css({"height":"auto","overflow":"inherit"});
		$(".modalWrap, #overlay").fadeOut(200);
	});

	/*
	if($(".location").length > 0){
		$(".location .tabList li").click(function(e){
			var mapIndex = $(this).index();
			var mapId = 'map'+(mapIndex+1);
			setTimeout(function(){
				var mapPosition;
				if(mapIndex == 0){
					mapPosition = new naver.maps.LatLng(37.3699277195, 126.9573305526);
				}else if(mapIndex == 1){
					mapPosition = new naver.maps.LatLng(37.4865534779, 126.89616164766);
				}else if(mapIndex == 2){
					mapPosition = new naver.maps.LatLng(36.3680000665, 127.3825808183);
				}
				var map = new naver.maps.Map(mapId, {
					center: mapPosition,
					zoom: 10
				});
				var marker = new naver.maps.Marker({
					position: mapPosition,
					map: map
				});
			},10);
		});

		var mapPosition = new naver.maps.LatLng(37.3699277195, 126.9573305526);
		var map = new naver.maps.Map('map1', {
			center: mapPosition,
			zoom: 10
		});
		var marker = new naver.maps.Marker({
			position: mapPosition,
			map: map
		});
	}
	*/

	$(".useActionMotion").each(function(){
		$(this).addClass("active");
	});

	$(".useZoom").each(function(){
		$(this).append('<a href="/content/zoom.asp?src='+$(this).children().attr("src")+'" target="_blank" title="��â����">�̹��� Ȯ�뺸��</a>');
	});

	$("ul.portfolioList img").load(function(){
		cardUIUse($("ul.portfolioList"));
	});
	setTimeout(function(){
		if(!$("ul.portfolioList").hasClass("active")){
			cardUIUse($("ul.portfolioList"));
		}
	},1000);

	$(".btn_tel").click(function(e){
		if(isMobile() && windowWidth < 1200){
		}else{
			e.preventDefault();
		}
	});

	$(".btn_scrollTop").click(function(e){
		$("html, body").animate({
			scrollTop:0
		},500);
	});
});

$(window).resize(function(){
	resposiveSize();
	
	setTimeout(function(){
		cardUIUse($("ul.portfolioList"));
	},100);
});

function resposiveSize(){
	windowWidth = $(window).width();
	windowHeight = $(window).height();
	if(/*isMobile() && */windowWidth < 1200){
		if(windowWidth >= 768){
			deviceType = "mobile tablet";
		}else{
			deviceType = "mobile phone";
		}
	}else{
		deviceType = "desktop";		
	}
	$('html').removeAttr('class').addClass(deviceType);
}
function getBrowser(){
    var ua=navigator.userAgent,tem,M=ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
    if(/trident/i.test(M[1])){
        tem=/\brv[ :]+(\d+)/g.exec(ua) || [];
        return {name:'IE',version:(tem[1]||'')};
        }
    if(M[1]==='Chrome'){
        tem=ua.match(/\bOPR\/(\d+)/)
        if(tem!=null)   {return {name:'Opera', version:tem[1]};}
        }
    M=M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];
    if((tem=ua.match(/version\/(\d+)/i))!=null) {M.splice(1,1,tem[1]);}
    return {
      name: M[0],
      version: M[1]
    };
}
function isMobile(){
	if(window.navigator.userAgent.match(/Mobile/i)
		|| window.navigator.userAgent.match(/iPhone/i)
		|| window.navigator.userAgent.match(/iPod/i)
		|| window.navigator.userAgent.match(/IEMobile/i)
		|| window.navigator.userAgent.match(/Windows Phone/i)
		|| window.navigator.userAgent.match(/Android/i)
		|| window.navigator.userAgent.match(/BlackBerry/i)
		|| window.navigator.userAgent.match(/webOS/i)){
		//console.log("Maybe Phone!");
	}
	if(window.navigator.userAgent.match(/Tablet/i)
		|| window.navigator.userAgent.match(/iPad/i)
		|| window.navigator.userAgent.match(/Nexus 7/i)
		|| window.navigator.userAgent.match(/Nexus 10/i)
		|| window.navigator.userAgent.match(/KFAPWI/i)){
		//console.log("Maybe Tablet!");
	}
	if(window.navigator.userAgent.match(/Mobile/i)
		|| window.navigator.userAgent.match(/iPhone/i)
		|| window.navigator.userAgent.match(/iPod/i)
		|| window.navigator.userAgent.match(/IEMobile/i)
		|| window.navigator.userAgent.match(/Windows Phone/i)
		|| window.navigator.userAgent.match(/Android/i)
		|| window.navigator.userAgent.match(/BlackBerry/i)
		|| window.navigator.userAgent.match(/webOS/i)
		|| window.navigator.userAgent.match(/Tablet/i)
		|| window.navigator.userAgent.match(/iPad/i)
		|| window.navigator.userAgent.match(/Nexus 7/i)
		|| window.navigator.userAgent.match(/Nexus 10/i)
		|| window.navigator.userAgent.match(/KFAPWI/i)){
		//console.log("Maybe Mobile!");
		return true;
	}else{
		//console.log("Maybe Desktop!");
		return false;
	}
}

function cardUIUse(cardWrap){
	if(cardWrap.length > 0){
		cardWrap.removeClass("active");
		cardWrap.children().removeAttr("style");
		var space = 32;
		var cols = 4;
		if(isMobile() && windowWidth < 1200){
			if(windowWidth >= 640){
				space = 20;
				cols = 3;
			}else{
				space = 10;
				cols = 2;
			}
		}
		var liWidth = (cardWrap.width() - (space * (cols - 1))) / cols;
		var cardItem = cardWrap.children();
		var itemRow = 0;
		var itemTop = [];
		var itemLeft = 0;
		cardItem.width(liWidth);
		for(i=0; i<cardItem.length; i++){
			var cardTarget = cardItem.eq(i);
			if(!itemTop[itemRow]){
				itemTop[itemRow] = 0;
			}
			if(i%cols == 0){
				itemRow = 0;
				itemLeft = 0;
			}
			cardTarget.css({
				"top":itemTop[itemRow]+"px",
				"left":itemLeft+"px",
			});
			itemTop[itemRow] += (cardTarget.height() + space);
			itemLeft += (cardTarget.width() + space);
			itemRow++;
		}
		var ulHeight = itemTop.slice(0).sort(function(a,b){a<b})[0];
		cardWrap.height(ulHeight);
		cardWrap.addClass("active");
	}
}