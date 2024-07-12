package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dao.JobposttbDAO;
import com.boot.dto.CareertbDTO;
import com.boot.dto.CoinfotbDTO;
import com.boot.dto.EdugbtbDTO;
import com.boot.dto.JobposttbDTO;
import com.boot.dto.WrktygbtbDTO;
import com.boot.service.CareertbService;
import com.boot.service.CoinfotbService;
import com.boot.service.EdugbtbService;
import com.boot.service.JobaplyService;
import com.boot.service.RecruitService;
import com.boot.service.WrktygbtbService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecruitController {
	
	@Autowired
	private RecruitService service;
	
	@Autowired
	private CareertbService Cservice;
	
	@Autowired
	private EdugbtbService Eservice;
	
	@Autowired
	private WrktygbtbService Wservice;
	
	@Autowired
	private JobaplyService jobaplyservice;
	
	@Autowired
	private CoinfotbService coinfoservice;
	
	@RequestMapping("/recruitadd")
	public String recruitadd(Model model) {
		log.info("@# recruitadd");
		
		ArrayList<CareertbDTO> cdto = Cservice.selectAll(); //경력 구분
		ArrayList<EdugbtbDTO> edto = Eservice.selectAll(); //학력 구분
		ArrayList<WrktygbtbDTO> wdto = Wservice.selectAll(); //고용 형태
		
		model.addAttribute("prsup", cdto); //경력
		model.addAttribute("educa", edto); //학력
		model.addAttribute("wrkty", wdto); //고용 형태
		
		return "recruit/recruitadd";
	}

	@GetMapping("/recruitmodify")
	public String recruitmodify(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitmodify");
		
		String cuserid = (String) session.getAttribute("id");
		
		param.put("cuserid", cuserid);
		
		log.info("@# cuserid: "+cuserid);
		log.info("@# csrno=> "+param.get("csrno"));
		log.info("@# jobno=> "+param.get("jobno"));
		
		ArrayList<CareertbDTO> cdto = Cservice.selectAll(); //경력 구분
		ArrayList<EdugbtbDTO> edto = Eservice.selectAll(); //학력 구분
		ArrayList<WrktygbtbDTO> wdto = Wservice.selectAll(); //고용 형태
		
		model.addAttribute("prsup", cdto); //경력
		model.addAttribute("educa", edto); //학력
		model.addAttribute("wrkty", wdto); //고용 형태
		
		JobposttbDTO dto = service.recruitinfo(param);
		log.info("recruitinfo => "+dto);
		model.addAttribute("recruitlist", dto);
		
		return "recruit/recruitmodify";
	}

	@RequestMapping("/recruitinsert")
	public String recruitadd(@RequestParam HashMap<String, String> param, Model model, HttpSession session, String defaultValue) {
		log.info("@# recruitadd");
		
		String cuserid = (String) session.getAttribute("id");
		
		param.put("cuserid", cuserid);
		param.put("csrno", "1");
		
		if (param.get("salary") == null || param.get("salary").trim().isEmpty()) {
			param.put("salary", defaultValue);
		}
		
		if (param.get("recno") == null || param.get("recno").trim().isEmpty()) {
			param.put("recno", "0");
		}
		
		service.recruitadd(param);

		JobposttbDTO jobinfoData = service.recruitinfo(param);
		log.info("@# jobinfoData: " + jobinfoData);
		model.addAttribute("jobinfoData", jobinfoData);

		return "recruit/recruitinfo";
	}
	
	@GetMapping("/recruitshow")
	public String recruitshow(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitshow");

		String cuserid = (String)session.getAttribute("id");
		
		param.put("cuserid", cuserid);
		
		log.info("@# cuserid => "+ cuserid);
		log.info("@# csrno => "+ param.get("csrno"));
		log.info("@# jobno => "+ param.get("jobno"));
		
		JobposttbDTO jobinfoData = service.recruitinfo(param);
		
		CoinfotbDTO coinfo = coinfoservice.Coinfotbinfo(param);
		
		log.info("@# jobinfoData: " + jobinfoData);
		model.addAttribute("jobinfoData", jobinfoData);
		model.addAttribute("companyInfo", coinfo);
		model.addAttribute("status", "cs");
		
		return "recruit/recruitinfo";
	}
	
	@RequestMapping("/recruitlist")
	public String recruitlist(HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# Displaying recruit list");
		
		String cuserid = (String) session.getAttribute("id");
		param.put("cuserid", cuserid);
		
		ArrayList<JobposttbDTO> jobinfoList = service.recruitlist(param);
		
		log.info("@# jobinfoList: " + jobinfoList);
		model.addAttribute("list", jobinfoList);
		
		return "recruit/recruitlist";
	}
	
	@GetMapping("/recruitupdate")
    public String updateRecruit(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("@# recruitmodify");
        log.info("@# param=>"+param);
        
        String cuserid = (String)session.getAttribute("id");
		param.put("cuserid", cuserid);
		
		//급여 미 입력 시, 0 처리
		if(param.get("salary").equals("") || param.get("salary") == null) param.put("salary", "0");

        service.recruitupdate(param);
        
        model.addAttribute("csrno", param.get("csrno"));
        model.addAttribute("jobno", param.get("jobno"));
        
        return "redirect:recruitshow?csrno="+param.get("csrno")+"&jobno="+param.get("jobno");
	}
	
	@RequestMapping("/recruitdelete")
	public String deleteRecruit(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# recruitdelete");
		log.info("@# param=>"+param);
		
		String cuserid = (String)session.getAttribute("id");
		param.put("cuserid", cuserid);
		
		//공고 이력 삭제
		service.recruitdelete(param);
		
		//해당 공고에 지원한 이력 삭제(jobaplytb)
		jobaplyservice.jobaply_delete(param);
		
		return "redirect:recruitlist";
	}
	
	@RequestMapping("/recruitinfo_p")
	public String recruitshow_p(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitshow_p");

		String puserid = (String)session.getAttribute("id");
		
		param.put("puserid", puserid);
		
		log.info("@# cuserid => "+ puserid);
		log.info("@# csrno => "+ param.get("csrno"));
		log.info("@# jobno => "+ param.get("jobno"));
		
		JobposttbDTO jobinfoData = service.recruitinfo(param);
		log.info("@# jobinfoData: " + jobinfoData);
		model.addAttribute("jobinfoData", jobinfoData);
		model.addAttribute("status", "ps");
		
		return "recruit/recruitinfo";
	}
}
