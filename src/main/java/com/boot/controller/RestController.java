package com.boot.controller;

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

import com.boot.service.CusertbService;
import com.boot.service.PusertbService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private PusertbService pservice;
	
	@Autowired
	private CusertbService cservice;
	
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
        
		pservice.PDelete(param);
		
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
        
		cservice.CDelete(param);
		
		//로그인 후 회원 탈퇴 진행, 세션 삭제
		session.invalidate();
		
		return ResponseEntity.ok("success");
	} 
	
}