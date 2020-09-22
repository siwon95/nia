<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript">
//<![CDATA[
//전체동의 클릭시
function checkAll() {
	if ($("input[name=chkall]").prop("checked")) {
		$("input[type=checkbox]").prop("checked", true);
	} else {
		$("input[type=checkbox]").prop("checked", false);
	}
}

//동의시
function agree() {
	var chkall = $("input[name=chkall]").prop("checked");
	var chk1 = $("input[name=chk1]").prop("checked");
	var chk3 = $("input[name=chk3]").prop("checked");
	var chk4 = $("input[name=chk4]").prop("checked");
	if (!chk1) {
		alert("양천구청 이용약관 동의에 동의하여 주십시오.");
		$("input[name=chk1]").focus();
		return;
	} else if (!chk3) {
		alert("개인정보 수집 이용약관 동의에 동의하여 주십시오.");
		$("input[name=chk3]").focus();
		return;
	} else if (!chk4) {
		alert("개인정보의 제3자 이용약관 동의에 동의하여 주십시오.");
		$("input[name=chk4]").focus();
		return;
	} else {
		$("input[name=piAgreeYn]").val('Y');
		document.UserFVo.action = "/site/yangcheon/user/User_Foreign_Step_03.do";
		document.UserFVo.submit();
	}
}
//미동의시
function disAgree() {
	window.location.href = "/site/yangcheon/main.do";
}
//]]>
</script>
<h1 class="hidden">01통합회원_02약관동의_03외국인회원</h1>
<form:form commandName="UserFVo" name="UserFVo" method="post">
	<form:hidden path="cuName" />
	<form:hidden path="ownAuth" />
	<form:hidden path="piAgreeYn" />
	<form:hidden path="youngAgreeName" />
</form:form>

<div class="join-process-container">
	<p class="decription">회원가입은 회원구분 - 약관동의 - 본인인증 - 개인정보입력 - 가입완료 순으로 진행됩니다.</p>
	<ol>
		<li class="join-pr1">
			<div>
				<span class="step">STEP 01</span>
				<span class="join-text">회원구분</span>
			</div>
		</li>
		<li class="join-pr2 on">
			<div>
				<span class="step">STEP 02</span>
				<h1 class="join-process-2"><span class="join-text">약관동의</span></h1>
			</div>
		</li>
		<li class="join-pr3">
			<div>
				<span class="step">STEP 03</span>
				<span class="join-text">본인인증</span>
			</div>
		</li>
		<li class="join-pr4">
			<div>
				<span class="step">STEP 04</span>
				<span class="join-text">개인정보 입력</span>
			</div>
		</li>
		<li class="join-pr5">
			<div>
				<span class="step"> STEP 05</span>
				<span class="join-text"> 가입완료</span>
			</div>
		</li>

	</ol>
</div>

<div class="join-content-wrap join-process-2">

	<div class="join-process-notice">
		<p>통합회원시스템은 개인정보를 보호하기 위하여 주민등록번호를 수집하지 않습니다.</p>
		<p>주민등록법에 의해 타인의 주민번호를 도용하여 온라인 회원가입을 하는 등 다른 사람의 주민등록번호를 부정사용하는	자는 3년 이하의 징역 또는 1천만원 이하의 벌금이 부과될 수 있습니다. </p>
		<p>[주민등록법 제 37조(벌칙)]</p>
	</div>

	<div class="agreement">
		<h1>Foreigner (국내거주 외국인)</h1>
		<div class="allagree-container">
			<p>
				<input type="checkbox" id="terms_all" name="chkall" class="agree-check-all" checked="checked" onclick="checkAll();"/>
				<label for="terms_all">All agree. (Agree Terms and Conditions, Collection of Personal Information · Consent to Usage, Consent to Providing Collected Personal Information to Third Party)</label>
			</p>
			<p class="allgree-btns">
				<input type="submit" id="" name="" value="동의" title="버튼을 누르시면 회원가입의 다음단계로 넘어갑니다." class="btn btn-agree" onclick="agree();"/>
				<a href="#" title="양천구청 메인으로 이동" class="btn btn-not-agree" onclick="disAgree();">동의안함</a>
			</p>
		</div>

		<article class="agree-box">
			<div tabindex="0">
			<h1>이용약관</h1>
			<h2>제1장 총 칙</h2>
			<h3>제1조(목적) 이 약관은 양천구청(이하 "구청"라 한다)이 제공하는 이메일 및 홈페이지구축서비스(이하 "서비스"라 한다)의 이용조건 및 절차에 관한 사항을 규정함을 목적으로 합니다.</h3>
			<h3>제2조(정의) 이 약관에서 사용하는 용어의 정의는 다음 각호와 같습니다.</h3>
			<ol>
				<li>1. 회원 : 이용자 아이디를 부여받은 자</li>
				<li>2. ID : 구청이 승인하는 문자 또는 숫자의 조합</li>
				<li>3. 비밀번호 : 비밀 보호를 위해 회원 자신이 설정한 문자, 숫자의 조합</li>
			</ol>
			<h3>제3조(효력의 발생과 변경)</h3>
			<ol>
				<li>① 이 약관의 내용은 구청 홈페이지 화면에 게시하거나 기타의 방법으로 공지함으로써 효력이 발생됩니다.</li>
				<li>② 구청은 이 약관을 변경할 수 있으며, 변경된 약관은 이용자에게 공지함으로써 효력이 발생됩니다.</li>
			</ol>
			
			<h3>제4조(준용규정) 본 약관에 명시되지 않은 이용자의 권리, 의무 및 책임사항은 전기통신사업법 및 기타 대한민국의 관련법령의 규정에 따릅니다.</h3>
			
			<h2>제2장 서비스 이용계약</h2>
			
			<h3>제5조(이용계약의 성립) 이용계약은 이용자의 이용신청에 대한 구청의 승낙과 이용자의 약관 내용에 대한 동의로 성립됩니다.</h3>
			
			<h3>제6조(이용신청) 이용신청은 인터넷을 통하여 이용자가 구청에서 정한 가입신청서에 기록하여 신청할 수 있습니다.</h3>
			<h3>제7조(이용신청의 승낙)</h3>
				<ol>
					<li>① 회원이 신청서의 모든 사항을 정확히 기재하여 이용신청을 하였을 경우에 승낙합니다.</li>
					<li>② 다음 각호의 1에 해당하는 경우에는 이용 승낙을 하지 않을 수 있습니다.
						<ol>
							<li>1. 이용신청의 내용을 허위로 기재한 경우</li>
							<li>2. 다른 사람의 명의를 사용하여 신청한 경우</li>
							<li>3. 기타 이용신청 요건에 미비 되었을 때</li>
						</ol>
					</li>
				</ol>
			
			<h3>제8조(계약사항의 변경) 회원은 이용신청시 기재한 사항이 변경되었을 경우에는 인터넷을 통하여 수정 요청을 할 수 있습니다.</h3>
			
			<h2>제3장 계약당사자의 의무</h2>
			
			<h3>제9조(구청의 의무) 구청은 서비스 제공과 관련해서 알고 있는 회원의 신상 정보를 본인의 승낙 없이 제3자에게 누설·배포하지 않습니다. 단, 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우, 범죄에 대한 수사상의 목적이 있거나 또는 기타 관계법령에서 정한 절차에 의한 요청이 있을 경우에는 그러하지 않습니다</h3>
			
			<h3>제10조(회원의 의무)</h3>
				<ol>
					<li>① 회원은 서비스를 이용할 때 다음 각호의 행위를 하지 않아야 합니다.
						<ol>
							<li>1. 다른 회원의 ID를 부정하게 사용하는 행위</li>
							<li>2. 서비스에서 얻은 정보를 복제, 출판 또는 제3자에게 제공하는 행위</li>
							<li>3. 구청의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 행위</li>
							<li>4. 공공질서 및 미풍양속에 위반되는 내용을 유포하는 행위</li>
							<li>5. 범죄와 결부된다고 객관적으로 판단되는 행위</li>
							<li>6. 기타 관계법령에 위반되는 행위</li>
						</ol>
					</li>
					<li>② 회원은 서비스를 이용하여 영업활동을 할 수 없으며, 영업활동에 이용하여 발생한 결과에 대하여 구청은 책임을 지지 않습니다.</li>
					<li>③ 회원은 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도·증여할 수 없으며, 이를 담보로도 제공할 수 없습니다.</li>
				</ol>
						
			<h2>제4장 서비스 이용</h2>
			
			<h3>제11조(회원 홈페이지에 관한 의무)</h3>
				<ol>
					<li>① 회원은 필요에 따라 자신의 홈페이지, 게시판, 방명록, 등록자료 유지보수에 대한 관리책임을 갖습니다.</li>
					<li>② 회원은 구청에서 제공하는 자료를 임의로 삭제, 변경할 수 없습니다.</li>
					<li>③ 회원은 자신의 홈페이지에 공공질서 및 미풍양속에 위반되는 내용물이나 제3자의 저작권 등 기타권리를 침해하는 내용물을 등록하는 행위를 하지 않아야 합니다. 만약 이와같은 내용물을 게재하였을때 발생하는 결과에 대한 모든 책임은 회원에게 있습니다.</li>
					<li>④ 회원은 자신의 책임하에 개설한 홈페이지를 백업 등 여러가지 방법으로 본인이 관리하여야 합니다.</li>
				</ol>
			
			<h3>제12조(회원의 게시물 관리 및 삭제) 효율적인 서비스 운영을 위하여 회원의 메모리 공간, 메시지크기, 보관일수 등을 제한할 수 있으며 등록하는 내용이 다음 각 호의 1에 해당하는 경우에는 사전 통지없이 삭제할 수 있습니다.</h3>
				<ol>
					<li>1. 다른 회원 또는 제3자를 비방하거나 중상모략으로 명예를 손상시키는 내용인 경우</li>
					<li>2. 공공질서 및 미풍양속에 위반되는 내용인 경우</li>
					<li>3. 범죄적 행위에 결부된다고 인정되는 내용인 경우</li>
					<li>4. 구청의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 내용인 경우</li>
					<li>5. 회원이 자신의 홈페이지와 게시판에 음란물을 게재하거나 음란 사이트를 링크하는 경우</li>
					<li>6. 기타 관계법령에 위반된다고 판단되는 경우</li>
				</ol>
			
			<h3>제13조(게시물의 저작권) 게시물의 저작권은 게시자 본인에게 있으며 회원은 서비스를 이용하여 얻은 정보를 가공, 판매하는 행위 등 서비스에 게재된 자료를 상업적으로 사용할 수 없습니다.</h3>
			
			<h3>제14조(서비스 이용 책임) 서비스를 이용하여 해킹, 음란사이트 링크, 상용S/W 불법배포 등의 행위를 하여서는 아니되며 이를 위반으로 인해 발생한 영업활동의 결과 및 손실, 관계기관에 의한 법적 조치 등에 관하여는 구청은 책임을 지지 않습니다.</h3>
			
			<h3>제15조(서비스 제공의 중지) 다음 각호의 1에 해당하는 경우에는 서비스 제공을 중지할 수 있습니다.</h3>
				<ol>
					<li>1. 서비스용 설비의 보수 등 공사로 인한 부득이한 경우</li>
					<li>2. 전기통신사업법에 규정된 기간통신사업자가 전기통신 서비스를 중지했을 경우</li>
					<li>3. 시스템 점검이 필요한 경우</li>
					<li>4. 기타 불가항력적 사유가 있는 경우</li>
				</ol>								
			
			<h2>제5장 계약해지 및 이용제한</h2>
			
			<h3>제17조(계약해지 및 이용제한)</h3>
				<ol>
					<li>① 회원이 이용계약을 해지하고자 하는 때에는 회원 본인이 인터넷을 통하여 해지신청을 하여야 하며, 구청에서는 본인 여부를 확인 후 조치합니다.</li>
					<li>② 구청은 회원이 다음 각호의 1에 해당하는 행위를 하였을 경우 사전 통지없이 이용계약을 해지하거나 또는 서비스 이용을 중지할 수 있습니다.
						<ol>
							<li>1. 타인의 이용자ID 및 비밀번호를 도용한 경우</li>
							<li>2. 서비스 운영을 고의로 방해한 경우</li>
							<li>3. 허위로 가입 신청을 한 경우</li>
							<li>4. 같은 사용자가 다른 ID로 이중 등록을 한 경우</li>
							<li>5. 공공질서 및 미풍양속에 저해되는 내용을 유포시킨 경우</li>
							<li>6. 타인의 명예를 손상시키거나 불이익을 주는 행위를 한 경우</li>
							<li>7. 서비스의 안정적 운영을 방해할 목적으로 다량의 정보를 전송하거나 광고성 정보를 전송하는 경우</li>
							<li>8. 정보통신설비의 오작동이나 정보 등의 파괴를 유발시키는 컴퓨터바이러스 프로그램 등을 유포하는 경우</li>
							<li>9. 구청 또는 다른 회원이나 제3자의 지적재산권을 침해하는 경우</li>
							<li>10. 타인의 개인정보, 이용자ID 및 비밀번호를 부정하게 사용하는 경우</li>
							<li>11. 회원이 자신의 홈페이지나 게시판 등에 음란물을 게재하거나 음란 사이트를 링크하는 경우</li>
							<li>12. 기타 관련법령에 위반된다고 판단되는 경우</li>
						</ol>
					</li>
				</ol>
			
			<h2>제6장 면 책</h2>
			
			<h3>제18조(면책 조항)</h3>
				<ol>
					<li>① 구청은 회원이 서비스를 이용하여 얻은 정보 및 자료 등에 대하여 책임을 지지 않습니다.</li>
					<li>② 구청은 회원이 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 기타 어떠한 내용에 관하여서도 책임을 지지 않습니다.</li>
				</ol>
				
			<h3>제19조(재판 관할) 서비스 이용으로 발생한 분쟁에 대해 소송이 제기 될 경우 구청은 본청 소재지를 관할하는 법원을 전속 관할법원으로 합니다.</h3>
			</div>
		</article>

		<div class="agree-cell-box">
			<input type="checkbox" name="chk1" id="terms1" class="agree-check-cell"/>
			<label for="terms1">양천구청 이용약관에 동의합니다.</label>
		</div>
		
		<article class="agree-box">
		<div tabindex="0">
			<h1>개인정보 수집이용 동의</h1>

			<h2>1. 개인정보의 수집항목 및 수집방법</h2>
			<p>양천구 홈페이지에서는 기본적인 회원 서비스 제공을 위한 필수정보와 정보주체 각각의 기호와 필요에 맞는 서비스를 제공하기 위한 선택정보로 구분하여 다음의 정보를 수집하고 있습니다. 선택정보를 입력하지 않은 경우에도 서비스 이용에는 제한이 없습니다.</p>
			<h3>가. 수집하는 개인정보의 항목</h3>
			<table class="agree-box-table">
				<caption>수집하는 개인정보의 항목</caption>
				<colgroup>
					<col style="width:16%;" />
					<col style="width:40%;" />
					<col style="width:16%;" />
					<col style="width:16%;" />
				</colgroup>
				<tbody>
					<tr>
						<th rowspan="2" scope="col">회원구분</th>
						<th colspan="2" scope="col">수집하는 필수항목</th>
						<th rowspan="2" scope="col">선택항목</th>
					</tr>
					<tr>
						<th scope="col">인증정보</th>
						<th scope="col">가입정보</th>
					</tr>
					<tr>
						<td>일반회원<br />(14세 이상 내국인)</td>
						<td>I-pIN(개인식별번호), 휴대폰 본인인증(이름, 휴대폰번호, 생년월일)</td>
						<td rowspan="3">아이디, 비밀번호, 이름, 휴대전화, 거주지 주소, 이메일주소</td>
						<td rowspan="3">유선전화, 뉴스레터설정</td>
					</tr>
					<tr>
						<td>어린이회원<br />(14세미만 내국인)</td>
						<td>어린이회원과 법정대리인의 [ I-pIN(개인식별번호), 휴대폰 본인인증(이름, 휴대폰번호, 생년월일) ]</td>
					</tr>
					<tr>
						<td>외국인회원 (국내거주)</td>
						<td>I-pIN(개인식별번호), 휴대폰 본인인증(이름,휴대폰번호,생년월일)</td>
					</tr>
				</tbody>
			</table>


			<h4>&lt;컴퓨터에 의해 자동으로 수집되는 정보&gt;</h4>
			<ul>
				<li>○ 홈페이지를 이용할 경우 다음의 정보는 로그인 기록을 통하여 자동적으로 수집·저장됩니다.
					<ul>
						<li>- 접속 로그, 쿠키, 접속 Ip 정보, 방문일시, 이용자의 브라우져 종류 및 OS</li>
					</ul>
				</li>
			</ul>

				<h3>나. 개인정보 수집방법</h3>
			<ul>
				<li>○ 홈페이지 회원가입을 통한 수집</li>
			</ul>

			<h2>2. 개인정보의 수집·이용목적 및 보유&middot;이용기간</h2>
			<p>양천구 홈페이지에서는 정보주체의 회원 가입일로부터 서비스를 제공하는 기간 동안에 한하여 양천구 홈페이지 서비스를 이용하기 위한 최소한의 개인정보를 보유 및 이용 하게 됩니다. 회원가입 등을 통해 개인정보의 수집․이용, 제공 등에 대해 동의하신 내용은 언제든지 철회하실 수 있습니다. 회원 탈퇴를 요청하거나 수집/이용목적을 달성하거나 보유/ 이용기간이 종료한 경우, 사업 폐지 등의 사유발생시 당해 개인정보를 지체없이 파기합니다.</p>
			<table class="agree-box-table">
				<caption>개인정보의 수집&middot;이용목적 및 보유&middot;이용기간</caption>
				<colgroup>
					<col style="width:25%;" />
					<col style="width:40%;" />
					<col style="width:25%;" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="col">수집하는 개인정보의 항목</th>
						<th scope="col">개인정보의 수집&middot;이용 목적</th>
						<th scope="col">개인정보의 보유 및 이용기간</th>
					</tr>
					<tr>
						<td>이름, 휴대폰번호, 생년월일 또는 I-pIN(개인식별번호), 법정대리인(이름, 휴대폰번호, 생년월일 또는 I-pIN(개인식별번호))</td>
						<td>
							<ul>
								<li>- 홈페이지 이용에 따른 본인 식별/인증 절차에 이용</li>
								<li>- 만14세 미만 아동의 개인정보 수집 시 법정대리인의 동의 여부 확인</li>
							</ul>
						</td>
						<td>I-pIN은 별도로 저장하지 않으며 인증용으로만 이용</td>
					</tr>
					<tr>
						<td>아이디, 비밀번호, 이름, 연락처(집/휴대폰 중 택일), 거주지 주소, 이메일주소</td>
						<td>홈페이지 서비스 이용 및 회원관리, 불량회원의 부정 이용 방지, 비인가 사용방지, 민원 신청 및 처리, 고지사항 전달, 게시물 등록, 자료 다운로드, 시설물 예약 및 교육강좌 신청결과의 안내, 전자민원, 본인 의사 확인, 원활한 의사소통 경로의 확보, 정기간행물 발송, 공공행사 및 공공서비스 이용 안내, 설문 등의 목적으로 양천구 패밀리 사이트에서 공동 이용</td>
						<td rowspan="2">회원 탈퇴시까지</td>
					</tr>
					<tr>
						<td>뉴스레터설정</td>
						<td>고객 관심사에 부합하는 웹서비스 제공 (이 정보는 선택적 기입 정보로서 고객께서 입력하지 않으셔도 서비스 이용 제한은 없음)</td>
					</tr>
				</tbody>
			</table>

			<p>정보주체는 개인정보의 수집·이용목적에 대한 동의를 거부할 수 있으며, 동의 거부시 양천구 홈페이지에 회원가입이 되지 않으며, 양천구 홈페이지에서 제공하는 서비스를 이용할 수 없습니다.</p>

			<p>※기타 양천구 가족사이트 개인정보 수집 및 이용 목적은 개별 사이트 개인정보처리방침을 참고하시기 바랍니다.</p>
		</div>
		</article>
		<div class="agree-cell-box">
			<input type="checkbox" name="chk3" id="terms2" class="agree-check-cell"/>
			<label for="terms2">개인정보 수집 이용에 동의합니다.</label>
		</div>
		
		<article class="agree-box">
			<div tabindex="0">
			<p>양천구 홈페이지에서는 아래의 경우 제3자 제공을 하고 있으며, 개인정보의 취급 위탁은 하지 않습니다.</p>
			<p>휴대폰본인인증 및 공공 I-pIN 인증시에 제공하는 개인정보는 개인정보보호법 제22조 제2항에 따라 정보주체와의 계약체결 등을 위하여 정보주체의 동의 없이 처리할 수 있는 개인정보로 지정 합니다.</p>
			&lt;양천구 홈페이지 회원 서비스 제공을 위해 개인정보를 제공하는 기관&gt;
			<p>아래에 해당하는 개인정보는 회원가입 및 본인확인을 위한 휴대폰본인인증 및 공공 I-pIN 인증시에만 제3자 제공을 하고 있으며 정보주체의 동의 없이는 제공되지 않습니다.</p>
			<table class="agree-box-table">
				<caption>양천구 홈페이지 회원 서비스 제공을 위해 개인정보를 제공하는 기관</caption>
				<colgroup>
					<col style="width:15%;" />
					<col style="width:25%;" />
					<col style="width:25%;" />
					<col style="width:25%;" />
				</colgroup>
				<tbody>
					<tr>
						<th>제공받는 자</th>
						<th>제공받는 자의 개인정보 이용 목적</th>
						<th>제공하는 개인정보의 항목</th>
						<th> 제공받는 자의 개인정보 보유 및 이용 기간</th>
					</tr>
					<tr>
						<td>나이스신용평가정보(주)</td>
						<td>휴대폰 본인인증</td>
						<td>이름, 휴대폰번호, 생년월일, 법정대리인(이름, 휴대폰번호, 생년월일)</td>
						<td>신용평가 기관에서는 이미 보유하고 있는 개인정보이기 때문에 별도로 저장하지 않음</td>
					</tr>
					<tr>
						<td>행정안전부</td>
						<td>공공 I-pIN 인증</td>
						<td>I-pIN(개인식별번호), 법정대리인(I-pIN(개인식별번호))</td>
						<td>행정안전부에서는 이미 보유하고 있는 개인정보이기 때문에 별도로 저장하지 않음</td>
					</tr>
				</tbody>
			</table>
			<p>단, 다음에 해당하는 경우에는 예외로 제공할 수 있습니다. [관련근거:개인정보보호법 제18조 제2항]</p>
			<ol>
				<li>1. 정보주체로부터 별도의 동의를 받은 경우</li>
				<li>2. 다른 법률에 특별한 규정이 있는 경우</li>
				<li>3. 정보주체 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 정보주체 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</li>
				<li>4. 통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로서 특정 개인을 알아볼 수 없는 형태로 개인정보를 제공하는 경우</li>
				<li>5. 개인정보를 목적 외의 용도로 이용하거나 이를 제3자에게 제공하지 아니하면 다른 법률에서 정하는 소관업무를 수행할 수 없는 경우로서 보호위원회의 심의·의결을 거친 경우</li>
				<li>6. 조약, 그 밖의 국제협정의 이행을 위하여 외국정부 또는 국제기구에 제공하기 위하여 필요한 경우</li>
				<li>7. 범죄의 수사와 공소의 제기 및 유지를 위하여 필요한 경우</li>
				<li>8. 법원의 재판업무 수행을 위하여 필요한 경우</li>
				<li>9. 형(刑) 및 감호, 보호처분의 집행을 위하여 필요한 경우</li>
			</ol>

			<p>정보주체는 개인정보의 제3자 제공에 대한 동의를 거부할 수 있으며, 동의 거부시 양천구 홈페이지에서 회원가입 및 본인확인이 필요한 서비스는 이용할 수 없습니다.</p>
			<p>※기타 양천구 가족사이트 개인정보 취급 위탁 현황은 개별 사이트 개인정보처리방침을 참고하시기 바랍니다.</p>
		</div>
		</article>
		<div class="agree-cell-box">
			<input type="checkbox" name="chk4" id="terms3" class="agree-check-cell"/>
			<label for="terms3">개인정보의 제3자 제공에 동의하십니까?</label>
		</div>
	</div>
	
	<div class="allagree-container">
		<p>
			<input type="checkbox" id="terms_all" name="chkall" class="agree-check-all" onclick="checkAll();" />
			<label for="terms_all">All agree. (Agree Terms and Conditions, Collection of Personal Information · Consent to Usage, Consent to Providing Collected Personal Information to Third Party)</label>
		</p>
		<p class="allgree-btns">
			<input type="submit" id="" name="" value="동의" title="버튼을 누르시면 회원가입의 다음단계로 넘어갑니다." class="btn btn-agree" onclick="agree();"/>
			<a href="#" title="양천구청 메인으로 이동" class="btn btn-not-agree" onclick="disagree();">동의안함</a>
		</p>
	</div>
	
</div>