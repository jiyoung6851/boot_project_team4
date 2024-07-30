<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hello World</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login_fn.js"></script>

</head>
<jsp:include page="../../header.jsp"/>
<body>
<div class="container" >
    <div class="header-container">
        <div class="btn-group">
            기업회원 아이디 찾기 
        </div>
    </div>
    <div>
        회원님 성함 : ${cusnm}
    </div>
    <div>
        회원님 사업자 번호 : ${bsnum}
    </div>
    <div>
        회원님 이메일 : ${cmail}
    </div>
    <div>
        회원님 아이디 : ${cuserid}
    </div>
<a href="/pwfind" class="btn btn-google btn-user btn-block">비밀번호 찾기</a> 
<a href="/login" class="btn btn-google btn-user btn-block">로그인</a> 
</div>
</body>
<jsp:include page="../../footer.jsp"/>
</html>