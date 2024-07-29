<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/callcenter/content.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/callcenter/callcenter_fn.js"></script>
</head>
<jsp:include page="../../header.jsp"/>

<body>
	<section class="board-name">
		<h2>문의</h2>
	</section>
	
	
<div class="container">	
	<input type="hidden" name="pageNum" value="${pageMaker.pageNum}">
	<input type="hidden" name="amount" value="${pageMaker.amount}">
	
	<button class="listbutton" onclick="move_calllist()">목록보기</button>
		<div class="content_area">
		<div class="content_title_area">
			<h1>${callview.calltitle}</h1><br>
			글번호: ${callview.callno}
		</div>
		<textarea class="call_content_area" rows="30" cols="100" readonly="readonly">${callview.callcontent}</textarea>
	</div>
	
	<c:if test="${callview.callyn == 'y' }">
		<div class="comment-list" id="comment-list">
			<h4>문의 답변</h4>
			<div class="p2">
				<fmt:formatDate value="${callview.mdate}" pattern="yyyy-MM-dd"/>에 작성
				<textarea class="call_content_area" rows="30" cols="100" readonly="readonly">${callview.callreply}</textarea>
			</div>
		</div>
	</c:if>
</div>

</body>
<jsp:include page="../../footer.jsp"/>
</html>






