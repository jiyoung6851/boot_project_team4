<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function fnMove() {
        var offset = $("#section1").offset();
        $('html, body').animate({
            scrollTop: offset.top
        }, 400); //0.4초 느리게
    }
</script>
<center>
	<input type="hidden" id="job_s" name="job_s" value="${job_s }">
	<input type="hidden" id="loc_s" name="loc_s" value="${loc_s }">
	<input type="hidden" id="career_s" name="career_s" value="${career_s }">
	<input type="hidden" id="edu_s" name="edu_s" value="${edu_s }">
	<input type="hidden" id="wrkty_s" name="wrkty_s" value="${wrkty_s }">
	<table style="width: 80%; border-collapse: collapse;">
		<c:forEach items="${joblist }" var="jobs" >
			<tr onclick="location.href='/recruitshowform?writer=${jobs.cuserid}&csrno=${jobs.csrno}&jobno=${jobs.jobno}'">
				<td width="20%;" rowspan="4" style="border-bottom: 1px solid #ccc;">
					<a href="/recruitshowform?writer=${jobs.cuserid}&csrno=${jobs.csrno}&jobno=${jobs.jobno}" style="text-decoration: none; color: black;" target="_blank">${jobs.cusnm } </a>
				</td>
				<td width="20%;">${jobs.jobtitle }</td>
				<td width="20%;">연봉: 회사규내에 따름</td>
				<c:if test="${jobs.daycha > 0}">
					<td width="20%;" rowspan="2" align="center"><font color="red">D-${jobs.daycha }</font></td>
					
				</c:if>
				<c:if test="${jobs.daycha <= 0}">
					<td width="20%;" rowspan="2" align="center"><font color="red">마감되었습니다.</font></td>
				</c:if>
			</tr>
			<tr onclick="location.href='/recruitshowform?writer=${jobs.cuserid}&csrno=${jobs.csrno}&jobno=${jobs.jobno}'">
				<td rowspan="2">담당업무: ${jobs.position }</td>
				<td rowspan="3" style="border-bottom: 1px solid #ccc;">${jobs.wrktm }</td>
			</tr>
			<tr onclick="location.href='/recruitshowform?writer=${jobs.cuserid}&csrno=${jobs.csrno}&jobno=${jobs.jobno}'">
				<fmt:formatDate value="${jobs.adate }" pattern="yy/MM/dd" var="adate"/>
				<td align="center">${adate } 등록</td>
			</tr>
			<tr onclick="location.href='/recruitshowform?writer=${jobs.cuserid}&csrno=${jobs.csrno}&jobno=${jobs.jobno}'">
				<td style="border-bottom: 1px solid #ccc;">${jobs.careernm } | ${jobs.edunm }</td>
				<%-- <td style="border-bottom: 1px solid #ccc;">09:00~18:00</td> --%>
				<fmt:formatDate value="${jobs.ddate }" pattern="yy/MM/dd" var="ddate"/>
				<td style="border-bottom: 1px solid #ccc;" align="center">${ddate } 마감</td>
			</tr>
		</c:forEach>
	</table>
	<div class="div_page">
		<ul>
			<c:if test="${pageMaker.prev }">
				<li class="paginate_button" style="padding: 0px; border-bottom: 0px; ">
					<a class="page-link" href="${pageMaker.startpage - 1}">Prev</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startpage }" end="${pageMaker.endpage }">
				<li class="paginate_button ${pageMaker.cri.pageNum == num?'active':'' }" style="padding: 0px; border-bottom: 0px; ">
					<a class="page-link" href="${num}">${num }</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next }">
				<li class="paginate_button" style="padding: 0px; border-bottom: 0px;">
					<a class="page-link" href="${pageMaker.endpage + 1}">Next</a>
				</li>
			</c:if>
		</ul>
    </div>
    <form id="actionForm" method="get">
		<input type="hidden" id="pageNum" name="pageNum" value="${pageMaker.cri.pageNum }">
		<input type="hidden" id="amount" name="amount" value="${pageMaker.cri.amount }">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		<input type="hidden" name="type" value="${pageMaker.cri.type}">
	</form>
</center>

<script type="text/javascript">
	$(".paginate_button a").on("click", function(e) {
		e.preventDefault();
		
		//선택한 페이지 번호만 active 유지
	    $(".paginate_button").removeClass("active");
	    $(this).parent().addClass("active");
	    
	    //컨트롤러단에 넘길 값들
		var job_s = $("#job_s").val();
		var loc_s = $("#loc_s").val();
		var career_s = $("#career_s").val();
		var edu_s = $("#edu_s").val();
		var wrkty_s = $("#wrkty_s").val();
		
		var pageNum = $(this).attr("href");
		var amount = $('#amount').val();
		//컨트롤러단에 넘길 값들
		$("input[name='pageNum']").val($(this).attr("href")); //현재 페이지 값 삽입
		
		$.ajax({
			type: 'get',
			data: { job_s: job_s, loc_s:loc_s,  career_s: career_s, edu_s: edu_s, wrkty_s:wrkty_s, pageNum: pageNum, amount: amount},
			url: '/jobpostPageAjax',
			success: function (result) {
	            //console.log("result: " + JSON.stringify(result));
	            $("#postArea").html(result); // 수정: 결과를 #postArea에 출력
	            fnMove();
	        },
			error: function(xhr, status, error) {
		        console.error("Ajax 요청 실패:", status, error);
		    }
		});
	}); //end paginate_button
</script>