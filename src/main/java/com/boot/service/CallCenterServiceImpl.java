package com.boot.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.CalltbDAO;
import com.boot.dto.CalltbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("CallCenterService")
public class CallCenterServiceImpl implements CallCenterService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CalltbDTO> calllist(HashMap<String, String> param) {
		log.info("@# calllist");
		CalltbDAO dao = sqlSession.getMapper(CalltbDAO.class);
		return dao.calllist(param);
	}
	
	public void callwrite(HashMap<String, String> param) {
		log.info("@# callwrite");
		
		CalltbDAO dao = sqlSession.getMapper(CalltbDAO.class);
		dao.callwrite(param);
	}

	@Override
	public int getTotalCount(HashMap<String, String> param) {
		log.info("@# getTotalCount");
		
		CalltbDAO dao = sqlSession.getMapper(CalltbDAO.class);
		
		return dao.getTotalCount(param);
	}

	@Override
	public CalltbDTO callselect(HashMap<String, String> param) {
		log.info("@# callselect");
		
		CalltbDAO dao = sqlSession.getMapper(CalltbDAO.class);
		
		return dao.callselect(param);
	}
}