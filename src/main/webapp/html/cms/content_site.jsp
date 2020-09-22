<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>사이트 관리</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<ul class="thumbList site">
			<li>
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen">
						<img src="/images/cms/sample/sample_demo.jpg" alt="데모사이트" onerror="this.style.display='none';">
					</a>
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
						<span class="subject">데모사이트</span>
						<span class="url">demo.injeinc.co.kr</span>
					</a>
				</div>
				<div class="btnArea">
					<a href="#" class="btn_inline">메뉴관리</a>
					<a href="#" class="btn_inline">게시판관리</a>
				</div>
			</li>
			<li>
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen">
						<img src="/images/cms/sample/sample_yangcheon.jpg" alt="양천구청" onerror="this.style.display='none';">
					</a>
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
						<span class="subject">양천구청</span>
						<span class="url">http://www.yangcheon.go.kr</span>
					</a>
				</div>
				<div class="btnArea">
					<a href="#" class="btn_inline">메뉴관리</a>
					<a href="#" class="btn_inline">게시판관리</a>
				</div>
			</li>
			<li>
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen">
						<img src="" alt="No Image" onerror="this.style.display='none';">
					</a>
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
						<span class="subject">대표홈페이지</span>
						<span class="url">easyframe.injeinc.co.kr</span>
					</a>
				</div>
				<div class="btnArea">
					<a href="#" class="btn_inline">메뉴관리</a>
					<a href="#" class="btn_inline">게시판관리</a>
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
			여러개의 사이트에 대한 관리가 가능합니다.<br />
			각 사이트별 메뉴와 게시판이 구분되어 관리 됩니다.
		</p>
		<h6>사이트 등록/수정</h6>
		<ul>
			<li>[+] 버튼을 통해 사이트 등록을 할 수 있습니다.</li>
			<li>사이트 섬네일을 클릭하시면 등록정보를 변경 할 수 있습니다.</li>
		</ul>
		<h6>미리보기</h6>
		<ul>
			<li>해상도/기기별 미리보기를 제공합니다.</li>
			<li><img src="/images/cms/icon_open_15b.png"> 버튼의 롤오버시 미리보기 메뉴가 나타납니다.</li>
		</ul>
		<h6>메뉴관리</h6>
		<ul>
			<li>각 섬네일 하단의 [메뉴관리]버튼을 클릭합니다.</li>
		</ul>
		<h6>게시판관리</h6>
		<ul>
			<li>각 섬네일 하단의 [게시판관리]버튼을 클릭합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<!-- ============================== 모달영역 ============================== -->
<!-- 사이트 등록 -->
<div id="modal_siteReg" class="modalWrap">
	<div class="modalTitle">
		<h3>사이트 등록</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<div class="tableDesc">
			*사이트 등록정보를 입력해주세요.
		</div>
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col class="w20p">
					<col>
				</colgroup>
				<tbody>
				<tr>
					<th><label for="siteReg_siteNm">사이트 명</label></th>
					<td><input id="siteReg_siteNm" name="siteNm" title="사이트 명" type="text" value="" size="20"></td>
				</tr>
				<tr>
					<th><label for="siteReg_siteCd">사이트 코드</label></th>
					<td><input id="siteReg_siteCd" name="siteCd" title="사이트 코드" type="text" value="" size="20"></td>
				</tr>
				<tr>
					<th><label for="siteReg_sitePath">사이트 경로</label></th>
					<td><input id="siteReg_sitePath" name="sitePath" title="사이트 경로" type="text" value="" size="20"> /[사이트경로]/[사이트코드]/이하에 컨텐츠 소스가 생성됨</td>
				</tr>
				<tr>
					<th><label for="siteReg_siteDomain">사이트 도메인</label></th>
					<td><input id="siteReg_siteDomain" name="siteDomain" title="사이트 도메인" type="text" value="" size="20"></td>
				</tr>
				<tr>
					<th><label for="siteReg_siteKdCd">사이트 유형</label></th>
					<td>
						<select id="siteReg_siteKdCd" name="siteKdCd" title="사이트 유형">
							<option value="" selected="selected">--선택하세요--</option>
							<option value="SITE0001">대표홈페이지</option>
						</select>
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
<!-- 사이트 수정 -->
<div id="modal_siteMod" class="modalWrap">
	<div class="modalTitle">
		<h3>사이트 수정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<div class="tableDesc">
			*사이트 등록정보를 입력해주세요.
		</div>
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col class="w20p">
					<col>
				</colgroup>
				<tbody>
				<tr>
					<th><label for="siteMod_siteNm">사이트 명</label></th>
					<td><input id="siteMod_siteNm" name="siteNm" type="text" value="데모사이트" size="20"></td>
				</tr>
				<tr>
					<th>사이트 코드</th>
					<td id="siteMod_siteCd">demo</td>
				</tr>
				<tr>
					<th>사이트 경로</th>
					<td id="siteMod_sitePath">/site/demo/</td>
				</tr>
				<tr>
					<th><label for="siteMod_siteDomain">사이트 도메인</label></th>
					<td><input id="siteMod_siteDomain" name="siteDomain" title="사이트 도메인" type="text" value="" size="20"></td>
				</tr>
				<tr>
					<th><label for="siteMod_siteKdCd">사이트 유형</label></th>
					<td>
						<select id="siteMod_siteKdCd" name="siteKdCd" title="사이트 유형">
							<option value="" selected="selected">--선택하세요--</option>
							<option value="SITE0001">대표홈페이지</option>
						</select>
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