<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/jobpost/jobpost.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resumesearch/dropdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resumesearch/resumesearch.js"></script>
</head>
<jsp:include page="../../header.jsp"/>

<body onload="searchBtn()">
	<section id="section1">
	    <div class="button-nav">
			<dl>
				<button class="dropdown-button">보유 기술</button>
				<ul id="skill" class="dropdown-content scrollable-content">
					<c:forEach items="${showskill}" var="skill">
						<li class="item" value="${skill.skillno}">${skill.skillnm}</li>
					</c:forEach>
				</ul>
			</dl>
	    </div>
		<div id="searchbox" class="search-box">
	        <div style="width: 80%; display: flex; align-items: center;">
	            <div style="width: 20%;"></div>
	            <div id="selectedItems" class="search-list"></div>
	            <div style="width: 20%; display: flex;">
	                <button class="sb-btn" onclick="resetBtn();">초기화</button>&nbsp;
	                <button class="sb-btn" onclick="searchBtn();">검색</button>
	            </div>
	        </div>
	    </div>
	    <br>
	    <div id="postArea">
	    	<!-- jobpost_ajax.jsp 출력 영역 -->
	    </div>
    </section>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resumesearch/search-box.js"></script>
</html>