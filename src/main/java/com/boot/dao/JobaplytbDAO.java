package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.JobaplytbDTO;

@Mapper
public interface JobaplytbDAO {
	public ArrayList<JobaplytbDTO> jobaplylist_p(HashMap<String, String> param);
	public int getTotalCount(HashMap<String, String> param);
	public void jobaply_prcnt(HashMap<String, String> param); //기업에서 이력서 확인시, prcnt = 1로 업데이트
	public void jobaply_delete(HashMap<String, String> param); //공고 삭제 시, 해당 공고의 지원 이력 삭제 
	public void insertjobapply(HashMap<String, String> param); //이력서 지원
	public int jobaplycnt(HashMap<String, String> param); //이력서 지원 여부 확인
	
	/* 기업에서 보는 지원자 현황 */
	public ArrayList<JobaplytbDTO> jobaplylist(HashMap<String, String> param);
	public int jobaplylist_count(HashMap<String, String> param);
}
