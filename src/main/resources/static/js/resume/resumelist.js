document.getElementById('addButtonDiv').addEventListener('click', function() {
    window.location.href = 'resume';
});

function update_resume(prono, imgno) {
	document.getElementById('resumefrm').action = 'resumeselect';
	
	$("#prono").val(prono);
	$("#imgno").val(imgno);
	
	document.getElementById('resumefrm').submit();
}

function delete_resume(prono, imgno) {
	document.getElementById('resumefrm').action = 'deleteresume';
	
	$("#prono").val(prono);
	$("#imgno").val(imgno);
	
	document.getElementById('resumefrm').submit();
}

function setRepresentative(prono, imgno) {
    $.ajax({
        url: '/setRepresentative',
        method: 'POST',
        data: {
            prono: prono,
            imgno: imgno
        },
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        dataType: 'json',
        success: function(response) {
            if (response.success) {
                // 기존 대표 이력서 해제
                document.querySelectorAll('.resume-card[data-representative="true"]').forEach(card => {
                    card.removeAttribute('data-representative');
                });

                // 새로운 대표 이력서 설정
                const newRepCard = document.querySelector(`.resume-card img[src*="${imgno}"]`).closest('.resume-card');
                newRepCard.setAttribute('data-representative', 'true');

                alert('대표 이력서로 설정되었습니다.');
            } else {
                alert('대표 이력서 설정에 실패했습니다.');
            }
        },
        error: function() {
            alert('대표 이력서 설정 중 오류가 발생했습니다.');
        }
    });
}