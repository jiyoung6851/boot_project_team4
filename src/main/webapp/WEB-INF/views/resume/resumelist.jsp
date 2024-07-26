<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이력서 관리</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/resume/resumelist.css"> 
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<jsp:include page="../../header.jsp"/>
    <div class="container">
        <section class="resume-management">
            <div id="titleDiv">
                <span id="title">이력서 관리</span>
            </div>
            <div id="addButtonDiv" class="resume-card">
                <a id="addResumeButton" class="f-s-b"> + 새 이력서 등록하기 </a>
            </div>
            <form method="get" id="resumefrm" name="resumefrm">
            	<input type="hidden" id="prono" name="prono">
				<input type="hidden" id="imgno" name="imgno">
            	<div class="resumes">
					<c:forEach items="${resumelist}" var="dto">
						<div class="resume-card" data-representative="${dto.representative ? 'true' : 'false'}">
                            <div>
                                <img src="/display_img_show?prono=${dto.prono }&imgno=${dto.imgno}">
                            </div>
                            <p class="f-s-b">
                                <a href="/resumedisplay?prono=${dto.prono }">${dto.protitle}</a><br>
                            </p>
                            <div class="buttons">
                                <button type="button" class="update" onclick="update_resume('${dto.prono}', '${dto.imgno}');">수정</button>
                                <button type="button" class="delete" onclick="delete_resume('${dto.prono}', '${dto.imgno}');">삭제</button>
                                <button type="button" class="set-representative" onclick="setRepresentative('${dto.prono}', '${dto.imgno}');">대표 이력서 설정</button>
                            </div>
                        </div>
					</c:forEach>
				</div>
			</form>
			<div class="div_page">
				<ul>
					<c:if test="${pageMaker.prev }">
						<li class="page-item paginate_button" style="padding: 0px; border-bottom: 0px; ">
							<a class="page-link" href="${pageMaker.startpage - 1}">Prev</a>
						</li>
					</c:if>
					<c:forEach var="num" begin="${pageMaker.startpage }" end="${pageMaker.endpage }">
						<li class="page-item paginate_button ${pageMaker.cri.pageNum == num?'active':'' }" style="padding: 0px; border-bottom: 0px; ">
							<a class="page-link" href="${num}">${num }</a>
						</li>
					</c:forEach>
					<c:if test="${pageMaker.next}">
						<li class="page-item paginate_button" style="padding: 0px; border-bottom: 0px;">
							<a class="page-link" href="${pageMaker.endpage + 1}">Next</a>
						</li>
					</c:if>
				</ul>
		    </div>
		    <form id="actionForm" method="get">
				<input type="hidden" id="pageNum" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" id="amount" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				<input type="hidden" name="type" value="${pageMaker.cri.type}">
			</form>
        </section>
    </div>
</body>
<script>
var actionForm = $("#actionForm");

//	페이지번호 처리
$(".paginate_button a").on("click", function (e){
	//기본 동작 막음: 페이지 링크를 통해서 이동
	e.preventDefault();

	actionForm.find("input[name='pageNum']").val($(this).attr("href"));
	actionForm.attr("action", "resumelist").submit();
});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resume/resumelist.js"></script>
</html>
