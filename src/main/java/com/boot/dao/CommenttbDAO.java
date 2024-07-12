package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.CommenttbDTO;

//실행시 매퍼파일을 읽어 들이도록 지정
@Mapper
public interface CommenttbDAO {
	public void save(HashMap<String, String> param);
	public ArrayList<CommenttbDTO> findAll(HashMap<String, String> param);
}