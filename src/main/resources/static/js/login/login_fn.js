function chkspace(inputId) {
    if (inputId.indexOf(' ') !== -1) {
        return true; // 공백이 포함되어 있음
    } else {
        return false; // 공백이 포함되어 있지 않음
    }
}

function id_chk(id, gubun) {
	var id = $("#"+id).val();

	if(id == '') {
		alert('아이디를 입력하세요');
		//$("#" + id).focus();
		return false;
	}
	
	if(chkspace(id)) {
		alert('아이디에 공백이 포함되었습니다.');
		return false;
	}
	
	$.ajax({
		type: 'post',
		data: {id: id, gubun: gubun},
		url: "/idchk",
		success: function(response) {
			console.log("response: "+response);
			var msg = JSON.parse(response);
			alert(msg.message);
			
			if(msg.flag == 't') {
				$("#"+msg.gubun+"button").removeClass("disabled");
			} else {
				if(!$("#"+msg.gubun+"button").hasClass("disabled")) {
					$("#"+msg.gubun+"button").addClass("disabled");
				}
			}
		},
		error: function() {
			console.log("ajax 에러...");
		}
	});
}

function inputtextreset(form) {
	const inputs = form.find('input');
	
	// 각 input 요소의 값을 빈 문자열로 설정
    inputs.each(function() { 
		$(this).val(''); 
	});
}

//로그인
function geloform() {
	$("#generalBtn").addClass("active");
	$("#businessBtn").removeClass("active");
	
	$("#generalForm").addClass("visible");
	$("#businessForm").removeClass("visible");
	
	inputtextreset($("#generalForm"));
}

function buloform() {
	$("#generalBtn").removeClass("active");
	$("#businessBtn").addClass("active");
	
	$("#generalForm").removeClass("visible");
	$("#businessForm").addClass("visible");
	
	inputtextreset($("#businessForm"));
}

//회원 가입
function geneform() {
	document.getElementById('generalBtn').classList.add('active');
	document.getElementById('generalForm').classList.remove('hidden');
    
	document.getElementById('businessForm').classList.add('hidden');
    document.getElementById('businessBtn').classList.remove('active');
	
	if(!$("#pbutton").hasClass("disabled")) {
		$("#pbutton").addClass("disabled");
	}
	
	inputtextreset($("#generalForm"));
}

function busiform(){
	document.getElementById('generalBtn').classList.remove('active');
	document.getElementById('generalForm').classList.add('hidden');
	
    document.getElementById('businessForm').classList.remove('hidden');
    document.getElementById('businessBtn').classList.add('active');
	
	if(!$("#cbutton").hasClass("disabled")) {
		$("#cbutton").addClass("disabled");
	}
	
	inputtextreset($("#businessForm"));
}