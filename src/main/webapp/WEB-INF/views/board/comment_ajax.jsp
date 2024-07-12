<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h4>댓글</h4>
<c:forEach items="${commentList}" var="comment">
	<div class="comments">
		<p>ID: ${comment.replyid}</p>
		<div class="p2">
			<p>${comment.content}</p>
		</div>
		<p><fmt:formatDate value="${comment.adate }" pattern="yyyy-MM-dd HH시 mm분"/>에 작성</p>
	</div>
</c:forEach>