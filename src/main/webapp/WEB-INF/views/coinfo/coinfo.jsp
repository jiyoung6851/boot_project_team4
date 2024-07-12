<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coinfo/coinfo.js"></script>
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/coinfo/coinfo.css">

<script>
	$(document).ready(function() {
	    var message = "${message}";
	    if (message) {
	        alert(message);
	    }
	});
	

   function insertSequeno() {
       var deptnm = document.getElementsByName("deptnm")[0];
       var sequeno = document.getElementsByName("sequeno")[0];
       var selectedValue = deptnm.value;
	   

      if (selectedValue == "db") {
          sequeno.value = "1";
      } else if (selectedValue == "back") {
          sequeno.value = "2";
      } else if (selectedValue == "front") {
          sequeno.value = "3";
      }

   }
</script>

</head>
<jsp:include page="../../header.jsp"/>
<body>
<div class="container">
    <c:choose>
        <c:when test="${not empty coinfotb.cuserid}">
            <div class="form-header">
                <h2>기업 정보</h2>
            </div>
			
			    <form method="post" action="Coinmodify" enctype="multipart/form-data">
				<table>
					<tr>
						<!--아이디는 세션에서 가지고 옴-->
		                <td>아이디</td>
		                <td>${coinfotb.cuserid}</td>
		            </tr>       
					<tr>
						<td>이미지 첨부</td>
						<td>
							<!-- 썸네일 이미지 표시 -->
							<c:if test="${not empty imgtb}">
								<%-- <img src="${pageContext.request.contextPath}/${imgtb.uploadpath}/${imgtb.uuid}_${imgtb.filename}" alt="썸네일 이미지" style="width: 100px; height: 100px;"> --%>
								<img src="show_coinfo_img?writer=${coinfotb.cuserid}&imgno=t&imggubun=s" alt="썸네일 이미지" style="width: 100px; height: 100px;">
							</c:if>
							<input type="file" name="file" accept="image/*">
						</td>
					</tr>
<!--					<tr>-->
<!--						<td>이미지 첨부</td>-->
<!--						<td><input type="file" name="file" accept="image/*"></td>-->
<!--					</tr>-->
						<!--기업정보번호 1로 고정-->
						<!--기업정보번호-->
		                <input type="hidden" name="csrno" value="1">
					<tr>
		                <td>기업 이름</td>
		                <td><input type="text" name="cusnm" value="${coinfotb.cusnm}"></td>
		            </tr>         
					<tr>
		                <td>대표자명</td>
		                <td><input type="text" name="bossnm" value="${coinfotb.bossnm}"></td>
		            </tr>         
					<tr>
		                <td>지역</td>
		                <td><input type="text" name="loc01" value="${coinfotb.loc01}"></td>
		            </tr>         
					<tr>
		                <td>상세주소</td>
						<div class="addr">
			                <td>
								<input type="text" name="loc02" value="${coinfotb.loc02}">
							</td>
						</div>
		            </tr>         
					<tr>
		                <td>업력</td>
		                <td><input type="text" name="indue" value="${coinfotb.indue}"></td>
		            </tr>         
					<tr>
                        <td>업종명</td>
                        <td class="select-deptnm">
							<!--sequeno 값에 따라 option selected 함-->
                            <select name="deptno" onchange="insertSequeno()">
                            	<c:forEach items="${sector }" var="sector">
                            		<option value="${sector.deptno }" ${sector.deptno == coinfotb.deptno ? 'selected' : ''}>${sector.deptnm }</option>
                            	</c:forEach>
                            </select>
                        </td>
                    </tr>      
					<tr>
		                <td>사원수</td>
		                <td><input type="text" name="emnum" value="${coinfotb.emnum}"></td>
		            </tr>         
					<tr>
		                <td>사업내용</td>
		                <td><input type="text" name="binfo" value="${coinfotb.binfo}"></td>
		            </tr>         
					<tr>
		                <td colspan="2" class="coinfo_adate">
							<fmt:formatDate value="${coinfotb.adate}" pattern="yyyy-MM-dd HH시 mm분" />에 작성됨
						</td>
		            </tr>         
				</table>
	                <button type="submit" id="modifybutton" class="modifycomplete">수정하기</button>
            </form>
        </c:when>
        
        <c:otherwise>
            <div class="form-header">
                <h2>기업 정보 등록</h2>
            </div>
			<form method="post" action="insert" enctype="multipart/form-data">
			    <table>
			        <tr>
			            <td>아이디</td>
			            <td>
			                <%=session.getAttribute("id") %>
			                <input type="hidden" name="cuserid" value="<%=session.getAttribute("id") %>">
			            </td>
			        </tr>
					<tr>
						<td>이미지 첨부</td>
						<td>
							<!-- 썸네일 이미지 표시 -->
							<c:if test="${not empty imgtb}">
								<img src="show_coinfo_img?writer=${coinfotb.cuserid}&imgno=t&imggubun=s" alt="썸네일 이미지" style="width: 100px; height: 100px;">
							</c:if>
							<input type="file" name="file" accept="image/*">
						</td>
					</tr>
<!--			        <tr>-->
<!--			            <td>이미지 첨부</td>-->
<!--			            <td><input type="file" name="file" accept="image/*"></td>-->
<!--			        </tr>-->
						<!--기업정보번호 1로 고정-->
						<!--기업정보번호-->
		                <input type="hidden" name="csrno" value="1">
			        <tr>
			            <td>기업 이름</td>
			            <td><input type="text" name="cusnm" placeholder="기업 이름을 입력해주세요" required></td>
			        </tr>
			        <tr>
			            <td>대표자명</td>
			            <td><input type="text" name="bossnm" placeholder="대표자명을 입력해주세요" required></td>
			        </tr>
			        <tr>
			            <td>지역</td>
			            <td><input type="text" name="loc01" placeholder="지역을 입력해주세요" required></td>
			        </tr>
			        <tr>
						<td>상세주소</td>
				            <td>
								<div class="addr">
									<input type="text" id="loc02" name="loc02" placeholder="상세주소를 입력해주세요" required>
									<button type="button" class="postal-button" onclick="open_kakao_api()">우편번호 찾기</button>
								</div>
							</td>
			        </tr>
			        <tr>
			            <td>업력</td>
			            <td><input type="text" name="indue" placeholder="업력을 입력해주세요" required></td>
			        </tr>
			        <tr>
			            <td>업종명</td>
			            <td class="select-deptnm">
			                <select name="deptno" onchange="insertSequeno()">
                            	<c:forEach items="${sector}" var="sector">
                            		<option value="${sector.deptno }">${sector.deptnm }</option>
                            	</c:forEach>
                            </select>
			            </td>
			        </tr>
			        <tr>
			            <td>사원수</td>
			            <td><input type="text" name="emnum" placeholder="사원수를 입력해주세요" required></td>
			        </tr>
			        <tr>
			            <td>사업내용</td>
			            <td><input type="text" name="binfo" placeholder="사업내용을 입력해주세요" required></td>
			        </tr>
			    </table>
			    <button type="submit" id="insertbutton" class="insertcomplete">등록하기</button>
			</form>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>