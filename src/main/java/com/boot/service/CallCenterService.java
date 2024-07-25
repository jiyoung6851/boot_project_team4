package com.boot.service;

import java.util.HashMap;
import java.util.List;

import com.boot.dto.CalltbDTO;

public interface CallCenterService {
	//문의 내용 조회
	public List<CalltbDTO> calllist(HashMap<String, String> param);
	public int getTotalCount(HashMap<String, String> param);
	
	//문의 내용 상세 조회
	public CalltbDTO callselect(HashMap<String, String> param);
	
	public void callwrite(HashMap<String, String> param);
}