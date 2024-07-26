$(document).ready(function() {
    
    let anotherFlag = true;
    let graduationFlag = true;

    const $skillButtons = $('.skill-button');
    const $selectedSkillsContainer = $('.selected-skills');
    const $resetButton = $('.reset-button');
    const $anotherMajorBtn = $('#anotherMajorBtn');
    const $graduationWorkBtn = $('#graduationWorkBtn');
	
	var selectedSkills = [];
	
	var selectedCount = $('.selected-skill');
	if(selectedCount.length > 0){
		selectedCount.each(function() {
    		var selectedNo = $(this).data('no');
    		$('.skill-button[data-no="' + selectedNo + '"]').addClass('selected');
    		selectedSkills.push(selectedNo);
    	});
		updateSelectedCount();
	}
	console.log('selectedSkills: '+selectedSkills);
	console.log('selectedSkills length: '+selectedSkills.length);
	
    $skillButtons.on('click', function(event) {
        event.preventDefault();
        const skill = $(this).text();
		const no = $(this).val();
		console.log('skill: '+skill);
		console.log('no: '+no);
		
        if (selectedSkills.some(s => parseInt(s) === parseInt(no))) {
			console.log("값이 있음");
            selectedSkills = selectedSkills.filter(s => parseInt(s) !== parseInt(no));
            $(this).removeClass('selected');
            $(`.selected-skills button[data-no="${no}"]`).remove();
        } else if (selectedSkills.length < 36) {
			console.log("값이 없음");
            selectedSkills.push(no);
            $(this).addClass('selected');

            const $selectedButton = $('<button>')
                .text(skill)
                .attr('data-skill', skill)
				.attr('data-no', no)
				.attr('name', 'skillbutton')
                .addClass('selected-skill')
                .on('click', function() {
                    selectedSkills = selectedSkills.filter(s => parseInt(s) !== parseInt(no));
                    //$(this).removeClass('selected');
					$(`#show-skill button[data-no="${no}"]`).removeClass('selected');
                    $selectedButton.remove();
                    updateSelectedCount();
                });
            $selectedSkillsContainer.append($selectedButton);
        }
        updateSelectedCount();
		console.log('selectedSkills: '+selectedSkills);
    });
	
    $('#photo').on('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                $('#photoPreview').html(`<img src="${e.target.result}" alt="미리보기 이미지">`);
            }
            reader.readAsDataURL(file);
        }
    });

    $anotherMajorBtn.on('click', function() {
        if (anotherFlag) {
            addAnotherMajor();
        } else {
            removeAnotherMajor();
        }
        anotherFlag = !anotherFlag;
    });

    $graduationWorkBtn.on('click', function() {
        if (graduationFlag) {
            addGraduationWork();
        } else {
            removeGraduationWork();
        }
        graduationFlag = !graduationFlag;
    });

    $resetButton.on('click', function(event) {
        event.preventDefault();
        selectedSkills = [];
        $selectedSkillsContainer.empty();
        $skillButtons.removeClass('selected');
        updateSelectedCount();
    });
	
    function updateSelectedCount() {
        const count = selectedSkills.length;
        $('#selectedCount').text(count);
    }
});

function saveResume() {
	var skillno = "";
	
	if(document.getElementsByName("skillbutton").length > 0) {
		var sb = document.getElementsByName("skillbutton");
		
		for(var i=0; i<sb.length; i++) {
			skillno += sb[i].dataset.no+",";
		}
		$("#skillno").val(skillno.slice(0, -1));
	}
	/*
	if(document.getElementsByName("birdy")[0].value == '') {
		alert('생일을 입력하세요.');
		return;
	}
	*/
	//document.resumefrm.submit();
}

function updateResume() {
	var skillno = "";
	
	if(document.getElementsByName("skillbutton").length > 0) {
		var sb = document.getElementsByName("skillbutton");
		
		for(var i=0; i<sb.length; i++) {
			skillno += sb[i].dataset.no+",";
		}
		$("#skillno").val(skillno.slice(0, -1));
	}
	
	document.resumeupfrm.submit();
}

function removeskill(button) {
	var no = $(button).data('no'); //해당 버튼의 data-no의 값 가져오기
	$(button).remove(); //해당 버튼 삭제

	// 해당 selectedNo 값을 가진 skill-button에서 selected 클래스를 제거
	$('.skill-button[data-no="' + no + '"]').removeClass('selected');

	//삭제 후 버튼 수량 체크
	var selectedCount = $('.selected-skill').length;
	$('#selectedCount').text(selectedCount);
}

function Resumelist() {
	resumeupfrm.action = "return_list";
	resumeupfrm.submit();
}

function formatDate(event){
	// Get the input element
	const input = event.target;

	// Get the value entered so far
	let value = input.value;

	// Remove any non-numeric characters
	value = value.replace(/\D/g, '');

	// Check the length to apply the format
	if (value.length >= 8) {
		// Format the value as YYYY.MM.DD
		value = value.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');

		// Update the input field with formatted value
		input.value = value;
	}
}

function formatDateym(event) {
	// Get the input element
	const input = event.target;

	// Get the value entered so far
	let value = input.value;

	// Remove any non-numeric characters
	value = value.replace(/\D/g, '');

	// Check the length to apply the format
	if (value.length >= 8) {
		// Format the value as YYYY.MM
		value = value.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');

		// Update the input field with formatted value
		input.value = value;
	}
}

function formatPhone(event){
	// Get the input element
	const input = event.target;

	// Get the value entered so far
	let value = input.value;

	// Remove any non-numeric characters
	value = value.replace(/\D/g, '');

	// Check the length to apply the format
	if (value.length >= 11) { //ex) 01011112222 -> 010-1111-2222
		// Format the value as YYYY.MM.DD
		value = value.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');

		// Update the input field with formatted value
		input.value = value;
	}
	
	if(value.length >= 10) { //ex) 0101112222 -> 010-111-2222
		// Format the value as YYYY.MM.DD
		value = value.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');

		// Update the input field with formatted value
		input.value = value;
	}
}

/* 카카오 주소 팝업창 api 스크립트 수정 금지 */
function open_kakao_api() {
	new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                //document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                //document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            //document.getElementById('sample6_postcode').value = data.zonecode; //우편번호
            document.getElementById("addRess").value = addr; //주소
            // 커서를 상세주소 필드로 이동한다.
            //document.getElementById("sample6_detailAddress").focus(); //상세주소 입력칸
        }
    }).open();
}