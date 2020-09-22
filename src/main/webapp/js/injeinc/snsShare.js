var shareSite = "[...]";
var shareLogo = "...";
var shareKakaoKey = "...";
var shareTitle, shareTitleURI, shareUrlURI;

$(function(){
	snsShare.init();
});

var snsShare = {
	"init":function(){
		shareTitle = shareSite;
		if($("#subContent > #subTitle > h3").length > 0){
			shareTitle += " - "+$("#subContent > #subTitle > h3").text();
		}
		if($(".thumbView > .title").length > 0){
			shareTitle += " : "+$(".thumbView > .title").text();
		}
		shareTitleURI = encodeURIComponent(shareTitle);
		shareUrlURI = encodeURIComponent(location.href);

		$(".btn_snsShare").click(function(e){
			e.preventDefault();
			snsShare[$(this).attr("href").replace("#","")]();
		});
	},
	"kakao":function(){
		Kakao.init(shareKakaoKey); //카카오키
		Kakao.Link.sendTalkLink({ //카카오톡 버튼 생성
			image:{src:shareLogo, width:90, height:90},
			webLink:{text:shareTitle, url:location.href}
		});
	},
	"story":function(){
		window.open("https://story.kakao.com/share?url="+shareUrlURI, "story_open", "width=500,height=540,resizable=no");
	},
	"band":function(){
		window.open("http://www.band.us/plugin/share?body="+shareTitleURI+shareUrlURI+"&route="+shareUrlURI, "share_band", "width=410,height=540,resizable=no");
	},
	"blog":function(){
		window.open("http://share.naver.com/web/shareView.nhn?title="+shareTitleURI+"&url="+shareUrlURI, "share_blog", "width=500,height=500,resizable=yes");
	},
	"facebook":function(){
		window.open("http://www.facebook.com/sharer.php?t="+shareTitleURI+"&u="+shareUrlURI, "share_facebook", "width=500,height=300,resizable=no");
	},
	"twitter":function(){
		window.open("http://twitter.com/share?text="+shareTitleURI+"&url="+shareUrlURI, "share_twitter", "width=800,height=400,resizable=yes");
	},
	"google":function(){
		window.open("https://plus.google.com/share?url="+shareUrlURI, "share_google", "width=500,height=540,resizable=no");
	}
}