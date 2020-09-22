/* Responsive Event */
var windowWidth;
var windowHeight;
function responsiveAction(){
	windowWidth = $(window).width();
	windowHeight = $(window).height();
	if(isMobile() && windowWidth < 1200){
		if(windowWidth >= 768){
			deviceType = "mobile tablet";
		}else{
			deviceType = "mobile phone";
		}
	}else{
		deviceType = "desktop";		
	}
	$('html').removeClass('desktop').removeClass('mobile').removeClass('tablet').removeClass('phone').addClass(deviceType);
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

/* Page Event */
function pageEvent(){
	contentSize();
	useDatepicker();
	useFileBtn();
	useTab();
	useWordLength();
}
function contentSize(){
}
function useWordLength(){ //텍스트입력박스의 글자길이 제공
	$("textarea").each(function(){
		if($(this).attr("data-limitByte")){
			var textName = "none";
			if($(this).attr("name")){
				textName = $(this).attr("name");
			}
			limitByte = parseInt($(this).attr("data-limitByte"));
			if($("wordCount_"+textName).length == 0){
				$(this).after('<span id="wordCount_'+textName+'" class="wordCount"><b>0</b> / '+limitByte+' Byte</span>');
			}
			$(this).keyup(function(){
				useWordLengthDo($(this));
			});
			useWordLengthDo($(this));
		}
    });
}
function useWordLengthDo(obj){
	textName = obj.attr("name");
	limitByte = parseInt(obj.attr("data-limitByte"));
	var totalByte = 0;
	var limitLength = 0;
	var message = obj.val();
	for(var i =0; i < message.length; i++){
		var currentByte = message.charCodeAt(i);
		if(currentByte > 128) totalByte += 2;
		else totalByte++;
		if(totalByte > limitByte){
			limitLength = i;
			obj.val(message.substring(0,limitLength));
			totalByte = limitByte;
			break;
			return;
		}
	}
	$("#wordCount_"+textName+" b").text(totalByte);
}
function useDatepicker(){ //날짜입력박스
	var holidayData = [
		{'mmdd':'1-1','title':'신정'},
		{'mmdd':'3-1','title':'3.1절'},
		{'mmdd':'5-5','title':'어린이날'},
		{'mmdd':'5-10','title':'석가탄신일'},
		{'mmdd':'6-6','title':'현충일'},
		{'mmdd':'8-15','title':'광복절'},
		{'mmdd':'10-3','title':'개천절'},
		{'mmdd':'10-9','title':'한글날'},
		{'mmdd':'12-25','title':'크리스마스'}
	];

	$(".useDatepicker").each(function(){ //날짜(일)입력박스
		if(!$(this).hasClass(".hasDatepicker")){
			var minDate = $(this).attr("data-minDate");
			var maxDate = $(this).attr("data-maxDate");
			var dateFormat = "yy-mm-dd";
			if($(this).attr("data-format")){
				dateFormat = $(this).attr("data-format");
			}
			$(this).css({"width":"105px"}).datepicker({
				prevText: '이전 달',
				nextText: '다음 달',
				monthNames: ['01','02','03','04','05','06','07','08','09','10','11','12'],
				monthNamesShort: ['01','02','03','04','05','06','07','08','09','10','11','12'],
				dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
				dayNamesShort: ['일','월','화','수','목','금','토'],
				dayNamesMin: ['일','월','화','수','목','금','토'],
				dateFormat: dateFormat,
				showMonthAfterYear: true,
				yearSuffix: '&nbsp;/',
				minDate: minDate,
				maxDate: maxDate,
				beforeShowDay: function(date){
					var holidayCheck = false;
					var mmdd = (date.getMonth() + 1)+"-"+date.getDate();
					for(var i=0; i<holidayData.length; i++){
						if(holidayData[i].mmdd == mmdd){
							holidayCheck = true;
							return [true, "date-holiday", holidayData[i].title];
							break;
						}
					}
					if(holidayCheck == false){
						return [true, ""];
					}
				},
				onSelect: function(selectedDate){
				},
				onClose: function(selectedDate){
					if($(this).hasClass("dateFrom")) {
						if(selectedDate != "" && $(this).parent().children(".dateTo").val() != ""){
							if(selectedDate >= $(this).parent().children(".dateTo").val()){
								alert("시작날짜는 종료날짜보다 작아야 합니다.");
								$(this).val("");
								return;
							}
						}
					}else if($(this).hasClass("dateTo")) {
						if(selectedDate != "" && $(this).parent().children(".dataFrom").val() != ""){
							if($(this).parent().children(".dateFrom").val() >= selectedDate){
								alert("종료날짜는 시작날짜보다 커야 합니다.");
								$(this).val("");
								return;
							}
						}
					}
				}
			});
		}
	});
	$(".useMonthpicker").each(function(){ //날짜(월)입력박스
		if(!$(this).hasClass(".hasDatepicker")){
			$(this).monthpicker({
				showOn: "focus",
				monthNames: ['1월','2월','3월','4월','5월','6월', '7월','8월','9월','10월','11월','12월'],
				monthNamesShort: ['1월','2월','3월','4월','5월','6월', '7월','8월','9월','10월','11월','12월'],
				changeYear: false,
				yearRange: 'c-2:c+2',
				dateFormat: 'yy-mm',
				onSelect: function(){	
				},
				onClose: function(selectedMonth){
					if($(this).hasClass("dateFrom")) {
						if(selectedMonth != "" && $(this).parent().children(".dateTo").val() != ""){
							if(selectedMonth > $(this).parent().children(".dateTo").val()){
								//inputCaptionOpen($("#monthTo"), "시작월은 종료월보다 작아야 합니다.");
								alert("시작월은 종료월보다 작아야 합니다.");
								$("#monthFrom").val("");
								return;
							}
						}
					}else if($(this).hasClass("dateTo")) {
						if(selectedMonth != "" && $(this).parent().children(".dataFrom").val() != ""){
							if($(this).parent().children(".dateFrom").val() > selectedMonth){
								//inputCaptionOpen($("#monthTo"), "종료월은 시작월보다 커야 합니다.");
								alert("종료월은 시작월보다 커야 합니다.");
								$("#monthTo").val("");
								return;
							}
						}
					}
				}
			});
		}
	});
}
function useFileBtn(){ //파일등록 버튼 생성
	$("input.fileBtn").each(function(){
		if($(this).css("display") != "none"){
			var file_name = $(this).attr("id");
			var file_class = $(this).attr("class").replace("fileBtn","");
			$(this).after('<span id="for_'+file_name+'"><input type="text" class="'+file_class+'" value="" title="사진"> <a href="#" class="btn_inline for_fileBtn">찾아보기</a></span>');
			$(this).hide();
			$(this).change(function(){
				$("#for_"+file_name+" input[type='text']").val($(this).val());
			});
			$("#for_"+file_name+" .for_fileBtn").click(function(e){
				e.preventDefault();
				var id = $(this).parent().attr("id").replace("for_","");
				$("#"+id).click();
			});
		}
	});
}
function useTab(){ //탭 관련 기본설정
	$(".tabBar").each(function(){
	    if(!$(this).hasClass("eventUse")){
	        if(!$(this).hasClass("notUsed")){
	            var tabBar = $(this);
	            if(tabBar.find(".active").length == 0){
	                tabBar.find("li").eq(0).not(".notUsed").addClass("active");
	                tabBar.siblings(".tabPage").eq(0).addClass("active");
	            }
	        }
            
            $(this).find("li a").on("click",function(e){
                var tabBar = $(this).parent().parent();
                var tabLi = $(this).parent();
                var tabLiAll = $(this).parent().parent().children("li");
                var tabNo = tabLi.index();
                var tabPageAll = tabBar.parent().children(".tabPage");
                var tabPage = tabPageAll.eq(tabNo);
                if(!tabBar.hasClass("notUsed")){
                    if(!tabPage.hasClass("active") && tabPage.length > 0){
                        e.preventDefault();
                        tabPageAll.removeClass("active");
                        tabPage.addClass("active");
                        tabLiAll.removeClass("active");
                        tabLi.addClass("active");
                        //pageEvent(); //Ajax탭 사용여부에 따라 사용
                    }
                }
            });
            
	    }
	});
}

/* Tree Location */
function treeLocation(){
	if(window.location.search.indexOf("treeLocationId") != -1){
		$("#loading").show();
		var treeTargetId = window.location.search.split("treeLocationId=")[1].split("&")[0];
		var intervalCount = 0;
		treeInterval = setInterval(function(){
			if(intervalCount > 5){
				clearInterval(treeInterval);
				$("#loading").hide();
			}else{
				if($(".jstree").length > 0){
					var treeObjId = $(".jstree").attr("id");
					$(".jstree-clicked").removeClass("jstree-clicked");
					$("#"+treeObjId).jstree("open_node", treeTargetId);
					$("#"+treeObjId).jstree("select_node", treeTargetId);
					clearInterval(treeInterval);
					$("#loading").hide();
				}
			}
			intervalCount++;
		}, 500);
	}
}

/* Modal */
function modalOpen(id){
	$(window).scrollTop(0);
	$("#overlay").show();
	$(id).show().addClass("active");
	pageEvent();
}
function modalClose(){
	$(".modalWrap.useAjax").remove();
	$(".modalWrap").removeClass("active").hide();
	$("#overlay").hide();
}

/* Ajax */
function ajaxModal(id,url,parameter,size){
	modalClose();
	$("#loading").show();
	if($("#"+id).length > 0){
		$("#"+id).remove();
	}
    $.ajax({
        type:"POST",
        url:url,
        dataType:"text",
        data:parameter,
        timeout:10000,
        success:function(data){
            var modalHtml = '<div id="'+id+'" class="modalWrap useAjax '+size+'"></div>';
            $("body").append(modalHtml);
            $("#"+id).html(data);
            modalOpen("#"+id);
            pageEvent();
        	$("#loading").hide();
        },
        error: function(response){
            //ajaxError(response);
        	console.log(response);
        	$("#loading").hide();
        }
    });
}
function ajaxActionMessage(ajaxUrl,ajaxParam,ajaxMessage,ajaxRefresh,ajaxOption){
	$("#loading").show();
	var _dataType = "json";
	if(ajaxOption){
		_dataType = ajaxOption.dataType;
	}
    $.ajax({
        type:"POST",
        url:ajaxUrl,
        dataType:_dataType,
        //contentType: "application/json",
        data:ajaxParam,
        timeout:10000,
        success:function(data){
        	if(ajaxMessage && ajaxMessage != ''){
            	alert(ajaxMessage);
        	}else if(data.message && data.message != ''){
            	alert(data.message);
        	}
			if(ajaxRefresh == true){
        		window.location.reload();
        	}
        	$("#loading").hide();
        },
        error: function(response){
            //ajaxError(response);
        	console.log(response);
        	$("#loading").hide();
        }
    });
}
function ajaxActionTreeMessage(ajaxUrl,ajaxParam,ajaxMessage,ajaxRefresh,ajaxOption){
	var _dataType = "json";
	if(ajaxOption){
		_dataType = ajaxOption.dataType;
	}
    $.ajax({
        type:"POST",
        url:ajaxUrl,
        dataType:_dataType,
        contentType: "application/json",
        data:ajaxParam,
        timeout:10000,
        success:function(data){
        	if(ajaxMessage && ajaxMessage != ''){
            	alert(ajaxMessage);
        	}else if(data.message && data.message != ''){
            	alert(data.message);
        	}
			if(ajaxRefresh == true){
        		window.location.reload();
        	}
        },
        error: function(response){
            //ajaxError(response);
        	console.log(response);
        }
    });
}
function ajaxAction(ajaxUrl,ajaxParam,ajaxOption){
	var _dataType = "json";
	var _success;
	var _fail;
	if(ajaxOption){
		_dataType = ajaxOption.dataType;
		_success = ajaxOption.success;
		_fail = ajaxOption.fail;
	}
    $.ajax({
        type:"POST",
        url:ajaxUrl,
        dataType:_dataType,
        data:ajaxParam,
        timeout:10000,
        success:function(data){
        	if(_success){
        		_success(data);
        	}
        },
        error: function(response){
        	if(_fail){
        		_fail();
        	}
            //ajaxError(response);
        	console.log(response);
        }
    });
}
function resultMessage(messageContent){
	alert(messageContent);
}

/* Form */
function formRequiredCheck(f){
	var requiredItem = $(f).find("input.required, select.required, textarea.required");
	var requiredMessage = "";
	for(i=0; i<requiredItem.length; i++){
		if(!requiredItem.eq(i).val()){
			var itemId = requiredItem.eq(i).attr("id");
			var itemTitle;;
			if(requiredItem.eq(i).attr("title")){
				itemTitle = requiredItem.eq(i).attr("title");
			}else{
				itemTitle = $("label[for='"+itemId+"']").text();
			}
			if(itemTitle && itemTitle != ""){
				if(requiredMessage != ""){
					requiredMessage += "\n";
				}
				requiredMessage += itemTitle+"은(는) 필수 입력입니다.";
			}
		}
	}
	if(requiredMessage == ""){
		return true;
	}else{
		alert(requiredMessage);
		return false;
	}
}

/* Search and Paging */
var searchFunc;
function doSearch(f){
	if(f.pageIndex){
		if(targetPage){
			f.pageIndex.value = targetPage;
		}else{
			f.pageIndex.value = 1;
		}
	}
	
	if(searchFunc){
		var searchFuncResult = searchFunc(f);
		if(searchFuncResult == false){
			return false;
		}
	}
	return true;
}
function doPageMove(pageIndex){
	targetPage = pageIndex;
	$(".searchListPage").submit();
}

/* Etc */
function copyToClipboard(id) {
	if(navigator.userAgent.toLowerCase().indexOf("msie") != -1){
		window.clipboardData.setData("Text", $("#"+id).val());
	}else{
		document.getElementById(id).select();
		document.execCommand("Copy");
	}
	alert("복사되었습니다.");
}
function doCommonPop(form, target, url, width, height){
	var winPop = window.open("", target, "width="+width+",height="+height+",scrollbars=yes");
	form.action = url;
	form.method = "post";
	form.target = target;
	form.submit();
	winPop.focus();
}
function doJusoPop(form){
	var offset = "http://" + location.host + "/cmm/Juso_Set.do";
	var winUrl = "http://www.juso.go.kr/addrlink/addrLinkUrl.do";
	doCommonPop(form, "JusoPop", winUrl, 570, 420);
}

/* CK editor */
var editorLib = {
	"ck":{
		"insert":function(editorId, editorAddHtml){
			var oEditor = CKEDITOR.instances[editorId];
			if(oEditor.mode == 'wysiwyg'){
				oEditor.insertHtml(editorAddHtml);
			}else{
				alert('위지윅 모드여야 가능합니다.');	
			}
		}
	},
	"cross":{
		"insert":function(editorId, editorAddHtml){
			CrossEditor.InsertValue(1, editorAddHtml);
		}
	}
};

/**
 * 설 명 : 폼의 입력값에 대해서 HashMap Object로 반환해주는 함수.
 * 입 력 : var params = $("#form").serializeObject();
 * 결 과 : params["폼안의 name"]
 */
$.fn.serializeObject = function() {
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || "");
       } else {
           o[this.name] = this.value || "";
       }
   });
   return o;
};