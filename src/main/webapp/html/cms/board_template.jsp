<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>게시판 템플릿관리</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<ul class="thumbList site">
			<li>
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen"><img src="/images/cms/sample/thumb_gallery.jpg" alt="포토목록형" onerror="this.style.display='none';"></a>
					<div class="previewBox">
						<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
						<ul>
							<li class="item1"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=1200,height=800"><em>데스크톱</em></a></li>
							<li class="item2"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=768,height=700"><em>테블릿</em></a></li>
							<li class="item3"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=360,height=640"><em>모바일</em></a></li>
						</ul>
					</div>
				</div>
				<div class="desc">
					<a href="#modal_siteMod" class="btn_modalOpen">
						<span class="subject">포토목록형</span>
						<span>일반</span>
					</a>
				</div>
				<div class="btnArea">
					<a href="#modal_siteMod" class="btn_inline btn_modalOpen">수정</a>
					<a href="#" class="btn_inline">삭제</a>
				</div>
			</li>
			<li>
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen"><img src="/images/cms/sample/thumb_normal.jpg" alt="일반게시판" onerror="this.style.display='none';"></a>
					<div class="previewBox">
						<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
						<ul>
							<li class="item1"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=1200,height=800"><em>데스크톱</em></a></li>
							<li class="item2"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=768,height=700"><em>테블릿</em></a></li>
							<li class="item3"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=360,height=640"><em>모바일</em></a></li>
						</ul>
					</div>
				</div>
				<div class="desc">
					<a href="#modal_siteMod" class="btn_modalOpen">
						<span class="subject">일반게시판</span>
						<span>일반</span>
					</a>
				</div>
				<div class="btnArea">
					<a href="#modal_siteMod" class="btn_inline btn_modalOpen">수정</a>
					<a href="#" class="btn_inline">삭제</a>
				</div>
			</li>
			<li>
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen"><img src="/images/cms/sample/thumb_qna.jpg" alt="묻고답하기" onerror="this.style.display='none';"></a>
					<div class="previewBox">
						<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
						<ul>
							<li class="item1"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=1200,height=800"><em>데스크톱</em></a></li>
							<li class="item2"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=768,height=700"><em>테블릿</em></a></li>
							<li class="item3"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=360,height=640"><em>모바일</em></a></li>
						</ul>
					</div>
				</div>
				<div class="desc">
					<a href="#modal_siteMod" class="btn_modalOpen">
						<span class="subject">묻고답하기</span>
						<span>일반</span>
					</a>
				</div>
				<div class="btnArea">
					<a href="#modal_siteMod" class="btn_inline btn_modalOpen">수정</a>
					<a href="#" class="btn_inline">삭제</a>
				</div>
			</li>
			<li>
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen"><img src="/images/cms/sample/thumb_card.jpg" alt="사전정보공표목록" onerror="this.style.display='none';"></a>
					<div class="previewBox">
						<a href="#" class="btn_previewBoxOpen"><em>미리보기</em></a>
						<ul>
							<li class="item1"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=1200,height=800"><em>데스크톱</em></a></li>
							<li class="item2"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=768,height=700"><em>테블릿</em></a></li>
							<li class="item3"><a href="#" class="btn_windowPopup" data-url="#" data-option="width=360,height=640"><em>모바일</em></a></li>
						</ul>
					</div>
				</div>
				<div class="desc">
					<a href="#modal_siteMod" class="btn_modalOpen">
						<span class="subject">사전정보공표목록</span>
						<span>카드단순형</span>
					</a>
				</div>
				<div class="btnArea">
					<a href="#modal_siteMod" class="btn_inline btn_modalOpen">수정</a>
					<a href="#" class="btn_inline">삭제</a>
				</div>
			</li>
			<li>
				<a href="#modal_siteReg" class="btn_modalOpen">
					<div class="thumbImg add">신규 등록</div>
				</a>
			</li>
		</ul>
	</div>
</div>
<!-- //컨텐츠영역 -->

<!-- 메뉴얼영역 -->
<div class="aside">
	<div class="asideTitle">
		<h5>메뉴얼</h5>
	</div>
	<div class="asideContent">
		<p>
			게시판 템플릿을 관리하는 메뉴입니다.<br />
			템플릿별 수정/삭제가 가능합니다.
		</p>
		<h6>템플릿 등록/수정</h6>
		<ul>
			<li>[+] 버튼을 통해 템플릿 등록을 할 수 있습니다.</li>
			<li>템플릿 섬네일을 클릭하시면 등록정보를 변경 할 수 있습니다.</li>
		</ul>
		<h6>템플릿 삭제</h6>
		<ul>
			<li>템플릿 섬네일 하단의 [삭제]버튼을 통해서 삭제합니다.</li>
		</ul>
		<h6>미리보기</h6>
		<ul>
			<li>해상도/기기별 미리보기를 제공합니다.</li>
			<li><img src="/images/cms/icon_open_15b.png"> 버튼의 롤오버시 미리보기 메뉴가 나타납니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<!-- ============================== 모달영역 ============================== -->
<!-- 템플릿 등록 -->
<div id="modal_siteReg" class="modalWrap">
	<div class="modalTitle">
		<h3>템플릿 등록</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<div class="tableDesc">
			*템플릿 등록정보를 입력해주세요.
		</div>
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col style="width:15%;">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="siteCd">사이트명</label></th>
						<td>
							<select id="siteCd" name="siteCd">
								<option value="" selected="selected">사이트선택</option>
								<option value="yangcheon">대표홈페이지</option>
								<option value="demo">데모사이트</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="layoutCode">템플릿 코드</label></th>
						<td><input id="layoutCode" name="layoutCode" type="text" value="" size="20"></td>
					</tr>
					<tr>
						<th>
							<label>
								템플릿 타입
								<div class="tooltipBox">
									선택하신 템플릿 형태로 Include 파일이 생성됩니다.<br />
									/layout/{사이트명}/ 경로에 생성됩니다.<br />
									*커스텀을 선택하시면 동일한 경로에 파일을 직접 생성해야 합니다.
								</div>
							</label>
						</th>
						<td>
							<ul class="layoutTypeList">
								<li>
									<span class="thumb type1"></span>
									<input type="radio" id="layoutType1" name="layoutType" value="1" checked>
									<label for="layoutType1">3단</label>
								</li>
								<li>
									<span class="thumb type2"></span>
									<input type="radio" id="layoutType2" name="layoutType" value="2" checked>
									<label for="layoutType2">3단+좌측메뉴</label>
								</li>
								<li>
									<span class="thumb type3"></span>
									<input type="radio" id="layoutType3" name="layoutType" value="3" checked>
									<label for="layoutType3">커스텀</label>
								</li>
							</ul>
						</td>
					</tr>
					<tr>
						<th><label for="layoutDesc">템플릿 명</label></th>
						<td><input id="layoutDesc" name="layoutDesc" type="text" value="" size="20"></td>
					</tr>
					<tr>
						<th><label for="layoutContent">템플릿 내용</label></th>
						<td>
							<textarea id="layoutContent" name="layoutContent" title="템플릿 내용" class="w100p" rows="10"></textarea>
<!-- <pre>&lt;%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%&gt;
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %&gt;
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %&gt;
&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %&gt;
&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %&gt;
&lt;jsp:include page="/html/cms/layout/사이트코드/템플릿 코드/head.jsp" flush="true"/&gt;
&lt;decorator:head /&gt;
&lt;jsp:include page="/html/cms/layout/사이트코드/템플릿 코드/top.jsp" flush="true"/&gt;
&lt;decorator:body /&gt;
&lt;jsp:include page="/html/cms/layout/사이트코드/템플릿 코드/bottom.do" flush="true"/&gt;</pre> -->
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<a href="#" class="btn_m on">확인</a>
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
	</div>
</div>
<!-- 템플릿 수정 -->
<div id="modal_siteMod" class="modalWrap">
	<div class="modalTitle">
		<h3>템플릿 수정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<div class="tableDesc">
			*템플릿 등록정보를 입력해주세요.
		</div>
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col style="width:15%;">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="siteCd">사이트명</label></th>
						<td>
							<select id="siteCd" name="siteCd">
								<option value="" selected="selected">사이트선택</option>
								<option value="yangcheon">대표홈페이지</option>
								<option value="demo">데모사이트</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="layoutCode">템플릿 코드</label></th>
						<td><input id="layoutCode" name="layoutCode" type="text" value="" size="20"></td>
					</tr>
					<tr>
						<th>
							<label>
								템플릿 타입
								<div class="tooltipBox">
									선택하신 템플릿 형태로 Include 파일이 생성됩니다.<br />
									/layout/{사이트명}/ 경로에 생성됩니다.<br />
									*커스텀을 선택하시면 동일한 경로에 파일을 직접 생성해야 합니다.
								</div>
							</label>
						</th>
						<td>
							<ul class="layoutTypeList">
								<li>
									<span class="thumb type1"></span>
									<input type="radio" id="layoutType1" name="layoutType" value="1" checked>
									<label for="layoutType1">3단</label>
								</li>
								<li>
									<span class="thumb type2"></span>
									<input type="radio" id="layoutType2" name="layoutType" value="2" checked>
									<label for="layoutType2">3단+좌측메뉴</label>
								</li>
								<li>
									<span class="thumb type3"></span>
									<input type="radio" id="layoutType3" name="layoutType" value="3" checked>
									<label for="layoutType3">커스텀</label>
								</li>
							</ul>
						</td>
					</tr>
					<tr>
						<th><label for="layoutDesc">템플릿 명</label></th>
						<td><input id="layoutDesc" name="layoutDesc" type="text" value="" size="20"></td>
					</tr>
					<tr>
						<th><label for="layoutContent">템플릿 내용</label></th>
						<td>
							<textarea id="layoutContent" name="layoutContent" title="템플릿 내용" class="w100p" rows="10"></textarea>
<!-- <pre>&lt;%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%&gt;
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %&gt;
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %&gt;
&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %&gt;
&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %&gt;
&lt;jsp:include page="/html/cms/layout/사이트코드/템플릿 코드/head.jsp" flush="true"/&gt;
&lt;decorator:head /&gt;
&lt;jsp:include page="/html/cms/layout/사이트코드/템플릿 코드/top.jsp" flush="true"/&gt;
&lt;decorator:body /&gt;
&lt;jsp:include page="/html/cms/layout/사이트코드/템플릿 코드/bottom.do" flush="true"/&gt;</pre> -->
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<a href="#" class="btn_m on">확인</a>
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>