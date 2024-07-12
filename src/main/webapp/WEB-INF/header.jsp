<%@page import="ch.qos.logback.core.recovery.ResilientSyslogOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header/header.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function() {
    var dropdownButton = document.querySelector(".menu-dropdown");
    var contentDropdown = document.querySelector(".content-dropdown");
    var navbarArea = document.querySelectorAll("a");
    var buttons = document.querySelectorAll("button");
    dropdownButton.addEventListener("mouseover", function() {
        contentDropdown.classList.add("active");
    });
    
    navbarArea.forEach(function(navbar) {
    	navbar.addEventListener("mouseover", function() {
	        contentDropdown.classList.remove("active");
	    });
    });
    
    buttons.forEach(function(button) {
    	button.addEventListener("mouseover", function() {
	        contentDropdown.classList.remove("active");
	    });
    });
});
</script>
<%	
	String userid = "", usergubun = "";
	try{
		userid = (String)session.getAttribute("id");
		usergubun = (String)session.getAttribute("usergubun");
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<div class="navbar">
    <div class="navbar-left">
            <a href="/main">
				<img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo">
			</a>
    </div>
	<%-- 일반 회원 로그인 --%>
	    <div class="navbar-right">
	    <%if(usergubun != null && usergubun.equals("p")) { %>	
	        <div class="navbar-left">
	        	<a href="/jobpost">구직공고</a>
	            <a href="/list">취업 게시판</a>
	            <a href="/mypage_ppwcheck">마이페이지</a>
				<div class="menu-dropdown">
					<%=session.getAttribute("id") %> 님<span class="arrow-down">▼</span>
				    <div class="content-dropdown">
						<a href="/resumelist">내 이력서 관리</a>
						<a href="/jobaplylist_p">나의 지원 현황</a>
						<%-- <a href="/scribe">스크랩 현황</a> --%>
					</div>
				</div>
			</div>
	        <button><a href="/logout">로그아웃</a></button>
	    <%} else if(usergubun != null && usergubun.equals("c")) { %>
	    	<div class="navbar-left">
	        	<a href="/jobpost">구직공고</a>
	            <a href="/list">취업 게시판</a>
	            <a href="/mypage_cpwcheck">마이페이지</a>
				<div class="menu-dropdown">
					<%=session.getAttribute("id") %> 님<span class="arrow-down">▼</span>
					<div class="content-dropdown">
						<a href="/recruitlist">모집 공고 관리</a>
						<a href="/coinfo">기업 정보</a>
					</div>
				</div>
	        </div>
	        <button><a href="/logout">로그아웃</a></button>  
	    <%} else { %>
	    	<div class="navbar-left">
	        	<a href="/jobpost">구직공고</a>
	            <a href="/list">취업 게시판</a>
	            <a href="/login">로그인</a>
	            <a href="/register">회원가입</a>
	        </div>
	    <%} %>
	    </div>
	</div>
	<%-- 
	<a href="#">구직공고</a>
	            <a href="#">구직자 게시판</a>
	            <a href="#">로그인</a>
	        <div id="sessionTimeout">
	        	<font size="2">로그인 유지 시간:</font>
	        </div>
	        --%>
	<script>
		var getid = '<%=session.getAttribute("id") %>';
		var time = '<%=session.getMaxInactiveInterval()%>';
		var gubun = '<%=session.getAttribute("usergubun") %>'
		console.log("getid: "+getid);
		console.log("usergubun: "+gubun);
	console.log("유지 시간: "+time);
	/*
	function updateSessionTimeout() {
		$.ajax({
			url: "/getSessionTimeout",
			success: function(seconds) {
				var displayTime = seconds;
				console.log('seconds: '+seconds);
	            $('#sessionTimeout').text('로그인 유지 시간: ' + displayTime + '초');
	            // 1초마다 세션 유지 시간을 감소시키는 함수 호출
	            setTimeout(function() {
	                updateSessionTimeout();
	            }, 1000);
			}, error: function() {
				console.log("ajax 에러...");
			}
		});
	}
	//한번 호출은 호출 해야 함
	updateSessionTimeout();
	*/
</script>
