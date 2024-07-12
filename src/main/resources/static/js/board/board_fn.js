function commentWrite() {
	var boardno = $("#boardno").val();
	var replyid = $("#replyid").val();
	var content = $("#content").val();
	
	$.ajax({
		type: 'post',
		data: {replyid: replyid, content: content, boardno: boardno },
		url: '/comment/Ajaxsave',
		success: function(result) {
			//console.log("result");
			//입력칸 초기화
			$("#replyid").val("");
			$("#content").val("");
			//댓글 화면 출력
			$("#comment-list").html(result);
		},
		error: function() {
			console.log("ajax 에러...");
		}
	});
}

function commentShow() {
	var boardno = $("#boardno").val();
	var replyid = $("#replyid").val();
	var content = $("#content").val();
	
	$.ajax({
		type: 'post',
		data: {replyid: replyid, content: content, boardno: boardno },
		url: '/comment/AjaxShow',
		success: function(result) {
			$("#comment-list").html(result);
		},
		error: function() {
			console.log("ajax 에러...");
		}
	});
}