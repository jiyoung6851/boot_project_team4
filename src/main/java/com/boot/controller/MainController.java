package com.boot.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.CoinfotbDTO;
import com.boot.dto.JobposttbDTO;
import com.boot.service.CoinfotbService;
import com.boot.service.JobaplyService;
import com.boot.service.RecruitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	private RecruitService service;
	
	@Autowired
	private JobaplyService jobaplyservice;
	
	@Autowired
	private CoinfotbService coinfoservice;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:main";
	}
	
	@RequestMapping("/recruitshowform")
	public String recruitshowform(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitshowform");
		log.info("@# param =>"+param);
		String nowid = (String)session.getAttribute("id"); //현재 접속한 아이디
		String usergubun = (String)session.getAttribute("usergubun"); //사용자 구분
		String gubun = "";
		
		log.info("@# writer => "+param.get("writer"));//공고 작성자 id(기업)
		param.put("cuserid", param.get("writer"));
		
		JobposttbDTO jobinfoData = service.recruitinfo(param);
		log.info("@# jobinfoData: " + jobinfoData);
		
		CoinfotbDTO coinfo = coinfoservice.Coinfotbinfo(param);
		
		if(usergubun == null) usergubun = "p";
		
		if(usergubun.equals("p")) param.put("puserid", nowid);
		else param.put("cuserid", nowid);
		
		int cnt = jobaplyservice.jobaplycnt(param);
		log.info("@# cnt => "+cnt);
		
		if(usergubun.equals("p") && cnt == 0) {
			gubun = "p";
		} else if (usergubun.equals("p") && cnt > 0) {
			gubun = "ps";
		} else if(usergubun.equals("c") && nowid.equals(param.get("writer"))) {
			gubun = "cs";
		} else {
			gubun = "c";
		}
		
		model.addAttribute("jobinfoData", jobinfoData);
		model.addAttribute("companyInfo", coinfo);
		model.addAttribute("status", gubun);
		
		return "recruit/recruitinfo";
	}
}