<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="../../header.jsp"/>

<!-- 기업명 동적 표시 -->
<h2>모집 중인 공고</h2>

<!-- 모집공고 리스트를 표시하는 폼 -->
<form id="recruitListForm" method="get">
    <input type="hidden" id="jobno" name="jobno">
    <input type="hidden" id="csrno" name="csrno">

    <!-- 모집공고 리스트를 표시하는 컨테이너 -->
    <div class="container">
        <section class="recruit-management">
            <c:forEach items="${list}" var="dto">
                <div class="recruit-item">
                    <div class="recruit-details">
                        <!-- 모집공고 제목 클릭 시 상세정보로 이동 -->
                        <p id="jobTitle" class="job-title" onclick="viewRecruitInfo('${dto.csrno}', '${dto.jobno}')">
                            ${dto.jobtitle}
                        </p>
                        <br>
                        <!-- 모집공고 부제목 표시 -->
                        <p id="jobSubtitle" class="job-subtitle">${dto.jobsubtitle}</p>
                        <br><br>
                        <!-- 모집공고 등록일 및 마감일 표시 -->
                        <fmt:formatDate value="${dto.adate}" pattern="yyyy-MM-dd" var="formattedAdate"/>
                        <fmt:formatDate value="${dto.ddate}" pattern="yyyy-MM-dd" var="formattedDdate"/>
                        <p id="adate" class="recruit-dates">공고등록일: ${formattedAdate} & 마감일: ${formattedDdate}</p>
                    </div>
                </div>
            </c:forEach>
        </section>
    </div>
</form>
</body>
<jsp:include page="../../footer.jsp"/>
</html>