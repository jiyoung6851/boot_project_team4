package com.boot.dao;

import java.util.HashMap;
import java.util.List;

import com.boot.dto.CalltbDTO;

public interface CalltbDAO {
	public List<CalltbDTO> calllist(HashMap<String, String> param); //일반회원 문의 내역
	public int getTotalCount(HashMap<String, String> param);
	
	public CalltbDTO callselect(HashMap<String, String> param);
	public void callwrite(HashMap<String, String> param);
	
	/* 관리자 계정 */
	public List<CalltbDTO> CallAllList(HashMap<String, String> param);
	public int getTotalCountAll(HashMap<String, String> param);
	public CalltbDTO Calldetail(HashMap<String, String> param);
}
