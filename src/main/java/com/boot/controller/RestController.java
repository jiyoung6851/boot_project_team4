package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.ImgtbDTO;
import com.boot.dto.JobaplytbDTO;
import com.boot.dto.ResumetbDTO;
import com.boot.service.CoinfotbService;
import com.boot.service.CusertbService;
import com.boot.service.ImgtbService;
import com.boot.service.JobaplyService;
import com.boot.service.JobposttbService;
import com.boot.service.PusertbService;
import com.boot.service.ResumeService;
import com.boot.service.ScribeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private PusertbService pservice;
	
	@Autowired
	private CusertbService cservice;
	
	@Autowired
	private ResumeService resumeservice;
	
	@Autowired
	private JobaplyService jobaplyservice;
	
	@Autowired
	private ImgtbService imgtbservice;
	
	@Autowired
	private JobposttbService jobpostservice;
	
	@Autowired
	private CoinfotbService coinfoservice;
	
	@Autowired
	private ScribeService scribeservice;
	
	@PostMapping("/RestRegisterInsert_p")
	public ResponseEntity<String> RestRegisterInsert_p(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterInsert_p");
		log.info("@# param => "+ param);
		
		pservice.pRegisterInsert(param);

		return ResponseEntity.ok("success");
	}
	
	@PutMapping("/RestRegisterUpdate_p")
	public ResponseEntity<String> RestRegisterUpdate_p(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterUpdate_p");
        log.info("@# controller param => "+param);
        
		pservice.PModify(param);
		
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("RestRegisterDelete_p")
	public ResponseEntity<String> RestRegisterDelete_p(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# RestRegisterDelete_p");
        log.info("@# controller param => "+param);
        
        //이력서 전체 검색(계정 기준)
        int cnt = resumeservice.resumeAll(param).size();
        ArrayList<ResumetbDTO> resumelist = resumeservice.resumeAll(param);
        //이력서에 등록한 사진 파일 삭제
        for (int i = 0; i < cnt; i++) {
        	//등록한 이미지 삭제
        	ImgtbDTO img = new ImgtbDTO();
        	img.setUsetb("resumetb");
        	img.setGubun(param.get("puserid")+"_"+resumelist.get(i).getProno());
        	
        	imgtbservice.imgdelete_coifno(img); //해당 이력서 관련 사진 삭제, 해당 메소드는 imgno 키가 필요 없음
		}
		
        cnt = jobaplyservice.jobaply_p_selectAll(param).size();
        ArrayList<JobaplytbDTO> jobaplylist = jobaplyservice.jobaply_p_selectAll(param);
        //지원한 이력 현황 삭제
        for (int i = 0; i < cnt; i++) {
        	param.put("cuserid", jobaplylist.get(i).getCuserid());
			param.put("csrno", jobaplylist.get(i).getCsrno()+"");
			param.put("jobno", jobaplylist.get(i).getJobno()+"");
			
			//jobposttb 에서 supno - 1
			jobpostservice.decreaseSupno(param); //탈퇴 회원으로 인한 지원자 수 감소
		}
        
		jobaplyservice.jobaply_p_all_delete(param); //지원 이력 내역 전체 삭제
        resumeservice.resumeAllDelete(param); //작성한 이력서 전체 삭제
        scribeservice.deleteAll_p(param); //스크랩 관련 삭제
        pservice.PDelete(param); //회원 정보 삭제
        
		//로그인 상태에서 탈퇴 진행 -> 세션 삭제
		session.invalidate();
		
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("RestRegisterSelect_p")
	public String RestRegisterSelect_p(@RequestParam HashMap<String, String> param) {
		return null;
	}
	
	@PostMapping("/RestRegisterInsert_c")
	public ResponseEntity<String> RestRegisterInsert_c(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterInsert_c");
		log.info("@# param => "+ param);
		
		cservice.cRegisterInsert(param);

		return ResponseEntity.ok("success");
	}
	
	@PutMapping("/RestRegisterUpdate_c")
	public ResponseEntity<String> RestRegisterUpdate_c(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterUpdate_c");
        log.info("@# controller param => "+param);
        
		cservice.CModify(param);
		
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("RestRegisterDelete_c")
	public ResponseEntity<String> RestRegisterDelete_c(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# RestRegisterDelete_c");
        log.info("@# controller param => "+param);
        
        //이미지 파일 삭제 //회사 정보에 존재하는 이미지 삭제
        ImgtbDTO img = new ImgtbDTO();
        img.setUsetb("coinfotb");
        img.setGubun(param.get("cuserid")+"_1");
        imgtbservice.imgdelete_coifno(img);
        
        //공고 이력 삭제
        jobpostservice.deleteAll_c(param);

        //회사 정보 삭제
        coinfoservice.delete(param);
        
        //스크랩 테이블에서 삭제
        scribeservice.deleteAll_c(param);
        
        //계정 삭제
		cservice.CDelete(param);
		
		//로그인 후 회원 탈퇴 진행, 세션 삭제
		session.invalidate();
		
		return ResponseEntity.ok("success");
	} 
	
}