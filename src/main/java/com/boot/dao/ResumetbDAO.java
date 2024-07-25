package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.ResumetbDTO;

@Mapper
public interface ResumetbDAO {
    public void resumesave(HashMap<String, String> param); //이력서 저장
	public ArrayList<ResumetbDTO> resumelist(HashMap<String, String> param); //저장된 이력서 리스트
	public ResumetbDTO resumeselect(HashMap<String, String> param); //선택한 리스트 출력
	public int getMaxProno(String puserid); //이력서 저장 후 이력서 번호 최댓값 가져오기
	public void resumedelete(HashMap<String, String> param); //선택한 이력서 삭제
	public ResumetbDTO resume_view(HashMap<String, String> param); //이력서 뷰어에서 출력
	public ArrayList<ResumetbDTO> getResumeList(String puserid); //지원하기 팝업창에서 이력서 목록 출력
	public int getTotalCount(HashMap<String, String> param); // 이력서 갯수
	public void resumeupdate(HashMap<String, String> param); //이력서 업데이트
}