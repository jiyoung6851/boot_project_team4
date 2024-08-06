package com.boot.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.RcareerDAO;
import com.boot.dto.RcareerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service ("RcareerService")
public class RcareerServiceImpl implements RcareerService {
	
	@Autowired
	private SqlSession sqlSession;

    @Override
    public void addRcareer(HashMap<String, String> param) {
    	RcareerDAO rcareer = sqlSession.getMapper(RcareerDAO.class);
    	rcareer.createRcareer(param);
    }
    
	/*
	 * @Override public RcareerDTO getRcareerByUserId(String puserid) { Rcareer
	 * rcareer = rcareerDAO.getRcareer(puserid); return convertToDTO(rcareer); }
	 * 
	 * @Override public void updateRcareer(RcareerDTO rcareerDTO) { Rcareer rcareer
	 * = convertToEntity(rcareerDTO); rcareerDAO.updateRcareer(rcareer); }
	 * 
	 * @Override public void deleteRcareer(String puserid) {
	 * rcareerDAO.deleteRcareer(puserid); }
	 * 
	 * private Rcareer convertToEntity(RcareerDTO rcareerDTO) { }
	 * 
	 * private RcareerDTO RcareerDTO(Rcareer rcareer) { }
	 * 
	 * @Override public void addRcareer(HashMap<String, String> param) {
	 * 
	 * 
	 * }
	 */
}
