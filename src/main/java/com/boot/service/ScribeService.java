package com.boot.service;

import java.util.HashMap;
import java.util.List;

import com.boot.dto.ScribetbDTO;

public interface ScribeService {
	/* 스크랩 관련 영역 */
	public void scribe_p_insert(HashMap<String, String> param);
	public void scribe_p_delete(HashMap<String, String> param);
	public ScribetbDTO scribe_p_select(HashMap<String, String> param);
	/* 스크랩 관련 영역*/
	
	public List<ScribetbDTO> allselect_p(HashMap<String, String> param);
	public int allcount_p(HashMap<String, String> param);
}
