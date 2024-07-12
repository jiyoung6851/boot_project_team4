<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login/register.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login_fn.js"></script>
<body>
<jsp:include page="../../header.jsp"/>
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
			<input type="text" name="puserid" id="puserid" placeholder="아이디를 입력해주세요" required style="width: 60%;">&nbsp;&nbsp;&nbsp;<button type="button" class="submit-btn" onclick="id_chk('puserid','p');">중복 확인</button><br>
			<input type="password" name="ppass" placeholder="비밀번호를 입력해주세요" required><br>
			<input type="text" name="pname" placeholder="이름(실명)을 입력해주세요" required><br>
			<input type="tel" name="phone" placeholder="휴대폰 번호를 입력해주세요(-제외)" required><br>
			<input type="email" name="email" placeholder="이메일을 입력해주세요" required><br>
			<button type="submit" id="pbutton" class="submit-btn disabled">가입하기</button>
	   </form>
	   <form method="post" id="businessForm" class="hidden" action="cRegisterInsert">
			<input type="hidden" name="gubun" value="c">
			<input type="text" name="cuserid" id="cuserid" placeholder="아이디를 입력해주세요" required style="width: 60%;">&nbsp;&nbsp;&nbsp;<button type="button" class="submit-btn" onclick="id_chk('cuserid', 'c');">중복 확인</button><br>
			<input type="password" name="cpass"  placeholder="비밀번호를 입력해주세요" required><br>
			<%-- <input type="text" name="gubun" placeholder="기업형태를 입력해주세요" required><br> --%>
			<input type="text" name="bsnum" placeholder="사업자등록번호를 입력해주세요" required><br>
			<input type="text" name="cleader" placeholder="대표자명을 입력해주세요" required><br>
			<input type="text" name="cusnm" placeholder="회사명을 입력해주세요" required><br>
			<input type="text" name="caddr"  placeholder="회사주소를 입력해주세요" required><br>
			<input type="tel" name="phone" placeholder="전화번호를 입력해주세요" required><br>
			<input type="email" name="cmail" placeholder="이메일을 입력해주세요" required><br>
			<button type="submit" id="cbutton" class="submit-btn disabled">가입하기</button>
	    </form>
	</div>
</body>
<jsp:include page="../../footer.jsp"/>
</html>