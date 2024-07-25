package com.boot.service;

import java.util.HashMap;

import com.boot.dto.ScribetbDTO;

public interface ScribeService {
	public void scribe_p_insert(HashMap<String, String> param);
	public void scribe_p_delete(HashMap<String, String> param);
	public ScribetbDTO scribe_p_select(HashMap<String, String> param);
}
