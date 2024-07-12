package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.CommenttbDTO;

public interface CommentService {
	public void save(HashMap<String, String> param);
	public ArrayList<CommenttbDTO> findAll(HashMap<String, String> param);
}
