function move_show() {
	document.getElementById('infofrm').action = 'recruitmodify';
	document.getElementById('infofrm').submit();
}

function move_list() {
	document.getElementById('infofrm').action = 'recruitlist';
	document.getElementById('infofrm').submit();
}

function move_jobpost() {
	document.getElementById('infofrm').action = 'jobpost';
	document.getElementById('infofrm').method = 'POST';
	document.getElementById('infofrm').submit();
}

function move_mylist() {
	document.getElementById('infofrm').action = "jobaplylist_p";
	document.getElementById('infofrm').method = 'POST';
	document.getElementById('infofrm').submit();
}

function deletedata(){
	if (confirm('정말 삭제하시겠습니까?')) {
		document.getElementById('infofrm').action = 'recruitdelete';
		document.getElementById('infofrm').submit();
	}
}

function ModifyItem(csrno, jobno) {
	var form = document.getElementById('recruitlist');
	
	$("#csrno").val(csrno);
	$("#jobno").val(jobno);
	
	form.action = 'recruitmodify';
	form.submit();
}

function deleteItem(csrno, jobno) {
    if (confirm('정말 삭제하시겠습니까?')) {
        var form = document.getElementById('recruitlist');

		$("#csrno").val(csrno);
		$("#jobno").val(jobno);
	
		form.action = 'recruitdelete';
        form.submit();
    }
}

function viewRecruitInfo(csrno, jobno) {
    var form = document.getElementById('recruitlist');
	
	$("#csrno").val(csrno);
	$("#jobno").val(jobno);

	form.action = 'recruitshow';
    form.submit();
}

function viewItem(csrno, jobno) {
	var form = document.getElementById('recruitlist');
	
	$("#csrno").val(csrno);
	$("#jobno").val(jobno);
	form.action = 'jobaplylist';
    form.submit();
}

function move_show2() {
    // 모달 창 표시
    document.getElementById('resumeModal').style.display = 'block';
}

function closeModal() {
    // 모달 창 닫기
    document.getElementById('resumeModal').style.display = 'none';
}

function submitApplication() {
	// 폼 제출
	var selectedResume = document.querySelector('input[name="selectedResume"]:checked');
	if (selectedResume) {
		document.getElementById('selectedResumeForm').submit();
	} else {
		alert("이력서를 선택하세요.");
	}
}

function scribed_p(writer, gubun) {
	console.log('writer: '+writer);
	
	$.ajax({
		type: 'post',
		url: '/scribed_p',
		data: {writer: writer, gubun: gubun},
		success: function(result) {
			console.log("result: " + result);
		}, error: function(){
			console.log('ajax 에러.....');
		}
		
	});
	
	if($("#scribed_button").hasClass("girBtnFav")) {
		$("#scribed_button").removeClass("girBtnFav");
		$("#scribed_button").addClass("girBtnFavOn");
	} else {
		$("#scribed_button").addClass("girBtnFav");
		$("#scribed_button").removeClass("girBtnFavOn");
	}
}