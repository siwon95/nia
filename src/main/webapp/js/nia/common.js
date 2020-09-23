/* ==============================================================================================================
= v1.0 - 20190411
============================================================================================================== */

var default_Bxslider = {auto:true,autoControls:true,controls:true};
var default_Slick = {
	autoplay:true,dots:true,infinite:true,slidesToShow:4,slidesToScroll:1,
	responsive:[{
		breakpoint:1024,
		settings:{autoplay:true,slidesToShow:3,centerMode:true}
	},
	{
		breakpoint:767,
		settings:{autoplay:true,slidesToShow:1,centerMode:true}
	}]
};
var sliderItem = [];

$(function(){
	injeinc.init(); //페이지기본이벤트
	injeinc.modal(); //모달관련함수
	injeinc.slider(); //슬라이드관련함수
});

$(document).ready(function(){
	
	$("#gnb.second").on("mouseover focusin",function(){
		//$("#allMenu").addClass("active");
		$("#allMenu").show();
	});
	$("#gnb.second").on("mouseout focusout",function(){
		$("#allMenu").hide();
	});
	$("#gnb.third > ul > li").on("mouseover focusin",function(){
		$(this).children("div").addClass("active");
	});
	$("#gnb.third > ul > li").on("mouseout focusout",function(){
		$(".subMenu").removeClass("active");
	});
});
$(window).resize(function(){
});

/*Injeinc Libary*/
var injeinc = {
	'init':function(){
		injeinc.tab();
		injeinc.datepicker();
		injeinc.wordLength();
		injeinc.fileBtn();
		injeinc.ajaxLinkBox();

		injeinc.addInputHandler({input:$(".onlyNum"),dataType:"N"});
		injeinc.addInputHandler({input:$(".onlyEng"),dataType:"AP"});
		injeinc.addInputHandler({input:$(".onlyHan"),dataType:"HA"});
		injeinc.addInputHandler({input:$(".engNum"),dataType:"AN"});
	},
	'addInputHandler':function(conditions){
		var targetInput = conditions.input;
		var dataType = conditions.dataType;
		var eventType = conditions.eventType;
		if((!targetInput) || (!dataType)){
			throw {error:"NotEnoughArguments", errorMsg:"required argument is missing " +((!targetInput)?" target input element":" dataType")}
			return;
		}
		if((!eventType)){
			eventType = "keyup";
		}
		var handlerFunc = conditions.handler;
		if((!handlerFunc)){ 
			handlerFunc = function(event){
				var regEx = null;
				switch(dataType){
					case 'N': regEx = /[^0-9]/gi; break;
					case 'AP': regEx = /[^a-z]/gi; break;
					case 'AN': regEx = /[^a-z0-9]/gi; break;
					case 'HA': regEx = /[a-z0-9]/gi; break;
					default: return; break;
				}
				injeinc.remainOnlyTargetValue(regEx, targetInput, event);
			};
		}
		targetInput.on(eventType,handlerFunc);
		if(conditions.maxlength){
			targetInput.attr("maxlength",conditions.maxlength);
		}
	},
	'remainOnlyTargetValue':function(regEx, targetInput, event) {
		if((!(event.keyCode >= 34 && event.keyCode <= 40)) && event.keyCode != 16){
			var inputVal = targetInput.val();
			if(regEx.test(inputVal)){
				event.preventDefault ? event.preventDefault() : event.returnValue = false;
				targetInput.val(inputVal.replace(regEx,''));
			}
		}
	},
	'tab':function(){
		$(".tabContent").each(function(){
			var tabBar = $(this).children(".tabBar");
			var tabPage = $(this).children(".tabPage");
			if(!$(this).hasClass("notUsed")){
				if(!tabBar.hasClass("useTabBtn") && tabBar.children("li.active").length == 0 && !tabBar.children("li").eq(0).children("a").hasClass("useLink")){
					injeinc.tabReset($(this));
					tabBar.children("li").eq(0).addClass("active");
					tabPage.eq(0).addClass("active");
				}
				tabBar.children("li").children("a").unbind("click").click(function(e){ //탭버튼 클릭이벤트
					if(!$(this).hasClass("useLink")){
						e.preventDefault();
						injeinc.tabReset($(this));
						$(this).parent().parent().siblings(".tabPage").eq($(this).parent().index()).addClass("active");
						$(this).parent().addClass("active");
					}
				}).keydown(function(e){
					if($(this).parent().hasClass("active") && e.keyCode == 9 && e.shiftKey==false){
						var focusItem = injeinc.findFocusItem($(this).parents(".tabBar").eq(0).siblings(".tabPage.active"));
						if(focusItem.length == 0 || $(this).hasClass("useLink")){
							if($(this).parent().next().children("a").length > 0){
								e.preventDefault();
								$(this).parent().next().children("a").trigger("click").focus();
							}else if($(this).parents(".tabContent").eq(1).length > 0){
								$(this).parents(".tabContent").eq(1).find(".tabBar li.active").next().children("a").trigger("click");
							}
						}else{
							e.preventDefault();
							if($(this).parents(".mainNotice").length > 0){
								for(i=0;i<focusItem.length;i++){
									if(focusItem.eq(i).css("display") != "none" && !focusItem.eq(i).parent().hasClass("slick-cloned")){
										focusItem.eq(i).focus();
										break;									
									}
								}
								$('.active .slick-slider').slick('slickGoTo', 0);
							}else{
								for(i=0;i<focusItem.length;i++){
									if(focusItem.eq(i).css("display") != "none" && !focusItem.eq(i).parent().hasClass("slick-cloned")){
										focusItem.eq(i).focus();
										break;									
									}
								}
							}
						}
					}
				});
			}
		});
		$(".tabPage").each(function(){
			if(!$(this).parent(".tabContent").hasClass("notUsed")){
				var focusItem = injeinc.findFocusItem($(this)); //탭페이지 포커스 이동
				if(focusItem.length > 0){
					focusItem.last().unbind("keydown").keydown(function(e){
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
		$(document).on("click", ".btn_tabOpen", function(e){
			e.preventDefault();
			var tabNo = parseInt($(this).attr("data-tabNo")) - 1;
			$($(this).attr("href")).find(".tabBar > li > a").eq(tabNo).trigger("click");
		});
	},
	'tabReset':function(tabItem){
		if(tabItem.hasClass("tabContent")){
			tabItem.children(".tabBar").children("li.active").removeClass("active");
			tabItem.children(".tabPage").removeClass("active");
		}else{
			tabItem.parents(".tabContent").eq(0).children(".tabBar").children("li.active").removeClass("active");
			tabItem.parents(".tabContent").eq(0).children(".tabPage").removeClass("active");
		}
	},
	'datepicker':function(){
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

		$(".useDatepicker").each(function(){
			if(!$(this).hasClass(".hasDatepicker")){
				var minDate = $(this).attr("data-minDate");
				var maxDate = $(this).attr("data-maxDate");
				var dateFormat = "yy-mm-dd";
				if($(this).attr("data-format")){
					dateFormat = $(this).attr("data-format");
				}
				$(this).datepicker({
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
					changeMonth: true,
					changeYear: true,
					yearRange: 'c-60:c+0',
					/* showOn: "both",
					buttonImage: "/images/sub/bg_inputDate.png", */
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
		$(".useMonthpicker").each(function(){
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
		});
	},
	'fileBtn':function(){
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
	},
	'wordLength':function(){
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
					return injeinc.wordLimit($(this));
				});
				injeinc.wordLimit($(this));
			}
		});
	},
	'wordLimit':function(textareaObj){
		textName = textareaObj.attr("name");
		limitByte = parseInt(textareaObj.attr("data-limitByte"));
		var totalByte = 0;
		var limitLength = 0;
		var message = textareaObj.val();
		for(var i =0; i < message.length; i++){
			var currentByte = message.charCodeAt(i);
			if(currentByte > 128) totalByte += 2;
			else totalByte++;
			if(totalByte > limitByte){
				limitLength = i;
				textareaObj.val(message.substring(0,limitLength));
				totalByte = limitByte;
				break;
				return;
			}
		}
		$("#wordCount_"+textName+" b").text(totalByte);
	},
	'ajaxLinkBox':function(){
		$(".ajaxLinkBox").each(function(){
			if($(this).attr("data-url")){
				var _url = $(this).attr("data-url");
				var _param = "";
				var _target = $(this);
				if($(this).attr("data-param")){
					_param = $(this).attr("data-param");
				}
			    $.ajax({
			        type:"POST",
			        url:_url,
			        dataType:"text",
			        data:_param,
			        timeout:10000,
			        success:function(data){
			        	_target.addClass("active").html(data);
			        },
			        error: function(response){
			        	console.log(response);
			        }
			    });
			}
		});
	},
	'modal':function(){
		$(document).on("click",".btn_modalOpen",function(e){
			e.preventDefault();
			var targetModal = $(this).attr("href");
			injeinc.modalOpen(targetModal);
		});
		$(document).on("click","#overlay, .btn_modalClose",function(e){
			e.preventDefault();
			injeinc.modalClose();
		});
	},
	'modalOpen':function(id){
		//$(window).scrollTop(0);
		$("#overlay").show();
		$(id).addClass("active");
		//$(id).find(".btn_modalClose").eq(0).focus();
	},
	'modalClose':function(){
		var modalId = $(".modalWrap.active").attr("id");
		$(".modalWrap").removeClass("active");
		$("#overlay").hide();
		if($("a.btn_modalOpen[href='#"+modalId+"']").length == 1){
			$("a.btn_modalOpen[href='#"+modalId+"']").focus();	
		}
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
					console.log(sliderNo+"|"+slideNo);
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
		var sliderNo = sliderItem.length + 1;
		sliderItem[sliderNo] = ul.slick(sliderOption);
		ul.append('<div class="slick-control"><a href="#" class="btn_slickStart">재생</a><a href="#" class="btn_slickStop">정지</a></div>');
		var btn_slickStart = ul.find(".btn_slickStart");
		var btn_slickStop = ul.find(".btn_slickStop");
		if(sliderOption['autoplay'] == false){
			btn_slickStart.addClass("active");
		}else{
			btn_slickStop.addClass("active");
		}
		btn_slickStart.click(function(e){
			e.preventDefault();
			sliderItem[sliderNo].slick("slickPlay");
			btn_slickStop.addClass("active");
			$(this).removeClass("active");
		});
		btn_slickStop.click(function(e){
			e.preventDefault();
			sliderItem[sliderNo].slick("slickPause");
			btn_slickStart.addClass("active");
			$(this).removeClass("active");
		});
	},
	'findFocusItem':function(area){
		return area.find("input, select, textarea, button, a");
	}
	
};