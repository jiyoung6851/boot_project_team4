<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login/register.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/register.js"></script>
<script>

</script>

<body>
	<jsp:include page="../../header.jsp" />
	<div class="container">
		<div class="header-container">
			<h2>회원가입</h2>
			<div class="btn-group">
				<button type="button" id="generalBtn" class="active" onclick="geneform()">일반회원</button>
				<button type="button" id="businessBtn" onclick="busiform()">기업회원</button>
			</div>
		</div>
		<form method="post" id="generalForm" action="pRegisterInsert">
			<input type="hidden" name="gubun" value="p">
			<input type="text" name="puserid" id="puserid" class="input_id" placeholder="아이디를 입력해주세요(알파벳,숫자만 가능)" required oninput="validateInput(event)">
			<button type="button" class="onclick_1" onclick="id_chk('puserid','p');">중복 확인</button><br>
			<input type="password" name="ppass" class="input_pw" placeholder="비밀번호를 입력해주세요" required><br>
			<input type="text" name="pname" class="input_text" placeholder="이름(실명)을 입력해주세요" required><br>
			<input type="tel" name="phone" class="input_tel" placeholder="휴대폰 번호를 입력해주세요(-제외)" required  maxlength="20" oninput="onlyNumbers(event)"><br>
			<input type="email" id="puser_email" name="email" class="input_email" placeholder="이메일을 입력해주세요" required>
			<button type="button" id="psendVerificationButton" class="onclick_2" onclick="psendVerificationCode()">인증번호
				전송</button><br>
			<input type="text" id="puser_checkNo" name="puser_checkNo" class="input_ck" placeholder="인증번호" required>
			<button type="button" class="onclick_2" onclick="pverifyCode()">인증번호 확인</button><br>
            <div class="terms-container">
				<div class="terms-header">
					필수동의 항목 모두 동의합니다.
					<input type="checkbox" id="pagree-all" class="agree_all" onclick="AllCheck(this)">
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<strong style="color: blue">[필수]</strong> 만 15세 이상입니다
					<input type="checkbox" name="agreement" class="checkbox" value="age">
					</label>
					<div class="details">
						여기에 만 15세 이상에 대한 구체적인 내용을 추가하세요.
					</div>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<input type="checkbox" name="agreement" class="checkbox" value="terms">
						<strong style="color: blue">[필수]</strong> 이용약관 동의
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						여기에 이용약관에 대한 구체적인 내용을 추가하세요.
					</div>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<strong style="color: blue">[필수]</strong> 개인정보 수집 및 이용 동의
						<input type="checkbox" name="agreement" class="checkbox" value="privacy">
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						여기에 개인정보 수집 및 이용에 대한 구체적인 내용을 추가하세요.
					</div>
				</div>
			</div>
			<button type="submit" id="pbutton" class="submit-btn disabled">가입하기</button>
		</form>
		<form method="post" id="businessForm" class="hidden" action="cRegisterInsert">
			<input type="hidden" name="gubun" value="c">
			<input type="text" name="cuserid" id="cuserid" class="input_id" placeholder="아이디를 입력해주세요(알파벳,숫자만 가능)" required oninput="validateInput(event)">
			<button type="button" class="onclick_1" onclick="id_chk('cuserid', 'c');">중복 확인</button><br>
			<input type="password" name="cpass" class="input_pw" placeholder="비밀번호를 입력해주세요" required><br>
			<input type="text" name="bsnum" class="input_text" placeholder="사업자등록번호를 입력해주세요" required maxlength="10" onkeyup="formatDate(event)"><br>
			<input type="text" name="cleader" class="input_text" placeholder="대표자명을 입력해주세요" required><br>
			<input type="text" name="cusnm" class="input_text" placeholder="회사명을 입력해주세요" required><br>
			<input type="text" name="caddr" class="input_text" placeholder="회사주소를 입력해주세요" required><br>
			<input type="tel" name="phone" class="input_tel" placeholder="전화번호를 입력해주세요" required maxlength="20" oninput="onlyNumbers(event)"><br>
			<input type="email" id="cuser_email" name="cuser_email" class="input_email" placeholder="이메일을 입력해주세요"
				required>
			<button type="button" id="csendVerificationButton" class="onclick_2" onclick="csendVerificationCode()">인증번호
				전송</button>
			<input type="text" id="cuser_checkNo" name="cuser_checkNo" class="input_ck" placeholder="인증번호" required>
			<button type="button" class="onclick_2" onclick="cverifyCode()">인증번호 확인</button><br>
			<div class="terms-container">
				<div class="terms-header">
					필수동의 항목 모두 동의합니다.
					<input type="checkbox" id="cagree-all" class="agree_all" onclick="AllCheck(this)">
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<strong style="color: blue">[필수]</strong> 만 15세 이상입니다
					<input type="checkbox" name="agreement" class="checkbox" value="age">
					</label>
					<div class="details">
						여기에 만 15세 이상에 대한 구체적인 내용을 추가하세요.
					</div>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<input type="checkbox" name="agreement" class="checkbox" value="terms">
						<strong style="color: blue">[필수]</strong> 이용약관 동의
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						여기에 이용약관에 대한 구체적인 내용을 추가하세요.
					</div>
				</div>
				<div class="terms-group">
					<label class="terms-item">
						<input type="checkbox" name="agreement" class="checkbox" value="privacy">
						<strong style="color: blue">[필수]</strong> 개인정보 수집 및 이용 동의
					</label>
					<span class="expand-link" onclick="toggleDetails(this)">내용보기 ▾</span>
					<div class="details">
						여기에 개인정보 수집 및 이용에 대한 구체적인 내용을 추가하세요.
					</div>
				</div>
			</div>
			<button type="submit" id="cbutton" class="submit-btn disabled">가입하기</button>
		</form>
	</div>
</body>
<jsp:include page="../../footer.jsp" />

</html>