function search() {
	var searchForm = $("#searchForm");
	
	if(searchForm.find("option:selected").val() != "" && !searchForm.find("input[name='keyword']").val()){
		alert("키워드를 입력하세요.");
		return false;
	}
	searchForm.attr("action", "scribe").submit();
}