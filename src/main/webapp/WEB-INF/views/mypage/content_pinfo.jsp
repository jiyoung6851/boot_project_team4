<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/footer/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/mypage/content_pinfo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage/mypage_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>

<body>

<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/update_pinfo">
		<div class="form-header">
		    <h2>마이페이지</h2>
		    <span class="required-field">이름과 ID는 변경할 수 없습니다.</span>
		</div>
		
        <table>
            <tr>
                <td>이름</td>
                <td>${content_pinfo.pname}</td>
            </tr>
            <tr>
                <td>ID</td>
                <td>${content_pinfo.puserid}</td>
                <input type="hidden" id="puserid" name="puserid" value="${content_pinfo.puserid}">
            </tr>
            <tr>
                <td>PW</td>
                <td><input type="text" id="ppass" name="ppass" value="${content_pinfo.ppass}"></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="email" id="email" name="email" value="${content_pinfo.email}"></td>
            </tr>
            <tr>
                <td>휴대폰번호</td>
                <td><input type="text" id="phone" name="phone" value="${content_pinfo.phone}"></td>
            </tr>
        </table>
        <div class="btn_area">
           <input type="button" class="modifybutton" value="수정" onclick="myinfo_modify_p()">
           <input type="button" class="modifybutton" value="탈퇴" onclick="myinfo_delete_p()">
		</div>
    </form>
</div>

</body>
<jsp:include page="../../footer.jsp"/>
</html>