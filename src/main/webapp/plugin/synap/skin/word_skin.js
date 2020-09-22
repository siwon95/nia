// word_skin.js
console.log( 'word_skin.js' );

localSynap = (function() {

	var member = {

		getViewFilePath: function(){
			var viewFileName = localSynap.getResultDir() + localSynap.getFileName();
			if (localSynap.isSingleLayout()){
				
			}else{
				viewFileName = viewFileName + '.view';
			}
			if (BROWSER.adjustXhtml()){
				 viewFileName = viewFileName + '.xhtml';
			}else{
				 viewFileName = viewFileName + '.htm';
			}
			return encodeURI(viewFileName);
		}
	}

	var word = {

		sheetViewFrame : null,

		skinReadyFunc: function() {
			if( BROWSER.isMobile() ){
				word.skinReadyMobileFunc();
			}else{
				word.skinReadyDesktopFunc();
			}
			containerSizeAdjust();
			setFullScreenClick();
			if (!localSynap.isAllowCopy()) {
				localSynap.killCopyHtml();
			}
		},

		skinReadyDesktopFunc: function() {
			var viewFileName = member.getViewFilePath();
			// 아래한글일때 아이콘 교체
			if (localSynap.getFileType() === 'hwp2k' || localSynap.getFileType() === 'hwp97'){
				$('#headTitle > img').attr('src','image/header/icon_format_HWP.png');
			}else if( localSynap.getFileType() === 'txt' ){
				$('#headTitle > img').attr('src', 'image/header/icon_format_TXT.png');
			}else{
				$('#headTitle > img').attr('src', 'image/header/icon_format_DOC.png');
			}
			$('#headTitle > img').show();
			
			setResizeHeaderTitle(); // #DEFECT-2580 title resize를 위해 호출
			// word 계열 resize동작이 하나밖에 없어서 임시 지정.
			window.onresize = setResizeHeaderTitle;
			
			var sheetViewFrame = document.getElementById('innerWrap');
			sheetViewFrame.contentWindow.location.replace(viewFileName);
			if (localSynap.properties.isRenderServer === true) {
				setDownloadButton(localSynap.downloadUrl);
			} else {
				setDownloadButton(localSynap.properties.xmlObj);
			}
			setPrintButton();
			docKeyboardControl();
		},

		skinReadyMobileFunc: function() {
			$('body').addClass('containBg').addClass('lt1');
			initMobile();
			
			window.onresize = localSynap.resize;
			var viewFileName = member.getViewFilePath();
			if (BROWSER.GRAPHIC.isNotIFrame()){
				localSynap.loadNoIframe();
			}else{
				var sheetViewFrame = document.getElementById('innerWrap');
				sheetViewFrame.contentWindow.location.replace(viewFileName);
				$(sheetViewFrame).height($(window).height());
			}
		},
		resize: function(){
			$('#innerWrap').css('height',  $(window).height() - $('#header').height());
		},
		
		getBookmarkPosition: function(){
			if (BROWSER.MOBILE.isIOS()) {
				return $(document).scrollTop();	
			} else {
				return $(document.getElementById('innerWrap').contentDocument).find('.containBg').scrollTop();
			}
		},

		moveBookmarkPosition: function(pos){
			if (BROWSER.MOBILE.isIOS()) {
				$(document).scrollTop(pos);
			} else {
				$(document.getElementById('innerWrap').contentDocument).find('.containBg').scrollTop(pos);
			}
		},

		getFileResultDir: function() {
			var viewFileName = "";
			viewFileName = localSynap.getResultDir() + localSynap.getFileName();
			viewFileName = viewFileName + '.files';
			return viewFileName;
		},
		
		loadNoIframe: function() {
			$('body').css('overflow','visible');
			var div = document.createElement('div');
			$(div).attr('id', 'content_body');
			$('#container').append(div);

			var div_hidden = document.createElement('div');
			$(div_hidden).attr('id', 'hidden_section');
			$('#container').append(div_hidden);

			var head = document.getElementsByTagName('head')[0];
			{
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.src = localSynap.getFileResultDir()+'/spin.min.js';
			head.appendChild(script);
			}
			{
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.src = localSynap.getFileResultDir()+'/canvg.js';
			head.appendChild(script);
			}
			{
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.src = localSynap.getFileResultDir()+'/convertCanvg.js';
			head.appendChild(script);
			}
			{
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.src = localSynap.getFileResultDir()+'/word_body.js';
			head.appendChild(script);
			}
			
			$.ajax({
				type: 'GET',
				url: member.getViewFilePath(),
				dataType: 'text',
				success: function(str){
					var nodeNames = str.split('<style type="text/css">');
					var head = document.getElementsByTagName('head')[0];
					var css_style = '';
					$.each(nodeNames, function (i, el){
						var endTag = el.split('</style>');
						if (endTag.length > 1){
							css_style += endTag[0];
						}
					});
					if (css_style!=''){
						var style_tag = document.createElement('style');
						style_tag.type = 'text/css';
						style_tag.textContent = css_style;
						head.appendChild(style_tag);
					}
				},
				error: function(err){
					eval(err.responseText);
				}
			});

			localSynap.contentReadyFunc = function() {
				console.log('contentReadyFunc skin');
			};
		}
	}
	return $.extend(localSynap, word);
})();

localSynap.getCurrentPage = function() {
	return 1;
}
localSynap.gotoHtmlPage = function(pageno) {

}

localSynap.killCopyHtml = function() {
	// common
	stopBrowserEvent(document.body);
	//innerWrap
	var innerWrap = document.getElementById('innerWrap');
	if(innerWrap) {
		innerWrap.onload = function(){
			var contentBody = innerWrap.contentDocument.getElementById('content_body');
			if(contentBody) {
				stopBrowserEvent(contentBody);
			}
		}
	}
};

// SKIN READY FUNC
$(document).ready(function() {
	if (typeof localSynap.skinReadyFunc == "function") {
		localSynap.skinReadyFunc();
	}
});

