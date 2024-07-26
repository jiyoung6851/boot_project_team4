package com.boot.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.ScribeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



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
}