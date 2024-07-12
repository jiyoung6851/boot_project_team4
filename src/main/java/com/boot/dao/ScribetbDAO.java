package com.boot.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScribetbDAO {
	public void scribe_p_insert(HashMap<String, String> param); //일반회원 스크랩 등록
	public void scribe_p_delete(HashMap<String, String> param); //일반회원 스크랩 삭제
}
