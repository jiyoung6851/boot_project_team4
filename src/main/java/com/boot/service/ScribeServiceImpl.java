package com.boot.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ScribetbDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ScribeService")
public class ScribeServiceImpl implements ScribeService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void scribe_p_insert(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl scribe_p_insert");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		dao.scribe_p_insert(param);
	}

	@Override
	public void scribe_p_delete(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl scribe_p_delete");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		dao.scribe_p_delete(param);
	}
}