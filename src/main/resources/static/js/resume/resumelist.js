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