package com.boot.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.ScribeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ScribeController {

	@Autowired
	ScribeService service;
	
	@RequestMapping("/scribe")
	public String scribe(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# scribe");
		String authorid = (String) session.getAttribute("id");
		param.put("authorid", authorid);
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		int total = service.allcount_p(param);
		
		model.addAttribute("scribelist", service.allselect_p(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		return "scribe/scribe";
	}
	
	@RequestMapping("scribed_p")
	public ResponseEntity<String> scribee_p(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# scribee_p");
		log.info("@# param => " + param);
		String authorid = (String) session.getAttribute("id");
		String result = "";
		
		// authorid가 null이면 요청을 처리하지 않고 "Unauthorized" 반환
	    if (authorid == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed");
	    }

	    // 파라미터에서 gubun 값을 안전하게 가져옴
	    String gubun = param.get("gubun");
	    if (gubun == null) {
	        return ResponseEntity.badRequest().body("failed");
	    }
		
		if(authorid != null) {
			param.put("authorid", authorid);
			param.put("scribeid", param.get("writer")); //스크랩하려는 id
			
			if(param.get("gubun").equals("T")) { //현재 등록 상태
				//관심 삭제
				service.scribe_p_delete(param);
				result = "F";
			} else { //현재 미등록 상태
				//관심 등록
				service.scribe_p_insert(param);
				result = "T";
			}
		}
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping("/scribe_search")
	public String scribe_search(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# scribe_search");
		String authorid = (String) session.getAttribute("id");
		param.put("authorid", authorid);
		cri.setPageNum(1); //페이지 1페이지로 이동
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		log.info("@# param => " + param);
		int total = service.allcount_p(param);
		
		model.addAttribute("scribelist", service.allselect_p(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		log.info("total: "+total);
		log.info("service.allselect_p(param): "+service.allselect_p(param).size());
		
		return "scribe/scribe";
	}
}