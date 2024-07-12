package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ResumetbDAO;
import com.boot.dto.ResumetbDTO;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void resumesave(HashMap<String, String> param) {
    	ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
    	dao.resumesave(param);
    }

	@Override
	public ArrayList<ResumetbDTO> resumelist(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		ArrayList<ResumetbDTO> list1 = dao.resumelist(param);
		return list1;
	}

	@Override
	public ResumetbDTO resumeselect(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		ResumetbDTO update = dao.resumeselect(param);
		return update;
	}

	@Override
	public int getMaxProno(String puserid) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		return dao.getMaxProno(puserid);
	}

	@Override
	public void resumedelete(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		dao.resumedelete(param);
	}

	@Override
	public ResumetbDTO resume_view(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		ResumetbDTO dto = dao.resume_view(param);
		return dto;
	}

	@Override
    public ArrayList<ResumetbDTO> getResumeList(String puserid) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
        ArrayList<ResumetbDTO> list = dao.getResumeList(puserid);
        return list;
    }

	@Override
	public int getTotalCount(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		
		return dao.getTotalCount(param);
	}

	@Override
	public void resumeupdate(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		dao.resumeupdate(param);
	}
}