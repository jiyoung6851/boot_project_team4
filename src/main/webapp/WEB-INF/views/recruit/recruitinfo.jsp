<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/recurit/recruitinfo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/recruit/recruit_fn.js"></script>
    <title>기업 채용 공고</title>
    <script>
        function fnMove(seq) {
            var offset = $("#section" + seq).offset();
            $('html, body').animate({
                scrollTop: offset.top
            }, 400); //0.4초 느리게
        }
		
		function openResumePopup(writer, csrno, jobno) {
			var popupWidth = 600;
			var popupHeight = 400;

			// 현재 브라우저 창의 너비와 높이를 가져옵니다.
			var screenWidth = window.innerWidth;
			var screenHeight = window.innerHeight;

			// 팝업 창의 위치를 중앙으로 설정합니다.
			var left = (screenWidth - popupWidth) / 2 + window.screenX;
			var top = (screenHeight - popupHeight) / 2 + window.screenY;
			
		    var popupUrl = '${pageContext.request.contextPath}/resumePopup?writer='+writer+'&csrno='+csrno+'&jobno='+jobno;
			var win = window.open(popupUrl, 'ApplyPopup', 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + left + ',top=' + top);

		    // 팝업 창이 완전히 열린 후에 이력서 리스트를 가져오기 위해 onload 이벤트를 설정합니다.
		    win.onload = function() {
		        // 이력서 리스트를 가져와서 콤보박스에 추가합니다.
		        var resumelist = win.document.getElementById('resumelist');
		        var select = document.getElementById('resumelist'); // 콤보박스 id를 'resumelist'로 변경해야 합니다.

		        for (var i = 0; i < resumelist.options.length; i++) {
		            var option = document.createElement('option');
		            option.value = resumelist.options[i].value;
		            option.text = resumelist.options[i].text;
		            select.appendChild(option);
		        }
		    };
		}
    </script>
</head>
<jsp:include page="../../header.jsp"/>
<body>
	<div class="container">
	    <div class="summary-details">
			<div class="left-column">
				<table class="main-column">
					<tr>
						<td colspan="2">
		            		<h2>
		            			<div class="headerform">
		            				<strong> ${jobinfoData.jobtitle}</strong>&nbsp;
		            				<%-- 
		            				<div id="scribe_button">
			            				<button type="button" id="scribed_button" class="girBtn girBtnFav scribeButton" onclick="scribed_p('${jobinfoData.cuserid}', 'T')">
			            					<span>관심 기업</span>
			            				</button>
		            				</div>
		            				--%>
	            				</div>
							</h2>
						</td>
					</tr>
					<tr>
						<td>
							<strong>담당업무:</strong> ${jobinfoData.position}
						</td>
						<td></td>
						<td>
							<strong>스킬:</strong> ${jobinfoData.skills}
						</td>
					</tr>
					<tr>
						<td>
							<strong>경력:</strong>
				                <c:choose>
				                    <c:when test="${jobinfoData.prsup == '0'}">신입/무관</c:when>
				                    <c:when test="${jobinfoData.prsup == '1'}">1-3년</c:when>
				                    <c:when test="${jobinfoData.prsup == '2'}">3-5년</c:when>
				                    <c:when test="${jobinfoData.prsup == '3'}">5-10년</c:when>
				                    <c:when test="${jobinfoData.prsup == '4'}">10년 이상</c:when>
				                    <c:otherwise>미정</c:otherwise>
				                </c:choose>
						</td>
						<td></td>
						<td>
							<strong>학력:</strong>
				                <c:choose>
				                    <c:when test="${jobinfoData.educa == '0'}">고졸</c:when>
				                    <c:when test="${jobinfoData.educa == '1'}">전문대졸</c:when>
				                    <c:when test="${jobinfoData.educa == '2'}">대졸</c:when>
				                    <c:when test="${jobinfoData.educa == '3'}">석사</c:when>
				                    <c:when test="${jobinfoData.educa == '4'}">박사</c:when>
				                    <c:otherwise>미정</c:otherwise>
				                </c:choose>
						</td>
					</tr>
					<tr>
						<td>
							<strong>고용 형태:</strong>
				                <c:choose>
				                    <c:when test="${jobinfoData.wrkty == '0'}">정규직</c:when>
				                    <c:when test="${jobinfoData.wrkty == '1'}">계약직</c:when>
				                    <c:when test="${jobinfoData.wrkty == '2'}">기간제</c:when>
				                    <c:otherwise>미정</c:otherwise>
				                </c:choose>
						</td>
						<td></td>
						<td>
							<strong>급여:</strong>
							    <c:choose>
							        <c:when test="${jobinfoData.salary == 0}">
							            회사 내규에 따름 - 면접 후 결정
							        </c:when>
							        <c:otherwise>
							            ${jobinfoData.salary}
							        </c:otherwise>
							    </c:choose>
						</td>
					</tr>
					<tr>
						<td>
							<strong>근무 지역:</strong> ${jobinfoData.loc01}
						</td>
						<td></td>
						<td>
							<strong>근무 요일/시간:</strong> ${jobinfoData.wrktm}
						</td>
					</tr>
				</table>
	        </div>
	    </div>
	</div>
	
    <div class="container">
        <div class="tabs">
	        <a href="javascript:void(0);" onclick="fnMove(1)">기업 소개</a>
	        <a href="javascript:void(0);" onclick="fnMove(2)">담당업무 및<br>자격 요건</a>
	        <a href="javascript:void(0);" onclick="fnMove(3)">근무 조건</a>
	        <a href="javascript:void(0);" onclick="fnMove(4)">복지 및 혜택</a>
	        <a href="javascript:void(0);" onclick="fnMove(5)">접수기간 및 방법</a>
	    </div>

        <div id="section1">
            <div class="section-title">기업 소개</div>
            <div class="job-details">
            	<form method="get" id="infofrm">
            		<input type="hidden" name="csrno" value="${jobinfoData.csrno }">
            		<input type="hidden" name="jobno" value="${jobinfoData.jobno }">
            	</form>
                <p><strong>공고 제목 : ${jobinfoData.jobtitle}</strong></p>
                <p><strong>공고 부제목 : ${jobinfoData.jobsubtitle}</strong></p><br><br>
				<p><strong>${jobinfoData.content}</strong></p>
			</div>
		</div>
		
		<div id="section2">
            <div class="section-title">담당업무 및 자격 요건</div>
            <div class="job-details">
                <p><strong>담당업무 : ${jobinfoData.position}</strong></p><br>
				<p><strong>스킬</strong></p>
					<ul>
						<li>${jobinfoData.skills}</li>
					</ul>
				<p><strong>자격 요건</strong></p>
				<ul>
					<li>경력 :
						<c:choose>
							<c:when test="${jobinfoData.prsup == '0'}">신입/무관</c:when>
							<c:when test="${jobinfoData.prsup == '1'}">1-3년</c:when>
							<c:when test="${jobinfoData.prsup == '2'}">3-5년</c:when>
							<c:when test="${jobinfoData.prsup == '3'}">5-10년</c:when>
							<c:when test="${jobinfoData.prsup == '4'}">10년 이상</c:when>
							<c:otherwise>미정</c:otherwise>
						</c:choose>
					</li>
					<li>학력 :
						<c:choose>
							<c:when test="${jobinfoData.educa == '0'}">고졸</c:when>
							<c:when test="${jobinfoData.educa == '1'}">전문대졸</c:when>
							<c:when test="${jobinfoData.educa == '2'}">대졸</c:when>
							<c:when test="${jobinfoData.educa == '3'}">석사</c:when>
							<c:when test="${jobinfoData.educa == '4'}">박사</c:when>
							<c:otherwise>미정</c:otherwise>
						</c:choose>
					</li>
					<li>${jobinfoData.duties}</li><br>
				</ul>
				<p><strong>우대 조건</strong></p>
				<ul>
					<li>${jobinfoData.pfntcd}</li>
				</ul>
            </div>
		</div>
		
		<div id="section3">
	        <div class="section-title">근무 조건</div>
	        <div class="job-details">
	            <ul>
	                <li>고용 형태 : 
						<c:choose>
							<c:when test="${jobinfoData.wrkty == '0'}">정규직</c:when>
	                        <c:when test="${jobinfoData.wrkty == '1'}">계약직</c:when>
	                        <c:when test="${jobinfoData.wrkty == '2'}">기간제</c:when>
							<c:otherwise>미정</c:otherwise>
						</c:choose>
					</li>
					<li>급여 : 
	                    <c:choose>
	                        <c:when test="${jobinfoData.salary == null or jobinfoData.salary == ''}">회사 내규에 따름 - 면접 후 결정</c:when>
	                        <c:otherwise>${jobinfoData.salary}</c:otherwise>
	                    </c:choose>
					</li>
	                <li>근무 지역 : ${jobinfoData.loc01}</li>
	                <li>근무 요일/시간 : ${jobinfoData.wrktm}</li>
	            </ul>
	        </div>
	    </div>
		
		<div id="section4">
	        <div class="section-title">복지 및 혜택</div>
	        <div class="job-details">
	            <ul>
	                <li>${jobinfoData.benefits}</li>
	            </ul>
	        </div>
	    </div>
     	
		<div id="section5">
	        <div class="section-title">접수기간 및 방법</div>
	        <div class="job-details">
	            <ul>
	            	<fmt:formatDate value= "${jobinfoData.ddate}" pattern="yyyy-MM-dd" var="dateValue"/>
	                <li>접수기간/방법 : ${dateValue} / ${jobinfoData.pamoa}</li>
	                <li>모집 인원 : ${jobinfoData.recno}</li>
	                <li>채용 절차 : ${jobinfoData.rcmpcd}</li>
	            </ul>
	        </div>
	    </div>
		
		<br>
		<div class="apply-button">
			<c:set var="status" value="${status }"/>
			<c:set var="daycha" value="${jobinfoData.daycha }"/>
			<c:if test="${status == 'p'}">
				<c:if test="${daycha >= 0 }">
					<button type="button" onclick="openResumePopup('${jobinfoData.cuserid }','${jobinfoData.csrno }','${jobinfoData.jobno }');">지원 하기</button>
				</c:if>
				<c:if test="${daycha < 0 }">
					<button type="button">공고 마감</button>
				</c:if>
				<button type="button" onclick="move_mylist();">목록 보기</button>
			</c:if>
			<c:if test="${status == 'ps'}">
				<button type="button" onclick="move_mylist();">목록 보기</button>				
			</c:if>
			<c:if test="${status == 'cs'}">
					<button type="button" onclick="move_show();">수정 하기</button>
		            <button type="button" onclick="move_list();">공고 목록 보기</button> <%-- 기존 가는 화면 --%>
			</c:if>
			<c:if test="${status == 'c'}">
	            <button type="button" onclick="move_jobpost();">공고 목록 보기</button> <%-- jobpost 화면 이동 --%>
			</c:if>
		</div>
	</div>
	
	<div class="container">
			<div class="company-info">
				<table class="company-info-left">
					<tr>
						<td>
							<c:if test="${empty companyInfo.imgno }">
                           		<img src="../../../resources/img/company.jpg" style="width: 250px; height: 165px;">
                           	</c:if>
                           	<c:if test="${not empty companyInfo.imgno }">
								<img src="show_coinfo_img?writer=${companyInfo.cuserid}&imgno=t&imggubun=b" style="width: 250px; height: 165px;">
							</c:if>
						</td>
						<td>
							<table class="company-info-right">
								<tr>
									<td>
										<h3>기업 정보</h3>
									</td>
								</tr>
								<tr>
									<td>
										<strong>기업명:</strong> ${companyInfo.cusnm}
									</td>
								</tr>
								<tr>
									<td>
										<strong>대표자:</strong> ${companyInfo.bossnm}
									</td>
								</tr>
								<tr>
									<td>
										<strong>업종:</strong> ${companyInfo.deptnm}
									</td>
								</tr>
								<tr>
									<td>
										<strong>사원수:</strong> ${companyInfo.emnum} 명
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
