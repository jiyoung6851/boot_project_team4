package com.boot.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Offerservice")
public class OfferServiceImpl implements OfferService {
	@Autowired
	private SqlSession sqlSession;
	
}
