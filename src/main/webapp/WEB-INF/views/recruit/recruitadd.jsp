<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>모집 공고 올리기</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/recurit/recurit.css">
</head>
<jsp:include page="../../header.jsp"/>
<body>
    <div class="container">
        <form id="jobPostingForm" action="recruitinsert" method="post">
            <div class="section">
                <h2>기업 소개</h2>
                <div class="card">
                    <p>제목:</p>
                    <textarea name="jobtitle" rows="1">이지 케어텍에서 백엔드 구인중</textarea>
                    <p>부제목:</p>
                    <textarea name="jobsubtitle" rows="1">국내 의료it 업계 1위 기업</textarea>
                    <p>내용:</p>
                    <textarea name="content" rows="5">업계최초로 국가공인인증 "신기술 인증 마크" 등 여러 기술력 보유</textarea>
                </div>
            </div>

            <div class="section">
                <h2>포지션 및 자격 요건</h2>
                <div class="card">
                    <p>담당 업무 :</p>
                    <textarea name="position">모바일 솔루션 개발</textarea>
                    <p>스킬 :</p>
                    <textarea name="skills">API, Oracle, java</textarea>
                    <p>경력 :</p>
                    <select name="prsup">
                    	<c:forEach items="${prsup}" var="prsup">
                    		<option value="${prsup.careerno }">${prsup.careernm }</option>
                    	</c:forEach>
					</select>
					<p>학력 :</p>
                    <select name="educa">
                    	<c:forEach items="${educa}" var="educa">
                    		<option value="${educa.eduno }">${educa.edunm }</option>
                    	</c:forEach>
                    </select>
                    <p>자격 요건 :</p>
                    <textarea name="duties">대졸이상, 경력 3~15년, 백엔드</textarea>
                    <p>우대 조건 :</p>
                    <textarea name="pfntcd">컨테이너를 활용한 인프라 관리 경험, 의료IT 및 동종업계 경력자</textarea>
                </div>
            </div>

            <div class="section">
                <h2>근무 조건 및 복지</h2>
                <div class="card">
                    <p>고용 형태 :</p>
                   	 <select name="wrkty">
                   	 	<c:forEach items="${wrkty }" var="wrkty">
                   	 		<option value="${wrkty.wrktyno }">${wrkty.wrktynm }</option>
                   	 	</c:forEach>
					</select>
					<p>급여 :</p>
					<c:choose>
                        <c:when test="${jobinfoData.salary == null or jobinfoData.salary == ''}">
                            <input type="text" name="salary" placeholder="미입력시 '면접후 결정'" />
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="salary" value="${jobinfoData.salary}" />
                        </c:otherwise>
                    </c:choose>					
                    <p>근무 지역 :</p>
                    <textarea name="loc01">서울 중구</textarea>
                    <p>근무 요일/시간 :</p>
                    <textarea name="wrktm">주5일(월~금) 09:00 ~ 18:00</textarea>
                </div>
            </div>
			
			<div class="section">
                <h2>복지 및 혜택</h2>
                <div class="card">
                    <textarea name="benefits">자녀학자금 지원, 사내동호희 운영 등</textarea>
				</div>
			</div>	
            <div class="section">
                <h2>접수 기간 및 방법</h2>
                <div class="card">
                    <p>접수 기간 및 방법 :</p>
                    <textarea name="pamoa">잡코리아 온라인이력서 제출</textarea>
                    <label for="ddate">마감일자<span>*</span></label>
					<input type="date" id="ddate" name="ddate" style="width: 120px;"/>
					<p>모집 인원 : <input type="text" name="recno" style="width: 50px;"></p>
                    <p>채용 절차 :</p>
                    <textarea name="rcmpcd">서류전형>인성검사>면접>최종합격</textarea>
                </div>
            </div>
			

            <div class="button-container">
                <button type="submit" class="submit-button">공고 올리기</button>
            </div>
        </form>
    </div>
</body>
</html>
